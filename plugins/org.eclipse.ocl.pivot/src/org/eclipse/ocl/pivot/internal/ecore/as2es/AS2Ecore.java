/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ecore.as2es;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMIException;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.common.OCLConstants;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.delegate.DelegateInstaller;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.internal.utilities.AbstractConversion;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotObjectImpl;
import org.eclipse.ocl.pivot.options.OCLinEcoreOptions;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;

public class AS2Ecore extends AbstractConversion
{
	public static final Logger logger = Logger.getLogger(AS2Ecore.class);

	/**
	 * True to add comments to the invariant context and doagnostics parameters.
	 */
	public static final @NonNull String OPTION_ADD_INVARIANT_COMMENTS = "addInvariantComments";

	/**
	 * True to apply result = () wrapper to invariant body.
	 */
	public static final @NonNull String OPTION_BOOLEAN_INVARIANTS = DelegateInstaller.OPTION_BOOLEAN_INVARIANTS;

	/**
	 * True to apply a prefix to invariant names.
	 */
	public static final @NonNull String OPTION_INVARIANT_PREFIX = "invariantPrefix";

	/**
	 * True to suppress the UML2Ecore duplicates EAnnotation. This is an experimental internal option used during
	 * the auto-generation of Pivot.ecore..
	 */
	public static final @NonNull String OPTION_SUPPRESS_DUPLICATES = "suppressDuplicates";

	public static void copyAnnotationComments(@NonNull EAnnotation eModelElement, @NonNull Constraint pivotConstraint) {
		String key = DelegateInstaller.getAnnotationKey(pivotConstraint);
		EAnnotation commentAnnotation = eModelElement.getEAnnotation(PivotConstantsInternal.DOCUMENTATION_ANNOTATION_SOURCE);
		List<Comment> newComments = pivotConstraint.getOwnedComments();
		int iMax = newComments.size();
		if (iMax > 0) {
			if (commentAnnotation == null) {
				commentAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
				commentAnnotation.setSource(PivotConstantsInternal.DOCUMENTATION_ANNOTATION_SOURCE);
				eModelElement.getEAnnotations().add(commentAnnotation);
			}
			StringBuilder s = new StringBuilder();
			for (int iComment = 0; iComment < iMax; iComment++) {
				if (iComment > 0) {
					s.append("\n");
				}
				s.append(newComments.get(iComment).getBody());
			}
			commentAnnotation.getDetails().put(key, s.toString());
		}
		else if (commentAnnotation != null) {
			commentAnnotation.getDetails().remove(key);
		}
	}

	public static void copyComments(EModelElement eModelElement, Element pivotElement) {
		EList<EAnnotation> allEAnnotations = eModelElement.getEAnnotations();
		List<Comment> newComments = pivotElement.getOwnedComments();
		int iComment = 0;
		int iMax = newComments.size();
		List<EAnnotation> removals = null;
		for (EAnnotation eAnnotation : allEAnnotations) {
			if (PivotConstantsInternal.DOCUMENTATION_ANNOTATION_SOURCE.equals(eAnnotation.getSource())) {
				if (iComment >= iMax) {
					if (removals == null) {
						removals = new ArrayList<EAnnotation>();
					}
					removals.add(eAnnotation);
				}
				else {
					String body = newComments.get(iComment).getBody();
					eAnnotation.getDetails().put(PivotConstantsInternal.DOCUMENTATION_ANNOTATION_KEY, body);
				}
				iComment++;
			}
		}
		for ( ; iComment < iMax; iComment++) {
			EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
			eAnnotation.setSource(PivotConstantsInternal.DOCUMENTATION_ANNOTATION_SOURCE);
			String body = newComments.get(iComment).getBody();
			eAnnotation.getDetails().put(PivotConstantsInternal.DOCUMENTATION_ANNOTATION_KEY, body);
			allEAnnotations.add(eAnnotation);
		}
		if (removals != null) {
			allEAnnotations.removeAll(removals);
		}
	}

