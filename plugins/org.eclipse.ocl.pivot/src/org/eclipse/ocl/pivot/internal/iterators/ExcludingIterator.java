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
import org.eclipse.ocl.pivot.utilities.TypeUtil;
import org.eclipse.ocl.pivot.values.BaggableIterator;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.LazyCollectionValue;

/**
 * ExcludingIterator provides a lazy evaluation of the Collection::excluding operation.
 *
 * @since 1.3
 */
public class ExcludingIterator extends LazyCollectionValueImpl
{
	public static @NonNull CollectionValue excluding(@NonNull CollectionValue sourceValue, @Nullable Object object) {
		if (object instanceof InvalidValueException) {
			throw (InvalidValueException)object;
		}
		return new ExcludingIterator(sourceValue, object);
	}

	private final @NonNull CollectionValue sourceValue;
	private final @NonNull BaggableIterator<@Nullable Object> sourceIterator;
	private final @Nullable Object object;
	private final @NonNull EqualsStrategy equalsStrategy;

	public ExcludingIterator(@NonNull CollectionValue sourceValue, @Nullable Object object) {
		super(sourceValue.getTypeId());
		this.sourceValue = sourceValue;
		this.sourceIterator = baggableIterator(sourceValue);
		this.object = object;
		this.equalsStrategy = TypeUtil.getEqualsStrategy(typeId.getElementTypeId(), object == null);
	}

	@Override
	protected int getNextCount() {
		for (int nextCount; (nextCount = sourceIterator.hasNextCount()) > 0; ) {
			Object next = sourceIterator.next();
			if (!equalsStrategy.isEqual(next, object)) {
				return setNext(next, nextCount);
			}
		}
		return 0;
	}

	@Override
	protected @NonNull LazyCollectionValue reIterator() {
		return new ExcludingIterator(sourceValue, object);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("Excluding{");
		s.append(sourceIterator);
		s.append(",");
		s.append(object instanceof String ? "'" + object + "'" : object);
		s.append("}");
	}
}
