/*******************************************************************************
 * Copyright (c) 2011, 2025 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   C.Damus, K.Hussey, E.D.Willink - Initial API and implementation
 * 	 E.D.Willink (Obeo) - Bug 416287 - tuple-valued constraints
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.delegate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.common.OCLCommon;
import org.eclipse.ocl.common.OCLConstants;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.ConstraintImpl;
import org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal;
import org.eclipse.ocl.pivot.internal.ecore.as2es.AS2Ecore;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrintOptions;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrinter;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotObjectImpl;
import org.eclipse.ocl.pivot.options.OCLinEcoreOptions;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.util.DerivedConstants;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.SemanticException;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.utilities.UniqueList;

import com.google.common.collect.Lists;

/**
 * A DelegateInstaller supports installation of Ecore delegates to implement functionality defined by OCL expressions embedded
 * in AS Constraints, Operations and Properties.
 */
public class DelegateInstaller
{
	/**
	 * True to apply result = () wrapper to invariant body.
	 */
	public static final @NonNull String OPTION_BOOLEAN_INVARIANTS = "booleanInvariants";

	/**
	 * True to omit the setting delegates declaration. Useful for matching UML2Ecore behavior.
	 */
	public static final @NonNull String OPTION_OMIT_SETTING_DELEGATES = "omitSettingDelegates";

	/**
	 * @since 7.0
	 */
	public static final @NonNull String CONSTRAINTS_KEY = "constraints";

	/**
	 * ValidationContext entry that may be set true when validating an EObject whose ResourceSet lacks an ExtendedEObjectValidatorAdapter.
	 * This prevents leakage of additional dynamic OCL constraints applied to an Xtext grammar leaking beyond the intended applications.
	 *
	 * @since 7.0
	 */
	public static final String SUPPRESS_DYNAMIC_OCL_DELEGATES = "suppressDynamicOCLdelegates";

	public static @NonNull String getAnnotationKey(@NonNull Constraint pivotConstraint) {
		String name = pivotConstraint.getName();
		EStructuralFeature eContainingFeature = pivotConstraint.eContainingFeature();
		if (eContainingFeature == PivotPackage.Literals.CLASS__OWNED_INVARIANTS) {
			if (pivotConstraint.isIsCallable()) {
				return "body";
			}
			else {
				return name != null ? name : "null";
			}
		}
		else if (eContainingFeature == PivotPackage.Literals.OPERATION__OWNED_PRECONDITIONS) {
			return name != null ? "pre_" + name : "pre";
		}
		else if (eContainingFeature == PivotPackage.Literals.OPERATION__OWNED_POSTCONDITIONS) {
			return name != null ? "post_" + name : "post";
		}
		else {
			//			error("Unsupported " + pivotConstraint);
		}
		return "null";
	}

	/**
	 * Return a non-null name for asConstraint using asConstraint's name unless null in which case a synthetic name is returned based on the
	 * asConstraint's position ion its container.
	 */
	private static @NonNull String getConstraintName(@NonNull Constraint asConstraint) {
		String constraintName = asConstraint.getName();
		if (constraintName == null) {
			List<?> asConstraints = (List<?>)asConstraint.eContainer().eGet(asConstraint.eContainingFeature());
			constraintName = "$$" + asConstraints.indexOf(asConstraint);
		}
		return constraintName;
	}

	/**
	 * Analyze eClass and return the names of constraints enumerated under the "constraints" key of the EcorePackage.eNS_URI EAnnotation.
	 */
	static @NonNull List<@NonNull String> getConstraintNames(@NonNull EClassifier eClass) {
		List<@NonNull String> constraintNames = new ArrayList<>();
		EAnnotation constraintNamesAnnotation = eClass.getEAnnotation(EcorePackage.eNS_URI);
		if (constraintNamesAnnotation != null) {
			String concatenatenatedConstraintNames = constraintNamesAnnotation.getDetails().get(CONSTRAINTS_KEY);
			if (concatenatenatedConstraintNames != null) {
				StringTokenizer stringTokenizer = new StringTokenizer(concatenatenatedConstraintNames);
				while (stringTokenizer.hasMoreTokens()) {
					String oldConstraintName = stringTokenizer.nextToken();
					assert oldConstraintName != null;
					constraintNames.add(oldConstraintName);
				}
			}
		}
		return constraintNames;
	}

