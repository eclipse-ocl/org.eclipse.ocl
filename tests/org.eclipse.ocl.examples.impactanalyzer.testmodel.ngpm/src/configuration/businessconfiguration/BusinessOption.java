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
 * A representation of the model object '<em><b>Business Option</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link configuration.businessconfiguration.BusinessOption#getValueSet <em>Value Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see configuration.businessconfiguration.BusinessconfigurationPackage#getBusinessOption()
 * @model
 * @generated
 */
public interface BusinessOption extends ConfigurationElement {
	/**
	 * Returns the value of the '<em><b>Value Set</b></em>' reference list.
	 * The list contents are of type {@link configuration.businessconfiguration.ValueSet}.
	 * It is bidirectional and its opposite is '{@link configuration.businessconfiguration.ValueSet#getConfigurationBusinessOption <em>Configuration Business Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value Set</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Set</em>' reference list.
	 * @see configuration.businessconfiguration.BusinessconfigurationPackage#getBusinessOption_ValueSet()
	 * @see configuration.businessconfiguration.ValueSet#getConfigurationBusinessOption
	 * @model opposite="configurationBusinessOption"
	 * @generated
	 */
	EList<ValueSet> getValueSet();

} // BusinessOption
