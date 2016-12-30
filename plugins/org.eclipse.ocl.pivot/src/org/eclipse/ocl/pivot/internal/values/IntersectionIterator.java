/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.values;

import java.util.NoSuchElementException;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.values.CollectionValue;

/**
 * IntersectionIterator provides a lazy evaluation of the Collection::intersection operation.
 *
 * @since 1.3
 */
public class IntersectionIterator extends AbstractCollectionIterator
{
	public static @NonNull IntersectionIterator intersection(@NonNull CollectionValue firstValue, @NonNull CollectionValue secondValue) {
		TypeId elementTypeId = firstValue.getElementTypeId();
		if (firstValue.isUnique()) {
			CollectionTypeId setTypeId = TypeId.SET.getSpecializedId(elementTypeId);
			return new IntersectionIterator(setTypeId, firstValue, secondValue);
		}
		else if (secondValue.isUnique()) {
			CollectionTypeId setTypeId = TypeId.SET.getSpecializedId(elementTypeId);
			return new IntersectionIterator(setTypeId, secondValue, firstValue);
		}
		else {
			CollectionTypeId bagTypeId = TypeId.BAG.getSpecializedId(elementTypeId);
			return new IntersectionIterator(bagTypeId, firstValue, secondValue);
		}
	}

	private final @NonNull LazyIterable<? extends Object> reference;
	private final @NonNull BagIterator<@Nullable Object> iterator;
	private boolean canBeIterable = true;
	private int nextCount = 0;
	private @Nullable Object next;
	private int useCount = 0;

	public IntersectionIterator(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue firstValue, @NonNull CollectionValue secondValue) {
		super(collectionTypeId);
		Iterable<? extends Object> iterable = firstValue.iterable();
		this.reference = iterable instanceof LazyIterable ? (LazyIterable<? extends Object>)iterable : new LazyIterable<>(firstValue.iterator(), firstValue.isOrdered(), firstValue.isUnique());;
		this.iterator = secondValue.iterator();
	}

	@Override
	protected boolean canBeIterable() {
		return canBeIterable;
	}

	@Override
	public boolean hasNext() {
		if (hasNextCount() >= 1) {
			useCount = 1;
			return true;
		}
		else {
			useCount = 0;
			return false;
		}
	}

	@Override
	public int hasNextCount() {
		if (nextCount <= 0) {
			while ((nextCount = iterator.hasNextCount()) > 0) {
				next = iterator.next();
				nextCount = Math.min(nextCount, reference.count(next));
				if (nextCount > 0) {
					break;
				}
			}
		}
		useCount = nextCount;
		return nextCount;
	}

	@Override
	public @Nullable Object next() {
		canBeIterable = false;
		if (useCount <= 0) {
			hasNextCount();
		}
		if (nextCount <= 0) {
			throw new NoSuchElementException();
		}
		nextCount -= useCount;
		useCount = 0;
		return next;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("Intersection{");
		appendIterator(s, sizeLimit, reference.iterator());
		s.append(", ");
		appendIterator(s, sizeLimit, iterator);
		s.append("}");
	}
}