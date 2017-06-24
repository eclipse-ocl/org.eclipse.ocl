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
import org.eclipse.ocl.pivot.library.AbstractUntypedUnaryOperation;
import org.eclipse.ocl.pivot.oclstdlib.Invalidable;

/**
 * OclTypeAsNonNullOperation realises the OclType::asNonNull() library operation.
 *
 * @since 1.4
 */
public class OclTypeAsNonNullOperation extends AbstractUntypedUnaryOperation
{
	public static final @NonNull OclTypeAsNonNullOperation INSTANCE = new OclTypeAsNonNullOperation();

	@Override
	public @NonNull Type evaluate(@NonNull Executor executor, @Nullable Object sourceVal) {
		Type thisType = asType(sourceVal);
		if (thisType instanceof Invalidable<?>) {
			return (Type) ((org.eclipse.ocl.pivot.oclstdlib.Nullable<?>)((Invalidable<?>)thisType).getNonInvalidType()).getNonNullType();
		}
		else if (thisType instanceof org.eclipse.ocl.pivot.oclstdlib.Nullable<?>) {
			return (Type) ((org.eclipse.ocl.pivot.oclstdlib.Nullable<?>)thisType).getNonNullType();
		}
		else {
			return thisType;
		}
	}
}
