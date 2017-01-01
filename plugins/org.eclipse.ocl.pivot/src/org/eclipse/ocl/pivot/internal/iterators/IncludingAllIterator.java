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
import org.eclipse.ocl.pivot.values.CollectionValue;

/**
 * IncludingAllIterator provides a lazy evaluation of the Collection::includingAll operation.
 *
 * @since 1.3
 */
public abstract class IncludingAllIterator extends AbstractBagIterator
{
	public static @NonNull CollectionValue includingAll(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue sourceValue, @NonNull CollectionValue includeValue) {
		if (sourceValue.isUnique()) {
			if (!includeValue.isUnique()) {
				includeValue = includeValue.asUniqueCollectionValue();
			}
			includeValue.iterable();
			return new ToUnique(collectionTypeId, sourceValue, includeValue);
		}
		else if (sourceValue.isOrdered()) {
			return new ToSequence(collectionTypeId, sourceValue, includeValue);
		}
		else {
			return new ToBag(collectionTypeId, sourceValue, includeValue);
		}
	}

	protected final @NonNull BagIterator<@Nullable Object> sourceIterator;
	protected final @NonNull BagIterator<@Nullable Object> includeIterator;

	public IncludingAllIterator(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue sourceValue, @NonNull CollectionValue includeValue) {
		super(collectionTypeId);
		this.sourceIterator = sourceValue.iterator();
		this.includeIterator = includeValue.iterator();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("IncludingAll{");
		s.append(sourceIterator);
		s.append(", ");
		s.append(includeIterator);
		s.append("}");
	}

	// The included values increment existing cpunys, otherwise they go at the end.
	private static class ToBag extends IncludingAllIterator
	{
		private final @NonNull CollectionValue sourceValue;		// FIXME Use MapOfElement2ElementCount
		private final @NonNull CollectionValue includeValue;		// FIXME Use MapOfElement2ElementCount

		public ToBag(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue sourceValue, @NonNull CollectionValue includeValue) {
			super(collectionTypeId, sourceValue, includeValue);
			this.sourceValue = sourceValue;
			this.includeValue = includeValue;
		}

		@Override
		protected int getNextCount() {
			int sourceCount = sourceIterator.hasNextCount();
			if (sourceCount > 0) {
				Object next = sourceIterator.next();
				int includeCount = includeValue.count(next).intValue();
				setNext(next);
				return sourceCount + includeCount;
			}
			for (int includeCount; (includeCount = includeIterator.hasNextCount()) > 0; ) {
				Object next = includeIterator.next();
				sourceCount = sourceValue.count(next).intValue();
				if (sourceCount <= 0) {
					setNext(next);
					return includeCount;
				}
			}
			return 0;
		}
	}

	// The includeed values go at the end.
	private static class ToSequence extends IncludingAllIterator
	{
		public ToSequence(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue sourceValue, @NonNull CollectionValue includeValue) {
			super(collectionTypeId, sourceValue, includeValue);
		}

		@Override
		protected int getNextCount() {
			boolean hasNext = sourceIterator.hasNext();
			if (hasNext) {
				setNext(sourceIterator.next());
				return 1;
			}
			hasNext = includeIterator.hasNext();
			if (hasNext) {
				setNext(includeIterator.next());
				return 1;
			}
			return 0;
		}
	}

	// The included values goes at the end unless there are already previous values.
	private static class ToUnique extends IncludingAllIterator
	{
		private final @NonNull CollectionValue sourceValue;		// FIXME Use MapOfElement2ElementCount

		public ToUnique(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue sourceValue, @NonNull CollectionValue includeValue) {
			super(collectionTypeId, sourceValue, includeValue);
			this.sourceValue = sourceValue;

		}

		@Override
		protected int getNextCount() {
			while (sourceIterator.hasNextCount() > 0) {
				setNext(sourceIterator.next());
				return 1;
			}
			if (includeIterator.hasNextCount() > 0) {
				Object next = includeIterator.next();
				if (!sourceValue.includes(next)) {
					setNext(next);
					return 1;
				}
			}
			return 0;
		}
	}
}
