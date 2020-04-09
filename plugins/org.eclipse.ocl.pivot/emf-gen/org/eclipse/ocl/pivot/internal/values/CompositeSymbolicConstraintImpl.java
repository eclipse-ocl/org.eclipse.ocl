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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.values.SymbolicConstraint;
import org.eclipse.ocl.pivot.values.SymbolicOperator;

/**
 * @since 1.12
 */
public class CompositeSymbolicConstraintImpl extends SymbolicUnknownValueImpl implements SymbolicConstraint
{
	protected final @NonNull List<@NonNull SymbolicConstraint> symbolicConstraints = new ArrayList<>();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public CompositeSymbolicConstraintImpl() {
		super(TypeId.BOOLEAN, false, false);		// FIXME inherited maBeInvalid/Null redundant
	}

	public void add(@NonNull SymbolicConstraint symbolicConstraint) {
		assert symbolicConstraint.getTypeId2() == typeId;
		assert symbolicConstraint instanceof SymbolicConstraintImpl;
		assert !symbolicConstraints.contains(symbolicConstraint);
		symbolicConstraints.add(symbolicConstraint);
	}

	@Override
	public @Nullable SymbolicOperator getSymbolicOperator() {
		return null;
	}

	@Override
	public @Nullable Object getSymbolicValue() {
		return null;
	}

	@Override
	public boolean mayBeInvalid() {
		for (@NonNull SymbolicConstraint symbolicConstraint : symbolicConstraints) {
			if (symbolicConstraint.mayBeInvalid()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean mayBeNull() {
		for (@NonNull SymbolicConstraint symbolicConstraint : symbolicConstraints) {
			if (symbolicConstraint.mayBeNull()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public /*final*/ @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 100);
		return s.toString();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int lengthLimit) {
		s.append("{");
		boolean isFirst = true;
		for (@NonNull SymbolicConstraint symbolicConstraint : symbolicConstraints) {
			if (!isFirst) {
				s.append(",");
			}
			s.append(symbolicConstraint);
			isFirst = false;
		}
		s.append("}");
	}
}
