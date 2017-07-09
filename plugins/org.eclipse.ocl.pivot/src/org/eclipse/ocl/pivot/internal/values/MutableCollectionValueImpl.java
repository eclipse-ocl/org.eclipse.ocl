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
package org.eclipse.ocl.pivot.internal.values;

import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.values.LazyIterator;

/**
 * A MutableCollectionValueImpl refines the inherited lazy protocols to impose eager construction so that
 * clients may perform mutable operations on the collection, provided of cpourse that the clients ensure
 * that the collection only has a single consumer post-mutation.
 */
public class MutableCollectionValueImpl extends LazyCollectionValueImpl
{
	protected final @Nullable Iterator<@Nullable Object> elements;

	public MutableCollectionValueImpl(@NonNull CollectionTypeId typeId) {
		super(typeId, null, 0);
		this.elements = null;
		mutableIterable();
	}

	public MutableCollectionValueImpl(@NonNull CollectionTypeId typeId, @NonNull Iterator<@Nullable Object> elements) {
		super(typeId, null, 0);
		this.elements = elements;
		mutableIterable();
		mutableIncludingAll(elements);
	}

	@Override
	protected int getNextCount() {
		//	assert size == 0;
		return 0;  // Occurs before add mutable additions
	}

	@Override
	public @NonNull LazyIterator reIterator() {
		Iterator<@Nullable Object> elements2 = elements;
		if (elements2 == null) {
			return new MutableCollectionValueImpl(typeId);
		}
		else {
			return new MutableCollectionValueImpl(typeId, elements2);
		}
	}
}