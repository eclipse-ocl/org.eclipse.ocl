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
package org.eclipse.ocl.pivot.values;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Symbolic Value</b></em>'.
 * @since 1.15
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
public interface SymbolicOperationCallValue extends SymbolicExpressionValue
{
	@NonNull List<@Nullable Object> getBoxedSourceAndArgumentValues();
}
