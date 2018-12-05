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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CG Invalid</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * CGInvalid represents an invalid value with an optional message.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid#getMessageTemplate <em>Message Template</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid#getBindings <em>Bindings</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGInvalid()
 * @generated
 */
public interface CGInvalid extends CGConstant {

	/**
	 * Returns the value of the '<em><b>Message Template</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * optional message template explaining the invalidity
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Message Template</em>' attribute.
	 * @see #setMessageTemplate(String)
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGInvalid_MessageTemplate()
	 * @generated
	 */
	String getMessageTemplate();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid#getMessageTemplate <em>Message Template</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message Template</em>' attribute.
	 * @see #getMessageTemplate()
	 * @generated
	 */
	void setMessageTemplate(String value);

	/**
	 * Returns the value of the '<em><b>Bindings</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Object}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * bindings for use by the messageTemplate
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bindings</em>' attribute list.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGInvalid_Bindings()
	 * @generated
	 */
	List<Object> getBindings();
} // CGInvalid
