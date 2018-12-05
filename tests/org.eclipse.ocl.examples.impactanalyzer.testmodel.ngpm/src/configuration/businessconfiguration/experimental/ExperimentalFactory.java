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
package configuration.businessconfiguration.experimental;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see configuration.businessconfiguration.experimental.ExperimentalPackage
 * @generated
 */
public interface ExperimentalFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ExperimentalFactory eINSTANCE = configuration.businessconfiguration.experimental.impl.ExperimentalFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Package Use</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Package Use</em>'.
	 * @generated
	 */
	PackageUse createPackageUse();

	/**
	 * Returns a new object of class '<em>Configuration Entity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Configuration Entity</em>'.
	 * @generated
	 */
	ConfigurationEntity createConfigurationEntity();

	/**
	 * Returns a new object of class '<em>Configuration Setting</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Configuration Setting</em>'.
	 * @generated
	 */
	ConfigurationSetting createConfigurationSetting();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ExperimentalPackage getExperimentalPackage();

} //ExperimentalFactory
