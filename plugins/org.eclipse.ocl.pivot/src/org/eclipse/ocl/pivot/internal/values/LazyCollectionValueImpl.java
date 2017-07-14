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
import org.eclipse.ocl.pivot.internal.iterators.AbstractLazyIterator;
import org.eclipse.ocl.pivot.internal.iterators.AppendAllIterator;
import org.eclipse.ocl.pivot.internal.iterators.AppendIterator;
import org.eclipse.ocl.pivot.internal.iterators.BagElementCount;
import org.eclipse.ocl.pivot.internal.iterators.ElementCount;
import org.eclipse.ocl.pivot.internal.iterators.EqualsStrategy;
import org.eclipse.ocl.pivot.internal.iterators.ExcludingAllIterator;
import org.eclipse.ocl.pivot.internal.iterators.ExcludingIterator;
import org.eclipse.ocl.pivot.internal.iterators.FlattenIterator;
import org.eclipse.ocl.pivot.internal.iterators.FromCollectionValueIterator;
import org.eclipse.ocl.pivot.internal.iterators.IncludingAllIterator;
import org.eclipse.ocl.pivot.internal.iterators.IncludingIterator;
import org.eclipse.ocl.pivot.internal.iterators.IntersectionIterator;
import org.eclipse.ocl.pivot.internal.iterators.PrependAllIterator;
import org.eclipse.ocl.pivot.internal.iterators.PrependIterator;
import org.eclipse.ocl.pivot.internal.iterators.SetElementCount;
import org.eclipse.ocl.pivot.internal.iterators.SubCollectionIterator;
import org.eclipse.ocl.pivot.internal.iterators.SymmetricDifferenceIterator;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
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
 * A LazyCollectionValueImpl maintains a determinsitic Collection of Values with support for lazy, cached or eager content.
 *
 * Distinctions between Bag/OrderedSet/Sequence/Set functionality is maintained by a CollectionStrategy determined
 * by the CollectionTypeId.
 *
 * Subtleties of equivalence are maintained by an EqualsStrategy.
 *
 * The collection content may be derived lazily from an input LazyIterator. Alternatively after mutableIterable()
 * has been invoked, mutable operations may be pewformed with the caller taking resposibility for consistent
 * access between mutations. Easiest to perform all mutations in one go, e.g. as for sort().
 *
 * The collection content can be accessed lazily by lazyIterator() with multiple lazyIterators preceded by
 * a cachedIterable() or eagerIterable() to ensure that a cache of the input is activated. The requisite
 * cache is activated by analysis of an ExpressionInOCL setting Variable.cacheNeeded. If however activation is
 * omitted an inefficient reIteration is supported which is cached on the second occasion to avoid further
 * costs on a third pass.
 *
 * The cached content is maintained using a determinstic list and a map of distinct value to repeat count.
 *
 * For a Sequence the list contains all the values and the map is null.
 *
 * For a Set or OrderedSet, the list contains the deterministic order and the map contains a unit count for each value.
 *
 * For a Bag the list provides a deterministic order of distinct values, with the map providing the repeat count.
 *
 * The hashCode is lazily computed once. Any mutation after hashCode computation is illegal.
 *
 * Long daisy chains of uncached LazyIterators can be inefficient. A daisy chain length is maintained so that
 * occasional cached iterators break the chains.
 *
 * @since 1.3
 */
