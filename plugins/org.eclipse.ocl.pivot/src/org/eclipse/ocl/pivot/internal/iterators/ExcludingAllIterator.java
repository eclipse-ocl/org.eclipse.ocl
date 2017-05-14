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
import org.eclipse.ocl.pivot.values.BaggableIterator;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.LazyCollectionValue;

/**
 * ExcludingAllIterator provides a lazy evaluation of the Collection::excludingAll operation.
 *
 * @since 1.3
 */
public class ExcludingAllIterator extends LazyCollectionValueImpl
{
	public static @NonNull CollectionValue excludingAll(@NonNull CollectionValue sourceValue, @NonNull CollectionValue excludeValue) {
		return new ExcludingAllIterator(sourceValue, excludeValue);
	}

	private final @NonNull CollectionValue sourceValue;
	private final @NonNull BaggableIterator<@Nullable Object> sourceIterator;
	private final @NonNull CollectionValue excludeValue;

	public ExcludingAllIterator(@NonNull CollectionValue sourceValue, @NonNull CollectionValue excludeValue) {
		super(sourceValue.getTypeId());
		this.sourceValue = sourceValue;
		this.sourceIterator = baggableIterator(sourceValue);
		this.excludeValue = eagerCollectionValue(excludeValue);
	}

	@Override
	public int getNextCount() {
		for (int nextCount; (nextCount = sourceIterator.hasNextCount()) > 0; ) {
			Object next = sourceIterator.next();
			if (!excludeValue.includes(next)) {
				return setNext(next, nextCount);
			}
		}
		return 0;
	}

	@Override
	protected @NonNull LazyCollectionValue reIterator() {
		return new ExcludingAllIterator(sourceValue, excludeValue);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("ExcludingAll{");
		s.append(sourceIterator);
		s.append(",");
		s.append(excludeValue);
		s.append("}");
	}
}