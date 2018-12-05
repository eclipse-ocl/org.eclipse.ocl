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
 * A representation of the model object '<em><b>Eclass1</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.validity.test.ecoreTest.Eclass1#getEAttribute1 <em>EAttribute1</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.validity.test.ecoreTest.Eclass1#getEAttribute2 <em>EAttribute2</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.validity.test.ecoreTest.Eclass1#getClasses2 <em>Classes2</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.EcoreTestPackage#getEclass1()
 * @model
 * @generated
 */
public interface Eclass1 extends EObject {
	/**
	 * Returns the value of the '<em><b>EAttribute1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EAttribute1</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EAttribute1</em>' attribute.
	 * @see #setEAttribute1(String)
	 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.EcoreTestPackage#getEclass1_EAttribute1()
	 * @model
	 * @generated
	 */
	String getEAttribute1();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.validity.test.ecoreTest.Eclass1#getEAttribute1 <em>EAttribute1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EAttribute1</em>' attribute.
	 * @see #getEAttribute1()
	 * @generated
	 */
	void setEAttribute1(String value);

	/**
	 * Returns the value of the '<em><b>EAttribute2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EAttribute2</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EAttribute2</em>' attribute.
	 * @see #setEAttribute2(String)
	 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.EcoreTestPackage#getEclass1_EAttribute2()
	 * @model
	 * @generated
	 */
	String getEAttribute2();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.validity.test.ecoreTest.Eclass1#getEAttribute2 <em>EAttribute2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EAttribute2</em>' attribute.
	 * @see #getEAttribute2()
	 * @generated
	 */
	void setEAttribute2(String value);

	/**
	 * Returns the value of the '<em><b>Classes2</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.validity.test.ecoreTest.EClass2}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classes2</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classes2</em>' containment reference list.
	 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.EcoreTestPackage#getEclass1_Classes2()
	 * @model containment="true"
	 * @generated
	 */
	EList<EClass2> getClasses2();

} // Eclass1
