/*******************************************************************************
 * Copyright (c) 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Sebastian Zarnekow (org.eclipse.xtext.xbase.lib.Pair) and common sense - initial inspiration
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;
import java.io.Serializable;
import java.util.Objects;

import org.eclipse.jdt.annotation.NonNull;

/**
 * A Pair provides an immutable association between a first/key and a second/value. Either key or value or both may be null.
 *
 * A Pair is a DataType rather than a Class, consequently distinct Pair instances are equal if their deep content is equal.
 *
 * @param <K>
 *            the key-type of the pair.
 * @param <V>
 *            the value-type of the pair.
 *
 * @since 7.0
 *
 * This revamp of org.eclipse.xtext.xbase.lib.Pair avoids the need to import Xtext in non-Xtext contexts.
 */
public final class Pair<K, V> implements Serializable
{
	private static final long serialVersionUID = 4679029414136697731L;

	private final K key;
	private final V value;

	/**
	 * Creates a new Pair associating a first element (the key) with a second element (the value).
	 */
	public Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Pair))
			return false;
		Pair<?, ?> that = (Pair<?, ?>) o;
		return Objects.equals(this.key, that.key) && Objects.equals(this.value, that.value);
	}

	/**
	 * Returns the first element of the pair - the key.
	 */
	public K getKey() {
		return key;
	}

	/**
	 * Returns the second element of the pair - the value.
	 */
	public V getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(key) ^ Objects.hashCode(value);
	}

	@Override
	public @NonNull String toString() {
		return Objects.toString(key) + "->" + Objects.toString(value);
	}
}
