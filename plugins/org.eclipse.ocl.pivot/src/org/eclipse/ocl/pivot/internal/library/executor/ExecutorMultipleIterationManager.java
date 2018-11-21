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
package org.eclipse.ocl.pivot.internal.library.executor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.AbstractIterationManager;
import org.eclipse.ocl.pivot.library.AbstractSimpleOperation;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.IterableValue;

/**
 * ExecutorMultipleIterationManager supervises a multiple iterator collection iteration evaluation for which the iteration context is
 * maintained in dedicated variables typically allocated by the code generator.
 *
 * @since 1.6
 */
public class ExecutorMultipleIterationManager extends AbstractIterationManager
{
	protected final @NonNull TypeId returnTypeId;
	protected final @NonNull AbstractSimpleOperation body;
	private @Nullable Object accumulatorValue;
	protected final @NonNull CollectionValue collectionValue;
	private @NonNull List<@NonNull Iterator<@Nullable Object>> iteratorValues;
	private final @Nullable Object @NonNull [] arguments;

	public ExecutorMultipleIterationManager(@NonNull Executor executor, int iterators, @NonNull TypeId returnTypeId, @NonNull AbstractSimpleOperation body,
			@Nullable CollectionValue collectionValue, @Nullable Object accumulatorValue) {
		super(executor);
		this.returnTypeId = returnTypeId;
		this.body = body;
		this.accumulatorValue = accumulatorValue;
		this.collectionValue = ValueUtil.asCollectionValue(collectionValue);
		this.iteratorValues = new ArrayList<@NonNull Iterator<@Nullable Object>>(iterators);
		this.arguments = new @Nullable Object[1 + iterators];
		this.arguments[0] = this.collectionValue;
		for (int i = 0; i < iterators; i++) {
			Iterator<@Nullable Object> iteratorValue = this.collectionValue.iterator();
			iteratorValues.add(iteratorValue);
			this.arguments[1 + i] = iteratorValue.hasNext() ? iteratorValue.next() : iteratorValues;
		}
	}

	@Override
	public boolean advanceIterators() {
		for (int i = 0; i < iteratorValues.size(); i++) {
			Iterator<@Nullable Object> iteratorValue = iteratorValues.get(i);
			if (iteratorValue.hasNext()) {
				Object keyValue = iteratorValue.next();
				arguments[1 + i] = keyValue;
				for (int j = i; --j >= 0; ) {
					iteratorValue = collectionValue.iterator();
					assert iteratorValue.hasNext();
					iteratorValues.set(j, iteratorValue);
					keyValue = iteratorValue.next();
					arguments[1 + j] = keyValue;
				}
				return true;
			}
		}
		for (int i = 0; i < iteratorValues.size(); i++) {
			arguments[1 + i] = iteratorValues;
		}
		return false;
	}

	@Override
	public @Nullable Object evaluateBody() {
		return body.evaluate(executor, returnTypeId, arguments);
	}

	@Override
	public @Nullable Object get() {
		if (iteratorValues.size() == 1) {
			return arguments[1];
		}
		return super.get();
	}

	@Override
	public @Nullable Object getAccumulatorValue() {
		return accumulatorValue;
	}

	@Override
	public @NonNull CollectionValue getSourceCollection() {
		return collectionValue;
	}

	@Override
	public @NonNull IterableValue getSourceIterable() {
		return collectionValue;
	}

	@Override
	public boolean hasCurrent() {
		return arguments[1] != iteratorValues;
	}

	@Override
	public @Nullable Object updateAccumulator(Object newValue) {
		this.accumulatorValue = newValue;
		return null;					// carry on
	}
}