	public static @Nullable String getDelegateURI(@NonNull List<EObject> contents) {
		for (EObject eObject : contents) {
			if (eObject instanceof EPackage) {
				String exportURI = getDelegateURI((EPackage)eObject);
				if (exportURI != null) {
					return exportURI;
				}
			}
		}
		return null;
	}

	public static @Nullable String getDelegateURI(@NonNull EPackage ePackage) {
		Set<String> allURIs = new HashSet<String>();
		//		allURIs.addAll(EcoreUtil.getConversionDelegates(ePackage));
		allURIs.addAll(ClassUtil.requireNonNull(EcoreUtil.getInvocationDelegates(ePackage)));
		//		allURIs.addAll(EcoreUtil.getQueryDelegates(ePackage));
		allURIs.addAll(ClassUtil.requireNonNull(EcoreUtil.getSettingDelegates(ePackage)));
		allURIs.addAll(ClassUtil.requireNonNull(EcoreUtil.getValidationDelegates(ePackage)));
		String theURI = null;
		for (String uri : allURIs) {
			if (uri.startsWith(OCLConstants.OCL_DELEGATE_URI)) {
				if (theURI != null) {
					return OCLConstants.OCL_DELEGATE_URI;
				}
				theURI = uri;
			}
		}
		if (theURI != null) {
			return theURI;
		}
		for (@SuppressWarnings("null")@NonNull EPackage eSubpackage : ePackage.getESubpackages()) {
			String exportURI = getDelegateURI(eSubpackage);
			if (exportURI != null) {
				return exportURI;
			}
		}
		for (EClassifier eClassifier : ePackage.getEClassifiers()) {
			EAnnotation classifierAnnotation = OCLCommon.getDelegateAnnotation(eClassifier);
			if ((classifierAnnotation != null) && !classifierAnnotation.getDetails().isEmpty()) {
				return classifierAnnotation.getSource();
			}
			if (eClassifier instanceof EClass) {
				EClass eClass = (EClass) eClassifier;
				for (EStructuralFeature eFeature : eClass.getEStructuralFeatures()) {
					EAnnotation featureAnnotation = OCLCommon.getDelegateAnnotation(eFeature);
					if ((featureAnnotation != null) && !featureAnnotation.getDetails().isEmpty()) {
						return featureAnnotation.getSource();
					}
				}
				for (EOperation eOperation : eClass.getEOperations()) {
					EAnnotation operationAnnotation = OCLCommon.getDelegateAnnotation(eOperation);
					if ((operationAnnotation != null) && !operationAnnotation.getDetails().isEmpty()) {
						return operationAnnotation.getSource();
					}
				}
			}
		}
		return null;
	}

	/**
	 * Return all the EPackages that contain eClasses.
	 */
	private static @NonNull List<@NonNull EPackage> getEPackages(Iterable<@NonNull EClass> eClasses) {
		List<@NonNull EPackage> ePackages = new UniqueList<>();
		for (@NonNull EClass eClass : eClasses) {
			EPackage ePackage = eClass.getEPackage();
			assert ePackage != null;
			ePackages.add(ePackage);
		}
		return ePackages;
	}

	public static @Nullable String getExportDelegateURI(@NonNull Map<@NonNull String, @Nullable Object> options) {
		String exportDelegateURI = (String)options.get(OCLConstants.OCL_DELEGATE_URI);
		return exportDelegateURI != null ? exportDelegateURI : OCLinEcoreOptions.EXPORT_DELEGATION_URI.getPreferredValue();
	}

	public static boolean isBooleanInvariants(@NonNull Map<@NonNull String, @Nullable Object> options) {
		return Boolean.valueOf(String.valueOf(options.get(OPTION_BOOLEAN_INVARIANTS)));
	}

	public static boolean needsDelegates(@NonNull EPackage ePackage) {
		boolean needsDelegates = false;
		for (EClassifier eClassifier : ePackage.getEClassifiers()) {
			EAnnotation classifierAnnotation = OCLCommon.getDelegateAnnotation(eClassifier);
			if ((classifierAnnotation != null) && !classifierAnnotation.getDetails().isEmpty()) {
				needsDelegates = true;
				break;
			}
			if (eClassifier instanceof EClass) {
				EClass eClass = (EClass) eClassifier;
				for (EStructuralFeature eFeature : eClass.getEStructuralFeatures()) {
					EAnnotation featureAnnotation = OCLCommon.getDelegateAnnotation(eFeature);
					if ((featureAnnotation != null) && !featureAnnotation.getDetails().isEmpty()) {
						needsDelegates = true;
						break;
					}
				}
				if (needsDelegates) {
					break;
				}
				for (EOperation eOperation : eClass.getEOperations()) {
					EAnnotation operationAnnotation = OCLCommon.getDelegateAnnotation(eOperation);
					if ((operationAnnotation != null) && !operationAnnotation.getDetails().isEmpty()) {
						needsDelegates = true;
						break;
					}
				}
				if (needsDelegates) {
					break;
				}
			}
		}
		return needsDelegates;
	}

