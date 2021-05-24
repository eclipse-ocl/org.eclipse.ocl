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
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.evaluation.AbstractSymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.values.SymbolicUnknownValueImpl;
import org.eclipse.ocl.pivot.library.AbstractSimpleUnaryOperation;
import org.eclipse.ocl.pivot.values.InvalidValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * OclAnyOclIsInvalidOperation realises the OclAny::oclIsInvalid() library operation.
 */
public class OclAnyOclIsInvalidOperation extends AbstractSimpleUnaryOperation
{
	public static final @NonNull OclAnyOclIsInvalidOperation INSTANCE = new OclAnyOclIsInvalidOperation();

	/**
	 * @since 1.15
	 */
	@Override
	protected @Nullable SymbolicValue checkPreconditions(@NonNull AbstractSymbolicEvaluationEnvironment symbolicEvaluationEnvironment, @NonNull OperationCallExp callExp) {
		return null;
	}

	@Override
	public @NonNull Object evaluate(@Nullable Object argument) {
		if (argument instanceof InvalidValue) {
			return Boolean.TRUE;
		}
		if (argument instanceof SymbolicValue) {
			SymbolicValue symbolicValue = (SymbolicValue)argument;
			if (symbolicValue.mayBeInvalid()) {
				return new SymbolicUnknownValueImpl(TypeId.BOOLEAN, false, false);
			}
		}
		return Boolean.FALSE;
	}
}
