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

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.library.LibraryIterationOrOperation;
import org.eclipse.ocl.pivot.library.LibraryProperty;

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
	@Nullable SymbolicValue basicGetChildSymbolicValue(@NonNull LibraryIterationOrOperation libraryCallable, @NonNull List<@NonNull SymbolicValue> argumentSymbolicValues);
	@Nullable SymbolicValue basicGetChildSymbolicValue(@NonNull LibraryProperty libraryProperty);

	/**
	 * Update symbolicExecutor from any deductions that can be made from knowing that symbolicConstraint is observede.
	 */
//	void deduceFrom(@NonNull SymbolicExecutor symbolicExecutor, @NonNull SimpleSymbolicConstraint symbolicConstraint);

	boolean isFalse();

//	@Override
//	boolean isInvalid();

	boolean isKnown();

	boolean isNull();

	boolean isTrue();

	boolean isZero();

	@Nullable SymbolicValue putChildSymbolicValue(@NonNull LibraryIterationOrOperation libraryCallable, @NonNull List<@NonNull SymbolicValue> argumentSymbolicValues, @NonNull SymbolicValue childSymbolicValue);
	@Nullable SymbolicValue putChildSymbolicValue(@NonNull LibraryProperty libraryProperty, @NonNull SymbolicValue childSymbolicValue);

	@NonNull SymbolicValue setIsNullFree();

} // SymbolicValue
