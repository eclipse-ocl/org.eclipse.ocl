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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.values.Bag;
import org.eclipse.ocl.pivot.values.BagValue;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.SequenceValue;
import org.eclipse.ocl.pivot.values.ValuesPackage;

/**
 * @generated NOT
 */
public class BagValueImpl extends CollectionValueImpl implements BagValue
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ValuesPackage.Literals.BAG_VALUE;
	}

	public static @NonNull Bag<@Nullable Object> createBagOfEach(@Nullable Object @NonNull [] boxedValues) {
		Bag<@Nullable Object> result = new BagImpl<>();
		for (Object boxedValue : boxedValues) {
			result.add(boxedValue);
		}
		return result;
	}

	public static class Accumulator extends BagValueImpl implements BagValue.Accumulator
	{
		public Accumulator(@NonNull CollectionTypeId typeId) {
			super(typeId, new BagImpl<>());
		}

		@Override
		public boolean add(@Nullable Object value) {
			return elements.add(value);
		}
	}

	public BagValueImpl(@NonNull CollectionTypeId typeId, @NonNull Bag<@Nullable Object> boxedValues) {
		super(typeId, boxedValues);
	}

	@Override
	public @NonNull BagValue asBagValue() {
		return this;
	}

	@Override
	public @NonNull Bag<Object> asUnboxedObject(@NonNull IdResolver idResolver) {
		Bag<@Nullable Object> unboxedValues = new BagImpl<>();
		for (Object boxedValue : elements) {
			unboxedValues.add(idResolver.unboxedValueOf(boxedValue));
		}
		return unboxedValues;
	}

	@Override
	public boolean equals(Object thatObject) {
		if (this == thatObject) {
			return true;
		}
		if (!(thatObject instanceof BagValue)) {
			return false;
		}
		/*		@Override
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
		} */
		return elements.equals(((BagValue)thatObject).getElements());
	}

	@Override
	public @NonNull BagValue excluding(@Nullable Object value) {
		Bag<@Nullable Object> result = new BagImpl<>();
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
			return new BagValueImpl(getTypeId(), result);
		}
		else {
			return this;
		}
	}

	@Override
	public @NonNull BagValue excludingAll(@NonNull CollectionValue values) {
		Bag<@Nullable Object> result = new BagImpl<>();
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
			return new BagValueImpl(getTypeId(), result);
		}
		else {
			return this;
		}
	}

	@Override
	public @NonNull BagValue flatten() {
		Bag<@Nullable Object> flattened = new BagImpl<>();
		if (flatten(flattened)) {
			return new BagValueImpl(getTypeId(), flattened);
		}
		else {
			return this;
		}
	}

	//    @Override
	//	public @NonNull CollectionTypeId getCollectionTypeId() {
	//		return TypeId.BAG;
	//	}

	@Override
	public @NonNull Bag<@Nullable Object> getElements() {
		return (Bag<@Nullable Object>) elements;
	}

	@Override
	public @NonNull String getKind() {
		return TypeId.BAG_NAME;
	}

	@Override
	public @NonNull BagValue including(@Nullable Object value) {
		assert !(value instanceof InvalidValueException);
		Iterable<@Nullable Object> iterables = elements;
		Bag<@Nullable Object> result = new BagImpl<>(iterables);
		result.add(value);
		return new BagValueImpl(getTypeId(), result);
	}

	@Override
	public @NonNull BagValue includingAll(@NonNull CollectionValue values) {
		Iterable<@Nullable Object> iterables = elements;
		Bag<@Nullable Object> result = new BagImpl<>(iterables);
		for (Object value : values) {
			result.add(value);
		}
		return new BagValueImpl(getTypeId(), result);
	}

	@Override
	public boolean isOrdered() {
		return false;
	}

	@Override
	public boolean isUnique() {
		return false;
	}

	@Override
	public @NonNull SequenceValue sort(@NonNull Comparator<@Nullable Object> comparator) {
		List<@Nullable Object> values = new ArrayList<>(elements);
		Collections.sort(values, comparator);
		return new SparseSequenceValueImpl(getSequenceTypeId(), values);
	}

	@Override
	public @NonNull SequenceValue toSequenceValue() {
		return new SparseSequenceValueImpl(getSequenceTypeId(), new ArrayList<>(elements));
	}

	@Override
	public void toString(@NonNull StringBuilder s, int lengthLimit) {
		s.append(TypeId.BAG_NAME);
		super.toString(s, lengthLimit);
	}
}
