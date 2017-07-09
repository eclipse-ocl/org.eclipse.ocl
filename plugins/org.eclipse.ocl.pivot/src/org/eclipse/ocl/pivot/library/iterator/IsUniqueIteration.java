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
import org.eclipse.ocl.pivot.evaluation.Evaluator;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.evaluation.IterationManager;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.AbstractIteration;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.MutableIterable;

/**
 * IsUniqueIteration realizes the Collection::isUnique() library iteration.
 */
public class IsUniqueIteration extends AbstractIteration
{
	public static final @NonNull IsUniqueIteration INSTANCE = new IsUniqueIteration();

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
		//		return new SetValueImpl.Accumulator(TypeId.SET.getSpecializedId(accumulatorTypeId));
		return ValueUtil.createCollectionAccumulatorValue(TypeId.SET.getSpecializedId(accumulatorTypeId));
	}

	@Override
	protected @NonNull Object resolveTerminalValue(@NonNull IterationManager iterationManager) {
		return true;
	}

	@Override
	protected @Nullable Object updateAccumulator(@NonNull IterationManager iterationManager) {
		MutableIterable accumulatorValue = (MutableIterable)iterationManager.getAccumulatorValue();
		assert accumulatorValue != null;
		Object bodyVal = iterationManager.evaluateBody();
		assert !(bodyVal instanceof InvalidValueException);
		if (accumulatorValue.includes(bodyVal) == TRUE_VALUE) {
			return false;						// Abort after second find
		}
		else {
			accumulatorValue.mutableIncluding(bodyVal);
			return CARRY_ON;					// Carry on after first find
		}
	}
}
