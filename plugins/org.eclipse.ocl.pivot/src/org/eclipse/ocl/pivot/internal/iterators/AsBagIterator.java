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
import org.eclipse.ocl.pivot.internal.values.LazyIterable;
import org.eclipse.ocl.pivot.values.BagValue;
import org.eclipse.ocl.pivot.values.CollectionValue;

/**
 * BagIterator provides a lazy evaluation of the Collection::asBag operation.
 *
 * @since 1.3
 */
public class AsBagIterator extends AbstractBagIterator implements BagValue
{
	private final @NonNull Iterator<? extends Object> iterator;
	private Object next;

	public AsBagIterator(@NonNull CollectionValue collectionValue) {
		this(TypeId.BAG.getSpecializedId(collectionValue.getElementTypeId()), collectionValue.iterator(), collectionValue.isUnique() || !collectionValue.isOrdered());
	}

	public AsBagIterator(@NonNull CollectionTypeId typeId, @NonNull Iterator<? extends Object> iterator, boolean sourceIteratorIsBagLike) {
		super(typeId);
		this.iterator = iterator;
		assert !isOrdered();
		assert !isUnique();
		if (!sourceIteratorIsBagLike) {
			getMapOfElement2elementCount();
		}
	}

	@Override
	protected Object getNext() {
		return next;
	}

	@Override
	protected int getNextCount() {
		if (iterator.hasNext()) {
			next = iterator.next();
			return 1;
		}
		return 0;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		s.append("AsBag{");
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
