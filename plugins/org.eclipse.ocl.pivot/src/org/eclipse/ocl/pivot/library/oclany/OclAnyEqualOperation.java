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
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.evaluation.AbstractSymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.values.SymbolicUnknownValueImpl;
import org.eclipse.ocl.pivot.library.AbstractSimpleBinaryOperation;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * OclAnyEqualOperation realises the OCLAny::=() library operation and
 * regular derived implementations since the Value classes exhibit
 * OCL value semantics.
 */
public class OclAnyEqualOperation extends AbstractSimpleBinaryOperation
{
	public static final @NonNull OclAnyEqualOperation INSTANCE = new OclAnyEqualOperation();

	/**
	 * @since 1.15
	 */
	@Override
	protected @Nullable SymbolicValue checkPreconditions(@NonNull AbstractSymbolicEvaluationEnvironment symbolicEvaluationEnvironment, @NonNull OperationCallExp callExp) {
		TypeId returnTypeId = callExp.getTypeId();
		OCLExpression source = PivotUtil.getOwnedSource(callExp);
		SymbolicValue sourceProblem = symbolicEvaluationEnvironment.checkNotInvalid(source, returnTypeId);
		if (sourceProblem != null) {
			return sourceProblem;
		}
		OCLExpression argument = PivotUtil.getOwnedArgument(callExp, 0);
		SymbolicValue argumentProblem = symbolicEvaluationEnvironment.checkNotInvalid(argument, returnTypeId);
		if (argumentProblem != null) {
			return argumentProblem;
		}
		return null;
	}

	/**
	 * Overridden to be invalid-out for any invalid in, never null, false for mismatching nullity.
	 *
	 * @since 1.15
	 */
	@Override
	protected @NonNull SymbolicValue createChildSymbolicValue(@NonNull AbstractSymbolicEvaluationEnvironment symbolicEvaluationEnvironment, @NonNull OperationCallExp callExp,
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
			return symbolicEvaluationEnvironment.getKnownValue(Boolean.FALSE);
		}
		else {
			return new SymbolicUnknownValueImpl(callExp.getTypeId(), false, mayBeInvalid);
		}
	}

	/**
	 * @since 1.15
	 *
	@Override
	public void deduceFrom(@NonNull SymbolicExecutor symbolicExecutor, @NonNull SymbolicOperationCallValue operationCallValue, @NonNull SimpleSymbolicConstraint resultConstraint) {
		deduceFrom(symbolicExecutor, operationCallValue, resultConstraint, false);
	} */

	/**
	 * @since 1.15
	 *
	protected void deduceFrom(@NonNull SymbolicExecutor symbolicExecutor, @NonNull SymbolicOperationCallValue resultValue, @NonNull SimpleSymbolicConstraint simpleConstraint, boolean isInverted) {
		SymbolicOperator thisSymbolicOperator = simpleConstraint.getSymbolicOperator();
		SymbolicOperator nestedSymbolicOperator = null;
		if (thisSymbolicOperator == (isInverted ? SymbolicOperator.NOT_EQUALS : SymbolicOperator.EQUALS)) {		// FIXME simplify, XOR
			Object knownValue = simpleConstraint.getSymbolicValue();
			if (knownValue == Boolean.TRUE) {
				nestedSymbolicOperator = SymbolicOperator.EQUALS;
			}
			else if (knownValue == Boolean.FALSE) {
				nestedSymbolicOperator = SymbolicOperator.NOT_EQUALS;
			}
		}
		else if (thisSymbolicOperator == (isInverted ? SymbolicOperator.EQUALS : SymbolicOperator.NOT_EQUALS)) {
			Object knownValue = simpleConstraint.getSymbolicValue();
			if (knownValue == Boolean.TRUE) {
				nestedSymbolicOperator = SymbolicOperator.NOT_EQUALS;
			}
			else if (knownValue == Boolean.FALSE) {
				nestedSymbolicOperator = SymbolicOperator.EQUALS;
			}
		}
		else {
			throw new IllegalStateException(String.valueOf(thisSymbolicOperator));
		}
		if (nestedSymbolicOperator != null) {
			List<@Nullable Object> boxedSourceAndArgumentValues = resultValue.getBoxedSourceAndArgumentValues();
			Object sourceValue = boxedSourceAndArgumentValues.get(0);
			Object argumentValue = boxedSourceAndArgumentValues.get(1);
		//	OCLExpression expression = resultValue.getExpression();
			if (sourceValue instanceof SymbolicValue) {
				SymbolicValue symbolicValue = (SymbolicValue)sourceValue;
				SimpleSymbolicConstraintImpl symbolicConstraint = new SimpleSymbolicConstraintImpl(symbolicValue.getTypeId(), false, false, nestedSymbolicOperator, argumentValue);
			//	symbolicExecutor.replace(this, symbolicConstraint);
				symbolicValue.deduceFrom(symbolicExecutor, symbolicConstraint);	// FIXME special case null argumentValue
			}
			if (argumentValue instanceof SymbolicValue) {
				SymbolicValue symbolicValue = (SymbolicValue)argumentValue;
				SimpleSymbolicConstraintImpl symbolicConstraint = new SimpleSymbolicConstraintImpl(symbolicValue.getTypeId(), false, false, nestedSymbolicOperator, sourceValue);
				symbolicValue.deduceFrom(symbolicExecutor, symbolicConstraint);
			}
		}
	} */

	@Override
	public @NonNull Boolean evaluate(@Nullable Object left, @Nullable Object right) {
		//
		//	A.2.2 is clear. 11.3.1 is vague.
		//
		if (left instanceof InvalidValueException) {
			throw (InvalidValueException)left;
		}
		if (right instanceof InvalidValueException) {
			throw (InvalidValueException)right;
		}
		if (left == null) {
			return right == null;
		}
		else if ((left instanceof Type) && (right instanceof Type)) {
			boolean result = ((Type) left).getTypeId().equals(((Type) right).getTypeId());		// FIXME is this a sound/efficient tradeoff for not boxing?
			return result;
		}
		else {
			boolean result = left.equals(right);
			return result;
		}
	}

	/**
	 * @since 1.15
	 *
	@Override
	public @Nullable Object symbolicEvaluate(@NonNull Executor executor, @NonNull OperationCallExp operationCallExp, @Nullable Object sourceValue, @Nullable Object argumentValue) {
		if ((sourceValue instanceof SymbolicValue) || (argumentValue instanceof SymbolicValue)) {
			if ((sourceValue == null) && !ValueUtil.mayBeNull(argumentValue)) {
				return ValueUtil.FALSE_VALUE;
			}
			if ((argumentValue == null) && !ValueUtil.mayBeNull(sourceValue)) {
				return ValueUtil.FALSE_VALUE;
			}
			boolean mayBeInvalid = ValueUtil.mayBeInvalid(sourceValue) || ValueUtil.mayBeInvalid(argumentValue);
			return new SymbolicOperationCallValueImpl(operationCallExp, false, / *mayBeNull ||* / mayBeInvalid, this, Lists.newArrayList(sourceValue, argumentValue));
		}
		return evaluate(sourceValue, argumentValue);
	} */
}
