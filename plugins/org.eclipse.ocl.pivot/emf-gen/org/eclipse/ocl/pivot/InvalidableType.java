/**
 * Copyright (c) 2010, 2017 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Invalidable Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.InvalidableType#getNonNullType <em>Non Null Type</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.pivot.PivotPackage#getInvalidableType()
 * @generated
 */
public interface InvalidableType extends org.eclipse.ocl.pivot.Class
{
	/**
	 * Returns the value of the '<em><b>Non Null Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Non Invalid Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Non Null Type</em>' reference.
	 * @see #setNonNullType(Type)
	 * @see org.eclipse.ocl.pivot.PivotPackage#getInvalidableType_NonNullType()
	 * @generated
	 */
	Type getNonNullType();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.InvalidableType#getNonNullType <em>Non Null Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Non Null Type</em>' reference.
	 * @see #getNonNullType()
	 * @generated
	 */
	void setNonNullType(Type value);

} // InvalidableType
