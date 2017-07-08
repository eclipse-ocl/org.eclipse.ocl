/**
 * <copyright>
 * 
 * Copyright (c) 2015, 2017 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 * 
 * </copyright>
 */
package codegen.company;

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
 *   <li>{@link codegen.company.Bug418716#getAttributeWithInitital <em>Attribute With Initital</em>}</li>
 *   <li>{@link codegen.company.Bug418716#getAttributeWithoutInitital <em>Attribute Without Initital</em>}</li>
 * </ul>
 *
 * @see codegen.company.CodegencompanyPackage#getBug418716()
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
	 * @see codegen.company.CodegencompanyPackage#getBug418716_AttributeWithInitital()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='/**\n * 100\n \052/\nfinal int ECORE_INT_100 = &lt;%org.eclipse.ocl.pivot.utilities.ValueUtil%&gt;.intValueOf(&lt;%codegen.company.CodegencompanyTables%&gt;.INT_100);\nreturn ECORE_INT_100;'"
	 * @generated
	 */
	int getAttributeWithInitital();

	/**
	 * Sets the value of the '{@link codegen.company.Bug418716#getAttributeWithInitital <em>Attribute With Initital</em>}' attribute.
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
	 * @see codegen.company.CodegencompanyPackage#getBug418716_AttributeWithoutInitital()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://www.eclipse.org/ocl/test/Pivot/Company.ecore!Bug418716!AttributeWithoutInitital'"
	 * @generated
	 */
	int getAttributeWithoutInitital();

	/**
	 * Sets the value of the '{@link codegen.company.Bug418716#getAttributeWithoutInitital <em>Attribute Without Initital</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attribute Without Initital</em>' attribute.
	 * @see #getAttributeWithoutInitital()
	 * @generated
	 */
	void setAttributeWithoutInitital(int value);

} // Bug418716
