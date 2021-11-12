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
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicReason;
import org.eclipse.ocl.pivot.library.AbstractSimpleUnaryOperation;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.NullValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * OclAnyOclIsUndefinedOperation realises the OclAny::oclIsUndefined() library operation.
 */
public class OclAnyOclIsUndefinedOperation extends AbstractSimpleUnaryOperation
{
	public static final @NonNull OclAnyOclIsUndefinedOperation INSTANCE = new OclAnyOclIsUndefinedOperation();

	/**
	 * @since 1.16
	 */
	@Override
	protected @NonNull SymbolicValue createResultValue(@NonNull SymbolicEvaluationEnvironment evaluationEnvironment, @NonNull OperationCallExp callExp,
			@NonNull SymbolicValue sourceSymbolicValue, @NonNull List<@NonNull SymbolicValue> argumentSymbolicValues) {
		OCLExpression ownedSource = PivotUtil.getOwnedSource(callExp);
		SymbolicReason mayBeInvalidReason = evaluationEnvironment.mayBeInvalidReason(ownedSource);
		return evaluationEnvironment.getUnknownValue(callExp, null, mayBeInvalidReason);
	}


	@Override
	public @NonNull Boolean evaluate(@Nullable Object argument) {
		return (argument == null) || (argument instanceof NullValue);	// NB InvalidValue is a NullValue
	}

	@Override
	public boolean sourceMayBeInvalid() {
		return true;
	}

	@Override
	public boolean sourceMayBeNull() {
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
		OCLExpression source = PivotUtil.getOwnedSource(callExp);
		SymbolicValue sourceValue = evaluationEnvironment.symbolicEvaluate(source);
		if (sourceValue.isInvalid() || sourceValue.isNull()) {
			return evaluationEnvironment.getKnownValue(Boolean.TRUE);
		}
		else if ((sourceValue.mayBeInvalidReason() != null) || (sourceValue.mayBeNullReason() != null)) {
			return evaluationEnvironment.getUnknownValue(callExp, null, null);
		}
		else {
			return evaluationEnvironment.getKnownValue(Boolean.FALSE);
		}
	}
}
