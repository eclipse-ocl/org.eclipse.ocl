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
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.values.BaggableIterator;
import org.eclipse.ocl.pivot.values.CollectionValue;

/**
 * SelectIterator provides the framework for lazy evaluation of a Collection::select iteration.
 *
 * @since 1.3
 */
public abstract class SelectIterator extends AbstractBaggableIterator
{
	private final @NonNull BaggableIterator<Object> sourceIterator;

	protected SelectIterator(@NonNull CollectionTypeId typeId, @NonNull CollectionValue sourceValue) {
		super(typeId);
		this.sourceIterator = sourceValue.baggableIterator();
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