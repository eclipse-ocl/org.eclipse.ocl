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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.EvaluationHaltedException;
import org.eclipse.ocl.pivot.evaluation.Evaluator;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.internal.evaluation.AbstractSymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.evaluation.ExecutorInternal;
import org.eclipse.ocl.pivot.internal.evaluation.ExecutorInternal.ExecutorInternalExtension;
import org.eclipse.ocl.pivot.internal.values.SymbolicUnknownValueImpl;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.SymbolicKnownValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;

import com.google.common.collect.Iterables;

/**
 * AbstractOperation defines the minimal functionality of all Operation implementations. Each implemented
 * operation is implemented by a distinct derived class whose evaluate method implements the operation.
 *
 * For interpreted purposes {@link #evaluate(Executor, OperationCallExp, Object[])} offers the maximum polymorphism
 * with source and arguments packed to facilitate cache lookup.
 *
 * For code generated purposes the many Unary/Binary/Ternary derived classes offer significantly simpler signatures
 * suitable for direct invocation from auto-generated Java code.
 */
public abstract class AbstractOperation extends AbstractIterationOrOperation implements LibraryOperation.LibraryOperationExtension2
{
	/**
	 * Return the evaluation from sourceAndArgumentValues using the executor for context wrt a caller.
	 *
	 * Derived calsses should override basicEvalute to evaluate a cacheable result, or evaluate to bypass caching.
	 *
	 * @since 1.3
	 */
	@Override
	public @Nullable Object basicEvaluate(@NonNull Executor executor, @NonNull TypedElement caller, @Nullable Object @NonNull [] sourceAndArgumentValues) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Return the evaluation from sourceAndArgumentValues using the executor for context wrt a caller.
	 * The implementation attempts to re-use a cached result which is initially provided by basicEvaluate.
	 *
	 * This method may be invoked by derived classes that have inherited an unwanted override of evaluate.
	 *
	 * @since 1.3
	 */
	protected final @Nullable Object cachedEvaluate(@NonNull Executor executor, @NonNull TypedElement caller, @Nullable Object @NonNull [] sourceAndArgumentValues) {
		if (executor instanceof ExecutorInternal.ExecutorInternalExtension) {
			return ((ExecutorInternal.ExecutorInternalExtension)executor).getCachedEvaluationResult(this, caller, sourceAndArgumentValues);
		}
		else {
			return basicEvaluate(executor, caller, sourceAndArgumentValues);
		}
	}

	/**
	 * @since 1.15
	 */
	protected @Nullable SymbolicValue checkPreconditions(@NonNull AbstractSymbolicEvaluationEnvironment symbolicEvaluationEnvironment, @NonNull OperationCallExp callExp) {
		return null;					// source/argument validity
	}

	/**
	 * @since 1.15
	 */
	protected @NonNull SymbolicValue createChildSymbolicValue(@NonNull AbstractSymbolicEvaluationEnvironment symbolicEvaluationEnvironment, @NonNull OperationCallExp callExp,
			@NonNull SymbolicValue sourceSymbolicValue, @NonNull LibraryOperation libraryOperation, @NonNull List<@NonNull SymbolicValue> argumentSymbolicValues) {
		OCLExpression ownedSource = PivotUtil.getOwnedSource(callExp);
	//	SymbolicValue sourceSymbolicValue = symbolicAnalysis.symbolicEvaluate(ownedSource);
	//	boolean mayBeInvalidOrNull = sourceSymbolicValue.mayBeInvalidOrNull();
		boolean mayBeInvalidOrNull = symbolicEvaluationEnvironment.mayBeInvalidOrNull(ownedSource);
		for (@NonNull SymbolicValue argumentSymbolicValue : argumentSymbolicValues) {		// XXX correlate parameter/return nullity
			if (argumentSymbolicValue.mayBeInvalidOrNull()) {
				mayBeInvalidOrNull = true;
			}
		}
		return new SymbolicUnknownValueImpl(callExp.getTypeId(), false, mayBeInvalidOrNull);

	}

	/**
	 * Return the evaluation from sourceAndArgumentValues using the executor for context wrt a caller.
	 * The default implementation attempts to re-use a cached result which is initially provided by basicEvaluate.
	 *
	 * basicEvaluate should be overridden if caching is required, evaluate if caching is to be bypassed.
	 *
	 * @since 1.3
	 */
	@Override
	public @Nullable Object evaluate(@NonNull Executor executor, @NonNull TypedElement caller, @Nullable Object @NonNull [] sourceAndArgumentValues) {
		if (executor instanceof ExecutorInternal.ExecutorInternalExtension) {
			return ((ExecutorInternal.ExecutorInternalExtension)executor).getCachedEvaluationResult(this, caller, sourceAndArgumentValues);
		}
		else {
			return basicEvaluate(executor, caller, sourceAndArgumentValues);
		}
	}
	/** @deprecated use Executor
	 * @since 1.1*/
	@Deprecated
	@Override
	public @Nullable Object dispatch(@NonNull Evaluator evaluator, @NonNull OperationCallExp callExp, @Nullable Object sourceValue) {
		return dispatch(getExecutor(evaluator), callExp, sourceValue);
	}

