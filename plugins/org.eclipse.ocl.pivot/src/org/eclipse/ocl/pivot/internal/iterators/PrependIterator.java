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
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
import org.eclipse.ocl.pivot.values.BaggableIterator;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.LazyIterator;

/**
 * PrependIterator provides a lazy evaluation of the Collection::prepend operation.
 *
 * @since 1.3
 */
public abstract class PrependIterator extends LazyCollectionValueImpl
{
	public static @NonNull CollectionValue prepend(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue sourceValue, @Nullable Object object) {
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
	protected int prependCount = 0;

	public PrependIterator(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue sourceValue, @Nullable Object object) {
		super(collectionTypeId, lazyDepth(sourceValue));
		this.sourceValue = sourceValue;
		this.object = object;
		this.sourceIterator = sourceValue.lazyIterator();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("Prepend{");
		s.append(sourceIterator);
		s.append(",");
		s.append(object instanceof String ? "'" + object + "'" : object);
		s.append("}");
	}

	// The prepended value increments the coount of a pre-existing value else it goes at the end.
	private static class ToBag extends PrependIterator
	{
		private final @NonNull EqualsStrategy equalsStrategy;

		public ToBag(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue sourceValue, @Nullable Object secondValue) {
			super(collectionTypeId, sourceValue, secondValue);
			this.equalsStrategy = TypeUtil.getEqualsStrategy(typeId.getElementTypeId(), false);
		}

		@Override
		protected int getNextCount() {
			if (prependCount <= 0) {
				Object next = object;
				prependCount = 1 + sourceValue.count(next).intValue();
				return setNext(next, prependCount);
			}
			for (int nextCount; (nextCount = sourceIterator.hasNextCount()) > 0; ) {
				Object next = sourceIterator.next();
				if ((prependCount == 0) || !equalsStrategy.isEqual(next, object)) {
					return setNext(next, nextCount);
				}
			}
			return 0;
		}

		@Override
		protected @NonNull LazyIterator reIterator() {
			return new ToBag(typeId, sourceValue, object);
		}
	}

	// The prepended value goes at the beginning.
	private static class ToSequence extends PrependIterator
	{
		public ToSequence(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue sourceValue, @Nullable Object secondValue) {
			super(collectionTypeId, sourceValue, secondValue);
		}

		@Override
		protected int getNextCount() {
			if (prependCount <= 0) {
				prependCount = 1;
				return setNext(object, 1);
			}
			int nextCount = sourceIterator.hasNextCount();
			if (nextCount > 0) {
				return setNext(sourceIterator.next(), nextCount);
			}
			return 0;
		}

		@Override
		protected @NonNull LazyIterator reIterator() {
			return new ToSequence(typeId, sourceValue, object);
		}
	}

	// The prepended value goes at the beginning.
	private static class ToUnique extends PrependIterator
	{
		private final @NonNull EqualsStrategy equalsStrategy;

		public ToUnique(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue sourceValue, @Nullable Object secondValue) {
			super(collectionTypeId, sourceValue, secondValue);
			this.equalsStrategy = TypeUtil.getEqualsStrategy(typeId.getElementTypeId(), false);
		}

		@Override
		protected int getNextCount() {
			if (prependCount <= 0) {
				prependCount = 1;
				return setNext(object, 1);
			}
			for (int nextCount; (nextCount = sourceIterator.hasNextCount()) > 0; ) {
				assert nextCount == 1;
				Object next = sourceIterator.next();
				if (!equalsStrategy.isEqual(next, object)) {
					return setNext(next, nextCount);
				}
			}
			return 0;
		}

		@Override
		protected @NonNull LazyIterator reIterator() {
			return new ToUnique(typeId, sourceValue, object);
		}
	}
}
