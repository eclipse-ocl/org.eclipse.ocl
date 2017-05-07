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
import org.eclipse.ocl.pivot.internal.values.CollectionStrategy;
import org.eclipse.ocl.pivot.utilities.IndexableIterable;
import org.eclipse.ocl.pivot.values.BaggableIterator;
import org.eclipse.ocl.pivot.values.CollectionValue;

/**
 * A LazyIterable provides a polymorphic lazy mutable Collection implementation.
 *
 * The collection is populated lazily from a source iterator.
 *
 * The collection behaviour is defined by its collectionStrategy.
 *
 * Identification of unique elements for Sets is performed by an equalsStrategy, which may be very simple when the contents
 * is guaranteed to have the same OCL/Java semantics, but more complicated for types with distinct semantics.
 *
 * Mutable activities may be used if the caller guarantees that there are no consumers of the unmutated collection.
 *
 * Lazy evaluation is incompatible with invalid values, therefore the caller must guarantee that no future invalid
 * value may occur thaat would invalidate the earlier lazy results.
 *
 * @since 1.3
 */
public class LazyIterable<@Nullable E> implements IndexableIterable<E>
{
	/**
	 * AbstractCollectionStrategy provides the mandatory/internal implementation of CollectionStrategy.
	 */
	protected static abstract class AbstractCollectionStrategy implements CollectionStrategy
	{
		protected final @NonNull String kind;

		protected AbstractCollectionStrategy(@NonNull String kind) {
			this.kind = kind;
		}