	public static @NonNull EOperation createConstraintEOperation(Constraint pivotConstraint, String operationName, @Nullable Map<String, Object> options) {
		if (options == null) {
			options = new HashMap<String, Object>();
		}
		boolean addInvariantComments = AS2Ecore.isAddInvariantComments(options);
		EOperation eOperation = EcoreFactory.eINSTANCE.createEOperation();
		eOperation.setName(operationName != null ? operationName : "");
		eOperation.setEType(EcorePackage.Literals.EBOOLEAN);
		{
			EParameter firstParameter = EcoreFactory.eINSTANCE.createEParameter();
			firstParameter.setName("diagnostics");
			firstParameter.setEType(EcorePackage.Literals.EDIAGNOSTIC_CHAIN);
			eOperation.getEParameters().add(firstParameter);
			if (addInvariantComments) {
				EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
				eAnnotation.setSource(GenModelPackage.eNS_URI);
				eAnnotation.getDetails().put("documentation", "The chain of diagnostics to which problems are to be appended.");
				firstParameter.getEAnnotations().add(eAnnotation);
			}
		}
		{
			EParameter secondParameter = EcoreFactory.eINSTANCE.createEParameter();
			secondParameter.setName("context");
			EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
			eGenericType.setEClassifier(EcorePackage.Literals.EMAP);
			EGenericType firstTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
			firstTypeArgument.setEClassifier(EcorePackage.Literals.EJAVA_OBJECT);
			eGenericType.getETypeArguments().add(firstTypeArgument);
			EGenericType secondTypeArgument = EcoreFactory.eINSTANCE.createEGenericType();
			secondTypeArgument.setEClassifier(EcorePackage.Literals.EJAVA_OBJECT);
			eGenericType.getETypeArguments().add(secondTypeArgument);
			secondParameter.setEGenericType(eGenericType);
			eOperation.getEParameters().add(secondParameter);
			if (addInvariantComments) {
				EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
				eAnnotation.setSource(GenModelPackage.eNS_URI);
				eAnnotation.getDetails().put("documentation", "The cache of context-specific information.");
				secondParameter.getEAnnotations().add(eAnnotation);
			}
		}
		LanguageExpression specification = pivotConstraint.getOwnedSpecification();
		if (specification != null) {
			String body = specification.getBody();
			if (body != null) {
				EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
				eAnnotation.setSource(getExportDelegateURI(options));
				if (DelegateInstaller.isBooleanInvariants(options)) {
					body = "result = (" + body + ")";
				}
				eAnnotation.getDetails().put("body", body);
				eOperation.getEAnnotations().add(eAnnotation);
			}
		}
		copyComments(eOperation, pivotConstraint);
		return eOperation;
	}

	public static @NonNull XMLResource createResource(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull Resource asResource, @NonNull URI ecoreURI, @Nullable Map<String,Object> options) {
		AS2Ecore converter = new AS2Ecore(environmentFactory, ecoreURI, options);
		return converter.convertResource(asResource, ecoreURI);
	}
	
	public static @NonNull Boolean getBoolean(@Nullable Map<String, Object> options, @NonNull String key) {
		if (options == null) {
			return false;
		}
		Object value = options.get(key);
		if (value instanceof Boolean) {
			return (Boolean) value;
		}
		if (value != null) {
			logger.error("Non-Boolean '" + key + "' for '" + value + "'");
		}
		return false;
	}

	public static @Nullable String getExportDelegateURI(@Nullable Map<String, Object> options) {
		String exportDelegateURI = options != null ? (String)options.get(OCLConstants.OCL_DELEGATE_URI) : null;
		return exportDelegateURI != null ? exportDelegateURI : OCLinEcoreOptions.EXPORT_DELEGATION_URI.getPreferredValue();
	}

	public static @Nullable String getInvariantPrefix(@Nullable Map<String, Object> options) {
		Object invariantPrefix = options != null ? options.get(OPTION_INVARIANT_PREFIX) : null;
		return invariantPrefix != null ? invariantPrefix.toString() : null;
	}
	
	public static @Nullable String getString(@Nullable Map<String, Object> options, @NonNull String key) {
		if (options == null) {
			return null;
		}
		Object value = options.get(key);
		if (value instanceof String) {
			return (String) value;
		}
		if (value != null) {
			logger.error("Non-String '" + key + "' for '" + value + "'");
		}
		return null;
	}

	public static boolean isAddInvariantComments(@NonNull Map<String,Object> options) {
		return Boolean.valueOf(String.valueOf(options.get(OPTION_ADD_INVARIANT_COMMENTS)));
	}

