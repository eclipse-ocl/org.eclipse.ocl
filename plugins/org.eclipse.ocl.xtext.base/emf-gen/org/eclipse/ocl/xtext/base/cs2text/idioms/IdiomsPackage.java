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
package org.eclipse.ocl.xtext.base.cs2text.idioms;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/OCL/Import ecore='http://www.eclipse.org/emf/2002/Ecore'"
 * @generated
 */
public interface IdiomsPackage extends EPackage
{
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
	IdiomsPackage eINSTANCE = org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.SegmentImpl <em>Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.SegmentImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getSegment()
	 * @generated
	 */
	int SEGMENT = 15;
	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.LocatorImpl <em>Locator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.LocatorImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getLocator()
	 * @generated
	 */
	int LOCATOR = 8;
	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.AssignmentLocatorImpl <em>Assignment Locator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.AssignmentLocatorImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getAssignmentLocator()
	 * @generated
	 */
	int ASSIGNMENT_LOCATOR = 0;
	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegmentSupport <em>Custom Segment Support</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegmentSupport
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getCustomSegmentSupport()
	 * @generated
	 */
	int CUSTOM_SEGMENT_SUPPORT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.CommentSegmentSupportImpl <em>Comment Segment Support</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.CommentSegmentSupportImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getCommentSegmentSupport()
	 * @generated
	 */
	int COMMENT_SEGMENT_SUPPORT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.CustomSegmentImpl <em>Custom Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.CustomSegmentImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getCustomSegment()
	 * @generated
	 */
	int CUSTOM_SEGMENT = 2;
	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.DefaultLocatorImpl <em>Default Locator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.DefaultLocatorImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getDefaultLocator()
	 * @generated
	 */
	int DEFAULT_LOCATOR = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomImpl <em>Idiom</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getIdiom()
	 * @generated
	 */
	int IDIOM = 6;
	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomModelImpl <em>Idiom Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomModelImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getIdiomModel()
	 * @generated
	 */
	int IDIOM_MODEL = 7;
	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.KeywordLocatorImpl <em>Keyword Locator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.KeywordLocatorImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getKeywordLocator()
	 * @generated
	 */
	int KEYWORD_LOCATOR = 9;
	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.NewLineSegmentImpl <em>New Line Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.NewLineSegmentImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getNewLineSegment()
	 * @generated
	 */
	int NEW_LINE_SEGMENT = 10;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.NoSpaceSegmentImpl <em>No Space Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.NoSpaceSegmentImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getNoSpaceSegment()
	 * @generated
	 */
	int NO_SPACE_SEGMENT = 11;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.PopSegmentImpl <em>Pop Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.PopSegmentImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getPopSegment()
	 * @generated
	 */
	int POP_SEGMENT = 12;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.ProducedEClassLocatorImpl <em>Produced EClass Locator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.ProducedEClassLocatorImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getProducedEClassLocator()
	 * @generated
	 */
	int PRODUCED_ECLASS_LOCATOR = 13;
	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.PushSegmentImpl <em>Push Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.PushSegmentImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getPushSegment()
	 * @generated
	 */
	int PUSH_SEGMENT = 14;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.SoftNewLineSegmentImpl <em>Soft New Line Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.SoftNewLineSegmentImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getSoftNewLineSegment()
	 * @generated
	 */
	int SOFT_NEW_LINE_SEGMENT = 16;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.SoftSpaceSegmentImpl <em>Soft Space Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.SoftSpaceSegmentImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getSoftSpaceSegment()
	 * @generated
	 */
	int SOFT_SPACE_SEGMENT = 17;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.StringSegmentImpl <em>String Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.StringSegmentImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getStringSegment()
	 * @generated
	 */
	int STRING_SEGMENT = 18;
	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.HalfNewLineSegmentImpl <em>Half New Line Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.HalfNewLineSegmentImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getHalfNewLineSegment()
	 * @generated
	 */
	int HALF_NEW_LINE_SEGMENT = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.SubIdiomImpl <em>Sub Idiom</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.SubIdiomImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getSubIdiom()
	 * @generated
	 */
	int SUB_IDIOM = 19;
	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.ValueSegmentImpl <em>Value Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.ValueSegmentImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getValueSegment()
	 * @generated
	 */
	int VALUE_SEGMENT = 20;
	/**
	 * The meta object id for the '<em>Locator Helper</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRuleAnalysis.LocatorHelper
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getLocatorHelper()
	 * @generated
	 */
	int LOCATOR_HELPER = 21;

