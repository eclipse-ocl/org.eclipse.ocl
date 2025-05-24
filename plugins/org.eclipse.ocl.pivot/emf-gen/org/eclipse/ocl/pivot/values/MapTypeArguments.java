/*******************************************************************************
 * Copyright (c) 2014, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.values;

import java.util.NoSuchElementException;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * MapTypeArguments aggregates the template parameter specialization of a MapType providing a single identity
 * that can be used as a key for a Map lookup.
 *
 * @since 7.0
 */
public class MapTypeArguments implements Iterable<@NonNull Type>
{
	protected class Iterator implements java.util.Iterator<@NonNull Type>
	{
		private int position = 0;

		@Override
		public boolean hasNext() {
			return position < 2;
		}

		@Override
		public @NonNull Type next() {
			switch (position++) {
				case 0: return keyType;
				case 1: return valueType;
			}
			throw new NoSuchElementException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private final int hashCode;				// XXX ?? leverage MapTypeId
	private final @NonNull Type keyType;
	private final boolean keysAreNullFree;
	private final @NonNull Type valueType;
	private final boolean valuesAreNullFree;

	public MapTypeArguments(@NonNull Type keyType, boolean keysAreNullFree, @NonNull Type valueType, boolean valuesAreNullFree) {
		this.hashCode = 5*keyType.hashCode() + (keysAreNullFree ? 9876 : 0) + 7*valueType.hashCode() + (valuesAreNullFree ? 5432 : 0);
		this.keyType = keyType;
		this.keysAreNullFree = keysAreNullFree;
		this.valueType = valueType;
		this.valuesAreNullFree = valuesAreNullFree;
		assert PivotUtil.assertIsNormalizedType(keyType);
		assert PivotUtil.assertIsNormalizedType(valueType);
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof MapTypeArguments)) {
			return false;
		}
		MapTypeArguments that = (MapTypeArguments)o;
		if (this.hashCode != that.hashCode){
			return false;
		}
		if (this.keysAreNullFree != that.keysAreNullFree) {
			return false;
		}
		if (this.valuesAreNullFree != that.valuesAreNullFree) {
			return false;
		}
		if (!this.keyType.equals(that.keyType)) {
			return false;
		}
		if (!this.valueType.equals(that.valueType)) {
			return false;
		}
		return true;
	}

	public @NonNull Type getKeyType() {
		return keyType;
	}

	public @NonNull Type getValueType() {
		return valueType;
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	public boolean isKeysAreNullFree() {
		return keysAreNullFree;
	}

	public boolean isValuesAreNullFree() {
		return valuesAreNullFree;
	}

	@Override
	public @NonNull Iterator iterator() {
		return new Iterator();
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append('(');
		s.append(keyType);
		s.append(',');
		s.append(keysAreNullFree);
		s.append(',');
		s.append(valueType);
		s.append(',');
		s.append(valuesAreNullFree);
		s.append(')');
		return s.toString();
	}
}