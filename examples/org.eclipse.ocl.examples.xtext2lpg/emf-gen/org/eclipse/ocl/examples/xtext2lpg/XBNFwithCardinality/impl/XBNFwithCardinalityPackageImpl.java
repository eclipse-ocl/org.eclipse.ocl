/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage;

import org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl;

import org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.Alternatives;
import org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.CompoundElement;
import org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.MultiplicityElement;
import org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.OneOrMore;
import org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.Succession;
import org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.XBNFwithCardinalityFactory;
import org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.XBNFwithCardinalityPackage;
import org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.ZeroOrMore;
import org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.ZeroOrOne;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class XBNFwithCardinalityPackageImpl extends EPackageImpl implements XBNFwithCardinalityPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass alternativesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compoundElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass multiplicityElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oneOrMoreEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass successionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass zeroOrMoreEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass zeroOrOneEClass = null;

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
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.XBNFwithCardinalityPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private XBNFwithCardinalityPackageImpl() {
		super(eNS_URI, XBNFwithCardinalityFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link XBNFwithCardinalityPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static XBNFwithCardinalityPackage init() {
		if (isInited) return (XBNFwithCardinalityPackage)EPackage.Registry.INSTANCE.getEPackage(XBNFwithCardinalityPackage.eNS_URI);

		// Obtain or create and register package
		XBNFwithCardinalityPackageImpl theXBNFwithCardinalityPackage = (XBNFwithCardinalityPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof XBNFwithCardinalityPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new XBNFwithCardinalityPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		XBNFPackageImpl theXBNFPackage = (XBNFPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(XBNFPackage.eNS_URI) instanceof XBNFPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(XBNFPackage.eNS_URI) : XBNFPackage.eINSTANCE);

		// Create package meta-data objects
		theXBNFwithCardinalityPackage.createPackageContents();
		theXBNFPackage.createPackageContents();

		// Initialize created meta-data
		theXBNFwithCardinalityPackage.initializePackageContents();
		theXBNFPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theXBNFwithCardinalityPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(XBNFwithCardinalityPackage.eNS_URI, theXBNFwithCardinalityPackage);
		return theXBNFwithCardinalityPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAlternatives() {
		return alternativesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompoundElement() {
		return compoundElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompoundElement_Elements() {
		return (EReference)compoundElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMultiplicityElement() {
		return multiplicityElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMultiplicityElement_Element() {
		return (EReference)multiplicityElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOneOrMore() {
		return oneOrMoreEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSuccession() {
		return successionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getZeroOrMore() {
		return zeroOrMoreEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getZeroOrOne() {
		return zeroOrOneEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XBNFwithCardinalityFactory getXBNFwithCardinalityFactory() {
		return (XBNFwithCardinalityFactory)getEFactoryInstance();
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
		alternativesEClass = createEClass(ALTERNATIVES);

		compoundElementEClass = createEClass(COMPOUND_ELEMENT);
		createEReference(compoundElementEClass, COMPOUND_ELEMENT__ELEMENTS);

		multiplicityElementEClass = createEClass(MULTIPLICITY_ELEMENT);
		createEReference(multiplicityElementEClass, MULTIPLICITY_ELEMENT__ELEMENT);

		oneOrMoreEClass = createEClass(ONE_OR_MORE);

		successionEClass = createEClass(SUCCESSION);

		zeroOrMoreEClass = createEClass(ZERO_OR_MORE);

		zeroOrOneEClass = createEClass(ZERO_OR_ONE);
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
		XBNFPackage theXBNFPackage = (XBNFPackage)EPackage.Registry.INSTANCE.getEPackage(XBNFPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		alternativesEClass.getESuperTypes().add(this.getCompoundElement());
		compoundElementEClass.getESuperTypes().add(theXBNFPackage.getAbstractElement());
		multiplicityElementEClass.getESuperTypes().add(theXBNFPackage.getAbstractElement());
		oneOrMoreEClass.getESuperTypes().add(this.getMultiplicityElement());
		successionEClass.getESuperTypes().add(this.getCompoundElement());
		zeroOrMoreEClass.getESuperTypes().add(this.getMultiplicityElement());
		zeroOrOneEClass.getESuperTypes().add(this.getMultiplicityElement());

		// Initialize classes, features, and operations; add parameters
		initEClass(alternativesEClass, Alternatives.class, "Alternatives", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(compoundElementEClass, CompoundElement.class, "CompoundElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCompoundElement_Elements(), theXBNFPackage.getAbstractElement(), null, "elements", null, 1, -1, CompoundElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(multiplicityElementEClass, MultiplicityElement.class, "MultiplicityElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMultiplicityElement_Element(), theXBNFPackage.getAbstractElement(), null, "element", null, 1, 1, MultiplicityElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(oneOrMoreEClass, OneOrMore.class, "OneOrMore", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(successionEClass, Succession.class, "Succession", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(zeroOrMoreEClass, ZeroOrMore.class, "ZeroOrMore", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(zeroOrOneEClass, ZeroOrOne.class, "ZeroOrOne", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //XBNFwithCardinalityPackageImpl