	public static boolean isBooleanInvariants(@NonNull Map<String,Object> options) {
		return Boolean.valueOf(String.valueOf(options.get(OPTION_BOOLEAN_INVARIANTS)));
	}

	/**
	 * Mapping of pivot elements to the resulting E elements.
	 */
	private final @NonNull Map<Element, EModelElement> createMap = new HashMap<Element, EModelElement>();

	/**
	 * Mapping of all E elements created during pass 1 that require further work
	 * with respect to the corresponding CS element in pass 2.
	 */
	private final @NonNull Set<Element> deferMap = new HashSet<Element>();
	
	private @Nullable List<Resource.Diagnostic> errors = null;
	
	protected final @NonNull Map<String,Object> options;
	protected final @NonNull DelegateInstaller delegateInstaller;
	protected final @NonNull AS2EcoreDeclarationVisitor pass1;	
	protected final @NonNull AS2EcoreReferenceVisitor pass2;
	protected final @NonNull URI ecoreURI;
	protected final @Nullable String primitiveTypesUriPrefix;
	
	public AS2Ecore(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull URI ecoreURI, @Nullable Map<String,Object> options) {
		super(environmentFactory);
		this.options = options != null ? options : new HashMap<String,Object>();
		this.delegateInstaller = new DelegateInstaller(environmentFactory, options);
		this.pass1 = new AS2EcoreDeclarationVisitor(this);	
		this.pass2 = new AS2EcoreReferenceVisitor(this);
		this.ecoreURI = ecoreURI;
		this.primitiveTypesUriPrefix = getString(options, PivotConstants.PRIMITIVE_TYPES_URI_PREFIX);
	}

	protected @Nullable Object convert(@NonNull Element pivotObject) {
		Object eObject = pass1.safeVisit(pivotObject);
		for (Element eKey : deferMap) {
			pass2.safeVisit(eKey);
		}
		return eObject;
	}

	public @NonNull XMLResource convertResource(@NonNull Resource asResource, @NonNull URI ecoreURI) {
		ResourceSet resourceSet = environmentFactory.getResourceSet();
		setGenerationInProgress(asResource, true);
		try {
			XMLResource ecoreResource = (XMLResource) resourceSet.createResource(ecoreURI);
			List<EObject> contents = ecoreResource.getContents();
			contents.clear();						// FIXME workaround for BUG 465326
			for (EObject eContent : asResource.getContents()) {
				if (eContent instanceof Model) {
					Object results = pass1.safeVisit((Model)eContent);
					if (results instanceof List<?>) {
						@SuppressWarnings("unchecked")
						List<EObject> results2 = (List<EObject>)results;
						contents.addAll(results2);
					}
				}
			}
			for (Element eKey : deferMap) {
				pass2.safeVisit(eKey);
			}
			for (Element pivotElement : createMap.keySet()) {
				EObject eObject = createMap.get(pivotElement);
				((PivotObjectImpl) pivotElement).setESObject(eObject);
			}
			return ecoreResource;
		}
		finally {
			setGenerationInProgress(asResource, false);
		}
	}

	public void defer(@NonNull Element pivotElement) {
		deferMap.add(pivotElement);
	}

	protected void error(@NonNull String message) {
		List<Diagnostic> errors2 = errors;
		if (errors2 == null) {
			errors = errors2 = new ArrayList<Resource.Diagnostic>();
		}
		errors2.add(new XMIException(message));
	}

	public <T extends EObject> @Nullable T getCreated(@NonNull Class<T> requiredClass, @NonNull Element pivotElement) {
		EModelElement eModelElement = createMap.get(pivotElement);
//		System.out.println("Get " + PivotUtil.debugSimpleName(pivotElement) + " " + PivotUtil.debugSimpleName(eModelElement));
		if (eModelElement == null) {
			Element primaryElement = metamodelManager.getPrimaryElement(pivotElement);
			if (pivotElement != primaryElement) {
				eModelElement = createMap.get(primaryElement);
			}
		}
		if (eModelElement == null) {
			return null;
		}
		if (!requiredClass.isAssignableFrom(eModelElement.getClass())) {
			logger.error("Ecore " + eModelElement.getClass().getName() + "' element is not a '" + requiredClass.getName() + "'"); //$NON-NLS-1$
			return null;
		}
		@SuppressWarnings("unchecked")
		T castElement = (T) eModelElement;
		return castElement;
	}

