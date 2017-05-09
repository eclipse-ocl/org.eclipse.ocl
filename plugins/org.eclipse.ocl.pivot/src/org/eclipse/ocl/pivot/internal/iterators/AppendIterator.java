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

/**
 * AppendIterator provides a lazy evaluation of the Collection::append operation.
 *
 * @since 1.3
 */
public abstract class AppendIterator extends LazyCollectionValueImpl
{
	public static @NonNull CollectionValue append(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue sourceValue, @Nullable Object object) {
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

	protected final @NonNull BaggableIterator<@Nullable Object> sourceIterator;
	protected final @Nullable Object object;
	protected boolean doneAppend = false;

	public AppendIterator(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue sourceValue, @Nullable Object object) {
		super(collectionTypeId);
		this.sourceIterator = baggableIterator(sourceValue);
		this.object = object;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("Append{");
		s.append(sourceIterator);
		s.append(",");
		s.append(object instanceof String ? "'" + object + "'" : object);
		s.append("}");
	}

	// The appended value goes at the end accruing any counts from a pre-existing value.
	private static class ToBag extends AppendIterator
	{
		private final @NonNull EqualsStrategy equalsStrategy;
		protected int appendCount = 0;

		public ToBag(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue sourceValue, @Nullable Object secondValue) {
			super(collectionTypeId, sourceValue, secondValue);
			this.equalsStrategy = TypeUtil.getEqualsStrategy(typeId.getElementTypeId(), false);
		}

		@Override
		protected int getNextCount() {
			for (int nextCount; (nextCount = sourceIterator.hasNextCount()) > 0; ) {
				Object next = sourceIterator.next();
				if ((appendCount == 0) && equalsStrategy.isEqual(next, object)) {
					appendCount = nextCount;
				}
				else {
					return setNext(next, nextCount);
				}
			}
			if (!doneAppend) {
				doneAppend = true;
				return setNext(object, appendCount + 1);
			}
			return 0;
		}
	}

	// The appended value goes at the end.
	private static class ToSequence extends AppendIterator
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
			if (!doneAppend) {
				doneAppend = true;
				return setNext(object, 1);
			}
			return 0;
		}
	}

	// The appended value goes at the end displacing any previous value.
	private static class ToUnique extends AppendIterator
	{
		private final @NonNull EqualsStrategy equalsStrategy;

		public ToUnique(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue sourceValue, @Nullable Object secondValue) {
			super(collectionTypeId, sourceValue, secondValue);
			this.equalsStrategy = TypeUtil.getEqualsStrategy(typeId.getElementTypeId(), false);
		}

		@Override
		protected int getNextCount() {
			for (int nextCount; (nextCount = sourceIterator.hasNextCount()) > 0; ) {
				Object next = sourceIterator.next();
				if (!equalsStrategy.isEqual(next, object)) {
					return setNext(next, nextCount);
				}
			}
			if (!doneAppend) {
				doneAppend = true;
				return setNext(object, 1);
			}
			return 0;
		}
	}
}
