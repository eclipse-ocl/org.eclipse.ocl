/*******************************************************************************
 * Copyright (c) 2013, 2019 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.cgmodel.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPropertyCallExp;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CG Property Call Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class CGPropertyCallExpImpl extends CGNavigationCallExpImpl implements CGPropertyCallExp {
	/**
	 * The number of structural features of the '<em>CG Property Call Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CG_PROPERTY_CALL_EXP_FEATURE_COUNT = CGNavigationCallExpImpl.CG_NAVIGATION_CALL_EXP_FEATURE_COUNT + 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CGPropertyCallExpImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CGModelPackage.Literals.CG_PROPERTY_CALL_EXP;
	}

} //CGPropertyCallExpImpl
