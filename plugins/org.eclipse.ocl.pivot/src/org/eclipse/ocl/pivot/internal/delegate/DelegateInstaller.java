/*******************************************************************************
 * Copyright (c) 2011, 2024 Willink Transformations and others.
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
import java.util.Collection;
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
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
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
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EObjectValidator;
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
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.utilities.UniqueList;
import org.eclipse.ocl.pivot.validation.ValidationContext;

import com.google.common.collect.Lists;

/**
 * A DelegateInstaller supports installation of Ecore delegates to implement functionality defined by OCL expressions emdedded
 * in AS Constraints, Operations and Properties.
 */
public class DelegateInstaller
{
	public static class ConstraintSelection
	{
		protected final @NonNull Constraint asConstraint;

		public ConstraintSelection(@NonNull Constraint asConstraint) {
			this.asConstraint = asConstraint;
		}

		public boolean matches(@NonNull String constraintName, @NonNull String validationDelegate) {
			if (!constraintName.equals(asConstraint.getName())) {
				return false;
			}
			EAnnotation eAnnotation = (EAnnotation)asConstraint.getESObject();
			assert eAnnotation != null;
			if (!eAnnotation.getSource().equals(validationDelegate)) {
				return false;
			}
			return eAnnotation.getDetails().containsKey(constraintName);
		}
	}

	/**
	 * ExtendedEObjectValidatorAdapter adapts a ResourceSet to enable the ExtendedEObjectValidator functionality for
	 * elements of the ResourceSet.
	 */
	public static class ExtendedEObjectValidatorAdapter implements Adapter
	{
		private @Nullable ResourceSet resourceSet;
		private @NonNull Map<@NonNull EClass, @NonNull Collection<@NonNull ConstraintSelection>> eClass2constraintSelections = new HashMap<>();

		public ExtendedEObjectValidatorAdapter(@NonNull ResourceSet resourceSet) {
			this.resourceSet = resourceSet;
		}

		public void addConstraintSelections(@NonNull Map<@NonNull EClass, @NonNull Collection<@NonNull ConstraintSelection>> addEClass2constraintSelections) {
			for (Map.@NonNull Entry<@NonNull EClass, @NonNull Collection<@NonNull ConstraintSelection>> entry : addEClass2constraintSelections.entrySet()) {
				EClass eClass = entry.getKey();
				Collection<@NonNull ConstraintSelection> newConstraintSelections = entry.getValue();
				Collection<@NonNull ConstraintSelection> allConstraintSelections = eClass2constraintSelections.get(eClass);
				if (allConstraintSelections == null) {
					allConstraintSelections = new ArrayList<>();
					eClass2constraintSelections.put(eClass, allConstraintSelections);
				}
				allConstraintSelections.addAll(newConstraintSelections);
			}
		}

		@Override
		public @NonNull ResourceSet getTarget() {
			assert resourceSet != null;
			return resourceSet;
		}

		@Override
		public boolean isAdapterForType(Object type) {
			return false;
		}

	//	public boolean isApplicableFor(Map.@NonNull Entry<String,String> eDetail) {
	//		return eDetails.contains(eDetail);
	//	}

		@Override
		public void notifyChanged(Notification notification) {}

		public void removeConstraintSelections(@NonNull Map<@NonNull EClass, @NonNull Collection<@NonNull ConstraintSelection>> removeEClass2ConstraintSelections) {
			for (Map.@NonNull Entry<@NonNull EClass, @NonNull Collection<@NonNull ConstraintSelection>> entry : removeEClass2ConstraintSelections.entrySet()) {
				EClass eClass = entry.getKey();
				Collection<@NonNull ConstraintSelection> removeConstraintSelections = entry.getValue();
				Collection<@NonNull ConstraintSelection> allConstraintSelections = eClass2constraintSelections.get(eClass);
				if (allConstraintSelections != null) {
					allConstraintSelections.removeAll(removeConstraintSelections);
					if (allConstraintSelections.size() <= 0) {
						eClass2constraintSelections.remove(eClass);
					}
				}
			}
		}

		@Override
		public void setTarget(Notifier newTarget) {
			this.resourceSet = (ResourceSet)newTarget;
		}
	}

