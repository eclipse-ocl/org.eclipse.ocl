/*******************************************************************************
 * Copyright (c) 2005, 2018 IBM Corporation, Zeligsoft Inc., and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   Zeligsoft - Bug 243976
 *******************************************************************************/
package org.eclipse.ocl.cst;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tuple Type CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.cst.TupleTypeCS#getVariables <em>Variables</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.cst.CSTPackage#getTupleTypeCS()
 * @model
 * @generated
 */
public interface TupleTypeCS
		extends TypeCS {

	/**
	 * Returns the value of the '<em><b>Variables</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.cst.VariableCS}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variables</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variables</em>' containment reference list.
	 * @see org.eclipse.ocl.cst.CSTPackage#getTupleTypeCS_Variables()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<VariableCS> getVariables();

} // TupleTypeCS
