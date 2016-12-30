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
	private static class LazyBagIterator<E> implements BagIterator<E>
	{
		private final @NonNull Map<E, @NonNull ElementCount> map;
		private final @NonNull Iterator<E> objectIterator;
		private E currentObject;

		/**
		 * The number of repeats of the currentObject still to be returned by next().
		 */
		private int residualCount;

		/**
		 * The number of repeats of the currentObject to be returned by next().
		 * This is 1 if a conventional hasNext() guard has been used.
		 * This is residualCount if the more efficuent hasNextCount() has been used.
		 */
		private int nextCount = 0;

		private LazyBagIterator(@NonNull LazyIterable<E> iterable) {
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
			if (residualCount > 0) {
				nextCount = 1;
				return true;
			}
			else {
				nextCount = 0;
				return false;
			}
		}

		@Override
		public int hasNextCount() {
			nextCount = residualCount;
			return residualCount;
		}

		@Override
		public E next() {
			if (residualCount <= 0) {
				throw new NoSuchElementException();
			}
			residualCount -= nextCount;
			if (residualCount > 0) {		// If iterating a bag element by element
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
	 * A LazyIterator support multiple access to the partially populated iteration cache provoking
	 * additional population as required.
	 */
	private class LazyNonBagIterator implements BagIterator<E>
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
		public int hasNextCount() {
			if (index < size) {
				return 1;
			}
			synchronized (LazyIterable.this) {
				return (index < size) || internalIterator.hasNext() ? 1 : 0;
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
	 * A ImmutableBagIterator provides better performance than the standard List Iterator by
	 * exploiting the immutability of a fully populated Iteration cache.
	 */
	public static class ImmutableBagIterator<E> implements BagIterator<E>
	{
		private final @NonNull List<E> elements;
		private final @NonNull Map<E, @NonNull ? extends Number> element2elementCount;
		private final int size;
		private int elementIndex = 0;
		private int residualCount = 0;
		private E currentElement;
		private int nextCount = 0;

		public ImmutableBagIterator(@NonNull List<E> elements, @NonNull Map<E, @NonNull ? extends Number> element2elementCount) {
			this.elements = elements;
			this.element2elementCount = element2elementCount;
			this.size = elements.size();
			if (elementIndex < size) {
				currentElement = elements.get(elementIndex++);
				Number number = element2elementCount.get(currentElement);
				assert number != null;
				residualCount = number.intValue();
				assert residualCount > 0;
			}
			else {
				@SuppressWarnings("null") E nullE = null;
				currentElement = nullE;
			}
			nextCount = 1;
		}

		@Override
		public boolean equals(Object obj) {
			throw new UnsupportedOperationException();	// This support class is not intended for more general use.
		}

		@Override
		public boolean hasNext() {
			nextCount = 1;
			return (elementIndex < size) || (residualCount > 0);
		}

		@Override
		public int hasNextCount() {
			nextCount = residualCount;
			return residualCount;
		}

		@Override
		public final int hashCode() {
			throw new UnsupportedOperationException();	// This support class is not intended for more general use.
		}

		@Override
		public E next() {
			if (residualCount <= 0) {
				throw new NoSuchElementException();
			}
			E savedElement = currentElement;
			residualCount -= nextCount;
			if ((residualCount <= 0) && (elementIndex < size)) {
				currentElement = elements.get(elementIndex++);
				Number number = element2elementCount.get(currentElement);
				assert number != null;
				residualCount = number.intValue();
				assert residualCount > 0;
			}
			nextCount = 1;
			return savedElement;
		}

		@Override
		public String toString() {
			StringBuilder s = new StringBuilder();
			appendBagIterable(s, elements, element2elementCount);
			return s.toString();
		}
	}

	/**
	 * A ImmutableBagIterator provides better performance than the standard List Iterator by
	 * exploiting the immutability of a fully populated Iteration cache.
	 */
	public static class ImmutableNonBagIterator<E> implements BagIterator<E>
	{
		private final @NonNull List<E> elements;
		private final int size;
		private int elementIndex = 0;

		public ImmutableNonBagIterator(@NonNull List<E> elements) {
			this.elements = elements;
			this.size = elements.size();
		}

		@Override
		public boolean equals(Object obj) {
			throw new UnsupportedOperationException();	// This support class is not intended for more general use.
		}

		@Override
		public boolean hasNext() {
			return elementIndex < size;
		}

		@Override
		public int hasNextCount() {
			return elementIndex < size ? 1 : 0;
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
			return elements.get(elementIndex++);
		}

		@Override
		public String toString() {
			StringBuilder s = new StringBuilder();
			s.append("[");
			boolean isFirst = true;
			for (Object element : elements) {
				if (!isFirst) {
					s.append(", ");
				}
				s.append(element instanceof String ? "'" + element + "'" : element);
				isFirst = false;
			}
			s.append("]");
			return s.toString();
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

	public static <E> void appendBagIterable(@NonNull StringBuilder s, @NonNull List<E> elements, @Nullable Map<E, @NonNull ? extends Number> element2elementCount) {
		s.append("[");
		boolean isFirst = true;
		for (E element : elements) {
			if (!isFirst) {
				s.append(", ");
			}
			if (element2elementCount != null) {
				Number count = element2elementCount.get(element);
				if ((count == null) || (count.intValue() != 1)) {
					s.append(count);
					s.append("*");
				}
			}
			s.append(element instanceof String ? "'" + element + "'" : element);
			isFirst = false;
		}
		s.append("]");
	}

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
		else {
			newElementCount.value = 1;
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

	/*	@Deprecated
	public @NonNull Iterator<E> bagIterator() {
		if (size > 0) {
			return new LazyBagIterator<>(this);
		}
		else {
			return Iterators.emptyIterator();
		}
	} */

	/*	@Deprecated
	public int bagSize() {
		int size = 0;
		for (@NonNull ElementCount elementCount : getMapOfElement2elementCount().values()) {
			size += elementCount.value;
		}
		return size;
	} */

	int count(Object object) {
		ElementCount elementCount = getMapOfElement2elementCount().get(object);
		return elementCount != null ? elementCount.intValue() : 0;
	}

	@Override
	public boolean equals(Object obj) {
		throw new UnsupportedOperationException();	// This support class is not intended for more general use.
	}

	@Override
	public synchronized E get(int javaIndex) {
		if (isUnique) {
			while ((size <= javaIndex) && internalIterator.hasNext()) {
				E anElement = internalIterator.next();
				if (addToCounts(anElement)) {
					size++;
					lazyListOfElements.add(anElement);
				}
			}
		}
		else if (isOrdered) {
			while ((size <= javaIndex) && internalIterator.hasNext()) {
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
			while ((indexes <= javaIndex) && internalIterator.hasNext()) {
				E anElement = internalIterator.next();
				size++;
				if (addToCounts(anElement)) {
					indexes++;
					lazyListOfElements.add(anElement);
				}
			}
		}
		return lazyListOfElements.get(javaIndex);
	}

	/**
	 * Ensure that all lazy iterations have completed and then return a list of all elements.
	 */
	public synchronized @NonNull List<E> getListOfElements() {
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
		Map<E, @NonNull ElementCount> lazyMapOfElement2elementCount2 = lazyMapOfElement2elementCount;
		if (lazyMapOfElement2elementCount2 == null) {
			lazyMapOfElement2elementCount2 = lazyMapOfElement2elementCount = new HashMap<>();
			for (E element : lazyListOfElements) {
				addToCounts(element);
			}
		}
		getListOfElements();
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
	public @NonNull BagIterator<E> iterator() {
		if (!isOrdered && !isUnique) {
			Map<E, @NonNull ElementCount> lazyMapOfElement2elementCount2 = lazyMapOfElement2elementCount;
			assert lazyMapOfElement2elementCount2 != null;
			if (internalIterator.hasNext()) {
				return new LazyBagIterator<>(this);
			}
			else {
				return new ImmutableBagIterator<>(lazyListOfElements, lazyMapOfElement2elementCount2);
			}
		}
		else {
			if (internalIterator.hasNext()) {
				return new LazyNonBagIterator();
			}
			else {
				return new ImmutableNonBagIterator<>(lazyListOfElements);
			}
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
		if (internalIterator.hasNext()) {
			return String.valueOf(internalIterator);
		}
		else {
			StringBuilder s = new StringBuilder();
			appendBagIterable(s, lazyListOfElements, lazyMapOfElement2elementCount);
			return s.toString();
		}
	}
}