	/**
	 * An ExtendedEObjectValidator instance displaces and wraps the regular EObjectValidator entry in the EValidator.Registry.INSTANCE
	 * to add support for the additional constraints and invariants supported by validation delegates.
	 */
	public static class ExtendedEObjectValidator extends EObjectValidator
	{
		/**
		 * ExtendedDynamicEClassValidator corrects the inherited functionality to perform the regular validation after
		 * processing any delegated invariants or constraints for all super types.
		 */
		public class ExtendedDynamicEClassValidator extends DynamicEClassValidator
		{
			protected final @NonNull ExtendedEObjectValidatorAdapter extendedEObjectValidatorAdapter;
			protected final @Nullable ValidationDelegate [] validationDelegates;

			public ExtendedDynamicEClassValidator(@NonNull ExtendedEObjectValidatorAdapter extendedEObjectValidatorAdapter, EValidator.ValidationDelegate.@NonNull Registry validationDelegateRegistry) {
				this.extendedEObjectValidatorAdapter = extendedEObjectValidatorAdapter;
			//	ResourceSet resourceSet = extendedEObjectValidatorAdapter.getTarget();
				List<String> validationDelegateURIs = EcoreUtil.getValidationDelegates(ePackage);
				validationDelegates = new @Nullable ValidationDelegate @NonNull [validationDelegateURIs.size()];
				int index = 0;
				for (String validationDelegateURI : validationDelegateURIs) {
					validationDelegates[index++] = validationDelegateRegistry.getValidationDelegate(validationDelegateURI);
				}
			}

			// Ensure that the class loader for this class will be used downstream.
			@Override
			public boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
				assert eClass.getEPackage() == ePackage;
				boolean result = validateDelegatedInvariants(eClass, eObject, diagnostics, context);
				if (result || diagnostics != null) {
					result &= validateDelegatedConstraints(eClass, eObject, diagnostics, context);
				}
				if (result || diagnostics != null) {
					List<EClass> eAllSuperTypes = eClass.getEAllSuperTypes();
					for (EClass eSuperType : eAllSuperTypes) {			// Must avoid multiple validation of multiply inherited super types
						if (result || diagnostics != null) {
							result &= validateDelegatedInvariants(eSuperType, eObject, diagnostics, context);
						}
						if (result || diagnostics != null) {
							result &= validateDelegatedConstraints(eSuperType, eObject, diagnostics, context);
						}
					}
					result &= eValidator.validate(eClass, eObject, diagnostics, context);
				}
				return result;
			}

			@Override
			protected boolean validateDelegatedConstraints(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
				// TODO Auto-generated method stub
				// return super.validateDelegatedConstraints(eClass, eObject,
				// diagnostics, context);
				Collection<@NonNull ConstraintSelection> constraintSelections = extendedEObjectValidatorAdapter.eClass2constraintSelections.get(eClass);
				Set<@NonNull ConstraintSelection> constraintSelectionsSet = constraintSelections != null ? new HashSet<>(constraintSelections) : Collections.emptySet();
				Set<@NonNull ConstraintSelection> actualSelections = new HashSet<>();
				boolean result = true;
				List<String> validationDelegates = EcoreUtil.getValidationDelegates(eClass.getEPackage());

				if (!validationDelegates.isEmpty()) {
					CONSTRAINTS : for (String constraintName : EcoreUtil.getConstraints(eClass)) {
						for (String validationDelegate : validationDelegates) {
							String expression1 = EcoreUtil.getAnnotation(eClass, validationDelegate, constraintName);
							String expression2 = null;
							EAnnotation eAnnotation = eClass.getEAnnotation(validationDelegate);
							if (eAnnotation != null) {
								for (Map.Entry<String, String> entry : eAnnotation.getDetails().entrySet()) {
									if (entry.getKey().equals(constraintName)) {
										expression2 = entry.getValue();
										break;
									}
								}
							}
							//	String expression = EcoreUtil.getAnnotation(eClass, validationDelegate, constraint);
							if (expression1 != null) {
								assert expression1.equals(expression2);


								ConstraintSelection actual = findConstraintSelection(constraintSelections, constraintName, validationDelegate);
								actualSelections.add(actual);

								result &= ExtendedEObjectValidator.this.validate(eClass, eObject, diagnostics, context, validationDelegate, constraintName, expression1, Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0);
								if (!result && diagnostics == null)
									break CONSTRAINTS;
							}
							else {
								assert expression2 == null;
							}
						//	}
						}
					}
				}
				assert actualSelections.equals(constraintSelectionsSet);
				return result;
			}

