/*******************************************************************************
 * Copyright (c) 2014, 2020 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.values;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.CoCollectionValue;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.IntegerValue;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CoCollectionValue</b></em>'.
 * <!-- end-user-doc -->
 *
 * @since 1.23
 */
public class CoCollectionValueImpl extends ValueImpl implements CoCollectionValue
{
	protected final @NonNull CollectionValue collectionValue;
	private @Nullable Map<@Nullable Object, @NonNull IntegerValue> value2count = null;		// XXX indexed optimization

	public CoCollectionValueImpl(@NonNull CollectionValue collectionValue) {
		this.collectionValue = collectionValue;
	}

	@Override
	public @NonNull Object asObject() {
		return this;
	}

	@Override
	public @NonNull IntegerValue count(@Nullable Object value) {							// XXX eager/lazy optimization
		Map<@Nullable Object, @NonNull IntegerValue> value2count2 = value2count;
		IntegerValue ONE = ValueUtil.ONE_VALUE;
		if (value2count2 == null) {
			value2count = value2count2 = new HashMap<>();
			for (Object element : collectionValue.getElements()) {
				IntegerValue count = value2count2.get(element);
				value2count2.put(element, count != null ? count.addInteger(ONE) : ONE);
			}
		}
		IntegerValue count = value2count2.get(value);
		return count != null ? ValueUtil.integerValueOf(count) : ValueUtil.ZERO_VALUE;
	}

	@Override
	public @NonNull TypeId getTypeId() {
		return TypeId.CO_COLLECTION;
	}
} // NumberValue
