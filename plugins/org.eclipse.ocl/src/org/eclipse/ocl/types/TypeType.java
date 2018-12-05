/*******************************************************************************
 * Copyright (c) 2006, 2018 IBM Corporation, Zeligsoft Inc., and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   IBM - Initial API and implementation
 *   Zeligsoft - Bug 207365
 *******************************************************************************/
package org.eclipse.ocl.types;

import org.eclipse.ocl.utilities.PredefinedType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.types.TypeType#getReferredType <em>Referred Type</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.types.TypesPackage#getTypeType()
 * @model
 * @generated
 */
public interface TypeType<C, O>
		extends PredefinedType<O> {

	String SINGLETON_NAME = "OclType"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Referred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referred Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referred Type</em>' reference.
	 * @see org.eclipse.ocl.types.TypesPackage#getTypeType_ReferredType()
	 * @model kind="reference" required="true" suppressedSetVisibility="true"
	 * @generated
	 */
	C getReferredType();

} // TypeType
