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
 * A representation of the model object '<em><b>Path Name CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.cst.PathNameCS#getSimpleNames <em>Simple Names</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.cst.CSTPackage#getPathNameCS()
 * @model
 * @generated
 */
public interface PathNameCS
		extends TypeCS {

	/**
	 * Returns the value of the '<em><b>Simple Names</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.cst.SimpleNameCS}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Simple Names</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * @since 3.0
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Simple Names</em>' containment reference list.
	 * @see org.eclipse.ocl.cst.CSTPackage#getPathNameCS_SimpleNames()
	 * @model containment="true"
	 * @generated
	 */
	EList<SimpleNameCS> getSimpleNames();

} // PathNameCS