		/**
		 * Add count of anElement to the lazyIterable updating element occurrence counts.
		 *
		 * The default implementation is that for a Set. Bag and Sequence override.
		 */
		protected <@Nullable E1> void addTo(@NonNull LazyIterable<E1> lazyIterable, @Nullable E1 anElement, int count) {
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

		/**
		 * Append count of anElement to the lazyIterable updating element occurrence counts.
		 *
		 * The default implementation re-uses addTo. OrderedSet overrides.
		 */
		protected <@Nullable E1> void appendTo(@NonNull LazyIterable<E1> lazyIterable, @Nullable E1 anElement, int count) {
			addTo( lazyIterable, anElement, count);
		}

		/**
		 * Convert lazyIterable to bag-form; counted but still deterministic elements.
		 *
		 * The default implementation does nothing. Sequence overrides.
		 */
		protected <@Nullable E1> void asBag(@NonNull LazyIterable<E1> lazyIterable) {}

		/**
		 * Convert lazyIterable to set-form; unique but still deterministic elements.
		 *
		 * The default implementation does nothing. Bag, Sequence override. Bag, Unique override.
		 */
		protected <@Nullable E1> void asSequence(@NonNull LazyIterable<E1> lazyIterable) {}

		/**
		 * Convert lazyIterable to set-form; unique but still deterministic elements.
		 *
		 * The default implementation does nothing. Bag, Sequence override.
		 */
		protected <@Nullable E1> void asUnique(@NonNull LazyIterable<E1> lazyIterable) {}

		/**
		 * Return true if lazyIterable contains anElement.
		 *
		 * The default implementation is that for a Set or Bag. Sequence overrides.
		 */
		protected <@Nullable E1> @NonNull Boolean contains(@NonNull LazyIterable<E1> lazyIterable, @Nullable Object anElement) {
			Map<@Nullable E1, @NonNull ElementCount> mapOfElement2elementCount = lazyIterable.getMapOfElement2elementCount();
			ElementCount elementCount = mapOfElement2elementCount.get(anElement);
			return elementCount != null ? Boolean.TRUE : Boolean.FALSE;
		}

		/**
		 * Return the number of occurrences of anElement in lazyIterable.
		 *
		 * The default implementation is that for a Set or Bag. Sequence overrides.
		 */
		protected <@Nullable E1> int count(@NonNull LazyIterable<E1> lazyIterable, @Nullable Object anElement) {
			Map<@Nullable E1, @NonNull ElementCount> mapOfElement2elementCount = lazyIterable.getMapOfElement2elementCount();
			ElementCount elementCount = mapOfElement2elementCount.get(anElement);
			return elementCount != null ? elementCount.intValue() : 0;
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

		/**
		 * Remove count of anElement from the lazyIterable updating element occurrence counts.
		 *
		 * The default implementation is that for a Set. Bag and Sequence override.
		 */
		protected <@Nullable E1> void removeFrom(@NonNull LazyIterable<E1> lazyIterable, @Nullable E1 anElement, int count) {
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

		@Override
		public String toString() {
			return getClass().getSimpleName();
		}
	}

	public static class BagStrategy extends AbstractCollectionStrategy
	{
		public static final @NonNull BagStrategy INSTANCE = new BagStrategy();

		private BagStrategy() {
			super(TypeId.BAG_NAME);
		}

		@Override
		protected <@Nullable E1> void addTo(@NonNull LazyIterable<E1> lazyIterable, @Nullable E1 anElement, int count) {
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
		protected <@Nullable E1> void asSequence(@NonNull LazyIterable<E1> lazyIterable) {
			Map<@Nullable E1, @NonNull ElementCount> mapOfElement2elementCount = lazyIterable.createMapOfElement2elementCount();
			List<@Nullable E1> listOfElements = lazyIterable.createListOfElements();
			for (@Nullable E1 anElement : lazyIterable.lazyListOfElements) {
				ElementCount elementCount = mapOfElement2elementCount.get(anElement);
				assert elementCount != null;
				for (int i = elementCount.intValue(); i > 0; --i) {
					listOfElements.add(anElement);
				}
			}
			lazyIterable.lazyListOfElements = listOfElements;
			lazyIterable.lazyMapOfElement2elementCount = null;
			lazyIterable.size = listOfElements.size();
		}

		@Override
		protected <@Nullable E1> void asUnique(@NonNull LazyIterable<E1> lazyIterable) {
			Map<@Nullable E1, @NonNull ElementCount> mapOfElement2elementCount = lazyIterable.getMapOfElement2elementCount();
			for (@NonNull ElementCount elementCount : mapOfElement2elementCount.values()) {
				elementCount.setValue(1);
			}
			lazyIterable.size = mapOfElement2elementCount.size();
		}

		@Override
		public boolean isBag() {
			return true;
		}

		@Override
		protected <@Nullable E1> void removeFrom(@NonNull LazyIterable<E1> lazyIterable, @Nullable E1 anElement, int count) {
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

	/**
	 * BaseCollectionStrategy is used when an abstract OCL Collection is in use; typically when boxing/unboxing
	 * abstract operation parameter arguments.
	 */
	public static class BaseCollectionStrategy extends OrderedStrategy
	{
		public static final @NonNull BaseCollectionStrategy INSTANCE = new BaseCollectionStrategy();

		private BaseCollectionStrategy() {
			super(TypeId.COLLECTION_NAME);
		}
	}

	public static abstract class OrderedStrategy extends AbstractCollectionStrategy
	{
		protected OrderedStrategy(@NonNull String kind) {
			super(kind);
		}

		@Override
		protected <@Nullable E1> void addTo(@NonNull LazyIterable<E1> lazyIterable, @Nullable E1 anElement, int count) {
			for (int i = count; i > 0; i--) {
				lazyIterable.lazyListOfElements.add(anElement);
				lazyIterable.size++;
			}
		}

		@Override
		public boolean isOrdered() {
			return true;
		}

		@Override
		protected <@Nullable E1> void removeFrom(@NonNull LazyIterable<E1> lazyIterable, @Nullable E1 anElement, int count) {
			for (int i = count; i > 0; i--) {
				if (!lazyIterable.lazyListOfElements.remove(anElement)) {
					break;
				}
				lazyIterable.size--;
			}
		}
	}

	public static class OrderedSetStrategy extends UniqueStrategy
	{
		public static final @NonNull OrderedSetStrategy INSTANCE = new OrderedSetStrategy();

		private OrderedSetStrategy() {
			super(TypeId.ORDERED_SET_NAME);
		}

		/**
		 * Append count of anElement to the lazyIterable updating element occurrence counts. If
		 * already present the old value is displaced. The new value goes at the end.
		 */
		@Override
		protected <@Nullable E1> void appendTo(@NonNull LazyIterable<E1> lazyIterable, @Nullable E1 anElement, int count) {
			if (count > 0) {
				Map<@Nullable E1, @NonNull ElementCount> lazyMapOfElement2elementCount2 = lazyIterable.lazyMapOfElement2elementCount;
				assert lazyMapOfElement2elementCount2 != null;
				ElementCount oldElementCount = lazyMapOfElement2elementCount2.put(anElement, SetElementCount.ONE);
				if (oldElementCount != null) {
					lazyIterable.lazyListOfElements.remove(anElement);
					lazyIterable.lazyListOfElements.add(anElement);
				}
				else {
					lazyIterable.lazyListOfElements.add(anElement);
					lazyIterable.size++;
				}
			}
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

	public static class SequenceStrategy extends OrderedStrategy
	{
		public static final @NonNull SequenceStrategy INSTANCE = new SequenceStrategy();

		private SequenceStrategy() {
			super(TypeId.SEQUENCE_NAME);
		}

		@Override
		protected <@Nullable E1> void asBag(@NonNull LazyIterable<E1> lazyIterable) {
			Map<@Nullable E1, @NonNull ElementCount> mapOfElement2elementCount = lazyIterable.createMapOfElement2elementCount();
			for (@Nullable E1 element : lazyIterable.lazyListOfElements) {
				mapOfElement2elementCount.put(element, SetElementCount.ONE);
			}
			lazyIterable.lazyMapOfElement2elementCount = mapOfElement2elementCount;
		}

		@Override
		protected <@Nullable E1> void asUnique(@NonNull LazyIterable<E1> lazyIterable) {
			Map<@Nullable E1, @NonNull ElementCount> mapOfElement2elementCount = new HashMap<>();
			List<@Nullable E1> lazyListOfElements = lazyIterable.createListOfElements();
			for (@Nullable E1 anElement : lazyIterable.lazyListOfElements) {
				if (!mapOfElement2elementCount.containsKey(anElement)) {
					lazyListOfElements.add(anElement);
					mapOfElement2elementCount.put(anElement, SetElementCount.ONE);
				}
			}
			lazyIterable.lazyListOfElements = lazyListOfElements;
			lazyIterable.size = mapOfElement2elementCount.size();
		}

		@Override
		protected <@Nullable E1> @NonNull Boolean contains(@NonNull LazyIterable<E1> lazyIterable, @Nullable Object anElement) {
			List<@Nullable E1> listOfElements = lazyIterable.getListOfElements();
			EqualsStrategy equalsStrategy = lazyIterable.equalsStrategy;
			for (Object element : listOfElements) {
				if (equalsStrategy.isEqual(element, anElement)) {
					return Boolean.TRUE;
				}
			}
			return Boolean.FALSE;
		}

		@Override
		protected <@Nullable E1> int count(@NonNull LazyIterable<E1> lazyIterable, @Nullable Object anElement) {
			List<@Nullable E1> listOfElements = lazyIterable.getListOfElements();
			EqualsStrategy equalsStrategy = lazyIterable.equalsStrategy;
			int count = 0;
			for (Object element : listOfElements) {
				if (equalsStrategy.isEqual(element, anElement)) {
					count++;
				}
			}
			return count;
		}

		@Override
		public boolean isSequence() {
			return true;
		}
	}

	public static class SetStrategy extends UniqueStrategy
	{
		public static final @NonNull SetStrategy INSTANCE = new SetStrategy();

		private SetStrategy() {
			super(TypeId.SET_NAME);
		}

		@Override
		public boolean isSet() {
			return true;
		}
	}

	public static abstract class UniqueStrategy extends AbstractCollectionStrategy
	{
		protected UniqueStrategy(@NonNull String kind) {
			super(kind);
		}

		@Override
		protected <@Nullable E1> void asSequence(@NonNull LazyIterable<E1> lazyIterable) {
			lazyIterable.lazyMapOfElement2elementCount = null;
		}

		@Override
		public boolean isUnique() {
			return true;
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
				return (index < size) || sourceIterator.hasNext();
			}
		}

		@Override
		public int hasNextCount() {
			if (index < size) {
				return 1;
			}
			synchronized (LazyIterable.this) {
				return (index < size) || sourceIterator.hasNext() ? 1 : 0;
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
			return sourceIterator.toString();
		}
	}

	public static final @NonNull CollectionStrategy BAG_STRATEGY = BagStrategy.INSTANCE;
	public static final @NonNull CollectionStrategy COLLECTION_STRATEGY = BaseCollectionStrategy.INSTANCE;
	public static final @NonNull CollectionStrategy ORDERED_SET_STRATEGY = OrderedSetStrategy.INSTANCE;
	public static final @NonNull CollectionStrategy SEQUENCE_STRATEGY = SequenceStrategy.INSTANCE;
	public static final @NonNull CollectionStrategy SET_STRATEGY = SetStrategy.INSTANCE;

	public static @Nullable Map<@NonNull Class<?>, @NonNull Integer> debugCollectionClass2lazyList = null;
	public static @Nullable Map<@NonNull Class<?>, @NonNull Integer> debugCollectionClass2lazyMap = null;

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

	public static @NonNull CollectionStrategy getCollectionStrategy(@NonNull CollectionTypeId typeId) {
		typeId = typeId.getGeneralizedId();
		if (typeId == TypeId.BAG) {
			return BAG_STRATEGY;
		}
		//		else if (typeId == TypeId.COLLECTION) {
		//			return "COLLECTION";
		//		}
		else if (typeId == TypeId.ORDERED_SET) {
			return ORDERED_SET_STRATEGY;
		}
		else if (typeId == TypeId.SEQUENCE) {
			return SEQUENCE_STRATEGY;
		}
		else if (typeId == TypeId.SET) {
			return SET_STRATEGY;
		}
		//		else if (typeId == TypeId.UNIQUE_COLLECTION) {
		//			return "UNIQUE_COLLECTION";
		//		}
		else {
			//			return null;
			//			throw new UnsupportedOperationException();
			return COLLECTION_STRATEGY;
		}
	}


	/**
	 * The iterator that provides the elements to be cached.
	 */
	private final @NonNull Iterator<E> sourceIterator;

	private @NonNull AbstractCollectionStrategy collectionStrategy;

	private final @NonNull EqualsStrategy equalsStrategy;

	/**
	 * The lazily cached elements obtained by iterating sourceIterator.
	 */
	private @NonNull List<E> lazyListOfElements;	// ArrayList reallocates arrays; could be better to do so ourselves with a smart estimatedSize()

	/**
	 * The lazily cached elements obtained by iterating sourceIterator.
	 */
	private @Nullable Map<E, @NonNull ElementCount> lazyMapOfElement2elementCount = null;

	/**
	 * The number of elements in the collection. For a Sequence, this is lazyListOfElements.size().
	 * For a Set/OrderedSet it is lazyMapOfElement2elementCount.keySet().size(). For a Bag it is
	 * the sum of lazyMapOfElement2elementCount.values().
	 */
	private int size = 0;

	public LazyIterable(@NonNull Iterator<E> sourceIterator, @NonNull CollectionStrategy collectionStrategy, @NonNull EqualsStrategy equalsStrategy) {
		this.sourceIterator = sourceIterator;
		this.collectionStrategy = (AbstractCollectionStrategy) collectionStrategy;
		this.equalsStrategy = equalsStrategy;
		lazyListOfElements = createListOfElements();
		if (!collectionStrategy.isSequence()) {
			lazyMapOfElement2elementCount = createMapOfElement2elementCount();
		}
	}

	public @NonNull Boolean contains(@Nullable Object value) {
		return collectionStrategy.contains(this, value);
	}

	public int count(Object value) {
		return collectionStrategy.count(this, value);
	}

	protected @NonNull List<E> createListOfElements() {
		Map<@NonNull Class<?>, @NonNull Integer> debugCollectionClass2lazyList2 = debugCollectionClass2lazyList;
		if (debugCollectionClass2lazyList2 != null) {
			Class<?> collectionClass = sourceIterator.getClass();
			Integer count = debugCollectionClass2lazyList2.get(collectionClass);
			count = count != null ? count+1 : 1;
			debugCollectionClass2lazyList2.put(collectionClass, count);
		}
		return new ArrayList<>();
	}

	protected @NonNull Map<@Nullable E, @NonNull ElementCount> createMapOfElement2elementCount() {
		Map<@NonNull Class<?>, @NonNull Integer> debugCollectionClass2lazyMap2 = debugCollectionClass2lazyMap;
		if (debugCollectionClass2lazyMap2 != null) {
			Class<?> collectionClass = sourceIterator.getClass();
			Integer count = debugCollectionClass2lazyMap2.get(collectionClass);
			count = count != null ? count+1 : 1;
			debugCollectionClass2lazyMap2.put(collectionClass, count);
		}
		return new HashMap<>();
	}

	@Override
	public boolean equals(Object obj) {
		throw new UnsupportedOperationException();	// This support class is not intended for more general use.
	}

	@Override
	public synchronized E get(int javaIndex) {
		//
		//	Ensure that sufficient of source has been read to reach the required index.
		//
		if (lazyListOfElements.size() <= javaIndex) {
			if (sourceIterator instanceof BaggableIterator) {
				BaggableIterator<@Nullable E> baggableIterator = (BaggableIterator<@Nullable E>)sourceIterator;
				for (int nextCount; ((nextCount = baggableIterator.hasNextCount()) > 0) && (lazyListOfElements.size() <= javaIndex); ) {
					collectionStrategy.addTo(this, sourceIterator.next(), nextCount);
				}
			}
			else {
				while (sourceIterator.hasNext() && (lazyListOfElements.size() <= javaIndex)) {
					collectionStrategy.addTo(this, sourceIterator.next(), 1);
				}
			}
		}
		//
		//	Return the required index (NB determinstic unqiue ordered index for Bag as well as Unique collections).
		//
		return lazyListOfElements.get(javaIndex);
	}

	public @NonNull CollectionStrategy getCollectionStrategy() {
		return collectionStrategy;
	}

	/**
	 * Ensure that all lazy iterations have completed and then return a list of all elements.
	 */
	public synchronized @NonNull List<E> getListOfElements() {
		if (sourceIterator instanceof BaggableIterator) {
			BaggableIterator<@Nullable E> baggableIterator = (BaggableIterator<@Nullable E>)sourceIterator;
			for (int nextCount; (nextCount = baggableIterator.hasNextCount()) > 0; ) {
				collectionStrategy.addTo(this, sourceIterator.next(), nextCount);
			}
		}
		else {
			while (sourceIterator.hasNext()) {
				collectionStrategy.addTo(this, sourceIterator.next(), 1);
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
			assert collectionStrategy.isSequence();
			Map<@NonNull Class<?>, @NonNull Integer> debugCollectionClass2lazyMap2 = debugCollectionClass2lazyMap;
			if (debugCollectionClass2lazyMap2 != null) {
				Class<?> collectionClass = sourceIterator.getClass();
				Integer count = debugCollectionClass2lazyMap2.get(collectionClass);
				count = count != null ? count+1 : 1;
				debugCollectionClass2lazyMap2.put(collectionClass, count);
			}
			lazyMapOfElement2elementCount2 = new HashMap<>();
			for (@Nullable E element : lazyListOfElements) {
				lazyMapOfElement2elementCount2.put(element, SetElementCount.ONE);
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
		if (collectionStrategy.isBag()) {
			Map<E, @NonNull ElementCount> lazyMapOfElement2elementCount2 = lazyMapOfElement2elementCount;
			assert lazyMapOfElement2elementCount2 != null;
			if (sourceIterator.hasNext()) {
				return new LazyBaggableIterator<>(this);
			}
			else {
				return new ImmutableBaggableIterator<>(lazyListOfElements, lazyMapOfElement2elementCount2);
			}
		}
		else {
			if (sourceIterator.hasNext()) {
				return new LazyNonBaggableIterator();
			}
			else {
				return new ImmutableNonBaggableIterator<>(lazyListOfElements);
			}
		}
	}

	public @NonNull CollectionValue mutableAppend(@NonNull CollectionValue leftCollectionValue, @Nullable E rightValue) {
		collectionStrategy.appendTo(this, rightValue, 1);
		return leftCollectionValue;
	}

	public @NonNull CollectionValue mutableAppendAll(@NonNull CollectionValue leftCollectionValue, @NonNull Iterator<@Nullable E> rightIterator) {
		if ((rightIterator instanceof BaggableIterator<?>) && !collectionStrategy.isSequence()) {
			BaggableIterator<?> baggableIterator = (BaggableIterator<?>)rightIterator;
			for (int nextCount; (nextCount = baggableIterator.hasNextCount()) > 0; ) {
				collectionStrategy.appendTo(this, rightIterator.next(), nextCount);
			}
		}
		else {
			while (rightIterator.hasNext()) {
				collectionStrategy.appendTo(this, rightIterator.next(), 1);
			}
		}
		return leftCollectionValue;
	}

	public @NonNull CollectionValue mutableAsBag(@NonNull CollectionValue collectionValue) {
		collectionStrategy.asBag(this);
		collectionStrategy = BagStrategy.INSTANCE;
		return collectionValue;
	}

	public @NonNull CollectionValue mutableAsOrderedSet(@NonNull CollectionValue collectionValue) {
		collectionStrategy.asUnique(this);
		collectionStrategy = OrderedSetStrategy.INSTANCE;
		return collectionValue;
	}

	public @NonNull CollectionValue mutableAsSequence(@NonNull CollectionValue collectionValue) {
		collectionStrategy.asSequence(this);
		collectionStrategy = SequenceStrategy.INSTANCE;
		return collectionValue;
	}

	public @NonNull CollectionValue mutableAsSet(@NonNull CollectionValue collectionValue) {
		collectionStrategy.asUnique(this);
		collectionStrategy = SetStrategy.INSTANCE;
		return collectionValue;
	}

	public @NonNull CollectionValue mutableExcluding(@NonNull CollectionValue leftCollectionValue, E rightValue) {
		collectionStrategy.removeFrom(this, rightValue, 1);
		return leftCollectionValue;
	}

	public @NonNull CollectionValue mutableExcludingAll(@NonNull CollectionValue leftCollectionValue, @NonNull Iterator<E> rightIterator) {
		if ((rightIterator instanceof BaggableIterator<?>) && !collectionStrategy.isSequence()) {
			BaggableIterator<?> baggableIterator = (BaggableIterator<?>)rightIterator;
			for (int nextCount; (nextCount = baggableIterator.hasNextCount()) > 0; ) {
				collectionStrategy.removeFrom(this, rightIterator.next(), nextCount);
			}
		}
		else {
			while (rightIterator.hasNext()) {
				collectionStrategy.removeFrom(this, rightIterator.next(), 1);
			}
		}
		return leftCollectionValue;
	}

	public @NonNull CollectionValue mutableIncluding(@NonNull CollectionValue leftCollectionValue, E rightValue) {
		collectionStrategy.addTo(this, rightValue, 1);
		return leftCollectionValue;
	}

	public @NonNull CollectionValue mutableIncludingAll(@NonNull CollectionValue leftCollectionValue, @NonNull Iterator<E> rightIterator) {
		if ((rightIterator instanceof BaggableIterator<?>) && !collectionStrategy.isSequence()) {
			BaggableIterator<?> baggableIterator = (BaggableIterator<?>)rightIterator;
			for (int nextCount; (nextCount = baggableIterator.hasNextCount()) > 0; ) {
				collectionStrategy.addTo(this, rightIterator.next(), nextCount);
			}
		}
		else {
			while (rightIterator.hasNext()) {
				collectionStrategy.addTo(this, rightIterator.next(), 1);
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
		collectionStrategy = isUnique ? SetStrategy.INSTANCE : BagStrategy.INSTANCE;
		if (rightIterator instanceof BaggableIterator<?>) {
			BaggableIterator<@Nullable E> baggableIterator = (BaggableIterator<@Nullable E>)rightIterator;
			for (int rightCount; (rightCount = baggableIterator.hasNextCount()) > 0; ) {
				E rightValue = baggableIterator.next();
				ElementCount leftElementCount = savedMapOfElement2elementCount.get(rightValue);
				if (leftElementCount != null) {
					collectionStrategy.addTo(this, rightValue, Math.min(leftElementCount.intValue(), rightCount));
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
						collectionStrategy.addTo(this, rightValue, 1);
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
		collectionStrategy = isUnique ? SetStrategy.INSTANCE : BagStrategy.INSTANCE;
		if (rightIterator instanceof BaggableIterator<?>) {
			BaggableIterator<@Nullable E> baggableIterator = (BaggableIterator<@Nullable E>)rightIterator;
			for (int rightCount; (rightCount = baggableIterator.hasNextCount()) > 0; ) {
				collectionStrategy.addTo(this, baggableIterator.next(), rightCount);
			}
		}
		else {
			while (rightIterator.hasNext()) {
				collectionStrategy.addTo(this, rightIterator.next(), 1);
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
		s.append(collectionStrategy.getKind());
		s.append("{");
		if (sourceIterator.hasNext()) {
			s.append("«future»");
		}
		else {
			appendBagIterable(s, lazyListOfElements, lazyMapOfElement2elementCount);
		}
		s.append("}");
		return s.toString();
	}
}