	/**
	 * The meta object id for the '<em>Serialization Builder</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationBuilder
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getSerializationBuilder()
	 * @generated
	 */
	int SERIALIZATION_BUILDER = 23;
	/**
	 * The meta object id for the '<em>Serialization Step</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getSerializationStep()
	 * @generated
	 */
	int SERIALIZATION_STEP = 24;

	/**
	 * The meta object id for the '<em>User Element Serializer</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.runtime.UserElementSerializer
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getUserElementSerializer()
	 * @generated
	 */
	int USER_ELEMENT_SERIALIZER = 25;


	/**
	 * The meta object id for the '<em>Segment Helper</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.SegmentHelper
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getSegmentHelper()
	 * @generated
	 */
	int SEGMENT_HELPER = 22;


	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.AssignmentLocator <em>Assignment Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assignment Locator</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.AssignmentLocator
	 * @generated
	 */
	EClass getAssignmentLocator();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.AssignmentLocator#getEStructuralFeature <em>EStructural Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EStructural Feature</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.AssignmentLocator#getEStructuralFeature()
	 * @see #getAssignmentLocator()
	 * @generated
	 */
	EReference getAssignmentLocator_EStructuralFeature();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.CommentSegmentSupport <em>Comment Segment Support</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Comment Segment Support</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.CommentSegmentSupport
	 * @generated
	 */
	EClass getCommentSegmentSupport();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.CommentSegmentSupport#getEpilogue <em>Epilogue</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Epilogue</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.CommentSegmentSupport#getEpilogue()
	 * @see #getCommentSegmentSupport()
	 * @generated
	 */
	EAttribute getCommentSegmentSupport_Epilogue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.CommentSegmentSupport#getIndentation <em>Indentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Indentation</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.CommentSegmentSupport#getIndentation()
	 * @see #getCommentSegmentSupport()
	 * @generated
	 */
	EAttribute getCommentSegmentSupport_Indentation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.CommentSegmentSupport#getPrologue <em>Prologue</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Prologue</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.CommentSegmentSupport#getPrologue()
	 * @see #getCommentSegmentSupport()
	 * @generated
	 */
	EAttribute getCommentSegmentSupport_Prologue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment <em>Custom Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Custom Segment</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment
	 * @generated
	 */
	EClass getCustomSegment();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment#getSupportClass <em>Support Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Support Class</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment#getSupportClass()
	 * @see #getCustomSegment()
	 * @generated
	 */
	EAttribute getCustomSegment_SupportClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment#getSupportClassName <em>Support Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Support Class Name</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment#getSupportClassName()
	 * @see #getCustomSegment()
	 * @generated
	 */
	EAttribute getCustomSegment_SupportClassName();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegmentSupport <em>Custom Segment Support</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Custom Segment Support</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegmentSupport
	 * @generated
	 */
	EClass getCustomSegmentSupport();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.DefaultLocator <em>Default Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Default Locator</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.DefaultLocator
	 * @generated
	 */
	EClass getDefaultLocator();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.HalfNewLineSegment <em>Half New Line Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Half New Line Segment</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.HalfNewLineSegment
	 * @generated
	 */
	EClass getHalfNewLineSegment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.Idiom <em>Idiom</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Idiom</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.Idiom
	 * @generated
	 */
	EClass getIdiom();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.Idiom#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.Idiom#getName()
	 * @see #getIdiom()
	 * @generated
	 */
	EAttribute getIdiom_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.Idiom#getOwnedSubIdioms <em>Owned Sub Idioms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Sub Idioms</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.Idiom#getOwnedSubIdioms()
	 * @see #getIdiom()
	 * @generated
	 */
	EReference getIdiom_OwnedSubIdioms();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomModel <em>Idiom Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Idiom Model</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomModel
	 * @generated
	 */
	EClass getIdiomModel();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomModel#getImports <em>Imports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Imports</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomModel#getImports()
	 * @see #getIdiomModel()
	 * @generated
	 */
	EReference getIdiomModel_Imports();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomModel#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomModel#getName()
	 * @see #getIdiomModel()
	 * @generated
	 */
	EAttribute getIdiomModel_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomModel#getOwnedIdioms <em>Owned Idioms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Idioms</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomModel#getOwnedIdioms()
	 * @see #getIdiomModel()
	 * @generated
	 */
	EReference getIdiomModel_OwnedIdioms();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomModel#getOwnedLocators <em>Owned Locators</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Locators</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomModel#getOwnedLocators()
	 * @see #getIdiomModel()
	 * @generated
	 */
	EReference getIdiomModel_OwnedLocators();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomModel#getOwnedSegments <em>Owned Segments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Segments</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomModel#getOwnedSegments()
	 * @see #getIdiomModel()
	 * @generated
	 */
	EReference getIdiomModel_OwnedSegments();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.Locator <em>Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Locator</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.Locator
	 * @generated
	 */
	EClass getLocator();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.Locator#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.Locator#getName()
	 * @see #getLocator()
	 * @generated
	 */
	EAttribute getLocator_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.Locator#getHelper <em>Helper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Helper</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.Locator#getHelper()
	 * @see #getLocator()
	 * @generated
	 */
	EAttribute getLocator_Helper();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.KeywordLocator <em>Keyword Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Keyword Locator</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.KeywordLocator
	 * @generated
	 */
	EClass getKeywordLocator();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.KeywordLocator#getString <em>String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>String</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.KeywordLocator#getString()
	 * @see #getKeywordLocator()
	 * @generated
	 */
	EAttribute getKeywordLocator_String();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.KeywordLocator#getInEClass <em>In EClass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>In EClass</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.KeywordLocator#getInEClass()
	 * @see #getKeywordLocator()
	 * @generated
	 */
	EReference getKeywordLocator_InEClass();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.NewLineSegment <em>New Line Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>New Line Segment</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.NewLineSegment
	 * @generated
	 */
	EClass getNewLineSegment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.NoSpaceSegment <em>No Space Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>No Space Segment</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.NoSpaceSegment
	 * @generated
	 */
	EClass getNoSpaceSegment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.PopSegment <em>Pop Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pop Segment</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.PopSegment
	 * @generated
	 */
	EClass getPopSegment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.ProducedEClassLocator <em>Produced EClass Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Produced EClass Locator</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.ProducedEClassLocator
	 * @generated
	 */
	EClass getProducedEClassLocator();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.ProducedEClassLocator#getEClass <em>EClass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EClass</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.ProducedEClassLocator#getEClass()
	 * @see #getProducedEClassLocator()
	 * @generated
	 */
	EReference getProducedEClassLocator_EClass();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.PushSegment <em>Push Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Push Segment</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.PushSegment
	 * @generated
	 */
	EClass getPushSegment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.Segment <em>Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Segment</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.Segment
	 * @generated
	 */
	EClass getSegment();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.Segment#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.Segment#getName()
	 * @see #getSegment()
	 * @generated
	 */
	EAttribute getSegment_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.Segment#getHelper <em>Helper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Helper</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.Segment#getHelper()
	 * @see #getSegment()
	 * @generated
	 */
	EAttribute getSegment_Helper();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.SoftNewLineSegment <em>Soft New Line Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Soft New Line Segment</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.SoftNewLineSegment
	 * @generated
	 */
	EClass getSoftNewLineSegment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.SoftSpaceSegment <em>Soft Space Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Soft Space Segment</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.SoftSpaceSegment
	 * @generated
	 */
	EClass getSoftSpaceSegment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.StringSegment <em>String Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Segment</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.StringSegment
	 * @generated
	 */
	EClass getStringSegment();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.StringSegment#isPrintable <em>Printable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Printable</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.StringSegment#isPrintable()
	 * @see #getStringSegment()
	 * @generated
	 */
	EAttribute getStringSegment_Printable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.StringSegment#getString <em>String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>String</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.StringSegment#getString()
	 * @see #getStringSegment()
	 * @generated
	 */
	EAttribute getStringSegment_String();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom <em>Sub Idiom</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sub Idiom</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom
	 * @generated
	 */
	EClass getSubIdiom();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom#getLocator <em>Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Locator</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom#getLocator()
	 * @see #getSubIdiom()
	 * @generated
	 */
	EReference getSubIdiom_Locator();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom#getName()
	 * @see #getSubIdiom()
	 * @generated
	 */
	EAttribute getSubIdiom_Name();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom#getSegments <em>Segments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Segments</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom#getSegments()
	 * @see #getSubIdiom()
	 * @generated
	 */
	EReference getSubIdiom_Segments();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.ValueSegment <em>Value Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Segment</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.ValueSegment
	 * @generated
	 */
	EClass getValueSegment();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRuleAnalysis.LocatorHelper <em>Locator Helper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Locator Helper</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRuleAnalysis.LocatorHelper
	 * @model instanceClass="org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRuleAnalysis.LocatorHelper" serializeable="false"
	 * @generated
	 */
	EDataType getLocatorHelper();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationBuilder <em>Serialization Builder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Serialization Builder</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationBuilder
	 * @model instanceClass="org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationBuilder" serializeable="false"
	 * @generated
	 */
	EDataType getSerializationBuilder();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep <em>Serialization Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Serialization Step</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep
	 * @model instanceClass="org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep"
	 * @generated
	 */
	EDataType getSerializationStep();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.xtext.base.cs2text.runtime.UserElementSerializer <em>User Element Serializer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>User Element Serializer</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.runtime.UserElementSerializer
	 * @model instanceClass="org.eclipse.ocl.xtext.base.cs2text.runtime.UserElementSerializer" serializeable="false"
	 * @generated
	 */
	EDataType getUserElementSerializer();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.SegmentHelper <em>Segment Helper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Segment Helper</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.SegmentHelper
	 * @model instanceClass="org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.SegmentHelper" serializeable="false"
	 * @generated
	 */
	EDataType getSegmentHelper();

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
	interface Literals
	{
		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.AssignmentLocatorImpl <em>Assignment Locator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.AssignmentLocatorImpl
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getAssignmentLocator()
		 * @generated
		 */
		EClass ASSIGNMENT_LOCATOR = eINSTANCE.getAssignmentLocator();

