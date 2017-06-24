/**
 * Copyright (c) 2010, 2017 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.LetVariable;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ValueSpecification;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.classifier.OclTypeConformsToOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanOrOperation;
import org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation;
import org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation;
import org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Let Variable</b></em>'.
 * @since 1.3
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class LetVariableImpl extends VariableImpl implements LetVariable
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LetVariableImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PivotPackage.Literals.LET_VARIABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateCompatibleNullityForInitializer(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv CompatibleNullityForInitializer:
		 *   let
		 *     severity : Integer[1] = 'LetVariable::CompatibleNullityForInitializer'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let result : Boolean[?] = ownedInit?.isRequired = isRequired or
		 *         ownedInit?.type.conformsTo(OclVoid)
		 *       in
		 *         'LetVariable::CompatibleNullityForInitializer'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_LetVariable_c_c_CompatibleNullityForInitializer);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_2;
		if (le) {
			symbol_2 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @Nullable Object CAUGHT_result;
			try {
				/*@Caught*/ @NonNull Object CAUGHT_eq;
				try {
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedInit = this.getOwnedInit();
					final /*@NonInvalid*/ @NonNull Object symbol_0 = ownedInit == null;
					/*@Thrown*/ java.lang.@Nullable Boolean safe_isRequired_source;
					if (symbol_0 == Boolean.TRUE) {
						safe_isRequired_source = null;
					}
					else {
						assert ownedInit != null;
						final /*@Thrown*/ boolean isRequired = ownedInit.isIsRequired();
						safe_isRequired_source = isRequired;
					}
					final /*@NonInvalid*/ boolean isRequired_0 = this.isIsRequired();
					final /*@Thrown*/ boolean eq = (safe_isRequired_source == Boolean.TRUE) == isRequired_0;
					CAUGHT_eq = eq;
				}
				catch (Exception e) {
					CAUGHT_eq = ValueUtil.createInvalidValue(e);
				}
				/*@Caught*/ @NonNull Object CAUGHT_conformsTo;
				try {
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_OclVoid = idResolver.getClass(TypeId.OCL_VOID, null);
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedInit_0 = this.getOwnedInit();
					final /*@NonInvalid*/ @NonNull Object symbol_1 = ownedInit_0 == null;
					/*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type safe_type_source;
					if (symbol_1 == Boolean.TRUE) {
						safe_type_source = null;
					}
					else {
						assert ownedInit_0 != null;
						final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type = ownedInit_0.getType();
						safe_type_source = type;
					}
					final /*@Thrown*/ boolean conformsTo = OclTypeConformsToOperation.INSTANCE.evaluate(executor, safe_type_source, TYP_OclVoid).booleanValue();
					CAUGHT_conformsTo = conformsTo;
				}
				catch (Exception e) {
					CAUGHT_conformsTo = ValueUtil.createInvalidValue(e);
				}
				final /*@Thrown*/ java.lang.@Nullable Boolean result = BooleanOrOperation.INSTANCE.evaluate(CAUGHT_eq, CAUGHT_conformsTo);
				CAUGHT_result = result;
			}
			catch (Exception e) {
				CAUGHT_result = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_LetVariable_c_c_CompatibleNullityForInitializer, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
			symbol_2 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateCompatibleTypeForInitializer(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv CompatibleTypeForInitializer:
		 *   let
		 *     severity : Integer[1] = 'LetVariable::CompatibleTypeForInitializer'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let result : Boolean[?] = ownedInit <> null implies
		 *         ownedInit.type?.conformsTo(type)
		 *       in
		 *         'LetVariable::CompatibleTypeForInitializer'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_LetVariable_c_c_CompatibleTypeForInitializer);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_1;
		if (le) {
			symbol_1 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @Nullable Object CAUGHT_result;
			try {
				final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedInit = this.getOwnedInit();
				final /*@NonInvalid*/ boolean ne = ownedInit != null;
				/*@Thrown*/ java.lang.@Nullable Boolean result;
				if (ne) {
					if (ownedInit == null) {
						throw new InvalidValueException("Null source for \'TypedElement::type\'");
					}
					final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type = ownedInit.getType();
					/*@Caught*/ @Nullable Object CAUGHT_type;
					try {
						CAUGHT_type = type;
					}
					catch (Exception e) {
						CAUGHT_type = ValueUtil.createInvalidValue(e);
					}
					final /*@NonInvalid*/ @NonNull Object symbol_0 = CAUGHT_type == null;
					/*@Thrown*/ java.lang.@Nullable Boolean safe_conformsTo_source;
					if (symbol_0 == Boolean.TRUE) {
						safe_conformsTo_source = null;
					}
					else {
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable Type type_0 = this.getType();
						final /*@Thrown*/ boolean conformsTo = OclTypeConformsToOperation.INSTANCE.evaluate(executor, type, type_0).booleanValue();
						safe_conformsTo_source = conformsTo;
					}
					result = safe_conformsTo_source;
				}
				else {
					result = ValueUtil.TRUE_VALUE;
				}
				CAUGHT_result = result;
			}
			catch (Exception e) {
				CAUGHT_result = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_LetVariable_c_c_CompatibleTypeForInitializer, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
			symbol_1 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateHasInitializer(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv HasInitializer:
		 *   let severity : Integer[1] = 'LetVariable::HasInitializer'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let result : Boolean[1] = ownedInit <> null
		 *       in
		 *         'LetVariable::HasInitializer'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_LetVariable_c_c_HasInitializer);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedInit = this.getOwnedInit();
			final /*@NonInvalid*/ boolean result = ownedInit != null;
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_LetVariable_c_c_HasInitializer, this, (Object)null, diagnostics, context, (Object)null, severity_0, result, PivotTables.INT_0).booleanValue();
			symbol_0 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException
	{
		switch (operationID)
		{
			case PivotPackage.LET_VARIABLE___ALL_OWNED_ELEMENTS:
				return allOwnedElements();
			case PivotPackage.LET_VARIABLE___GET_VALUE__TYPE_STRING:
				return getValue((Type)arguments.get(0), (String)arguments.get(1));
			case PivotPackage.LET_VARIABLE___COMPATIBLE_BODY__VALUESPECIFICATION:
				return CompatibleBody((ValueSpecification)arguments.get(0));
			case PivotPackage.LET_VARIABLE___VALIDATE_NAME_IS_NOT_NULL__DIAGNOSTICCHAIN_MAP:
				return validateNameIsNotNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.LET_VARIABLE___VALIDATE_TYPE_IS_NOT_INVALID__DIAGNOSTICCHAIN_MAP:
				return validateTypeIsNotInvalid((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.LET_VARIABLE___VALIDATE_TYPE_IS_NOT_NULL__DIAGNOSTICCHAIN_MAP:
				return validateTypeIsNotNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.LET_VARIABLE___VALIDATE_COMPATIBLE_INITIALISER_TYPE__DIAGNOSTICCHAIN_MAP:
				return validateCompatibleInitialiserType((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.LET_VARIABLE___VALIDATE_COMPATIBLE_NULLITY_FOR_INITIALIZER__DIAGNOSTICCHAIN_MAP:
				return validateCompatibleNullityForInitializer((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.LET_VARIABLE___VALIDATE_COMPATIBLE_TYPE_FOR_INITIALIZER__DIAGNOSTICCHAIN_MAP:
				return validateCompatibleTypeForInitializer((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.LET_VARIABLE___VALIDATE_HAS_INITIALIZER__DIAGNOSTICCHAIN_MAP:
				return validateHasInitializer((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
		}
		return eDynamicInvoke(operationID, arguments);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitLetVariable(this);
	}

} //LetVariableImpl
