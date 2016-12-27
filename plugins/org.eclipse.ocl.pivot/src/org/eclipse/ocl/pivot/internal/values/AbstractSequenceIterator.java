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
package org.eclipse.ocl.pivot.internal.values;

import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.OrderedCollectionValue;
import org.eclipse.ocl.pivot.values.SequenceValue;

/**
 * AbstractCollectionValueImpl provides the common functionality for eager and lazy CollectionValues.
 * @generated NOT
 * @since 1.3
 */
public abstract class AbstractSequenceIterator extends AbstractCollectionIterator implements SequenceValue
{
	//	private @Nullable SequenceValue iterable = null;
	private @Nullable LazyIterable<@Nullable Object> iterable = null;
	private int hashCode = 0;

	protected AbstractSequenceIterator(@NonNull TypeId elementTypeId) {
		super(TypeId.SEQUENCE.getSpecializedId(elementTypeId));
	}

	@Override
	public @NonNull OrderedCollectionValue append(@Nullable Object object) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull OrderedCollectionValue appendAll(@NonNull OrderedCollectionValue objects) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @Nullable Object at(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean equals(Object obj) {
		throw new UnsupportedOperationException();	// This support class is not intended for more general use.
	}

	@Override
	public @Nullable Object first() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Return true if this iterator has noy yet iterated and so it is not too late
	 * to create a LazyIterable to support multiple access.
	 */
	protected abstract boolean canBeIterable();

	@Override
	public int hashCode() {
		if (hashCode == 0) {
			synchronized (this) {
				if (hashCode == 0) {
					hashCode = computeCollectionHashCode(isOrdered(), isUnique(), iterable().getElements());
				}
			}
		}
		return hashCode;
	}

	@Override
	public @NonNull OrderedCollectionValue insertAt(int index, @Nullable Object object) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull IntegerValue indexOf(@Nullable Object object) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull LazyIterable<@Nullable Object> iterable() {
		LazyIterable<@Nullable Object> iterable2 = iterable;
		if (iterable2 == null) {
			if (!canBeIterable()) {
				throw new IllegalStateException();
			}
			iterable2 = iterable = new LazyIterable<>(this);
		}
		return iterable2;
	}

	@Override
	public @NonNull Iterator<@Nullable Object> iterator() {
		return iterable != null ? iterable.iterator() : this;
	}

	@Override
	public @Nullable Object last() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull OrderedCollectionValue prepend(@Nullable Object object) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull OrderedCollectionValue prependAll(@NonNull OrderedCollectionValue objects) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull OrderedCollectionValue reverse() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull SequenceValue subSequence(int lower, int upper) {
		throw new UnsupportedOperationException();
	}
}
