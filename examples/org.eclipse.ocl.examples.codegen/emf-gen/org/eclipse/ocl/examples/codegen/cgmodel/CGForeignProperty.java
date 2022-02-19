/**
 * <copyright>
 *
 * Copyright (c) 2015, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *
 * </copyright>
 */
package org.eclipse.ocl.examples.codegen.cgmodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CG Foreign Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An operation that must be called to evaluate a ConstrainedProperty. Source, argument and result values are boxed.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGForeignProperty#getParameter <em>Parameter</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGForeignProperty#getInitExpression <em>Init Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGForeignProperty()
 * @generated
 */
public interface CGForeignProperty extends CGProperty {

	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The parameters of this operation, with 'self' as the first parameter.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Parameter</em>' containment reference.
	 * @see #setParameter(CGParameter)
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGForeignProperty_Parameter()
	 * @generated
	 */
	CGParameter getParameter();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGForeignProperty#getParameter <em>Parameter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter</em>' containment reference.
	 * @see #getParameter()
	 * @generated
	 */
	void setParameter(CGParameter value);
} // CGForeignProperty
