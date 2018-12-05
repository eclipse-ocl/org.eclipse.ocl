/*******************************************************************************
 * Copyright (c) 2009, 2018 SAP AG and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Axel Uhl - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.ecore.utilities;

import org.eclipse.ocl.ecore.OppositePropertyCallExp;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Visitor Extension</b></em>'.
 * @since 3.1
 * <!-- end-user-doc -->
 *
 *
 * @model interface="true" abstract="true"
 */
public interface VisitorExtension<T> {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model callExpRequired="true"
	 * @generated
	 */
	T visitOppositePropertyCallExp(OppositePropertyCallExp callExp);

} // VisitorExtension
