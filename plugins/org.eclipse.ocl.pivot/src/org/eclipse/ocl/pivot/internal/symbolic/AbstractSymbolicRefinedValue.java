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
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent.SymbolicCollectionContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent.SymbolicMapContent;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValue;
import org.eclipse.ocl.pivot.values.SymbolicRefinedValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * @since 1.16
 */
public abstract class AbstractSymbolicRefinedValue extends AbstractSymbolicValue implements SymbolicRefinedValue
{
	/**
	 * BeKnown reports a SATISFIED/UNSATISFIED BooleanStatus or ZeroStatus for known constants.
	 *
	private static class BeKnownSymbolicValue extends AbstractRefinedSymbolicValue
	{
		protected final @NonNull Object knownValue;

		public BeKnownSymbolicValue(@NonNull SymbolicValue value, @NonNull Object knownValue) {
			super(value);
			this.knownValue = knownValue;
			assert ValueUtil.isBoxed(knownValue);
		}

		@Override
		public @Nullable SymbolicStatus basicGetBooleanStatus() {
			if (knownValue instanceof Boolean) {
				return SymbolicStatus.valueOf(((Boolean)knownValue).booleanValue());
			}
			return null;
		}

		@Override
		public @Nullable SymbolicStatus basicGetZeroStatus() {
			if (knownValue instanceof NumberValue) {
				return SymbolicStatus.valueOf(((NumberValue)knownValue).equals(ValueUtil.ZERO_VALUE));
			}
			return null;
		}

		@Override
		public @NonNull SymbolicStatus getInvalidStatus() {
			return SymbolicStatus.valueOf(knownValue instanceof InvalidValue);
		}

		@Override
		public @NonNull SymbolicStatus getNullStatus() {
			return SymbolicStatus.valueOf(knownValue == ValueUtil.NULL_VALUE);
		}

		@Override
		public void toString(@NonNull StringBuilder s, int sizeLimit) {
			s.append("%BeKnown(");
			super.toString(s, sizeLimit);
			s.append(", ");
			s.append(knownValue);
			s.append(")");
		}
	} */

	/**
	 * Except refines a SymbolicStatus to define a dead.(unreachable) evaluation
	 */
	private static class SymbolicDeadValue extends AbstractSymbolicRefinedValue
	{
		public SymbolicDeadValue(@NonNull SymbolicValue value) {
			super(value);
			assert !(value instanceof SymbolicRefinedValue);
		}

		@Override
		public @NonNull SymbolicStatus basicGetBooleanStatus() {
			return getDeadStatus();
		}

		@Override
		public @NonNull SymbolicStatus basicGetInvalidStatus() {
			return SymbolicStatus.UNDECIDED;
		}

		@Override
		public @NonNull SymbolicStatus basicGetNullStatus() {
			return SymbolicStatus.UNDECIDED;
		}

		@Override
		public @NonNull SymbolicStatus basicGetZeroStatus() {
			return SymbolicStatus.UNDECIDED;
		}

		@Override
		public @NonNull SymbolicStatus getDeadStatus() {
			return SymbolicStatus.SATISFIED;
		}

		@Override
		public boolean isNullFree() {
			return false;
		}

/*		@Override
		public boolean isSmallerThan(@NonNull SymbolicValue minSizeValue) {
			return false;
		} */

		@Override
		public boolean mayBeInvalid() {
			return false;
		}

		@Override
		public boolean mayBeNull() {
			return false;
		}

/*		@Override
		public boolean mayBeSmallerThan(@NonNull SymbolicValue minSizeValue) {
			return false;
		} */

		@Override
		public boolean mayBeZero() {
			return false;
		}

		@Override
		public void toString(@NonNull StringBuilder s, int sizeLimit) {
			s.append("%Dead(");
			super.toString(s, sizeLimit);
			s.append(")");
		}
	}

	/**
	 * Except refines a SymbolicStatus to exclude a possible value.
	 */
	private static class SymbolicExceptValue extends AbstractSymbolicRefinedValue
	{
		protected final @NonNull Object exceptValue;

		public SymbolicExceptValue(@NonNull SymbolicValue value, @Nullable Object exceptValue) {
			super(value);
			assert ValueUtil.isBoxed(exceptValue);
			this.exceptValue = exceptValue != null ? exceptValue : ValueUtil.NULL_VALUE;
			assert !(ValueUtil.isInvalidValue(exceptValue) && !value.mayBeInvalid());
			assert !(ValueUtil.isNullValue(exceptValue) && !value.mayBeNull());
			assert !((exceptValue == ValueUtil.ZERO_VALUE) && !value.mayBeZero());
		}

