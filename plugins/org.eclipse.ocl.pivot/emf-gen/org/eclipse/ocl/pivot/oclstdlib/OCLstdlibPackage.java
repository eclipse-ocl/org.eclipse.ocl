/*******************************************************************************
 * Copyright (c) 2014, 2022 Willink Transformations and others.
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
package org.eclipse.ocl.pivot.oclstdlib;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.values.Bag;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.OrderedSet;
import org.eclipse.ocl.pivot.values.RealValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * @noreference This class is not intended to be referenced by clients.
 * <!-- end-user-doc -->
 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibFactory
 * @generated
 */
public class OCLstdlibPackage extends EPackageImpl {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNAME = "oclstdlib"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_URI = "http://www.eclipse.org/ocl/2015/Library"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_PREFIX = "oclstdlib"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("null")
	public static final @NonNull OCLstdlibPackage eINSTANCE = org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage.init();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bagEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass collectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass entryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mapEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclAnyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclComparableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclEnumerationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclInvalidEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclLambdaEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclMessageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclSelfEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclStateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclStereotypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclSummableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclTupleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclVoidEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass orderedCollectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass orderedSetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sequenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass setEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uniqueCollectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass entry_r_rEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType booleanEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType integerEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType realEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType stringEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType unlimitedNaturalEDataType = null;

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
	 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private OCLstdlibPackage() {
		super(eNS_URI, OCLstdlibFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link OCLstdlibPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static OCLstdlibPackage init() {
		if (isInited) return (OCLstdlibPackage)EPackage.Registry.INSTANCE.getEPackage(OCLstdlibPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredOCLstdlibPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		OCLstdlibPackage theOCLstdlibPackage = registeredOCLstdlibPackage instanceof OCLstdlibPackage ? (OCLstdlibPackage)registeredOCLstdlibPackage : new OCLstdlibPackage();

		isInited = true;

		// Create package meta-data objects
		theOCLstdlibPackage.createPackageContents();

		// Initialize created meta-data
		theOCLstdlibPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theOCLstdlibPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(OCLstdlibPackage.eNS_URI, theOCLstdlibPackage);
		return theOCLstdlibPackage;
	}


	/**
	 * Returns the meta object for class '{@link java.lang.Object <em>Ocl Comparable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Comparable</em>'.
	 * @see java.lang.Object
	 * @generated
	 */
	public EClass getOclComparable() {
		return oclComparableEClass;
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#_lt_(java.lang.Object) <em>lt </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>lt </em>' operation.
	 * @see java.lang.Object#_lt_(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclComparable___lt___Object() {
		return oclComparableEClass.getEOperations().get(3);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#_le_(java.lang.Object) <em>le </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>le </em>' operation.
	 * @see java.lang.Object#_le_(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclComparable___le___Object() {
		return oclComparableEClass.getEOperations().get(2);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#_gt_(java.lang.Object) <em>gt </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>gt </em>' operation.
	 * @see java.lang.Object#_gt_(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclComparable___gt___Object() {
		return oclComparableEClass.getEOperations().get(1);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#_ge_(java.lang.Object) <em>ge </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>ge </em>' operation.
	 * @see java.lang.Object#_ge_(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclComparable___ge___Object() {
		return oclComparableEClass.getEOperations().get(0);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#compareTo(java.lang.Object) <em>Compare To</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Compare To</em>' operation.
	 * @see java.lang.Object#compareTo(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclComparable__CompareTo__Object() {
		return oclComparableEClass.getEOperations().get(4);
	}


	/**
	 * Returns the meta object for class '{@link java.lang.Object <em>Ocl Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Element</em>'.
	 * @see java.lang.Object
	 * @generated
	 */
	public EClass getOclElement() {
		return oclElementEClass;
	}


	/**
	 * Returns the meta object for the reference '{@link java.lang.Object#getOclContainer <em>Ocl Container</em>}'.
	 * <!-- begin-user-doc -->
	 * @since 1.1
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Ocl Container</em>'.
	 * @see java.lang.Object#getOclContainer()
	 * @see #getOclElement()
	 * @generated
	 */
	public EReference getOclElement_OclContainer() {
		return (EReference)oclElementEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the reference list '{@link java.lang.Object#getOclContents <em>Ocl Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * @since 1.1
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Ocl Contents</em>'.
	 * @see java.lang.Object#getOclContents()
	 * @see #getOclElement()
	 * @generated
	 */
	public EReference getOclElement_OclContents() {
		return (EReference)oclElementEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclAsModelType(java.lang.Object) <em>Ocl As Model Type</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl As Model Type</em>' operation.
	 * @see java.lang.Object#oclAsModelType(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclElement__OclAsModelType__Object() {
		return oclElementEClass.getEOperations().get(0);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclBase() <em>Ocl Base</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Base</em>' operation.
	 * @see java.lang.Object#oclBase()
	 * @generated
	 */
	public EOperation getOclElement__OclBase() {
		return oclElementEClass.getEOperations().get(1);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclBase(java.lang.Object) <em>Ocl Base</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Base</em>' operation.
	 * @see java.lang.Object#oclBase(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclElement__OclBase__Object() {
		return oclElementEClass.getEOperations().get(2);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclContainer() <em>Ocl Container</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Container</em>' operation.
	 * @see java.lang.Object#oclContainer()
	 * @generated
	 */
	public EOperation getOclElement__OclContainer() {
		return oclElementEClass.getEOperations().get(3);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclContents() <em>Ocl Contents</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Contents</em>' operation.
	 * @see java.lang.Object#oclContents()
	 * @generated
	 */
	public EOperation getOclElement__OclContents() {
		return oclElementEClass.getEOperations().get(4);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclExtension(java.lang.Object) <em>Ocl Extension</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Extension</em>' operation.
	 * @see java.lang.Object#oclExtension(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclElement__OclExtension__Object() {
		return oclElementEClass.getEOperations().get(5);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclExtensions() <em>Ocl Extensions</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Extensions</em>' operation.
	 * @see java.lang.Object#oclExtensions()
	 * @generated
	 */
	public EOperation getOclElement__OclExtensions() {
		return oclElementEClass.getEOperations().get(6);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclExtensions(java.lang.Object) <em>Ocl Extensions</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Extensions</em>' operation.
	 * @see java.lang.Object#oclExtensions(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclElement__OclExtensions__Object() {
		return oclElementEClass.getEOperations().get(7);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclIsModelKindOf(java.lang.Object) <em>Ocl Is Model Kind Of</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Is Model Kind Of</em>' operation.
	 * @see java.lang.Object#oclIsModelKindOf(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclElement__OclIsModelKindOf__Object() {
		return oclElementEClass.getEOperations().get(8);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclModelType() <em>Ocl Model Type</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Model Type</em>' operation.
	 * @see java.lang.Object#oclModelType()
	 * @generated
	 */
	public EOperation getOclElement__OclModelType() {
		return oclElementEClass.getEOperations().get(9);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclModelTypes() <em>Ocl Model Types</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Model Types</em>' operation.
	 * @see java.lang.Object#oclModelTypes()
	 * @generated
	 */
	public EOperation getOclElement__OclModelTypes() {
		return oclElementEClass.getEOperations().get(10);
	}


	/**
	 * Returns the meta object for class '{@link java.lang.Object <em>Ocl Enumeration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Enumeration</em>'.
	 * @see java.lang.Object
	 * @generated
	 */
	public EClass getOclEnumeration() {
		return oclEnumerationEClass;
	}


	/**
	 * Returns the meta object for class '{@link java.lang.Object <em>Ocl Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Any</em>'.
	 * @see java.lang.Object
	 * @generated
	 */
	public EClass getOclAny() {
		return oclAnyEClass;
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#_eq_(java.lang.Object) <em>eq </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>eq </em>' operation.
	 * @see java.lang.Object#_eq_(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclAny___eq___Object() {
		return oclAnyEClass.getEOperations().get(0);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#_ne_(java.lang.Object) <em>ne </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>ne </em>' operation.
	 * @see java.lang.Object#_ne_(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclAny___ne___Object() {
		return oclAnyEClass.getEOperations().get(1);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclAsSet() <em>Ocl As Set</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl As Set</em>' operation.
	 * @see java.lang.Object#oclAsSet()
	 * @generated
	 */
	public EOperation getOclAny__OclAsSet() {
		return oclAnyEClass.getEOperations().get(2);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclAsType(java.lang.Object) <em>Ocl As Type</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl As Type</em>' operation.
	 * @see java.lang.Object#oclAsType(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclAny__OclAsType__Object() {
		return oclAnyEClass.getEOperations().get(3);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclIsInState(java.lang.Object) <em>Ocl Is In State</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Is In State</em>' operation.
	 * @see java.lang.Object#oclIsInState(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclAny__OclIsInState__Object() {
		return oclAnyEClass.getEOperations().get(4);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclIsInvalid() <em>Ocl Is Invalid</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Is Invalid</em>' operation.
	 * @see java.lang.Object#oclIsInvalid()
	 * @generated
	 */
	public EOperation getOclAny__OclIsInvalid() {
		return oclAnyEClass.getEOperations().get(5);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclIsKindOf(java.lang.Object) <em>Ocl Is Kind Of</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Is Kind Of</em>' operation.
	 * @see java.lang.Object#oclIsKindOf(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclAny__OclIsKindOf__Object() {
		return oclAnyEClass.getEOperations().get(6);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclIsNew() <em>Ocl Is New</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Is New</em>' operation.
	 * @see java.lang.Object#oclIsNew()
	 * @generated
	 */
	public EOperation getOclAny__OclIsNew() {
		return oclAnyEClass.getEOperations().get(7);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclIsTypeOf(java.lang.Object) <em>Ocl Is Type Of</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Is Type Of</em>' operation.
	 * @see java.lang.Object#oclIsTypeOf(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclAny__OclIsTypeOf__Object() {
		return oclAnyEClass.getEOperations().get(8);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclIsUndefined() <em>Ocl Is Undefined</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Is Undefined</em>' operation.
	 * @see java.lang.Object#oclIsUndefined()
	 * @generated
	 */
	public EOperation getOclAny__OclIsUndefined() {
		return oclAnyEClass.getEOperations().get(9);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclLog() <em>Ocl Log</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Log</em>' operation.
	 * @see java.lang.Object#oclLog()
	 * @generated
	 */
	public EOperation getOclAny__OclLog() {
		return oclAnyEClass.getEOperations().get(10);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclLog(java.lang.String) <em>Ocl Log</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Log</em>' operation.
	 * @see java.lang.Object#oclLog(java.lang.String)
	 * @generated
	 */
	public EOperation getOclAny__OclLog__String() {
		return oclAnyEClass.getEOperations().get(11);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclType() <em>Ocl Type</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Type</em>' operation.
	 * @see java.lang.Object#oclType()
	 * @generated
	 */
	public EOperation getOclAny__OclType() {
		return oclAnyEClass.getEOperations().get(12);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclTypes() <em>Ocl Types</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Types</em>' operation.
	 * @see java.lang.Object#oclTypes()
	 * @generated
	 */
	public EOperation getOclAny__OclTypes() {
		return oclAnyEClass.getEOperations().get(13);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#toString() <em>To String</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>To String</em>' operation.
	 * @see java.lang.Object#toString()
	 * @generated
	 */
	public EOperation getOclAny__ToString() {
		return oclAnyEClass.getEOperations().get(14);
	}


	/**
	 * Returns the meta object for class '{@link java.lang.Object <em>Ocl Invalid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Invalid</em>'.
	 * @see java.lang.Object
	 * @generated
	 */
	public EClass getOclInvalid() {
		return oclInvalidEClass;
	}


	/**
	 * Returns the meta object for the reference '{@link java.lang.Object#getOclBadProperty <em>Ocl Bad Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Ocl Bad Property</em>'.
	 * @see java.lang.Object#getOclBadProperty()
	 * @see #getOclInvalid()
	 * @generated
	 */
	public EReference getOclInvalid_OclBadProperty() {
		return (EReference)oclInvalidEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#_ne_(java.lang.Object) <em>ne </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>ne </em>' operation.
	 * @see java.lang.Object#_ne_(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclInvalid___ne___Object() {
		return oclInvalidEClass.getEOperations().get(1);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#_eq_(java.lang.Object) <em>eq </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>eq </em>' operation.
	 * @see java.lang.Object#_eq_(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclInvalid___eq___Object() {
		return oclInvalidEClass.getEOperations().get(0);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#and(java.lang.Boolean) <em>And</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>And</em>' operation.
	 * @see java.lang.Object#and(java.lang.Boolean)
	 * @generated
	 */
	public EOperation getOclInvalid__And__Boolean() {
		return oclInvalidEClass.getEOperations().get(2);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#implies(java.lang.Boolean) <em>Implies</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Implies</em>' operation.
	 * @see java.lang.Object#implies(java.lang.Boolean)
	 * @generated
	 */
	public EOperation getOclInvalid__Implies__Boolean() {
		return oclInvalidEClass.getEOperations().get(3);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclAsSet() <em>Ocl As Set</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl As Set</em>' operation.
	 * @see java.lang.Object#oclAsSet()
	 * @generated
	 */
	public EOperation getOclInvalid__OclAsSet() {
		return oclInvalidEClass.getEOperations().get(4);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclAsType(java.lang.Object) <em>Ocl As Type</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl As Type</em>' operation.
	 * @see java.lang.Object#oclAsType(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclInvalid__OclAsType__Object() {
		return oclInvalidEClass.getEOperations().get(5);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclBadOperation() <em>Ocl Bad Operation</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Bad Operation</em>' operation.
	 * @see java.lang.Object#oclBadOperation()
	 * @generated
	 */
	public EOperation getOclInvalid__OclBadOperation() {
		return oclInvalidEClass.getEOperations().get(6);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclIsInvalid() <em>Ocl Is Invalid</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Is Invalid</em>' operation.
	 * @see java.lang.Object#oclIsInvalid()
	 * @generated
	 */
	public EOperation getOclInvalid__OclIsInvalid() {
		return oclInvalidEClass.getEOperations().get(7);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclIsKindOf(java.lang.Object) <em>Ocl Is Kind Of</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Is Kind Of</em>' operation.
	 * @see java.lang.Object#oclIsKindOf(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclInvalid__OclIsKindOf__Object() {
		return oclInvalidEClass.getEOperations().get(8);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclIsTypeOf(java.lang.Object) <em>Ocl Is Type Of</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Is Type Of</em>' operation.
	 * @see java.lang.Object#oclIsTypeOf(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclInvalid__OclIsTypeOf__Object() {
		return oclInvalidEClass.getEOperations().get(9);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclIsUndefined() <em>Ocl Is Undefined</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Is Undefined</em>' operation.
	 * @see java.lang.Object#oclIsUndefined()
	 * @generated
	 */
	public EOperation getOclInvalid__OclIsUndefined() {
		return oclInvalidEClass.getEOperations().get(10);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclType() <em>Ocl Type</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Type</em>' operation.
	 * @see java.lang.Object#oclType()
	 * @generated
	 */
	public EOperation getOclInvalid__OclType() {
		return oclInvalidEClass.getEOperations().get(11);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#or(java.lang.Boolean) <em>Or</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Or</em>' operation.
	 * @see java.lang.Object#or(java.lang.Boolean)
	 * @generated
	 */
	public EOperation getOclInvalid__Or__Boolean() {
		return oclInvalidEClass.getEOperations().get(12);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#toString() <em>To String</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>To String</em>' operation.
	 * @see java.lang.Object#toString()
	 * @generated
	 */
	public EOperation getOclInvalid__ToString() {
		return oclInvalidEClass.getEOperations().get(13);
	}


	/**
	 * Returns the meta object for class '{@link java.lang.Object <em>Ocl Lambda</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Lambda</em>'.
	 * @see java.lang.Object
	 * @generated
	 */
	public EClass getOclLambda() {
		return oclLambdaEClass;
	}


	/**
	 * Returns the meta object for class '{@link java.lang.Object <em>Ocl Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Message</em>'.
	 * @see java.lang.Object
	 * @generated
	 */
	public EClass getOclMessage() {
		return oclMessageEClass;
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#hasReturned() <em>Has Returned</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Has Returned</em>' operation.
	 * @see java.lang.Object#hasReturned()
	 * @generated
	 */
	public EOperation getOclMessage__HasReturned() {
		return oclMessageEClass.getEOperations().get(0);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#isOperationCall() <em>Is Operation Call</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Is Operation Call</em>' operation.
	 * @see java.lang.Object#isOperationCall()
	 * @generated
	 */
	public EOperation getOclMessage__IsOperationCall() {
		return oclMessageEClass.getEOperations().get(1);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#isSignalSent() <em>Is Signal Sent</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Is Signal Sent</em>' operation.
	 * @see java.lang.Object#isSignalSent()
	 * @generated
	 */
	public EOperation getOclMessage__IsSignalSent() {
		return oclMessageEClass.getEOperations().get(2);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#result() <em>Result</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Result</em>' operation.
	 * @see java.lang.Object#result()
	 * @generated
	 */
	public EOperation getOclMessage__Result() {
		return oclMessageEClass.getEOperations().get(3);
	}


	/**
	 * Returns the meta object for class '{@link java.lang.Object <em>Ocl Self</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Self</em>'.
	 * @see java.lang.Object
	 * @generated
	 */
	public EClass getOclSelf() {
		return oclSelfEClass;
	}


	/**
	 * Returns the meta object for class '{@link java.lang.Object <em>Ocl State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl State</em>'.
	 * @see java.lang.Object
	 * @generated
	 */
	public EClass getOclState() {
		return oclStateEClass;
	}


	/**
	 * Returns the meta object for class '{@link java.lang.Object <em>Ocl Stereotype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Stereotype</em>'.
	 * @see java.lang.Object
	 * @generated
	 */
	public EClass getOclStereotype() {
		return oclStereotypeEClass;
	}


	/**
	 * Returns the meta object for class '{@link java.lang.Object <em>Ocl Summable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Summable</em>'.
	 * @see java.lang.Object
	 * @generated
	 */
	public EClass getOclSummable() {
		return oclSummableEClass;
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#sum(java.lang.Object) <em>Sum</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Sum</em>' operation.
	 * @see java.lang.Object#sum(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclSummable__Sum__Object() {
		return oclSummableEClass.getEOperations().get(0);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#zero() <em>Zero</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Zero</em>' operation.
	 * @see java.lang.Object#zero()
	 * @generated
	 */
	public EOperation getOclSummable__Zero() {
		return oclSummableEClass.getEOperations().get(1);
	}


	/**
	 * Returns the meta object for class '{@link java.lang.Object <em>Ocl Tuple</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Tuple</em>'.
	 * @see java.lang.Object
	 * @generated
	 */
	public EClass getOclTuple() {
		return oclTupleEClass;
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#_ne_(java.lang.Object) <em>ne </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>ne </em>' operation.
	 * @see java.lang.Object#_ne_(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclTuple___ne___Object() {
		return oclTupleEClass.getEOperations().get(1);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#_eq_(java.lang.Object) <em>eq </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>eq </em>' operation.
	 * @see java.lang.Object#_eq_(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclTuple___eq___Object() {
		return oclTupleEClass.getEOperations().get(0);
	}


	/**
	 * Returns the meta object for class '{@link java.lang.Object <em>Ocl Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Type</em>'.
	 * @see java.lang.Object
	 * @generated
	 */
	public EClass getOclType() {
		return oclTypeEClass;
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#conformsTo(java.lang.Object) <em>Conforms To</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Conforms To</em>' operation.
	 * @see java.lang.Object#conformsTo(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclType__ConformsTo__Object() {
		return oclTypeEClass.getEOperations().get(0);
	}


	/**
	 * Returns the meta object for class '{@link java.lang.Object <em>Ocl Void</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Void</em>'.
	 * @see java.lang.Object
	 * @generated
	 */
	public EClass getOclVoid() {
		return oclVoidEClass;
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#_add_(java.lang.String) <em>add </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>add </em>' operation.
	 * @see java.lang.Object#_add_(java.lang.String)
	 * @generated
	 */
	public EOperation getOclVoid___add___String() {
		return oclVoidEClass.getEOperations().get(0);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#_ne_(java.lang.Object) <em>ne </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>ne </em>' operation.
	 * @see java.lang.Object#_ne_(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclVoid___ne___Object() {
		return oclVoidEClass.getEOperations().get(2);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#_eq_(java.lang.Object) <em>eq </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>eq </em>' operation.
	 * @see java.lang.Object#_eq_(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclVoid___eq___Object() {
		return oclVoidEClass.getEOperations().get(1);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#and(java.lang.Boolean) <em>And</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>And</em>' operation.
	 * @see java.lang.Object#and(java.lang.Boolean)
	 * @generated
	 */
	public EOperation getOclVoid__And__Boolean() {
		return oclVoidEClass.getEOperations().get(3);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#concat(java.lang.String) <em>Concat</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Concat</em>' operation.
	 * @see java.lang.Object#concat(java.lang.String)
	 * @generated
	 */
	public EOperation getOclVoid__Concat__String() {
		return oclVoidEClass.getEOperations().get(4);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#implies(java.lang.Boolean) <em>Implies</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Implies</em>' operation.
	 * @see java.lang.Object#implies(java.lang.Boolean)
	 * @generated
	 */
	public EOperation getOclVoid__Implies__Boolean() {
		return oclVoidEClass.getEOperations().get(5);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#not() <em>Not</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Not</em>' operation.
	 * @see java.lang.Object#not()
	 * @generated
	 */
	public EOperation getOclVoid__Not() {
		return oclVoidEClass.getEOperations().get(6);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclAsSet() <em>Ocl As Set</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl As Set</em>' operation.
	 * @see java.lang.Object#oclAsSet()
	 * @generated
	 */
	public EOperation getOclVoid__OclAsSet() {
		return oclVoidEClass.getEOperations().get(7);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclAsType(java.lang.Object) <em>Ocl As Type</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl As Type</em>' operation.
	 * @see java.lang.Object#oclAsType(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclVoid__OclAsType__Object() {
		return oclVoidEClass.getEOperations().get(8);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclIsInvalid() <em>Ocl Is Invalid</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Is Invalid</em>' operation.
	 * @see java.lang.Object#oclIsInvalid()
	 * @generated
	 */
	public EOperation getOclVoid__OclIsInvalid() {
		return oclVoidEClass.getEOperations().get(9);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclIsKindOf(java.lang.Object) <em>Ocl Is Kind Of</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Is Kind Of</em>' operation.
	 * @see java.lang.Object#oclIsKindOf(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclVoid__OclIsKindOf__Object() {
		return oclVoidEClass.getEOperations().get(10);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclIsTypeOf(java.lang.Object) <em>Ocl Is Type Of</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Is Type Of</em>' operation.
	 * @see java.lang.Object#oclIsTypeOf(java.lang.Object)
	 * @generated
	 */
	public EOperation getOclVoid__OclIsTypeOf__Object() {
		return oclVoidEClass.getEOperations().get(11);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclIsUndefined() <em>Ocl Is Undefined</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Is Undefined</em>' operation.
	 * @see java.lang.Object#oclIsUndefined()
	 * @generated
	 */
	public EOperation getOclVoid__OclIsUndefined() {
		return oclVoidEClass.getEOperations().get(12);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclType() <em>Ocl Type</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Type</em>' operation.
	 * @see java.lang.Object#oclType()
	 * @generated
	 */
	public EOperation getOclVoid__OclType() {
		return oclVoidEClass.getEOperations().get(13);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#oclTypes() <em>Ocl Types</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ocl Types</em>' operation.
	 * @see java.lang.Object#oclTypes()
	 * @generated
	 */
	public EOperation getOclVoid__OclTypes() {
		return oclVoidEClass.getEOperations().get(14);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#or(java.lang.Boolean) <em>Or</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Or</em>' operation.
	 * @see java.lang.Object#or(java.lang.Boolean)
	 * @generated
	 */
	public EOperation getOclVoid__Or__Boolean() {
		return oclVoidEClass.getEOperations().get(15);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#toString() <em>To String</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>To String</em>' operation.
	 * @see java.lang.Object#toString()
	 * @generated
	 */
	public EOperation getOclVoid__ToString() {
		return oclVoidEClass.getEOperations().get(16);
	}


	/**
	 * Returns the meta object for the '{@link java.lang.Object#xor(java.lang.Boolean) <em>Xor</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Xor</em>' operation.
	 * @see java.lang.Object#xor(java.lang.Boolean)
	 * @generated
	 */
	public EOperation getOclVoid__Xor__Boolean() {
		return oclVoidEClass.getEOperations().get(17);
	}


	/**
	 * Returns the meta object for class '{@link java.util.Collection <em>Ordered Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ordered Collection</em>'.
	 * @see java.util.Collection
	 * @generated
	 */
	public EClass getOrderedCollection() {
		return orderedCollectionEClass;
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#at(org.eclipse.ocl.pivot.values.IntegerValue) <em>At</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>At</em>' operation.
	 * @see java.util.Collection#at(org.eclipse.ocl.pivot.values.IntegerValue)
	 * @generated
	 */
	public EOperation getOrderedCollection__At__IntegerValue() {
		return orderedCollectionEClass.getEOperations().get(0);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#first() <em>First</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>First</em>' operation.
	 * @see java.util.Collection#first()
	 * @generated
	 */
	public EOperation getOrderedCollection__First() {
		return orderedCollectionEClass.getEOperations().get(1);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#gather1(java.lang.Object) <em>Gather1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Gather1</em>' operation.
	 * @see java.util.Collection#gather1(java.lang.Object)
	 * @generated
	 */
	public EOperation getOrderedCollection__Gather1__Object() {
		return orderedCollectionEClass.getEOperations().get(2);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#indexOf(java.lang.Object) <em>Index Of</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Index Of</em>' operation.
	 * @see java.util.Collection#indexOf(java.lang.Object)
	 * @generated
	 */
	public EOperation getOrderedCollection__IndexOf__Object() {
		return orderedCollectionEClass.getEOperations().get(3);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#last() <em>Last</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Last</em>' operation.
	 * @see java.util.Collection#last()
	 * @generated
	 */
	public EOperation getOrderedCollection__Last() {
		return orderedCollectionEClass.getEOperations().get(4);
	}


	/**
	 * Returns the meta object for data type '{@link java.lang.Boolean <em>Boolean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Boolean</em>'.
	 * @see java.lang.Boolean
	 * @generated
	 */
	public EDataType getBoolean() {
		return booleanEDataType;
	}

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entry</em>'.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public EClass getEntry() {
		return entryEClass;
	}


	/**
	 * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getEntry()
	 * @generated
	 */
	public EReference getEntry_Key() {
		return (EReference)entryEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getEntry()
	 * @generated
	 */
	public EReference getEntry_Value() {
		return (EReference)entryEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.pivot.values.IntegerValue <em>Integer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Integer</em>'.
	 * @see org.eclipse.ocl.pivot.values.IntegerValue
	 * @generated
	 */
	public EDataType getInteger() {
		return integerEDataType;
	}

	/**
	 * Returns the meta object for class '{@link java.util.Map <em>Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Map</em>'.
	 * @see java.util.Map
	 * @generated
	 */
	public EClass getMap() {
		return mapEClass;
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#_ne_(java.lang.Object) <em>ne </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>ne </em>' operation.
	 * @see java.util.Map#_ne_(java.lang.Object)
	 * @generated
	 */
	public EOperation getMap___ne___Object() {
		return mapEClass.getEOperations().get(0);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#_eq_(java.lang.Object) <em>eq </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>eq </em>' operation.
	 * @see java.util.Map#_eq_(java.lang.Object)
	 * @generated
	 */
	public EOperation getMap___eq___Object() {
		return mapEClass.getEOperations().get(1);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#any1(java.lang.Object) <em>Any1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Any1</em>' operation.
	 * @see java.util.Map#any1(java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__Any1__Object() {
		return mapEClass.getEOperations().get(2);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#at(java.lang.Object) <em>At</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>At</em>' operation.
	 * @see java.util.Map#at(java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__At__Object() {
		return mapEClass.getEOperations().get(3);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#collect1(java.lang.Object) <em>Collect1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Collect1</em>' operation.
	 * @see java.util.Map#collect1(java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__Collect1__Object() {
		return mapEClass.getEOperations().get(4);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#collectBy1(java.lang.Object) <em>Collect By1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Collect By1</em>' operation.
	 * @see java.util.Map#collectBy1(java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__CollectBy1__Object() {
		return mapEClass.getEOperations().get(5);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#collectNested1(java.lang.Object) <em>Collect Nested1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Collect Nested1</em>' operation.
	 * @see java.util.Map#collectNested1(java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__CollectNested1__Object() {
		return mapEClass.getEOperations().get(6);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#excludes(java.lang.Object) <em>Excludes</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Excludes</em>' operation.
	 * @see java.util.Map#excludes(java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__Excludes__Object() {
		return mapEClass.getEOperations().get(7);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#excludes(java.lang.Object, java.lang.Object) <em>Excludes</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Excludes</em>' operation.
	 * @see java.util.Map#excludes(java.lang.Object, java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__Excludes__Object_Object() {
		return mapEClass.getEOperations().get(8);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#excludesAll(java.util.Collection) <em>Excludes All</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Excludes All</em>' operation.
	 * @see java.util.Map#excludesAll(java.util.Collection)
	 * @generated
	 */
	public EOperation getMap__ExcludesAll__Collection() {
		return mapEClass.getEOperations().get(9);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#excludesMap(org.eclipse.emf.common.util.EMap) <em>Excludes Map</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Excludes Map</em>' operation.
	 * @see java.util.Map#excludesMap(org.eclipse.emf.common.util.EMap)
	 * @generated
	 */
	public EOperation getMap__ExcludesMap__EMap() {
		return mapEClass.getEOperations().get(10);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#excludesValue(java.lang.Object) <em>Excludes Value</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Excludes Value</em>' operation.
	 * @see java.util.Map#excludesValue(java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__ExcludesValue__Object() {
		return mapEClass.getEOperations().get(11);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#excluding(java.lang.Object) <em>Excluding</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Excluding</em>' operation.
	 * @see java.util.Map#excluding(java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__Excluding__Object() {
		return mapEClass.getEOperations().get(12);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#excluding(java.lang.Object, java.lang.Object) <em>Excluding</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Excluding</em>' operation.
	 * @see java.util.Map#excluding(java.lang.Object, java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__Excluding__Object_Object() {
		return mapEClass.getEOperations().get(13);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#excludingAll(java.util.Collection) <em>Excluding All</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Excluding All</em>' operation.
	 * @see java.util.Map#excludingAll(java.util.Collection)
	 * @generated
	 */
	public EOperation getMap__ExcludingAll__Collection() {
		return mapEClass.getEOperations().get(14);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#excludingMap(org.eclipse.emf.common.util.EMap) <em>Excluding Map</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Excluding Map</em>' operation.
	 * @see java.util.Map#excludingMap(org.eclipse.emf.common.util.EMap)
	 * @generated
	 */
	public EOperation getMap__ExcludingMap__EMap() {
		return mapEClass.getEOperations().get(15);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#exists1(java.lang.Object) <em>Exists1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Exists1</em>' operation.
	 * @see java.util.Map#exists1(java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__Exists1__Object() {
		return mapEClass.getEOperations().get(16);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#exists2(java.lang.Object) <em>Exists2</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Exists2</em>' operation.
	 * @see java.util.Map#exists2(java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__Exists2__Object() {
		return mapEClass.getEOperations().get(17);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#exists3(java.lang.Object) <em>Exists3</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Exists3</em>' operation.
	 * @see java.util.Map#exists3(java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__Exists3__Object() {
		return mapEClass.getEOperations().get(18);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#forAll1(java.lang.Object) <em>For All1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>For All1</em>' operation.
	 * @see java.util.Map#forAll1(java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__ForAll1__Object() {
		return mapEClass.getEOperations().get(19);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#forAll2(java.lang.Object) <em>For All2</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>For All2</em>' operation.
	 * @see java.util.Map#forAll2(java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__ForAll2__Object() {
		return mapEClass.getEOperations().get(20);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#forAll3(java.lang.Object) <em>For All3</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>For All3</em>' operation.
	 * @see java.util.Map#forAll3(java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__ForAll3__Object() {
		return mapEClass.getEOperations().get(21);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#gather1(java.lang.Object) <em>Gather1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Gather1</em>' operation.
	 * @see java.util.Map#gather1(java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__Gather1__Object() {
		return mapEClass.getEOperations().get(22);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#includes(java.lang.Object) <em>Includes</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Includes</em>' operation.
	 * @see java.util.Map#includes(java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__Includes__Object() {
		return mapEClass.getEOperations().get(23);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#includes(java.lang.Object, java.lang.Object) <em>Includes</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Includes</em>' operation.
	 * @see java.util.Map#includes(java.lang.Object, java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__Includes__Object_Object() {
		return mapEClass.getEOperations().get(24);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#includesAll(java.util.Collection) <em>Includes All</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Includes All</em>' operation.
	 * @see java.util.Map#includesAll(java.util.Collection)
	 * @generated
	 */
	public EOperation getMap__IncludesAll__Collection() {
		return mapEClass.getEOperations().get(25);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#includesMap(org.eclipse.emf.common.util.EMap) <em>Includes Map</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Includes Map</em>' operation.
	 * @see java.util.Map#includesMap(org.eclipse.emf.common.util.EMap)
	 * @generated
	 */
	public EOperation getMap__IncludesMap__EMap() {
		return mapEClass.getEOperations().get(26);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#includesValue(java.lang.Object) <em>Includes Value</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Includes Value</em>' operation.
	 * @see java.util.Map#includesValue(java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__IncludesValue__Object() {
		return mapEClass.getEOperations().get(27);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#including(java.lang.Object, java.lang.Object) <em>Including</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Including</em>' operation.
	 * @see java.util.Map#including(java.lang.Object, java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__Including__Object_Object() {
		return mapEClass.getEOperations().get(28);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#includingMap(org.eclipse.emf.common.util.EMap) <em>Including Map</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Including Map</em>' operation.
	 * @see java.util.Map#includingMap(org.eclipse.emf.common.util.EMap)
	 * @generated
	 */
	public EOperation getMap__IncludingMap__EMap() {
		return mapEClass.getEOperations().get(29);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#isEmpty() <em>Is Empty</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Is Empty</em>' operation.
	 * @see java.util.Map#isEmpty()
	 * @generated
	 */
	public EOperation getMap__IsEmpty() {
		return mapEClass.getEOperations().get(30);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#isUnique1(java.lang.Object) <em>Is Unique1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Is Unique1</em>' operation.
	 * @see java.util.Map#isUnique1(java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__IsUnique1__Object() {
		return mapEClass.getEOperations().get(31);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#iterate1(java.lang.Object) <em>Iterate1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Iterate1</em>' operation.
	 * @see java.util.Map#iterate1(java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__Iterate1__Object() {
		return mapEClass.getEOperations().get(32);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#keys() <em>Keys</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Keys</em>' operation.
	 * @see java.util.Map#keys()
	 * @generated
	 */
	public EOperation getMap__Keys() {
		return mapEClass.getEOperations().get(33);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#notEmpty() <em>Not Empty</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Not Empty</em>' operation.
	 * @see java.util.Map#notEmpty()
	 * @generated
	 */
	public EOperation getMap__NotEmpty() {
		return mapEClass.getEOperations().get(34);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#one1(java.lang.Object) <em>One1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>One1</em>' operation.
	 * @see java.util.Map#one1(java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__One1__Object() {
		return mapEClass.getEOperations().get(35);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#reject1(java.lang.Object) <em>Reject1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Reject1</em>' operation.
	 * @see java.util.Map#reject1(java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__Reject1__Object() {
		return mapEClass.getEOperations().get(36);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#search1(java.lang.Object, java.lang.Object, java.lang.Object) <em>Search1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Search1</em>' operation.
	 * @see java.util.Map#search1(java.lang.Object, java.lang.Object, java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__Search1__Object_Object_Object() {
		return mapEClass.getEOperations().get(37);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#search2(java.lang.Object, java.lang.Object, java.lang.Object) <em>Search2</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Search2</em>' operation.
	 * @see java.util.Map#search2(java.lang.Object, java.lang.Object, java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__Search2__Object_Object_Object() {
		return mapEClass.getEOperations().get(38);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#search3(java.lang.Object, java.lang.Object, java.lang.Object) <em>Search3</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Search3</em>' operation.
	 * @see java.util.Map#search3(java.lang.Object, java.lang.Object, java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__Search3__Object_Object_Object() {
		return mapEClass.getEOperations().get(39);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#select1(java.lang.Object) <em>Select1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Select1</em>' operation.
	 * @see java.util.Map#select1(java.lang.Object)
	 * @generated
	 */
	public EOperation getMap__Select1__Object() {
		return mapEClass.getEOperations().get(40);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#size() <em>Size</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Size</em>' operation.
	 * @see java.util.Map#size()
	 * @generated
	 */
	public EOperation getMap__Size() {
		return mapEClass.getEOperations().get(41);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Map#values() <em>Values</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Values</em>' operation.
	 * @see java.util.Map#values()
	 * @generated
	 */
	public EOperation getMap__Values() {
		return mapEClass.getEOperations().get(42);
	}


	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.pivot.values.RealValue <em>Real</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Real</em>'.
	 * @see org.eclipse.ocl.pivot.values.RealValue
	 * @generated
	 */
	public EDataType getReal() {
		return realEDataType;
	}

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>String</em>'.
	 * @see java.lang.String
	 * @generated
	 */
	public EDataType getString() {
		return stringEDataType;
	}

	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.pivot.values.UnlimitedNaturalValue <em>Unlimited Natural</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Unlimited Natural</em>'.
	 * @see org.eclipse.ocl.pivot.values.UnlimitedNaturalValue
	 * @generated
	 */
	public EDataType getUnlimitedNatural() {
		return unlimitedNaturalEDataType;
	}

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.values.Bag <em>Bag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bag</em>'.
	 * @see org.eclipse.ocl.pivot.values.Bag
	 * @generated
	 */
	public EClass getBag() {
		return bagEClass;
	}

	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.Bag#_ne_(java.lang.Object) <em>ne </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>ne </em>' operation.
	 * @see org.eclipse.ocl.pivot.values.Bag#_ne_(java.lang.Object)
	 * @generated
	 */
	public EOperation getBag___ne___Object() {
		return bagEClass.getEOperations().get(0);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.Bag#_eq_(java.lang.Object) <em>eq </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>eq </em>' operation.
	 * @see org.eclipse.ocl.pivot.values.Bag#_eq_(java.lang.Object)
	 * @generated
	 */
	public EOperation getBag___eq___Object() {
		return bagEClass.getEOperations().get(1);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.Bag#closure1(java.lang.Object) <em>Closure1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Closure1</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.Bag#closure1(java.lang.Object)
	 * @generated
	 */
	public EOperation getBag__Closure1__Object() {
		return bagEClass.getEOperations().get(2);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.Bag#collect1(java.lang.Object) <em>Collect1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Collect1</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.Bag#collect1(java.lang.Object)
	 * @generated
	 */
	public EOperation getBag__Collect1__Object() {
		return bagEClass.getEOperations().get(3);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.Bag#collectNested1(java.lang.Object) <em>Collect Nested1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Collect Nested1</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.Bag#collectNested1(java.lang.Object)
	 * @generated
	 */
	public EOperation getBag__CollectNested1__Object() {
		return bagEClass.getEOperations().get(4);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.Bag#excluding(java.lang.Object) <em>Excluding</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Excluding</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.Bag#excluding(java.lang.Object)
	 * @generated
	 */
	public EOperation getBag__Excluding__Object() {
		return bagEClass.getEOperations().get(5);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.Bag#excludingAll(java.util.Collection) <em>Excluding All</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Excluding All</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.Bag#excludingAll(java.util.Collection)
	 * @generated
	 */
	public EOperation getBag__ExcludingAll__Collection() {
		return bagEClass.getEOperations().get(6);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.Bag#flatten() <em>Flatten</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Flatten</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.Bag#flatten()
	 * @generated
	 */
	public EOperation getBag__Flatten() {
		return bagEClass.getEOperations().get(7);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.Bag#including(java.lang.Object) <em>Including</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Including</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.Bag#including(java.lang.Object)
	 * @generated
	 */
	public EOperation getBag__Including__Object() {
		return bagEClass.getEOperations().get(8);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.Bag#includingAll(java.util.Collection) <em>Including All</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Including All</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.Bag#includingAll(java.util.Collection)
	 * @generated
	 */
	public EOperation getBag__IncludingAll__Collection() {
		return bagEClass.getEOperations().get(9);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.Bag#reject1(java.lang.Object) <em>Reject1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Reject1</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.Bag#reject1(java.lang.Object)
	 * @generated
	 */
	public EOperation getBag__Reject1__Object() {
		return bagEClass.getEOperations().get(10);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.Bag#select1(java.lang.Object) <em>Select1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Select1</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.Bag#select1(java.lang.Object)
	 * @generated
	 */
	public EOperation getBag__Select1__Object() {
		return bagEClass.getEOperations().get(11);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.Bag#selectByKind(java.lang.Object) <em>Select By Kind</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Select By Kind</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.Bag#selectByKind(java.lang.Object)
	 * @generated
	 */
	public EOperation getBag__SelectByKind__Object() {
		return bagEClass.getEOperations().get(12);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.Bag#selectByType(java.lang.Object) <em>Select By Type</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Select By Type</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.Bag#selectByType(java.lang.Object)
	 * @generated
	 */
	public EOperation getBag__SelectByType__Object() {
		return bagEClass.getEOperations().get(13);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.Bag#sortedBy1(java.lang.Object) <em>Sorted By1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Sorted By1</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.Bag#sortedBy1(java.lang.Object)
	 * @generated
	 */
	public EOperation getBag__SortedBy1__Object() {
		return bagEClass.getEOperations().get(14);
	}


	/**
	 * Returns the meta object for class '{@link java.util.Collection <em>Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection</em>'.
	 * @see java.util.Collection
	 * @generated
	 */
	public EClass getCollection() {
		return collectionEClass;
	}

	/**
	 * Returns the meta object for the '{@link java.util.Collection#_ne_(java.lang.Object) <em>ne </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>ne </em>' operation.
	 * @see java.util.Collection#_ne_(java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection___ne___Object() {
		return collectionEClass.getEOperations().get(0);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#_eq_(java.lang.Object) <em>eq </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>eq </em>' operation.
	 * @see java.util.Collection#_eq_(java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection___eq___Object() {
		return collectionEClass.getEOperations().get(1);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#any1(java.lang.Object) <em>Any1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Any1</em>' operation.
	 * @see java.util.Collection#any1(java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection__Any1__Object() {
		return collectionEClass.getEOperations().get(2);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#asBag() <em>As Bag</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>As Bag</em>' operation.
	 * @see java.util.Collection#asBag()
	 * @generated
	 */
	public EOperation getCollection__AsBag() {
		return collectionEClass.getEOperations().get(3);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#asOrderedSet() <em>As Ordered Set</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>As Ordered Set</em>' operation.
	 * @see java.util.Collection#asOrderedSet()
	 * @generated
	 */
	public EOperation getCollection__AsOrderedSet() {
		return collectionEClass.getEOperations().get(4);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#asSequence() <em>As Sequence</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>As Sequence</em>' operation.
	 * @see java.util.Collection#asSequence()
	 * @generated
	 */
	public EOperation getCollection__AsSequence() {
		return collectionEClass.getEOperations().get(5);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#asSet() <em>As Set</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>As Set</em>' operation.
	 * @see java.util.Collection#asSet()
	 * @generated
	 */
	public EOperation getCollection__AsSet() {
		return collectionEClass.getEOperations().get(6);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#collect1(java.lang.Object) <em>Collect1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Collect1</em>' operation.
	 * @see java.util.Collection#collect1(java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection__Collect1__Object() {
		return collectionEClass.getEOperations().get(7);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#collectBy1(java.lang.Object) <em>Collect By1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Collect By1</em>' operation.
	 * @see java.util.Collection#collectBy1(java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection__CollectBy1__Object() {
		return collectionEClass.getEOperations().get(8);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#collectNested1(java.lang.Object) <em>Collect Nested1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Collect Nested1</em>' operation.
	 * @see java.util.Collection#collectNested1(java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection__CollectNested1__Object() {
		return collectionEClass.getEOperations().get(9);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#count(java.lang.Object) <em>Count</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Count</em>' operation.
	 * @see java.util.Collection#count(java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection__Count__Object() {
		return collectionEClass.getEOperations().get(10);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#excludes(java.lang.Object) <em>Excludes</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Excludes</em>' operation.
	 * @see java.util.Collection#excludes(java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection__Excludes__Object() {
		return collectionEClass.getEOperations().get(11);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#excludesAll(java.util.Collection) <em>Excludes All</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Excludes All</em>' operation.
	 * @see java.util.Collection#excludesAll(java.util.Collection)
	 * @generated
	 */
	public EOperation getCollection__ExcludesAll__Collection() {
		return collectionEClass.getEOperations().get(12);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#excluding(java.lang.Object) <em>Excluding</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Excluding</em>' operation.
	 * @see java.util.Collection#excluding(java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection__Excluding__Object() {
		return collectionEClass.getEOperations().get(13);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#excludingAll(java.util.Collection) <em>Excluding All</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Excluding All</em>' operation.
	 * @see java.util.Collection#excludingAll(java.util.Collection)
	 * @generated
	 */
	public EOperation getCollection__ExcludingAll__Collection() {
		return collectionEClass.getEOperations().get(14);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#exists1(java.lang.Object) <em>Exists1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Exists1</em>' operation.
	 * @see java.util.Collection#exists1(java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection__Exists1__Object() {
		return collectionEClass.getEOperations().get(15);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#exists2(java.lang.Object) <em>Exists2</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Exists2</em>' operation.
	 * @see java.util.Collection#exists2(java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection__Exists2__Object() {
		return collectionEClass.getEOperations().get(16);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#exists3(java.lang.Object) <em>Exists3</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Exists3</em>' operation.
	 * @see java.util.Collection#exists3(java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection__Exists3__Object() {
		return collectionEClass.getEOperations().get(17);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#flatten() <em>Flatten</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Flatten</em>' operation.
	 * @see java.util.Collection#flatten()
	 * @generated
	 */
	public EOperation getCollection__Flatten() {
		return collectionEClass.getEOperations().get(18);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#forAll1(java.lang.Object) <em>For All1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>For All1</em>' operation.
	 * @see java.util.Collection#forAll1(java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection__ForAll1__Object() {
		return collectionEClass.getEOperations().get(19);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#forAll2(java.lang.Object) <em>For All2</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>For All2</em>' operation.
	 * @see java.util.Collection#forAll2(java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection__ForAll2__Object() {
		return collectionEClass.getEOperations().get(20);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#forAll3(java.lang.Object) <em>For All3</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>For All3</em>' operation.
	 * @see java.util.Collection#forAll3(java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection__ForAll3__Object() {
		return collectionEClass.getEOperations().get(21);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#gather1(java.lang.Object) <em>Gather1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Gather1</em>' operation.
	 * @see java.util.Collection#gather1(java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection__Gather1__Object() {
		return collectionEClass.getEOperations().get(22);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#includes(java.lang.Object) <em>Includes</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Includes</em>' operation.
	 * @see java.util.Collection#includes(java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection__Includes__Object() {
		return collectionEClass.getEOperations().get(23);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#includesAll(java.util.Collection) <em>Includes All</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Includes All</em>' operation.
	 * @see java.util.Collection#includesAll(java.util.Collection)
	 * @generated
	 */
	public EOperation getCollection__IncludesAll__Collection() {
		return collectionEClass.getEOperations().get(24);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#including(java.lang.Object) <em>Including</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Including</em>' operation.
	 * @see java.util.Collection#including(java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection__Including__Object() {
		return collectionEClass.getEOperations().get(25);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#includingAll(java.util.Collection) <em>Including All</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Including All</em>' operation.
	 * @see java.util.Collection#includingAll(java.util.Collection)
	 * @generated
	 */
	public EOperation getCollection__IncludingAll__Collection() {
		return collectionEClass.getEOperations().get(26);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#intersection(java.util.Collection) <em>Intersection</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Intersection</em>' operation.
	 * @see java.util.Collection#intersection(java.util.Collection)
	 * @generated
	 */
	public EOperation getCollection__Intersection__Collection() {
		return collectionEClass.getEOperations().get(27);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#intersection(org.eclipse.emf.common.util.EList) <em>Intersection</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Intersection</em>' operation.
	 * @see java.util.Collection#intersection(org.eclipse.emf.common.util.EList)
	 * @generated
	 */
	public EOperation getCollection__Intersection__EList() {
		return collectionEClass.getEOperations().get(28);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#isEmpty() <em>Is Empty</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Is Empty</em>' operation.
	 * @see java.util.Collection#isEmpty()
	 * @generated
	 */
	public EOperation getCollection__IsEmpty() {
		return collectionEClass.getEOperations().get(29);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#isUnique1(java.lang.Object) <em>Is Unique1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Is Unique1</em>' operation.
	 * @see java.util.Collection#isUnique1(java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection__IsUnique1__Object() {
		return collectionEClass.getEOperations().get(30);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#iterate1(java.lang.Object) <em>Iterate1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Iterate1</em>' operation.
	 * @see java.util.Collection#iterate1(java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection__Iterate1__Object() {
		return collectionEClass.getEOperations().get(31);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#max() <em>Max</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Max</em>' operation.
	 * @see java.util.Collection#max()
	 * @generated
	 */
	public EOperation getCollection__Max() {
		return collectionEClass.getEOperations().get(32);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#min() <em>Min</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Min</em>' operation.
	 * @see java.util.Collection#min()
	 * @generated
	 */
	public EOperation getCollection__Min() {
		return collectionEClass.getEOperations().get(33);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#notEmpty() <em>Not Empty</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Not Empty</em>' operation.
	 * @see java.util.Collection#notEmpty()
	 * @generated
	 */
	public EOperation getCollection__NotEmpty() {
		return collectionEClass.getEOperations().get(34);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#one1(java.lang.Object) <em>One1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>One1</em>' operation.
	 * @see java.util.Collection#one1(java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection__One1__Object() {
		return collectionEClass.getEOperations().get(35);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#product(java.util.Collection) <em>Product</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Product</em>' operation.
	 * @see java.util.Collection#product(java.util.Collection)
	 * @generated
	 */
	public EOperation getCollection__Product__Collection() {
		return collectionEClass.getEOperations().get(36);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#reject1(java.lang.Object) <em>Reject1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Reject1</em>' operation.
	 * @see java.util.Collection#reject1(java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection__Reject1__Object() {
		return collectionEClass.getEOperations().get(37);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#search1(java.lang.Object, java.lang.Object, java.lang.Object) <em>Search1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Search1</em>' operation.
	 * @see java.util.Collection#search1(java.lang.Object, java.lang.Object, java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection__Search1__Object_Object_Object() {
		return collectionEClass.getEOperations().get(38);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#search2(java.lang.Object, java.lang.Object, java.lang.Object) <em>Search2</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Search2</em>' operation.
	 * @see java.util.Collection#search2(java.lang.Object, java.lang.Object, java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection__Search2__Object_Object_Object() {
		return collectionEClass.getEOperations().get(39);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#search3(java.lang.Object, java.lang.Object, java.lang.Object) <em>Search3</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Search3</em>' operation.
	 * @see java.util.Collection#search3(java.lang.Object, java.lang.Object, java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection__Search3__Object_Object_Object() {
		return collectionEClass.getEOperations().get(40);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#select1(java.lang.Object) <em>Select1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Select1</em>' operation.
	 * @see java.util.Collection#select1(java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection__Select1__Object() {
		return collectionEClass.getEOperations().get(41);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#selectByKind(java.lang.Object) <em>Select By Kind</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Select By Kind</em>' operation.
	 * @see java.util.Collection#selectByKind(java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection__SelectByKind__Object() {
		return collectionEClass.getEOperations().get(42);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#selectByType(java.lang.Object) <em>Select By Type</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Select By Type</em>' operation.
	 * @see java.util.Collection#selectByType(java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection__SelectByType__Object() {
		return collectionEClass.getEOperations().get(43);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#size() <em>Size</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Size</em>' operation.
	 * @see java.util.Collection#size()
	 * @generated
	 */
	public EOperation getCollection__Size() {
		return collectionEClass.getEOperations().get(44);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#sortedBy1(java.lang.Object) <em>Sorted By1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Sorted By1</em>' operation.
	 * @see java.util.Collection#sortedBy1(java.lang.Object)
	 * @generated
	 */
	public EOperation getCollection__SortedBy1__Object() {
		return collectionEClass.getEOperations().get(45);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#sum() <em>Sum</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Sum</em>' operation.
	 * @see java.util.Collection#sum()
	 * @generated
	 */
	public EOperation getCollection__Sum() {
		return collectionEClass.getEOperations().get(46);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#union(java.util.Collection) <em>Union</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Union</em>' operation.
	 * @see java.util.Collection#union(java.util.Collection)
	 * @generated
	 */
	public EOperation getCollection__Union__Collection() {
		return collectionEClass.getEOperations().get(47);
	}


	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.values.OrderedSet <em>Ordered Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ordered Set</em>'.
	 * @see org.eclipse.ocl.pivot.values.OrderedSet
	 * @generated
	 */
	public EClass getOrderedSet() {
		return orderedSetEClass;
	}

	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.OrderedSet#_sub_(org.eclipse.emf.common.util.EList) <em>sub </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>sub </em>' operation.
	 * @see org.eclipse.ocl.pivot.values.OrderedSet#_sub_(org.eclipse.emf.common.util.EList)
	 * @generated
	 */
	public EOperation getOrderedSet___sub___EList() {
		return orderedSetEClass.getEOperations().get(0);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.OrderedSet#_ne_(java.lang.Object) <em>ne </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>ne </em>' operation.
	 * @see org.eclipse.ocl.pivot.values.OrderedSet#_ne_(java.lang.Object)
	 * @generated
	 */
	public EOperation getOrderedSet___ne___Object() {
		return orderedSetEClass.getEOperations().get(1);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.OrderedSet#_eq_(java.lang.Object) <em>eq </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>eq </em>' operation.
	 * @see org.eclipse.ocl.pivot.values.OrderedSet#_eq_(java.lang.Object)
	 * @generated
	 */
	public EOperation getOrderedSet___eq___Object() {
		return orderedSetEClass.getEOperations().get(2);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.OrderedSet#append(java.lang.Object) <em>Append</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Append</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.OrderedSet#append(java.lang.Object)
	 * @generated
	 */
	public EOperation getOrderedSet__Append__Object() {
		return orderedSetEClass.getEOperations().get(3);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.OrderedSet#appendAll(org.eclipse.emf.common.util.EList) <em>Append All</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Append All</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.OrderedSet#appendAll(org.eclipse.emf.common.util.EList)
	 * @generated
	 */
	public EOperation getOrderedSet__AppendAll__EList() {
		return orderedSetEClass.getEOperations().get(4);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.OrderedSet#closure1(java.lang.Object) <em>Closure1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Closure1</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.OrderedSet#closure1(java.lang.Object)
	 * @generated
	 */
	public EOperation getOrderedSet__Closure1__Object() {
		return orderedSetEClass.getEOperations().get(5);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.OrderedSet#collect1(java.lang.Object) <em>Collect1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Collect1</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.OrderedSet#collect1(java.lang.Object)
	 * @generated
	 */
	public EOperation getOrderedSet__Collect1__Object() {
		return orderedSetEClass.getEOperations().get(6);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.OrderedSet#collectNested1(java.lang.Object) <em>Collect Nested1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Collect Nested1</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.OrderedSet#collectNested1(java.lang.Object)
	 * @generated
	 */
	public EOperation getOrderedSet__CollectNested1__Object() {
		return orderedSetEClass.getEOperations().get(7);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.OrderedSet#excluding(java.lang.Object) <em>Excluding</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Excluding</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.OrderedSet#excluding(java.lang.Object)
	 * @generated
	 */
	public EOperation getOrderedSet__Excluding__Object() {
		return orderedSetEClass.getEOperations().get(8);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.OrderedSet#excludingAll(java.util.Collection) <em>Excluding All</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Excluding All</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.OrderedSet#excludingAll(java.util.Collection)
	 * @generated
	 */
	public EOperation getOrderedSet__ExcludingAll__Collection() {
		return orderedSetEClass.getEOperations().get(9);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.OrderedSet#flatten() <em>Flatten</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Flatten</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.OrderedSet#flatten()
	 * @generated
	 */
	public EOperation getOrderedSet__Flatten() {
		return orderedSetEClass.getEOperations().get(10);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.OrderedSet#including(java.lang.Object) <em>Including</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Including</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.OrderedSet#including(java.lang.Object)
	 * @generated
	 */
	public EOperation getOrderedSet__Including__Object() {
		return orderedSetEClass.getEOperations().get(11);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.OrderedSet#includingAll(java.util.Collection) <em>Including All</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Including All</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.OrderedSet#includingAll(java.util.Collection)
	 * @generated
	 */
	public EOperation getOrderedSet__IncludingAll__Collection() {
		return orderedSetEClass.getEOperations().get(12);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.OrderedSet#insertAt(org.eclipse.ocl.pivot.values.IntegerValue, java.lang.Object) <em>Insert At</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Insert At</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.OrderedSet#insertAt(org.eclipse.ocl.pivot.values.IntegerValue, java.lang.Object)
	 * @generated
	 */
	public EOperation getOrderedSet__InsertAt__IntegerValue_Object() {
		return orderedSetEClass.getEOperations().get(13);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.OrderedSet#prepend(java.lang.Object) <em>Prepend</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Prepend</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.OrderedSet#prepend(java.lang.Object)
	 * @generated
	 */
	public EOperation getOrderedSet__Prepend__Object() {
		return orderedSetEClass.getEOperations().get(14);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.OrderedSet#prependAll(org.eclipse.emf.common.util.EList) <em>Prepend All</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Prepend All</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.OrderedSet#prependAll(org.eclipse.emf.common.util.EList)
	 * @generated
	 */
	public EOperation getOrderedSet__PrependAll__EList() {
		return orderedSetEClass.getEOperations().get(15);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.OrderedSet#reject1(java.lang.Object) <em>Reject1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Reject1</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.OrderedSet#reject1(java.lang.Object)
	 * @generated
	 */
	public EOperation getOrderedSet__Reject1__Object() {
		return orderedSetEClass.getEOperations().get(16);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.OrderedSet#reverse() <em>Reverse</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Reverse</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.OrderedSet#reverse()
	 * @generated
	 */
	public EOperation getOrderedSet__Reverse() {
		return orderedSetEClass.getEOperations().get(17);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.OrderedSet#select1(java.lang.Object) <em>Select1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Select1</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.OrderedSet#select1(java.lang.Object)
	 * @generated
	 */
	public EOperation getOrderedSet__Select1__Object() {
		return orderedSetEClass.getEOperations().get(18);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.OrderedSet#selectByKind(java.lang.Object) <em>Select By Kind</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Select By Kind</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.OrderedSet#selectByKind(java.lang.Object)
	 * @generated
	 */
	public EOperation getOrderedSet__SelectByKind__Object() {
		return orderedSetEClass.getEOperations().get(19);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.OrderedSet#selectByType(java.lang.Object) <em>Select By Type</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Select By Type</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.OrderedSet#selectByType(java.lang.Object)
	 * @generated
	 */
	public EOperation getOrderedSet__SelectByType__Object() {
		return orderedSetEClass.getEOperations().get(20);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.OrderedSet#sortedBy1(java.lang.Object) <em>Sorted By1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Sorted By1</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.OrderedSet#sortedBy1(java.lang.Object)
	 * @generated
	 */
	public EOperation getOrderedSet__SortedBy1__Object() {
		return orderedSetEClass.getEOperations().get(21);
	}


	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.pivot.values.OrderedSet#subOrderedSet(org.eclipse.ocl.pivot.values.IntegerValue, org.eclipse.ocl.pivot.values.IntegerValue) <em>Sub Ordered Set</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Sub Ordered Set</em>' operation.
	 * @see org.eclipse.ocl.pivot.values.OrderedSet#subOrderedSet(org.eclipse.ocl.pivot.values.IntegerValue, org.eclipse.ocl.pivot.values.IntegerValue)
	 * @generated
	 */
	public EOperation getOrderedSet__SubOrderedSet__IntegerValue_IntegerValue() {
		return orderedSetEClass.getEOperations().get(22);
	}


	/**
	 * Returns the meta object for class '{@link java.util.List <em>Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sequence</em>'.
	 * @see java.util.List
	 * @generated
	 */
	public EClass getSequence() {
		return sequenceEClass;
	}

	/**
	 * Returns the meta object for the '{@link java.util.List#_ne_(java.lang.Object) <em>ne </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>ne </em>' operation.
	 * @see java.util.List#_ne_(java.lang.Object)
	 * @generated
	 */
	public EOperation getSequence___ne___Object() {
		return sequenceEClass.getEOperations().get(0);
	}


	/**
	 * Returns the meta object for the '{@link java.util.List#_eq_(java.lang.Object) <em>eq </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>eq </em>' operation.
	 * @see java.util.List#_eq_(java.lang.Object)
	 * @generated
	 */
	public EOperation getSequence___eq___Object() {
		return sequenceEClass.getEOperations().get(1);
	}


	/**
	 * Returns the meta object for the '{@link java.util.List#append(java.lang.Object) <em>Append</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Append</em>' operation.
	 * @see java.util.List#append(java.lang.Object)
	 * @generated
	 */
	public EOperation getSequence__Append__Object() {
		return sequenceEClass.getEOperations().get(2);
	}


	/**
	 * Returns the meta object for the '{@link java.util.List#appendAll(org.eclipse.emf.common.util.EList) <em>Append All</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Append All</em>' operation.
	 * @see java.util.List#appendAll(org.eclipse.emf.common.util.EList)
	 * @generated
	 */
	public EOperation getSequence__AppendAll__EList() {
		return sequenceEClass.getEOperations().get(3);
	}


	/**
	 * Returns the meta object for the '{@link java.util.List#closure1(java.lang.Object) <em>Closure1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Closure1</em>' operation.
	 * @see java.util.List#closure1(java.lang.Object)
	 * @generated
	 */
	public EOperation getSequence__Closure1__Object() {
		return sequenceEClass.getEOperations().get(4);
	}


	/**
	 * Returns the meta object for the '{@link java.util.List#collect1(java.lang.Object) <em>Collect1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Collect1</em>' operation.
	 * @see java.util.List#collect1(java.lang.Object)
	 * @generated
	 */
	public EOperation getSequence__Collect1__Object() {
		return sequenceEClass.getEOperations().get(5);
	}


	/**
	 * Returns the meta object for the '{@link java.util.List#collectNested1(java.lang.Object) <em>Collect Nested1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Collect Nested1</em>' operation.
	 * @see java.util.List#collectNested1(java.lang.Object)
	 * @generated
	 */
	public EOperation getSequence__CollectNested1__Object() {
		return sequenceEClass.getEOperations().get(6);
	}


	/**
	 * Returns the meta object for the '{@link java.util.List#excluding(java.lang.Object) <em>Excluding</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Excluding</em>' operation.
	 * @see java.util.List#excluding(java.lang.Object)
	 * @generated
	 */
	public EOperation getSequence__Excluding__Object() {
		return sequenceEClass.getEOperations().get(7);
	}


	/**
	 * Returns the meta object for the '{@link java.util.List#excludingAll(java.util.Collection) <em>Excluding All</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Excluding All</em>' operation.
	 * @see java.util.List#excludingAll(java.util.Collection)
	 * @generated
	 */
	public EOperation getSequence__ExcludingAll__Collection() {
		return sequenceEClass.getEOperations().get(8);
	}


	/**
	 * Returns the meta object for the '{@link java.util.List#flatten() <em>Flatten</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Flatten</em>' operation.
	 * @see java.util.List#flatten()
	 * @generated
	 */
	public EOperation getSequence__Flatten() {
		return sequenceEClass.getEOperations().get(9);
	}


	/**
	 * Returns the meta object for the '{@link java.util.List#including(java.lang.Object) <em>Including</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Including</em>' operation.
	 * @see java.util.List#including(java.lang.Object)
	 * @generated
	 */
	public EOperation getSequence__Including__Object() {
		return sequenceEClass.getEOperations().get(10);
	}


	/**
	 * Returns the meta object for the '{@link java.util.List#includingAll(java.util.Collection) <em>Including All</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Including All</em>' operation.
	 * @see java.util.List#includingAll(java.util.Collection)
	 * @generated
	 */
	public EOperation getSequence__IncludingAll__Collection() {
		return sequenceEClass.getEOperations().get(11);
	}


	/**
	 * Returns the meta object for the '{@link java.util.List#insertAt(org.eclipse.ocl.pivot.values.IntegerValue, java.lang.Object) <em>Insert At</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Insert At</em>' operation.
	 * @see java.util.List#insertAt(org.eclipse.ocl.pivot.values.IntegerValue, java.lang.Object)
	 * @generated
	 */
	public EOperation getSequence__InsertAt__IntegerValue_Object() {
		return sequenceEClass.getEOperations().get(12);
	}


	/**
	 * Returns the meta object for the '{@link java.util.List#prepend(java.lang.Object) <em>Prepend</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Prepend</em>' operation.
	 * @see java.util.List#prepend(java.lang.Object)
	 * @generated
	 */
	public EOperation getSequence__Prepend__Object() {
		return sequenceEClass.getEOperations().get(13);
	}


	/**
	 * Returns the meta object for the '{@link java.util.List#prependAll(org.eclipse.emf.common.util.EList) <em>Prepend All</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Prepend All</em>' operation.
	 * @see java.util.List#prependAll(org.eclipse.emf.common.util.EList)
	 * @generated
	 */
	public EOperation getSequence__PrependAll__EList() {
		return sequenceEClass.getEOperations().get(14);
	}


	/**
	 * Returns the meta object for the '{@link java.util.List#reject1(java.lang.Object) <em>Reject1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Reject1</em>' operation.
	 * @see java.util.List#reject1(java.lang.Object)
	 * @generated
	 */
	public EOperation getSequence__Reject1__Object() {
		return sequenceEClass.getEOperations().get(15);
	}


	/**
	 * Returns the meta object for the '{@link java.util.List#reverse() <em>Reverse</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Reverse</em>' operation.
	 * @see java.util.List#reverse()
	 * @generated
	 */
	public EOperation getSequence__Reverse() {
		return sequenceEClass.getEOperations().get(16);
	}


	/**
	 * Returns the meta object for the '{@link java.util.List#select1(java.lang.Object) <em>Select1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Select1</em>' operation.
	 * @see java.util.List#select1(java.lang.Object)
	 * @generated
	 */
	public EOperation getSequence__Select1__Object() {
		return sequenceEClass.getEOperations().get(17);
	}


	/**
	 * Returns the meta object for the '{@link java.util.List#selectByKind(java.lang.Object) <em>Select By Kind</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Select By Kind</em>' operation.
	 * @see java.util.List#selectByKind(java.lang.Object)
	 * @generated
	 */
	public EOperation getSequence__SelectByKind__Object() {
		return sequenceEClass.getEOperations().get(18);
	}


	/**
	 * Returns the meta object for the '{@link java.util.List#selectByType(java.lang.Object) <em>Select By Type</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Select By Type</em>' operation.
	 * @see java.util.List#selectByType(java.lang.Object)
	 * @generated
	 */
	public EOperation getSequence__SelectByType__Object() {
		return sequenceEClass.getEOperations().get(19);
	}


	/**
	 * Returns the meta object for the '{@link java.util.List#sortedBy1(java.lang.Object) <em>Sorted By1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Sorted By1</em>' operation.
	 * @see java.util.List#sortedBy1(java.lang.Object)
	 * @generated
	 */
	public EOperation getSequence__SortedBy1__Object() {
		return sequenceEClass.getEOperations().get(20);
	}


	/**
	 * Returns the meta object for the '{@link java.util.List#subSequence(org.eclipse.ocl.pivot.values.IntegerValue, org.eclipse.ocl.pivot.values.IntegerValue) <em>Sub Sequence</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Sub Sequence</em>' operation.
	 * @see java.util.List#subSequence(org.eclipse.ocl.pivot.values.IntegerValue, org.eclipse.ocl.pivot.values.IntegerValue)
	 * @generated
	 */
	public EOperation getSequence__SubSequence__IntegerValue_IntegerValue() {
		return sequenceEClass.getEOperations().get(21);
	}


	/**
	 * Returns the meta object for class '{@link java.util.Set <em>Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Set</em>'.
	 * @see java.util.Set
	 * @generated
	 */
	public EClass getSet() {
		return setEClass;
	}

	/**
	 * Returns the meta object for the '{@link java.util.Set#_sub_(org.eclipse.emf.common.util.EList) <em>sub </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>sub </em>' operation.
	 * @see java.util.Set#_sub_(org.eclipse.emf.common.util.EList)
	 * @generated
	 */
	public EOperation getSet___sub___EList() {
		return setEClass.getEOperations().get(0);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Set#_ne_(java.lang.Object) <em>ne </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>ne </em>' operation.
	 * @see java.util.Set#_ne_(java.lang.Object)
	 * @generated
	 */
	public EOperation getSet___ne___Object() {
		return setEClass.getEOperations().get(1);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Set#_eq_(java.lang.Object) <em>eq </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>eq </em>' operation.
	 * @see java.util.Set#_eq_(java.lang.Object)
	 * @generated
	 */
	public EOperation getSet___eq___Object() {
		return setEClass.getEOperations().get(2);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Set#closure1(java.lang.Object) <em>Closure1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Closure1</em>' operation.
	 * @see java.util.Set#closure1(java.lang.Object)
	 * @generated
	 */
	public EOperation getSet__Closure1__Object() {
		return setEClass.getEOperations().get(3);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Set#collect1(java.lang.Object) <em>Collect1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Collect1</em>' operation.
	 * @see java.util.Set#collect1(java.lang.Object)
	 * @generated
	 */
	public EOperation getSet__Collect1__Object() {
		return setEClass.getEOperations().get(4);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Set#collectNested1(java.lang.Object) <em>Collect Nested1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Collect Nested1</em>' operation.
	 * @see java.util.Set#collectNested1(java.lang.Object)
	 * @generated
	 */
	public EOperation getSet__CollectNested1__Object() {
		return setEClass.getEOperations().get(5);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Set#excluding(java.lang.Object) <em>Excluding</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Excluding</em>' operation.
	 * @see java.util.Set#excluding(java.lang.Object)
	 * @generated
	 */
	public EOperation getSet__Excluding__Object() {
		return setEClass.getEOperations().get(6);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Set#excludingAll(java.util.Collection) <em>Excluding All</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Excluding All</em>' operation.
	 * @see java.util.Set#excludingAll(java.util.Collection)
	 * @generated
	 */
	public EOperation getSet__ExcludingAll__Collection() {
		return setEClass.getEOperations().get(7);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Set#flatten() <em>Flatten</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Flatten</em>' operation.
	 * @see java.util.Set#flatten()
	 * @generated
	 */
	public EOperation getSet__Flatten() {
		return setEClass.getEOperations().get(8);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Set#including(java.lang.Object) <em>Including</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Including</em>' operation.
	 * @see java.util.Set#including(java.lang.Object)
	 * @generated
	 */
	public EOperation getSet__Including__Object() {
		return setEClass.getEOperations().get(9);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Set#includingAll(java.util.Collection) <em>Including All</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Including All</em>' operation.
	 * @see java.util.Set#includingAll(java.util.Collection)
	 * @generated
	 */
	public EOperation getSet__IncludingAll__Collection() {
		return setEClass.getEOperations().get(10);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Set#reject1(java.lang.Object) <em>Reject1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Reject1</em>' operation.
	 * @see java.util.Set#reject1(java.lang.Object)
	 * @generated
	 */
	public EOperation getSet__Reject1__Object() {
		return setEClass.getEOperations().get(11);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Set#select1(java.lang.Object) <em>Select1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Select1</em>' operation.
	 * @see java.util.Set#select1(java.lang.Object)
	 * @generated
	 */
	public EOperation getSet__Select1__Object() {
		return setEClass.getEOperations().get(12);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Set#selectByKind(java.lang.Object) <em>Select By Kind</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Select By Kind</em>' operation.
	 * @see java.util.Set#selectByKind(java.lang.Object)
	 * @generated
	 */
	public EOperation getSet__SelectByKind__Object() {
		return setEClass.getEOperations().get(13);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Set#selectByType(java.lang.Object) <em>Select By Type</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Select By Type</em>' operation.
	 * @see java.util.Set#selectByType(java.lang.Object)
	 * @generated
	 */
	public EOperation getSet__SelectByType__Object() {
		return setEClass.getEOperations().get(14);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Set#sortedBy1(java.lang.Object) <em>Sorted By1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Sorted By1</em>' operation.
	 * @see java.util.Set#sortedBy1(java.lang.Object)
	 * @generated
	 */
	public EOperation getSet__SortedBy1__Object() {
		return setEClass.getEOperations().get(15);
	}


	/**
	 * Returns the meta object for class '{@link java.util.Collection <em>Unique Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unique Collection</em>'.
	 * @see java.util.Collection
	 * @generated
	 */
	public EClass getUniqueCollection() {
		return uniqueCollectionEClass;
	}

	/**
	 * Returns the meta object for the '{@link java.util.Collection#_sub_(org.eclipse.emf.common.util.EList) <em>sub </em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>sub </em>' operation.
	 * @see java.util.Collection#_sub_(org.eclipse.emf.common.util.EList)
	 * @generated
	 */
	public EOperation getUniqueCollection___sub___EList() {
		return uniqueCollectionEClass.getEOperations().get(0);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#intersection(java.util.Collection) <em>Intersection</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Intersection</em>' operation.
	 * @see java.util.Collection#intersection(java.util.Collection)
	 * @generated
	 */
	public EOperation getUniqueCollection__Intersection__Collection() {
		return uniqueCollectionEClass.getEOperations().get(1);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#sortedBy1(java.lang.Object) <em>Sorted By1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Sorted By1</em>' operation.
	 * @see java.util.Collection#sortedBy1(java.lang.Object)
	 * @generated
	 */
	public EOperation getUniqueCollection__SortedBy1__Object() {
		return uniqueCollectionEClass.getEOperations().get(2);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#symmetricDifference(org.eclipse.emf.common.util.EList) <em>Symmetric Difference</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Symmetric Difference</em>' operation.
	 * @see java.util.Collection#symmetricDifference(org.eclipse.emf.common.util.EList)
	 * @generated
	 */
	public EOperation getUniqueCollection__SymmetricDifference__EList() {
		return uniqueCollectionEClass.getEOperations().get(3);
	}


	/**
	 * Returns the meta object for the '{@link java.util.Collection#union(org.eclipse.emf.common.util.EList) <em>Union</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Union</em>' operation.
	 * @see java.util.Collection#union(org.eclipse.emf.common.util.EList)
	 * @generated
	 */
	public EOperation getUniqueCollection__Union__EList() {
		return uniqueCollectionEClass.getEOperations().get(4);
	}


	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Entry rr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entry rr</em>'.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public EClass getEntry_r_r() {
		return entry_r_rEClass;
	}


	/**
	 * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getEntry_r_r()
	 * @generated
	 */
	public EReference getEntry_r_r_Key() {
		return (EReference)entry_r_rEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getEntry_r_r()
	 * @generated
	 */
	public EReference getEntry_r_r_Value() {
		return (EReference)entry_r_rEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	public OCLstdlibFactory getOCLstdlibFactory() {
		return (OCLstdlibFactory)getEFactoryInstance();
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
		bagEClass = createEClass(0);
		createEOperation(bagEClass, 48);
		createEOperation(bagEClass, 49);
		createEOperation(bagEClass, 50);
		createEOperation(bagEClass, 51);
		createEOperation(bagEClass, 52);
		createEOperation(bagEClass, 53);
		createEOperation(bagEClass, 54);
		createEOperation(bagEClass, 55);
		createEOperation(bagEClass, 56);
		createEOperation(bagEClass, 57);
		createEOperation(bagEClass, 58);
		createEOperation(bagEClass, 59);
		createEOperation(bagEClass, 60);
		createEOperation(bagEClass, 61);
		createEOperation(bagEClass, 62);

		collectionEClass = createEClass(1);
		createEOperation(collectionEClass, 0);
		createEOperation(collectionEClass, 1);
		createEOperation(collectionEClass, 2);
		createEOperation(collectionEClass, 3);
		createEOperation(collectionEClass, 4);
		createEOperation(collectionEClass, 5);
		createEOperation(collectionEClass, 6);
		createEOperation(collectionEClass, 7);
		createEOperation(collectionEClass, 8);
		createEOperation(collectionEClass, 9);
		createEOperation(collectionEClass, 10);
		createEOperation(collectionEClass, 11);
		createEOperation(collectionEClass, 12);
		createEOperation(collectionEClass, 13);
		createEOperation(collectionEClass, 14);
		createEOperation(collectionEClass, 15);
		createEOperation(collectionEClass, 16);
		createEOperation(collectionEClass, 17);
		createEOperation(collectionEClass, 18);
		createEOperation(collectionEClass, 19);
		createEOperation(collectionEClass, 20);
		createEOperation(collectionEClass, 21);
		createEOperation(collectionEClass, 22);
		createEOperation(collectionEClass, 23);
		createEOperation(collectionEClass, 24);
		createEOperation(collectionEClass, 25);
		createEOperation(collectionEClass, 26);
		createEOperation(collectionEClass, 27);
		createEOperation(collectionEClass, 28);
		createEOperation(collectionEClass, 29);
		createEOperation(collectionEClass, 30);
		createEOperation(collectionEClass, 31);
		createEOperation(collectionEClass, 32);
		createEOperation(collectionEClass, 33);
		createEOperation(collectionEClass, 34);
		createEOperation(collectionEClass, 35);
		createEOperation(collectionEClass, 36);
		createEOperation(collectionEClass, 37);
		createEOperation(collectionEClass, 38);
		createEOperation(collectionEClass, 39);
		createEOperation(collectionEClass, 40);
		createEOperation(collectionEClass, 41);
		createEOperation(collectionEClass, 42);
		createEOperation(collectionEClass, 43);
		createEOperation(collectionEClass, 44);
		createEOperation(collectionEClass, 45);
		createEOperation(collectionEClass, 46);
		createEOperation(collectionEClass, 47);

		entryEClass = createEClass(2);
		createEReference(entryEClass, 0);
		createEReference(entryEClass, 1);

		mapEClass = createEClass(3);
		createEOperation(mapEClass, 0);
		createEOperation(mapEClass, 1);
		createEOperation(mapEClass, 2);
		createEOperation(mapEClass, 3);
		createEOperation(mapEClass, 4);
		createEOperation(mapEClass, 5);
		createEOperation(mapEClass, 6);
		createEOperation(mapEClass, 7);
		createEOperation(mapEClass, 8);
		createEOperation(mapEClass, 9);
		createEOperation(mapEClass, 10);
		createEOperation(mapEClass, 11);
		createEOperation(mapEClass, 12);
		createEOperation(mapEClass, 13);
		createEOperation(mapEClass, 14);
		createEOperation(mapEClass, 15);
		createEOperation(mapEClass, 16);
		createEOperation(mapEClass, 17);
		createEOperation(mapEClass, 18);
		createEOperation(mapEClass, 19);
		createEOperation(mapEClass, 20);
		createEOperation(mapEClass, 21);
		createEOperation(mapEClass, 22);
		createEOperation(mapEClass, 23);
		createEOperation(mapEClass, 24);
		createEOperation(mapEClass, 25);
		createEOperation(mapEClass, 26);
		createEOperation(mapEClass, 27);
		createEOperation(mapEClass, 28);
		createEOperation(mapEClass, 29);
		createEOperation(mapEClass, 30);
		createEOperation(mapEClass, 31);
		createEOperation(mapEClass, 32);
		createEOperation(mapEClass, 33);
		createEOperation(mapEClass, 34);
		createEOperation(mapEClass, 35);
		createEOperation(mapEClass, 36);
		createEOperation(mapEClass, 37);
		createEOperation(mapEClass, 38);
		createEOperation(mapEClass, 39);
		createEOperation(mapEClass, 40);
		createEOperation(mapEClass, 41);
		createEOperation(mapEClass, 42);

		oclAnyEClass = createEClass(4);
		createEOperation(oclAnyEClass, 0);
		createEOperation(oclAnyEClass, 1);
		createEOperation(oclAnyEClass, 2);
		createEOperation(oclAnyEClass, 3);
		createEOperation(oclAnyEClass, 4);
		createEOperation(oclAnyEClass, 5);
		createEOperation(oclAnyEClass, 6);
		createEOperation(oclAnyEClass, 7);
		createEOperation(oclAnyEClass, 8);
		createEOperation(oclAnyEClass, 9);
		createEOperation(oclAnyEClass, 10);
		createEOperation(oclAnyEClass, 11);
		createEOperation(oclAnyEClass, 12);
		createEOperation(oclAnyEClass, 13);
		createEOperation(oclAnyEClass, 14);

		oclComparableEClass = createEClass(5);
		createEOperation(oclComparableEClass, 15);
		createEOperation(oclComparableEClass, 16);
		createEOperation(oclComparableEClass, 17);
		createEOperation(oclComparableEClass, 18);
		createEOperation(oclComparableEClass, 19);

		oclElementEClass = createEClass(6);
		createEReference(oclElementEClass, 0);
		createEReference(oclElementEClass, 1);
		createEOperation(oclElementEClass, 15);
		createEOperation(oclElementEClass, 16);
		createEOperation(oclElementEClass, 17);
		createEOperation(oclElementEClass, 18);
		createEOperation(oclElementEClass, 19);
		createEOperation(oclElementEClass, 20);
		createEOperation(oclElementEClass, 21);
		createEOperation(oclElementEClass, 22);
		createEOperation(oclElementEClass, 23);
		createEOperation(oclElementEClass, 24);
		createEOperation(oclElementEClass, 25);

		oclEnumerationEClass = createEClass(7);

		oclInvalidEClass = createEClass(8);
		createEReference(oclInvalidEClass, 0);
		createEOperation(oclInvalidEClass, 33);
		createEOperation(oclInvalidEClass, 34);
		createEOperation(oclInvalidEClass, 35);
		createEOperation(oclInvalidEClass, 36);
		createEOperation(oclInvalidEClass, 37);
		createEOperation(oclInvalidEClass, 38);
		createEOperation(oclInvalidEClass, 39);
		createEOperation(oclInvalidEClass, 40);
		createEOperation(oclInvalidEClass, 41);
		createEOperation(oclInvalidEClass, 42);
		createEOperation(oclInvalidEClass, 43);
		createEOperation(oclInvalidEClass, 44);
		createEOperation(oclInvalidEClass, 45);
		createEOperation(oclInvalidEClass, 46);

		oclLambdaEClass = createEClass(9);

		oclMessageEClass = createEClass(10);
		createEOperation(oclMessageEClass, 15);
		createEOperation(oclMessageEClass, 16);
		createEOperation(oclMessageEClass, 17);
		createEOperation(oclMessageEClass, 18);

		oclSelfEClass = createEClass(11);

		oclStateEClass = createEClass(12);

		oclStereotypeEClass = createEClass(13);

		oclSummableEClass = createEClass(14);
		createEOperation(oclSummableEClass, 15);
		createEOperation(oclSummableEClass, 16);

		oclTupleEClass = createEClass(15);
		createEOperation(oclTupleEClass, 15);
		createEOperation(oclTupleEClass, 16);

		oclTypeEClass = createEClass(16);
		createEOperation(oclTypeEClass, 26);

		oclVoidEClass = createEClass(17);
		createEOperation(oclVoidEClass, 15);
		createEOperation(oclVoidEClass, 16);
		createEOperation(oclVoidEClass, 17);
		createEOperation(oclVoidEClass, 18);
		createEOperation(oclVoidEClass, 19);
		createEOperation(oclVoidEClass, 20);
		createEOperation(oclVoidEClass, 21);
		createEOperation(oclVoidEClass, 22);
		createEOperation(oclVoidEClass, 23);
		createEOperation(oclVoidEClass, 24);
		createEOperation(oclVoidEClass, 25);
		createEOperation(oclVoidEClass, 26);
		createEOperation(oclVoidEClass, 27);
		createEOperation(oclVoidEClass, 28);
		createEOperation(oclVoidEClass, 29);
		createEOperation(oclVoidEClass, 30);
		createEOperation(oclVoidEClass, 31);
		createEOperation(oclVoidEClass, 32);

		orderedCollectionEClass = createEClass(18);
		createEOperation(orderedCollectionEClass, 48);
		createEOperation(orderedCollectionEClass, 49);
		createEOperation(orderedCollectionEClass, 50);
		createEOperation(orderedCollectionEClass, 51);
		createEOperation(orderedCollectionEClass, 52);

		orderedSetEClass = createEClass(19);
		createEOperation(orderedSetEClass, 58);
		createEOperation(orderedSetEClass, 59);
		createEOperation(orderedSetEClass, 60);
		createEOperation(orderedSetEClass, 61);
		createEOperation(orderedSetEClass, 62);
		createEOperation(orderedSetEClass, 63);
		createEOperation(orderedSetEClass, 64);
		createEOperation(orderedSetEClass, 65);
		createEOperation(orderedSetEClass, 66);
		createEOperation(orderedSetEClass, 67);
		createEOperation(orderedSetEClass, 68);
		createEOperation(orderedSetEClass, 69);
		createEOperation(orderedSetEClass, 70);
		createEOperation(orderedSetEClass, 71);
		createEOperation(orderedSetEClass, 72);
		createEOperation(orderedSetEClass, 73);
		createEOperation(orderedSetEClass, 74);
		createEOperation(orderedSetEClass, 75);
		createEOperation(orderedSetEClass, 76);
		createEOperation(orderedSetEClass, 77);
		createEOperation(orderedSetEClass, 78);
		createEOperation(orderedSetEClass, 79);
		createEOperation(orderedSetEClass, 80);

		sequenceEClass = createEClass(20);
		createEOperation(sequenceEClass, 53);
		createEOperation(sequenceEClass, 54);
		createEOperation(sequenceEClass, 55);
		createEOperation(sequenceEClass, 56);
		createEOperation(sequenceEClass, 57);
		createEOperation(sequenceEClass, 58);
		createEOperation(sequenceEClass, 59);
		createEOperation(sequenceEClass, 60);
		createEOperation(sequenceEClass, 61);
		createEOperation(sequenceEClass, 62);
		createEOperation(sequenceEClass, 63);
		createEOperation(sequenceEClass, 64);
		createEOperation(sequenceEClass, 65);
		createEOperation(sequenceEClass, 66);
		createEOperation(sequenceEClass, 67);
		createEOperation(sequenceEClass, 68);
		createEOperation(sequenceEClass, 69);
		createEOperation(sequenceEClass, 70);
		createEOperation(sequenceEClass, 71);
		createEOperation(sequenceEClass, 72);
		createEOperation(sequenceEClass, 73);
		createEOperation(sequenceEClass, 74);

		setEClass = createEClass(21);
		createEOperation(setEClass, 53);
		createEOperation(setEClass, 54);
		createEOperation(setEClass, 55);
		createEOperation(setEClass, 56);
		createEOperation(setEClass, 57);
		createEOperation(setEClass, 58);
		createEOperation(setEClass, 59);
		createEOperation(setEClass, 60);
		createEOperation(setEClass, 61);
		createEOperation(setEClass, 62);
		createEOperation(setEClass, 63);
		createEOperation(setEClass, 64);
		createEOperation(setEClass, 65);
		createEOperation(setEClass, 66);
		createEOperation(setEClass, 67);
		createEOperation(setEClass, 68);

		uniqueCollectionEClass = createEClass(22);
		createEOperation(uniqueCollectionEClass, 48);
		createEOperation(uniqueCollectionEClass, 49);
		createEOperation(uniqueCollectionEClass, 50);
		createEOperation(uniqueCollectionEClass, 51);
		createEOperation(uniqueCollectionEClass, 52);

		entry_r_rEClass = createEClass(23);
		createEReference(entry_r_rEClass, 0);
		createEReference(entry_r_rEClass, 1);

		// Create data types
		booleanEDataType = createEDataType(24);
		integerEDataType = createEDataType(25);
		realEDataType = createEDataType(26);
		stringEDataType = createEDataType(27);
		unlimitedNaturalEDataType = createEDataType(28);
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
		ETypeParameter bagEClass_T = addETypeParameter(bagEClass, "T"); //$NON-NLS-1$
		ETypeParameter collectionEClass_T = addETypeParameter(collectionEClass, "T"); //$NON-NLS-1$
		ETypeParameter entryEClass_K = addETypeParameter(entryEClass, "K"); //$NON-NLS-1$
		ETypeParameter entryEClass_V = addETypeParameter(entryEClass, "V"); //$NON-NLS-1$
		ETypeParameter mapEClass_K = addETypeParameter(mapEClass, "K"); //$NON-NLS-1$
		ETypeParameter mapEClass_V = addETypeParameter(mapEClass, "V"); //$NON-NLS-1$
		ETypeParameter orderedCollectionEClass_T = addETypeParameter(orderedCollectionEClass, "T"); //$NON-NLS-1$
		ETypeParameter orderedSetEClass_T = addETypeParameter(orderedSetEClass, "T"); //$NON-NLS-1$
		ETypeParameter sequenceEClass_T = addETypeParameter(sequenceEClass, "T"); //$NON-NLS-1$
		ETypeParameter setEClass_T = addETypeParameter(setEClass, "T"); //$NON-NLS-1$
		ETypeParameter uniqueCollectionEClass_T = addETypeParameter(uniqueCollectionEClass, "T"); //$NON-NLS-1$
		ETypeParameter entry_r_rEClass_K = addETypeParameter(entry_r_rEClass, "K"); //$NON-NLS-1$
		ETypeParameter entry_r_rEClass_V = addETypeParameter(entry_r_rEClass, "V"); //$NON-NLS-1$

		// Set bounds for type parameters

		// Add supertypes to classes
		EGenericType g1 = createEGenericType(this.getCollection());
		EGenericType g2 = createEGenericType(bagEClass_T);
		g1.getETypeArguments().add(g2);
		bagEClass.getEGenericSuperTypes().add(g1);
		oclComparableEClass.getESuperTypes().add(this.getOclAny());
		oclElementEClass.getESuperTypes().add(this.getOclAny());
		oclEnumerationEClass.getESuperTypes().add(this.getOclAny());
		oclInvalidEClass.getESuperTypes().add(this.getOclVoid());
		oclLambdaEClass.getESuperTypes().add(this.getOclAny());
		oclMessageEClass.getESuperTypes().add(this.getOclAny());
		oclSelfEClass.getESuperTypes().add(this.getOclAny());
		oclStateEClass.getESuperTypes().add(this.getOclAny());
		oclStereotypeEClass.getESuperTypes().add(this.getOclType());
		oclSummableEClass.getESuperTypes().add(this.getOclAny());
		oclTupleEClass.getESuperTypes().add(this.getOclAny());
		oclTypeEClass.getESuperTypes().add(this.getOclElement());
		oclVoidEClass.getESuperTypes().add(this.getOclAny());
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(orderedCollectionEClass_T);
		g1.getETypeArguments().add(g2);
		orderedCollectionEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getOrderedCollection());
		g2 = createEGenericType(orderedSetEClass_T);
		g1.getETypeArguments().add(g2);
		orderedSetEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getUniqueCollection());
		g2 = createEGenericType(orderedSetEClass_T);
		g1.getETypeArguments().add(g2);
		orderedSetEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getOrderedCollection());
		g2 = createEGenericType(sequenceEClass_T);
		g1.getETypeArguments().add(g2);
		sequenceEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getUniqueCollection());
		g2 = createEGenericType(setEClass_T);
		g1.getETypeArguments().add(g2);
		setEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(uniqueCollectionEClass_T);
		g1.getETypeArguments().add(g2);
		uniqueCollectionEClass.getEGenericSuperTypes().add(g1);

		// Initialize classes, features, and operations; add parameters
		initEClass(bagEClass, Bag.class, "Bag", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		EOperation op = initEOperation(getBag___ne___Object(), this.getBoolean(), "_ne_", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclSelf(), "object2", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getBag___eq___Object(), this.getBoolean(), "_eq_", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclSelf(), "object2", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getBag__Closure1__Object(), null, "closure1", 0, -1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		ETypeParameter t1 = addETypeParameter(op, "E"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getBag__Collect1__Object(), null, "collect1", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "E"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getBag__CollectNested1__Object(), null, "collectNested1", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "E"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getBag__Excluding__Object(), null, "excluding", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(bagEClass_T);
		addEParameter(op, g1, "object", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(bagEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getBag__ExcludingAll__Collection(), null, "excludingAll", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(bagEClass_T);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "objects", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(bagEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getBag__Flatten(), null, "flatten", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "T2"); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getBag__Including__Object(), null, "including", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(bagEClass_T);
		addEParameter(op, g1, "object", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(bagEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getBag__IncludingAll__Collection(), null, "includingAll", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(bagEClass_T);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "objects", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(bagEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getBag__Reject1__Object(), null, "reject1", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(bagEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getBag__Select1__Object(), null, "select1", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(bagEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getBag__SelectByKind__Object(), null, "selectByKind", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "TT"); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		addEParameter(op, g1, "type", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getBag__SelectByType__Object(), null, "selectByType", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "TT"); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		addEParameter(op, g1, "type", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getBag__SortedBy1__Object(), null, "sortedBy1", 0, -1, !IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(bagEClass_T);
		initEOperation(op, g1);

		initEClass(collectionEClass, Collection.class, "Collection", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		op = initEOperation(getCollection___ne___Object(), this.getBoolean(), "_ne_", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclSelf(), "object2", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCollection___eq___Object(), this.getBoolean(), "_eq_", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclSelf(), "object2", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCollection__Any1__Object(), null, "any1", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(collectionEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getCollection__AsBag(), null, "asBag", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(collectionEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getCollection__AsOrderedSet(), null, "asOrderedSet", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(collectionEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getCollection__AsSequence(), null, "asSequence", 0, -1, !IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(collectionEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getCollection__AsSet(), null, "asSet", 0, -1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(collectionEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getCollection__Collect1__Object(), null, "collect1", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "E"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(t1);
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = initEOperation(getCollection__CollectBy1__Object(), null, "collectBy1", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "E"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getEntry_r_r());
		g2 = createEGenericType(collectionEClass_T);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(t1);
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = initEOperation(getCollection__CollectNested1__Object(), null, "collectNested1", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "E"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(t1);
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = initEOperation(getCollection__Count__Object(), this.getInteger(), "count", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(collectionEClass_T);
		addEParameter(op, g1, "object", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCollection__Excludes__Object(), this.getBoolean(), "excludes", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(collectionEClass_T);
		addEParameter(op, g1, "object", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCollection__ExcludesAll__Collection(), this.getBoolean(), "excludesAll", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "T2"); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(t1);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "c2", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCollection__Excluding__Object(), null, "excluding", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(collectionEClass_T);
		addEParameter(op, g1, "object", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(collectionEClass_T);
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = initEOperation(getCollection__ExcludingAll__Collection(), null, "excludingAll", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(collectionEClass_T);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "objects", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(collectionEClass_T);
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = initEOperation(getCollection__Exists1__Object(), this.getBoolean(), "exists1", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCollection__Exists2__Object(), this.getBoolean(), "exists2", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCollection__Exists3__Object(), this.getBoolean(), "exists3", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCollection__Flatten(), null, "flatten", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "T2"); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(t1);
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = initEOperation(getCollection__ForAll1__Object(), this.getBoolean(), "forAll1", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCollection__ForAll2__Object(), this.getBoolean(), "forAll2", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCollection__ForAll3__Object(), this.getBoolean(), "forAll3", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCollection__Gather1__Object(), null, "gather1", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "V"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getCollection__Includes__Object(), this.getBoolean(), "includes", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(collectionEClass_T);
		addEParameter(op, g1, "object", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCollection__IncludesAll__Collection(), this.getBoolean(), "includesAll", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "T2"); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(t1);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "c2", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCollection__Including__Object(), null, "including", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(collectionEClass_T);
		addEParameter(op, g1, "object", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(collectionEClass_T);
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = initEOperation(getCollection__IncludingAll__Collection(), null, "includingAll", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(collectionEClass_T);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "objects", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(collectionEClass_T);
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = initEOperation(getCollection__Intersection__Collection(), null, "intersection", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(collectionEClass_T);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "c", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(collectionEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getCollection__Intersection__EList(), null, "intersection", 0, -1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(collectionEClass_T);
		addEParameter(op, g1, "u", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(collectionEClass_T);
		initEOperation(op, g1);

		initEOperation(getCollection__IsEmpty(), this.getBoolean(), "isEmpty", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCollection__IsUnique1__Object(), this.getBoolean(), "isUnique1", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCollection__Iterate1__Object(), null, "iterate1", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "Tacc"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getCollection__Max(), null, "max", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(collectionEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getCollection__Min(), null, "min", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(collectionEClass_T);
		initEOperation(op, g1);

		initEOperation(getCollection__NotEmpty(), this.getBoolean(), "notEmpty", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCollection__One1__Object(), this.getBoolean(), "one1", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCollection__Product__Collection(), this.getOclTuple(), "product", 0, -1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "T2"); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(t1);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "c2", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCollection__Reject1__Object(), null, "reject1", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(collectionEClass_T);
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = initEOperation(getCollection__Search1__Object_Object_Object(), null, "search1", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addETypeParameter(op, "Q"); //$NON-NLS-1$
		ETypeParameter t2 = addETypeParameter(op, "R"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "nextAcc", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "break_", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "return_", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t2);
		initEOperation(op, g1);

		op = initEOperation(getCollection__Search2__Object_Object_Object(), null, "search2", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addETypeParameter(op, "Q"); //$NON-NLS-1$
		t2 = addETypeParameter(op, "R"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "nextAcc", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "break_", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "return_", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t2);
		initEOperation(op, g1);

		op = initEOperation(getCollection__Search3__Object_Object_Object(), null, "search3", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addETypeParameter(op, "Q"); //$NON-NLS-1$
		t2 = addETypeParameter(op, "R"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "nextAcc", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "break_", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "return_", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t2);
		initEOperation(op, g1);

		op = initEOperation(getCollection__Select1__Object(), null, "select1", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(collectionEClass_T);
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = initEOperation(getCollection__SelectByKind__Object(), null, "selectByKind", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "TT"); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		addEParameter(op, g1, "type", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCollection__SelectByType__Object(), null, "selectByType", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "TT"); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		addEParameter(op, g1, "type", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getCollection__Size(), this.getInteger(), "size", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getCollection__SortedBy1__Object(), null, "sortedBy1", 0, -1, !IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(collectionEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getCollection__Sum(), null, "sum", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(collectionEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getCollection__Union__Collection(), null, "union", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(collectionEClass_T);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "c", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(collectionEClass_T);
		initEOperation(op, g1);

		initEClass(entryEClass, Map.Entry.class, "Entry", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		g1 = createEGenericType(entryEClass_K);
		initEReference(getEntry_Key(), g1, null, "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(entryEClass_V);
		initEReference(getEntry_Value(), g1, null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(mapEClass, Map.class, "Map", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		op = initEOperation(getMap___ne___Object(), this.getBoolean(), "_ne_", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclSelf(), "object2", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getMap___eq___Object(), this.getBoolean(), "_eq_", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclSelf(), "object2", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getMap__Any1__Object(), null, "any1", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(mapEClass_K);
		initEOperation(op, g1);

		op = initEOperation(getMap__At__Object(), null, "at", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(mapEClass_K);
		addEParameter(op, g1, "key", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(mapEClass_V);
		initEOperation(op, g1);

		op = initEOperation(getMap__Collect1__Object(), null, "collect1", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "E"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getMap__CollectBy1__Object(), null, "collectBy1", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "E"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getEntry_r_r());
		g2 = createEGenericType(mapEClass_K);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(t1);
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = initEOperation(getMap__CollectNested1__Object(), null, "collectNested1", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "E"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getMap__Excludes__Object(), this.getBoolean(), "excludes", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(mapEClass_K);
		addEParameter(op, g1, "key", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getMap__Excludes__Object_Object(), this.getBoolean(), "excludes", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(mapEClass_K);
		addEParameter(op, g1, "key", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(mapEClass_V);
		addEParameter(op, g1, "value", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getMap__ExcludesAll__Collection(), this.getBoolean(), "excludesAll", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "K2"); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(t1);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "coll", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getMap__ExcludesMap__EMap(), this.getBoolean(), "excludesMap", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "K2"); //$NON-NLS-1$
		t2 = addETypeParameter(op, "V2"); //$NON-NLS-1$
		g1 = createEGenericType(this.getEntry_r_r());
		g2 = createEGenericType(t1);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(t2);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "map", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getMap__ExcludesValue__Object(), this.getBoolean(), "excludesValue", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(mapEClass_V);
		addEParameter(op, g1, "value", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getMap__Excluding__Object(), null, "excluding", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(mapEClass_K);
		addEParameter(op, g1, "key", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getEntry_r_r());
		g2 = createEGenericType(mapEClass_K);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(mapEClass_V);
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = initEOperation(getMap__Excluding__Object_Object(), null, "excluding", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(mapEClass_K);
		addEParameter(op, g1, "key", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(mapEClass_V);
		addEParameter(op, g1, "value", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getEntry_r_r());
		g2 = createEGenericType(mapEClass_K);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(mapEClass_V);
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = initEOperation(getMap__ExcludingAll__Collection(), null, "excludingAll", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(mapEClass_K);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "keys", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getEntry_r_r());
		g2 = createEGenericType(mapEClass_K);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(mapEClass_V);
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = initEOperation(getMap__ExcludingMap__EMap(), null, "excludingMap", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "K2"); //$NON-NLS-1$
		t2 = addETypeParameter(op, "V2"); //$NON-NLS-1$
		g1 = createEGenericType(this.getEntry_r_r());
		g2 = createEGenericType(t1);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(t2);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "map", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getEntry_r_r());
		g2 = createEGenericType(mapEClass_K);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(mapEClass_V);
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = initEOperation(getMap__Exists1__Object(), this.getBoolean(), "exists1", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getMap__Exists2__Object(), this.getBoolean(), "exists2", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getMap__Exists3__Object(), this.getBoolean(), "exists3", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getMap__ForAll1__Object(), this.getBoolean(), "forAll1", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getMap__ForAll2__Object(), this.getBoolean(), "forAll2", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getMap__ForAll3__Object(), this.getBoolean(), "forAll3", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getMap__Gather1__Object(), null, "gather1", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "E"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getMap__Includes__Object(), this.getBoolean(), "includes", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(mapEClass_K);
		addEParameter(op, g1, "key", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getMap__Includes__Object_Object(), this.getBoolean(), "includes", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(mapEClass_K);
		addEParameter(op, g1, "key", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(mapEClass_V);
		addEParameter(op, g1, "value", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getMap__IncludesAll__Collection(), this.getBoolean(), "includesAll", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "K2"); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(t1);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "coll", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getMap__IncludesMap__EMap(), this.getBoolean(), "includesMap", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "K2"); //$NON-NLS-1$
		t2 = addETypeParameter(op, "V2"); //$NON-NLS-1$
		g1 = createEGenericType(this.getEntry_r_r());
		g2 = createEGenericType(t1);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(t2);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "map", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getMap__IncludesValue__Object(), this.getBoolean(), "includesValue", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(mapEClass_V);
		addEParameter(op, g1, "value", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getMap__Including__Object_Object(), null, "including", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(mapEClass_K);
		addEParameter(op, g1, "key", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(mapEClass_V);
		addEParameter(op, g1, "value", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getEntry_r_r());
		g2 = createEGenericType(mapEClass_K);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(mapEClass_V);
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = initEOperation(getMap__IncludingMap__EMap(), null, "includingMap", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "K2"); //$NON-NLS-1$
		t2 = addETypeParameter(op, "V2"); //$NON-NLS-1$
		g1 = createEGenericType(this.getEntry_r_r());
		g2 = createEGenericType(t1);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(t2);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "map", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getEntry_r_r());
		g2 = createEGenericType(mapEClass_K);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(mapEClass_V);
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		initEOperation(getMap__IsEmpty(), this.getBoolean(), "isEmpty", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getMap__IsUnique1__Object(), this.getBoolean(), "isUnique1", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getMap__Iterate1__Object(), null, "iterate1", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "Tacc"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getMap__Keys(), null, "keys", 0, -1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(mapEClass_K);
		initEOperation(op, g1);

		initEOperation(getMap__NotEmpty(), this.getBoolean(), "notEmpty", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getMap__One1__Object(), this.getBoolean(), "one1", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getMap__Reject1__Object(), null, "reject1", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getEntry_r_r());
		g2 = createEGenericType(mapEClass_K);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(mapEClass_V);
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = initEOperation(getMap__Search1__Object_Object_Object(), null, "search1", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addETypeParameter(op, "Q"); //$NON-NLS-1$
		t2 = addETypeParameter(op, "R"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "nextAcc", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "break_", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "return_", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t2);
		initEOperation(op, g1);

		op = initEOperation(getMap__Search2__Object_Object_Object(), null, "search2", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addETypeParameter(op, "Q"); //$NON-NLS-1$
		t2 = addETypeParameter(op, "R"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "nextAcc", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "break_", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "return_", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t2);
		initEOperation(op, g1);

		op = initEOperation(getMap__Search3__Object_Object_Object(), null, "search3", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addETypeParameter(op, "Q"); //$NON-NLS-1$
		t2 = addETypeParameter(op, "R"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "nextAcc", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "break_", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "return_", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t2);
		initEOperation(op, g1);

		op = initEOperation(getMap__Select1__Object(), null, "select1", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getEntry_r_r());
		g2 = createEGenericType(mapEClass_K);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(mapEClass_V);
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		initEOperation(getMap__Size(), this.getInteger(), "size", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getMap__Values(), null, "values", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(mapEClass_V);
		initEOperation(op, g1);

		initEClass(oclAnyEClass, Object.class, "OclAny", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		op = initEOperation(getOclAny___eq___Object(), this.getBoolean(), "_eq_", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclSelf(), "object2", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclAny___ne___Object(), this.getBoolean(), "_ne_", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclSelf(), "object2", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclAny__OclAsSet(), this.getOclSelf(), "oclAsSet", 1, 1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclAny__OclAsType__Object(), null, "oclAsType", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "TT"); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		addEParameter(op, g1, "type", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getOclAny__OclIsInState__Object(), this.getBoolean(), "oclIsInState", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclState(), "statespec", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclAny__OclIsInvalid(), this.getBoolean(), "oclIsInvalid", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclAny__OclIsKindOf__Object(), this.getBoolean(), "oclIsKindOf", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclType(), "type", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclAny__OclIsNew(), this.getBoolean(), "oclIsNew", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclAny__OclIsTypeOf__Object(), this.getBoolean(), "oclIsTypeOf", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclType(), "type", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclAny__OclIsUndefined(), this.getBoolean(), "oclIsUndefined", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclAny__OclLog(), this.getOclSelf(), "oclLog", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclAny__OclLog__String(), this.getOclSelf(), "oclLog", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getString(), "message", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclAny__OclType(), this.getOclSelf(), "oclType", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclAny__OclTypes(), this.getOclSelf(), "oclTypes", 1, 1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclAny__ToString(), this.getString(), "toString", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(oclComparableEClass, Object.class, "OclComparable", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		op = initEOperation(getOclComparable___ge___Object(), this.getBoolean(), "_ge_", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclSelf(), "that", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclComparable___gt___Object(), this.getBoolean(), "_gt_", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclSelf(), "that", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclComparable___le___Object(), this.getBoolean(), "_le_", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclSelf(), "that", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclComparable___lt___Object(), this.getBoolean(), "_lt_", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclSelf(), "that", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclComparable__CompareTo__Object(), this.getInteger(), "compareTo", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclSelf(), "that", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(oclElementEClass, Object.class, "OclElement", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getOclElement_OclContainer(), this.getOclElement(), null, "oclContainer", null, 0, 1, Object.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getOclElement_OclContents(), this.getOclElement(), null, "oclContents", null, 0, -1, Object.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclElement__OclAsModelType__Object(), null, "oclAsModelType", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "TT"); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		addEParameter(op, g1, "type", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		initEOperation(getOclElement__OclBase(), this.getOclType(), "oclBase", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclElement__OclBase__Object(), this.getOclType(), "oclBase", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclType(), "base", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclElement__OclContainer(), this.getOclElement(), "oclContainer", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclElement__OclContents(), this.getOclElement(), "oclContents", 0, -1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclElement__OclExtension__Object(), this.getOclElement(), "oclExtension", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclStereotype(), "stereotype", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclElement__OclExtensions(), this.getOclElement(), "oclExtensions", 0, -1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclElement__OclExtensions__Object(), this.getOclElement(), "oclExtensions", 0, -1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclStereotype(), "stereotype", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclElement__OclIsModelKindOf__Object(), this.getBoolean(), "oclIsModelKindOf", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclType(), "type", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclElement__OclModelType(), this.getOclSelf(), "oclModelType", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclElement__OclModelTypes(), this.getOclSelf(), "oclModelTypes", 1, 1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$

		initEClass(oclEnumerationEClass, Object.class, "OclEnumeration", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(oclInvalidEClass, Object.class, "OclInvalid", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getOclInvalid_OclBadProperty(), this.getOclAny(), null, "oclBadProperty", null, 0, 1, Object.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclInvalid___eq___Object(), this.getBoolean(), "_eq_", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclSelf(), "object2", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclInvalid___ne___Object(), this.getBoolean(), "_ne_", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclSelf(), "object2", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclInvalid__And__Boolean(), this.getBoolean(), "and", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getBoolean(), "b", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclInvalid__Implies__Boolean(), this.getBoolean(), "implies", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getBoolean(), "b", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclInvalid__OclAsSet(), this.getOclSelf(), "oclAsSet", 0, -1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclInvalid__OclAsType__Object(), null, "oclAsType", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "TT"); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		addEParameter(op, g1, "type", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		initEOperation(getOclInvalid__OclBadOperation(), this.getOclAny(), "oclBadOperation", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclInvalid__OclIsInvalid(), this.getBoolean(), "oclIsInvalid", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclInvalid__OclIsKindOf__Object(), this.getBoolean(), "oclIsKindOf", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclType(), "type", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclInvalid__OclIsTypeOf__Object(), this.getBoolean(), "oclIsTypeOf", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclType(), "type", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclInvalid__OclIsUndefined(), this.getBoolean(), "oclIsUndefined", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclInvalid__OclType(), this.getOclSelf(), "oclType", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclInvalid__Or__Boolean(), this.getBoolean(), "or", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getBoolean(), "b", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclInvalid__ToString(), this.getString(), "toString", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(oclLambdaEClass, Object.class, "OclLambda", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(oclMessageEClass, Object.class, "OclMessage", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEOperation(getOclMessage__HasReturned(), this.getBoolean(), "hasReturned", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclMessage__IsOperationCall(), this.getBoolean(), "isOperationCall", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclMessage__IsSignalSent(), this.getBoolean(), "isSignalSent", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclMessage__Result(), this.getOclAny(), "result", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(oclSelfEClass, Object.class, "OclSelf", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(oclStateEClass, Object.class, "OclState", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(oclStereotypeEClass, Object.class, "OclStereotype", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(oclSummableEClass, Object.class, "OclSummable", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		op = initEOperation(getOclSummable__Sum__Object(), this.getOclSelf(), "sum", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclSelf(), "that", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclSummable__Zero(), this.getOclSelf(), "zero", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(oclTupleEClass, Object.class, "OclTuple", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		op = initEOperation(getOclTuple___eq___Object(), this.getBoolean(), "_eq_", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclSelf(), "object2", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclTuple___ne___Object(), this.getBoolean(), "_ne_", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclSelf(), "object2", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(oclTypeEClass, Object.class, "OclType", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		op = initEOperation(getOclType__ConformsTo__Object(), this.getBoolean(), "conformsTo", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclType(), "type2", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(oclVoidEClass, Object.class, "OclVoid", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		op = initEOperation(getOclVoid___add___String(), this.getString(), "_add_", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getString(), "s", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclVoid___eq___Object(), this.getBoolean(), "_eq_", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclSelf(), "object2", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclVoid___ne___Object(), this.getBoolean(), "_ne_", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclSelf(), "object2", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclVoid__And__Boolean(), this.getBoolean(), "and", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getBoolean(), "b", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclVoid__Concat__String(), this.getString(), "concat", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getString(), "s", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclVoid__Implies__Boolean(), this.getBoolean(), "implies", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getBoolean(), "b", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclVoid__Not(), this.getBoolean(), "not", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclVoid__OclAsSet(), this.getOclSelf(), "oclAsSet", 0, 1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclVoid__OclAsType__Object(), null, "oclAsType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "TT"); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		addEParameter(op, g1, "type", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		initEOperation(getOclVoid__OclIsInvalid(), this.getBoolean(), "oclIsInvalid", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclVoid__OclIsKindOf__Object(), this.getBoolean(), "oclIsKindOf", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclType(), "type", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclVoid__OclIsTypeOf__Object(), this.getBoolean(), "oclIsTypeOf", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclType(), "type", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclVoid__OclIsUndefined(), this.getBoolean(), "oclIsUndefined", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclVoid__OclType(), this.getOclSelf(), "oclType", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclVoid__OclTypes(), this.getOclSelf(), "oclTypes", 1, 1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclVoid__Or__Boolean(), this.getBoolean(), "or", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getBoolean(), "b", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEOperation(getOclVoid__ToString(), this.getString(), "toString", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOclVoid__Xor__Boolean(), this.getBoolean(), "xor", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getBoolean(), "b", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(orderedCollectionEClass, Collection.class, "OrderedCollection", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		op = initEOperation(getOrderedCollection__At__IntegerValue(), null, "at", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getInteger(), "index", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(orderedCollectionEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getOrderedCollection__First(), null, "first", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(orderedCollectionEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getOrderedCollection__Gather1__Object(), null, "gather1", 0, -1, !IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "E"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getOrderedCollection__IndexOf__Object(), this.getInteger(), "indexOf", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(orderedCollectionEClass_T);
		addEParameter(op, g1, "obj", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOrderedCollection__Last(), null, "last", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(orderedCollectionEClass_T);
		initEOperation(op, g1);

		initEClass(orderedSetEClass, OrderedSet.class, "OrderedSet", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		op = initEOperation(getOrderedSet___sub___EList(), null, "_sub_", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclAny(), "s", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(orderedSetEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getOrderedSet___ne___Object(), this.getBoolean(), "_ne_", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclSelf(), "object2", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOrderedSet___eq___Object(), this.getBoolean(), "_eq_", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclSelf(), "object2", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getOrderedSet__Append__Object(), null, "append", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(orderedSetEClass_T);
		addEParameter(op, g1, "object", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(orderedSetEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getOrderedSet__AppendAll__EList(), null, "appendAll", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(orderedSetEClass_T);
		addEParameter(op, g1, "objects", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(orderedSetEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getOrderedSet__Closure1__Object(), null, "closure1", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "E"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getOrderedSet__Collect1__Object(), null, "collect1", 0, -1, !IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "E"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getOrderedSet__CollectNested1__Object(), null, "collectNested1", 0, -1, !IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "E"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getOrderedSet__Excluding__Object(), null, "excluding", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(orderedSetEClass_T);
		addEParameter(op, g1, "object", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(orderedSetEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getOrderedSet__ExcludingAll__Collection(), null, "excludingAll", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(orderedSetEClass_T);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "objects", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(orderedSetEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getOrderedSet__Flatten(), null, "flatten", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "T2"); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getOrderedSet__Including__Object(), null, "including", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(orderedSetEClass_T);
		addEParameter(op, g1, "object", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(orderedSetEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getOrderedSet__IncludingAll__Collection(), null, "includingAll", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(orderedSetEClass_T);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "objects", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(orderedSetEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getOrderedSet__InsertAt__IntegerValue_Object(), null, "insertAt", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getInteger(), "index", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(orderedSetEClass_T);
		addEParameter(op, g1, "object", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(orderedSetEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getOrderedSet__Prepend__Object(), null, "prepend", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(orderedSetEClass_T);
		addEParameter(op, g1, "object", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(orderedSetEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getOrderedSet__PrependAll__EList(), null, "prependAll", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(orderedSetEClass_T);
		addEParameter(op, g1, "objects", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(orderedSetEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getOrderedSet__Reject1__Object(), null, "reject1", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(orderedSetEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getOrderedSet__Reverse(), null, "reverse", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(orderedSetEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getOrderedSet__Select1__Object(), null, "select1", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(orderedSetEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getOrderedSet__SelectByKind__Object(), null, "selectByKind", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "TT"); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		addEParameter(op, g1, "type", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getOrderedSet__SelectByType__Object(), null, "selectByType", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "TT"); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		addEParameter(op, g1, "type", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getOrderedSet__SortedBy1__Object(), null, "sortedBy1", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(orderedSetEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getOrderedSet__SubOrderedSet__IntegerValue_IntegerValue(), null, "subOrderedSet", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getInteger(), "lower", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getInteger(), "upper", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(orderedSetEClass_T);
		initEOperation(op, g1);

		initEClass(sequenceEClass, List.class, "Sequence", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		op = initEOperation(getSequence___ne___Object(), this.getBoolean(), "_ne_", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclSelf(), "object2", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getSequence___eq___Object(), this.getBoolean(), "_eq_", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclSelf(), "object2", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getSequence__Append__Object(), null, "append", 0, -1, !IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(sequenceEClass_T);
		addEParameter(op, g1, "object", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(sequenceEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getSequence__AppendAll__EList(), null, "appendAll", 0, -1, !IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(sequenceEClass_T);
		addEParameter(op, g1, "objects", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(sequenceEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getSequence__Closure1__Object(), null, "closure1", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "E"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getSequence__Collect1__Object(), null, "collect1", 0, -1, !IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "E"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getSequence__CollectNested1__Object(), null, "collectNested1", 0, -1, !IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "E"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getSequence__Excluding__Object(), null, "excluding", 0, -1, !IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(sequenceEClass_T);
		addEParameter(op, g1, "object", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(sequenceEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getSequence__ExcludingAll__Collection(), null, "excludingAll", 0, -1, !IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(sequenceEClass_T);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "objects", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(sequenceEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getSequence__Flatten(), null, "flatten", 0, -1, !IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "T2"); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getSequence__Including__Object(), null, "including", 0, -1, !IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(sequenceEClass_T);
		addEParameter(op, g1, "object", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(sequenceEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getSequence__IncludingAll__Collection(), null, "includingAll", 0, -1, !IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(sequenceEClass_T);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "objects", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(sequenceEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getSequence__InsertAt__IntegerValue_Object(), null, "insertAt", 0, -1, !IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getInteger(), "index", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(sequenceEClass_T);
		addEParameter(op, g1, "object", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(sequenceEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getSequence__Prepend__Object(), null, "prepend", 0, -1, !IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(sequenceEClass_T);
		addEParameter(op, g1, "object", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(sequenceEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getSequence__PrependAll__EList(), null, "prependAll", 0, -1, !IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(sequenceEClass_T);
		addEParameter(op, g1, "objects", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(sequenceEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getSequence__Reject1__Object(), null, "reject1", 0, -1, !IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(sequenceEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getSequence__Reverse(), null, "reverse", 0, -1, !IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(sequenceEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getSequence__Select1__Object(), null, "select1", 0, -1, !IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(sequenceEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getSequence__SelectByKind__Object(), null, "selectByKind", 0, -1, !IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "TT"); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		addEParameter(op, g1, "type", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getSequence__SelectByType__Object(), null, "selectByType", 0, -1, !IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "TT"); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		addEParameter(op, g1, "type", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getSequence__SortedBy1__Object(), null, "sortedBy1", 0, -1, !IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(sequenceEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getSequence__SubSequence__IntegerValue_IntegerValue(), null, "subSequence", 0, -1, !IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getInteger(), "lower", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getInteger(), "upper", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(sequenceEClass_T);
		initEOperation(op, g1);

		initEClass(setEClass, Set.class, "Set", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		op = initEOperation(getSet___sub___EList(), null, "_sub_", 0, -1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclAny(), "s", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(setEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getSet___ne___Object(), this.getBoolean(), "_ne_", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclSelf(), "object2", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getSet___eq___Object(), this.getBoolean(), "_eq_", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclSelf(), "object2", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = initEOperation(getSet__Closure1__Object(), null, "closure1", 0, -1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "E"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getSet__Collect1__Object(), null, "collect1", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "E"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getSet__CollectNested1__Object(), null, "collectNested1", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "E"); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getSet__Excluding__Object(), null, "excluding", 0, -1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(setEClass_T);
		addEParameter(op, g1, "object", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(setEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getSet__ExcludingAll__Collection(), null, "excludingAll", 0, -1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(setEClass_T);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "objects", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(setEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getSet__Flatten(), null, "flatten", 0, -1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "T2"); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getSet__Including__Object(), null, "including", 0, -1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(setEClass_T);
		addEParameter(op, g1, "object", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(setEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getSet__IncludingAll__Collection(), null, "includingAll", 0, -1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(setEClass_T);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "objects", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(setEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getSet__Reject1__Object(), null, "reject1", 0, -1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(setEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getSet__Select1__Object(), null, "select1", 0, -1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(setEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getSet__SelectByKind__Object(), null, "selectByKind", 0, -1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "TT"); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		addEParameter(op, g1, "type", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getSet__SelectByType__Object(), null, "selectByType", 0, -1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		t1 = addETypeParameter(op, "TT"); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		addEParameter(op, g1, "type", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(t1);
		initEOperation(op, g1);

		op = initEOperation(getSet__SortedBy1__Object(), null, "sortedBy1", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(setEClass_T);
		initEOperation(op, g1);

		initEClass(uniqueCollectionEClass, Collection.class, "UniqueCollection", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		op = initEOperation(getUniqueCollection___sub___EList(), null, "_sub_", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclAny(), "s", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(uniqueCollectionEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getUniqueCollection__Intersection__Collection(), null, "intersection", 0, -1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(this.getCollection());
		g2 = createEGenericType(uniqueCollectionEClass_T);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "c", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(uniqueCollectionEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getUniqueCollection__SortedBy1__Object(), null, "sortedBy1", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclLambda(), "lambda", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(uniqueCollectionEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getUniqueCollection__SymmetricDifference__EList(), null, "symmetricDifference", 0, -1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getOclAny(), "s", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(uniqueCollectionEClass_T);
		initEOperation(op, g1);

		op = initEOperation(getUniqueCollection__Union__EList(), null, "union", 0, -1, IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(uniqueCollectionEClass_T);
		addEParameter(op, g1, "s", 0, -1, !IS_UNIQUE, !IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(uniqueCollectionEClass_T);
		initEOperation(op, g1);

		initEClass(entry_r_rEClass, Map.Entry.class, "Entry_r_r", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		g1 = createEGenericType(entry_r_rEClass_K);
		initEReference(getEntry_r_r_Key(), g1, null, "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		g1 = createEGenericType(entry_r_rEClass_V);
		initEReference(getEntry_r_r_Value(), g1, null, "value", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		// Initialize data types
		initEDataType(booleanEDataType, Boolean.class, "Boolean", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(integerEDataType, IntegerValue.class, "Integer", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(realEDataType, RealValue.class, "Real", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(stringEDataType, String.class, "String", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(unlimitedNaturalEDataType, UnlimitedNaturalValue.class, "UnlimitedNatural", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/OCL/ASLibrary
		createASLibraryAnnotations();
		// http://www.eclipse.org/uml2/2.0.0/UML
		createUMLAnnotations();
		// http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot
		createPivotAnnotations();
		// http://www.eclipse.org/OCL/Collection
		createCollectionAnnotations();
		// http://www.eclipse.org/OCL/CoEClass
		createCoEClassAnnotations();
		// http://www.eclipse.org/OCL/EntryEClass
		createEntryEClassAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/OCL/ASLibrary</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createASLibraryAnnotations() {
		String source = "http://www.eclipse.org/OCL/ASLibrary"; //$NON-NLS-1$
		addAnnotation
		  (this,
		   source,
		   new String[] {
		   });
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/uml2/2.0.0/UML</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createUMLAnnotations() {
		String source = "http://www.eclipse.org/uml2/2.0.0/UML"; //$NON-NLS-1$
		addAnnotation
		  (getBag___ne___Object(),
		   source,
		   new String[] {
			   "originalName", "<>" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getBag___eq___Object(),
		   source,
		   new String[] {
			   "originalName", "=" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getBag__Closure1__Object(),
		   source,
		   new String[] {
			   "originalName", "closure" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getBag__Collect1__Object(),
		   source,
		   new String[] {
			   "originalName", "collect" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getBag__CollectNested1__Object(),
		   source,
		   new String[] {
			   "originalName", "collectNested" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getBag__Reject1__Object(),
		   source,
		   new String[] {
			   "originalName", "reject" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getBag__Select1__Object(),
		   source,
		   new String[] {
			   "originalName", "select" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getBag__SortedBy1__Object(),
		   source,
		   new String[] {
			   "originalName", "sortedBy" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection___ne___Object(),
		   source,
		   new String[] {
			   "originalName", "<>" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection___eq___Object(),
		   source,
		   new String[] {
			   "originalName", "=" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Any1__Object(),
		   source,
		   new String[] {
			   "originalName", "any" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Collect1__Object(),
		   source,
		   new String[] {
			   "originalName", "collect" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__CollectBy1__Object(),
		   source,
		   new String[] {
			   "originalName", "collectBy" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__CollectNested1__Object(),
		   source,
		   new String[] {
			   "originalName", "collectNested" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Exists1__Object(),
		   source,
		   new String[] {
			   "originalName", "exists" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Exists2__Object(),
		   source,
		   new String[] {
			   "originalName", "exists" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Exists3__Object(),
		   source,
		   new String[] {
			   "originalName", "exists" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__ForAll1__Object(),
		   source,
		   new String[] {
			   "originalName", "forAll" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__ForAll2__Object(),
		   source,
		   new String[] {
			   "originalName", "forAll" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__ForAll3__Object(),
		   source,
		   new String[] {
			   "originalName", "forAll" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Gather1__Object(),
		   source,
		   new String[] {
			   "originalName", "gather" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__IsUnique1__Object(),
		   source,
		   new String[] {
			   "originalName", "isUnique" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Iterate1__Object(),
		   source,
		   new String[] {
			   "originalName", "iterate" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__One1__Object(),
		   source,
		   new String[] {
			   "originalName", "one" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Reject1__Object(),
		   source,
		   new String[] {
			   "originalName", "reject" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Search1__Object_Object_Object(),
		   source,
		   new String[] {
			   "originalName", "search" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Search2__Object_Object_Object(),
		   source,
		   new String[] {
			   "originalName", "search" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Search3__Object_Object_Object(),
		   source,
		   new String[] {
			   "originalName", "search" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Select1__Object(),
		   source,
		   new String[] {
			   "originalName", "select" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__SortedBy1__Object(),
		   source,
		   new String[] {
			   "originalName", "sortedBy" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap___ne___Object(),
		   source,
		   new String[] {
			   "originalName", "<>" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap___eq___Object(),
		   source,
		   new String[] {
			   "originalName", "=" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Any1__Object(),
		   source,
		   new String[] {
			   "originalName", "any" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Collect1__Object(),
		   source,
		   new String[] {
			   "originalName", "collect" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__CollectBy1__Object(),
		   source,
		   new String[] {
			   "originalName", "collectBy" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__CollectNested1__Object(),
		   source,
		   new String[] {
			   "originalName", "collectNested" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Exists1__Object(),
		   source,
		   new String[] {
			   "originalName", "exists" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Exists2__Object(),
		   source,
		   new String[] {
			   "originalName", "exists" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Exists3__Object(),
		   source,
		   new String[] {
			   "originalName", "exists" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__ForAll1__Object(),
		   source,
		   new String[] {
			   "originalName", "forAll" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__ForAll2__Object(),
		   source,
		   new String[] {
			   "originalName", "forAll" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__ForAll3__Object(),
		   source,
		   new String[] {
			   "originalName", "forAll" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Gather1__Object(),
		   source,
		   new String[] {
			   "originalName", "gather" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__IsUnique1__Object(),
		   source,
		   new String[] {
			   "originalName", "isUnique" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Iterate1__Object(),
		   source,
		   new String[] {
			   "originalName", "iterate" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__One1__Object(),
		   source,
		   new String[] {
			   "originalName", "one" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Reject1__Object(),
		   source,
		   new String[] {
			   "originalName", "reject" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Search1__Object_Object_Object(),
		   source,
		   new String[] {
			   "originalName", "search" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Search2__Object_Object_Object(),
		   source,
		   new String[] {
			   "originalName", "search" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Search3__Object_Object_Object(),
		   source,
		   new String[] {
			   "originalName", "search" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Select1__Object(),
		   source,
		   new String[] {
			   "originalName", "select" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclAny___eq___Object(),
		   source,
		   new String[] {
			   "originalName", "=" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclAny___ne___Object(),
		   source,
		   new String[] {
			   "originalName", "<>" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclComparable___ge___Object(),
		   source,
		   new String[] {
			   "originalName", ">=" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclComparable___gt___Object(),
		   source,
		   new String[] {
			   "originalName", ">" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclComparable___le___Object(),
		   source,
		   new String[] {
			   "originalName", "<=" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclComparable___lt___Object(),
		   source,
		   new String[] {
			   "originalName", "<" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclInvalid___eq___Object(),
		   source,
		   new String[] {
			   "originalName", "=" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclInvalid___ne___Object(),
		   source,
		   new String[] {
			   "originalName", "<>" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclTuple___eq___Object(),
		   source,
		   new String[] {
			   "originalName", "=" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclTuple___ne___Object(),
		   source,
		   new String[] {
			   "originalName", "<>" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclVoid___add___String(),
		   source,
		   new String[] {
			   "originalName", "+" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclVoid___eq___Object(),
		   source,
		   new String[] {
			   "originalName", "=" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclVoid___ne___Object(),
		   source,
		   new String[] {
			   "originalName", "<>" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedCollection__Gather1__Object(),
		   source,
		   new String[] {
			   "originalName", "gather" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet___sub___EList(),
		   source,
		   new String[] {
			   "originalName", "-" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet___ne___Object(),
		   source,
		   new String[] {
			   "originalName", "<>" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet___eq___Object(),
		   source,
		   new String[] {
			   "originalName", "=" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__Closure1__Object(),
		   source,
		   new String[] {
			   "originalName", "closure" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__Collect1__Object(),
		   source,
		   new String[] {
			   "originalName", "collect" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__CollectNested1__Object(),
		   source,
		   new String[] {
			   "originalName", "collectNested" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__Reject1__Object(),
		   source,
		   new String[] {
			   "originalName", "reject" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__Select1__Object(),
		   source,
		   new String[] {
			   "originalName", "select" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__SortedBy1__Object(),
		   source,
		   new String[] {
			   "originalName", "sortedBy" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence___ne___Object(),
		   source,
		   new String[] {
			   "originalName", "<>" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence___eq___Object(),
		   source,
		   new String[] {
			   "originalName", "=" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__Closure1__Object(),
		   source,
		   new String[] {
			   "originalName", "closure" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__Collect1__Object(),
		   source,
		   new String[] {
			   "originalName", "collect" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__CollectNested1__Object(),
		   source,
		   new String[] {
			   "originalName", "collectNested" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__Reject1__Object(),
		   source,
		   new String[] {
			   "originalName", "reject" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__Select1__Object(),
		   source,
		   new String[] {
			   "originalName", "select" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__SortedBy1__Object(),
		   source,
		   new String[] {
			   "originalName", "sortedBy" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet___sub___EList(),
		   source,
		   new String[] {
			   "originalName", "-" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet___ne___Object(),
		   source,
		   new String[] {
			   "originalName", "<>" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet___eq___Object(),
		   source,
		   new String[] {
			   "originalName", "=" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet__Closure1__Object(),
		   source,
		   new String[] {
			   "originalName", "closure" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet__Collect1__Object(),
		   source,
		   new String[] {
			   "originalName", "collect" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet__CollectNested1__Object(),
		   source,
		   new String[] {
			   "originalName", "collectNested" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet__Reject1__Object(),
		   source,
		   new String[] {
			   "originalName", "reject" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet__Select1__Object(),
		   source,
		   new String[] {
			   "originalName", "select" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet__SortedBy1__Object(),
		   source,
		   new String[] {
			   "originalName", "sortedBy" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getUniqueCollection___sub___EList(),
		   source,
		   new String[] {
			   "originalName", "-" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getUniqueCollection__SortedBy1__Object(),
		   source,
		   new String[] {
			   "originalName", "sortedBy" //$NON-NLS-1$ //$NON-NLS-2$
		   });
	}


	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createPivotAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot"; //$NON-NLS-1$
		addAnnotation
		  (getBag___ne___Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getBag___eq___Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getBag__Closure1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.ClosureIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getBag__Collect1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.CollectIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getBag__CollectNested1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getBag__Excluding__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getBag__ExcludingAll__Collection(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getBag__Flatten(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getBag__Including__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getBag__IncludingAll__Collection(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getBag__Reject1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.RejectIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getBag__Select1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.SelectIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getBag__SelectByKind__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getBag__SelectByType__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getBag__SortedBy1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.SortedByIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection___ne___Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection___eq___Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Any1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.AnyIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__AsBag(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionAsBagOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__AsOrderedSet(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionAsOrderedSetOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__AsSequence(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionAsSequenceOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__AsSet(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionAsSetOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Collect1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.CollectIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__CollectBy1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.CollectByIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__CollectNested1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Count__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionCountOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Excludes__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionExcludesOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__ExcludesAll__Collection(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionExcludesAllOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Excluding__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__ExcludingAll__Collection(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Exists1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.ExistsIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Exists2__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.ExistsIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Exists3__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.ExistsIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Flatten(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__ForAll1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.ForAllIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__ForAll2__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.ForAllIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__ForAll3__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.ForAllIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Gather1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.GatherIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Includes__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionIncludesOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__IncludesAll__Collection(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionIncludesAllOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Including__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__IncludingAll__Collection(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Intersection__Collection(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Intersection__EList(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__IsEmpty(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionIsEmptyOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__IsUnique1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.IsUniqueIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Iterate1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.IterateIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Max(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionMaxOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Min(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionMinOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__NotEmpty(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionNotEmptyOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__One1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.OneIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Product__Collection(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionProductOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Reject1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.RejectIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Search1__Object_Object_Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.SearchIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Search2__Object_Object_Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.SearchIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Search3__Object_Object_Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.SearchIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Select1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.SelectIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__SelectByKind__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__SelectByType__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Size(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionSizeOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__SortedBy1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.SortedByIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Sum(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionSumOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Union__Collection(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionUnionOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getEntry_Key(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.map.EntryKeyProperty" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getEntry_Value(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.map.EntryValueProperty" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap___ne___Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap___eq___Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Any1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.AnyIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__At__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.map.MapAtOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Collect1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.CollectIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__CollectBy1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.CollectByIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__CollectNested1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Excludes__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.map.MapExcludesOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Excludes__Object_Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.map.MapExcludesPairOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__ExcludesAll__Collection(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.map.MapExcludesAllOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__ExcludesMap__EMap(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.map.MapExcludesMapOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__ExcludesValue__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.map.MapExcludesValueOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Excluding__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.map.MapExcludingOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Excluding__Object_Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.map.MapExcludingPairOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__ExcludingAll__Collection(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.map.MapExcludingAllOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__ExcludingMap__EMap(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.map.MapExcludingMapOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Exists1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.ExistsIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Exists2__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.ExistsIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Exists3__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.ExistsIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__ForAll1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.ForAllIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__ForAll2__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.ForAllIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__ForAll3__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.ForAllIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Gather1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.GatherIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Includes__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.map.MapIncludesOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Includes__Object_Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.map.MapIncludesPairOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__IncludesAll__Collection(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.map.MapIncludesAllOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__IncludesMap__EMap(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.map.MapIncludesMapOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__IncludesValue__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.map.MapIncludesValueOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Including__Object_Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.map.MapIncludingPairOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__IncludingMap__EMap(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.map.MapIncludingMapOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__IsEmpty(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.map.MapIsEmptyOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__IsUnique1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.IsUniqueIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Iterate1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.IterateIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Keys(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.map.MapKeysOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__NotEmpty(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.map.MapNotEmptyOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__One1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.OneIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Reject1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.MapRejectIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Search1__Object_Object_Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.SearchIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Search2__Object_Object_Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.SearchIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Search3__Object_Object_Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.SearchIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Select1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.MapSelectIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Size(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.map.MapSizeOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getMap__Values(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.map.MapValuesOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclAny___eq___Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation", //$NON-NLS-1$ //$NON-NLS-2$
			   "post", "result = (self = object2)" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclAny___ne___Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation", //$NON-NLS-1$ //$NON-NLS-2$
			   "post", "result = not (self = object2)" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclAny__OclAsSet(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclAny__OclAsType__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation", //$NON-NLS-1$ //$NON-NLS-2$
			   "post_IsSelf", "result = self" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclAny__OclIsInState__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInStateOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclAny__OclIsInvalid(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInvalidOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclAny__OclIsKindOf__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclAny__OclIsNew(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclAny__OclIsTypeOf__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsTypeOfOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclAny__OclIsUndefined(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsUndefinedOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclAny__OclLog(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclLogOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclAny__OclLog__String(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclLogOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclAny__OclType(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypeOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclAny__OclTypes(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypesOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclAny__ToString(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclComparable___ge___Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclComparableGreaterThanEqualOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclComparable___gt___Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclComparableGreaterThanOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclComparable___le___Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclComparable___lt___Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclComparable__CompareTo__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclComparableCompareToOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclElement__OclAsModelType__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclElementOclAsModelTypeOperation", //$NON-NLS-1$ //$NON-NLS-2$
			   "post_IsSelf", "result = self" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclElement__OclBase(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclElementOclBaseOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclElement__OclBase__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclElementOclBaseOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclElement__OclContainer(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.classifier.ClassifierOclContainerOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclElement__OclContents(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.classifier.ClassifierOclContentsOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclElement__OclExtension__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclElementOclExtensionOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclElement__OclExtensions(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclElementOclExtensionsOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclElement__OclExtensions__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclElementOclExtensionsOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclElement__OclIsModelKindOf__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclElementOclIsModelKindOfOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclElement__OclModelType(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclElementOclModelTypeOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclElement__OclModelTypes(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclElementOclModelTypesOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclElement_OclContainer(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclElementOclContainerProperty" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclElement_OclContents(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclElementOclContentsProperty" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclInvalid___eq___Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclInvalid___ne___Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclInvalid__And__Boolean(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.logical.BooleanAndOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclInvalid__Implies__Boolean(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclInvalid__OclAsSet(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclInvalid__OclAsType__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclInvalid__OclIsInvalid(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInvalidOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclInvalid__OclIsKindOf__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclInvalid__OclIsTypeOf__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsTypeOfOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclInvalid__OclIsUndefined(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsUndefinedOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclInvalid__OclType(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypeOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclInvalid__Or__Boolean(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.logical.BooleanAndOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclInvalid__ToString(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclMessage__HasReturned(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclMessage__IsOperationCall(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclMessage__IsSignalSent(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclMessage__Result(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclTuple___eq___Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclTuple___ne___Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclType__ConformsTo__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.classifier.OclTypeConformsToOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclVoid___add___String(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.string.StringConcatOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclVoid___eq___Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclVoid___ne___Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclVoid__And__Boolean(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclvoid.OclVoidAndOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclVoid__Concat__String(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.string.StringConcatOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclVoid__Implies__Boolean(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclvoid.OclVoidImpliesOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclVoid__Not(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.logical.BooleanNotOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclVoid__OclAsSet(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclVoid__OclAsType__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclVoid__OclIsInvalid(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInvalidOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclVoid__OclIsKindOf__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclVoid__OclIsTypeOf__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsTypeOfOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclVoid__OclIsUndefined(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsUndefinedOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclVoid__OclType(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypeOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclVoid__OclTypes(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyOclTypesOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclVoid__Or__Boolean(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclvoid.OclVoidOrOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclVoid__ToString(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyToStringOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclVoid__Xor__Boolean(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.logical.BooleanXorOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedCollection__At__IntegerValue(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionAtOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedCollection__First(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionFirstOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedCollection__Gather1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.GatherIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedCollection__IndexOf__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionIndexOfOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedCollection__Last(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionLastOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet___sub___EList(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.SetMinusOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet___ne___Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet___eq___Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__Append__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__AppendAll__EList(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendAllOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__Closure1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.ClosureIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__Collect1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.CollectIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__CollectNested1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__Excluding__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__ExcludingAll__Collection(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__Flatten(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__Including__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__IncludingAll__Collection(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__InsertAt__IntegerValue_Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionInsertAtOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__Prepend__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependOperation", //$NON-NLS-1$ //$NON-NLS-2$
			   "post_IsSizePlusOne", "result->size() = self->size() + 1", //$NON-NLS-1$ //$NON-NLS-2$
			   "post_IsAtStart", "result->at(1) = object", //$NON-NLS-1$ //$NON-NLS-2$
			   "post_IsShiftedAlong", "Sequence{1..self->size()}->forAll(index : Integer[1] | self->at(index) = result->at(index + 1))" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__PrependAll__EList(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependAllOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__Reject1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.RejectIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__Reverse(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionReverseOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__Select1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.SelectIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__SelectByKind__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__SelectByType__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__SortedBy1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.SortedByIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__SubOrderedSet__IntegerValue_IntegerValue(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.OrderedSetSubOrderedSetOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence___ne___Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence___eq___Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__Append__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__AppendAll__EList(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionAppendAllOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__Closure1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.ClosureIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__Collect1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.CollectIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__CollectNested1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__Excluding__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__ExcludingAll__Collection(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__Flatten(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__Including__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__IncludingAll__Collection(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__InsertAt__IntegerValue_Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionInsertAtOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__Prepend__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__PrependAll__EList(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependAllOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__Reject1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.RejectIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__Reverse(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.OrderedCollectionReverseOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__Select1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.SelectIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__SelectByKind__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__SelectByType__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__SortedBy1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.SortedByIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__SubSequence__IntegerValue_IntegerValue(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.SequenceSubSequenceOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet___sub___EList(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.SetMinusOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet___ne___Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet___eq___Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet__Closure1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.ClosureIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet__Collect1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.CollectIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet__CollectNested1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.CollectNestedIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet__Excluding__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet__ExcludingAll__Collection(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionExcludingAllOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet__Flatten(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionFlattenOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet__Including__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet__IncludingAll__Collection(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet__Reject1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.RejectIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet__Select1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.SelectIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet__SelectByKind__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet__SelectByType__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionSelectByTypeOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet__SortedBy1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.SortedByIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getUniqueCollection___sub___EList(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.SetMinusOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getUniqueCollection__Intersection__Collection(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionIntersectionOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getUniqueCollection__SortedBy1__Object(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.iterator.SortedByIteration" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getUniqueCollection__SymmetricDifference__EList(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.SetSymmetricDifferenceOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getUniqueCollection__Union__EList(),
		   source,
		   new String[] {
			   "class", "org.eclipse.ocl.pivot.library.collection.CollectionUnionOperation" //$NON-NLS-1$ //$NON-NLS-2$
		   });
	}


	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/OCL/Collection</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createCollectionAnnotations() {
		String source = "http://www.eclipse.org/OCL/Collection"; //$NON-NLS-1$
		addAnnotation
		  (getBag__Closure1__Object(),
		   source,
		   new String[] {
			   "nullFree", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getBag__SelectByKind__Object(),
		   source,
		   new String[] {
			   "nullFree", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getBag__SelectByType__Object(),
		   source,
		   new String[] {
			   "nullFree", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getCollection__Product__Collection(),
		   source,
		   new String[] {
			   "nullFree", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclAny__OclAsSet(),
		   source,
		   new String[] {
			   "nullFree", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclElement__OclContents(),
		   source,
		   new String[] {
			   "nullFree", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclElement__OclExtensions(),
		   source,
		   new String[] {
			   "nullFree", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclElement__OclExtensions__Object(),
		   source,
		   new String[] {
			   "nullFree", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclElement_OclContents(),
		   source,
		   new String[] {
			   "nullFree", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclInvalid__OclAsSet(),
		   source,
		   new String[] {
			   "nullFree", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOclVoid__OclAsSet(),
		   source,
		   new String[] {
			   "nullFree", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__Closure1__Object(),
		   source,
		   new String[] {
			   "nullFree", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__SelectByKind__Object(),
		   source,
		   new String[] {
			   "nullFree", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getOrderedSet__SelectByType__Object(),
		   source,
		   new String[] {
			   "nullFree", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__Closure1__Object(),
		   source,
		   new String[] {
			   "nullFree", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__SelectByKind__Object(),
		   source,
		   new String[] {
			   "nullFree", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSequence__SelectByType__Object(),
		   source,
		   new String[] {
			   "nullFree", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet__Closure1__Object(),
		   source,
		   new String[] {
			   "nullFree", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet__SelectByKind__Object(),
		   source,
		   new String[] {
			   "nullFree", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });
		addAnnotation
		  (getSet__SelectByType__Object(),
		   source,
		   new String[] {
			   "nullFree", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/OCL/CoEClass</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createCoEClassAnnotations() {
		String source = "http://www.eclipse.org/OCL/CoEClass"; //$NON-NLS-1$
		addAnnotation
		  (booleanEDataType,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (integerEDataType,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (realEDataType,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (stringEDataType,
		   source,
		   new String[] {
		   });
		addAnnotation
		  (unlimitedNaturalEDataType,
		   source,
		   new String[] {
		   });
	}


	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/OCL/EntryEClass</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEntryEClassAnnotations() {
		String source = "http://www.eclipse.org/OCL/EntryEClass"; //$NON-NLS-1$
		addAnnotation
		  (entry_r_rEClass,
		   source,
		   new String[] {
		   });
	}

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public interface Literals {
		/**
		 * The meta object literal for the '{@link java.lang.Object <em>Ocl Comparable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Object
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getOclComparable()
		 * @generated
		 */
		public static final EClass OCL_COMPARABLE = eINSTANCE.getOclComparable();

		/**
		 * The meta object literal for the '<em><b>lt </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_COMPARABLE___LT____OBJECT = eINSTANCE.getOclComparable___lt___Object();

		/**
		 * The meta object literal for the '<em><b>le </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_COMPARABLE___LE____OBJECT = eINSTANCE.getOclComparable___le___Object();

		/**
		 * The meta object literal for the '<em><b>gt </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_COMPARABLE___GT____OBJECT = eINSTANCE.getOclComparable___gt___Object();

		/**
		 * The meta object literal for the '<em><b>ge </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_COMPARABLE___GE____OBJECT = eINSTANCE.getOclComparable___ge___Object();

		/**
		 * The meta object literal for the '<em><b>Compare To</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_COMPARABLE___COMPARE_TO__OBJECT = eINSTANCE.getOclComparable__CompareTo__Object();

		/**
		 * The meta object literal for the '{@link java.lang.Object <em>Ocl Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Object
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getOclElement()
		 * @generated
		 */
		public static final EClass OCL_ELEMENT = eINSTANCE.getOclElement();

		/**
		 * The meta object literal for the '<em><b>Ocl Container</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * @since 1.1
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference OCL_ELEMENT__OCL_CONTAINER = eINSTANCE.getOclElement_OclContainer();

		/**
		 * The meta object literal for the '<em><b>Ocl Contents</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * @since 1.1
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference OCL_ELEMENT__OCL_CONTENTS = eINSTANCE.getOclElement_OclContents();

		/**
		 * The meta object literal for the '<em><b>Ocl As Model Type</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_ELEMENT___OCL_AS_MODEL_TYPE__OBJECT = eINSTANCE.getOclElement__OclAsModelType__Object();

		/**
		 * The meta object literal for the '<em><b>Ocl Base</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_ELEMENT___OCL_BASE = eINSTANCE.getOclElement__OclBase();

		/**
		 * The meta object literal for the '<em><b>Ocl Base</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_ELEMENT___OCL_BASE__OBJECT = eINSTANCE.getOclElement__OclBase__Object();

		/**
		 * The meta object literal for the '<em><b>Ocl Container</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_ELEMENT___OCL_CONTAINER = eINSTANCE.getOclElement__OclContainer();

		/**
		 * The meta object literal for the '<em><b>Ocl Contents</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_ELEMENT___OCL_CONTENTS = eINSTANCE.getOclElement__OclContents();

		/**
		 * The meta object literal for the '<em><b>Ocl Extension</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_ELEMENT___OCL_EXTENSION__OBJECT = eINSTANCE.getOclElement__OclExtension__Object();

		/**
		 * The meta object literal for the '<em><b>Ocl Extensions</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_ELEMENT___OCL_EXTENSIONS = eINSTANCE.getOclElement__OclExtensions();

		/**
		 * The meta object literal for the '<em><b>Ocl Extensions</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_ELEMENT___OCL_EXTENSIONS__OBJECT = eINSTANCE.getOclElement__OclExtensions__Object();

		/**
		 * The meta object literal for the '<em><b>Ocl Is Model Kind Of</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_ELEMENT___OCL_IS_MODEL_KIND_OF__OBJECT = eINSTANCE.getOclElement__OclIsModelKindOf__Object();

		/**
		 * The meta object literal for the '<em><b>Ocl Model Type</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_ELEMENT___OCL_MODEL_TYPE = eINSTANCE.getOclElement__OclModelType();

		/**
		 * The meta object literal for the '<em><b>Ocl Model Types</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_ELEMENT___OCL_MODEL_TYPES = eINSTANCE.getOclElement__OclModelTypes();

		/**
		 * The meta object literal for the '{@link java.lang.Object <em>Ocl Enumeration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Object
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getOclEnumeration()
		 * @generated
		 */
		public static final EClass OCL_ENUMERATION = eINSTANCE.getOclEnumeration();

		/**
		 * The meta object literal for the '{@link java.lang.Object <em>Ocl Any</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Object
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getOclAny()
		 * @generated
		 */
		public static final EClass OCL_ANY = eINSTANCE.getOclAny();

		/**
		 * The meta object literal for the '<em><b>eq </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_ANY___EQ____OBJECT = eINSTANCE.getOclAny___eq___Object();

		/**
		 * The meta object literal for the '<em><b>ne </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_ANY___NE____OBJECT = eINSTANCE.getOclAny___ne___Object();

		/**
		 * The meta object literal for the '<em><b>Ocl As Set</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_ANY___OCL_AS_SET = eINSTANCE.getOclAny__OclAsSet();

		/**
		 * The meta object literal for the '<em><b>Ocl As Type</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_ANY___OCL_AS_TYPE__OBJECT = eINSTANCE.getOclAny__OclAsType__Object();

		/**
		 * The meta object literal for the '<em><b>Ocl Is In State</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_ANY___OCL_IS_IN_STATE__OBJECT = eINSTANCE.getOclAny__OclIsInState__Object();

		/**
		 * The meta object literal for the '<em><b>Ocl Is Invalid</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_ANY___OCL_IS_INVALID = eINSTANCE.getOclAny__OclIsInvalid();

		/**
		 * The meta object literal for the '<em><b>Ocl Is Kind Of</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_ANY___OCL_IS_KIND_OF__OBJECT = eINSTANCE.getOclAny__OclIsKindOf__Object();

		/**
		 * The meta object literal for the '<em><b>Ocl Is New</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_ANY___OCL_IS_NEW = eINSTANCE.getOclAny__OclIsNew();

		/**
		 * The meta object literal for the '<em><b>Ocl Is Type Of</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_ANY___OCL_IS_TYPE_OF__OBJECT = eINSTANCE.getOclAny__OclIsTypeOf__Object();

		/**
		 * The meta object literal for the '<em><b>Ocl Is Undefined</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_ANY___OCL_IS_UNDEFINED = eINSTANCE.getOclAny__OclIsUndefined();

		/**
		 * The meta object literal for the '<em><b>Ocl Log</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_ANY___OCL_LOG = eINSTANCE.getOclAny__OclLog();

		/**
		 * The meta object literal for the '<em><b>Ocl Log</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_ANY___OCL_LOG__STRING = eINSTANCE.getOclAny__OclLog__String();

		/**
		 * The meta object literal for the '<em><b>Ocl Type</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_ANY___OCL_TYPE = eINSTANCE.getOclAny__OclType();

		/**
		 * The meta object literal for the '<em><b>Ocl Types</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_ANY___OCL_TYPES = eINSTANCE.getOclAny__OclTypes();

		/**
		 * The meta object literal for the '<em><b>To String</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_ANY___TO_STRING = eINSTANCE.getOclAny__ToString();

		/**
		 * The meta object literal for the '{@link java.lang.Object <em>Ocl Invalid</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Object
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getOclInvalid()
		 * @generated
		 */
		public static final EClass OCL_INVALID = eINSTANCE.getOclInvalid();

		/**
		 * The meta object literal for the '<em><b>Ocl Bad Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * @since 1.4
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference OCL_INVALID__OCL_BAD_PROPERTY = eINSTANCE.getOclInvalid_OclBadProperty();

		/**
		 * The meta object literal for the '<em><b>ne </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_INVALID___NE____OBJECT = eINSTANCE.getOclInvalid___ne___Object();

		/**
		 * The meta object literal for the '<em><b>eq </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_INVALID___EQ____OBJECT = eINSTANCE.getOclInvalid___eq___Object();

		/**
		 * The meta object literal for the '<em><b>And</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_INVALID___AND__BOOLEAN = eINSTANCE.getOclInvalid__And__Boolean();

		/**
		 * The meta object literal for the '<em><b>Implies</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_INVALID___IMPLIES__BOOLEAN = eINSTANCE.getOclInvalid__Implies__Boolean();

		/**
		 * The meta object literal for the '<em><b>Ocl As Set</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_INVALID___OCL_AS_SET = eINSTANCE.getOclInvalid__OclAsSet();

		/**
		 * The meta object literal for the '<em><b>Ocl As Type</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_INVALID___OCL_AS_TYPE__OBJECT = eINSTANCE.getOclInvalid__OclAsType__Object();

		/**
		 * The meta object literal for the '<em><b>Ocl Bad Operation</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_INVALID___OCL_BAD_OPERATION = eINSTANCE.getOclInvalid__OclBadOperation();

		/**
		 * The meta object literal for the '<em><b>Ocl Is Invalid</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_INVALID___OCL_IS_INVALID = eINSTANCE.getOclInvalid__OclIsInvalid();

		/**
		 * The meta object literal for the '<em><b>Ocl Is Kind Of</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_INVALID___OCL_IS_KIND_OF__OBJECT = eINSTANCE.getOclInvalid__OclIsKindOf__Object();

		/**
		 * The meta object literal for the '<em><b>Ocl Is Type Of</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_INVALID___OCL_IS_TYPE_OF__OBJECT = eINSTANCE.getOclInvalid__OclIsTypeOf__Object();

		/**
		 * The meta object literal for the '<em><b>Ocl Is Undefined</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_INVALID___OCL_IS_UNDEFINED = eINSTANCE.getOclInvalid__OclIsUndefined();

		/**
		 * The meta object literal for the '<em><b>Ocl Type</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_INVALID___OCL_TYPE = eINSTANCE.getOclInvalid__OclType();

		/**
		 * The meta object literal for the '<em><b>Or</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_INVALID___OR__BOOLEAN = eINSTANCE.getOclInvalid__Or__Boolean();

		/**
		 * The meta object literal for the '<em><b>To String</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_INVALID___TO_STRING = eINSTANCE.getOclInvalid__ToString();

		/**
		 * The meta object literal for the '{@link java.lang.Object <em>Ocl Lambda</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Object
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getOclLambda()
		 * @generated
		 */
		public static final EClass OCL_LAMBDA = eINSTANCE.getOclLambda();

		/**
		 * The meta object literal for the '{@link java.lang.Object <em>Ocl Message</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Object
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getOclMessage()
		 * @generated
		 */
		public static final EClass OCL_MESSAGE = eINSTANCE.getOclMessage();

		/**
		 * The meta object literal for the '<em><b>Has Returned</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_MESSAGE___HAS_RETURNED = eINSTANCE.getOclMessage__HasReturned();

		/**
		 * The meta object literal for the '<em><b>Is Operation Call</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_MESSAGE___IS_OPERATION_CALL = eINSTANCE.getOclMessage__IsOperationCall();

		/**
		 * The meta object literal for the '<em><b>Is Signal Sent</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_MESSAGE___IS_SIGNAL_SENT = eINSTANCE.getOclMessage__IsSignalSent();

		/**
		 * The meta object literal for the '<em><b>Result</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_MESSAGE___RESULT = eINSTANCE.getOclMessage__Result();

		/**
		 * The meta object literal for the '{@link java.lang.Object <em>Ocl Self</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Object
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getOclSelf()
		 * @generated
		 */
		public static final EClass OCL_SELF = eINSTANCE.getOclSelf();

		/**
		 * The meta object literal for the '{@link java.lang.Object <em>Ocl State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Object
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getOclState()
		 * @generated
		 */
		public static final EClass OCL_STATE = eINSTANCE.getOclState();

		/**
		 * The meta object literal for the '{@link java.lang.Object <em>Ocl Stereotype</em>}' class.
		 * <!-- begin-user-doc -->
		 * @since 1.1
		 * <!-- end-user-doc -->
		 * @see java.lang.Object
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getOclStereotype()
		 * @generated
		 */
		public static final EClass OCL_STEREOTYPE = eINSTANCE.getOclStereotype();

		/**
		 * The meta object literal for the '{@link java.lang.Object <em>Ocl Summable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Object
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getOclSummable()
		 * @generated
		 */
		public static final EClass OCL_SUMMABLE = eINSTANCE.getOclSummable();

		/**
		 * The meta object literal for the '<em><b>Sum</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_SUMMABLE___SUM__OBJECT = eINSTANCE.getOclSummable__Sum__Object();

		/**
		 * The meta object literal for the '<em><b>Zero</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_SUMMABLE___ZERO = eINSTANCE.getOclSummable__Zero();

		/**
		 * The meta object literal for the '{@link java.lang.Object <em>Ocl Tuple</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Object
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getOclTuple()
		 * @generated
		 */
		public static final EClass OCL_TUPLE = eINSTANCE.getOclTuple();

		/**
		 * The meta object literal for the '<em><b>ne </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_TUPLE___NE____OBJECT = eINSTANCE.getOclTuple___ne___Object();

		/**
		 * The meta object literal for the '<em><b>eq </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_TUPLE___EQ____OBJECT = eINSTANCE.getOclTuple___eq___Object();

		/**
		 * The meta object literal for the '{@link java.lang.Object <em>Ocl Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Object
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getOclType()
		 * @generated
		 */
		public static final EClass OCL_TYPE = eINSTANCE.getOclType();

		/**
		 * The meta object literal for the '<em><b>Conforms To</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_TYPE___CONFORMS_TO__OBJECT = eINSTANCE.getOclType__ConformsTo__Object();

		/**
		 * The meta object literal for the '{@link java.lang.Object <em>Ocl Void</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Object
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getOclVoid()
		 * @generated
		 */
		public static final EClass OCL_VOID = eINSTANCE.getOclVoid();

		/**
		 * The meta object literal for the '<em><b>add </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_VOID___ADD____STRING = eINSTANCE.getOclVoid___add___String();

		/**
		 * The meta object literal for the '<em><b>ne </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_VOID___NE____OBJECT = eINSTANCE.getOclVoid___ne___Object();

		/**
		 * The meta object literal for the '<em><b>eq </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_VOID___EQ____OBJECT = eINSTANCE.getOclVoid___eq___Object();

		/**
		 * The meta object literal for the '<em><b>And</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_VOID___AND__BOOLEAN = eINSTANCE.getOclVoid__And__Boolean();

		/**
		 * The meta object literal for the '<em><b>Concat</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_VOID___CONCAT__STRING = eINSTANCE.getOclVoid__Concat__String();

		/**
		 * The meta object literal for the '<em><b>Implies</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_VOID___IMPLIES__BOOLEAN = eINSTANCE.getOclVoid__Implies__Boolean();

		/**
		 * The meta object literal for the '<em><b>Not</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_VOID___NOT = eINSTANCE.getOclVoid__Not();

		/**
		 * The meta object literal for the '<em><b>Ocl As Set</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_VOID___OCL_AS_SET = eINSTANCE.getOclVoid__OclAsSet();

		/**
		 * The meta object literal for the '<em><b>Ocl As Type</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_VOID___OCL_AS_TYPE__OBJECT = eINSTANCE.getOclVoid__OclAsType__Object();

		/**
		 * The meta object literal for the '<em><b>Ocl Is Invalid</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_VOID___OCL_IS_INVALID = eINSTANCE.getOclVoid__OclIsInvalid();

		/**
		 * The meta object literal for the '<em><b>Ocl Is Kind Of</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_VOID___OCL_IS_KIND_OF__OBJECT = eINSTANCE.getOclVoid__OclIsKindOf__Object();

		/**
		 * The meta object literal for the '<em><b>Ocl Is Type Of</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_VOID___OCL_IS_TYPE_OF__OBJECT = eINSTANCE.getOclVoid__OclIsTypeOf__Object();

		/**
		 * The meta object literal for the '<em><b>Ocl Is Undefined</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_VOID___OCL_IS_UNDEFINED = eINSTANCE.getOclVoid__OclIsUndefined();

		/**
		 * The meta object literal for the '<em><b>Ocl Type</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_VOID___OCL_TYPE = eINSTANCE.getOclVoid__OclType();

		/**
		 * The meta object literal for the '<em><b>Ocl Types</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_VOID___OCL_TYPES = eINSTANCE.getOclVoid__OclTypes();

		/**
		 * The meta object literal for the '<em><b>Or</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_VOID___OR__BOOLEAN = eINSTANCE.getOclVoid__Or__Boolean();

		/**
		 * The meta object literal for the '<em><b>To String</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_VOID___TO_STRING = eINSTANCE.getOclVoid__ToString();

		/**
		 * The meta object literal for the '<em><b>Xor</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation OCL_VOID___XOR__BOOLEAN = eINSTANCE.getOclVoid__Xor__Boolean();

		/**
		 * The meta object literal for the '{@link java.util.Collection <em>Ordered Collection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.Collection
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getOrderedCollection()
		 * @generated
		 */
		public static final EClass ORDERED_COLLECTION = eINSTANCE.getOrderedCollection();

		/**
		 * The meta object literal for the '<em><b>At</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_COLLECTION___AT__INTEGERVALUE = eINSTANCE.getOrderedCollection__At__IntegerValue();

		/**
		 * The meta object literal for the '<em><b>First</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_COLLECTION___FIRST = eINSTANCE.getOrderedCollection__First();

		/**
		 * The meta object literal for the '<em><b>Gather1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_COLLECTION___GATHER1__OBJECT = eINSTANCE.getOrderedCollection__Gather1__Object();

		/**
		 * The meta object literal for the '<em><b>Index Of</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_COLLECTION___INDEX_OF__OBJECT = eINSTANCE.getOrderedCollection__IndexOf__Object();

		/**
		 * The meta object literal for the '<em><b>Last</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_COLLECTION___LAST = eINSTANCE.getOrderedCollection__Last();

		/**
		 * The meta object literal for the '<em>Boolean</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Boolean
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getBoolean()
		 * @generated
		 */
		public static final EDataType BOOLEAN = eINSTANCE.getBoolean();

		/**
		 * The meta object literal for the '{@link java.util.Map.Entry <em>Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.Map.Entry
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getEntry()
		 * @generated
		 */
		public static final EClass ENTRY = eINSTANCE.getEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ENTRY__KEY = eINSTANCE.getEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ENTRY__VALUE = eINSTANCE.getEntry_Value();

		/**
		 * The meta object literal for the '<em>Integer</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.values.IntegerValue
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getInteger()
		 * @generated
		 */
		public static final EDataType INTEGER = eINSTANCE.getInteger();

		/**
		 * The meta object literal for the '{@link java.util.Map <em>Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.Map
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getMap()
		 * @generated
		 */
		public static final EClass MAP = eINSTANCE.getMap();

		/**
		 * The meta object literal for the '<em><b>ne </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___NE____OBJECT = eINSTANCE.getMap___ne___Object();

		/**
		 * The meta object literal for the '<em><b>eq </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___EQ____OBJECT = eINSTANCE.getMap___eq___Object();

		/**
		 * The meta object literal for the '<em><b>Any1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___ANY1__OBJECT = eINSTANCE.getMap__Any1__Object();

		/**
		 * The meta object literal for the '<em><b>At</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___AT__OBJECT = eINSTANCE.getMap__At__Object();

		/**
		 * The meta object literal for the '<em><b>Collect1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___COLLECT1__OBJECT = eINSTANCE.getMap__Collect1__Object();

		/**
		 * The meta object literal for the '<em><b>Collect By1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___COLLECT_BY1__OBJECT = eINSTANCE.getMap__CollectBy1__Object();

		/**
		 * The meta object literal for the '<em><b>Collect Nested1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___COLLECT_NESTED1__OBJECT = eINSTANCE.getMap__CollectNested1__Object();

		/**
		 * The meta object literal for the '<em><b>Excludes</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___EXCLUDES__OBJECT = eINSTANCE.getMap__Excludes__Object();

		/**
		 * The meta object literal for the '<em><b>Excludes</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___EXCLUDES__OBJECT_OBJECT = eINSTANCE.getMap__Excludes__Object_Object();

		/**
		 * The meta object literal for the '<em><b>Excludes All</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___EXCLUDES_ALL__COLLECTION = eINSTANCE.getMap__ExcludesAll__Collection();

		/**
		 * The meta object literal for the '<em><b>Excludes Map</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___EXCLUDES_MAP__EMAP = eINSTANCE.getMap__ExcludesMap__EMap();

		/**
		 * The meta object literal for the '<em><b>Excludes Value</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___EXCLUDES_VALUE__OBJECT = eINSTANCE.getMap__ExcludesValue__Object();

		/**
		 * The meta object literal for the '<em><b>Excluding</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___EXCLUDING__OBJECT = eINSTANCE.getMap__Excluding__Object();

		/**
		 * The meta object literal for the '<em><b>Excluding</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___EXCLUDING__OBJECT_OBJECT = eINSTANCE.getMap__Excluding__Object_Object();

		/**
		 * The meta object literal for the '<em><b>Excluding All</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___EXCLUDING_ALL__COLLECTION = eINSTANCE.getMap__ExcludingAll__Collection();

		/**
		 * The meta object literal for the '<em><b>Excluding Map</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___EXCLUDING_MAP__EMAP = eINSTANCE.getMap__ExcludingMap__EMap();

		/**
		 * The meta object literal for the '<em><b>Exists1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___EXISTS1__OBJECT = eINSTANCE.getMap__Exists1__Object();

		/**
		 * The meta object literal for the '<em><b>Exists2</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___EXISTS2__OBJECT = eINSTANCE.getMap__Exists2__Object();

		/**
		 * The meta object literal for the '<em><b>Exists3</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___EXISTS3__OBJECT = eINSTANCE.getMap__Exists3__Object();

		/**
		 * The meta object literal for the '<em><b>For All1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___FOR_ALL1__OBJECT = eINSTANCE.getMap__ForAll1__Object();

		/**
		 * The meta object literal for the '<em><b>For All2</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___FOR_ALL2__OBJECT = eINSTANCE.getMap__ForAll2__Object();

		/**
		 * The meta object literal for the '<em><b>For All3</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___FOR_ALL3__OBJECT = eINSTANCE.getMap__ForAll3__Object();

		/**
		 * The meta object literal for the '<em><b>Gather1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___GATHER1__OBJECT = eINSTANCE.getMap__Gather1__Object();

		/**
		 * The meta object literal for the '<em><b>Includes</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___INCLUDES__OBJECT = eINSTANCE.getMap__Includes__Object();

		/**
		 * The meta object literal for the '<em><b>Includes</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___INCLUDES__OBJECT_OBJECT = eINSTANCE.getMap__Includes__Object_Object();

		/**
		 * The meta object literal for the '<em><b>Includes All</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___INCLUDES_ALL__COLLECTION = eINSTANCE.getMap__IncludesAll__Collection();

		/**
		 * The meta object literal for the '<em><b>Includes Map</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___INCLUDES_MAP__EMAP = eINSTANCE.getMap__IncludesMap__EMap();

		/**
		 * The meta object literal for the '<em><b>Includes Value</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___INCLUDES_VALUE__OBJECT = eINSTANCE.getMap__IncludesValue__Object();

		/**
		 * The meta object literal for the '<em><b>Including</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___INCLUDING__OBJECT_OBJECT = eINSTANCE.getMap__Including__Object_Object();

		/**
		 * The meta object literal for the '<em><b>Including Map</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___INCLUDING_MAP__EMAP = eINSTANCE.getMap__IncludingMap__EMap();

		/**
		 * The meta object literal for the '<em><b>Is Empty</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___IS_EMPTY = eINSTANCE.getMap__IsEmpty();

		/**
		 * The meta object literal for the '<em><b>Is Unique1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___IS_UNIQUE1__OBJECT = eINSTANCE.getMap__IsUnique1__Object();

		/**
		 * The meta object literal for the '<em><b>Iterate1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___ITERATE1__OBJECT = eINSTANCE.getMap__Iterate1__Object();

		/**
		 * The meta object literal for the '<em><b>Keys</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___KEYS = eINSTANCE.getMap__Keys();

		/**
		 * The meta object literal for the '<em><b>Not Empty</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___NOT_EMPTY = eINSTANCE.getMap__NotEmpty();

		/**
		 * The meta object literal for the '<em><b>One1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___ONE1__OBJECT = eINSTANCE.getMap__One1__Object();

		/**
		 * The meta object literal for the '<em><b>Reject1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___REJECT1__OBJECT = eINSTANCE.getMap__Reject1__Object();

		/**
		 * The meta object literal for the '<em><b>Search1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___SEARCH1__OBJECT_OBJECT_OBJECT = eINSTANCE.getMap__Search1__Object_Object_Object();

		/**
		 * The meta object literal for the '<em><b>Search2</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___SEARCH2__OBJECT_OBJECT_OBJECT = eINSTANCE.getMap__Search2__Object_Object_Object();

		/**
		 * The meta object literal for the '<em><b>Search3</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___SEARCH3__OBJECT_OBJECT_OBJECT = eINSTANCE.getMap__Search3__Object_Object_Object();

		/**
		 * The meta object literal for the '<em><b>Select1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___SELECT1__OBJECT = eINSTANCE.getMap__Select1__Object();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___SIZE = eINSTANCE.getMap__Size();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation MAP___VALUES = eINSTANCE.getMap__Values();

		/**
		 * The meta object literal for the '<em>Real</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.values.RealValue
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getReal()
		 * @generated
		 */
		public static final EDataType REAL = eINSTANCE.getReal();

		/**
		 * The meta object literal for the '<em>String</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getString()
		 * @generated
		 */
		public static final EDataType STRING = eINSTANCE.getString();

		/**
		 * The meta object literal for the '<em>Unlimited Natural</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.values.UnlimitedNaturalValue
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getUnlimitedNatural()
		 * @generated
		 */
		public static final EDataType UNLIMITED_NATURAL = eINSTANCE.getUnlimitedNatural();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.values.Bag <em>Bag</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.values.Bag
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getBag()
		 * @generated
		 */
		public static final EClass BAG = eINSTANCE.getBag();

		/**
		 * The meta object literal for the '<em><b>ne </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation BAG___NE____OBJECT = eINSTANCE.getBag___ne___Object();

		/**
		 * The meta object literal for the '<em><b>eq </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation BAG___EQ____OBJECT = eINSTANCE.getBag___eq___Object();

		/**
		 * The meta object literal for the '<em><b>Closure1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation BAG___CLOSURE1__OBJECT = eINSTANCE.getBag__Closure1__Object();

		/**
		 * The meta object literal for the '<em><b>Collect1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation BAG___COLLECT1__OBJECT = eINSTANCE.getBag__Collect1__Object();

		/**
		 * The meta object literal for the '<em><b>Collect Nested1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation BAG___COLLECT_NESTED1__OBJECT = eINSTANCE.getBag__CollectNested1__Object();

		/**
		 * The meta object literal for the '<em><b>Excluding</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation BAG___EXCLUDING__OBJECT = eINSTANCE.getBag__Excluding__Object();

		/**
		 * The meta object literal for the '<em><b>Excluding All</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation BAG___EXCLUDING_ALL__COLLECTION = eINSTANCE.getBag__ExcludingAll__Collection();

		/**
		 * The meta object literal for the '<em><b>Flatten</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation BAG___FLATTEN = eINSTANCE.getBag__Flatten();

		/**
		 * The meta object literal for the '<em><b>Including</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation BAG___INCLUDING__OBJECT = eINSTANCE.getBag__Including__Object();

		/**
		 * The meta object literal for the '<em><b>Including All</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation BAG___INCLUDING_ALL__COLLECTION = eINSTANCE.getBag__IncludingAll__Collection();

		/**
		 * The meta object literal for the '<em><b>Reject1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation BAG___REJECT1__OBJECT = eINSTANCE.getBag__Reject1__Object();

		/**
		 * The meta object literal for the '<em><b>Select1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation BAG___SELECT1__OBJECT = eINSTANCE.getBag__Select1__Object();

		/**
		 * The meta object literal for the '<em><b>Select By Kind</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation BAG___SELECT_BY_KIND__OBJECT = eINSTANCE.getBag__SelectByKind__Object();

		/**
		 * The meta object literal for the '<em><b>Select By Type</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation BAG___SELECT_BY_TYPE__OBJECT = eINSTANCE.getBag__SelectByType__Object();

		/**
		 * The meta object literal for the '<em><b>Sorted By1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation BAG___SORTED_BY1__OBJECT = eINSTANCE.getBag__SortedBy1__Object();

		/**
		 * The meta object literal for the '{@link java.util.Collection <em>Collection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.Collection
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getCollection()
		 * @generated
		 */
		public static final EClass COLLECTION = eINSTANCE.getCollection();

		/**
		 * The meta object literal for the '<em><b>ne </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___NE____OBJECT = eINSTANCE.getCollection___ne___Object();

		/**
		 * The meta object literal for the '<em><b>eq </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___EQ____OBJECT = eINSTANCE.getCollection___eq___Object();

		/**
		 * The meta object literal for the '<em><b>Any1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___ANY1__OBJECT = eINSTANCE.getCollection__Any1__Object();

		/**
		 * The meta object literal for the '<em><b>As Bag</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___AS_BAG = eINSTANCE.getCollection__AsBag();

		/**
		 * The meta object literal for the '<em><b>As Ordered Set</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___AS_ORDERED_SET = eINSTANCE.getCollection__AsOrderedSet();

		/**
		 * The meta object literal for the '<em><b>As Sequence</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___AS_SEQUENCE = eINSTANCE.getCollection__AsSequence();

		/**
		 * The meta object literal for the '<em><b>As Set</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___AS_SET = eINSTANCE.getCollection__AsSet();

		/**
		 * The meta object literal for the '<em><b>Collect1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___COLLECT1__OBJECT = eINSTANCE.getCollection__Collect1__Object();

		/**
		 * The meta object literal for the '<em><b>Collect By1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___COLLECT_BY1__OBJECT = eINSTANCE.getCollection__CollectBy1__Object();

		/**
		 * The meta object literal for the '<em><b>Collect Nested1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___COLLECT_NESTED1__OBJECT = eINSTANCE.getCollection__CollectNested1__Object();

		/**
		 * The meta object literal for the '<em><b>Count</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___COUNT__OBJECT = eINSTANCE.getCollection__Count__Object();

		/**
		 * The meta object literal for the '<em><b>Excludes</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___EXCLUDES__OBJECT = eINSTANCE.getCollection__Excludes__Object();

		/**
		 * The meta object literal for the '<em><b>Excludes All</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___EXCLUDES_ALL__COLLECTION = eINSTANCE.getCollection__ExcludesAll__Collection();

		/**
		 * The meta object literal for the '<em><b>Excluding</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___EXCLUDING__OBJECT = eINSTANCE.getCollection__Excluding__Object();

		/**
		 * The meta object literal for the '<em><b>Excluding All</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___EXCLUDING_ALL__COLLECTION = eINSTANCE.getCollection__ExcludingAll__Collection();

		/**
		 * The meta object literal for the '<em><b>Exists1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___EXISTS1__OBJECT = eINSTANCE.getCollection__Exists1__Object();

		/**
		 * The meta object literal for the '<em><b>Exists2</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___EXISTS2__OBJECT = eINSTANCE.getCollection__Exists2__Object();

		/**
		 * The meta object literal for the '<em><b>Exists3</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___EXISTS3__OBJECT = eINSTANCE.getCollection__Exists3__Object();

		/**
		 * The meta object literal for the '<em><b>Flatten</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___FLATTEN = eINSTANCE.getCollection__Flatten();

		/**
		 * The meta object literal for the '<em><b>For All1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___FOR_ALL1__OBJECT = eINSTANCE.getCollection__ForAll1__Object();

		/**
		 * The meta object literal for the '<em><b>For All2</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___FOR_ALL2__OBJECT = eINSTANCE.getCollection__ForAll2__Object();

		/**
		 * The meta object literal for the '<em><b>For All3</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___FOR_ALL3__OBJECT = eINSTANCE.getCollection__ForAll3__Object();

		/**
		 * The meta object literal for the '<em><b>Gather1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___GATHER1__OBJECT = eINSTANCE.getCollection__Gather1__Object();

		/**
		 * The meta object literal for the '<em><b>Includes</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___INCLUDES__OBJECT = eINSTANCE.getCollection__Includes__Object();

		/**
		 * The meta object literal for the '<em><b>Includes All</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___INCLUDES_ALL__COLLECTION = eINSTANCE.getCollection__IncludesAll__Collection();

		/**
		 * The meta object literal for the '<em><b>Including</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___INCLUDING__OBJECT = eINSTANCE.getCollection__Including__Object();

		/**
		 * The meta object literal for the '<em><b>Including All</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___INCLUDING_ALL__COLLECTION = eINSTANCE.getCollection__IncludingAll__Collection();

		/**
		 * The meta object literal for the '<em><b>Intersection</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___INTERSECTION__COLLECTION = eINSTANCE.getCollection__Intersection__Collection();

		/**
		 * The meta object literal for the '<em><b>Intersection</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___INTERSECTION__ELIST = eINSTANCE.getCollection__Intersection__EList();

		/**
		 * The meta object literal for the '<em><b>Is Empty</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___IS_EMPTY = eINSTANCE.getCollection__IsEmpty();

		/**
		 * The meta object literal for the '<em><b>Is Unique1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___IS_UNIQUE1__OBJECT = eINSTANCE.getCollection__IsUnique1__Object();

		/**
		 * The meta object literal for the '<em><b>Iterate1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___ITERATE1__OBJECT = eINSTANCE.getCollection__Iterate1__Object();

		/**
		 * The meta object literal for the '<em><b>Max</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___MAX = eINSTANCE.getCollection__Max();

		/**
		 * The meta object literal for the '<em><b>Min</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___MIN = eINSTANCE.getCollection__Min();

		/**
		 * The meta object literal for the '<em><b>Not Empty</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___NOT_EMPTY = eINSTANCE.getCollection__NotEmpty();

		/**
		 * The meta object literal for the '<em><b>One1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___ONE1__OBJECT = eINSTANCE.getCollection__One1__Object();

		/**
		 * The meta object literal for the '<em><b>Product</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___PRODUCT__COLLECTION = eINSTANCE.getCollection__Product__Collection();

		/**
		 * The meta object literal for the '<em><b>Reject1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___REJECT1__OBJECT = eINSTANCE.getCollection__Reject1__Object();

		/**
		 * The meta object literal for the '<em><b>Search1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___SEARCH1__OBJECT_OBJECT_OBJECT = eINSTANCE.getCollection__Search1__Object_Object_Object();

		/**
		 * The meta object literal for the '<em><b>Search2</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___SEARCH2__OBJECT_OBJECT_OBJECT = eINSTANCE.getCollection__Search2__Object_Object_Object();

		/**
		 * The meta object literal for the '<em><b>Search3</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___SEARCH3__OBJECT_OBJECT_OBJECT = eINSTANCE.getCollection__Search3__Object_Object_Object();

		/**
		 * The meta object literal for the '<em><b>Select1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___SELECT1__OBJECT = eINSTANCE.getCollection__Select1__Object();

		/**
		 * The meta object literal for the '<em><b>Select By Kind</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___SELECT_BY_KIND__OBJECT = eINSTANCE.getCollection__SelectByKind__Object();

		/**
		 * The meta object literal for the '<em><b>Select By Type</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___SELECT_BY_TYPE__OBJECT = eINSTANCE.getCollection__SelectByType__Object();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___SIZE = eINSTANCE.getCollection__Size();

		/**
		 * The meta object literal for the '<em><b>Sorted By1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___SORTED_BY1__OBJECT = eINSTANCE.getCollection__SortedBy1__Object();

		/**
		 * The meta object literal for the '<em><b>Sum</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___SUM = eINSTANCE.getCollection__Sum();

		/**
		 * The meta object literal for the '<em><b>Union</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation COLLECTION___UNION__COLLECTION = eINSTANCE.getCollection__Union__Collection();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.values.OrderedSet <em>Ordered Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.values.OrderedSet
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getOrderedSet()
		 * @generated
		 */
		public static final EClass ORDERED_SET = eINSTANCE.getOrderedSet();

		/**
		 * The meta object literal for the '<em><b>sub </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_SET___SUB____ELIST = eINSTANCE.getOrderedSet___sub___EList();

		/**
		 * The meta object literal for the '<em><b>ne </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_SET___NE____OBJECT = eINSTANCE.getOrderedSet___ne___Object();

		/**
		 * The meta object literal for the '<em><b>eq </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_SET___EQ____OBJECT = eINSTANCE.getOrderedSet___eq___Object();

		/**
		 * The meta object literal for the '<em><b>Append</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_SET___APPEND__OBJECT = eINSTANCE.getOrderedSet__Append__Object();

		/**
		 * The meta object literal for the '<em><b>Append All</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_SET___APPEND_ALL__ELIST = eINSTANCE.getOrderedSet__AppendAll__EList();

		/**
		 * The meta object literal for the '<em><b>Closure1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_SET___CLOSURE1__OBJECT = eINSTANCE.getOrderedSet__Closure1__Object();

		/**
		 * The meta object literal for the '<em><b>Collect1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_SET___COLLECT1__OBJECT = eINSTANCE.getOrderedSet__Collect1__Object();

		/**
		 * The meta object literal for the '<em><b>Collect Nested1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_SET___COLLECT_NESTED1__OBJECT = eINSTANCE.getOrderedSet__CollectNested1__Object();

		/**
		 * The meta object literal for the '<em><b>Excluding</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_SET___EXCLUDING__OBJECT = eINSTANCE.getOrderedSet__Excluding__Object();

		/**
		 * The meta object literal for the '<em><b>Excluding All</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_SET___EXCLUDING_ALL__COLLECTION = eINSTANCE.getOrderedSet__ExcludingAll__Collection();

		/**
		 * The meta object literal for the '<em><b>Flatten</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_SET___FLATTEN = eINSTANCE.getOrderedSet__Flatten();

		/**
		 * The meta object literal for the '<em><b>Including</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_SET___INCLUDING__OBJECT = eINSTANCE.getOrderedSet__Including__Object();

		/**
		 * The meta object literal for the '<em><b>Including All</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_SET___INCLUDING_ALL__COLLECTION = eINSTANCE.getOrderedSet__IncludingAll__Collection();

		/**
		 * The meta object literal for the '<em><b>Insert At</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_SET___INSERT_AT__INTEGERVALUE_OBJECT = eINSTANCE.getOrderedSet__InsertAt__IntegerValue_Object();

		/**
		 * The meta object literal for the '<em><b>Prepend</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_SET___PREPEND__OBJECT = eINSTANCE.getOrderedSet__Prepend__Object();

		/**
		 * The meta object literal for the '<em><b>Prepend All</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_SET___PREPEND_ALL__ELIST = eINSTANCE.getOrderedSet__PrependAll__EList();

		/**
		 * The meta object literal for the '<em><b>Reject1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_SET___REJECT1__OBJECT = eINSTANCE.getOrderedSet__Reject1__Object();

		/**
		 * The meta object literal for the '<em><b>Reverse</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_SET___REVERSE = eINSTANCE.getOrderedSet__Reverse();

		/**
		 * The meta object literal for the '<em><b>Select1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_SET___SELECT1__OBJECT = eINSTANCE.getOrderedSet__Select1__Object();

		/**
		 * The meta object literal for the '<em><b>Select By Kind</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_SET___SELECT_BY_KIND__OBJECT = eINSTANCE.getOrderedSet__SelectByKind__Object();

		/**
		 * The meta object literal for the '<em><b>Select By Type</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_SET___SELECT_BY_TYPE__OBJECT = eINSTANCE.getOrderedSet__SelectByType__Object();

		/**
		 * The meta object literal for the '<em><b>Sorted By1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_SET___SORTED_BY1__OBJECT = eINSTANCE.getOrderedSet__SortedBy1__Object();

		/**
		 * The meta object literal for the '<em><b>Sub Ordered Set</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation ORDERED_SET___SUB_ORDERED_SET__INTEGERVALUE_INTEGERVALUE = eINSTANCE.getOrderedSet__SubOrderedSet__IntegerValue_IntegerValue();

		/**
		 * The meta object literal for the '{@link java.util.List <em>Sequence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.List
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getSequence()
		 * @generated
		 */
		public static final EClass SEQUENCE = eINSTANCE.getSequence();

		/**
		 * The meta object literal for the '<em><b>ne </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SEQUENCE___NE____OBJECT = eINSTANCE.getSequence___ne___Object();

		/**
		 * The meta object literal for the '<em><b>eq </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SEQUENCE___EQ____OBJECT = eINSTANCE.getSequence___eq___Object();

		/**
		 * The meta object literal for the '<em><b>Append</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SEQUENCE___APPEND__OBJECT = eINSTANCE.getSequence__Append__Object();

		/**
		 * The meta object literal for the '<em><b>Append All</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SEQUENCE___APPEND_ALL__ELIST = eINSTANCE.getSequence__AppendAll__EList();

		/**
		 * The meta object literal for the '<em><b>Closure1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SEQUENCE___CLOSURE1__OBJECT = eINSTANCE.getSequence__Closure1__Object();

		/**
		 * The meta object literal for the '<em><b>Collect1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SEQUENCE___COLLECT1__OBJECT = eINSTANCE.getSequence__Collect1__Object();

		/**
		 * The meta object literal for the '<em><b>Collect Nested1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SEQUENCE___COLLECT_NESTED1__OBJECT = eINSTANCE.getSequence__CollectNested1__Object();

		/**
		 * The meta object literal for the '<em><b>Excluding</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SEQUENCE___EXCLUDING__OBJECT = eINSTANCE.getSequence__Excluding__Object();

		/**
		 * The meta object literal for the '<em><b>Excluding All</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SEQUENCE___EXCLUDING_ALL__COLLECTION = eINSTANCE.getSequence__ExcludingAll__Collection();

		/**
		 * The meta object literal for the '<em><b>Flatten</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SEQUENCE___FLATTEN = eINSTANCE.getSequence__Flatten();

		/**
		 * The meta object literal for the '<em><b>Including</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SEQUENCE___INCLUDING__OBJECT = eINSTANCE.getSequence__Including__Object();

		/**
		 * The meta object literal for the '<em><b>Including All</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SEQUENCE___INCLUDING_ALL__COLLECTION = eINSTANCE.getSequence__IncludingAll__Collection();

		/**
		 * The meta object literal for the '<em><b>Insert At</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SEQUENCE___INSERT_AT__INTEGERVALUE_OBJECT = eINSTANCE.getSequence__InsertAt__IntegerValue_Object();

		/**
		 * The meta object literal for the '<em><b>Prepend</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SEQUENCE___PREPEND__OBJECT = eINSTANCE.getSequence__Prepend__Object();

		/**
		 * The meta object literal for the '<em><b>Prepend All</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SEQUENCE___PREPEND_ALL__ELIST = eINSTANCE.getSequence__PrependAll__EList();

		/**
		 * The meta object literal for the '<em><b>Reject1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SEQUENCE___REJECT1__OBJECT = eINSTANCE.getSequence__Reject1__Object();

		/**
		 * The meta object literal for the '<em><b>Reverse</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SEQUENCE___REVERSE = eINSTANCE.getSequence__Reverse();

		/**
		 * The meta object literal for the '<em><b>Select1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SEQUENCE___SELECT1__OBJECT = eINSTANCE.getSequence__Select1__Object();

		/**
		 * The meta object literal for the '<em><b>Select By Kind</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SEQUENCE___SELECT_BY_KIND__OBJECT = eINSTANCE.getSequence__SelectByKind__Object();

		/**
		 * The meta object literal for the '<em><b>Select By Type</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SEQUENCE___SELECT_BY_TYPE__OBJECT = eINSTANCE.getSequence__SelectByType__Object();

		/**
		 * The meta object literal for the '<em><b>Sorted By1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SEQUENCE___SORTED_BY1__OBJECT = eINSTANCE.getSequence__SortedBy1__Object();

		/**
		 * The meta object literal for the '<em><b>Sub Sequence</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SEQUENCE___SUB_SEQUENCE__INTEGERVALUE_INTEGERVALUE = eINSTANCE.getSequence__SubSequence__IntegerValue_IntegerValue();

		/**
		 * The meta object literal for the '{@link java.util.Set <em>Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.Set
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getSet()
		 * @generated
		 */
		public static final EClass SET = eINSTANCE.getSet();

		/**
		 * The meta object literal for the '<em><b>sub </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SET___SUB____ELIST = eINSTANCE.getSet___sub___EList();

		/**
		 * The meta object literal for the '<em><b>ne </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SET___NE____OBJECT = eINSTANCE.getSet___ne___Object();

		/**
		 * The meta object literal for the '<em><b>eq </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SET___EQ____OBJECT = eINSTANCE.getSet___eq___Object();

		/**
		 * The meta object literal for the '<em><b>Closure1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SET___CLOSURE1__OBJECT = eINSTANCE.getSet__Closure1__Object();

		/**
		 * The meta object literal for the '<em><b>Collect1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SET___COLLECT1__OBJECT = eINSTANCE.getSet__Collect1__Object();

		/**
		 * The meta object literal for the '<em><b>Collect Nested1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SET___COLLECT_NESTED1__OBJECT = eINSTANCE.getSet__CollectNested1__Object();

		/**
		 * The meta object literal for the '<em><b>Excluding</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SET___EXCLUDING__OBJECT = eINSTANCE.getSet__Excluding__Object();

		/**
		 * The meta object literal for the '<em><b>Excluding All</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SET___EXCLUDING_ALL__COLLECTION = eINSTANCE.getSet__ExcludingAll__Collection();

		/**
		 * The meta object literal for the '<em><b>Flatten</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SET___FLATTEN = eINSTANCE.getSet__Flatten();

		/**
		 * The meta object literal for the '<em><b>Including</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SET___INCLUDING__OBJECT = eINSTANCE.getSet__Including__Object();

		/**
		 * The meta object literal for the '<em><b>Including All</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SET___INCLUDING_ALL__COLLECTION = eINSTANCE.getSet__IncludingAll__Collection();

		/**
		 * The meta object literal for the '<em><b>Reject1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SET___REJECT1__OBJECT = eINSTANCE.getSet__Reject1__Object();

		/**
		 * The meta object literal for the '<em><b>Select1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SET___SELECT1__OBJECT = eINSTANCE.getSet__Select1__Object();

		/**
		 * The meta object literal for the '<em><b>Select By Kind</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SET___SELECT_BY_KIND__OBJECT = eINSTANCE.getSet__SelectByKind__Object();

		/**
		 * The meta object literal for the '<em><b>Select By Type</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SET___SELECT_BY_TYPE__OBJECT = eINSTANCE.getSet__SelectByType__Object();

		/**
		 * The meta object literal for the '<em><b>Sorted By1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation SET___SORTED_BY1__OBJECT = eINSTANCE.getSet__SortedBy1__Object();

		/**
		 * The meta object literal for the '{@link java.util.Collection <em>Unique Collection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.Collection
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getUniqueCollection()
		 * @generated
		 */
		public static final EClass UNIQUE_COLLECTION = eINSTANCE.getUniqueCollection();

		/**
		 * The meta object literal for the '<em><b>sub </b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation UNIQUE_COLLECTION___SUB____ELIST = eINSTANCE.getUniqueCollection___sub___EList();

		/**
		 * The meta object literal for the '<em><b>Intersection</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation UNIQUE_COLLECTION___INTERSECTION__COLLECTION = eINSTANCE.getUniqueCollection__Intersection__Collection();

		/**
		 * The meta object literal for the '<em><b>Sorted By1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation UNIQUE_COLLECTION___SORTED_BY1__OBJECT = eINSTANCE.getUniqueCollection__SortedBy1__Object();

		/**
		 * The meta object literal for the '<em><b>Symmetric Difference</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation UNIQUE_COLLECTION___SYMMETRIC_DIFFERENCE__ELIST = eINSTANCE.getUniqueCollection__SymmetricDifference__EList();

		/**
		 * The meta object literal for the '<em><b>Union</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation UNIQUE_COLLECTION___UNION__ELIST = eINSTANCE.getUniqueCollection__Union__EList();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.oclstdlib.Entry_r_r <em>Entry rr</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.oclstdlib.Entry_r_r
		 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getEntry_r_r()
		 * @generated
		 */
		public static final EClass ENTRY_RR = eINSTANCE.getEntry_r_r();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ENTRY_RR__KEY = eINSTANCE.getEntry_r_r_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ENTRY_RR__VALUE = eINSTANCE.getEntry_r_r_Value();

	}

} //OCLstdlibPackage
