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
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.evaluation.AbstractSymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.library.AbstractSimpleBinaryOperation;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * OrOperation realises the or() library operation.
 */
public class BooleanOrOperation extends AbstractSimpleBinaryOperation
{
	public static final @NonNull BooleanOrOperation INSTANCE = new BooleanOrOperation();

	@Override
	protected @Nullable SymbolicValue checkPreconditions(@NonNull AbstractSymbolicEvaluationEnvironment symbolicEvaluationEnvironment, @NonNull OperationCallExp callExp) {
		TypeId returnTypeId = callExp.getTypeId();
		OCLExpression source = PivotUtil.getOwnedSource(callExp);
		OCLExpression argument = PivotUtil.getOwnedArgument(callExp, 0);
		SymbolicValue sourceValue = symbolicEvaluationEnvironment.symbolicEvaluate(source);
		SymbolicValue argumentValue = symbolicEvaluationEnvironment.symbolicEvaluate(argument);
		if (sourceValue.isTrue()) {
			symbolicEvaluationEnvironment.setDead(argument);
			return symbolicEvaluationEnvironment.getKnownValue(Boolean.TRUE);
		}
		if (argumentValue.isTrue()) {
			symbolicEvaluationEnvironment.setDead(source);
			return symbolicEvaluationEnvironment.getKnownValue(Boolean.TRUE);
		}
		SymbolicValue invalidSourceProblem = symbolicEvaluationEnvironment.checkNotInvalid(source, returnTypeId);
		if (invalidSourceProblem != null) {
			return invalidSourceProblem;
		}
		SymbolicValue nullSourceProblem = symbolicEvaluationEnvironment.checkNotNull(source, returnTypeId);
		if (nullSourceProblem != null) {
			return nullSourceProblem;
		}
		// ??? XXX short-circuits
		SymbolicValue invalidArgumentProblem = symbolicEvaluationEnvironment.checkNotInvalid(argument, returnTypeId);
		if (invalidArgumentProblem != null) {
			return invalidArgumentProblem;
		}
		SymbolicValue nullArgumentProblem = symbolicEvaluationEnvironment.checkNotNull(argument, returnTypeId);
		if (nullArgumentProblem != null) {
			return nullArgumentProblem;
		}
		return null;
	}

	/**
	 * @since 1.15
	 *
	@Override
	public void deduceFrom(@NonNull SymbolicExecutor symbolicExecutor, @NonNull SymbolicOperationCallValue resultValue, @NonNull SimpleSymbolicConstraint simpleConstraint) {
		if ((simpleConstraint.getSymbolicOperator() == SymbolicOperator.EQUALS) && (simpleConstraint.getSymbolicValue() == Boolean.FALSE)) {
			List<@Nullable Object> boxedSourceAndArgumentValues = resultValue.getBoxedSourceAndArgumentValues();
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
		if ((left == Boolean.TRUE) || (right == Boolean.TRUE)) {
			return TRUE_VALUE;
		}
		else if ((left == Boolean.FALSE) && (right == Boolean.FALSE)) {
			return FALSE_VALUE;
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
