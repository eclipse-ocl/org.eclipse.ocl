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

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.OrderedCollectionValue;
import org.eclipse.ocl.pivot.values.OrderedSetValue;
import org.eclipse.ocl.pivot.values.SequenceValue;
import org.eclipse.ocl.pivot.values.UniqueCollectionValue;

import com.google.common.collect.Lists;

/**
 * AbstractCollectionValueImpl provides the common functionality for eager and lazy CollectionValues.
 * @generated NOT
 * @since 1.3
 */
public abstract class AbstractCollectionIterator extends AbstractCollectionValueImpl implements BagIterator<@Nullable Object>
{
	private @Nullable LazyIterable<@Nullable Object> iterable = null;
	private int hashCode = 0;
	private boolean usedAsIterator = false;

	protected AbstractCollectionIterator(@NonNull CollectionTypeId typeId) {
		super(typeId);
	}

	@Override
	public @NonNull Collection<? extends Object> asCollection() {
		return asEagerCollectionValue().asCollection();
	}

	private @NonNull CollectionValue asEagerCollectionValue() {
		if (isOrdered()) {
			if (isUnique()) {
				return new SparseOrderedSetValueImpl(getTypeId(), getElements());
			}
			else {
				return new SparseSequenceValueImpl(getTypeId(), iterable().getListOfElements());
			}
		}
		else {
			if (isUnique()) {
				return new SetValueImpl(getTypeId(), getElements());
			}
			else {
				BagImpl<@Nullable Object> bagImpl = new BagImpl<>();
				for (BagIterator<@Nullable Object> it = this; it.hasNext(); ) {
					int count = it.hasNextCount();
					Object next = it.next();
					bagImpl.put(next,  count);
				}
				return new BagValueImpl(getTypeId(), bagImpl);
			}
		}
	}

	private @NonNull OrderedCollectionValue asEagerOrderedCollectionValue() {
		if (isOrdered()) {
			if (isUnique()) {
				return new SparseOrderedSetValueImpl(getTypeId(), getElements());
			}
			else {
				return new SparseSequenceValueImpl(getTypeId(), iterable().getListOfElements());
			}
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	private @NonNull OrderedSetValue asEagerOrderedSetValue() {
		if (isOrdered() && isUnique()) {
			return new SparseOrderedSetValueImpl(getTypeId(), getElements());
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	private @NonNull SequenceValue asEagerSequenceValue() {
		if (isOrdered() && !isUnique()) {
			return new SparseSequenceValueImpl(getTypeId(), iterable().getListOfElements());
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	/*	private @NonNull SetValue asEagerSetValue() {
		if (!isOrdered() && isUnique()) {
			return new SetValueImpl(getTypeId(), getElements());
		}
		else {
			throw new UnsupportedOperationException();
		}
	} */

	private @NonNull UniqueCollectionValue asEagerUniqueCollectionValue() {
		if (isUnique()) {
			if (isOrdered()) {
				return new SparseOrderedSetValueImpl(getTypeId(), getElements());
			}
			else {
				return new SetValueImpl(getTypeId(), getElements());
			}
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public @NonNull Object asObject() {
		return asEagerCollectionValue().asCollection();
	}

	@Override
	public @NonNull OrderedCollectionValue asOrderedCollectionValue() {
		return isUnique() ? asOrderedSetValue() : asSequenceValue();
	}

	@Override
	public @NonNull UniqueCollectionValue asUniqueCollectionValue() {
		return isOrdered() ? asOrderedSetValue() : asSetValue();
	}

	//	@Override
	public @Nullable Object at(int oclIndex) {
		if (!isOrdered()) {
			throw new UnsupportedOperationException();
		}
		int javaIindex = oclIndex - 1;
		int size = intSize();
		if (javaIindex < 0 || size <= javaIindex) {
			throw new InvalidValueException(PivotMessages.IndexOutOfRange, oclIndex, size);
		}
		return iterable().get(javaIindex);
	}

	protected @Nullable LazyIterable<@Nullable Object> basicGetIterable() {
		return iterable;
	}

	/**
	 * Return true if this iterator has not yet iterated and so it is not too late
	 * to create a LazyIterable to support multiple access.
	 */
	protected abstract boolean canBeIterable();

	public @Nullable Object first() {
		return at(1);
	}

	/*	@Deprecated
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
		public boolean equals(Object obj) {
			throw new UnsupportedOperationException();
		}

		@Override
		public @NonNull Map<E, ? extends Number> getMap() {
			return iterable.getMapOfElement2elementCount();
		}

		@Override
		public int hashCode() {
			throw new UnsupportedOperationException();
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
	} */

	@Override
	public @NonNull Collection<? extends Object> getElements() {
		if (isUnique() || isOrdered()) {
			return iterable().getListOfElements();
		}
		else {
			return Lists.newArrayList(iterable());			// FIXME avoid this
		}
	}

	/**
	 * @since 1.3
	 */
	//	@Override
	protected @NonNull List<@Nullable Object> getListOfElements() {
		return iterable().getListOfElements();
	}

	/**
	 * @since 1.3
	 */
	@Override
	protected @NonNull Map<@Nullable Object, @NonNull ? extends Number> getMapOfElement2elementCount() {
		return iterable().getMapOfElement2elementCount();
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

	@Override
	public int hasNextCount() {				// FIXME move to derived classes to support Bags
		throw new UnsupportedOperationException();
		//		return hasNext() ? 1 : 0;
	}

	public @NonNull IntegerValue indexOf(@Nullable Object object) {
		return asEagerOrderedCollectionValue().indexOf(object);
	}

	public @NonNull OrderedCollectionValue insertAt(int index, @Nullable Object object) {
		return asEagerOrderedCollectionValue().insertAt(index, object);
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
	public @NonNull BagIterator<@Nullable Object> iterator() {
		if (usedAsIterator) {
			throw new IllegalStateException("Must invoke iterable() before first of multiple iterator() calls.");
		}
		else if (iterable != null) {
			return iterable.iterator();
		}
		else {
			usedAsIterator = true;
			return this;
		}
	}

	public @Nullable Object last() {
		return at(intSize());
	}

	public @NonNull UniqueCollectionValue minus(@NonNull UniqueCollectionValue set) {
		return asEagerUniqueCollectionValue().minus(set);
	}

	public @NonNull OrderedCollectionValue reverse() {
		return asEagerOrderedCollectionValue().reverse();
	}

	public @NonNull OrderedSetValue subOrderedSet(int lower, int upper) {
		return asEagerOrderedSetValue().subOrderedSet(lower, upper);
	}

	public @NonNull SequenceValue subSequence(int lower, int upper) {
		return asEagerSequenceValue().subSequence(lower, upper);
	}

	public @NonNull UniqueCollectionValue symmetricDifference(@NonNull UniqueCollectionValue set) {
		return asEagerUniqueCollectionValue().symmetricDifference(set);
	}
}
