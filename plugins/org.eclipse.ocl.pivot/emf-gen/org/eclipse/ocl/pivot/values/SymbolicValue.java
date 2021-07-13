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
package org.eclipse.ocl.pivot.values;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent.SymbolicCollectionContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent.SymbolicMapContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicStatus;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Symbolic Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Interface of a symbolic instance value.  OCL expressions that cannot be fully evaluated result in symbolic values. instances of this interface.
 * <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.ocl.pivot.values.ValuesPackage#getSymbolicValue()
 * @generated
 */
public interface SymbolicValue extends Value
{
	/**
	 * Return an equals SymbolicValue that is a refinement of unrefinedValue using unrefinedValue.getBaseValue();
	 *
	 * throws IllegalStateException if incompatoble.
	 */
	@NonNull SymbolicValue asRefinementOf(@NonNull SymbolicValue unrefinedValue);

	@Nullable SymbolicStatus basicGetBooleanStatus();

	// Returns null when invalid not applicable to e.g. a ZeroStatus
	@Nullable SymbolicStatus basicGetInvalidStatus();

	// Returns null when null not applicable to e.g. a ZeroStatus
	@Nullable SymbolicStatus basicGetNullStatus();

	@Nullable SymbolicStatus basicGetZeroStatus();

	@NonNull SymbolicValue getBaseValue();

	@NonNull SymbolicStatus getBooleanStatus();

	@NonNull SymbolicCollectionContent getCollectionContent();

	@NonNull SymbolicContent getContent();

	@NonNull SymbolicStatus getDeadStatus();

	@NonNull SymbolicStatus getInvalidStatus();

	@Nullable Object getKnownValue();

	@NonNull SymbolicStatus getNullStatus();

	@NonNull SymbolicMapContent getMapContent();

	@NonNull SymbolicStatus getZeroStatus();

	boolean isCollection();

	boolean isDead();

	boolean isFalse();

	boolean isKnown();

	boolean isMap();

	boolean isNull();

	boolean isNullFree();

	boolean isRefinementOf(@NonNull SymbolicValue unrefinedValue);

	boolean isTrue();

	boolean isZero();

	boolean mayBeFalse();

	boolean mayBeTrue();

} // SymbolicValue
