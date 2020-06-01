/*******************************************************************************
 * Copyright (c) 2016, 2020 Willink Transformations and others.
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
package org.eclipse.ocl.examples.xtext.tests.bug477283.b;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see org.eclipse.ocl.examples.xtext.tests.bug477283.b.Bug477283BFactory
 * @model kind="package"
 * @generated
 */
public interface Bug477283BPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "b";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/ocl/Bug477283b";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "b";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Bug477283BPackage eINSTANCE = org.eclipse.ocl.examples.xtext.tests.bug477283.b.impl.Bug477283BPackageImpl.init();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext.tests.bug477283.b.B <em>B</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>B</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.bug477283.b.B
	 * @generated
	 */
	EClass getB();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.xtext.tests.bug477283.b.B#getRef <em>Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Ref</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.bug477283.b.B#getRef()
	 * @see #getB()
	 * @generated
	 */
	EReference getB_Ref();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Bug477283BFactory getBug477283BFactory();

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
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext.tests.bug477283.b.impl.BImpl <em>B</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext.tests.bug477283.b.impl.BImpl
		 * @see org.eclipse.ocl.examples.xtext.tests.bug477283.b.impl.Bug477283BPackageImpl#getB()
		 * @generated
		 */
		EClass B = eINSTANCE.getB();

		/**
		 * The meta object literal for the '<em><b>Ref</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference B__REF = eINSTANCE.getB_Ref();

	}

} //Bug477283BPackage
