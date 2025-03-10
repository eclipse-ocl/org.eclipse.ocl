/*******************************************************************************
 * Copyright (c) 2025 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
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

/**
 * SearchIteration realizes the Collection::search() library iteration.
 *
 * @since 1.23
 */
public class SearchIteration extends AbstractIteration
{
	public static final @NonNull SearchIteration INSTANCE = new SearchIteration();

	/** @deprecated use Executor */
	@Deprecated
	@Override
	public @NonNull Object createAccumulatorValue(@NonNull Evaluator evaluator, @NonNull TypeId accumulatorTypeId, @NonNull TypeId bodyTypeId) {
		return createAccumulatorValue(ValueUtil.getExecutor(evaluator), accumulatorTypeId, bodyTypeId);
	}

	/**
	 * @since 1.1
	 */
	@Override
	public @NonNull Object createAccumulatorValue(@NonNull Executor executor, @NonNull TypeId accumulatorTypeId, @NonNull TypeId bodyTypeId) {
		throw new UnsupportedOperationException();		// Never used since values are assigned directly as the accumulator
	}

	@Override
	protected @Nullable Object resolveTerminalValue(@NonNull IterationManager iterationManager) {
	//	return ((IterationManagerExtension)iterationManager).getExecutor().evaluate(body);
		// TODO Auto-generated method stub
		return super.resolveTerminalValue(iterationManager);
	}

	@Override
	protected @Nullable Object updateAccumulator(@NonNull IterationManager iterationManager) {
		Object bodyValue = iterationManager.evaluateBody();
		iterationManager.updateAccumulator(bodyValue);
		return CARRY_ON;
	}
}
