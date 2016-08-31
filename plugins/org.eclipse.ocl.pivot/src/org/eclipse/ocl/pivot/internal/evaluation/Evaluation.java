/*******************************************************************************
 * Copyright (c) 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.evaluation;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.IdResolver;

/**
 * An Evaluation identifies a unique evaluation of its input values. This is used to support
 * - an operation cache which associates a result with its arguments.
 *
 * @noimplement clients should derive from AbstractEvaluation
 * @since 1.3
 */
public interface Evaluation
{
	@Nullable Object getResult();

	/**
	 * Return true if this is an evaluation from thoseValues.
	 */
	boolean isEqual(@NonNull IdResolver idResolver, @Nullable Object @NonNull [] thoseValues);

	public interface Constructor
	{
		/**
		 * Return the cached evaluation from sourceAndArgumentValues, using newInstance(sourceAndArgumentValues) to
		 * create a new evaluation instance if necessary.
		 */
		public @Nullable Object getUniqueEvaluationResult(@Nullable Object @NonNull ... sourceAndArgumentValues);
	}
}