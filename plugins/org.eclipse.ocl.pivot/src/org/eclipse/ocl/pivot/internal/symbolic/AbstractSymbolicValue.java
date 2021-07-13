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
import org.eclipse.ocl.pivot.internal.values.ValueImpl;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * @since 1.16
 */
public abstract class AbstractSymbolicValue extends ValueImpl implements SymbolicValue
{
	@Override
	public @NonNull SymbolicValue asRefinementOf(@NonNull SymbolicValue unrefinedValue) {
	//	@NonNull SymbolicValue thisBaseValue = this.getBaseValue();
		@NonNull SymbolicValue baseValue = unrefinedValue.getBaseValue();
		@NonNull SymbolicValue resultValue = baseValue;			// XXX ?? fudging erroneous IsDead
		if (!mayBeInvalid() && baseValue.mayBeInvalid()) {
			resultValue = AbstractSymbolicRefinedValue.createExceptValue(resultValue, ValueUtil.INVALID_VALUE);
		}
		if (!mayBeNull() && baseValue.mayBeNull()) {
			resultValue = AbstractSymbolicRefinedValue.createExceptValue(resultValue, null);
		}
		if ((basicGetZeroStatus() != null) && !mayBeZero() && (baseValue.basicGetZeroStatus() != null) && baseValue.mayBeZero()) {
			resultValue = AbstractSymbolicRefinedValue.createNotValue(AbstractSymbolicRefinedValue.createIsZeroValue(resultValue));
		}
		return resultValue;
	}

	@Override
	public final @NonNull SymbolicStatus getBooleanStatus() {
		return ClassUtil.nonNullState(basicGetBooleanStatus());
	}

	@Override
	public final @NonNull SymbolicStatus getInvalidStatus() {
		SymbolicStatus invalidStatus = basicGetInvalidStatus();
		return invalidStatus != null ? invalidStatus : SymbolicStatus.UNSATISFIED;
	}

	@Override
	public final @NonNull SymbolicStatus getNullStatus() {
		SymbolicStatus nullStatus = basicGetNullStatus();
		return nullStatus != null ? nullStatus : SymbolicStatus.UNSATISFIED;
	}

	@Override
	public final @NonNull SymbolicStatus getZeroStatus() {
		return ClassUtil.nonNullState(basicGetZeroStatus());
	}

	@Override
	public final boolean isDead() {
		return getDeadStatus().isSatisfied();
	}

	@Override
	public final boolean isFalse() {
		SymbolicStatus booleanStatus = basicGetBooleanStatus();
		return (booleanStatus != null) && booleanStatus.isUnsatisfied();
	//	return getBooleanStatus().isUnsatisfied();
	}

	@Override
	public final boolean isInvalid() {
		return getInvalidStatus().isSatisfied();
	}

	@Override
	public final boolean isNull() {
		return getNullStatus().isSatisfied();
	}

	@Override
	public boolean isRefinementOf(@NonNull SymbolicValue unrefinedValue) {
		@NonNull SymbolicValue thisBaseValue = this.getBaseValue();
		@NonNull SymbolicValue thatBaseValue = unrefinedValue.getBaseValue();
		if (thisBaseValue != thatBaseValue) {
			return false;
		}
		if (mayBeInvalid() && !unrefinedValue.mayBeInvalid()) {
			return false;
		}
		if (mayBeNull() && !unrefinedValue.mayBeNull()) {
			return false;
		}
		if (mayBeZero() && !unrefinedValue.mayBeZero()) {
			return false;
		}
		return true;
	}

	@Override
	public final boolean isTrue() {
		SymbolicStatus booleanStatus = basicGetBooleanStatus();
		return (booleanStatus != null) && booleanStatus.isSatisfied();
	}

	@Override
	public final boolean isZero() {
		return getZeroStatus().isSatisfied();
	}

	@Override
	public boolean mayBeFalse() {
		SymbolicStatus booleanStatus = basicGetBooleanStatus();
		return (booleanStatus != null) && !booleanStatus.isUnsatisfied();
	}

	@Override
	public boolean mayBeInvalid() {
		return !getInvalidStatus().isUnsatisfied();
	}

	@Override
	public boolean mayBeNull() {
		return !getNullStatus().isUnsatisfied();
	}

	@Override
	public boolean mayBeTrue() {
		SymbolicStatus booleanStatus = basicGetBooleanStatus();
		return (booleanStatus != null) && !booleanStatus.isSatisfied();
	}

	@Override
	public boolean mayBeZero() {
		return !getZeroStatus().isUnsatisfied();
	}
}
