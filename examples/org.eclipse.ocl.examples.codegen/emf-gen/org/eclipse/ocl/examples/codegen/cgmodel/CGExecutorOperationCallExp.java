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
 * A representation of the model object '<em><b>CG Executor Operation Call Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A CGExecutorOperationCallExp is not used.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOperationCallExp#getExecutorOperation <em>Executor Operation</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGExecutorOperationCallExp()
 * @generated
 */
public interface CGExecutorOperationCallExp extends CGOperationCallExp {

	/**
	 * Returns the value of the '<em><b>Executor Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Executor Operation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Executor Operation</em>' reference.
	 * @see #setExecutorOperation(CGExecutorOperation)
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGExecutorOperationCallExp_ExecutorOperation()
	 * @generated
	 */
	CGExecutorOperation getExecutorOperation();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOperationCallExp#getExecutorOperation <em>Executor Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Executor Operation</em>' reference.
	 * @see #getExecutorOperation()
	 * @generated
	 */
	void setExecutorOperation(CGExecutorOperation value);
} // CGExecutorOperationCallExp