	public @NonNull DelegateInstaller getDelegateInstaller() {
		return delegateInstaller;
	}

	public final @NonNull URI getEcoreURI() {
		return ecoreURI;
	}

	public @NonNull Map<String, Object> getOptions() {
		return options;
	}

	public String getPrimitiveTypesUriPrefix() {
		return primitiveTypesUriPrefix;
	}

	/**
	 * Return the non-Null CollectionType if asType can use Ecore multiplicities to express the (outer) collection.
	 */
	public @Nullable CollectionType isEcoreCollection(@Nullable Type asType) {
		if (!(asType instanceof CollectionType)) {
			return null;
		}
		if (((CollectionType)asType).getUnspecializedElement() == standardLibrary.getCollectionType()) {
			return null;		// Collection(T) cannot be distinguished from concrete Ecore collections
		}
		return (CollectionType)asType;
	}

	/**
	 * Return tre if asPackage is a Pivot Metamodel.
	 */
	public boolean isPivot(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		List<org.eclipse.ocl.pivot.Class> asTypes = asPackage.getOwnedClasses();
		if (NameUtil.getNameable(asTypes, PivotPackage.Literals.ENUMERATION_LITERAL.getName()) == null) {
			return false;
		}
		if (NameUtil.getNameable(asTypes, PivotPackage.Literals.EXPRESSION_IN_OCL.getName()) == null) {
			return false;
		}
		if (NameUtil.getNameable(asTypes, PivotPackage.Literals.OPERATION_CALL_EXP.getName()) == null) {
			return false;
		}
		if (NameUtil.getNameable(asTypes, PivotPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION.getName()) == null) {
			return false;
		}
		return true;
	}

	public boolean isSuppressDuplicates() {
		return Boolean.valueOf(String.valueOf(options.get(OPTION_SUPPRESS_DUPLICATES)));
	}

	public void putCreated(@NonNull Element pivotElement, @NonNull EModelElement eModelElement) {
		Element primaryElement = metamodelManager.getPrimaryElement(pivotElement);
//		System.out.println("Put1 " + PivotUtil.debugSimpleName(pivotElement) + " " + PivotUtil.debugSimpleName(eModelElement));
		EModelElement oldPivot = createMap.put(pivotElement, eModelElement);
		assert oldPivot == null;
		if ((pivotElement != primaryElement) && !createMap.containsKey(primaryElement)) {
//			System.out.println("Put2 " + PivotUtil.debugSimpleName(pivotElement) + " " + PivotUtil.debugSimpleName(eModelElement));
			createMap.put(primaryElement, eModelElement);
		}
	}

	protected void setGenerationInProgress(@NonNull Resource asResource, boolean isLoading) {
		for (EObject eRoot : asResource.getContents()) {
			if (eRoot instanceof Model) {
				for (org.eclipse.ocl.pivot.Package asPackage : ((Model)eRoot).getOwnedPackages()) {
					if (asPackage != null) {
						setGenerationInProgress(asPackage, isLoading);
					}
				}
			}
		}
	}

	protected void setGenerationInProgress(org.eclipse.ocl.pivot.@NonNull Package asPackage, boolean isGenerating) {
		String nsUri = asPackage.getURI();
		if (nsUri != null) {
			ProjectManager projectManager = environmentFactory.getProjectManager();
			@NonNull URI nsURI = URI.createURI(nsUri);
			StandaloneProjectMap.IPackageDescriptor packageDescriptor = projectManager.getPackageDescriptor(nsURI);
			if (packageDescriptor != null) {
				StandaloneProjectMap.IResourceDescriptor resourceDescriptor = packageDescriptor.getResourceDescriptor();
				ResourceSet resourceSet = environmentFactory.getResourceSet();
				StandaloneProjectMap.IResourceLoadStatus resourceLoadStatus = resourceDescriptor.getResourceLoadStatus(resourceSet);
				resourceLoadStatus.setGenerationInProgress(isGenerating);
			}
		}
		for (org.eclipse.ocl.pivot.Package asNestedPackage : asPackage.getOwnedPackages()) {
			if (asNestedPackage != null) {
				setGenerationInProgress(asNestedPackage, isGenerating);
			}
		}
	}
}
