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
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.SequenceValue;

import com.google.common.collect.Iterators;

/**
 * AsSequenceIterator provides a lazy evaluation of the Collection::asSequence operation.
 *
 * @since 1.3
 */
public abstract class AsSequenceIterator extends LazyCollectionValueImpl implements SequenceValue
{
	private final @NonNull Iterator<? extends Object> sourceIterator;

	protected AsSequenceIterator(@NonNull CollectionTypeId typeId, @NonNull Iterator<? extends Object> sourceIterator) {
		super(typeId, lazyDepth(sourceIterator));
		this.sourceIterator = sourceIterator;
		assert isOrdered();
		assert !isUnique();
	}

	@Override
	@Deprecated
	public @NonNull CollectionValue append(@Nullable Object object) {
		return super.append(object).asOrderedCollectionValue();
	}

	@Override
	@Deprecated
	public @NonNull CollectionValue appendAll(@NonNull CollectionValue that) {
		return super.appendAll(that).asOrderedCollectionValue();
	}

	@Override
	protected int getNextCount() {
		if (sourceIterator.hasNext()) {
			return setNext(sourceIterator.next(), 1);
		}
		return 0;
	}

	@Override
	@Deprecated
	public @NonNull CollectionValue prepend(@Nullable Object object) {
		return super.prepend(object).asOrderedCollectionValue();
	}

	@Override
	@Deprecated
	public @NonNull CollectionValue prependAll(@NonNull CollectionValue that) {
		return super.prependAll(that).asOrderedCollectionValue();
	}

	@Override
	@Deprecated
	public @NonNull SequenceValue subSequence(int lower, int upper) {
		return super.subSequence(lower, upper).asSequenceValue();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("AsSequence{");
		LazyIterable iterable = basicGetIterable();
		if (iterable != null) {
			s.append(iterable);
			if (hasNext()) {
				s.append(";«future»");
			}
		}
		else {
			s.append("«future»");
		}
		s.append("}");
	}

	public static class FromArray extends AsSequenceIterator
	{
		private @Nullable Object @NonNull [] boxedValues;

		public FromArray(@NonNull CollectionTypeId typeId, @Nullable Object @NonNull [] boxedValues) {
			super(typeId, Iterators.forArray(boxedValues));
			this.boxedValues = boxedValues;
		}

		@Override
		protected @NonNull Iterator<@Nullable Object> reIterator() {
			return new FromArray(typeId, boxedValues);
		}
	}

	public static class FromCollection extends AsSequenceIterator
	{
		private @NonNull Collection<@Nullable ? extends Object> boxedValues;

		public FromCollection(@NonNull CollectionTypeId typeId, @NonNull Collection<@Nullable ? extends Object> boxedValues) {
			super(typeId, boxedValues.iterator());
			this.boxedValues = boxedValues;
		}

		@Override
		protected @NonNull Iterator<@Nullable Object> reIterator() {
			return new FromCollection(typeId, boxedValues);
		}
	}

	public static class FromCollectionValue extends AsSequenceIterator
	{
		private @NonNull CollectionValue sourceValue;

		public FromCollectionValue(@NonNull CollectionValue sourceValue) {
			super(TypeId.SEQUENCE.getSpecializedId(sourceValue.getTypeId().getElementTypeId()), lazyIterator(sourceValue));
			this.sourceValue = sourceValue;
		}

		@Override
		protected @NonNull Iterator<@Nullable Object> reIterator() {
			return new FromCollectionValue(sourceValue);
		}
	}
}
