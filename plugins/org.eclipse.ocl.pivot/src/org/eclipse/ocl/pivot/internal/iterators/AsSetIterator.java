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
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.values.LazyCollectionValueImpl;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.LazyIterator;

import com.google.common.collect.Iterators;

/**
 * AsSetIterator provides a BaggableIterator that behaves as a SetValue for an arbitrary iterator.
 *
 * @since 1.3
 */
public abstract class AsSetIterator extends LazyCollectionValueImpl implements CollectionValue
{
	private final @NonNull Iterator<? extends Object> sourceIterator;

	protected AsSetIterator(@NonNull CollectionTypeId typeId, @NonNull Iterator<? extends Object> sourceIterator, boolean sourceIteratorIsUnique) {
		super(typeId, lazyDepth(sourceIterator));
		this.sourceIterator = sourceIterator;
		assert !isOrdered();
		assert isUnique();
		if (!sourceIteratorIsUnique) {
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
	@Deprecated
	public @NonNull CollectionValue minus(@NonNull CollectionValue that) {
		return super.minus(that).asUniqueCollectionValue();
	}

	@Override
	@Deprecated
	public @NonNull CollectionValue symmetricDifference(@NonNull CollectionValue that) {
		return super.symmetricDifference(that).asUniqueCollectionValue();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("AsSet{");
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

	public static class FromArray extends AsSetIterator
	{
		private @Nullable Object @NonNull [] boxedValues;

		public FromArray(@NonNull CollectionTypeId typeId, @Nullable Object @NonNull [] boxedValues) {
			super(typeId, Iterators.forArray(boxedValues), false);
			this.boxedValues = boxedValues;
		}

		@Override
		protected @NonNull LazyIterator reIterator() {
			return new FromArray(typeId, boxedValues);
		}
	}

	public static class FromCollection extends AsSetIterator
	{
		private @NonNull Collection<@Nullable ? extends Object> boxedValues;

		public FromCollection(@NonNull CollectionTypeId typeId, @NonNull Collection<@Nullable ? extends Object> boxedValues) {
			super(typeId, boxedValues.iterator(), boxedValues instanceof Set);
			this.boxedValues = boxedValues;
		}

		@Override
		protected @NonNull LazyIterator reIterator() {
			return new FromCollection(typeId, boxedValues);
		}
	}

	public static class FromCollectionValue extends AsSetIterator
	{
		private @NonNull CollectionValue sourceValue;

		public FromCollectionValue(@NonNull CollectionValue sourceValue) {
			super(TypeId.SET.getSpecializedId(sourceValue.getTypeId().getElementTypeId()), sourceValue.lazyIterator(), sourceValue.isUnique());
			this.sourceValue = sourceValue;
		}

		@Override
		protected @NonNull LazyIterator reIterator() {
			return new FromCollectionValue(sourceValue);
		}
	}
}
