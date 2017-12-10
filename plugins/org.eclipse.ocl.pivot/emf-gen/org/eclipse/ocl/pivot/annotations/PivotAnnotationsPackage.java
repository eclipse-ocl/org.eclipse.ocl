/**
 * Copyright (c) 2010, 2017 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.annotations;

import org.eclipse.emf.ecore.EAttribute;
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
 * @see org.eclipse.ocl.pivot.annotations.PivotAnnotationsFactory
 * @model kind="package"
 * @generated
 */
public interface PivotAnnotationsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "annotations";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/ocl/2018/Annotations";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "annotations";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PivotAnnotationsPackage eINSTANCE = org.eclipse.ocl.pivot.annotations.impl.PivotAnnotationsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.pivot.annotations.impl.ASLibrary_EPackageImpl <em>AS Library EPackage</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.pivot.annotations.impl.ASLibrary_EPackageImpl
	 * @see org.eclipse.ocl.pivot.annotations.impl.PivotAnnotationsPackageImpl#getASLibrary_EPackage()
	 * @generated
	 */
	int AS_LIBRARY_EPACKAGE = 0;

	/**
	 * The number of structural features of the '<em>AS Library EPackage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_LIBRARY_EPACKAGE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>AS Library EPackage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_LIBRARY_EPACKAGE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.pivot.annotations.impl.ASMetamodel_EPackageImpl <em>AS Metamodel EPackage</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.pivot.annotations.impl.ASMetamodel_EPackageImpl
	 * @see org.eclipse.ocl.pivot.annotations.impl.PivotAnnotationsPackageImpl#getASMetamodel_EPackage()
	 * @generated
	 */
	int AS_METAMODEL_EPACKAGE = 1;

	/**
	 * The number of structural features of the '<em>AS Metamodel EPackage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_METAMODEL_EPACKAGE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>AS Metamodel EPackage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_METAMODEL_EPACKAGE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.pivot.annotations.impl.Collection_EClassImpl <em>Collection EClass</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.pivot.annotations.impl.Collection_EClassImpl
	 * @see org.eclipse.ocl.pivot.annotations.impl.PivotAnnotationsPackageImpl#getCollection_EClass()
	 * @generated
	 */
	int COLLECTION_ECLASS = 2;

	/**
	 * The feature id for the '<em><b>Null Free</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_ECLASS__NULL_FREE = 0;

	/**
	 * The number of structural features of the '<em>Collection EClass</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_ECLASS_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Collection EClass</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_ECLASS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.pivot.annotations.impl.Collection_EPackageImpl <em>Collection EPackage</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.pivot.annotations.impl.Collection_EPackageImpl
	 * @see org.eclipse.ocl.pivot.annotations.impl.PivotAnnotationsPackageImpl#getCollection_EPackage()
	 * @generated
	 */
	int COLLECTION_EPACKAGE = 3;

	/**
	 * The feature id for the '<em><b>Null Free</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_EPACKAGE__NULL_FREE = 0;

	/**
	 * The number of structural features of the '<em>Collection EPackage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_EPACKAGE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Collection EPackage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_EPACKAGE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.pivot.annotations.impl.Collection_ETypedElementImpl <em>Collection ETyped Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.pivot.annotations.impl.Collection_ETypedElementImpl
	 * @see org.eclipse.ocl.pivot.annotations.impl.PivotAnnotationsPackageImpl#getCollection_ETypedElement()
	 * @generated
	 */
	int COLLECTION_ETYPED_ELEMENT = 4;

	/**
	 * The feature id for the '<em><b>Null Free</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_ETYPED_ELEMENT__NULL_FREE = 0;

	/**
	 * The number of structural features of the '<em>Collection ETyped Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_ETYPED_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Collection ETyped Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_ETYPED_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.pivot.annotations.impl.Ecore_OCL_EClassifierImpl <em>Ecore OCL EClassifier</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.pivot.annotations.impl.Ecore_OCL_EClassifierImpl
	 * @see org.eclipse.ocl.pivot.annotations.impl.PivotAnnotationsPackageImpl#getEcore_OCL_EClassifier()
	 * @generated
	 */
	int ECORE_OCL_ECLASSIFIER = 5;

	/**
	 * The number of structural features of the '<em>Ecore OCL EClassifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECORE_OCL_ECLASSIFIER_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Ecore OCL EClassifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECORE_OCL_ECLASSIFIER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.pivot.annotations.impl.Ecore_OCL_EOperationImpl <em>Ecore OCL EOperation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.pivot.annotations.impl.Ecore_OCL_EOperationImpl
	 * @see org.eclipse.ocl.pivot.annotations.impl.PivotAnnotationsPackageImpl#getEcore_OCL_EOperation()
	 * @generated
	 */
	int ECORE_OCL_EOPERATION = 6;

	/**
	 * The feature id for the '<em><b>Body</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECORE_OCL_EOPERATION__BODY = 0;

	/**
	 * The number of structural features of the '<em>Ecore OCL EOperation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECORE_OCL_EOPERATION_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Ecore OCL EOperation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECORE_OCL_EOPERATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.pivot.annotations.impl.Ecore_OCL_EStructuralFeatureImpl <em>Ecore OCL EStructural Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.pivot.annotations.impl.Ecore_OCL_EStructuralFeatureImpl
	 * @see org.eclipse.ocl.pivot.annotations.impl.PivotAnnotationsPackageImpl#getEcore_OCL_EStructuralFeature()
	 * @generated
	 */
	int ECORE_OCL_ESTRUCTURAL_FEATURE = 7;

	/**
	 * The feature id for the '<em><b>Derivation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECORE_OCL_ESTRUCTURAL_FEATURE__DERIVATION = 0;

	/**
	 * The number of structural features of the '<em>Ecore OCL EStructural Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECORE_OCL_ESTRUCTURAL_FEATURE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Ecore OCL EStructural Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECORE_OCL_ESTRUCTURAL_FEATURE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.pivot.annotations.impl.Import_EPackageImpl <em>Import EPackage</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.pivot.annotations.impl.Import_EPackageImpl
	 * @see org.eclipse.ocl.pivot.annotations.impl.PivotAnnotationsPackageImpl#getImport_EPackage()
	 * @generated
	 */
	int IMPORT_EPACKAGE = 8;

	/**
	 * The number of structural features of the '<em>Import EPackage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_EPACKAGE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Import EPackage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_EPACKAGE_OPERATION_COUNT = 0;

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.annotations.ASLibrary_EPackage <em>AS Library EPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AS Library EPackage</em>'.
	 * @see org.eclipse.ocl.pivot.annotations.ASLibrary_EPackage
	 * @generated
	 */
	EClass getASLibrary_EPackage();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.annotations.ASMetamodel_EPackage <em>AS Metamodel EPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AS Metamodel EPackage</em>'.
	 * @see org.eclipse.ocl.pivot.annotations.ASMetamodel_EPackage
	 * @generated
	 */
	EClass getASMetamodel_EPackage();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.annotations.Collection_EClass <em>Collection EClass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection EClass</em>'.
	 * @see org.eclipse.ocl.pivot.annotations.Collection_EClass
	 * @generated
	 */
	EClass getCollection_EClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.pivot.annotations.Collection_EClass#isNullFree <em>Null Free</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Null Free</em>'.
	 * @see org.eclipse.ocl.pivot.annotations.Collection_EClass#isNullFree()
	 * @see #getCollection_EClass()
	 * @generated
	 */
	EAttribute getCollection_EClass_NullFree();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.annotations.Collection_EPackage <em>Collection EPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection EPackage</em>'.
	 * @see org.eclipse.ocl.pivot.annotations.Collection_EPackage
	 * @generated
	 */
	EClass getCollection_EPackage();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.pivot.annotations.Collection_EPackage#isNullFree <em>Null Free</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Null Free</em>'.
	 * @see org.eclipse.ocl.pivot.annotations.Collection_EPackage#isNullFree()
	 * @see #getCollection_EPackage()
	 * @generated
	 */
	EAttribute getCollection_EPackage_NullFree();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.annotations.Collection_ETypedElement <em>Collection ETyped Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection ETyped Element</em>'.
	 * @see org.eclipse.ocl.pivot.annotations.Collection_ETypedElement
	 * @generated
	 */
	EClass getCollection_ETypedElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.pivot.annotations.Collection_ETypedElement#isNullFree <em>Null Free</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Null Free</em>'.
	 * @see org.eclipse.ocl.pivot.annotations.Collection_ETypedElement#isNullFree()
	 * @see #getCollection_ETypedElement()
	 * @generated
	 */
	EAttribute getCollection_ETypedElement_NullFree();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.annotations.Ecore_OCL_EClassifier <em>Ecore OCL EClassifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ecore OCL EClassifier</em>'.
	 * @see org.eclipse.ocl.pivot.annotations.Ecore_OCL_EClassifier
	 * @generated
	 */
	EClass getEcore_OCL_EClassifier();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.annotations.Ecore_OCL_EOperation <em>Ecore OCL EOperation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ecore OCL EOperation</em>'.
	 * @see org.eclipse.ocl.pivot.annotations.Ecore_OCL_EOperation
	 * @generated
	 */
	EClass getEcore_OCL_EOperation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.pivot.annotations.Ecore_OCL_EOperation#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Body</em>'.
	 * @see org.eclipse.ocl.pivot.annotations.Ecore_OCL_EOperation#getBody()
	 * @see #getEcore_OCL_EOperation()
	 * @generated
	 */
	EAttribute getEcore_OCL_EOperation_Body();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.annotations.Ecore_OCL_EStructuralFeature <em>Ecore OCL EStructural Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ecore OCL EStructural Feature</em>'.
	 * @see org.eclipse.ocl.pivot.annotations.Ecore_OCL_EStructuralFeature
	 * @generated
	 */
	EClass getEcore_OCL_EStructuralFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.pivot.annotations.Ecore_OCL_EStructuralFeature#getDerivation <em>Derivation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Derivation</em>'.
	 * @see org.eclipse.ocl.pivot.annotations.Ecore_OCL_EStructuralFeature#getDerivation()
	 * @see #getEcore_OCL_EStructuralFeature()
	 * @generated
	 */
	EAttribute getEcore_OCL_EStructuralFeature_Derivation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.annotations.Import_EPackage <em>Import EPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Import EPackage</em>'.
	 * @see org.eclipse.ocl.pivot.annotations.Import_EPackage
	 * @generated
	 */
	EClass getImport_EPackage();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PivotAnnotationsFactory getPivotAnnotationsFactory();

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
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.annotations.impl.ASLibrary_EPackageImpl <em>AS Library EPackage</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.annotations.impl.ASLibrary_EPackageImpl
		 * @see org.eclipse.ocl.pivot.annotations.impl.PivotAnnotationsPackageImpl#getASLibrary_EPackage()
		 * @generated
		 */
		EClass AS_LIBRARY_EPACKAGE = eINSTANCE.getASLibrary_EPackage();
		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.annotations.impl.ASMetamodel_EPackageImpl <em>AS Metamodel EPackage</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.annotations.impl.ASMetamodel_EPackageImpl
		 * @see org.eclipse.ocl.pivot.annotations.impl.PivotAnnotationsPackageImpl#getASMetamodel_EPackage()
		 * @generated
		 */
		EClass AS_METAMODEL_EPACKAGE = eINSTANCE.getASMetamodel_EPackage();
		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.annotations.impl.Collection_EClassImpl <em>Collection EClass</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.annotations.impl.Collection_EClassImpl
		 * @see org.eclipse.ocl.pivot.annotations.impl.PivotAnnotationsPackageImpl#getCollection_EClass()
		 * @generated
		 */
		EClass COLLECTION_ECLASS = eINSTANCE.getCollection_EClass();
		/**
		 * The meta object literal for the '<em><b>Null Free</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLLECTION_ECLASS__NULL_FREE = eINSTANCE.getCollection_EClass_NullFree();
		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.annotations.impl.Collection_EPackageImpl <em>Collection EPackage</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.annotations.impl.Collection_EPackageImpl
		 * @see org.eclipse.ocl.pivot.annotations.impl.PivotAnnotationsPackageImpl#getCollection_EPackage()
		 * @generated
		 */
		EClass COLLECTION_EPACKAGE = eINSTANCE.getCollection_EPackage();
		/**
		 * The meta object literal for the '<em><b>Null Free</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLLECTION_EPACKAGE__NULL_FREE = eINSTANCE.getCollection_EPackage_NullFree();
		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.annotations.impl.Collection_ETypedElementImpl <em>Collection ETyped Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.annotations.impl.Collection_ETypedElementImpl
		 * @see org.eclipse.ocl.pivot.annotations.impl.PivotAnnotationsPackageImpl#getCollection_ETypedElement()
		 * @generated
		 */
		EClass COLLECTION_ETYPED_ELEMENT = eINSTANCE.getCollection_ETypedElement();
		/**
		 * The meta object literal for the '<em><b>Null Free</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLLECTION_ETYPED_ELEMENT__NULL_FREE = eINSTANCE.getCollection_ETypedElement_NullFree();
		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.annotations.impl.Ecore_OCL_EClassifierImpl <em>Ecore OCL EClassifier</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.annotations.impl.Ecore_OCL_EClassifierImpl
		 * @see org.eclipse.ocl.pivot.annotations.impl.PivotAnnotationsPackageImpl#getEcore_OCL_EClassifier()
		 * @generated
		 */
		EClass ECORE_OCL_ECLASSIFIER = eINSTANCE.getEcore_OCL_EClassifier();
		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.annotations.impl.Ecore_OCL_EOperationImpl <em>Ecore OCL EOperation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.annotations.impl.Ecore_OCL_EOperationImpl
		 * @see org.eclipse.ocl.pivot.annotations.impl.PivotAnnotationsPackageImpl#getEcore_OCL_EOperation()
		 * @generated
		 */
		EClass ECORE_OCL_EOPERATION = eINSTANCE.getEcore_OCL_EOperation();
		/**
		 * The meta object literal for the '<em><b>Body</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ECORE_OCL_EOPERATION__BODY = eINSTANCE.getEcore_OCL_EOperation_Body();
		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.annotations.impl.Ecore_OCL_EStructuralFeatureImpl <em>Ecore OCL EStructural Feature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.annotations.impl.Ecore_OCL_EStructuralFeatureImpl
		 * @see org.eclipse.ocl.pivot.annotations.impl.PivotAnnotationsPackageImpl#getEcore_OCL_EStructuralFeature()
		 * @generated
		 */
		EClass ECORE_OCL_ESTRUCTURAL_FEATURE = eINSTANCE.getEcore_OCL_EStructuralFeature();
		/**
		 * The meta object literal for the '<em><b>Derivation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ECORE_OCL_ESTRUCTURAL_FEATURE__DERIVATION = eINSTANCE.getEcore_OCL_EStructuralFeature_Derivation();
		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.annotations.impl.Import_EPackageImpl <em>Import EPackage</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.annotations.impl.Import_EPackageImpl
		 * @see org.eclipse.ocl.pivot.annotations.impl.PivotAnnotationsPackageImpl#getImport_EPackage()
		 * @generated
		 */
		EClass IMPORT_EPACKAGE = eINSTANCE.getImport_EPackage();

	}

} //AnnotationsPackage
