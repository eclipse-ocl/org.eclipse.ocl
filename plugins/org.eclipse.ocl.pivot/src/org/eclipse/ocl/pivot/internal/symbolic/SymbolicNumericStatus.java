/**
 * Copyright (c) 2020, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal.symbolic;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.NumberValue;

/**
 * SymbolicNumericStatus maintains the status of a partial numeric knowledge involing known lower and upper bounds.
 *
 * @since 1.16
 */
public class SymbolicNumericStatus implements SymbolicStatus
{
	public static @NonNull SymbolicNumericStatus ZERO = new SymbolicNumericStatus(ValueUtil.ZERO_VALUE, ValueUtil.ZERO_VALUE);
	public static @NonNull SymbolicNumericStatus ZERO_OR_NOT_ZERO = new SymbolicNumericStatus(ValueUtil.ZERO_VALUE, null);
	public static @NonNull SymbolicNumericStatus NOT_ZERO = new SymbolicNumericStatus(ValueUtil.ONE_VALUE, null);

	public static @Nullable SymbolicNumericStatus get(@NonNull NumberValue numericValue) {
		if (numericValue.equals(ValueUtil.ZERO_VALUE)) {
			return ZERO;
		}
//		return ValueUtil.ZERO_VALUE.equals(knownValue) ? SymbolicNumericStatus.ZERO : SymbolicNumericStatus.NOT_ZERO;
		// TODO Auto-generated method stub
		return new SymbolicNumericStatus(numericValue, numericValue);
	}

	/**
	 * Lower inclusive bound on the value.
	 */
	private final @NonNull NumberValue lowerBound;

	/**
	 * Upper inclusive bound on the value. null for no limit.
	 */
	private final @Nullable NumberValue upperBound;

	private SymbolicNumericStatus(@NonNull NumberValue lowerBound, @Nullable NumberValue upperBound) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	public boolean isNotZero() {
		return (lowerBound != ValueUtil.ZERO_VALUE);// && (upperBound != ValueUtil.ZERO_VALUE);
	}

	public boolean isZero() {
		return (lowerBound == ValueUtil.ZERO_VALUE) && (upperBound == ValueUtil.ZERO_VALUE);
	}

	public boolean mayBeZero() {
		return (lowerBound == ValueUtil.ZERO_VALUE);// ^ (upperBound == ValueUtil.ZERO_VALUE);
	}

	@Override
	public @NonNull String toString() {
		return lowerBound + ".." + (upperBound != null ? upperBound.toString() : "*");
	}
}