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
package org.eclipse.ocl.ocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Call Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.CallExp#getIsImplicit <em>Is Implicit</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.CallExp#getIsSafe <em>Is Safe</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.CallExp#getOwnedSource <em>Owned Source</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getCallExp()
 * @generated
 */
public interface CallExp extends OCLExpression
{
	/**
	 * Returns the value of the '<em><b>Is Implicit</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Implicit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Implicit</em>' attribute.
	 * @see #setIsImplicit(Boolean)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getCallExp_IsImplicit()
	 * @generated
	 */
	Boolean getIsImplicit();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.CallExp#getIsImplicit <em>Is Implicit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Implicit</em>' attribute.
	 * @see #getIsImplicit()
	 * @generated
	 */
	void setIsImplicit(Boolean value);

	/**
	 * Returns the value of the '<em><b>Is Safe</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Safe</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Safe</em>' attribute.
	 * @see #setIsSafe(Boolean)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getCallExp_IsSafe()
	 * @generated
	 */
	Boolean getIsSafe();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.CallExp#getIsSafe <em>Is Safe</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Safe</em>' attribute.
	 * @see #getIsSafe()
	 * @generated
	 */
	void setIsSafe(Boolean value);

	/**
	 * Returns the value of the '<em><b>Owned Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Source</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Source</em>' containment reference.
	 * @see #setOwnedSource(OCLExpression)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getCallExp_OwnedSource()
	 * @generated
	 */
	OCLExpression getOwnedSource();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.CallExp#getOwnedSource <em>Owned Source</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owned Source</em>' containment reference.
	 * @see #getOwnedSource()
	 * @generated
	 */
	void setOwnedSource(OCLExpression value);

} // CallExp
