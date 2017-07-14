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
import org.eclipse.ocl.pivot.internal.values.LazyCollectionValueImpl;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.LazyIterator;

/**
 * IntersectionIterator provides a lazy evaluation of the Collection::intersection operation.
 *
 * @since 1.3
 */
public class IntersectionIterator extends AbstractLazyIterator
{
	public static @NonNull CollectionValue intersection(@NonNull CollectionValue sourceValue, @NonNull CollectionValue secondValue) {
		CollectionTypeId collectionTypeId;
		LazyIterator inputIterator;
		if (sourceValue.isUnique()) {
			collectionTypeId = TypeUtil.getSetTypeId(sourceValue.getTypeId());
			inputIterator = new IntersectionIterator(sourceValue, secondValue);
		}
		else if (secondValue.isUnique()) {
			collectionTypeId = TypeUtil.getSetTypeId(sourceValue.getTypeId());
			inputIterator = new IntersectionIterator(secondValue, sourceValue);
		}
		else {
			collectionTypeId = TypeUtil.getBagTypeId(sourceValue.getTypeId());
			inputIterator = new IntersectionIterator(sourceValue, secondValue);
		}
		return new LazyCollectionValueImpl(collectionTypeId, inputIterator, sourceValue);
	}

	private final @NonNull CollectionValue sourceValue;
	private final @NonNull CollectionValue secondValue;
	private final @NonNull LazyIterator secondIterator;

	public IntersectionIterator(@NonNull CollectionValue sourceValue, @NonNull CollectionValue secondValue) {
		this.sourceValue = ValueUtil.eagerCollectionValue(sourceValue);
		this.secondValue = secondValue;
		this.secondIterator = secondValue.lazyIterator();
	}

	@Override
	public int getNextCount() {
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
		return new IntersectionIterator(sourceValue, secondValue);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("Intersection{");
		sourceValue.toString(s, sizeLimit-20);
		s.append(", ");
		secondIterator.reIterator().toString(s, sizeLimit-1);
		s.append("}");
	}
}