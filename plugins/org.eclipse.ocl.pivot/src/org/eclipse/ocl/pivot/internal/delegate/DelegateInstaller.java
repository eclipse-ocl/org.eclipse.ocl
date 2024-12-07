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

import java.lang.reflect.InvocationTargetException;
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
import org.eclipse.ocl.pivot.CompleteModel;
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
import org.eclipse.ocl.pivot.internal.dynamic.DerivedEObjectValidator;
import org.eclipse.ocl.pivot.internal.dynamic.DerivedEObjectValidatorManager;
import org.eclipse.ocl.pivot.internal.ecore.as2es.AS2Ecore;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrintOptions;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrinter;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
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
import org.eclipse.ocl.pivot.validation.ValidationContext;

import com.google.common.collect.Lists;

/**
 * A DelegateInstaller supports installation of Ecore delegates to implement functionality defined by OCL expressions emdedded
 * in AS Constraints, Operations and Properties.
 */
public class DelegateInstaller
{
	/**
	 * ExtendedEObjectValidatorAdapter adapts a ResourceSet to enable the ExtendedEObjectValidator functionality for
	 * elements of the ResourceSet.
	 * @since 1.23
	 */
	public static class ExtendedEObjectValidatorAdapter implements Adapter
	{
		private @Nullable ResourceSet resourceSet;
		private @NonNull Map<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> eClass2constraints = new HashMap<>();

		public ExtendedEObjectValidatorAdapter(@NonNull ResourceSet resourceSet) {
			this.resourceSet = resourceSet;
		}

		public void addConstraints(@NonNull Map<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> addEClass2constraints) {
			for (Map.@NonNull Entry<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> entry : addEClass2constraints.entrySet()) {
				EClass eClass = entry.getKey();
				UniqueList<@NonNull Constraint> newConstraints = entry.getValue();
				UniqueList<@NonNull Constraint> allConstraints = eClass2constraints.get(eClass);
				if (allConstraints == null) {
					allConstraints = new UniqueList<>();
					eClass2constraints.put(eClass, allConstraints);
				}
				allConstraints.addAll(newConstraints);
			}
		}

		public Iterable<@NonNull Constraint> getConstraints(@NonNull EClass eClass) {
			UniqueList<@NonNull Constraint> asConstraints = eClass2constraints.get(eClass);
			if (asConstraints == null) {
				asConstraints = new UniqueList<>();
				for (EClass eSuperType : eClass.getESuperTypes()) {		// XXX generic supertypes
					getSuperConstraints(asConstraints, eSuperType);
				}
				// XXX supers
				eClass2constraints.put(eClass, asConstraints);
			}
			return asConstraints;
		}

