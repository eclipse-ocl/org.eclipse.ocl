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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.OrderedSetValue;
import org.eclipse.ocl.pivot.values.SequenceValue;
import org.eclipse.ocl.pivot.values.SetValue;
import org.eclipse.ocl.pivot.values.UniqueCollectionValue;
import org.eclipse.ocl.pivot.values.ValuesPackage;

//
//	Note that it is not necessary to adjust set uniqueness for OCL value equivalence
//	since Value.equals realises OCL equivalence, and so Collection operations that
//	inherently use Object.equals automatically observe OCL uniqueness.
//
/**
 * @generated NOT
 */
public class SetValueImpl extends CollectionValueImpl implements SetValue
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ValuesPackage.Literals.SET_VALUE;
	}

	public static @NonNull Set<@Nullable Object> createSetOfEach(@Nullable Object @NonNull [] boxedValues) {
		Set<@Nullable Object> result = new HashSet<>();
		for (Object boxedValue : boxedValues) {
			result.add(boxedValue);
		}
		return result;
	}

	public static class Accumulator extends SetValueImpl implements SetValue.Accumulator
	{
		public Accumulator(@NonNull CollectionTypeId typeId) {
			super(typeId, new HashSet<>());
		}

		@Override
		public boolean add(@Nullable Object value) {
			return elements.add(value);
		}
	}

	public SetValueImpl(@NonNull CollectionTypeId typeId, @NonNull Collection<@Nullable Object> boxedValues) {
		super(typeId, boxedValues);
		assert checkElementsAreUnique(elements);
	}

	@Override
	public @NonNull UniqueCollectionValue asUniqueCollectionValue() {
		return this;
	}

	@Override
	public @NonNull SetValue asSetValue() {
		return this;
	}

	@Override
	public @NonNull Set<Object> asUnboxedObject(@NonNull IdResolver idResolver) {
		Set<@Nullable Object> unboxedValues = new HashSet<>();
		for (Object boxedValue : elements) {
			unboxedValues.add(idResolver.unboxedValueOf(boxedValue));
		}
		return unboxedValues;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SetValue)) {
			return false;
		}
		int thisSize = elements.size();
		Collection<? extends Object> thoseElements = ((SetValue)obj).getElements();
		int thatSize = thoseElements.size();
		if (thisSize != thatSize) {
			return false;
		}
		if (thoseElements instanceof Set<?>) {
			return thoseElements.containsAll(elements);
		}
		else {
			return elements.containsAll(thoseElements);
		}
	}

	@Override
	public @NonNull SetValue excluding(@Nullable Object value) {
		Set<@Nullable Object> result = new HashSet<>();
		if (value == null) {
			for (Object element : elements) {
				if (element != null) {
					result.add(element);
				}
			}
		}
		else {
			for (Object element : elements) {
				if (!value.equals(element)) {
					result.add(element);
				}
			}
		}
		if (result.size() < elements.size()) {
			return new SetValueImpl(getTypeId(), result);
		}
		else {
			return this;
		}
	}

	@Override
	public @NonNull SetValue excludingAll(@NonNull CollectionValue values) {
		Set<@Nullable Object> result = new HashSet<>();
		for (Object element : elements) {
			boolean reject = false;
			if (element == null) {
				for (Object value : values) {
					if (value == null) {
						reject = true;
						break;
					}
				}
			}
			else {
				for (Object value : values) {
					if ((value != null) && value.equals(element)) {
						reject = true;
						break;
					}
				}
			}
			if (!reject) {
				result.add(element);
			}
		}
		if (result.size() < elements.size()) {
			return new SetValueImpl(getTypeId(), result);
		}
		else {
			return this;
		}
	}

	@Override
	public @NonNull SetValue flatten() {
		Set<@Nullable Object> flattened = new HashSet<>();
		if (flatten(flattened)) {
			return new SetValueImpl(getTypeId(), flattened);
		}
		else {
			return this;
		}
	}

	//    @Override
	//	public @NonNull CollectionTypeId getCollectionTypeId() {
	//		return TypeId.SET;
	//	}

	//	@Override
	//	protected @NonNull Set<? extends Object> getElements() {
	//		return (Set<? extends Object>) elements;
	//	}

	@Override
	public @NonNull String getKind() {
		return TypeId.SET_NAME;
	}

	@Override
	public @NonNull SetValue including(@Nullable Object value) {
		assert !(value instanceof InvalidValueException);
		Set<@Nullable Object> result = new HashSet<>(elements);
		result.add(value);
		return new SetValueImpl(getTypeId(), result);
	}

	@Override
	public @NonNull SetValue includingAll(@NonNull CollectionValue values) {
		Set<@Nullable Object> result = new HashSet<>(elements);
		for (Object value : values) {
			result.add(value);
		}
		return new SetValueImpl(getTypeId(), result);
	}

	@Override
	public boolean isOrdered() {
		return false;
	}

	@Override
	public boolean isUnique() {
		return true;
	}

	@Override
	public @NonNull SetValue minus(@NonNull UniqueCollectionValue set) {
		Set<@Nullable Object> result = new HashSet<>(elements);
		result.removeAll(set.asCollection());
		return new SetValueImpl(getTypeId(), result);
	}

	@Override
	public @NonNull OrderedSetValue sort(@NonNull Comparator<@Nullable Object> comparator) {
		List<@Nullable Object> values = new ArrayList<>(elements);
		Collections.sort(values, comparator);
		return new SparseOrderedSetValueImpl(getOrderedSetTypeId(), values);
	}

	@Override
	public @NonNull SetValue symmetricDifference(@NonNull UniqueCollectionValue set) {
		Set<@Nullable Object> result = new HashSet<>(elements);
		for (Object e : set.iterable()) {
			if (result.contains(e)) {
				result.remove(e);
			} else {
				result.add(e);
			}
		}
		return new SetValueImpl(getTypeId(), result);
	}

	@Override
	public @NonNull SequenceValue toSequenceValue() {
		return new SparseSequenceValueImpl(getSequenceTypeId(), SparseSequenceValueImpl.createSequenceOfEach(elements));
	}

	@Override
	public void toString(@NonNull StringBuilder s, int lengthLimit) {
		s.append(TypeId.SET_NAME);
		super.toString(s, lengthLimit);
	}
}
