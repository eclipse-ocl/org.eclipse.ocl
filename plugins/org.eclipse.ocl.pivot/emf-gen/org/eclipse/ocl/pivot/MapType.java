/**
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Map Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.MapType#getKeyType <em>Key Type</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.MapType#isKeysAreNullFree <em>Keys Are Null Free</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.MapType#getValueType <em>Value Type</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.MapType#isValuesAreNullFree <em>Values Are Null Free</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.pivot.PivotPackage#getMapType()
 * @generated
 */
public interface MapType extends IterableType
{
	/**
	 * Returns the value of the '<em><b>Key Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key Type</em>' reference.
	 * @see #setKeyType(Type)
	 * @see org.eclipse.ocl.pivot.PivotPackage#getMapType_KeyType()
	 * @generated
	 */
	Type getKeyType();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.MapType#getKeyType <em>Key Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key Type</em>' reference.
	 * @see #getKeyType()
	 * @generated
	 */
	void setKeyType(Type value);

	/**
	 * Returns the value of the '<em><b>Keys Are Null Free</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Keys Are Null Free</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * @since 1.6
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Keys Are Null Free</em>' attribute.
	 * @see #setKeysAreNullFree(boolean)
	 * @see org.eclipse.ocl.pivot.PivotPackage#getMapType_KeysAreNullFree()
	 * @generated NOT
	 */
	default boolean isKeysAreNullFree() { return false; }

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.MapType#isKeysAreNullFree <em>Keys Are Null Free</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 1.6
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Keys Are Null Free</em>' attribute.
	 * @see #isKeysAreNullFree()
	 * @generated NOT
	 */
	default void setKeysAreNullFree(boolean value) {}

	/**
	 * Returns the value of the '<em><b>Value Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Type</em>' reference.
	 * @see #setValueType(Type)
	 * @see org.eclipse.ocl.pivot.PivotPackage#getMapType_ValueType()
	 * @generated
	 */
	Type getValueType();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.MapType#getValueType <em>Value Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Type</em>' reference.
	 * @see #getValueType()
	 * @generated
	 */
	void setValueType(Type value);

	/**
	 * Returns the value of the '<em><b>Values Are Null Free</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Values Are Null Free</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * @since 1.6
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values Are Null Free</em>' attribute.
	 * @see #setValuesAreNullFree(boolean)
	 * @see org.eclipse.ocl.pivot.PivotPackage#getMapType_ValuesAreNullFree()
	 * @generated NOT
	 */
	default boolean isValuesAreNullFree() { return false; }

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.MapType#isValuesAreNullFree <em>Values Are Null Free</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 1.6
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Values Are Null Free</em>' attribute.
	 * @see #isValuesAreNullFree()
	 * @generated NOT
	 */
	default void setValuesAreNullFree(boolean value) {}

} // MapType
