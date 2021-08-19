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
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.NumberValue;

/**
 * SymbolicNumericValue maintains the status of a partial numeric knowledge involing known lower and upper bounds.
 *
 * @since 1.16
 */
public class SymbolicNumericValue extends AbstractLeafSymbolicValue
{
	private static @NonNull SymbolicNumericValue ZERO = new SymbolicNumericValue(ValueUtil.ZERO_VALUE, ValueUtil.ZERO_VALUE);
	private static @NonNull SymbolicNumericValue ZERO_OR_NOT_ZERO = new SymbolicNumericValue(ValueUtil.ZERO_VALUE, null);
	private static @NonNull SymbolicNumericValue NOT_ZERO = new SymbolicNumericValue(ValueUtil.ONE_VALUE, null);
	private static @NonNull SymbolicNumericValue ONE = new SymbolicNumericValue(ValueUtil.ONE_VALUE, ValueUtil.ONE_VALUE);

	public static @NonNull SymbolicNumericValue get(@NonNull NumberValue lowerBound, @Nullable NumberValue upperBound) {
		if (lowerBound.equals(ValueUtil.ZERO_VALUE)) {
			if (upperBound == null) {
				return ZERO_OR_NOT_ZERO;
			}
			else if (upperBound.equals(ValueUtil.ZERO_VALUE)) {
				return ZERO;
			}
		}
		else if (lowerBound.equals(ValueUtil.ONE_VALUE)) {
			if (upperBound == null) {
				return NOT_ZERO;
			}
			else if (upperBound.equals(ValueUtil.ONE_VALUE)) {
				return ONE;
			}
		}
//		return ValueUtil.ZERO_VALUE.equals(knownValue) ? SymbolicNumericStatus.ZERO : SymbolicNumericStatus.NOT_ZERO;
		// TODO Auto-generated method stub
		return new SymbolicNumericValue(lowerBound, upperBound);
	}

	public static @NonNull SymbolicNumericValue getNotZero() {
		return NOT_ZERO;
	}

	public static @NonNull SymbolicNumericValue getZeroOrNotZero() {
		return ZERO_OR_NOT_ZERO;
	}

	/**
	 * Lower inclusive bound on the value.
	 */
	private final @NonNull NumberValue lowerBound;


	/**
	 * Upper inclusive bound on the value. null for no limit.
	 */
	private final @Nullable NumberValue upperBound;

	private SymbolicNumericValue(@NonNull NumberValue lowerBound, @Nullable NumberValue upperBound) {
		super(lowerBound + ".." + (upperBound != null ? upperBound.toString() : "*"), lowerBound instanceof IntegerValue ? TypeId.INTEGER : TypeId.REAL, false, false, null);
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	@Override
	public @NonNull NumberValue getLowerBound() {
		return lowerBound;
	}

	@Override
	public @Nullable NumberValue getUpperBound() {
		return upperBound;
	}

	public boolean isNotZero() {
		return (lowerBound != ValueUtil.ZERO_VALUE);// && (upperBound != ValueUtil.ZERO_VALUE);
	}

	@Override
	public boolean isZero() {
		return (lowerBound == ValueUtil.ZERO_VALUE) && (upperBound == ValueUtil.ZERO_VALUE);
	}

	@Override
	public boolean mayBeZero() {
		return (lowerBound == ValueUtil.ZERO_VALUE);// ^ (upperBound == ValueUtil.ZERO_VALUE);
	}

	@Override
	public @NonNull String toString() {
		return name;
	}
}