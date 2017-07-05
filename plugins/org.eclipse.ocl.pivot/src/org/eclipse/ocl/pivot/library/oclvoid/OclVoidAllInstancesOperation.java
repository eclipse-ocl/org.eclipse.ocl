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
package org.eclipse.ocl.pivot.library.oclvoid;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.AbstractSimpleUnaryOperation;
import org.eclipse.ocl.pivot.values.CollectionValue;

/**
 * OclVoidAllInstancesOperation realises the OclVoid::allInstances() library operation.
 */
public class OclVoidAllInstancesOperation extends AbstractSimpleUnaryOperation
{
	public static final @NonNull OclVoidAllInstancesOperation INSTANCE = new OclVoidAllInstancesOperation();
	public static final @NonNull CollectionTypeId SET_OCL_VOID = TypeId.SET.getSpecializedId(TypeId.OCL_VOID);

	@Override
	public @NonNull CollectionValue evaluate(@Nullable Object sourceVal) {
		// OclVoid has a single instance: null
		return createSetOfEach(SET_OCL_VOID, (Object)null);
	}
}
