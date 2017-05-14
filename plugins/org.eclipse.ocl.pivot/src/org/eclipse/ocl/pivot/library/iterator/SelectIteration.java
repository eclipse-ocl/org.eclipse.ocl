/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library.iterator;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.evaluation.Evaluator;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.evaluation.IterationManager;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.iterators.SelectIterator;
import org.eclipse.ocl.pivot.library.AbstractIteration;
import org.eclipse.ocl.pivot.library.LibraryIteration;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.CollectionValue.Accumulator;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.LazyCollectionValue;
import org.eclipse.ocl.pivot.values.Value;

/**
 * SelectIteration realizes the Collection::select() library iteration.
 */
public class SelectIteration extends AbstractIteration implements LibraryIteration.LazyIteration
{
	protected static class LazySelectIterator extends SelectIterator
	{
		private final @NonNull Executor executor;
		private final @NonNull OCLExpression body;
		private final @NonNull VariableDeclaration firstIterator;

		protected LazySelectIterator(@NonNull CollectionTypeId typeId, @NonNull CollectionValue sourceValue,
				@NonNull Executor executor, @NonNull OCLExpression body, @NonNull VariableDeclaration firstIterator) {
			super(typeId, sourceValue);
			this.executor = executor;
			this.body = body;
			this.firstIterator = firstIterator;
		}

		@Override
		protected boolean body(Object next) {
			executor.replace(firstIterator, next);
			return ValueUtil.asBoolean(executor.evaluate(body)) == Boolean.TRUE;
		}

		@Override
		protected @NonNull LazyCollectionValue reIterator() {
			return new LazySelectIterator(typeId, sourceValue, executor, body, firstIterator);
		}
	}

	public static final @NonNull SelectIteration INSTANCE = new SelectIteration();

	/** @deprecated use Executor */
	@Deprecated
	@Override
	public CollectionValue.@NonNull Accumulator createAccumulatorValue(@NonNull Evaluator evaluator, @NonNull TypeId accumulatorTypeId, @NonNull TypeId bodyTypeId) {
		return createAccumulatorValue(ValueUtil.getExecutor(evaluator), accumulatorTypeId, bodyTypeId);
	}

	/**
	 * @since 1.1
	 */
	@Override
	public CollectionValue.@NonNull Accumulator createAccumulatorValue(@NonNull Executor executor, @NonNull TypeId accumulatorTypeId, @NonNull TypeId bodyTypeId) {
		return createCollectionAccumulatorValue((CollectionTypeId)accumulatorTypeId);
	}

	@Override
	protected @Nullable Object updateAccumulator(@NonNull IterationManager iterationManager) {
		Object bodyVal = iterationManager.evaluateBody();
		assert !(bodyVal instanceof InvalidValueException);
		if (bodyVal == null) {
			throw new InvalidValueException(PivotMessages.UndefinedBody, "select"); 	// Null body is invalid //$NON-NLS-1$
		}
		else if (bodyVal == TRUE_VALUE) {
			Object value = iterationManager.get();
			Accumulator accumulatorValue = (CollectionValue.Accumulator)iterationManager.getAccumulatorValue();
			assert accumulatorValue != null;												// createAccumulatorValue is @NonNull
			accumulatorValue.add(value);
		}
		else if (bodyVal != Boolean.FALSE) {
			throw new InvalidValueException(PivotMessages.NonBooleanBody, "select"); 	// Non boolean body is invalid //$NON-NLS-1$
		}
		return CARRY_ON;
	}

	/**
	 * @since 1.3
	 */
	@Override
	public @NonNull Value evaluate(@NonNull Executor executor, @NonNull CollectionTypeId typeId, @NonNull CollectionValue sourceValue, @NonNull VariableDeclaration firstIterator,
			@NonNull OCLExpression body) {
		return new LazySelectIterator(typeId, sourceValue, executor, body, firstIterator);
	}
}
