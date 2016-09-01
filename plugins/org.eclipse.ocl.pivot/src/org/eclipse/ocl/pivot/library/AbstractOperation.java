/*******************************************************************************
 * Copyright (c) 2010, 2015 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.evaluation.Evaluator;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.internal.evaluation.ExecutorInternal.ExecutorInternalExtension;

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
public abstract class AbstractOperation extends AbstractFeature implements LibraryOperation.LibraryOperationExtension2
{
	/** @deprecated use Executor
	 * @since 1.1*/
	@Deprecated
	@Override
	public @Nullable Object dispatch(@NonNull Evaluator evaluator, @NonNull OperationCallExp callExp, @Nullable Object sourceValue) {
		return dispatch(getExecutor(evaluator), callExp, sourceValue);
	}

	/**
	 * Return the evaluation from sourceAndArgumentValues using the executor for context wrt a callExp context.
	 *
	 * Derived calsses should override basicEvalute to evaluate a cacheable result, or evaluate to bypass caching.
	 *
	 * @since 1.3
	 */
	@Override
	public @Nullable Object basicEvaluate(@NonNull Executor executor, @NonNull OCLExpression callExp, @Nullable Object @NonNull [] sourceAndArgumentValues) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Return the evaluation from sourceAndArgumentValues using the executor for context wrt a callExp context.
	 * The implementation attempts to re-use a cached result which is initially provided by basicEvaluate.
	 *
	 * This method may be invoked by derived classes that have inherited an unwanted override of evaluate.
	 *
	 * @since 1.3
	 */
	protected final @Nullable Object cachedEvaluate(@NonNull Executor executor, @NonNull OCLExpression callExp, @Nullable Object @NonNull [] sourceAndArgumentValues) {
		if (executor instanceof ExecutorInternalExtension) {
			return ((ExecutorInternalExtension)executor).getCachedEvaluationResult(this, callExp, sourceAndArgumentValues);
		}
		else {
			return basicEvaluate(executor, callExp, sourceAndArgumentValues);
		}
	}

	/**
	 * Return the evaluation from sourceAndArgumentValues using the executor for context wrt a callExp context.
	 * The default implementation attempts to re-use a cached result which is initially provided by basicEvaluate.
	 *
	 * basicEvaluate should be overridden if caching is required, evaluate if caching is to be bypassed.
	 *
	 * @since 1.3
	 */
	@Override
	public @Nullable Object evaluate(@NonNull Executor executor, @NonNull OCLExpression callExp, @Nullable Object @NonNull [] sourceAndArgumentValues) {
		if (executor instanceof ExecutorInternalExtension) {
			return ((ExecutorInternalExtension)executor).getCachedEvaluationResult(this, callExp, sourceAndArgumentValues);
		}
		else {
			return basicEvaluate(executor, callExp, sourceAndArgumentValues);
		}
	}

	/**
	 * @since 1.3
	 */
	//	@Override
	//	public boolean isCached() {
	//		return true;
	//	}

}
