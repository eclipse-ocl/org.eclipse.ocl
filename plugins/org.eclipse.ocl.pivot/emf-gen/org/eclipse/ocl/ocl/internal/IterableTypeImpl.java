/**
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.ocl.internal;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.ocl.ocl.IterableType;
import org.eclipse.ocl.ocl.OCLASPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Iterable Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class IterableTypeImpl extends DataTypeImpl implements IterableType
{
	/**
	 * The number of structural features of the '<em>Iterable Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERABLE_TYPE_FEATURE_COUNT = DataTypeImpl.DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Iterable Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERABLE_TYPE_OPERATION_COUNT = DataTypeImpl.DATA_TYPE_OPERATION_COUNT + 0;


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IterableTypeImpl()
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
		return OCLASPackage.Literals.ITERABLE_TYPE;
	}


} //IterableTypeImpl
