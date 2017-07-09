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

import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.evaluation.Evaluator;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.evaluation.IterationManager;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.AbstractIteration;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.MutableIterable;

/**
 * CollectIteration realizes the Collection::collect() library iteration.
 */
public class CollectIteration extends AbstractIteration
{
	public static final @NonNull CollectIteration INSTANCE = new CollectIteration();

	/** @deprecated use Executor */
	@Deprecated
	@Override
	public @NonNull MutableIterable createAccumulatorValue(@NonNull Evaluator evaluator, @NonNull TypeId accumulatorTypeId, @NonNull TypeId bodyTypeId) {
		return createAccumulatorValue(ValueUtil.getExecutor(evaluator), accumulatorTypeId, bodyTypeId);
	}

	/**
	 * @since 1.1
	 */
	@Override
	public @NonNull MutableIterable createAccumulatorValue(@NonNull Executor executor, @NonNull TypeId accumulatorTypeId, @NonNull TypeId bodyTypeId) {
		return createCollectionAccumulatorValue((CollectionTypeId) accumulatorTypeId);
	}

	@Override
	protected @Nullable Object updateAccumulator(@NonNull IterationManager iterationManager) {
		Object bodyVal = iterationManager.evaluateBody();
		MutableIterable accumulatorValue = (MutableIterable)iterationManager.getAccumulatorValue();
		assert accumulatorValue != null;
		if (bodyVal == null) {
			accumulatorValue.mutableIncluding(bodyVal);
		}
		else if (bodyVal instanceof CollectionValue) {
			CollectionValue bodyColl = (CollectionValue) bodyVal;
			//			try {
			Iterator<@Nullable Object> iterator = bodyColl.flatten().lazyIterator();
			while (iterator.hasNext()) {
				Object value = iterator.next();
				if (value != null) {
					accumulatorValue.mutableIncluding(value);
				}
			}
			//			} catch (InvalidValueException e) {
			//				iterationManager.throwInvalidEvaluation(e);
			//			}
		}
		else
			accumulatorValue.mutableIncluding(bodyVal);
		return CARRY_ON;								// Carry on
	}
}
