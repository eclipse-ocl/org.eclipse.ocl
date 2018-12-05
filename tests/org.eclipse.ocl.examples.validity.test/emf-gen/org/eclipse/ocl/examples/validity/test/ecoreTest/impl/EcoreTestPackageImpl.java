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
package org.eclipse.ocl.examples.validity.test.ecoreTest.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.ocl.examples.validity.test.ecoreTest.EClass2;
import org.eclipse.ocl.examples.validity.test.ecoreTest.EClass3;
import org.eclipse.ocl.examples.validity.test.ecoreTest.Eclass1;
import org.eclipse.ocl.examples.validity.test.ecoreTest.EcoreTestFactory;
import org.eclipse.ocl.examples.validity.test.ecoreTest.EcoreTestPackage;
import org.eclipse.ocl.examples.validity.test.ecoreTest2.EcoreTest2Package;
import org.eclipse.ocl.examples.validity.test.ecoreTest2.impl.EcoreTest2PackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EcoreTestPackageImpl extends EPackageImpl implements EcoreTestPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eclass1EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eClass2EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eClass3EClass = null;

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
	 * @see org.eclipse.ocl.examples.validity.test.ecoreTest.EcoreTestPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private EcoreTestPackageImpl() {
		super(eNS_URI, EcoreTestFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link EcoreTestPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static EcoreTestPackage init() {
		if (isInited) return (EcoreTestPackage)EPackage.Registry.INSTANCE.getEPackage(EcoreTestPackage.eNS_URI);

		// Obtain or create and register package
		EcoreTestPackageImpl theEcoreTestPackage = (EcoreTestPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof EcoreTestPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new EcoreTestPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		EcoreTest2PackageImpl theEcoreTest2Package = (EcoreTest2PackageImpl)(EPackage.Registry.INSTANCE.getEPackage(EcoreTest2Package.eNS_URI) instanceof EcoreTest2PackageImpl ? EPackage.Registry.INSTANCE.getEPackage(EcoreTest2Package.eNS_URI) : EcoreTest2Package.eINSTANCE);

		// Create package meta-data objects
		theEcoreTestPackage.createPackageContents();
		theEcoreTest2Package.createPackageContents();

		// Initialize created meta-data
		theEcoreTestPackage.initializePackageContents();
		theEcoreTest2Package.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theEcoreTestPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(EcoreTestPackage.eNS_URI, theEcoreTestPackage);
		return theEcoreTestPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEclass1() {
		return eclass1EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEclass1_EAttribute1() {
		return (EAttribute)eclass1EClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEclass1_EAttribute2() {
		return (EAttribute)eclass1EClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEclass1_Classes2() {
		return (EReference)eclass1EClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEClass2() {
		return eClass2EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEClass2_EAttribute3() {
		return (EAttribute)eClass2EClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEClass2_Classes3() {
		return (EReference)eClass2EClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEClass2_EAttribute4() {
		return (EAttribute)eClass2EClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEClass3() {
		return eClass3EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EcoreTestFactory getEcoreTestFactory() {
		return (EcoreTestFactory)getEFactoryInstance();
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
		eclass1EClass = createEClass(ECLASS1);
		createEAttribute(eclass1EClass, ECLASS1__EATTRIBUTE1);
		createEAttribute(eclass1EClass, ECLASS1__EATTRIBUTE2);
		createEReference(eclass1EClass, ECLASS1__CLASSES2);

		eClass2EClass = createEClass(ECLASS2);
		createEAttribute(eClass2EClass, ECLASS2__EATTRIBUTE3);
		createEReference(eClass2EClass, ECLASS2__CLASSES3);
		createEAttribute(eClass2EClass, ECLASS2__EATTRIBUTE4);

		eClass3EClass = createEClass(ECLASS3);
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

		// Obtain other dependent packages
		EcoreTest2Package theEcoreTest2Package = (EcoreTest2Package)EPackage.Registry.INSTANCE.getEPackage(EcoreTest2Package.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		eClass3EClass.getESuperTypes().add(theEcoreTest2Package.getEclass5());

		// Initialize classes, features, and operations; add parameters
		initEClass(eclass1EClass, Eclass1.class, "Eclass1", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getEclass1_EAttribute1(), ecorePackage.getEString(), "eAttribute1", null, 0, 1, Eclass1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getEclass1_EAttribute2(), ecorePackage.getEString(), "eAttribute2", null, 0, 1, Eclass1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEclass1_Classes2(), this.getEClass2(), null, "classes2", null, 0, -1, Eclass1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(eClass2EClass, EClass2.class, "EClass2", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getEClass2_EAttribute3(), ecorePackage.getEShort(), "eAttribute3", null, 0, 1, EClass2.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getEClass2_Classes3(), this.getEClass3(), null, "classes3", null, 0, -1, EClass2.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getEClass2_EAttribute4(), ecorePackage.getEString(), "eAttribute4", null, 0, 1, EClass2.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(eClass3EClass, EClass3.class, "EClass3", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		// Create resource
		createResource(eNS_URI);
	}

} //EcoreTestPackageImpl
