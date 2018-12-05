/*******************************************************************************
 * Copyright (c) 2018 Willink Transformations and others.
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
package org.eclipse.ocl.ecore.tests.extlibrary;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Periodical</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ecore.tests.extlibrary.Periodical#getTitle <em>Title</em>}</li>
 *   <li>{@link org.eclipse.ocl.ecore.tests.extlibrary.Periodical#getIssuesPerYear <em>Issues Per Year</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ecore.tests.extlibrary.EXTLibraryPackage#getPeriodical()
 * @model abstract="true"
 * @generated
 */
public interface Periodical extends Item {
	/**
	 * Returns the value of the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Title</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Title</em>' attribute.
	 * @see #setTitle(String)
	 * @see org.eclipse.ocl.ecore.tests.extlibrary.EXTLibraryPackage#getPeriodical_Title()
	 * @model
	 * @generated
	 */
	String getTitle();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ecore.tests.extlibrary.Periodical#getTitle <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' attribute.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(String value);

	/**
	 * Returns the value of the '<em><b>Issues Per Year</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Issues Per Year</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Issues Per Year</em>' attribute.
	 * @see #setIssuesPerYear(int)
	 * @see org.eclipse.ocl.ecore.tests.extlibrary.EXTLibraryPackage#getPeriodical_IssuesPerYear()
	 * @model required="true"
	 * @generated
	 */
	int getIssuesPerYear();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ecore.tests.extlibrary.Periodical#getIssuesPerYear <em>Issues Per Year</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Issues Per Year</em>' attribute.
	 * @see #getIssuesPerYear()
	 * @generated
	 */
	void setIssuesPerYear(int value);

} // Periodical
