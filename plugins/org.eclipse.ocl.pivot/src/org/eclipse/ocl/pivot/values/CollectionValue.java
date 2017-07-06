/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.values;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.internal.iterators.ElementCount;

/**
 * LazyCollectionValue extends the inherently eager CollectionValue to support lazy and lazily cached iterations.
 *
 * A lazy iteration avoids any cache overheads by computing each output value from an input value on the fly.
 * A collection that is invalid becuase of an invalid content may not be detected until the offending input is
 * traverse.
 *
 * A lazily cached iteration similarly computes output from input on the fly, but caches the results so that a
 * subsequent iteration can re-use the computations.
 * A collection that is invalid becuase of an invalid content may not be detected until the offending input is
 * traverse.
 *
 * An eagerly cached iteration computes every cache entry so that any invalid content is detected before any output
 * iteration occurs.
 *
 * @since 1.3
 */
public interface CollectionValue extends Value, Iterable<@Nullable Object>
{
	/**
	 * @generated NOT
	 * @noimplement This interface is not intended to be implemented by clients.
	 */
	interface Accumulator extends CollectionValue {		// FIXME fold into MutableIterable
		@Deprecated /* @deprecated use mutableIncluding */
		boolean add(@Nullable Object value);
	}

	/**
	 * @generated NOT
	 */
	@NonNull CollectionValue append(@Nullable Object object);

	/**
	 * @generated NOT
	 */
	@NonNull CollectionValue appendAll(@NonNull CollectionValue objects);

	/**
	 * @generated NOT
	 */
	@NonNull Collection<@Nullable Object> asCollection();

	/**
	 * @generated NOT
	 */
	@Override
	@Nullable List<@Nullable Object> asEcoreObject(@NonNull IdResolver idResolver, @Nullable Class<?> instanceClass);

	/**
	 * @generated NOT
	 */
	@Nullable <T> List<T> asEcoreObjects(@NonNull IdResolver idResolver, @Nullable Class<T> instanceClass);

	/**
	 * @generated NOT
	 */
	@Nullable Object at(int index);

	/**
	 * Return an iterable that is lazily populated and which my be re-iterated exploiting cached
	 * values from a first iteration. This provides opportunities for redundant iterations to be skipped.
	 */
	@NonNull LazyIterable cachedIterable();

	boolean canBeLazy();

	/**
	 * @generated NOT
	 */
	@NonNull IntegerValue count(@Nullable Object value);

	/**
	 * Return an iterable that has been eagerly populated. This inhibits opportunities for
	 * redundant iterations to be skipped but may improve the speed of subsequent iterations.
	 *
	 * An eager evaluation is needed to ensure that any invalid content is discovered before any element is used.
	 */
	@NonNull LazyIterable eagerIterable();

	/**
	 * @generated NOT
	 */
	@NonNull Boolean excludes(@Nullable Object value);

	/**
	 * @generated NOT
	 */
	@NonNull Boolean excludesAll(@NonNull CollectionValue c);

	/**
	 * @generated NOT
	 */
	@NonNull CollectionValue excluding(@Nullable Object value);

	/**
	 * @generated NOT
	 */
	@NonNull CollectionValue excludingAll(@NonNull CollectionValue c);

	/**
	 * @generated NOT
	 */
	@Nullable Object first();

	/**
	 * @generated NOT
	 */
	@NonNull CollectionValue flatten();

	/**
	 * @generated NOT
	 */
	boolean flatten(@NonNull Collection<Object> flattenedElements);

	/**
	 * @generated NOT
	 */
	@NonNull Collection<@Nullable Object> getElements();

	/**
	 * @generated NOT
	 */
	String getKind();

	/**
	 * @generated NOT
	 */
	@NonNull Map<@Nullable Object, @NonNull ? extends ElementCount> getMapOfElement2elementCount();

	/**
	 * @generated NOT
	 */
	@Override
	@NonNull CollectionTypeId getTypeId();

	/**
	 * @generated NOT
	 */
	@NonNull Boolean includes(@Nullable Object value);

	/**
	 * @generated NOT
	 */
	@NonNull Boolean includesAll(@NonNull CollectionValue c);

	/**
	 * @generated NOT
	 */
	@NonNull CollectionValue including(@Nullable Object value);

	/**
	 * @generated NOT
	 */
	@NonNull CollectionValue includingAll(@NonNull CollectionValue c);

	/**
	 * @generated NOT
	 */
	@NonNull IntegerValue indexOf(@Nullable Object object);

	/**
	 * @generated NOT
	 */
	@NonNull CollectionValue insertAt(int index, @Nullable Object object);

	/**
	 * @generated NOT
	 */
	int intSize();

	/**
	 * @generated NOT
	 */
	@NonNull CollectionValue intersection(@NonNull CollectionValue c);

	/**
	 * @generated NOT
	 */
	@NonNull Boolean isEmpty();

	boolean isOrdered();

	boolean isUnique();

	/**
	 * @generated NOT
	 */
	@NonNull Iterable<@Nullable Object> iterable();

	/**
	 * @generated NOT
	 */
	@Override
	@NonNull Iterator<@Nullable Object> iterator();

	/**
	 * @generated NOT
	 */
	@Nullable Object last();

	/**
	 * Return an iterator that may avoid creating and populating a cache of the contents.
	 *
	 * If the collection is inherently cached then an ordinary iterator is returned.
	 *
	 * If the collection supports lazy iteration then a lazy iterator is returned for the first consumer. If
	 * a further (second) iterator is requested, a re-iteration is performed and a cache populated so that a
	 * further further (third...) iterators re-uses the cache. Re-iteration is undesirable so consumers
	 * should invoke iterator() or eagerIterable() if multiple access are likely to occur.
	 */
	@NonNull BaggableIterator<@Nullable Object> lazyIterator();

	/**
	 * @generated NOT
	 */
	@NonNull CollectionValue minus(@NonNull CollectionValue set);

	/**
	 * Return an iterable that has been eagerly populated and may then be modified. This inhibits opportunities for
	 * redundant iterations to be skipped but may improve the speed of subsequent iterations.
	 *
	 * Returns null if this CollectionValue is not mutable.
	 */
	@Nullable MutableIterable mutableIterable();

	/**
	 * @generated NOT
	 */
	@NonNull Boolean notEmpty();

	/**
	 * @generated NOT
	 */
	@NonNull CollectionValue prepend(@Nullable Object object);

	/**
	 * @generated NOT
	 */
	@NonNull CollectionValue prependAll(@NonNull CollectionValue objects);

	/**
	 * @generated NOT
	 */
	@Nullable Set<@NonNull TupleValue> product(@NonNull CollectionValue c, @NonNull TupleTypeId tupleTypeId);

	/**
	 * @generated NOT
	 */
	@NonNull CollectionValue reverse();

	/**
	 * @generated NOT
	 */
	@NonNull IntegerValue size();

	/**
	 * @generated NOT
	 */
	@NonNull CollectionValue sort(@NonNull Comparator<@Nullable Object> comparator);

	/**
	 * @generated NOT
	 */
	@NonNull CollectionValue subOrderedSet(int lower, int upper);

	/**
	 * @generated NOT
	 */
	@NonNull CollectionValue subSequence(int lower, int upper);

	/**
	 * @generated NOT
	 */
	@NonNull CollectionValue symmetricDifference(@NonNull CollectionValue set);

	/**
	 * @generated NOT
	 */
	@NonNull CollectionValue toSequenceValue();

	/**
	 * @generated NOT
	 */
	@NonNull CollectionValue union(@NonNull CollectionValue c);
}
