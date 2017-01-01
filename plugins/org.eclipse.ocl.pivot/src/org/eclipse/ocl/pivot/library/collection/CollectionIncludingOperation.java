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
import org.eclipse.ocl.pivot.library.AbstractBinaryOperation;
import org.eclipse.ocl.pivot.values.CollectionValue;

/**
 * CollectionIncludingOperation realises the Collection::including() library operation.
 */
public class CollectionIncludingOperation extends AbstractBinaryOperation//AbstractSimpleBinaryOperation
{
	public static final @NonNull CollectionIncludingOperation INSTANCE = new CollectionIncludingOperation();

	/* @deprecated use return type id */
	@Deprecated
	public @NonNull CollectionValue evaluate(@Nullable Object left, @Nullable Object right) {
		CollectionValue leftCollectionValue = asCollectionValue(left);
		return IncludingIterator.including(leftCollectionValue.getTypeId(), leftCollectionValue, right);
	}

	/**
	 * @since 1.1
	 */
	@Override
	public @Nullable Object evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object sourceValue, @Nullable Object argumentValue) {
		CollectionValue leftCollectionValue = asCollectionValue(sourceValue);
		return IncludingIterator.including((CollectionTypeId)returnTypeId, leftCollectionValue, argumentValue);
	}

	/**
	 * @since 1.3
	 *
	@Override
	public @NonNull CollectionValue evaluate(@NonNull Executor executor, @NonNull TypedElement caller, @Nullable Object @NonNull [] boxedSourceAndArgumentValues) {
		CollectionValue leftCollectionValue = asCollectionValue(boxedSourceAndArgumentValues[0]);
		Object right = boxedSourceAndArgumentValues[1];
		return IncludingEvaluator.including((CollectionTypeId)caller.getTypeId(), leftCollectionValue, right);
	} */
}
