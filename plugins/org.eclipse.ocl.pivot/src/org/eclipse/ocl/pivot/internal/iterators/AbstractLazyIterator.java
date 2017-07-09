/*******************************************************************************
 * Copyright (c) 2017 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.values.LazyIterator;

public abstract class AbstractLazyIterator implements LazyIterator
{
	/**
	 * The next value to be returned by this iterator, if hasNextCount > 0.
	 */
	private Object next = null;

	/**
	 * The number of repeats of next that are available.
	 */
	private int hasNextCount = 0;

	/**
	 * The number of repeats to be advanced by an invocation of next(). Set to 1 by the traditional hasNext().
	 * Set to hasNextCount by the BaggableIterator protocol of hasNextCount().
	 */
	private int useCount = 0;

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
	public abstract int getNextCount();

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
		if (hasNextCount <= 0) {
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
	public boolean isCached() {
		return false;
	}

	@Override
	public final Object next() {
		if (hasNextCount <= 0) {
			throw new NoSuchElementException();
		}
		hasNextCount -= useCount;
		useCount = 0;
		return next;
	}

	@Override
	public abstract @NonNull LazyIterator reIterator();

	protected int setNext(Object next, int nextCount) {
		assert nextCount > 0;
		this.next = next;
		this.hasNextCount = nextCount;
		return nextCount;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 50);
		return s.toString();
	}

	public abstract void toString(@NonNull StringBuilder s, int sizeLimit);
}