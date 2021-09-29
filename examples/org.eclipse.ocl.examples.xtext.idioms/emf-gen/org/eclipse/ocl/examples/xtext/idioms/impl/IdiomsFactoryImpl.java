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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.xtext.idioms.AnyAssignmentLocator;
import org.eclipse.ocl.examples.xtext.idioms.AnyElementLocator;
import org.eclipse.ocl.examples.xtext.idioms.AssignmentLocator;
import org.eclipse.ocl.examples.xtext.idioms.CustomSegment;
import org.eclipse.ocl.examples.xtext.idioms.EPackageImport;
import org.eclipse.ocl.examples.xtext.idioms.FinalLocator;
import org.eclipse.ocl.examples.xtext.idioms.HalfNewLineSegment;
import org.eclipse.ocl.examples.xtext.idioms.Idiom;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsFactory;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsImport;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsModel;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage;
import org.eclipse.ocl.examples.xtext.idioms.KeywordLocator;
import org.eclipse.ocl.examples.xtext.idioms.LocatorDeclaration;
import org.eclipse.ocl.examples.xtext.idioms.NewLineSegment;
import org.eclipse.ocl.examples.xtext.idioms.NoSpaceSegment;
import org.eclipse.ocl.examples.xtext.idioms.PopSegment;
import org.eclipse.ocl.examples.xtext.idioms.PostCommentSegment;
import org.eclipse.ocl.examples.xtext.idioms.PreCommentSegment;
import org.eclipse.ocl.examples.xtext.idioms.PushSegment;
import org.eclipse.ocl.examples.xtext.idioms.ReferredLocator;
import org.eclipse.ocl.examples.xtext.idioms.ReferredSegment;
import org.eclipse.ocl.examples.xtext.idioms.ReturnsLocator;
import org.eclipse.ocl.examples.xtext.idioms.SegmentDeclaration;
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
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class IdiomsFactoryImpl
		extends EFactoryImpl
		implements IdiomsFactory {

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static @NonNull IdiomsFactory init() {
		try
		{
			IdiomsFactory theIdiomsFactory = (IdiomsFactory)EPackage.Registry.INSTANCE.getEFactory(IdiomsPackage.eNS_URI);
			if (theIdiomsFactory != null)
			{
				return theIdiomsFactory;
			}
		}
		catch (Exception exception)
		{
			EcorePlugin.INSTANCE.log(exception);
		}
		return new IdiomsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdiomsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull EObject create(EClass eClass) {
		switch (eClass.getClassifierID())
		{
			case 0: return createAnyAssignmentLocator();
			case 1: return createAnyElementLocator();
			case 2: return createAssignmentLocator();
			case 3: return createCustomSegment();
			case 4: return createEPackageImport();
			case 5: return createFinalLocator();
			case 6: return createHalfNewLineSegment();
			case 7: return createIdiom();
			case 9: return createIdiomsImport();
			case 10: return createIdiomsModel();
			case 11: return createKeywordLocator();
			case 13: return createLocatorDeclaration();
			case 14: return createNewLineSegment();
			case 15: return createNoSpaceSegment();
			case 16: return createPopSegment();
			case 17: return createPostCommentSegment();
			case 18: return createPreCommentSegment();
			case 19: return createReferredLocator();
			case 20: return createReferredSegment();
			case 21: return createReturnsLocator();
			case 22: return createPushSegment();
			case 24: return createSegmentDeclaration();
			case 25: return createSoftNewLineSegment();
			case 26: return createSoftSpaceSegment();
			case 27: return createStringSegment();
			case 28: return createSubIdiom();
			case 29: return createValueSegment();
			case 30: return createWrapAnchorSegment();
			case 31: return createWrapBeginSomeSegment();
			case 32: return createWrapBeginAllSegment();
			case 33: return createWrapEndSegment();
			case 34: return createWrapHereSegment();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull AnyAssignmentLocator createAnyAssignmentLocator() {
		AnyAssignmentLocatorImpl anyAssignmentLocator = new AnyAssignmentLocatorImpl();
		return anyAssignmentLocator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull AnyElementLocator createAnyElementLocator() {
		AnyElementLocatorImpl anyElementLocator = new AnyElementLocatorImpl();
		return anyElementLocator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull AssignmentLocator createAssignmentLocator() {
		AssignmentLocatorImpl assignmentLocator = new AssignmentLocatorImpl();
		return assignmentLocator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CustomSegment createCustomSegment() {
		CustomSegmentImpl customSegment = new CustomSegmentImpl();
		return customSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull EPackageImport createEPackageImport() {
		EPackageImportImpl ePackageImport = new EPackageImportImpl();
		return ePackageImport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull FinalLocator createFinalLocator() {
		FinalLocatorImpl finalLocator = new FinalLocatorImpl();
		return finalLocator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull HalfNewLineSegment createHalfNewLineSegment() {
		HalfNewLineSegmentImpl halfNewLineSegment = new HalfNewLineSegmentImpl();
		return halfNewLineSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull Idiom createIdiom() {
		IdiomImpl idiom = new IdiomImpl();
		return idiom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull IdiomsImport createIdiomsImport() {
		IdiomsImportImpl idiomsImport = new IdiomsImportImpl();
		return idiomsImport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull IdiomsModel createIdiomsModel() {
		IdiomsModelImpl idiomsModel = new IdiomsModelImpl();
		return idiomsModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull KeywordLocator createKeywordLocator() {
		KeywordLocatorImpl keywordLocator = new KeywordLocatorImpl();
		return keywordLocator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull LocatorDeclaration createLocatorDeclaration() {
		LocatorDeclarationImpl locatorDeclaration = new LocatorDeclarationImpl();
		return locatorDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull NewLineSegment createNewLineSegment() {
		NewLineSegmentImpl newLineSegment = new NewLineSegmentImpl();
		return newLineSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull NoSpaceSegment createNoSpaceSegment() {
		NoSpaceSegmentImpl noSpaceSegment = new NoSpaceSegmentImpl();
		return noSpaceSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull PopSegment createPopSegment() {
		PopSegmentImpl popSegment = new PopSegmentImpl();
		return popSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull PostCommentSegment createPostCommentSegment() {
		PostCommentSegmentImpl postCommentSegment = new PostCommentSegmentImpl();
		return postCommentSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull PreCommentSegment createPreCommentSegment() {
		PreCommentSegmentImpl preCommentSegment = new PreCommentSegmentImpl();
		return preCommentSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull ReferredLocator createReferredLocator() {
		ReferredLocatorImpl referredLocator = new ReferredLocatorImpl();
		return referredLocator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull ReferredSegment createReferredSegment() {
		ReferredSegmentImpl referredSegment = new ReferredSegmentImpl();
		return referredSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull ReturnsLocator createReturnsLocator() {
		ReturnsLocatorImpl returnsLocator = new ReturnsLocatorImpl();
		return returnsLocator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull PushSegment createPushSegment() {
		PushSegmentImpl pushSegment = new PushSegmentImpl();
		return pushSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull SegmentDeclaration createSegmentDeclaration() {
		SegmentDeclarationImpl segmentDeclaration = new SegmentDeclarationImpl();
		return segmentDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull SoftNewLineSegment createSoftNewLineSegment() {
		SoftNewLineSegmentImpl softNewLineSegment = new SoftNewLineSegmentImpl();
		return softNewLineSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull SoftSpaceSegment createSoftSpaceSegment() {
		SoftSpaceSegmentImpl softSpaceSegment = new SoftSpaceSegmentImpl();
		return softSpaceSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull StringSegment createStringSegment() {
		StringSegmentImpl stringSegment = new StringSegmentImpl();
		return stringSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull SubIdiom createSubIdiom() {
		SubIdiomImpl subIdiom = new SubIdiomImpl();
		return subIdiom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull ValueSegment createValueSegment() {
		ValueSegmentImpl valueSegment = new ValueSegmentImpl();
		return valueSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull WrapAnchorSegment createWrapAnchorSegment() {
		WrapAnchorSegmentImpl wrapAnchorSegment = new WrapAnchorSegmentImpl();
		return wrapAnchorSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull WrapBeginSomeSegment createWrapBeginSomeSegment() {
		WrapBeginSomeSegmentImpl wrapBeginSomeSegment = new WrapBeginSomeSegmentImpl();
		return wrapBeginSomeSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull WrapBeginAllSegment createWrapBeginAllSegment() {
		WrapBeginAllSegmentImpl wrapBeginAllSegment = new WrapBeginAllSegmentImpl();
		return wrapBeginAllSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull WrapEndSegment createWrapEndSegment() {
		WrapEndSegmentImpl wrapEndSegment = new WrapEndSegmentImpl();
		return wrapEndSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull WrapHereSegment createWrapHereSegment() {
		WrapHereSegmentImpl wrapHereSegment = new WrapHereSegmentImpl();
		return wrapHereSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("null")
	@Override
	public @NonNull IdiomsPackage getIdiomsPackage() {
		return (IdiomsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static IdiomsPackage getPackage() {
		return IdiomsPackage.eINSTANCE;
	}

} //IdiomsFactoryImpl
