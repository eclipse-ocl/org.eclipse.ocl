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
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.iterators.ExcludingAllIterator;
import org.eclipse.ocl.pivot.internal.iterators.ExcludingIterator;
import org.eclipse.ocl.pivot.internal.iterators.FlattenIterator;
import org.eclipse.ocl.pivot.internal.iterators.IncludingAllIterator;
import org.eclipse.ocl.pivot.internal.iterators.IncludingIterator;
import org.eclipse.ocl.pivot.values.Bag;
import org.eclipse.ocl.pivot.values.BagValue;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.OrderedCollectionValue;
import org.eclipse.ocl.pivot.values.SequenceValue;
import org.eclipse.ocl.pivot.values.UniqueCollectionValue;
import org.eclipse.ocl.pivot.values.ValuesPackage;

/**
 * @generated NOT
 */
public class BagValueImpl extends CollectionValueImpl implements BagValue.Internal
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

	public static @NonNull Bag<Object> createBagOfEach(@Nullable Object @NonNull [] boxedValues) {
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
	public @NonNull OrderedCollectionValue asOrderedCollectionValue() {
		return isUnique() ? asOrderedSetValue() : asSequenceValue();
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
	public @NonNull UniqueCollectionValue asUniqueCollectionValue() {
		return isOrdered() ? asOrderedSetValue() : asSetValue();
	}

	@Override
	public @NonNull BagValue excluding(@Nullable Object value) {
		return ExcludingIterator.excluding(this, value).asBagValue();
	}

	@Override
	public @NonNull BagValue excludingAll(@NonNull CollectionValue values) {
		return ExcludingAllIterator.excludingAll(this, values).asBagValue();
	}

	@Override
	public @NonNull BagValue flatten() {
		return FlattenIterator.flatten(this).asBagValue();
	}

	@Override
	public @NonNull Bag<@Nullable Object> getElements() {
		return (Bag<@Nullable Object>) elements;
	}

	@Override
	public @NonNull String getKind() {
		return TypeId.BAG_NAME;
	}

	/**
	 * @since 1.3
	 */
	@Override
	public @NonNull Map<@Nullable Object, @NonNull ? extends Number> getMapOfElement2elementCount() {
		return ((BagImpl<@Nullable Object>) elements).getMap();
	}

	@Override
	public @NonNull BagValue including(@Nullable Object value) {
		return IncludingIterator.including(getTypeId(), this, value).asBagValue();
	}

	@Override
	public @NonNull BagValue includingAll(@NonNull CollectionValue values) {
		return IncludingAllIterator.includingAll(getTypeId(), this, values).asBagValue();
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
		return super.toSequenceValue();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int lengthLimit) {
		s.append(TypeId.BAG_NAME);
		super.toString(s, lengthLimit);
	}
}
