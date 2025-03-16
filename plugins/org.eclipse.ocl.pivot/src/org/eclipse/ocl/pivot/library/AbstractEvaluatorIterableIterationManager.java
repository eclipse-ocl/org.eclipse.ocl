/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.IteratorExp;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.utilities.DelegatedValue;
import org.eclipse.ocl.pivot.utilities.IteratorValue;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.IterableValue;
import org.eclipse.ocl.pivot.values.MapValue;

/**
 * AbstractEvaluatorIterableIterationManager supervises an iteration evaluation for which the iteration context is
 * maintained in the executor's evaluationEnvironment for access by the body expression evaluation.
 *
 * This generic variant of AbstractEvaluatorIterationManager is suitable for Maps or Collections but is currently only used by the
 * derived single/multiple map iteration managers. The corresponding collection managers should exploit as soon as the API ripple is acceptable.
 *
 * @since 1.6
 */
public abstract class AbstractEvaluatorIterableIterationManager<@NonNull IV extends IterableValue> extends AbstractIterationManager
{
	protected static abstract class AbstractValueIterator<@NonNull IV extends IterableValue> implements IteratorValue
	{
		protected final EvaluationEnvironment evaluationEnvironment;
		protected final @NonNull IV iterableValue;
		private final @NonNull TypedElement iteratorVariable;
		private Iterator<? extends Object> javaIter;
		private Object iteratorValue;		// 'null' is a valid value so 'this' is used as end of iteration

		public AbstractValueIterator(@NonNull Executor executor, @NonNull IV iterableValue, @NonNull TypedElement iteratorVariable) {
			this.evaluationEnvironment = executor.getEvaluationEnvironment();
			this.iterableValue = iterableValue;
			this.iteratorVariable = iteratorVariable;
		}

		/**
		 * @since 1.23
		 */
		protected @NonNull Iterator<@Nullable Object> createJavaIterator() {
			return iterableValue.iterator();
		}

		@Override
		public @Nullable Object get() {
			return iteratorValue;
		}

		@Override
		public @NonNull IV getIterableValue() {
			return iterableValue;
		}

		public boolean hasCurrent() {
			return iteratorValue != this;
		}

		public @Nullable Object next() {
			if (!javaIter.hasNext()) {
				iteratorValue = this;
			}
			else {
				iteratorValue = javaIter.next();
			}
			return iteratorValue;
		}

		public Object reset() {
			javaIter = createJavaIterator();
			return next();
		}

		@Override
		public String toString() {
			return String.valueOf(iteratorVariable) + " = " + (iteratorValue != this ? String.valueOf(iteratorValue) : "<<END>>");
		}
	}

	/**
	 * CollectionValue makes the evolving collection value iterator of an iteration available via the EvaluationEnvironment.
	 */
	protected static class CollectionValueIterator extends AbstractValueIterator<@NonNull CollectionValue>
	{
		/**
		 * CollectionCoValue makes the evolving collection index co-iterator of an iteration available via the EvaluationEnvironment.
		 */
		private class CollectionCoValue implements DelegatedValue
		{
			@Override
			public @NonNull Object get() {
				return ValueUtil.integerValueOf(currentIndex);
			}

			@Override
			public @NonNull String toString() {
				return String.valueOf(currentValue) + " @ " + String.valueOf(currentIndex);
			}
		}

		private Object currentValue;
		private int currentIndex = 0;

		@Deprecated /* @deprecated no longer used */
		public CollectionValueIterator(@NonNull Executor executor, @NonNull CollectionValue collectionValue, @NonNull TypedElement keyVariable) {
			this(executor, collectionValue, keyVariable, null);
		}

		/**
		 * @since 1.23
		 */
		public CollectionValueIterator(@NonNull Executor executor, @NonNull CollectionValue collectionValue, @NonNull TypedElement iteratorVariable, @Nullable TypedElement coIteratorVariable) {
			super(executor, collectionValue, iteratorVariable);
			reset();
			executor.add(iteratorVariable, this);
			if (coIteratorVariable != null) {
				executor.add(coIteratorVariable, new CollectionCoValue());
			}
		}

		@Override
		public @Nullable Object next() {
			currentValue = super.next();
			currentIndex++;
			return currentValue;
		}

		@Override
		public Object reset() {
			currentIndex = 0;
			return super.reset();
		}
	}

