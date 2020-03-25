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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.values.SymbolicValue;
import org.eclipse.ocl.pivot.values.ValuesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Symbolic Value</b></em>'.
 * @since 1.12
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class SymbolicValueImpl extends ValueImpl implements SymbolicValue {
	/**
	 * The number of structural features of the '<em>Symbolic Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYMBOLIC_VALUE_FEATURE_COUNT = ValueImpl.VALUE_FEATURE_COUNT + 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public SymbolicValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ValuesPackage.Literals.SYMBOLIC_VALUE;
	}

	@Override
	public @NonNull Object asObject() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull TypeId getTypeId() {
		return getTypeId2();
	}

	@Override
	public @NonNull TypeId getTypeId2() {
		throw new UnsupportedOperationException();
	}
} //SymbolicValueImpl
