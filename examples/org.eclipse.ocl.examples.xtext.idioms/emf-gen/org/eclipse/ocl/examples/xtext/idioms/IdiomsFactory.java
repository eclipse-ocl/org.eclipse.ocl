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
package org.eclipse.ocl.examples.xtext.idioms;

import org.eclipse.emf.ecore.EFactory;
import org.eclipse.jdt.annotation.NonNull;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage
 * @generated
 */
public interface IdiomsFactory
		extends EFactory {

	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@NonNull
	IdiomsFactory eINSTANCE = org.eclipse.ocl.examples.xtext.idioms.impl.IdiomsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Any Assignment Locator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Any Assignment Locator</em>'.
	 * @generated
	 */
	@NonNull
	AnyAssignmentLocator createAnyAssignmentLocator();

	/**
	 * Returns a new object of class '<em>Any Element Locator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Any Element Locator</em>'.
	 * @generated
	 */
	@NonNull
	AnyElementLocator createAnyElementLocator();

	/**
	 * Returns a new object of class '<em>Assignment Locator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Assignment Locator</em>'.
	 * @generated
	 */
	@NonNull
	AssignmentLocator createAssignmentLocator();

	/**
	 * Returns a new object of class '<em>Custom Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Custom Segment</em>'.
	 * @generated
	 */
	@NonNull
	CustomSegment createCustomSegment();

	/**
	 * Returns a new object of class '<em>EPackage Import</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>EPackage Import</em>'.
	 * @generated
	 */
	@NonNull
	EPackageImport createEPackageImport();

	/**
	 * Returns a new object of class '<em>Final Locator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Final Locator</em>'.
	 * @generated
	 */
	@NonNull
	FinalLocator createFinalLocator();

	/**
	 * Returns a new object of class '<em>Half New Line Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Half New Line Segment</em>'.
	 * @generated
	 */
	@NonNull
	HalfNewLineSegment createHalfNewLineSegment();

	/**
	 * Returns a new object of class '<em>Idiom</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Idiom</em>'.
	 * @generated
	 */
	@NonNull
	Idiom createIdiom();

	/**
	 * Returns a new object of class '<em>Import</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Import</em>'.
	 * @generated
	 */
	@NonNull
	IdiomsImport createIdiomsImport();

	/**
	 * Returns a new object of class '<em>Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model</em>'.
	 * @generated
	 */
	@NonNull
	IdiomsModel createIdiomsModel();

	/**
	 * Returns a new object of class '<em>Keyword Locator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Keyword Locator</em>'.
	 * @generated
	 */
	@NonNull
	KeywordLocator createKeywordLocator();

	/**
	 * Returns a new object of class '<em>Locator Declaration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Locator Declaration</em>'.
	 * @generated
	 */
	@NonNull
	LocatorDeclaration createLocatorDeclaration();

	/**
	 * Returns a new object of class '<em>New Line Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>New Line Segment</em>'.
	 * @generated
	 */
	@NonNull
	NewLineSegment createNewLineSegment();

	/**
	 * Returns a new object of class '<em>No Space Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>No Space Segment</em>'.
	 * @generated
	 */
	@NonNull
	NoSpaceSegment createNoSpaceSegment();

	/**
	 * Returns a new object of class '<em>Pop Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Pop Segment</em>'.
	 * @generated
	 */
	@NonNull
	PopSegment createPopSegment();

	/**
	 * Returns a new object of class '<em>Post Comment Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Post Comment Segment</em>'.
	 * @generated
	 */
	@NonNull
	PostCommentSegment createPostCommentSegment();

	/**
	 * Returns a new object of class '<em>Pre Comment Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Pre Comment Segment</em>'.
	 * @generated
	 */
	@NonNull
	PreCommentSegment createPreCommentSegment();

	/**
	 * Returns a new object of class '<em>Referred Locator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Referred Locator</em>'.
	 * @generated
	 */
	@NonNull
	ReferredLocator createReferredLocator();

	/**
	 * Returns a new object of class '<em>Referred Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Referred Segment</em>'.
	 * @generated
	 */
	@NonNull
	ReferredSegment createReferredSegment();

	/**
	 * Returns a new object of class '<em>Returns Locator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Returns Locator</em>'.
	 * @generated
	 */
	@NonNull
	ReturnsLocator createReturnsLocator();

	/**
	 * Returns a new object of class '<em>Push Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Push Segment</em>'.
	 * @generated
	 */
	@NonNull
	PushSegment createPushSegment();

	/**
	 * Returns a new object of class '<em>Segment Declaration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Segment Declaration</em>'.
	 * @generated
	 */
	@NonNull
	SegmentDeclaration createSegmentDeclaration();

	/**
	 * Returns a new object of class '<em>Soft New Line Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Soft New Line Segment</em>'.
	 * @generated
	 */
	@NonNull
	SoftNewLineSegment createSoftNewLineSegment();

	/**
	 * Returns a new object of class '<em>Soft Space Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Soft Space Segment</em>'.
	 * @generated
	 */
	@NonNull
	SoftSpaceSegment createSoftSpaceSegment();

	/**
	 * Returns a new object of class '<em>String Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>String Segment</em>'.
	 * @generated
	 */
	@NonNull
	StringSegment createStringSegment();

	/**
	 * Returns a new object of class '<em>Sub Idiom</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sub Idiom</em>'.
	 * @generated
	 */
	@NonNull
	SubIdiom createSubIdiom();

	/**
	 * Returns a new object of class '<em>Value Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Value Segment</em>'.
	 * @generated
	 */
	@NonNull
	ValueSegment createValueSegment();

	/**
	 * Returns a new object of class '<em>Wrap Anchor Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Wrap Anchor Segment</em>'.
	 * @generated
	 */
	@NonNull
	WrapAnchorSegment createWrapAnchorSegment();

	/**
	 * Returns a new object of class '<em>Wrap Begin Some Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Wrap Begin Some Segment</em>'.
	 * @generated
	 */
	@NonNull
	WrapBeginSomeSegment createWrapBeginSomeSegment();

	/**
	 * Returns a new object of class '<em>Wrap Begin All Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Wrap Begin All Segment</em>'.
	 * @generated
	 */
	@NonNull
	WrapBeginAllSegment createWrapBeginAllSegment();

	/**
	 * Returns a new object of class '<em>Wrap End Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Wrap End Segment</em>'.
	 * @generated
	 */
	@NonNull
	WrapEndSegment createWrapEndSegment();

	/**
	 * Returns a new object of class '<em>Wrap Here Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Wrap Here Segment</em>'.
	 * @generated
	 */
	@NonNull
	WrapHereSegment createWrapHereSegment();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	@NonNull
	IdiomsPackage getIdiomsPackage();

} //IdiomsFactory