	/**
	 * MapCoValue makes the evolving map key iterator of an iteration available via the EvaluationEnvironment.
	 */
	protected static class MapValueIterator extends AbstractValueIterator<@NonNull MapValue>
	{
		/**
		 * MapCoValue makes the evolving map value co-iterator of an iteration available via the EvaluationEnvironment.
		 */
		private class MapCoValue implements DelegatedValue
		{
			@Override
			public @Nullable Object get() {
				return valueValue;
			}

			@Override
			public @NonNull String toString() {
				return String.valueOf(valueValue) + " @ " + String.valueOf(keyValue);
			}
		}

		private final boolean hasCoValues;
		private @Nullable Object keyValue;
		private @Nullable Object valueValue;

		public MapValueIterator(@NonNull Executor executor, @NonNull MapValue mapValue, @NonNull TypedElement keyVariable, @Nullable TypedElement valueVariable) {
			super(executor, mapValue, keyVariable);
			this.hasCoValues = valueVariable != null;
			reset();
			executor.add(keyVariable, this);
			if (hasCoValues) {
				assert valueVariable != null;
				executor.add(valueVariable, new MapCoValue());
			}
		}

		@Override
		protected @NonNull Iterator<@Nullable Object> createJavaIterator() {
			Set<?> iterable = hasCoValues ? iterableValue.entrySet() : iterableValue.keySet();
			@SuppressWarnings("unchecked") @NonNull Iterator<@Nullable Object> iterator = (Iterator<@Nullable Object>)iterable.iterator();
			return iterator;
		}

		@Override
		public @Nullable Object get() {
			return keyValue;
		}

		@Override
		public @Nullable Object next() {
			Object nextValue = super.next();
			if (nextValue == this) {
				return this;
			}
			else if (hasCoValues) {
				Map.Entry<?,?> entry = (Map.Entry<?,?>)nextValue;
				assert entry != null;
				keyValue = entry.getKey();
				valueValue = entry.getValue();
			}
			else {
				keyValue = nextValue;
			}
			return keyValue;
		}
	}

	/**
	 * AccumulatorValue makes the prevailing evolving accumulator value of an iteration available as the accumulator value via the EvaluationEnvironment.
	 * <br>
	 * Iterations rather than iterates may use an accumulator without publishing the value in the evaluation environment.
	 */
	private static class AccumulatorValue implements DelegatedValue
	{
		private @Nullable Object evolvingValue;		// Non-null for well-behaved iterations, Might be null for a weird iterate().

		protected AccumulatorValue(@NonNull Executor executor, @Nullable TypedElement/*Variable*/ accumulatorVariable, @Nullable Object initialValue) {
			this.evolvingValue = initialValue;
			if (accumulatorVariable != null) {
				executor.add(accumulatorVariable, this);
			}
		}

		@Override
		public @Nullable Object get() {
			return evolvingValue;
		}

		public void set(@Nullable Object nextValue) {
			this.evolvingValue = nextValue;
		}

		@Override
		public @NonNull String toString() {
			return String.valueOf(evolvingValue);
		}
	}

	protected final @NonNull IV iterableValue;
	protected final @NonNull CallExp callExp;		// ?? Null at root or when calling context unknown
	protected final @NonNull OCLExpression body;
	@Deprecated /* @deprecated not used */
	protected final @Nullable TypedElement accumulatorVariable = null;
	private final @NonNull AccumulatorValue accumulatorValue;

	protected AbstractEvaluatorIterableIterationManager(@NonNull Executor executor, @NonNull CallExp callExp, @NonNull OCLExpression body,
			@NonNull IV iterableValue, @Nullable TypedElement/*Variable*/ accumulatorVariable, @Nullable Object accumulatorValue) {
		this(executor, iterableValue, callExp, body, new AccumulatorValue(executor, accumulatorVariable, accumulatorValue));
	}

	@Deprecated /* @deprecated fold into subclasses */
	public AbstractEvaluatorIterableIterationManager(@NonNull AbstractEvaluatorIterableIterationManager<@NonNull IV> iterationManager, @NonNull IV iterableValue) {
		this(iterationManager.executor, iterableValue, iterationManager.callExp, iterationManager.body, iterationManager.accumulatorValue);
	}

