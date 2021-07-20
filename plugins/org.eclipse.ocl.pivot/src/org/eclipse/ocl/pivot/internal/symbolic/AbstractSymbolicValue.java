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
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * @since 1.16
 */
public abstract class AbstractSymbolicValue implements SymbolicValue
{
	@Override
	public @Nullable String asIncompatibility() {
		return null;
	}

	@Override
	public @NonNull SymbolicValue asRefinementOf(@NonNull SymbolicValue unrefinedValue) {
		@NonNull SymbolicValue baseValue = unrefinedValue.getBaseValue();
		@NonNull SymbolicValue resultValue = baseValue;			// XXX ?? fudging erroneous IsDead
		//
		//	Resolve invalid refinement
		//
		if (isInvalid()) {
			if (baseValue.mayBeInvalid()) {
				return this;					// "invalid" is except-everything else
			}
			else {
				assert !mayBeNull();
				assert !mayBeZero();
				return AbstractSymbolicRefinedValue.createIncompatibility(resultValue, "isInvalid is incompatible with !mayBeInvalid");
			}
		}
		else if (mayBeInvalid()) {
			if (!baseValue.mayBeInvalid()) {
				return AbstractSymbolicRefinedValue.createIncompatibility(resultValue, "mayBeInvalid is incompatible with !mayBeInvalid");
			}
			else {
				// no change
			}
		}
		else {
			resultValue = AbstractSymbolicRefinedValue.createExceptValue(resultValue, ValueUtil.INVALID_VALUE);
		}
		//
		//	Resolve null refinement
		//
		if (isNull()) {
			if (baseValue.mayBeNull()) {
				return this;					// "null" is except-everything else
			}
			else {
				assert !mayBeInvalid();
				assert !mayBeZero();
				return AbstractSymbolicRefinedValue.createIncompatibility(resultValue, "isNull is incompatible with !mayBeNull");
			}
		}
		else if (mayBeNull()) {
			if (!baseValue.mayBeNull()) {
				return AbstractSymbolicRefinedValue.createIncompatibility(resultValue, "mayBeNull is incompatible with !mayBeNull");
			}
			else {
				// no change
			}
		}
		else {
			if (resultValue.isNull()) {
				return AbstractSymbolicRefinedValue.createIncompatibility(resultValue, "notNull is incompatible with isNull");
			}
			else {
			//	assert !mayBeInvalid();
			//	assert !mayBeZero();
			//	return AbstractSymbolicRefinedValue.createIncompatibility(resultValue, "isNull is incompatible with !mayBeNull");
				resultValue = AbstractSymbolicRefinedValue.createExceptValue(resultValue, null);
			}
		}
		//
		//	Resolve zero refinement
		//
		if (baseValue.basicGetZeroStatus() != null) {
			if (isZero()) {
				if (baseValue.mayBeZero()) {
					return this;					// "0" is except-everything else
				}
				else {
					assert !mayBeInvalid();
					assert !mayBeNull();
					return AbstractSymbolicRefinedValue.createIncompatibility(resultValue, "isZero is incompatible with !mayBeZero");
				}
			}
			else if (mayBeZero()) {
				if (!baseValue.mayBeZero()) {
					return AbstractSymbolicRefinedValue.createIncompatibility(resultValue, "mayBeZero is incompatible with !mayBeZero");
				}
				else {
					// no change
				}
			}
			else {
				resultValue = AbstractSymbolicRefinedValue.createExceptValue(resultValue, ValueUtil.ZERO_VALUE);
			}
		}
		//
		//	Resolve true/false refinement
		//
		if (baseValue.basicGetBooleanStatus() != null) {
			if (isTrue()) {
				if (baseValue.mayBeTrue()) {
					return this;					// "true" is except-everything else
				}
				else {
					assert !mayBeInvalid();
					assert !mayBeNull();
					assert !mayBeZero();
					return AbstractSymbolicRefinedValue.createIncompatibility(resultValue, "isTrue is incompatible with !mayBeTrue");
				}
			}
			else if (isFalse()) {
				if (baseValue.mayBeFalse()) {
					return this;					// "false" is except-everything else
				}
				else {
					assert !mayBeInvalid();
					assert !mayBeNull();
					assert !mayBeZero();
					return AbstractSymbolicRefinedValue.createIncompatibility(resultValue, "isFalse is incompatible with !mayBeFalse");
				}
			}
			else if (mayBeTrue()) {
				if (!baseValue.mayBeTrue()) {
					return AbstractSymbolicRefinedValue.createIncompatibility(resultValue, "mayBeTrue is incompatible with !mayBeTrue");
				}
				else if (!baseValue.mayBeFalse()) {
					resultValue = AbstractSymbolicRefinedValue.createExceptValue(resultValue, ValueUtil.FALSE_VALUE);
				}
				else {
					// no change
				}
			}
			else if (mayBeFalse()) {
				if (!baseValue.mayBeFalse()) {
					return AbstractSymbolicRefinedValue.createIncompatibility(resultValue, "mayBeFalse is incompatible with !mayBeFalse");
				}
				else if (!baseValue.mayBeTrue()) {
					resultValue = AbstractSymbolicRefinedValue.createExceptValue(resultValue, ValueUtil.TRUE_VALUE);
				}
				else {
					// no change
				}
			}
			else {			// ?? not-true and not-false never happens
				if (!baseValue.mayBeFalse()) {
					resultValue = AbstractSymbolicRefinedValue.createExceptValue(resultValue, ValueUtil.FALSE_VALUE);
				}
				if (!baseValue.mayBeTrue()) {
					resultValue = AbstractSymbolicRefinedValue.createExceptValue(resultValue, ValueUtil.TRUE_VALUE);
				}
			}
		}



//		if ((basicGetZeroStatus() != null) && !mayBeZero() && (baseValue.basicGetZeroStatus() != null) && baseValue.mayBeZero()) {
//			resultValue = AbstractSymbolicRefinedValue.createNotValue(AbstractSymbolicRefinedValue.createIsZeroValue(resultValue));
//		}
		assert resultValue.isRefinementOf(unrefinedValue);
		return resultValue;		// XXX define a contradiction refinement
/*		SymbolicStatus booleanWriteStatus = writeValue.basicGetBooleanStatus();
		if (booleanWriteStatus != null) {
			boolean mayBeFalse = !booleanWriteStatus.isSatisfied();
			boolean mayBeTrue = !booleanWriteStatus.isUnsatisfied();
			boolean mustBeFalse = readValue.isFalse();
			boolean mustBeTrue = readValue.isTrue();
			if (mustBeFalse && !mayBeFalse) {
				return "mustBeFalse is incompatible with !mayBeFalse";
			}
			if (mustBeTrue && !mayBeTrue) {
				return "mustBeTrue is incompatible with !mayBeTrue";
			}
		}
		if (writeValue.basicGetZeroStatus() != null) {
			if (writeValue.isZero() && !readValue.mayBeZero()) {
				return "isZero is incompatible with !mayBeZero";
			}
			if (writeValue.mayBeZero() && !readValue.mayBeZero()) {
				return "mayBeZero is incompatible with !mayBeZero";
			}
		} */
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
		if (((basicGetZeroStatus() != null) && mayBeZero()) && !((unrefinedValue.basicGetZeroStatus() != null) && unrefinedValue.mayBeZero())) {
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

	@Override
	public /*final*/ @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 100);
		return s.toString();
	}
}
