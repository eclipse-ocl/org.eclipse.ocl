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
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicCollectionContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicMapContent;
import org.eclipse.ocl.pivot.values.RefinedSymbolicValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Symbolic Value</b></em>'.
 * @since 1.15
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
		public boolean isDead() {
			return true;
		}

		@Override
		public boolean isFalse() {
			return false;
		}

		@Override
		public boolean isInvalid() {
			return false;
		}

		@Override
		public boolean isNull() {
			return false;
		}

		@Override
		public boolean isNullFree() {
			return false;
		}

		@Override
		public boolean isSmallerThan(@NonNull SymbolicValue minSizeValue) {
			return false;
		}

		@Override
		public boolean isTrue() {
			return false;
		}

		@Override
		public boolean isZero() {
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

		@Override
		public boolean mayBeSmallerThan(@NonNull SymbolicValue minSizeValue) {
			return false;
		}

		@Override
		public boolean mayBeZero() {
			return false;
		}

		@Override
		public @NonNull String toString() {
			return "%IsDead";
		}
	}

	private static class IsEmptySymbolicValue extends AbstractRefinedSymbolicValue
	{
		public IsEmptySymbolicValue(@NonNull SymbolicValue value) {
			super(value);
		}

		@Override
		public boolean isEmpty() {
			return true;
		}

		@Override
		public boolean mayBeEmpty() {
			return true;
		}

		@Override
		public @NonNull String toString() {
			return "%IsEmpty(" + value.toString() + ")";
		}
	}

	private static class IsZeroSymbolicValue extends AbstractRefinedSymbolicValue
	{
		public IsZeroSymbolicValue(@NonNull SymbolicValue value) {
			super(value);
		}

		@Override
		public boolean isZero() {
			return true;
		}

		@Override
		public boolean mayBeZero() {
			return true;
		}

		@Override
		public @NonNull String toString() {
			return "%IsZero(" + value.toString() + ")";
		}
	}

	private static class NotEmptySymbolicValue extends AbstractRefinedSymbolicValue
	{
		public NotEmptySymbolicValue(@NonNull SymbolicValue value) {
			super(value);
		}

		@Override
		public boolean isEmpty() {
			return false;
		}

		@Override
		public boolean mayBeEmpty() {
			return false;
		}

		@Override
		public @NonNull String toString() {
			return "%NotEmpty(" + value.toString() + ")";
		}
	}

	private static class NotInvalidSymbolicValue extends AbstractRefinedSymbolicValue
	{
		public NotInvalidSymbolicValue(@NonNull SymbolicValue value) {
			super(value);
		}

		@Override
		public boolean isInvalid() {
			return false;
		}

		@Override
		public boolean mayBeInvalid() {
			return false;
		}

		@Override
		public @NonNull String toString() {
			return "%NotInvalid(" + value.toString() + ")";
		}
	}

