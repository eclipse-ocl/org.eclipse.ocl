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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.EnumerationLiteralId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.iterators.AppendAllIterator;
import org.eclipse.ocl.pivot.internal.iterators.AppendIterator;
import org.eclipse.ocl.pivot.internal.iterators.AsBagIterator;
import org.eclipse.ocl.pivot.internal.iterators.AsOrderedSetIterator;
import org.eclipse.ocl.pivot.internal.iterators.AsSequenceIterator;
import org.eclipse.ocl.pivot.internal.iterators.AsSetIterator;
import org.eclipse.ocl.pivot.internal.iterators.BagElementCount;
import org.eclipse.ocl.pivot.internal.iterators.ElementCount;
import org.eclipse.ocl.pivot.internal.iterators.EqualsStrategy;
import org.eclipse.ocl.pivot.internal.iterators.EqualsStrategy.SimpleEqualsStrategy;
import org.eclipse.ocl.pivot.internal.iterators.ExcludingAllIterator;
import org.eclipse.ocl.pivot.internal.iterators.ExcludingIterator;
import org.eclipse.ocl.pivot.internal.iterators.FlattenIterator;
import org.eclipse.ocl.pivot.internal.iterators.IncludingAllIterator;
import org.eclipse.ocl.pivot.internal.iterators.IncludingIterator;
import org.eclipse.ocl.pivot.internal.iterators.IntersectionIterator;
import org.eclipse.ocl.pivot.internal.iterators.PrependAllIterator;
import org.eclipse.ocl.pivot.internal.iterators.PrependIterator;
import org.eclipse.ocl.pivot.internal.iterators.SetElementCount;
import org.eclipse.ocl.pivot.internal.iterators.SubOrderedSetIterator;
import org.eclipse.ocl.pivot.internal.iterators.SubSequenceIterator;
import org.eclipse.ocl.pivot.internal.iterators.SymmetricDifferenceIterator;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.BaggableIterator;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.LazyCollectionValue;
import org.eclipse.ocl.pivot.values.LazyIterable;
import org.eclipse.ocl.pivot.values.LazyIterator;
import org.eclipse.ocl.pivot.values.MutableIterable;
import org.eclipse.ocl.pivot.values.TupleValue;
import org.eclipse.ocl.pivot.values.Value;

import com.google.common.collect.Lists;

/**
 * LazyCollectionValueImpl provides the common functionality for lazy evaluation using the hasNextCount/next
 * BaggableIterator protocol. Derived baggable iterators must implement getNextCount() to describe the next entry
 * by a callback to setNext().
 *
 * The LazyCollectionValueImpl may be used as a simple iterator by invoking lazyIterator() without invoking iterable().
 * If a usage of lazyIterator() is followed by another iteration a separate iteration repeats the original
 * caching results to avoid a third repeat..
 *
 * The LazyCollectionValueImpl may be used as a cached iterable by invoking iterable(). Derived
 * implementations that require memory of their output may invoke iterable() in their constructor. Multiple
 * calls to iterator() while the underlying iteration is in progress return a synchronized multi-access lazy
 * iterator. Call to iterator() after the underlying iteration has completed return a much more efficient
 * iterator. Concurrent iteration should be avoided whenever possible.
 *
 * The iterable is currently provided by a LazyIterable in order to preserve API compatibility. LazyIterable will
 * be folded in at the next major version change.
 *
 * Lazy evaluation is incompatible with invalid values, therefore the caller must guarantee that no future invalid
 * value may occur thaat would invalidate the earlier lazy results.
 *
 * @since 1.3
 */
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
public abstract class LazyCollectionValueImpl extends ValueImpl implements LazyCollectionValue, LazyIterator, MutableIterable
{
	/**
	 * Arbitrary nesting of lazy iterators can run out of stack, so eager iterables are needed every so often.
	 * Too often and memory is wasted. For one nested Sequence::including test, speed degraded increasingly below
	 * a limit of 15 daisy chained lazy iterators.
	 */
	public static int LAZY_DEPTH_TRAP = 15;

	@SuppressWarnings("serial")
	private static final class UnmodifiableEcoreObjects extends EcoreEList.UnmodifiableEList<@Nullable Object>
	{
		private static final /*@NonNull*/ EStructuralFeature unhangeableStructuralFeature;
		static {
			unhangeableStructuralFeature = EcoreFactory.eINSTANCE.createEAttribute();
			unhangeableStructuralFeature.setName("unchangeable");
			unhangeableStructuralFeature.setEType(EcorePackage.Literals.EOBJECT);
			unhangeableStructuralFeature.setLowerBound(0);
			unhangeableStructuralFeature.setUpperBound(-1);
			unhangeableStructuralFeature.setChangeable(false);
		}
		private UnmodifiableEcoreObjects(int size, @Nullable Object[] data) {
			super(null, unhangeableStructuralFeature, size, data);
		}

		@Override
		protected boolean useEquals() {
			return false;
		}
	}
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
		protected void addTo(@NonNull LazyCollectionValueImpl lazyIterable, @Nullable Object anElement, int count) {
			if (count > 0) {
				Map<@Nullable Object, @NonNull ElementCount> lazyMapOfElement2elementCount2 = lazyIterable.lazyMapOfElement2elementCount;
				assert lazyMapOfElement2elementCount2 != null;
				ElementCount oldElementCount = lazyMapOfElement2elementCount2.put(anElement, SetElementCount.ONE);
				if (oldElementCount == null) {
					List<@Nullable Object> lazyListOfElements2 = lazyIterable.lazyListOfElements;
					assert lazyListOfElements2 != null;
					lazyListOfElements2.add(anElement);
					lazyIterable.size++;
				}
			}
		}

		/**
		 * Append count of anElement to the lazyIterable updating element occurrence counts.
		 *
		 * The default implementation re-uses addTo. OrderedSet overrides.
		 */
		protected void appendTo(@NonNull LazyCollectionValueImpl lazyIterable, @Nullable Object anElement, int count) {
			addTo(lazyIterable, anElement, count);
		}

		/**
		 * Convert lazyIterable to bag-form; counted but still deterministic elements.
		 *
		 * The default implementation does nothing. Sequence overrides.
		 */
		protected void asBag(@NonNull LazyCollectionValueImpl lazyIterable) {}

		/**
		 * Convert lazyIterable to set-form; unique but still deterministic elements.
		 *
		 * The default implementation does nothing. Bag, Sequence override. Bag, Unique override.
		 */
		protected void asSequence(@NonNull LazyCollectionValueImpl lazyIterable) {}

		/**
		 * Convert lazyIterable to set-form; unique but still deterministic elements.
		 *
		 * The default implementation does nothing. Bag, Sequence override.
		 */
		protected void asUnique(@NonNull LazyCollectionValueImpl lazyIterable) {}

