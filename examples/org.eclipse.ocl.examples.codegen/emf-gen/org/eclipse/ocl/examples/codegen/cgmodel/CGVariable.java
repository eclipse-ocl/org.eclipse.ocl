/*******************************************************************************
 * Copyright (c) 2013, 2014 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.cgmodel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CG Variable Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A CGVariable supports the shared use of its init expression by many VariableExps.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGVariable#getInit <em>Init</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGVariable#isCacheNeeded <em>Cache Needed</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGVariable()
 * @generated
 */
public interface CGVariable extends CGValuedElement {
	/**
	 * Returns the value of the '<em><b>Init</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Init</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The shared value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Init</em>' containment reference.
	 * @see #setInit(CGValuedElement)
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGVariable_Init()
	 * @generated
	 */
	CGValuedElement getInit();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGVariable#getInit <em>Init</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Init</em>' containment reference.
	 * @see #getInit()
	 * @generated
	 */
	void setInit(CGValuedElement value);

	/**
	 * Returns the value of the '<em><b>Cache Needed</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cache Needed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cache Needed</em>' attribute.
	 * @see #setCacheNeeded(boolean)
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGVariable_CacheNeeded()
	 * @generated
	 */
	boolean isCacheNeeded();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGVariable#isCacheNeeded <em>Cache Needed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cache Needed</em>' attribute.
	 * @see #isCacheNeeded()
	 * @generated
	 */
	void setCacheNeeded(boolean value);

	/**
	 * Set the non-invalid status.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$24
	void setNonInvalid();

	/**
	 * Set the non-null status.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$25
	void setNonNull();

} // CGVariableDeclaration
