/**
 * <copyright>
 * 
 * Copyright (c) 2015, 2020 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.ocl.examples.xtext.tests.codegen.company;

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
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Bug418716#getAttributeWithInitital <em>Attribute With Initital</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Bug418716#getAttributeWithoutInitital <em>Attribute Without Initital</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage#getBug418716()
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
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage#getBug418716_AttributeWithInitital()
	 * @model required="true"
	 * @generated
	 */
	int getAttributeWithInitital();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Bug418716#getAttributeWithInitital <em>Attribute With Initital</em>}' attribute.
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
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage#getBug418716_AttributeWithoutInitital()
	 * @model required="true"
	 * @generated
	 */
	int getAttributeWithoutInitital();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Bug418716#getAttributeWithoutInitital <em>Attribute Without Initital</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attribute Without Initital</em>' attribute.
	 * @see #getAttributeWithoutInitital()
	 * @generated
	 */
	void setAttributeWithoutInitital(int value);

} // Bug418716
