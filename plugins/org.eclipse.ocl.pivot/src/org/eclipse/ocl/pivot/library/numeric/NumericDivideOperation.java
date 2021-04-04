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
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.internal.values.SymbolicOperationCallValueImpl;
import org.eclipse.ocl.pivot.library.AbstractSimpleBinaryOperation;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.RealValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;

import com.google.common.collect.Lists;

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
	public @Nullable Object symbolicEvaluate(@NonNull Executor executor, @NonNull OperationCallExp operationCallExp, @Nullable Object sourceValue, @Nullable Object argumentValue) {
		assert (sourceValue instanceof SymbolicValue) || (argumentValue instanceof SymbolicValue);
		if (!(argumentValue instanceof SymbolicValue)) {
			RealValue right = asRealValue(argumentValue);
			if (right.signum() == 0) {
				throw new InvalidValueException("divide by zero");
			}
		}
		else if (((SymbolicValue)argumentValue).mayBeZero()) {
			return new SymbolicOperationCallValueImpl(operationCallExp, false, true, this, Lists.newArrayList(sourceValue, argumentValue));
		}
		boolean mayBeInvalid = ValueUtil.mayBeInvalid(sourceValue) || ValueUtil.mayBeInvalid(argumentValue);
		boolean mayBeNull = ValueUtil.mayBeNull(sourceValue) || ValueUtil.mayBeNull(argumentValue);
		return new SymbolicOperationCallValueImpl(operationCallExp, false, mayBeInvalid || mayBeNull, this, Lists.newArrayList(sourceValue, argumentValue));
	}
}
