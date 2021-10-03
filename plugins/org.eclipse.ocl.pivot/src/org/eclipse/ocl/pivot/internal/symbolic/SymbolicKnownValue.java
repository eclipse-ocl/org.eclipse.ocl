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
import org.eclipse.ocl.pivot.values.NumberValue;

/**
 * @since 1.17
 */
public class SymbolicKnownValue extends AbstractLeafSymbolicValue {

	private @Nullable Object knownValue;

	public SymbolicKnownValue(@NonNull String name, @NonNull TypeId typeId, @Nullable Object knownValue, @Nullable SymbolicContent content) { //, @NonNull SymbolicValue value) {
		super(name, typeId, SymbolicUtil.mayBeNullReason(knownValue), SymbolicUtil.mayBeInvalidReason(knownValue), content);
		this.knownValue = knownValue;
	}

	@Override
	public @Nullable SymbolicSimpleStatus basicGetBooleanStatus() {
		if (knownValue == ValueUtil.TRUE_VALUE) {
			return SymbolicSimpleStatus.SATISFIED;
		}
		else if (knownValue == ValueUtil.FALSE_VALUE) {
			return SymbolicSimpleStatus.UNSATISFIED;
		}
		return super.basicGetBooleanStatus();
	}

	@Override
	public @NonNull SymbolicSimpleStatus basicGetInvalidStatus() {
		return ValueUtil.isInvalidValue(knownValue) ? SymbolicSimpleStatus.SATISFIED : SymbolicSimpleStatus.UNSATISFIED;
	}

	@Override
	public @NonNull SymbolicSimpleStatus basicGetNullStatus() {
		return ValueUtil.isNullValue(knownValue) ? SymbolicSimpleStatus.SATISFIED : SymbolicSimpleStatus.UNSATISFIED;
	}

	@Override
	public @Nullable SymbolicNumericValue basicGetNumericValue() {
		if (isNumeric()) {
			assert knownValue != null;
			return SymbolicNumericValue.get((NumberValue)knownValue, (NumberValue)knownValue);
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
	public void toString(@NonNull StringBuilder s) {
		super.toString(s);
		s.append(" = ");
		ValueUtil.toString(knownValue, s, 100);
	}
}
