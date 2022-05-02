/**
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
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
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.ResultVariable;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ValueSpecification;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.classifier.OclTypeConformsToOperation;
import org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation;
import org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation;
import org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Result Variable</b></em>'.
 * @since 1.3
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class ResultVariableImpl extends VariableImpl implements ResultVariable
{
	/**
	 * The number of structural features of the '<em>Result Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RESULT_VARIABLE_FEATURE_COUNT = VariableImpl.VARIABLE_FEATURE_COUNT + 0;
	/**
	 * The number of operations of the '<em>Result Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RESULT_VARIABLE_OPERATION_COUNT = VariableImpl.VARIABLE_OPERATION_COUNT + 3;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResultVariableImpl()
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
		return PivotPackage.Literals.RESULT_VARIABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateCompatibleNullityForInitializer(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		final @NonNull String constraintName = "ResultVariable::CompatibleNullityForInitializer";
		try {
			/**
			 *
			 * inv CompatibleNullityForInitializer:
			 *   let severity : Integer[1] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let result : Boolean[?] = not ownedInit?.isRequired implies not isRequired
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IntegerValue severity = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.RESULT_VARIABLE___VALIDATE_COMPATIBLE_NULLITY_FOR_INITIALIZER__DIAGNOSTICCHAIN_MAP);
			final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity, PivotTables.INT_0).booleanValue();
			/*@NonInvalid*/ boolean IF_le;
			if (le) {
				IF_le = true;
			}
			else {
				/*@Caught*/ @Nullable Object result;
				try {
					/*@Caught*/ @Nullable Object CAUGHT_not;
					try {
						/*@Caught*/ @Nullable Object CAUGHT_IF_IsEQ2_;
						try {
							final /*@NonInvalid*/ @Nullable OCLExpression ownedInit = this.getOwnedInit();
							final /*@NonInvalid*/ @NonNull Object IsEQ2_ = ownedInit == null;
							/*@Thrown*/ @Nullable Boolean IF_IsEQ2_;
							if (IsEQ2_ == Boolean.TRUE) {
								IF_IsEQ2_ = null;
							}
							else {
								assert ownedInit != null;
								final /*@Thrown*/ boolean isRequired_0 = ownedInit.isIsRequired();
								IF_IsEQ2_ = isRequired_0;
							}
							CAUGHT_IF_IsEQ2_ = IF_IsEQ2_;
						}
						catch (Exception THROWN_CAUGHT_IF_IsEQ2_) {
							CAUGHT_IF_IsEQ2_ = ValueUtil.createInvalidValue(THROWN_CAUGHT_IF_IsEQ2_);
						}
						if (CAUGHT_IF_IsEQ2_ instanceof InvalidValueException) {
							throw (InvalidValueException)CAUGHT_IF_IsEQ2_;
						}
						final /*@Thrown*/ @Nullable Boolean not;
						if (CAUGHT_IF_IsEQ2_ == ValueUtil.FALSE_VALUE) {
							not = ValueUtil.TRUE_VALUE;
						}
						else {
							if (CAUGHT_IF_IsEQ2_ == ValueUtil.TRUE_VALUE) {
								not = ValueUtil.FALSE_VALUE;
							}
							else {
								not = null;
							}
						}
						CAUGHT_not = not;
					}
					catch (Exception THROWN_CAUGHT_not) {
						CAUGHT_not = ValueUtil.createInvalidValue(THROWN_CAUGHT_not);
					}
					final /*@Thrown*/ @Nullable Boolean implies;
					if (CAUGHT_not == ValueUtil.FALSE_VALUE) {
						implies = ValueUtil.TRUE_VALUE;
					}
					else {
						final /*@NonInvalid*/ boolean isRequired = this.isIsRequired();
						final /*@NonInvalid*/ @Nullable Boolean not_0;
						if (!isRequired) {
							not_0 = ValueUtil.TRUE_VALUE;
						}
						else {
							if (isRequired) {
								not_0 = ValueUtil.FALSE_VALUE;
							}
							else {
								not_0 = null;
							}
						}
						if (not_0 == ValueUtil.TRUE_VALUE) {
							implies = ValueUtil.TRUE_VALUE;
						}
						else {
							if (CAUGHT_not instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_not;
							}
							if ((CAUGHT_not == null) || (not_0 == null)) {
								implies = null;
							}
							else {
								implies = ValueUtil.FALSE_VALUE;
							}
						}
					}
					result = implies;
				}
				catch (Exception THROWN_result) {
					result = ValueUtil.createInvalidValue(THROWN_result);
				}
				final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, this, (Object)null, diagnostics, context, (Object)null, severity, result, PivotTables.INT_0).booleanValue();
				IF_le = logDiagnostic;
			}
			return IF_le;
		}
		catch (Throwable e) {
			return ValueUtil.validationFailedDiagnostic(constraintName, this, diagnostics, context, e);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateCompatibleTypeForInitializer(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		final @NonNull String constraintName = "ResultVariable::CompatibleTypeForInitializer";
		try {
			/**
			 *
			 * inv CompatibleTypeForInitializer:
			 *   let severity : Integer[1] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let result : Boolean[?] = ownedInit <> null implies
			 *         ownedInit.type?.conformsTo(type)
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IntegerValue severity = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.RESULT_VARIABLE___VALIDATE_COMPATIBLE_TYPE_FOR_INITIALIZER__DIAGNOSTICCHAIN_MAP);
			final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity, PivotTables.INT_0).booleanValue();
			/*@NonInvalid*/ boolean IF_le;
			if (le) {
				IF_le = true;
			}
			else {
				/*@Caught*/ @Nullable Object result;
				try {
					final /*@NonInvalid*/ @Nullable OCLExpression ownedInit = this.getOwnedInit();
					final /*@NonInvalid*/ boolean IsEQ_ = ownedInit != null;
					final /*@Thrown*/ @Nullable Boolean implies;
					if (!IsEQ_) {
						implies = ValueUtil.TRUE_VALUE;
					}
					else {
						/*@Caught*/ @Nullable Object CAUGHT_IF_IsEQ2_;
						try {
							if (ownedInit == null) {
								throw new InvalidValueException("Null source for \'TypedElement::type\'");
							}
							final /*@Thrown*/ @Nullable Type type_0 = ownedInit.getType();
							/*@Caught*/ @Nullable Object CAUGHT_type;
							try {
								CAUGHT_type = type_0;
							}
							catch (Exception THROWN_CAUGHT_type) {
								CAUGHT_type = ValueUtil.createInvalidValue(THROWN_CAUGHT_type);
							}
							final /*@NonInvalid*/ @NonNull Object IsEQ2_ = CAUGHT_type == null;
							/*@Thrown*/ @Nullable Boolean IF_IsEQ2_;
							if (IsEQ2_ == Boolean.TRUE) {
								IF_IsEQ2_ = null;
							}
							else {
								final /*@NonInvalid*/ @Nullable Type type = this.getType();
								if (type_0 == null) {
									throw new InvalidValueException("Null argument");
								}
								final /*@Thrown*/ boolean conformsTo = OclTypeConformsToOperation.INSTANCE.evaluate(executor, type_0, type).booleanValue();
								IF_IsEQ2_ = conformsTo;
							}
							CAUGHT_IF_IsEQ2_ = IF_IsEQ2_;
						}
						catch (Exception THROWN_CAUGHT_IF_IsEQ2_) {
							CAUGHT_IF_IsEQ2_ = ValueUtil.createInvalidValue(THROWN_CAUGHT_IF_IsEQ2_);
						}
						if (CAUGHT_IF_IsEQ2_ == ValueUtil.TRUE_VALUE) {
							implies = ValueUtil.TRUE_VALUE;
						}
						else {
							if (CAUGHT_IF_IsEQ2_ instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_IF_IsEQ2_;
							}
							if (CAUGHT_IF_IsEQ2_ == null) {
								implies = null;
							}
							else {
								implies = ValueUtil.FALSE_VALUE;
							}
						}
					}
					result = implies;
				}
				catch (Exception THROWN_result) {
					result = ValueUtil.createInvalidValue(THROWN_result);
				}
				final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, this, (Object)null, diagnostics, context, (Object)null, severity, result, PivotTables.INT_0).booleanValue();
				IF_le = logDiagnostic;
			}
			return IF_le;
		}
		catch (Throwable e) {
			return ValueUtil.validationFailedDiagnostic(constraintName, this, diagnostics, context, e);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateHasInitializer(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		final @NonNull String constraintName = "ResultVariable::HasInitializer";
		try {
			/**
			 *
			 * inv HasInitializer:
			 *   let severity : Integer[1] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let result : Boolean[1] = ownedInit <> null
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IntegerValue severity = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.RESULT_VARIABLE___VALIDATE_HAS_INITIALIZER__DIAGNOSTICCHAIN_MAP);
			final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity, PivotTables.INT_0).booleanValue();
			/*@NonInvalid*/ boolean IF_le;
			if (le) {
				IF_le = true;
			}
			else {
				final /*@NonInvalid*/ @Nullable OCLExpression ownedInit = this.getOwnedInit();
				final /*@NonInvalid*/ boolean result = ownedInit != null;
				final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, this, (Object)null, diagnostics, context, (Object)null, severity, result, PivotTables.INT_0).booleanValue();
				IF_le = logDiagnostic;
			}
			return IF_le;
		}
		catch (Throwable e) {
			return ValueUtil.validationFailedDiagnostic(constraintName, this, diagnostics, context, e);
		}
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
			case 0:
				return allOwnedElements();
			case 1:
				return getValue((Type)arguments.get(0), (String)arguments.get(1));
			case 2:
				return CompatibleBody((ValueSpecification)arguments.get(0));
			case 3:
				return validateNameIsNotNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 4:
				return validateTypeIsNotInvalid((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 5:
				return validateTypeIsNotNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 6:
				return validateCompatibleInitialiserType((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 7:
				return validateCompatibleNullityForInitializer((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 8:
				return validateCompatibleTypeForInitializer((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 9:
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
		return visitor.visitResultVariable(this);
	}

} //ResultVariableImpl
