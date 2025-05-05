/**
 * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.xtext.basecs;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Lambda Parameter CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.LambdaParameterCS#getOwnedContextType <em>Owned Context Type</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.LambdaParameterCS#getOwnedParameters <em>Owned Parameters</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage#getLambdaParameterCS()
 * @model
 * @generated
 */
public interface LambdaParameterCS extends ParameterCS
{
	/**
	 * Returns the value of the '<em><b>Owned Context Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Context Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Context Type</em>' containment reference.
	 * @see #setOwnedContextType(TypedRefCS)
	 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage#getLambdaParameterCS_OwnedContextType()
	 * @model containment="true"
	 * @generated
	 */
	TypedRefCS getOwnedContextType();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.basecs.LambdaParameterCS#getOwnedContextType <em>Owned Context Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owned Context Type</em>' containment reference.
	 * @see #getOwnedContextType()
	 * @generated
	 */
	void setOwnedContextType(TypedRefCS value);

	/**
	 * Returns the value of the '<em><b>Owned Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.xtext.basecs.ParameterCS}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Parameters</em>' containment reference list.
	 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage#getLambdaParameterCS_OwnedParameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<ParameterCS> getOwnedParameters();

} // LambdaParameterCS
