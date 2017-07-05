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
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TupleTypeId;

public interface CollectionValue extends Value, Iterable<@Nullable Object>
{
	/**
	 * @generated NOT
	 * @noimplement This interface is not intended to be implemented by clients.
	 */
	interface Accumulator extends CollectionValue {
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
	 * @generated NOT
	 */
	@NonNull IntegerValue count(@Nullable Object value);

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

	/** FIXME at next major version change */
	//		@NonNull Map<@Nullable Object, @NonNull ? extends Number> getMapOfElement2elementCount();

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
	 * @generated NOT
	 */
	@NonNull CollectionValue minus(@NonNull CollectionValue set);

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
