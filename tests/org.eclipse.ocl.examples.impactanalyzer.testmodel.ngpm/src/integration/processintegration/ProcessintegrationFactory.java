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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see integration.processintegration.ProcessintegrationPackage
 * @generated
 */
public interface ProcessintegrationFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ProcessintegrationFactory eINSTANCE = integration.processintegration.impl.ProcessintegrationFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Integration Scenario</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integration Scenario</em>'.
	 * @generated
	 */
	IntegrationScenario createIntegrationScenario();

	/**
	 * Returns a new object of class '<em>Process Component Interaction</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Process Component Interaction</em>'.
	 * @generated
	 */
	ProcessComponentInteraction createProcessComponentInteraction();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ProcessintegrationPackage getProcessintegrationPackage();

} //ProcessintegrationFactory
