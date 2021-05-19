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
		public boolean mayBeZero() {
			return false;
		}

		@Override
		public @NonNull String toString() {
			return "%IsDead";
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

	public static @NonNull SymbolicValue createIsDeadValue(SymbolicValue symbolicValue) {
		if (symbolicValue.isDead()) {
			return symbolicValue;
		}
		else {
			return new IsDeadSymbolicValue(symbolicValue.getBaseValue());
		}
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
	public boolean isTrue() {
		return value.isTrue();
	}

	@Override
	public boolean isZero() {
		return value.isZero();
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
	public boolean mayBeZero() {
		return value.mayBeZero();
	}
}
