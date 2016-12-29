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

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.values.Bag;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.OrderedCollectionValue;
import org.eclipse.ocl.pivot.values.OrderedSetValue;
import org.eclipse.ocl.pivot.values.SequenceValue;
import org.eclipse.ocl.pivot.values.UniqueCollectionValue;

/**
 * AbstractCollectionValueImpl provides the common functionality for eager and lazy CollectionValues.
 * @generated NOT
 * @since 1.3
 */
public abstract class AbstractCollectionIterator extends AbstractCollectionValueImpl implements Iterator<@Nullable Object>
{
	private @Nullable LazyIterable<@Nullable Object> iterable = null;
	private int hashCode = 0;

	protected AbstractCollectionIterator(@NonNull CollectionTypeId typeId) {
		super(typeId);
	}

	public @NonNull OrderedCollectionValue append(@Nullable Object object) {
		throw new UnsupportedOperationException();
	}

	public @NonNull OrderedCollectionValue appendAll(@NonNull OrderedCollectionValue objects) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull Collection<? extends Object> asCollection() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull Object asObject() {
		throw new UnsupportedOperationException();
	}

	//	@Override
	public @Nullable Object at(int index) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Return true if this iterator has not yet iterated and so it is not too late
	 * to create a LazyIterable to support multiple access.
	 */
	protected abstract boolean canBeIterable();

	public @Nullable Object first() {
		throw new UnsupportedOperationException();
	}

	@Deprecated
	private static class MyBag<E> extends AbstractCollection<E> implements Bag.Internal<E>
	{
		private final @NonNull LazyIterable<E> iterable;

		public MyBag(@NonNull LazyIterable<E> iterable) {
			this.iterable = iterable;
		}

		@Override
		public boolean add(Object e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean addAll(Collection<? extends E> c) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int count(Object o) {
			throw new UnsupportedOperationException();
		}

		@Override
		public @NonNull Map<E, ? extends Number> getMap() {
			return iterable.getMapOfElement2elementCount();
		}

		@Override
		public @NonNull Iterator<E> iterator() {
			return iterable.bagIterator();
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int size() {
			return iterable.bagSize();
		}

		@Override
		public Object @NonNull [] toArray(Object @NonNull [] a) {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public @NonNull Collection<? extends Object> getElements() {
		if (isUnique() || isOrdered()) {
			return iterable().getListOfElements();
		}
		else {
			return new MyBag<>(iterable());
		}
	}

	@Override
	public int hashCode() {
		if (hashCode == 0) {
			synchronized (this) {
				if (hashCode == 0) {
					boolean isOrdered = isOrdered();
					boolean isUnique = isUnique();
					if (isOrdered || isUnique) {
						hashCode = computeCollectionHashCode(isOrdered, isUnique, iterable().getListOfElements());
					}
					else {
						hashCode = computeCollectionHashCode(iterable().getMapOfElement2elementCount());
					}
				}
			}
		}
		return hashCode;
	}

	public @NonNull IntegerValue indexOf(@Nullable Object object) {
		throw new UnsupportedOperationException();
	}

	public @NonNull OrderedCollectionValue insertAt(int index, @Nullable Object object) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int intSize() {
		return iterable().size();
	}

	@Override
	public @NonNull LazyIterable<@Nullable Object> iterable() {
		LazyIterable<@Nullable Object> iterable2 = iterable;
		if (iterable2 == null) {
			if (!canBeIterable()) {
				throw new IllegalStateException();
			}
			iterable = iterable2 = new LazyIterable<>(this, isOrdered(), isUnique());
		}
		return iterable2;
	}

	@Override
	public @NonNull Iterator<@Nullable Object> iterator() {
		return iterable != null ? iterable.iterator() : this;
	}

	public @Nullable Object last() {
		throw new UnsupportedOperationException();
	}

	public @NonNull UniqueCollectionValue minus(@NonNull UniqueCollectionValue set) {
		throw new UnsupportedOperationException();
	}

	public @NonNull OrderedCollectionValue prepend(@Nullable Object object) {
		throw new UnsupportedOperationException();
	}

	public @NonNull OrderedCollectionValue prependAll(@NonNull OrderedCollectionValue objects) {
		throw new UnsupportedOperationException();
	}

	public @NonNull OrderedCollectionValue reverse() {
		throw new UnsupportedOperationException();
	}

	public @NonNull OrderedSetValue subOrderedSet(int lower, int upper) {
		throw new UnsupportedOperationException();
	}

	public @NonNull SequenceValue subSequence(int lower, int upper) {
		throw new UnsupportedOperationException();
	}

	public @NonNull UniqueCollectionValue symmetricDifference(@NonNull UniqueCollectionValue set) {
		throw new UnsupportedOperationException();
	}
}
