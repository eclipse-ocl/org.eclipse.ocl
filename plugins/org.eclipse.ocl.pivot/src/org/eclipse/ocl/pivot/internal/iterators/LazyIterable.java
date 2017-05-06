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
package org.eclipse.ocl.pivot.internal.iterators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.values.CollectionFactory;
import org.eclipse.ocl.pivot.utilities.IndexableIterable;
import org.eclipse.ocl.pivot.values.BaggableIterator;
import org.eclipse.ocl.pivot.values.CollectionValue;

/**
 * A LazyIterable provides a lazy cache of the elements of an Iterator so that the elements are
 * available for access by more than one Iterator, and also so that the elements can be accessed
 * by iteration index.
 *
 * @since 1.3
 */
public class LazyIterable<@Nullable E> implements IndexableIterable<E>
{
	protected static abstract class AbstractCollectionFactory implements CollectionFactory
	{
		protected final @NonNull String kind;
		//		protected final boolean isOrdered;
		//		protected final boolean isUnique;

		protected AbstractCollectionFactory(@NonNull String kind) {
			this.kind = kind;
			//			this.isOrdered = isOrdered;
			//			this.isUnique = isUnique;
		}

		@Override
		public @NonNull String getKind() {
			return kind;
		}

		@Override
		public boolean isBag() {
			return false; // !isUnique && !isOrdered;
		}

		@Override
		public boolean isOrdered() {
			return false; // isOrdered;
		}

		@Override
		public boolean isOrderedSet() {
			return false; // isUnique && isOrdered;
		}

		@Override
		public boolean isSequence() {
			return false; // !isUnique && isOrdered;
		}

		@Override
		public boolean isSet() {
			return false; // isUnique && !isOrdered;
		}

		@Override
		public boolean isUnique() {
			return false; // isUnique;
		}

		@Override
		public String toString() {
			return getClass().getSimpleName();
		}
	}

	public static class BagFactory extends AbstractCollectionFactory
	{
		private BagFactory() {
			super(TypeId.BAG_NAME);
		}

		@Override
		public <@Nullable E1> void addTo(@NonNull LazyIterable<E1> lazyIterable, @Nullable E1 anElement, int count) {
			if (count > 0) {
				Map<@Nullable E1, @NonNull ElementCount> lazyMapOfElement2elementCount2 = lazyIterable.lazyMapOfElement2elementCount;
				assert lazyMapOfElement2elementCount2 != null;
				BagElementCount newElementCount = new BagElementCount(count);
				ElementCount oldElementCount = lazyMapOfElement2elementCount2.put(anElement, newElementCount);
				if (oldElementCount != null) {
					newElementCount.setValue(count + oldElementCount.intValue());
				}
				else {
					lazyIterable.lazyListOfElements.add(anElement);
				}
				lazyIterable.size++;
			}
		}

		@Override
		public boolean isBag() {
			return true;
		}

		@Override
		public <@Nullable E1> void removeFrom(@NonNull LazyIterable<E1> lazyIterable, @Nullable E1 anElement, int count) {
			if (count > 0) {
				Map<@Nullable E1, @NonNull ElementCount> lazyMapOfElement2elementCount2 = lazyIterable.lazyMapOfElement2elementCount;
				if (lazyMapOfElement2elementCount2 != null) {
					ElementCount oldElementCount = lazyMapOfElement2elementCount2.get(anElement);
					if (oldElementCount != null) {
						int oldCount = oldElementCount.intValue();
						if (oldCount <= count) {
							lazyMapOfElement2elementCount2.remove(anElement);
							lazyIterable.lazyListOfElements.remove(anElement);
							lazyIterable.size -= oldCount;
						}
						else {
							oldElementCount.setValue(oldCount - count);
							lazyIterable.size -= count;
						}
					}
				}
			}
		}
	}

	public static class BaseCollectionFactory extends OrderedFactory
	{
		private BaseCollectionFactory() {
			super(TypeId.COLLECTION_NAME);
		}
	}

