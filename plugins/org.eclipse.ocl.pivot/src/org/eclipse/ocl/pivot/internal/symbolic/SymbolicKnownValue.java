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
import org.eclipse.ocl.pivot.values.SymbolicValue;
import org.eclipse.ocl.pivot.values.Value;

/**
 * @since 1.16
 */
public class SymbolicKnownValue extends AbstractLeafSymbolicValue {

	private @Nullable Object knownValue;

	private static boolean mayBeInvalid(@Nullable Object value) {
		assert !(value instanceof SymbolicValue) : "SymbolValue is no longer a Value";
		if (value == null) {
			return false;
		}
		else if (value instanceof Value) {
			return ((Value)value).mayBeInvalid();
		}
		return false;
	}

	private static boolean mayBeNull(@Nullable Object value) {
		assert !(value instanceof SymbolicValue) : "SymbolValue is no longer a Value";
		if (value == null) {
			return true;
		}
		else if (value instanceof Value) {
			return ((Value)value).mayBeNull();
		}
		return false;
	}

	public SymbolicKnownValue(@NonNull String name, @NonNull TypeId typeId, @Nullable Object knownValue, @Nullable SymbolicContent content) { //, @NonNull SymbolicValue value) {
		super(name, typeId, mayBeNull(knownValue), mayBeInvalid(knownValue), content);
		this.knownValue = knownValue;
	}

	@Override
	public @Nullable SymbolicStatus basicGetBooleanStatus() {
		if (knownValue == ValueUtil.TRUE_VALUE) {
			return SymbolicStatus.SATISFIED;
		}
		else if (knownValue == ValueUtil.FALSE_VALUE) {
			return SymbolicStatus.UNSATISFIED;
		}
		return super.basicGetBooleanStatus();
	}

	@Override
	public @NonNull SymbolicStatus basicGetInvalidStatus() {
		return ValueUtil.isInvalidValue(knownValue) ? SymbolicStatus.SATISFIED : SymbolicStatus.UNSATISFIED;
	}

	@Override
	public @NonNull SymbolicStatus basicGetNullStatus() {
		return ValueUtil.isNullValue(knownValue) ? SymbolicStatus.SATISFIED : SymbolicStatus.UNSATISFIED;
	}

	@Override
	public @Nullable SymbolicStatus basicGetZeroStatus() {
		if (isNumeric()) {
			return ValueUtil.ZERO_VALUE.equals(knownValue) ? SymbolicStatus.SATISFIED : SymbolicStatus.UNSATISFIED;
		}
		return null;
	}

	@Override
	public @Nullable Object getKnownValue() {
		return knownValue;
	}

	@Override
	public boolean isKnown() {
		return true;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int lengthLimit) {
		ValueUtil.toString(knownValue, s, lengthLimit);
		s.append(" for: ");
		super.toString(s, lengthLimit);
	}
}
