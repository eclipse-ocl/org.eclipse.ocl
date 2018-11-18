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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.MapValue;

/**
 * @since 1.6
 */
public abstract class AbstractEvaluatorMapIterationManager extends AbstractIterationManager
{
	protected static class MapValueIterator
	{
		private final EvaluationEnvironment evaluationEnvironment;
		private final @NonNull MapValue mapValue;
		private final @NonNull TypedElement keyVariable;
		private final @Nullable TypedElement valueVariable;
		private Iterator<? extends Object> javaIter;
		private Object keyValue;		// 'null' is a valid value so 'this' is used as end of iteration

		/**
		 * @since 1.6
		 */
		public MapValueIterator(@NonNull Executor executor, @NonNull MapValue mapValue, @NonNull TypedElement keyVariable, @Nullable TypedElement valueVariable) {
			this.evaluationEnvironment = executor.getEvaluationEnvironment();
			this.mapValue = mapValue;
			this.keyVariable = keyVariable;
			this.valueVariable = valueVariable;
			reset();
		}

		public @Nullable Object get() {
			return keyValue;
		}

		public boolean hasCurrent() {
			return keyValue != this;
		}

		public @Nullable Object next() {
			if (!javaIter.hasNext()) {
				keyValue = this;
			}
			else {
				keyValue = javaIter.next();
				evaluationEnvironment.replace(keyVariable, keyValue);
				TypedElement valueVariable2 = valueVariable;
				if (valueVariable2 != null) {
					Object valueValue = mapValue.at(keyValue);
					evaluationEnvironment.replace(valueVariable2, valueValue);
				}
			}
			return keyValue;
		}

		public Object reset() {
			javaIter = mapValue.iterator();
			return next();
		}

		@Override
		public String toString() {
			return String.valueOf(keyVariable) + " = " + (keyValue != this ? String.valueOf(keyValue) : "<<END>>");
		}
	}

	protected final @NonNull MapValue mapValue;
	protected final /*@NonNull*/ CallExp callExp;		// Null at root or when calling context unknown
	protected final @NonNull OCLExpression body;
	protected final @Nullable TypedElement accumulatorVariable;
	private @Nullable Object accumulatorValue;

	protected AbstractEvaluatorMapIterationManager(@NonNull Executor executor, /*@NonNull*/ CallExp callExp, @NonNull OCLExpression body, @NonNull MapValue mapValue,
			@Nullable TypedElement accumulatorVariable, @Nullable Object accumulatorValue) {
		super(executor);
		this.mapValue = mapValue;
		this.callExp = callExp;
		this.body = body;
		this.accumulatorVariable = accumulatorVariable;
		this.accumulatorValue = accumulatorValue;
		if (accumulatorVariable != null) {
			getEvaluationEnvironment().add(accumulatorVariable, accumulatorValue);
		}
		((Executor.ExecutorExtension)this.executor).pushEvaluationEnvironment(body, (Object)callExp);
	}

	public AbstractEvaluatorMapIterationManager(@NonNull AbstractEvaluatorMapIterationManager iterationManager, @NonNull MapValue mapValue) {
		super(iterationManager.executor);
		this.callExp = iterationManager.callExp;
		this.body = iterationManager.body;
		this.mapValue = mapValue;
		this.accumulatorValue = iterationManager.accumulatorValue;
		this.accumulatorVariable = iterationManager.accumulatorVariable;
		((Executor.ExecutorExtension)this.executor).pushEvaluationEnvironment(body, (Object)callExp);
	}

	@Override
	public void dispose() {
		executor.popEvaluationEnvironment();
	}

	@Override
	public @Nullable Object evaluateBody() {
		return executor.evaluate(body);
	}

	@Override
	public @Nullable Object getAccumulatorValue() {
		return accumulatorValue;
	}

	//	public @NonNull CollectionValue getCollectionValue() {
	//		//	return collectionValue;
	//		throw new UnsupportedOperationException();
	//	}

	public @NonNull EvaluationEnvironment getEvaluationEnvironment() {
		return executor.getEvaluationEnvironment();
	}

	@Override
	public @NonNull CollectionValue getSourceCollection() {
		//	return collectionValue;
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return body.toString();
	}

	@Override
	public @Nullable Object updateAccumulator(Object newValue) {
		this.accumulatorValue = newValue;
		TypedElement accumulatorVariable2 = accumulatorVariable;
		if (accumulatorVariable2 != null) {
			getEvaluationEnvironment().replace(accumulatorVariable2, accumulatorValue);
		}
		return null;					// carry on
	}
}