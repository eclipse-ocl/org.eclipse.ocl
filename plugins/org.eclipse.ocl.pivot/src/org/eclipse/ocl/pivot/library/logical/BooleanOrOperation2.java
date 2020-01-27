/*******************************************************************************
 * Copyright (c) 2016, 2018 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.library.AbstractSimpleBinaryOperation;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * BooleanOrOperation2 realises the 2-valued or() library operation.
 *
 * @since 1.3
 */
public class BooleanOrOperation2 extends AbstractSimpleBinaryOperation
{
	public static final @NonNull BooleanOrOperation2 INSTANCE = new BooleanOrOperation2();

	@Override
	public @Nullable Object dispatch(@NonNull Executor executor, @NonNull OperationCallExp callExp, @Nullable Object sourceValue) {
		if (sourceValue == Boolean.TRUE) {
			return TRUE_VALUE;
		}
		List<? extends OCLExpression> arguments = callExp.getOwnedArguments();
		OCLExpression argument0 = arguments.get(0);
		assert argument0 != null;
		Object firstArgument = executor.evaluate(argument0);
		Boolean sourceBoolean = ValueUtil.asBoolean(sourceValue);
		Boolean argBoolean = ValueUtil.asBoolean(firstArgument);
		return evaluate(sourceBoolean, argBoolean);
	}

	@Override
	public @NonNull Boolean evaluate(@Nullable Object left, @Nullable Object right) {
		if ((left == Boolean.TRUE) || (right == Boolean.TRUE)) {
			return TRUE_VALUE;
		}
		else {
			return FALSE_VALUE;
		}
	}
}
