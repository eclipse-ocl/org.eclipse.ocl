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
package org.eclipse.ocl.pivot.library.logical;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicReason;
import org.eclipse.ocl.pivot.library.AbstractSimpleUnaryOperation;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * NotOperation realises the not() library operation.
 */
public class BooleanNotOperation extends AbstractSimpleUnaryOperation
{
	public static final @NonNull BooleanNotOperation INSTANCE = new BooleanNotOperation();

	/**
	 * @since 1.16
	 */
	@Override
	protected @Nullable SymbolicValue checkPreconditions(@NonNull SymbolicEvaluationEnvironment evaluationEnvironment, @NonNull OperationCallExp callExp) {
		return checkPreconditions(evaluationEnvironment, callExp, CHECK_NOT_INVALID);
	}

	/**
	 * @since 1.16
	 */
	@Override
	protected @NonNull SymbolicValue createResultValue( @NonNull SymbolicEvaluationEnvironment evaluationEnvironment, @NonNull OperationCallExp callExp,
			@NonNull SymbolicValue sourceSymbolicValue, @NonNull List<@NonNull SymbolicValue> argumentSymbolicValues) {
		Operation referredOperation = PivotUtil.getReferredOperation(callExp);
		assert referredOperation.isIsValidating() : "Spurious createResultValue overload for " + referredOperation.getImplementationClass();
		assert !callExp.isIsSafe() : "Spurious isSafe for " + referredOperation.getImplementationClass();
		OCLExpression ownedSource = PivotUtil.getOwnedSource(callExp);
		SymbolicReason mayBeInvalidReason = evaluationEnvironment.mayBeInvalidReason(ownedSource);
		SymbolicReason mayBeNullReason = evaluationEnvironment.mayBeNullReason(ownedSource);
		return evaluationEnvironment.getUnknownValue(callExp, mayBeNullReason, mayBeInvalidReason);
	}

	@Override
	public @Nullable Boolean evaluate(@Nullable Object argument) {
		if (argument == Boolean.FALSE) {
			return TRUE_VALUE;
		}
		else if (argument == Boolean.TRUE) {
			return FALSE_VALUE;
		}
		if (argument == null) {
			return null;
		}
		else if (argument instanceof InvalidValueException) {
			throw (InvalidValueException)argument;
		}
		else {
			throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.BOOLEAN_NAME, getTypeName(argument));
		}
	}
}
