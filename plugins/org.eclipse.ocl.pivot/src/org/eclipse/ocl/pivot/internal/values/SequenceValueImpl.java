/*******************************************************************************
 * Copyright (c) 2011, 2016 Willink Transformations and others.
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.iterators.AppendAllIterator;
import org.eclipse.ocl.pivot.internal.iterators.AppendIterator;
import org.eclipse.ocl.pivot.internal.iterators.ExcludingAllIterator;
import org.eclipse.ocl.pivot.internal.iterators.ExcludingIterator;
import org.eclipse.ocl.pivot.internal.iterators.FlattenIterator;
import org.eclipse.ocl.pivot.internal.iterators.IncludingAllIterator;
import org.eclipse.ocl.pivot.internal.iterators.IncludingIterator;
import org.eclipse.ocl.pivot.internal.iterators.PrependAllIterator;
import org.eclipse.ocl.pivot.internal.iterators.PrependIterator;
import org.eclipse.ocl.pivot.internal.iterators.SubSequenceIterator;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * @generated NOT
 */
public abstract class SequenceValueImpl extends CollectionValueImpl
{
	public SequenceValueImpl(@NonNull CollectionTypeId typeId, @NonNull List<@Nullable Object> values) {
		super(typeId, values);
	}

	@Override
	public @NonNull CollectionValue append(@Nullable Object value) {
		return AppendIterator.append(getTypeId(), this, value).asOrderedCollectionValue();
	}

	@Override
	public @NonNull CollectionValue appendAll(@NonNull CollectionValue values) {
		return AppendAllIterator.appendAll(this, values).asOrderedCollectionValue();
	}

	/*	@Override
	public @NonNull OrderedCollectionValue append(@Nullable Object object) {
		if (object instanceof InvalidValueException) {
			throw new InvalidValueException(PivotMessages.InvalidSource, "append");
		}
		List<Object> result = new ArrayList<Object>(elements);
		result.add(object);
		return new SparseSequenceValueImpl(getTypeId(), result);
	} */

	/*	@Override
	public @NonNull OrderedCollectionValue appendAll(@NonNull OrderedCollectionValue objects) {
		List<Object> result = new ArrayList<Object>(elements);
		result.addAll(objects.getElements());
		return new SparseSequenceValueImpl(getTypeId(), result);
	} */

	@Override
	public @NonNull List<@Nullable Object> asList() {
		return getElements();
	}

	@Override
	public @NonNull CollectionValue asOrderedCollectionValue() {
		return this;
	}

	@Override
	public @NonNull CollectionValue asSequenceValue() {
		return this;
	}

	@Override
	public @NonNull List<Object> asUnboxedObject(@NonNull IdResolver idResolver) {
		List<Object> unboxedValues = new ArrayList<Object>();
		for (Object boxedValue : elements) {
			unboxedValues.add(idResolver.unboxedValueOf(boxedValue));
		}
		return unboxedValues;
	}

	@Override
	public @Nullable Object at(int index) {
		index = index - 1;
		if (index < 0 || elements.size() <= index) {
			throw new InvalidValueException(PivotMessages.IndexOutOfRange, index + 1, size());
		}
		return getElements().get(index);
	}

	@Override
	public @NonNull CollectionValue excluding(@Nullable Object value) {
		return ExcludingIterator.excluding(this, value).asSequenceValue();
	}

	@Override
	public @NonNull CollectionValue excludingAll(@NonNull CollectionValue values) {
		return ExcludingAllIterator.excludingAll(this, values).asSequenceValue();
	}

	@Override
	public @Nullable Object first() {
		if (elements.size() <= 0) {
			throw new InvalidValueException(PivotMessages.EmptyCollection, TypeId.SEQUENCE_NAME, "first");
		}
		return getElements().get(0);
	}

	@Override
	public @NonNull CollectionValue flatten() {
		return FlattenIterator.flatten(this).asSequenceValue();
	}

	@Override
	public @NonNull List<@Nullable Object> getElements() {
		return (List<@Nullable Object>) elements;
	}

	@Override
	public @NonNull String getKind() {
		return TypeId.SEQUENCE_NAME;
	}

	@Override
	public @NonNull CollectionValue including(@Nullable Object value) {
		return IncludingIterator.including(getTypeId(), this, value).asSequenceValue();
	}

