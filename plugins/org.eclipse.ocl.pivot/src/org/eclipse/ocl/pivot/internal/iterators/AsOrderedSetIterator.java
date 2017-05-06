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
import org.eclipse.ocl.pivot.values.OrderedCollectionValue;
import org.eclipse.ocl.pivot.values.OrderedSetValue;
import org.eclipse.ocl.pivot.values.UniqueCollectionValue;

/**
 * AsOrderedSetIterator provides a BaggableIterator that behaves as an OrderedSetValue for an arbitrary iterator.
 *
 * @since 1.3
 */
public class AsOrderedSetIterator extends LazyCollectionValueImpl implements OrderedSetValue
{
	private final @NonNull Iterator<? extends Object> sourceIterator;

	public AsOrderedSetIterator(@NonNull CollectionValue sourceValue) {
		this(TypeId.ORDERED_SET.getSpecializedId(sourceValue.getTypeId().getElementTypeId()), sourceValue.iterator(), sourceValue.isUnique());
	}

	public AsOrderedSetIterator(@NonNull CollectionTypeId typeId, @NonNull Iterator<? extends Object> sourceIterator, boolean sourceIteratorIsUnique) {
		super(typeId);
		this.sourceIterator = sourceIterator;
		assert isOrdered();
		assert isUnique();
		if (!sourceIteratorIsUnique) {
			getMapOfElement2elementCount();
		}
	}

	@Override
	@Deprecated
	public @NonNull OrderedCollectionValue append(@Nullable Object object) {
		return super.append(object).asOrderedCollectionValue();
	}

	@Override
	@Deprecated
	public @NonNull OrderedCollectionValue appendAll(@NonNull OrderedCollectionValue that) {
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
	public @NonNull UniqueCollectionValue minus(@NonNull UniqueCollectionValue that) {
		return super.minus(that).asUniqueCollectionValue();
	}

	@Override
	@Deprecated
	public @NonNull OrderedCollectionValue prepend(@Nullable Object object) {
		return super.prepend(object).asOrderedCollectionValue();
	}

	@Override
	@Deprecated
	public @NonNull OrderedCollectionValue prependAll(@NonNull OrderedCollectionValue that) {
		return super.prependAll(that).asOrderedCollectionValue();
	}

	@Override
	@Deprecated
	public @NonNull OrderedSetValue subOrderedSet(int lower, int upper) {
		return super.subOrderedSet(lower, upper).asOrderedSetValue();
	}

	@Override
	@Deprecated
	public @NonNull UniqueCollectionValue symmetricDifference(@NonNull UniqueCollectionValue that) {
		return super.symmetricDifference(that).asUniqueCollectionValue();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("AsOrderedSet{");
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
