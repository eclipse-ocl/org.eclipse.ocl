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
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.manager.SymbolicExecutor;
import org.eclipse.ocl.pivot.internal.values.SymbolicOperationCallValueImpl;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.SimpleSymbolicConstraint;
import org.eclipse.ocl.pivot.values.SymbolicOperationCallValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;

import com.google.common.collect.Lists;


/**
 * OclAnyNotEqualOperation realises the OCLAny::<>() library operation and
 * regular derived implementations since the Value classes exhibit
 * OCL value semantics.
 */
public class OclAnyNotEqualOperation extends OclAnyEqualOperation
{
	public static final @NonNull OclAnyNotEqualOperation INSTANCE = new OclAnyNotEqualOperation();

	@Override
	public void deduceFrom(@NonNull SymbolicExecutor symbolicExecutor, @NonNull SymbolicOperationCallValue operationCallValue, @NonNull SimpleSymbolicConstraint resultConstraint) {
		deduceFrom(symbolicExecutor, operationCallValue, resultConstraint, true);
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

	@Override
	public @Nullable Object symbolicEvaluate(@NonNull Executor executor, @NonNull OperationCallExp operationCallExp, @Nullable Object sourceValue, @Nullable Object argumentValue) {
		if ((sourceValue instanceof SymbolicValue) || (argumentValue instanceof SymbolicValue)) {
			if ((sourceValue == null) && !ValueUtil.mayBeNull(argumentValue)) {
				return ValueUtil.TRUE_VALUE;
			}
			if ((argumentValue == null) && !ValueUtil.mayBeNull(sourceValue)) {
				return ValueUtil.TRUE_VALUE;
			}
			boolean mayBeInvalid = ValueUtil.mayBeInvalid(sourceValue) || ValueUtil.mayBeInvalid(argumentValue);
			return new SymbolicOperationCallValueImpl(operationCallExp, false, /*mayBeNull ||*/ mayBeInvalid, this, Lists.newArrayList(sourceValue, argumentValue));
		}
		return evaluate(sourceValue, argumentValue);
	}
}