	private static void refreshValidationDelegates(@NonNull EPackage ePackage, @NonNull List<String> validationDelegates) {
		EcoreUtil.setValidationDelegates(ePackage, validationDelegates);
		DelegateEPackageAdapter adapter = DelegateEPackageAdapter.findAdapter(ePackage);
		if (adapter != null) {
			adapter.getDelegateDomains(true);					// Force recomputation with additional delegateURI
		}
	}

	private static void refreshValidationDelegates(@NonNull Iterable <@NonNull EClass> eClasses) {
		for (@NonNull EClass eClass : eClasses) {
			DelegateEClassifierAdapter adapter = DelegateEClassifierAdapter.findAdapter(eClass);
			if (adapter != null) {
				adapter.getValidationDelegates(true);			// Force recomputation with additional delegateURI
			}
		}
	}

	/**
	 * Ensure that eClass has an EcorePackage.eNS_URI EAnnotation with a "constraints" key corresponding to
	 * the presence or absence of constraintNames.
	 */
	private static void setConstraintNames(@NonNull EClassifier eClassifier, @Nullable List<@NonNull String> constraintNames) {
		EAnnotation constraintNamesAnnotation = eClassifier.getEAnnotation(EcorePackage.eNS_URI);
		if ((constraintNames == null) || constraintNames.isEmpty()) {
			if (constraintNamesAnnotation != null) {
				EMap<String, String> details = constraintNamesAnnotation.getDetails();
				details.removeKey(CONSTRAINTS_KEY);
				if (details.isEmpty()) {
					eClassifier.getEAnnotations().remove(constraintNamesAnnotation);
				}
			}
		}
		else {
			@SuppressWarnings("null")
			List<String> castConstraintNames = constraintNames;
			Collections.sort(castConstraintNames);
			String splicedConstraintNames = StringUtil.splice(castConstraintNames, " ");
			if (constraintNamesAnnotation == null) {
				EcoreUtil.setAnnotation(eClassifier, EcorePackage.eNS_URI, CONSTRAINTS_KEY, splicedConstraintNames);
			}
			else {
				EMap<String, String> details = constraintNamesAnnotation.getDetails();
				details.put(CONSTRAINTS_KEY, splicedConstraintNames);
			}
		}
	}

	protected final @NonNull EnvironmentFactoryInternal environmentFactory;
	protected final @NonNull Map<@NonNull String, @Nullable Object> options;
	protected final @Nullable String exportDelegateURI;

	public DelegateInstaller(@NonNull EnvironmentFactoryInternal environmentFactory, @Nullable Map<@NonNull String, @Nullable Object> options) {
		this.environmentFactory = environmentFactory;
		this.options = options != null ? options : new HashMap<>();
		this.exportDelegateURI = getExportDelegateURI(this.options);
	}

	protected @NonNull EAnnotation createAnnotation(@NonNull EModelElement eModelElement) {
		EAnnotation oclAnnotation = removeDelegateAnnotations(eModelElement, exportDelegateURI);
		if (oclAnnotation == null) {
			oclAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
			oclAnnotation.setSource(exportDelegateURI);
			eModelElement.getEAnnotations().add(oclAnnotation);
		}
		return oclAnnotation;
	}

	/**
	 * @since 7.0
	 */
	public @Nullable EStringToStringMapEntryImpl createConstraintDelegateDetail(@NonNull EModelElement eModelElement, @NonNull Constraint pivotConstraint, @Nullable URI ecoreURI) {
		LanguageExpression specification = pivotConstraint.getOwnedSpecification();
		if (specification == null) {
			return null;
		}
		String exprString = createExpression(specification, ecoreURI);
		if (exprString == null) {
			return null;
		}
		EAnnotation oclAnnotation = createAnnotation(eModelElement);
		@NonNull String key = getAnnotationKey(pivotConstraint);
		EMap<String, String> eDetails = oclAnnotation.getDetails();
		eDetails.put(key, exprString);
		for (int i = eDetails.size(); --i >= 0; ) {
			EStringToStringMapEntryImpl eDetail = (EStringToStringMapEntryImpl)eDetails.get(i);
			if (key.equals(eDetail.getKey())) {
			//	eDetail.setValue(exprString);
				return eDetail;
			}
		}
		assert false;
		return null;
	}

