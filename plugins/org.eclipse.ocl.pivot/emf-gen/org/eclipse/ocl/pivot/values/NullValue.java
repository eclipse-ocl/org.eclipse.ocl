/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.values;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.OclVoidTypeId;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Null Value</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.ocl.pivot.values.ValuesPackage#getNullValue()
 * @generated
 */
public interface NullValue extends ObjectValue, IntegerValue, UnlimitedValue, BagValue, MapValue, OrderedSetValue, SequenceValue, SetValue, TupleValue
{
	/**
	 * @generated NOT
	 */
	@Override
	@NonNull NullValue excluding(@Nullable Object value);

	/**
	 * @generated NOT
	 */
	@Override
	@NonNull NullValue excludingAll(@NonNull CollectionValue c);

	/**
	 * @generated NOT
	 */
	@Override
	@NonNull OclVoidTypeId getTypeId();
} // NullValue
