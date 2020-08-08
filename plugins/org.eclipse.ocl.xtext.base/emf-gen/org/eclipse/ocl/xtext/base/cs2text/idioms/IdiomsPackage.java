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
 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsFactory
 * @model kind="package"
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
	int SEGMENT = 9;
	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.AbstractCommentSegmentImpl <em>Abstract Comment Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.AbstractCommentSegmentImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getAbstractCommentSegment()
	 * @generated
	 */
	int ABSTRACT_COMMENT_SEGMENT = 0;
	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.LocatorImpl <em>Locator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.LocatorImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getLocator()
	 * @generated
	 */
	int LOCATOR = 6;
	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.AssignmentLocatorImpl <em>Assignment Locator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.AssignmentLocatorImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getAssignmentLocator()
	 * @generated
	 */
	int ASSIGNMENT_LOCATOR = 1;
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
	int DEFAULT_LOCATOR = 3;
	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomImpl <em>Idiom</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getIdiom()
	 * @generated
	 */
	int IDIOM = 4;
	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomModelImpl <em>Idiom Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomModelImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getIdiomModel()
	 * @generated
	 */
	int IDIOM_MODEL = 5;
	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.KeywordLocatorImpl <em>Keyword Locator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.KeywordLocatorImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getKeywordLocator()
	 * @generated
	 */
	int KEYWORD_LOCATOR = 7;
	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.ProducedEClassLocatorImpl <em>Produced EClass Locator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.ProducedEClassLocatorImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getProducedEClassLocator()
	 * @generated
	 */
	int PRODUCED_ECLASS_LOCATOR = 8;
	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.StringSegmentImpl <em>String Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.StringSegmentImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getStringSegment()
	 * @generated
	 */
	int STRING_SEGMENT = 10;
	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.SubIdiomImpl <em>Sub Idiom</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.SubIdiomImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getSubIdiom()
	 * @generated
	 */
	int SUB_IDIOM = 11;
	/**
	 * The meta object id for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.ValueSegmentImpl <em>Value Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.ValueSegmentImpl
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getValueSegment()
	 * @generated
	 */
	int VALUE_SEGMENT = 12;
	/**
	 * The meta object id for the '<em>Basic Serialization Rule</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.elements.BasicSerializationRule
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getBasicSerializationRule()
	 * @generated
	 */
	int BASIC_SERIALIZATION_RULE = 13;
	/**
	 * The meta object id for the '<em>Idiom Match</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomMatch
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getIdiomMatch()
	 * @generated
	 */
	int IDIOM_MATCH = 14;
	/**
	 * The meta object id for the '<em>Serialization Builder</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getSerializationBuilder()
	 * @generated
	 */
	int SERIALIZATION_BUILDER = 15;
	/**
	 * The meta object id for the '<em>Serialization Node</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getSerializationNode()
	 * @generated
	 */
	int SERIALIZATION_NODE = 16;
	/**
	 * The meta object id for the '<em>User Element Serializer</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getUserElementSerializer()
	 * @generated
	 */
	int USER_ELEMENT_SERIALIZER = 17;


	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.AbstractCommentSegment <em>Abstract Comment Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Comment Segment</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.AbstractCommentSegment
	 * @generated
	 */
	EClass getAbstractCommentSegment();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.AbstractCommentSegment#getEpilogue <em>Epilogue</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Epilogue</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.AbstractCommentSegment#getEpilogue()
	 * @see #getAbstractCommentSegment()
	 * @generated
	 */
	EAttribute getAbstractCommentSegment_Epilogue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.AbstractCommentSegment#getIndentation <em>Indentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Indentation</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.AbstractCommentSegment#getIndentation()
	 * @see #getAbstractCommentSegment()
	 * @generated
	 */
	EAttribute getAbstractCommentSegment_Indentation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.AbstractCommentSegment#getPrologue <em>Prologue</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Prologue</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.AbstractCommentSegment#getPrologue()
	 * @see #getAbstractCommentSegment()
	 * @generated
	 */
	EAttribute getAbstractCommentSegment_Prologue();

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
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment <em>Custom Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Custom Segment</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment
	 * @generated
	 */
	EClass getCustomSegment();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment#getDelegate <em>Delegate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Delegate</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment#getDelegate()
	 * @see #getCustomSegment()
	 * @generated
	 */
	EReference getCustomSegment_Delegate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment#getDelegateClass <em>Delegate Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Delegate Class</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment#getDelegateClass()
	 * @see #getCustomSegment()
	 * @generated
	 */
	EAttribute getCustomSegment_DelegateClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment#getString <em>String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>String</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment#getString()
	 * @see #getCustomSegment()
	 * @generated
	 */
	EAttribute getCustomSegment_String();

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
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.Idiom <em>Idiom</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Idiom</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.Idiom
	 * @generated
	 */
	EClass getIdiom();

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
	 * Returns the meta object for class '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.Segment <em>Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Segment</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.Segment
	 * @generated
	 */
	EClass getSegment();

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
	 * Returns the meta object for data type '{@link org.eclipse.ocl.xtext.base.cs2text.elements.BasicSerializationRule <em>Basic Serialization Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Basic Serialization Rule</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.elements.BasicSerializationRule
	 * @model instanceClass="org.eclipse.ocl.xtext.base.cs2text.elements.BasicSerializationRule" serializeable="false"
	 * @generated
	 */
	EDataType getBasicSerializationRule();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomMatch <em>Idiom Match</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Idiom Match</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomMatch
	 * @model instanceClass="org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomMatch" serializeable="false"
	 * @generated
	 */
	EDataType getIdiomMatch();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder <em>Serialization Builder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Serialization Builder</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder
	 * @model instanceClass="org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder" serializeable="false"
	 * @generated
	 */
	EDataType getSerializationBuilder();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode <em>Serialization Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Serialization Node</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode
	 * @model instanceClass="org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode"
	 * @generated
	 */
	EDataType getSerializationNode();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer <em>User Element Serializer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>User Element Serializer</em>'.
	 * @see org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer
	 * @model instanceClass="org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer" serializeable="false"
	 * @generated
	 */
	EDataType getUserElementSerializer();

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
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.AbstractCommentSegmentImpl <em>Abstract Comment Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.AbstractCommentSegmentImpl
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getAbstractCommentSegment()
		 * @generated
		 */
		EClass ABSTRACT_COMMENT_SEGMENT = eINSTANCE.getAbstractCommentSegment();

		/**
		 * The meta object literal for the '<em><b>Epilogue</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_COMMENT_SEGMENT__EPILOGUE = eINSTANCE.getAbstractCommentSegment_Epilogue();

		/**
		 * The meta object literal for the '<em><b>Indentation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_COMMENT_SEGMENT__INDENTATION = eINSTANCE.getAbstractCommentSegment_Indentation();

		/**
		 * The meta object literal for the '<em><b>Prologue</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_COMMENT_SEGMENT__PROLOGUE = eINSTANCE.getAbstractCommentSegment_Prologue();

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
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.CustomSegmentImpl <em>Custom Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.CustomSegmentImpl
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getCustomSegment()
		 * @generated
		 */
		EClass CUSTOM_SEGMENT = eINSTANCE.getCustomSegment();

		/**
		 * The meta object literal for the '<em><b>Delegate</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUSTOM_SEGMENT__DELEGATE = eINSTANCE.getCustomSegment_Delegate();

		/**
		 * The meta object literal for the '<em><b>Delegate Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOM_SEGMENT__DELEGATE_CLASS = eINSTANCE.getCustomSegment_DelegateClass();

		/**
		 * The meta object literal for the '<em><b>String</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOM_SEGMENT__STRING = eINSTANCE.getCustomSegment_String();

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
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomImpl <em>Idiom</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomImpl
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getIdiom()
		 * @generated
		 */
		EClass IDIOM = eINSTANCE.getIdiom();

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
		 * The meta object literal for the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.SegmentImpl <em>Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.SegmentImpl
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getSegment()
		 * @generated
		 */
		EClass SEGMENT = eINSTANCE.getSegment();

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
		 * The meta object literal for the '<em>Basic Serialization Rule</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.elements.BasicSerializationRule
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getBasicSerializationRule()
		 * @generated
		 */
		EDataType BASIC_SERIALIZATION_RULE = eINSTANCE.getBasicSerializationRule();

		/**
		 * The meta object literal for the '<em>Idiom Match</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomMatch
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getIdiomMatch()
		 * @generated
		 */
		EDataType IDIOM_MATCH = eINSTANCE.getIdiomMatch();

		/**
		 * The meta object literal for the '<em>Serialization Builder</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getSerializationBuilder()
		 * @generated
		 */
		EDataType SERIALIZATION_BUILDER = eINSTANCE.getSerializationBuilder();

		/**
		 * The meta object literal for the '<em>Serialization Node</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getSerializationNode()
		 * @generated
		 */
		EDataType SERIALIZATION_NODE = eINSTANCE.getSerializationNode();

		/**
		 * The meta object literal for the '<em>User Element Serializer</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer
		 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsPackageImpl#getUserElementSerializer()
		 * @generated
		 */
		EDataType USER_ELEMENT_SERIALIZER = eINSTANCE.getUserElementSerializer();

	}

} //IdiomsPackage
