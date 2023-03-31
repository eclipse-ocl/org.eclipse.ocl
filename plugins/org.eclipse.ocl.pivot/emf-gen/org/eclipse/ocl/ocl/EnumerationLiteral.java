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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.pivot.values.IntegerValue;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enumeration Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.EnumerationLiteral#getLiteral <em>Literal</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.EnumerationLiteral#getOwningEnumeration <em>Owning Enumeration</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.EnumerationLiteral#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getEnumerationLiteral()
 * @generated
 */
public interface EnumerationLiteral extends Object, InstanceSpecification
{

	/**
	 * Returns the value of the '<em><b>Literal</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Literal</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Literal</em>' attribute.
	 * @see #setLiteral(String)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getEnumerationLiteral_Literal()
	 * @generated
	 */
	String getLiteral();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.EnumerationLiteral#getLiteral <em>Literal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Literal</em>' attribute.
	 * @see #getLiteral()
	 * @generated
	 */
	void setLiteral(String value);

	/**
	 * Returns the value of the '<em><b>Owning Enumeration</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.Enumeration#getOwnedLiterals <em>Owned Literals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Enumeration that this EnumerationLiteral is a member of.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owning Enumeration</em>' container reference.
	 * @see #setOwningEnumeration(Enumeration)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getEnumerationLiteral_OwningEnumeration()
	 * @see org.eclipse.ocl.ocl.Enumeration#getOwnedLiterals
	 * @generated
	 */
	Enumeration getOwningEnumeration();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.EnumerationLiteral#getOwningEnumeration <em>Owning Enumeration</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Enumeration</em>' container reference.
	 * @see #getOwningEnumeration()
	 * @generated
	 */
	void setOwningEnumeration(Enumeration value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(IntegerValue)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getEnumerationLiteral_Value()
	 * @generated
	 */
	IntegerValue getValue();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.EnumerationLiteral#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(IntegerValue value);
} // EnumerationLiteral
