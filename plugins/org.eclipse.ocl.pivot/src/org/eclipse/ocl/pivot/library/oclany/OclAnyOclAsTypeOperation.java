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
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.library.AbstractUntypedBinaryOperation;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * OclAnyOclAsTypeOperation realises the OclAny::oclAsType() library operation.
 */
public class OclAnyOclAsTypeOperation extends AbstractUntypedBinaryOperation
{
	public static final @NonNull OclAnyOclAsTypeOperation INSTANCE = new OclAnyOclAsTypeOperation();

	/**
	 * @since 1.16
	 */
	@Override
	protected @Nullable SymbolicValue checkPreconditions(@NonNull SymbolicEvaluationEnvironment evaluationEnvironment, @NonNull OperationCallExp callExp) {
		return checkPreconditions(evaluationEnvironment, callExp, CHECK_NOT_INVALID);
	}

	/**
	 * @since 1.1
	 */
	@Override
	public @Nullable Object evaluate(@NonNull Executor executor, @Nullable Object sourceVal, @Nullable Object argVal) {
		if (sourceVal instanceof InvalidValueException) {
			throw (InvalidValueException)sourceVal;
		}
		Type argType = asType(argVal);
		Type sourceType = executor.getIdResolver().getDynamicTypeOf(sourceVal);
		if (sourceVal == null) {
			throw new InvalidValueException(PivotMessages.IncompatibleOclAsTypeSourceType, sourceType, argType);
		}
		StandardLibrary standardLibrary = executor.getStandardLibrary();
		if (sourceType.conformsTo(standardLibrary, argType)) {
			return sourceVal;
		}
		else {
			throw new InvalidValueException(PivotMessages.IncompatibleOclAsTypeSourceType, sourceType, argType);
		}
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
		if (sourceValue.isNull()) {
			return sourceValue;
		}
		else {
			return evaluationEnvironment.getUnknownValue(callExp, false, false);
		}
	}
}
