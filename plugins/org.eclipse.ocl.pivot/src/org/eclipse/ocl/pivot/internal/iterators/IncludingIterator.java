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
import org.eclipse.ocl.pivot.internal.values.LazyCollectionValueImpl;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.LazyIterator;

/**
 * IncludingIterator provides a lazy evaluation of the Collection::including operation.
 *
 * @since 1.3
 */
public abstract class IncludingIterator extends AbstractLazyIterator
{
	public static @NonNull CollectionValue including(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue sourceValue, @Nullable Object object) {
		if (object instanceof InvalidValueException) {
			throw (InvalidValueException)object;
		}
		LazyIterator inputIterator;
		if (sourceValue.isUnique()) {
			EqualsStrategy equalsStrategy = TypeUtil.getEqualsStrategy(collectionTypeId.getElementTypeId(), false);
			inputIterator = new ToUnique(equalsStrategy, sourceValue, object);
		}
		else if (sourceValue.isOrdered()) {
			inputIterator = new ToSequence(sourceValue, object);
		}
		else {
			EqualsStrategy equalsStrategy = TypeUtil.getEqualsStrategy(collectionTypeId.getElementTypeId(), false);
			inputIterator = new ToBag(equalsStrategy, sourceValue, object);
		}
		return new LazyCollectionValueImpl(collectionTypeId, inputIterator, sourceValue);
	}

	protected final @NonNull CollectionValue sourceValue;
	protected final @Nullable Object object;
	protected final @NonNull LazyIterator sourceIterator;
	protected boolean doneInclude = false;

	public IncludingIterator(@NonNull CollectionValue sourceValue, @Nullable Object object) {
		this.sourceValue = sourceValue;
		this.sourceIterator = sourceValue.lazyIterator();
		this.object = object;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("Including{");
		sourceIterator.reIterator().toString(s, sizeLimit-6);
		s.append(",");
		s.append(object instanceof String ? "'" + object + "'" : object);
		s.append("}");
	}

	// The included value increments the coount of a pre-existing value else it goes at the end.
	private static class ToBag extends IncludingIterator
	{
		private final @NonNull EqualsStrategy equalsStrategy;

		public ToBag(@NonNull EqualsStrategy equalsStrategy, @NonNull CollectionValue sourceValue, @Nullable Object secondValue) {
			super(sourceValue, secondValue);
			this.equalsStrategy = equalsStrategy;
		}

		@Override
		public int getNextCount() {
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
		public @NonNull LazyIterator reIterator() {
			return new ToBag(equalsStrategy, sourceValue, object);
		}
	}

	// The included value goes at the end.
	private static class ToSequence extends IncludingIterator
	{
		public ToSequence(@NonNull CollectionValue sourceValue, @Nullable Object secondValue) {
			super(sourceValue, secondValue);
		}

		@Override
		public int getNextCount() {
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
		public @NonNull LazyIterator reIterator() {
			return new ToSequence(sourceValue, object);
		}
	}

	// The included value goes at the end unless there is already a previous value.
	private static class ToUnique extends IncludingIterator
	{
		private final @NonNull EqualsStrategy equalsStrategy;

		public ToUnique(@NonNull EqualsStrategy equalsStrategy, @NonNull CollectionValue sourceValue, @Nullable Object secondValue) {
			super(sourceValue, secondValue);
			this.equalsStrategy = equalsStrategy;
		}

		@Override
		public int getNextCount() {
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
		public @NonNull LazyIterator reIterator() {
			return new ToUnique(equalsStrategy, sourceValue, object);
		}
	}
}
