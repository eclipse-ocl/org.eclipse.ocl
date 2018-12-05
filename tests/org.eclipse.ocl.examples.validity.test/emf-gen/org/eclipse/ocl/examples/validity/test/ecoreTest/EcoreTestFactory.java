/*******************************************************************************
 * Copyright (c) 2014, 2018 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.validity.test.ecoreTest;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.EcoreTestPackage
 * @generated
 */
public interface EcoreTestFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EcoreTestFactory eINSTANCE = org.eclipse.ocl.examples.validity.test.ecoreTest.impl.EcoreTestFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Eclass1</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Eclass1</em>'.
	 * @generated
	 */
	Eclass1 createEclass1();

	/**
	 * Returns a new object of class '<em>EClass2</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>EClass2</em>'.
	 * @generated
	 */
	EClass2 createEClass2();

	/**
	 * Returns a new object of class '<em>EClass3</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>EClass3</em>'.
	 * @generated
	 */
	EClass3 createEClass3();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	EcoreTestPackage getEcoreTestPackage();

} //EcoreTestFactory
