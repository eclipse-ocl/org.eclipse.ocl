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
package company;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see company.CompanyPackage
 * @generated
 */
public interface CompanyFactory extends EFactory {
    /**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    CompanyFactory eINSTANCE = company.impl.CompanyFactoryImpl.init();

    /**
	 * Returns a new object of class '<em>Employee</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Employee</em>'.
	 * @generated
	 */
    Employee createEmployee();

    /**
	 * Returns a new object of class '<em>Department</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Department</em>'.
	 * @generated
	 */
    Department createDepartment();

    /**
	 * Returns a new object of class '<em>Freelance</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Freelance</em>'.
	 * @generated
	 */
    Freelance createFreelance();

    /**
	 * Returns a new object of class '<em>Division</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Division</em>'.
	 * @generated
	 */
    Division createDivision();

    /**
	 * Returns a new object of class '<em>Student</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Student</em>'.
	 * @generated
	 */
    Student createStudent();

    /**
	 * Returns a new object of class '<em>Company</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Company</em>'.
	 * @generated
	 */
    Company createCompany();

    /**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
    CompanyPackage getCompanyPackage();

} //CompanyFactory
