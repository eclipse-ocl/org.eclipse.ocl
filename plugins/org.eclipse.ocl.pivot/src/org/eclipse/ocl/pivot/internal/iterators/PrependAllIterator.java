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
 * PrependAllIterator provides a lazy evaluation of the OrderedCollection::prependAll operation.
 *
 * @since 1.3
 */
public abstract class PrependAllIterator extends LazyCollectionValueImpl
{
	public static @NonNull CollectionValue prependAll(@NonNull CollectionValue sourceValue, @NonNull CollectionValue prependValue) {
		if (sourceValue.isUnique()) {
			if (!prependValue.isUnique()) {
				prependValue = prependValue.asUniqueCollectionValue();
			}
			return new ToUnique(sourceValue, prependValue);
		}
		else if (sourceValue.isOrdered()) {
			return new ToSequence(sourceValue, prependValue);
		}
		else {
			return new ToBag(sourceValue, prependValue);
		}
	}

	protected final @NonNull CollectionValue sourceValue;
	protected final @NonNull CollectionValue prependValue;
	protected final @NonNull BaggableIterator<@Nullable Object> sourceIterator;
	protected final @NonNull BaggableIterator<@Nullable Object> prependIterator;

	public PrependAllIterator(@NonNull CollectionValue sourceValue, @NonNull CollectionValue prependValue) {
		super(sourceValue.getTypeId());
		this.sourceValue = sourceValue;
		this.prependValue = prependValue;
		this.sourceIterator = baggableIterator(sourceValue);
		this.prependIterator = baggableIterator(prependValue);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("PrependAll{");
		s.append(sourceIterator);
		s.append(",");
		s.append(prependIterator);
		s.append("}");
	}

	// The prepended value goes at the beginning.
	private static class ToBag extends PrependAllIterator
	{
		private final @NonNull CollectionValue sourceValue;		// FIXME Use MapOfElement2ElementCount
		private final @NonNull CollectionValue prependValue;		// FIXME Use MapOfElement2ElementCount

		public ToBag(@NonNull CollectionValue sourceValue, @NonNull CollectionValue prependValue) {
			super(sourceValue, prependValue);
			this.sourceValue = sourceValue;
			this.prependValue = prependValue;
		}

		@Override
		protected int getNextCount() {
			int prependCount = prependIterator.hasNextCount();
			if (prependCount > 0) {
				Object next = prependIterator.next();
				int sourceCount = sourceValue.count(next).intValue();
				return setNext(next, prependCount + sourceCount);
			}
			for (int sourceCount; (sourceCount = sourceIterator.hasNextCount()) > 0; ) {
				Object next = sourceIterator.next();
				prependCount = prependValue.count(next).intValue();
				if (prependCount <= 0) {
					return setNext(next, sourceCount);
				}
			}
			return 0;
		}

		@Override
		protected @NonNull LazyCollectionValue reIterator() {
			return new ToBag(sourceValue, prependValue);
		}
	}

	// The prepended value goes at the beginning.
	private static class ToSequence extends PrependAllIterator
	{
		public ToSequence(@NonNull CollectionValue sourceValue, @NonNull CollectionValue prependValue) {
			super(sourceValue, prependValue);
		}

		@Override
		protected int getNextCount() {
			int nextCount = prependIterator.hasNextCount();
			if (nextCount > 0) {
				return setNext(prependIterator.next(), nextCount);
			}
			nextCount = sourceIterator.hasNextCount();
			if (nextCount > 0) {
				return setNext(sourceIterator.next(), nextCount);
			}
			return 0;
		}

		@Override
		protected @NonNull LazyCollectionValue reIterator() {
			return new ToSequence(sourceValue, prependValue);
		}
	}

	// The prepended value goes at the beginning.
	private static class ToUnique extends PrependAllIterator
	{
		private final @NonNull CollectionValue prependValue;		// FIXME Use MapOfElement2ElementCount

		public ToUnique(@NonNull CollectionValue sourceValue, @NonNull CollectionValue prependValue) {
			super(sourceValue, eagerCollectionValue(prependValue));		// Multiple accesses occur
			this.prependValue = prependValue;
		}

		@Override
		protected int getNextCount() {
			int nextCount = prependIterator.hasNextCount();
			if (nextCount > 0) {
				return setNext(prependIterator.next(), nextCount);
			}
			while ((nextCount = sourceIterator.hasNextCount()) > 0) {
				assert nextCount == 1;
				Object next = sourceIterator.next();
				if (!prependValue.includes(next)) {
					return setNext(next, 1);
				}
			}
			return 0;
		}

		@Override
		protected @NonNull LazyCollectionValue reIterator() {
			return new ToUnique(sourceValue, prependValue);
		}
	}
}
