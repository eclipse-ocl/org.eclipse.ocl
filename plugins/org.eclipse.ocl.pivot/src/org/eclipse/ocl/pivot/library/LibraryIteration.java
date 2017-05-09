/*******************************************************************************
 * Copyright (c) 2011, 2015 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.evaluation.Evaluator;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.evaluation.IterationManager;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.Value;

/**
 */
public interface LibraryIteration extends LibraryFeature
{
	/**
	 * @since 1.1
	 */
	public interface LibraryIterationExtension extends LibraryIteration
	{
		/**
		 * Create the value that will accumulate the iteration results.
		 */
		@NonNull Object createAccumulatorValue(@NonNull Executor executor, @NonNull TypeId accumulatorTypeId, @NonNull TypeId bodyTypeId);
	}

	/**
	 * @since 1.3
	 */
	public interface LazyIteration extends LibraryIteration
	{
		/**
		 * Create the value that will accumulate the iteration results.
		 */
		@NonNull Value evaluate(@NonNull Executor executor, @NonNull CollectionTypeId typeId,
				@NonNull CollectionValue sourceValue, @NonNull VariableDeclaration firstIterator,
				@NonNull OCLExpression body);
	}

	/** @deprecated use Executor */
	@Deprecated
	@NonNull Object createAccumulatorValue(@NonNull Evaluator evaluator, @NonNull TypeId accumulatorTypeId, @NonNull TypeId bodyTypeId);

	/**
	 * Evaluate the iteration under the supervision of an iterationManager.
	 *
	 * @param iterationManager the iteration space
	 * @return the result
	 *
	 * @throws InvalidValueException if a body evaluates to invalid
	 */
	@Nullable Object evaluateIteration(@NonNull IterationManager iterationManager);
}
