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
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.evaluation.HypothesizedSymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.library.AbstractSimpleBinaryOperation;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * AndOperation realises the and() library operation.
 */
public class BooleanAndOperation extends AbstractSimpleBinaryOperation
{
	public static final @NonNull BooleanAndOperation INSTANCE = new BooleanAndOperation();

	/**
	 * @since 1.16
	 */
	@Override
	protected @Nullable SymbolicValue checkPreconditions(@NonNull SymbolicEvaluationEnvironment evaluationEnvironment, @NonNull OperationCallExp callExp) {
		OCLExpression source = PivotUtil.getOwnedSource(callExp);
		OCLExpression argument = PivotUtil.getOwnedArgument(callExp, 0);
		SymbolicValue sourceValue = evaluationEnvironment.symbolicEvaluate(source);
		SymbolicValue argumentValue = evaluationEnvironment.symbolicEvaluate(argument);
		if (sourceValue.isFalse()) {		// source can short-circuit argument
			evaluationEnvironment.setDead(argument);
			return evaluationEnvironment.getKnownValue(Boolean.FALSE);
		}
		if (argumentValue.isFalse()) {		// argument can short-circuit source
			evaluationEnvironment.setDead(source);
			return evaluationEnvironment.getKnownValue(Boolean.FALSE);
		}
		return super.checkPreconditions(evaluationEnvironment, callExp);
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

	/**
	 * @since 1.16
	 */
	@Override
	protected @NonNull SymbolicValue createResultValue( @NonNull SymbolicEvaluationEnvironment evaluationEnvironment, @NonNull OperationCallExp callExp,
			@NonNull SymbolicValue sourceSymbolicValue, @NonNull List<@NonNull SymbolicValue> argumentSymbolicValues) {
		return createValidatingResultValue(evaluationEnvironment, callExp, sourceSymbolicValue, argumentSymbolicValues);
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
	 * @since 1.17
	 */
	@Override
	protected boolean hasRedundantOverloadForInvalid() {
		return true;
	}

	/**
	 * @since 1.17
	 */
	@Override
	protected boolean hasRedundantOverloadForNull() {
		return true;
	}

	/**
	 * @since 1.16
	 */
	@Override
	public @Nullable String installPathConstraints(@NonNull HypothesizedSymbolicEvaluationEnvironment evaluationEnvironment, @NonNull TypedElement activeTypedElement, @NonNull OperationCallExp operationCallExp) {
		OCLExpression source = PivotUtil.getOwnedSource(operationCallExp);
		OCLExpression argument = PivotUtil.getOwnedArgument(operationCallExp, 0);
		SymbolicValue refinedSymbolicValue = evaluationEnvironment.getKnownValue(Boolean.TRUE);
		if (activeTypedElement == source) {
			return evaluationEnvironment.installRefinement(argument, refinedSymbolicValue);
		}
		else {
			assert activeTypedElement == argument;
			return evaluationEnvironment.installRefinement(source, refinedSymbolicValue);
		}
	}

	/**
	 * @since 1.16
	 */
	@Override
	public @NonNull SymbolicValue symbolicEvaluate(@NonNull SymbolicEvaluationEnvironment evaluationEnvironment, @NonNull OperationCallExp callExp) {
		SymbolicValue symbolicPreconditionValue = checkPreconditions(evaluationEnvironment, callExp);
		if (symbolicPreconditionValue != null) {
			return symbolicPreconditionValue;
		}
		OCLExpression source = PivotUtil.getOwnedSource(callExp);
		OCLExpression argument = PivotUtil.getOwnedArgument(callExp, 0);
		SymbolicValue sourceValue = evaluationEnvironment.symbolicEvaluate(source);
		SymbolicValue argumentValue = evaluationEnvironment.symbolicEvaluate(argument);
		if (sourceValue.isTrue()) {
			return argumentValue;		// Re-use known symbolic value
		}
		if (argumentValue.isTrue()) {
			return sourceValue;			// Re-use known symbolic value
		}
		return super.symbolicEvaluate(evaluationEnvironment, callExp);
	}
}
