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
import org.eclipse.ocl.pivot.internal.values.SmartCollectionValueImpl;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.LazyIterator;

/**
 * SubOrderedSetIterator provides a lazy evaluation of the Collection::subOrderedSet/subSequence operation.
 *
 * @since 1.3
 */
public class SubCollectionIterator extends AbstractLazyIterator
{
	public static @NonNull CollectionValue subCollection(@NonNull CollectionValue sourceValue, int lower, int upper) {
		SubCollectionIterator inputIterator = new SubCollectionIterator(sourceValue, lower, upper);
		return new SmartCollectionValueImpl(sourceValue.getTypeId(), inputIterator, sourceValue);
	}

	private final @NonNull CollectionValue sourceValue;
	private final int lower;
	private final int upper;
	private final @NonNull LazyIterator sourceIterator;
	private int size = 0;

	public SubCollectionIterator(@NonNull CollectionValue sourceValue, int lower, int upper) {
		this.sourceValue = sourceValue;
		this.lower = lower;
		this.upper = upper;
		if (lower < 1) {
			throw new InvalidValueException(PivotMessages.IndexOutOfRange, lower, "?");
		}
		if (upper < lower) {
			throw new InvalidValueException(PivotMessages.IndexOutOfRange, upper, "?");
		}
		this.sourceIterator = sourceValue.lazyIterator();
	}

	@Override
	public int getNextCount() {
		if (size > (upper-1)) {
			return 0;
		}
		for (int nextCount; (nextCount = sourceIterator.hasNextCount()) > 0; ) {
			Object next = sourceIterator.next();
			size += nextCount;
			int newElements = size - (lower-1);
			if (newElements > 0) {
				if (newElements > nextCount) {
					newElements = nextCount;
				}
				return setNext(next, newElements);
			}
		}
		if (lower > size) {
			throw new InvalidValueException(PivotMessages.IndexOutOfRange, lower, size);
		}
		if (upper > size) {
			throw new InvalidValueException(PivotMessages.IndexOutOfRange, upper, size);
		}
		return 0;
	}

	@Override
	public boolean isCached() {
		return true;
	}

	@Override
	public @NonNull LazyIterator reIterator() {
		return new SubCollectionIterator(sourceValue, lower, upper);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("SubColl{");
		s.append(sourceIterator);
		s.append(",");
		s.append(lower);
		s.append(",");
		s.append(upper);
		s.append("}");
	}
}