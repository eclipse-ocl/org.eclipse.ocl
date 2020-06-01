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
package org.eclipse.ocl.examples.xtext.tests.bug477283.a.asub.asubsub;

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
 * @see org.eclipse.ocl.examples.xtext.tests.bug477283.a.asub.asubsub.Bug477283AsubsubFactory
 * @model kind="package"
 * @generated
 */
public interface Bug477283AsubsubPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "asubsub";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/ocl/Bug477283asubsub";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "asubsub";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Bug477283AsubsubPackage eINSTANCE = org.eclipse.ocl.examples.xtext.tests.bug477283.a.asub.asubsub.impl.Bug477283AsubsubPackageImpl.init();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext.tests.bug477283.a.asub.asubsub.ASubSub <em>ASub Sub</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ASub Sub</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.bug477283.a.asub.asubsub.ASubSub
	 * @generated
	 */
	EClass getASubSub();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Bug477283AsubsubFactory getBug477283AsubsubFactory();

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
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext.tests.bug477283.a.asub.asubsub.impl.ASubSubImpl <em>ASub Sub</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext.tests.bug477283.a.asub.asubsub.impl.ASubSubImpl
		 * @see org.eclipse.ocl.examples.xtext.tests.bug477283.a.asub.asubsub.impl.Bug477283AsubsubPackageImpl#getASubSub()
		 * @generated
		 */
		EClass ASUB_SUB = eINSTANCE.getASubSub();

	}

} //Bug477283AsubsubPackage
