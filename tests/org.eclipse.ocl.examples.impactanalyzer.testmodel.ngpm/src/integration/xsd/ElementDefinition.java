/*******************************************************************************
 * Copyright (c) 2009, 2018 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     SAP AG - initial API and implementation
 ******************************************************************************
 */
package integration.xsd;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link integration.xsd.ElementDefinition#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see integration.xsd.XsdPackage#getElementDefinition()
 * @model
 * @generated
 */
public interface ElementDefinition extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(AbstractType)
	 * @see integration.xsd.XsdPackage#getElementDefinition_Type()
	 * @model
	 * @generated
	 */
	AbstractType getType();

	/**
	 * Sets the value of the '{@link integration.xsd.ElementDefinition#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(AbstractType value);

} // ElementDefinition
