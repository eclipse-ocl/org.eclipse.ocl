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
package org.eclipse.ocl.xtext.idioms.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.idioms.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.ocl.xtext.idioms.IdiomsPackage
 * @generated
 */
public class IdiomsAdapterFactory
		extends AdapterFactoryImpl {

	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static IdiomsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdiomsAdapterFactory() {
		if (modelPackage == null)
		{
			modelPackage = IdiomsPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage)
		{
			return true;
		}
		if (object instanceof EObject)
		{
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IdiomsSwitch<@Nullable Adapter> modelSwitch = new IdiomsSwitch<@Nullable Adapter>()
		{
			@Override
			public Adapter caseAnyAssignmentLocator(AnyAssignmentLocator object)
			{
				return createAnyAssignmentLocatorAdapter();
			}
			@Override
			public Adapter caseAnyElementLocator(AnyElementLocator object)
			{
				return createAnyElementLocatorAdapter();
			}
			@Override
			public Adapter caseAssignmentLocator(AssignmentLocator object)
			{
				return createAssignmentLocatorAdapter();
			}
			@Override
			public Adapter caseCustomSegment(CustomSegment object)
			{
				return createCustomSegmentAdapter();
			}
			@Override
			public Adapter caseEPackageDeclaration(EPackageDeclaration object)
			{
				return createEPackageDeclarationAdapter();
			}
			@Override
			public Adapter caseFinalLocator(FinalLocator object)
			{
				return createFinalLocatorAdapter();
			}
			@Override
			public Adapter caseGrammarDeclaration(GrammarDeclaration object)
			{
				return createGrammarDeclarationAdapter();
			}
			@Override
			public Adapter caseHalfNewLineSegment(HalfNewLineSegment object)
			{
				return createHalfNewLineSegmentAdapter();
			}
			@Override
			public Adapter caseIdiom(Idiom object)
			{
				return createIdiomAdapter();
			}
			@Override
			public Adapter caseIdiomsElement(IdiomsElement object)
			{
				return createIdiomsElementAdapter();
			}
			@Override
			public Adapter caseIdiomsImport(IdiomsImport object)
			{
				return createIdiomsImportAdapter();
			}
			@Override
			public Adapter caseIdiomsModel(IdiomsModel object)
			{
				return createIdiomsModelAdapter();
			}
			@Override
			public Adapter caseKeywordLocator(KeywordLocator object)
			{
				return createKeywordLocatorAdapter();
			}
			@Override
			public Adapter caseLocator(Locator object)
			{
				return createLocatorAdapter();
			}
			@Override
			public Adapter caseLocatorDeclaration(LocatorDeclaration object)
			{
				return createLocatorDeclarationAdapter();
			}
			@Override
			public Adapter caseNewLineSegment(NewLineSegment object)
			{
				return createNewLineSegmentAdapter();
			}
			@Override
			public Adapter caseNoNewLineSegment(NoNewLineSegment object)
			{
				return createNoNewLineSegmentAdapter();
			}
			@Override
			public Adapter caseNoSpaceSegment(NoSpaceSegment object)
			{
				return createNoSpaceSegmentAdapter();
			}
			@Override
			public Adapter casePopSegment(PopSegment object)
			{
				return createPopSegmentAdapter();
			}
			@Override
			public Adapter casePostCommentSegment(PostCommentSegment object)
			{
				return createPostCommentSegmentAdapter();
			}
			@Override
			public Adapter casePreCommentSegment(PreCommentSegment object)
			{
				return createPreCommentSegmentAdapter();
			}
			@Override
			public Adapter casePushSegment(PushSegment object)
			{
				return createPushSegmentAdapter();
			}
			@Override
			public Adapter caseReferredLocator(ReferredLocator object)
			{
				return createReferredLocatorAdapter();
			}
			@Override
			public Adapter caseReferredSegment(ReferredSegment object)
			{
				return createReferredSegmentAdapter();
			}
			@Override
			public Adapter caseReturnsLocator(ReturnsLocator object)
			{
				return createReturnsLocatorAdapter();
			}
			@Override
			public Adapter caseRuleLocator(RuleLocator object)
			{
				return createRuleLocatorAdapter();
			}
			@Override
			public Adapter caseSegment(Segment object)
			{
				return createSegmentAdapter();
			}
			@Override
			public Adapter caseSegmentDeclaration(SegmentDeclaration object)
			{
				return createSegmentDeclarationAdapter();
			}
			@Override
			public Adapter caseSoftNewLineSegment(SoftNewLineSegment object)
			{
				return createSoftNewLineSegmentAdapter();
			}
			@Override
			public Adapter caseSoftSpaceSegment(SoftSpaceSegment object)
			{
				return createSoftSpaceSegmentAdapter();
			}
			@Override
			public Adapter caseStringSegment(StringSegment object)
			{
				return createStringSegmentAdapter();
			}
			@Override
			public Adapter caseSubIdiom(SubIdiom object)
			{
				return createSubIdiomAdapter();
			}
			@Override
			public Adapter caseValueSegment(ValueSegment object)
			{
				return createValueSegmentAdapter();
			}
			@Override
			public Adapter caseWrapAnchorSegment(WrapAnchorSegment object)
			{
				return createWrapAnchorSegmentAdapter();
			}
			@Override
			public Adapter caseWrapBeginSomeSegment(WrapBeginSomeSegment object)
			{
				return createWrapBeginSomeSegmentAdapter();
			}
			@Override
			public Adapter caseWrapBeginAllSegment(WrapBeginAllSegment object)
			{
				return createWrapBeginAllSegmentAdapter();
			}
			@Override
			public Adapter caseWrapEndSegment(WrapEndSegment object)
			{
				return createWrapEndSegmentAdapter();
			}
			@Override
			public Adapter caseWrapHereSegment(WrapHereSegment object)
			{
				return createWrapHereSegmentAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object)
			{
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.AnyAssignmentLocator <em>Any Assignment Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.AnyAssignmentLocator
	 * @generated
	 */
	public Adapter createAnyAssignmentLocatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.AnyElementLocator <em>Any Element Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.AnyElementLocator
	 * @generated
	 */
	public Adapter createAnyElementLocatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.AssignmentLocator <em>Assignment Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.AssignmentLocator
	 * @generated
	 */
	public Adapter createAssignmentLocatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.CustomSegment <em>Custom Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.CustomSegment
	 * @generated
	 */
	public Adapter createCustomSegmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.EPackageDeclaration <em>EPackage Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.EPackageDeclaration
	 * @generated
	 */
	public Adapter createEPackageDeclarationAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.FinalLocator <em>Final Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.FinalLocator
	 * @generated
	 */
	public Adapter createFinalLocatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.GrammarDeclaration <em>Grammar Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.GrammarDeclaration
	 * @generated
	 */
	public Adapter createGrammarDeclarationAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.HalfNewLineSegment <em>Half New Line Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.HalfNewLineSegment
	 * @generated
	 */
	public Adapter createHalfNewLineSegmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.Idiom <em>Idiom</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.Idiom
	 * @generated
	 */
	public Adapter createIdiomAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.IdiomsElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.IdiomsElement
	 * @generated
	 */
	public Adapter createIdiomsElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.IdiomsImport <em>Import</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.IdiomsImport
	 * @generated
	 */
	public Adapter createIdiomsImportAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.IdiomsModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.IdiomsModel
	 * @generated
	 */
	public Adapter createIdiomsModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.Locator <em>Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.Locator
	 * @generated
	 */
	public Adapter createLocatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.KeywordLocator <em>Keyword Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.KeywordLocator
	 * @generated
	 */
	public Adapter createKeywordLocatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.LocatorDeclaration <em>Locator Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.LocatorDeclaration
	 * @generated
	 */
	public Adapter createLocatorDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.NewLineSegment <em>New Line Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.NewLineSegment
	 * @generated
	 */
	public Adapter createNewLineSegmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.NoNewLineSegment <em>No New Line Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.NoNewLineSegment
	 * @generated
	 */
	public Adapter createNoNewLineSegmentAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.NoSpaceSegment <em>No Space Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.NoSpaceSegment
	 * @generated
	 */
	public Adapter createNoSpaceSegmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.PopSegment <em>Pop Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.PopSegment
	 * @generated
	 */
	public Adapter createPopSegmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.PostCommentSegment <em>Post Comment Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.PostCommentSegment
	 * @generated
	 */
	public Adapter createPostCommentSegmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.PreCommentSegment <em>Pre Comment Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.PreCommentSegment
	 * @generated
	 */
	public Adapter createPreCommentSegmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.ReferredLocator <em>Referred Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.ReferredLocator
	 * @generated
	 */
	public Adapter createReferredLocatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.ReferredSegment <em>Referred Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.ReferredSegment
	 * @generated
	 */
	public Adapter createReferredSegmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.ReturnsLocator <em>Returns Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.ReturnsLocator
	 * @generated
	 */
	public Adapter createReturnsLocatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.RuleLocator <em>Rule Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.RuleLocator
	 * @generated
	 */
	public Adapter createRuleLocatorAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.PushSegment <em>Push Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.PushSegment
	 * @generated
	 */
	public Adapter createPushSegmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.Segment <em>Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.Segment
	 * @generated
	 */
	public Adapter createSegmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.SegmentDeclaration <em>Segment Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.SegmentDeclaration
	 * @generated
	 */
	public Adapter createSegmentDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.SoftNewLineSegment <em>Soft New Line Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.SoftNewLineSegment
	 * @generated
	 */
	public Adapter createSoftNewLineSegmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.SoftSpaceSegment <em>Soft Space Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.SoftSpaceSegment
	 * @generated
	 */
	public Adapter createSoftSpaceSegmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.StringSegment <em>String Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.StringSegment
	 * @generated
	 */
	public Adapter createStringSegmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.SubIdiom <em>Sub Idiom</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.SubIdiom
	 * @generated
	 */
	public Adapter createSubIdiomAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.ValueSegment <em>Value Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.ValueSegment
	 * @generated
	 */
	public Adapter createValueSegmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.WrapAnchorSegment <em>Wrap Anchor Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.WrapAnchorSegment
	 * @generated
	 */
	public Adapter createWrapAnchorSegmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.WrapBeginSomeSegment <em>Wrap Begin Some Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.WrapBeginSomeSegment
	 * @generated
	 */
	public Adapter createWrapBeginSomeSegmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.WrapBeginAllSegment <em>Wrap Begin All Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.WrapBeginAllSegment
	 * @generated
	 */
	public Adapter createWrapBeginAllSegmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.WrapEndSegment <em>Wrap End Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.WrapEndSegment
	 * @generated
	 */
	public Adapter createWrapEndSegmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.xtext.idioms.WrapHereSegment <em>Wrap Here Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.xtext.idioms.WrapHereSegment
	 * @generated
	 */
	public Adapter createWrapHereSegmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //IdiomsAdapterFactory
