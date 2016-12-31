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
package org.eclipse.ocl.pivot.internal.iterators;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.values.BagIterator;
import org.eclipse.ocl.pivot.internal.values.LazyIterable;
import org.eclipse.ocl.pivot.values.CollectionValue;

/**
 * IntersectionIterator provides a lazy evaluation of the Collection::intersection operation.
 *
 * @since 1.3
 */
public class IntersectionIterator extends AbstractBagIterator
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
	private final @NonNull BagIterator<Object> iterator;
	private Object next;

	public IntersectionIterator(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue firstValue, @NonNull CollectionValue secondValue) {
		super(collectionTypeId);
		Iterable<? extends Object> iterable = firstValue.iterable();
		this.reference = iterable instanceof LazyIterable ? (LazyIterable<? extends Object>)iterable : new LazyIterable<>(firstValue.iterator(), firstValue.isOrdered(), firstValue.isUnique());;
		this.iterator = secondValue.iterator();
	}

	@Override
	protected Object getNext() {
		return next;
	}

	@Override
	protected int getNextCount() {
		for (int nextCount; (nextCount = iterator.hasNextCount()) > 0; ) {
			next = iterator.next();
			nextCount = Math.min(nextCount, reference.count(next));
			if (nextCount > 0) {
				return nextCount;
			}
		}
		return 0;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("Intersection{");
		s.append(reference.iterator());
		s.append(", ");
		s.append(iterator);
		s.append("}");
	}
}