/**
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.xtext.base.cs2text.idioms;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Keyword Locator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.KeywordLocator#getString <em>String</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.KeywordLocator#getInEClass <em>In EClass</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage#getKeywordLocator()
 * @model
 * @generated
 */
public interface KeywordLocator extends Locator
{
	/**
	 * Returns the value of the '<em><b>String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>String</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>String</em>' attribute.
	 * @see #setString(String)
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage#getKeywordLocator_String()
	 * @model required="true"
	 * @generated
	 */
	String getString();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.KeywordLocator#getString <em>String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>String</em>' attribute.
	 * @see #getString()
	 * @generated
	 */
	void setString(String value);

	/**
	 * Returns the value of the '<em><b>In EClass</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In EClass</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In EClass</em>' reference.
	 * @see #setInEClass(EClass)
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage#getKeywordLocator_InEClass()
	 * @model
	 * @generated
	 */
	EClass getInEClass();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.KeywordLocator#getInEClass <em>In EClass</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>In EClass</em>' reference.
	 * @see #getInEClass()
	 * @generated
	 */
	void setInEClass(EClass value);

} // KeywordLocator