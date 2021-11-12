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
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.internal.evaluation.HypothesizedSymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.library.AbstractSimpleBinaryOperation;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * BooleanAndOperation2 realises the 2-valued and() library operation.
 *
 * @since 1.3
 */
public class BooleanAndOperation2 extends AbstractSimpleBinaryOperation
{
	public static final @NonNull BooleanAndOperation2 INSTANCE = new BooleanAndOperation2();

	/**
	 * @since 1.16
	 */
	@Override
	protected @Nullable SymbolicValue checkPreconditions(@NonNull SymbolicEvaluationEnvironment evaluationEnvironment, @NonNull OperationCallExp callExp) {
		OCLExpression source = PivotUtil.getOwnedSource(callExp);
		OCLExpression argument = PivotUtil.getOwnedArgument(callExp, 0);
		SymbolicValue sourceValue = evaluationEnvironment.symbolicEvaluate(source);
		SymbolicValue argumentValue = evaluationEnvironment.symbolicEvaluate(argument);
		if (sourceValue.isFalse()) {		// Only source can short-circuit argument
			evaluationEnvironment.setDead(argument);
			return evaluationEnvironment.getKnownValue(Boolean.FALSE);
		}
		SymbolicValue superProblem = super.checkPreconditions(evaluationEnvironment, callExp);
		if (superProblem != null) {
			return superProblem;
		}
		if (argumentValue.isFalse()) {	// argument does not short-circuit source
			evaluationEnvironment.setDead(source);
			return evaluationEnvironment.getKnownValue(Boolean.FALSE);
		}
		return null;
	}

	@Override
	public @Nullable Object dispatch(@NonNull Executor executor, @NonNull OperationCallExp callExp, @Nullable Object sourceValue) {
		if (sourceValue == Boolean.FALSE) {
			return FALSE_VALUE;
		}
		Boolean sourceBoolean = ValueUtil.asBoolean(sourceValue);
		OCLExpression argument = PivotUtil.getOwnedArgument(callExp, 0);
		Object argumentValue = executor.evaluate(argument);
		Boolean argBoolean = ValueUtil.asBoolean(argumentValue);
		return evaluate(sourceBoolean, argBoolean);
	}

	@Override
	public @NonNull Boolean evaluate(@Nullable Object left, @Nullable Object right) {
		if ((left == Boolean.FALSE) || (right == Boolean.FALSE)) {
			return FALSE_VALUE;
		}
		else {
			return TRUE_VALUE;
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