		@Override
		public @Nullable SymbolicStatus basicGetBooleanStatus() {
			SymbolicStatus booleanStatus = super.basicGetBooleanStatus();
			if (exceptValue.equals(ValueUtil.TRUE_VALUE)) {
				booleanStatus = SymbolicStatus.UNSATISFIED;
			}
			else if (exceptValue.equals(ValueUtil.FALSE_VALUE)) {
				booleanStatus = SymbolicStatus.SATISFIED;
			}
			return booleanStatus;
		}

		@Override
		public @Nullable SymbolicStatus basicGetInvalidStatus() {
			SymbolicStatus invalidStatus = super.basicGetInvalidStatus();
			if (exceptValue instanceof InvalidValue) {
				invalidStatus = SymbolicStatus.UNSATISFIED;
			}
			return invalidStatus;
		}

		@Override
		public @Nullable SymbolicStatus basicGetNullStatus() {
			SymbolicStatus nullStatus = super.basicGetNullStatus();
			if (exceptValue == ValueUtil.NULL_VALUE) {
				nullStatus = SymbolicStatus.UNSATISFIED;
			}
			return nullStatus;
		}

		@Override
		public @Nullable SymbolicStatus basicGetZeroStatus() {
			SymbolicStatus zeroStatus = super.basicGetInvalidStatus();
			if (exceptValue.equals(ValueUtil.ZERO_VALUE)) {
				zeroStatus = SymbolicStatus.UNSATISFIED;
			}
			return zeroStatus;
		}

		@Override
		public void toString(@NonNull StringBuilder s, int sizeLimit) {
			s.append("%Except(");
			super.toString(s, sizeLimit);
			s.append(", ");
			ValueUtil.toString(exceptValue, s, sizeLimit);
			s.append(")");
		}
	}

	/**
	 * IsZero converts a ZeroStatus value to a BooleanStatus
	 */
	private static class SymbolicIsZeroValue extends AbstractSymbolicRefinedValue
	{
		public SymbolicIsZeroValue(@NonNull SymbolicValue value) {
			super(value);
		}

		@Override
		public @NonNull SymbolicStatus basicGetBooleanStatus() {
			return basicGetZeroStatus();
		}

		@Override
		public @Nullable SymbolicStatus basicGetInvalidStatus() {
			return null;
		}

		@Override
		public @Nullable SymbolicStatus basicGetNullStatus() {
			return null;
		}

		@Override
		public @NonNull SymbolicStatus basicGetZeroStatus() {
			return value.getZeroStatus();
		}

		@Override
		public @Nullable Object getKnownValue() {
			SymbolicStatus booleanStatus = getBooleanStatus();
			return /*booleanStatus == null ? null :*/ booleanStatus.isSatisfied() ? Boolean.TRUE : Boolean.FALSE;
		}

		@Override
		public void toString(@NonNull StringBuilder s, int sizeLimit) {
			s.append("%IsZero(");
			super.toString(s, sizeLimit);
			s.append(")");
		}
	}

	private static class SymbolicNotValue extends AbstractSymbolicRefinedValue
	{		// FIXME only negate Boolean / correct TypeId
		public SymbolicNotValue(@NonNull SymbolicValue value) {
			super(value);
		}

		@Override
		public @NonNull SymbolicStatus basicGetBooleanStatus() {
			return value.getBooleanStatus().not();		// not super to avoid recursion
		}

		@Override
		public @Nullable SymbolicStatus basicGetInvalidStatus() {
			SymbolicStatus invalidStatus = value.basicGetInvalidStatus();
			return invalidStatus != null ? invalidStatus.not() : null;
		}

		@Override
		public @Nullable SymbolicStatus basicGetNullStatus() {
			SymbolicStatus nullStatus = value.basicGetNullStatus();
			return nullStatus != null ? nullStatus.not() : null;
		}

		@Override
		public @Nullable SymbolicStatus basicGetZeroStatus() {
			SymbolicStatus zeroStatus = value.basicGetZeroStatus();
			return zeroStatus != null ? zeroStatus.not() : null;
		}

