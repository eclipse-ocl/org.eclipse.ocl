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
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.library.AbstractSimpleUnaryOperation;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * OclAnyToStringOperation realises the OclAny::toString() library operation.
 */
public class OclAnyToStringOperation extends AbstractSimpleUnaryOperation
{
	public static final @NonNull OclAnyToStringOperation INSTANCE = new OclAnyToStringOperation();

	/**
	 * @since 1.15
	 */
	@Override
	protected @Nullable SymbolicValue checkPreconditions(@NonNull SymbolicEvaluationEnvironment symbolicEvaluationEnvironment, @NonNull OperationCallExp callExp) {
		TypeId returnTypeId = callExp.getTypeId();
		OCLExpression source = PivotUtil.getOwnedSource(callExp);
		SymbolicValue sourceProblem = symbolicEvaluationEnvironment.checkNotInvalid(source, returnTypeId);
		if (sourceProblem != null) {
			return sourceProblem;
		}
		return null;
	}

	@Override
	public @NonNull String evaluate(@Nullable Object sourceVal) {
		if (sourceVal instanceof InvalidValueException)	{				// FIXME Remove this once CG has proper invalid analysis
			throw (InvalidValueException)sourceVal;
		}
		return sourceVal != null ? oclToString(sourceVal) : NULL_STRING;
	}
}
