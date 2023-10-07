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
package org.eclipse.ocl.xtext.idioms;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * The Idioms configure the Declarative XSerializer/Formatter for  Xtext.
 * <!-- end-model-doc -->
 * @see org.eclipse.ocl.xtext.idioms.IdiomsFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/OCL/Import ecore='http://www.eclipse.org/emf/2002/Ecore'"
 * @generated
 */
public interface IdiomsPackage
		extends EPackage {

	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "idioms"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/ocl/2020/Idioms"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "idioms"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	IdiomsPackage eINSTANCE = org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.IdiomsElementImpl <em>Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsElementImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getIdiomsElement()
	 * @generated
	 */
	int IDIOMS_ELEMENT = 9;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.SegmentImpl <em>Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.SegmentImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getSegment()
	 * @generated
	 */
	int SEGMENT = 25;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.LocatorImpl <em>Locator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.LocatorImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getLocator()
	 * @generated
	 */
	int LOCATOR = 13;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.AnyAssignmentLocatorImpl <em>Any Assignment Locator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.AnyAssignmentLocatorImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getAnyAssignmentLocator()
	 * @generated
	 */
	int ANY_ASSIGNMENT_LOCATOR = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.AnyElementLocatorImpl <em>Any Element Locator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.AnyElementLocatorImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getAnyElementLocator()
	 * @generated
	 */
	int ANY_ELEMENT_LOCATOR = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.AssignmentLocatorImpl <em>Assignment Locator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.AssignmentLocatorImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getAssignmentLocator()
	 * @generated
	 */
	int ASSIGNMENT_LOCATOR = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.CustomSegmentImpl <em>Custom Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.CustomSegmentImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getCustomSegment()
	 * @generated
	 */
	int CUSTOM_SEGMENT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.EPackageDeclarationImpl <em>EPackage Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.EPackageDeclarationImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getEPackageDeclaration()
	 * @generated
	 */
	int EPACKAGE_DECLARATION = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.FinalLocatorImpl <em>Final Locator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.FinalLocatorImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getFinalLocator()
	 * @generated
	 */
	int FINAL_LOCATOR = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.GrammarDeclarationImpl <em>Grammar Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.GrammarDeclarationImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getGrammarDeclaration()
	 * @generated
	 */
	int GRAMMAR_DECLARATION = 6;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.IdiomImpl <em>Idiom</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getIdiom()
	 * @generated
	 */
	int IDIOM = 8;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.IdiomsImportImpl <em>Import</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsImportImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getIdiomsImport()
	 * @generated
	 */
	int IDIOMS_IMPORT = 10;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.IdiomsModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsModelImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getIdiomsModel()
	 * @generated
	 */
	int IDIOMS_MODEL = 11;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.KeywordLocatorImpl <em>Keyword Locator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.KeywordLocatorImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getKeywordLocator()
	 * @generated
	 */
	int KEYWORD_LOCATOR = 12;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.LocatorDeclarationImpl <em>Locator Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.LocatorDeclarationImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getLocatorDeclaration()
	 * @generated
	 */
	int LOCATOR_DECLARATION = 14;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.NewLineSegmentImpl <em>New Line Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.NewLineSegmentImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getNewLineSegment()
	 * @generated
	 */
	int NEW_LINE_SEGMENT = 15;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.NoSpaceSegmentImpl <em>No Space Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.NoSpaceSegmentImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getNoSpaceSegment()
	 * @generated
	 */
	int NO_SPACE_SEGMENT = 16;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.PopSegmentImpl <em>Pop Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.PopSegmentImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getPopSegment()
	 * @generated
	 */
	int POP_SEGMENT = 17;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.ReferredLocatorImpl <em>Referred Locator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.ReferredLocatorImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getReferredLocator()
	 * @generated
	 */
	int REFERRED_LOCATOR = 21;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.ReferredSegmentImpl <em>Referred Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.ReferredSegmentImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getReferredSegment()
	 * @generated
	 */
	int REFERRED_SEGMENT = 22;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.ReturnsLocatorImpl <em>Returns Locator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.ReturnsLocatorImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getReturnsLocator()
	 * @generated
	 */
	int RETURNS_LOCATOR = 23;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.RuleLocatorImpl <em>Rule Locator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.RuleLocatorImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getRuleLocator()
	 * @generated
	 */
	int RULE_LOCATOR = 24;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.PushSegmentImpl <em>Push Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.PushSegmentImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getPushSegment()
	 * @generated
	 */
	int PUSH_SEGMENT = 20;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.SegmentDeclarationImpl <em>Segment Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.SegmentDeclarationImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getSegmentDeclaration()
	 * @generated
	 */
	int SEGMENT_DECLARATION = 26;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.SoftNewLineSegmentImpl <em>Soft New Line Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.SoftNewLineSegmentImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getSoftNewLineSegment()
	 * @generated
	 */
	int SOFT_NEW_LINE_SEGMENT = 27;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.SoftSpaceSegmentImpl <em>Soft Space Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.SoftSpaceSegmentImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getSoftSpaceSegment()
	 * @generated
	 */
	int SOFT_SPACE_SEGMENT = 28;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.StringSegmentImpl <em>String Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.StringSegmentImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getStringSegment()
	 * @generated
	 */
	int STRING_SEGMENT = 29;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.HalfNewLineSegmentImpl <em>Half New Line Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.HalfNewLineSegmentImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getHalfNewLineSegment()
	 * @generated
	 */
	int HALF_NEW_LINE_SEGMENT = 7;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.PostCommentSegmentImpl <em>Post Comment Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.PostCommentSegmentImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getPostCommentSegment()
	 * @generated
	 */
	int POST_COMMENT_SEGMENT = 18;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.PreCommentSegmentImpl <em>Pre Comment Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.PreCommentSegmentImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getPreCommentSegment()
	 * @generated
	 */
	int PRE_COMMENT_SEGMENT = 19;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.SubIdiomImpl <em>Sub Idiom</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.SubIdiomImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getSubIdiom()
	 * @generated
	 */
	int SUB_IDIOM = 30;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.ValueSegmentImpl <em>Value Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.ValueSegmentImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getValueSegment()
	 * @generated
	 */
	int VALUE_SEGMENT = 31;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.WrapAnchorSegmentImpl <em>Wrap Anchor Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.WrapAnchorSegmentImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getWrapAnchorSegment()
	 * @generated
	 */
	int WRAP_ANCHOR_SEGMENT = 32;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.WrapBeginSomeSegmentImpl <em>Wrap Begin Some Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.WrapBeginSomeSegmentImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getWrapBeginSomeSegment()
	 * @generated
	 */
	int WRAP_BEGIN_SOME_SEGMENT = 33;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.WrapBeginAllSegmentImpl <em>Wrap Begin All Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.WrapBeginAllSegmentImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getWrapBeginAllSegment()
	 * @generated
	 */
	int WRAP_BEGIN_ALL_SEGMENT = 34;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.WrapEndSegmentImpl <em>Wrap End Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.WrapEndSegmentImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getWrapEndSegment()
	 * @generated
	 */
	int WRAP_END_SEGMENT = 35;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.idioms.impl.WrapHereSegmentImpl <em>Wrap Here Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.idioms.impl.WrapHereSegmentImpl
	 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getWrapHereSegment()
	 * @generated
	 */
	int WRAP_HERE_SEGMENT = 36;

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.AnyAssignmentLocator <em>Any Assignment Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Any Assignment Locator</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.AnyAssignmentLocator
	 * @generated
	 */
	EClass getAnyAssignmentLocator();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.AnyElementLocator <em>Any Element Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Any Element Locator</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.AnyElementLocator
	 * @generated
	 */
	EClass getAnyElementLocator();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.AssignmentLocator <em>Assignment Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assignment Locator</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.AssignmentLocator
	 * @generated
	 */
	EClass getAssignmentLocator();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.xtext.idioms.AssignmentLocator#getEPackage <em>EPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EPackage</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.AssignmentLocator#getEPackage()
	 * @see #getAssignmentLocator()
	 * @generated
	 */
	EReference getAssignmentLocator_EPackage();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.xtext.idioms.AssignmentLocator#getEClass <em>EClass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EClass</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.AssignmentLocator#getEClass()
	 * @see #getAssignmentLocator()
	 * @generated
	 */
	EReference getAssignmentLocator_EClass();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.xtext.idioms.AssignmentLocator#getEStructuralFeature <em>EStructural Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EStructural Feature</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.AssignmentLocator#getEStructuralFeature()
	 * @see #getAssignmentLocator()
	 * @generated
	 */
	EReference getAssignmentLocator_EStructuralFeature();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.CustomSegment <em>Custom Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Custom Segment</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.CustomSegment
	 * @generated
	 */
	EClass getCustomSegment();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.idioms.CustomSegment#getSupportClassName <em>Support Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Support Class Name</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.CustomSegment#getSupportClassName()
	 * @see #getCustomSegment()
	 * @generated
	 */
	EAttribute getCustomSegment_SupportClassName();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.EPackageDeclaration <em>EPackage Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EPackage Declaration</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.EPackageDeclaration
	 * @generated
	 */
	EClass getEPackageDeclaration();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.idioms.EPackageDeclaration#getAs <em>As</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>As</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.EPackageDeclaration#getAs()
	 * @see #getEPackageDeclaration()
	 * @generated
	 */
	EAttribute getEPackageDeclaration_As();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.xtext.idioms.EPackageDeclaration#getEPackage <em>EPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EPackage</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.EPackageDeclaration#getEPackage()
	 * @see #getEPackageDeclaration()
	 * @generated
	 */
	EReference getEPackageDeclaration_EPackage();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.FinalLocator <em>Final Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Final Locator</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.FinalLocator
	 * @generated
	 */
	EClass getFinalLocator();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.GrammarDeclaration <em>Grammar Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Grammar Declaration</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.GrammarDeclaration
	 * @generated
	 */
	EClass getGrammarDeclaration();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.idioms.GrammarDeclaration#getAs <em>As</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>As</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.GrammarDeclaration#getAs()
	 * @see #getGrammarDeclaration()
	 * @generated
	 */
	EAttribute getGrammarDeclaration_As();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.xtext.idioms.GrammarDeclaration#getGrammar <em>Grammar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Grammar</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.GrammarDeclaration#getGrammar()
	 * @see #getGrammarDeclaration()
	 * @generated
	 */
	EReference getGrammarDeclaration_Grammar();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.HalfNewLineSegment <em>Half New Line Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Half New Line Segment</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.HalfNewLineSegment
	 * @generated
	 */
	EClass getHalfNewLineSegment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.Idiom <em>Idiom</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Idiom</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.Idiom
	 * @generated
	 */
	EClass getIdiom();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.idioms.Idiom#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.Idiom#getName()
	 * @see #getIdiom()
	 * @generated
	 */
	EAttribute getIdiom_Name();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.xtext.idioms.Idiom#getForEPackage <em>For EPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>For EPackage</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.Idiom#getForEPackage()
	 * @see #getIdiom()
	 * @generated
	 */
	EReference getIdiom_ForEPackage();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.xtext.idioms.Idiom#getForEClass <em>For EClass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>For EClass</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.Idiom#getForEClass()
	 * @see #getIdiom()
	 * @generated
	 */
	EReference getIdiom_ForEClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.idioms.Idiom#getInRuleRegex <em>In Rule Regex</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>In Rule Regex</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.Idiom#getInRuleRegex()
	 * @see #getIdiom()
	 * @generated
	 */
	EAttribute getIdiom_InRuleRegex();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.idioms.Idiom#isMixin <em>Mixin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mixin</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.Idiom#isMixin()
	 * @see #getIdiom()
	 * @generated
	 */
	EAttribute getIdiom_Mixin();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.xtext.idioms.Idiom#getOwnedSubIdioms <em>Owned Sub Idioms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Sub Idioms</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.Idiom#getOwnedSubIdioms()
	 * @see #getIdiom()
	 * @generated
	 */
	EReference getIdiom_OwnedSubIdioms();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.IdiomsElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.IdiomsElement
	 * @generated
	 */
	EClass getIdiomsElement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.IdiomsImport <em>Import</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Import</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.IdiomsImport
	 * @generated
	 */
	EClass getIdiomsImport();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.idioms.IdiomsImport#getAs <em>As</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>As</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.IdiomsImport#getAs()
	 * @see #getIdiomsImport()
	 * @generated
	 */
	EAttribute getIdiomsImport_As();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.xtext.idioms.IdiomsImport#getIdiomsModel <em>Idioms Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Idioms Model</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.IdiomsImport#getIdiomsModel()
	 * @see #getIdiomsImport()
	 * @generated
	 */
	EReference getIdiomsImport_IdiomsModel();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.IdiomsModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.IdiomsModel
	 * @generated
	 */
	EClass getIdiomsModel();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.idioms.IdiomsModel#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.IdiomsModel#getName()
	 * @see #getIdiomsModel()
	 * @generated
	 */
	EAttribute getIdiomsModel_Name();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.ocl.xtext.idioms.IdiomsModel#getNames <em>Names</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Names</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.IdiomsModel#getNames()
	 * @see #getIdiomsModel()
	 * @generated
	 */
	EAttribute getIdiomsModel_Names();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.xtext.idioms.IdiomsModel#getOwnedGrammarDeclarations <em>Owned Grammar Declarations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Grammar Declarations</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.IdiomsModel#getOwnedGrammarDeclarations()
	 * @see #getIdiomsModel()
	 * @generated
	 */
	EReference getIdiomsModel_OwnedGrammarDeclarations();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.xtext.idioms.IdiomsModel#getOwnedLocatorDeclarations <em>Owned Locator Declarations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Locator Declarations</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.IdiomsModel#getOwnedLocatorDeclarations()
	 * @see #getIdiomsModel()
	 * @generated
	 */
	EReference getIdiomsModel_OwnedLocatorDeclarations();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.xtext.idioms.IdiomsModel#getOwnedSegmentDeclarations <em>Owned Segment Declarations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Segment Declarations</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.IdiomsModel#getOwnedSegmentDeclarations()
	 * @see #getIdiomsModel()
	 * @generated
	 */
	EReference getIdiomsModel_OwnedSegmentDeclarations();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.xtext.idioms.IdiomsModel#getOwnedIdioms <em>Owned Idioms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Idioms</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.IdiomsModel#getOwnedIdioms()
	 * @see #getIdiomsModel()
	 * @generated
	 */
	EReference getIdiomsModel_OwnedIdioms();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.xtext.idioms.IdiomsModel#getOwnedImportDeclarations <em>Owned Import Declarations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Import Declarations</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.IdiomsModel#getOwnedImportDeclarations()
	 * @see #getIdiomsModel()
	 * @generated
	 */
	EReference getIdiomsModel_OwnedImportDeclarations();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.xtext.idioms.IdiomsModel#getOwnedWiths <em>Owned Withs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Withs</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.IdiomsModel#getOwnedWiths()
	 * @see #getIdiomsModel()
	 * @generated
	 */
	EReference getIdiomsModel_OwnedWiths();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.Locator <em>Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Locator</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.Locator
	 * @generated
	 */
	EClass getLocator();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.xtext.idioms.Locator#getOwningSubIdiom <em>Owning Sub Idiom</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Sub Idiom</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.Locator#getOwningSubIdiom()
	 * @see #getLocator()
	 * @generated
	 */
	EReference getLocator_OwningSubIdiom();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.KeywordLocator <em>Keyword Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Keyword Locator</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.KeywordLocator
	 * @generated
	 */
	EClass getKeywordLocator();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.idioms.KeywordLocator#getString <em>String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>String</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.KeywordLocator#getString()
	 * @see #getKeywordLocator()
	 * @generated
	 */
	EAttribute getKeywordLocator_String();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.LocatorDeclaration <em>Locator Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Locator Declaration</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.LocatorDeclaration
	 * @generated
	 */
	EClass getLocatorDeclaration();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.idioms.LocatorDeclaration#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.LocatorDeclaration#getName()
	 * @see #getLocatorDeclaration()
	 * @generated
	 */
	EAttribute getLocatorDeclaration_Name();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.xtext.idioms.LocatorDeclaration#getOwnedLocator <em>Owned Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Locator</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.LocatorDeclaration#getOwnedLocator()
	 * @see #getLocatorDeclaration()
	 * @generated
	 */
	EReference getLocatorDeclaration_OwnedLocator();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.xtext.idioms.LocatorDeclaration#getOwningIdiomsModel <em>Owning Idioms Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Idioms Model</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.LocatorDeclaration#getOwningIdiomsModel()
	 * @see #getLocatorDeclaration()
	 * @generated
	 */
	EReference getLocatorDeclaration_OwningIdiomsModel();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.NewLineSegment <em>New Line Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>New Line Segment</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.NewLineSegment
	 * @generated
	 */
	EClass getNewLineSegment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.NoSpaceSegment <em>No Space Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>No Space Segment</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.NoSpaceSegment
	 * @generated
	 */
	EClass getNoSpaceSegment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.PopSegment <em>Pop Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pop Segment</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.PopSegment
	 * @generated
	 */
	EClass getPopSegment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.PostCommentSegment <em>Post Comment Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Post Comment Segment</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.PostCommentSegment
	 * @generated
	 */
	EClass getPostCommentSegment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.PreCommentSegment <em>Pre Comment Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pre Comment Segment</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.PreCommentSegment
	 * @generated
	 */
	EClass getPreCommentSegment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.ReferredLocator <em>Referred Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Referred Locator</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.ReferredLocator
	 * @generated
	 */
	EClass getReferredLocator();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.xtext.idioms.ReferredLocator#getIdiomsModel <em>Idioms Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Idioms Model</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.ReferredLocator#getIdiomsModel()
	 * @see #getReferredLocator()
	 * @generated
	 */
	EReference getReferredLocator_IdiomsModel();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.xtext.idioms.ReferredLocator#getLocatorDeclaration <em>Locator Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Locator Declaration</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.ReferredLocator#getLocatorDeclaration()
	 * @see #getReferredLocator()
	 * @generated
	 */
	EReference getReferredLocator_LocatorDeclaration();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.ReferredSegment <em>Referred Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Referred Segment</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.ReferredSegment
	 * @generated
	 */
	EClass getReferredSegment();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.xtext.idioms.ReferredSegment#getIdiomsModel <em>Idioms Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Idioms Model</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.ReferredSegment#getIdiomsModel()
	 * @see #getReferredSegment()
	 * @generated
	 */
	EReference getReferredSegment_IdiomsModel();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.xtext.idioms.ReferredSegment#getSegmentDeclaration <em>Segment Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Segment Declaration</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.ReferredSegment#getSegmentDeclaration()
	 * @see #getReferredSegment()
	 * @generated
	 */
	EReference getReferredSegment_SegmentDeclaration();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.ReturnsLocator <em>Returns Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Returns Locator</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.ReturnsLocator
	 * @generated
	 */
	EClass getReturnsLocator();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.xtext.idioms.ReturnsLocator#getEPackage <em>EPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EPackage</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.ReturnsLocator#getEPackage()
	 * @see #getReturnsLocator()
	 * @generated
	 */
	EReference getReturnsLocator_EPackage();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.xtext.idioms.ReturnsLocator#getEClass <em>EClass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EClass</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.ReturnsLocator#getEClass()
	 * @see #getReturnsLocator()
	 * @generated
	 */
	EReference getReturnsLocator_EClass();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.RuleLocator <em>Rule Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rule Locator</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.RuleLocator
	 * @generated
	 */
	EClass getRuleLocator();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.xtext.idioms.RuleLocator#getReferredGrammar <em>Referred Grammar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referred Grammar</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.RuleLocator#getReferredGrammar()
	 * @see #getRuleLocator()
	 * @generated
	 */
	EReference getRuleLocator_ReferredGrammar();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.xtext.idioms.RuleLocator#getReferredRule <em>Referred Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referred Rule</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.RuleLocator#getReferredRule()
	 * @see #getRuleLocator()
	 * @generated
	 */
	EReference getRuleLocator_ReferredRule();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.PushSegment <em>Push Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Push Segment</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.PushSegment
	 * @generated
	 */
	EClass getPushSegment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.Segment <em>Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Segment</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.Segment
	 * @generated
	 */
	EClass getSegment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.SegmentDeclaration <em>Segment Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Segment Declaration</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.SegmentDeclaration
	 * @generated
	 */
	EClass getSegmentDeclaration();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.idioms.SegmentDeclaration#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.SegmentDeclaration#getName()
	 * @see #getSegmentDeclaration()
	 * @generated
	 */
	EAttribute getSegmentDeclaration_Name();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.xtext.idioms.SegmentDeclaration#getOwnedSegment <em>Owned Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Segment</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.SegmentDeclaration#getOwnedSegment()
	 * @see #getSegmentDeclaration()
	 * @generated
	 */
	EReference getSegmentDeclaration_OwnedSegment();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.xtext.idioms.SegmentDeclaration#getOwningIdiomsModel <em>Owning Idioms Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Idioms Model</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.SegmentDeclaration#getOwningIdiomsModel()
	 * @see #getSegmentDeclaration()
	 * @generated
	 */
	EReference getSegmentDeclaration_OwningIdiomsModel();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.SoftNewLineSegment <em>Soft New Line Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Soft New Line Segment</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.SoftNewLineSegment
	 * @generated
	 */
	EClass getSoftNewLineSegment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.SoftSpaceSegment <em>Soft Space Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Soft Space Segment</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.SoftSpaceSegment
	 * @generated
	 */
	EClass getSoftSpaceSegment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.StringSegment <em>String Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Segment</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.StringSegment
	 * @generated
	 */
	EClass getStringSegment();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.idioms.StringSegment#isPrintable <em>Printable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Printable</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.StringSegment#isPrintable()
	 * @see #getStringSegment()
	 * @generated
	 */
	EAttribute getStringSegment_Printable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.idioms.StringSegment#getString <em>String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>String</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.StringSegment#getString()
	 * @see #getStringSegment()
	 * @generated
	 */
	EAttribute getStringSegment_String();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.SubIdiom <em>Sub Idiom</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sub Idiom</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.SubIdiom
	 * @generated
	 */
	EClass getSubIdiom();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.idioms.SubIdiom#isAll <em>All</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>All</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.SubIdiom#isAll()
	 * @see #getSubIdiom()
	 * @generated
	 */
	EAttribute getSubIdiom_All();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.xtext.idioms.SubIdiom#getOwnedLocator <em>Owned Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Locator</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.SubIdiom#getOwnedLocator()
	 * @see #getSubIdiom()
	 * @generated
	 */
	EReference getSubIdiom_OwnedLocator();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.xtext.idioms.SubIdiom#getOwnedSegments <em>Owned Segments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Segments</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.SubIdiom#getOwnedSegments()
	 * @see #getSubIdiom()
	 * @generated
	 */
	EReference getSubIdiom_OwnedSegments();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.xtext.idioms.SubIdiom#getOwningIdiom <em>Owning Idiom</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Idiom</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.SubIdiom#getOwningIdiom()
	 * @see #getSubIdiom()
	 * @generated
	 */
	EReference getSubIdiom_OwningIdiom();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.ValueSegment <em>Value Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Segment</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.ValueSegment
	 * @generated
	 */
	EClass getValueSegment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.WrapAnchorSegment <em>Wrap Anchor Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Wrap Anchor Segment</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.WrapAnchorSegment
	 * @generated
	 */
	EClass getWrapAnchorSegment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.WrapBeginSomeSegment <em>Wrap Begin Some Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Wrap Begin Some Segment</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.WrapBeginSomeSegment
	 * @generated
	 */
	EClass getWrapBeginSomeSegment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.WrapBeginAllSegment <em>Wrap Begin All Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Wrap Begin All Segment</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.WrapBeginAllSegment
	 * @generated
	 */
	EClass getWrapBeginAllSegment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.WrapEndSegment <em>Wrap End Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Wrap End Segment</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.WrapEndSegment
	 * @generated
	 */
	EClass getWrapEndSegment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.idioms.WrapHereSegment <em>Wrap Here Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Wrap Here Segment</em>'.
	 * @see org.eclipse.ocl.xtext.idioms.WrapHereSegment
	 * @generated
	 */
	EClass getWrapHereSegment();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	IdiomsFactory getIdiomsFactory();

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
	interface Literals {

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.AnyAssignmentLocatorImpl <em>Any Assignment Locator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.AnyAssignmentLocatorImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getAnyAssignmentLocator()
		 * @generated
		 */
		EClass ANY_ASSIGNMENT_LOCATOR = eINSTANCE.getAnyAssignmentLocator();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.AnyElementLocatorImpl <em>Any Element Locator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.AnyElementLocatorImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getAnyElementLocator()
		 * @generated
		 */
		EClass ANY_ELEMENT_LOCATOR = eINSTANCE.getAnyElementLocator();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.AssignmentLocatorImpl <em>Assignment Locator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.AssignmentLocatorImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getAssignmentLocator()
		 * @generated
		 */
		EClass ASSIGNMENT_LOCATOR = eINSTANCE.getAssignmentLocator();

		/**
		 * The meta object literal for the '<em><b>EPackage</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSIGNMENT_LOCATOR__EPACKAGE = eINSTANCE.getAssignmentLocator_EPackage();

		/**
		 * The meta object literal for the '<em><b>EClass</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSIGNMENT_LOCATOR__ECLASS = eINSTANCE.getAssignmentLocator_EClass();

		/**
		 * The meta object literal for the '<em><b>EStructural Feature</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSIGNMENT_LOCATOR__ESTRUCTURAL_FEATURE = eINSTANCE.getAssignmentLocator_EStructuralFeature();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.CustomSegmentImpl <em>Custom Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.CustomSegmentImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getCustomSegment()
		 * @generated
		 */
		EClass CUSTOM_SEGMENT = eINSTANCE.getCustomSegment();

		/**
		 * The meta object literal for the '<em><b>Support Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOM_SEGMENT__SUPPORT_CLASS_NAME = eINSTANCE.getCustomSegment_SupportClassName();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.EPackageDeclarationImpl <em>EPackage Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.EPackageDeclarationImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getEPackageDeclaration()
		 * @generated
		 */
		EClass EPACKAGE_DECLARATION = eINSTANCE.getEPackageDeclaration();

		/**
		 * The meta object literal for the '<em><b>As</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EPACKAGE_DECLARATION__AS = eINSTANCE.getEPackageDeclaration_As();

		/**
		 * The meta object literal for the '<em><b>EPackage</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EPACKAGE_DECLARATION__EPACKAGE = eINSTANCE.getEPackageDeclaration_EPackage();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.FinalLocatorImpl <em>Final Locator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.FinalLocatorImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getFinalLocator()
		 * @generated
		 */
		EClass FINAL_LOCATOR = eINSTANCE.getFinalLocator();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.GrammarDeclarationImpl <em>Grammar Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.GrammarDeclarationImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getGrammarDeclaration()
		 * @generated
		 */
		EClass GRAMMAR_DECLARATION = eINSTANCE.getGrammarDeclaration();

		/**
		 * The meta object literal for the '<em><b>As</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRAMMAR_DECLARATION__AS = eINSTANCE.getGrammarDeclaration_As();

		/**
		 * The meta object literal for the '<em><b>Grammar</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAMMAR_DECLARATION__GRAMMAR = eINSTANCE.getGrammarDeclaration_Grammar();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.HalfNewLineSegmentImpl <em>Half New Line Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.HalfNewLineSegmentImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getHalfNewLineSegment()
		 * @generated
		 */
		EClass HALF_NEW_LINE_SEGMENT = eINSTANCE.getHalfNewLineSegment();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.IdiomImpl <em>Idiom</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getIdiom()
		 * @generated
		 */
		EClass IDIOM = eINSTANCE.getIdiom();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDIOM__NAME = eINSTANCE.getIdiom_Name();

		/**
		 * The meta object literal for the '<em><b>For EPackage</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IDIOM__FOR_EPACKAGE = eINSTANCE.getIdiom_ForEPackage();

		/**
		 * The meta object literal for the '<em><b>For EClass</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IDIOM__FOR_ECLASS = eINSTANCE.getIdiom_ForEClass();

		/**
		 * The meta object literal for the '<em><b>In Rule Regex</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDIOM__IN_RULE_REGEX = eINSTANCE.getIdiom_InRuleRegex();

		/**
		 * The meta object literal for the '<em><b>Mixin</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDIOM__MIXIN = eINSTANCE.getIdiom_Mixin();

		/**
		 * The meta object literal for the '<em><b>Owned Sub Idioms</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IDIOM__OWNED_SUB_IDIOMS = eINSTANCE.getIdiom_OwnedSubIdioms();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.IdiomsElementImpl <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsElementImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getIdiomsElement()
		 * @generated
		 */
		EClass IDIOMS_ELEMENT = eINSTANCE.getIdiomsElement();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.IdiomsImportImpl <em>Import</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsImportImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getIdiomsImport()
		 * @generated
		 */
		EClass IDIOMS_IMPORT = eINSTANCE.getIdiomsImport();

		/**
		 * The meta object literal for the '<em><b>As</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDIOMS_IMPORT__AS = eINSTANCE.getIdiomsImport_As();

		/**
		 * The meta object literal for the '<em><b>Idioms Model</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IDIOMS_IMPORT__IDIOMS_MODEL = eINSTANCE.getIdiomsImport_IdiomsModel();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.IdiomsModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsModelImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getIdiomsModel()
		 * @generated
		 */
		EClass IDIOMS_MODEL = eINSTANCE.getIdiomsModel();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDIOMS_MODEL__NAME = eINSTANCE.getIdiomsModel_Name();

		/**
		 * The meta object literal for the '<em><b>Names</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDIOMS_MODEL__NAMES = eINSTANCE.getIdiomsModel_Names();

		/**
		 * The meta object literal for the '<em><b>Owned Grammar Declarations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IDIOMS_MODEL__OWNED_GRAMMAR_DECLARATIONS = eINSTANCE.getIdiomsModel_OwnedGrammarDeclarations();

		/**
		 * The meta object literal for the '<em><b>Owned Locator Declarations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IDIOMS_MODEL__OWNED_LOCATOR_DECLARATIONS = eINSTANCE.getIdiomsModel_OwnedLocatorDeclarations();

		/**
		 * The meta object literal for the '<em><b>Owned Segment Declarations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IDIOMS_MODEL__OWNED_SEGMENT_DECLARATIONS = eINSTANCE.getIdiomsModel_OwnedSegmentDeclarations();

		/**
		 * The meta object literal for the '<em><b>Owned Idioms</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IDIOMS_MODEL__OWNED_IDIOMS = eINSTANCE.getIdiomsModel_OwnedIdioms();

		/**
		 * The meta object literal for the '<em><b>Owned Import Declarations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IDIOMS_MODEL__OWNED_IMPORT_DECLARATIONS = eINSTANCE.getIdiomsModel_OwnedImportDeclarations();

		/**
		 * The meta object literal for the '<em><b>Owned Withs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IDIOMS_MODEL__OWNED_WITHS = eINSTANCE.getIdiomsModel_OwnedWiths();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.LocatorImpl <em>Locator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.LocatorImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getLocator()
		 * @generated
		 */
		EClass LOCATOR = eINSTANCE.getLocator();

		/**
		 * The meta object literal for the '<em><b>Owning Sub Idiom</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOCATOR__OWNING_SUB_IDIOM = eINSTANCE.getLocator_OwningSubIdiom();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.KeywordLocatorImpl <em>Keyword Locator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.KeywordLocatorImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getKeywordLocator()
		 * @generated
		 */
		EClass KEYWORD_LOCATOR = eINSTANCE.getKeywordLocator();

		/**
		 * The meta object literal for the '<em><b>String</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute KEYWORD_LOCATOR__STRING = eINSTANCE.getKeywordLocator_String();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.LocatorDeclarationImpl <em>Locator Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.LocatorDeclarationImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getLocatorDeclaration()
		 * @generated
		 */
		EClass LOCATOR_DECLARATION = eINSTANCE.getLocatorDeclaration();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCATOR_DECLARATION__NAME = eINSTANCE.getLocatorDeclaration_Name();

		/**
		 * The meta object literal for the '<em><b>Owned Locator</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOCATOR_DECLARATION__OWNED_LOCATOR = eINSTANCE.getLocatorDeclaration_OwnedLocator();

		/**
		 * The meta object literal for the '<em><b>Owning Idioms Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOCATOR_DECLARATION__OWNING_IDIOMS_MODEL = eINSTANCE.getLocatorDeclaration_OwningIdiomsModel();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.NewLineSegmentImpl <em>New Line Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.NewLineSegmentImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getNewLineSegment()
		 * @generated
		 */
		EClass NEW_LINE_SEGMENT = eINSTANCE.getNewLineSegment();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.NoSpaceSegmentImpl <em>No Space Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.NoSpaceSegmentImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getNoSpaceSegment()
		 * @generated
		 */
		EClass NO_SPACE_SEGMENT = eINSTANCE.getNoSpaceSegment();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.PopSegmentImpl <em>Pop Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.PopSegmentImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getPopSegment()
		 * @generated
		 */
		EClass POP_SEGMENT = eINSTANCE.getPopSegment();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.PostCommentSegmentImpl <em>Post Comment Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.PostCommentSegmentImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getPostCommentSegment()
		 * @generated
		 */
		EClass POST_COMMENT_SEGMENT = eINSTANCE.getPostCommentSegment();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.PreCommentSegmentImpl <em>Pre Comment Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.PreCommentSegmentImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getPreCommentSegment()
		 * @generated
		 */
		EClass PRE_COMMENT_SEGMENT = eINSTANCE.getPreCommentSegment();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.ReferredLocatorImpl <em>Referred Locator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.ReferredLocatorImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getReferredLocator()
		 * @generated
		 */
		EClass REFERRED_LOCATOR = eINSTANCE.getReferredLocator();

		/**
		 * The meta object literal for the '<em><b>Idioms Model</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERRED_LOCATOR__IDIOMS_MODEL = eINSTANCE.getReferredLocator_IdiomsModel();

		/**
		 * The meta object literal for the '<em><b>Locator Declaration</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERRED_LOCATOR__LOCATOR_DECLARATION = eINSTANCE.getReferredLocator_LocatorDeclaration();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.ReferredSegmentImpl <em>Referred Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.ReferredSegmentImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getReferredSegment()
		 * @generated
		 */
		EClass REFERRED_SEGMENT = eINSTANCE.getReferredSegment();

		/**
		 * The meta object literal for the '<em><b>Idioms Model</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERRED_SEGMENT__IDIOMS_MODEL = eINSTANCE.getReferredSegment_IdiomsModel();

		/**
		 * The meta object literal for the '<em><b>Segment Declaration</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERRED_SEGMENT__SEGMENT_DECLARATION = eINSTANCE.getReferredSegment_SegmentDeclaration();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.ReturnsLocatorImpl <em>Returns Locator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.ReturnsLocatorImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getReturnsLocator()
		 * @generated
		 */
		EClass RETURNS_LOCATOR = eINSTANCE.getReturnsLocator();

		/**
		 * The meta object literal for the '<em><b>EPackage</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RETURNS_LOCATOR__EPACKAGE = eINSTANCE.getReturnsLocator_EPackage();

		/**
		 * The meta object literal for the '<em><b>EClass</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RETURNS_LOCATOR__ECLASS = eINSTANCE.getReturnsLocator_EClass();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.RuleLocatorImpl <em>Rule Locator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.RuleLocatorImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getRuleLocator()
		 * @generated
		 */
		EClass RULE_LOCATOR = eINSTANCE.getRuleLocator();

		/**
		 * The meta object literal for the '<em><b>Referred Grammar</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE_LOCATOR__REFERRED_GRAMMAR = eINSTANCE.getRuleLocator_ReferredGrammar();

		/**
		 * The meta object literal for the '<em><b>Referred Rule</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE_LOCATOR__REFERRED_RULE = eINSTANCE.getRuleLocator_ReferredRule();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.PushSegmentImpl <em>Push Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.PushSegmentImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getPushSegment()
		 * @generated
		 */
		EClass PUSH_SEGMENT = eINSTANCE.getPushSegment();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.SegmentImpl <em>Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.SegmentImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getSegment()
		 * @generated
		 */
		EClass SEGMENT = eINSTANCE.getSegment();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.SegmentDeclarationImpl <em>Segment Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.SegmentDeclarationImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getSegmentDeclaration()
		 * @generated
		 */
		EClass SEGMENT_DECLARATION = eINSTANCE.getSegmentDeclaration();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEGMENT_DECLARATION__NAME = eINSTANCE.getSegmentDeclaration_Name();

		/**
		 * The meta object literal for the '<em><b>Owned Segment</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEGMENT_DECLARATION__OWNED_SEGMENT = eINSTANCE.getSegmentDeclaration_OwnedSegment();

		/**
		 * The meta object literal for the '<em><b>Owning Idioms Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEGMENT_DECLARATION__OWNING_IDIOMS_MODEL = eINSTANCE.getSegmentDeclaration_OwningIdiomsModel();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.SoftNewLineSegmentImpl <em>Soft New Line Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.SoftNewLineSegmentImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getSoftNewLineSegment()
		 * @generated
		 */
		EClass SOFT_NEW_LINE_SEGMENT = eINSTANCE.getSoftNewLineSegment();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.SoftSpaceSegmentImpl <em>Soft Space Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.SoftSpaceSegmentImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getSoftSpaceSegment()
		 * @generated
		 */
		EClass SOFT_SPACE_SEGMENT = eINSTANCE.getSoftSpaceSegment();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.StringSegmentImpl <em>String Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.StringSegmentImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getStringSegment()
		 * @generated
		 */
		EClass STRING_SEGMENT = eINSTANCE.getStringSegment();

		/**
		 * The meta object literal for the '<em><b>Printable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_SEGMENT__PRINTABLE = eINSTANCE.getStringSegment_Printable();

		/**
		 * The meta object literal for the '<em><b>String</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_SEGMENT__STRING = eINSTANCE.getStringSegment_String();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.SubIdiomImpl <em>Sub Idiom</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.SubIdiomImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getSubIdiom()
		 * @generated
		 */
		EClass SUB_IDIOM = eINSTANCE.getSubIdiom();

		/**
		 * The meta object literal for the '<em><b>All</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUB_IDIOM__ALL = eINSTANCE.getSubIdiom_All();

		/**
		 * The meta object literal for the '<em><b>Owned Locator</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUB_IDIOM__OWNED_LOCATOR = eINSTANCE.getSubIdiom_OwnedLocator();

		/**
		 * The meta object literal for the '<em><b>Owned Segments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUB_IDIOM__OWNED_SEGMENTS = eINSTANCE.getSubIdiom_OwnedSegments();

		/**
		 * The meta object literal for the '<em><b>Owning Idiom</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUB_IDIOM__OWNING_IDIOM = eINSTANCE.getSubIdiom_OwningIdiom();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.ValueSegmentImpl <em>Value Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.ValueSegmentImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getValueSegment()
		 * @generated
		 */
		EClass VALUE_SEGMENT = eINSTANCE.getValueSegment();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.WrapAnchorSegmentImpl <em>Wrap Anchor Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.WrapAnchorSegmentImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getWrapAnchorSegment()
		 * @generated
		 */
		EClass WRAP_ANCHOR_SEGMENT = eINSTANCE.getWrapAnchorSegment();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.WrapBeginSomeSegmentImpl <em>Wrap Begin Some Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.WrapBeginSomeSegmentImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getWrapBeginSomeSegment()
		 * @generated
		 */
		EClass WRAP_BEGIN_SOME_SEGMENT = eINSTANCE.getWrapBeginSomeSegment();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.WrapBeginAllSegmentImpl <em>Wrap Begin All Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.WrapBeginAllSegmentImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getWrapBeginAllSegment()
		 * @generated
		 */
		EClass WRAP_BEGIN_ALL_SEGMENT = eINSTANCE.getWrapBeginAllSegment();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.WrapEndSegmentImpl <em>Wrap End Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.WrapEndSegmentImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getWrapEndSegment()
		 * @generated
		 */
		EClass WRAP_END_SEGMENT = eINSTANCE.getWrapEndSegment();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.idioms.impl.WrapHereSegmentImpl <em>Wrap Here Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.idioms.impl.WrapHereSegmentImpl
		 * @see org.eclipse.ocl.xtext.idioms.impl.IdiomsPackageImpl#getWrapHereSegment()
		 * @generated
		 */
		EClass WRAP_HERE_SEGMENT = eINSTANCE.getWrapHereSegment();

	}

} //IdiomsPackage
