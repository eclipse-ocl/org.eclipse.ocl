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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Configuration Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link configuration.businessconfiguration.ConfigurationElement#getApplicableInContext <em>Applicable In Context</em>}</li>
 * </ul>
 * </p>
 *
 * @see configuration.businessconfiguration.BusinessconfigurationPackage#getConfigurationElement()
 * @model
 * @generated
 */
public interface ConfigurationElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Applicable In Context</b></em>' reference list.
	 * The list contents are of type {@link configuration.businessconfiguration.ScopingContext}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Applicable In Context</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Applicable In Context</em>' reference list.
	 * @see configuration.businessconfiguration.BusinessconfigurationPackage#getConfigurationElement_ApplicableInContext()
	 * @model
	 * @generated
	 */
	EList<ScopingContext> getApplicableInContext();

} // ConfigurationElement