	protected @Nullable String createExpression(@NonNull LanguageExpression specification, @Nullable URI ecoreURI) {
		String exprString = specification.getBody();
		if ((exprString == null) && (specification instanceof ExpressionInOCL)) {
			OCLExpression bodyExpression2 = ((ExpressionInOCL)specification).getOwnedBody();
			if (bodyExpression2 != null) {
				exprString = createExpression(bodyExpression2, ecoreURI);
			}
		}
		return exprString;
	}

	protected @Nullable String createExpression(@NonNull OCLExpression bodyExpression, @Nullable URI ecoreURI) {
		Namespace namespace = PivotUtil.getNamespace(bodyExpression);
		PrettyPrintOptions.Global options = PrettyPrinter.createOptions(namespace);
		options.setBaseURI(ecoreURI);
		return PrettyPrinter.print(bodyExpression, options);
	}

	public @Nullable EAnnotation createOperationDelegate(@NonNull EOperation eOperation, @NonNull LanguageExpression bodyExpression, @Nullable URI ecoreURI) {
		String exprString = createExpression(bodyExpression, ecoreURI);
		if (exprString == null) {
			return null;
		}
		if (isBooleanInvariants(options)) {
			exprString = "result = (" + exprString + ")";
		}
		EAnnotation oclAnnotation = createAnnotation(eOperation);
		oclAnnotation.getDetails().put(InvocationBehavior.BODY_CONSTRAINT_KEY, exprString);
		return oclAnnotation;
	}

	public @Nullable EAnnotation createPropertyDelegate(@NonNull EStructuralFeature eStructuralFeature, @NonNull LanguageExpression defaultExpression, @Nullable URI ecoreURI) {
		String exprString = createExpression(defaultExpression, ecoreURI);
		if (exprString == null) {
			return null;
		}
		EAnnotation oclAnnotation = createAnnotation(eStructuralFeature);
		oclAnnotation.getDetails().put(SettingBehavior.DERIVATION_CONSTRAINT_KEY, exprString);
		return oclAnnotation;
	}

	/**
	 * Analyze asResources and return a map of all their Class invariant Constraints to eClass2constraints.
	 */
	private @NonNull Map<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> gatherEClass2Constraints(@NonNull Iterable<@NonNull Resource> asResources) {
		Map<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> eClass2constraints = new HashMap<>();
		CompleteModelInternal completeModel = environmentFactory.getCompleteModel();
		for (Resource asResource : asResources) {
			for (EObject eObject : new TreeIterable(asResource)) {
				if (eObject instanceof Constraint) {
					Constraint asConstraint = (Constraint)eObject;
					EStructuralFeature eContainingFeature = eObject.eContainingFeature();
					if (eContainingFeature == PivotPackage.Literals.CLASS__OWNED_INVARIANTS) {
						org.eclipse.ocl.pivot.Class asClass = (org.eclipse.ocl.pivot.Class)eObject.eContainer();
						assert asClass != null;
						CompleteClass completeClass = completeModel.getCompleteClass(asClass);
						for (org.eclipse.ocl.pivot.Class partialClass : completeClass.getPartialClasses()) {
							EObject esObject = partialClass.getESObject();
							if (esObject instanceof EClass) {			// XXX ignores UML's Class
								EClass eClass = (EClass)esObject;
								UniqueList<@NonNull Constraint> constraints = eClass2constraints.get(eClass);
								if (constraints == null) {
									constraints = new UniqueList<>();
									eClass2constraints.put(eClass, constraints);
								}
								constraints.add(asConstraint);
								break;
							}
						}
					}
				}
			}
		}
		return eClass2constraints;
	}

	public @NonNull EnvironmentFactory getEnvironmentFactory() {
		return environmentFactory;
	}

	public @Nullable String getExportDelegateURI() {
		return exportDelegateURI;
	}

