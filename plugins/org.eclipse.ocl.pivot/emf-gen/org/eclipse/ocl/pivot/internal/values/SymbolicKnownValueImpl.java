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
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicStatus;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.SymbolicKnownValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Symbolic Value</b></em>'.
 * @since 1.16
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class SymbolicKnownValueImpl extends SymbolicValueImpl implements SymbolicKnownValue {

	private @Nullable Object knownValue;

	public SymbolicKnownValueImpl(@NonNull String name, @NonNull TypeId typeId, @Nullable Object knownValue) { //, @NonNull SymbolicValue value) {
		super(name, typeId, ValueUtil.mayBeNull(knownValue), ValueUtil.mayBeInvalid(knownValue));
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
	public @NonNull SymbolicStatus basicGetZeroStatus() {
		return ValueUtil.ZERO_VALUE.equals(knownValue) ? SymbolicStatus.SATISFIED : SymbolicStatus.UNSATISFIED;
	}

	@Override
	public @Nullable Object getValue() {
		return knownValue;
	}

	@Override
	public boolean isFalse() {
		return knownValue == Boolean.FALSE;
	}

	@Override
	public boolean isKnown() {
		return true;
	}

	@Override
	public boolean isTrue() {
		return knownValue == Boolean.TRUE;
	}

//	@Override
//	public boolean mayBeNull() {
//		boolean isRequired = variable.isIsRequired();
//		boolean mayBeNull = value.mayBeNull();
//		assert !isRequired || !mayBeNull;
//		return mayBeNull;
//	}

	@Override
	public void toString(@NonNull StringBuilder s, int lengthLimit) {
		s.append(knownValue);
		s.append(" for: ");
		super.toString(s, lengthLimit);
	}
} //SymbolicValueImpl
