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

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicReason;
import org.eclipse.ocl.pivot.library.AbstractSimpleBinaryOperation;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * OclAnyEqualOperation realises the OCLAny::=() library operation and
 * regular derived implementations since the Value classes exhibit
 * OCL value semantics.
 */
public class OclAnyEqualOperation extends AbstractSimpleBinaryOperation
{
	public static final @NonNull OclAnyEqualOperation INSTANCE = new OclAnyEqualOperation();

	/**
	 * Overridden to be invalid-out for any invalid in, never null, false for mismatching nullity.
	 *
	 * @since 1.16
	 */
	@Override
	protected @NonNull SymbolicValue createResultValue(@NonNull SymbolicEvaluationEnvironment evaluationEnvironment, @NonNull OperationCallExp callExp,
			@NonNull SymbolicValue sourceSymbolicValue, @NonNull List<@NonNull SymbolicValue> argumentSymbolicValues) {
		SymbolicReason mayBeInvalidReason = sourceSymbolicValue.mayBeInvalidReason();
		SymbolicReason mayBeNullReason = null;
		int isNullCount = 0;
		if (sourceSymbolicValue.isNull()) {
			isNullCount++;
		}
		else {
			mayBeNullReason = sourceSymbolicValue.mayBeNullReason();
		}
		for (@NonNull SymbolicValue argumentSymbolicValue : argumentSymbolicValues) {
			if (mayBeInvalidReason == null) {
				mayBeInvalidReason = argumentSymbolicValue.mayBeInvalidReason();
			}
			if (argumentSymbolicValue.isNull()) {
				isNullCount++;
			}
			else if (mayBeNullReason == null) {
				mayBeNullReason = argumentSymbolicValue.mayBeNullReason();
			}
		}
		if ((mayBeInvalidReason == null) && (mayBeNullReason == null) && ((isNullCount & 1) != 0)) {
			return evaluationEnvironment.getKnownValue(Boolean.FALSE);
		}
		else {
			return evaluationEnvironment.getUnknownValue(callExp, null, mayBeInvalidReason);
		}
	}

	@Override
	public @NonNull Boolean evaluate(@Nullable Object left, @Nullable Object right) {
		//
		//	A.2.2 is clear. 11.3.1 is vague.
		//
		if (left instanceof InvalidValueException) {
			throw (InvalidValueException)left;
		}
		if (right instanceof InvalidValueException) {
			throw (InvalidValueException)right;
		}
		if (left == null) {
			return right == null;
		}
		else if ((left instanceof Type) && (right instanceof Type)) {
			boolean result = ((Type) left).getTypeId().equals(((Type) right).getTypeId());		// FIXME is this a sound/efficient tradeoff for not boxing?
			return result;
		}
		else {
			boolean result = left.equals(right);
			return result;
		}
	}

	/**
	 * @since 1.17
	 */
	@Override
	protected boolean hasRedundantOverloadForInvalid() {
		return true;
	}

	/**
	 * @since 1.17
	 */
	@Override
	protected boolean sourceMayBeNull() {
		return true;
	}

	/**
	 * @since 1.15
	 *
	@Override
	public @Nullable Object symbolicEvaluate(@NonNull Executor executor, @NonNull OperationCallExp operationCallExp, @Nullable Object sourceValue, @Nullable Object argumentValue) {
		if ((sourceValue instanceof SymbolicValue) || (argumentValue instanceof SymbolicValue)) {
			if ((sourceValue == null) && !ValueUtil.mayBeNull(argumentValue)) {
				return ValueUtil.FALSE_VALUE;
			}
			if ((argumentValue == null) && !ValueUtil.mayBeNull(sourceValue)) {
				return ValueUtil.FALSE_VALUE;
			}
			boolean mayBeInvalid = ValueUtil.mayBeInvalid(sourceValue) || ValueUtil.mayBeInvalid(argumentValue);
			return new SymbolicOperationCallValueImpl(operationCallExp, false, / *mayBeNull ||* / mayBeInvalid, this, Lists.newArrayList(sourceValue, argumentValue));
		}
		return evaluate(sourceValue, argumentValue);
	} */
}
