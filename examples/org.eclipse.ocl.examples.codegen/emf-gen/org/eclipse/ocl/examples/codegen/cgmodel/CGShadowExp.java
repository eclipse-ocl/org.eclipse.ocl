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

import org.eclipse.jdt.annotation.NonNull;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CG Constructor Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGShadowExp#getParts <em>Parts</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGShadowExp#getExecutorType <em>Executor Type</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGShadowExp()
 * @generated
 */
public interface CGShadowExp extends CGValuedElement {
	/**
	 * Returns the value of the '<em><b>Parts</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.codegen.cgmodel.CGShadowPart}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGShadowPart#getShadowExp <em>Shadow Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parts</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parts</em>' containment reference list.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGShadowExp_Parts()
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGShadowPart#getShadowExp
	 * @generated
	 */
	@NonNull List<CGShadowPart> getParts();

	/**
	 * Returns the value of the '<em><b>Executor Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Executor Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Executor Type</em>' reference.
	 * @see #setExecutorType(CGExecutorType)
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGShadowExp_ExecutorType()
	 * @generated
	 */
	CGExecutorType getExecutorType();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGShadowExp#getExecutorType <em>Executor Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Executor Type</em>' reference.
	 * @see #getExecutorType()
	 * @generated
	 */
	void setExecutorType(CGExecutorType value);

} // CGConstructorExp
