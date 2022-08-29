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
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp#getCgOperation <em>Cg Operation</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp#getCgArguments <em>Cg Arguments</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGOperationCallExp()
 * @generated
 */
public interface CGOperationCallExp extends CGCallExp {
	/**
	 * Returns the value of the '<em><b>Referred Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referred Operation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referred Operation</em>' attribute.
	 * @see #setReferredOperation(Operation)
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGOperationCallExp_ReferredOperation()
	 * @generated
	 */
	Operation getReferredOperation();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp#getReferredOperation <em>Referred Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referred Operation</em>' attribute.
	 * @see #getReferredOperation()
	 * @generated
	 */
	void setReferredOperation(Operation value);

	/**
	 * Returns the value of the '<em><b>Cg Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The this expression for the call when invoking a native or ecore implementation.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cg Operation</em>' reference.
	 * @see #setCgOperation(CGOperation)
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGOperationCallExp_CgOperation()
	 * @generated
	 */
	CGOperation getCgOperation();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp#getCgOperation <em>Cg Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cg Operation</em>' reference.
	 * @see #getCgOperation()
	 * @generated
	 */
	void setCgOperation(CGOperation value);

	/**
	 * Returns the value of the '<em><b>Cg Arguments</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * All argument expressions for the call often corresponding to executor then typeid then source/self then arguments
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cg Arguments</em>' containment reference list.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGOperationCallExp_CgArguments()
	 * @generated
	 */
	List<CGValuedElement> getCgArguments();

	default CGValuedElement getCgThis() { return null; }

} // CGOperationCallExp
