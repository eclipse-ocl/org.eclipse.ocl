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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * @since 7.0
 */
public class CollectionTypeArguments //implements Iterable<Object>
{
	protected class Iterator implements java.util.Iterator<Object>
	{
		private int position = 0;

		@Override
		public boolean hasNext() {
			return position < 3;
		}

		@Override
		public Object next() {
			switch (position++) {
				case 0: return elementType;
				case 1: return lower;
				case 2: return upper;
			}
			throw new NoSuchElementException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private final int hashCode;
	protected final @NonNull CollectionTypeId collectionTypeId;
	protected final @NonNull Type elementType;
	protected final boolean isNullFree;
	protected final @NonNull IntegerValue lower;
	protected final @NonNull UnlimitedNaturalValue upper;

	public CollectionTypeArguments(@NonNull CollectionTypeId collectionTypeId, @NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		assert collectionTypeId == collectionTypeId.getGeneralizedId();
		if (isNullFree && (collectionTypeId == TypeId.ORDERED_SET)) {
			getClass();			// XXX
		}
		this.collectionTypeId = collectionTypeId;
		this.elementType = elementType;
		this.isNullFree = isNullFree;
		this.lower = lower != null ? lower : PivotConstants.DEFAULT_LOWER_BOUND;
		this.upper = upper != null ? upper : PivotConstants.DEFAULT_UPPER_BOUND;
		int hash = collectionTypeId.hashCode();
		hash += 3 * elementType.getTypeId().hashCode();
		hash += isNullFree ? 9876 : 0;
		hash += 7 * this.lower.hashCode();
		hash += 17 * this.upper.hashCode();
		hashCode = hash;
		assert PivotUtil.assertIsNormalizedType(elementType);
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof CollectionTypeArguments)) {
			return false;
		}
		CollectionTypeArguments that = (CollectionTypeArguments)o;
		if (this.hashCode != that.hashCode){
			return false;
		}
		if (this.collectionTypeId != that.collectionTypeId) {
			return false;
		}
		if (this.isNullFree != that.isNullFree) {
			return false;
		}
		if (this.elementType.getTypeId() != that.elementType.getTypeId()) {
			return false;
		}
		if (!this.lower.equals(that.lower)) {
			return false;
		}
		if (!this.upper.equals(that.upper)) {
			return false;
		}
		return true;
	}

	public @NonNull CollectionTypeId getCollectionTypeId() {
		return collectionTypeId;
	}

	public @NonNull Type getElementType() {
		return elementType;
	}

	public @NonNull IntegerValue getLower() {
		return lower;
	}

	public @NonNull UnlimitedNaturalValue getUpper() {
		return upper;
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	public boolean isNullFree() {
		return isNullFree;
	}

//	@Override
//	public @NonNull Iterator iterator() {
//		return new Iterator();
//	}

	public int parametersSize() {
		return 1;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(collectionTypeId);
		s.append('(');
		s.append(elementType);
		s.append(',');
		s.append(isNullFree);
		s.append(',');
		s.append(lower);
		s.append(',');
		s.append(upper);
		s.append(')');
		return s.toString();
	}
}