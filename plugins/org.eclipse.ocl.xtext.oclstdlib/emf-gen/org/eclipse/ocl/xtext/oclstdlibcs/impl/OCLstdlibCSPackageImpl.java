/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.xtext.oclstdlibcs.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.oclstdlibcs.JavaClassCS;
import org.eclipse.ocl.xtext.oclstdlibcs.JavaImplementationCS;
import org.eclipse.ocl.xtext.oclstdlibcs.LibClassCS;
import org.eclipse.ocl.xtext.oclstdlibcs.LibCoercionCS;
import org.eclipse.ocl.xtext.oclstdlibcs.LibConstraintCS;
import org.eclipse.ocl.xtext.oclstdlibcs.LibIterationCS;
import org.eclipse.ocl.xtext.oclstdlibcs.LibOperationCS;
import org.eclipse.ocl.xtext.oclstdlibcs.LibPackageCS;
import org.eclipse.ocl.xtext.oclstdlibcs.LibPropertyCS;
import org.eclipse.ocl.xtext.oclstdlibcs.LibRootPackageCS;
import org.eclipse.ocl.xtext.oclstdlibcs.MetaclassNameCS;
import org.eclipse.ocl.xtext.oclstdlibcs.OCLstdlibCSFactory;
import org.eclipse.ocl.xtext.oclstdlibcs.OCLstdlibCSPackage;
import org.eclipse.ocl.xtext.oclstdlibcs.PrecedenceCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OCLstdlibCSPackageImpl
extends EPackageImpl
implements OCLstdlibCSPackage {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass javaClassCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass libClassCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass libCoercionCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass libConstraintCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass libIterationCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass libOperationCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass libPackageCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass libPropertyCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass libRootPackageCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass metaclassNameCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass javaImplementationCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass precedenceCSEClass = null;

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
	 * @see org.eclipse.ocl.xtext.oclstdlibcs.OCLstdlibCSPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private OCLstdlibCSPackageImpl() {
		super(eNS_URI, OCLstdlibCSFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link OCLstdlibCSPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static OCLstdlibCSPackage init() {
		if (isInited) return (OCLstdlibCSPackage)EPackage.Registry.INSTANCE.getEPackage(OCLstdlibCSPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredOCLstdlibCSPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		OCLstdlibCSPackageImpl theOCLstdlibCSPackage = registeredOCLstdlibCSPackage instanceof OCLstdlibCSPackageImpl ? (OCLstdlibCSPackageImpl)registeredOCLstdlibCSPackage : new OCLstdlibCSPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		BaseCSPackage.eINSTANCE.eClass();
		PivotPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theOCLstdlibCSPackage.createPackageContents();

		// Initialize created meta-data
		theOCLstdlibCSPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theOCLstdlibCSPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(OCLstdlibCSPackage.eNS_URI, theOCLstdlibCSPackage);
		return theOCLstdlibCSPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getJavaClassCS()
	{
		return javaClassCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLibClassCS() {
		return libClassCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLibClassCS_MetaclassName()
	{
		return (EReference)libClassCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLibCoercionCS()
	{
		return libCoercionCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLibConstraintCS() {
		return libConstraintCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLibIterationCS() {
		return libIterationCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLibIterationCS_OwnedIterators()
	{
		return (EReference)libIterationCSEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLibIterationCS_OwnedAccumulators()
	{
		return (EReference)libIterationCSEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLibIterationCS_IsInvalidating()
	{
		return (EAttribute)libIterationCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLibIterationCS_IsValidating()
	{
		return (EAttribute)libIterationCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLibOperationCS() {
		return libOperationCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLibOperationCS_Precedence() {
		return (EReference)libOperationCSEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLibOperationCS_IsInvalidating()
	{
		return (EAttribute)libOperationCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLibOperationCS_IsStatic()
	{
		return (EAttribute)libOperationCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLibOperationCS_IsValidating()
	{
		return (EAttribute)libOperationCSEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLibPackageCS()
	{
		return libPackageCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLibPackageCS_OwnedPrecedences()
	{
		return (EReference)libPackageCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLibPropertyCS() {
		return libPropertyCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLibPropertyCS_IsStatic()
	{
		return (EAttribute)libPropertyCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLibRootPackageCS()
	{
		return libRootPackageCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMetaclassNameCS()
	{
		return metaclassNameCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMetaclassNameCS_Name()
	{
		return (EAttribute)metaclassNameCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getJavaImplementationCS() {
		return javaImplementationCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJavaImplementationCS_Implementation() {
		return (EReference)javaImplementationCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPrecedenceCS() {
		return precedenceCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPrecedenceCS_IsRightAssociative()
	{
		return (EAttribute)precedenceCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OCLstdlibCSFactory getOCLstdlibCSFactory()
	{
		return (OCLstdlibCSFactory)getEFactoryInstance();
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
		javaClassCSEClass = createEClass(JAVA_CLASS_CS);

		javaImplementationCSEClass = createEClass(JAVA_IMPLEMENTATION_CS);
		createEReference(javaImplementationCSEClass, JAVA_IMPLEMENTATION_CS__IMPLEMENTATION);

		libClassCSEClass = createEClass(LIB_CLASS_CS);
		createEReference(libClassCSEClass, LIB_CLASS_CS__METACLASS_NAME);

		libCoercionCSEClass = createEClass(LIB_COERCION_CS);

		libConstraintCSEClass = createEClass(LIB_CONSTRAINT_CS);

		libIterationCSEClass = createEClass(LIB_ITERATION_CS);
		createEAttribute(libIterationCSEClass, LIB_ITERATION_CS__IS_INVALIDATING);
		createEAttribute(libIterationCSEClass, LIB_ITERATION_CS__IS_VALIDATING);
		createEReference(libIterationCSEClass, LIB_ITERATION_CS__OWNED_ACCUMULATORS);
		createEReference(libIterationCSEClass, LIB_ITERATION_CS__OWNED_ITERATORS);

		libOperationCSEClass = createEClass(LIB_OPERATION_CS);
		createEAttribute(libOperationCSEClass, LIB_OPERATION_CS__IS_INVALIDATING);
		createEAttribute(libOperationCSEClass, LIB_OPERATION_CS__IS_STATIC);
		createEAttribute(libOperationCSEClass, LIB_OPERATION_CS__IS_VALIDATING);
		createEReference(libOperationCSEClass, LIB_OPERATION_CS__PRECEDENCE);

		libPackageCSEClass = createEClass(LIB_PACKAGE_CS);
		createEReference(libPackageCSEClass, LIB_PACKAGE_CS__OWNED_PRECEDENCES);

		libPropertyCSEClass = createEClass(LIB_PROPERTY_CS);
		createEAttribute(libPropertyCSEClass, LIB_PROPERTY_CS__IS_STATIC);

		libRootPackageCSEClass = createEClass(LIB_ROOT_PACKAGE_CS);

		metaclassNameCSEClass = createEClass(METACLASS_NAME_CS);
		createEAttribute(metaclassNameCSEClass, METACLASS_NAME_CS__NAME);

		precedenceCSEClass = createEClass(PRECEDENCE_CS);
		createEAttribute(precedenceCSEClass, PRECEDENCE_CS__IS_RIGHT_ASSOCIATIVE);
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
		BaseCSPackage theBaseCSPackage = (BaseCSPackage)EPackage.Registry.INSTANCE.getEPackage(BaseCSPackage.eNS_URI);
		PivotPackage thePivotPackage = (PivotPackage)EPackage.Registry.INSTANCE.getEPackage(PivotPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		javaClassCSEClass.getESuperTypes().add(theBaseCSPackage.getNamedElementCS());
		javaImplementationCSEClass.getESuperTypes().add(theBaseCSPackage.getElementCS());
		libClassCSEClass.getESuperTypes().add(theBaseCSPackage.getStructuredClassCS());
		libCoercionCSEClass.getESuperTypes().add(theBaseCSPackage.getOperationCS());
		libCoercionCSEClass.getESuperTypes().add(this.getJavaImplementationCS());
		libConstraintCSEClass.getESuperTypes().add(theBaseCSPackage.getConstraintCS());
		libIterationCSEClass.getESuperTypes().add(theBaseCSPackage.getOperationCS());
		libIterationCSEClass.getESuperTypes().add(this.getJavaImplementationCS());
		libOperationCSEClass.getESuperTypes().add(theBaseCSPackage.getOperationCS());
		libOperationCSEClass.getESuperTypes().add(this.getJavaImplementationCS());
		libPackageCSEClass.getESuperTypes().add(theBaseCSPackage.getPackageCS());
		libPropertyCSEClass.getESuperTypes().add(theBaseCSPackage.getAttributeCS());
		libPropertyCSEClass.getESuperTypes().add(this.getJavaImplementationCS());
		libRootPackageCSEClass.getESuperTypes().add(theBaseCSPackage.getRootPackageCS());
		metaclassNameCSEClass.getESuperTypes().add(theBaseCSPackage.getElementCS());
		metaclassNameCSEClass.getESuperTypes().add(thePivotPackage.getNameable());
		precedenceCSEClass.getESuperTypes().add(theBaseCSPackage.getNamedElementCS());

		// Initialize classes and features; add operations and parameters
		initEClass(javaClassCSEClass, JavaClassCS.class, "JavaClassCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(javaImplementationCSEClass, JavaImplementationCS.class, "JavaImplementationCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getJavaImplementationCS_Implementation(), this.getJavaClassCS(), null, "implementation", null, 0, 1, JavaImplementationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(libClassCSEClass, LibClassCS.class, "LibClassCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLibClassCS_MetaclassName(), this.getMetaclassNameCS(), null, "metaclassName", null, 0, 1, LibClassCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(libCoercionCSEClass, LibCoercionCS.class, "LibCoercionCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(libConstraintCSEClass, LibConstraintCS.class, "LibConstraintCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(libIterationCSEClass, LibIterationCS.class, "LibIterationCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLibIterationCS_IsInvalidating(), thePivotPackage.getBoolean(), "isInvalidating", "false", 0, 1, LibIterationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLibIterationCS_IsValidating(), thePivotPackage.getBoolean(), "isValidating", "false", 0, 1, LibIterationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLibIterationCS_OwnedAccumulators(), theBaseCSPackage.getParameterCS(), null, "ownedAccumulators", null, 0, -1, LibIterationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLibIterationCS_OwnedIterators(), theBaseCSPackage.getParameterCS(), null, "ownedIterators", null, 0, -1, LibIterationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(libOperationCSEClass, LibOperationCS.class, "LibOperationCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLibOperationCS_IsInvalidating(), thePivotPackage.getBoolean(), "isInvalidating", "false", 0, 1, LibOperationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLibOperationCS_IsStatic(), thePivotPackage.getBoolean(), "isStatic", "false", 0, 1, LibOperationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLibOperationCS_IsValidating(), thePivotPackage.getBoolean(), "isValidating", "false", 0, 1, LibOperationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLibOperationCS_Precedence(), thePivotPackage.getPrecedence(), null, "precedence", null, 0, 1, LibOperationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(libPackageCSEClass, LibPackageCS.class, "LibPackageCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLibPackageCS_OwnedPrecedences(), this.getPrecedenceCS(), null, "ownedPrecedences", null, 0, -1, LibPackageCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(libPropertyCSEClass, LibPropertyCS.class, "LibPropertyCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLibPropertyCS_IsStatic(), thePivotPackage.getBoolean(), "isStatic", "false", 0, 1, LibPropertyCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(libRootPackageCSEClass, LibRootPackageCS.class, "LibRootPackageCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(metaclassNameCSEClass, MetaclassNameCS.class, "MetaclassNameCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMetaclassNameCS_Name(), ecorePackage.getEString(), "name", null, 0, 1, MetaclassNameCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(precedenceCSEClass, PrecedenceCS.class, "PrecedenceCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPrecedenceCS_IsRightAssociative(), ecorePackage.getEBoolean(), "isRightAssociative", "false", 0, 1, PrecedenceCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //OCLstdlibCSPackageImpl
