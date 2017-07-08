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

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.values.LazyCollectionValueImpl;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.LazyIterator;

import com.google.common.collect.Iterators;

/**
 * AsBagIterator provides a BaggableIterator that behaves as a BagValue for an arbitrary iterator.
 *
 * @since 1.3
 */
public abstract class AsBagIterator extends LazyCollectionValueImpl
{
	private final @NonNull Iterator<? extends Object> sourceIterator;

	protected AsBagIterator(@NonNull CollectionTypeId typeId, @NonNull Iterator<? extends Object> sourceIterator, boolean sourceIteratorIsBagLike) {
		super(typeId, lazyDepth(sourceIterator));
		this.sourceIterator = sourceIterator;
		assert !isOrdered();
		assert !isUnique();
		if (!sourceIteratorIsBagLike) {
			eagerIterable();
		}
	}

	@Override
	protected int getNextCount() {
		if (sourceIterator.hasNext()) {
			return setNext(sourceIterator.next(), 1);
		}
		return 0;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("AsBag{");
		if (hasCache()) {
			appendIterable(s);
			if (hasNext()) {
				s.append(";«future»");
			}
		}
		else {
			s.append("«future»");
		}
		s.append("}");
	}

	public static class FromArray extends AsBagIterator
	{
		public static @NonNull CollectionValue create(@NonNull CollectionTypeId collectionTypeId, @Nullable Object @NonNull [] boxedValues) {
			return new FromArray(collectionTypeId, boxedValues);
		}

		private @Nullable Object @NonNull [] boxedValues;

		protected FromArray(@NonNull CollectionTypeId collectionTypeId, @Nullable Object @NonNull [] boxedValues) {
			super(collectionTypeId, Iterators.forArray(boxedValues), false);
			this.boxedValues = boxedValues;
		}

		@Override
		protected @NonNull LazyIterator reIterator() {
			return new FromArray(typeId, boxedValues);
		}
	}

	public static class FromCollection extends AsBagIterator
	{
		public static @NonNull CollectionValue create(@NonNull CollectionTypeId collectionTypeId, @NonNull Collection<@Nullable ? extends Object> boxedValues) {
			return new FromCollection(collectionTypeId, boxedValues);
		}

		private @NonNull Collection<@Nullable ? extends Object> boxedValues;

		protected FromCollection(@NonNull CollectionTypeId typeId, @NonNull Collection<@Nullable ? extends Object> boxedValues) {
			super(typeId, boxedValues.iterator(), false);
			this.boxedValues = boxedValues;
		}

		@Override
		protected @NonNull LazyIterator reIterator() {
			return new FromCollection(typeId, boxedValues);
		}
	}

	public static class FromCollectionValue extends AsBagIterator
	{
		public static @NonNull CollectionValue create(@NonNull CollectionValue sourceValue) {
			CollectionTypeId collectionTypeId = TypeId.BAG.getSpecializedId(sourceValue.getTypeId().getElementTypeId());
			return new FromCollectionValue(collectionTypeId, sourceValue);
		}

		private @NonNull CollectionValue sourceValue;

		protected FromCollectionValue(@NonNull CollectionTypeId collectionTypeId, @NonNull CollectionValue sourceValue) {
			super(collectionTypeId, sourceValue.lazyIterator(), sourceValue.isUnique() || !sourceValue.isOrdered());
			this.sourceValue = sourceValue;
		}

		@Override
		protected @NonNull LazyIterator reIterator() {
			return new FromCollectionValue(typeId, sourceValue);
		}
	}
}