		@Override
		public void toString(@NonNull StringBuilder s, int sizeLimit) {
			s.append("%Not(");
			super.toString(s, sizeLimit);
			s.append(")");
		}
	}

/*	private static class NotSmallerThanSymbolicValue extends AbstractRefinedSymbolicValue
	{
		protected final @NonNull SymbolicValue minSizeValue;

		public NotSmallerThanSymbolicValue(@NonNull SymbolicValue value, @NonNull SymbolicValue minSizeValue) {
			super(value);
			this.minSizeValue = minSizeValue;
		}

		@Override
		public boolean isSmallerThan(@NonNull SymbolicValue minSizeValue) {
			// TODO Auto-generated method stub
		//	return super.isSmallerThan(minSizeValue);
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean mayBeSmallerThan(@NonNull SymbolicValue minSizeValue) {
			// TODO Auto-generated method stub
		//	return super.mayBeSmallerThan(minSizeValue);
			throw new UnsupportedOperationException();
		}

		@Override
		public @NonNull String toString() {
			return "%NotSmallerThan(" + value.toString() + "," + minSizeValue.toString() + ")";
		}
	} */

	private static class SymbolicNullFreeValue extends AbstractSymbolicRefinedValue
	{
		public SymbolicNullFreeValue(@NonNull SymbolicValue value) {
			super(value);
		}

		@Override
		public boolean isNullFree() {
			return true;
		}

		@Override
		public void toString(@NonNull StringBuilder s, int sizeLimit) {
			s.append("%NullFree(");
			super.toString(s, sizeLimit);
			s.append(")");
		}
	}

	public static class SymbolicRefinedContentValue extends AbstractSymbolicRefinedValue
	{
		private final @NonNull SymbolicContent content;

		public SymbolicRefinedContentValue(@NonNull SymbolicValue value) {
			super(value);
			this.content = value.getContent().shallowClone();
		}

		@Override
		public @NonNull SymbolicStatus basicGetZeroStatus() {
			return content.getSize().getZeroStatus();
		}

		@Override
		public @NonNull SymbolicCollectionContent getCollectionContent() {
			return (SymbolicCollectionContent) content;
		}

		@Override
		public @NonNull SymbolicContent getContent() {
			return content;
		}

		@Override
		public @NonNull SymbolicMapContent getMapContent() {
			return (SymbolicMapContent) content;
		}

		public @NonNull SymbolicValue getSize() {
			return content.getSize();
		}

		public void setSize(@NonNull SymbolicValue refinedSize) {
			content.setSize(refinedSize);
		}

		@Override
		public void toString(@NonNull StringBuilder s, int sizeLimit) {
			s.append("%Refined(");
			value.toString(s, sizeLimit);
			s.append(",{");
			content.toString(s);
			s.append("}");
			s.append(")");
		}
	}

	private static class SymbolicSizeValue extends AbstractSymbolicRefinedValue
	{
		public SymbolicSizeValue(@NonNull SymbolicValue value) {
			super(value);
		}

		@Override
		public @Nullable SymbolicStatus basicGetInvalidStatus() {
			return null;
		}

		@Override
		public @Nullable SymbolicStatus basicGetNullStatus() {
			return null;
		}

		@Override
		public @NonNull SymbolicStatus basicGetZeroStatus() {
			return value.getContent().getSize().getZeroStatus();
		}

		@Override
		public @NonNull String toString() {
			return "%Size(" + value.toString() + ")";
		}
	}

/*	public static @NonNull SymbolicValue createBeKnownValue(@NonNull SymbolicValue symbolicValue, @NonNull Object knownValue) {
		if (!symbolicValue.isZero()) {
			return new BeKnownSymbolicValue(symbolicValue, knownValue);
		}
		else {
			return symbolicValue;
		}
	} */

	public static @NonNull SymbolicValue createDeadValue(SymbolicValue symbolicValue) {
		if (!symbolicValue.isDead()) {
			return new SymbolicDeadValue(symbolicValue.getBaseValue());
		}
		else {
			return symbolicValue;
		}
	}

	public static @NonNull SymbolicValue createExceptValue(@NonNull SymbolicValue symbolicValue, @Nullable Object exceptValue) {
		assert ValueUtil.isBoxed(exceptValue);
		if (((exceptValue == null) && !symbolicValue.mayBeNull())
		 || ((exceptValue instanceof InvalidValue) && !symbolicValue.mayBeInvalid())
		 || (ValueUtil.ZERO_VALUE.equals(exceptValue) && !symbolicValue.mayBeZero())
		 || (ValueUtil.FALSE_VALUE.equals(exceptValue) && !symbolicValue.mayBeFalse())
		 || (ValueUtil.TRUE_VALUE.equals(exceptValue) && !symbolicValue.mayBeTrue())) {
			return symbolicValue;
		}
		else {
			return new SymbolicExceptValue(symbolicValue, exceptValue);
		}
	}

