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
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.evaluation.AbstractSymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.values.SymbolicUnknownValueImpl;
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
	 * @since 1.15
	 */
	@Override
	protected @Nullable SymbolicValue checkPreconditions(@NonNull AbstractSymbolicEvaluationEnvironment symbolicEvaluationEnvironment, @NonNull OperationCallExp callExp) {
		TypeId returnTypeId = callExp.getTypeId();
		OCLExpression source = PivotUtil.getOwnedSource(callExp);
		SymbolicValue invalidSourceProblem = symbolicEvaluationEnvironment.checkNotInvalid(source, returnTypeId);
		if (invalidSourceProblem != null) {
			return invalidSourceProblem;
		}
		return null;
	}

	/**
	 * @since 1.15
	 */
	@Override
	protected @NonNull SymbolicValue createChildSymbolicValue(@NonNull AbstractSymbolicEvaluationEnvironment evaluationEnvironment, @NonNull CallExp callExp,
			@NonNull SymbolicValue sourceSymbolicValue, @NonNull List<@NonNull SymbolicValue> argumentSymbolicValues) {
		OCLExpression ownedSource = PivotUtil.getOwnedSource(callExp);
		boolean mayBeInvalid = evaluationEnvironment.mayBeInvalid(ownedSource);
		return new SymbolicUnknownValueImpl(callExp.getTypeId(), false, mayBeInvalid);
	}


	@Override
	public @NonNull Boolean evaluate(@Nullable Object argument) {
		return (argument == null) || (argument instanceof NullValue);	// NB InvalidValue is a NullValue
	}
}
