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
 * A representation of the model object '<em><b>Precedence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.Precedence#getAssociativity <em>Associativity</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Precedence#getOrder <em>Order</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getPrecedence()
 * @generated
 */
public interface Precedence extends NamedElement
{
	/**
	 * Returns the value of the '<em><b>Associativity</b></em>' attribute.
	 * The default value is <code>"left"</code>.
	 * The literals are from the enumeration {@link org.eclipse.ocl.ocl.AssociativityKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Associativity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Associativity</em>' attribute.
	 * @see org.eclipse.ocl.ocl.AssociativityKind
	 * @see #setAssociativity(AssociativityKind)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getPrecedence_Associativity()
	 * @generated
	 */
	AssociativityKind getAssociativity();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Precedence#getAssociativity <em>Associativity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Associativity</em>' attribute.
	 * @see org.eclipse.ocl.ocl.AssociativityKind
	 * @see #getAssociativity()
	 * @generated
	 */
	void setAssociativity(AssociativityKind value);

	/**
	 * Returns the value of the '<em><b>Order</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Order</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Order</em>' attribute.
	 * @see #setOrder(IntegerValue)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getPrecedence_Order()
	 * @generated
	 */
	IntegerValue getOrder();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Precedence#getOrder <em>Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Order</em>' attribute.
	 * @see #getOrder()
	 * @generated
	 */
	void setOrder(IntegerValue value);

} // Precedence
