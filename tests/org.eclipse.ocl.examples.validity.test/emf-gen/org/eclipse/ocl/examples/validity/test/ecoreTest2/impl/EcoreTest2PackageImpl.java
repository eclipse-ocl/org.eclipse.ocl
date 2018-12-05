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
package org.eclipse.ocl.examples.validity.test.ecoreTest2.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.ocl.examples.validity.test.ecoreTest.EcoreTestPackage;
import org.eclipse.ocl.examples.validity.test.ecoreTest.impl.EcoreTestPackageImpl;
import org.eclipse.ocl.examples.validity.test.ecoreTest2.Eclass5;
import org.eclipse.ocl.examples.validity.test.ecoreTest2.EcoreTest2Factory;
import org.eclipse.ocl.examples.validity.test.ecoreTest2.EcoreTest2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EcoreTest2PackageImpl extends EPackageImpl implements EcoreTest2Package {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eclass5EClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.ocl.examples.validity.test.ecoreTest2.EcoreTest2Package#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private EcoreTest2PackageImpl() {
		super(eNS_URI, EcoreTest2Factory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link EcoreTest2Package#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static EcoreTest2Package init() {
		if (isInited) return (EcoreTest2Package)EPackage.Registry.INSTANCE.getEPackage(EcoreTest2Package.eNS_URI);

		// Obtain or create and register package
		EcoreTest2PackageImpl theEcoreTest2Package = (EcoreTest2PackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof EcoreTest2PackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new EcoreTest2PackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		EcoreTestPackageImpl theEcoreTestPackage = (EcoreTestPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(EcoreTestPackage.eNS_URI) instanceof EcoreTestPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(EcoreTestPackage.eNS_URI) : EcoreTestPackage.eINSTANCE);

		// Create package meta-data objects
		theEcoreTest2Package.createPackageContents();
		theEcoreTestPackage.createPackageContents();

		// Initialize created meta-data
		theEcoreTest2Package.initializePackageContents();
		theEcoreTestPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theEcoreTest2Package.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(EcoreTest2Package.eNS_URI, theEcoreTest2Package);
		return theEcoreTest2Package;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEclass5() {
		return eclass5EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEclass5_EAttribute5() {
		return (EAttribute)eclass5EClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EcoreTest2Factory getEcoreTest2Factory() {
		return (EcoreTest2Factory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		eclass5EClass = createEClass(ECLASS5);
		createEAttribute(eclass5EClass, ECLASS5__EATTRIBUTE5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(eclass5EClass, Eclass5.class, "Eclass5", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getEclass5_EAttribute5(), ecorePackage.getEString(), "eAttribute5", null, 0, 1, Eclass5.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		// Create resource
		createResource(eNS_URI);
	}

} //EcoreTest2PackageImpl
