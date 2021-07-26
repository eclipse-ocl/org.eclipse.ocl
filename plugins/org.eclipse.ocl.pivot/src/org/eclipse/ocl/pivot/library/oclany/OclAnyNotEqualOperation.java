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
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.SymbolicValue;


/**
 * OclAnyNotEqualOperation realises the OCLAny::<>() library operation and
 * regular derived implementations since the Value classes exhibit
 * OCL value semantics.
 */
public class OclAnyNotEqualOperation extends OclAnyEqualOperation
{
	public static final @NonNull OclAnyNotEqualOperation INSTANCE = new OclAnyNotEqualOperation();

	/**
	 * Overridden to be invalid out for any invalid in, never null, true for mismatching nullity.
	 *
	 * @since 1.16
	 */
	@Override
	protected @NonNull SymbolicValue createResultValue(@NonNull SymbolicEvaluationEnvironment evaluationEnvironment, @NonNull OperationCallExp callExp,
			@NonNull SymbolicValue sourceSymbolicValue, @NonNull List<@NonNull SymbolicValue> argumentSymbolicValues) {
		boolean mayBeInvalid = sourceSymbolicValue.mayBeInvalid();
		int mayBeNullCount = sourceSymbolicValue.mayBeNull() ? 1: 0;
		for (@NonNull SymbolicValue argumentSymbolicValue : argumentSymbolicValues) {
			if (argumentSymbolicValue.mayBeInvalid()) {
				mayBeInvalid = true;
			}
			if (argumentSymbolicValue.mayBeNull()) {
				mayBeNullCount++;
			}
		}
		if (!mayBeInvalid && ((mayBeNullCount & 1) != 0)) {
			return evaluationEnvironment.getKnownValue(Boolean.TRUE);
		}
		else {
			return evaluationEnvironment.getUnknownValue(callExp, false, mayBeInvalid);
		}
	}

	@Override
	public @NonNull Boolean evaluate(@Nullable Object left, @Nullable Object right) {
		Object equals = super.evaluate(left, right);
		if (equals == Boolean.FALSE) {
			return TRUE_VALUE;
		}
		else if (equals == Boolean.TRUE) {
			return FALSE_VALUE;
		}
		else if (equals instanceof InvalidValueException) {
			throw (InvalidValueException)equals;
		}
		else {
			throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.BOOLEAN_NAME, getTypeName(right));
		}
	}

/*	@Override
	public @NonNull SymbolicValue symbolicEvaluate(@NonNull SymbolicEvaluationEnvironment evaluationEnvironment, @NonNull OperationCallExp callExp) {
		SymbolicValue symbolicPreconditionValue = checkPreconditions(evaluationEnvironment, callExp);
		if (symbolicPreconditionValue != null) {
			return symbolicPreconditionValue;
		}
		SymbolicValue sourceSymbolicValue = evaluationEnvironment.symbolicEvaluate(PivotUtil.getOwnedSource(callExp));
		SymbolicValue argumentSymbolicValue = evaluationEnvironment.symbolicEvaluate(PivotUtil.getOwnedArgument(callExp, 0));
		boolean isKnown = sourceSymbolicValue.isKnown() && argumentSymbolicValue.isKnown();
		if (isKnown) {
			Object sourceKnownValue = sourceSymbolicValue.getKnownValue();
			Object argumentKnownValue = argumentSymbolicValue.getKnownValue();
			Boolean resultKnownValue = evaluate(sourceKnownValue, argumentKnownValue);
			return evaluationEnvironment.getKnownValue(resultKnownValue);
		}
		SymbolicStatus sourceNullStatus = sourceSymbolicValue.getNullStatus();
		SymbolicStatus argumentNullStatus = argumentSymbolicValue.getNullStatus();
		Boolean nullSameness = sourceNullStatus.asSame(argumentNullStatus);
		if (nullSameness != null) {
			return evaluationEnvironment.getKnownValue(ValueUtil.asBoolean(!nullSameness));
		}
		SymbolicStatus sourceZeroStatus = sourceSymbolicValue.getZeroStatus();
		SymbolicStatus argumentZeroStatus = argumentSymbolicValue.getZeroStatus();
		Boolean zeroSameness = sourceZeroStatus.asSame(argumentZeroStatus);
		if (zeroSameness != null) {
			return evaluationEnvironment.getKnownValue(ValueUtil.asBoolean(!zeroSameness));
		}
		return createResultValue(evaluationEnvironment, callExp, sourceSymbolicValue, Collections.singletonList(argumentSymbolicValue));
	} */

/*	@Override
	public @Nullable Object symbolicEvaluate(@NonNull Executor executor, @NonNull OperationCallExp operationCallExp, @Nullable Object sourceValue, @Nullable Object argumentValue) {
		if ((sourceValue instanceof SymbolicValue) || (argumentValue instanceof SymbolicValue)) {
			if ((sourceValue == null) && !ValueUtil.mayBeNull(argumentValue)) {
				return ValueUtil.TRUE_VALUE;
			}
			if ((argumentValue == null) && !ValueUtil.mayBeNull(sourceValue)) {
				return ValueUtil.TRUE_VALUE;
			}
			boolean mayBeInvalid = ValueUtil.mayBeInvalid(sourceValue) || ValueUtil.mayBeInvalid(argumentValue);
			return new SymbolicOperationCallValueImpl(operationCallExp, false, / *mayBeNull ||* / mayBeInvalid, this, Lists.newArrayList(sourceValue, argumentValue));
		}
		return evaluate(sourceValue, argumentValue);
	} */
}
