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
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.values.CollectionStrategy;
import org.eclipse.ocl.pivot.utilities.IndexableIterable;
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
public interface LazyIterable extends IndexableIterable<@Nullable Object>
{
	@NonNull CollectionStrategy getCollectionStrategy();

	/**
	 * Ensure that all lazy iterations have completed and then return a list of all elements.
	 */
	@NonNull List<@Nullable Object> getListOfElements();

	/**
	 * Ensure that all lazy iterations have completed and then return a bag of all elements.
	 */
	@NonNull Map<@Nullable Object, @NonNull ElementCount> getMapOfElement2elementCount();

	@Override
	@NonNull LazyIterator iterator();

	@NonNull CollectionValue mutableAppend(@NonNull CollectionValue leftCollectionValue, @Nullable Object rightValue);

	@NonNull CollectionValue mutableAppendAll(@NonNull CollectionValue leftCollectionValue, @NonNull Iterator<@Nullable Object> rightIterator);

	@NonNull CollectionValue mutableAsBag(@NonNull CollectionValue collectionValue);

	@NonNull CollectionValue mutableAsOrderedSet(@NonNull CollectionValue collectionValue);

	@NonNull CollectionValue mutableAsSequence(@NonNull CollectionValue collectionValue);

	@NonNull CollectionValue mutableAsSet(@NonNull CollectionValue collectionValue);

	@NonNull CollectionValue mutableExcluding(@NonNull CollectionValue leftCollectionValue, @Nullable Object rightValue);

	@NonNull CollectionValue mutableExcludingAll(@NonNull CollectionValue leftCollectionValue, @NonNull Iterator<@Nullable Object> rightIterator);

	@NonNull CollectionValue mutableIncluding(@NonNull CollectionValue leftCollectionValue, @Nullable Object rightValue);

	@NonNull CollectionValue mutableIncludingAll(@NonNull CollectionValue leftCollectionValue, @NonNull Iterator<@Nullable Object> rightIterator);

	/**
	 * Return leftCollectionValue after modification to be the intersection of leftCollectionValue and rightIterator,
	 * This the underlying content of leftCollectionValue. If isUnique, the resulting intersection has unit counts
	 * rather than common minimum counts.
	 */
	@NonNull CollectionValue mutableIntersection(@NonNull CollectionValue leftCollectionValue, @NonNull Iterator<@Nullable Object> rightIterator, boolean isUnique);

	/**
	 * Return leftCollectionValue after modification to be the union of leftCollectionValue and rightIterator,
	 * This the underlying content of leftCollectionValue. If isUnique, the resulting union has unit counts
	 * rather than sum counts.
	 */
	@NonNull CollectionValue mutableUnion(@NonNull CollectionValue leftCollectionValue, @NonNull Iterator<@Nullable Object> rightIterator, boolean isUnique);
}