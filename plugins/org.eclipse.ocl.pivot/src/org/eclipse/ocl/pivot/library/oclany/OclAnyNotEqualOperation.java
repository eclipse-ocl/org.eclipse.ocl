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
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.manager.SymbolicExecutor;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.SymbolicConstraint;
import org.eclipse.ocl.pivot.values.SymbolicValue;


/**
 * OclAnyNotEqualOperation realises the OCLAny::<>() library operation and
 * regular derived implementations since the Value classes exhibit
 * OCL value semantics.
 */
public class OclAnyNotEqualOperation extends OclAnyEqualOperation
{
	public static final @NonNull OclAnyNotEqualOperation INSTANCE = new OclAnyNotEqualOperation();

	@Override
	public void deduceFrom(@NonNull SymbolicExecutor symbolicExecutor, @NonNull SymbolicValue resultValue, @NonNull SymbolicConstraint resultConstraint) {
		deduceFrom(symbolicExecutor, resultValue, resultConstraint, true);
	}

	@Override
	public @NonNull Boolean evaluate(@Nullable Object left, @Nullable Object right) {
		Object equals = super.evaluate(left, right);
		if (equals == Boolean.FALSE) {
			return TRUE_VALUE;
		}
		else if (equals == Boolean.TRUE) {
			return FALSE_VALUE;
		}
		else if (equals instanceof InvalidValueException) {
			throw (InvalidValueException)equals;
		}
		else {
			throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.BOOLEAN_NAME, getTypeName(right));
		}
	}
}
