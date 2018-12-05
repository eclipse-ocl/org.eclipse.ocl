/*******************************************************************************
 * Copyright (c) 2013, 2018 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.cgmodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CG Number</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * CGNumber represents a numeric value.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNumber#getNumericValue <em>Numeric Value</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGNumber()
 * @generated
 */
public interface CGNumber extends CGConstant {
	/**
	 * Returns the value of the '<em><b>Numeric Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * the numeric value
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Numeric Value</em>' attribute.
	 * @see #setNumericValue(Number)
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGNumber_NumericValue()
	 * @generated
	 */
	Number getNumericValue();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNumber#getNumericValue <em>Numeric Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Numeric Value</em>' attribute.
	 * @see #getNumericValue()
	 * @generated
	 */
	void setNumericValue(Number value);

} // CGNumber