public class LazyCollectionValueImpl extends ValueImpl implements LazyCollectionValue, MutableIterable
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
		/**
		 * Add count of anElement to the lazyIterable updating element occurrence counts, returning true if anElement actually added.
		 *
		 * The default implementation is that for a Set. Bag and Sequence override.
		 */
		protected boolean addTo(@NonNull LazyCollectionValueImpl lazyIterable, @Nullable Object anElement, int count) {
			assert count > 0;
			Map<@Nullable Object, @NonNull ElementCount> mapOfElement2elementCount = lazyIterable.lazyMapOfElement2elementCount;
			assert mapOfElement2elementCount != null;
			ElementCount oldElementCount = mapOfElement2elementCount.put(anElement, SetElementCount.ONE);
			if (oldElementCount == null) {
				List<@Nullable Object> listOfElements = lazyIterable.lazyListOfElements;
				assert listOfElements != null;
				listOfElements.add(anElement);
				lazyIterable.size++;
				return true;
			}
			else {
				return false;
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

		protected abstract void loadCache(@NonNull LazyCollectionValueImpl lazyIterable,
				@Nullable List<@Nullable Object> oldListOfElements,
				@Nullable Map<@Nullable Object, @NonNull ElementCount> oldMapOfElement2elementCount);

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

		@Override
		protected boolean addTo(@NonNull LazyCollectionValueImpl lazyIterable, @Nullable Object anElement, int count) {
			assert count > 0;
			Map<@Nullable Object, @NonNull ElementCount> lazyMapOfElement2elementCount2 = lazyIterable.lazyMapOfElement2elementCount;
			assert lazyMapOfElement2elementCount2 != null;
			BagElementCount newElementCount = new BagElementCount(count);
			ElementCount oldElementCount = lazyMapOfElement2elementCount2.put(anElement, newElementCount);
			if (oldElementCount != null) {
				newElementCount.setValue(count + oldElementCount.intValue());	// setValue only to newly created element
			}
			else {
				List<@Nullable Object> lazyListOfElements2 = lazyIterable.lazyListOfElements;
				assert lazyListOfElements2 != null;
				lazyListOfElements2.add(anElement);
			}
			lazyIterable.size++;
			return true;
		}

		@Override
		protected void asSequence(@NonNull LazyCollectionValueImpl lazyIterable) {
			SequenceStrategy.INSTANCE.loadCache(lazyIterable, lazyIterable.lazyListOfElements, lazyIterable.lazyMapOfElement2elementCount);
		}

		@Override
		protected void asUnique(@NonNull LazyCollectionValueImpl lazyIterable) {
			Map<@Nullable Object, @NonNull ElementCount> mapOfElement2elementCount = lazyIterable.getMapOfElement2elementCount();
			assert mapOfElement2elementCount != null;
			for (@NonNull ElementCount elementCount : mapOfElement2elementCount.values()) {
				elementCount.setValue(1);
			}
			//lazyIterable.lazyListOfElements = oldListOfElements; -- no chnage
			lazyIterable.size = mapOfElement2elementCount.size();
		}

		@Override
		public @NonNull String getKind() {
			return TypeId.BAG_NAME;
		}

		@Override
		public boolean isBag() {
			return true;
		}

		@Override
		protected void loadCache(@NonNull LazyCollectionValueImpl lazyIterable,
				@Nullable List<@Nullable Object> oldListOfElements,
				@Nullable Map<@Nullable Object, @NonNull ElementCount> oldMapOfElement2elementCount) {
			Map<@Nullable Object, @NonNull ElementCount> newMapOfElement2elementCount = new HashMap<>();
			List<@Nullable Object> newLazyListOfElements = new ArrayList<>();
			if (oldMapOfElement2elementCount != null) {
				assert oldListOfElements != null;
				for (@Nullable Object anElement : oldListOfElements) {
					newLazyListOfElements.add(anElement);
					ElementCount elementCount = oldMapOfElement2elementCount.get(anElement);
					assert elementCount != null;
					newMapOfElement2elementCount.put(anElement, elementCount);
				}
			}
			else if (oldListOfElements != null) {
				for (@Nullable Object anElement : oldListOfElements) {
					BagElementCount newElementCount = new BagElementCount(1);
					ElementCount oldElementCount = newMapOfElement2elementCount.put(anElement, newElementCount);
					if (oldElementCount != null) {
						newElementCount.setValue(1 + oldElementCount.intValue());
					}
					else {
						newLazyListOfElements.add(anElement);
					}
				}
			}
			lazyIterable.lazyListOfElements = newLazyListOfElements;
			lazyIterable.lazyMapOfElement2elementCount = newMapOfElement2elementCount;
			lazyIterable.size = oldListOfElements != null ? oldListOfElements.size() : 0;
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

		@Override
		public @NonNull String getKind() {
			return TypeId.COLLECTION_NAME;
		}
	}

	public static abstract class OrderedStrategy extends AbstractCollectionStrategy
	{
		@Override
		protected boolean addTo(@NonNull LazyCollectionValueImpl lazyIterable, @Nullable Object anElement, int count) {
			assert count > 0;
			List<@Nullable Object> lazyOfElements = lazyIterable.lazyListOfElements;
			assert lazyOfElements != null;
			for (int i = count; i > 0; i--) {
				lazyOfElements.add(anElement);
				lazyIterable.size++;
			}
			return true;
		}

		@Override
		public boolean isOrdered() {
			return true;
		}

		@Override
		protected void loadCache(@NonNull LazyCollectionValueImpl lazyIterable,
				@Nullable List<@Nullable Object> oldListOfElements,
				@Nullable Map<@Nullable Object, @NonNull ElementCount> oldMapOfElement2elementCount) {
			List<@Nullable Object> newListOfElements = new ArrayList<>();
			if (oldMapOfElement2elementCount != null) {
				assert oldListOfElements != null;
				for (@Nullable Object anElement : oldListOfElements) {
					ElementCount elementCount = oldMapOfElement2elementCount.get(anElement);
					assert elementCount != null;
					for (int i = elementCount.intValue(); i > 0; --i) {
						newListOfElements.add(anElement);
					}
				}
			}
			else if (oldListOfElements != null) {
				for (@Nullable Object anElement : oldListOfElements) {
					newListOfElements.add(anElement);
				}
			}
			lazyIterable.lazyListOfElements = newListOfElements;
			lazyIterable.lazyMapOfElement2elementCount = null;
			lazyIterable.size = newListOfElements.size();
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
		public @NonNull String getKind() {
			return TypeId.ORDERED_SET_NAME;
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

		@Override
		protected void asBag(@NonNull LazyCollectionValueImpl lazyIterable) {
			BagStrategy.INSTANCE.loadCache(lazyIterable, lazyIterable.lazyListOfElements, null);
		}

		@Override
		protected void asUnique(@NonNull LazyCollectionValueImpl lazyIterable) {
			SetStrategy.INSTANCE.loadCache(lazyIterable, lazyIterable.lazyListOfElements, null);
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
		public @NonNull String getKind() {
			return TypeId.SEQUENCE_NAME;
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

		@Override
		public @NonNull String getKind() {
			return TypeId.SET_NAME;
		}

		@Override
		public boolean isSet() {
			return true;
		}
	}

	public static abstract class UniqueStrategy extends AbstractCollectionStrategy
	{
		@Override
		protected void asSequence(@NonNull LazyCollectionValueImpl lazyIterable) {
			lazyIterable.lazyMapOfElement2elementCount = null;
		}

		@Override
		public boolean isUnique() {
			return true;
		}

		@Override
		protected void loadCache(@NonNull LazyCollectionValueImpl lazyIterable,
				@Nullable List<@Nullable Object> oldListOfElements,
				@Nullable Map<@Nullable Object, @NonNull ElementCount> oldMapOfElement2elementCount) {
			Map<@Nullable Object, @NonNull ElementCount> newMapOfElement2elementCount = new HashMap<>();
			List<@Nullable Object> newListOfElements = new ArrayList<>();
			if (oldListOfElements != null) {
				for (@Nullable Object anElement : oldListOfElements) {
					if (!newMapOfElement2elementCount.containsKey(anElement)) {
						newMapOfElement2elementCount.put(anElement, SetElementCount.ONE);
						newListOfElements.add(anElement);
					}
				}
			}
			lazyIterable.lazyListOfElements = newListOfElements;
			lazyIterable.lazyMapOfElement2elementCount = newMapOfElement2elementCount;
			lazyIterable.size = newListOfElements.size();
		}
	}

	/**
	 * An CachedBagIterator provides better performance than the standard List Iterator by
	 * exploiting the immutability of the fully populated Iteration cache.
	 */
	private static class CachedBagIterator extends AbstractLazyIterator
	{
		private final @NonNull List<@Nullable Object> listOfElements;
		private final @NonNull Map<@Nullable Object, @NonNull ? extends Number> element2elementCount;
		private final int listSize;
		private int nextListIndex = 0;

		public CachedBagIterator(@NonNull List<@Nullable Object> listOfElements, @NonNull Map<@Nullable Object, @NonNull ? extends Number> element2elementCount) {
			this.listOfElements = listOfElements;
			this.element2elementCount = element2elementCount;
			this.listSize = listOfElements.size();
		}

		@Override
		public int getNextCount() {
			while (nextListIndex < listSize) {
				@Nullable Object currentElement = listOfElements.get(nextListIndex);
				nextListIndex++;			// After IndexOutOfBoundsException has been thrown
				Number elementCount = element2elementCount.get(currentElement);
				assert elementCount != null;
				int hasNextCount = elementCount.intValue();
				if (hasNextCount > 0) {
					return setNext(currentElement, hasNextCount);
				}
			}
			return 0;
		}

		@Override
		public boolean isCached() {
			return true;
		}

		@Override
		public @NonNull LazyIterator reIterator() {
			return new CachedBagIterator(listOfElements, element2elementCount);
		}

		@Override
		public void toString(@NonNull StringBuilder s, int sizeLimit) {
			appendIterable(s, listOfElements, element2elementCount, sizeLimit);
		}
	}

	/**
	 * A CachedListIterator provides an iterator over the fully cached listOfElements.
	 */
	private static class CachedListIterator extends AbstractLazyIterator
	{
		private final @NonNull List<@Nullable Object> listOfElements;
		private final int listSize;
		private int nextListIndex = 0;

		public CachedListIterator(@NonNull List<@Nullable Object> listOfElements) {
			this.listOfElements = listOfElements;
			this.listSize = listOfElements.size();
		}

		@Override
		public int getNextCount() {
			if (nextListIndex < listSize) {
				@Nullable Object currentElement = listOfElements.get(nextListIndex);
				nextListIndex++;			// After IndexOutOfBoundsException has been thrown
				return setNext(currentElement, 1);
			}
			return 0;
		}

		@Override
		public boolean isCached() {
			return true;
		}

		@Override
		public @NonNull LazyIterator reIterator() {
			return new CachedListIterator(listOfElements);
		}

		@Override
		public void toString(@NonNull StringBuilder s, int sizeLimit) {
			appendIterable(s, listOfElements, null, sizeLimit);
		}
	}

	/**
	 * A CachingListIterator provides an iterator over the lazily cached listOfElements.
	 * Maintenance of the lazy cache is synchronized to allow multiple CachingListIterator
	 * to co-exist.
	 */
	private class CachingListIterator extends AbstractLazyIterator
	{
		private int nextListIndex = 0;

		@Override
		public int getNextCount() {
			List<@Nullable Object> listOfElements = lazyListOfElements;
			assert listOfElements != null;
			if (nextListIndex < size) {
				@Nullable Object currentElement = listOfElements.get(nextListIndex);
				nextListIndex++;			// After IndexOutOfBoundsException has been thrown
				return setNext(currentElement, 1);
			}
			LazyCollectionValueImpl lazyCollectionValue = LazyCollectionValueImpl.this;
			synchronized (lazyCollectionValue) {
				while (inputIterator.hasNext()) {
					if (collectionStrategy.addTo(lazyCollectionValue, inputIterator.next(), 1)) {
						assert nextListIndex < size;
						@Nullable Object currentElement = listOfElements.get(nextListIndex);
						nextListIndex++;			// After IndexOutOfBoundsException has been thrown
						return setNext(currentElement, 1);
					}
				}
			}
			return 0;
		}

		@Override
		public boolean isCached() {
			return false;
		}

		@Override
		public @NonNull LazyIterator reIterator() {
			return new CachingListIterator();
		}

		@Override
		public void toString(@NonNull StringBuilder s, int sizeLimit) {
			inputIterator.reIterator().toString(s, sizeLimit);
		}
	}

	public static final @NonNull AbstractCollectionStrategy BAG_STRATEGY = BagStrategy.INSTANCE;
	public static final @NonNull AbstractCollectionStrategy COLLECTION_STRATEGY = BaseCollectionStrategy.INSTANCE;
	public static final @NonNull AbstractCollectionStrategy ORDERED_SET_STRATEGY = OrderedSetStrategy.INSTANCE;
	public static final @NonNull AbstractCollectionStrategy SEQUENCE_STRATEGY = SequenceStrategy.INSTANCE;
	public static final @NonNull AbstractCollectionStrategy SET_STRATEGY = SetStrategy.INSTANCE;

	public static @NonNull AbstractCollectionStrategy getCollectionStrategy(@NonNull CollectionTypeId typeId) {
		typeId = typeId.getGeneralizedId();
		if (typeId == TypeId.BAG) {
			return BAG_STRATEGY;
		}
		else if (typeId == TypeId.ORDERED_SET) {
			return ORDERED_SET_STRATEGY;
		}
		else if (typeId == TypeId.SEQUENCE) {
			return SEQUENCE_STRATEGY;
		}
		else if (typeId == TypeId.SET) {
			return SET_STRATEGY;
		}
		else {
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
	public static int lazyDepth(@NonNull Object sourceValue) {
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
	protected final int lazyDepth;

	/**
	 * The iterator that provides the elements that have yet to be cached in lazyListOfElements.
	 */
	private final @NonNull LazyIterator inputIterator;

	/**
	 * The Bag/Sequence/Unique strategy that determines how new/old elements are added/removed.
	 */
	private @NonNull AbstractCollectionStrategy collectionStrategy;

	/**
	 * The Java/Not/OCL/Simple Equals strategy that determines how elements are compared for uniqueness.
	 */
	private final @NonNull EqualsStrategy equalsStrategy;

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
	 * The hashCode of the boxed values in this collection. A non-zero value is computed lazily.
	 * It is illegal toi perform a mutable operation after the hashCode has been computed.
	 */
	private int hashCode = 0;

	/**
	 * Set true if the first usage of this LazyCollectionValue is a bypass lazyIterator(). Once set any further
	 * attempts at lazy iteration force a reIterator() to be created that then caches to inhibit further further
	 * re-iterations.
	 */
	private boolean lazyIterator = false;

	public LazyCollectionValueImpl(@NonNull CollectionTypeId typeId, @NonNull LazyIterator inputIterator, @Nullable CollectionValue precedingCollectionValue) {
		this(typeId, inputIterator, precedingCollectionValue != null ? lazyDepth(precedingCollectionValue) : 0);
	}

	protected LazyCollectionValueImpl(@NonNull CollectionTypeId typeId, @NonNull LazyIterator inputIterator, int lazyDepth) {
		this.typeId = typeId;
		this.lazyDepth = lazyDepth;
		this.inputIterator = inputIterator;
		this.collectionStrategy = getCollectionStrategy(typeId);
		this.equalsStrategy = TypeUtil.getEqualsStrategy(typeId.getElementTypeId(), false);
	}

	@Override
	public @NonNull CollectionValue append(@Nullable Object object) {
		return AppendIterator.append(typeId, this, object);
	}

	@Override
	public @NonNull CollectionValue appendAll(@NonNull CollectionValue values) {
		return AppendAllIterator.appendAll(this, values);
	}

	@Override
	public @NonNull CollectionValue asBagValue() {
		if (lazyDepth >= LAZY_DEPTH_TRAP) {
			eagerIterable();
		}
		return FromCollectionValueIterator.create(TypeUtil.getBagTypeId(typeId), this);
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
			LazyIterator iterator = lazyIterator();
			BagImpl<@Nullable Object> bagImpl = new BagImpl<>();
			for (int count; (count = iterator.hasNextCount()) > 0; ) {
				Object next = iterator.next();
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
		return FromCollectionValueIterator.create(TypeUtil.getOrderedSetTypeId(typeId), this);
	}

	@Override
	public @NonNull CollectionValue asSequenceValue() {
		if (lazyDepth >= LAZY_DEPTH_TRAP) {
			eagerIterable();
		}
		return FromCollectionValueIterator.create(TypeUtil.getSequenceTypeId(typeId), this);
	}

	@Override
	public @NonNull CollectionValue asSetValue() {
		if (lazyDepth >= LAZY_DEPTH_TRAP) {
			eagerIterable();
		}
		return FromCollectionValueIterator.create(TypeUtil.getSetTypeId(typeId), this);
	}

	@Override
	public @NonNull CollectionValue asUniqueCollectionValue() {
		return isOrdered() ? asOrderedSetValue() : asSetValue();
	}

	@Override
	public @Nullable Object at(int oclIndex) {
		if (!collectionStrategy.isOrdered()) {
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
		List<@Nullable Object> listOfElements = lazyListOfElements;
		if (listOfElements == null) {
			if (lazyIterator) {
				System.err.println(NameUtil.debugSimpleName(this) + " re-iterating");
				//				throw new UnsupportedOperationException();
				return new LazyCollectionValueImpl(typeId, inputIterator.reIterator(), lazyDepth);
			}
			collectionStrategy.loadCache(this, null, null);
		}
		return this;
	}


	@Override
	public boolean canBeCached() {
		return !lazyIterator || (lazyListOfElements != null);
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

	/*	protected void createCache(@Nullable AbstractCollectionStrategy oldCollectionStrategy) {
		assert lazyListOfElements == null;
		assert lazyMapOfElement2elementCount == null;
		this.lazyListOfElements = new ArrayList<>();
		if (!collectionStrategy.isSequence()) {
			this.lazyMapOfElement2elementCount = new HashMap<>();
		}
	} */

	protected @NonNull LazyIterator createCachedIterator() {
		List<@Nullable Object> listOfElements = lazyListOfElements;
		assert listOfElements != null;
		if (collectionStrategy.isBag()) {
			Map<@Nullable Object, @NonNull ElementCount> mapOfElement2elementCount = getMapOfElement2elementCount();
			assert mapOfElement2elementCount != null;
			return new CachedBagIterator(listOfElements, mapOfElement2elementCount);
		}
		else if (inputIterator.hasNext()) {
			return new CachingListIterator();
		}
		else {
			return new CachedListIterator(listOfElements);
		}
	}

	@Override
	public @NonNull CollectionValue eagerIterable() {
		//		cachedIterable();
		getListOfElements();
		return this;
	}

	@Override
	public @NonNull LazyIterator eagerIterator() {
		return eagerIterable().lazyIterator();
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
				if (equalsStrategy.isEqual(value, next)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public @NonNull Boolean excludesAll(@NonNull CollectionValue c) {
		Iterable<@Nullable Object> cachedIterable = c.cachedIterable();
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
					if (equalsStrategy.isEqual(e1, e2)) {
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

	@Override
	public @NonNull Collection<@Nullable Object> getElements() {
		if (!isBag()) {
			return getListOfElements();
		}
		else {
			return Lists.newArrayList(lazyIterator());			// FIXME avoid this
		}
	}

	//	@Override
	//	public @NonNull EqualsStrategy getEqualsStrategy() {
	//		return equalsStrategy;
	//	}

	@Override
	public @NonNull String getKind() {
		return collectionStrategy.getKind();
	}

	/**
	 * Ensure that all lazy iterations have completed and then return a list of all elements.
	 */
	@Override
	public synchronized @NonNull List<@Nullable Object> getListOfElements() {
		cachedIterable();
		for (int nextCount; (nextCount = inputIterator.hasNextCount()) > 0; ) {
			collectionStrategy.addTo(this, inputIterator.next(), nextCount);
		}
		List<@Nullable Object> listOfElements = lazyListOfElements;
		assert listOfElements != null;
		return listOfElements;
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
	 * Ensure that all lazy iterations have completed and then return a set of all elements.
	 *
	public @NonNull Set<@Nullable Object> getSetOfElements() {
		Map<@Nullable Object, @NonNull ElementCount> lazyMapOfElement2elementCount2 = lazyMapOfElement2elementCount;
		if (lazyMapOfElement2elementCount2 == null) {
			lazyMapOfElement2elementCount2 = getMapOfElement2elementCount();
		}
		return lazyMapOfElement2elementCount2.keySet();
	} */

	@Override
	public @NonNull CollectionTypeId getTypeId() {
		return typeId;
	}

	protected boolean hasCache() {
		return lazyListOfElements != null;
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
				if (equalsStrategy.isEqual(value, next)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public @NonNull Boolean includesAll(@NonNull CollectionValue c) {
		Iterator<@Nullable Object> iterator = c.lazyIterator();
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
					if (equalsStrategy.isEqual(e1, e2)) {
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
		MutableIterable mutableValue = FromCollectionValueIterator.create(typeId, this).mutableIterable();
		assert mutableValue != null;
		List<@Nullable Object> values = mutableValue.getListOfElements();
		values.add(javaIindex, object);
		return mutableValue;
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

	@Override
	public boolean isBag() {
		return collectionStrategy.isBag();
	}

	@Override
	public boolean isOrdered() {
		return collectionStrategy.isOrdered();
	}

	@Override
	public boolean isOrderedSet() {
		return collectionStrategy.isOrderedSet();
	}

	@Override
	public boolean isSequence() {
		return collectionStrategy.isSequence();
	}

	@Override
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
		return createCachedIterator();
	}

	@Override
	public @Nullable Object last() {
		return at(intSize());
	}

	@Override
	public synchronized @NonNull LazyIterator lazyIterator() {
		if (lazyListOfElements == null) {
			if (!lazyIterator) {						// First time
				lazyIterator = true;
				return inputIterator;					// use the input iterator
			}
			LazyIterator reIterator = inputIterator.reIterator();
			if (inputIterator.isCached()) {				// If the input iterator is cached
				return reIterator;						// use a reIterator
			}
			collectionStrategy.loadCache(this, null, null);							// activate the cache
			for (int nextCount; (nextCount = reIterator.hasNextCount()) > 0; ) {	// Populate cache from the reIterator
				collectionStrategy.addTo(this, reIterator.next(), nextCount);
			}
		}
		return createCachedIterator();
	}

	@Override
	public @NonNull CollectionValue minus(@NonNull CollectionValue that) {
		return ExcludingAllIterator.excludingAll(this, that);
	}

	@Override
	public void mutableAppend(@Nullable Object rightValue) {
		assert hashCode == 0;
		collectionStrategy.appendTo(this, rightValue, 1);
	}

	@Override
	public void mutableAppendAll(@NonNull LazyIterator rightIterator) {
		assert hashCode == 0;
		if (!collectionStrategy.isSequence()) {
			for (int nextCount; (nextCount = rightIterator.hasNextCount()) > 0; ) {
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
		assert hashCode == 0;
		collectionStrategy.asBag(this);
		collectionStrategy = BagStrategy.INSTANCE;
	}

	@Override
	public void mutableAsOrderedSet() {
		assert hashCode == 0;
		collectionStrategy.asUnique(this);
		collectionStrategy = OrderedSetStrategy.INSTANCE;
	}

	@Override
	public void mutableAsSequence() {
		assert hashCode == 0;
		collectionStrategy.asSequence(this);
		collectionStrategy = SequenceStrategy.INSTANCE;
	}

	@Override
	public void mutableAsSet() {
		assert hashCode == 0;
		collectionStrategy.asUnique(this);
		collectionStrategy = SetStrategy.INSTANCE;
	}

	@Override
	public void mutableExcluding(@Nullable Object rightValue) {
		assert hashCode == 0;
		collectionStrategy.removeFrom(this, rightValue, 1);
	}

	@Override
	public void mutableExcludingAll(@NonNull LazyIterator rightIterator) {
		assert hashCode == 0;
		if (!collectionStrategy.isSequence()) {
			for (int nextCount; (nextCount = rightIterator.hasNextCount()) > 0; ) {
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
	public boolean mutableIncluding(@Nullable Object rightValue) {
		assert hashCode == 0;
		return collectionStrategy.addTo(this, rightValue, 1);
	}

	@Override
	public void mutableIncludingAll(@NonNull LazyIterator rightIterator) {
		assert hashCode == 0;
		if (!collectionStrategy.isSequence()) {
			for (int nextCount; (nextCount = rightIterator.hasNextCount()) > 0; ) {
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
	public void mutableIntersection(@NonNull LazyIterator rightIterator, boolean isUnique) {
		assert hashCode == 0;
		assert ((CollectionValue)this).isUnique() || !((CollectionValue)this).isOrdered();
		Map<@Nullable Object, @NonNull ElementCount> savedMapOfElement2elementCount = getMapOfElement2elementCount();
		lazyListOfElements = new ArrayList<>();
		size = 0;
		collectionStrategy = isUnique ? SetStrategy.INSTANCE : BagStrategy.INSTANCE;
		for (int rightCount; (rightCount = rightIterator.hasNextCount()) > 0; ) {
			@Nullable Object rightValue = rightIterator.next();
			ElementCount leftElementCount = savedMapOfElement2elementCount.get(rightValue);
			if (leftElementCount != null) {
				collectionStrategy.addTo(this, rightValue, Math.min(leftElementCount.intValue(), rightCount));
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
	public void mutableUnion(@NonNull LazyIterator rightIterator, boolean isUnique) {
		assert hashCode == 0;
		assert ((CollectionValue)this).isUnique() || !((CollectionValue)this).isOrdered();
		collectionStrategy = isUnique ? SetStrategy.INSTANCE : BagStrategy.INSTANCE;
		for (int rightCount; (rightCount = rightIterator.hasNextCount()) > 0; ) {
			collectionStrategy.addTo(this, rightIterator.next(), rightCount);
		}
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
		Iterable<@Nullable Object> cachedIterable = c.cachedIterable();
		Iterator<@Nullable Object> iterator1 = lazyIterator();
		while (iterator1.hasNext()) {
			Object next1 = iterator1.next();
			for (Object next2 : cachedIterable) {
				result.add(new TupleValueImpl(tupleTypeId, next1, next2));
			}
		}
		return result;
	}

	@Override
	public @NonNull CollectionValue reValue() {
		if (lazyListOfElements != null) {			// If we already have a cache
			return this;							//  another lazy iterator can use the cache
		}
		else if (!lazyIterator) {					// If we haven't even started to iterate
			return this;							//  a lazy iterator can still be created
		}
		else {										// If we started to iterate without caching
			return new LazyCollectionValueImpl(typeId, inputIterator.reIterator(), lazyDepth);	// only a new value and reIterator() guarantees to re-traverse
		}
	}

	@Override
	public @NonNull CollectionValue reverse() {
		MutableIterable mutableValue = FromCollectionValueIterator.create(typeId, this).mutableIterable();
		assert mutableValue != null;
		List<@Nullable Object> values = mutableValue.getListOfElements();
		Collections.reverse(values);
		return mutableValue;
	}

	@Override
	public @NonNull IntegerValue size() {
		return ValueUtil.integerValueOf(intSize());
	}

	@Override
	public @NonNull CollectionValue sort(@NonNull Comparator<@Nullable Object> comparator) {
		MutableIterable mutableValue = FromCollectionValueIterator.create(typeId, this).mutableIterable();
		assert mutableValue != null;
		List<@Nullable Object> values = mutableValue.getListOfElements();
		Collections.sort(values, comparator);
		return mutableValue;
	}

	@Override
	public @NonNull CollectionValue subCollection(int lower, int upper) {
		return SubCollectionIterator.subCollection(this, lower, upper);
	}

	@Override
	public @NonNull CollectionValue symmetricDifference(@NonNull CollectionValue that) {
		return SymmetricDifferenceIterator.symmetricDifference(this, that);
	}

	@Override
	public @NonNull CollectionValue toSequenceValue() {
		//		return new MutableCollectionValueImpl(TypeUtil.getSequenceTypeId(typeId), lazyIterator());		// FIXME
		return asSequenceValue();
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 100);
		return s.toString();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		if (lazyListOfElements == null) {
			s.append("Lazy");
			s.append(getKind());
			inputIterator.reIterator().toString(s, sizeLimit);
		}
		else {
			s.append(getKind());
			lazyIterator().toString(s, sizeLimit);
		}
	}

	@Override
	public @NonNull CollectionValue union(@NonNull CollectionValue that) {
		return IncludingAllIterator.union(this, that);
	}
}
