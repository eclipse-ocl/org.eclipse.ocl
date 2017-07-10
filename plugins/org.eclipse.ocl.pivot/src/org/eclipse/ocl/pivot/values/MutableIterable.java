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
package org.eclipse.ocl.pivot.values;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * The MutableIterable provides additional functionality for a LazyCollectionValue that is only exposed once
 * mutableIterable() has been invoked to permit mutation..
 */
public interface MutableIterable extends CollectionValue.Accumulator
{
	void mutableAppend(@Nullable Object rightValue);

	void mutableAppendAll(@NonNull LazyIterator rightIterator);

	void mutableAsBag();

	void mutableAsOrderedSet();

	void mutableAsSequence();

	void mutableAsSet();

	void mutableExcluding(@Nullable Object rightValue);

	void mutableExcludingAll(@NonNull LazyIterator rightIterator);

	void mutableIncluding(@Nullable Object rightValue);

	void mutableIncludingAll(@NonNull LazyIterator rightIterator);

	/**
	 * Modify this to be the intersection of this and rightIterator,
	 * This the underlying content of initialthe CollectionValue. If isUnique, the resulting intersection has unit counts
	 * rather than common minimum counts.
	 */
	void mutableIntersection(@NonNull LazyIterator rightIterator, boolean isUnique);

	/**
	 * Modify this to be the union of this and rightIterator,
	 * This the underlying content of the CollectionValue. If isUnique, the resulting union has unit counts
	 * rather than sum counts.
	 */
	void mutableUnion(@NonNull LazyIterator rightIterator, boolean isUnique);
}