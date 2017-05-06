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

/**
 * AppendAllIterator provides a lazy evaluation of the Collection::appendAll operation.
 *
 * @since 1.3
 */
public abstract class AppendAllIterator extends AbstractBaggableIterator
{
	public static @NonNull CollectionValue appendAll(@NonNull CollectionValue sourceValue, @NonNull CollectionValue appendedValue) {
		if (sourceValue.isUnique()) {
			if (!appendedValue.isUnique()) {
				appendedValue = appendedValue.asUniqueCollectionValue();
			}
			appendedValue.iterable();
			return new ToUnique(sourceValue, appendedValue);
		}
		else if (sourceValue.isOrdered()) {
			return new ToSequence(sourceValue, appendedValue);
		}
		else {
			return new ToBag(sourceValue, appendedValue);
		}
	}

	protected final @NonNull BaggableIterator<@Nullable Object> sourceIterator;
	protected final @NonNull BaggableIterator<@Nullable Object> appendIterator;

	protected AppendAllIterator(@NonNull CollectionValue sourceValue, @NonNull CollectionValue appendedValue) {
		super(sourceValue.getTypeId());
		this.sourceIterator = baggableIterator(sourceValue);
		this.appendIterator = baggableIterator(appendedValue);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("AppendAll{");
		s.append(sourceIterator);
		s.append(",");
		s.append(appendIterator);
		s.append("}");
	}

	// The appended values go at the end accruing any counts from pre-existing values.
	private static class ToBag extends AppendAllIterator
	{
		private final @NonNull CollectionValue sourceValue;		// FIXME Use MapOfElement2ElementCount
		private final @NonNull CollectionValue appendedValue;		// FIXME Use MapOfElement2ElementCount

		public ToBag(@NonNull CollectionValue sourceValue, @NonNull CollectionValue appendedValue) {
			super(sourceValue, appendedValue);
			this.sourceValue = sourceValue;
			this.appendedValue = appendedValue;
		}

		@Override
		protected int getNextCount() {
			for (int nextCount; (nextCount = sourceIterator.hasNextCount()) > 0; ) {
				Object next = sourceIterator.next();
				if (!appendedValue.includes(next)) {
					return setNext(next, nextCount);
				}
			}
			int nextCount = appendIterator.hasNextCount();
			if (nextCount > 0) {
				Object next = appendIterator.next();
				nextCount += sourceValue.count(next).intValue();
				return setNext(next, nextCount);
			}
			return 0;
		}
	}

	// The appended values go at the end.
	private static class ToSequence extends AppendAllIterator
	{
		public ToSequence(@NonNull CollectionValue sourceValue, @NonNull CollectionValue appendedValue) {
			super(sourceValue, appendedValue);
		}

		@Override
		protected int getNextCount() {
			boolean hasNext = sourceIterator.hasNext();
			if (hasNext) {
				return setNext(sourceIterator.next(), 1);
			}
			hasNext = appendIterator.hasNext();
			if (hasNext) {
				return setNext(appendIterator.next(), 1);
			}
			return 0;
		}
	}

	// The appended values go at the end displacing any previous values.
	private static class ToUnique extends AppendAllIterator
	{
		private final @NonNull CollectionValue appendedValue;		// FIXME Use MapOfElement2ElementCount

		public ToUnique(@NonNull CollectionValue sourceValue, @NonNull CollectionValue appendedValue) {
			super(sourceValue, appendedValue);
			this.appendedValue = appendedValue;
		}

		@Override
		protected int getNextCount() {
			while (sourceIterator.hasNextCount() > 0) {
				Object next = sourceIterator.next();
				if (!appendedValue.includes(next)) {
					return setNext(next, 1);
				}
			}
			if (appendIterator.hasNextCount() > 0) {
				Object next = appendIterator.next();
				return setNext(next, 1);
			}
			return 0;
		}
	}
}