	public static @NonNull SymbolicValue createIsZeroValue(@NonNull SymbolicValue symbolicValue) {
		if (!symbolicValue.isZero()) {
			return new SymbolicIsZeroValue(symbolicValue);
		}
		else {
			return symbolicValue;
		}
	}

	public static @NonNull SymbolicValue createNotValue(@NonNull SymbolicValue symbolicValue) {
		return new SymbolicNotValue(symbolicValue);
	}

	public static @NonNull SymbolicValue createNullFreeValue(@NonNull SymbolicValue symbolicValue) {
		if (!symbolicValue.isNullFree()) {
			return new SymbolicNullFreeValue(symbolicValue);
		}
		else {
			return symbolicValue;
		}
	}

	public static SymbolicRefinedContentValue createRefinedContent(@NonNull SymbolicValue symbolicValue) {
		return new SymbolicRefinedContentValue(symbolicValue);
	}

	public static @NonNull SymbolicValue createSizeValue(@NonNull SymbolicValue symbolicValue) {
		return new SymbolicSizeValue(symbolicValue);
	}

	public static @NonNull SymbolicValue createSmallerThanValue(@NonNull SymbolicValue symbolicValue, @NonNull SymbolicValue minSizeValue) {
		throw new UnsupportedOperationException();
	}

	protected final @NonNull SymbolicValue value;

	protected AbstractSymbolicRefinedValue(@NonNull SymbolicValue value) {
		this.value = value;
		assert !value.isDead();
	}

	@Override
	public @NonNull SymbolicValue asRefinementOf(@NonNull SymbolicValue unrefinedValue) {
	//	@NonNull SymbolicValue thisBaseValue = this.getBaseValue();
		@NonNull SymbolicValue resultValue = unrefinedValue.getBaseValue();
		if (!mayBeInvalid()) {// && unrefinedValue.mayBeInvalid()) {
			resultValue = createExceptValue(resultValue, ValueUtil.INVALID_VALUE);
		}
		if (!mayBeNull()) {// && unrefinedValue.mayBeNull()) {
			resultValue = createExceptValue(resultValue, null);
		}
	//	if ((basicGetZeroStatus() != null) && !mayBeZero() && (unrefinedValue.basicGetZeroStatus() != null) && unrefinedValue.mayBeZero()) {
		if (!mayBeZero()) {// && (unrefinedValue.basicGetZeroStatus() != null) && unrefinedValue.mayBeZero()) {
			resultValue = createNotValue(createIsZeroValue(resultValue));
		}
		return resultValue;
	}

	@Override
	public @Nullable SymbolicStatus basicGetBooleanStatus() {
		return value.basicGetBooleanStatus();
	}

	@Override
	public @Nullable SymbolicStatus basicGetInvalidStatus() {
		return value.basicGetInvalidStatus();
	}

	@Override
	public @Nullable SymbolicStatus basicGetNullStatus() {
		return value.basicGetNullStatus();
	}

	@Override
	public @Nullable SymbolicStatus basicGetZeroStatus() {
		return value.basicGetZeroStatus();
	}

	@Override
	public @NonNull SymbolicValue getBaseValue() {
		return value.getBaseValue();
	}

	@Override
	public @NonNull SymbolicCollectionContent getCollectionContent() {
		return value.getCollectionContent();
	}

	@Override
	public @NonNull SymbolicContent getContent() {
		return value.getContent();
	}

	@Override
	public @NonNull SymbolicStatus getDeadStatus() {
		return value.getDeadStatus();
	}

	@Override
	public @NonNull SymbolicMapContent getMapContent() {
		return value.getMapContent();
	}

	@Override
	public @NonNull TypeId getTypeId() {
		return value.getTypeId();
	}

	@Override
	public @Nullable Object getKnownValue() {
		return value.getKnownValue();
	}

	@Override
	public boolean isCollection() {
		return value.isCollection();
	}

	@Override
	public boolean isKnown() {
		return value.isKnown();
	}

	@Override
	public boolean isMap() {
		return value.isMap();
	}

	@Override
	public boolean isNullFree() {
		return value.isNullFree();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {
		ValueUtil.toString(value, s, sizeLimit);
	}
}
