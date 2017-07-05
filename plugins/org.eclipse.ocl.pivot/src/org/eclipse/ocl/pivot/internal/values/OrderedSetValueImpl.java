/*******************************************************************************
 * Copyright (c) 2010, 2014 Willink Transformations and others.
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
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.iterators.AppendAllIterator;
import org.eclipse.ocl.pivot.internal.iterators.ExcludingAllIterator;
import org.eclipse.ocl.pivot.internal.iterators.ExcludingIterator;
import org.eclipse.ocl.pivot.internal.iterators.IncludingAllIterator;
import org.eclipse.ocl.pivot.internal.iterators.PrependAllIterator;
import org.eclipse.ocl.pivot.internal.iterators.SubOrderedSetIterator;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.OrderedSet;

/**
 * @generated NOT
 */
public abstract class OrderedSetValueImpl extends CollectionValueImpl
{
	public OrderedSetValueImpl(@NonNull CollectionTypeId typeId, @NonNull Collection<@Nullable Object> elements) {
		super(typeId, elements);
		assert checkElementsAreUnique(this.elements);
	}

	@Override
	public @NonNull CollectionValue appendAll(@NonNull CollectionValue values) {
		return AppendAllIterator.appendAll(this, values).asOrderedSetValue();
	}

	/*	@Override
	public @NonNull CollectionValue appendAll(@NonNull CollectionValue objects) {
		OrderedSet<Object> result = new OrderedSetImpl<Object>(elements);
		Collection<? extends Object> thoseElements = objects.getElements();
		result.removeAll(thoseElements);  // appended objects must be last
		result.addAll(thoseElements);
		return new SparseOrderedSetValueImpl(getTypeId(), result);
	} */

	@Override
	public @NonNull CollectionValue asOrderedCollectionValue() {
		return this;
	}

	@Override
	public @NonNull OrderedSetValueImpl asOrderedSetValue() {
		return this;
	}

	@Override
	public @NonNull LinkedHashSet<Object> asUnboxedObject(@NonNull IdResolver idResolver) {
		LinkedHashSet<Object> unboxedValues = new LinkedHashSet<Object>();
		for (Object boxedValue : elements) {
			unboxedValues.add(idResolver.unboxedValueOf(boxedValue));
		}
		return unboxedValues;
	}

	@Override
	public @NonNull CollectionValue asUniqueCollectionValue() {
		return this;
	}

	@Override
	public @Nullable Object at(int index) {
		index = index - 1;
		if (index < 0 || index >= elements.size()) {
			throw new InvalidValueException(PivotMessages.IndexOutOfRange, index + 1, size());
		}
		int curr = 0;
		for (Iterator<? extends Object> it = iterator(); it.hasNext();) {
			Object object = it.next();
			if (curr++ == index) {
				return object;
			}
		}
		throw new InvalidValueException("Null collection content");
	}

	@Override
	public @NonNull CollectionValue excluding(@Nullable Object value) {
		return ExcludingIterator.excluding(this, value).asOrderedSetValue();
	}

	@Override
	public @NonNull CollectionValue excludingAll(@NonNull CollectionValue values) {
		return ExcludingAllIterator.excludingAll(this, values).asOrderedSetValue();
	}

	@Override
	public @NonNull String getKind() {
		return TypeId.ORDERED_SET_NAME;
	}

	@Override
	public @NonNull CollectionValue includingAll(@NonNull CollectionValue values) {
		return IncludingAllIterator.includingAll(getTypeId(), this, values).asOrderedSetValue();
	}

	@Override
	public @NonNull IntegerValue indexOf(@Nullable Object object) {
		int index = 1;
		if (object == null) {
			for (Object next : elements) {
				if (next == null) {
					return ValueUtil.integerValueOf(index);
				}
				index++;
			}
		}
		else {
			for (Object next : elements) {
				if (object.equals(next)) {
					return ValueUtil.integerValueOf(index);
				}
				index++;
			}
		}
		throw new InvalidValueException(PivotMessages.MissingValue, "indexOf");
	}

