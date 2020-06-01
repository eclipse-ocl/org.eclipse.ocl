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
package org.eclipse.ocl.examples.xtext.tests.bug477283.b.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.ocl.examples.xtext.tests.bug477283.a.Bug477283APackage;
import org.eclipse.ocl.examples.xtext.tests.bug477283.a.asub.Bug477283subPackage;
import org.eclipse.ocl.examples.xtext.tests.bug477283.b.Bug477283BFactory;
import org.eclipse.ocl.examples.xtext.tests.bug477283.b.Bug477283BPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Bug477283BPackageImpl extends EPackageImpl implements Bug477283BPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bEClass = null;

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
	 * @see org.eclipse.ocl.examples.xtext.tests.bug477283.b.Bug477283BPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Bug477283BPackageImpl() {
		super(eNS_URI, Bug477283BFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link Bug477283BPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Bug477283BPackage init() {
		if (isInited) return (Bug477283BPackage)EPackage.Registry.INSTANCE.getEPackage(Bug477283BPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredBug477283BPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		Bug477283BPackageImpl theBug477283BPackage = registeredBug477283BPackage instanceof Bug477283BPackageImpl ? (Bug477283BPackageImpl)registeredBug477283BPackage : new Bug477283BPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		Bug477283APackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theBug477283BPackage.createPackageContents();

		// Initialize created meta-data
		theBug477283BPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theBug477283BPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Bug477283BPackage.eNS_URI, theBug477283BPackage);
		return theBug477283BPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getB() {
		return bEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getB_Ref() {
		return (EReference)bEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Bug477283BFactory getBug477283BFactory() {
		return (Bug477283BFactory)getEFactoryInstance();
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
		bEClass = createEClass(0);
		createEReference(bEClass, 0);
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
		Bug477283subPackage theBug477283subPackage = (Bug477283subPackage)EPackage.Registry.INSTANCE.getEPackage(Bug477283subPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(bEClass, org.eclipse.ocl.examples.xtext.tests.bug477283.b.B.class, "B", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getB_Ref(), theBug477283subPackage.getASub(), null, "ref", null, 0, 1, org.eclipse.ocl.examples.xtext.tests.bug477283.b.B.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //Bug477283BPackageImpl
