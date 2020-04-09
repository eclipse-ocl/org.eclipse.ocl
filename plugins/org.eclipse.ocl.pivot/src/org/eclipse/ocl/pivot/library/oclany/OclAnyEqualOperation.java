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
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.manager.SymbolicExecutor;
import org.eclipse.ocl.pivot.internal.values.SymbolicConstraintImpl;
import org.eclipse.ocl.pivot.internal.values.SymbolicResultValueImpl;
import org.eclipse.ocl.pivot.library.AbstractSimpleBinaryOperation;
import org.eclipse.ocl.pivot.library.InvertibleLibraryOperation;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.SymbolicConstraint;
import org.eclipse.ocl.pivot.values.SymbolicOperator;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * OclAnyEqualOperation realises the OCLAny::=() library operation and
 * regular derived implementations since the Value classes exhibit
 * OCL value semantics.
 */
public class OclAnyEqualOperation extends AbstractSimpleBinaryOperation implements InvertibleLibraryOperation
{
	public static final @NonNull OclAnyEqualOperation INSTANCE = new OclAnyEqualOperation();

	@Override
	public void deduceFrom(@NonNull SymbolicExecutor symbolicExecutor, @NonNull SymbolicValue resultValue, @NonNull SymbolicConstraint resultConstraint) {
		deduceFrom(symbolicExecutor, resultValue, resultConstraint, false);
	}

	/**
	 * @since 1.12
	 */
	protected void deduceFrom(@NonNull SymbolicExecutor symbolicExecutor, @NonNull SymbolicValue resultValue, @NonNull SymbolicConstraint resultConstraint, boolean isInverted) {
		SymbolicOperator thisSymbolicOperator = resultConstraint.getSymbolicOperator();
		SymbolicOperator nestedSymbolicOperator = null;
		if (thisSymbolicOperator == (isInverted ? SymbolicOperator.NOT_EQUALS : SymbolicOperator.EQUALS)) {		// FIXME simplify, XOR
			Object knownValue = resultConstraint.getSymbolicValue();
			if (knownValue == Boolean.TRUE) {
				nestedSymbolicOperator = SymbolicOperator.EQUALS;
			}
			else if (knownValue == Boolean.FALSE) {
				nestedSymbolicOperator = SymbolicOperator.NOT_EQUALS;
			}
		}
		else if (thisSymbolicOperator == (isInverted ? SymbolicOperator.EQUALS : SymbolicOperator.NOT_EQUALS)) {
			Object knownValue = resultConstraint.getSymbolicValue();
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
			Object sourceValue = ((SymbolicResultValueImpl)resultValue).getSourceValue();
			Object argumentValue = ((SymbolicResultValueImpl)resultValue).getArgumentValue();
			if (sourceValue instanceof SymbolicValue) {
				((SymbolicValue)sourceValue).deduceFrom(symbolicExecutor, new SymbolicConstraintImpl(TypeId.BOOLEAN, false, false, nestedSymbolicOperator, argumentValue));
			}
			if (argumentValue instanceof SymbolicValue) {
				((SymbolicValue)argumentValue).deduceFrom(symbolicExecutor, new SymbolicConstraintImpl(TypeId.BOOLEAN, false, false, nestedSymbolicOperator, sourceValue));
			}
		}
	}

	@Override
	public @Nullable Object evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId,
			@Nullable Object sourceValue, @Nullable Object argumentValue) {
		if ((sourceValue instanceof SymbolicValue) || (argumentValue instanceof SymbolicValue)) {
//			boolean mayBeInvalid = ValueUtil.mayBeInvalid(sourceValue) || ValueUtil.mayBeInvalid(argumentValue);
			if ((!ValueUtil.mayBeNull(sourceValue) && (argumentValue == null)) || ((sourceValue == null) && !ValueUtil.mayBeNull(argumentValue))) {
				return evaluate(executor, sourceValue, argumentValue);
//				return new SymbolicBooleanValueImpl(returnTypeId, false, /*mayBeNull ||*/ mayBeInvalid, this, sourceValue, argumentValue);
			}
			else {
				return new SymbolicResultValueImpl(returnTypeId, false, /*mayBeNull ||*/ false, this, sourceValue, argumentValue);
			}
		}
		else {
			return evaluate(executor, sourceValue, argumentValue);
		}
	}

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
}
