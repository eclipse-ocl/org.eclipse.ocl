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

import java.util.NoSuchElementException;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.internal.values.AbstractCollectionIterator;

/**
 * AbstractBagIterator provides the common functionality for lazy evaluation using the hasNextCount/next
 * BagIterator protocol. Derived bag iterators must implement getNextCount() to describe the next entry that
 * may then be obtained by getNext().
 *
 * The AbstractBagIterator may be only used as a simple iterator by invoking iterator() without invoking iterable().
 * If a usage of iterator() is followed by a usage of iterable() an IllegalStateException is thrown.
 *
 * The AbstractBagIterator may be used as an iterable by invoking iterable() before iterator(). Derived
 * implementations that require memory of their output may invoke iterable() in their constructor. Multiple
 * calls to iterator() while the underlying iteration is in progress return a synchronized multi-access lazy
 * iterator. Call to iterator() after the underlying iteration has completed return a much more efficient
 * iterator. Concurrent iteration should be avoided whenever possible.
 *
 * @since 1.3
 */
public abstract class AbstractBagIterator extends AbstractCollectionIterator
{
	/**
	 * Whether this AbstractBagIterator behaves as a multi-iterable iterable or as a single iterable iterator.
	 * Initially null and indertetminate. Set true by invocatio  of iterable(). Set false by invocation of iterator().
	 * Conflicting usag, that is attempting to use a single use iterable for multi[le purposes throws an
	 * IllegalStateException.
	 */
	private Boolean withIterable = null;
	private int hasNextCount = 0;
	private int useCount = 0;
	private boolean hasNext = false;
	private Object next = null;

	protected AbstractBagIterator(@NonNull CollectionTypeId collectionTypeId) {
		super(collectionTypeId);
		//		System.out.println(NameUtil.debugSimpleName(this) + " ctor withIterable: " + withIterable);
	}

	@Override
	protected final boolean canBeIterable() {
		if (withIterable != Boolean.FALSE) {
			withIterable = Boolean.TRUE;
			//			System.out.println(NameUtil.debugSimpleName(this) + " canBeIterable() withIterable: " + withIterable);
			return true;
		}
		else {
			//			System.out.println(NameUtil.debugSimpleName(this) + " canBeIterable() withIterable: " + withIterable);
			return false;
		}
	}

	/**
	 * Derived classes must implement to return the number of times the next element is repeated.
	 * If the return is more than zero, the next eelment must be assigned prior to return by invoking setNext().
	 *
	 * It is desirable, but not mandatory for derived classes to exploit the BagIterator protocol to avoid
	 * redundant re-computation of repeated Bag elements. Repeated Bag elements may be returned one repeat at
	 * a time, by returning 1 rather than the repeat. If this is to be done, the constructior must inhibit lazy
	 * of this iterable/iterator by invoking getMapOfElement2elementCount().
	 */
	protected abstract int getNextCount();

	@Override
	public final boolean hasNext() {
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
			hasNext = false;
			hasNextCount = getNextCount();
			assert hasNext == hasNextCount > 0;
			if (!hasNext) {
				next = null;
			}
		}
		useCount = hasNextCount;
		return useCount;
	}

	@Override
	public @NonNull LazyIterable<@Nullable Object> iterable() {
		if (withIterable == null) {
			withIterable = Boolean.TRUE;
		}
		else if (withIterable == Boolean.FALSE) {
			//			System.out.println(NameUtil.debugSimpleName(this) + " iterable() - withIterable: " + withIterable);
			throw new IllegalStateException("Cannot invoke iterable() after exploiting an iterator().");
		}
		withIterable = Boolean.TRUE;
		return super.iterable();
	}

	@Override
	public @NonNull BagIterator<@Nullable Object> iterator() {
		if (withIterable == null) {
			withIterable = Boolean.FALSE;
		}
		else if (withIterable == Boolean.FALSE) {
			//			System.out.println(NameUtil.debugSimpleName(this) + " iterator() - withIterable: " + withIterable);
			throw new IllegalStateException("Must invoke iterable() before first of multiple iterator() calls.");
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

	@Override
	public final Object next() {
		assert withIterable != null;
		if (hasNextCount <= 0) {
			throw new NoSuchElementException();
		}
		hasNextCount -= useCount;
		useCount = 0;
		assert hasNext;
		return next;
	}

	protected void setNext(Object next) {
		this.next = next;
		this.hasNext = true;
	}
}