		/**
		 * The meta object literal for the '<em><b>EStructural Feature</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSIGNMENT_LOCATOR__ESTRUCTURAL_FEATURE = eINSTANCE.getAssignmentLocator_EStructuralFeature();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.CommentSegmentSupportImpl <em>Comment Segment Support</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.CommentSegmentSupportImpl
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getCommentSegmentSupport()
		 * @generated
		 */
		EClass COMMENT_SEGMENT_SUPPORT = eINSTANCE.getCommentSegmentSupport();

		/**
		 * The meta object literal for the '<em><b>Epilogue</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT_SEGMENT_SUPPORT__EPILOGUE = eINSTANCE.getCommentSegmentSupport_Epilogue();

		/**
		 * The meta object literal for the '<em><b>Indentation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT_SEGMENT_SUPPORT__INDENTATION = eINSTANCE.getCommentSegmentSupport_Indentation();

		/**
		 * The meta object literal for the '<em><b>Prologue</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT_SEGMENT_SUPPORT__PROLOGUE = eINSTANCE.getCommentSegmentSupport_Prologue();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.CustomSegmentImpl <em>Custom Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.CustomSegmentImpl
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getCustomSegment()
		 * @generated
		 */
		EClass CUSTOM_SEGMENT = eINSTANCE.getCustomSegment();

