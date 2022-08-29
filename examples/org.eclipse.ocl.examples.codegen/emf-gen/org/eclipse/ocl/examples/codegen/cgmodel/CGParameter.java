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
 * A representation of the model object '<em><b>CG Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGParameter#getCallable <em>Callable</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGParameter#isIsSelf <em>Is Self</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGParameter#isIsThis <em>Is This</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGParameter()
 * @generated
 */
public interface CGParameter extends CGVariable {

	/**
	 * Returns the value of the '<em><b>Callable</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGCallable#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Callable</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Callable</em>' container reference.
	 * @see #setCallable(CGCallable)
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGParameter_Callable()
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGCallable#getParameters
	 * @generated
	 */
	CGCallable getCallable();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGParameter#getCallable <em>Callable</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Callable</em>' container reference.
	 * @see #getCallable()
	 * @generated
	 */
	void setCallable(CGCallable value);

	/**
	 * Returns the value of the '<em><b>Is Self</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether this parameter supports the OCL self concept from the Abstract Syntax.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Self</em>' attribute.
	 * @see #setIsSelf(boolean)
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGParameter_IsSelf()
	 * @generated
	 */
	boolean isIsSelf();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGParameter#isIsSelf <em>Is Self</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Self</em>' attribute.
	 * @see #isIsSelf()
	 * @generated
	 */
	void setIsSelf(boolean value);

	/**
	 * Returns the value of the '<em><b>Is This</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether this parameter supports the Java this concept.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is This</em>' attribute.
	 * @see #setIsThis(boolean)
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGParameter_IsThis()
	 * @generated
	 */
	boolean isIsThis();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGParameter#isIsThis <em>Is This</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is This</em>' attribute.
	 * @see #isIsThis()
	 * @generated
	 */
	void setIsThis(boolean value);
} // CGParameter
