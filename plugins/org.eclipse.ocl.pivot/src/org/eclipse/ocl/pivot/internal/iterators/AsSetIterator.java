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
import org.eclipse.ocl.pivot.internal.values.SmartCollectionValueImpl;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.LazyIterator;

/**
 * AsSetIterator provides a BaggableIterator that behaves as a SetValue for an arbitrary iterator.
 *
 * @since 1.3
 */
public abstract class AsSetIterator extends AbstractLazyIterator
{
	private final @NonNull Iterator<? extends Object> sourceIterator;

	protected AsSetIterator(@NonNull Iterator<? extends Object> sourceIterator) {
		this.sourceIterator = sourceIterator;
	}

	@Override
	public int getNextCount() {
		if (sourceIterator.hasNext()) {
			return setNext(sourceIterator.next(), 1);
		}
		return 0;
	}

	/*	@Override
	@Deprecated
	public @NonNull CollectionValue minus(@NonNull CollectionValue that) {
		return super.minus(that).asUniqueCollectionValue();
	}

	@Override
	@Deprecated
	public @NonNull CollectionValue symmetricDifference(@NonNull CollectionValue that) {
		return super.symmetricDifference(that).asUniqueCollectionValue();
	} */

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("AsSet{");
		//		if (hasCache()) {
		//			appendIterable(s);
		//			if (hasNext()) {
		//				s.append(";«future»");
		//			}
		//		}
		//		else {
		s.append("«future»");
		//		}
		s.append("}");
	}

	public static class FromArray extends AbstractLazyIterator
	{
		public static @NonNull CollectionValue create(@NonNull CollectionTypeId collectionTypeId, @Nullable Object @NonNull [] boxedValues) {
			SmartCollectionValueImpl collectionValue = new SmartCollectionValueImpl(collectionTypeId, new FromArray(boxedValues));
			if (collectionValue.isSequence()) {
				collectionValue.cachedIterable();
			}
			else {
				collectionValue.eagerIterable();
			}
			return collectionValue;
		}

		private @Nullable Object @NonNull [] boxedValues;
		private int nextIndex = 0;;

		protected FromArray(@Nullable Object @NonNull [] boxedValues) {
			this.boxedValues = boxedValues;
		}

		@Override
		public @NonNull LazyIterator reIterator() {
			return new FromArray(boxedValues);
		}

		@Override
		public int getNextCount() {
			if (nextIndex < boxedValues.length) {
				return setNext(boxedValues[nextIndex++], 1);
			}
			return 0;
		}

		@Override
		public void toString(@NonNull StringBuilder s, int sizeLimit) {
			s.append("AsSet{");
			//		if (hasCache()) {
			//			appendIterable(s);
			//			if (hasNext()) {
			//				s.append(";«future»");
			//			}
			//		}
			//		else {
			s.append("«future»");
			//		}
			s.append("}");
		}
	}

	public static class FromCollection extends AsSetIterator
	{
		public static @NonNull CollectionValue create(@NonNull CollectionTypeId collectionTypeId, @NonNull Collection<@Nullable ? extends Object> boxedValues) {
			assert !(boxedValues instanceof CollectionValue);
			SmartCollectionValueImpl collectionValue = new SmartCollectionValueImpl(collectionTypeId, new FromCollection(boxedValues));
			if (collectionValue.isSequence()) {
				collectionValue.cachedIterable();
			}
			else {
				collectionValue.eagerIterable();
			}
			return collectionValue;
		}

		private @NonNull Collection<@Nullable ? extends Object> boxedValues;

		protected FromCollection(@NonNull Collection<@Nullable ? extends Object> boxedValues) {
			super(boxedValues.iterator());
			this.boxedValues = boxedValues;
		}

		@Override
		public @NonNull LazyIterator reIterator() {
			return new FromCollection(boxedValues);
		}
	}

	public static class FromCollectionValue extends AsSetIterator
	{
		public static @NonNull CollectionValue create(@NonNull CollectionValue sourceValue) {
			CollectionTypeId collectionTypeId = TypeId.SET.getSpecializedId(sourceValue.getTypeId().getElementTypeId());
			SmartCollectionValueImpl collectionValue = new SmartCollectionValueImpl(collectionTypeId, new FromCollectionValue(sourceValue));
			if (collectionValue.isSequence()) {
				collectionValue.cachedIterable();
			}
			else {
				collectionValue.eagerIterable();
			}
			return collectionValue;
		}

		private @NonNull CollectionValue sourceValue;

		protected FromCollectionValue(@NonNull CollectionValue sourceValue) {
			super(sourceValue.lazyIterator());
			this.sourceValue = sourceValue;
		}

		@Override
		public @NonNull LazyIterator reIterator() {
			return new FromCollectionValue(sourceValue);
		}
	}
}
