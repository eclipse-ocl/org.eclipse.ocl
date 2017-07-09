/*******************************************************************************
 * Copyright (c) 2017 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.internal.values.LazyCollectionValueImpl;
import org.eclipse.ocl.pivot.internal.values.SmartCollectionValueImpl;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.LazyIterator;

/**
 * FromArrayIterator provides the loader for a CollectionValue from a Collection.
 */
public class FromCollectionIterator extends AbstractLazyIterator
{
	public static @NonNull CollectionValue create(@NonNull CollectionTypeId collectionTypeId, boolean uniqueElements, @NonNull Collection<@Nullable ? extends Object> elements) {
		SmartCollectionValueImpl collectionValue = new SmartCollectionValueImpl(collectionTypeId, new FromCollectionIterator(elements));
		if (!uniqueElements && !collectionValue.isSequence()) {
			collectionValue.eagerIterable();	// uniqueness/counts must be eager
		}
		return collectionValue;
	}

	private @NonNull Collection<@Nullable ? extends Object> elements;
	private @NonNull Iterator<@Nullable ? extends Object> iterator;

	protected FromCollectionIterator(@NonNull Collection<@Nullable ? extends Object> elements) {
		this.elements = elements;
		this.iterator = elements.iterator();
	}

	@Override
	public boolean isCached() {
		return true;
	}

	@Override
	public int getNextCount() {
		if (iterator.hasNext()) {
			return setNext(iterator.next(), 1);
		}
		return 0;
	}

	@Override
	public @NonNull LazyIterator reIterator() {
		return new FromCollectionIterator(elements);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		LazyCollectionValueImpl.appendIterable(s, elements, 50);
	}
}