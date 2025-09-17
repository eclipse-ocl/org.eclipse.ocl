/*******************************************************************************
 * Copyright (c) 2025 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.delegate;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreValidator;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.common.OCLCommon;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.dynamic.DerivedEObjectValidator;
import org.eclipse.ocl.pivot.internal.dynamic.DerivedEObjectValidatorManager;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.SemanticException;
import org.eclipse.ocl.pivot.utilities.UniqueList;
import org.eclipse.ocl.pivot.validation.ValidationContext;

/**
 * An ExtendedEObjectValidator instance displaces and wraps the regular EObjectValidator entry in the EValidator.Registry.INSTANCE
 * to add support for the additional constraints and invariants supported by validation delegates.
 * @since 7.0
 */
public class ExtendedEObjectValidator extends EObjectValidator
{
	/**
	 * ExtendedDynamicEClassValidator corrects the inherited functionality to perform the regular validation after
	 * processing any delegated invariants or constraints for all super types.
	 */
	public class ExtendedDynamicEClassValidator extends DynamicEClassValidator
	{
		protected final @NonNull EnvironmentFactoryInternal environmentFactory;
		protected final @NonNull Map<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> eClass2delegateConstraints;
		protected final EValidator.ValidationDelegate.@NonNull Registry validationDelegateRegistry;
		protected final @Nullable ValidationDelegate [] validationDelegates;

		public ExtendedDynamicEClassValidator(@NonNull EnvironmentFactoryInternal environmentFactory,
				@NonNull Map<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> eClass2delegateConstraints,
				EValidator.ValidationDelegate.@NonNull Registry validationDelegateRegistry) {
		//	System.out.println("ctor " + NameUtil.debugSimpleName(this) + " " + NameUtil.debugSimpleName(eClass2delegateConstraints));
			this.environmentFactory = environmentFactory;
			this.eClass2delegateConstraints = eClass2delegateConstraints;
			this.validationDelegateRegistry = validationDelegateRegistry;
			List<String> validationDelegateURIs = EcoreUtil.getValidationDelegates(ePackage);
			this.validationDelegates = new @Nullable ValidationDelegate @NonNull [validationDelegateURIs.size()];
			int index = 0;
			for (String validationDelegateURI : validationDelegateURIs) {
				validationDelegates[index++] = validationDelegateRegistry.getValidationDelegate(validationDelegateURI);
			}
		}

		@Override
		public boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
			assert eClass.getEPackage() == ePackage;
			DerivedEObjectValidator derivedEValidator2 = derivedEValidator;
			if (derivedEValidator2 != null) {
				boolean result = validateDelegates(eClass, eObject, diagnostics, context);
				if (result || diagnostics != null) {
					//	assert eClass.getEPackage() == derivedEValidator.getEPackage();
					result &= derivedEValidator2.validate(eClass.getClassifierID(), eObject, diagnostics, context);		// Normal EMF validator without duplication of delegates
				}
				return result;
			}
			else {
				return eValidator.validate(eClass, eObject, diagnostics, context);		// Normal EMF validator without duplication of delegates
			}
		}

