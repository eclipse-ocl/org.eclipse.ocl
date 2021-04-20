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
import org.eclipse.ocl.pivot.library.AbstractSimpleBinaryOperation;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * BooleanOrOperation2 realises the 2-valued or() library operation.
 *
 * @since 1.3
 */
public class BooleanOrOperation2 extends AbstractSimpleBinaryOperation
{
	public static final @NonNull BooleanOrOperation2 INSTANCE = new BooleanOrOperation2();

	/**
	 * @since 1.15
	 *
	@Override
	public void deduceFrom(@NonNull SymbolicExecutor symbolicExecutor, @NonNull SymbolicOperationCallValue resultValue, @NonNull SimpleSymbolicConstraint simpleConstraint) {
		if ((simpleConstraint.getSymbolicOperator() == SymbolicOperator.EQUALS) && (simpleConstraint.getSymbolicValue() == Boolean.FALSE)) {
			List<@Nullable Object>boxedSourceAndArgumentValues = resultValue.getBoxedSourceAndArgumentValues();
			Object sourceValue = boxedSourceAndArgumentValues.get(0);
			if (sourceValue instanceof SymbolicValue) {
				SimpleSymbolicConstraintImpl symbolicConstraint = new SimpleSymbolicConstraintImpl(TypeId.BOOLEAN, false, false, SymbolicOperator.EQUALS, Boolean.FALSE);
				((SymbolicValue)sourceValue).deduceFrom(symbolicExecutor, symbolicConstraint);
			}
			Object argumentValue = boxedSourceAndArgumentValues.get(1);
			if (argumentValue instanceof SymbolicValue) {
				SimpleSymbolicConstraintImpl symbolicConstraint = new SimpleSymbolicConstraintImpl(TypeId.BOOLEAN, false, false, SymbolicOperator.EQUALS, Boolean.FALSE);
				((SymbolicValue)argumentValue).deduceFrom(symbolicExecutor, symbolicConstraint);
			}
		}
	} */

	@Override
	public @Nullable Object dispatch(@NonNull Executor executor, @NonNull OperationCallExp callExp, @Nullable Object sourceValue) {
		if (sourceValue == Boolean.TRUE) {
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
		if ((left == Boolean.TRUE) || (right == Boolean.TRUE)) {
			return TRUE_VALUE;
		}
		else {
			return FALSE_VALUE;
		}
	}

	/**
	 * @since 1.15
	 *
	@Override
	public @Nullable Object symbolicDispatch(@NonNull EvaluationVisitor evaluationVisitor, @NonNull OperationCallExp callExp, @Nullable Object sourceValue) {
		assert sourceValue != null;
		assert !ValueUtil.isInvalidValue(sourceValue);
		assert !ValueUtil.isNullValue(sourceValue);
		if (sourceValue == Boolean.TRUE) {
			return TRUE_VALUE;
		}
		OCLExpression argument = PivotUtil.getOwnedArgument(callExp, 0);
		if (sourceValue == Boolean.FALSE) {
			return evaluationVisitor.evaluate(argument);
		}
		assert sourceValue instanceof SymbolicExpressionValue;
		SymbolicExecutor symbolicExecutor = (SymbolicExecutor)evaluationVisitor.getExecutor();
		try {
			symbolicExecutor.pushSymbolicEvaluationEnvironment((SymbolicExpressionValue)sourceValue, Boolean.FALSE, callExp);
			Object argumentValue = evaluationVisitor.evaluate(argument);
			boolean mayBeInvalid = ValueUtil.mayBeInvalid(sourceValue) || ValueUtil.mayBeInvalid(argumentValue);
			boolean mayBeNull = ValueUtil.mayBeNull(sourceValue) || ValueUtil.mayBeNull(argumentValue);
			return new SymbolicOperationCallValueImpl(callExp, false, mayBeNull || mayBeInvalid, this, Lists.newArrayList(sourceValue, argumentValue));
		}
		finally {
			evaluationVisitor.getExecutor().popEvaluationEnvironment();
		}
	} */
}
