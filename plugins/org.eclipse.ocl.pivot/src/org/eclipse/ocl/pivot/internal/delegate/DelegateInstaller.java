/*******************************************************************************
 * Copyright (c) 2011, 2021 Willink Transformations and others.
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

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.WeakHashMap;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreValidator;
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
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrintOptions;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrinter;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.options.OCLinEcoreOptions;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.util.DerivedConstants;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.utilities.UniqueList;

import com.google.common.collect.Lists;

public class DelegateInstaller
{
	/**
	 * DelegatingEPackage is an EPackage that delegates everything, except hash() and equals() to another EPackage.
	 * It is therefore a distinct not-equals object that behaves identically. If therefore breaks the "==" test
	 * in EObjectValidator.
	 */
	private static final class DelegatingEPackage implements EPackage
	{
		private final @NonNull EPackage delegate;

		public DelegatingEPackage(@NonNull EPackage delegate) {
			this.delegate = delegate;
		}

		@Override
		public EList<Adapter> eAdapters() {
			return delegate.eAdapters();
		}

		@Override
		public TreeIterator<@NonNull EObject> eAllContents() {
			return delegate.eAllContents();
		}

		@Override
		public EClass eClass() {
			return delegate.eClass();
		}

		@Override
		public EObject eContainer() {
			return delegate.eContainer();
		}

		@Override
		public EStructuralFeature eContainingFeature() {
			return delegate.eContainingFeature();
		}

		@Override
		public EReference eContainmentFeature() {
			return delegate.eContainmentFeature();
		}

		@Override
		public EList<EObject> eContents() {
			return delegate.eContents();
		}

		@Override
		public EList<EObject> eCrossReferences() {
			return delegate.eCrossReferences();
		}

		@Override
		public boolean eDeliver() {
			return delegate.eDeliver();
		}

		@Override
		public Object eGet(EStructuralFeature feature) {
			return delegate.eGet(feature);
		}

		@Override
		public Object eGet(EStructuralFeature feature, boolean resolve) {
			return delegate.eGet(feature, resolve);
		}

		@Override
		public Object eInvoke(EOperation operation, EList<?> arguments) throws InvocationTargetException {
			return delegate.eInvoke(operation, arguments);
		}

		@Override
		public boolean eIsProxy() {
			return delegate.eIsProxy();
		}

		@Override
		public boolean eIsSet(EStructuralFeature feature) {
			return delegate.eIsSet(feature);
		}

		@Override
		public void eNotify(Notification notification) {
			delegate.eNotify(notification);
		}

		@Override
		public Resource eResource() {
			return delegate.eResource();
		}

		@Override
		public void eSet(EStructuralFeature feature, Object newValue) {
			delegate.eSet(feature, newValue);
		}

		@Override
		public void eSetDeliver(boolean deliver) {
			delegate.eSetDeliver(deliver);
		}

		@Override
		public void eUnset(EStructuralFeature feature) {
			delegate.eUnset(feature);
		}

		@Override
		public EAnnotation getEAnnotation(String source) {
			return delegate.getEAnnotation(source);
		}

		@Override
		public EList<EAnnotation> getEAnnotations() {
			return delegate.getEAnnotations();
		}

		@Override
		public EClassifier getEClassifier(String name) {
			return delegate.getEClassifier(name);
		}

		@Override
		public EList<EClassifier> getEClassifiers() {
			return delegate.getEClassifiers();
		}

		@Override
		public EFactory getEFactoryInstance() {
			return delegate.getEFactoryInstance();
		}

		@Override
		public EList<EPackage> getESubpackages() {
			return delegate.getESubpackages();
		}

		@Override
		public EPackage getESuperPackage() {
			return delegate.getESuperPackage();
		}

		@Override
		public String getName() {
			return delegate.getName();
		}

		@Override
		public String getNsPrefix() {
			return delegate.getNsPrefix();
		}

		@Override
		public String getNsURI() {
			return delegate.getNsURI();
		}

		@Override
		public void setNsURI(String value) {
			delegate.setNsURI(value);
		}

		@Override
		public void setEFactoryInstance(EFactory value) {
			delegate.setEFactoryInstance(value);
		}

		@Override
		public void setName(String value) {
			delegate.setName(value);
		}

		@Override
		public void setNsPrefix(String value) {
			delegate.setNsPrefix(value);
		}
	}

	/**
	 * DynamicEcoreValidator displaces the regular EcoreValidator to force the EcoreValidator to use
	 * dynamic validation and so support additional constraints supported by validation delegates.
	 */
	private static final class DynamicEcoreValidator extends EcoreValidator
	{
		private static @Nullable DynamicEcoreValidator INSTANCE = null;

		public static @NonNull EValidator get(@NonNull EnvironmentFactory environmentFactory) {
			DynamicEcoreValidator instance = INSTANCE;
			if (instance == null) {
				instance = INSTANCE = new DynamicEcoreValidator();
			}
			instance.add(environmentFactory);
			return instance;
		}

		private final @NonNull EPackage delegate = new DelegatingEPackage(EcorePackage.eINSTANCE);
		private final @NonNull WeakHashMap<@NonNull EnvironmentFactory, @NonNull DynamicEcoreValidator> setOfEnvironmentFactory = new WeakHashMap<>();			// XXX

		private void add(@NonNull EnvironmentFactory environmentFactory) {
			setOfEnvironmentFactory.put(environmentFactory, this);
		}

		@Override
		protected EPackage getEPackage() {
			EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
			if ((environmentFactory != null) && setOfEnvironmentFactory.containsKey(environmentFactory)) {
				return delegate;
			}
			else {
				return EcorePackage.eINSTANCE;
			}
		}
	}

	/**
	 * True to apply result = () wrapper to invariant body.
	 */
	public static final @NonNull String OPTION_BOOLEAN_INVARIANTS = "booleanInvariants";

	/**
	 * True to omit the setting delegates declaration. Useful for matching UML2Ecore behavior.
	 */
	public static final @NonNull String OPTION_OMIT_SETTING_DELEGATES = "omitSettingDelegates";

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
		allURIs.addAll(ClassUtil.nonNull(EcoreUtil.getInvocationDelegates(ePackage)));
		//		allURIs.addAll(EcoreUtil.getQueryDelegates(ePackage));
		allURIs.addAll(ClassUtil.nonNull(EcoreUtil.getSettingDelegates(ePackage)));
		allURIs.addAll(ClassUtil.nonNull(EcoreUtil.getValidationDelegates(ePackage)));
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

	public static @Nullable String getExportDelegateURI(@NonNull Map<String, Object> options) {
		String exportDelegateURI = (String)options.get(OCLConstants.OCL_DELEGATE_URI);
		return exportDelegateURI != null ? exportDelegateURI : OCLinEcoreOptions.EXPORT_DELEGATION_URI.getPreferredValue();
	}

	public static boolean isBooleanInvariants(@NonNull Map<String,Object> options) {
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

	protected final @NonNull EnvironmentFactoryInternal environmentFactory;
	protected final @NonNull Map<String, Object> options;
	protected final @Nullable String exportDelegateURI;

	public DelegateInstaller(@NonNull EnvironmentFactoryInternal environmentFactory, @Nullable Map<String, Object> options) {
		this.environmentFactory = environmentFactory;
		//		this.metamodelManager = metamodelManager;
		this.options = options != null ? options : new HashMap<String,Object>();
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

	public @Nullable EAnnotation createConstraintDelegate(@NonNull EModelElement eModelElement, @NonNull Constraint pivotConstraint, @Nullable URI ecoreURI) {
		LanguageExpression specification = pivotConstraint.getOwnedSpecification();
		if (specification == null) {
			return null;
		}
		String exprString = createExpression(specification, ecoreURI);
		if (exprString == null) {
			return null;
		}
		EAnnotation oclAnnotation = createAnnotation(eModelElement);
		String key = getAnnotationKey(pivotConstraint);
		oclAnnotation.getDetails().put(key, exprString);
		return oclAnnotation;
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

	public @NonNull EnvironmentFactory getEnvironmentFactory() {
		return environmentFactory;
	}

	public @Nullable String getExportDelegateURI() {
		return exportDelegateURI;
	}

	//	public @NonNull MetamodelManager getMetamodelManager() {
	//		return metamodelManager;
	//	}


	public static @NonNull EValidator init(@NonNull EnvironmentFactory environmentFactory) {
		return DynamicEcoreValidator.get(environmentFactory);
	}

	/**
	 * Synthesize the PivotConstants.OCL_DELEGATE_URI_PIVOT_COMPLETE_OCL EAnnotations
	 * convert the Constraints in asResource into a format the regular Diagnostician supports..
	 *
	 * @since 1.22
	 */
	public void installCompleteOCLDelegates(@NonNull ASResource asResource) {
		CompleteModelInternal completeModel = environmentFactory.getCompleteModel();
		//
		//	Determine AS Constraints per EClass and the containing EPackages.
		//
		List<@NonNull EPackage> ePackages = new UniqueList<>();
		Map<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> eClass2constraints = new HashMap<>();
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
							EPackage ePackage = eClass.getEPackage();
							assert ePackage != null;
							ePackages.add(ePackage);
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
		//
		//	Install EClass EAnnotations for AS Constraints.
		//
		for (Map.Entry<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> entry : eClass2constraints.entrySet()) {
			EClass eClass = entry.getKey();
			UniqueList<@NonNull Constraint> asConstraints = entry.getValue();
			Collections.sort(asConstraints, NameUtil.NAMEABLE_COMPARATOR);
			List<@NonNull String> newConstraintNames = new UniqueList<>();
			EAnnotation eClassAnnotation = eClass.getEAnnotation(EcorePackage.eNS_URI);
			if (eClassAnnotation != null) {
				String oldConstraintNames = eClassAnnotation.getDetails().get("constraints");
				StringTokenizer stringTokenizer = new StringTokenizer(oldConstraintNames);
				while (stringTokenizer.hasMoreTokens()) {
					String oldConstraintName = stringTokenizer.nextToken();
					assert oldConstraintName != null;
					newConstraintNames.add(oldConstraintName);
				}
			}
			for (@NonNull Constraint asConstraint : asConstraints) {
				String constraintName = asConstraint.getName();
				if (constraintName == null) {
					constraintName = "";
				}
				newConstraintNames.add(constraintName);			// XXX bad name
				EAnnotation eAnnotation = eClass.getEAnnotation(PivotConstants.OCL_DELEGATE_URI_PIVOT_COMPLETE_OCL);
				if (eAnnotation == null) {
					eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
					eAnnotation.setSource(PivotConstants.OCL_DELEGATE_URI_PIVOT_COMPLETE_OCL);
					eClass.getEAnnotations().add(eAnnotation);
				}
				String old = eAnnotation.getDetails().put(constraintName, "$$complete-ocl$$");			// XXX toString
				((ConstraintImpl)asConstraint).setESObject(eAnnotation);
			}
			Collections.sort(newConstraintNames);
			String splicedConstraintNames = StringUtil.splice(newConstraintNames, " ");
			if (eClassAnnotation == null) {
				EcoreUtil.setAnnotation(eClass, EcorePackage.eNS_URI, "constraints", splicedConstraintNames);
			}
			else {
				eClassAnnotation.getDetails().put("constraints", splicedConstraintNames);
			}
		}
		//
		//	Install EPackage EAnnotations and force DelegateEPackageAdapter recomputation.
		//
		for (EPackage ePackage : ePackages) {
			List<String> validationDelegates = EcoreUtil.getValidationDelegates(ePackage);
			if (!validationDelegates.contains(PivotConstants.OCL_DELEGATE_URI_PIVOT_COMPLETE_OCL)) {
				validationDelegates = Lists.newArrayList(validationDelegates);
				validationDelegates.add(PivotConstants.OCL_DELEGATE_URI_PIVOT_COMPLETE_OCL);
				EcoreUtil.setValidationDelegates(ePackage, validationDelegates);
				DelegateEPackageAdapter adapter = DelegateEPackageAdapter.findAdapter(ePackage);
				if (adapter != null) {
					adapter.getDelegateDomains(true);			// Force recomputation with additional delegateURI
				}
			}
			EValidator eValidator = EValidator.Registry.INSTANCE.getEValidator(ePackage);
			if (eValidator instanceof EcoreValidator) {
				eValidator = DynamicEcoreValidator.get(environmentFactory);
				EValidator.Registry.INSTANCE.put(ePackage, eValidator);
			}
			else {}		// XXX
		}
		//
		//	Force DelegateEClassifierAdapter recomputation.
		//
		for (@NonNull EClass eClass : eClass2constraints.keySet()) {
			DelegateEClassifierAdapter adapter = DelegateEClassifierAdapter.findAdapter(eClass);
			if (adapter != null) {
				adapter.getValidationDelegates(true);			// Force recomputation with additional delegateURI
			}
		}
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
		PivotMetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		Type primaryType = metamodelManager.getPrimaryType(pivotType);
		EObject eTarget = primaryType.getESObject();
		if (eTarget instanceof EClassifier) {
			@NonNull EClassifier eClassifier = (EClassifier)eTarget;
			removeDelegateAnnotations(eClassifier, null);
			for (Constraint constraint : metamodelManager.getLocalInvariants(pivotType)) {
				if (constraint.isIsCallable()) {
					EOperation eContext = null;
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
					EAnnotation oclAnnotation = createConstraintDelegate(eContext, constraint, null);
					if (oclAnnotation == null) {
						return false;
					}
					eContext.getEAnnotations().add(oclAnnotation);
					hasDelegates = true;
				}
				else {
					EAnnotation oclAnnotation = createConstraintDelegate(eClassifier, constraint, null);
					if (oclAnnotation == null) {
						return false;
					}
					eClassifier.getEAnnotations().add(oclAnnotation);
					hasDelegates = true;
				}
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
		Set<@NonNull String> constraintNameSet = null;
		StringBuilder s = null;
		PivotMetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		for (Constraint pivotConstraint : metamodelManager.getLocalInvariants(pivotType)) {
			assert pivotConstraint != null;
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
		EAnnotation eAnnotation = eClassifier.getEAnnotation(EcorePackage.eNS_URI);
		if (s != null) {
			if (eAnnotation == null) {
				eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
				eAnnotation.setSource(EcorePackage.eNS_URI);
				eClassifier.getEAnnotations().add(/*0,*/ eAnnotation);
			}
			eAnnotation.getDetails().put("constraints", s.toString());
		}
		else {
			eClassifier.getEAnnotations().remove(eAnnotation);
		}
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
		EAnnotation annotation4 = eModelElement.getEAnnotation(DerivedConstants.UML2_GEN_MODEL_PACKAGE_1_1_NS_URI);
		if (annotation4 != null) {
			eAnnotations.remove(annotation4);
		}
		return oclAnnotation;
	}
}
