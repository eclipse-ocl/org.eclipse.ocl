/*******************************************************************************
 * Copyright (c) 2007, 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.ecore.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.ocl.ecore.EcorePackage;
import org.eclipse.ocl.ecore.NumericLiteralExp;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Numeric Literal Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class NumericLiteralExpImpl
		extends PrimitiveLiteralExpImpl
		implements NumericLiteralExp {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NumericLiteralExpImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EcorePackage.Literals.NUMERIC_LITERAL_EXP;
	}

} //NumericLiteralExpImpl
