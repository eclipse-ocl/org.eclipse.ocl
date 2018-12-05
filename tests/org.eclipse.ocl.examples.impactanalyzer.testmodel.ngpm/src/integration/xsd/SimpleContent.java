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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Simple Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link integration.xsd.SimpleContent#getBase <em>Base</em>}</li>
 *   <li>{@link integration.xsd.SimpleContent#getEnumVals <em>Enum Vals</em>}</li>
 * </ul>
 * </p>
 *
 * @see integration.xsd.XsdPackage#getSimpleContent()
 * @model
 * @generated
 */
public interface SimpleContent extends AbstractContent {
	/**
	 * Returns the value of the '<em><b>Base</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base</em>' attribute.
	 * @see #setBase(String)
	 * @see integration.xsd.XsdPackage#getSimpleContent_Base()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	String getBase();

	/**
	 * Sets the value of the '{@link integration.xsd.SimpleContent#getBase <em>Base</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base</em>' attribute.
	 * @see #getBase()
	 * @generated
	 */
	void setBase(String value);

	/**
	 * Returns the value of the '<em><b>Enum Vals</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enum Vals</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enum Vals</em>' attribute list.
	 * @see integration.xsd.XsdPackage#getSimpleContent_EnumVals()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	EList<String> getEnumVals();

} // SimpleContent
