/*******************************************************************************
 * Copyright (c) 2010, 2014 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library.collection;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.iterators.AsOrderedSetIterator;
import org.eclipse.ocl.pivot.internal.iterators.LazyIterable;
import org.eclipse.ocl.pivot.library.AbstractUnaryOperation;
import org.eclipse.ocl.pivot.values.CollectionValue;

/**
 * CollectionMutableAsOrderedSetOperation realises the mutable variant of the Collection::asOrderedSet() library operation.
 *
 * @since 1.3
 */
public class CollectionMutableAsOrderedSetOperation extends AbstractUnaryOperation
{
	public static final @NonNull CollectionMutableAsOrderedSetOperation INSTANCE = new CollectionMutableAsOrderedSetOperation();

	@Override
	public @NonNull CollectionValue evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object sourceValue) {
		CollectionValue leftCollectionValue = asCollectionValue(sourceValue);
		Iterable<@Nullable Object> iterable = leftCollectionValue.iterable();
		if (iterable instanceof LazyIterable) {
			return ((LazyIterable<@Nullable Object>)iterable).mutableAsOrderedSet(leftCollectionValue);
		}
		else {
			return new AsOrderedSetIterator(leftCollectionValue);
		}
	}
}