			private ConstraintSelection findConstraintSelection(Collection<@NonNull ConstraintSelection> constraintSelections,
					String constraintName, String validationDelegate) {
				for (ConstraintSelection constraintSelection : constraintSelections) {
					if (constraintSelection.matches(constraintName, validationDelegate)) {
						return constraintSelection;
					}
				}
				return null;
			}
		}

		private static final @NonNull WeakHashMap<@NonNull EPackage, @NonNull ExtendedEObjectValidator> ePackage2extendedEObjectValidator = new WeakHashMap<>();

		private static @NonNull Map<@NonNull EClass, @NonNull Collection<@NonNull ConstraintSelection>> getEClass2ConstraintSelections(@NonNull EnvironmentFactory environmentFactory, @NonNull ASResource asResource) {
			Map<@NonNull EClass, @NonNull Collection<@NonNull ConstraintSelection>> eClass2constraintSelections = new HashMap<>();
			for (@NonNull TreeIterator<EObject> tit = asResource.getAllContents(); tit.hasNext(); ) {
				EObject eObject = tit.next();
				if (eObject instanceof org.eclipse.ocl.pivot.Class) {
					org.eclipse.ocl.pivot.Class asClass = (org.eclipse.ocl.pivot.Class)eObject;
					List<Constraint> asInvariants = asClass.getOwnedInvariants();
					if (asInvariants.size() > 0) {
						CompleteClass completeClass = environmentFactory.getCompleteModel().getCompleteClass(asClass);
						EObject esObject = completeClass.getPrimaryClass().getESObject();
						if (esObject instanceof EClass) {
							EClass eClass = (EClass)esObject;
							Collection<@NonNull ConstraintSelection> constraintSelections = eClass2constraintSelections.get(eClass);
							if (constraintSelections == null) {
								constraintSelections = new ArrayList<>();
								eClass2constraintSelections.put(eClass, constraintSelections);
							}
							for (Constraint asConstraint : asInvariants) {
								esObject = asConstraint.getESObject();
								if (esObject instanceof EAnnotation) {
								//	EAnnotation eAnnotation = (EAnnotation)esObject;
								//	EMap<String, String> details = eAnnotation.getDetails();
								//	assert details.size() == 1;
								// XXX	constraintSelections.addAll(details.entrySet());
									constraintSelections.add(new ConstraintSelection(asConstraint));
								}
							}
						}
					}
					tit.prune();
				}
			}
			return eClass2constraintSelections;
		}

		public static void installFor(@NonNull EnvironmentFactory environmentFactory, @NonNull EPackage ePackage, @NonNull ASResource asResource) {
			EValidator eValidator = EValidator.Registry.INSTANCE.getEValidator(ePackage);
		// FIXME	if (eValidator instanceof CompositeEValidator // ComposedValidator) {
			if (eValidator instanceof ExtendedEObjectValidator) {
				((ExtendedEObjectValidator)eValidator).installFor(environmentFactory.getResourceSet(), getEClass2ConstraintSelections(environmentFactory, asResource));
			}
			else if (eValidator != null) {
				ExtendedEObjectValidator extendedEObjectValidator = ePackage2extendedEObjectValidator.get(ePackage);
				if (extendedEObjectValidator == null) {
					extendedEObjectValidator = new ExtendedEObjectValidator(ePackage, eValidator);
					ePackage2extendedEObjectValidator.put(ePackage, extendedEObjectValidator);
				}
				extendedEObjectValidator.installFor(environmentFactory.getResourceSet(), getEClass2ConstraintSelections(environmentFactory, asResource));
				EValidator.Registry.INSTANCE.put(ePackage, extendedEObjectValidator);
			}
			else {
				// XXX null - ?? install a CompleteOCLValidator or wrap the default
			}
		}

		/**
		 * Revert all EValidator.Registry.INSTANCE entries that were displaced to accommodate an ExtendedEObjectValidator.
		 * This is typically used at the end of a JUnit test to clean up for another test.
		 */
		public static void reset() {
			for (ExtendedEObjectValidator extendedEObjectValidator : ePackage2extendedEObjectValidator.values()) {
				EPackage ePackage = extendedEObjectValidator.ePackage;
				EValidator eValidator = extendedEObjectValidator.eValidator;
				EValidator.Registry.INSTANCE.put(ePackage, eValidator);
			}
			ePackage2extendedEObjectValidator.clear();
		}

