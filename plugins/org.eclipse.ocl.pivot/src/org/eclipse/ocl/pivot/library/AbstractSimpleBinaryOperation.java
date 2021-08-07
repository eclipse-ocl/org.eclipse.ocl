/*******************************************************************************
 * Copyright (c) 2010, 2020 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.Evaluator;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * AbstractSimpleBinaryOperation defines the default implementation of a binary operation redirecting the
 * invocation to the argument-only form.
 */
public abstract class AbstractSimpleBinaryOperation extends AbstractUntypedBinaryOperation implements LibrarySimpleBinaryOperation.LibrarySimpleBinaryOperationExtension
{
	protected @NonNull SymbolicValue createValidatingResultValue( @NonNull SymbolicEvaluationEnvironment evaluationEnvironment, @NonNull OperationCallExp callExp,
			@NonNull SymbolicValue sourceSymbolicValue, @NonNull List<@NonNull SymbolicValue> argumentSymbolicValues) {
		Operation referredOperation = PivotUtil.getReferredOperation(callExp);
		assert referredOperation.isIsValidating() : "Spurious createResultValue overload for " + referredOperation.getImplementationClass();
		assert !callExp.isIsSafe() : "Spurious isSafe for " + referredOperation.getImplementationClass();
		boolean mayBeInvalid = false;
		boolean mayBeNull = false;
		OCLExpression ownedSource = PivotUtil.getOwnedSource(callExp);
		if (evaluationEnvironment.mayBeInvalid(ownedSource)) {
			mayBeInvalid = true;
		}
		if (evaluationEnvironment.mayBeNull(ownedSource)) {
			mayBeNull = true;
		}
		assert !PivotUtil.getOwnedParameter(referredOperation, 0).isIsRequired() : "Spurious isRequired for " + referredOperation.getImplementationClass();
		OCLExpression ownedArgument = PivotUtil.getOwnedArgument(callExp, 0);
		if (evaluationEnvironment.mayBeInvalid(ownedArgument)) {
			mayBeInvalid = true;
		}
		if (evaluationEnvironment.mayBeNull(ownedArgument)) {
			mayBeNull = true;
		}
		return evaluationEnvironment.getUnknownValue(callExp, mayBeNull, mayBeInvalid);
	}

	/** @deprecated use Executor */
	@Deprecated
	@Override
	public @Nullable Object dispatch(@NonNull Evaluator evaluator, @NonNull OperationCallExp callExp, @Nullable Object sourceValue) {
		return dispatch(getExecutor(evaluator), callExp, sourceValue);
	}

	@Override
	@Nullable
	public Object dispatch(@NonNull Executor executor, @NonNull OperationCallExp callExp, @Nullable Object sourceValue) {
		assert !PivotUtil.getReferredOperation(callExp).isIsValidating();
		List<? extends OCLExpression> arguments = callExp.getOwnedArguments();
		OCLExpression argument0 = arguments.get(0);
		assert argument0 != null;
		Object firstArgument = executor.evaluate(argument0);
		return evaluate(sourceValue, firstArgument);
	}

	/** @deprecated use Executor */
	@Deprecated
	@Override
	public @Nullable Object evaluate(@NonNull Evaluator evaluator, @NonNull TypeId returnTypeId, @Nullable Object sourceValue, @Nullable Object argumentValue) {
		return evaluate(sourceValue, argumentValue);
	}

	/**
	 * @since 1.1
	 */
	@Override
	public @Nullable Object evaluate(@NonNull Executor executor, @Nullable Object sourceValue, @Nullable Object argumentValue) {
		return evaluate(sourceValue, argumentValue);
	}

	/** @deprecated use Executor */
	@Deprecated
	@Override
	public @Nullable /*@Thrown*/ Object evaluate(@NonNull Evaluator evaluator, @Nullable Object sourceValue, @Nullable Object argumentValue) {
		return evaluate(sourceValue, argumentValue);
	}

	/**
	 * @since 1.3
	 */
	@Override
	public @Nullable Object evaluate(@NonNull Executor executor, @NonNull TypedElement caller, @Nullable Object @NonNull [] boxedSourceAndArgumentValues) {
		if (boxedSourceAndArgumentValues.length == 2) {
			return evaluate(boxedSourceAndArgumentValues[0], boxedSourceAndArgumentValues[1]);
		}
		else {
			return super.evaluate(executor, caller, boxedSourceAndArgumentValues);
		}
	}

	// Redundant declaration avoids @Override dilemma for 1.5/1.6
	@Override
	public abstract @Nullable /*@Thrown*/ Object evaluate(@Nullable Object sourceValue, @Nullable Object argumentValue);
}
