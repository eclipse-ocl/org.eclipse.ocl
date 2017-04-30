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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.SetValue;
import org.eclipse.ocl.pivot.values.UniqueCollectionValue;

/**
 * AsSetIterator provides a BaggableIterator that behaves as a SetValue for an arbitrary iterator.
 *
 * @since 1.3
 */
public class AsSetIterator extends AbstractBaggableIterator implements SetValue
{
	private final @NonNull Iterator<? extends Object> sourceIterator;

	public AsSetIterator(CollectionValue.@NonNull Extension sourceValue) {
		this(TypeId.SET.getSpecializedId(sourceValue.getElementTypeId()), sourceValue.iterator(), sourceValue.isUnique());
	}

	public AsSetIterator(@NonNull CollectionTypeId typeId, @NonNull Iterator<? extends Object> sourceIterator, boolean sourceIteratorIsUnique) {
		super(typeId);
		this.sourceIterator = sourceIterator;
		assert !isOrdered();
		assert isUnique();
		if (!sourceIteratorIsUnique) {
			getMapOfElement2elementCount();
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
	public @NonNull UniqueCollectionValue minus(@NonNull UniqueCollectionValue that) {
		return super.minus(that).asUniqueCollectionValue();
	}

	@Override
	@Deprecated
	public @NonNull UniqueCollectionValue symmetricDifference(@NonNull UniqueCollectionValue that) {
		return super.symmetricDifference(that).asUniqueCollectionValue();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("AsSet{");
		LazyIterable<@Nullable Object> iterable = basicGetIterable();
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
}
