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
import org.eclipse.ocl.pivot.internal.iterators.ExcludingAllIterator;
import org.eclipse.ocl.pivot.internal.iterators.ExcludingIterator;
import org.eclipse.ocl.pivot.internal.iterators.FlattenIterator;
import org.eclipse.ocl.pivot.internal.iterators.IncludingAllIterator;
import org.eclipse.ocl.pivot.internal.iterators.IncludingIterator;
import org.eclipse.ocl.pivot.internal.iterators.SymmetricDifferenceIterator;
import org.eclipse.ocl.pivot.values.CollectionValue;
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

	public static @NonNull Set<Object> createSetOfEach(@Nullable Object @NonNull [] boxedValues) {
		Set<Object> result = new HashSet<Object>();
		for (Object boxedValue : boxedValues) {
			result.add(boxedValue);
		}
		return result;
	}

	public static class Accumulator extends SetValueImpl implements SetValue.Accumulator
	{
		public Accumulator(@NonNull CollectionTypeId typeId) {
			super(typeId, new HashSet<Object>());
		}

		@Override
		@SuppressWarnings("unchecked")
		public boolean add(@Nullable Object value) {
			return ((Collection<Object>)elements).add(value);
		}
	}

	public SetValueImpl(@NonNull CollectionTypeId typeId, @NonNull Collection<? extends Object> boxedValues) {
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
		Set<Object> unboxedValues = new HashSet<Object>();
		for (Object boxedValue : elements) {
			unboxedValues.add(idResolver.unboxedValueOf(boxedValue));
		}
		return unboxedValues;
	}

	@Override
	public @NonNull SetValue excluding(@Nullable Object value) {
		return ExcludingIterator.excluding(this, value).asSetValue();
	}

	@Override
	public @NonNull SetValue excludingAll(@NonNull CollectionValue values) {
		return ExcludingAllIterator.excludingAll(this, values).asSetValue();
	}

	@Override
	public @NonNull SetValue flatten() {
		return FlattenIterator.flatten(this).asSetValue();
	}

	@Override
	public @NonNull String getKind() {
		return TypeId.SET_NAME;
	}

	@Override
	public @NonNull SetValue including(@Nullable Object value) {
		return IncludingIterator.including(getTypeId(), this, value).asSetValue();
	}

	@Override
	public @NonNull SetValue includingAll(@NonNull CollectionValue values) {
		return IncludingAllIterator.includingAll(getTypeId(), this, values).asSetValue();
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
	public @NonNull SetValue minus(@NonNull UniqueCollectionValue that) {
		return ExcludingAllIterator.excludingAll(this, that).asSetValue();
	}

	/*	@Override
	public @NonNull SetValue minus(@NonNull UniqueCollectionValue set) {
		Set<Object> result = new HashSet<Object>(elements);
		result.removeAll(set.asCollection());
		return new SetValueImpl(getTypeId(), result);
	} */

	@Override
	public @NonNull OrderedSetValue sort(@NonNull Comparator<Object> comparator) {
		List<Object> values = new ArrayList<Object>(elements);
		Collections.sort(values, comparator);
		return new SparseOrderedSetValueImpl(getOrderedSetTypeId(), values);
	}

	@Override
	public @NonNull SetValue symmetricDifference(@NonNull UniqueCollectionValue that) {
		return SymmetricDifferenceIterator.symmetricDifference(this, that).asSetValue();
	}

	/*	@Override
	public @NonNull SetValue symmetricDifference(@NonNull UniqueCollectionValue set) {
		Set<Object> result = new HashSet<Object>(elements);
		for (Object e : set.iterable()) {
			if (result.contains(e)) {
				result.remove(e);
			} else {
				result.add(e);
			}
		}
		return new SetValueImpl(getTypeId(), result);
	} */

	@Override
	public @NonNull SequenceValue toSequenceValue() {
		return super.toSequenceValue();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int lengthLimit) {
		s.append(TypeId.SET_NAME);
		super.toString(s, lengthLimit);
	}
}