	/**
	 * Synthesize the PivotConstants.OCL_DELEGATE_URI_PIVOT_COMPLETE_OCL EAnnotations
	 * convert the Constraints in asResource into a format the regular Diagnostician supports.
	 * and make them accessible when validating within userResourceSet.
	 * @throws SemanticException
	 *
	 * @since 7.0
	 */
	public void installCompleteOCLDelegates(@NonNull ResourceSet userResourceSet, @NonNull ASResource asResource) throws SemanticException {
		assert userResourceSet == environmentFactory.getUserResourceSet();			// XXX
		//
		//	Determine AS Constraints per EClass and the containing EPackages.
		//
		Map<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> eClass2constraints = gatherEClass2Constraints(Collections.singletonList(asResource));
		//
		//	Install EClass EDetails for AS Constraints.
		//
		for (Map.Entry<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> entry : eClass2constraints.entrySet()) {
			EClass eClass = entry.getKey();
			UniqueList<@NonNull Constraint> asConstraints = entry.getValue();
			Collections.sort(asConstraints, NameUtil.NAMEABLE_COMPARATOR);
			List<@NonNull String> constraintNames = getConstraintNames(eClass);
			EAnnotation dynamicEAnnotation = eClass.getEAnnotation(PivotConstants.OCL_DELEGATE_URI_PIVOT_DYNAMIC);
			for (@NonNull Constraint asConstraint : asConstraints) {
				String constraintName = getConstraintName(asConstraint);
				if (!constraintNames.contains(constraintName)) {		// XXX do we mean to give way to an alternative ?
					constraintNames.add(constraintName);
				}
				else {
					// XXX what do we do if Complete OCL redefines an 'Ecore' constraint ?
				}
				if (dynamicEAnnotation == null) {
					dynamicEAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
					dynamicEAnnotation.setSource(PivotConstants.OCL_DELEGATE_URI_PIVOT_DYNAMIC);
					eClass.getEAnnotations().add(dynamicEAnnotation);
				}
				EMap<String, String> eDetails = dynamicEAnnotation.getDetails();
				@SuppressWarnings("unused")
				String old = eDetails.put(constraintName, PivotConstants.DUMMY_COMPLETE_OCL_BODY);			// XXX toString
				for (int i = eDetails.size(); --i >= 0; ) {		// Reverse search since new entry typically last
					EStringToStringMapEntryImpl eDetail = (EStringToStringMapEntryImpl)eDetails.get(i);
					if (Objects.equals(eDetail.getKey(), constraintName)) {
						((ConstraintImpl)asConstraint).setESObject(eDetail);
						break;
					}
				}
			//	}
			//	else if (completeOCLbodiesAnnotation != null) {
			//		((ConstraintImpl)asConstraint).setESObject(completeOCLbodiesAnnotation);
			//	}
			}
			setConstraintNames(eClass, constraintNames);
		}
		//
		//	Install EPackage EAnnotations and force DelegateEPackageAdapter/DelegateEClassifierAdapter recomputation.
		//
		Iterable<@NonNull EClass> eClasses = eClass2constraints.keySet();
		List<@NonNull EPackage> ePackages = getEPackages(eClasses);
		for (EPackage ePackage : ePackages) {
			List<String> validationDelegates = EcoreUtil.getValidationDelegates(ePackage);
			if (!validationDelegates.contains(PivotConstants.OCL_DELEGATE_URI_PIVOT_DYNAMIC)) {
				validationDelegates = Lists.newArrayList(validationDelegates);
				validationDelegates.add(PivotConstants.OCL_DELEGATE_URI_PIVOT_DYNAMIC);
				refreshValidationDelegates(ePackage, validationDelegates);
			}
			ExtendedEObjectValidator.installFor(userResourceSet, environmentFactory, ePackage, asResource);
		}
		refreshValidationDelegates(eClasses);			//	Force DelegateEClassifierAdapter recomputation.
	}

	/**
	 * Install all Constraints from pivotPackage and its nestedPackages as OCL Delegates.
	 */
	public void installDelegates(@NonNull CompletePackage completePackage) {
		boolean hasDelegates = false;
		//		for (Type aType : metamodelManager.getLocalClasses(pivotPackage)) {
		for (CompleteClass completeClass : completePackage.getOwnedCompleteClasses()) {
			if (installDelegates(completeClass.getPrimaryClass())) {
				hasDelegates = true;
			}
		}
		//		PackageServer packageServer = metamodelManager.getPackageServer(pivotPackage);
		EPackage ePackage = completePackage.getEPackage();
		if ((ePackage != null) && hasDelegates) {
			installDelegates(ePackage);
		}
		for (CompletePackage nestedPackage : completePackage.getOwnedCompletePackages()) {
			if (nestedPackage != null) {
				installDelegates(nestedPackage);
			}
		}
	}

