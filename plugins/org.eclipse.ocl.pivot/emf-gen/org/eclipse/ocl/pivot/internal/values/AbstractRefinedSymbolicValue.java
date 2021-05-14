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
import org.eclipse.ocl.pivot.internal.evaluation.Hypothesis;
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
public abstract class AbstractRefinedSymbolicValue extends ValueImpl implements RefinedSymbolicValue
{
	protected final @NonNull Hypothesis hypothesis;
	protected final @NonNull SymbolicValue value;

	protected AbstractRefinedSymbolicValue(@NonNull Hypothesis hypothesis, @NonNull SymbolicValue value) {
		this.hypothesis = hypothesis;
		this.value = value;
	}

	@Override
	public @NonNull Object asObject() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull Hypothesis getHypothesis() {
		return hypothesis;
	}

	@Override
	public @NonNull TypeId getTypeId() {
		return value.getTypeId();
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
	public boolean isNull() {
		return value.isNull();
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

	@Override
	public @NonNull SymbolicValue setIsNullFree() {
		throw new UnsupportedOperationException();
	}
}
