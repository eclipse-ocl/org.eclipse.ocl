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
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.SimpleSymbolicConstraint;
import org.eclipse.ocl.pivot.values.SymbolicOperator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Symbolic Value</b></em>'.
 * @since 1.15
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class SimpleSymbolicConstraintImpl extends SymbolicConstraintImpl implements SimpleSymbolicConstraint
{
	protected final @NonNull SymbolicOperator symbolicOperator;
	protected final @Nullable Object symbolicValue;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public SimpleSymbolicConstraintImpl(@NonNull TypeId typeId, boolean mayBeNull, boolean mayBeInvalid, @NonNull SymbolicOperator symbolicOperator, @Nullable Object symbolicValue) {
		super(typeId, mayBeNull, mayBeInvalid);
		this.symbolicOperator = symbolicOperator;
		this.symbolicValue = symbolicValue;
	}

	@Override
	public @NonNull SymbolicOperator getSymbolicOperator() {
		return symbolicOperator;
	}

	@Override
	public @Nullable Object getSymbolicValue() {
		return symbolicValue;
	}

	@Override
	public boolean mayBeInvalid() {
		return ValueUtil.mayBeInvalid(symbolicValue);
	}

	@Override
	public boolean mayBeNull() {
		switch (symbolicOperator) {
			case EQUALS: return ValueUtil.mayBeNull(symbolicValue);
			case NOT_EQUALS: return !ValueUtil.mayBeNull(symbolicValue);
			case COMPARE_TO: return false;
		}
		throw new UnsupportedOperationException(symbolicOperator.toString());
	}

	@Override
	public /*final*/ @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 100);
		return s.toString();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int lengthLimit) {
		s.append(symbolicOperator);
		s.append("-");
		s.append(symbolicValue);
	}
}
