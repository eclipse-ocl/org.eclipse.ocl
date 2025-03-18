/*******************************************************************************
 * Copyright (c) 2011, 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.IteratorExp;
import org.eclipse.ocl.pivot.IteratorVariable;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.evaluation.IterationManager;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.utilities.DelegatedValue;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.IterableValue;

/**
 * EvaluatorConstrainedIterationManager supervises the redirection of an iterator collection iteration
 * to an OCL implementation of the iteration.
 *
 * @since 1.23
 */
public class EvaluatorConstrainedIterationManager extends AbstractEvaluatorIterableIterationManager<@NonNull IterableValue>
{
	/**
	 * SpecializingIteratorValue redirects the outer iterator to the specialized inner.
	 */
	private class SpecializingIteratorValue implements DelegatedValue
	{
		private @NonNull IteratorVariable iteratorVariable;

		protected SpecializingIteratorValue(@NonNull Executor executor, @Nullable Object accumulatorValue, @NonNull IteratorVariable iteratorVariable) {
			this.iteratorVariable = iteratorVariable;
			executor.add(iteratorVariable, this);
		}

		@Override
		public @Nullable Object get() {
			IteratorVariable specializedIteratorVariable = PivotUtil.getSpecializedIterator(iteratorVariable);
			return executor.getEvaluationEnvironment().getValueOf(specializedIteratorVariable);
		}

		@Override
		public @NonNull String toString() {
			try {
				Object value = get();
				return String.valueOf(value);
			}
			catch (Throwable e) {
				return "«" + String.valueOf(e.getMessage()) + "»";
			}
		}
	}

	public static @NonNull EvaluatorConstrainedIterationManager create(@NonNull Executor executor, @NonNull IteratorExp iteratorExp, @NonNull CollectionValue collectionValue) {
		OCLExpression body = PivotUtil.getOwnedBody(iteratorExp);
		List<@NonNull Variable> iterators = PivotUtilInternal.getOwnedIteratorsList(iteratorExp);
		List</*@Nullable*/ IteratorVariable> coIterators = iteratorExp.getOwnedCoIterators();
		IteratorVariable firstIterator = (IteratorVariable)iterators.get(0);
		IteratorVariable indexIterator = coIterators.size() >= 1 ? coIterators.get(0) : null;
		return new EvaluatorConstrainedIterationManager(executor, iteratorExp, body, collectionValue, null, null, firstIterator, indexIterator);
	}

	public EvaluatorConstrainedIterationManager(@NonNull Executor executor,
			@NonNull CallExp callExp, @NonNull OCLExpression body, @NonNull CollectionValue collectionValue,
			@Nullable Variable accumulatorVariable, @Nullable Object accumulatorValue,
			@NonNull IteratorVariable firstIterator, @Nullable IteratorVariable indexIterator) {
		super(executor, callExp, body, collectionValue, accumulatorVariable, accumulatorValue);
		new SpecializingIteratorValue(executor, collectionValue, firstIterator);
		if (indexIterator != null) {
			new SpecializingIteratorValue(executor, collectionValue, indexIterator);
		}
	}

	@Override
	public boolean advanceIterators() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull IterationManager createNestedIterationManager(@NonNull IterableValue value) {
		throw new UnsupportedOperationException();
	}

	public Object evaluate() {
		IteratorExp iteratorExp = (IteratorExp)this.callExp;
		ExpressionInOCL specializedExpressionInOCL = iteratorExp.getOwnedSpecializedBody();		// 	specialized
		assert specializedExpressionInOCL != null;
		//
		CollectionValue sourceValue = (CollectionValue)this.iterableValue;			// XXX

		PivotUtil.checkExpression(specializedExpressionInOCL);
		EvaluationEnvironment nestedEvaluationEnvironment = executor.pushEvaluationEnvironment(specializedExpressionInOCL, iteratorExp);
		nestedEvaluationEnvironment.add(Objects.requireNonNull(specializedExpressionInOCL.getOwnedContext()), sourceValue);
		List<Variable> parameters = specializedExpressionInOCL.getOwnedParameters();
		List<OCLExpression> asArguments = iteratorExp instanceof IterateExp ? ((IterateExp)iteratorExp).getOwnedBodies() : Collections.singletonList(iteratorExp.getOwnedBody());
		assert parameters.size() == asArguments.size();
		for (int i = 0; i < parameters.size(); i++) {
			nestedEvaluationEnvironment.add(Objects.requireNonNull(parameters.get(i)), asArguments.get(i));
		}
		try {
			OCLExpression bodyExpression = specializedExpressionInOCL.getOwnedBody();
			assert bodyExpression != null;
			return executor.evaluate(bodyExpression);
		}
		finally {
			executor.popEvaluationEnvironment();
		}
	}

	@Override
	public @Nullable Object get() {
		throw new UnsupportedOperationException();
	}

	public int getDepth() {
		return 0;
	}

	public @NonNull EvaluatorConstrainedIterationManager getRootIterationManager() {
		return this;
	}

	@Override
	public boolean hasCurrent() {
//		return iterator.hasCurrent();
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return ((IteratorExp)callExp).getReferredIteration().getBodyExpression().toString();
	}
}