	private AbstractEvaluatorIterableIterationManager(@NonNull Executor executor, @NonNull IV iterableValue, @NonNull CallExp callExp,
			@NonNull OCLExpression body, /*@Nullable TypedElement/ *Variable* / accumulatorVariable,*/ @NonNull AccumulatorValue accumulatorValue) {
		super(executor);
		this.iterableValue = iterableValue;
		this.callExp = callExp;
		this.body = body;
		this.accumulatorValue = accumulatorValue;
		((Executor.ExecutorExtension)this.executor).pushEvaluationEnvironment(body, (Object)callExp);
	}

	@Override
	public void dispose() {
		executor.popEvaluationEnvironment();
	}

	@Override
	public @Nullable Object evaluateBody() {
		Object value = executor.evaluate(body);
		if ((value instanceof OCLExpression) && (body.getType() instanceof LambdaType)) {

			IteratorExp outerIteratorExp = (IteratorExp)((OCLExpression)value).eContainer();
			List<Variable> outerIteratorVariables = outerIteratorExp.getOwnedIterators();
		//	List<IteratorVariable> outerCoIteratorVariables = outerIteratorExp.getOwnedCoIterators();
			ExpressionInOCL outerExpressionInOCL = PivotUtil.getContainingExpressionInOCL(outerIteratorExp);
		//	Iteration outerIteration = outerIteratorExp.getReferredIteration();
		//	List<Parameter> outerIteratorParameters = outerIteration.getOwnedIterators();			// XXX redundant inferrable from outerIteratorVariables

			IteratorExp innerIteratorExp = (IteratorExp)callExp;
			List<Variable> innerIteratorVariables = innerIteratorExp.getOwnedIterators();
		//	List<IteratorVariable> innerCoIteratorVariables = innerIteratorExp.getOwnedCoIterators();
			ExpressionInOCL expressionInOCL = PivotUtil.getContainingExpressionInOCL(body);
		//	Iteration innerIteration = innerIteratorExp.getReferredIteration();
		//	assert innerIteration != null;
		//	List<Parameter> innerIteratorParameters = innerIteration.getOwnedIterators();			// XXX redundant inferrable from outerIteratorVariables

			EvaluationEnvironment nestedEvaluationEnvironment = executor.pushEvaluationEnvironment(body, innerIteratorExp);
		//	List<Variable> iteratorVariables = iteratorExp.getOwnedIterators();
		//	List<IteratorVariable> coIteratorVariables = iteratorExp.getOwnedCoIterators();
		//	List<Parameter> iteratorParameters = iteration.getOwnedIterators();
		//	List<Parameter> coIteratorParameters = iteration.getOwnedCoIterators();
/*			int iMax = Math.max(outerIteratorVariables.size(), innerIteratorVariables.size());
			for (int i = 0; i < iMax; i++) {
				Variable outerIteratorVariable = outerIteratorVariables.get(i);
				assert outerIteratorVariable != null;
			//	Parameter outerIteratorParameter = outerIteratorVariable.getRepresentedParameter();
			//	Variable innerIteratorVariable = innerIteratorVariables.get(i);
			//	Parameter innerIteratorParameter = innerIteratorVariable.getRepresentedParameter();
				AbstractValueIterator<?> innerIteratorValue = getIteratorValue(i);
			//	Object innerValue = nestedEvaluationEnvironment.getValueOf(iterator);
	//			nestedEvaluationEnvironment.add(outerIteratorVariable, innerIteratorValue);
			//	if ((coIteratorVariables != null) && (i < coIteratorVariables.size())) {
			//		Variable coIteratorVariable = coIteratorVariables.get(i);
				//	Parameter coIteratorParameter = coIteratorParameters.get(i);
			//	}
			} */
			value = executor.evaluate((OCLExpression)value);		// XXX Push re-nesting
			executor.popEvaluationEnvironment();
		}
		return value;
	}

	@Override
	public @Nullable Object evaluateBody(int bodyIndex) {
		OCLExpression indexedBody = ((IterateExp)callExp).getOwnedBodies().get(bodyIndex);
		assert indexedBody != null;
		return executor.evaluate(indexedBody);
	}

	@Override
	public @Nullable Object getAccumulatorValue() {
		return accumulatorValue.get();
	}

	public @NonNull EvaluationEnvironment getEvaluationEnvironment() {
		return executor.getEvaluationEnvironment();
	}

	@Override
	public boolean advanceIterators() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public @NonNull IterableValue getSourceIterable() {
		return iterableValue;
	}

	@Override
	public String toString() {
		return body.toString();
	}

	@Override
	public @Nullable Object updateAccumulator(Object newValue) {
		accumulatorValue.set(newValue);
		return null;					// carry on
	}
}