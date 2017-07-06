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
import org.eclipse.ocl.pivot.internal.iterators.ExcludingIterator;
import org.eclipse.ocl.pivot.internal.iterators.MutableIterable;
import org.eclipse.ocl.pivot.library.AbstractBinaryOperation;
import org.eclipse.ocl.pivot.values.CollectionValue;

/**
 * CollectionMutableExcludingOperation realises the mutable variant of the Collection::excluding() library operation.
 *
 * @since 1.3
 */
public class CollectionMutableExcludingOperation extends AbstractBinaryOperation
{
	public static final @NonNull CollectionMutableExcludingOperation INSTANCE = new CollectionMutableExcludingOperation();

	@Override
	public @NonNull CollectionValue evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object sourceValue, @Nullable Object argumentValue) {
		CollectionValue leftCollectionValue = asCollectionValue(sourceValue);
		MutableIterable mutableIterable = leftCollectionValue.mutableIterable();
		if (mutableIterable != null) {
			mutableIterable.mutableExcluding(argumentValue);
			return leftCollectionValue;
		}
		else {
			return ExcludingIterator.excluding(leftCollectionValue, argumentValue);
		}
	}
}
