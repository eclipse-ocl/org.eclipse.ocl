/*******************************************************************************
 * Copyright (c) 2005, 2018 IBM Corporation, Zeligsoft Inc., and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   Zeligsoft - Bug 243976
 *******************************************************************************/
package org.eclipse.ocl.cst;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>If Exp CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.cst.IfExpCS#getThenExpression <em>Then Expression</em>}</li>
 *   <li>{@link org.eclipse.ocl.cst.IfExpCS#getElseExpression <em>Else Expression</em>}</li>
 *   <li>{@link org.eclipse.ocl.cst.IfExpCS#getCondition <em>Condition</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.cst.CSTPackage#getIfExpCS()
 * @model
 * @generated
 */
public interface IfExpCS
		extends OCLExpressionCS {

	/**
	 * Returns the value of the '<em><b>Then Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Then Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Then Expression</em>' containment reference.
	 * @see #setThenExpression(OCLExpressionCS)
	 * @see org.eclipse.ocl.cst.CSTPackage#getIfExpCS_ThenExpression()
	 * @model containment="true" required="true"
	 * @generated
	 */
	OCLExpressionCS getThenExpression();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.cst.IfExpCS#getThenExpression <em>Then Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Then Expression</em>' containment reference.
	 * @see #getThenExpression()
	 * @generated
	 */
	void setThenExpression(OCLExpressionCS value);

	/**
	 * Returns the value of the '<em><b>Else Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Else Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Else Expression</em>' containment reference.
	 * @see #setElseExpression(OCLExpressionCS)
	 * @see org.eclipse.ocl.cst.CSTPackage#getIfExpCS_ElseExpression()
	 * @model containment="true" required="true"
	 * @generated
	 */
	OCLExpressionCS getElseExpression();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.cst.IfExpCS#getElseExpression <em>Else Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Else Expression</em>' containment reference.
	 * @see #getElseExpression()
	 * @generated
	 */
	void setElseExpression(OCLExpressionCS value);

	/**
	 * Returns the value of the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' containment reference.
	 * @see #setCondition(OCLExpressionCS)
	 * @see org.eclipse.ocl.cst.CSTPackage#getIfExpCS_Condition()
	 * @model containment="true" required="true"
	 * @generated
	 */
	OCLExpressionCS getCondition();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.cst.IfExpCS#getCondition <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' containment reference.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(OCLExpressionCS value);

} // IfExpCS
