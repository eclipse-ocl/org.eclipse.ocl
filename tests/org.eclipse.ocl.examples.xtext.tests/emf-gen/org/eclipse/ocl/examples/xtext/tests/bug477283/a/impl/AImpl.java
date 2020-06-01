/*******************************************************************************
 * Copyright (c) 2016, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
/**
 */
package org.eclipse.ocl.examples.xtext.tests.bug477283.a.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.ocl.examples.xtext.tests.bug477283.a.A;
import org.eclipse.ocl.examples.xtext.tests.bug477283.a.Bug477283APackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>A</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class AImpl extends MinimalEObjectImpl.Container implements A {
	/**
	 * The number of structural features of the '<em>A</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int A_FEATURE_COUNT = 0;
	/**
	 * The number of operations of the '<em>A</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int A_OPERATION_COUNT = 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Bug477283APackage.Literals.A;
	}

} //AImpl
