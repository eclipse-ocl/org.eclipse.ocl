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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Collection Range CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.cst.CollectionRangeCS#getLastExpressionCS <em>Last Expression CS</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.cst.CSTPackage#getCollectionRangeCS()
 * @model
 * @generated
 */
public interface CollectionRangeCS
		extends CollectionLiteralPartCS {

	/**
	 * Returns the value of the '<em><b>Last Expression CS</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Last Expression CS</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Last Expression CS</em>' containment reference.
	 * @see #setLastExpressionCS(OCLExpressionCS)
	 * @see org.eclipse.ocl.cst.CSTPackage#getCollectionRangeCS_LastExpressionCS()
	 * @model containment="true"
	 *        extendedMetaData="name='lastOclExpressionCS'"
	 * @generated
	 */
	OCLExpressionCS getLastExpressionCS();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.cst.CollectionRangeCS#getLastExpressionCS <em>Last Expression CS</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Expression CS</em>' containment reference.
	 * @see #getLastExpressionCS()
	 * @generated
	 */
	void setLastExpressionCS(OCLExpressionCS value);

} // CollectionRangeCS
