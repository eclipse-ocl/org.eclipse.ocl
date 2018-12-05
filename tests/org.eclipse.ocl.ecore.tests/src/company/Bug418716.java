/*******************************************************************************
 * Copyright (c) 2016, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
/**
 */
package company;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bug418716</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link company.Bug418716#getAttributeWithInitital <em>Attribute With Initital</em>}</li>
 *   <li>{@link company.Bug418716#getAttributeWithoutInitital <em>Attribute Without Initital</em>}</li>
 * </ul>
 *
 * @see company.CompanyPackage#getBug418716()
 * @model
 * @generated
 */
public interface Bug418716 extends EObject {
	/**
	 * Returns the value of the '<em><b>Attribute With Initital</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute With Initital</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute With Initital</em>' attribute.
	 * @see #setAttributeWithInitital(int)
	 * @see company.CompanyPackage#getBug418716_AttributeWithInitital()
	 * @model annotation="http://www.eclipse.org/emf/2002/Ecore/OCL initial='100'"
	 * @generated
	 */
	int getAttributeWithInitital();

	/**
	 * Sets the value of the '{@link company.Bug418716#getAttributeWithInitital <em>Attribute With Initital</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attribute With Initital</em>' attribute.
	 * @see #getAttributeWithInitital()
	 * @generated
	 */
	void setAttributeWithInitital(int value);

	/**
	 * Returns the value of the '<em><b>Attribute Without Initital</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute Without Initital</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute Without Initital</em>' attribute.
	 * @see #setAttributeWithoutInitital(int)
	 * @see company.CompanyPackage#getBug418716_AttributeWithoutInitital()
	 * @model
	 * @generated
	 */
	int getAttributeWithoutInitital();

	/**
	 * Sets the value of the '{@link company.Bug418716#getAttributeWithoutInitital <em>Attribute Without Initital</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attribute Without Initital</em>' attribute.
	 * @see #getAttributeWithoutInitital()
	 * @generated
	 */
	void setAttributeWithoutInitital(int value);

} // Bug418716