	@Override
	public @NonNull CollectionValue insertAt(int index, @Nullable Object object) {
		if (object instanceof InvalidValueException) {
			throw new InvalidValueException(PivotMessages.InvalidSource, "insertAt");
		}
		index = index - 1;
		boolean isContained = elements.contains(object);
		int effectiveSize = elements.size() - (isContained ? 1 : 0);
		if ((index < 0) || (effectiveSize < index)) {
			throw new InvalidValueException(PivotMessages.IndexOutOfRange, index + 1, size());
		}

		OrderedSet<@Nullable Object> result = new OrderedSetImpl<>();
		int curr = 0;
		if (object == null) {
			for (Iterator<? extends Object> it = iterator(); it.hasNext();) {
				if (curr == index) {
					result.add(object);
				}
				Object next = it.next();
				if (next != null) {
					result.add(next);
					curr++;
				}
			}
		}
		else {
			for (Iterator<? extends Object> it = iterator(); it.hasNext();) {
				if (curr == index) {
					result.add(object);
				}
				Object next = it.next();
				if (!object.equals(next)) {
					result.add(next);
					curr++;
				}
			}
		}

		if (index == effectiveSize) {
			// the loop finished before we could add the object
			result.add(object);
		}
		return new SparseOrderedSetValueImpl(getTypeId(), result);
	}

	@Override
	public boolean isOrdered() {
		return true;
	}

	@Override
	public boolean isUnique() {
		return true;
	}

	@Override
	public @NonNull CollectionValue minus(@NonNull CollectionValue set) {
		OrderedSet<@Nullable Object> result = new OrderedSetImpl<>(elements);
		result.removeAll(set.asCollection());
		return new SparseOrderedSetValueImpl(getTypeId(), result);
	}

	@Override
	public @NonNull CollectionValue prependAll(@NonNull CollectionValue values) {
		return PrependAllIterator.prependAll(this, values).asOrderedSetValue();
	}

	/*	@Override
	public @NonNull CollectionValue prependAll(@NonNull CollectionValue objects) {
		OrderedSet<Object> result = new OrderedSetImpl<Object>(objects.getElements());
		result.addAll(elements);
		return new SparseOrderedSetValueImpl(getTypeId(), result);
	} */

	@Override
	public @NonNull CollectionValue reverse() {
		List<@Nullable Object> elements = asList();
		Collections.reverse(elements);
		return new SparseOrderedSetValueImpl(getTypeId(), elements);
	}

	@Override
	public @NonNull CollectionValue sort(@NonNull Comparator<@Nullable Object> comparator) {
		List<@Nullable Object> values = new ArrayList<>(elements);
		Collections.sort(values, comparator);
		return new SparseOrderedSetValueImpl(getTypeId(), values);
	}

	@Override
	public @NonNull CollectionValue subOrderedSet(int lower, int upper) {
		return SubOrderedSetIterator.subOrderedSet(this, lower, upper).asOrderedSetValue();
	}

	/*	@Override
	public @NonNull CollectionValue subOrderedSet(int lower, int upper) {
		lower = lower - 1;
		upper = upper - 1;

		if (lower < 0) {
			throw new InvalidValueException(new IndexOutOfBoundsException("lower: " + (lower + 1))); //$NON-NLS-1$
		} else if (upper >= elements.size()) {
			throw new InvalidValueException(new IndexOutOfBoundsException(
				"upper: " + (upper + 1) + ", size: " //$NON-NLS-1$ //$NON-NLS-2$
				+ size()));
		} else if (upper < lower) {
			throw new InvalidValueException(new IllegalArgumentException(
				"lower: " + (lower + 1) + ", upper: " //$NON-NLS-1$ //$NON-NLS-2$
				+ (upper + 1)));
		}

		OrderedSet<Object> result = new OrderedSetImpl<Object>();
		int curr = 0;
		for (Iterator<? extends Object> it = elements.iterator(); it.hasNext();) {
			Object object = it.next();
			if (curr >= lower && curr <= upper) {
				result.add(object);
			}
			curr++;
		}
		return new SparseOrderedSetValueImpl(getTypeId(), result);
	} */

	//	public @NonNull SequenceValue subSequence(int lower, int upper) {
	//		return subOrderedSet(lower, upper);
	//	}

	@Override
	public @NonNull CollectionValue symmetricDifference(@NonNull CollectionValue set) {
		OrderedSet<@Nullable Object> result = new OrderedSetImpl<>(elements);
		for (Object e : set.iterable()) {
			if (result.contains(e)) {
				result.remove(e);
			} else {
				result.add(e);
			}
		}
		return new SparseOrderedSetValueImpl(getTypeId(), result);
	}

	//	public SequenceValue toSequenceValue() {
	//		return this;
	//	}
}
