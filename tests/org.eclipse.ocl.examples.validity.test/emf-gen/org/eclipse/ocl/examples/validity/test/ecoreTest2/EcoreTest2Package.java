/*******************************************************************************
 * Copyright (c) 2014, 2018 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.validity.test.ecoreTest2;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.ocl.examples.validity.test.ecoreTest2.EcoreTest2Factory
 * @model kind="package"
 * @generated
 */
public interface EcoreTest2Package extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ecoreTest2"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://eclipse.org/ocl/debug/test/ecoreTest2"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "ecoreTest2"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EcoreTest2Package eINSTANCE = org.eclipse.ocl.examples.validity.test.ecoreTest2.impl.EcoreTest2PackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.validity.test.ecoreTest2.impl.Eclass5Impl <em>Eclass5</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.validity.test.ecoreTest2.impl.Eclass5Impl
	 * @see org.eclipse.ocl.examples.validity.test.ecoreTest2.impl.EcoreTest2PackageImpl#getEclass5()
	 * @generated
	 */
	int ECLASS5 = 0;

	/**
	 * The feature id for the '<em><b>EAttribute5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECLASS5__EATTRIBUTE5 = 0;

	/**
	 * The number of structural features of the '<em>Eclass5</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECLASS5_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Eclass5</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECLASS5_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.validity.test.ecoreTest2.Eclass5 <em>Eclass5</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Eclass5</em>'.
	 * @see org.eclipse.ocl.examples.validity.test.ecoreTest2.Eclass5
	 * @generated
	 */
	EClass getEclass5();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.validity.test.ecoreTest2.Eclass5#getEAttribute5 <em>EAttribute5</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>EAttribute5</em>'.
	 * @see org.eclipse.ocl.examples.validity.test.ecoreTest2.Eclass5#getEAttribute5()
	 * @see #getEclass5()
	 * @generated
	 */
	EAttribute getEclass5_EAttribute5();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EcoreTest2Factory getEcoreTest2Factory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.validity.test.ecoreTest2.impl.Eclass5Impl <em>Eclass5</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.validity.test.ecoreTest2.impl.Eclass5Impl
		 * @see org.eclipse.ocl.examples.validity.test.ecoreTest2.impl.EcoreTest2PackageImpl#getEclass5()
		 * @generated
		 */
		EClass ECLASS5 = eINSTANCE.getEclass5();

		/**
		 * The meta object literal for the '<em><b>EAttribute5</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ECLASS5__EATTRIBUTE5 = eINSTANCE.getEclass5_EAttribute5();

	}

} //EcoreTest2Package
