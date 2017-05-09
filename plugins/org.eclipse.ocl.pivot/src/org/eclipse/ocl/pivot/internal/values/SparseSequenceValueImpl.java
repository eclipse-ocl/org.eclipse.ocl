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
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.SequenceValue;

/**
 * @generated NOT
 */
public class SparseSequenceValueImpl extends SequenceValueImpl
{
	public static @NonNull List<@Nullable Object> createSequenceOfEach(@Nullable Object @NonNull [] boxedValues) {
		List<@Nullable Object> result = new ArrayList<>();
		for (Object boxedValue : boxedValues) {
			result.add(boxedValue);
		}
		return result;
	}

	public static @NonNull List<@Nullable Object> createSequenceOfEach(@NonNull Collection<@Nullable Object> elements) {
		List<@Nullable Object> list = elements instanceof List<?> ? (List<@Nullable Object>)elements : new ArrayList<>(elements);
		return list;
	}

	public static class Accumulator extends SparseSequenceValueImpl implements SequenceValue.Accumulator
	{
		public Accumulator(@NonNull CollectionTypeId typeId) {
			super(typeId, new ArrayList<>());
		}

		public Accumulator(@NonNull CollectionTypeId typeId, @NonNull List<@Nullable Object> values) {
			super(typeId, values);
		}

		@Override
		public boolean add(@Nullable Object value) {
			return elements.add(value);
		}

		@Override
		public @NonNull SequenceValue append(@Nullable Object value) {
			assert !(value instanceof InvalidValueException);
			add(value);
			return this;
		}
	}

	public SparseSequenceValueImpl(@NonNull CollectionTypeId typeId, @NonNull List<@Nullable Object> boxedValues) {
		super(typeId, boxedValues);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SparseSequenceValueImpl) {
			return elements.equals(((SparseSequenceValueImpl)obj).elements);
		}
		else {
			return super.equals(obj);
		}
	}
}