		/**
		 * The meta object literal for the '<em><b>Support Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOM_SEGMENT__SUPPORT_CLASS = eINSTANCE.getCustomSegment_SupportClass();

		/**
		 * The meta object literal for the '<em><b>Support Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOM_SEGMENT__SUPPORT_CLASS_NAME = eINSTANCE.getCustomSegment_SupportClassName();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegmentSupport <em>Custom Segment Support</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegmentSupport
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getCustomSegmentSupport()
		 * @generated
		 */
		EClass CUSTOM_SEGMENT_SUPPORT = eINSTANCE.getCustomSegmentSupport();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.DefaultLocatorImpl <em>Default Locator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.DefaultLocatorImpl
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getDefaultLocator()
		 * @generated
		 */
		EClass DEFAULT_LOCATOR = eINSTANCE.getDefaultLocator();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.HalfNewLineSegmentImpl <em>Half New Line Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.HalfNewLineSegmentImpl
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getHalfNewLineSegment()
		 * @generated
		 */
		EClass HALF_NEW_LINE_SEGMENT = eINSTANCE.getHalfNewLineSegment();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomImpl <em>Idiom</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomImpl
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getIdiom()
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
		 * The meta object literal for the '<em><b>Owned Sub Idioms</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IDIOM__OWNED_SUB_IDIOMS = eINSTANCE.getIdiom_OwnedSubIdioms();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomModelImpl <em>Idiom Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomModelImpl
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getIdiomModel()
		 * @generated
		 */
		EClass IDIOM_MODEL = eINSTANCE.getIdiomModel();

