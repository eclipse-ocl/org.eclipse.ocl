/*******************************************************************************
 * Copyright (c) 2016, 2020 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library.logical;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.internal.evaluation.AbstractSymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.values.SymbolicUnknownValueImpl;
import org.eclipse.ocl.pivot.library.AbstractSimpleBinaryOperation;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * BooleanImpliesOperation2 realises the 2-valued implies() library operation.
 *
 * @since 1.3
 */
public class BooleanImpliesOperation2 extends AbstractSimpleBinaryOperation
{
	public static final @NonNull BooleanImpliesOperation2 INSTANCE = new BooleanImpliesOperation2();

/*	@Override
	protected @Nullable SymbolicValue checkPreconditions(@NonNull AbstractSymbolicEvaluationEnvironment symbolicEvaluationEnvironment, @NonNull OperationCallExp callExp) {
		OCLExpression source = PivotUtil.getOwnedSource(callExp);
		if (symbolicEvaluationEnvironment.mayBeInvalidOrNull(source)) {
			return symbolicEvaluationEnvironment.getMayBeInvalidValue(callExp);
		}
		OCLExpression argument = PivotUtil.getOwnedArgument(callExp, 0);
		if (symbolicEvaluationEnvironment.mayBeInvalidOrNull(argument)) {
			return symbolicEvaluationEnvironment.getMayBeInvalidValue(callExp);
		}
		return null;
	} */

	/**
	 * @since 1.15
	 *
	@Override
	public void deduceFrom(@NonNull SymbolicExecutor symbolicExecutor, @NonNull SymbolicOperationCallValue resultValue, @NonNull SimpleSymbolicConstraint simpleConstraint) {
		if ((simpleConstraint.getSymbolicOperator() == SymbolicOperator.EQUALS) && (simpleConstraint.getSymbolicValue() == Boolean.TRUE)) {
			List<@Nullable Object> boxedSourceAndArgumentValues = resultValue.getBoxedSourceAndArgumentValues();
			Object sourceValue = boxedSourceAndArgumentValues.get(0);
			if (sourceValue instanceof SymbolicValue) {
				SimpleSymbolicConstraintImpl symbolicConstraint = new SimpleSymbolicConstraintImpl(TypeId.BOOLEAN, false, false, SymbolicOperator.EQUALS, Boolean.FALSE);
				((SymbolicValue)sourceValue).deduceFrom(symbolicExecutor, symbolicConstraint);
			}
			Object argumentValue = boxedSourceAndArgumentValues.get(1);
			if (argumentValue instanceof SymbolicValue) {
				SimpleSymbolicConstraintImpl symbolicConstraint = new SimpleSymbolicConstraintImpl(TypeId.BOOLEAN, false, false, SymbolicOperator.EQUALS, Boolean.TRUE);
				((SymbolicValue)argumentValue).deduceFrom(symbolicExecutor, symbolicConstraint);
			}
		}
	} */

	@Override
	public @Nullable Object dispatch(@NonNull Executor executor, @NonNull OperationCallExp callExp, @Nullable Object sourceValue) {
		if (sourceValue == Boolean.FALSE) {
			return TRUE_VALUE;
		}
		Boolean sourceBoolean = ValueUtil.asBoolean(sourceValue);
		OCLExpression argument = PivotUtil.getOwnedArgument(callExp, 0);
		Object argumentValue = executor.evaluate(argument);
		Boolean argBoolean = ValueUtil.asBoolean(argumentValue);
		return evaluate(sourceBoolean, argBoolean);
	}

	@Override
	public @NonNull Boolean evaluate(@Nullable Object left, @Nullable Object right) {
		if ((left == Boolean.FALSE) || (right == Boolean.TRUE)) {
			return TRUE_VALUE;
		}
		else  {
			return FALSE_VALUE;
		}
	}

	/**
	 * @since 1.15
	 */
	@Override
	public @NonNull SymbolicValue symbolicEvaluate(@NonNull AbstractSymbolicEvaluationEnvironment evaluationEnvironment, @NonNull OperationCallExp callExp) {
		SymbolicValue symbolicPreconditionValue = checkPreconditions(evaluationEnvironment, callExp);
		if (symbolicPreconditionValue != null) {
			return symbolicPreconditionValue;
		}
		OCLExpression source = PivotUtil.getOwnedSource(callExp);
		OCLExpression argument = PivotUtil.getOwnedArgument(callExp, 0);
		SymbolicValue unconstrainedSourceValue = evaluationEnvironment.symbolicEvaluate(source);
		if (unconstrainedSourceValue.isFalse()) {
			evaluationEnvironment.setDead(argument);
			return evaluationEnvironment.getKnownValue(Boolean.TRUE);
		}
		if (unconstrainedSourceValue.isTrue()) {							// If we know the source is true there is no need to install the extra fact.
			return evaluationEnvironment.symbolicEvaluate(argument);
		}
		AbstractSymbolicEvaluationEnvironment constrainedEvaluationEnvironment = evaluationEnvironment;
		SymbolicValue unconstrainedArgumentValue = constrainedEvaluationEnvironment.symbolicEvaluate(argument);
		boolean mayBeInvalid = ValueUtil.mayBeInvalid(unconstrainedSourceValue) || ValueUtil.mayBeInvalid(unconstrainedArgumentValue);
		boolean mayBeNull = ValueUtil.mayBeNull(unconstrainedSourceValue) || ValueUtil.mayBeNull(unconstrainedArgumentValue);
		boolean mayBeInvalidOrNull = mayBeNull || mayBeInvalid;
		SymbolicUnknownValueImpl resultValue = new SymbolicUnknownValueImpl(callExp.getTypeId(), false, mayBeInvalidOrNull);
		return resultValue;
	}
}
