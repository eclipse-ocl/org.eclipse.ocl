/**
 * <copyright>
 * 
 * Copyright (c) 2021 Willink Transformations and others.
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
package org.eclipse.ocl.control;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Settings</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.control.ControlSettings#isCacheIsValid <em>Cache Is Valid</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.control.ControlPackage#getControlSettings()
 * @model
 * @generated
 */
public interface ControlSettings extends ControlElement {
	/**
	 * Returns the value of the '<em><b>Cache Is Valid</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * When the cache is valid, allInstances/implicitOpposites re-sue previous values. Set invalid if a model change undermines this re-use.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cache Is Valid</em>' attribute.
	 * @see #setCacheIsValid(boolean)
	 * @see org.eclipse.ocl.control.ControlPackage#getControlSettings_CacheIsValid()
	 * @model default="false"
	 * @generated
	 */
	boolean isCacheIsValid();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.control.ControlSettings#isCacheIsValid <em>Cache Is Valid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cache Is Valid</em>' attribute.
	 * @see #isCacheIsValid()
	 * @generated
	 */
	void setCacheIsValid(boolean value);

} // ControlSettings
