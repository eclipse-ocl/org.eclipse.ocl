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
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.IndexableIterable;

/**
 * A LazyIterable provides a lazy cache of the elements of an Iterator so that the elements are
 * available for access by more than one Iterator, and also so that the elements can be accessed
 * by iteration index.
 *
 * @since 1.3
 */
public abstract class LazyIterable<E> implements IndexableIterable<E>
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
				return lazyListOfElements.get(index++);
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

	public static class Bag<E> extends LazyIterable<E>
	{
		public Bag(@NonNull Iterator<E> internalIterator) {
			super(internalIterator);
		}

	}

	public static class Sequence<E> extends LazyIterable<E>
	{
		public Sequence(@NonNull Iterator<E> internalIterator) {
			super(internalIterator);
		}

		@Override
		protected void add(E anElement) {
			size++;
			lazyListOfElements.add(anElement);
			if (lazyBagOfElements != null) {
				lazyBagOfElements.add(anElement);
			}
		}
	}

	public static class Unique<E> extends LazyIterable<E>
	{
		public Unique(@NonNull Iterator<E> internalIterator) {
			super(internalIterator);
		}

		@Override
		protected void add(E anElement) {
			size++;
			lazyListOfElements.add(anElement);
			if (lazyBagOfElements != null) {
				lazyBagOfElements.add(anElement);
			}
		}

	}

	/**
	 * The iterator that provides the elements to be cached.
	 */
	private final @NonNull Iterator<E> internalIterator;

	/**
	 * The lazily cached elements obtained by iterating internalIterator.
	 */
	protected final @NonNull List<E> lazyListOfElements = new ArrayList<>();	// ArrayList reallocates arrays; could be better to do so ourselves with a smart estimatedSize()

	/**
	 * The lazily cached elements obtained by iterating internalIterator.
	 */
	//	protected @Nullable Set<E> lazySetOfElements = null;

	/**
	 * The lazily cached elements obtained by iterating internalIterator.
	 */
	protected @Nullable Map<E, Integer> lazyMapOfElement2count = null;

	/**
	 * A local copy of lazyListOfElements.size();
	 */
	protected int size = 0;

	public LazyIterable(@NonNull Iterator<E> internalIterator) {
		this.internalIterator = internalIterator;
	}

	protected abstract void add(E anElement);

	@Override
	public boolean equals(Object obj) {
		throw new UnsupportedOperationException();	// This support class is not intended for more general use.
	}

	@Override
	public synchronized E get(int index) {
		while ((size <= index) && internalIterator.hasNext()) {
			add(internalIterator.next());
		}
		if (index < size) {
			return lazyListOfElements.get(index);
		}
		else {
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * Ensure that all lazy iterations have completed and then return a bag of all elements.
	 */
	public synchronized @NonNull Bag<?> getBagOfElements() {
		getListOfElements();
		Bag<E> lazyBagOfElements2 = lazyBagOfElements;
		if (lazyBagOfElements2 == null) {
			lazyBagOfElements2 = lazyBagOfElements = new BagImpl<>();
			for (E element : lazyListOfElements) {
				lazyBagOfElements2.add(element);
			}
		}
		return lazyBagOfElements2;
	}

	/**
	 * Ensure that all lazy iterations have completed and then return a list of all elements.
	 */
	public synchronized @NonNull List<?> getListOfElements() {
		while (internalIterator.hasNext()) {
			add(internalIterator.next());
		}
		return lazyListOfElements;
	}

	/**
	 * Ensure that all lazy iterations have completed and then return a set of all elements.
	 */
	public @NonNull Set<E> getSetOfElements() {
		getBagOfElements();
		return lazyBagOfElements.getMap().keySet();
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
			return new FasterListIterator<>(lazyListOfElements);
		}
	}

	/**
	 * Ensure that all lazy iterations have completed and then return the number of elements.
	 */
	@Override
	public int size() {
		getListOfElements();
		return size;
	}

	@Override
	public @NonNull String toString() {
		return String.valueOf(internalIterator);
	}
}