	/**
	 * Install all Constraints from pivotType and its operations as OCL Delegates. Returning true if any OCL Delegate installed.
	 *
	 * @param metamodelManager
	 * @param pivotPackage
	 */
	private boolean installDelegates(org.eclipse.ocl.pivot.@NonNull Class pivotType) {
		boolean hasDelegates = false;
		MetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		Type primaryType = metamodelManager.getPrimaryType(pivotType);
		EObject eTarget = primaryType.getESObject();
		if (eTarget instanceof EClassifier) {
			@NonNull EClassifier eClassifier = (EClassifier)eTarget;
			removeDelegateAnnotations(eClassifier, null);
			for (Constraint constraint : metamodelManager.getLocalInvariants(pivotType)) {
				EModelElement eContext = null;
				if (constraint.isIsCallable()) {
					@NonNull String name = PivotUtil.getName(constraint);
					for (EOperation candidate : ((EClass) eClassifier).getEOperations()) {
						if (name.equals(candidate.getName()) && EcoreUtil.isInvariant(candidate)) {
							eContext = candidate;
							break;
						}
					}
					if (eContext == null) {
						@NonNull EOperation eOperation = AS2Ecore.createConstraintEOperation(constraint, name, null);
						((EClass) eClassifier).getEOperations().add(eOperation);
						eContext = eOperation;
					}
				}
				else {
					eContext = eClassifier;
				}
				EStringToStringMapEntryImpl oclDelegate = createConstraintDelegateDetail(eContext, constraint, null);
				if (oclDelegate == null) {
					return false;
				}
				eContext.getEAnnotations().add((EAnnotation)oclDelegate.eContainer());
				hasDelegates = true;
			}
			for (Operation anOperation : metamodelManager.getMemberOperations(pivotType, false)) {
				EOperation eOperation = (EOperation)anOperation.getESObject();
				if (eOperation != null) {
					installDelegate(eOperation);
				}
			}
			for (Operation anOperation : metamodelManager.getMemberOperations(pivotType, true)) {
				EOperation eOperation = (EOperation)anOperation.getESObject();
				if (eOperation != null) {
					installDelegate(eOperation);
				}
			}
			for (Property aProperty : metamodelManager.getMemberProperties(pivotType, false)) {
				EStructuralFeature eFeature = (EStructuralFeature)aProperty.getESObject();
				if (eFeature != null) {
					installDelegate(eFeature);
				}
			}
			for (Property aProperty : metamodelManager.getMemberProperties(pivotType, true)) {
				EStructuralFeature eFeature = (EStructuralFeature)aProperty.getESObject();
				if (eFeature != null) {
					installDelegate(eFeature);
				}
			}
			for (EAnnotation eAnnotation : eClassifier.getEAnnotations()) {		// Fix redefines/duplicates
				for (TreeIterator<EObject> tit = eAnnotation.eAllContents(); tit.hasNext(); ) {
					EObject eObject = tit.next();
					if (eObject instanceof EAnnotation) {
						EAnnotation nestedAnnotation = (EAnnotation) eObject;
						if (DerivedConstants.UML2_GEN_MODEL_PACKAGE_1_1_NS_URI.equals(nestedAnnotation.getSource())) {
							nestedAnnotation.setSource(PivotConstants.OCL_DELEGATE_URI_PIVOT);
						}
					}
				}
			}
			if (hasDelegates) {
				installDelegates(eClassifier, pivotType);
			}
		}
		return hasDelegates;
	}

	public void installDelegate(@NonNull EOperation eOperation) {
		List<EAnnotation> eAnnotations = eOperation.getEAnnotations();
		EAnnotation oclAnnotation = eOperation.getEAnnotation(DerivedConstants.UML2_GEN_MODEL_PACKAGE_1_1_NS_URI);
		if (oclAnnotation != null) {
			eAnnotations.remove(oclAnnotation);
			oclAnnotation.setSource(exportDelegateURI);
			eAnnotations.add(oclAnnotation);
		}
	}

