/*******************************************************************************
 * Copyright (c) 2016, 2020 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.manager.SymbolicExecutor;
import org.eclipse.ocl.pivot.internal.values.SimpleSymbolicConstraintImpl;
import org.eclipse.ocl.pivot.library.AbstractSimpleUnaryOperation;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.SimpleSymbolicConstraint;
import org.eclipse.ocl.pivot.values.SymbolicOperationCallValue;
import org.eclipse.ocl.pivot.values.SymbolicOperator;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * BooleanNotOperation2 realises the 2-valued not() library operation.
 *
 * @since 1.3
 */
public class BooleanNotOperation2 extends AbstractSimpleUnaryOperation
{
	public static final @NonNull BooleanNotOperation2 INSTANCE = new BooleanNotOperation2();

	/**
	 * @since 1.15
	 */
	@Override
	public void deduceFrom(@NonNull SymbolicExecutor symbolicExecutor, @NonNull SymbolicOperationCallValue resultValue, @NonNull SimpleSymbolicConstraint simpleConstraint) {
		if (simpleConstraint.getSymbolicOperator() == SymbolicOperator.EQUALS) {
			List<@Nullable Object> boxedSourceAndArgumentValues = resultValue.getBoxedSourceAndArgumentValues();
			Object sourceValue = boxedSourceAndArgumentValues.get(0);
			if (sourceValue instanceof SymbolicValue) {
				Object symbolicValue = simpleConstraint.getSymbolicValue();
				SymbolicValue symbolicValue2 = (SymbolicValue)sourceValue;
				if (symbolicValue == Boolean.TRUE) {
					SimpleSymbolicConstraintImpl symbolicConstraint = new SimpleSymbolicConstraintImpl(TypeId.BOOLEAN, false, false, SymbolicOperator.EQUALS, Boolean.FALSE);
					symbolicValue2.deduceFrom(symbolicExecutor, symbolicConstraint);
				}
				else if (symbolicValue == Boolean.FALSE) {
					SimpleSymbolicConstraintImpl symbolicConstraint = new SimpleSymbolicConstraintImpl(TypeId.BOOLEAN, false, false, SymbolicOperator.EQUALS, Boolean.TRUE);
					symbolicValue2.deduceFrom(symbolicExecutor, symbolicConstraint);
				}
			}
		}
	}

	@Override
	public @Nullable Object dispatch(@NonNull Executor executor, @NonNull OperationCallExp callExp, @Nullable Object sourceValue) {
		Boolean sourceBoolean = ValueUtil.asBoolean(sourceValue);
		return evaluate(sourceBoolean);
	}

	@Override
	public @NonNull Boolean evaluate(@Nullable Object argument) {
		if (argument == Boolean.FALSE) {
			return TRUE_VALUE;
		}
		else {
			return FALSE_VALUE;
		}
	}
}