	public static abstract class OrderedFactory extends AbstractCollectionFactory
	{
		protected OrderedFactory(@NonNull String kind) {
			super(kind);
		}

		@Override
		public <@Nullable E1> void addTo(@NonNull LazyIterable<E1> lazyIterable, @Nullable E1 anElement, int count) {
			for (int i = count; i > 0; i--) {
				lazyIterable.lazyListOfElements.add(anElement);
				lazyIterable.size++;
			}
			//			return false;
		}

		@Override
		public boolean isOrdered() {
			return true;
		}

		@Override
		public <@Nullable E1> void removeFrom(@NonNull LazyIterable<E1> lazyIterable, @Nullable E1 anElement, int count) {
			for (int i = count; i > 0; i--) {
				if (!lazyIterable.lazyListOfElements.remove(anElement)) {
					break;
				}
				lazyIterable.size--;
			}
		}
	}

	public static class OrderedSetFactory extends UniqueFactory
	{
		private OrderedSetFactory() {
			super(TypeId.ORDERED_SET_NAME);
		}

		@Override
		public boolean isOrdered() {
			return true;
		}

		@Override
		public boolean isOrderedSet() {
			return true;
		}
	}

	public static class SequenceFactory extends OrderedFactory
	{
		private SequenceFactory() {
			super(TypeId.SEQUENCE_NAME);
		}

		@Override
		public boolean isSequence() {
			return true;
		}
	}

	public static class SetFactory extends UniqueFactory
	{
		private SetFactory() {
			super(TypeId.SET_NAME);
		}

		@Override
		public boolean isSet() {
			return true;
		}
	}

	public static abstract class UniqueFactory extends AbstractCollectionFactory
	{
		protected UniqueFactory(@NonNull String kind) {
			super(kind);
		}

		@Override
		public <@Nullable E1> void addTo(@NonNull LazyIterable<E1> lazyIterable, @Nullable E1 anElement, int count) {
			if (count > 0) {
				Map<@Nullable E1, @NonNull ElementCount> lazyMapOfElement2elementCount2 = lazyIterable.lazyMapOfElement2elementCount;
				assert lazyMapOfElement2elementCount2 != null;
				ElementCount oldElementCount = lazyMapOfElement2elementCount2.put(anElement, SetElementCount.ONE);
				if (oldElementCount == null) {
					lazyIterable.lazyListOfElements.add(anElement);
					lazyIterable.size++;
				}
			}
		}

		@Override
		public boolean isUnique() {
			return true;
		}

		@Override
		public <@Nullable E1> void removeFrom(@NonNull LazyIterable<E1> lazyIterable, @Nullable E1 anElement, int count) {
			if (count > 0) {
				Map<@Nullable E1, @NonNull ElementCount> lazyMapOfElement2elementCount2 = lazyIterable.lazyMapOfElement2elementCount;
				if (lazyMapOfElement2elementCount2 != null) {
					ElementCount oldElementCount = lazyMapOfElement2elementCount2.remove(anElement);
					if (oldElementCount != null) {
						lazyIterable.lazyListOfElements.remove(anElement);
						lazyIterable.size--;
					}
				}
			}
		}
	}

	/**
	 * An ImmutableBaggableIterator provides better performance than the standard List Iterator by
	 * exploiting the immutability of a fully populated Iteration cache.
	 */
	private static class ImmutableBaggableIterator<@Nullable E> implements BaggableIterator<E>
	{
		private final @NonNull List<E> elements;
		private final @NonNull Map<E, @NonNull ? extends Number> element2elementCount;
		private final int size;
		private int elementIndex = 0;
		private int residualCount = 0;
		private E currentElement;
		private int nextCount = 0;