		public static void uninstallFor(@NonNull EnvironmentFactory environmentFactory, @NonNull EPackage ePackage, @NonNull ASResource asResource) {
			EValidator eValidator = EValidator.Registry.INSTANCE.getEValidator(ePackage);
			if (eValidator instanceof ExtendedEObjectValidator) {
				((ExtendedEObjectValidator)eValidator).uninstallFor(environmentFactory, asResource);
			//	EValidator.Registry.INSTANCE.put(ePackage, instance);		--- could revert to eValidator.eValidator once idle
			}
		}

		protected final @NonNull EPackage ePackage;			// The validated EPackage
		protected final @NonNull EValidator eValidator;		// The displaced EValidator

		public ExtendedEObjectValidator(@NonNull EPackage ePackage, @NonNull EValidator eValidator) {
			this.ePackage = ePackage;
			this.eValidator = eValidator;
		}

		@Override
		public @NonNull EPackage getEPackage() {
			return ePackage;
		}

		public @NonNull EValidator getEValidator() {
			return eValidator;
		}

		private void installFor(@NonNull ResourceSet resourceSet, @NonNull Map<@NonNull EClass, @NonNull Collection<@NonNull ConstraintSelection>> eClass2constraintSelections) {
			ExtendedEObjectValidatorAdapter extendedEObjectValidatorAdapter = null;
			EList<Adapter> eAdapters = resourceSet.eAdapters();
			for (Adapter eAdapter : eAdapters) {
				if (eAdapter instanceof ExtendedEObjectValidatorAdapter) {
					return;
				}
			}
			synchronized (eAdapters) {
				for (Adapter eAdapter : eAdapters) {
					if (eAdapter instanceof ExtendedEObjectValidatorAdapter) {
						extendedEObjectValidatorAdapter = (ExtendedEObjectValidatorAdapter)eAdapter;
						return;
					}
				}
				if (extendedEObjectValidatorAdapter == null) {
					extendedEObjectValidatorAdapter = new ExtendedEObjectValidatorAdapter(resourceSet);
					eAdapters.add(extendedEObjectValidatorAdapter);
				}
			}
			extendedEObjectValidatorAdapter.addConstraintSelections(eClass2constraintSelections);
		}

		private @Nullable ExtendedEObjectValidatorAdapter isApplicableFor(@NonNull ResourceSet resourceSet) {
			for (Adapter eAdapter : resourceSet.eAdapters()) {
				if (eAdapter instanceof ExtendedEObjectValidatorAdapter) {
					return (ExtendedEObjectValidatorAdapter)eAdapter;
				}
			}
			return null;
		}

		private void uninstallFor(@NonNull EnvironmentFactory environmentFactory, @NonNull ASResource asResource) {
			EList<Adapter> eAdapters = environmentFactory.getResourceSet().eAdapters();
			synchronized (eAdapters) {
				for (Adapter eAdapter : eAdapters) {
					if (eAdapter instanceof ExtendedEObjectValidatorAdapter) {
						ExtendedEObjectValidatorAdapter extendedEObjectValidatorAdapter = (ExtendedEObjectValidatorAdapter)eAdapter;
						eAdapters.remove(extendedEObjectValidatorAdapter);
						extendedEObjectValidatorAdapter.removeConstraintSelections(getEClass2ConstraintSelections(environmentFactory, asResource));
						break;
					}
				}
			}
		}

