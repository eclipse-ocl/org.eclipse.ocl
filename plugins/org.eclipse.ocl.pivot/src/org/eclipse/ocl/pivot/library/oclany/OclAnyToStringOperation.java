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
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicReason;
import org.eclipse.ocl.pivot.library.AbstractSimpleUnaryOperation;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;
import org.eclipse.ocl.pivot.values.Value;

/**
 * OclAnyToStringOperation realises the OclAny::toString() library operation.
 */
public class OclAnyToStringOperation extends AbstractSimpleUnaryOperation
{
	public static final @NonNull OclAnyToStringOperation INSTANCE = new OclAnyToStringOperation();

	@Override
	public @NonNull String evaluate(@Nullable Object sourceVal) {
//		if (sourceVal instanceof InvalidValueException)	{				// FIXME Remove this once CG has proper invalid analysis
//			throw (InvalidValueException)sourceVal;
//		}
		if (sourceVal == null)	{
			return ValueUtil.NULL_STRING;
		}
		else if (sourceVal instanceof InvalidValue)	{
			return Value.INVALID_NAME;
		}
		else {
			return oclToString(sourceVal);
		}
	}

	/**
	 * @since 1.17
	 */
	@Override
	protected boolean sourceMayBeInvalid() {
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
	 * @since 1.16
	 */
	@Override
	public @NonNull SymbolicValue symbolicEvaluate(@NonNull SymbolicEvaluationEnvironment evaluationEnvironment, @NonNull OperationCallExp callExp) {
		SymbolicValue symbolicPreconditionValue = checkPreconditions(evaluationEnvironment, callExp);
		if (symbolicPreconditionValue != null) {
			return symbolicPreconditionValue;
		}
		boolean isSafe = callExp.isIsSafe();
		OCLExpression source = PivotUtil.getOwnedSource(callExp);
		SymbolicValue sourceValue = evaluationEnvironment.symbolicEvaluate(source);
		if (sourceValue.isNull()) {
			return evaluationEnvironment.getKnownValue(isSafe ? null : ValueUtil.NULL_STRING);
		}
		else if (sourceValue.isInvalid()) {
			return evaluationEnvironment.getKnownValue(Value.INVALID_NAME);
		} else {
			SymbolicReason mayBeNullReason = isSafe ? sourceValue.mayBeNullReason() : null;
			return evaluationEnvironment.getUnknownValue(callExp, mayBeNullReason, null);
		}
	}
}
