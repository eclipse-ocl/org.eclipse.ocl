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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.library.AbstractSimpleUnaryOperation;
import org.eclipse.ocl.pivot.messages.PivotMessages;
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

	/**
	 * @since 1.15
	 *
	@Override
	public @Nullable Object symbolicDispatch(@NonNull EvaluationVisitor evaluationVisitor, @NonNull OperationCallExp callExp, @Nullable Object sourceValue) {
		if (sourceValue instanceof SymbolicValue) {
			boolean mayBeInvalid = ValueUtil.mayBeInvalid(sourceValue);
			boolean mayBeNull = ValueUtil.mayBeNull(sourceValue);
			return new SymbolicOperationCallValueImpl(callExp, mayBeNull, mayBeInvalid, this, Lists.newArrayList(sourceValue));
		}
		return dispatch(evaluationVisitor.getExecutor(), callExp, sourceValue);
	} */
}
