/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Lambda Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.LambdaType#getOwnedContext <em>Owned Context</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.LambdaType#getOwnedParameters <em>Owned Parameters</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.LambdaType#getOwnedResult <em>Owned Result</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.pivot.PivotPackage#getLambdaType()
 * @generated
 */
public interface LambdaType extends DataType
{
	/**
	 * Returns the value of the '<em><b>Owned Context</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Context</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Context</em>' containment reference.
	 * @see #setOwnedContext(LambdaParameter)
	 * @see org.eclipse.ocl.pivot.PivotPackage#getLambdaType_OwnedContext()
	 * @generated
	 */
	LambdaParameter getOwnedContext();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.LambdaType#getOwnedContext <em>Owned Context</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owned Context</em>' containment reference.
	 * @see #getOwnedContext()
	 * @generated
	 */
	void setOwnedContext(LambdaParameter value);

	/**
	 * Returns the value of the '<em><b>Owned Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.pivot.LambdaParameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Parameters</em>' containment reference list.
	 * @see org.eclipse.ocl.pivot.PivotPackage#getLambdaType_OwnedParameters()
	 * @generated
	 */
	List<LambdaParameter> getOwnedParameters();

	/**
	 * Returns the value of the '<em><b>Owned Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Result</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Result</em>' containment reference.
	 * @see #setOwnedResult(LambdaParameter)
	 * @see org.eclipse.ocl.pivot.PivotPackage#getLambdaType_OwnedResult()
	 * @generated
	 */
	LambdaParameter getOwnedResult();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.LambdaType#getOwnedResult <em>Owned Result</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owned Result</em>' containment reference.
	 * @see #getOwnedResult()
	 * @generated
	 */
	void setOwnedResult(LambdaParameter value);

	@Deprecated
	Type getResultType();

	@Deprecated
	Type getContextType();
} // LambdaType
