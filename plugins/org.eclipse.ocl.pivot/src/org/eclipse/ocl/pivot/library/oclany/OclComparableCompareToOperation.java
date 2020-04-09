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
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.values.SymbolicConstraintImpl;
import org.eclipse.ocl.pivot.library.AbstractSimpleBinaryOperation;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.SymbolicOperator;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * OclComparableCompareToOperation realizes the abstract compareTo library operation using intrinsic Java functionality.
 */
public class OclComparableCompareToOperation extends AbstractSimpleBinaryOperation
{
	public static final @NonNull OclComparableCompareToOperation INSTANCE = new OclComparableCompareToOperation();

	@Override
	public @NonNull IntegerValue evaluate(@Nullable Object left, @Nullable Object right) {
		if (left instanceof Comparable<?>) {
			@SuppressWarnings("unchecked") int compareTo = ((Comparable<Object>)left).compareTo(right);
			return integerValueOf(compareTo);
		}
		else if (left instanceof SymbolicValue) {
			boolean mayBeInvalid = ValueUtil.mayBeInvalid(left) || ValueUtil.mayBeInvalid(right);
			boolean mayBeNull = ValueUtil.mayBeNull(left) || ValueUtil.mayBeNull(right);
			return new SymbolicConstraintImpl(TypeId.INTEGER, false, mayBeNull || mayBeInvalid, SymbolicOperator.COMPARE_TO, right);
		}
		else {
			return integerValueOf(ValueUtil.throwUnsupportedCompareTo(left, right));
		}
	}
}
