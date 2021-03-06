/*******************************************************************************
 * Copyright (c) 2013, 2021 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	E.D.Willink (CEA LIST) - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.validity.locator;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.emf.validation.validity.LeafConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.validity.Result;
import org.eclipse.ocl.examples.emf.validation.validity.Severity;
import org.eclipse.ocl.examples.emf.validation.validity.ValidatableNode;
import org.eclipse.ocl.examples.emf.validation.validity.locator.ConstraintLocator;
import org.eclipse.ocl.examples.emf.validation.validity.manager.ConstrainingURI;
import org.eclipse.ocl.examples.emf.validation.validity.manager.TypeURI;
import org.eclipse.ocl.examples.emf.validation.validity.manager.ValidityManager;
import org.eclipse.ocl.examples.emf.validation.validity.manager.ValidityModel;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.evaluation.AbstractConstraintEvaluator;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.util.DerivedConstants;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.ValueSpecification;

public class UMLConstraintLocator extends AbstractPivotConstraintLocator
{
	public static @NonNull UMLConstraintLocator INSTANCE = new UMLConstraintLocator();

	protected void appendPath(@NonNull StringBuilder s, @NonNull NamedElement eObject) {
		EObject eContainer = eObject.eContainer();
		if (eContainer instanceof NamedElement) {
			appendPath(s, (NamedElement)eContainer);
			s.append("::");
		}
		s.append(eObject.getName());
	}

	@Override
	public @NonNull Set<@NonNull TypeURI> getAllTypes(@NonNull ValidityManager validityManager, @NonNull EObject constrainingType) {
		if (constrainingType instanceof org.eclipse.uml2.uml.Class) {
			Set<@NonNull TypeURI> allTypes = new HashSet<@NonNull TypeURI>();
			allTypes.add(validityManager.getTypeURI(constrainingType));
			if (constrainingType instanceof org.eclipse.uml2.uml.Class) {
				getAllTypes(allTypes, validityManager, ((org.eclipse.uml2.uml.Class)constrainingType).getSuperClasses());
			}
			return allTypes;
		}
		else {
			return super.getAllTypes(validityManager, constrainingType);
		}
	}

	private void getAllTypes(Set<@NonNull TypeURI> knownTypes, @NonNull ValidityManager validityManager, Iterable<org.eclipse.uml2.uml.Class> moreTypes) {
		for (org.eclipse.uml2.uml.Class anotherType : moreTypes) {
			if ((anotherType != null) && knownTypes.add(validityManager.getTypeURI(anotherType))) {
				getAllTypes(knownTypes, validityManager, anotherType.getSuperClasses());
			}
		}
	}

	@Override	// FIXME is it getConstrainingURI or getTypeURI that needs the overload - no JUnit test uses the code
	public @Nullable ConstrainingURI getConstrainingURI(@NonNull EObject eObject) {
		EObject eContainer = eObject;
		for ( ; true; eContainer = eContainer.eContainer()) {
			if (eContainer == null) {
				return null;
			}
			if (eContainer instanceof Package) {
				break;
			}
		}
		String nsURI = null;
		Stereotype appliedStereotype = ((Package)eContainer).getAppliedStereotype("Ecore::EPackage");
		if (appliedStereotype != null) {
			Object value = ((Package)eContainer).getValue(appliedStereotype, "nsURI");
			if (value != null) {
				nsURI = value.toString();
			}
		}
		if (nsURI == null) {
			nsURI = ((Package)eContainer).getURI();
		}
		if (nsURI == null) {
			return null;
		}
		Resource resource = eObject.eResource();
		if (resource == null) {
			return null;
		}
		String uriFragment = resource.getURIFragment(eObject);
		if (uriFragment == null) {
			return null;
		}
		if (!uriFragment.startsWith("//")) {
			uriFragment = "//" + uriFragment;		// FIXME regularize this ?? UML2Ecore
		}
		@NonNull URI constrainingURI = URI.createURI(nsURI).appendFragment(uriFragment);
		return new ConstrainingURI(constrainingURI);
	}

	@Override
	public @Nullable Map<@NonNull EObject, @NonNull List<@NonNull LeafConstrainingNode>> getConstraints(@NonNull ValidityModel validityModel,
			@NonNull EPackage ePackage, @NonNull Set<@NonNull Resource> resources, @NonNull Monitor monitor) {
		Map<@NonNull EObject, @NonNull List<@NonNull LeafConstrainingNode>> map = null;
		for (@NonNull Resource resource : resources) {
			if (monitor.isCanceled()) {
				return null;
			}
			for (TreeIterator<EObject> tit = resource.getAllContents(); tit.hasNext(); ) {
				EObject eObject = tit.next();
				if (eObject instanceof Constraint) {
					Constraint umlConstraint = (Constraint)eObject;
					Element contextElement = umlConstraint.getContext();
					if (contextElement instanceof Type) {
						@NonNull String label = String.valueOf(umlConstraint.getName());
						/*					LeafConstrainingNode constraint = validityModel.createLeafConstrainingNode();
						constraint.setConstraintLocator(this);
						constraint.setConstrainingObject(umlConstraint);
						constraint.setLabel(label);
						ConstrainingNode constrainingNode = validityModel.getConstrainingNode(constrainedElement);
						constrainingNode.getChildren().add(constraint);
						if (map == null) {
							map = new HashMap<EModelElement, List<LeafConstrainingNode>>();
						}
						List<LeafConstrainingNode> constraints = map.get(constrainedElement);
						if (constraints == null) {
							constraints = new ArrayList<LeafConstrainingNode>();
							map.put(constrainedElement, constraints);
						}
						constraints.add(constraint); */
						//						EClass eC = constrainedElement.eClass();
						map = createLeafConstrainingNode(map, validityModel, contextElement, umlConstraint, label);
					}
				}
				if (monitor.isCanceled()) {
					return null;
				}
			}
		}
		return map;
	}

	@Override
	public @Nullable Collection<@NonNull Resource> getImports(@NonNull EPackage ePackage, @NonNull Resource resource) {
		Set<@NonNull Resource> imports = new HashSet<@NonNull Resource>();
		for (TreeIterator<EObject> tit = resource.getAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof PackageImport) {
				PackageImport umlPackageImport = (PackageImport)eObject;
				Package importedPackage = umlPackageImport.getImportedPackage();
				if (importedPackage != null) {
					Resource eResource = importedPackage.eResource();
					if (eResource != null) {
						imports.add(eResource);
					}
				}
				//				tit.prune();
			}
			//			else if (eObject instanceof Type) {
			//				tit.prune();
			//			}
			Resource eResource = eObject.eClass().eResource();
			if (eResource != null) {
				imports.add(eResource);
			}
		}
		return imports;
	}

	@Override
	public @NonNull ConstraintLocator getInstance() {
		return INSTANCE;
	}

	@Override
	public @NonNull String getLabel(@NonNull EModelElement eObject) {
		if (eObject instanceof NamedElement) {			// FIXME debugging - remove UML dependency
			StringBuilder s = new StringBuilder();
			appendPath(s, (NamedElement)eObject);
			return s.toString();
		}
		else {
			return super.getLabel(eObject);
		}
	}

	@Override
	public @NonNull String getName() {
		return "UML Constraints";
	}

	@Override
	public @Nullable String getSourceExpression(@NonNull LeafConstrainingNode node) {
		Object constrainingObject = node.getConstrainingObject();
		if (!(constrainingObject instanceof Constraint)) {
			return null;
		}
		ValueSpecification specification = ((Constraint)constrainingObject).getSpecification();
		if (!(specification instanceof OpaqueExpression)) {
			return null;
		}
		List<String> bodies = ((OpaqueExpression)specification).getBodies();
		return bodies.size() > 0 ? bodies.get(0) : null;
	}

	@Override
	public @Nullable Resource getSourceResource(@NonNull LeafConstrainingNode node) {
		Object constrainingObject = node.getConstrainingObject();
		if (!(constrainingObject instanceof EObject)) {
			return null;
		}
		return ((EObject)constrainingObject).eResource();
	}

	@Override
	public @Nullable TypeURI getTypeURI(@NonNull EObject eObject) {
		EObject eContainer = eObject;
		for ( ; true; eContainer = eContainer.eContainer()) {
			if (eContainer == null) {
				return null;
			}
			if (eContainer instanceof Package) {
				break;
			}
		}
		String nsURI = null;
		Stereotype appliedStereotype = ((Package)eContainer).getAppliedStereotype("Ecore::EPackage");
		if (appliedStereotype != null) {
			Object value = ((Package)eContainer).getValue(appliedStereotype, "nsURI");
			if (value != null) {
				nsURI = value.toString();
			}
		}
		if (nsURI == null) {
			nsURI = ((Package)eContainer).getURI();
		}
		if (nsURI == null) {
			return null;
		}
		Resource resource = eObject.eResource();
		if (resource == null) {
			return null;
		}
		String uriFragment = resource.getURIFragment(eObject);
		if (!uriFragment.startsWith("//")) {
			uriFragment = "//" + uriFragment;		// FIXME regularize this ?? UML2Ecore
		}
		@NonNull URI typeURI = URI.createURI(nsURI).appendFragment(uriFragment);
		return new TypeURI(typeURI);
	}

	@Override
	public @Nullable Set<@NonNull TypeURI> getTypeURIs(@NonNull ValidityManager validityManager, @NonNull EObject validatableObject) {
		EClass eClass = validatableObject.eClass();
		if (eClass != null) {
			EAnnotation eAnnotation = eClass.getEAnnotation(DerivedConstants.UML2_UML_PACKAGE_2_0_NS_URI);
			if ((eAnnotation != null) && (eAnnotation.getReferences().size() > 0)) { // Stereotype application
				EObject umlClass = eAnnotation.getReferences().get(0);
				if (umlClass != null) {
					Set<@NonNull TypeURI> allTypeURIs = new HashSet<@NonNull TypeURI>();
					TypeURI typeURI = validityManager.getTypeURI(umlClass);
					allTypeURIs.add(typeURI);
					return allTypeURIs;
				}
			}
		}
		if (validatableObject instanceof InstanceSpecification) {
			Set<@NonNull TypeURI> allTypeURIs = new HashSet<@NonNull TypeURI>();
			for (org.eclipse.uml2.uml.Classifier umlClassifier : ((InstanceSpecification)validatableObject).getClassifiers()) {
				if (umlClassifier != null) {
					TypeURI typeURI = validityManager.getTypeURI(umlClassifier);
					allTypeURIs.add(typeURI);
				}
			}
			return allTypeURIs;
		}
		return null;
	}

	@Override
	public void validate(@NonNull Result result, @NonNull ValidityManager validityManager, @Nullable Monitor monitor) {
		ValidatableNode validatableNode = result.getValidatableNode();
		EObject contextObject = validatableNode.getConstrainedObject();
		LeafConstrainingNode leafConstrainingNode = result.getLeafConstrainingNode();
		org.eclipse.uml2.uml.Constraint umlConstraint = (org.eclipse.uml2.uml.Constraint)leafConstrainingNode.getConstrainingObject();
		if (umlConstraint == null) {
			return;
		}
		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension) PivotUtilInternal.getEnvironmentFactory(umlConstraint);
		PivotMetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		Severity severity = Severity.UNKNOWN;
		try {
			final org.eclipse.ocl.pivot.Constraint pivotConstraint = environmentFactory.getASOf(org.eclipse.ocl.pivot.Constraint.class, umlConstraint);
			if (pivotConstraint == null) {
				throw new ParserException("Failed to create pivot Constraint");
			}
			ResourceSet resourceSet = contextObject.eResource().getResourceSet();
			if (resourceSet != null) {
				ExpressionInOCL query = getQuery(metamodelManager, pivotConstraint);
				EvaluationVisitor evaluationVisitor = createEvaluationVisitor(environmentFactory, query, contextObject, monitor);
				AbstractConstraintEvaluator<Diagnostic> constraintEvaluator = new AbstractConstraintLocator(metamodelManager, query, contextObject)
				{
					@Override
					protected String getObjectLabel() {
						org.eclipse.ocl.pivot.Type type = PivotUtil.getContainingType(pivotConstraint);
						return type != null ? type.getName() : "??";
					}
				};
				@Nullable Diagnostic diagnostic = constraintEvaluator.evaluate(evaluationVisitor);
				result.setDiagnostic(diagnostic);
				severity = diagnostic != null ? getSeverity(diagnostic) : Severity.OK;
			}
		} catch (Throwable e) {
			result.setException(e);
			severity = Severity.FATAL;
		} finally {
			result.setSeverity(severity);
		}
	}
}