/*	private static class NotInvalidOrNullSymbolicValue extends AbstractRefinedSymbolicValue
	{
		public NotInvalidOrNullSymbolicValue(@NonNull SymbolicValue value) {
			super(value);
		}

		@Override
		public boolean isInvalid() {
			return false;
		}

		@Override
		public boolean isNull() {
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

		@Override
		public @NonNull String toString() {
			return "%NotInvalidOrNull(" + value.toString() + ")";
		}
	} */

	private static class NotNullSymbolicValue extends AbstractRefinedSymbolicValue
	{
		public NotNullSymbolicValue(@NonNull SymbolicValue value) {
			super(value);
		}

		@Override
		public boolean isNull() {
			return false;
		}

		@Override
		public boolean mayBeNull() {
			return false;
		}

		@Override
		public @NonNull String toString() {
			return "%NotNull(" + value.toString() + ")";
		}
	}

	private static class NotZeroSymbolicValue extends AbstractRefinedSymbolicValue
	{
		public NotZeroSymbolicValue(@NonNull SymbolicValue value) {
			super(value);
		}

		@Override
		public boolean isZero() {
			return false;
		}

		@Override
		public boolean mayBeZero() {
			return false;
		}

		@Override
		public @NonNull String toString() {
			return "%NotZero(" + value.toString() + ")";
		}
	}

	private static class NotSmallerThanSymbolicValue extends AbstractRefinedSymbolicValue
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
	}

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

	private static class SizeSymbolicValue extends AbstractRefinedSymbolicValue
	{
		public SizeSymbolicValue(@NonNull SymbolicValue value) {
			super(value);
		}

	//	@Override
	//	public boolean isZero() {
	//		return true;
	//	}

	//	@Override
	//	public boolean mayBeZero() {
	//		return true;
	//	}

		@Override
		public @NonNull String toString() {
			return "%Size(" + value.toString() + ")";
		}
	}

	public static @NonNull SymbolicValue createIsDeadValue(SymbolicValue symbolicValue) {
		if (symbolicValue.isDead()) {
			return symbolicValue;
		}
		else {
			return new IsDeadSymbolicValue(symbolicValue.getBaseValue());
		}
	}

	public static @NonNull SymbolicValue createIsEmptyValue(@NonNull SymbolicValue symbolicValue) {
		if (symbolicValue.isEmpty()) {
			return symbolicValue;
		}
		else {
			return new IsEmptySymbolicValue(symbolicValue);
		}
	}

	public static @NonNull SymbolicValue createIsZeroValue(@NonNull SymbolicValue symbolicValue) {
		if (symbolicValue.isZero()) {
			return symbolicValue;
		}
		else {
			return new IsZeroSymbolicValue(symbolicValue);
		}
	}

	public static @NonNull SymbolicValue createNotEmptyValue(@NonNull SymbolicValue symbolicValue) {
	//	if (!symbolicValue.isEmpty()) {
	//		return symbolicValue;
	//	}
	//	else {
			return new NotEmptySymbolicValue(symbolicValue);
	//	}
	}

	public static @NonNull SymbolicValue createNotInvalidValue(@NonNull SymbolicValue symbolicValue) {
		if (!symbolicValue.mayBeInvalid()) {
			return symbolicValue;
		}
		else {
			return new NotInvalidSymbolicValue(symbolicValue);
		}
	}

	public static @NonNull SymbolicValue createNotInvalidOrNullValue(@NonNull SymbolicValue symbolicValue) {
		if (symbolicValue.mayBeInvalid()) {
			symbolicValue = new NotInvalidSymbolicValue(symbolicValue);
		}
		if (symbolicValue.mayBeNull()) {
			symbolicValue = new NotNullSymbolicValue(symbolicValue);
		}
		return symbolicValue;
	}

	public static @NonNull SymbolicValue createNotNullValue(@NonNull SymbolicValue symbolicValue) {
		if (symbolicValue.mayBeNull()) {
			symbolicValue = new NotNullSymbolicValue(symbolicValue);
		}
		return symbolicValue;
	}

	public static @NonNull SymbolicValue createNotSmallerThanValue(@NonNull SymbolicValue symbolicValue, @NonNull SymbolicValue minSizeValue) {
		if (symbolicValue.mayBeSmallerThan(minSizeValue)) {
			symbolicValue = new NotSmallerThanSymbolicValue(symbolicValue, minSizeValue);
		}
		return symbolicValue;
	}

	public static @NonNull SymbolicValue createNotZeroValue(@NonNull SymbolicValue symbolicValue) {
		if (symbolicValue.mayBeZero()) {
			symbolicValue = new NotZeroSymbolicValue(symbolicValue);
		}
		return symbolicValue;
	}

	public static @NonNull SymbolicValue createNullFreeValue(@NonNull SymbolicValue symbolicValue) {
		if (!symbolicValue.isNullFree()) {
			symbolicValue = new NullFreeSymbolicValue(symbolicValue);
		}
		return symbolicValue;
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
			resultValue = AbstractRefinedSymbolicValue.createNotInvalidValue(resultValue);
		}
		if (!mayBeNull() && unrefinedValue.mayBeNull()) {
			resultValue = AbstractRefinedSymbolicValue.createNotNullValue(resultValue);
		}
		if (!mayBeZero() && unrefinedValue.mayBeZero()) {
			resultValue = AbstractRefinedSymbolicValue.createNotZeroValue(resultValue);
		}
		return resultValue;
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
	public @NonNull SymbolicMapContent getMapContent() {
		return value.getMapContent();
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
	public boolean isDead() {
		return value.isDead();
	}

	@Override
	public boolean isEmpty() {
		return value.isEmpty();
	}

	@Override
	public boolean isFalse() {
		return value.isFalse();
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
	public boolean isNull() {
		return value.isNull();
	}

	@Override
	public boolean isNullFree() {
		return value.isNullFree();
	}

	@Override
	public boolean isSmallerThan(@NonNull SymbolicValue minSizeValue) {
		return value.isSmallerThan(minSizeValue);
	}

	@Override
	public boolean isTrue() {
		return value.isTrue();
	}

	@Override
	public boolean isZero() {
		return value.isZero();
	}

	@Override
	public boolean mayBeEmpty() {
		return value.mayBeEmpty();
	}

	@Override
	public boolean mayBeInvalid() {
		return value.mayBeInvalid();
	}

	@Override
	public boolean mayBeNull() {
		return value.mayBeNull();
	}

	@Override
	public boolean mayBeSmallerThan(@NonNull SymbolicValue minSizeValue) {
		return value.mayBeSmallerThan(minSizeValue);
	}

	@Override
	public boolean mayBeZero() {
		return value.mayBeZero();
	}
}
