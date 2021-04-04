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
package org.eclipse.ocl.pivot.library;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.evaluation.Evaluator;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.values.SymbolicOperationCallValueImpl;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.SymbolicValue;

import com.google.common.collect.Lists;

/**
 * LibraryBinaryOperation defines the invocation API of a binary operation using
 * <br>
 * either an evaluator call expression, source and argument array
 * <br>
 * or an evaluator return type id and arguments.
 */
public interface LibraryBinaryOperation extends LibraryOperation
{
	/**
	 * Return the result of evaluating the operation on left and right arguments.
	 * An invalid return may be indicated by throwing an exception returning Java null or OCL invalid.
	 * @since 1.1
	 */
	public interface LibraryBinaryOperationExtension extends LibraryBinaryOperation, LibraryOperationExtension
	{
		@Nullable Object evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object sourceValue, @Nullable Object argumentValue);
	}
	/** @deprecated use Executor */
	@Deprecated
	@Nullable Object evaluate(@NonNull Evaluator evaluator, @NonNull TypeId returnTypeId, @Nullable Object sourceValue, @Nullable Object argumentValue);

	/**
	 * Evaluate an OclSelf binary operations as part of the symbolic evaluator. At least one of the source/argument values is a SymbolicValue.
	 *
	 * @since 1.15
	 */
	default @Nullable Object symbolicEvaluate(@NonNull Executor executor, @NonNull OperationCallExp operationCallExp, @Nullable Object sourceValue, @Nullable Object argumentValue) {
		assert (sourceValue instanceof SymbolicValue) || (argumentValue instanceof SymbolicValue);
		boolean mayBeInvalid = ValueUtil.mayBeInvalid(sourceValue) || ValueUtil.mayBeInvalid(argumentValue);
		boolean mayBeNull = ValueUtil.mayBeNull(sourceValue) || ValueUtil.mayBeNull(argumentValue);
		return new SymbolicOperationCallValueImpl(operationCallExp, false, mayBeNull || mayBeInvalid, this, Lists.newArrayList(sourceValue, argumentValue));
	}
}
