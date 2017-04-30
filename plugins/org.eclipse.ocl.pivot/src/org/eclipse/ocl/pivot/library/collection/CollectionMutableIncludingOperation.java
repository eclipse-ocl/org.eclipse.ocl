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
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.iterators.IncludingIterator;
import org.eclipse.ocl.pivot.internal.iterators.LazyIterable;
import org.eclipse.ocl.pivot.library.AbstractSimpleBinaryOperation;
import org.eclipse.ocl.pivot.values.CollectionValue;

/**
 * CollectionIncludingOperation realises the mutable Collection::including() library operation.
 *
 * @since 1.3
 */
public class CollectionMutableIncludingOperation extends AbstractSimpleBinaryOperation//AbstractBinaryOperation
{
	public static final @NonNull CollectionMutableIncludingOperation INSTANCE = new CollectionMutableIncludingOperation();

	/* @deprecated use return type id */
	@Deprecated
	@Override
	public @NonNull CollectionValue evaluate(@Nullable Object left, @Nullable Object right) {
		CollectionValue leftCollectionValue = asCollectionValue(left);
		Iterable<@Nullable Object> iterable = leftCollectionValue.iterable();
		if (iterable instanceof LazyIterable) {
			return ((LazyIterable<@Nullable Object>)iterable).mutableIncluding(leftCollectionValue, right);
		}
		else {
			return IncludingIterator.including(leftCollectionValue.getTypeId(), leftCollectionValue, right);
		}
	}

	@Override
	public @NonNull CollectionValue evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object sourceValue, @Nullable Object argumentValue) {
		CollectionValue leftCollectionValue = asCollectionValue(sourceValue);
		Iterable<@Nullable Object> iterable = leftCollectionValue.iterable();
		if (iterable instanceof LazyIterable) {
			return ((LazyIterable<@Nullable Object>)iterable).mutableIncluding(leftCollectionValue, argumentValue);
		}
		else {
			return IncludingIterator.including((CollectionTypeId)returnTypeId, leftCollectionValue, argumentValue);
		}
	}
}
