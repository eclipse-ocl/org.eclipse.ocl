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
package dataaccess.analytics;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see dataaccess.analytics.AnalyticsPackage
 * @generated
 */
public interface AnalyticsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AnalyticsFactory eINSTANCE = dataaccess.analytics.impl.AnalyticsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dimension</em>'.
	 * @generated
	 */
	Dimension createDimension();

	/**
	 * Returns a new object of class '<em>Cell Set</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Cell Set</em>'.
	 * @generated
	 */
	CellSet createCellSet();

	/**
	 * Returns a new object of class '<em>Dimension Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dimension Expression</em>'.
	 * @generated
	 */
	DimensionExpression createDimensionExpression();

	/**
	 * Returns a new object of class '<em>Group By</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Group By</em>'.
	 * @generated
	 */
	GroupBy createGroupBy();

	/**
	 * Returns a new object of class '<em>Dimension Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dimension Definition</em>'.
	 * @generated
	 */
	DimensionDefinition createDimensionDefinition();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	AnalyticsPackage getAnalyticsPackage();

} //AnalyticsFactory
