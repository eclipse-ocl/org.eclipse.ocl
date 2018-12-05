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
package modelmanagement.deploymentunits;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see modelmanagement.deploymentunits.DeploymentunitsPackage
 * @generated
 */
public interface DeploymentunitsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DeploymentunitsFactory eINSTANCE = modelmanagement.deploymentunits.impl.DeploymentunitsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Deployment Unit</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Deployment Unit</em>'.
	 * @generated
	 */
	DeploymentUnit createDeploymentUnit();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	DeploymentunitsPackage getDeploymentunitsPackage();

} //DeploymentunitsFactory
