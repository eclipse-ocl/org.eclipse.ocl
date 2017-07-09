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
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.internal.values.SmartCollectionValueImpl;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.LazyIterator;

/**
 * IncludingAllIterator provides a lazy evaluation of the Collection::includingAll operation.
 *
 * @since 1.3
 */
public abstract class IncludingAllIterator extends AbstractLazyIterator
{
	public static @NonNull CollectionValue includingAll(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue sourceValue, @NonNull CollectionValue includeValue) {
		LazyIterator inputIterator;
		if (sourceValue.isUnique()) {
			if (!includeValue.isUnique()) {
				includeValue = includeValue.asUniqueCollectionValue();
			}
			inputIterator = new ToUnique(sourceValue, includeValue);
		}
		else if (sourceValue.isOrdered()) {
			inputIterator = new ToSequence(sourceValue, includeValue);
		}
		else {
			inputIterator = new ToBag(sourceValue, includeValue);
		}
		return new SmartCollectionValueImpl(collectionTypeId, inputIterator, sourceValue);
	}

	public static @NonNull CollectionValue union(@NonNull CollectionValue sourceValue, @NonNull CollectionValue unionValue) {
		LazyIterator inputIterator;
		CollectionTypeId collectionTypeId;
		if (sourceValue.isUnique() && unionValue.isUnique()) {
			inputIterator = new ToUnique(sourceValue, unionValue);
			collectionTypeId = TypeUtil.getSetTypeId(sourceValue.getTypeId());
		}
		else {
			inputIterator = new ToBag(sourceValue, unionValue);
			collectionTypeId = TypeUtil.getBagTypeId(sourceValue.getTypeId());
		}
		return new SmartCollectionValueImpl(collectionTypeId, inputIterator, sourceValue);
	}

	protected final @NonNull CollectionValue sourceValue;
	protected final @NonNull CollectionValue includeValue;
	protected final @NonNull LazyIterator sourceIterator;
	protected final @NonNull LazyIterator includeIterator;

	public IncludingAllIterator(@NonNull CollectionValue sourceValue, @NonNull CollectionValue includeValue) {
		this.sourceValue = sourceValue;
		this.includeValue = includeValue;
		this.sourceIterator = sourceValue.lazyIterator();
		this.includeIterator = includeValue.lazyIterator();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("IncludingAll{");
		s.append(sourceIterator);
		s.append(", ");
		s.append(includeIterator);
		s.append("}");
	}

	// The included values increment existing counts, otherwise they go at the end.
	private static class ToBag extends IncludingAllIterator
	{
		private final @NonNull CollectionValue sourceValue;		// FIXME Use MapOfElement2ElementCount
		private final @NonNull CollectionValue includeValue;		// FIXME Use MapOfElement2ElementCount

		public ToBag(@NonNull CollectionValue sourceValue, @NonNull CollectionValue includeValue) {
			super(ValueUtil.eagerCollectionValue(sourceValue), ValueUtil.eagerCollectionValue(includeValue));
			this.sourceValue = sourceValue;
			this.includeValue = includeValue;
		}

		@Override
		public int getNextCount() {
			int sourceCount = sourceIterator.hasNextCount();
			if (sourceCount > 0) {
				Object next = sourceIterator.next();
				int includeCount = includeValue.count(next).intValue();
				return setNext(next, sourceCount + includeCount);
			}
			for (int includeCount; (includeCount = includeIterator.hasNextCount()) > 0; ) {
				Object next = includeIterator.next();
				sourceCount = sourceValue.count(next).intValue();
				if (sourceCount <= 0) {
					return setNext(next, includeCount);
				}
			}
			return 0;
		}

		@Override
		public @NonNull LazyIterator reIterator() {
			return new ToBag(sourceValue, includeValue);
		}
	}

	// The included values go at the end.
	private static class ToSequence extends IncludingAllIterator
	{
		public ToSequence(@NonNull CollectionValue sourceValue, @NonNull CollectionValue includeValue) {
			super(sourceValue, includeValue);
		}

		@Override
		public int getNextCount() {
			boolean hasNext = sourceIterator.hasNext();
			if (hasNext) {
				return setNext(sourceIterator.next(), 1);
			}
			hasNext = includeIterator.hasNext();
			if (hasNext) {
				return setNext(includeIterator.next(), 1);
			}
			return 0;
		}

		@Override
		public @NonNull LazyIterator reIterator() {
			return new ToSequence(sourceValue, includeValue);
		}
	}

	// The included values goes at the end unless there are already previous values.
	private static class ToUnique extends IncludingAllIterator
	{
		private final @NonNull CollectionValue sourceValue;		// FIXME Use MapOfElement2ElementCount

		public ToUnique(@NonNull CollectionValue sourceValue, @NonNull CollectionValue includeValue) {
			super(sourceValue, ValueUtil.eagerCollectionValue(includeValue));
			this.sourceValue = sourceValue;

		}

		@Override
		public int getNextCount() {
			while (sourceIterator.hasNextCount() > 0) {
				return setNext(sourceIterator.next(), 1);
			}
			while (includeIterator.hasNextCount() > 0) {
				Object next = includeIterator.next();
				if (!sourceValue.includes(next)) {
					return setNext(next, 1);
				}
			}
			return 0;
		}

		@Override
		public @NonNull LazyIterator reIterator() {
			return new ToUnique(sourceValue, includeValue);
		}
	}
}
