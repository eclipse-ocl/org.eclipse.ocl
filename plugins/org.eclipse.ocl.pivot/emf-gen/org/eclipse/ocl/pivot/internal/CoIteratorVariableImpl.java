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
package org.eclipse.ocl.pivot.internal;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.jdt.annotation.NonNull;

import org.eclipse.ocl.pivot.CoIteratorVariable;
import org.eclipse.ocl.pivot.PivotPackage;

import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Co Iterator Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class CoIteratorVariableImpl extends VariableImpl implements CoIteratorVariable
{
	/**
	 * The number of structural features of the '<em>Co Iterator Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CO_ITERATOR_VARIABLE_FEATURE_COUNT = VariableImpl.VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Co Iterator Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CO_ITERATOR_VARIABLE_OPERATION_COUNT = VariableImpl.VARIABLE_OPERATION_COUNT + 0;


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CoIteratorVariableImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PivotPackage.Literals.CO_ITERATOR_VARIABLE;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitCoIteratorVariable(this);
	}

} //CoIteratorVariableImpl
