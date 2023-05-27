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
package org.eclipse.ocl.pivot.internal;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.ocl.pivot.OclComparable;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.SelfType;

import org.eclipse.ocl.pivot.internal.utilities.PivotObjectImpl;

import org.eclipse.ocl.pivot.values.IntegerValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ocl Comparable</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class OclComparableImpl extends PivotObjectImpl implements OclComparable
{
	/**
	 * The number of structural features of the '<em>Ocl Comparable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OCL_COMPARABLE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Ocl Comparable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OCL_COMPARABLE_OPERATION_COUNT = 1;


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OclComparableImpl()
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
		return PivotPackage.Literals.OCL_COMPARABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IntegerValue compareTo(final SelfType that)
	{
		throw new UnsupportedOperationException();  // FIXME Unimplemented http://www.eclipse.org/ocl/2015/Pivot!OclComparable!compareTo(http://www.eclipse.org/ocl/2015/Pivot!OclSelf)
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException
	{
		switch (operationID)
		{
			case 0:
				return compareTo((SelfType)arguments.get(0));
		}
		return eDynamicInvoke(operationID, arguments);
	}


} //OclComparableImpl
