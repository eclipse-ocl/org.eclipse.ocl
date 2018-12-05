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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Lendable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ecore.tests.extlibrary.Lendable#getCopies <em>Copies</em>}</li>
 *   <li>{@link org.eclipse.ocl.ecore.tests.extlibrary.Lendable#getBorrowers <em>Borrowers</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ecore.tests.extlibrary.EXTLibraryPackage#getLendable()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface Lendable extends EObject {
	/**
	 * Returns the value of the '<em><b>Copies</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Copies</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Copies</em>' attribute.
	 * @see #setCopies(int)
	 * @see org.eclipse.ocl.ecore.tests.extlibrary.EXTLibraryPackage#getLendable_Copies()
	 * @model required="true"
	 * @generated
	 */
	int getCopies();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ecore.tests.extlibrary.Lendable#getCopies <em>Copies</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Copies</em>' attribute.
	 * @see #getCopies()
	 * @generated
	 */
	void setCopies(int value);

	/**
	 * Returns the value of the '<em><b>Borrowers</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ecore.tests.extlibrary.Borrower}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ecore.tests.extlibrary.Borrower#getBorrowed <em>Borrowed</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Borrowers</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Borrowers</em>' reference list.
	 * @see org.eclipse.ocl.ecore.tests.extlibrary.EXTLibraryPackage#getLendable_Borrowers()
	 * @see org.eclipse.ocl.ecore.tests.extlibrary.Borrower#getBorrowed
	 * @model opposite="borrowed" ordered="false"
	 * @generated
	 */
	EList<Borrower> getBorrowers();

} // Lendable
