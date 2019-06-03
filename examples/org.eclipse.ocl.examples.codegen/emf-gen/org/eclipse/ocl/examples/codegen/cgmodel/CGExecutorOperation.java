/*******************************************************************************
 * Copyright (c) 2013, 2019 CEA LIST and others.
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
 * A representation of the model object '<em><b>CG Executor Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A CGExecutorOperation is not used.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOperation#getUnderlyingOperationId <em>Underlying Operation Id</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGExecutorOperation()
 * @generated
 */
public interface CGExecutorOperation extends CGValuedElement {
	/**
	 * Returns the value of the '<em><b>Underlying Operation Id</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Underlying Operation Id</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Underlying Operation Id</em>' reference.
	 * @see #setUnderlyingOperationId(CGElementId)
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGExecutorOperation_UnderlyingOperationId()
	 * @generated
	 */
	CGElementId getUnderlyingOperationId();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOperation#getUnderlyingOperationId <em>Underlying Operation Id</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Underlying Operation Id</em>' reference.
	 * @see #getUnderlyingOperationId()
	 * @generated
	 */
	void setUnderlyingOperationId(CGElementId value);

} // CGExecutorOperation
