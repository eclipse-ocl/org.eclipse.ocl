/**
 * Copyright (c) 2020, 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.xtext.idioms.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.idioms.*;

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
			case 4: return createEPackageDeclaration();
			case 5: return createFinalLocator();
			case 6: return createGrammarDeclaration();
			case 7: return createHalfNewLineSegment();
			case 8: return createIdiom();
			case 10: return createIdiomsImport();
			case 11: return createIdiomsModel();
			case 12: return createKeywordLocator();
			case 14: return createLocatorDeclaration();
			case 15: return createNewLineSegment();
			case 16: return createNoNewLineSegment();
			case 17: return createNoSpaceSegment();
			case 18: return createPopSegment();
			case 19: return createPostCommentSegment();
			case 20: return createPreCommentSegment();
			case 21: return createPushSegment();
			case 22: return createReferredLocator();
			case 23: return createReferredSegment();
			case 24: return createReturnsLocator();
			case 25: return createRuleLocator();
			case 27: return createSegmentDeclaration();
			case 28: return createSoftNewLineSegment();
			case 29: return createSoftSpaceSegment();
			case 30: return createStringSegment();
			case 31: return createSubIdiom();
			case 32: return createValueSegment();
			case 33: return createWrapAnchorSegment();
			case 34: return createWrapBeginSomeSegment();
			case 35: return createWrapBeginAllSegment();
			case 36: return createWrapEndSegment();
			case 37: return createWrapHereSegment();
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
	public EPackageDeclaration createEPackageDeclaration()
	{
		EPackageDeclarationImpl ePackageDeclaration = new EPackageDeclarationImpl();
		return ePackageDeclaration;
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
	public GrammarDeclaration createGrammarDeclaration()
	{
		GrammarDeclarationImpl grammarDeclaration = new GrammarDeclarationImpl();
		return grammarDeclaration;
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
	public NoNewLineSegment createNoNewLineSegment()
	{
		NoNewLineSegmentImpl noNewLineSegment = new NoNewLineSegmentImpl();
		return noNewLineSegment;
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
	public RuleLocator createRuleLocator()
	{
		RuleLocatorImpl ruleLocator = new RuleLocatorImpl();
		return ruleLocator;
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
