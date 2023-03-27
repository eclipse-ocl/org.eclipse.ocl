/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.basecs;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Multiplicity CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.MultiplicityCS#isIsNonNullFree <em>Is Non Null Free</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.MultiplicityCS#isIsNullFree <em>Is Null Free</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage#getMultiplicityCS()
 * @model abstract="true"
 * @generated
 */
public interface MultiplicityCS extends ElementCS
{
	/**
	 * Returns the value of the '<em><b>Is Non Null Free</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Non Null Free</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Non Null Free</em>' attribute.
	 * @see #isSetIsNonNullFree()
	 * @see #unsetIsNonNullFree()
	 * @see #setIsNonNullFree(boolean)
	 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage#getMultiplicityCS_IsNonNullFree()
	 * @model unsettable="true"
	 * @generated
	 */
	boolean isIsNonNullFree();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.basecs.MultiplicityCS#isIsNonNullFree <em>Is Non Null Free</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Non Null Free</em>' attribute.
	 * @see #isSetIsNonNullFree()
	 * @see #unsetIsNonNullFree()
	 * @see #isIsNonNullFree()
	 * @generated
	 */
	void setIsNonNullFree(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.ocl.xtext.basecs.MultiplicityCS#isIsNonNullFree <em>Is Non Null Free</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsNonNullFree()
	 * @see #isIsNonNullFree()
	 * @see #setIsNonNullFree(boolean)
	 * @generated
	 */
	void unsetIsNonNullFree();

	/**
	 * Returns whether the value of the '{@link org.eclipse.ocl.xtext.basecs.MultiplicityCS#isIsNonNullFree <em>Is Non Null Free</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is Non Null Free</em>' attribute is set.
	 * @see #unsetIsNonNullFree()
	 * @see #isIsNonNullFree()
	 * @see #setIsNonNullFree(boolean)
	 * @generated
	 */
	boolean isSetIsNonNullFree();

	/**
	 * Returns the value of the '<em><b>Is Null Free</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Null Free</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Null Free</em>' attribute.
	 * @see #isSetIsNullFree()
	 * @see #unsetIsNullFree()
	 * @see #setIsNullFree(boolean)
	 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage#getMultiplicityCS_IsNullFree()
	 * @model unsettable="true"
	 * @generated
	 */
	boolean isIsNullFree();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.basecs.MultiplicityCS#isIsNullFree <em>Is Null Free</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Null Free</em>' attribute.
	 * @see #isSetIsNullFree()
	 * @see #unsetIsNullFree()
	 * @see #isIsNullFree()
	 * @generated
	 */
	void setIsNullFree(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.ocl.xtext.basecs.MultiplicityCS#isIsNullFree <em>Is Null Free</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsNullFree()
	 * @see #isIsNullFree()
	 * @see #setIsNullFree(boolean)
	 * @generated
	 */
	void unsetIsNullFree();

	/**
	 * Returns whether the value of the '{@link org.eclipse.ocl.xtext.basecs.MultiplicityCS#isIsNullFree <em>Is Null Free</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is Null Free</em>' attribute is set.
	 * @see #unsetIsNullFree()
	 * @see #isIsNullFree()
	 * @see #setIsNullFree(boolean)
	 * @generated
	 */
	boolean isSetIsNullFree();

	/**
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lower</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	int getLower();

	/**
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Upper</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	int getUpper();

} // MultiplicityCS