		/**
		 * The meta object literal for the '<em><b>Imports</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IDIOM_MODEL__IMPORTS = eINSTANCE.getIdiomModel_Imports();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDIOM_MODEL__NAME = eINSTANCE.getIdiomModel_Name();

		/**
		 * The meta object literal for the '<em><b>Owned Idioms</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IDIOM_MODEL__OWNED_IDIOMS = eINSTANCE.getIdiomModel_OwnedIdioms();

		/**
		 * The meta object literal for the '<em><b>Owned Locators</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IDIOM_MODEL__OWNED_LOCATORS = eINSTANCE.getIdiomModel_OwnedLocators();

		/**
		 * The meta object literal for the '<em><b>Owned Segments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IDIOM_MODEL__OWNED_SEGMENTS = eINSTANCE.getIdiomModel_OwnedSegments();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.LocatorImpl <em>Locator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.LocatorImpl
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getLocator()
		 * @generated
		 */
		EClass LOCATOR = eINSTANCE.getLocator();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCATOR__NAME = eINSTANCE.getLocator_Name();

		/**
		 * The meta object literal for the '<em><b>Helper</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCATOR__HELPER = eINSTANCE.getLocator_Helper();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.KeywordLocatorImpl <em>Keyword Locator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.KeywordLocatorImpl
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getKeywordLocator()
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
		 * The meta object literal for the '<em><b>In EClass</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference KEYWORD_LOCATOR__IN_ECLASS = eINSTANCE.getKeywordLocator_InEClass();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.NewLineSegmentImpl <em>New Line Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.NewLineSegmentImpl
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getNewLineSegment()
		 * @generated
		 */
		EClass NEW_LINE_SEGMENT = eINSTANCE.getNewLineSegment();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.NoSpaceSegmentImpl <em>No Space Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.NoSpaceSegmentImpl
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getNoSpaceSegment()
		 * @generated
		 */
		EClass NO_SPACE_SEGMENT = eINSTANCE.getNoSpaceSegment();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.PopSegmentImpl <em>Pop Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.PopSegmentImpl
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getPopSegment()
		 * @generated
		 */
		EClass POP_SEGMENT = eINSTANCE.getPopSegment();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.ProducedEClassLocatorImpl <em>Produced EClass Locator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.ProducedEClassLocatorImpl
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getProducedEClassLocator()
		 * @generated
		 */
		EClass PRODUCED_ECLASS_LOCATOR = eINSTANCE.getProducedEClassLocator();