		/**
		 * Return true if lazyIterable contains anElement.
		 *
		 * The default implementation is that for a Set or Bag. Sequence overrides.
		 */
		protected @NonNull Boolean contains(@NonNull LazyCollectionValueImpl lazyIterable, @Nullable Object anElement) {
			Map<@Nullable Object, @NonNull ElementCount> mapOfElement2elementCount = lazyIterable.getMapOfElement2elementCount();
			ElementCount elementCount = mapOfElement2elementCount.get(anElement);
			return elementCount != null ? Boolean.TRUE : Boolean.FALSE;
		}

		@Override
		public @NonNull String getKind() {
			return kind;
		}

		/**
		 * Return the number of occurrences of anElement in lazyIterable.
		 *
		 * The default implementation is that for a Set or Bag. Sequence overrides.
		 */
		protected int intCount(@NonNull LazyCollectionValueImpl lazyIterable, @Nullable Object anElement) {
			Map<@Nullable Object, @NonNull ElementCount> mapOfElement2elementCount = lazyIterable.getMapOfElement2elementCount();
			ElementCount elementCount = mapOfElement2elementCount.get(anElement);
			return elementCount != null ? elementCount.intValue() : 0;
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
		protected void removeFrom(@NonNull LazyCollectionValueImpl lazyIterable, @Nullable Object anElement, int count) {
			if (count > 0) {
				Map<@Nullable Object, @NonNull ElementCount> lazyMapOfElement2elementCount2 = lazyIterable.lazyMapOfElement2elementCount;
				if (lazyMapOfElement2elementCount2 != null) {
					ElementCount oldElementCount = lazyMapOfElement2elementCount2.remove(anElement);
					if (oldElementCount != null) {
						List<@Nullable Object> lazyListOfElements2 = lazyIterable.lazyListOfElements;
						assert lazyListOfElements2 != null;
						lazyListOfElements2.remove(anElement);
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
		protected void addTo(@NonNull LazyCollectionValueImpl lazyIterable, @Nullable Object anElement, int count) {
			if (count > 0) {
				Map<@Nullable Object, @NonNull ElementCount> lazyMapOfElement2elementCount2 = lazyIterable.lazyMapOfElement2elementCount;
				assert lazyMapOfElement2elementCount2 != null;
				BagElementCount newElementCount = new BagElementCount(count);
				ElementCount oldElementCount = lazyMapOfElement2elementCount2.put(anElement, newElementCount);
				if (oldElementCount != null) {
					newElementCount.setValue(count + oldElementCount.intValue());
				}
				else {
					List<@Nullable Object> lazyListOfElements2 = lazyIterable.lazyListOfElements;
					assert lazyListOfElements2 != null;
					lazyListOfElements2.add(anElement);
				}
				lazyIterable.size++;
			}
		}

		@Override
		protected void asSequence(@NonNull LazyCollectionValueImpl lazyIterable) {
			Map<@Nullable Object, @NonNull ElementCount> mapOfElement2elementCount = lazyIterable.createMapOfElement2elementCount();
			List<@Nullable Object> listOfElements = lazyIterable.createListOfElements();
			List<@Nullable Object> lazyListOfElements2 = lazyIterable.lazyListOfElements;
			assert lazyListOfElements2 != null;
			for (@Nullable Object anElement : lazyListOfElements2) {
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
		protected void asUnique(@NonNull LazyCollectionValueImpl lazyIterable) {
			Map<@Nullable Object, @NonNull ElementCount> mapOfElement2elementCount = lazyIterable.getMapOfElement2elementCount();
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
		protected void removeFrom(@NonNull LazyCollectionValueImpl lazyIterable, @Nullable Object anElement, int count) {
			if (count > 0) {
				Map<@Nullable Object, @NonNull ElementCount> lazyMapOfElement2elementCount2 = lazyIterable.lazyMapOfElement2elementCount;
				if (lazyMapOfElement2elementCount2 != null) {
					ElementCount oldElementCount = lazyMapOfElement2elementCount2.get(anElement);
					if (oldElementCount != null) {
						int oldCount = oldElementCount.intValue();
						if (oldCount <= count) {
							lazyMapOfElement2elementCount2.remove(anElement);
							List<@Nullable Object> lazyListOfElements2 = lazyIterable.lazyListOfElements;
							assert lazyListOfElements2 != null;
							lazyListOfElements2.remove(anElement);
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
		protected void addTo(@NonNull LazyCollectionValueImpl lazyIterable, @Nullable Object anElement, int count) {
			List<@Nullable Object> lazyListOfElements2 = lazyIterable.lazyListOfElements;
			assert lazyListOfElements2 != null;
			for (int i = count; i > 0; i--) {
				lazyListOfElements2.add(anElement);
				lazyIterable.size++;
			}
		}

		@Override
		public boolean isOrdered() {
			return true;
		}

		@Override
		protected void removeFrom(@NonNull LazyCollectionValueImpl lazyIterable, @Nullable Object anElement, int count) {
			List<@Nullable Object> lazyListOfElements2 = lazyIterable.lazyListOfElements;
			assert lazyListOfElements2 != null;
			for (int i = count; i > 0; i--) {
				if (!lazyListOfElements2.remove(anElement)) {
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
		protected void appendTo(@NonNull LazyCollectionValueImpl lazyIterable, @Nullable Object anElement, int count) {
			if (count > 0) {
				Map<@Nullable Object, @NonNull ElementCount> lazyMapOfElement2elementCount2 = lazyIterable.lazyMapOfElement2elementCount;
				assert lazyMapOfElement2elementCount2 != null;
				ElementCount oldElementCount = lazyMapOfElement2elementCount2.put(anElement, SetElementCount.ONE);
				List<@Nullable Object> lazyListOfElements2 = lazyIterable.lazyListOfElements;
				assert lazyListOfElements2 != null;
				if (oldElementCount != null) {
					lazyListOfElements2.remove(anElement);
					lazyListOfElements2.add(anElement);
				}
				else {
					lazyListOfElements2.add(anElement);
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
		protected void asBag(@NonNull LazyCollectionValueImpl lazyIterable) {
			Map<@Nullable Object, @NonNull ElementCount> mapOfElement2elementCount = lazyIterable.createMapOfElement2elementCount();
			List<@Nullable Object> lazyListOfElements2 = lazyIterable.lazyListOfElements;
			assert lazyListOfElements2 != null;
			for (@Nullable Object element : lazyListOfElements2) {
				mapOfElement2elementCount.put(element, SetElementCount.ONE);
			}
			lazyIterable.lazyMapOfElement2elementCount = mapOfElement2elementCount;
		}

		@Override
		protected void asUnique(@NonNull LazyCollectionValueImpl lazyIterable) {
			Map<@Nullable Object, @NonNull ElementCount> mapOfElement2elementCount = new HashMap<>();
			List<@Nullable Object> newLazyListOfElements = lazyIterable.createListOfElements();
			List<@Nullable Object> oldLazyListOfElements = lazyIterable.lazyListOfElements;
			assert oldLazyListOfElements != null;
			for (@Nullable Object anElement : oldLazyListOfElements) {
				if (!mapOfElement2elementCount.containsKey(anElement)) {
					newLazyListOfElements.add(anElement);
					mapOfElement2elementCount.put(anElement, SetElementCount.ONE);
				}
			}
			lazyIterable.lazyListOfElements = newLazyListOfElements;
			lazyIterable.size = mapOfElement2elementCount.size();
		}

		@Override
		protected @NonNull Boolean contains(@NonNull LazyCollectionValueImpl lazyIterable, @Nullable Object anElement) {
			EqualsStrategy equalsStrategy2 = lazyIterable.equalsStrategy;
			for (Object element : lazyIterable.getListOfElements()) {
				if (equalsStrategy2.isEqual(element, anElement)) {
					return Boolean.TRUE;
				}
			}
			return Boolean.FALSE;
		}

		@Override
		protected int intCount(@NonNull LazyCollectionValueImpl lazyIterable, @Nullable Object anElement) {
			List<@Nullable Object> listOfElements = lazyIterable.getListOfElements();
			EqualsStrategy equalsStrategy2 = lazyIterable.equalsStrategy;
			int count = 0;
			for (Object element : listOfElements) {
				if (equalsStrategy2.isEqual(element, anElement)) {
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
		protected void asSequence(@NonNull LazyCollectionValueImpl lazyIterable) {
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
	private static class ImmutableBaggableIterator implements LazyIterator
	{
		private final @NonNull List<@Nullable Object> elements;
		private final @NonNull Map<@Nullable Object, @NonNull ? extends Number> element2elementCount;
		private final int size;
		private int elementIndex = 0;
		private int residualCount = 0;
		private @Nullable Object currentElement;
		private int nextCount = 0;

		public ImmutableBaggableIterator(@NonNull List<@Nullable Object> elements, @NonNull Map<@Nullable Object, @NonNull ? extends Number> element2elementCount) {
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
		public @Nullable Object next() {
			if (residualCount <= 0) {
				throw new NoSuchElementException();
			}
			@Nullable Object savedElement = currentElement;
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
	private static class ImmutableNonBaggableIterator implements LazyIterator
	{
		private final @NonNull List<@Nullable Object> elements;
		private final int size;
		private int elementIndex = 0;

		public ImmutableNonBaggableIterator(@NonNull List<@Nullable Object> elements) {
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
		public @Nullable Object next() {
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
	private static class LazyBaggableIterator implements LazyIterator
	{
		private final @NonNull Map<@Nullable Object, @NonNull ElementCount> map;
		private final @NonNull Iterator<@Nullable Object> objectIterator;
		private @Nullable Object currentObject;

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

		private LazyBaggableIterator(@NonNull LazyCollectionValueImpl iterable) {
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
		public @Nullable Object next() {
			if (residualCount <= 0) {
				throw new NoSuchElementException();
			}
			residualCount -= nextCount;
			if (residualCount > 0) {		// If iterating a bag element by element
				return currentObject;
			}
			if (objectIterator.hasNext()) {
				@Nullable Object savedObject = currentObject;
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
	private class LazyNonBaggableIterator implements LazyIterator
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
			synchronized (LazyCollectionValueImpl.this) {
				return (index < size) || inputIterator.hasNext();
			}
		}

		@Override
		public int hasNextCount() {
			if (index < size) {
				return 1;
			}
			synchronized (LazyCollectionValueImpl.this) {
				return (index < size) || inputIterator.hasNext() ? 1 : 0;
			}
		}

		@Override
		public final int hashCode() {
			throw new UnsupportedOperationException();	// This support class is not intended for more general use.
		}

		@Override
		public @Nullable Object next() {
			if (index < size) {
				List<@Nullable Object> lazyListOfElements2 = lazyListOfElements;
				assert lazyListOfElements2 != null;
				return lazyListOfElements2.get(index++);
			}
			@Nullable Object next = get(index);
			index++;			// After IndexOutOfBoundsException has been thrown
			return next;
		}

		@Override
		public String toString() {
			return inputIterator.toString();
		}
	}

	public static final @NonNull AbstractCollectionStrategy BAG_STRATEGY = BagStrategy.INSTANCE;
	public static final @NonNull AbstractCollectionStrategy COLLECTION_STRATEGY = BaseCollectionStrategy.INSTANCE;
	public static final @NonNull AbstractCollectionStrategy ORDERED_SET_STRATEGY = OrderedSetStrategy.INSTANCE;
	public static final @NonNull AbstractCollectionStrategy SEQUENCE_STRATEGY = SequenceStrategy.INSTANCE;
	public static final @NonNull AbstractCollectionStrategy SET_STRATEGY = SetStrategy.INSTANCE;

	//	public static @Nullable Map<@NonNull Class<?>, @NonNull Integer> debugCollectionClass2lazyList = null;
	//	public static @Nullable Map<@NonNull Class<?>, @NonNull Integer> debugCollectionClass2lazyMap = null;

	//	public static @Nullable Map<@NonNull Class<?>, @NonNull Integer> debugCollectionClass2lazy = null;
	//	public static @Nullable Map<@NonNull Class<?>, @NonNull Integer> debugCollectionClass2cached = null;
	//	public static @Nullable Map<@NonNull Class<?>, @NonNull Integer> debugCollectionClass2reiterated = null;

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

	public static void appendIterable(StringBuilder s, @NonNull Iterable<? extends Object> iterable, int lengthLimit) {
		s.append("{");
		boolean isFirst = true;
		for (Object element : iterable) {
			if (!isFirst) {
				s.append(",");
			}
			if (s.length() < lengthLimit) {
				ValueUtil.toString(element, s, lengthLimit-1);
			}
			else {
				s.append("...");
				break;
			}
			isFirst = false;
		}
		s.append("}");
	}

	public static @NonNull AbstractCollectionStrategy getCollectionStrategy(@NonNull CollectionTypeId typeId) {
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
	 * A simple public static method that may be used to force class initialization.
	 */
	public static void initStatics() {}

	/**
	 * Return the number of cascaded lazy iterators terminating in sourceValue.
	 */
	protected static int lazyDepth(@NonNull Object sourceValue) {
		if (!(sourceValue instanceof LazyCollectionValueImpl)) {
			return 0;
		}
		LazyCollectionValueImpl lazySource = (LazyCollectionValueImpl)sourceValue;
		if (lazySource.lazyListOfElements != null) {
			return 0;
		}
		else {
			return lazySource.lazyDepth+1;
		}
	}


	/**
	 * The type of the resulting collection.
	 */
	protected final @NonNull CollectionTypeId typeId;

	/**
	 * The number of preceding lazy iterators feeding this lazy collection.
	 */
	private final int lazyDepth;

	/**
	 * The iterator that provides the elements that have yet to be cached in lazyListOfElements.
	 */
	private @NonNull LazyIterator inputIterator;

	/**
	 * The Bag/Sequence/Unique strategy that determines how new/old elements are added/removed.
	 */
	private @NonNull AbstractCollectionStrategy collectionStrategy;

	/**
	 * The Java/Not/OCL/Simple Equals strategy that determines how elements are compared for uniqueness.
	 */
	private @NonNull EqualsStrategy equalsStrategy;

	/**
	 * The lazily cached and deterministically ordered elements obtained by iterating sourceIterator.
	 * Initially null. Non-null once a a cache is needed.
	 */
	private @Nullable List<@Nullable Object> lazyListOfElements = null;	// ArrayList reallocates arrays; could be better to do so ourselves with a smart estimatedSize()

	/**
	 * A lazily created map from element to its repeat count. SetCount.ONE for Set/OrderedSet.
	 * Initially null. Non-null once a non-Sequence is cached.
	 */
	private @Nullable Map<@Nullable Object, @NonNull ElementCount> lazyMapOfElement2elementCount = null;

	/**
	 * The number of elements in the collection. For a Sequence, this is lazyListOfElements.size().
	 * For a Set/OrderedSet it is lazyMapOfElement2elementCount.keySet().size(). For a Bag it is
	 * the sum of lazyMapOfElement2elementCount.values().
	 */
	private int size = 0;

	/**
	 * The hashCode of the boxed values in this collection.
	 */
	private int hashCode = 0;

	/**
	 * Set true if the first usage of this LazyCollectionValue is a bypass lazyIterator(). Once set any further
	 * attempts at lazy iteration force a reIterator() to be created that then caches to inhibit further further
	 * re-iterations.
	 */
	private boolean lazyIterator = false;

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

	protected LazyCollectionValueImpl(@NonNull CollectionTypeId typeId, int lazyDepth) {
		this.typeId = typeId;
		this.lazyDepth = lazyDepth;
		this.inputIterator = ValueUtil.EMPTY_ITERATOR;
		this.collectionStrategy = getCollectionStrategy(typeId);
		this.equalsStrategy = SimpleEqualsStrategy.INSTANCE;
	}

	@Override
	public boolean add(@Nullable Object value) {
		eagerIterable();
		int oldSize = size;
		mutableIncluding(value);
		return size > oldSize;
	}

	@Override
	public @NonNull CollectionValue append(@Nullable Object object) {
		return AppendIterator.append(typeId, this, object);
	}

	@Override
	public @NonNull CollectionValue appendAll(@NonNull CollectionValue values) {
		return AppendAllIterator.appendAll(this, values);
	}

	protected void appendIterable(@NonNull StringBuilder s) {
		s.append(collectionStrategy.getKind());
		s.append("{");
		if (inputIterator.hasNext()) {
			s.append("«future»");
		}
		else {
			List<@Nullable Object> lazyListOfElements2 = lazyListOfElements;
			assert lazyListOfElements2 != null;
			appendBagIterable(s, lazyListOfElements2, lazyMapOfElement2elementCount);
		}
		s.append("}");
	}

	@Override
	public @NonNull CollectionValue asBagValue() {
		if (lazyDepth >= LAZY_DEPTH_TRAP) {
			eagerIterable();
		}
		return new AsBagIterator.FromCollectionValue(this);
	}

	@Override
	public @NonNull CollectionValue asCollectionValue() {
		if (lazyDepth >= LAZY_DEPTH_TRAP) {
			eagerIterable();
		}
		return this;
	}

	@Override
	public @NonNull Collection<@Nullable Object> asCollection() {
		if (collectionStrategy.isBag()) {
			BagImpl<@Nullable Object> bagImpl = new BagImpl<>();
			for (int count; (count = hasNextCount()) > 0; ) {
				Object next = next();
				bagImpl.put(next, count);
			}
			return bagImpl;
		}
		else {
			return getListOfElements();
		}
	}

	@Override
	public @NonNull List<@Nullable Object> asEcoreObject(@NonNull IdResolver idResolver, @Nullable Class<?> instanceClass) {
		//		eagerIterable();			// Force an InvalidValueException to be thrown for any invalid element
		//		return new AsEcoreIterator(this, idResolver, instanceClass).getListOfElements();
		@Nullable Object[] unboxedValues = new @Nullable Object[intSize()];
		int i= 0;
		Iterator<@Nullable Object> iterator = lazyIterator();
		while (iterator.hasNext()) {
			Object element = iterator.next();
			if (element instanceof Value)
				unboxedValues[i++] = ((Value)element).asEcoreObject(idResolver, instanceClass);
			else if (element instanceof EnumerationLiteralId) {
				unboxedValues[i++] = idResolver.unboxedValueOf(element);
			}
			else {
				unboxedValues[i++] = element;
			}
		}
		return new UnmodifiableEcoreObjects(i, unboxedValues);
	}

	@Override
	@SuppressWarnings("unchecked")			// FIXME check element types
	public @Nullable <T> List<T> asEcoreObjects(@NonNull IdResolver idResolver, @Nullable Class<T> instanceClass) {
		return (List<T>) asEcoreObject(idResolver, instanceClass);
	}

	@Override
	public @NonNull Object asObject() {
		return asCollection();
	}

	@Override
	public @NonNull CollectionValue asOrderedCollectionValue() {
		return isUnique() ? asOrderedSetValue() : asSequenceValue();
	}

	@Override
	public @NonNull CollectionValue asOrderedSetValue() {
		if (lazyDepth >= LAZY_DEPTH_TRAP) {
			eagerIterable();
		}
		return new AsOrderedSetIterator.FromCollectionValue(this);
	}

	@Override
	public @NonNull CollectionValue asSequenceValue() {
		if (lazyDepth >= LAZY_DEPTH_TRAP) {
			eagerIterable();
		}
		return new AsSequenceIterator.FromCollectionValue(this);
	}

	@Override
	public @NonNull CollectionValue asSetValue() {
		if (lazyDepth >= LAZY_DEPTH_TRAP) {
			eagerIterable();
		}
		return new AsSetIterator.FromCollectionValue(this);
	}

	@Override
	public @NonNull CollectionValue asUniqueCollectionValue() {
		return isOrdered() ? asOrderedSetValue() : asSetValue();
	}

	@Override
	public @Nullable Object at(int oclIndex) {
		if (!isOrdered()) {
			throw new UnsupportedOperationException();
		}
		int javaIindex = oclIndex - 1;
		int size = intSize();
		if (javaIindex < 0 || size <= javaIindex) {
			throw new InvalidValueException(PivotMessages.IndexOutOfRange, oclIndex, size);
		}
		cachedIterable();
		return get(javaIindex);
	}

	@Override
	public synchronized @NonNull MutableIterable cachedIterable() {
		List<@Nullable Object> lazyListOfElements2 = lazyListOfElements;
		if (lazyListOfElements2 == null) {
			if (lazyIterator) {
				System.err.println(NameUtil.debugSimpleName(this) + " re-iterating");
				this.inputIterator = reIterator();
			}
			else {
				this.inputIterator = this;
			}
			this.equalsStrategy = TypeUtil.getEqualsStrategy(typeId.getElementTypeId(), false);
			this.lazyListOfElements = lazyListOfElements2 = createListOfElements();
			if (!collectionStrategy.isSequence()) {
				this.lazyMapOfElement2elementCount = createMapOfElement2elementCount();
			}
			//			Map<@NonNull Class<?>, @NonNull Integer> debugCollectionClass2count2 = debugCollectionClass2cached;
			//			if (debugCollectionClass2count2 != null) {
			//				Class<? extends @NonNull CollectionValue> collectionClass = getClass();
			//				Integer count = debugCollectionClass2count2.get(collectionClass);
			//				count = count != null ? count+1 : 1;
			//				debugCollectionClass2count2.put(collectionClass, count);
			//			}
		}
		return this;
	}

	@Override
	public boolean canBeLazy() {
		return true;
	}

	protected boolean checkElementsAreValues(@NonNull Iterable<? extends Object> elements) {
		for (Object element : elements) {
			assert ValueUtil.isBoxed(element);
			//			if (element instanceof Collection<?>) {
			//				assert isNormalized((Iterable<?>)element);
			//				assert checkElementsAreValues((Iterable<?>)element);
			//			}
		}
		return true;
	}

	public @NonNull Boolean contains(@Nullable Object value) {
		return collectionStrategy.contains(this, value);
	}

	@Override
	public @NonNull IntegerValue count(@Nullable Object value) {
		int count = 0;
		Iterator<@Nullable Object> iterator = lazyIterator();
		if (value == null) {
			while (iterator.hasNext()) {
				Object next = iterator.next();
				if (next == null) {
					count++;
				}
			}
		}
		else {
			while (iterator.hasNext()) {
				Object next = iterator.next();
				if (value.equals(next)) {
					count++;
				}
			}
		}
		return ValueUtil.integerValueOf(count);
	}

	protected @NonNull LazyIterator createLazyIterator() {
		if (collectionStrategy.isBag()) {
			Map<@Nullable Object, @NonNull ElementCount> lazyMapOfElement2elementCount2 = getMapOfElement2elementCount();
			assert lazyMapOfElement2elementCount2 != null;
			if (inputIterator.hasNext()) {
				return new LazyBaggableIterator(this);
			}
			else {
				List<@Nullable Object> lazyListOfElements2 = lazyListOfElements;
				assert lazyListOfElements2 != null;
				return new ImmutableBaggableIterator(lazyListOfElements2, lazyMapOfElement2elementCount2);
			}
		}
		else {
			if (inputIterator.hasNext()) {
				return new LazyNonBaggableIterator();
			}
			else {
				List<@Nullable Object> lazyListOfElements2 = lazyListOfElements;
				assert lazyListOfElements2 != null;
				return new ImmutableNonBaggableIterator(lazyListOfElements2);
			}
		}
	}

	protected @NonNull List<@Nullable Object> createListOfElements() {
		//		Map<@NonNull Class<?>, @NonNull Integer> debugCollectionClass2lazyList2 = debugCollectionClass2lazyList;
		//		if (debugCollectionClass2lazyList2 != null) {
		//			Class<?> collectionClass = inputIterator.getClass();
		//			Integer count = debugCollectionClass2lazyList2.get(collectionClass);
		//			count = count != null ? count+1 : 1;
		//			debugCollectionClass2lazyList2.put(collectionClass, count);
		//		}
		return new ArrayList<>();
	}

	protected @NonNull Map<@Nullable Object, @NonNull ElementCount> createMapOfElement2elementCount() {
		//		Map<@NonNull Class<?>, @NonNull Integer> debugCollectionClass2lazyMap2 = debugCollectionClass2lazyMap;
		//		if (debugCollectionClass2lazyMap2 != null) {
		//			Class<?> collectionClass = inputIterator.getClass();
		//			Integer count = debugCollectionClass2lazyMap2.get(collectionClass);
		//			count = count != null ? count+1 : 1;
		//			debugCollectionClass2lazyMap2.put(collectionClass, count);
		//		}
		return new HashMap<>();
	}

	@Override
	public @NonNull LazyIterable eagerIterable() {
		cachedIterable();
		getListOfElements();
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		checkValid(obj);
		if (!(obj instanceof CollectionValue)) {
			return false;
		}
		CollectionValue that = (CollectionValue)obj;
		boolean isOrdered = isOrdered();
		if (isOrdered != that.isOrdered()) {
			return false;
		}
		boolean isUnique = isUnique();
		if (isUnique != that.isUnique()) {
			return false;
		}
		if (isOrdered) {
			if (isUnique) {
				// This is probably a bug fix on LinkedHashSet that should consider ordering for equals
				Collection<? extends Object> theseElements = this.getElements();
				Collection<? extends Object> thoseElements = that.getElements();
				Iterator<? extends Object> thisElement = theseElements.iterator();
				Iterator<? extends Object> thatElement = thoseElements.iterator();
				while (thisElement.hasNext() && thatElement.hasNext()) {
					Object thisValue = thisElement.next();
					Object thatValue = thatElement.next();
					if (thisValue == null) {
						if (thatValue != null) {
							return false;
						}
					}
					else {
						if (!thisValue.equals(thatValue)) {
							return false;
						}
					}
				}
				return !thisElement.hasNext() && !thatElement.hasNext();
			}
			else {
				Collection<? extends Object> theseElements = this.getElements();
				Collection<? extends Object> thoseElements = that.getElements();
				Iterator<? extends Object> thisElement = theseElements.iterator();
				Iterator<? extends Object> thatElement = thoseElements.iterator();
				while (thisElement.hasNext() && thatElement.hasNext()) {
					Object thisValue = thisElement.next();
					Object thatValue = thatElement.next();
					if (!ClassUtil.safeEquals(thisValue, thatValue)) {
						return false;
					}
				}
				return !thisElement.hasNext() && !thatElement.hasNext();
			}
		}
		else {
			if (isUnique) {
				Collection<? extends Object> theseElements = this.getElements();
				Collection<? extends Object> thoseElements = that.getElements();
				int thisSize = theseElements.size();
				int thatSize = thoseElements.size();
				if (thisSize != thatSize) {
					return false;
				}
				if (thoseElements instanceof Set<?>) {
					return thoseElements.containsAll(theseElements);
				}
				else {
					return theseElements.containsAll(thoseElements);
				}
			}
			else {
				Map<? extends Object, @NonNull ? extends Number> theseElements = this.getMapOfElement2elementCount();
				Map<? extends Object, @NonNull ? extends Number> thoseElements = that.getMapOfElement2elementCount();
				return theseElements.equals(thoseElements);
			}
		}
	}

	@Override
	public @NonNull Boolean excludes(@Nullable Object value) {
		Iterator<@Nullable Object> iterator = lazyIterator();
		if (value == null) {
			while (iterator.hasNext()) {
				Object next = iterator.next();
				if (next == null) {
					return false;
				}
			}
		}
		else {
			while (iterator.hasNext()) {
				Object next = iterator.next();
				if (value.equals(next)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public @NonNull Boolean excludesAll(@NonNull CollectionValue c) {
		Iterable<@Nullable Object> cachedIterable = cachedIterable(c);
		Iterator<@Nullable Object> iterator1 = lazyIterator();
		while (iterator1.hasNext()) {
			Object e1 = iterator1.next();
			if (e1 == null) {
				for (Object e2 : cachedIterable) {
					if (e2 == null) {
						return false;
					}
				}
			}
			else {
				for (Object e2 : cachedIterable) {
					if (e1.equals(e2)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public @NonNull CollectionValue excluding(@Nullable Object value) {
		return ExcludingIterator.excluding(this, value);
	}

	@Override
	public @NonNull CollectionValue excludingAll(@NonNull CollectionValue values) {
		return ExcludingAllIterator.excludingAll(this, values);
	}

	@Override
	public @Nullable Object first() {
		return at(1);
	}

	/**
	 * Returns true if any element flattened.
	 * @throws InvalidValueException
	 */
	@Override @Deprecated
	public boolean flatten(@NonNull Collection<Object> flattenedElements) {
		boolean flattened = false;
		Iterator<@Nullable Object> iterator = lazyIterator();
		while (iterator.hasNext()) {
			Object element = iterator.next();
			CollectionValue collectionElement = ValueUtil.isCollectionValue(element);
			if (collectionElement != null) {
				flattened = true;
				collectionElement.flatten(flattenedElements);
			}
			else {
				flattenedElements.add(element);
			}
		}
		return flattened;
	}

	@Override
	public @NonNull CollectionValue flatten() {
		return FlattenIterator.flatten(this);
	}

	private synchronized @Nullable Object get(int javaIndex) {
		//
		//	Ensure that sufficient of source has been read to reach the required index.
		//
		List<@Nullable Object> lazyListOfElements2 = lazyListOfElements;
		assert lazyListOfElements2 != null;
		if (lazyListOfElements2.size() <= javaIndex) {
			for (int nextCount; ((nextCount = inputIterator.hasNextCount()) > 0) && (lazyListOfElements2.size() <= javaIndex); ) {
				collectionStrategy.addTo(this, inputIterator.next(), nextCount);
			}
		}
		//
		//	Return the required index (NB determinstic unqiue ordered index for Bag as well as Unique collections).
		//
		return lazyListOfElements2.get(javaIndex);
	}

	public @NonNull CollectionTypeId getBagTypeId() {
		return TypeId.BAG.getSpecializedId(typeId.getElementTypeId());
	}

	@Override
	public @NonNull Collection<@Nullable Object> getElements() {
		if (!isBag()) {
			return getListOfElements();
		}
		else {
			return Lists.newArrayList(lazyIterator());			// FIXME avoid this
		}
	}

	@Override
	public @NonNull String getKind() {
		return collectionStrategy.getKind();
	}

	/**
	 * Ensure that all lazy iterations have completed and then return a list of all elements.
	 */
	private synchronized @NonNull List<@Nullable Object> getListOfElements() {
		cachedIterable();
		for (int nextCount; (nextCount = inputIterator.hasNextCount()) > 0; ) {
			collectionStrategy.addTo(this, inputIterator.next(), nextCount);
		}
		List<@Nullable Object> lazyListOfElements2 = lazyListOfElements;
		assert lazyListOfElements2 != null;
		return lazyListOfElements2;
	}

	/**
	 * Ensure that all lazy iterations have completed and then return a bag of all elements.
	 */
	@Override
	public synchronized @NonNull Map<@Nullable Object, @NonNull ElementCount> getMapOfElement2elementCount() {
		getListOfElements();
		Map<@Nullable Object, @NonNull ElementCount> lazyMapOfElement2elementCount2 = lazyMapOfElement2elementCount;
		if (lazyMapOfElement2elementCount2 == null) {			// Lazy creation is only needed for Sequences
			assert collectionStrategy.isSequence();
			//			Map<@NonNull Class<?>, @NonNull Integer> debugCollectionClass2lazyMap2 = debugCollectionClass2lazyMap;
			//			if (debugCollectionClass2lazyMap2 != null) {
			//				Class<?> collectionClass = inputIterator.getClass();
			//				Integer count = debugCollectionClass2lazyMap2.get(collectionClass);
			//				count = count != null ? count+1 : 1;
			//				debugCollectionClass2lazyMap2.put(collectionClass, count);
			//			}
			lazyMapOfElement2elementCount2 = new HashMap<>();
			List<@Nullable Object> lazyListOfElements2 = lazyListOfElements;
			assert lazyListOfElements2 != null;
			for (@Nullable Object element : lazyListOfElements2) {
				lazyMapOfElement2elementCount2.put(element, SetElementCount.ONE);
			}
		}
		return lazyMapOfElement2elementCount2;
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

	public @NonNull CollectionTypeId getOrderedSetTypeId() {
		return TypeId.ORDERED_SET.getSpecializedId(typeId.getElementTypeId());
	}

	public @NonNull CollectionTypeId getSequenceTypeId() {
		return TypeId.SEQUENCE.getSpecializedId(typeId.getElementTypeId());
	}

	/**
	 * Ensure that all lazy iterations have completed and then return a set of all elements.
	 */
	public @NonNull Set<@Nullable Object> getSetOfElements() {
		Map<@Nullable Object, @NonNull ElementCount> lazyMapOfElement2elementCount2 = lazyMapOfElement2elementCount;
		if (lazyMapOfElement2elementCount2 == null) {
			lazyMapOfElement2elementCount2 = getMapOfElement2elementCount();
		}
		return lazyMapOfElement2elementCount2.keySet();
	}

	public @NonNull CollectionTypeId getSetTypeId() {
		return TypeId.SET.getSpecializedId(typeId.getElementTypeId());
	}

	@Override
	public @NonNull CollectionTypeId getTypeId() {
		return typeId;
	}

	protected boolean hasCache() {
		return lazyListOfElements != null;
	}

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
	public int hashCode() {
		if (hashCode == 0) {
			synchronized (this) {
				if (hashCode == 0) {
					boolean isOrdered = isOrdered();
					boolean isUnique = isUnique();
					if (isOrdered || isUnique) {
						hashCode = computeCollectionHashCode(isOrdered, isUnique, getListOfElements());
					}
					else {			// Bag
						hashCode = computeCollectionHashCode(getMapOfElement2elementCount());
					}
				}
			}
		}
		return hashCode;
	}

	@Override
	public @NonNull Boolean includes(@Nullable Object value) {
		Iterator<@Nullable Object> iterator = lazyIterator();
		if (value == null) {
			while (iterator.hasNext()) {
				Object next = iterator.next();
				if (next == null) {
					return true;
				}
			}
		}
		else {
			while (iterator.hasNext()) {
				Object next = iterator.next();
				if (value.equals(next)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public @NonNull Boolean includesAll(@NonNull CollectionValue c) {
		Iterator<@Nullable Object> iterator = ValueUtil.lazyIterator(c);
		Iterable<@Nullable Object> iterable2 = cachedIterable();
		while (iterator.hasNext()) {
			Object e1 = iterator.next();
			boolean gotIt = false;
			if (e1 == null) {
				for (Object e2 : iterable2) {
					if (e2 == null) {
						gotIt = true;
						break;
					}
				}
			}
			else {
				for (Object e2 : iterable2) {
					if (e1.equals(e2)) {
						gotIt = true;
						break;
					}
				}
			}
			if (!gotIt) {
				return false;
			}
		}
		return true;
	}

	@Override
	public @NonNull CollectionValue including(@Nullable Object value) {
		return IncludingIterator.including(typeId, this, value);
	}

	@Override
	public @NonNull CollectionValue includingAll(@NonNull CollectionValue values) {
		return IncludingAllIterator.includingAll(typeId, this, values);
	}

	@Override
	public @NonNull IntegerValue indexOf(@Nullable Object object) {
		int index = 1;
		if (object == null) {
			for (Object next : getListOfElements()) {
				if (next == null) {
					return ValueUtil.integerValueOf(index);
				}
				index++;
			}
		}
		else {
			for (Object next : getListOfElements()) {
				if (equalsStrategy.isEqual(object, next)) {
					return ValueUtil.integerValueOf(index);
				}
				index++;
			}
		}
		throw new InvalidValueException(PivotMessages.MissingValue, "indexOf");
	}

	@Override
	public @NonNull CollectionValue insertAt(int oclIndex, @Nullable Object object) {
		if (object instanceof InvalidValueException) {
			throw new InvalidValueException(PivotMessages.InvalidSource, "insertAt");
		}
		List<@Nullable Object> listOfElements = getListOfElements();
		int javaIindex = oclIndex - 1;
		int javaSize = listOfElements.size();
		if (javaIindex < 0) {
			throw new InvalidValueException(PivotMessages.IndexOutOfRange, oclIndex, javaSize);
		}
		if (javaIindex > javaSize) {
			throw new InvalidValueException(PivotMessages.IndexOutOfRange, oclIndex, javaSize);
		}
		if (collectionStrategy.isUnique() && contains(object)) {
			return this;
		}
		List<@Nullable Object> result = new ArrayList<>(listOfElements);
		result.add(javaIindex, object);
		return new MutableCollectionValueImpl(typeId, result.iterator());
	}

	public int intCount(Object value) {
		return collectionStrategy.intCount(this, value);
	}

	@Override
	public int intSize() {
		eagerIterable();
		return size;
	}

	@Override
	public @NonNull CollectionValue intersection(@NonNull CollectionValue that) {
		return IntersectionIterator.intersection(this, that);
	}

	@Override
	public @NonNull Boolean isEmpty() {
		return intSize() == 0;
	}

	public boolean isBag() {
		return collectionStrategy.isBag();
	}

	@Override
	public boolean isOrdered() {
		return collectionStrategy.isOrdered();
	}

	public boolean isOrderedSet() {
		return collectionStrategy.isOrderedSet();
	}

	public boolean isSequence() {
		return collectionStrategy.isSequence();
	}

	public boolean isSet() {
		return collectionStrategy.isSet();
	}

	@Override
	public boolean isUnique() {
		return collectionStrategy.isUnique();
	}

	@Override
	public @NonNull LazyIterable iterable() {
		//		System.err.println(NameUtil.debugSimpleName(this) + " iterable() rather than cachedIterable()");
		return eagerIterable();
	}

	@Override
	public @NonNull LazyIterator iterator() {
		//		System.err.println(NameUtil.debugSimpleName(this) + " iterator() rather than cachedIterator()");
		eagerIterable();
		return createLazyIterator();
	}

	@Override
	public @Nullable Object last() {
		return at(intSize());
	}

	@Override
	public synchronized @NonNull LazyIterator lazyIterator() {
		if (lazyListOfElements == null) {
			if (!lazyIterator) {
				lazyIterator = true;
				return this;
			}
			cachedIterable();
		}
		return createLazyIterator();
	}

	@Override
	public @NonNull CollectionValue minus(@NonNull CollectionValue that) {
		return ExcludingAllIterator.excludingAll(this, that);
	}

	@Override
	public void mutableAppend(@Nullable Object rightValue) {
		collectionStrategy.appendTo(this, rightValue, 1);
	}

	@Override
	public void mutableAppendAll(@NonNull Iterator<@Nullable Object> rightIterator) {
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
	}

	@Override
	public void mutableAsBag() {
		collectionStrategy.asBag(this);
		collectionStrategy = BagStrategy.INSTANCE;
	}

	@Override
	public void mutableAsOrderedSet() {
		collectionStrategy.asUnique(this);
		collectionStrategy = OrderedSetStrategy.INSTANCE;
	}

	@Override
	public void mutableAsSequence() {
		collectionStrategy.asSequence(this);
		collectionStrategy = SequenceStrategy.INSTANCE;
	}

	@Override
	public void mutableAsSet() {
		collectionStrategy.asUnique(this);
		collectionStrategy = SetStrategy.INSTANCE;
	}

	@Override
	public void mutableExcluding(@Nullable Object rightValue) {
		collectionStrategy.removeFrom(this, rightValue, 1);
	}

	@Override
	public void mutableExcludingAll(@NonNull Iterator<@Nullable Object> rightIterator) {
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
	}

	@Override
	public void mutableIncluding(@Nullable Object rightValue) {
		collectionStrategy.addTo(this, rightValue, 1);
	}

	@Override
	public void mutableIncludingAll(@NonNull Iterator<@Nullable Object> rightIterator) {
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
	}

	/**
	 * Return leftCollectionValue after modification to be the intersection of leftCollectionValue and rightIterator,
	 * This the underlying content of leftCollectionValue. If isUnique, the resulting intersection has unit counts
	 * rather than common minimum counts.
	 */
	@Override
	public void mutableIntersection(@NonNull Iterator<@Nullable Object> rightIterator, boolean isUnique) {
		assert ((CollectionValue)this).isUnique() || !((CollectionValue)this).isOrdered();
		Map<@Nullable Object, @NonNull ElementCount> savedMapOfElement2elementCount = getMapOfElement2elementCount();
		Map<@Nullable Object, @NonNull ElementCount> lazyMapOfElement2elementCount2 = lazyMapOfElement2elementCount = new HashMap<>();
		lazyListOfElements = new ArrayList<>();
		size = 0;
		collectionStrategy = isUnique ? SetStrategy.INSTANCE : BagStrategy.INSTANCE;
		if (rightIterator instanceof BaggableIterator<?>) {
			BaggableIterator<@Nullable Object> baggableIterator = (BaggableIterator<@Nullable Object>)rightIterator;
			for (int rightCount; (rightCount = baggableIterator.hasNextCount()) > 0; ) {
				@Nullable Object rightValue = baggableIterator.next();
				ElementCount leftElementCount = savedMapOfElement2elementCount.get(rightValue);
				if (leftElementCount != null) {
					collectionStrategy.addTo(this, rightValue, Math.min(leftElementCount.intValue(), rightCount));
				}
			}
		}
		else {
			while (rightIterator.hasNext()) {
				@Nullable Object rightValue = rightIterator.next();
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
	}

	@Override
	public @NonNull MutableIterable mutableIterable() {
		getListOfElements();
		return this;
	}

	/**
	 * Return leftCollectionValue after modification to be the union of leftCollectionValue and rightIterator,
	 * This the underlying content of leftCollectionValue. If isUnique, the resulting union has unit counts
	 * rather than sum counts.
	 */
	@Override
	public void mutableUnion(@NonNull Iterator<@Nullable Object> rightIterator, boolean isUnique) {
		assert ((CollectionValue)this).isUnique() || !((CollectionValue)this).isOrdered();
		collectionStrategy = isUnique ? SetStrategy.INSTANCE : BagStrategy.INSTANCE;
		if (rightIterator instanceof BaggableIterator<?>) {
			BaggableIterator<@Nullable Object> baggableIterator = (BaggableIterator<@Nullable Object>)rightIterator;
			for (int rightCount; (rightCount = baggableIterator.hasNextCount()) > 0; ) {
				collectionStrategy.addTo(this, baggableIterator.next(), rightCount);
			}
		}
		else {
			while (rightIterator.hasNext()) {
				collectionStrategy.addTo(this, rightIterator.next(), 1);
			}
		}
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
	public @NonNull Boolean notEmpty() {
		return intSize() != 0;
	}

	@Override
	public @NonNull CollectionValue prepend(@Nullable Object value) {
		return PrependIterator.prepend(typeId, this, value);
	}

	@Override
	public @NonNull CollectionValue prependAll(@NonNull CollectionValue values) {
		return PrependAllIterator.prependAll(this, values);
	}

	@Override
	public @NonNull Set<@NonNull TupleValue> product(@NonNull CollectionValue c, @NonNull TupleTypeId tupleTypeId) {
		Set<@NonNull TupleValue> result = new HashSet<>();
		Iterable<@Nullable Object> cachedIterable = cachedIterable(c);
		Iterator<@Nullable Object> iterator1 = lazyIterator();
		while (iterator1.hasNext()) {
			Object next1 = iterator1.next();
			for (Object next2 : cachedIterable) {
				result.add(new TupleValueImpl(tupleTypeId, next1, next2));
			}
		}
		return result;
	}

	/**
	 * Create a reiterator that may be used to perform a further lazy iteration even though an earlier
	 * lazyIteration has already been returned.
	 *
	 * This method provides a fall-back compatibility for legacy code that may not have invoked cached iterable()
	 * to ensure that the results of the first iteration were cached.
	 *
	 * This method may be removed once the LazyCollectionValue API is promoted to CollectionValue.
	 *
	 * @deprecated ensure that cahedIterable() is invoked to avoid the need for re-iteration
	 */
	@Deprecated
	protected abstract @NonNull LazyIterator reIterator();

	@Override
	public @NonNull CollectionValue reverse() {
		List<@Nullable Object> result = new ArrayList<>(getListOfElements());
		Collections.reverse(result);
		return new MutableCollectionValueImpl(typeId, result.iterator());
	}

	protected int setNext(Object next, int nextCount) {
		assert nextCount > 0;
		this.next = next;
		this.hasNextCount = nextCount;
		return nextCount;
	}

	@Override
	public @NonNull IntegerValue size() {
		return ValueUtil.integerValueOf(intSize());
	}

	@Override
	public @NonNull CollectionValue sort(@NonNull Comparator<@Nullable Object> comparator) {
		List<@Nullable Object> values = Lists.newArrayList(lazyIterator());
		Collections.sort(values, comparator);
		return new MutableCollectionValueImpl(typeId, values.iterator());
	}

	@Override
	public @NonNull CollectionValue subOrderedSet(int lower, int upper) {
		return SubOrderedSetIterator.subOrderedSet(this, lower, upper);
	}

	@Override
	public @NonNull CollectionValue subSequence(int lower, int upper) {
		return SubSequenceIterator.subSequence(this, lower, upper);
	}

	@Override
	public @NonNull CollectionValue symmetricDifference(@NonNull CollectionValue that) {
		return SymmetricDifferenceIterator.symmetricDifference(this, that);
	}

	@Override
	public @NonNull CollectionValue toSequenceValue() {
		return new MutableCollectionValueImpl(getSequenceTypeId(), this);
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 100);
		return s.toString();
	}

	/*	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		s.append(collectionStrategy.getKind());
		s.append("{");
		if (sourceIterator.hasNext()) {
			s.append("«future»");
		}
		else {
			List<@Nullable Object> lazyListOfElements2 = lazyListOfElements;
			assert lazyListOfElements2 != null;
			appendBagIterable(s, lazyListOfElements2, lazyMapOfElement2elementCount);
		}
		s.append("}");
		return s.toString();
	} */

	@Override
	public @NonNull CollectionValue union(@NonNull CollectionValue that) {
		return IncludingAllIterator.union(this, that);
	}
}