		protected boolean validateDelegatedConstraint(@NonNull EClass eClass, @NonNull EObject eObject, DiagnosticChain diagnostics,
				Map<Object, Object> context, @NonNull Constraint asConstraint, @NonNull EAnnotation eAnnotation) {
			boolean result = true;
			String constraintName = asConstraint.getName();
			try {
				String validationDelegateURI = eAnnotation.getSource();
				ValidationDelegate validationDelegateFactory = validationDelegateRegistry.getValidationDelegate(validationDelegateURI);
				if (validationDelegateFactory instanceof OCLValidationDelegateFactory) {
					OCLValidationDelegateFactory oclValidationDelegateFactory = (OCLValidationDelegateFactory)validationDelegateFactory;
					OCLValidationDelegate validationDelegate = null;
					EClassifier eContainer = (EClassifier)eAnnotation.eContainer();
					if (eContainer != null) {
						validationDelegate = (OCLValidationDelegate)oclValidationDelegateFactory.getValidationDelegate(eContainer);
					}
					if (validationDelegate == null) {
						validationDelegate = (OCLValidationDelegate)oclValidationDelegateFactory.getValidationDelegate(eClass);			// XXX debugging
						throw new IllegalStateException("No '" + validationDelegateURI + "' ValidationDelegate for '" + EObjectValidator.getObjectLabel(eObject, context) + "'");
					}
				//	MetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
				//	ExpressionInOCL query = ValidationBehavior.INSTANCE.getQueryOrThrow(metamodelManager, asConstraint);
					ExpressionInOCL query = (ExpressionInOCL)asConstraint.getOwnedSpecification();
					assert query != null;
				//	System.out.println("validateDelegatedConstraint " + NameUtil.debugSimpleName(this) + " " + NameUtil.debugSimpleName(query) + " " + query + " " + NameUtil.debugSimpleName(asConstraint) +  " " + asConstraint);
					result = validationDelegate.validateExpressionInOCL(environmentFactory, eClass, eObject, null, context, DIAGNOSTIC_SOURCE, 0, query);
				}
				else if (validationDelegateFactory != null) {
					String expression = eAnnotation.getDetails().get(constraintName);
					if (expression != null) {
						result = validationDelegateFactory.validate(eClass, eObject, context, constraintName, expression);
					}
					else {
						Namespace namespace = asConstraint.getContext();
						SemanticException cause = new SemanticException(PivotMessagesInternal.MissingSpecificationBody_ERROR_, namespace, PivotConstantsInternal.CONSTRAINT_ROLE);
						throw new OCLDelegateException(cause);
					}
				}
				else {
					if (diagnostics != null) {
						reportConstraintDelegateNotFound(eClass, eObject, diagnostics, context, constraintName, Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0, validationDelegateURI);
					}
				}
				if (!result) {
					if (diagnostics != null) {
						reportConstraintDelegateViolation(eClass, eObject, diagnostics, context, constraintName, Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0);
					}
				}
			} catch (Throwable throwable) {
				if (diagnostics != null) {
					reportConstraintDelegateException(eClass, eObject, diagnostics, context, constraintName, Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0, throwable);
				}
			}
			return result;
		}

		@Override
		protected boolean validateDelegatedConstraints(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
			throw new IllegalStateException("validateDelegatedInvariants cannot be used");
		}

		protected boolean validateDelegatedInvariant(@NonNull EClass eClass, @NonNull EObject eObject, DiagnosticChain diagnostics,
				Map<Object, Object> context, @NonNull Constraint asConstraint, @NonNull EOperation eOperation) {
			boolean result = false;
			try {
				EAnnotation eAnnotation = OCLCommon.getDelegateAnnotation(eOperation);
				String validationDelegateURI = eAnnotation.getSource();
				ValidationDelegate validationDelegateFactory = validationDelegateRegistry.getValidationDelegate(validationDelegateURI);
				if (validationDelegateFactory instanceof OCLValidationDelegateFactory) {
					OCLValidationDelegateFactory oclValidationDelegateFactory = (OCLValidationDelegateFactory)validationDelegateFactory;
					EClass eContainingClass = eOperation.getEContainingClass();
					assert eContainingClass != null;
					OCLValidationDelegate validationDelegate = (OCLValidationDelegate)oclValidationDelegateFactory.getValidationDelegate(eContainingClass);
					if (validationDelegate == null) {
						validationDelegate = (OCLValidationDelegate)oclValidationDelegateFactory.getValidationDelegate(eClass);			// XXX debugging
						throw new IllegalStateException("No '" + validationDelegateURI + "' ValidationDelegate for '" + EObjectValidator.getObjectLabel(eObject, context) + "'");
					}
					MetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
					ExpressionInOCL query = ValidationBehavior.INSTANCE.getQueryOrThrow(metamodelManager, asConstraint);
				//	ExpressionInOCL query = (ExpressionInOCL)asConstraint.getOwnedSpecification();		// XXX trimmed testValidationTutorial is not pre-parsed
					result = validationDelegate.validateExpressionInOCL(environmentFactory, eClass, eObject, null, context, DIAGNOSTIC_SOURCE, 0, query);
				}
				else if (validationDelegateFactory != null) {
					String expression = eAnnotation.getDetails().get("body");
					if (expression != null) {
						result = validationDelegateFactory.validate(eClass, eObject, context, eOperation, expression);
					}
					else {
						Namespace namespace = asConstraint.getContext();
						SemanticException cause = new SemanticException(PivotMessagesInternal.MissingSpecificationBody_ERROR_, namespace, PivotConstantsInternal.CONSTRAINT_ROLE);
						throw new OCLDelegateException(cause);
					}
				}
				else {
					if (diagnostics != null) {
						reportInvariantDelegateNotFound(eClass, eObject, diagnostics, context, eOperation, Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0, validationDelegateURI);
					}
				}
				if (!result) {
					if (diagnostics != null) {
						reportInvariantDelegateViolation(eClass, eObject, diagnostics, context, eOperation, Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0);
					}
				}
			} catch (Throwable throwable) {
				if (diagnostics != null) {
					reportInvariantDelegateException(eClass, eObject, diagnostics, context, eOperation, Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0, throwable);
				}
			}
			return result;
		}

