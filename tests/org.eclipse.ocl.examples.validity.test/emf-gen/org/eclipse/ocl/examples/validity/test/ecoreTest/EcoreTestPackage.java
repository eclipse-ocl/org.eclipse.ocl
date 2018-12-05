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
package org.eclipse.ocl.examples.validity.test.ecoreTest;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.ocl.examples.validity.test.ecoreTest2.EcoreTest2Package;

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
 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.EcoreTestFactory
 * @model kind="package"
 * @generated
 */
public interface EcoreTestPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ecoreTest"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://eclipse.org/ocl/debug/test/ecoreTest"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "ecoreTest"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EcoreTestPackage eINSTANCE = org.eclipse.ocl.examples.validity.test.ecoreTest.impl.EcoreTestPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.validity.test.ecoreTest.impl.Eclass1Impl <em>Eclass1</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.impl.Eclass1Impl
	 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.impl.EcoreTestPackageImpl#getEclass1()
	 * @generated
	 */
	int ECLASS1 = 0;

	/**
	 * The feature id for the '<em><b>EAttribute1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECLASS1__EATTRIBUTE1 = 0;

	/**
	 * The feature id for the '<em><b>EAttribute2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECLASS1__EATTRIBUTE2 = 1;

	/**
	 * The feature id for the '<em><b>Classes2</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECLASS1__CLASSES2 = 2;

	/**
	 * The number of structural features of the '<em>Eclass1</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECLASS1_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Eclass1</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECLASS1_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.validity.test.ecoreTest.impl.EClass2Impl <em>EClass2</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.impl.EClass2Impl
	 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.impl.EcoreTestPackageImpl#getEClass2()
	 * @generated
	 */
	int ECLASS2 = 1;

	/**
	 * The feature id for the '<em><b>EAttribute3</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECLASS2__EATTRIBUTE3 = 0;

	/**
	 * The feature id for the '<em><b>Classes3</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECLASS2__CLASSES3 = 1;

	/**
	 * The feature id for the '<em><b>EAttribute4</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECLASS2__EATTRIBUTE4 = 2;

	/**
	 * The number of structural features of the '<em>EClass2</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECLASS2_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>EClass2</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECLASS2_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.validity.test.ecoreTest.impl.EClass3Impl <em>EClass3</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.impl.EClass3Impl
	 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.impl.EcoreTestPackageImpl#getEClass3()
	 * @generated
	 */
	int ECLASS3 = 2;

	/**
	 * The feature id for the '<em><b>EAttribute5</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECLASS3__EATTRIBUTE5 = EcoreTest2Package.ECLASS5__EATTRIBUTE5;

	/**
	 * The number of structural features of the '<em>EClass3</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECLASS3_FEATURE_COUNT = EcoreTest2Package.ECLASS5_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>EClass3</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECLASS3_OPERATION_COUNT = EcoreTest2Package.ECLASS5_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.validity.test.ecoreTest.Eclass1 <em>Eclass1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Eclass1</em>'.
	 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.Eclass1
	 * @generated
	 */
	EClass getEclass1();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.validity.test.ecoreTest.Eclass1#getEAttribute1 <em>EAttribute1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>EAttribute1</em>'.
	 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.Eclass1#getEAttribute1()
	 * @see #getEclass1()
	 * @generated
	 */
	EAttribute getEclass1_EAttribute1();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.validity.test.ecoreTest.Eclass1#getEAttribute2 <em>EAttribute2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>EAttribute2</em>'.
	 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.Eclass1#getEAttribute2()
	 * @see #getEclass1()
	 * @generated
	 */
	EAttribute getEclass1_EAttribute2();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.validity.test.ecoreTest.Eclass1#getClasses2 <em>Classes2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Classes2</em>'.
	 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.Eclass1#getClasses2()
	 * @see #getEclass1()
	 * @generated
	 */
	EReference getEclass1_Classes2();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.validity.test.ecoreTest.EClass2 <em>EClass2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EClass2</em>'.
	 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.EClass2
	 * @generated
	 */
	EClass getEClass2();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.validity.test.ecoreTest.EClass2#getEAttribute3 <em>EAttribute3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>EAttribute3</em>'.
	 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.EClass2#getEAttribute3()
	 * @see #getEClass2()
	 * @generated
	 */
	EAttribute getEClass2_EAttribute3();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.validity.test.ecoreTest.EClass2#getClasses3 <em>Classes3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Classes3</em>'.
	 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.EClass2#getClasses3()
	 * @see #getEClass2()
	 * @generated
	 */
	EReference getEClass2_Classes3();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.validity.test.ecoreTest.EClass2#getEAttribute4 <em>EAttribute4</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>EAttribute4</em>'.
	 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.EClass2#getEAttribute4()
	 * @see #getEClass2()
	 * @generated
	 */
	EAttribute getEClass2_EAttribute4();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.validity.test.ecoreTest.EClass3 <em>EClass3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EClass3</em>'.
	 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.EClass3
	 * @generated
	 */
	EClass getEClass3();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EcoreTestFactory getEcoreTestFactory();

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
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.validity.test.ecoreTest.impl.Eclass1Impl <em>Eclass1</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.impl.Eclass1Impl
		 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.impl.EcoreTestPackageImpl#getEclass1()
		 * @generated
		 */
		EClass ECLASS1 = eINSTANCE.getEclass1();

		/**
		 * The meta object literal for the '<em><b>EAttribute1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ECLASS1__EATTRIBUTE1 = eINSTANCE.getEclass1_EAttribute1();

		/**
		 * The meta object literal for the '<em><b>EAttribute2</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ECLASS1__EATTRIBUTE2 = eINSTANCE.getEclass1_EAttribute2();

		/**
		 * The meta object literal for the '<em><b>Classes2</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ECLASS1__CLASSES2 = eINSTANCE.getEclass1_Classes2();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.validity.test.ecoreTest.impl.EClass2Impl <em>EClass2</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.impl.EClass2Impl
		 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.impl.EcoreTestPackageImpl#getEClass2()
		 * @generated
		 */
		EClass ECLASS2 = eINSTANCE.getEClass2();

		/**
		 * The meta object literal for the '<em><b>EAttribute3</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ECLASS2__EATTRIBUTE3 = eINSTANCE.getEClass2_EAttribute3();

		/**
		 * The meta object literal for the '<em><b>Classes3</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ECLASS2__CLASSES3 = eINSTANCE.getEClass2_Classes3();

		/**
		 * The meta object literal for the '<em><b>EAttribute4</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ECLASS2__EATTRIBUTE4 = eINSTANCE.getEClass2_EAttribute4();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.validity.test.ecoreTest.impl.EClass3Impl <em>EClass3</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.impl.EClass3Impl
		 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.impl.EcoreTestPackageImpl#getEClass3()
		 * @generated
		 */
		EClass ECLASS3 = eINSTANCE.getEClass3();

	}

} //EcoreTestPackage
