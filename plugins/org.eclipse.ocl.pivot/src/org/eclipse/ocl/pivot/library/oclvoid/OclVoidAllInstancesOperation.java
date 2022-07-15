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
package org.eclipse.ocl.pivot.library.oclvoid;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.AbstractSimpleUnaryOperation;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.SetValue;

/**
 * OclVoidAllInstancesOperation realises the OclVoid::allInstances() library operation.
 */
public class OclVoidAllInstancesOperation extends AbstractSimpleUnaryOperation
{
	@Deprecated /* @deprecated invoke the polymorphic VoidTypeImpl.allInstances() */
	public static final @NonNull OclVoidAllInstancesOperation INSTANCE = new OclVoidAllInstancesOperation();
	public static final @NonNull CollectionTypeId SET_OCL_VOID = TypeId.SET.getSpecializedCollectionTypeId(TypeId.OCL_VOID);

	/**
	 * @since 1.18
	 */
	public static @NonNull SetValue allInstances() {
		return ValueUtil.createSetOfEach(SET_OCL_VOID, (Object)null);
	}

	/**
	 * @since 1.18
	 */
	@Override
	public @NonNull SetValue evaluate(@Nullable Object sourceVal) {
		return allInstances();
	}
}
