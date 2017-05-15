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

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
import org.eclipse.ocl.pivot.internal.values.BagImpl;
import org.eclipse.ocl.pivot.internal.values.BagValueImpl;
import org.eclipse.ocl.pivot.internal.values.CollectionStrategy;
import org.eclipse.ocl.pivot.internal.values.SetValueImpl;
import org.eclipse.ocl.pivot.internal.values.SparseOrderedSetValueImpl;
import org.eclipse.ocl.pivot.internal.values.SparseSequenceValueImpl;
import org.eclipse.ocl.pivot.internal.values.TupleValueImpl;
import org.eclipse.ocl.pivot.internal.values.ValueImpl;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.BagValue;
import org.eclipse.ocl.pivot.values.BaggableIterator;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.LazyCollectionValue;
import org.eclipse.ocl.pivot.values.OrderedCollectionValue;
import org.eclipse.ocl.pivot.values.OrderedSetValue;
import org.eclipse.ocl.pivot.values.SequenceValue;
import org.eclipse.ocl.pivot.values.SetValue;
import org.eclipse.ocl.pivot.values.TupleValue;
import org.eclipse.ocl.pivot.values.UniqueCollectionValue;
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
public abstract class LazyCollectionValueImpl extends ValueImpl implements LazyCollectionValue, BaggableIterator<@Nullable Object>
{
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

	public static @NonNull BaggableNullIterator EMPTY_ITERATOR = new BaggableNullIterator();

	public static @Nullable Map<@NonNull Class<?>, @NonNull Integer> debugCollectionClass2lazy = null;
	public static @Nullable Map<@NonNull Class<?>, @NonNull Integer> debugCollectionClass2cached = null;
	public static @Nullable Map<@NonNull Class<?>, @NonNull Integer> debugCollectionClass2reiterated = null;

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

	protected final @NonNull CollectionTypeId typeId;

	private final @NonNull CollectionStrategy initialCollectionStrategy;

	/**
	 * The hashCode of the boxed values in this collection.
	 */
	private int hashCode = 0;

	/**
	 * A non-null lazyIterable provides the lazily populated cache. It may remain null if the LazyCollectionValue
	 * is used solely as a bypass lazyterator.
	 */
	private @Nullable LazyIterable<@Nullable Object> lazyIterable = null;

	/**
	 * Set true if the first usage of this LazyCollectionValue is a bypass lazyIterator(). Once set any further
	 * attempts at lazy iteration force a reIterator() to be created that then caches to inhibit further further
	 * re-iterations.
	 */
	private boolean lazyIterator = false;

