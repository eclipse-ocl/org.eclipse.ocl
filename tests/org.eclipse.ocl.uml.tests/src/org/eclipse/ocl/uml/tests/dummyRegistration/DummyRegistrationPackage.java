/*******************************************************************************
 * Copyright (c) 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
/**
 */
package org.eclipse.ocl.uml.tests.dummyRegistration;

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
 * <!-- begin-model-doc -->
 * The dummy registration ensures that the EcorePlugin sees a genmodel and so establishes a URI mapping for this test plugin.
 * <!-- end-model-doc -->
 * @see org.eclipse.ocl.uml.tests.dummyRegistration.DummyRegistrationFactory
 * @model kind="package"
 * @generated
 */
public interface DummyRegistrationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "dummyRegistration"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/ocl/test/2018/DummyRegistration.ecore"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "dummy"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DummyRegistrationPackage eINSTANCE = org.eclipse.ocl.uml.tests.dummyRegistration.impl.DummyRegistrationPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.uml.tests.dummyRegistration.impl.DummyClassImpl <em>Dummy Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.uml.tests.dummyRegistration.impl.DummyClassImpl
	 * @see org.eclipse.ocl.uml.tests.dummyRegistration.impl.DummyRegistrationPackageImpl#getDummyClass()
	 * @generated
	 */
	int DUMMY_CLASS = 0;

	/**
	 * The number of structural features of the '<em>Dummy Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DUMMY_CLASS_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Dummy Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DUMMY_CLASS_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.uml.tests.dummyRegistration.DummyClass <em>Dummy Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dummy Class</em>'.
	 * @see org.eclipse.ocl.uml.tests.dummyRegistration.DummyClass
	 * @generated
	 */
	EClass getDummyClass();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DummyRegistrationFactory getDummyRegistrationFactory();

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
		 * The meta object literal for the '{@link org.eclipse.ocl.uml.tests.dummyRegistration.impl.DummyClassImpl <em>Dummy Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.uml.tests.dummyRegistration.impl.DummyClassImpl
		 * @see org.eclipse.ocl.uml.tests.dummyRegistration.impl.DummyRegistrationPackageImpl#getDummyClass()
		 * @generated
		 */
		EClass DUMMY_CLASS = eINSTANCE.getDummyClass();

	}

} //DummyRegistrationPackage
