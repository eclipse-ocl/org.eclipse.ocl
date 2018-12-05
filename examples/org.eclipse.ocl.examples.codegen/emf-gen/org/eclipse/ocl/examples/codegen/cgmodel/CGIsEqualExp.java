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
 * A representation of the model object '<em><b>CG Is Equal Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A CGIsEqualExp realizes the built-in "=" operation.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqualExp#getArgument <em>Argument</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqualExp#isNotEquals <em>Not Equals</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGIsEqualExp()
 * @generated
 */
public interface CGIsEqualExp extends CGCallExp {

	/**
	 * Returns the value of the '<em><b>Argument</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The argument expression for the call.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Argument</em>' containment reference.
	 * @see #setArgument(CGValuedElement)
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGIsEqualExp_Argument()
	 * @generated
	 */
	CGValuedElement getArgument();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqualExp#getArgument <em>Argument</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Argument</em>' containment reference.
	 * @see #getArgument()
	 * @generated
	 */
	void setArgument(CGValuedElement value);

	/**
	 * Returns the value of the '<em><b>Not Equals</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * True for not-equals rather than equals.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Not Equals</em>' attribute.
	 * @see #setNotEquals(boolean)
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGIsEqualExp_NotEquals()
	 * @generated
	 */
	boolean isNotEquals();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqualExp#isNotEquals <em>Not Equals</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Not Equals</em>' attribute.
	 * @see #isNotEquals()
	 * @generated
	 */
	void setNotEquals(boolean value);
} // CGIsEqualExp