	@Override
	public @Nullable Object dispatch(@NonNull Executor executor, @NonNull OperationCallExp callExp, @Nullable Object sourceValue) {
		ExecutorInternalExtension castExecutor = (ExecutorInternalExtension)executor;
		Operation apparentOperation = callExp.getReferredOperation();
		assert apparentOperation != null;
		//
		//	Resolve argument values catching invalid values for validating operations.
		//
		List<@NonNull OCLExpression> arguments = ClassUtil.nullFree(callExp.getOwnedArguments());
		@Nullable Object[] sourceAndArgumentValues = new @Nullable Object[1+arguments.size()];
		int argumentIndex = 0;
		sourceAndArgumentValues[argumentIndex++] = sourceValue;
		if (!apparentOperation.isIsValidating()) {
			for (@NonNull OCLExpression argument : arguments) {
				Object argValue = castExecutor.evaluate(argument);
				sourceAndArgumentValues[argumentIndex++] = argValue;
			}
		}
		else {
			for (@NonNull OCLExpression argument : arguments) {
				Object argValue;
				try {
					argValue = castExecutor.evaluate(argument);
					assert ValueUtil.isBoxed(argValue);	// Make sure Integer/Real are boxed, invalid is an exception, null is null
				}
				catch (EvaluationHaltedException e) {
					throw e;
				}
				catch (InvalidValueException e) {
					argValue = e;	// FIXME ?? propagate part of environment
				}
				sourceAndArgumentValues[argumentIndex++] = argValue;
			}
		}
		return castExecutor.internalExecuteOperationCallExp(callExp, sourceAndArgumentValues);
	}

	/**
	 * @since 1.3
	 */
	//	@Override
	//	public boolean isCached() {
	//		return true;
	//	}

	/**
	 * @since 1.15
	 */
	@Override
	public @NonNull SymbolicValue symbolicEvaluate(@NonNull AbstractSymbolicEvaluationEnvironment evaluationEnvironment, @NonNull OperationCallExp callExp) {
		SymbolicValue symbolicPreconditionValue = checkPreconditions(evaluationEnvironment, callExp);
		if (symbolicPreconditionValue != null) {
			return symbolicPreconditionValue;
		}
		SymbolicValue sourceSymbolicValue = evaluationEnvironment.symbolicEvaluate(PivotUtil.getOwnedSource(callExp));
		boolean isKnown = sourceSymbolicValue.isKnown();
		Iterable<@NonNull OCLExpression> ownedArguments = PivotUtil.getOwnedArguments(callExp);
		int argumentsSize = Iterables.size(ownedArguments);
		List<@NonNull SymbolicValue> argumentSymbolicValues = new ArrayList<@NonNull SymbolicValue>(argumentsSize);
		for (@NonNull OCLExpression argument : ownedArguments) {
			SymbolicValue argumentSymbolicValue = evaluationEnvironment.symbolicEvaluate(argument);
			if (!argumentSymbolicValue.isKnown()) {
				isKnown = false;
			}
			argumentSymbolicValues.add(argumentSymbolicValue);
		}
		if (isKnown) {
			@Nullable Object[] sourceAndArgumentValues = new @Nullable Object[1+argumentsSize];
			sourceAndArgumentValues[0] = ((SymbolicKnownValue)sourceSymbolicValue).getValue();
			for (int i = 0; i < argumentsSize; i++) {
				sourceAndArgumentValues[i+1] = ((SymbolicKnownValue)argumentSymbolicValues.get(i)).getValue();
			}
			Object result = ((LibraryOperationExtension2)this).evaluate(evaluationEnvironment.getExecutor(), callExp, sourceAndArgumentValues);
			return evaluationEnvironment.getKnownValue(result);
		}
		else {

			SymbolicValue childSymbolicValue = null;//sourceSymbolicValue.basicGetChildSymbolicValue(this, argumentSymbolicValues);
			if (childSymbolicValue == null) {
				childSymbolicValue = createChildSymbolicValue(evaluationEnvironment, callExp, sourceSymbolicValue, this, argumentSymbolicValues);
			//	sourceSymbolicValue.putChildSymbolicValue(this, argumentSymbolicValues, childSymbolicValue);
			}
			return childSymbolicValue;
		}
	}
}
