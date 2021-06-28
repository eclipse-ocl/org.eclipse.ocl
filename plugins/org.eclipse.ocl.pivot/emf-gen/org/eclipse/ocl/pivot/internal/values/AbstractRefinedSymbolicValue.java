/**
 * Copyright (c) 2010, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal.values;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicCollectionContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicMapContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicStatus;
import org.eclipse.ocl.pivot.values.RefinedSymbolicValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Symbolic Value</b></em>'.
 * @since 1.16
 * <!-- end-user-doc -->
 *
 * @generated NOT
 */
public abstract class AbstractRefinedSymbolicValue extends AbstractSymbolicValue implements RefinedSymbolicValue
{
	private static class IsDeadSymbolicValue extends AbstractRefinedSymbolicValue
	{
		public IsDeadSymbolicValue(@NonNull SymbolicValue value) {
			super(value);
			assert !(value instanceof RefinedSymbolicValue);
		}

		@Override
		public @NonNull SymbolicStatus basicGetBooleanStatus() {
			return getDeadStatus();
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
		public @NonNull SymbolicStatus getInvalidStatus() {
			return SymbolicStatus.UNDECIDED;
		}

		@Override
		public @NonNull SymbolicStatus getNullStatus() {
			return SymbolicStatus.UNDECIDED;
		}

		@Override
		public boolean isFalse() {
			return false;
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
		public boolean isTrue() {
			return false;
		}

		@Override
		public boolean mayBeFalse() {
			return false;
		}

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
		public boolean mayBeTrue() {
			return false;
		}

		@Override
		public boolean mayBeZero() {
			return false;
		}

		@Override
		public @NonNull String toString() {
			return "%IsDead(" + value.toString() + ")";
		}
	}

	private static class IsZeroSymbolicValue extends AbstractRefinedSymbolicValue
	{
		public IsZeroSymbolicValue(@NonNull SymbolicValue value) {
			super(value);
		}

		@Override
		public @NonNull SymbolicStatus basicGetBooleanStatus() {
			return basicGetZeroStatus();
		}

		@Override
		public @NonNull SymbolicStatus basicGetZeroStatus() {
			return SymbolicStatus.SATISFIED;
		}

		@Override
		public @NonNull String toString() {
			return "%IsZero(" + value.toString() + ")";
		}
	}

	private static class NotInvalidSymbolicValue extends AbstractRefinedSymbolicValue
	{
		public NotInvalidSymbolicValue(@NonNull SymbolicValue value) {
			super(value);
		}

		@Override
		public @NonNull SymbolicStatus basicGetBooleanStatus() {
			return getInvalidStatus();
		}

		@Override
		public @NonNull SymbolicStatus getInvalidStatus() {
			return SymbolicStatus.UNSATISFIED;
		}

		@Override
		public @NonNull String toString() {
			return "%NotInvalid(" + value.toString() + ")";
		}
	}

	private static class NotNullSymbolicValue extends AbstractRefinedSymbolicValue
	{
		public NotNullSymbolicValue(@NonNull SymbolicValue value) {
			super(value);
		}

		@Override
		public @NonNull SymbolicStatus basicGetBooleanStatus() {
			return getNullStatus();
		}

		@Override
		public @NonNull SymbolicStatus getNullStatus() {
			return SymbolicStatus.UNSATISFIED;
		}

		@Override
		public @NonNull String toString() {
			return "%NotNull(" + value.toString() + ")";
		}
	}

	private static class NotSymbolicValue extends AbstractRefinedSymbolicValue
	{
		public NotSymbolicValue(@NonNull SymbolicValue value) {
			super(value);
		}

		@Override
		public @NonNull SymbolicStatus basicGetBooleanStatus() {
			return value.getBooleanStatus().not();		// not super to avoid recursion
		}

		@Override
		public @NonNull SymbolicStatus basicGetZeroStatus() {
			return value.getZeroStatus().not();			// not super to avoid recursion
		}

	//	@Override
	//	public @NonNull SymbolicStatus getNullStatus() {
	//		return super.getNullStatus().not();
	//	}

	//	@Override
	//	public @NonNull SymbolicStatus getZeroStatus() {
	//		return super.getZeroStatus().not();
	//	}

//		@Override
//		public boolean isFalse() {
//			return !value.mayBeTrue();
//		}

//		@Override
//		public boolean isTrue() {
//			return !value.mayBeFalse();
//		}

		@Override
		public boolean mayBeFalse() {
			return !value.isTrue();
		}

		@Override
		public boolean mayBeTrue() {
			return !value.isFalse();
		}

		@Override
		public @NonNull String toString() {
			return "%Not(" + value.toString() + ")";
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

	private static class NullFreeSymbolicValue extends AbstractRefinedSymbolicValue
	{
		public NullFreeSymbolicValue(@NonNull SymbolicValue value) {
			super(value);
		}

		@Override
		public boolean isNullFree() {
			return true;
		}

		@Override
		public @NonNull String toString() {
			return "%NullFree(" + value.toString() + ")";
		}
	}

	public static class RefinedContentSymbolicValue extends AbstractRefinedSymbolicValue
	{
		private final @NonNull SymbolicContent content;

		public RefinedContentSymbolicValue(@NonNull SymbolicValue value) {
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
		public @NonNull String toString() {
			return "%Refined(" + value.toString() + ")";
		}
	}

	private static class SizeSymbolicValue extends AbstractRefinedSymbolicValue
	{
		public SizeSymbolicValue(@NonNull SymbolicValue value) {
			super(value);
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

	public static @NonNull SymbolicValue createIsDeadValue(SymbolicValue symbolicValue) {
		if (!symbolicValue.isDead()) {
			return new IsDeadSymbolicValue(symbolicValue.getBaseValue());
		}
		else {
			return symbolicValue;
		}
	}

/*	public static @NonNull SymbolicValue createIsEmptyValue(@NonNull SymbolicValue symbolicValue) {
		if (!symbolicValue.isEmpty()) {
			return new IsEmptySymbolicValue(symbolicValue);
		}
		else {
			return symbolicValue;
		}
	} */

	public static @NonNull SymbolicValue createIsZeroValue(@NonNull SymbolicValue symbolicValue) {
		if (!symbolicValue.isZero()) {
			return new IsZeroSymbolicValue(symbolicValue);
		}
		else {
			return symbolicValue;
		}
	}

	public static @NonNull SymbolicValue createNotInvalidValue(@NonNull SymbolicValue symbolicValue) {
		if (symbolicValue.mayBeInvalid()) {
			return new NotInvalidSymbolicValue(symbolicValue);
		}
		else {
			return symbolicValue;
		}
	}

	public static @NonNull SymbolicValue createNotInvalidOrNullValue(@NonNull SymbolicValue symbolicValue) {
		if (symbolicValue.mayBeInvalid()) {
			return new NotInvalidSymbolicValue(symbolicValue);
		}
		else if (symbolicValue.mayBeNull()) {
			return new NotNullSymbolicValue(symbolicValue);
		}
		else {
			return symbolicValue;
		}
	}

	public static @NonNull SymbolicValue createNotNullValue(@NonNull SymbolicValue symbolicValue) {
		if (symbolicValue.mayBeNull()) {
			return new NotNullSymbolicValue(symbolicValue);
		}
		else {
			return symbolicValue;
		}
	}

/*	public static @NonNull SymbolicValue createNotSmallerThanValue(@NonNull SymbolicValue symbolicValue, @NonNull SymbolicValue minSizeValue) {
		if (symbolicValue.mayBeSmallerThan(minSizeValue)) {
			return new NotSmallerThanSymbolicValue(symbolicValue, minSizeValue);
		}
		else {
			return symbolicValue;
		}
	} */

	public static @NonNull SymbolicValue createNotValue(@NonNull SymbolicValue symbolicValue) {
		return new NotSymbolicValue(symbolicValue);
	}

	public static @NonNull SymbolicValue createNullFreeValue(@NonNull SymbolicValue symbolicValue) {
		if (!symbolicValue.isNullFree()) {
			return new NullFreeSymbolicValue(symbolicValue);
		}
		else {
			return symbolicValue;
		}
	}

	public static RefinedContentSymbolicValue createRefinedContent(@NonNull SymbolicValue symbolicValue) {
		return new RefinedContentSymbolicValue(symbolicValue);
	}

	public static @NonNull SymbolicValue createSizeValue(@NonNull SymbolicValue symbolicValue) {
		return new SizeSymbolicValue(symbolicValue);
	}

	public static @NonNull SymbolicValue createSmallerThanValue(@NonNull SymbolicValue symbolicValue, @NonNull SymbolicValue minSizeValue) {
		throw new UnsupportedOperationException();
	}

//	public static @NonNull SymbolicValue createZeroValue(@NonNull SymbolicValue symbolicValue) {
//		if (!symbolicValue.isZero()) {
//			symbolicValue = new ZeroSymbolicValue(symbolicValue);
//		}
//		return symbolicValue;
//	}

	protected final @NonNull SymbolicValue value;

	protected AbstractRefinedSymbolicValue(@NonNull SymbolicValue value) {
		this.value = value;
		assert !value.isDead();
	}

	@Override
	public @NonNull Object asObject() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull SymbolicValue asRefinementOf(@NonNull SymbolicValue unrefinedValue) {
	//	@NonNull SymbolicValue thisBaseValue = this.getBaseValue();
		@NonNull SymbolicValue resultValue = unrefinedValue.getBaseValue();
		if (!mayBeInvalid() && unrefinedValue.mayBeInvalid()) {
			resultValue = createNotInvalidValue(resultValue);
		}
		if (!mayBeNull() && unrefinedValue.mayBeNull()) {
			resultValue = createNotNullValue(resultValue);
		}
		if ((basicGetZeroStatus() != null) && !mayBeZero() && (unrefinedValue.basicGetZeroStatus() != null) && unrefinedValue.mayBeZero()) {
			resultValue = createNotValue(createIsZeroValue(resultValue));
		}
		return resultValue;
	}

	@Override
	public @Nullable SymbolicStatus basicGetBooleanStatus() {
		return value.basicGetBooleanStatus();
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
	public @NonNull SymbolicStatus getInvalidStatus() {
		return value.getInvalidStatus();
	}

	@Override
	public @NonNull SymbolicMapContent getMapContent() {
		return value.getMapContent();
	}

	@Override
	public @NonNull SymbolicStatus getNullStatus() {
		return value.getNullStatus();
	}

	@Override
	public @NonNull TypeId getTypeId() {
		return value.getTypeId();
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

//	@Override
//	public boolean isSmallerThan(@NonNull SymbolicValue minSizeValue) {
//		return value.isSmallerThan(minSizeValue);
//	}

	@Override
	public boolean mayBeFalse() {
		return value.mayBeFalse();
	}

//	@Override
//	public boolean mayBeSmallerThan(@NonNull SymbolicValue minSizeValue) {
//		return value.mayBeSmallerThan(minSizeValue);
//	}

	@Override
	public boolean mayBeTrue() {
		return value.mayBeTrue();
	}
}