		public ImmutableBaggableIterator(@NonNull List<E> elements, @NonNull Map<E, @NonNull ? extends Number> element2elementCount) {
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
				currentElement = null;
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
	 * An ImmutableNonBaggableIterator provides better performance than the standard List Iterator by
	 * exploiting the immutability of a fully populated Iteration cache.
	 */
	private static class ImmutableNonBaggableIterator<@Nullable E> implements BaggableIterator<E>
	{
		private final @NonNull List<E> elements;
		private final int size;
		private int elementIndex = 0;

		public ImmutableNonBaggableIterator(@NonNull List<E> elements) {
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

	/**
	 * LazyBaggableIterator iterates over the Bag content returning each multiple element multiple times.
	 */
	private static class LazyBaggableIterator<@Nullable E> implements BaggableIterator<E>
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

		private LazyBaggableIterator(@NonNull LazyIterable<E> iterable) {
			this.map = iterable.getMapOfElement2elementCount();
			this.objectIterator = iterable.iterator();
			assert objectIterator.hasNext();
			currentObject = objectIterator.next();
			ElementCount count = map.get(currentObject);
			assert count != null;
			residualCount = count.intValue();
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
				residualCount = count.intValue();
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
	 * A LazyNonBaggableIterator support multiple access to the partially populated iteration cache provoking
	 * additional population as required.
	 */
	private class LazyNonBaggableIterator implements BaggableIterator<E>
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

	public static final @NonNull CollectionFactory BAG_FACTORY = new BagFactory();
	public static final @NonNull CollectionFactory COLLECTION_FACTORY = new BaseCollectionFactory();
	public static final @NonNull CollectionFactory ORDERED_SET_FACTORY = new OrderedSetFactory();
	public static final @NonNull CollectionFactory SEQUENCE_FACTORY = new SequenceFactory();
	public static final @NonNull CollectionFactory SET_FACTORY = new SetFactory();

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

	public static @NonNull CollectionFactory getCollectionFactory(@NonNull CollectionTypeId typeId) {
		typeId = typeId.getGeneralizedId();
		if (typeId == TypeId.BAG) {
			return BAG_FACTORY;
		}
		//		else if (typeId == TypeId.COLLECTION) {
		//			return "COLLECTION";
		//		}
		else if (typeId == TypeId.ORDERED_SET) {
			return ORDERED_SET_FACTORY;
		}
		else if (typeId == TypeId.SEQUENCE) {
			return SEQUENCE_FACTORY;
		}
		else if (typeId == TypeId.SET) {
			return SET_FACTORY;
		}
		//		else if (typeId == TypeId.UNIQUE_COLLECTION) {
		//			return "UNIQUE_COLLECTION";
		//		}
		else {
			//			return null;
			//			throw new UnsupportedOperationException();
			return COLLECTION_FACTORY;
		}
	}


	/**
	 * The iterator that provides the elements to be cached.
	 */
	private final @NonNull Iterator<E> internalIterator;

	private @NonNull CollectionFactory collectionFactory;

	private final @NonNull EqualsStrategy equalsStrategy;

	/**
	 * The lazily cached elements obtained by iterating internalIterator.
	 */
	private @NonNull List<E> lazyListOfElements = new ArrayList<>();	// ArrayList reallocates arrays; could be better to do so ourselves with a smart estimatedSize()

	/**
	 * The lazily cached elements obtained by iterating internalIterator.
	 */
	private @Nullable Map<E, @NonNull ElementCount> lazyMapOfElement2elementCount = null;

	/**
	 * The number of elements in the collection. For a Sequence, this is lazyListOfElements.size().
	 * For a Set/OrderedSet it is lazyMapOfElement2elementCount.keySet().size(). For a Bag it is
	 * the sum of lazyMapOfElement2elementCount.values().
	 */
	private int size = 0;
	/**
	 * @since 1.3
	 */
	public static @Nullable Map<@NonNull Class<?>, @NonNull Integer> debugCollectionClass2lazyList = null;

	/**
	 * @since 1.3
	 */
	public static @Nullable Map<@NonNull Class<?>, @NonNull Integer> debugCollectionClass2lazyMap = null;

	public LazyIterable(@NonNull Iterator<E> internalIterator, @NonNull CollectionFactory collectionFactory, @NonNull EqualsStrategy equalsStrategy) {
		this.internalIterator = internalIterator;
		this.collectionFactory = collectionFactory;
		this.equalsStrategy = equalsStrategy;
		Map<@NonNull Class<?>, @NonNull Integer> debugCollectionClass2lazyList2 = debugCollectionClass2lazyList;
		if (debugCollectionClass2lazyList2 != null) {
			Class<?> collectionClass = internalIterator.getClass();
			Integer count = debugCollectionClass2lazyList2.get(collectionClass);
			count = count != null ? count+1 : 1;
			debugCollectionClass2lazyList2.put(collectionClass, count);
		}
		if (!collectionFactory.isSequence()) {
			Map<@NonNull Class<?>, @NonNull Integer> debugCollectionClass2lazyMap2 = debugCollectionClass2lazyMap;
			if (debugCollectionClass2lazyMap2 != null) {
				Class<?> collectionClass = internalIterator.getClass();
				Integer count = debugCollectionClass2lazyMap2.get(collectionClass);
				count = count != null ? count+1 : 1;
				debugCollectionClass2lazyMap2.put(collectionClass, count);
			}
			lazyMapOfElement2elementCount = new HashMap<>();
		}
	}

	public @NonNull Boolean contains(@Nullable Object value) {
		if (collectionFactory.isSequence()) {
			for (Object element : getListOfElements()) {
				if (equalsStrategy.isEqual(element, value)) {
					return Boolean.TRUE;
				}
			}
			return Boolean.FALSE;
		}
		else {
			ElementCount elementCount = getMapOfElement2elementCount().get(value);
			return elementCount != null ? Boolean.TRUE : Boolean.FALSE;
		}
	}

	public int count(Object object) {
		if (collectionFactory.isSequence()) {
			int count = 0;
			for (Object element : getListOfElements()) {
				if (equalsStrategy.isEqual(element, object)) {
					count++;
				}
			}
			return count;
		}
		else {
			ElementCount elementCount = getMapOfElement2elementCount().get(object);
			if (elementCount == null) {
				return 0;
			}
			//			else if (collectionFactory.isUnique()) {
			//				return 1;
			//			}
			else {
				return elementCount.intValue();
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		throw new UnsupportedOperationException();	// This support class is not intended for more general use.
	}

	@Override
	public synchronized E get(int javaIndex) {
		if (lazyListOfElements.size() <= javaIndex) {
			if (internalIterator instanceof BaggableIterator) {
				BaggableIterator<@Nullable E> baggableIterator = (BaggableIterator<@Nullable E>)internalIterator;
				for (int nextCount; ((nextCount = baggableIterator.hasNextCount()) > 0) && (lazyListOfElements.size() <= javaIndex); ) {
					collectionFactory.addTo(this, internalIterator.next(), nextCount);
				}
			}
			else {
				while (internalIterator.hasNext() && (lazyListOfElements.size() <= javaIndex)) {
					collectionFactory.addTo(this, internalIterator.next(), 1);
				}
			}
		}
		return lazyListOfElements.get(javaIndex);
	}

	/**
	 * Ensure that all lazy iterations have completed and then return a list of all elements.
	 */
	public synchronized @NonNull List<E> getListOfElements() {
		if (internalIterator instanceof BaggableIterator) {
			BaggableIterator<@Nullable E> baggableIterator = (BaggableIterator<@Nullable E>)internalIterator;
			for (int nextCount; (nextCount = baggableIterator.hasNextCount()) > 0; ) {
				collectionFactory.addTo(this, internalIterator.next(), nextCount);
			}
		}
		else {
			while (internalIterator.hasNext()) {
				collectionFactory.addTo(this, internalIterator.next(), 1);
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
		if (lazyMapOfElement2elementCount2 == null) {			// Lazy creation is only needed for Sequences
			Map<Class<?>, Integer> debugCollectionClass2lazyMap2 = debugCollectionClass2lazyMap;
			if (debugCollectionClass2lazyMap2 != null) {
				Class<?> collectionClass = internalIterator.getClass();
				Integer count = debugCollectionClass2lazyMap2.get(collectionClass);
				count = count != null ? count+1 : 1;
				debugCollectionClass2lazyMap2.put(collectionClass, count);
			}
			lazyMapOfElement2elementCount2 = lazyMapOfElement2elementCount = new HashMap<>();
			for (@Nullable E element : lazyListOfElements) {
				collectionFactory.addTo(this, element, 1);
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
	public @NonNull BaggableIterator<E> iterator() {
		if (collectionFactory.isBag()) {
			Map<E, @NonNull ElementCount> lazyMapOfElement2elementCount2 = lazyMapOfElement2elementCount;
			assert lazyMapOfElement2elementCount2 != null;
			if (internalIterator.hasNext()) {
				return new LazyBaggableIterator<>(this);
			}
			else {
				return new ImmutableBaggableIterator<>(lazyListOfElements, lazyMapOfElement2elementCount2);
			}
		}
		else {
			if (internalIterator.hasNext()) {
				return new LazyNonBaggableIterator();
			}
			else {
				return new ImmutableNonBaggableIterator<>(lazyListOfElements);
			}
		}
	}

	public @NonNull CollectionValue mutableAsSet(@NonNull CollectionValue leftCollectionValue) {
		if (collectionFactory.isBag()) {
			Map<@Nullable E, @NonNull ElementCount> mapOfElement2elementCount = getMapOfElement2elementCount();
			for (@NonNull ElementCount elementCount : mapOfElement2elementCount.values()) {
				elementCount.setValue(1);
			}
			size = mapOfElement2elementCount.size();
		}
		else if (collectionFactory.isSequence()) {
			List<@Nullable E> savedListOfElements = lazyListOfElements;
			Map<@Nullable E, @NonNull ElementCount> mapOfElement2elementCount = new HashMap<>();
			lazyListOfElements = new ArrayList<>();
			for (@Nullable E anElement : savedListOfElements) {
				if (!mapOfElement2elementCount.containsKey(anElement)) {
					lazyListOfElements.add(anElement);
					mapOfElement2elementCount.put(anElement, SetElementCount.ONE);
				}
			}
			size = mapOfElement2elementCount.size();
		}
		collectionFactory = SET_FACTORY;
		return leftCollectionValue;
	}

	public @NonNull CollectionValue mutableExcluding(@NonNull CollectionValue leftCollectionValue, E rightValue) {
		collectionFactory.removeFrom(this, rightValue, 1);
		return leftCollectionValue;
	}

	public @NonNull CollectionValue mutableExcludingAll(@NonNull CollectionValue leftCollectionValue, @NonNull Iterator<E> rightIterator) {
		if ((rightIterator instanceof BaggableIterator<?>) && !collectionFactory.isSequence()) {
			BaggableIterator<?> baggableIterator = (BaggableIterator<?>)rightIterator;
			for (int nextCount; (nextCount = baggableIterator.hasNextCount()) > 0; ) {
				collectionFactory.removeFrom(this, rightIterator.next(), nextCount);
			}
		}
		else {
			while (rightIterator.hasNext()) {
				collectionFactory.removeFrom(this, rightIterator.next(), 1);
			}
		}
		return leftCollectionValue;
	}

	public @NonNull CollectionValue mutableIncluding(@NonNull CollectionValue leftCollectionValue, E rightValue) {
		collectionFactory.addTo(this, rightValue, 1);
		return leftCollectionValue;
	}

	public @NonNull CollectionValue mutableIncludingAll(@NonNull CollectionValue leftCollectionValue, @NonNull Iterator<E> rightIterator) {
		if ((rightIterator instanceof BaggableIterator<?>) && !collectionFactory.isSequence()) {
			BaggableIterator<?> baggableIterator = (BaggableIterator<?>)rightIterator;
			for (int nextCount; (nextCount = baggableIterator.hasNextCount()) > 0; ) {
				collectionFactory.addTo(this, rightIterator.next(), nextCount);
			}
		}
		else {
			while (rightIterator.hasNext()) {
				collectionFactory.addTo(this, rightIterator.next(), 1);
			}
		}
		return leftCollectionValue;
	}

	/**
	 * Return leftCollectionValue after modification to be the intersection of leftCollectionValue and rightIterator,
	 * This the underlying content of leftCollectionValue. If isUnique, the resulting intersection has unit counts
	 * rather than common minimum counts.
	 */
	public @NonNull CollectionValue mutableIntersection(@NonNull CollectionValue leftCollectionValue, @NonNull Iterator<@Nullable E> rightIterator, boolean isUnique) {
		assert leftCollectionValue.isUnique() || !leftCollectionValue.isOrdered();
		Map<@Nullable E, @NonNull ElementCount> savedMapOfElement2elementCount = getMapOfElement2elementCount();
		Map<@Nullable E, @NonNull ElementCount> lazyMapOfElement2elementCount2 = lazyMapOfElement2elementCount = new HashMap<>();
		lazyListOfElements = new ArrayList<>();
		size = 0;
		collectionFactory = isUnique ? SET_FACTORY : BAG_FACTORY;
		if (rightIterator instanceof BaggableIterator<?>) {
			BaggableIterator<@Nullable E> baggableIterator = (BaggableIterator<@Nullable E>)rightIterator;
			for (int rightCount; (rightCount = baggableIterator.hasNextCount()) > 0; ) {
				E rightValue = baggableIterator.next();
				ElementCount leftElementCount = savedMapOfElement2elementCount.get(rightValue);
				if (leftElementCount != null) {
					collectionFactory.addTo(this, rightValue, Math.min(leftElementCount.intValue(), rightCount));
				}
			}
		}
		else {
			while (rightIterator.hasNext()) {
				E rightValue = rightIterator.next();
				ElementCount leftElementCount = savedMapOfElement2elementCount.get(rightValue);
				if (leftElementCount != null) {
					ElementCount intersectionElementCount = lazyMapOfElement2elementCount2.get(rightValue);
					if ((intersectionElementCount == null) || (intersectionElementCount.intValue() < leftElementCount.intValue())) {
						collectionFactory.addTo(this, rightValue, 1);
					}
				}
			}
		}
		// NB the determinstic order is that of the right value; ?? should we re-instate the left order
		return leftCollectionValue;
	}

	/**
	 * Return leftCollectionValue after modification to be the union of leftCollectionValue and rightIterator,
	 * This the underlying content of leftCollectionValue. If isUnique, the resulting union has unit counts
	 * rather than sum counts.
	 */
	public @NonNull CollectionValue mutableUnion(@NonNull CollectionValue leftCollectionValue, @NonNull Iterator<@Nullable E> rightIterator, boolean isUnique) {
		assert leftCollectionValue.isUnique() || !leftCollectionValue.isOrdered();
		collectionFactory = isUnique ? SET_FACTORY : BAG_FACTORY;
		if (rightIterator instanceof BaggableIterator<?>) {
			BaggableIterator<@Nullable E> baggableIterator = (BaggableIterator<@Nullable E>)rightIterator;
			for (int rightCount; (rightCount = baggableIterator.hasNextCount()) > 0; ) {
				collectionFactory.addTo(this, baggableIterator.next(), rightCount);
			}
		}
		else {
			while (rightIterator.hasNext()) {
				collectionFactory.addTo(this, rightIterator.next(), 1);
			}
		}
		return leftCollectionValue;
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
		StringBuilder s = new StringBuilder();
		s.append(collectionFactory.getKind());
		s.append("{");
		if (internalIterator.hasNext()) {
			s.append("«future»");
		}
		else {
			appendBagIterable(s, lazyListOfElements, lazyMapOfElement2elementCount);
		}
		s.append("}");
		return s.toString();
	}
}