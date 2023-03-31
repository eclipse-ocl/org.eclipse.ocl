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

import org.eclipse.ocl.ocl.NumericLiteralExp;
import org.eclipse.ocl.ocl.OCLASPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Numeric Literal Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class NumericLiteralExpImpl extends PrimitiveLiteralExpImpl implements NumericLiteralExp
{
	/**
	 * The number of structural features of the '<em>Numeric Literal Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NUMERIC_LITERAL_EXP_FEATURE_COUNT = PrimitiveLiteralExpImpl.PRIMITIVE_LITERAL_EXP_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Numeric Literal Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NUMERIC_LITERAL_EXP_OPERATION_COUNT = PrimitiveLiteralExpImpl.PRIMITIVE_LITERAL_EXP_OPERATION_COUNT + 0;


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NumericLiteralExpImpl()
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
		return OCLASPackage.Literals.NUMERIC_LITERAL_EXP;
	}


} //NumericLiteralExpImpl
