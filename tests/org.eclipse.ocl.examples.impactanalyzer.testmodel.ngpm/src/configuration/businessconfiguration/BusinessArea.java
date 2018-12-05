/*******************************************************************************
 * Copyright (c) 2009, 2018 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     SAP AG - initial API and implementation
 ******************************************************************************
 */
package configuration.businessconfiguration;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Business Area</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link configuration.businessconfiguration.BusinessArea#getBusinessPackage <em>Business Package</em>}</li>
 * </ul>
 * </p>
 *
 * @see configuration.businessconfiguration.BusinessconfigurationPackage#getBusinessArea()
 * @model
 * @generated
 */
public interface BusinessArea extends ConfigurationElement {
	/**
	 * Returns the value of the '<em><b>Business Package</b></em>' containment reference list.
	 * The list contents are of type {@link configuration.businessconfiguration.BusinessPackage}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Business Package</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Business Package</em>' containment reference list.
	 * @see configuration.businessconfiguration.BusinessconfigurationPackage#getBusinessArea_BusinessPackage()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<BusinessPackage> getBusinessPackage();

} // BusinessArea
