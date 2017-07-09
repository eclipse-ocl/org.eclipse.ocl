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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.values.LazyCollectionValueImpl;
import org.eclipse.ocl.pivot.values.BaggableIterator;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.LazyIterator;

/**
 * IntersectionIterator provides a lazy evaluation of the Collection::intersection operation.
 *
 * @since 1.3
 */
public class IntersectionIterator extends LazyCollectionValueImpl
{
	public static @NonNull IntersectionIterator intersection(@NonNull CollectionValue sourceValue, @NonNull CollectionValue secondValue) {
		TypeId elementTypeId = sourceValue.getTypeId().getElementTypeId();
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

	private final @NonNull CollectionValue sourceValue;
	private final @NonNull CollectionValue secondValue;
	private final @NonNull BaggableIterator<@Nullable Object> secondIterator;

	public IntersectionIterator(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue sourceValue, @NonNull CollectionValue secondValue) {
		super(collectionTypeId, lazyDepth(sourceValue));
		this.sourceValue = eagerCollectionValue(sourceValue);
		this.secondValue = secondValue;
		this.secondIterator = secondValue.lazyIterator();
	}

	@Override
	protected int getNextCount() {
		int nextCount = 0;
		while ((nextCount = secondIterator.hasNextCount()) > 0) {
			Object next = secondIterator.next();
			nextCount = Math.min(nextCount, sourceValue.count(next).intValue());
			if (nextCount > 0) {
				return setNext(next, nextCount);
			}
		}
		return 0;
	}

	@Override
	public @NonNull LazyIterator reIterator() {
		return new IntersectionIterator(typeId, sourceValue, secondValue);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("Intersection{");
		s.append(sourceValue);
		s.append(", ");
		s.append(secondIterator);
		s.append("}");
	}
}