/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.iterators;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.internal.values.BagImpl;
import org.eclipse.ocl.pivot.internal.values.BagValueImpl;
import org.eclipse.ocl.pivot.internal.values.SetValueImpl;
import org.eclipse.ocl.pivot.internal.values.SparseOrderedSetValueImpl;
import org.eclipse.ocl.pivot.internal.values.SparseSequenceValueImpl;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.BaggableIterator;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.OrderedCollectionValue;
import org.eclipse.ocl.pivot.values.UniqueCollectionValue;

import com.google.common.collect.Lists;

/**
 * AbstractBaggableIterator provides the common functionality for lazy evaluation using the hasNextCount/next
 * BaggableIterator protocol. Derived baggable iterators must implement getNextCount() to describe the next entry
 * by a callback to setNext().
 *
 * The AbstractBaggableIterator may only used as a simple iterator by invoking iterator() without invoking iterable().
 * If a usage of iterator() is followed by a usage of iterable() an IllegalStateException is thrown.
 *
 * The AbstractBaggableIterator may be used as an iterable by invoking iterable() before iterator(). Derived
 * implementations that require memory of their output may invoke iterable() in their constructor. Multiple
 * calls to iterator() while the underlying iteration is in progress return a synchronized multi-access lazy
 * iterator. Call to iterator() after the underlying iteration has completed return a much more efficient
 * iterator. Concurrent iteration should be avoided whenever possible.
 *
 * @since 1.3
 */
public abstract class AbstractBaggableIterator extends AbstractBaggableValueImpl implements BaggableIterator<@Nullable Object>
{
	/**
	 * The hashCode of the boxed values in this collection.
	 */
	private int hashCode = 0;

	/**
	 * If a history of the iterator is required, a non-null LazyIterable is used to provide it by calling
	 * back to this iterator after ensuring that withIterable is TRUE.
	 */
	private @Nullable LazyIterable<@Nullable Object> lazyIterable = null;

	/**
	 * Whether this AbstractBaggableIterator behaves as a multi-iterable iterable or as a single iterable iterator.
	 * Initially null and indeterminate. Set true by invocation of iterable(). Set false by invocation of iterator().
	 * Conflicting usage, that is attempting to use a single use iterable for multiple purposes throws an
	 * IllegalStateException.
	 */
	private Boolean withIterable = null;

	/**
	 * The next value to be returned by this itrerator, if hasNext is true.
	 */
	private Object next = null;

	/**
	 * The number of repeats of next that are available.
	 */
	private int hasNextCount = 0;

	/**
	 * The number of repeats to be advanced by an invocatuion of next(). Set to 1 by the traditional hasNext().
	 * Set to hasNextCpunt by the BaggableIterator protocol of hasNextCount().
	 */
	private int useCount = 0;

	protected AbstractBaggableIterator(@NonNull CollectionTypeId collectionTypeId) {
		super(collectionTypeId);
		//		System.out.println(NameUtil.debugSimpleName(this) + " ctor withIterable: " + withIterable);
	}

	@Override
	public @NonNull Collection<? extends Object> asCollection() {
		return asEagerCollectionValue().asCollection();
	}

