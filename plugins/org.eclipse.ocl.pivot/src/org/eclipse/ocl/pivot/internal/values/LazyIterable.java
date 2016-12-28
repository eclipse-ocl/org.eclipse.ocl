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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.IndexableIterable;

import com.google.common.collect.Iterators;

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
	 * BagIterator iterates over the Bag content returning each multiple element multiple times.
	 */
	private static class BagIterator<E> implements Iterator<E>
	{
		private final @NonNull Map<E, @NonNull ElementCount> map;
		private final @NonNull Iterator<E> objectIterator;
		private E currentObject;
		private int residualCount;

		private BagIterator(@NonNull LazyIterable<E> iterable) {
			this.map = iterable.getMapOfElement2elementCount();
			this.objectIterator = iterable.iterator();
			assert objectIterator.hasNext();
			currentObject = objectIterator.next();
			ElementCount count = map.get(currentObject);
			assert count != null;
			residualCount = count.value;
		}

		@Override
		public boolean hasNext() {
			return residualCount > 0;
		}

		@Override
		public E next() {
			if (residualCount <= 0) {
				throw new NoSuchElementException();
			}
			if (--residualCount > 0) {
				return currentObject;
			}
			if (objectIterator.hasNext()) {
				E savedObject = currentObject;
				currentObject = objectIterator.next();
				ElementCount count = map.get(currentObject);
				assert count != null;
				residualCount = count.value;
				return savedObject;
			}
			else {
				residualCount = 0;
				return currentObject;
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Remove not supported by OCL collections");	// Unimplemented optional operation
		}
	}
	/**
	 * ElementCounter is used as the count of a Bag element. It avoids thrashing Integer objects as counts evolve.
	 */
	private static class ElementCount extends Number
	{
		private static final long serialVersionUID = 6003094405498386037L;

		private int value;

		public ElementCount(int value) {
			this.value = value;
		}

		@Override
		public double doubleValue() {
			return value;
		}

		@Override
		public float floatValue() {
			return value;
		}

		@Override
		public int intValue() {
			return value;
		}

		@Override
		public long longValue() {
			return value;
		}

		@Override
		public String toString() {
			return Integer.toString(value);
		}
	}

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

	/*	public static class Bag<E> extends LazyIterable<E>
	{
		public Bag(@NonNull Iterator<E> internalIterator) {
			super(internalIterator);
			lazyMapOfElement2elementCount = new HashMap<>();
		}


		@Override
		protected void add(E anElement) {
			size++;
			if (addToCounts(anElement)) {
				lazyListOfElements.add(anElement);
			}
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
			if (lazyMapOfElement2elementCount != null) {
				addToCounts(anElement);
			}
		}
	}

	public static class Unique<E> extends LazyIterable<E>
	{
		public Unique(@NonNull Iterator<E> internalIterator) {
			super(internalIterator);
			lazyMapOfElement2elementCount = new HashMap<>();
		}

		@Override
		protected void add(E anElement) {
			if (addToCounts(anElement)) {
				size++;
				lazyListOfElements.add(anElement);
			}
		}

	} */

	/**
	 * The iterator that provides the elements to be cached.
	 */
	private final @NonNull Iterator<E> internalIterator;

	private final boolean isOrdered;
	private final boolean isUnique;

	/**
	 * The lazily cached elements obtained by iterating internalIterator.
	 */
	protected final @NonNull List<E> lazyListOfElements = new ArrayList<>();	// ArrayList reallocates arrays; could be better to do so ourselves with a smart estimatedSize()

	/**
	 * The lazily cached elements obtained by iterating internalIterator.
	 */
	protected @Nullable Map<E, @NonNull ElementCount> lazyMapOfElement2elementCount = null;

	/**
	 * The number of elements in the collection. For a Sequence, this is lazyListOfElements.size().
	 * For a Set/OrderedSet it is lazyMapOfElement2elementCount.keySet().size(). For a Bag it is
	 * the sum of lazyMapOfElement2elementCount.values().
	 */
	protected int size = 0;

	private @Nullable ElementCount spareElementCount = null;

	public LazyIterable(@NonNull Iterator<E> internalIterator, boolean isOrdered, boolean isUnique) {
		this.internalIterator = internalIterator;
		this.isOrdered = isOrdered;
		this.isUnique = isUnique;
		if (!isOrdered || isUnique) {
			lazyMapOfElement2elementCount = new HashMap<>();
		}
	}

	/**
	 * Add anElement to the collection updating elemnt occurrence counts. Returns true if this
	 * results in a new distinct element value.
	 */
	private boolean addToCounts(E anElement) {
		Map<E, @NonNull ElementCount> lazyMapOfElement2elementCount2 = lazyMapOfElement2elementCount;
		assert lazyMapOfElement2elementCount2 != null;
		ElementCount newElementCount = spareElementCount;
		if (newElementCount == null) {
			newElementCount = new ElementCount(1);
		}
		ElementCount oldElementCount = lazyMapOfElement2elementCount2.put(anElement, newElementCount);
		if (oldElementCount != null) {
			newElementCount.value += oldElementCount.value;
			spareElementCount = oldElementCount;
			return false;
		}
		else {
			spareElementCount = null;
			return true;
		}
	}

	@Deprecated
	public @NonNull Iterator<E> bagIterator() {
		if (size > 0) {
			return new BagIterator<>(this);
		}
		else {
			return Iterators.emptyIterator();
		}
	}

	@Deprecated
	public int bagSize() {
		int size = 0;
		for (@NonNull ElementCount elementCount : getMapOfElement2elementCount().values()) {
			size += elementCount.value;
		}
		return size;
	}

	@Override
	public boolean equals(Object obj) {
		throw new UnsupportedOperationException();	// This support class is not intended for more general use.
	}

	@Override
	public synchronized E get(int index) {
		if (isUnique) {
			while ((size <= index) && internalIterator.hasNext()) {
				E anElement = internalIterator.next();
				if (addToCounts(anElement)) {
					size++;
					lazyListOfElements.add(anElement);
				}
			}
		}
		else if (isOrdered) {
			while ((size <= index) && internalIterator.hasNext()) {
				E anElement = internalIterator.next();
				size++;
				lazyListOfElements.add(anElement);
				if (lazyMapOfElement2elementCount != null) {
					addToCounts(anElement);
				}
			}
		}
		else {
			int indexes = 0;
			while ((indexes <= index) && internalIterator.hasNext()) {
				E anElement = internalIterator.next();
				size++;
				if (addToCounts(anElement)) {
					indexes++;
					lazyListOfElements.add(anElement);
				}
			}
		}
		if (index < size) {
			return lazyListOfElements.get(index);
		}
		else {
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * Ensure that all lazy iterations have completed and then return a list of all elements.
	 */
	public synchronized @NonNull List<?> getListOfElements() {
		if (isUnique) {
			while (internalIterator.hasNext()) {
				E anElement = internalIterator.next();
				if (addToCounts(anElement)) {
					size++;
					lazyListOfElements.add(anElement);
				}
			}
		}
		else if (isOrdered) {
			while (internalIterator.hasNext()) {
				E anElement = internalIterator.next();
				size++;
				lazyListOfElements.add(anElement);
				if (lazyMapOfElement2elementCount != null) {
					addToCounts(anElement);
				}
			}
		}
		else {
			while (internalIterator.hasNext()) {
				E anElement = internalIterator.next();
				size++;
				if (addToCounts(anElement)) {
					lazyListOfElements.add(anElement);
				}
			}
		}
		return lazyListOfElements;
	}

	/**
	 * Ensure that all lazy iterations have completed and then return a bag of all elements.
	 */
	public synchronized @NonNull Map<E, @NonNull ElementCount> getMapOfElement2elementCount() {
		getListOfElements();
		Map<E, @NonNull ElementCount> lazyMapOfElement2elementCount2 = lazyMapOfElement2elementCount;
		if (lazyMapOfElement2elementCount2 == null) {
			lazyMapOfElement2elementCount2 = lazyMapOfElement2elementCount = new HashMap<>();
			for (E element : lazyListOfElements) {
				addToCounts(element);
			}
		}
		return lazyMapOfElement2elementCount2;
	}

	/**
	 * Ensure that all lazy iterations have completed and then return a set of all elements.
	 */
	public @NonNull Set<E> getSetOfElements() {
		Map<E, @NonNull ElementCount> lazyMapOfElement2elementCount2 = lazyMapOfElement2elementCount;
		if (lazyMapOfElement2elementCount2 == null) {
			lazyMapOfElement2elementCount2 = getMapOfElement2elementCount();
		}
		return lazyMapOfElement2elementCount2.keySet();
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