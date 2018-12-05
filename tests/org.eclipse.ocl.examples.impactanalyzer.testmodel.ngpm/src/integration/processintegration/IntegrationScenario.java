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
package integration.processintegration;

import modelmanagement.NamedElement;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Integration Scenario</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link integration.processintegration.IntegrationScenario#getInteractions <em>Interactions</em>}</li>
 * </ul>
 * </p>
 *
 * @see integration.processintegration.ProcessintegrationPackage#getIntegrationScenario()
 * @model
 * @generated
 */
public interface IntegrationScenario extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Interactions</b></em>' containment reference list.
	 * The list contents are of type {@link integration.processintegration.ProcessComponentInteraction}.
	 * It is bidirectional and its opposite is '{@link integration.processintegration.ProcessComponentInteraction#getUses <em>Uses</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interactions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interactions</em>' containment reference list.
	 * @see integration.processintegration.ProcessintegrationPackage#getIntegrationScenario_Interactions()
	 * @see integration.processintegration.ProcessComponentInteraction#getUses
	 * @model opposite="uses" containment="true" resolveProxies="true" required="true"
	 * @generated
	 */
	EList<ProcessComponentInteraction> getInteractions();

} // IntegrationScenario