	public void installDelegate(@NonNull EStructuralFeature eFeature) {
		List<EAnnotation> eAnnotations = eFeature.getEAnnotations();
		EAnnotation oclAnnotation = eFeature.getEAnnotation(DerivedConstants.UML2_GEN_MODEL_PACKAGE_1_1_NS_URI);
		if (oclAnnotation != null) {
			eAnnotations.remove(oclAnnotation);
			oclAnnotation.setSource(exportDelegateURI);
			eAnnotations.add(oclAnnotation);
		}
	}

	public void installDelegates(@NonNull EClassifier eClassifier, org.eclipse.ocl.pivot.@NonNull Class pivotType) {
		List<@NonNull String> constraintNameSet = null;
		StringBuilder s = null;
		MetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		for (@NonNull Constraint pivotConstraint : metamodelManager.getLocalInvariants(pivotType)) {		// XXX metamodelManager
			String constraintName = getAnnotationKey(pivotConstraint);
			if (!pivotConstraint.isIsCallable()) {
				if (constraintNameSet == null) {
					constraintNameSet = new UniqueList<>();
				}
				if (constraintNameSet.add(constraintName)) {	// Avoid duplicates that Bug 571760 fix facilitates
					if (s == null) {
						s = new StringBuilder();
					}
					else {
						s.append(" ");
					}
					s.append(constraintName);					// Preserve order for RoundTripTests
				}
			}
		}
		setConstraintNames(eClassifier, constraintNameSet);
	}

	public void installDelegates(@NonNull EPackage ePackage) {
		EAnnotation packageAnnotation = ClassUtil.getEAnnotation(ePackage, EcorePackage.eNS_URI);
		EMap<String, String> details = packageAnnotation.getDetails();
		details.put(InvocationBehavior.NAME, exportDelegateURI);
		if (!Boolean.valueOf(String.valueOf(options.get(OPTION_OMIT_SETTING_DELEGATES)))) {
			details.put(SettingBehavior.NAME, exportDelegateURI);
		}
		details.put(ValidationBehavior.NAME, exportDelegateURI);
	}

