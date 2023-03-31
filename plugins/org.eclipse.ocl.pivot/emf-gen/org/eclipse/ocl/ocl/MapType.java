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
 * A representation of the model object '<em><b>Map Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.MapType#getEntryClass <em>Entry Class</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.MapType#getKeyType <em>Key Type</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.MapType#getKeysAreNullFree <em>Keys Are Null Free</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.MapType#getValueType <em>Value Type</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.MapType#getValuesAreNullFree <em>Values Are Null Free</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getMapType()
 * @generated
 */
public interface MapType extends OclElement, IterableType
{
	/**
	 * Returns the value of the '<em><b>Entry Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A type for an entry that may allow an external syntax serialization as a set-of-entryClass.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Entry Class</em>' reference.
	 * @see #setEntryClass(org.eclipse.ocl.ocl.Class)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getMapType_EntryClass()
	 * @generated
	 */
	org.eclipse.ocl.ocl.Class getEntryClass();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.MapType#getEntryClass <em>Entry Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Entry Class</em>' reference.
	 * @see #getEntryClass()
	 * @generated
	 */
	void setEntryClass(org.eclipse.ocl.ocl.Class value);

	/**
	 * Returns the value of the '<em><b>Key Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The key type of the key-value pairs of oclText[self].
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Key Type</em>' reference.
	 * @see #setKeyType(Type)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getMapType_KeyType()
	 * @generated
	 */
	Type getKeyType();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.MapType#getKeyType <em>Key Type</em>}' reference.
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
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Keys Are Null Free</em>' attribute.
	 * @see #setKeysAreNullFree(Boolean)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getMapType_KeysAreNullFree()
	 * @generated
	 */
	Boolean getKeysAreNullFree();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.MapType#getKeysAreNullFree <em>Keys Are Null Free</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Keys Are Null Free</em>' attribute.
	 * @see #getKeysAreNullFree()
	 * @generated
	 */
	void setKeysAreNullFree(Boolean value);

	/**
	 * Returns the value of the '<em><b>Value Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The value type of the key-value pairs of oclText[self].
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value Type</em>' reference.
	 * @see #setValueType(Type)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getMapType_ValueType()
	 * @generated
	 */
	Type getValueType();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.MapType#getValueType <em>Value Type</em>}' reference.
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
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values Are Null Free</em>' attribute.
	 * @see #setValuesAreNullFree(Boolean)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getMapType_ValuesAreNullFree()
	 * @generated
	 */
	Boolean getValuesAreNullFree();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.MapType#getValuesAreNullFree <em>Values Are Null Free</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Values Are Null Free</em>' attribute.
	 * @see #getValuesAreNullFree()
	 * @generated
	 */
	void setValuesAreNullFree(Boolean value);

} // MapType
