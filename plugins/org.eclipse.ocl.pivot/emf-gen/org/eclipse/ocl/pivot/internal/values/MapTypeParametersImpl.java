/*******************************************************************************
 * Copyright (c) 2015, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.values;

import java.util.NoSuchElementException;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.MapTypeParameters;

public class MapTypeParametersImpl<K extends Type, V extends Type> implements MapTypeParameters<K, V>
{
	protected class Iterator implements java.util.Iterator<Object>
	{
		private int position = 0;

		@Override
		public boolean hasNext() {
			return position < 2;
		}

		@Override
		public Object next() {
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

	private final int hashCode;
	private final @NonNull K keyType;
	private final boolean keysAreNullFree;
	private final @NonNull V valueType;
	private final boolean valuesAreNullFree;

	/**
	 * @since 1.6
	 */
	public MapTypeParametersImpl(@NonNull K keyType, boolean keysAreNullFree, @NonNull V valueType, boolean valuesAreNullFree) {
		this.keyType = keyType;
		this.keysAreNullFree = keysAreNullFree;
		this.valueType = valueType;
		this.valuesAreNullFree = valuesAreNullFree;
		hashCode = 5*keyType.hashCode() + (keysAreNullFree ? 9876 : 0) + 7*valueType.hashCode() + (valuesAreNullFree ? 5432 : 0);
		assert PivotUtil.assertIsNormalizedType(keyType);
		assert PivotUtil.assertIsNormalizedType(valueType);
	}

	/**
	 * @since 1.7
	 *
	public MapTypeParametersImpl(org.eclipse.ocl.pivot.@NonNull Class entryClass) {
		this.entryClass = entryClass;
		Iterable<@NonNull Property> ownedProperties = PivotUtil.getOwnedProperties(entryClass);
		Property keyProperty = ClassUtil.requireNonNull(NameUtil.getNameable(ownedProperties, "key"));
		Property valueProperty = ClassUtil.requireNonNull(NameUtil.getNameable(ownedProperties, "value"));
		@SuppressWarnings("unchecked")
		K castKeyType = (K) PivotUtil.getType(keyProperty);
		this.keyType = castKeyType;
		this.keysAreNullFree = keyProperty.isIsRequired();
		@SuppressWarnings("unchecked")
		V castValueType = (V) PivotUtil.getType(valueProperty);
		this.valueType = castValueType;
		this.valuesAreNullFree = valueProperty.isIsRequired();
		hashCode = entryClass.hashCode() + 5*keyType.hashCode() + (keysAreNullFree ? 9876 : 0) + 7*valueType.hashCode() + (valuesAreNullFree ? 5432 : 0);
		assert PivotUtil.assertIsNormalizedType(keyType);
		assert PivotUtil.assertIsNormalizedType(valueType);
	} */

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof MapTypeParametersImpl<?,?>)) {
			return false;
		}
		MapTypeParametersImpl<?,?> that = (MapTypeParametersImpl<?,?>)o;
		if (this.hashCode != that.hashCode){
			return false;
		}
		if (!this.keyType.equals(that.keyType)) {
			return false;
		}
		if (this.keysAreNullFree != that.keysAreNullFree) {
			return false;
		}
		if (!this.valueType.equals(that.valueType)) {
			return false;
		}
		if (this.valuesAreNullFree != that.valuesAreNullFree) {
			return false;
		}
		return true;
	}

	@Override
	public @NonNull K getKeyType() {
		return keyType;
	}

	@Override
	public @NonNull V getValueType() {
		return valueType;
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public @NonNull Iterator iterator() {
		return new Iterator();
	}

	@Override
	public boolean isKeysAreNullFree() {
		return keysAreNullFree;
	}

	@Override
	public boolean isValuesAreNullFree() {
		return valuesAreNullFree;
	}

	public int parametersSize() {
		return 2;
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