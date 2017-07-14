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
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.IntegerRange;
import org.eclipse.ocl.pivot.values.LazyIterator;

/**
 * FromRangesIterator provides the loader for a CollectionValue from one of more values some of which may be IntegerRanges.
 */
public class FromIntegerRangesIterator extends AbstractLazyIterator
{
	public static @NonNull CollectionValue create(@NonNull CollectionTypeId collectionTypeId, boolean uniqueElements, @NonNull Object @NonNull [] elements) {
		LazyCollectionValueImpl collectionValue = new LazyCollectionValueImpl(collectionTypeId, new FromIntegerRangesIterator(elements), null);
		if (!uniqueElements && !collectionValue.isSequence()) {
			collectionValue.eagerIterable();	// uniqueness/counts must be eager
		}
		return collectionValue;
	}

	private @NonNull Object @NonNull [] elements;
	private int nextIndex;
	private @Nullable IntegerRange nextRange;
	private int rangeIndex;

	protected FromIntegerRangesIterator(@NonNull Object @NonNull [] elements) {
		this.elements = elements;
		this.nextIndex = 0;
		this.nextRange = null;
		this.rangeIndex = 0;
	}

	@Override
	public int getNextCount() {
		IntegerRange nextRange2 = nextRange;
		if (nextRange2 != null) {
			if (rangeIndex < nextRange2.size()) {
				return setNext(nextRange2.get(rangeIndex++), 1);
			}
			nextRange = nextRange2 = null;
			rangeIndex = 0;
		}
		while (nextIndex < elements.length) {
			assert nextRange == null;
			assert rangeIndex == 0;
			Object value = elements[nextIndex++];
			if (value instanceof IntegerRange) {
				nextRange2 = (IntegerRange)value;
				if (0 < nextRange2.size()) {
					nextRange = nextRange2;
					return setNext(nextRange2.get(rangeIndex++), 1);
				}
			}
			else {
				return setNext(value, 1);
			}
		}
		return 0;
	}

	@Override
	public @NonNull LazyIterator reIterator() {
		return new FromIntegerRangesIterator(elements);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		AbstractLazyIterator.appendArray(s, elements, 50);
	}
}