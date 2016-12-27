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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.IndexableIterable;

/**
 * A LazyIterable provides a lazy cache of the elements of an Iterator so that the elements are
 * available for access by more than one Iterator, and also so that the elements can be accessed
 * by iteration index.
 *
 * @since 1.3
 */
public class LazyIterable<E> implements IndexableIterable<E>
{
	/**
	 * A LazyIterator support multiple access to the partially populated iteration cache provoking
	 * additional population as required.
	 */
	private class LazyIterator implements Iterator<E>
	{
		private int index = 0;

		@Override
		public boolean equals(Object obj) {
			throw new UnsupportedOperationException();	// This support class is not intended for more general use.
		}

		@Override
		public boolean hasNext() {
			if (index < size) {
				return true;
			}
			synchronized (LazyIterable.this) {
				return (index < size) || internalIterator.hasNext();
			}
		}

		@Override
		public final int hashCode() {
			throw new UnsupportedOperationException();	// This support class is not intended for more general use.
		}

		@Override
		public E next() {
			if (index < size) {
				return lazilyCachedElements.get(index++);
			}
			E next = get(index);
			index++;			// After IndexOutOfBoundsException has been thrown
			return next;
		}

		@Override
		public String toString() {
			return internalIterator.toString();
		}
	}

	/**
	 * A FasterListIterator provides better performance than the standard List Iterator by
	 * exploiting the immutability of a fully populated Iteration cache.
	 */
	private static class FasterListIterator<E> implements Iterator<E>
	{
		private final @NonNull List<E> elements;
		private final int size;
		private int index = 0;

		public FasterListIterator(@NonNull List<E> elements) {
			this.elements = elements;
			this.size = elements.size();
		}

		@Override
		public boolean equals(Object obj) {
			throw new UnsupportedOperationException();	// This support class is not intended for more general use.
		}

		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public final int hashCode() {
			throw new UnsupportedOperationException();	// This support class is not intended for more general use.
		}

		@Override
		public E next() {
			//	if (index >= size) {
			//		throw new NoSuchElementException();		-- get will throw an IOOBE if the impossible happens
			//	}
			return elements.get(index++);
		}
	}

	/**
	 * The iterator that provides the elements to be cached.
	 */
	private final @NonNull Iterator<E> internalIterator;

	/**
	 * The lazily cached elements obtained by iterating internalIterator.
	 */
	private final @NonNull List<E> lazilyCachedElements = new ArrayList<>();	// ArrayList reallocates arrays; could be better to do so ourselves with a smart estimatedSize()

	/**
	 * A local copy of lazilyCachedElements.size();
	 */
	private int size = 0;

	public LazyIterable(@NonNull Iterator<E> internalIterator) {
		this.internalIterator = internalIterator;
	}

	@Override
	public boolean equals(Object obj) {
		throw new UnsupportedOperationException();	// This support class is not intended for more general use.
	}

	@Override
	public synchronized E get(int index) {
		while ((size <= index) && internalIterator.hasNext()) {
			lazilyCachedElements.add(internalIterator.next());
			size++;
		}
		if (index < size) {
			return lazilyCachedElements.get(index);
		}
		else {
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * Ensure that all lazy iterations have completed and then return all elements.
	 */
	public synchronized @NonNull List<?> getElements() {
		while (internalIterator.hasNext()) {
			lazilyCachedElements.add(internalIterator.next());
			size++;
		}
		return lazilyCachedElements;
	}

	@Override
	public final int hashCode() {
		throw new IllegalStateException();	// This support class is not intended for more general use.
	}

	@Override
	public @NonNull Iterator<E> iterator() {
		if (internalIterator.hasNext()) {
			return new LazyIterator();
		}
		else {
			return new FasterListIterator<>(lazilyCachedElements);
		}
	}

	/**
	 * Ensure that all lazy iterations have completed and then return the number of elements.
	 */
	@Override
	public int size() {
		getElements();
		return size;
	}

	@Override
	public @NonNull String toString() {
		return String.valueOf(internalIterator);
	}
}