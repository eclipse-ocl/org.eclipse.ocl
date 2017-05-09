/*******************************************************************************
 * Copyright (c) 2010, 2015 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.values;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.MapTypeId;

public interface MapValue extends Value//, Iterable<Object>
{
	/**
	 * @generated NOT
	 */
	interface Accumulator extends MapValue {
		boolean add(@Nullable Object value);
	}

	/**
	 * @generated NOT
	 */
	@NonNull Map<@Nullable Object, @Nullable Object> asMap();

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
	Object at(Object right);

	/**
	 * @generated NOT
	 */
	@NonNull Set<Map.Entry<@Nullable Object, @Nullable Object>> entrySet();

	/**
	 * @generated NOT
	 */
	@NonNull Boolean excludes(@Nullable Object value);

	/**
	 * @generated NOT
	 */
	@NonNull Boolean excludes(@Nullable Object key, @Nullable Object value);

	/**
	 * @generated NOT
	 */
	@NonNull Boolean excludesAll(@NonNull CollectionValue c);

	/**
	 * @generated NOT
	 */
	@NonNull Boolean excludesMap(@NonNull MapValue m);

	/**
	 * @generated NOT
	 */
	@NonNull Boolean excludesValue(@Nullable Object value);

	/**
	 * @generated NOT
	 */
	@NonNull MapValue excluding(@Nullable Object value);

	/**
	 * @generated NOT
	 */
	@NonNull MapValue excluding(@Nullable Object key, @Nullable Object value);

	/**
	 * @generated NOT
	 */
	@NonNull MapValue excludingAll(@NonNull CollectionValue c);

	/**
	 * @generated NOT
	 */
	@NonNull MapValue excludingMap(@NonNull MapValue m);

	/**
	 * @generated NOT
	 */
	@NonNull Set<Map.Entry<Object, Object>> getElements();

	/**
	 * @generated NOT
	 */
	@NonNull SetValue getKeys();

	/**
	 * @generated NOT
	 */
	@NonNull BagValue getValues();

	/**
	 * @generated NOT
	 */
	@Override
	@NonNull MapTypeId getTypeId();

	/**
	 * @generated NOT
	 */
	@NonNull Boolean includes(@Nullable Object value);

	/**
	 * @generated NOT
	 */
	@NonNull Boolean includes(@Nullable Object key, @Nullable Object value);

	/**
	 * @generated NOT
	 */
	@NonNull Boolean includesAll(@NonNull CollectionValue c);

	/**
	 * @generated NOT
	 */
	@NonNull Boolean includesMap(@NonNull MapValue m);

	/**
	 * @generated NOT
	 */
	@NonNull Boolean includesValue(@Nullable Object value);

	/**
	 * @generated NOT
	 */
	@NonNull MapValue including(@NonNull MapTypeId returnTypeId, @Nullable Object key, @Nullable Object value);

	/**
	 * @generated NOT
	 */
	@NonNull MapValue includingMap(@NonNull MapTypeId returnTypeId, @NonNull MapValue m);

	/**
	 * @generated NOT
	 */
	int intSize();

	/**
	 * @generated NOT
	 */
	@NonNull Boolean isEmpty();

	/**
	 * @generated NOT
	 */
	@NonNull Set<@Nullable Object> keySet();

	/**
	 * @generated NOT
	 */
	@NonNull Boolean notEmpty();

	/**
	 * @generated NOT
	 */
	@NonNull IntegerValue size();
}
