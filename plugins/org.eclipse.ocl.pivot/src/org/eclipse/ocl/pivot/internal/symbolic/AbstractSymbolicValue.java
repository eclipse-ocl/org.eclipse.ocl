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
		//
		//	The following comparions return unrefinedValue wrapped in refinements to exhibit the
		//	characteristics of this, unless this is a known literal that totally supplants the unrefineValue.
		//
	//	@NonNull SymbolicValue zzbaseValue = unrefinedValue.getBaseValue();
		@NonNull SymbolicValue resultValue = unrefinedValue;			// XXX ?? fudging erroneous IsDead
		//
		//	Resolve invalid refinement
		//
		if (isInvalid()) {
			if (unrefinedValue.mayBeInvalid()) {
				return this;					// "invalid" is except-everything else
			}
			else {
				assert !mayBeNull();
			//	assert !mayBeZero();
				return AbstractSymbolicRefinedValue.createIncompatibility(resultValue, "isInvalid is incompatible with !mayBeInvalid");
			}
		}
		else if (mayBeInvalid()) {
			if (!unrefinedValue.mayBeInvalid()) {
				return AbstractSymbolicRefinedValue.createIncompatibility(resultValue, "mayBeInvalid is incompatible with !mayBeInvalid");
			}
			else {
				// no change
			}
		}
		else {
			if (unrefinedValue.isInvalid()) {
				return AbstractSymbolicRefinedValue.createIncompatibility(resultValue, "notInvalid is incompatible with isInvalid");
			}
			else {
				resultValue = AbstractSymbolicRefinedValue.createExceptValue(resultValue, ValueUtil.INVALID_VALUE);
			}
		}
		//
		//	Resolve null refinement
		//
		if (isNull()) {
			if (unrefinedValue.mayBeNull()) {
				return this;					// "null" is except-everything else
			}
			else {
				assert !mayBeInvalid();
			//	assert !mayBeZero();
				return AbstractSymbolicRefinedValue.createIncompatibility(resultValue, "isNull is incompatible with !mayBeNull");
			}
		}
		else if (mayBeNull()) {
			if (!unrefinedValue.mayBeNull()) {
				return AbstractSymbolicRefinedValue.createIncompatibility(resultValue, "mayBeNull is incompatible with !mayBeNull");
			}
			else {
				// no change
			}
		}
		else {
			if (unrefinedValue.isNull()) {
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
		if (unrefinedValue.basicGetZeroStatus() != null) {
			if (isZero()) {
				if (unrefinedValue.mayBeZero()) {
					return this;					// "0" is except-everything else
				}
				else {
					assert !mayBeInvalid();
					assert !mayBeNull();
					return AbstractSymbolicRefinedValue.createIncompatibility(resultValue, "isZero is incompatible with !mayBeZero");
				}
			}
			else if (mayBeZero()) {
				if (!unrefinedValue.mayBeZero()) {
					return AbstractSymbolicRefinedValue.createIncompatibility(resultValue, "mayBeZero is incompatible with !mayBeZero");
				}
				else {
					// no change
				}
			}
			else {
				if (unrefinedValue.isZero()) {
					return AbstractSymbolicRefinedValue.createIncompatibility(resultValue, "notZero is incompatible with isZero");
				}
				else {
					resultValue = AbstractSymbolicRefinedValue.createExceptValue(resultValue, ValueUtil.ZERO_VALUE);
				}
			}
		}
		//
		//	Resolve true/false refinement
		//
		if (unrefinedValue.basicGetBooleanStatus() != null) {
			if (isTrue()) {
				if (unrefinedValue.mayBeTrue()) {
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
				if (unrefinedValue.mayBeFalse()) {
					return this;					// "false" is except-everything else
				}
				else {
					assert !mayBeInvalid();
					assert !mayBeNull();
				//	assert !mayBeZero();
					return AbstractSymbolicRefinedValue.createIncompatibility(resultValue, "isFalse is incompatible with !mayBeFalse");
				}
			}
			else if (mayBeTrue()) {
				if (!unrefinedValue.mayBeTrue()) {
					return AbstractSymbolicRefinedValue.createIncompatibility(resultValue, "mayBeTrue is incompatible with !mayBeTrue");
				}
				else if (!unrefinedValue.mayBeFalse()) {
					resultValue = AbstractSymbolicRefinedValue.createExceptValue(resultValue, ValueUtil.FALSE_VALUE);
				}
				else {
					// no change
				}
			}
			else if (mayBeFalse()) {
				if (!unrefinedValue.mayBeFalse()) {
					return AbstractSymbolicRefinedValue.createIncompatibility(resultValue, "mayBeFalse is incompatible with !mayBeFalse");
				}
				else if (!unrefinedValue.mayBeTrue()) {
					resultValue = AbstractSymbolicRefinedValue.createExceptValue(resultValue, ValueUtil.TRUE_VALUE);
				}
				else {
					// no change
				}
			}
			else {			// ?? not-true and not-false never happens
				if (!unrefinedValue.mayBeFalse()) {
					resultValue = AbstractSymbolicRefinedValue.createExceptValue(resultValue, ValueUtil.FALSE_VALUE);
				}
				if (!unrefinedValue.mayBeTrue()) {
					resultValue = AbstractSymbolicRefinedValue.createExceptValue(resultValue, ValueUtil.TRUE_VALUE);
				}
			}
		}
		//
		//	Resolve size refinement
		//
		SymbolicContent thisContent = basicGetContent();
		SymbolicContent unrefinedContent = unrefinedValue.basicGetContent();
		if ((thisContent != null) || (unrefinedContent != null)) {
			SymbolicValue resultSize = null;
			boolean thisNotEmpty = (thisContent != null) && !thisContent.mayBeEmpty();
			boolean unrefinedContentNotEmpty = (unrefinedContent != null) && !unrefinedContent.mayBeEmpty();
			SymbolicValue thisSize = thisContent != null ? thisContent.getSize() : null;
			SymbolicValue unrefinedSize = unrefinedContent != null ? unrefinedContent.getSize() : null;
			if (thisNotEmpty && unrefinedContentNotEmpty) {
				resultSize = thisSize;					// ok !isEmpty
			}
			else if (thisNotEmpty || unrefinedContentNotEmpty) {
				return AbstractSymbolicRefinedValue.createIncompatibility(resultValue, "notEmpty is incompatible with mayBeEmpty");
			}
			else if ((thisContent != null) && thisContent.isEmpty()) {
				resultSize = thisSize;						// ok isEmpty
			}
			else if ((unrefinedContent != null) && unrefinedContent.isEmpty()) {
				resultSize = unrefinedContent.getSize();	// ok isEmpty
			}
			else if (thisContent != null) {
				resultSize = thisSize;						// ok mayBeEmpty
			}
			else if (unrefinedContent != null) {
				resultSize = unrefinedContent.getSize();	// ok mayBeEmpty
			}
			else {
				// resultSize = null;							// ok mayBeEmpty
			}
			SymbolicContent resultContent;
			if (resultSize == unrefinedSize) {
				if (resultValue != unrefinedValue) {
					resultValue = AbstractSymbolicRefinedValue.createRefinedContent(resultValue);
					resultContent = resultValue.getContent();
					if (resultSize != null) {
						resultContent.setSize(resultSize);
					}
				}
			}
			else if (resultSize == thisSize) {
				resultValue = AbstractSymbolicRefinedValue.createRefinedContent(resultValue);
				resultContent = resultValue.getContent();
				if (resultSize != null) {
					resultContent.setSize(resultSize);
				}
			}
			else {
				resultValue = AbstractSymbolicRefinedValue.createRefinedContent(resultValue);
				resultContent = resultValue.getContent();
				if (resultSize != null) {
					resultContent.setSize(resultSize);
				}
			}
		}
	//	assert resultValue.isRefinementOf(this);					-- a 'new' UnknownValue may be refined to an 'old' UnknownValue
	//	assert resultValue.isRefinementOf(unrefinedValue);			-- a 'new' KnownValue may refine an 'old' UnknownValue
		return resultValue;		// XXX define a contradiction refinement
	}

	@Override
	public @Nullable SymbolicContent basicGetContent() {
		return null;
	}

	@Override
	public final @NonNull SymbolicStatus getBooleanStatus() {
		return ClassUtil.nonNullState(basicGetBooleanStatus());
	}

	@Override
	public @NonNull SymbolicContent getContent() {
		return ClassUtil.nonNullState(basicGetContent());
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
		if (thisBaseValue != thatBaseValue) {		// ??? null refining may-be-null has new base
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
		toString(s);
		SymbolicContent content = basicGetContent();
		if (content != null) {
			s.append("{");
			content.toString(s);
			s.append("}");
		}
		return s.toString();
	}
}