		@Override
		public boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
			// Minimize overhead on the OCL-not-required control path.
			DynamicEClassValidator dynamicEClassValidator = null;
			if (!eObject.eIsProxy()) {
				Resource eResource = eObject.eResource();
				if (eResource != null) {
					ResourceSet resourceSet = eResource.getResourceSet();
					if (resourceSet != null) {
						ExtendedEObjectValidatorAdapter extendedEObjectValidatorAdapter = isApplicableFor(resourceSet);
						if (extendedEObjectValidatorAdapter != null) {		// XXX
							EValidator.ValidationDelegate.Registry validationDelegateRegistry = getValidationDelegateRegistry(context);
							@SuppressWarnings("unused")
							EnvironmentFactoryInternal environmentFactory = ValidationContext.getEnvironmentFactory(context, eObject);
							dynamicEClassValidator = new ExtendedDynamicEClassValidator(extendedEObjectValidatorAdapter, validationDelegateRegistry);			// XXX ?? cache in context
						}
					}
				}
			}
			if (dynamicEClassValidator == null) {
				return eValidator.validate(eClass, eObject, diagnostics, context);
			}
			else {				// Re-implement super.validate to avoid exclusion of delegation for EcorePackage.INSTANCE
				return dynamicEClassValidator.validate(eClass, eObject, diagnostics, context);
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

	private static final @NonNull String CONSTRAINTS_KEY = "constraints";

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
	private static @NonNull List<@NonNull String> getConstraintNames(@NonNull EClass eClass) {
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
	private static void setConstraintNames(@NonNull EClass eClass, @Nullable List<@NonNull String> constraintNames) {
		EAnnotation constraintNamesAnnotation = eClass.getEAnnotation(EcorePackage.eNS_URI);
		if ((constraintNames == null) || constraintNames.isEmpty()) {
			if (constraintNamesAnnotation != null) {
				EMap<String, String> details = constraintNamesAnnotation.getDetails();
				details.removeKey(CONSTRAINTS_KEY);
				if (details.isEmpty()) {
					eClass.getEAnnotations().remove(constraintNamesAnnotation);
				}
			}
		}
		else {
			@SuppressWarnings("null")
			List<String> castConstraintNames = constraintNames;
			Collections.sort(castConstraintNames);
			String splicedConstraintNames = StringUtil.splice(castConstraintNames, " ");
			if (constraintNamesAnnotation == null) {
				EcoreUtil.setAnnotation(eClass, EcorePackage.eNS_URI, CONSTRAINTS_KEY, splicedConstraintNames);
			}
			else {
				EMap<String, String> details = constraintNamesAnnotation.getDetails();
				details.put(CONSTRAINTS_KEY, splicedConstraintNames);
			}
		}
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

	/**
	 * Analyze asResource and return a map of all Class invariant Constraints to eClass2constraints.
	 */
	private @NonNull Map<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> getConstraints(@NonNull ASResource asResource) {
		Map<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> eClass2constraints = new HashMap<>();
		CompleteModelInternal completeModel = environmentFactory.getCompleteModel();
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
	 * convert the Constraints in asResource into a format the regular Diagnostician supports..
	 *
	 * @since 1.23
	 */
	public void installCompleteOCLDelegates(@NonNull ASResource asResource) {
		//
		//	Determine AS Constraints per EClass and the containing EPackages.
		//
		Map<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> eClass2constraints = getConstraints(asResource);
		//
		//	Install EClass EAnnotations for AS Constraints.
		//
		for (Map.Entry<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> entry : eClass2constraints.entrySet()) {
			EClass eClass = entry.getKey();
			UniqueList<@NonNull Constraint> asConstraints = entry.getValue();
			Collections.sort(asConstraints, NameUtil.NAMEABLE_COMPARATOR);
			List<@NonNull String> constraintNames = getConstraintNames(eClass);
			EAnnotation completeOCLbodiesAnnotation = eClass.getEAnnotation(PivotConstants.OCL_DELEGATE_URI_PIVOT_COMPLETE_OCL);
			for (@NonNull Constraint asConstraint : asConstraints) {
				String constraintName = getConstraintName(asConstraint);
				if (!constraintNames.contains(constraintName)) {
					constraintNames.add(constraintName);
					if (completeOCLbodiesAnnotation == null) {
						completeOCLbodiesAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
						completeOCLbodiesAnnotation.setSource(PivotConstants.OCL_DELEGATE_URI_PIVOT_COMPLETE_OCL);
						eClass.getEAnnotations().add(completeOCLbodiesAnnotation);
					}
					@SuppressWarnings("unused")
					String old = completeOCLbodiesAnnotation.getDetails().put(constraintName, PivotConstants.DUMMY_COMPLETE_OCL_BODY);			// XXX toString
					((ConstraintImpl)asConstraint).setESObject(completeOCLbodiesAnnotation);
				}
				else if (completeOCLbodiesAnnotation != null) {
					((ConstraintImpl)asConstraint).setESObject(completeOCLbodiesAnnotation);
				}
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
			if (!validationDelegates.contains(PivotConstants.OCL_DELEGATE_URI_PIVOT_COMPLETE_OCL)) {
				validationDelegates = Lists.newArrayList(validationDelegates);
				validationDelegates.add(PivotConstants.OCL_DELEGATE_URI_PIVOT_COMPLETE_OCL);
				refreshValidationDelegates(ePackage, validationDelegates);
			}
			ExtendedEObjectValidator.installFor(environmentFactory, ePackage, asResource);
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
		EAnnotation constraintNamesAnnotation = eClassifier.getEAnnotation(EcorePackage.eNS_URI);
		if (s != null) {
			if (constraintNamesAnnotation == null) {
				constraintNamesAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
				constraintNamesAnnotation.setSource(EcorePackage.eNS_URI);
				eClassifier.getEAnnotations().add(/*0,*/ constraintNamesAnnotation);
			}
			constraintNamesAnnotation.getDetails().put(CONSTRAINTS_KEY, s.toString());
		}
		else {
			eClassifier.getEAnnotations().remove(constraintNamesAnnotation);
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

	/**
	 * Remove the PivotConstants.OCL_DELEGATE_URI_PIVOT_COMPLETE_OCL EAnnotations synthesized from asResource.
	 *
	 * @since 1.23
	 */
	public void uninstallCompleteOCLDelegates(@NonNull ASResource asResource) {
		//
		//	Uninstall EClass EAnnotations for AS Constraints.
		//
		Map<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> eClass2constraints = getConstraints(asResource);
		for (Map.Entry<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> entry : eClass2constraints.entrySet()) {
			EClass eClass = entry.getKey();
			List<@NonNull Constraint> asConstraints = entry.getValue();
			Collections.sort(asConstraints, NameUtil.NAMEABLE_COMPARATOR);
			List<@NonNull String> constraintNames = getConstraintNames(eClass);
			EAnnotation completeOCLbodiesAnnotation = eClass.getEAnnotation(PivotConstants.OCL_DELEGATE_URI_PIVOT_COMPLETE_OCL);
			if (completeOCLbodiesAnnotation != null) {
				for (@NonNull Constraint asConstraint : asConstraints) {
					String constraintName = getConstraintName(asConstraint);
					constraintNames.remove(constraintName);
					@SuppressWarnings("unused")
					String old = completeOCLbodiesAnnotation.getDetails().removeKey(constraintName);			// XXX toString
				}
				setConstraintNames(eClass, constraintNames);
				if (constraintNames.isEmpty() && completeOCLbodiesAnnotation.getDetails().isEmpty()) {
					eClass.getEAnnotations().remove(completeOCLbodiesAnnotation);
				}
			}
		}
		//
		//	Uninstall EPackage EAnnotations and force DelegateEPackageAdapter/DelegateEClassifierAdapter recomputation.
		//
		Iterable<@NonNull EClass> eClasses = eClass2constraints.keySet();
		List<@NonNull EPackage> ePackages = getEPackages(eClasses);
		for (EPackage ePackage : ePackages) {
			boolean usesCompleteOCL = false;
			for (EClassifier eClassifier : ePackage.getEClassifiers()) {
				EAnnotation completeOCLbodiesAnnotation = eClassifier.getEAnnotation(PivotConstants.OCL_DELEGATE_URI_PIVOT_COMPLETE_OCL);
				if (completeOCLbodiesAnnotation != null) {
					usesCompleteOCL = true;
				}
			}
			if (!usesCompleteOCL) {
				List<String> validationDelegates = EcoreUtil.getValidationDelegates(ePackage);
				if (validationDelegates.remove(PivotConstants.OCL_DELEGATE_URI_PIVOT_COMPLETE_OCL)) {
					validationDelegates = Lists.newArrayList(validationDelegates);
					assert validationDelegates != null;
					refreshValidationDelegates(ePackage, validationDelegates);
				}
				ExtendedEObjectValidator.uninstallFor(environmentFactory, ePackage, asResource);
			}
		}
		refreshValidationDelegates(eClasses);			//	Force DelegateEClassifierAdapter recomputation.
	}
}
