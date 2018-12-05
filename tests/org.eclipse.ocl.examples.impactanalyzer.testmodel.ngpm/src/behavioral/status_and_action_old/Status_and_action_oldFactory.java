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
package behavioral.status_and_action_old;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see behavioral.status_and_action_old.Status_and_action_oldPackage
 * @generated
 */
public interface Status_and_action_oldFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Status_and_action_oldFactory eINSTANCE = behavioral.status_and_action_old.impl.Status_and_action_oldFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>SAM Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>SAM Action</em>'.
	 * @generated
	 */
	SAMAction createSAMAction();

	/**
	 * Returns a new object of class '<em>SAM Status Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>SAM Status Variable</em>'.
	 * @generated
	 */
	SAMStatusVariable createSAMStatusVariable();

	/**
	 * Returns a new object of class '<em>SAM Derivator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>SAM Derivator</em>'.
	 * @generated
	 */
	SAMDerivator createSAMDerivator();

	/**
	 * Returns a new object of class '<em>SAM Status Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>SAM Status Value</em>'.
	 * @generated
	 */
	SAMStatusValue createSAMStatusValue();

	/**
	 * Returns a new object of class '<em>SAM Status Schema</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>SAM Status Schema</em>'.
	 * @generated
	 */
	SAMStatusSchema createSAMStatusSchema();

	/**
	 * Returns a new object of class '<em>SAM Operator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>SAM Operator</em>'.
	 * @generated
	 */
	SAMOperator createSAMOperator();

	/**
	 * Returns a new object of class '<em>SAM Schema Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>SAM Schema Variable</em>'.
	 * @generated
	 */
	SAMSchemaVariable createSAMSchemaVariable();

	/**
	 * Returns a new object of class '<em>SAM Schema Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>SAM Schema Value</em>'.
	 * @generated
	 */
	SAMSchemaValue createSAMSchemaValue();

	/**
	 * Returns a new object of class '<em>SAM Schema Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>SAM Schema Action</em>'.
	 * @generated
	 */
	SAMSchemaAction createSAMSchemaAction();

	/**
	 * Returns a new object of class '<em>SAM Schema Derivator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>SAM Schema Derivator</em>'.
	 * @generated
	 */
	SAMSchemaDerivator createSAMSchemaDerivator();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	Status_and_action_oldPackage getStatus_and_action_oldPackage();

} //Status_and_action_oldFactory
