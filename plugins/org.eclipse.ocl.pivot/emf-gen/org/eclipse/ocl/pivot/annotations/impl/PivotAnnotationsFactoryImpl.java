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
package org.eclipse.ocl.pivot.annotations.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.annotations.ASLibrary_EPackage;
import org.eclipse.ocl.pivot.annotations.ASMetamodel_EPackage;
import org.eclipse.ocl.pivot.annotations.Collection_EClass;
import org.eclipse.ocl.pivot.annotations.Collection_EPackage;
import org.eclipse.ocl.pivot.annotations.Collection_ETypedElement;
import org.eclipse.ocl.pivot.annotations.Ecore_OCL_EClassifier;
import org.eclipse.ocl.pivot.annotations.Ecore_OCL_EOperation;
import org.eclipse.ocl.pivot.annotations.Ecore_OCL_EStructuralFeature;
import org.eclipse.ocl.pivot.annotations.Import_EPackage;
import org.eclipse.ocl.pivot.annotations.MetaAnnotation_EAnnotation;
import org.eclipse.ocl.pivot.annotations.PivotAnnotationsFactory;
import org.eclipse.ocl.pivot.annotations.PivotAnnotationsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PivotAnnotationsFactoryImpl extends EFactoryImpl implements PivotAnnotationsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PivotAnnotationsFactory init() {
		try {
			PivotAnnotationsFactory thePivotAnnotationsFactory = (PivotAnnotationsFactory)EPackage.Registry.INSTANCE.getEFactory(PivotAnnotationsPackage.eNS_URI);
			if (thePivotAnnotationsFactory != null) {
				return thePivotAnnotationsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PivotAnnotationsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PivotAnnotationsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case PivotAnnotationsPackage.AS_LIBRARY_EPACKAGE: return createASLibrary_EPackage();
			case PivotAnnotationsPackage.AS_METAMODEL_EPACKAGE: return createASMetamodel_EPackage();
			case PivotAnnotationsPackage.COLLECTION_ECLASS: return createCollection_EClass();
			case PivotAnnotationsPackage.COLLECTION_EPACKAGE: return createCollection_EPackage();
			case PivotAnnotationsPackage.COLLECTION_ETYPED_ELEMENT: return createCollection_ETypedElement();
			case PivotAnnotationsPackage.ECORE_OCL_ECLASSIFIER: return createEcore_OCL_EClassifier();
			case PivotAnnotationsPackage.ECORE_OCL_EOPERATION: return createEcore_OCL_EOperation();
			case PivotAnnotationsPackage.ECORE_OCL_ESTRUCTURAL_FEATURE: return createEcore_OCL_EStructuralFeature();
			case PivotAnnotationsPackage.IMPORT_EPACKAGE: return createImport_EPackage();
			case PivotAnnotationsPackage.META_ANNOTATION_EANNOTATION: return createMetaAnnotation_EAnnotation();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull ASLibrary_EPackage createASLibrary_EPackage() {
		ASLibrary_EPackageImpl asLibrary_EPackage = new ASLibrary_EPackageImpl();
		return asLibrary_EPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull ASMetamodel_EPackage createASMetamodel_EPackage() {
		ASMetamodel_EPackageImpl asMetamodel_EPackage = new ASMetamodel_EPackageImpl();
		return asMetamodel_EPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull Collection_EClass createCollection_EClass() {
		Collection_EClassImpl collection_EClass = new Collection_EClassImpl();
		return collection_EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull Collection_EPackage createCollection_EPackage() {
		Collection_EPackageImpl collection_EPackage = new Collection_EPackageImpl();
		return collection_EPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull Collection_ETypedElement createCollection_ETypedElement() {
		Collection_ETypedElementImpl collection_ETypedElement = new Collection_ETypedElementImpl();
		return collection_ETypedElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull Ecore_OCL_EClassifier createEcore_OCL_EClassifier() {
		Ecore_OCL_EClassifierImpl ecore_OCL_EClassifier = new Ecore_OCL_EClassifierImpl();
		return ecore_OCL_EClassifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull Ecore_OCL_EOperation createEcore_OCL_EOperation() {
		Ecore_OCL_EOperationImpl ecore_OCL_EOperation = new Ecore_OCL_EOperationImpl();
		return ecore_OCL_EOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull Ecore_OCL_EStructuralFeature createEcore_OCL_EStructuralFeature() {
		Ecore_OCL_EStructuralFeatureImpl ecore_OCL_EStructuralFeature = new Ecore_OCL_EStructuralFeatureImpl();
		return ecore_OCL_EStructuralFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull Import_EPackage createImport_EPackage() {
		Import_EPackageImpl import_EPackage = new Import_EPackageImpl();
		return import_EPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull MetaAnnotation_EAnnotation createMetaAnnotation_EAnnotation() {
		MetaAnnotation_EAnnotationImpl metaAnnotation_EAnnotation = new MetaAnnotation_EAnnotationImpl();
		return metaAnnotation_EAnnotation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String createImportURIFromString(EDataType eDataType, String initialValue) {
		return initialValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String convertImportURIToString(EDataType eDataType, Object instanceValue) {
		return String.valueOf(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PivotAnnotationsPackage getPivotAnnotationsPackage() {
		return (PivotAnnotationsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PivotAnnotationsPackage getPackage() {
		return PivotAnnotationsPackage.eINSTANCE;
	}

} //AnnotationsFactoryImpl