		protected Iterable<@NonNull Constraint> getSuperConstraints(@NonNull Collection<@NonNull Constraint> asConstraints, @NonNull EClass eClass) {
			@Nullable Collection<@NonNull Constraint> asConstraints2 = eClass2constraints.get(eClass);
			if (asConstraints2 != null) {
				asConstraints.addAll(asConstraints2);		// XXX duplicates
			}
			else {
				for (EClass eSuperType : eClass.getESuperTypes()) {		// XXX generic supertypes
					getSuperConstraints(asConstraints, eSuperType);
				}
			}
			return asConstraints;
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

		public void removeConstraints(@NonNull Map<@NonNull EClass, @NonNull Collection<@NonNull Constraint>> removeEClass2Constraints) {
			for (Map.@NonNull Entry<@NonNull EClass, @NonNull Collection<@NonNull Constraint>> entry : removeEClass2Constraints.entrySet()) {
				EClass eClass = entry.getKey();
				Collection<@NonNull Constraint> removeConstraints = entry.getValue();
				Collection<@NonNull Constraint> allConstraints = eClass2constraints.get(eClass);
				if (allConstraints != null) {
					allConstraints.removeAll(removeConstraints);
					if (allConstraints.size() <= 0) {
						eClass2constraints.remove(eClass);
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
	 * @since 1.23
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
			protected final @NonNull EnvironmentFactoryInternal environmentFactory;
			protected final EValidator.ValidationDelegate.@NonNull Registry validationDelegateRegistry;
			protected final @Nullable ValidationDelegate [] validationDelegates;

			public ExtendedDynamicEClassValidator(@NonNull ExtendedEObjectValidatorAdapter extendedEObjectValidatorAdapter, @NonNull EnvironmentFactoryInternal environmentFactory, EValidator.ValidationDelegate.@NonNull Registry validationDelegateRegistry) {
				this.extendedEObjectValidatorAdapter = extendedEObjectValidatorAdapter;
				this.environmentFactory = environmentFactory;
				this.validationDelegateRegistry = validationDelegateRegistry;
				List<String> validationDelegateURIs = EcoreUtil.getValidationDelegates(ePackage);
				validationDelegates = new @Nullable ValidationDelegate @NonNull [validationDelegateURIs.size()];
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
						OCLValidationDelegate validationDelegate = (OCLValidationDelegate)oclValidationDelegateFactory.getValidationDelegate((EClassifier)eAnnotation.eContainer());
						if (validationDelegate == null) {
							validationDelegate = (OCLValidationDelegate)oclValidationDelegateFactory.getValidationDelegate(eClass);			// XXX debugging
							throw new IllegalStateException("No '" + validationDelegateURI + "' ValidationDelegate for '" + EObjectValidator.getObjectLabel(eObject, context) + "'");
						}
					//	MetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
					//	ExpressionInOCL query = ValidationBehavior.INSTANCE.getQueryOrThrow(metamodelManager, asConstraint);
						ExpressionInOCL query = (ExpressionInOCL)asConstraint.getOwnedSpecification();
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
						OCLValidationDelegate validationDelegate = (OCLValidationDelegate)oclValidationDelegateFactory.getValidationDelegate(eOperation.getEContainingClass());
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
				Iterable<@NonNull Constraint> asConstraints = extendedEObjectValidatorAdapter.getConstraints(eClass);
				boolean allOk = true;
				if (asConstraints != null) {
					for (@NonNull Constraint asConstraint : asConstraints) {
						EObject esObject = asConstraint.getESObject();
						if (esObject instanceof EAnnotation) {
							EAnnotation eAnnotation = (EAnnotation)esObject;
							allOk &= validateDelegatedConstraint(eClass, eObject, diagnostics, context, asConstraint, eAnnotation);
						}
						else if (esObject instanceof EOperation) {
							allOk &= validateDelegatedInvariant(eClass, eObject, diagnostics, context, asConstraint, (EOperation)esObject);
						}
					}
				}
				return allOk;
			}
		}

		private static final @NonNull WeakHashMap<@NonNull EPackage, @NonNull ExtendedEObjectValidator> ePackage2extendedEObjectValidator = new WeakHashMap<>();

		private static void addConstraint(@NonNull Collection<@NonNull Constraint> asConstraints, @NonNull Constraint asConstraint) {
			EObject esObject = asConstraint.getESObject();
			if (esObject instanceof EAnnotation) {								// EMF constraint
				String constraintName = asConstraint.getName();
				String constraintNameText = OCLCommon.getDelegateAnnotation((EModelElement)((EAnnotation)esObject).eContainer(), constraintName);
				if (constraintNameText != null) {
					asConstraints.add(asConstraint);
				}
				else {
					System.err.println("Missing " + constraintName + " detail");		// XXX
				}
			}
			else if (esObject instanceof EOperation) {							// EMF invariant
				EOperation eOperation = (EOperation)esObject;
				String bodyText = OCLCommon.getDelegateAnnotation(eOperation, "body");
				if (bodyText != null) {
					asConstraints.add(asConstraint);
				}
				else {		// Manually implemented Java Constraint
				//	assert eOperation.getEAnnotations().isEmpty();
					// XXX verify that XXXValidate has validateClassName_validateOperationName
				//	System.err.println("Missing body detail");		// XXX
				}
			}
			else if (esObject != null) {				// Null for Java implementations
				assert false;
			}
		}

		private static @NonNull Map<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> getEClass2Constraints(@NonNull EnvironmentFactory environmentFactory, @NonNull ASResource asResource) {
			Map<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> eClass2constraints = new HashMap<>();
			CompleteModel completeModel = environmentFactory.getCompleteModel();
			for (CompletePackage completePackage : completeModel.getAllCompletePackages()) {
				for (CompleteClass completeClass : completePackage.getOwnedCompleteClasses()) {
					org.eclipse.ocl.pivot.Class asClass = completeClass.getPrimaryClass();
					Iterable<@NonNull Object> allInvariantOrInvariants = completeModel.getAllCompleteInvariants(asClass);
					if (allInvariantOrInvariants != null) {
						EObject esObject = completeClass.getPrimaryClass().getESObject();
						if (esObject instanceof EClass) {
							EClass eClass = (EClass)esObject;
							UniqueList<@NonNull Constraint> constraints = eClass2constraints.get(eClass);
							if (constraints == null) {
								constraints = new UniqueList<>();
								eClass2constraints.put(eClass, constraints);
							}
							for (Object invariantOrInvariants : allInvariantOrInvariants) {
								if (invariantOrInvariants instanceof Constraint) {
									Constraint asConstraint = (Constraint)invariantOrInvariants;
									addConstraint(constraints, asConstraint);
								}
								else {
									@SuppressWarnings("unchecked")
									List<@NonNull Constraint> invariants = (List<@NonNull Constraint>)invariantOrInvariants;
									for (Constraint asConstraint : invariants) {
										addConstraint(constraints, asConstraint);
									}
								}
							}
						}
					}
				}
			}
			return eClass2constraints;
		}

		@Deprecated /* @deprecated all resources XXX */
		private static @NonNull Map<@NonNull EClass, @NonNull Collection<@NonNull Constraint>> getEClass2Constraints2(@NonNull EnvironmentFactory environmentFactory, @NonNull ASResource asResource) {
			Map<@NonNull EClass, @NonNull Collection<@NonNull Constraint>> eClass2constraints = new HashMap<>();
			CompleteModel completeModel = environmentFactory.getCompleteModel();
			for (@NonNull TreeIterator<EObject> tit = asResource.getAllContents(); tit.hasNext(); ) {
				EObject eObject = tit.next();
				if (eObject instanceof org.eclipse.ocl.pivot.Class) {
					org.eclipse.ocl.pivot.Class asClass = (org.eclipse.ocl.pivot.Class)eObject;
					CompleteClass completeClass = completeModel.getCompleteClass(asClass);
					Iterable<@NonNull Object> allInvariantOrInvariants = completeModel.getAllCompleteInvariants(asClass);
					if (allInvariantOrInvariants != null) {
						EObject esObject = completeClass.getPrimaryClass().getESObject();
						if (esObject instanceof EClass) {
							EClass eClass = (EClass)esObject;
							Collection<@NonNull Constraint> constraints = eClass2constraints.get(eClass);
							if (constraints == null) {
								constraints = new ArrayList<>();
								eClass2constraints.put(eClass, constraints);
							}
							for (Object invariantOrInvariants : allInvariantOrInvariants) {
								if (invariantOrInvariants instanceof Constraint) {
									Constraint asConstraint = (Constraint)invariantOrInvariants;
									addConstraint(constraints, asConstraint);
								}
								else {
									@SuppressWarnings("unchecked")
									List<@NonNull Constraint> invariants = (List<@NonNull Constraint>)invariantOrInvariants;
									for (Constraint asConstraint : invariants) {
										addConstraint(constraints, asConstraint);
									}
								}
							}
						}
					}
					tit.prune();
				}
			}
			return eClass2constraints;
		}

		public static void installFor(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull EPackage ePackage, @NonNull ASResource asResource) throws SemanticException {
			EValidator eValidator = EValidator.Registry.INSTANCE.getEValidator(ePackage);
		// XXX FIXME	if (eValidator instanceof CompositeEValidator // ComposedValidator) {
			if (eValidator instanceof ExtendedEObjectValidator) {
				((ExtendedEObjectValidator)eValidator).installFor(environmentFactory.getResourceSet(), getEClass2Constraints(environmentFactory, asResource));
			}
			else {
				if (eValidator == null) {
					eValidator = EObjectValidator.INSTANCE;
				}
				ExtendedEObjectValidator extendedEObjectValidator = ePackage2extendedEObjectValidator.get(ePackage);
				if (extendedEObjectValidator == null) {
					extendedEObjectValidator = new ExtendedEObjectValidator(ePackage, eValidator);
					ePackage2extendedEObjectValidator.put(ePackage, extendedEObjectValidator);
				}
				extendedEObjectValidator.installFor(environmentFactory.getResourceSet(), getEClass2Constraints(environmentFactory, asResource));
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

		public static void uninstallFor(@NonNull EnvironmentFactory environmentFactory, @NonNull EPackage ePackage, @NonNull ASResource asResource) {
			EValidator eValidator = EValidator.Registry.INSTANCE.getEValidator(ePackage);
			if (eValidator instanceof ExtendedEObjectValidator) {
				((ExtendedEObjectValidator)eValidator).uninstallFor(environmentFactory, asResource);
			//	EValidator.Registry.INSTANCE.put(ePackage, instance);		--- could revert to eValidator.eValidator once idle
			}
		}

		protected final @NonNull EPackage ePackage;						// The validated EPackage
		protected final @NonNull EValidator eValidator;					// The displaced EValidator
		protected @Nullable DerivedEObjectValidator derivedEValidator;	// The displaced EValidator with a public validate(int... methiod

		public ExtendedEObjectValidator(@NonNull EPackage ePackage, @NonNull EValidator eValidator) throws SemanticException {
			this.ePackage = ePackage;
			this.eValidator = eValidator;
			if (eValidator instanceof EObjectValidator) {
				try {
					Class<? extends DerivedEObjectValidator> derivedEObjectValidatorClass = DerivedEObjectValidatorManager.getInstance().findDerivedEObjectValidator(((EObjectValidator)eValidator).getClass());
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

		private void installFor(@NonNull ResourceSet resourceSet, @NonNull Map<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> eClass2constraints) {
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
			extendedEObjectValidatorAdapter.addConstraints(eClass2constraints);
		}

		private void uninstallFor(@NonNull EnvironmentFactory environmentFactory, @NonNull ASResource asResource) {
			EList<Adapter> eAdapters = environmentFactory.getResourceSet().eAdapters();
			synchronized (eAdapters) {
				for (Adapter eAdapter : eAdapters) {
					if (eAdapter instanceof ExtendedEObjectValidatorAdapter) {
						ExtendedEObjectValidatorAdapter extendedEObjectValidatorAdapter = (ExtendedEObjectValidatorAdapter)eAdapter;
						eAdapters.remove(extendedEObjectValidatorAdapter);
						extendedEObjectValidatorAdapter.removeConstraints(getEClass2Constraints2(environmentFactory, asResource));		// XXX recompute / invalidate cache after unload
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
			if (context.containsKey(SUPPRESS_DYNAMIC_OCL_DELEGATES)) {						// If OCL suppressed because wrong ResourceSet
				return eValidator.validate(eClass, eObject, diagnostics, context);	//  regular EMF validation
			}
			DynamicEClassValidator dynamicEClassValidator = (DynamicEClassValidator)context.get(ExtendedDynamicEClassValidator.class);
			if (dynamicEClassValidator != null) {									// If OCL support already available
				return dynamicEClassValidator.validate(eClass, eObject, diagnostics, context);		// OCL enabled validation
			}
			Resource eResource = eObject.eResource();
			if (eResource != null) {
				ResourceSet resourceSet = eResource.getResourceSet();
				if (resourceSet != null) {
					ExtendedEObjectValidatorAdapter extendedEObjectValidatorAdapter = null;
					for (Adapter eAdapter : resourceSet.eAdapters()) {
						if (eAdapter instanceof ExtendedEObjectValidatorAdapter) {		// If ResourceSet enables OCL validation
							extendedEObjectValidatorAdapter = (ExtendedEObjectValidatorAdapter)eAdapter;
							@SuppressWarnings("null")
							EValidator.ValidationDelegate.@NonNull Registry validationDelegateRegistry = getValidationDelegateRegistry(context);
							EnvironmentFactoryInternal environmentFactory = ValidationContext.getEnvironmentFactory(context, eObject);
							dynamicEClassValidator = new ExtendedDynamicEClassValidator(extendedEObjectValidatorAdapter, environmentFactory, validationDelegateRegistry);			// XXX ?? cache in context
							context.put(ExtendedDynamicEClassValidator.class, dynamicEClassValidator);		// cache for other element validations
							return dynamicEClassValidator.validate(eClass, eObject, diagnostics, context);		// OCL enabled validation
						}
					}
					//
					//	Installing a variant of ExtendedDynamicEClassValidator that suppresses just OCL_DELEGATE_URI_PIVOT_DYNAMIC
					//	would allow regular validation to benefit from the one-off meta-analysis of all applicable constraints but would
					//	require OCL-free metamodels to incur the one-off overhead of an Ecore-to-Pivot conversion. So for safety/compatibility
					//	we just cache the don't need dynamic OCL delegates knowledge.
					//
					EnvironmentFactoryInternal environmentFactory = ValidationContext.basicGetEnvironmentFactory(context, eObject);
					if ((environmentFactory == null) || !environmentFactory.canValidate(resourceSet)) {
						context.put(SUPPRESS_DYNAMIC_OCL_DELEGATES, Boolean.TRUE);					// cache the 'wrong' ResourceSet for other element validations
					}
				}
			}
			return eValidator.validate(eClass, eObject, diagnostics, context);
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

	/**
	 * ValidationContext entry that may be set true when validating an EObject whose ResourceSet lacks an ExtendedEObjectValidatorAdapter.
	 * This prevents leakage of additional dynamic OCL constraints applied to an Xtext grammar leaking beyond the intended applications.
	 *
	 * @since 1.23
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
	private @NonNull Map<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> getConstraints(@NonNull Iterable<@NonNull Resource> asResources) {
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
	 * convert the Constraints in asResource into a format the regular Diagnostician supports..
	 * @throws SemanticException
	 *
	 * @since 1.23
	 */
	public void installCompleteOCLDelegates(@NonNull ASResource asResource) throws SemanticException {
		//
		//	Determine AS Constraints per EClass and the containing EPackages.
		//
		Map<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> eClass2constraints = getConstraints(Collections.singletonList(asResource));
		//
		//	Install EClass EAnnotations for AS Constraints.
		//
		for (Map.Entry<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> entry : eClass2constraints.entrySet()) {
			EClass eClass = entry.getKey();
			UniqueList<@NonNull Constraint> asConstraints = entry.getValue();
			Collections.sort(asConstraints, NameUtil.NAMEABLE_COMPARATOR);
			List<@NonNull String> constraintNames = getConstraintNames(eClass);
			EAnnotation completeOCLbodiesAnnotation = eClass.getEAnnotation(PivotConstants.OCL_DELEGATE_URI_PIVOT_DYNAMIC);
			for (@NonNull Constraint asConstraint : asConstraints) {
				String constraintName = getConstraintName(asConstraint);
				if (!constraintNames.contains(constraintName)) {
					constraintNames.add(constraintName);
					if (completeOCLbodiesAnnotation == null) {
						completeOCLbodiesAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
						completeOCLbodiesAnnotation.setSource(PivotConstants.OCL_DELEGATE_URI_PIVOT_DYNAMIC);
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
			if (!validationDelegates.contains(PivotConstants.OCL_DELEGATE_URI_PIVOT_DYNAMIC)) {
				validationDelegates = Lists.newArrayList(validationDelegates);
				validationDelegates.add(PivotConstants.OCL_DELEGATE_URI_PIVOT_DYNAMIC);
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
	 * @since 1.23
	 */
	public void uninstallCompleteOCLDelegates(@NonNull ASResource asResource) { // XXX asymmetric wrt install
		List<@NonNull EAnnotation> eAnnotationsToRemove = new ArrayList<>();
		//
		//	Uninstall EClass EAnnotations for AS Constraints.
		//
		Map<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> eClass2constraints = getConstraints(Collections.singletonList(asResource));
		for (Map.Entry<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> entry : eClass2constraints.entrySet()) {
			EClass eClass = entry.getKey();
			List<@NonNull Constraint> asConstraints = entry.getValue();
			Collections.sort(asConstraints, NameUtil.NAMEABLE_COMPARATOR);
			List<@NonNull String> constraintNames = getConstraintNames(eClass);
			EAnnotation completeOCLbodiesAnnotation = eClass.getEAnnotation(PivotConstants.OCL_DELEGATE_URI_PIVOT_DYNAMIC);
			if (completeOCLbodiesAnnotation != null) {
				for (@NonNull Constraint asConstraint : asConstraints) {
					String constraintName = getConstraintName(asConstraint);
					constraintNames.remove(constraintName);
					@SuppressWarnings("unused")
					String old = completeOCLbodiesAnnotation.getDetails().removeKey(constraintName);			// XXX toString
				}
				setConstraintNames(eClass, constraintNames);
				if (constraintNames.isEmpty() && completeOCLbodiesAnnotation.getDetails().isEmpty()) {
					eAnnotationsToRemove.add(completeOCLbodiesAnnotation);
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
			boolean usesCompleteOCL = false;
			for (EClassifier eClassifier : ePackage.getEClassifiers()) {
				EAnnotation completeOCLbodiesAnnotation = eClassifier.getEAnnotation(PivotConstants.OCL_DELEGATE_URI_PIVOT_DYNAMIC);
				if (completeOCLbodiesAnnotation != null) {
					usesCompleteOCL = true;
				}
			}
			if (!usesCompleteOCL) {
				List<String> validationDelegates = EcoreUtil.getValidationDelegates(ePackage);
				if (validationDelegates.remove(PivotConstants.OCL_DELEGATE_URI_PIVOT_DYNAMIC)) {
					validationDelegates = Lists.newArrayList(validationDelegates);
					assert validationDelegates != null;
					refreshValidationDelegates(ePackage, validationDelegates);
				}
				ExtendedEObjectValidator.uninstallFor(environmentFactory, ePackage, asResource);
			}
		}
		for (@NonNull EAnnotation eAnnotation : eAnnotationsToRemove) {
			((EModelElement)eAnnotation.eContainer()).getEAnnotations().remove(eAnnotation);
		}
		refreshValidationDelegates(eClasses);			//	Force DelegateEClassifierAdapter recomputation.
	}
}