	public @NonNull CollectionValue asEagerCollectionValue() {
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
				for (BaggableIterator<@Nullable Object> it = this; it.hasNext(); ) {
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

	@Override
	public @NonNull BaggableIterator<@Nullable Object> baggableIterator() {
		if (withIterable == null) {
			withIterable = Boolean.FALSE;
		}
		else if (withIterable == Boolean.FALSE) {
			System.err.println(NameUtil.debugSimpleName(this) + " iterator() - withIterable: " + withIterable);
			//			throw new IllegalStateException("Must invoke iterable() before first of multiple iterator() calls.");
		}
		LazyIterable<@Nullable Object> iterable = basicGetIterable();
		if (iterable != null) {
			return iterable.iterator();
		}
		else {
			withIterable = Boolean.FALSE;
			//			System.out.println(NameUtil.debugSimpleName(this) + " iterator() withIterable: " + withIterable);
			return this;
		}
	}

	protected @Nullable LazyIterable<@Nullable Object> basicGetIterable() {
		return lazyIterable;
	}

	@Override
	public @NonNull IntegerValue count(@Nullable Object value) {
		return ValueUtil.integerValueOf(iterable().count(value));
	}

	public @Nullable Object first() {
		return at(1);
	}

	@Override
	public @NonNull Collection<? extends Object> getElements() {
		if (!isBag()) {
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
	public @NonNull Map<@Nullable Object, @NonNull ? extends Number> getMapOfElement2elementCount() {
		return iterable().getMapOfElement2elementCount();
	}

	/**
	 * Derived classes must implement to return the number of times the next element is repeated.
	 * If the return is more than zero, the next element and count must be assigned prior to return
	 * by invoking setNext().
	 *
	 * It is desirable, but not mandatory for derived classes to exploit the BaggableIterator protocol to avoid
	 * redundant re-computation of repeated Bag elements. Repeated Bag elements may be returned one repeat at
	 * a time, by returning 1 rather than the repeat. If this is to be done, the constructor must inhibit lazy
	 * use of this iterable/iterator by invoking getMapOfElement2elementCount().
	 */
	protected abstract int getNextCount();

	@Override
	public final boolean hasNext() {
		assert withIterable != null;
		if ((hasNextCount > 0) || (hasNextCount() > 0)) {
			useCount = 1;
			return true;
		}
		else {
			useCount = 0;
			return false;
		}
	}

	@Override
	public final int hasNextCount() {
		assert withIterable != null;
		if (hasNextCount <= 0) {
			//			if (withIterable == null) {
			//				withIterable = Boolean.FALSE; System.out.println(NameUtil.debugSimpleName(this) + " hasNextCount() withIterable: " + withIterable);
			//			}
			int hasNextCount = getNextCount();
			assert hasNextCount == this.hasNextCount;
			if (hasNextCount <= 0) {
				next = null;
			}
		}
		useCount = hasNextCount;
		return useCount;
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
	public @NonNull Boolean includes(@Nullable Object value) {
		return iterable().contains(value);
	}

	public @NonNull IntegerValue indexOf(@Nullable Object object) {
		return asEagerOrderedCollectionValue().indexOf(object);
	}

	public @NonNull OrderedCollectionValue insertAt(int index, @Nullable Object object) {
		return asEagerOrderedCollectionValue().insertAt(index, object);
	}

	@Override
	public int intCount(@Nullable Object value) {
		return iterable().count(value);
	}

	@Override
	public int intSize() {
		return iterable().size();
	}

	@Override
	public @NonNull LazyIterable<@Nullable Object> iterable() {
		if (withIterable == null) {
			withIterable = Boolean.TRUE;
		}
		else if (withIterable == Boolean.FALSE) {
			System.err.println(NameUtil.debugSimpleName(this) + " iterable() - withIterable: " + withIterable);
			//			throw new IllegalStateException("Cannot invoke iterable() after exploiting an iterator().");
		}
		//		withIterable = Boolean.TRUE;
		LazyIterable<@Nullable Object> lazyIterable2 = lazyIterable;
		if (lazyIterable2 == null) {
			EqualsStrategy equalsStrategy = TypeUtil.getEqualsStrategy(typeId.getElementTypeId(), false);
			lazyIterable = lazyIterable2 = new LazyIterable<>(this, collectionFactory, equalsStrategy);
		}
		return lazyIterable2;
	}

	@Override
	public @NonNull BaggableIterator<@Nullable Object> iterator() {
		return baggableIterator();
	}

	public @Nullable Object last() {
		return at(intSize());
	}

	@Override
	public final Object next() {
		assert withIterable != null;
		if (hasNextCount <= 0) {
			throw new NoSuchElementException();
		}
		hasNextCount -= useCount;
		useCount = 0;
		return next;
	}

	public @NonNull OrderedCollectionValue reverse() {
		return asEagerOrderedCollectionValue().reverse();
	}

	protected int setNext(Object next, int nextCount) {
		assert nextCount > 0;
		this.next = next;
		this.hasNextCount = nextCount;
		return nextCount;
	}
}