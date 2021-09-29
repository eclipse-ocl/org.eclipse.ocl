/**
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.examples.xtext.idioms.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.ocl.examples.xtext.idioms.FinalLocator;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Default Locator</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class FinalLocatorImpl
		extends LocatorImpl
		implements FinalLocator {

	/**
	 * The number of structural features of the '<em>Final Locator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FINAL_LOCATOR_FEATURE_COUNT = LocatorImpl.LOCATOR_FEATURE_COUNT + 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FinalLocatorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdiomsPackage.Literals.FINAL_LOCATOR;
	}

	@Override
	public String toString() {
		return "final";
	}
} //DefaultLocatorImpl
