/*******************************************************************************
 * Copyright (c) 2017, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.analysis;

import org.eclipse.jdt.annotation.NonNull;

/**
 * KnownValue defines the interface to the results of a static partial evaluation.
 * @since 1.12
 */
public class AbstractSymbolicValue implements SymbolicValue
{
	public static @NonNull SymbolicValue getUnknownValue(boolean maybeNull, boolean maybeInvalid) {
		if (maybeNull) {
			return maybeInvalid ? UNKNOWN_MAYBE_NULL_MAYBE_INVALID : UNKNOWN_MAYBE_NULL_NOT_INVALID;
		}
		else {
			return maybeInvalid ? UNKNOWN_NOT_NULL_MAYBE_INVALID : UNKNOWN_NOT_NULL_NOT_INVALID;
		}
	}

	@Override
	public boolean isInvalid() { return false; }

	@Override
	public boolean isKnown() { return false; }

	@Override
	public boolean isNonInvalid() { return false; }

	@Override
	public boolean isNonNull() { return false; }

	@Override
	public boolean isNull() { return false; }

	@Override
	public final boolean maybeInvalid() {
		return !isNonInvalid();
	}

	@Override
	public final boolean maybeNull() {
		return !isNonNull();
	}

	@Override
	public @NonNull SymbolicValue setNonNullValue() {
		return this;
	}

	@Override
	public @NonNull SymbolicValue union(@NonNull SymbolicValue thatValue) {
		if (this == thatValue) {
			return this;
		}
		if (isInvalid() || thatValue.isInvalid()) {
			return KNOWN_INVALID;
		}
		if (isKnown() && !isNull() && thatValue.isKnown() && !thatValue.isNull()) {
			return KNOWN_NOT_NULL_LITERALS;
		}
		boolean maybeNull = !isNonNull() || !thatValue.isNonNull();
		boolean maybeInvalid = !isNonInvalid() || !thatValue.isNonInvalid();
		return getUnknownValue(maybeNull, maybeInvalid);
	}

	public static class KnownBooleanValue extends KnownLiteralsValue implements SymbolicBooleanValue
	{
		private boolean isTrue;

		public KnownBooleanValue(boolean isTrue) {
			this.isTrue = isTrue;
		}

		@Override
		public boolean isFalse() { return !isTrue; }

		@Override
		public boolean isTrue() { return isTrue; }

		@Override
		public @NonNull String toString() {
			return "KNOWN-" + (isTrue ? "TRUE" : "FALSE");
		}
	}

	public static class KnownInvalidValue extends AbstractSymbolicValue
	{
		@Override
		public boolean isInvalid() { return true; }

		@Override
		public boolean isKnown() { return true; }

		@Override
		public boolean isNonNull() { return true; }

		@Override
		public @NonNull String toString() {
			return "KNOWN-INVALID";
		}
	}

	public static class KnownLiteralsValue extends AbstractSymbolicValue
	{
		//		private @NonNull LiteralExp literalExp;

		//		public KnownLiteralValue(@NonNull LiteralExp literalExp) {
		//			this.literalExp = literalExp;
		//			assert !(literalExp instanceof BooleanLiteralExp) &&  !(literalExp instanceof InvalidLiteralExp) && !(literalExp instanceof NullLiteralExp);
		//		}
		@Override
		public boolean isKnown() { return true; }

		@Override
		public boolean isNonInvalid() { return true; }

		@Override
		public boolean isNonNull() { return true; }

		@Override
		public @NonNull String toString() {
			return "KNOWN-LITERALS";
		}
	}

	public static class KnownNullValue extends AbstractSymbolicValue
	{
		@Override
		public boolean isNonInvalid() { return true; }

		@Override
		public boolean isKnown() { return true; }

		@Override
		public boolean isNull() { return true; }

		@Override
		public @NonNull String toString() {
			return "KNOWN-NULL";
		}
	}

	public static class UnknownValue extends AbstractSymbolicValue
	{
		private final boolean nonNull;
		private final boolean nonInvalid;

		public UnknownValue(boolean nonNull, boolean nonInvalid) {
			this.nonNull = nonNull;
			this.nonInvalid = nonInvalid;
		}

		@Override
		public boolean isNonInvalid() { return nonInvalid; }

		@Override
		public boolean isNonNull() { return nonNull; }

		@Override
		public @NonNull SymbolicValue setNonNullValue() {
			return getUnknownValue(false, maybeInvalid());
		}

		@Override
		public @NonNull String toString() {
			return "UNKNOWN," + (nonNull ? "not" : "maybe") + "NULL," + (nonInvalid ? "not" : "maybe") + "INVALID";
		}
	}
}
