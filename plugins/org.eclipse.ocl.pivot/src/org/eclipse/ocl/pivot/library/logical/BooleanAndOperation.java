/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library.logical;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicHypothesisEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.manager.SymbolicExecutor;
import org.eclipse.ocl.pivot.internal.values.SimpleSymbolicConstraintImpl;
import org.eclipse.ocl.pivot.internal.values.SymbolicOperationCallValueImpl;
import org.eclipse.ocl.pivot.library.AbstractSimpleBinaryOperation;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.SimpleSymbolicConstraint;
import org.eclipse.ocl.pivot.values.SymbolicExpressionValue;
import org.eclipse.ocl.pivot.values.SymbolicOperationCallValue;
import org.eclipse.ocl.pivot.values.SymbolicOperator;
import org.eclipse.ocl.pivot.values.SymbolicValue;

import com.google.common.collect.Lists;

/**
 * AndOperation realises the and() library operation.
 */
public class BooleanAndOperation extends AbstractSimpleBinaryOperation
{
	public static final @NonNull BooleanAndOperation INSTANCE = new BooleanAndOperation();

	/**
	 * @since 1.15
	 */
	@Override
	public void deduceFrom(@NonNull SymbolicExecutor symbolicExecutor, @NonNull SymbolicOperationCallValue resultValue, @NonNull SimpleSymbolicConstraint simpleConstraint) {
		if ((simpleConstraint.getSymbolicOperator() == SymbolicOperator.EQUALS) && (simpleConstraint.getSymbolicValue() == Boolean.TRUE)) {
			List<@Nullable Object> boxedSourceAndArgumentValues = resultValue.getBoxedSourceAndArgumentValues();
			Object sourceValue = boxedSourceAndArgumentValues.get(0);
			if (sourceValue instanceof SymbolicValue) {
				SimpleSymbolicConstraintImpl symbolicConstraint = new SimpleSymbolicConstraintImpl(TypeId.BOOLEAN, false, false, SymbolicOperator.EQUALS, Boolean.TRUE);
				((SymbolicValue)sourceValue).deduceFrom(symbolicExecutor, symbolicConstraint);
			}
			Object argumentValue = boxedSourceAndArgumentValues.get(1);
			if (argumentValue instanceof SymbolicValue) {
				SimpleSymbolicConstraintImpl symbolicConstraint = new SimpleSymbolicConstraintImpl(TypeId.BOOLEAN, false, false, SymbolicOperator.EQUALS, Boolean.TRUE);
				((SymbolicValue)argumentValue).deduceFrom(symbolicExecutor, symbolicConstraint);
			}
		}
	}

	@Override
	public @Nullable Object dispatch(@NonNull Executor executor, @NonNull OperationCallExp callExp, @Nullable Object sourceValue) {
		if (sourceValue == Boolean.FALSE) {
			return FALSE_VALUE;
		}
		List<? extends OCLExpression> arguments = callExp.getOwnedArguments();
		OCLExpression argument0 = arguments.get(0);
		assert argument0 != null;
		Object firstArgument;
		try {
			firstArgument = executor.evaluate(argument0);
		}
		catch (InvalidValueException e) {
			firstArgument = e;	// FIXME ?? propagate part of environment
		}
		return evaluate(sourceValue, firstArgument);
	}

	@Override
	public @Nullable Boolean evaluate(@Nullable Object left, @Nullable Object right) {
		if ((left == Boolean.FALSE) || (right == Boolean.FALSE)) {
			return FALSE_VALUE;
		}
		else if ((left == Boolean.TRUE) && (right == Boolean.TRUE)) {
			return TRUE_VALUE;
		}
		if (left instanceof InvalidValueException) {
			throw (InvalidValueException)left;
		}
		else if (right instanceof InvalidValueException) {
			throw (InvalidValueException)right;
		}
		else if ((left == null) || (right == null)) {
			return null;
		}
		else if (!(left instanceof Boolean)) {
			throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.BOOLEAN_NAME, getTypeName(left));
		}
		else {
			throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.BOOLEAN_NAME, getTypeName(right));
		}
	}

	/**
	 * @since 1.15
	 */
	@Override
	public @Nullable Object symbolicDispatch(@NonNull EvaluationVisitor evaluationVisitor, @NonNull OperationCallExp callExp, @Nullable Object sourceValue) {
		assert sourceValue != null;
		assert !ValueUtil.isInvalidValue(sourceValue);
		assert !ValueUtil.isNullValue(sourceValue);
		if (sourceValue == Boolean.FALSE) {
			return FALSE_VALUE;
		}
		OCLExpression source = PivotUtil.getOwnedSource(callExp);
		OCLExpression argument = PivotUtil.getOwnedArgument(callExp, 0);
		if (sourceValue == Boolean.TRUE) {
			return evaluationVisitor.evaluate(argument);
		}
		assert sourceValue instanceof SymbolicExpressionValue;
		SymbolicExecutor symbolicExecutor = (SymbolicExecutor)evaluationVisitor.getExecutor();
		try {
			SymbolicHypothesisEvaluationEnvironment symbolicHypothesisEvaluationEnvironment = symbolicExecutor.pushSymbolicHypothesis(callExp);
			Object sourceValue2 = evaluationVisitor.evaluate(source);
			symbolicHypothesisEvaluationEnvironment.addHypothesis(source, Boolean.TRUE);
			Object argumentValue2 = evaluationVisitor.evaluate(argument);
			if ((sourceValue instanceof SymbolicValue) || (sourceValue instanceof SymbolicValue)) {
					boolean mayBeInvalid = ValueUtil.mayBeInvalid(sourceValue2) || ValueUtil.mayBeInvalid(argumentValue2);
					boolean mayBeNull = ValueUtil.mayBeNull(sourceValue2) || ValueUtil.mayBeNull(argumentValue2);
				return new SymbolicOperationCallValueImpl(callExp, false, mayBeNull || mayBeInvalid, this, Lists.newArrayList(sourceValue2, argumentValue2));
			}
			else {
				return evaluate(sourceValue2, argumentValue2);
			}




		//	symbolicExecutor.pushSymbolicEvaluationEnvironment((SymbolicExpressionValue)sourceValue, Boolean.TRUE, callExp);
		//	Object argumentValue = evaluationVisitor.evaluate(argument);
		//	boolean mayBeInvalid = ValueUtil.mayBeInvalid(sourceValue) || ValueUtil.mayBeInvalid(argumentValue);
		//	boolean mayBeNull = ValueUtil.mayBeNull(sourceValue) || ValueUtil.mayBeNull(argumentValue);
		//	return new SymbolicOperationCallValueImpl(callExp, false, mayBeNull || mayBeInvalid, this, Lists.newArrayList(sourceValue, argumentValue));
		}
		finally {
		//	symbolicExecutor.popEvaluationEnvironment();
			symbolicExecutor.popSymbolicHypothesis();
		}
	}
}
