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
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
import org.eclipse.ocl.pivot.values.BaggableIterator;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * IncludingIterator provides a lazy evaluation of the Collection::including operation.
 *
 * @since 1.3
 */
public abstract class IncludingIterator extends LazyCollectionValueImpl
{
	public static @NonNull CollectionValue including(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue sourceValue, @Nullable Object object) {
		if (object instanceof InvalidValueException) {
			throw (InvalidValueException)object;
		}
		if (sourceValue.isUnique()) {
			return new ToUnique(collectionTypeId, sourceValue, object);
		}
		else if (sourceValue.isOrdered()) {
			return new ToSequence(collectionTypeId, sourceValue, object);
		}
		else {
			return new ToBag(collectionTypeId, sourceValue, object);
		}
	}

	protected final @NonNull CollectionValue sourceValue;
	protected final @NonNull BaggableIterator<@Nullable Object> sourceIterator;
	protected final @Nullable Object object;
	protected boolean doneInclude = false;

	public IncludingIterator(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue sourceValue, @Nullable Object object) {
		super(collectionTypeId);
		this.sourceValue = sourceValue;
		this.sourceIterator = baggableIterator(sourceValue);
		this.object = object;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("Including{");
		s.append(sourceIterator);
		s.append(",");
		s.append(object instanceof String ? "'" + object + "'" : object);
		s.append("}");
	}

	// The included value increments the coount of a pre-existing value else it goes at the end.
	private static class ToBag extends IncludingIterator
	{
		private final @NonNull EqualsStrategy equalsStrategy;

		public ToBag(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue sourceValue, @Nullable Object secondValue) {
			super(collectionTypeId, sourceValue, secondValue);
			this.equalsStrategy = TypeUtil.getEqualsStrategy(typeId.getElementTypeId(), false);
		}

		@Override
		protected int getNextCount() {
			for (int nextCount; (nextCount = sourceIterator.hasNextCount()) > 0; ) {
				Object next = sourceIterator.next();
				if (equalsStrategy.isEqual(next, object)) {
					doneInclude = true;
					return setNext(next, nextCount+1);
				}
				else {
					return setNext(next, nextCount);
				}
			}
			if (!doneInclude) {
				doneInclude = true;
				return setNext(object, 1);
			}
			return 0;
		}

		@Override
		protected @NonNull Iterator<@Nullable Object> reIterator() {
			return new ToBag(typeId, sourceValue, object);
		}
	}

	// The included value goes at the end.
	private static class ToSequence extends IncludingIterator
	{
		public ToSequence(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue sourceValue, @Nullable Object secondValue) {
			super(collectionTypeId, sourceValue, secondValue);
		}

		@Override
		protected int getNextCount() {
			int nextCount = sourceIterator.hasNextCount();
			if (nextCount > 0) {
				return setNext(sourceIterator.next(), nextCount);
			}
			if (!doneInclude) {
				doneInclude = true;
				return setNext(object, 1);
			}
			return 0;
		}

		@Override
		protected @NonNull Iterator<@Nullable Object> reIterator() {
			return new ToSequence(typeId, sourceValue, object);
		}
	}

	// The included value goes at the end unless there is already a previous value.
	private static class ToUnique extends IncludingIterator
	{
		private final @NonNull EqualsStrategy equalsStrategy;

		public ToUnique(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue sourceValue, @Nullable Object secondValue) {
			super(collectionTypeId, sourceValue, secondValue);
			this.equalsStrategy = TypeUtil.getEqualsStrategy(typeId.getElementTypeId(), false);
		}

		@Override
		protected int getNextCount() {
			for (int nextCount; (nextCount = sourceIterator.hasNextCount()) > 0; ) {
				assert nextCount == 1;
				Object next = sourceIterator.next();
				if (equalsStrategy.isEqual(next, object)) {
					doneInclude = true;
				}
				return setNext(next, nextCount);
			}
			if (!doneInclude) {
				doneInclude = true;
				return setNext(object, 1);
			}
			return 0;
		}

		@Override
		protected @NonNull Iterator<@Nullable Object> reIterator() {
			return new ToUnique(typeId, sourceValue, object);
		}
	}
}
