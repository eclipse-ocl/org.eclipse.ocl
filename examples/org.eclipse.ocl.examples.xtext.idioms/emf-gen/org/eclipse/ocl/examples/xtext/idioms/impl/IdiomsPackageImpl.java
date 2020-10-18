/**
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.examples.xtext.idioms.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.ocl.examples.xtext.idioms.AnyAssignmentLocator;
import org.eclipse.ocl.examples.xtext.idioms.AnyElementLocator;
import org.eclipse.ocl.examples.xtext.idioms.AssignmentLocator;
import org.eclipse.ocl.examples.xtext.idioms.CustomSegment;
import org.eclipse.ocl.examples.xtext.idioms.EPackageImport;
import org.eclipse.ocl.examples.xtext.idioms.FinalLocator;
import org.eclipse.ocl.examples.xtext.idioms.HalfNewLineSegment;
import org.eclipse.ocl.examples.xtext.idioms.Idiom;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsElement;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsFactory;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsImport;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsModel;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage;
import org.eclipse.ocl.examples.xtext.idioms.ReferredLocator;
import org.eclipse.ocl.examples.xtext.idioms.KeywordLocator;
import org.eclipse.ocl.examples.xtext.idioms.Locator;
import org.eclipse.ocl.examples.xtext.idioms.LocatorDeclaration;
import org.eclipse.ocl.examples.xtext.idioms.NewLineSegment;
import org.eclipse.ocl.examples.xtext.idioms.NoSpaceSegment;
import org.eclipse.ocl.examples.xtext.idioms.PopSegment;
import org.eclipse.ocl.examples.xtext.idioms.PostCommentSegment;
import org.eclipse.ocl.examples.xtext.idioms.PreCommentSegment;
import org.eclipse.ocl.examples.xtext.idioms.PushSegment;
import org.eclipse.ocl.examples.xtext.idioms.ReturnsLocator;
import org.eclipse.ocl.examples.xtext.idioms.Segment;
import org.eclipse.ocl.examples.xtext.idioms.SegmentDeclaration;
import org.eclipse.ocl.examples.xtext.idioms.ReferredSegment;
import org.eclipse.ocl.examples.xtext.idioms.SoftNewLineSegment;
import org.eclipse.ocl.examples.xtext.idioms.SoftSpaceSegment;
import org.eclipse.ocl.examples.xtext.idioms.StringSegment;
import org.eclipse.ocl.examples.xtext.idioms.SubIdiom;
import org.eclipse.ocl.examples.xtext.idioms.ValueSegment;
import org.eclipse.ocl.examples.xtext.idioms.WrapAnchorSegment;
import org.eclipse.ocl.examples.xtext.idioms.WrapBeginAllSegment;
import org.eclipse.ocl.examples.xtext.idioms.WrapBeginSomeSegment;
import org.eclipse.ocl.examples.xtext.idioms.WrapEndSegment;
import org.eclipse.ocl.examples.xtext.idioms.WrapHereSegment;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class IdiomsPackageImpl
		extends EPackageImpl
		implements IdiomsPackage {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass anyAssignmentLocatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass anyElementLocatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass assignmentLocatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customSegmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ePackageImportEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass finalLocatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass halfNewLineSegmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass idiomEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass idiomsElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass idiomsImportEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass idiomsModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass locatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass keywordLocatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass locatorDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass newLineSegmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass noSpaceSegmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass popSegmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass postCommentSegmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass preCommentSegmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referredLocatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referredSegmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass returnsLocatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pushSegmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass segmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass segmentDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass softNewLineSegmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass softSpaceSegmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringSegmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass subIdiomEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass valueSegmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wrapAnchorSegmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wrapBeginSomeSegmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wrapBeginAllSegmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wrapEndSegmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wrapHereSegmentEClass = null;

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
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private IdiomsPackageImpl() {
		super(eNS_URI, IdiomsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link IdiomsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static IdiomsPackage init() {
		if (isInited)
			return (IdiomsPackage) EPackage.Registry.INSTANCE
				.getEPackage(IdiomsPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredIdiomsPackage = EPackage.Registry.INSTANCE
			.get(eNS_URI);
		IdiomsPackageImpl theIdiomsPackage = registeredIdiomsPackage instanceof IdiomsPackageImpl
			? (IdiomsPackageImpl) registeredIdiomsPackage
			: new IdiomsPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theIdiomsPackage.createPackageContents();

		// Initialize created meta-data
		theIdiomsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theIdiomsPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(IdiomsPackage.eNS_URI, theIdiomsPackage);
		return theIdiomsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAnyAssignmentLocator() {
		return anyAssignmentLocatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAnyElementLocator() {
		return anyElementLocatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAssignmentLocator() {
		return assignmentLocatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAssignmentLocator_EPackage() {
		return (EReference) assignmentLocatorEClass.getEStructuralFeatures()
			.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAssignmentLocator_EClass() {
		return (EReference) assignmentLocatorEClass.getEStructuralFeatures()
			.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAssignmentLocator_EStructuralFeature() {
		return (EReference) assignmentLocatorEClass.getEStructuralFeatures()
			.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCustomSegment() {
		return customSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomSegment_SupportClassName() {
		return (EAttribute) customSegmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEPackageImport() {
		return ePackageImportEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEPackageImport_As() {
		return (EAttribute) ePackageImportEClass.getEStructuralFeatures()
			.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEPackageImport_EPackage() {
		return (EReference) ePackageImportEClass.getEStructuralFeatures()
			.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFinalLocator() {
		return finalLocatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getHalfNewLineSegment() {
		return halfNewLineSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIdiom() {
		return idiomEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIdiom_Name() {
		return (EAttribute) idiomEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIdiom_ForEPackage() {
		return (EReference) idiomEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIdiom_ForEClass() {
		return (EReference) idiomEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIdiom_InRuleRegex() {
		return (EAttribute) idiomEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIdiom_Mixin() {
		return (EAttribute) idiomEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIdiom_OwnedSubIdioms() {
		return (EReference) idiomEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIdiomsElement() {
		return idiomsElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIdiomsImport() {
		return idiomsImportEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIdiomsImport_As() {
		return (EAttribute) idiomsImportEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIdiomsImport_IdiomsModel() {
		return (EReference) idiomsImportEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIdiomsModel() {
		return idiomsModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIdiomsModel_Name() {
		return (EAttribute) idiomsModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIdiomsModel_Names() {
		return (EAttribute) idiomsModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIdiomsModel_OwnedImports() {
		return (EReference) idiomsModelEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIdiomsModel_OwnedLocatorDeclarations() {
		return (EReference) idiomsModelEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIdiomsModel_OwnedSegmentDeclarations() {
		return (EReference) idiomsModelEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIdiomsModel_OwnedIdioms() {
		return (EReference) idiomsModelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIdiomsModel_OwnedWiths() {
		return (EReference) idiomsModelEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLocator() {
		return locatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getKeywordLocator() {
		return keywordLocatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getKeywordLocator_String() {
		return (EAttribute) keywordLocatorEClass.getEStructuralFeatures()
			.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLocatorDeclaration() {
		return locatorDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLocatorDeclaration_Name() {
		return (EAttribute) locatorDeclarationEClass.getEStructuralFeatures()
			.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLocatorDeclaration_OwnedLocator() {
		return (EReference) locatorDeclarationEClass.getEStructuralFeatures()
			.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLocatorDeclaration_OwningIdiomsModel() {
		return (EReference) locatorDeclarationEClass.getEStructuralFeatures()
			.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNewLineSegment() {
		return newLineSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNoSpaceSegment() {
		return noSpaceSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPopSegment() {
		return popSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPostCommentSegment() {
		return postCommentSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPreCommentSegment() {
		return preCommentSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getReferredLocator() {
		return referredLocatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getReferredLocator_IdiomsModel() {
		return (EReference) referredLocatorEClass.getEStructuralFeatures()
			.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getReferredLocator_LocatorDeclaration() {
		return (EReference) referredLocatorEClass.getEStructuralFeatures()
			.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getReferredSegment() {
		return referredSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getReferredSegment_IdiomsModel() {
		return (EReference) referredSegmentEClass.getEStructuralFeatures()
			.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getReferredSegment_SegmentDeclaration() {
		return (EReference) referredSegmentEClass.getEStructuralFeatures()
			.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getReturnsLocator() {
		return returnsLocatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getReturnsLocator_EPackage() {
		return (EReference) returnsLocatorEClass.getEStructuralFeatures()
			.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getReturnsLocator_EClass() {
		return (EReference) returnsLocatorEClass.getEStructuralFeatures()
			.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPushSegment() {
		return pushSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSegment() {
		return segmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSegmentDeclaration() {
		return segmentDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSegmentDeclaration_Name() {
		return (EAttribute) segmentDeclarationEClass.getEStructuralFeatures()
			.get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSegmentDeclaration_OwnedSegment() {
		return (EReference) segmentDeclarationEClass.getEStructuralFeatures()
			.get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSegmentDeclaration_OwningIdiomsModel() {
		return (EReference) segmentDeclarationEClass.getEStructuralFeatures()
			.get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSoftNewLineSegment() {
		return softNewLineSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSoftSpaceSegment() {
		return softSpaceSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStringSegment() {
		return stringSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStringSegment_Printable() {
		return (EAttribute) stringSegmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStringSegment_String() {
		return (EAttribute) stringSegmentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSubIdiom() {
		return subIdiomEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSubIdiom_All() {
		return (EAttribute) subIdiomEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSubIdiom_OwnedLocator() {
		return (EReference) subIdiomEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSubIdiom_OwnedSegments() {
		return (EReference) subIdiomEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSubIdiom_OwningIdiom() {
		return (EReference) subIdiomEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getValueSegment() {
		return valueSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getWrapAnchorSegment() {
		return wrapAnchorSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getWrapBeginSomeSegment() {
		return wrapBeginSomeSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getWrapBeginAllSegment() {
		return wrapBeginAllSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getWrapEndSegment() {
		return wrapEndSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getWrapHereSegment() {
		return wrapHereSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IdiomsFactory getIdiomsFactory() {
		return (IdiomsFactory) getEFactoryInstance();
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
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		anyAssignmentLocatorEClass = createEClass(ANY_ASSIGNMENT_LOCATOR);

		anyElementLocatorEClass = createEClass(ANY_ELEMENT_LOCATOR);

		assignmentLocatorEClass = createEClass(ASSIGNMENT_LOCATOR);
		createEReference(assignmentLocatorEClass, ASSIGNMENT_LOCATOR__EPACKAGE);
		createEReference(assignmentLocatorEClass, ASSIGNMENT_LOCATOR__ECLASS);
		createEReference(assignmentLocatorEClass,
			ASSIGNMENT_LOCATOR__ESTRUCTURAL_FEATURE);

		customSegmentEClass = createEClass(CUSTOM_SEGMENT);
		createEAttribute(customSegmentEClass,
			CUSTOM_SEGMENT__SUPPORT_CLASS_NAME);

		ePackageImportEClass = createEClass(EPACKAGE_IMPORT);
		createEAttribute(ePackageImportEClass, EPACKAGE_IMPORT__AS);
		createEReference(ePackageImportEClass, EPACKAGE_IMPORT__EPACKAGE);

		finalLocatorEClass = createEClass(FINAL_LOCATOR);

		halfNewLineSegmentEClass = createEClass(HALF_NEW_LINE_SEGMENT);

		idiomEClass = createEClass(IDIOM);
		createEAttribute(idiomEClass, IDIOM__NAME);
		createEReference(idiomEClass, IDIOM__FOR_EPACKAGE);
		createEReference(idiomEClass, IDIOM__FOR_ECLASS);
		createEAttribute(idiomEClass, IDIOM__IN_RULE_REGEX);
		createEAttribute(idiomEClass, IDIOM__MIXIN);
		createEReference(idiomEClass, IDIOM__OWNED_SUB_IDIOMS);

		idiomsElementEClass = createEClass(IDIOMS_ELEMENT);

		idiomsImportEClass = createEClass(IDIOMS_IMPORT);
		createEAttribute(idiomsImportEClass, IDIOMS_IMPORT__AS);
		createEReference(idiomsImportEClass, IDIOMS_IMPORT__IDIOMS_MODEL);

		idiomsModelEClass = createEClass(IDIOMS_MODEL);
		createEAttribute(idiomsModelEClass, IDIOMS_MODEL__NAME);
		createEAttribute(idiomsModelEClass, IDIOMS_MODEL__NAMES);
		createEReference(idiomsModelEClass, IDIOMS_MODEL__OWNED_IDIOMS);
		createEReference(idiomsModelEClass, IDIOMS_MODEL__OWNED_IMPORTS);
		createEReference(idiomsModelEClass,
			IDIOMS_MODEL__OWNED_LOCATOR_DECLARATIONS);
		createEReference(idiomsModelEClass,
			IDIOMS_MODEL__OWNED_SEGMENT_DECLARATIONS);
		createEReference(idiomsModelEClass, IDIOMS_MODEL__OWNED_WITHS);

		keywordLocatorEClass = createEClass(KEYWORD_LOCATOR);
		createEAttribute(keywordLocatorEClass, KEYWORD_LOCATOR__STRING);

		locatorEClass = createEClass(LOCATOR);

		locatorDeclarationEClass = createEClass(LOCATOR_DECLARATION);
		createEAttribute(locatorDeclarationEClass, LOCATOR_DECLARATION__NAME);
		createEReference(locatorDeclarationEClass,
			LOCATOR_DECLARATION__OWNED_LOCATOR);
		createEReference(locatorDeclarationEClass,
			LOCATOR_DECLARATION__OWNING_IDIOMS_MODEL);

		newLineSegmentEClass = createEClass(NEW_LINE_SEGMENT);

		noSpaceSegmentEClass = createEClass(NO_SPACE_SEGMENT);

		popSegmentEClass = createEClass(POP_SEGMENT);

		postCommentSegmentEClass = createEClass(POST_COMMENT_SEGMENT);

		preCommentSegmentEClass = createEClass(PRE_COMMENT_SEGMENT);

		referredLocatorEClass = createEClass(REFERRED_LOCATOR);
		createEReference(referredLocatorEClass, REFERRED_LOCATOR__IDIOMS_MODEL);
		createEReference(referredLocatorEClass,
			REFERRED_LOCATOR__LOCATOR_DECLARATION);

		referredSegmentEClass = createEClass(REFERRED_SEGMENT);
		createEReference(referredSegmentEClass, REFERRED_SEGMENT__IDIOMS_MODEL);
		createEReference(referredSegmentEClass,
			REFERRED_SEGMENT__SEGMENT_DECLARATION);

		returnsLocatorEClass = createEClass(RETURNS_LOCATOR);
		createEReference(returnsLocatorEClass, RETURNS_LOCATOR__EPACKAGE);
		createEReference(returnsLocatorEClass, RETURNS_LOCATOR__ECLASS);

		pushSegmentEClass = createEClass(PUSH_SEGMENT);

		segmentEClass = createEClass(SEGMENT);

		segmentDeclarationEClass = createEClass(SEGMENT_DECLARATION);
		createEAttribute(segmentDeclarationEClass, SEGMENT_DECLARATION__NAME);
		createEReference(segmentDeclarationEClass,
			SEGMENT_DECLARATION__OWNED_SEGMENT);
		createEReference(segmentDeclarationEClass,
			SEGMENT_DECLARATION__OWNING_IDIOMS_MODEL);

		softNewLineSegmentEClass = createEClass(SOFT_NEW_LINE_SEGMENT);

		softSpaceSegmentEClass = createEClass(SOFT_SPACE_SEGMENT);

		stringSegmentEClass = createEClass(STRING_SEGMENT);
		createEAttribute(stringSegmentEClass, STRING_SEGMENT__PRINTABLE);
		createEAttribute(stringSegmentEClass, STRING_SEGMENT__STRING);

		subIdiomEClass = createEClass(SUB_IDIOM);
		createEAttribute(subIdiomEClass, SUB_IDIOM__ALL);
		createEReference(subIdiomEClass, SUB_IDIOM__OWNED_LOCATOR);
		createEReference(subIdiomEClass, SUB_IDIOM__OWNED_SEGMENTS);
		createEReference(subIdiomEClass, SUB_IDIOM__OWNING_IDIOM);

		valueSegmentEClass = createEClass(VALUE_SEGMENT);

		wrapAnchorSegmentEClass = createEClass(WRAP_ANCHOR_SEGMENT);

		wrapBeginSomeSegmentEClass = createEClass(WRAP_BEGIN_SOME_SEGMENT);

		wrapBeginAllSegmentEClass = createEClass(WRAP_BEGIN_ALL_SEGMENT);

		wrapEndSegmentEClass = createEClass(WRAP_END_SEGMENT);

		wrapHereSegmentEClass = createEClass(WRAP_HERE_SEGMENT);
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
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		anyAssignmentLocatorEClass.getESuperTypes().add(this.getLocator());
		anyElementLocatorEClass.getESuperTypes().add(this.getLocator());
		assignmentLocatorEClass.getESuperTypes().add(this.getLocator());
		customSegmentEClass.getESuperTypes().add(this.getSegment());
		ePackageImportEClass.getESuperTypes().add(this.getIdiomsElement());
		finalLocatorEClass.getESuperTypes().add(this.getLocator());
		halfNewLineSegmentEClass.getESuperTypes().add(this.getSegment());
		idiomEClass.getESuperTypes().add(this.getIdiomsElement());
		idiomsImportEClass.getESuperTypes().add(this.getIdiomsElement());
		idiomsModelEClass.getESuperTypes().add(this.getIdiomsElement());
		keywordLocatorEClass.getESuperTypes().add(this.getLocator());
		locatorEClass.getESuperTypes().add(this.getIdiomsElement());
		locatorDeclarationEClass.getESuperTypes().add(this.getIdiomsElement());
		newLineSegmentEClass.getESuperTypes().add(this.getSegment());
		noSpaceSegmentEClass.getESuperTypes().add(this.getSegment());
		popSegmentEClass.getESuperTypes().add(this.getSegment());
		postCommentSegmentEClass.getESuperTypes().add(this.getSegment());
		preCommentSegmentEClass.getESuperTypes().add(this.getSegment());
		referredLocatorEClass.getESuperTypes().add(this.getLocator());
		referredSegmentEClass.getESuperTypes().add(this.getSegment());
		returnsLocatorEClass.getESuperTypes().add(this.getLocator());
		pushSegmentEClass.getESuperTypes().add(this.getSegment());
		segmentEClass.getESuperTypes().add(this.getIdiomsElement());
		segmentDeclarationEClass.getESuperTypes().add(this.getIdiomsElement());
		softNewLineSegmentEClass.getESuperTypes().add(this.getSegment());
		softSpaceSegmentEClass.getESuperTypes().add(this.getSegment());
		stringSegmentEClass.getESuperTypes().add(this.getSegment());
		subIdiomEClass.getESuperTypes().add(this.getIdiomsElement());
		valueSegmentEClass.getESuperTypes().add(this.getSegment());
		wrapAnchorSegmentEClass.getESuperTypes().add(this.getSegment());
		wrapBeginSomeSegmentEClass.getESuperTypes().add(this.getSegment());
		wrapBeginAllSegmentEClass.getESuperTypes().add(this.getSegment());
		wrapEndSegmentEClass.getESuperTypes().add(this.getSegment());
		wrapHereSegmentEClass.getESuperTypes().add(this.getSegment());

		// Initialize classes and features; add operations and parameters
		initEClass(anyAssignmentLocatorEClass, AnyAssignmentLocator.class,
			"AnyAssignmentLocator", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
			IS_GENERATED_INSTANCE_CLASS);

		initEClass(anyElementLocatorEClass, AnyElementLocator.class,
			"AnyElementLocator", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
			IS_GENERATED_INSTANCE_CLASS);

		initEClass(assignmentLocatorEClass, AssignmentLocator.class,
			"AssignmentLocator", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAssignmentLocator_EPackage(),
			ecorePackage.getEPackage(), null, "ePackage", null, 1, 1, //$NON-NLS-1$
			AssignmentLocator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
			!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
			!IS_DERIVED, IS_ORDERED);
		initEReference(getAssignmentLocator_EClass(), ecorePackage.getEClass(),
			null, "eClass", null, 1, 1, AssignmentLocator.class, !IS_TRANSIENT, //$NON-NLS-1$
			!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAssignmentLocator_EStructuralFeature(),
			ecorePackage.getEStructuralFeature(), null, "eStructuralFeature", //$NON-NLS-1$
			null, 1, 1, AssignmentLocator.class, !IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
			IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(customSegmentEClass, CustomSegment.class, "CustomSegment", //$NON-NLS-1$
			!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCustomSegment_SupportClassName(),
			ecorePackage.getEString(), "supportClassName", null, 0, 1, //$NON-NLS-1$
			CustomSegment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
			!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ePackageImportEClass, EPackageImport.class, "EPackageImport", //$NON-NLS-1$
			!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEPackageImport_As(), ecorePackage.getEString(), "as", //$NON-NLS-1$
			null, 0, 1, EPackageImport.class, !IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
			IS_ORDERED);
		initEReference(getEPackageImport_EPackage(), ecorePackage.getEPackage(),
			null, "ePackage", null, 1, 1, EPackageImport.class, !IS_TRANSIENT, //$NON-NLS-1$
			!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(finalLocatorEClass, FinalLocator.class, "FinalLocator", //$NON-NLS-1$
			!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(halfNewLineSegmentEClass, HalfNewLineSegment.class,
			"HalfNewLineSegment", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
			IS_GENERATED_INSTANCE_CLASS);

		initEClass(idiomEClass, Idiom.class, "Idiom", !IS_ABSTRACT, //$NON-NLS-1$
			!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIdiom_Name(), ecorePackage.getEString(), "name", null, //$NON-NLS-1$
			1, 1, Idiom.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
			!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIdiom_ForEPackage(), ecorePackage.getEPackage(), null,
			"forEPackage", null, 0, 1, Idiom.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
			IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
			IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIdiom_ForEClass(), ecorePackage.getEClass(), null,
			"forEClass", null, 0, 1, Idiom.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
			IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
			IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIdiom_InRuleRegex(), ecorePackage.getEString(),
			"inRuleRegex", null, 0, 1, Idiom.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
			IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
			IS_ORDERED);
		initEAttribute(getIdiom_Mixin(), ecorePackage.getEBoolean(), "mixin", //$NON-NLS-1$
			"false", 0, 1, Idiom.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
			IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
			IS_ORDERED);
		initEReference(getIdiom_OwnedSubIdioms(), this.getSubIdiom(),
			this.getSubIdiom_OwningIdiom(), "ownedSubIdioms", null, 0, -1, //$NON-NLS-1$
			Idiom.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
			IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
			!IS_DERIVED, IS_ORDERED);

		initEClass(idiomsElementEClass, IdiomsElement.class, "IdiomsElement", //$NON-NLS-1$
			IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(idiomsImportEClass, IdiomsImport.class, "IdiomsImport", //$NON-NLS-1$
			!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIdiomsImport_As(), ecorePackage.getEString(), "as", //$NON-NLS-1$
			null, 0, 1, IdiomsImport.class, !IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
			IS_ORDERED);
		initEReference(getIdiomsImport_IdiomsModel(), this.getIdiomsModel(),
			null, "idiomsModel", null, 1, 1, IdiomsImport.class, !IS_TRANSIENT, //$NON-NLS-1$
			!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(idiomsModelEClass, IdiomsModel.class, "IdiomsModel", //$NON-NLS-1$
			!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIdiomsModel_Name(), ecorePackage.getEString(), "name", //$NON-NLS-1$
			null, 1, 1, IdiomsModel.class, !IS_TRANSIENT, !IS_VOLATILE,
			!IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED,
			IS_ORDERED);
		initEAttribute(getIdiomsModel_Names(), ecorePackage.getEString(),
			"names", null, 1, -1, IdiomsModel.class, !IS_TRANSIENT, //$NON-NLS-1$
			!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
			!IS_DERIVED, IS_ORDERED);
		initEReference(getIdiomsModel_OwnedIdioms(), this.getIdiom(), null,
			"ownedIdioms", null, 0, -1, IdiomsModel.class, !IS_TRANSIENT, //$NON-NLS-1$
			!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIdiomsModel_OwnedImports(), this.getEPackageImport(),
			null, "ownedImports", null, 0, -1, IdiomsModel.class, !IS_TRANSIENT, //$NON-NLS-1$
			!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIdiomsModel_OwnedLocatorDeclarations(),
			this.getLocatorDeclaration(),
			this.getLocatorDeclaration_OwningIdiomsModel(),
			"ownedLocatorDeclarations", null, 0, -1, IdiomsModel.class, //$NON-NLS-1$
			!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
			!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
			IS_ORDERED);
		initEReference(getIdiomsModel_OwnedSegmentDeclarations(),
			this.getSegmentDeclaration(),
			this.getSegmentDeclaration_OwningIdiomsModel(),
			"ownedSegmentDeclarations", null, 0, -1, IdiomsModel.class, //$NON-NLS-1$
			!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
			!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
			IS_ORDERED);
		initEReference(getIdiomsModel_OwnedWiths(), this.getIdiomsImport(),
			null, "ownedWiths", null, 0, -1, IdiomsModel.class, !IS_TRANSIENT, //$NON-NLS-1$
			!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(keywordLocatorEClass, KeywordLocator.class, "KeywordLocator", //$NON-NLS-1$
			!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getKeywordLocator_String(), ecorePackage.getEString(),
			"string", null, 1, 1, KeywordLocator.class, !IS_TRANSIENT, //$NON-NLS-1$
			!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
			!IS_DERIVED, IS_ORDERED);

		initEClass(locatorEClass, Locator.class, "Locator", IS_ABSTRACT, //$NON-NLS-1$
			!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(locatorDeclarationEClass, LocatorDeclaration.class,
			"LocatorDeclaration", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
			IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLocatorDeclaration_Name(), ecorePackage.getEString(),
			"name", null, 1, 1, LocatorDeclaration.class, !IS_TRANSIENT, //$NON-NLS-1$
			!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
			!IS_DERIVED, IS_ORDERED);
		initEReference(getLocatorDeclaration_OwnedLocator(), this.getLocator(),
			null, "ownedLocator", null, 1, 1, LocatorDeclaration.class, //$NON-NLS-1$
			!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
			!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
			IS_ORDERED);
		initEReference(getLocatorDeclaration_OwningIdiomsModel(),
			this.getIdiomsModel(),
			this.getIdiomsModel_OwnedLocatorDeclarations(), "owningIdiomsModel", //$NON-NLS-1$
			null, 1, 1, LocatorDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
			IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(newLineSegmentEClass, NewLineSegment.class, "NewLineSegment", //$NON-NLS-1$
			!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(noSpaceSegmentEClass, NoSpaceSegment.class, "NoSpaceSegment", //$NON-NLS-1$
			!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(popSegmentEClass, PopSegment.class, "PopSegment", //$NON-NLS-1$
			!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(postCommentSegmentEClass, PostCommentSegment.class,
			"PostCommentSegment", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
			IS_GENERATED_INSTANCE_CLASS);

		initEClass(preCommentSegmentEClass, PreCommentSegment.class,
			"PreCommentSegment", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
			IS_GENERATED_INSTANCE_CLASS);

		initEClass(referredLocatorEClass, ReferredLocator.class,
			"ReferredLocator", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReferredLocator_IdiomsModel(), this.getIdiomsModel(),
			null, "idiomsModel", null, 0, 1, ReferredLocator.class, //$NON-NLS-1$
			!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
			IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
			IS_ORDERED);
		initEReference(getReferredLocator_LocatorDeclaration(),
			this.getLocatorDeclaration(), null, "locatorDeclaration", null, 1, //$NON-NLS-1$
			1, ReferredLocator.class, !IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
			IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(referredSegmentEClass, ReferredSegment.class,
			"ReferredSegment", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReferredSegment_IdiomsModel(), this.getIdiomsModel(),
			null, "idiomsModel", null, 0, 1, ReferredSegment.class, //$NON-NLS-1$
			!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
			IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
			IS_ORDERED);
		initEReference(getReferredSegment_SegmentDeclaration(),
			this.getSegmentDeclaration(), null, "segmentDeclaration", null, 1, //$NON-NLS-1$
			1, ReferredSegment.class, !IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
			IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(returnsLocatorEClass, ReturnsLocator.class, "ReturnsLocator", //$NON-NLS-1$
			!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReturnsLocator_EPackage(), ecorePackage.getEPackage(),
			null, "ePackage", null, 1, 1, ReturnsLocator.class, !IS_TRANSIENT, //$NON-NLS-1$
			!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getReturnsLocator_EClass(), ecorePackage.getEClass(),
			null, "eClass", null, 1, 1, ReturnsLocator.class, !IS_TRANSIENT, //$NON-NLS-1$
			!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pushSegmentEClass, PushSegment.class, "PushSegment", //$NON-NLS-1$
			!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(segmentEClass, Segment.class, "Segment", IS_ABSTRACT, //$NON-NLS-1$
			!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(segmentDeclarationEClass, SegmentDeclaration.class,
			"SegmentDeclaration", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
			IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSegmentDeclaration_Name(), ecorePackage.getEString(),
			"name", null, 1, 1, SegmentDeclaration.class, !IS_TRANSIENT, //$NON-NLS-1$
			!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
			!IS_DERIVED, IS_ORDERED);
		initEReference(getSegmentDeclaration_OwnedSegment(), this.getSegment(),
			null, "ownedSegment", null, 1, 1, SegmentDeclaration.class, //$NON-NLS-1$
			!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
			!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
			IS_ORDERED);
		initEReference(getSegmentDeclaration_OwningIdiomsModel(),
			this.getIdiomsModel(),
			this.getIdiomsModel_OwnedSegmentDeclarations(), "owningIdiomsModel", //$NON-NLS-1$
			null, 1, 1, SegmentDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
			IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(softNewLineSegmentEClass, SoftNewLineSegment.class,
			"SoftNewLineSegment", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
			IS_GENERATED_INSTANCE_CLASS);

		initEClass(softSpaceSegmentEClass, SoftSpaceSegment.class,
			"SoftSpaceSegment", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
			IS_GENERATED_INSTANCE_CLASS);

		initEClass(stringSegmentEClass, StringSegment.class, "StringSegment", //$NON-NLS-1$
			!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringSegment_Printable(), ecorePackage.getEBoolean(),
			"printable", "true", 1, 1, StringSegment.class, !IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
			!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
			!IS_DERIVED, IS_ORDERED);
		initEAttribute(getStringSegment_String(), ecorePackage.getEString(),
			"string", null, 1, 1, StringSegment.class, !IS_TRANSIENT, //$NON-NLS-1$
			!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
			!IS_DERIVED, IS_ORDERED);

		initEClass(subIdiomEClass, SubIdiom.class, "SubIdiom", !IS_ABSTRACT, //$NON-NLS-1$
			!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSubIdiom_All(), ecorePackage.getEBoolean(), "all", //$NON-NLS-1$
			"false", 1, 1, SubIdiom.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
			IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
			IS_ORDERED);
		initEReference(getSubIdiom_OwnedLocator(), this.getLocator(), null,
			"ownedLocator", null, 0, 1, SubIdiom.class, !IS_TRANSIENT, //$NON-NLS-1$
			!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSubIdiom_OwnedSegments(), this.getSegment(), null,
			"ownedSegments", null, 0, -1, SubIdiom.class, !IS_TRANSIENT, //$NON-NLS-1$
			!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSubIdiom_OwningIdiom(), this.getIdiom(),
			this.getIdiom_OwnedSubIdioms(), "owningIdiom", null, 1, 1, //$NON-NLS-1$
			SubIdiom.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
			!IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
			!IS_DERIVED, IS_ORDERED);

		initEClass(valueSegmentEClass, ValueSegment.class, "ValueSegment", //$NON-NLS-1$
			!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(wrapAnchorSegmentEClass, WrapAnchorSegment.class,
			"WrapAnchorSegment", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
			IS_GENERATED_INSTANCE_CLASS);

		initEClass(wrapBeginSomeSegmentEClass, WrapBeginSomeSegment.class,
			"WrapBeginSomeSegment", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
			IS_GENERATED_INSTANCE_CLASS);

		initEClass(wrapBeginAllSegmentEClass, WrapBeginAllSegment.class,
			"WrapBeginAllSegment", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
			IS_GENERATED_INSTANCE_CLASS);

		initEClass(wrapEndSegmentEClass, WrapEndSegment.class, "WrapEndSegment", //$NON-NLS-1$
			!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(wrapHereSegmentEClass, WrapHereSegment.class,
			"WrapHereSegment", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
			IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/OCL/Import
		createImportAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/OCL/Import</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createImportAnnotations() {
		String source = "http://www.eclipse.org/OCL/Import"; //$NON-NLS-1$
		addAnnotation(this, source,
			new String[]{"ecore", "http://www.eclipse.org/emf/2002/Ecore" //$NON-NLS-1$ //$NON-NLS-2$
			});
	}

} //IdiomsPackageImpl
