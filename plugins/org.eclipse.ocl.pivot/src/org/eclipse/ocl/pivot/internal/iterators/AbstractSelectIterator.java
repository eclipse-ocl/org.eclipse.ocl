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
package org.eclipse.ocl.pivot.internal.iterators;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.LazyIterator;

/**
 * AbstractSelectIterator provides the framework for lazy evaluation of a Collection::select iteration.
 *
 * @since 1.3
 */
public abstract class AbstractSelectIterator extends AbstractLazyIterator
{
	protected final @NonNull CollectionValue sourceValue;
	private final @NonNull LazyIterator sourceIterator;

	protected AbstractSelectIterator(@NonNull CollectionValue sourceValue) {
		this.sourceValue = sourceValue.eagerIterable();
		assert sourceValue.canBeCached();
		//		sourceValue.toString();
		this.sourceIterator = sourceValue.lazyIterator();
	}

	protected abstract boolean body(Object next);

	@Override
	public int getNextCount() {
		for (int nextCount; (nextCount = sourceIterator.hasNextCount()) > 0; ) {
			Object next = sourceIterator.next();
			boolean hasNext = body(next);
			if (hasNext) {
				return setNext(next, nextCount);
			}
		}
		return 0;
	}
}