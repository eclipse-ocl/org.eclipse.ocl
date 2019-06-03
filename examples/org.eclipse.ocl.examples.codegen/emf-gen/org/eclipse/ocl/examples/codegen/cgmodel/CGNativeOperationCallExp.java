/*******************************************************************************
 * Copyright (c) 2014, 2019 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.cgmodel;

import java.lang.reflect.Method;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CG Method Operation Call Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A call of an operation using its Java declaration. The OCL self may be passed as an extra argument.
 * Other arguments are passed using the unboxed representation.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp#getMethod <em>Method</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp#isThisIsSelf <em>This Is Self</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGNativeOperationCallExp()
 * @generated
 */
public interface CGNativeOperationCallExp extends CGOperationCallExp {
	/**
	 * Returns the value of the '<em><b>Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method</em>' attribute.
	 * @see #setMethod(Method)
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGNativeOperationCallExp_Method()
	 * @generated
	 */
	Method getMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp#getMethod <em>Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method</em>' attribute.
	 * @see #getMethod()
	 * @generated
	 */
	void setMethod(Method value);

	/**
	 * Returns the value of the '<em><b>This Is Self</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * true when OCL's self is Java's this, false when OCL's self is passed as an additional first argument
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>This Is Self</em>' attribute.
	 * @see #setThisIsSelf(boolean)
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGNativeOperationCallExp_ThisIsSelf()
	 * @generated
	 */
	boolean isThisIsSelf();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp#isThisIsSelf <em>This Is Self</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>This Is Self</em>' attribute.
	 * @see #isThisIsSelf()
	 * @generated
	 */
	void setThisIsSelf(boolean value);

} // CGMethodOperationCallExp
