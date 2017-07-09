/*******************************************************************************
 * Copyright (c) 2017 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.values;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * A LazyIterator supports lazy iterations that may use the bag-aware iterator protocol.
 * A LazyIteratpr may not be used for a Collection with an undiscovered invalid value.
 *
 * @since 1.3
 */
public interface LazyIterator extends BaggableIterator<@Nullable Object>
{
	/**
	 * Return true if the source of this iterator is cached making reIteration simple.
	 */
	boolean isCached();

	/**
	 * Return another iterator for the source of this iterator. This is only intended to
	 * be used when isCached() returns true. It may however be-used for an uncached source
	 * in which case reundant repeated compuations are likely.
	 */
	@NonNull LazyIterator reIterator();
}