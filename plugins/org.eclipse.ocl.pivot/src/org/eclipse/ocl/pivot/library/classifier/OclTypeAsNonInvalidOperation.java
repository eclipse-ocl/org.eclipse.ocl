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
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.oclstdlib.Invalidable;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * OclTypeAsNonInvalidOperation realises the OclType::asNonInvalid() library operation.
 *
 * @since 1.4
 */
public class OclTypeAsNonInvalidOperation extends AbstractUntypedUnaryOperation
{
	public static final @NonNull OclTypeAsNonInvalidOperation INSTANCE = new OclTypeAsNonInvalidOperation();

	@Override
	public @NonNull Type evaluate(@NonNull Executor executor, @Nullable Object sourceVal) {
		Type thisType = asType(sourceVal);
		if (thisType instanceof Invalidable<?>) {
			return (Type) ((Invalidable<?>)thisType).getNonInvalidType();
		}
		else if (thisType instanceof org.eclipse.ocl.pivot.oclstdlib.Nullable<?>) {
			throw new InvalidValueException(PivotMessages.ConvertibleValueRequired, "asNonNull");	// FIXME better message
		}
		else {
			return thisType;
		}
	}
}
