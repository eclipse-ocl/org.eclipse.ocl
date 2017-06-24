/*******************************************************************************
 * Copyright (c) 2017 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library.classifier;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.AbstractProperty;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * NullableNonNullTypeProperty realizes the Nullable::nonNullType library property.
 *
 * @since 1.4
 */
public class NullableNonNullTypeProperty extends AbstractProperty
{
	public static final @NonNull NullableNonNullTypeProperty INSTANCE = new NullableNonNullTypeProperty();

	@Override
	public @NonNull Type evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object sourceValue) {
		org.eclipse.ocl.pivot.oclstdlib.Nullable<?> sourceType = asNullable(sourceValue);
		return (Type) ClassUtil.nonNullModel(sourceType.getNonNullType());
	}
}
