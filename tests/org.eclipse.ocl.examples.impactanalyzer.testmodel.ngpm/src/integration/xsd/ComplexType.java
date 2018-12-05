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
 * A representation of the model object '<em><b>Complex Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link integration.xsd.ComplexType#getAtts <em>Atts</em>}</li>
 *   <li>{@link integration.xsd.ComplexType#getContent <em>Content</em>}</li>
 * </ul>
 * </p>
 *
 * @see integration.xsd.XsdPackage#getComplexType()
 * @model
 * @generated
 */
public interface ComplexType extends AbstractType {
	/**
	 * Returns the value of the '<em><b>Atts</b></em>' reference list.
	 * The list contents are of type {@link integration.xsd.Attribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Atts</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Atts</em>' reference list.
	 * @see integration.xsd.XsdPackage#getComplexType_Atts()
	 * @model
	 * @generated
	 */
	EList<Attribute> getAtts();

	/**
	 * Returns the value of the '<em><b>Content</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Content</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Content</em>' reference.
	 * @see #setContent(AbstractContent)
	 * @see integration.xsd.XsdPackage#getComplexType_Content()
	 * @model
	 * @generated
	 */
	AbstractContent getContent();

	/**
	 * Sets the value of the '{@link integration.xsd.ComplexType#getContent <em>Content</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Content</em>' reference.
	 * @see #getContent()
	 * @generated
	 */
	void setContent(AbstractContent value);

} // ComplexType