		@Override
		protected boolean validateDelegatedInvariants(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
			throw new IllegalStateException("validateDelegatedInvariants cannot be used");
		}

		protected boolean validateDelegates(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
			assert eClass != null;
			assert eObject != null;
			Iterable<@NonNull Constraint> asConstraints = eClass2delegateConstraints.get(eClass);
			boolean allOk = true;
			if (asConstraints != null) {
				for (@NonNull Constraint asConstraint : asConstraints) {
					EObject esObject = asConstraint.getESObject();
					if (esObject instanceof EStringToStringMapEntryImpl) {			// OCL delegate constraint
						EStringToStringMapEntryImpl eDetail = (EStringToStringMapEntryImpl)esObject;
						EAnnotation eAnnotation = (EAnnotation)eDetail.eContainer();
						assert OCLCommon.isDelegateURI(eAnnotation.getSource());
						allOk &= validateDelegatedConstraint(eClass, eObject, diagnostics, context, asConstraint, eAnnotation);
					}
					else if (esObject instanceof EAnnotation) {						// EMF Ecore constraint
						EAnnotation eAnnotation = (EAnnotation)esObject;
						assert EcorePackage.eNS_URI.equals(eAnnotation.getSource());
						//
					}
					else if (esObject instanceof EOperation) {
						allOk &= validateDelegatedInvariant(eClass, eObject, diagnostics, context, asConstraint, (EOperation)esObject);
					}
					else if (esObject != null) {
						// never happens
					}
					else if (asConstraint.getOwnedSpecification() != null) {
						//
					}
				}
			}
			return allOk;
		}
	}

	private static final @NonNull WeakHashMap<@NonNull EPackage, @NonNull ExtendedEObjectValidator> ePackage2extendedEObjectValidator = new WeakHashMap<>();

	private static void addConstraint(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull Collection<@NonNull URI> delegateURIs, @NonNull Constraint asConstraint) {
	//	Resource eResource = asConstraint.eResource();
		EObject esObject = asConstraint.getESObject();
		if (esObject == null) {							// XXX Complete OCL partial class - dynamic delegate is another partial
			Type asType = PivotUtil.getContainingType(asConstraint);
			assert asType != null;
			EClass eClass = (EClass) asType.getESObject();
			for (EOperation eOperation : eClass.getEOperations()) {
				assert eOperation != null;
				String name = NameUtil.getOriginalName(eOperation);
				if (Objects.equals(name, asConstraint.getName())) {
					System.out.println("Missing esObject for " + asType.getName() + "::" + asConstraint.getName());		// Upgrade MetaModel generator
					return;
				}
			}
			System.err.println("Missing esObject for " + asType.getName() + "::" + asConstraint.getName());
		//	assert false;
		}
		else if (esObject instanceof EAnnotation) {									// EMF Ecore Java-implemented constraint
			EAnnotation eAnnotation = (EAnnotation)esObject;
			assert EcorePackage.eNS_URI.equals(eAnnotation.getSource());
			// not a delegate
		}
		else if (esObject instanceof EStringToStringMapEntryImpl) {					// OCL delegate
			EStringToStringMapEntryImpl eDetail = (EStringToStringMapEntryImpl)esObject;
			EAnnotation eAnnotation = (EAnnotation)eDetail.eContainer();
			assert OCLCommon.isDelegateURI(eAnnotation.getSource());
			URI reloadableURI = asConstraint.getReloadableURI(environmentFactory);
			assert reloadableURI != null;			// XXX
			assert !PivotUtil.isASURI(reloadableURI);
			delegateURIs.add(reloadableURI);
		}
		else if (esObject instanceof EOperation) {									// invariant operation e.g. validateYYY
			EOperation eOperation = (EOperation)esObject;
			EAnnotation eAnnotation = OCLCommon.getDelegateAnnotation(eOperation);
			if (eAnnotation != null) {												// OCL-defined invariant
				String bodyText = eAnnotation.getDetails().get("body");
				assert bodyText != null;
				assert !"«null»".equals(String.valueOf(asConstraint.getOwnedSpecification()));		// XXX

				URI reloadableURI = asConstraint.getReloadableURI(environmentFactory);
				assert reloadableURI != null;
				delegateURIs.add(reloadableURI);
			}
			else {																	// EMF Ecore Java-implemented invariant
		//		Type asType = PivotUtil.getContainingType(asConstraint);
		//		CompleteClass asCompleteClass = environmentFactory.getCompleteModel().getCompleteClass(asType);
		//		List<org.eclipse.ocl.pivot.Class> partialClasses = asCompleteClass.getPartialClasses();
	// XXX			assert asConstraint.getOwnedSpecification() == null;
				// not a delegate
			}
		}
	}

	private static @NonNull Map<@NonNull EClass, @NonNull UniqueList<@NonNull URI>> gatherEClass2delegateURIs(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull ASResource asResource) {
	//	Map<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> eClass2constraints = new HashMap<>();
		Map<@NonNull EClass, @NonNull UniqueList<@NonNull URI>> eClass2delegateURIs = new HashMap<>();
		CompleteModel completeModel = environmentFactory.getCompleteModel();
		for (CompletePackage completePackage : completeModel.getAllCompletePackages()) {
			for (CompleteClass completeClass : completePackage.getOwnedCompleteClasses()) {
				org.eclipse.ocl.pivot.Class asClass = completeClass.getPrimaryClass();
				if ("Signal".equals(asClass.getName()) ) {
					environmentFactory.getClass();		// XXX
				}
				EObject esObject = asClass.getESObject();
				if (esObject instanceof EClass) {
					EClass eClass = (EClass)esObject;
					Iterable<@NonNull Object> allInvariantOrInvariants = completeModel.getAllCompleteInvariants(asClass);			// XXX skip supers - done later
					if (allInvariantOrInvariants != null) {
						UniqueList<@NonNull URI> delegateURIs = eClass2delegateURIs.get(eClass);
						if (delegateURIs == null) {
							delegateURIs = new UniqueList<>();
							eClass2delegateURIs.put(eClass, delegateURIs);
						}
						for (Object invariantOrInvariants : allInvariantOrInvariants) {
							if (invariantOrInvariants instanceof Constraint) {
								Constraint asConstraint = (Constraint)invariantOrInvariants;
								addConstraint(environmentFactory, delegateURIs, asConstraint);
							}
							else {
								@SuppressWarnings("unchecked")
								List<@NonNull Constraint> invariants = (List<@NonNull Constraint>)invariantOrInvariants;
								for (Constraint asConstraint : invariants) {
									addConstraint(environmentFactory, delegateURIs, asConstraint);
								}
							}
						}
					}
				}
			}
		}
		return eClass2delegateURIs;
	}

	public static void installFor(@NonNull ResourceSet userResourceSet, @NonNull EnvironmentFactoryInternal environmentFactory, @NonNull EPackage ePackage, @NonNull ASResource asResource) throws SemanticException {
		assert userResourceSet == environmentFactory.getUserResourceSet();			// XXX
		EValidator eValidator = EValidator.Registry.INSTANCE.getEValidator(ePackage);
	// XXX FIXME	if (eValidator instanceof CompositeEValidator // ComposedValidator) {
		if (eValidator instanceof ExtendedEObjectValidator) {
			ExtendedEObjectValidator extendedEObjectValidator = (ExtendedEObjectValidator)eValidator;
			extendedEObjectValidator.installFor(userResourceSet, gatherEClass2delegateURIs(environmentFactory, asResource));
		}
		else {
			if (eValidator == null) {
				eValidator = EObjectValidator.INSTANCE;
				assert eValidator != null;
			}
			ExtendedEObjectValidator extendedEObjectValidator = ePackage2extendedEObjectValidator.get(ePackage);
			if (extendedEObjectValidator == null) {
				extendedEObjectValidator = new ExtendedEObjectValidator(ePackage, eValidator);
				ePackage2extendedEObjectValidator.put(ePackage, extendedEObjectValidator);
			}
			extendedEObjectValidator.installFor(userResourceSet, gatherEClass2delegateURIs(environmentFactory, asResource));		// XXX share / promote
			EValidator.Registry.INSTANCE.put(ePackage, extendedEObjectValidator);
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

	public static void uninstallFor(@NonNull ResourceSet userResourceSet, @NonNull EPackage ePackage, @NonNull ASResource asResource) {
		EValidator eValidator = EValidator.Registry.INSTANCE.getEValidator(ePackage);
		if (eValidator instanceof ExtendedEObjectValidator) {
			((ExtendedEObjectValidator)eValidator).uninstallFor(userResourceSet, asResource);
		//	EValidator.Registry.INSTANCE.put(ePackage, instance);		--- could revert to eValidator.eValidator once idle
		}
	}

	protected final @NonNull EPackage ePackage;						// The validated EPackage
	protected final @NonNull EValidator eValidator;					// The displaced EValidator
	protected @Nullable DerivedEObjectValidator derivedEValidator;	// The displaced EValidator with a public validate(int... method

	public ExtendedEObjectValidator(@NonNull EPackage ePackage, @NonNull EValidator eValidator) throws SemanticException {
		this.ePackage = ePackage;
		this.eValidator = eValidator;
		if (eValidator instanceof EObjectValidator) {
			try {
				EObjectValidator eObjectValidator = (EObjectValidator)eValidator;
				Class<? extends DerivedEObjectValidator> derivedEObjectValidatorClass = DerivedEObjectValidatorManager.getInstance().findDerivedEObjectValidator(eObjectValidator.getClass());
				derivedEValidator = derivedEObjectValidatorClass.getDeclaredConstructor().newInstance();
			} catch (SemanticException e) {
				throw e;
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | IllegalArgumentException | SecurityException e) {
				throw new SemanticException(e.getMessage());		// XXX
			}
		}
	}

	@Override
	public @NonNull EPackage getEPackage() {
		return ePackage;
	}

	public @NonNull EValidator getEValidator() {
		return eValidator;
	}

	private void installFor(@NonNull ResourceSet resourceSet, @NonNull Map<@NonNull EClass, @NonNull UniqueList<@NonNull URI>> eClass2delegateURIs) {
		ExtendedEObjectValidatorAdapter extendedEObjectValidatorAdapter = null;
		List<Adapter> eAdapters = resourceSet.eAdapters();
		for (Adapter eAdapter : eAdapters) {
			if (eAdapter instanceof ExtendedEObjectValidatorAdapter) {
				extendedEObjectValidatorAdapter = (ExtendedEObjectValidatorAdapter)eAdapter;
				break;
			}
		}
		if (extendedEObjectValidatorAdapter == null) {
			synchronized (eAdapters) {
				for (Adapter eAdapter : eAdapters) {
					if (eAdapter instanceof ExtendedEObjectValidatorAdapter) {
						extendedEObjectValidatorAdapter = (ExtendedEObjectValidatorAdapter)eAdapter;
						break;
					}
				}
				if (extendedEObjectValidatorAdapter == null) {
					extendedEObjectValidatorAdapter = new ExtendedEObjectValidatorAdapter(resourceSet);
					eAdapters.add(extendedEObjectValidatorAdapter);
				}
			}
		}
		extendedEObjectValidatorAdapter.addEClass2DelegateURIs(eClass2delegateURIs);
	}

	private void uninstallFor(@NonNull ResourceSet userResourceSet, @NonNull ASResource asResource) {
		EList<Adapter> eAdapters = userResourceSet.eAdapters();
		synchronized (eAdapters) {
			for (Adapter eAdapter : eAdapters) {
				if (eAdapter instanceof ExtendedEObjectValidatorAdapter) {
					ExtendedEObjectValidatorAdapter extendedEObjectValidatorAdapter = (ExtendedEObjectValidatorAdapter)eAdapter;
					eAdapters.remove(extendedEObjectValidatorAdapter);
// XXX				extendedEObjectValidatorAdapter.removeConstraintsOrURIs(geteClass2delegateURIs(environmentFactory, asResource));		// XXX recompute / invalidate cache after unload
					break;
				}
			}
		}
	}

	@Override
	public boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		assert context != null;
		if (eObject.eIsProxy()) {													// If proxy
			return eValidator.validate(eClass, eObject, diagnostics, context);		//  regular EMF validation
		}
		if (context.containsKey(DelegateInstaller.SUPPRESS_DYNAMIC_OCL_DELEGATES)) {						// If OCL suppressed because wrong ResourceSet
			return eValidator.validate(eClass, eObject, diagnostics, context);	//  regular EMF validation
		}
		DynamicEClassValidator dynamicEClassValidator = (DynamicEClassValidator)context.get(ExtendedDynamicEClassValidator.class);
		if (dynamicEClassValidator != null) {									// If OCL support already available
			return dynamicEClassValidator.validate(eClass, eObject, diagnostics, context);		// OCL enabled validation
		}
		if (context.containsKey(ExtendedDynamicEClassValidator.class)) {		// If fatal OCL support isuue already reported
			return diagnostics != null;
		}
		ExtendedEObjectValidatorAdapter extendedEObjectValidatorAdapter = null;
		EnvironmentFactoryInternal environmentFactory = null;
		Resource eResource = eObject.eResource();
		if (eResource != null) {
			//
			//	Look for Complete OCL support in the eObject's ResourceSet
			//
			ResourceSet resourceSet = eResource.getResourceSet();
			if (resourceSet != null) {
				for (Adapter eAdapter : resourceSet.eAdapters()) {
					if (eAdapter instanceof ExtendedEObjectValidatorAdapter) {		// If ResourceSet enables OCL validation
						extendedEObjectValidatorAdapter = (ExtendedEObjectValidatorAdapter)eAdapter;
						environmentFactory = ValidationContext.getEnvironmentFactory(context, eObject);
						break;
					}
				}
				//
				//	Else look for Complete OCL support in the the prevailing EnvironmentFactory that complements the eObject's ResourceSet
				/*
				if (extendedEObjectValidatorAdapter == null) {
					environmentFactory = ValidationContext.basicGetEnvironmentFactory(context, eObject);
					if ((environmentFactory != null) && environmentFactory.canValidate(resourceSet)) {
						resourceSet = environmentFactory.getResourceSet();
						for (Adapter eAdapter : resourceSet.eAdapters()) {
							if (eAdapter instanceof ExtendedEObjectValidatorAdapter) {		// If ResourceSet enables OCL validation
								extendedEObjectValidatorAdapter = (ExtendedEObjectValidatorAdapter)eAdapter;
								break;
							}
						}
					}
				} */
			}
		}
		if (extendedEObjectValidatorAdapter != null) {
			assert environmentFactory != null;
			@SuppressWarnings("null")
			EValidator.ValidationDelegate.@NonNull Registry validationDelegateRegistry = getValidationDelegateRegistry(context);
			try {
				Map<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> eClass2delegateConstraints = extendedEObjectValidatorAdapter.resolveDelegateConstraints(environmentFactory);
				dynamicEClassValidator = new ExtendedDynamicEClassValidator(environmentFactory, eClass2delegateConstraints, validationDelegateRegistry);			// XXX ?? cache in context
				context.put(ExtendedDynamicEClassValidator.class, dynamicEClassValidator);		// cache for other element validations
				return dynamicEClassValidator.validate(eClass, eObject, diagnostics, context);		// OCL enabled validation
			} catch (ParserException e) {
				context.put(ExtendedDynamicEClassValidator.class, null);		// cache the failure for other element validations
				if (diagnostics != null) {
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, EcoreValidator.DIAGNOSTIC_SOURCE,
						0, "OCL Delegate Initialization Failed", new Object[] { eObject, e }));
				}
				return diagnostics != null;
			}
		}
		else {
			//
			//	Installing a variant of ExtendedDynamicEClassValidator that suppresses just OCL_DELEGATE_URI_PIVOT_DYNAMIC
			//	would allow regular validation to benefit from the one-off meta-analysis of all applicable constraints but would
			//	require OCL-free metamodels to incur the one-off overhead of an Ecore-to-Pivot conversion. So for safety/compatibility
			//	we just cache the don't need dynamic OCL delegates knowledge.
			//
			context.put(DelegateInstaller.SUPPRESS_DYNAMIC_OCL_DELEGATES, Boolean.TRUE);					// cache the 'wrong' ResourceSet for other element validations
			return eValidator.validate(eClass, eObject, diagnostics, context);
		}
	}
}