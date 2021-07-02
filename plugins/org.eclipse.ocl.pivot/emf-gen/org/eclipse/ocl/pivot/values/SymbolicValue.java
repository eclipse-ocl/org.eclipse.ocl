/**
 * Copyright (c) 2010, 2021 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicCollectionContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicMapContent;
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

	@Nullable SymbolicStatus basicGetInvalidStatus();

	@Nullable SymbolicStatus basicGetNullStatus();

	@Nullable SymbolicStatus basicGetZeroStatus();

	/**
	 * Update symbolicExecutor from any deductions that can be made from knowing that symbolicConstraint is observede.
	 */
//	void deduceFrom(@NonNull SymbolicExecutor symbolicExecutor, @NonNull SimpleSymbolicConstraint symbolicConstraint);

	@NonNull SymbolicValue getBaseValue();

	@NonNull SymbolicStatus getBooleanStatus();

	@NonNull SymbolicCollectionContent getCollectionContent();

	@NonNull SymbolicContent getContent();

	@NonNull SymbolicStatus getDeadStatus();

	@NonNull SymbolicStatus getInvalidStatus();

	@NonNull SymbolicStatus getNullStatus();

	@NonNull SymbolicMapContent getMapContent();

	@NonNull SymbolicStatus getZeroStatus();

	boolean isCollection();

	boolean isDead();

	boolean isFalse();

//	@Override
//	boolean isInvalid();

	boolean isKnown();

	boolean isMap();

	boolean isNull();

	boolean isNullFree();

	boolean isRefinementOf(@NonNull SymbolicValue unrefinedValue);

//	boolean isSmallerThan(@NonNull SymbolicValue minSizeValue);

	boolean isTrue();

	boolean isZero();

	boolean mayBeFalse();

//	boolean mayBeSmallerThan(@NonNull SymbolicValue minSizeValue);

	boolean mayBeTrue();

} // SymbolicValue
