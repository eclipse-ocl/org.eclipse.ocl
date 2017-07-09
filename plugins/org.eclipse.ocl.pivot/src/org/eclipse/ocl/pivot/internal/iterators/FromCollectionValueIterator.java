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
package org.eclipse.ocl.pivot.internal.iterators;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.internal.values.LazyCollectionValueImpl;
import org.eclipse.ocl.pivot.internal.values.SmartCollectionValueImpl;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.LazyIterator;

/**
 * FromCollectionValueIterator provides the loader for a CollectionValue from a CollectionValue.
 * This is typically to convert between collection strategies.
 */
public class FromCollectionValueIterator extends AbstractLazyIterator
{
	public static @NonNull CollectionValue create(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue oldCollectionValue) {
		FromCollectionValueIterator inputIterator = new FromCollectionValueIterator(oldCollectionValue);
		SmartCollectionValueImpl newCollectionValue = new SmartCollectionValueImpl(collectionTypeId, inputIterator, oldCollectionValue);
		if (oldCollectionValue.isSequence() && !newCollectionValue.isSequence()) {
			newCollectionValue.eagerIterable();	// uniqueness/counts must be eager
		}
		return newCollectionValue;
	}

	private @NonNull CollectionValue elements;
	private @NonNull LazyIterator iterator;

	protected FromCollectionValueIterator(@NonNull CollectionValue elements) {
		this.elements = elements;
		this.iterator = elements.lazyIterator();
	}

	@Override
	public boolean isCached() {
		return iterator.isCached();
	}

	@Override
	public int getNextCount() {
		int hasNextCount = iterator.hasNextCount();
		if (hasNextCount > 0) {
			return setNext(iterator.next(), hasNextCount);
		}
		return 0;
	}

	@Override
	public @NonNull LazyIterator reIterator() {
		return new FromCollectionValueIterator(elements);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		LazyCollectionValueImpl.appendIterable(s, elements, 50);
	}
}