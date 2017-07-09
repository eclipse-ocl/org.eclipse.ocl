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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.LazyIterator;

/**
 * A SmartCollectionValueImpl refines the inherited lazy protocols to impose eager construction so that
 * clients may perform mutable operations on the collection, provided of cpourse that the clients ensure
 * that the collection only has a single consumer post-mutation.
 */
public class SmartCollectionValueImpl extends LazyCollectionValueImpl
{
	public SmartCollectionValueImpl(@NonNull CollectionTypeId typeId, @NonNull LazyIterator inputIterator, @Nullable CollectionValue precedingCollectionValue) {
		super(typeId, inputIterator, precedingCollectionValue != null ? lazyDepth(precedingCollectionValue) : 0);
	}

	protected SmartCollectionValueImpl(@NonNull CollectionTypeId typeId, @NonNull LazyIterator inputIterator, int lazyDepth) {
		super(typeId, inputIterator, lazyDepth);
	}

	@Override
	protected @NonNull CollectionValue reValue2(@NonNull CollectionTypeId typeId, @NonNull LazyIterator inputIterator, int lazyDepth) {
		return new SmartCollectionValueImpl(typeId, inputIterator.reIterator(), lazyDepth);
	}
}