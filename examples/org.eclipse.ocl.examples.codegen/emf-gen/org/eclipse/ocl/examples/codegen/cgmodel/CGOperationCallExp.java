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

import java.util.List;

import org.eclipse.ocl.pivot.Operation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CG Operation Call Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp#getReferredOperation <em>Referred Operation</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp#getArguments <em>Arguments</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp#getAsOperation <em>As Operation</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGOperationCallExp()
 * @generated
 */
public interface CGOperationCallExp extends CGCallExp {
	/**
	 * Returns the value of the '<em><b>Referred Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referred Operation</em>' reference.
	 * @see #setReferredOperation(CGOperation)
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGOperationCallExp_ReferredOperation()
	 * @generated
	 */
	CGOperation getReferredOperation();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp#getReferredOperation <em>Referred Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referred Operation</em>' reference.
	 * @see #getReferredOperation()
	 * @generated
	 */
	void setReferredOperation(CGOperation value);

	/**
	 * Returns the value of the '<em><b>Arguments</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * All argument expressions for the call often corresponding to executor then typeid then source/self then arguments
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Arguments</em>' containment reference list.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGOperationCallExp_Arguments()
	 * @generated
	 */
	List<CGValuedElement> getArguments();

	/**
	 * Returns the value of the '<em><b>As Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>As Operation</em>' attribute.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGOperationCallExp_AsOperation()
	 * @generated
	 */
	Operation getAsOperation();

	default CGValuedElement getCgThis() { return null; }
} // CGOperationCallExp
