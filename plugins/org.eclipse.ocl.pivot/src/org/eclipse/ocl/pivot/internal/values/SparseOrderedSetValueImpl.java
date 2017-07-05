/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.values;

import java.util.Collection;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.iterators.AppendIterator;
import org.eclipse.ocl.pivot.internal.iterators.FlattenIterator;
import org.eclipse.ocl.pivot.internal.iterators.IncludingIterator;
import org.eclipse.ocl.pivot.internal.iterators.PrependIterator;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.OrderedSet;
import org.eclipse.ocl.pivot.values.OrderedSetValue;

/**
 * @generated NOT
 */
public class SparseOrderedSetValueImpl extends OrderedSetValueImpl
{
	public static @NonNull OrderedSet<Object> createOrderedSetOfEach(@Nullable Object @NonNull [] boxedValues) {
		OrderedSet<@Nullable Object> result = new OrderedSetImpl<>();
		for (Object boxedValue : boxedValues) {
			result.add(boxedValue);
		}
		return result;
	}

	public static class Accumulator extends SparseOrderedSetValueImpl implements OrderedSetValue.Accumulator
	{
		public Accumulator(@NonNull CollectionTypeId typeId) {
			super(typeId, new OrderedSetImpl<>());
		}

		@Override
		public boolean add(@Nullable Object value) {
			return elements.add(value);
		}
	}

	public SparseOrderedSetValueImpl(@NonNull CollectionTypeId typeId, @NonNull Collection<@Nullable Object> boxedValues) {
		super(typeId, boxedValues);
	}

	@Override
	public @NonNull OrderedSetValue append(@Nullable Object value) {
		return AppendIterator.append(getTypeId(), this, value).asOrderedSetValue();
	}

	/*	@Override
	public @NonNull OrderedSetValue append(@Nullable Object object) {
		if (object instanceof InvalidValueException) {
			throw new InvalidValueException(PivotMessages.InvalidSource, "append");
		}
		OrderedSet<Object> result = new OrderedSetImpl<Object>(elements);
		result.remove(object);  // appended object must be last
		result.add(object);
		return new SparseOrderedSetValueImpl(getTypeId(), result);
	} */

	@Override
	public @Nullable Object first() {
		if (elements.size() <= 0) {
			throw new InvalidValueException(PivotMessages.EmptyCollection, TypeId.ORDERED_SET_NAME, "first");
		}
		return elements.iterator().next();
	}

	@Override
	public @NonNull OrderedSetValue flatten() {
		return FlattenIterator.flatten(this).asOrderedSetValue();
	}

	@Override
	public @NonNull OrderedSetValue including(@Nullable Object value) {
		return IncludingIterator.including(getTypeId(), this, value).asOrderedSetValue();
	}

	@Override
	public @Nullable Object last() {
		if (elements.size() <= 0) {
			throw new InvalidValueException(PivotMessages.EmptyCollection, TypeId.ORDERED_SET_NAME, "last");
		}
		Object result = null;
		for (Object next : elements) {
			result = next;
		}
		return result;
	}

	@Override
	public @NonNull OrderedSetValue prepend(@Nullable Object value) {
		return PrependIterator.prepend(getTypeId(), this, value).asOrderedSetValue();
	}

	/*	@Override
	public @NonNull OrderedSetValue prepend(@Nullable Object object) {
		if (object instanceof InvalidValueException) {
			throw new InvalidValueException(PivotMessages.InvalidSource, "prepend");
		}
		OrderedSet<Object> result = new OrderedSetImpl<Object>();
		result.add(object);
		result.addAll(elements);
		return new SparseOrderedSetValueImpl(getTypeId(), result);
	} */

	@Override
	public @NonNull CollectionValue toSequenceValue() {
		return super.toSequenceValue();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int lengthLimit) {
		s.append(TypeId.ORDERED_SET_NAME);
		super.toString(s, lengthLimit);
	}
}
