/**
 * <copyright>
 * 
 * Copyright (c) 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.ocl.control;

import org.eclipse.emf.ecore.EAttribute;
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
 * <!-- begin-model-doc -->
 * The control model provides ResourceSet-specific settings to control OCL validation and show OCL diagnostics.
 * <!-- end-model-doc -->
 * @see org.eclipse.ocl.control.ControlFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/OCL/Import ecore='http://www.eclipse.org/emf/2002/Ecore'"
 * @generated
 */
public interface ControlPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "control";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/ocl/1.0.0/Control";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "oclctl";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ControlPackage eINSTANCE = org.eclipse.ocl.control.impl.ControlPackageImpl.init();



	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.control.ControlElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see org.eclipse.ocl.control.ControlElement
	 * @generated
	 */
	EClass getControlElement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.control.ControlModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see org.eclipse.ocl.control.ControlModel
	 * @generated
	 */
	EClass getControlModel();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.control.ControlModel#getSettings <em>Settings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Settings</em>'.
	 * @see org.eclipse.ocl.control.ControlModel#getSettings()
	 * @see #getControlModel()
	 * @generated
	 */
	EReference getControlModel_Settings();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.control.ControlSettings <em>Settings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Settings</em>'.
	 * @see org.eclipse.ocl.control.ControlSettings
	 * @generated
	 */
	EClass getControlSettings();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.control.ControlSettings#isCacheIsValid <em>Cache Is Valid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cache Is Valid</em>'.
	 * @see org.eclipse.ocl.control.ControlSettings#isCacheIsValid()
	 * @see #getControlSettings()
	 * @generated
	 */
	EAttribute getControlSettings_CacheIsValid();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ControlFactory getControlFactory();

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
		 * The meta object literal for the '{@link org.eclipse.ocl.control.impl.ControlElementImpl <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.control.impl.ControlElementImpl
		 * @see org.eclipse.ocl.control.impl.ControlPackageImpl#getControlElement()
		 * @generated
		 */
		EClass CONTROL_ELEMENT = eINSTANCE.getControlElement();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.control.impl.ControlModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.control.impl.ControlModelImpl
		 * @see org.eclipse.ocl.control.impl.ControlPackageImpl#getControlModel()
		 * @generated
		 */
		EClass CONTROL_MODEL = eINSTANCE.getControlModel();

		/**
		 * The meta object literal for the '<em><b>Settings</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTROL_MODEL__SETTINGS = eINSTANCE.getControlModel_Settings();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.control.impl.ControlSettingsImpl <em>Settings</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.control.impl.ControlSettingsImpl
		 * @see org.eclipse.ocl.control.impl.ControlPackageImpl#getControlSettings()
		 * @generated
		 */
		EClass CONTROL_SETTINGS = eINSTANCE.getControlSettings();

		/**
		 * The meta object literal for the '<em><b>Cache Is Valid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTROL_SETTINGS__CACHE_IS_VALID = eINSTANCE.getControlSettings_CacheIsValid();

	}

} //ControlPackage
