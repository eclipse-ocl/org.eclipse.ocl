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

import org.eclipse.emf.common.util.BasicEList;
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
import org.eclipse.ocl.pivot.internal.values.AbstractCollectionFactory;
import org.eclipse.ocl.pivot.internal.values.CollectionFactory;
import org.eclipse.ocl.pivot.internal.values.SparseOrderedSetValueImpl;
import org.eclipse.ocl.pivot.internal.values.SparseSequenceValueImpl;
import org.eclipse.ocl.pivot.internal.values.TupleValueImpl;
import org.eclipse.ocl.pivot.internal.values.ValueImpl;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.BagValue;
import org.eclipse.ocl.pivot.values.BaggableIterator;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.OrderedCollectionValue;
import org.eclipse.ocl.pivot.values.OrderedSetValue;
import org.eclipse.ocl.pivot.values.SequenceValue;
import org.eclipse.ocl.pivot.values.SetValue;
import org.eclipse.ocl.pivot.values.TupleValue;
import org.eclipse.ocl.pivot.values.Value;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * AbstractBaggableValueImpl provides the common functionality for eager and lazy CollectionValues.
 * @generated NOT
 * @since 1.3
 * @noextend This class is not intended to be subclassed by clients.
 */
public abstract class AbstractBaggableValueImpl extends ValueImpl implements CollectionValue, Iterable<@Nullable Object>
{
	/**
	 * Optimized iterator over an Array for use in OCL contents where the array is known to be stable
	 * and any call to next() is guarded by hasNext().
	 */
	private static class ArrayIterator<T> implements BaggableIterator<T>
	{
		protected final T @NonNull [] elements;
		protected final int size;
		private int index;

		/**
		 * Returns new array iterator over the given object array
		 */
		public ArrayIterator(T @NonNull [] elements, int size) {
			this.elements = elements;
			index = 0;
			this.size = size;
		}

		/**
		 * Returns true if this iterator contains more elements.
		 */
		@Override
		public boolean hasNext() {
			return index < size;
		}

		/**
		 * Returns 1 if this iterator contains more elements.
		 */
		@Override
		public int hasNextCount() {
			return index < size ? 1 : 0;
		}