	@Override
	public @NonNull CollectionValue includingAll(@NonNull CollectionValue values) {
		return IncludingAllIterator.includingAll(getTypeId(), this, values).asSequenceValue();
	}

	@Override
	public @NonNull IntegerValue indexOf(@Nullable Object object) {
		int index = getElements().indexOf(object);
		if (index < 0) {
			throw new InvalidValueException(PivotMessages.MissingValue, "indexOf");
		}
		return ValueUtil.integerValueOf(index+1);
	}

	@Override
	public @NonNull CollectionValue insertAt(int index, @Nullable Object object) {
		if (object instanceof InvalidValueException) {
			throw new InvalidValueException(PivotMessages.InvalidSource, "insertAt");
		}
		index = index - 1;
		if (index < 0 || index > elements.size()) {
			throw new InvalidValueException(PivotMessages.IndexOutOfRange, index + 1, size());
		}
		List<@Nullable Object> result = new ArrayList<>(elements);
		result.add(index, object);
		return new SparseSequenceValueImpl(getTypeId(), result);
	}

	@Override
	public boolean isOrdered() {
		return true;
	}

	@Override
	public boolean isUnique() {
		return false;
	}

	@Override
	public @Nullable Object last() {
		int size = elements.size();
		if (size <= 0) {
			throw new InvalidValueException(PivotMessages.EmptyCollection, TypeId.SEQUENCE_NAME, "last");
		}
		return getElements().get(size-1);
	}

	@Override
	public @NonNull CollectionValue prepend(@Nullable Object value) {
		return PrependIterator.prepend(getTypeId(), this, value).asSequenceValue();
	}

	@Override
	public @NonNull CollectionValue prependAll(@NonNull CollectionValue values) {
		return PrependAllIterator.prependAll(this, values).asSequenceValue();
	}

	/*	@Override
	public @NonNull CollectionValue prepend(@Nullable Object object) {
		if (object instanceof InvalidValueException) {
			throw new InvalidValueException(PivotMessages.InvalidSource, "prepend");
		}
		List<Object> result = new ArrayList<Object>();
		result.add(object);
		result.addAll(elements);
		return new SparseSequenceValueImpl(getTypeId(), result);
	} */

	/*	@Override
	public @NonNull CollectionValue prependAll(@NonNull CollectionValue objects) {
		List<Object> result = new ArrayList<Object>(objects.getElements());
		result.addAll(elements);
		return new SparseSequenceValueImpl(getTypeId(), result);
	} */

	@Override
	public @NonNull CollectionValue reverse() {
		List<@Nullable Object> elements = new ArrayList<>(this.elements);
		Collections.reverse(elements);
		return new SparseSequenceValueImpl(getTypeId(), elements);
	}

	@Override
	public @NonNull CollectionValue sort(@NonNull Comparator<@Nullable Object> comparator) {
		List<@Nullable Object> values = new ArrayList<>(elements);
		Collections.sort(values, comparator);
		return new SparseSequenceValueImpl(getTypeId(), values);
	}

	@Override
	public @NonNull CollectionValue subSequence(int lower, int upper) {
		return SubSequenceIterator.subSequence(this, lower, upper).asSequenceValue();
	}

	/**
	 * Implementation of the OCL
	 * <tt>Sequence::subSequence(lower : Integer, upper : Integer) : Sequence(T)</tt>
	 * operation.
	 *
	 * @param lower the 1-based (in OCL fashion) inclusive lower bound
	 * @param upper the 1-based (in OCL fashion) inclusive upper bound
	 * @return the source collection with the object inserted at the index
	 *
	 * @throws IndexOutOfBoundsException if an index is out of bounds
	 * @throws IllegalArgumentException if the lower bound is greater than the upper
	 *
	@Override
	public @NonNull CollectionValue subSequence(int lower, int upper) {
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

		List<Object> result = new ArrayList<Object>();
		int curr = 0;
		for (Object object : iterable()) {
			if (curr >= lower && curr <= upper) {
				result.add(object);
			}
			curr++;
		}
		return new SparseSequenceValueImpl(getTypeId(), result);
	} */

	@Override
	public @NonNull CollectionValue toSequenceValue() {
		return this;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int lengthLimit) {
		s.append(TypeId.SEQUENCE_NAME);
		super.toString(s, lengthLimit);
	}
}