		/**
		 * The meta object literal for the '<em><b>EClass</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCED_ECLASS_LOCATOR__ECLASS = eINSTANCE.getProducedEClassLocator_EClass();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.PushSegmentImpl <em>Push Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.PushSegmentImpl
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getPushSegment()
		 * @generated
		 */
		EClass PUSH_SEGMENT = eINSTANCE.getPushSegment();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.SegmentImpl <em>Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.SegmentImpl
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getSegment()
		 * @generated
		 */
		EClass SEGMENT = eINSTANCE.getSegment();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEGMENT__NAME = eINSTANCE.getSegment_Name();

		/**
		 * The meta object literal for the '<em><b>Helper</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEGMENT__HELPER = eINSTANCE.getSegment_Helper();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.SoftNewLineSegmentImpl <em>Soft New Line Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.SoftNewLineSegmentImpl
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getSoftNewLineSegment()
		 * @generated
		 */
		EClass SOFT_NEW_LINE_SEGMENT = eINSTANCE.getSoftNewLineSegment();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.SoftSpaceSegmentImpl <em>Soft Space Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.SoftSpaceSegmentImpl
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getSoftSpaceSegment()
		 * @generated
		 */
		EClass SOFT_SPACE_SEGMENT = eINSTANCE.getSoftSpaceSegment();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.StringSegmentImpl <em>String Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.StringSegmentImpl
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getStringSegment()
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
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.SubIdiomImpl <em>Sub Idiom</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.SubIdiomImpl
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getSubIdiom()
		 * @generated
		 */
		EClass SUB_IDIOM = eINSTANCE.getSubIdiom();

		/**
		 * The meta object literal for the '<em><b>Locator</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUB_IDIOM__LOCATOR = eINSTANCE.getSubIdiom_Locator();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUB_IDIOM__NAME = eINSTANCE.getSubIdiom_Name();

		/**
		 * The meta object literal for the '<em><b>Segments</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUB_IDIOM__SEGMENTS = eINSTANCE.getSubIdiom_Segments();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.ValueSegmentImpl <em>Value Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.ValueSegmentImpl
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getValueSegment()
		 * @generated
		 */
		EClass VALUE_SEGMENT = eINSTANCE.getValueSegment();

		/**
		 * The meta object literal for the '<em>Locator Helper</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRuleAnalysis.LocatorHelper
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getLocatorHelper()
		 * @generated
		 */
		EDataType LOCATOR_HELPER = eINSTANCE.getLocatorHelper();

		/**
		 * The meta object literal for the '<em>Serialization Builder</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationBuilder
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getSerializationBuilder()
		 * @generated
		 */
		EDataType SERIALIZATION_BUILDER = eINSTANCE.getSerializationBuilder();

		/**
		 * The meta object literal for the '<em>Serialization Step</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getSerializationStep()
		 * @generated
		 */
		EDataType SERIALIZATION_STEP = eINSTANCE.getSerializationStep();

		/**
		 * The meta object literal for the '<em>User Element Serializer</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.runtime.UserElementSerializer
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getUserElementSerializer()
		 * @generated
		 */
		EDataType USER_ELEMENT_SERIALIZER = eINSTANCE.getUserElementSerializer();

		/**
		 * The meta object literal for the '<em>Segment Helper</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.SegmentHelper
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getSegmentHelper()
		 * @generated
		 */
		EDataType SEGMENT_HELPER = eINSTANCE.getSegmentHelper();

	}

} //IdiomsPackage
