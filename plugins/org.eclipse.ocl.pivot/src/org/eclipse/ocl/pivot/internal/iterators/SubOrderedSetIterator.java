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

import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.values.BaggableIterator;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * SubOrderedSetIterator provides a lazy evaluation of the Collection::subOrderedSet operation.
 *
 * @since 1.3
 */
public class SubOrderedSetIterator extends LazyCollectionValueImpl
{
	public static @NonNull CollectionValue subOrderedSet(@NonNull CollectionValue sourceValue, int lower, int upper) {
		return new SubOrderedSetIterator(sourceValue, lower, upper);
	}

	private final @NonNull CollectionValue sourceValue;
	private final @NonNull BaggableIterator<@Nullable Object> sourceIterator;
	private final int lower;
	private final int upper;
	private int size = 0;

	public SubOrderedSetIterator(@NonNull CollectionValue sourceValue, int lower, int upper) {
		super(sourceValue.getTypeId());
		this.sourceValue = sourceValue;
		this.sourceIterator = baggableIterator(sourceValue);
		this.lower = lower;
		this.upper = upper;
		if (lower < 1) {
			throw new InvalidValueException(PivotMessages.IndexOutOfRange, lower, "?");
		}
		if (upper < lower) {
			throw new InvalidValueException(PivotMessages.IndexOutOfRange, upper, "?");
		}
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
	protected @NonNull Iterator<@Nullable Object> reIterator() {
		return new SubOrderedSetIterator(sourceValue, lower, upper);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("SubOrd{");
		s.append(sourceIterator);
		s.append(",");
		s.append(lower);
		s.append(",");
		s.append(upper);
		s.append("}");
	}
}