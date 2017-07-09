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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.internal.values.LazyCollectionValueImpl;
import org.eclipse.ocl.pivot.internal.values.SmartCollectionValueImpl;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.LazyIterator;

/**
 * FromArrayIterator provides the loader for a non-lazy CollectionValue from an array.
 */
public class FromArrayIterator extends AbstractLazyIterator
{
	public static @NonNull CollectionValue create(@NonNull CollectionTypeId collectionTypeId, @Nullable Object @NonNull [] elements) {
		SmartCollectionValueImpl collectionValue = new SmartCollectionValueImpl(collectionTypeId, new FromArrayIterator(elements));
		if (collectionValue.isSequence()) {
			collectionValue.cachedIterable();
		}
		else {
			collectionValue.eagerIterable();	// uniqueness/counts must be eager
		}
		return collectionValue;
	}

	private @Nullable Object @NonNull [] elements;
	private int nextIndex;

	protected FromArrayIterator(@Nullable Object @NonNull [] elements) {
		this.elements = elements;
		this.nextIndex = 0;
	}

	@Override
	public boolean isCached() {
		return true;
	}

	@Override
	public @NonNull LazyIterator reIterator() {
		return new FromArrayIterator(elements);
	}

	@Override
	public int getNextCount() {
		if (nextIndex < elements.length) {
			return setNext(elements[nextIndex++], 1);
		}
		return 0;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		LazyCollectionValueImpl.appendArray(s, elements, 50);
	}
}