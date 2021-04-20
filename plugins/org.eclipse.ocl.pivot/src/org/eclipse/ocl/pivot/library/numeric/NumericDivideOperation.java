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
package org.eclipse.ocl.pivot.library.numeric;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.evaluation.AbstractSymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.values.SymbolicKnownValueImpl;
import org.eclipse.ocl.pivot.internal.values.SymbolicUnknownValueImpl;
import org.eclipse.ocl.pivot.library.AbstractSimpleBinaryOperation;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.RealValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * NumericDivideOperation realises the /() library operation.
 */
public class NumericDivideOperation extends AbstractSimpleBinaryOperation
{
	public static final @NonNull NumericDivideOperation INSTANCE = new NumericDivideOperation();

	@Override
	public @NonNull RealValue evaluate(@Nullable Object left, @Nullable Object right) {
		RealValue leftNumeric = asRealValue(left);
		RealValue rightNumeric = asRealValue(right);
		return rightNumeric.commutatedDivide(leftNumeric);
	}

	/**
	 * @since 1.15
	 */
	@Override
	protected @Nullable SymbolicValue checkPreconditions(@NonNull AbstractSymbolicEvaluationEnvironment symbolicEvaluationEnvironment, @NonNull OperationCallExp callExp) {
		Iterable<@NonNull OCLExpression> ownedArguments = PivotUtil.getOwnedArguments(callExp);
		OCLExpression argument = ownedArguments.iterator().next();
		if (symbolicEvaluationEnvironment.isZero(argument)) {
			return new SymbolicKnownValueImpl(TypeId.OCL_INVALID, ValueUtil.INVALID_VALUE);
		}
		else if (symbolicEvaluationEnvironment.mayBeZero(argument)) {
			return new SymbolicUnknownValueImpl(callExp.getTypeId(), false, true);
		}
		return null;
	}
}
