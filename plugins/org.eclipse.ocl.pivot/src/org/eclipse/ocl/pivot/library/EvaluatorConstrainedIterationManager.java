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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.IteratorExp;
import org.eclipse.ocl.pivot.IteratorVariable;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.evaluation.IterationManager;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.optimizer.Optimizer;
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
	 * RedirectedIteratorValue redirects the outer iterator to the inner.
	 */
	private class RedirectedIteratorValue implements DelegatedValue
	{
		private @NonNull IteratorVariable iteratorVariable;

		protected RedirectedIteratorValue(@NonNull Executor executor, @Nullable Object accumulatorValue, @NonNull IteratorVariable iteratorVariable) {
			this.iteratorVariable = iteratorVariable;
			executor.add(iteratorVariable, this);
//			toString();
		}

		@Override
		public @Nullable Object get() {
			for (Adapter eAdapter : iteratorVariable.eAdapters()) {
				if (eAdapter instanceof Optimizer.InliningAdapter) {
					Optimizer.InliningAdapter inliningAdapter = (Optimizer.InliningAdapter)eAdapter;
					IteratorVariable specializedIteratorVariable = inliningAdapter.getSpecializedIteratorVariable();
					return executor.getEvaluationEnvironment().getValueOf(specializedIteratorVariable);
				}
			}
			return null;
		}

	//	public void set(@Nullable Object accumulatorValue) {
	//		this.accumulatorValue = accumulatorValue;
	//	}

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
		VariableDeclaration firstIterator = iterators.get(0);
		VariableDeclaration indexIterator = coIterators.size() >= 1 ? coIterators.get(0) : null;
		return new EvaluatorConstrainedIterationManager(executor, iteratorExp, body, collectionValue, null, null, firstIterator, indexIterator);
	}

	protected final @NonNull TypedElement referredIterator;

	/** @ deprecated supply a callExp
	@ Deprecated
	public EvaluatorConstrainedIterationManager(@NonNull Evaluator invokingEvaluator,
			@NonNull OCLExpression body, @NonNull CollectionValue collectionValue,
			@Nullable TypedElement accumulator, @Nullable Object accumulatorValue,
			@NonNull TypedElement referredIterator) {
		this(ValueUtil.getExecutor(invokingEvaluator), null, body, collectionValue, accumulator, accumulatorValue, referredIterator);
	} */

	/*@ Deprecated / * @ deprecated specify indexIterator
	public EvaluatorConstrainedIterationManager(@NonNull Executor invokingExecutor,
			/ *@NonNull* / CallExp callExp, @NonNull OCLExpression body, @NonNull CollectionValue collectionValue,
			@Nullable TypedElement accumulator, @Nullable Object accumulatorValue,
			@NonNull TypedElement referredIterator) {
		this(invokingExecutor, callExp, body, collectionValue, accumulator, accumulatorValue, referredIterator, null);
	} */

	public EvaluatorConstrainedIterationManager(@NonNull Executor executor,
			@NonNull CallExp callExp, @NonNull OCLExpression body, @NonNull CollectionValue collectionValue,
			@Nullable Variable accumulatorVariable, @Nullable Object accumulatorValue,
			@NonNull TypedElement firstIterator, @Nullable TypedElement indexIterator) {
		super(executor, callExp, body, collectionValue, accumulatorVariable, accumulatorValue);
		this.referredIterator = firstIterator;
		new RedirectedIteratorValue(executor, collectionValue, (IteratorVariable)firstIterator);
		if (indexIterator != null) {
			new RedirectedIteratorValue(executor, collectionValue, (IteratorVariable)indexIterator);
		}
	}

/*	protected EvaluatorConstrainedIterationManager(@NonNull EvaluatorConstrainedIterationManager iterationManager, @NonNull CollectionValue value) {
		super(iterationManager, value);
		this.referredIterator = iterationManager.referredIterator;
		this.iterator = new ValueIterator(executor, collectionValue, referredIterator, null);		// FIXME
	} */

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
		Iteration referredIteration = iteratorExp.getReferredIteration();
		ExpressionInOCL expressionInOCL = iteratorExp.getOwnedInlinedBody();		// 	specialized
		//
		OCLExpression source = iteratorExp.getOwnedSource();
		CollectionValue sourceValue = (CollectionValue) this.iterableValue;			// XXX
		//
		List<Variable> outerIterators = iteratorExp.getOwnedIterators();
		List<IteratorVariable> outerCoIterators = iteratorExp.getOwnedCoIterators();
		//
		OCLExpression iterationBody = iteratorExp.getOwnedBody();



		PivotUtil.checkExpression(expressionInOCL);
		EvaluationEnvironment nestedEvaluationEnvironment = executor.pushEvaluationEnvironment(expressionInOCL, iteratorExp);
		nestedEvaluationEnvironment.add(Objects.requireNonNull(expressionInOCL.getOwnedContext()), sourceValue);
		List<Variable> parameters = expressionInOCL.getOwnedParameters();
		List<OCLExpression> asArguments = iteratorExp instanceof IterateExp ? ((IterateExp)iteratorExp).getOwnedBodies() : Collections.singletonList(iteratorExp.getOwnedBody());
		assert parameters.size() == asArguments.size();
		for (int i = 0; i < parameters.size(); i++) {
			nestedEvaluationEnvironment.add(Objects.requireNonNull(parameters.get(i)), asArguments.get(i));
		}
		try {
			OCLExpression bodyExpression = expressionInOCL.getOwnedBody();
			assert bodyExpression != null;
			return executor.evaluate(bodyExpression);			// XXX eager specialize on call or lazy specialize on access ???
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