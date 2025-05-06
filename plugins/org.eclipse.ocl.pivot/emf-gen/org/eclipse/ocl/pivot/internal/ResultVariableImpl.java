/**
 * Copyright (c) 2010, 2025 Willink Transformations and others.
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
			 *   let severity : Integer[?] = constraintName.getSeverity()
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
			final /*@NonInvalid*/ @Nullable IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.RESULT_VARIABLE___VALIDATE_COMPATIBLE_NULLITY_FOR_INITIALIZER__DIAGNOSTICCHAIN_MAP);
			if (severity_0 == null) {
				throw new InvalidValueException("Null \'\'OclComparable\'\' rather than \'\'OclVoid\'\' value required");
			}
			final /*@Thrown*/ @Nullable Boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0);
			if (le == null) {
				throw new InvalidValueException("Null if condition");
			}
			/*@NonInvalid*/ @Nullable Boolean IF_le;
			if (le) {
				IF_le = ValueUtil.TRUE_VALUE;
			}
			else {
				/*@Caught*/ @Nullable Object CAUGHT_result;
				try {
					/*@Caught*/ @Nullable Object CAUGHT_not;
					try {
						/*@Caught*/ @Nullable Object CAUGHT_safe_isRequired_source;
						try {
							if (this == null) {
								throw new InvalidValueException("Null source for \'Variable::ownedInit\'");
							}
							final /*@Thrown*/ @Nullable OCLExpression ownedInit = this.getOwnedInit();
							/*@Caught*/ @Nullable Object CAUGHT_ownedInit;
							try {
								CAUGHT_ownedInit = ownedInit;
							}
							catch (Exception e) {
								CAUGHT_ownedInit = ValueUtil.createInvalidValue(e);
							}
							final /*@NonInvalid*/ @NonNull Object isRequired = CAUGHT_ownedInit == null;
							/*@Thrown*/ @Nullable Boolean safe_isRequired_source;
							if (isRequired == Boolean.TRUE) {
								safe_isRequired_source = null;
							}
							else {
								assert ownedInit != null;
								final /*@Thrown*/ boolean isRequired_0 = ownedInit.isIsRequired();
								safe_isRequired_source = isRequired_0;
							}
							CAUGHT_safe_isRequired_source = safe_isRequired_source;
						}
						catch (Exception e) {
							CAUGHT_safe_isRequired_source = ValueUtil.createInvalidValue(e);
						}
						if (CAUGHT_safe_isRequired_source instanceof InvalidValueException) {
							throw (InvalidValueException)CAUGHT_safe_isRequired_source;
						}
						final /*@Thrown*/ @Nullable Boolean not;
						if (CAUGHT_safe_isRequired_source == ValueUtil.FALSE_VALUE) {
							not = ValueUtil.TRUE_VALUE;
						}
						else {
							if (CAUGHT_safe_isRequired_source == ValueUtil.TRUE_VALUE) {
								not = ValueUtil.FALSE_VALUE;
							}
							else {
								not = null;
							}
						}
						CAUGHT_not = not;
					}
					catch (Exception e) {
						CAUGHT_not = ValueUtil.createInvalidValue(e);
					}
					final /*@Thrown*/ @Nullable Boolean result;
					if (CAUGHT_not == ValueUtil.FALSE_VALUE) {
						result = ValueUtil.TRUE_VALUE;
					}
					else {
						/*@Caught*/ @Nullable Object CAUGHT_not_0;
						try {
							/*@Caught*/ @NonNull Object CAUGHT_isRequired_1;
							try {
								if (this == null) {
									throw new InvalidValueException("Null source for \'TypedElement::isRequired\'");
								}
								final /*@Thrown*/ boolean isRequired_1 = this.isIsRequired();
								CAUGHT_isRequired_1 = isRequired_1;
							}
							catch (Exception e) {
								CAUGHT_isRequired_1 = ValueUtil.createInvalidValue(e);
							}
							if (CAUGHT_isRequired_1 instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_isRequired_1;
							}
							final /*@Thrown*/ @Nullable Boolean not_0;
							if (CAUGHT_isRequired_1 == ValueUtil.FALSE_VALUE) {
								not_0 = ValueUtil.TRUE_VALUE;
							}
							else {
								if (CAUGHT_isRequired_1 == ValueUtil.TRUE_VALUE) {
									not_0 = ValueUtil.FALSE_VALUE;
								}
								else {
									not_0 = null;
								}
							}
							CAUGHT_not_0 = not_0;
						}
						catch (Exception e) {
							CAUGHT_not_0 = ValueUtil.createInvalidValue(e);
						}
						if (CAUGHT_not_0 == ValueUtil.TRUE_VALUE) {
							result = ValueUtil.TRUE_VALUE;
						}
						else {
							if (CAUGHT_not instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_not;
							}
							if (CAUGHT_not_0 instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_not_0;
							}
							if ((CAUGHT_not == null) || (CAUGHT_not_0 == null)) {
								result = null;
							}
							else {
								result = ValueUtil.FALSE_VALUE;
							}
						}
					}
					CAUGHT_result = result;
				}
				catch (Exception e) {
					CAUGHT_result = ValueUtil.createInvalidValue(e);
				}
				/*@Caught*/ @NonNull Object CAUGHT_this;
				try {
					if (this == null) {
						throw new InvalidValueException("Null \'\'OclAny\'\' rather than \'\'OclVoid\'\' value required");
					}
					CAUGHT_this = this;
				}
				catch (Exception e) {
					CAUGHT_this = ValueUtil.createInvalidValue(e);
				}
				/*@Caught*/ @NonNull Object CAUGHT_severity_0;
				try {
					CAUGHT_severity_0 = severity_0;
				}
				catch (Exception e) {
					CAUGHT_severity_0 = ValueUtil.createInvalidValue(e);
				}
				if (CAUGHT_this instanceof InvalidValueException) {
					throw (InvalidValueException)CAUGHT_this;
				}
				if (CAUGHT_severity_0 instanceof InvalidValueException) {
					throw (InvalidValueException)CAUGHT_severity_0;
				}
				final /*@NonInvalid*/ @Nullable Boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, CAUGHT_this, (Object)null, diagnostics, context, (Object)null, CAUGHT_severity_0, CAUGHT_result, PivotTables.INT_0);
				IF_le = logDiagnostic;
			}
			return Boolean.TRUE == IF_le;
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
			 *   let severity : Integer[?] = constraintName.getSeverity()
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
			final /*@NonInvalid*/ @Nullable IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.RESULT_VARIABLE___VALIDATE_COMPATIBLE_TYPE_FOR_INITIALIZER__DIAGNOSTICCHAIN_MAP);
			if (severity_0 == null) {
				throw new InvalidValueException("Null \'\'OclComparable\'\' rather than \'\'OclVoid\'\' value required");
			}
			final /*@Thrown*/ @Nullable Boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0);
			if (le == null) {
				throw new InvalidValueException("Null if condition");
			}
			/*@NonInvalid*/ @Nullable Boolean IF_le;
			if (le) {
				IF_le = ValueUtil.TRUE_VALUE;
			}
			else {
				/*@Caught*/ @Nullable Object CAUGHT_result;
				try {
					/*@Caught*/ @NonNull Object CAUGHT_ne;
					try {
						if (this == null) {
							throw new InvalidValueException("Null source for \'Variable::ownedInit\'");
						}
						final /*@Thrown*/ @Nullable OCLExpression ownedInit = this.getOwnedInit();
						final /*@Thrown*/ boolean ne = ownedInit != null;
						CAUGHT_ne = ne;
					}
					catch (Exception e) {
						CAUGHT_ne = ValueUtil.createInvalidValue(e);
					}
					final /*@Thrown*/ @Nullable Boolean result;
					if (CAUGHT_ne == ValueUtil.FALSE_VALUE) {
						result = ValueUtil.TRUE_VALUE;
					}
					else {
						/*@Caught*/ @Nullable Object CAUGHT_safe_conformsTo_source;
						try {
							if (this == null) {
								throw new InvalidValueException("Null source for \'TypedElement::type\'");
							}
							final /*@Thrown*/ @Nullable OCLExpression ownedInit_0 = this.getOwnedInit();
							if (ownedInit_0 == null) {
								throw new InvalidValueException("Null source for \'TypedElement::type\'");
							}
							final /*@Thrown*/ @Nullable Type type = ownedInit_0.getType();
							/*@Caught*/ @Nullable Object CAUGHT_type;
							try {
								CAUGHT_type = type;
							}
							catch (Exception e) {
								CAUGHT_type = ValueUtil.createInvalidValue(e);
							}
							final /*@NonInvalid*/ @NonNull Object conformsTo = CAUGHT_type == null;
							/*@Thrown*/ @Nullable Boolean safe_conformsTo_source;
							if (conformsTo == Boolean.TRUE) {
								safe_conformsTo_source = null;
							}
							else {
								if (type == null) {
									throw new InvalidValueException("Null \'\'Type\'\' rather than \'\'OclVoid\'\' value required");
								}
								final /*@Thrown*/ @Nullable Type type_0 = this.getType();
								final /*@Thrown*/ @Nullable Boolean conformsTo_0 = OclTypeConformsToOperation.INSTANCE.evaluate(executor, type, type_0);
								safe_conformsTo_source = conformsTo_0;
							}
							CAUGHT_safe_conformsTo_source = safe_conformsTo_source;
						}
						catch (Exception e) {
							CAUGHT_safe_conformsTo_source = ValueUtil.createInvalidValue(e);
						}
						if (CAUGHT_safe_conformsTo_source == ValueUtil.TRUE_VALUE) {
							result = ValueUtil.TRUE_VALUE;
						}
						else {
							if (CAUGHT_ne instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_ne;
							}
							if (CAUGHT_safe_conformsTo_source instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_safe_conformsTo_source;
							}
							if (CAUGHT_safe_conformsTo_source == null) {
								result = null;
							}
							else {
								result = ValueUtil.FALSE_VALUE;
							}
						}
					}
					CAUGHT_result = result;
				}
				catch (Exception e) {
					CAUGHT_result = ValueUtil.createInvalidValue(e);
				}
				/*@Caught*/ @NonNull Object CAUGHT_this;
				try {
					if (this == null) {
						throw new InvalidValueException("Null \'\'OclAny\'\' rather than \'\'OclVoid\'\' value required");
					}
					CAUGHT_this = this;
				}
				catch (Exception e) {
					CAUGHT_this = ValueUtil.createInvalidValue(e);
				}
				/*@Caught*/ @NonNull Object CAUGHT_severity_0;
				try {
					CAUGHT_severity_0 = severity_0;
				}
				catch (Exception e) {
					CAUGHT_severity_0 = ValueUtil.createInvalidValue(e);
				}
				if (CAUGHT_this instanceof InvalidValueException) {
					throw (InvalidValueException)CAUGHT_this;
				}
				if (CAUGHT_severity_0 instanceof InvalidValueException) {
					throw (InvalidValueException)CAUGHT_severity_0;
				}
				final /*@NonInvalid*/ @Nullable Boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, CAUGHT_this, (Object)null, diagnostics, context, (Object)null, CAUGHT_severity_0, CAUGHT_result, PivotTables.INT_0);
				IF_le = logDiagnostic;
			}
			return Boolean.TRUE == IF_le;
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
			 *   let severity : Integer[?] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let result : Boolean[?] = ownedInit <> null
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @Nullable IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.RESULT_VARIABLE___VALIDATE_HAS_INITIALIZER__DIAGNOSTICCHAIN_MAP);
			if (severity_0 == null) {
				throw new InvalidValueException("Null \'\'OclComparable\'\' rather than \'\'OclVoid\'\' value required");
			}
			final /*@Thrown*/ @Nullable Boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0);
			if (le == null) {
				throw new InvalidValueException("Null if condition");
			}
			/*@NonInvalid*/ @Nullable Boolean IF_le;
			if (le) {
				IF_le = ValueUtil.TRUE_VALUE;
			}
			else {
				/*@Caught*/ @NonNull Object CAUGHT_result;
				try {
					if (this == null) {
						throw new InvalidValueException("Null source for \'Variable::ownedInit\'");
					}
					final /*@Thrown*/ @Nullable OCLExpression ownedInit = this.getOwnedInit();
					final /*@Thrown*/ boolean result = ownedInit != null;
					CAUGHT_result = result;
				}
				catch (Exception e) {
					CAUGHT_result = ValueUtil.createInvalidValue(e);
				}
				/*@Caught*/ @NonNull Object CAUGHT_this;
				try {
					if (this == null) {
						throw new InvalidValueException("Null \'\'OclAny\'\' rather than \'\'OclVoid\'\' value required");
					}
					CAUGHT_this = this;
				}
				catch (Exception e) {
					CAUGHT_this = ValueUtil.createInvalidValue(e);
				}
				/*@Caught*/ @NonNull Object CAUGHT_severity_0;
				try {
					CAUGHT_severity_0 = severity_0;
				}
				catch (Exception e) {
					CAUGHT_severity_0 = ValueUtil.createInvalidValue(e);
				}
				if (CAUGHT_this instanceof InvalidValueException) {
					throw (InvalidValueException)CAUGHT_this;
				}
				if (CAUGHT_severity_0 instanceof InvalidValueException) {
					throw (InvalidValueException)CAUGHT_severity_0;
				}
				final /*@NonInvalid*/ @Nullable Boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, CAUGHT_this, (Object)null, diagnostics, context, (Object)null, CAUGHT_severity_0, CAUGHT_result, PivotTables.INT_0);
				IF_le = logDiagnostic;
			}
			return Boolean.TRUE == IF_le;
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
