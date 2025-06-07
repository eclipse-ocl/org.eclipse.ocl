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
package org.eclipse.ocl.pivot.library.oclany;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.library.AbstractUntypedBinaryOperation;

/**
 * OclAnyOclIsTypeOfOperation realizes the OclAny::oclIsTypeOf() library operation.
 */
public class OclAnyOclIsTypeOfOperation extends AbstractUntypedBinaryOperation
{
	public static final @NonNull OclAnyOclIsTypeOfOperation INSTANCE = new OclAnyOclIsTypeOfOperation();

	/**
	 * @since 1.1
	 */
	@Override
	public @NonNull Boolean evaluate(@NonNull Executor executor, @Nullable Object sourceVal, @Nullable Object argVal) {
		Type sourceType = executor.getIdResolver().getDynamicClassOf(sourceVal);
		Type argType = asType(argVal);
		boolean result = executor.getStandardLibrary().isEqualTo(sourceType, argType);
		return result;
	}
}
