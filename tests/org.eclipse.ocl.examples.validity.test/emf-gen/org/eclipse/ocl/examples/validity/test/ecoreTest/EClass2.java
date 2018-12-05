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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EClass2</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.validity.test.ecoreTest.EClass2#getEAttribute3 <em>EAttribute3</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.validity.test.ecoreTest.EClass2#getClasses3 <em>Classes3</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.validity.test.ecoreTest.EClass2#getEAttribute4 <em>EAttribute4</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.EcoreTestPackage#getEClass2()
 * @model
 * @generated
 */
public interface EClass2 extends EObject {
	/**
	 * Returns the value of the '<em><b>EAttribute3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EAttribute3</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EAttribute3</em>' attribute.
	 * @see #setEAttribute3(short)
	 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.EcoreTestPackage#getEClass2_EAttribute3()
	 * @model
	 * @generated
	 */
	short getEAttribute3();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.validity.test.ecoreTest.EClass2#getEAttribute3 <em>EAttribute3</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EAttribute3</em>' attribute.
	 * @see #getEAttribute3()
	 * @generated
	 */
	void setEAttribute3(short value);

	/**
	 * Returns the value of the '<em><b>Classes3</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.validity.test.ecoreTest.EClass3}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classes3</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classes3</em>' containment reference list.
	 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.EcoreTestPackage#getEClass2_Classes3()
	 * @model containment="true"
	 * @generated
	 */
	EList<EClass3> getClasses3();

	/**
	 * Returns the value of the '<em><b>EAttribute4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EAttribute4</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EAttribute4</em>' attribute.
	 * @see #setEAttribute4(String)
	 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.EcoreTestPackage#getEClass2_EAttribute4()
	 * @model
	 * @generated
	 */
	String getEAttribute4();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.validity.test.ecoreTest.EClass2#getEAttribute4 <em>EAttribute4</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EAttribute4</em>' attribute.
	 * @see #getEAttribute4()
	 * @generated
	 */
	void setEAttribute4(String value);

} // EClass2
