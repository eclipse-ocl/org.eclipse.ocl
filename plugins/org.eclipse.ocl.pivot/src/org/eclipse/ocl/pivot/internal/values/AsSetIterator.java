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
package org.eclipse.ocl.pivot.internal.values;

import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.SetValue;

/**
 * SetIterator provides a lazy evaluation of the Collection::asSet operation.
 *
 * @since 1.3
 */
public class AsSetIterator extends AbstractCollectionIterator implements SetValue
{
	private final @NonNull Iterator<? extends Object> iterator;
	private boolean canBeIterable = true;

	public AsSetIterator(@NonNull CollectionValue firstValue) {
		this(TypeId.SET.getSpecializedId(firstValue.getElementTypeId()), firstValue.iterator());
	}

	public AsSetIterator(@NonNull CollectionTypeId typeId, @NonNull Iterator<? extends Object> iterator) {
		super(typeId);
		this.iterator = iterator;
		assert !isOrdered();
		assert isUnique();
	}

	@Override
	protected boolean canBeIterable() {
		return canBeIterable;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public @Nullable Object next() {
		canBeIterable = false;
		return iterator.next();
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
