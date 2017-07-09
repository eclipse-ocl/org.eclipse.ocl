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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.values.LazyCollectionValueImpl;
import org.eclipse.ocl.pivot.values.BaggableIterator;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.LazyIterator;

/**
 * SymmetricDifferenceIterator provides a lazy evaluation of the Collection::symmetricDifference operation.
 *
 * @since 1.3
 */
public class SymmetricDifferenceIterator extends LazyCollectionValueImpl
{
	public static @NonNull CollectionValue symmetricDifference(@NonNull CollectionValue sourceValue, @NonNull CollectionValue otherValue) {
		return new SymmetricDifferenceIterator(sourceValue, otherValue);
	}

	private final @NonNull CollectionValue sourceValue;
	private final @NonNull CollectionValue otherValue;
	private final @NonNull BaggableIterator<@Nullable Object> sourceIterator;
	private final @NonNull BaggableIterator<@Nullable Object> otherIterator;

	public SymmetricDifferenceIterator(@NonNull CollectionValue sourceValue, @NonNull CollectionValue otherValue) {
		super(sourceValue.getTypeId(), lazyDepth(sourceValue));
		sourceValue.eagerIterable();
		otherValue.eagerIterable();
		this.sourceValue = sourceValue;
		this.otherValue = otherValue;
		this.sourceIterator = sourceValue.lazyIterator();
		this.otherIterator = otherValue.lazyIterator();
	}

	@Override
	public int getNextCount() {
		for (int nextCount; (nextCount = sourceIterator.hasNextCount()) > 0; ) {
			Object next = sourceIterator.next();
			if (!otherValue.includes(next)) {
				return setNext(next, nextCount);
			}
		}
		for (int nextCount; (nextCount = otherIterator.hasNextCount()) > 0; ) {
			Object next = otherIterator.next();
			if (!sourceValue.includes(next)) {
				return setNext(next, nextCount);
			}
		}
		return 0;
	}

	@Override
	public @NonNull LazyIterator reIterator() {
		return new SymmetricDifferenceIterator(sourceValue, otherValue);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("SymDiff{");
		s.append(sourceValue);
		s.append(",");
		s.append(otherValue);
		s.append("}");
	}
}