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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>OCL Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.OCLExpression#getTypeValue <em>Type Value</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getOCLExpression()
 * @generated
 */
public interface OCLExpression extends TypedElement
{
	/**
	 * Returns the value of the '<em><b>Type Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * When oclType() returns a Class value with a known actual type, the typeValue propagates the known type.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type Value</em>' reference.
	 * @see #setTypeValue(Type)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getOCLExpression_TypeValue()
	 * @generated
	 */
	Type getTypeValue();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.OCLExpression#getTypeValue <em>Type Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Value</em>' reference.
	 * @see #getTypeValue()
	 * @generated
	 */
	void setTypeValue(Type value);

} // OCLExpression
