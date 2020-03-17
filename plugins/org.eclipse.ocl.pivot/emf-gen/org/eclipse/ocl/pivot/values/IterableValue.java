/*******************************************************************************
 * Copyright (c) 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.values;

import java.util.Collections;
import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @since 1.6
 */
public interface IterableValue extends Value, Iterable<@Nullable Object>
{
	/**
	 * @generated NOT
	 */
	default @NonNull Boolean excludes(@Nullable Object value) {
		return false;
	}

	/**
	 * @generated NOT
	 */
	default @NonNull Boolean excludesAll(@NonNull CollectionValue c) {
		return false;
	}

	/**+
	 * @generated NOT
	 */
	default @NonNull IterableValue excluding(@Nullable Object value) {
		return this;
	}

	/**
	 * @generated NOT
	 */
	default @NonNull IterableValue excludingAll(@NonNull CollectionValue c) {
		return this;
	}

	@SuppressWarnings("null")
	@Override
	default @NonNull Iterator<@Nullable Object> iterator() {
		return Collections.emptyIterator();
	}
}
