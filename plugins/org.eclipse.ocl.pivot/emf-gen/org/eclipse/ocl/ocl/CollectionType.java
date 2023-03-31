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

import org.eclipse.ocl.pivot.values.IntegerValue;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Collection Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.CollectionType#getElementType <em>Element Type</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.CollectionType#getIsNullFree <em>Is Null Free</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.CollectionType#getLower <em>Lower</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.CollectionType#getUpper <em>Upper</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getCollectionType()
 * @generated
 */
public interface CollectionType extends OclElement, IterableType
{
	/**
	 * Returns the value of the '<em><b>Element Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Evaluates to the type of the collection elements.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Element Type</em>' reference.
	 * @see #setElementType(Type)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getCollectionType_ElementType()
	 * @generated
	 */
	Type getElementType();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.CollectionType#getElementType <em>Element Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element Type</em>' reference.
	 * @see #getElementType()
	 * @generated
	 */
	void setElementType(Type value);

	/**
	 * Returns the value of the '<em><b>Is Null Free</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Null Free</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Null Free</em>' attribute.
	 * @see #setIsNullFree(Boolean)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getCollectionType_IsNullFree()
	 * @generated
	 */
	Boolean getIsNullFree();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.CollectionType#getIsNullFree <em>Is Null Free</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Null Free</em>' attribute.
	 * @see #getIsNullFree()
	 * @generated
	 */
	void setIsNullFree(Boolean value);

	/**
	 * Returns the value of the '<em><b>Lower</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Evaluates to the lower bound on the number of collection elements.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Lower</em>' attribute.
	 * @see #setLower(IntegerValue)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getCollectionType_Lower()
	 * @generated
	 */
	IntegerValue getLower();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.CollectionType#getLower <em>Lower</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lower</em>' attribute.
	 * @see #getLower()
	 * @generated
	 */
	void setLower(IntegerValue value);

	/**
	 * Returns the value of the '<em><b>Upper</b></em>' attribute.
	 * The default value is <code>"*"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Evaluates to the upper bound on the number of collection elements.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Upper</em>' attribute.
	 * @see #setUpper(IntegerValue)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getCollectionType_Upper()
	 * @generated
	 */
	IntegerValue getUpper();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.CollectionType#getUpper <em>Upper</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upper</em>' attribute.
	 * @see #getUpper()
	 * @generated
	 */
	void setUpper(IntegerValue value);

} // CollectionType
