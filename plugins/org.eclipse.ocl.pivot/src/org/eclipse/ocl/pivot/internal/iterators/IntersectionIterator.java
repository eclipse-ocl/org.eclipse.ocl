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
import org.eclipse.ocl.pivot.values.BaggableIterator;
import org.eclipse.ocl.pivot.values.CollectionValue;

/**
 * IntersectionIterator provides a lazy evaluation of the Collection::intersection operation.
 *
 * @since 1.3
 */
public class IntersectionIterator extends AbstractBaggableIterator
{
	public static @NonNull IntersectionIterator intersection(@NonNull CollectionValue sourceValue, @NonNull CollectionValue secondValue) {
		TypeId elementTypeId = sourceValue.getElementTypeId();
		if (sourceValue.isUnique()) {
			CollectionTypeId setTypeId = TypeId.SET.getSpecializedId(elementTypeId);
			return new IntersectionIterator(setTypeId, sourceValue, secondValue);
		}
		else if (secondValue.isUnique()) {
			CollectionTypeId setTypeId = TypeId.SET.getSpecializedId(elementTypeId);
			return new IntersectionIterator(setTypeId, secondValue, sourceValue);
		}
		else {
			CollectionTypeId bagTypeId = TypeId.BAG.getSpecializedId(elementTypeId);
			return new IntersectionIterator(bagTypeId, sourceValue, secondValue);
		}
	}

	private final @NonNull LazyIterable<? extends Object> sourceIterable;
	private final @NonNull BaggableIterator<Object> secondIterator;

	public IntersectionIterator(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue sourceValue, @NonNull CollectionValue secondValue) {
		super(collectionTypeId);
		Iterable<? extends Object> sourceIterable = sourceValue.iterable();
		this.sourceIterable = sourceIterable instanceof LazyIterable ? (LazyIterable<? extends Object>)sourceIterable : new LazyIterable<>(sourceValue.iterator(), sourceValue.getCollectionFactory());;
		this.secondIterator = secondValue.iterator();
	}

	@Override
	protected int getNextCount() {
		int nextCount = 0;
		while ((nextCount = secondIterator.hasNextCount()) > 0) {
			Object next = secondIterator.next();
			nextCount = Math.min(nextCount, sourceIterable.count(next));
			if (nextCount > 0) {
				setNext(next);
				return nextCount;
			}
		}
		return 0;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("Intersection{");
		s.append(sourceIterable.iterator());
		s.append(", ");
		s.append(secondIterator);
		s.append("}");
	}
}