	/**
	 * The next value to be returned by this iterator, if hasNext is true.
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

	protected LazyCollectionValueImpl(@NonNull CollectionTypeId typeId) {
		this.typeId = typeId;
		this.initialCollectionStrategy = LazyIterable.getCollectionStrategy(typeId);
	}

	//	@Override
	public @NonNull CollectionValue append(@Nullable Object object) {
		return AppendIterator.append(getTypeId(), this, object);
	}

	//	@Override
	public @NonNull CollectionValue appendAll(@NonNull CollectionValue values) {
		return AppendAllIterator.appendAll(this, values);
	}

	@Override
	public @NonNull BagValue asBagValue() {
		eagerIterable();					// Force an InvalidValueEception to be thrown for any invalid element
		return new AsBagIterator.FromCollectionValue(this);
	}

	@Override
	public @NonNull CollectionValue asCollectionValue() {
		eagerIterable();			// Force an InvalidValueEception to be thrown for any invalid element
		return this;
	}

	@Override
	public @NonNull Collection<@Nullable Object> asCollection() {
		return asEagerCollectionValue().asCollection();
	}

	public @NonNull CollectionValue asEagerCollectionValue() {
		if (isOrdered()) {
			if (isUnique()) {
				return new SparseOrderedSetValueImpl(getTypeId(), getElements());
			}
			else {
				return new SparseSequenceValueImpl(getTypeId(), eagerIterable().getListOfElements());
			}
		}
		else {
			if (isUnique()) {
				return new SetValueImpl(getTypeId(), getElements());
			}
			else {
				BagImpl<@Nullable Object> bagImpl = new BagImpl<>();
				for (int count; (count = hasNextCount()) > 0; ) {
					Object next = next();
					bagImpl.put(next, count);
				}
				return new BagValueImpl(getTypeId(), bagImpl);
			}
		}
	}

	private @NonNull OrderedCollectionValue asEagerOrderedCollectionValue() {
		if (isOrdered()) {
			if (isUnique()) {
				return new SparseOrderedSetValueImpl(getTypeId(), getElements());
			}
			else {
				return new SparseSequenceValueImpl(getTypeId(), eagerIterable().getListOfElements());
			}
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public @NonNull List<@Nullable Object> asEcoreObject(@NonNull IdResolver idResolver, @Nullable Class<?> instanceClass) {
		//		eagerIterable();			// Force an InvalidValueEception to be thrown for any invalid element
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
		return asEagerCollectionValue().asCollection();
	}

	@Override
	public @NonNull OrderedCollectionValue asOrderedCollectionValue() {
		return isUnique() ? asOrderedSetValue() : asSequenceValue();
	}

	@Override
	public @NonNull OrderedSetValue asOrderedSetValue() {
		eagerIterable();			// Force an InvalidValueEception to be thrown for any invalid element
		return new AsOrderedSetIterator.FromCollectionValue(this);
	}

	@Override
	public @NonNull SequenceValue asSequenceValue() {
		eagerIterable();			// Force an InvalidValueEception to be thrown for any invalid element
		return new AsSequenceIterator.FromCollectionValue(this);
	}

	@Override
	public @NonNull SetValue asSetValue() {
		eagerIterable();			// Force an InvalidValueEception to be thrown for any invalid element
		return new AsSetIterator.FromCollectionValue(this);
	}

	@Override
	public @NonNull UniqueCollectionValue asUniqueCollectionValue() {
		return isOrdered() ? asOrderedSetValue() : asSetValue();
	}

	//	@Override
	public @Nullable Object at(int oclIndex) {
		if (!isOrdered()) {
			throw new UnsupportedOperationException();
		}
		int javaIindex = oclIndex - 1;
		int size = intSize();
		if (javaIindex < 0 || size <= javaIindex) {
			throw new InvalidValueException(PivotMessages.IndexOutOfRange, oclIndex, size);
		}
		return cachedIterable().get(javaIindex);
	}

	protected @Nullable LazyIterable<@Nullable Object> basicGetIterable() {
		return lazyIterable;
	}

	@Override
	public synchronized @NonNull LazyIterable<@Nullable Object> cachedIterable() {
		LazyIterable<@Nullable Object> lazyIterable2 = lazyIterable;
		if (lazyIterable2 == null) {
			EqualsStrategy equalsStrategy = TypeUtil.getEqualsStrategy(typeId.getElementTypeId(), false);
			Iterator<@Nullable Object> sourceIterator = this;
			if (lazyIterator) {
				System.err.println(NameUtil.debugSimpleName(this) + " re-iterating");
				sourceIterator = reIterator();
			}
			lazyIterable = lazyIterable2 = new LazyIterable<>(sourceIterator, getCollectionStrategy(), equalsStrategy);
			Map<Class<?>, Integer> debugCollectionClass2count2 = debugCollectionClass2cached;
			if (debugCollectionClass2count2 != null) {
				Class<? extends @NonNull CollectionValue> collectionClass = getClass();
				Integer count = debugCollectionClass2count2.get(collectionClass);
				count = count != null ? count+1 : 1;
				debugCollectionClass2count2.put(collectionClass, count);
			}
		}
		return lazyIterable2;
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

	@Override
	public @NonNull LazyIterable<@Nullable Object> eagerIterable() {
		LazyIterable<@Nullable Object> lazyIterable2 = cachedIterable();
		lazyIterable2.size();
		return lazyIterable2;
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
				Map<? extends Object, @NonNull ? extends Number> theseElements = getMapOfElement2elementCount(this);
				Map<? extends Object, @NonNull ? extends Number> thoseElements = getMapOfElement2elementCount(that);
				return theseElements.equals(thoseElements);
			}
		}
	}

	/**
	 * Implementation of the OCL
	 * <tt>Collection::excludes(object : T) : Boolean</tt>
	 * operation.
	 *
	 * @param value an object
	 * @return whether the collection does not include the object
	 */
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

	/**
	 * Implementation of the OCL
	 * <tt>Collection::excludesAll(c : Collection(T)) : Boolean</tt>
	 * operation.
	 *
	 * @param c another collection
	 * @return whether the source collection does not contain any of the
	 *     elements of the other
	 */
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

	public @NonNull CollectionTypeId getBagTypeId() {
		return TypeId.BAG.getSpecializedId(getTypeId().getElementTypeId());
	}

	//	@Override
	public @NonNull CollectionStrategy getCollectionStrategy() {
		if (lazyIterable != null) {
			return lazyIterable.getCollectionStrategy();
		}
		else {
			return initialCollectionStrategy;
		}
	}

	//	@Override
	//	public @NonNull TypeId getElementTypeId() {
	//		return getTypeId().getElementTypeId();
	//	}

	//	@Override
	//	public @NonNull Collection<@Nullable Object> getElements() {
	//		return asCollection();
	//	}

	@Override
	public @NonNull Collection<@Nullable Object> getElements() {
		if (!isBag()) {
			return cachedIterable().getListOfElements();
		}
		else {
			return Lists.newArrayList(lazyIterator());			// FIXME avoid this
		}
	}

