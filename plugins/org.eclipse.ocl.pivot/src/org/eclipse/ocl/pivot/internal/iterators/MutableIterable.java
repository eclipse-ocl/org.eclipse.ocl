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
import org.eclipse.ocl.pivot.values.CollectionValue;

/**
 * A LazyIterable provides a polymorphic lazy mutable Collection implementation.
 *
 * The collection is populated lazily from a source iterator.
 *
 * The collection behaviour is defined by its collectionStrategy.
 *
 * Identification of unique elements for Sets is performed by an equalsStrategy, which may be very simple when the contents
 * is guaranteed to have the same OCL/Java semantics, but more complicated for types with distinct semantics.
 *
 * Mutable activities may be used if the caller guarantees that there are no consumers of the unmutated collection.
 *
 * Lazy evaluation is incompatible with invalid values, therefore the caller must guarantee that no future invalid
 * value may occur thaat would invalidate the earlier lazy results.
 *
 * @since 1.3
 */
public interface MutableIterable extends CollectionValue.Accumulator, LazyIterable
{
	void mutableAppend(@Nullable Object rightValue);

	void mutableAppendAll(@NonNull Iterator<@Nullable Object> rightIterator);

	void mutableAsBag();

	void mutableAsOrderedSet();

	void mutableAsSequence();

	void mutableAsSet();

	void mutableExcluding(@Nullable Object rightValue);

	void mutableExcludingAll(@NonNull Iterator<@Nullable Object> rightIterator);

	void mutableIncluding(@Nullable Object rightValue);

	void mutableIncludingAll(@NonNull Iterator<@Nullable Object> rightIterator);

	/**
	 * Modify this to be the intersection of this and rightIterator,
	 * This the underlying content of initialthe CollectionValue. If isUnique, the resulting intersection has unit counts
	 * rather than common minimum counts.
	 */
	void mutableIntersection(@NonNull Iterator<@Nullable Object> rightIterator, boolean isUnique);

	/**
	 * Modify this to be the union of this and rightIterator,
	 * This the underlying content of the CollectionValue. If isUnique, the resulting union has unit counts
	 * rather than sum counts.
	 */
	void mutableUnion(@NonNull Iterator<@Nullable Object> rightIterator, boolean isUnique);
}