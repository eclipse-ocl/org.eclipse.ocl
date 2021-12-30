/*******************************************************************************
 * Copyright (c) 2013, 2021 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	E.D.Willink (CEA LIST) - initial API and implementation
 *  Obeo - Implement constraints validation
 *******************************************************************************/
package org.eclipse.ocl.examples.validity.locator;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.TreeIterator;
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
import org.eclipse.ocl.examples.emf.validation.validity.locator.AbstractConstraintLocator;
import org.eclipse.ocl.examples.emf.validation.validity.locator.ConstraintLocator;
import org.eclipse.ocl.examples.emf.validation.validity.manager.TypeURI;
import org.eclipse.ocl.examples.emf.validation.validity.manager.ValidityManager;
import org.eclipse.ocl.examples.emf.validation.validity.manager.ValidityModel;
import org.eclipse.ocl.examples.validity.plugin.OCLValidityPlugin;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.validation.PivotEObjectValidator;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.xtext.base.utilities.ElementUtil;
import org.eclipse.ocl.xtext.basecs.ModelElementCS;

public class PivotConstraintLocator extends AbstractConstraintLocator
{
	public static @NonNull PivotConstraintLocator INSTANCE = new PivotConstraintLocator();

	@Override
	public @NonNull Set<@NonNull TypeURI> getAllTypes(@NonNull ValidityManager validityManager, @NonNull EObject constrainingObject) {
	//	if (constrainingObject instanceof org.eclipse.ocl.pivot.Class) {
			EnvironmentFactory environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
			if (environmentFactory != null) {
				Set<@NonNull TypeURI> allTypes = new HashSet<@NonNull TypeURI>();
				CompleteClass completeClass = environmentFactory.getCompleteModel().getCompleteClass((org.eclipse.ocl.pivot.Class)constrainingObject);
				for (CompleteClass superCompleteClass : completeClass.getSuperCompleteClasses()) {
					for (org.eclipse.ocl.pivot.Class partialClass : superCompleteClass.getPartialClasses()) {
						EObject eTarget = partialClass.getESObject();
						if (eTarget != null) {
							allTypes.add(validityManager.getTypeURI(eTarget));
						}
					}
				}
				return allTypes;
			}
	//	}
		return super.getAllTypes(validityManager, constrainingObject);
	}

	protected EObject getConstrainedESObject(@NonNull EnvironmentFactory environmentFactory, @NonNull Constraint asConstraint) {
		Namespace constrainedElement = asConstraint.getContext();
		if (constrainedElement instanceof org.eclipse.ocl.pivot.Class) {
			CompleteClass completeClass = environmentFactory.getCompleteModel().getCompleteClass((org.eclipse.ocl.pivot.Class)constrainedElement);
			for (org.eclipse.ocl.pivot.Class partialClass : completeClass.getPartialClasses()) {
				EObject esObject = partialClass.getESObject();
				if (esObject != null) {
					return esObject;
				}
			}
		}
		if (constrainedElement != null) {
			MetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
			return metamodelManager.getEcoreOfPivot(EModelElement.class, constrainedElement);
		}
		return null;
	}

	@Override
	public @Nullable Map<@NonNull EObject, @NonNull List<@NonNull LeafConstrainingNode>> getConstraints(@NonNull ValidityModel validityModel,
		@NonNull EPackage ePackage, @NonNull Set<@NonNull Resource> resources, @NonNull Monitor monitor) {
		Map<@NonNull EObject, @NonNull List<@NonNull LeafConstrainingNode>> map = null;
		for (@NonNull Resource resource : resources) {
			if (monitor.isCanceled()) {
				return null;
			}
			ASResource asResource = null;
			if (resource instanceof ASResource) {
				asResource = (ASResource) resource;
			}
			if (asResource != null) {
				EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
				if (environmentFactory != null) {
					for (TreeIterator<EObject> tit = asResource.getAllContents(); tit.hasNext(); ) {
						if (monitor.isCanceled()) {
							return null;
						}
						EObject eObject = tit.next();
						if (eObject instanceof Constraint) {
							Constraint asConstraint = (Constraint)eObject;
							EObject esObject = getConstrainedESObject(environmentFactory, asConstraint);
							if (esObject != null) {
								@NonNull String label = String.valueOf(asConstraint.getName());
								map = createLeafConstrainingNode(map, validityModel, esObject, asConstraint, label);
							}
						}
					}
				}
			}
		}
		return map;
	}

	@Override
	public Object getImage() {
		return OCLValidityPlugin.INSTANCE.getImage("OCLModelFile.gif");
	}

	@Override
	public @NonNull ConstraintLocator getInstance() {
		return INSTANCE;
	}

	@Override
	public @NonNull String getName() {
		return "Complete OCL constraints";
	}

	@Override
	public @Nullable String getSourceExpression(@NonNull LeafConstrainingNode node) {
		Object constrainingObject = node.getConstrainingObject();
		if (!(constrainingObject instanceof Constraint)) {
			return null;
		}
		LanguageExpression specification = ((Constraint)constrainingObject).getOwnedSpecification();
		if (specification == null) {
			return null;
		}
		ModelElementCS csElement = ElementUtil.getCsElement(specification);
		if (csElement == null) {
			return null;
		}
		return ElementUtil.getText(csElement);
	}

	@Override
	public @Nullable Resource getSourceResource(@NonNull LeafConstrainingNode node) {
		Object constrainingObject = node.getConstrainingObject();
		if (!(constrainingObject instanceof Constraint)) {
			return null;
		}
		ModelElementCS csElement = ElementUtil.getCsElement((Constraint)constrainingObject);
		if (csElement == null) {
			return null;
		}
		return csElement.eResource();
	}

	@Override
	public @Nullable TypeURI getTypeURI(@NonNull EObject constrainedObject) {
	//	if (constrainedObject instanceof org.eclipse.ocl.pivot.Class) {
			EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
			if (environmentFactory != null) {
				CompleteClass completeClass = environmentFactory.getCompleteModel().getCompleteClass((org.eclipse.ocl.pivot.Class)constrainedObject);
				for (org.eclipse.ocl.pivot.Class partialClass : completeClass.getPartialClasses()) {
					EObject eTarget = partialClass.getESObject();
					if (eTarget != null) {
						return super.getTypeURI(eTarget);
					}
				}
				MetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
				EObject eTarget = metamodelManager.getEcoreOfPivot(EObject.class, (org.eclipse.ocl.pivot.Class)constrainedObject);
				if (eTarget != null) {
					return super.getTypeURI(eTarget);
				}
			}
	//	}
		return super.getTypeURI(constrainedObject);
	}

	@Override
	public void validate(@NonNull Result result, @NonNull ValidityManager validityManager, @Nullable Monitor monitor) {
		Severity severity = Severity.UNKNOWN;
		try {
			Constraint constraint = (Constraint) result.getLeafConstrainingNode().getConstrainingObject();
			if (constraint != null){
				EObject eObject = result.getValidatableNode().getConstrainedObject();
				try {
					ResourceSet resourceSet = eObject.eResource().getResourceSet();
					if (resourceSet != null) {
						Map<Object, Object> context = validityManager.createDefaultContext();
						context.put(Monitor.class,  monitor);
						Diagnostic diagnostic = PivotEObjectValidator.INSTANCE.validate(constraint, eObject, context);
						result.setDiagnostic(diagnostic);
						severity = diagnostic != null ? getSeverity(diagnostic) : Severity.OK;
					}
				} catch (Throwable e) {
					result.setException(e);
					severity = Severity.FATAL;
				}
			}
		} finally {
			result.setSeverity(severity);
		}
	}
}