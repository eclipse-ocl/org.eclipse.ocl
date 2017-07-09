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

import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.values.SmartCollectionValueImpl;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.LazyIterator;

/**
 * AsBagIterator provides a BaggableIterator that behaves as a BagValue for an arbitrary iterator.
 *
 * @since 1.3
 */
public abstract class AsBagIterator extends AbstractLazyIterator
{
	private final @NonNull Iterator<? extends Object> sourceIterator;

	protected AsBagIterator(@NonNull Iterator<? extends Object> sourceIterator) {
		this.sourceIterator = sourceIterator;
	}

	@Override
	public int getNextCount() {
		if (sourceIterator.hasNext()) {
			return setNext(sourceIterator.next(), 1);
		}
		return 0;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("AsBag{");
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

	public static class FromCollectionValue extends AsBagIterator
	{
		public static @NonNull CollectionValue create(@NonNull CollectionValue sourceValue) {
			CollectionTypeId collectionTypeId = TypeId.BAG.getSpecializedId(sourceValue.getTypeId().getElementTypeId());
			SmartCollectionValueImpl collectionValue = new SmartCollectionValueImpl(collectionTypeId, new FromCollectionValue(sourceValue));
			if (!collectionValue.isSequence()) {
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
