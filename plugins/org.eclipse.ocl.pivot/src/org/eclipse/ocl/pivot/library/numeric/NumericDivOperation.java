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
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.library.AbstractSimpleBinaryOperation;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * NumericDivOperation realises the div() library operation.
 */
public class NumericDivOperation extends AbstractSimpleBinaryOperation
{
	public static final @NonNull NumericDivOperation INSTANCE = new NumericDivOperation();

	@Override
	public @NonNull IntegerValue evaluate(@Nullable Object left, @Nullable Object right) {
		IntegerValue leftInteger = asIntegerValue(left);
		IntegerValue rightInteger = asIntegerValue(right);
		return rightInteger.commutatedDiv(leftInteger);
	}

	/**
	 * @since 1.16
	 */
	@Override
	protected @Nullable SymbolicValue checkPreconditions(@NonNull SymbolicEvaluationEnvironment evaluationEnvironment, @NonNull OperationCallExp callExp) {
		SymbolicValue superProblem = super.checkPreconditions(evaluationEnvironment, callExp);
		if (superProblem != null) {
			return superProblem;
		}
		OCLExpression argument = PivotUtil.getOwnedArgument(callExp, 0);
		SymbolicValue argumentProblem = evaluationEnvironment.checkNotZero(argument, PivotUtil.getType(callExp), false);
		if (argumentProblem != null) {
			return argumentProblem;
		}
		return null;
	}
}
