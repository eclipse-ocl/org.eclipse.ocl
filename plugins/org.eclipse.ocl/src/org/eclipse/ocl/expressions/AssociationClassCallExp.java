/*******************************************************************************
 * Copyright (c) 2006, 2018 IBM Corporation, Zeligsoft Inc., and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   IBM - Initial API and implementation
 *   Zeligsoft - Bug 207365
 *******************************************************************************/
package org.eclipse.ocl.expressions;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Association Class Call Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.expressions.AssociationClassCallExp#getReferredAssociationClass <em>Referred Association Class</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.expressions.ExpressionsPackage#getAssociationClassCallExp()
 * @model
 * @generated
 */
public interface AssociationClassCallExp<C, P>
		extends NavigationCallExp<C, P> {

	/**
	 * Returns the value of the '<em><b>Referred Association Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referred Association Class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referred Association Class</em>' reference.
	 * @see #setReferredAssociationClass(Object)
	 * @see org.eclipse.ocl.expressions.ExpressionsPackage#getAssociationClassCallExp_ReferredAssociationClass()
	 * @model kind="reference"
	 * @generated
	 */
	C getReferredAssociationClass();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.expressions.AssociationClassCallExp#getReferredAssociationClass <em>Referred Association Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referred Association Class</em>' reference.
	 * @see #getReferredAssociationClass()
	 * @generated
	 */
	void setReferredAssociationClass(C value);

} // AssociationClassCallExp
