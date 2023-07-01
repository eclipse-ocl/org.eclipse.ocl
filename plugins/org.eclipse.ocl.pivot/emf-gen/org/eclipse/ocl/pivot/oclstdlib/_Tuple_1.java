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
package org.eclipse.ocl.pivot.oclstdlib;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tuple 1</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.oclstdlib._Tuple_1#getFirst <em>First</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.oclstdlib._Tuple_1#getSecond <em>Second</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#get_Tuple_1()
 * @generated
 */
public interface _Tuple_1<T, T2> extends EObject {
	/**
	 * Returns the value of the '<em><b>First</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>First</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>First</em>' reference.
	 * @see #setFirst(Object)
	 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#get_Tuple_1_First()
	 * @generated
	 */
	T getFirst();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.oclstdlib._Tuple_1#getFirst <em>First</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>First</em>' reference.
	 * @see #getFirst()
	 * @generated
	 */
	void setFirst(T value);

	/**
	 * Returns the value of the '<em><b>Second</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Second</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Second</em>' reference.
	 * @see #setSecond(Object)
	 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#get_Tuple_1_Second()
	 * @generated
	 */
	T2 getSecond();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.oclstdlib._Tuple_1#getSecond <em>Second</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Second</em>' reference.
	 * @see #getSecond()
	 * @generated
	 */
	void setSecond(T2 value);

} // _Tuple_1