	/**
	 * Remove all OCL Delegate annotations except that corresponding to exportDelegateURI which is returned.
	 */
	protected @Nullable EAnnotation removeDelegateAnnotations(@NonNull EModelElement eModelElement, @Nullable String exportDelegateURI) {
		List<EAnnotation> eAnnotations = eModelElement.getEAnnotations();
		EAnnotation oclAnnotation = null;
		EAnnotation annotation1 = eModelElement.getEAnnotation(OCLConstants.OCL_DELEGATE_URI);
		if (annotation1 != null) {
			if (OCLConstants.OCL_DELEGATE_URI.equals(exportDelegateURI)) {
				oclAnnotation = annotation1;
			}
			else {
				eAnnotations.remove(annotation1);
			}
		}
		EAnnotation annotation2 = eModelElement.getEAnnotation(OCLConstants.OCL_DELEGATE_URI_LPG);
		if (annotation2 != null) {
			if (OCLConstants.OCL_DELEGATE_URI_LPG.equals(exportDelegateURI)) {
				oclAnnotation = annotation2;
			}
			else {
				eAnnotations.remove(annotation2);
			}
		}
		EAnnotation annotation3 = eModelElement.getEAnnotation(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		if (annotation3 != null) {
			if (PivotConstants.OCL_DELEGATE_URI_PIVOT.equals(exportDelegateURI)) {
				oclAnnotation = annotation3;
			}
			else {
				eAnnotations.remove(annotation3);
			}
		}
		EAnnotation annotation4 = eModelElement.getEAnnotation(PivotConstants.OCL_DELEGATE_URI_PIVOT_DYNAMIC);
		if (annotation4 != null) {
			if (PivotConstants.OCL_DELEGATE_URI_PIVOT_DYNAMIC.equals(exportDelegateURI)) {
				oclAnnotation = annotation4;
			}
			else {
				eAnnotations.remove(annotation4);
			}
		}
		EAnnotation annotation5 = eModelElement.getEAnnotation(DerivedConstants.UML2_GEN_MODEL_PACKAGE_1_1_NS_URI);
		if (annotation5 != null) {
			eAnnotations.remove(annotation5);
		}
		return oclAnnotation;
	}

	/**
	 * Remove the PivotConstants.OCL_DELEGATE_URI_PIVOT_COMPLETE_OCL EAnnotations synthesized from asResource.
	 *
	 * @since 7.0
	 */
	public void uninstallCompleteOCLDelegates(@NonNull ResourceSet userResourceSet, @NonNull ASResource asResource) {
		// XXX asymmetric wrt install -- if asResource had been unloaded we could share a refreshCompleteOCLDelegates
		assert userResourceSet == environmentFactory.getUserResourceSet();				// XXX
		Map<@NonNull EAnnotation, @NonNull List<@NonNull Constraint>> unwantedEAnnotations2Constraints = new HashMap<>();
		//
		//	Uninstall EClass EAnnotations for AS Constraints.
		//
		Map<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> eClass2constraints = gatherEClass2Constraints(Collections.singletonList(asResource));
		for (Map.Entry<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> entry : eClass2constraints.entrySet()) {
			EClass eClass = entry.getKey();
			List<@NonNull Constraint> asConstraints = entry.getValue();
			Collections.sort(asConstraints, NameUtil.NAMEABLE_COMPARATOR);
			List<@NonNull String> constraintNames = getConstraintNames(eClass);
			EAnnotation dynamicAnnotation = eClass.getEAnnotation(PivotConstants.OCL_DELEGATE_URI_PIVOT_DYNAMIC);
			if (dynamicAnnotation != null) {
				List<@NonNull Constraint> obsoleteConstraints = null;
				for (@NonNull Constraint asConstraint : asConstraints) {
					String constraintName = getConstraintName(asConstraint);
					constraintNames.remove(constraintName);
					@SuppressWarnings("unused")
					String old = dynamicAnnotation.getDetails().removeKey(constraintName);			// XXX toString
					if (old != null) {
						if (obsoleteConstraints == null) {
							obsoleteConstraints = new ArrayList<>();
						}
						obsoleteConstraints.add(asConstraint);
					}
				}
				setConstraintNames(eClass, constraintNames);
				if (/*constraintNames.isEmpty() &&*/ dynamicAnnotation.getDetails().isEmpty() && (obsoleteConstraints != null)) {
					unwantedEAnnotations2Constraints.put(dynamicAnnotation, obsoleteConstraints);
				//	eClass.getEAnnotations().remove(completeOCLbodiesAnnotation);	// defer till ExtendedEObjectValidator.uninstallFor done.
				}
			}
		}
		//
		//	Uninstall EPackage EAnnotations and force DelegateEPackageAdapter/DelegateEClassifierAdapter recomputation.
		//
		Iterable<@NonNull EClass> eClasses = eClass2constraints.keySet();
		List<@NonNull EPackage> ePackages = getEPackages(eClasses);
		for (EPackage ePackage : ePackages) {
			boolean hasDynamicAnnotation = false;
			for (EClassifier eClassifier : ePackage.getEClassifiers()) {
				EAnnotation dynamicAnnotation = eClassifier.getEAnnotation(PivotConstants.OCL_DELEGATE_URI_PIVOT_DYNAMIC);
				if ((dynamicAnnotation != null) && !dynamicAnnotation.getDetails().isEmpty()) {
					hasDynamicAnnotation = true;
				}
			}
			if (!hasDynamicAnnotation) {
				List<String> validationDelegates = EcoreUtil.getValidationDelegates(ePackage);
				if (validationDelegates.remove(PivotConstants.OCL_DELEGATE_URI_PIVOT_DYNAMIC)) {
					validationDelegates = Lists.newArrayList(validationDelegates);
					assert validationDelegates != null;
					refreshValidationDelegates(ePackage, validationDelegates);
				}
				ExtendedEObjectValidator.uninstallFor(userResourceSet, ePackage, asResource);
			}
		}
		for (@NonNull Entry<@NonNull EAnnotation, @NonNull List<@NonNull Constraint>> entry : unwantedEAnnotations2Constraints.entrySet()) {
			@NonNull EAnnotation eAnnotation = entry.getKey();
			@NonNull List<@NonNull Constraint> asConstraints = entry.getValue();
			for (@NonNull Constraint asConstraint : asConstraints) {
				((PivotObjectImpl)asConstraint).resetESObject();
			}
			EModelElement eContainer = (EModelElement)eAnnotation.eContainer();
			eContainer.getEAnnotations().remove(eAnnotation);
		}
		refreshValidationDelegates(eClasses);			//	Force DelegateEClassifierAdapter recomputation.
	}
}
