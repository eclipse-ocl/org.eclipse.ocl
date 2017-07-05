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
package org.eclipse.ocl.pivot.values;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.ids.OclVoidTypeId;

public interface NullValue
extends ObjectValue, IntegerValue, UnlimitedValue,
BagValue, OrderedSetValue, TupleValue
{
	@Override
	@NonNull OclVoidTypeId getTypeId();
	/**
	 * @generated NOT
	 */
	@Override
	@NonNull NullValue negate();
}
