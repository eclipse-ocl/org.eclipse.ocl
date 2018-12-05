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
package ap_runtime_constraints;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see ap_runtime_constraints.Ap_runtime_constraintsPackage
 * @generated
 */
public interface Ap_runtime_constraintsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Ap_runtime_constraintsFactory eINSTANCE = ap_runtime_constraints.impl.Ap_runtime_constraintsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Query Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Query Constraint</em>'.
	 * @generated
	 */
	QueryConstraint createQueryConstraint();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	Ap_runtime_constraintsPackage getAp_runtime_constraintsPackage();

} //Ap_runtime_constraintsFactory