		/**
		 * Returns the next element of this iterator.
		 */
		@Override
		public T next() {
			return elements[index++];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * Optimized iterator over a List for use in OCL contents where the list is known to be stable
	 * and any call to next() is guarded by hasNext().
	 */
	private static class ListIterator<T> implements BaggableIterator<T>
	{
		protected final @NonNull List<T> elements;
		protected final int size;
		private int index;

		/**
		 * Returns new array iterator over the given object array
		 */
		public ListIterator(@NonNull List<T> elements) {
			this.elements = elements;
			index = 0;
			this.size = elements.size();
		}

		/**
		 * Returns true if this iterator contains more elements.
		 */
		@Override
		public boolean hasNext() {
			return index < size;
		}

		/**
		 * Returns 1 if this iterator contains more elements.
		 */
		@Override
		public int hasNextCount() {
			return index < size ? 1 : 0;
		}

		/**
		 * Returns the next element of this iterator.
		 */
		@Override
		public T next() {
			return elements.get(index++);
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String toString() {
			StringBuilder s = new StringBuilder();
			s.append("List");
			appendIterable(s, elements, 50);
			return s.toString();
		}
	}

	/**
	 * Optimized iterator over an empty Collection.
	 */
	private static class NullIterator implements BaggableIterator<@Nullable Object>
	{
		/**
		 * Returns new array iterator over the given object array
		 */
		public NullIterator() {}

		/**
		 * Returns true if this iterator contains more elements.
		 */
		@Override
		public boolean hasNext() {
			return false;
		}

		/**
		 * Returns 1 if this iterator contains more elements.
		 */
		@Override
		public int hasNextCount() {
			return 0;
		}

		/**
		 * Returns the next element of this iterator.
		 */
		@Override
		public Object next() {
			throw new NoSuchElementException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

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

	public static @NonNull NullIterator EMPTY_ITERATOR = new NullIterator();


	/**
	 * @since 1.3
	 */
	public static @Nullable Map<@NonNull Class<?>, @NonNull Integer> collectionClass2count = null;

	/**
	 * @since 1.3
	 */
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

	/**
	 * Optimized iterator over a List for use in OCL contents where the list is known to be stable
	 * and any call to next() is guarded by hasNext().
	 */
	public static class WrappedBaggableIterator<T> implements BaggableIterator<T>
	{
		protected final @NonNull Iterator<? extends T> iterator;

		/**
		 * Returns new array iterator over the given object array
		 */
		public WrappedBaggableIterator(@NonNull Iterator<? extends T> iterator) {
			assert !(iterator instanceof BaggableIterator);
			this.iterator = iterator;
		}

		/**
		 * Returns true if this iterator contains more elements.
		 */
		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		/**
		 * Returns 1 if this iterator contains more elements.
		 */
		@Override
		public int hasNextCount() {
			return iterator.hasNext() ? 1 : 0;
		}

		/**
		 * Returns the next element of this iterator.
		 */
		@Override
		public T next() {
			return iterator.next();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String toString() {
			return iterator.toString();
		}
	}

	/**
	 * A simple public static method that may be used to force class initialization.
	 */
	public static void initStatics() {}

	protected final @NonNull CollectionTypeId typeId;
	protected final @NonNull CollectionFactory collectionFactory;

	protected AbstractBaggableValueImpl(@NonNull CollectionTypeId typeId) {
		this.typeId = typeId;
		this.collectionFactory = AbstractCollectionFactory.getCollectionFactory(typeId);
		Map<Class<?>, Integer> collectionClass2count2 = collectionClass2count;
		if (collectionClass2count2 != null) {
			Class<? extends @NonNull CollectionValue> collectionClass = getClass();
			Integer count = collectionClass2count2.get(collectionClass);
			count = count != null ? count+1 : 1;
			collectionClass2count2.put(collectionClass, count);
		}
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
		intSize();			// Force an InvalidValueEception to be thrown for any invalid element
		return new AsBagIterator(this);
	}

	@Override
	public @NonNull CollectionValue asCollectionValue() {
		intSize();			// Force an InvalidValueEception to be thrown for any invalid element
		return this;
	}

	@Override
	public @NonNull List<@Nullable Object> asEcoreObject(@NonNull IdResolver idResolver, @Nullable Class<?> instanceClass) {
		//		intSize();			// Force an InvalidValueEception to be thrown for any invalid element
		//		return new AsEcoreIterator(this, idResolver, instanceClass).getListOfElements();
		@Nullable Object[] unboxedValues = new @Nullable Object[intSize()];
		int i= 0;
		for (Object element : iterable()) {
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
	public @NonNull OrderedSetValue asOrderedSetValue() {
		intSize();			// Force an InvalidValueEception to be thrown for any invalid element
		return new AsOrderedSetIterator(this);
	}

	@Override
	public @NonNull SequenceValue asSequenceValue() {
		intSize();			// Force an InvalidValueEception to be thrown for any invalid element
		return new AsSequenceIterator(this);
	}

	@Override
	public @NonNull SetValue asSetValue() {
		intSize();			// Force an InvalidValueEception to be thrown for any invalid element
		return new AsSetIterator(this);
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

	/**
	 * Implementation of the OCL
	 * <tt>Collection::count(object : T) : Integer</tt>
	 * operation.
	 *
	 * @param value an object
	 * @return the number of occurrences of the object in the collection
	 * @throws InvalidValueException
	 */
	@Override
	public @NonNull IntegerValue count(@Nullable Object value) {
		long count = 0;
		if (value == null) {
			for (Object next : iterable()) {
				if (next == null) {
					count++;
				}
			}
		}
		else {
			for (Object next : iterable()) {
				if (value.equals(next)) {
					count++;
				}
			}
		}
		return ValueUtil.integerValueOf(count);
	}

	@Override
	public boolean equals(Object obj) {
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
				Map<? extends Object, @NonNull ? extends Number> theseElements = getMapOfElement2elementCount();
				Map<? extends Object, @NonNull ? extends Number> thoseElements = that.getMapOfElement2elementCount();
				//				Collection<? extends Object> theseElements = this.getElements();
				//				Collection<? extends Object> thoseElements = that.getElements();
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
		if (value == null) {
			for (Object next : this) {
				if (next == null) {
					return false;
				}
			}
		}
		else {
			for (Object next : this) {
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
		for (Object e1 : this) {
			if (e1 == null) {
				for (Object e2 : c.iterable()) {
					if (e2 == null) {
						return false;
					}
				}
			}
			else {
				for (Object e2 : c.iterable()) {
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

	/**
	 * Returns true if any element flattened.
	 * @throws InvalidValueException
	 */
	@Override @Deprecated
	public boolean flatten(@NonNull Collection<Object> flattenedElements) {
		boolean flattened = false;
		for (Object element : iterable()) {
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
		return TypeId.BAG.getSpecializedId(getElementTypeId());
	}

	@Override
	public @NonNull CollectionFactory getCollectionFactory() {
		return collectionFactory;
	}

	@Override
	public @NonNull TypeId getElementTypeId() {
		return getTypeId().getElementTypeId();
	}

	@Override
	public @NonNull Collection<@Nullable Object> getElements() {
		return asCollection();
	}

	@Override
	public @NonNull String getKind() {
		return collectionFactory.getKind();
	}

	@Override
	public @NonNull Map<@Nullable Object, @NonNull ? extends Number> getMapOfElement2elementCount() {
		throw new UnsupportedOperationException();
	}

	public @NonNull CollectionTypeId getOrderedSetTypeId() {
		return TypeId.ORDERED_SET.getSpecializedId(getElementTypeId());
	}

	public @NonNull CollectionTypeId getSequenceTypeId() {
		return TypeId.SEQUENCE.getSpecializedId(getElementTypeId());
	}

	public @NonNull CollectionTypeId getSetTypeId() {
		return TypeId.SET.getSpecializedId(getElementTypeId());
	}

	@Override
	public @NonNull CollectionTypeId getTypeId() {
		return typeId;
	}

	@Override
	public abstract int hashCode();

	@Override
	public @NonNull Boolean includes(@Nullable Object value) {
		return Iterables.contains(iterable(), value) != false;			// FIXME redundant test to suppress warning
	}

	/**
	 * Implementation of the OCL
	 * <tt>Collection::includesAll(c : Collection(T)) : Boolean</tt>
	 * operation.
	 *
	 * @param c another collection
	 * @return whether the source collection includes all of the elements
	 *     of the other
	 */
	@Override
	public @NonNull Boolean includesAll(@NonNull CollectionValue c) {
		for (Object e1 : c.iterable()) {
			boolean gotIt = false;
			if (e1 == null) {
				for (Object e2 : this) {
					if (e2 == null) {
						gotIt = true;
						break;
					}
				}
			}
			else {
				for (Object e2 : this) {
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

	@Override
	public @NonNull CollectionValue intersection(@NonNull CollectionValue that) {
		return IntersectionIterator.intersection(this, that);
	}

	@Override
	public @NonNull Boolean isEmpty() {
		return intSize() == 0;
	}

	public boolean isBag() {
		return collectionFactory.isBag();
	}

	@Override
	public boolean isOrdered() {
		return collectionFactory.isOrdered();
	}

	public boolean isOrderedSet() {
		return collectionFactory.isOrderedSet();
	}

	public boolean isSequence() {
		return collectionFactory.isSequence();
	}

	public boolean isSet() {
		return collectionFactory.isSet();
	}

	@Override
	public boolean isUnique() {
		return collectionFactory.isUnique();
	}

	@Override
	public @NonNull BaggableIterator<@Nullable Object> iterator() {
		Iterable<@Nullable Object> elements = iterable();
		if (this instanceof BaggableIterator) {
			iterable();
			@SuppressWarnings("unchecked")
			BaggableIterator<@Nullable Object> castElements = (BaggableIterator<@Nullable Object>)this;
			return castElements;
		}
		else if (elements instanceof BaggableIterator) {
			@SuppressWarnings("unchecked")
			BaggableIterator<@Nullable Object> castElements = (BaggableIterator<@Nullable Object>)elements;
			return castElements;
		}
		else if (elements instanceof BasicEList) {
			BasicEList<Object> castElements = (BasicEList<Object>)elements;
			@SuppressWarnings("null")@Nullable Object[] data = castElements.data();
			return data != null ? new ArrayIterator<>(data, castElements.size()) : EMPTY_ITERATOR;
		}
		else if (elements instanceof List<?>) {
			List<@Nullable Object> castElements = (List<@Nullable Object>)elements;
			return new ListIterator<>(castElements);
		}
		else {
			return new WrappedBaggableIterator<>(elements.iterator());
		}
	}

	public @NonNull CollectionValue minus(@NonNull CollectionValue that) {
		return ExcludingAllIterator.excludingAll(this, that);
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
		for (Object next1 : iterable()) {
			for (Object next2 : c.iterable()) {
				result.add(new TupleValueImpl(tupleTypeId, next1, next2));
			}
		}
		return result;
	}

	@Override
	public @NonNull IntegerValue size() {
		return ValueUtil.integerValueOf(intSize());
	}

	@Override
	public @NonNull OrderedCollectionValue sort(@NonNull Comparator<@Nullable Object> comparator) {
		List<@Nullable Object> values = Lists.newArrayList(iterable());
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
		Iterable<@Nullable Object> elements = iterable();
		if (isUnique()) {
			return new SparseSequenceValueImpl(getSequenceTypeId(), SparseSequenceValueImpl.createSequenceOfEach(elements));
		}
		else {
			return new SparseSequenceValueImpl(getSequenceTypeId(), Lists.newArrayList(elements));
		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 100);
		return s.toString();
	}

	@Override
	public @NonNull CollectionValue union(@NonNull CollectionValue that) {
		return IncludingAllIterator.union(this, that);
	}
}