	@Override
	public @NonNull String getKind() {
		return getCollectionStrategy().getKind();
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
		return TypeId.ORDERED_SET.getSpecializedId(getTypeId().getElementTypeId());
	}

	public @NonNull CollectionTypeId getSequenceTypeId() {
		return TypeId.SEQUENCE.getSpecializedId(getTypeId().getElementTypeId());
	}

	public @NonNull CollectionTypeId getSetTypeId() {
		return TypeId.SET.getSpecializedId(getTypeId().getElementTypeId());
	}

	@Override
	public @NonNull CollectionTypeId getTypeId() {
		return typeId;
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
						hashCode = computeCollectionHashCode(isOrdered, isUnique, cachedIterable().getListOfElements());
					}
					else {			// Bag
						hashCode = computeCollectionHashCode(cachedIterable().getMapOfElement2elementCount());
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
		return IncludingIterator.including(getTypeId(), this, value);
	}

	@Override
	public @NonNull CollectionValue includingAll(@NonNull CollectionValue values) {
		return IncludingAllIterator.includingAll(getTypeId(), this, values);
	}

	public @NonNull IntegerValue indexOf(@Nullable Object object) {
		return asEagerOrderedCollectionValue().indexOf(object);
	}

	public @NonNull OrderedCollectionValue insertAt(int index, @Nullable Object object) {
		return asEagerOrderedCollectionValue().insertAt(index, object);
	}

	@Override
	public int intSize() {
		return eagerIterable().size();
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
		return getCollectionStrategy().isBag();
	}

	@Override
	public boolean isOrdered() {
		return getCollectionStrategy().isOrdered();
	}

	public boolean isOrderedSet() {
		return getCollectionStrategy().isOrderedSet();
	}

	public boolean isSequence() {
		return getCollectionStrategy().isSequence();
	}

	public boolean isSet() {
		return getCollectionStrategy().isSet();
	}

	@Override
	public boolean isUnique() {
		return getCollectionStrategy().isUnique();
	}

	@Override
	public @NonNull LazyIterable<@Nullable Object> iterable() {
		//		System.err.println(NameUtil.debugSimpleName(this) + " iterable() rather than cachedIterable()");
		return eagerIterable();
	}

	@Override
	public @NonNull BaggableIterator<@Nullable Object> iterator() {
		//		System.err.println(NameUtil.debugSimpleName(this) + " iterator() rather than cachedIterator()");
		return eagerIterable().iterator();
	}

	public @Nullable Object last() {
		return at(intSize());
	}

	@Override
	public synchronized @NonNull BaggableIterator<@Nullable Object> lazyIterator() {
		LazyIterable<@Nullable Object> lazyIterable2 = lazyIterable;
		if (lazyIterable2 != null) {
			return lazyIterable2.iterator();
		}
		if (!lazyIterator) {
			lazyIterator = true;
			return this;
		}
		return cachedIterable().iterator();
	}

	public @NonNull CollectionValue minus(@NonNull CollectionValue that) {
		return ExcludingAllIterator.excludingAll(this, that);
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

	//	@Override
	public @NonNull CollectionValue prepend(@Nullable Object value) {
		return PrependIterator.prepend(getTypeId(), this, value);
	}

	//	@Override
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
	protected abstract @NonNull Iterator<@Nullable Object> reIterator();

	public @NonNull OrderedCollectionValue reverse() {
		return asEagerOrderedCollectionValue().reverse();
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
	public @NonNull OrderedCollectionValue sort(@NonNull Comparator<@Nullable Object> comparator) {
		List<@Nullable Object> values = Lists.newArrayList(lazyIterator());
		Collections.sort(values, comparator);
		if (isUnique()) {
			return new SparseOrderedSetValueImpl(getTypeId(), values);
		}
		else {
			return new SparseSequenceValueImpl(getTypeId(), values);
		}
	}

	public @NonNull CollectionValue subOrderedSet(int lower, int upper) {
		return SubOrderedSetIterator.subOrderedSet(this, lower, upper);
	}

	public @NonNull CollectionValue subSequence(int lower, int upper) {
		return SubSequenceIterator.subSequence(this, lower, upper);
	}

	public @NonNull CollectionValue symmetricDifference(@NonNull CollectionValue that) {
		return SymmetricDifferenceIterator.symmetricDifference(this, that);
	}

	@Override
	public @NonNull SequenceValue toSequenceValue() {
		Iterable<@Nullable Object> elements = Lists.newArrayList(lazyIterator());
		if (isUnique()) {
			return new SparseSequenceValueImpl(getSequenceTypeId(), SparseSequenceValueImpl.createSequenceOfEach(elements));
		}
		else {
			return new SparseSequenceValueImpl(getSequenceTypeId(), Lists.newArrayList(elements));
		}
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 100);
		return s.toString();
	}

	@Override
	public @NonNull CollectionValue union(@NonNull CollectionValue that) {
		return IncludingAllIterator.union(this, that);
	}
}
