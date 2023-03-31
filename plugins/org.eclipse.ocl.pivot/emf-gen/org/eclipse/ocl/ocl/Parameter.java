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
 * A representation of the model object '<em><b>Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.Parameter#getIsTypeof <em>Is Typeof</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Parameter#getOwningOperation <em>Owning Operation</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getParameter()
 * @generated
 */
public interface Parameter extends VariableDeclaration
{
	/**
	 * Returns the value of the '<em><b>Is Typeof</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Typeof</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Typeof</em>' attribute.
	 * @see #setIsTypeof(Boolean)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getParameter_IsTypeof()
	 * @generated
	 */
	Boolean getIsTypeof();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Parameter#getIsTypeof <em>Is Typeof</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Typeof</em>' attribute.
	 * @see #getIsTypeof()
	 * @generated
	 */
	void setIsTypeof(Boolean value);

	/**
	 * Returns the value of the '<em><b>Owning Operation</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.Operation#getOwnedParameters <em>Owned Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Operation owning this parameter.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owning Operation</em>' container reference.
	 * @see #setOwningOperation(Operation)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getParameter_OwningOperation()
	 * @see org.eclipse.ocl.ocl.Operation#getOwnedParameters
	 * @generated
	 */
	Operation getOwningOperation();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Parameter#getOwningOperation <em>Owning Operation</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Operation</em>' container reference.
	 * @see #getOwningOperation()
	 * @generated
	 */
	void setOwningOperation(Operation value);

} // Parameter
