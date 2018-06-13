/*******************************************************************************
 * Copyright (c) 2015 Willink Transformations and others.
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
	private final @NonNull V valueType;

	public MapTypeParametersImpl(@NonNull K keyType, @NonNull V valueType ) {
		this.keyType = keyType;
		this.valueType = valueType;
		hashCode = 5*keyType.hashCode() + 7*valueType.hashCode();
	}
	
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
		if (!this.valueType.equals(that.valueType)) {
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

	public int parametersSize() {
		return 2;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append('(');
		s.append(keyType);
		s.append(',');
		s.append(valueType);
		s.append(')');
		return s.toString();
	}
}