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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage
 * @generated
 */
public interface IdiomsFactory extends EFactory
{
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	IdiomsFactory eINSTANCE = org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Assignment Locator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Assignment Locator</em>'.
	 * @generated
	 */
	AssignmentLocator createAssignmentLocator();

	/**
	 * Returns a new object of class '<em>Custom Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Custom Segment</em>'.
	 * @generated
	 */
	CustomSegment createCustomSegment();

	/**
	 * Returns a new object of class '<em>Default Locator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Default Locator</em>'.
	 * @generated
	 */
	DefaultLocator createDefaultLocator();

	/**
	 * Returns a new object of class '<em>Half New Line Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Half New Line Segment</em>'.
	 * @generated
	 */
	HalfNewLineSegment createHalfNewLineSegment();

	/**
	 * Returns a new object of class '<em>Idiom</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Idiom</em>'.
	 * @generated
	 */
	Idiom createIdiom();

	/**
	 * Returns a new object of class '<em>Idiom Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Idiom Model</em>'.
	 * @generated
	 */
	IdiomModel createIdiomModel();

	/**
	 * Returns a new object of class '<em>Keyword Locator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Keyword Locator</em>'.
	 * @generated
	 */
	KeywordLocator createKeywordLocator();

	/**
	 * Returns a new object of class '<em>New Line Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>New Line Segment</em>'.
	 * @generated
	 */
	NewLineSegment createNewLineSegment();

	/**
	 * Returns a new object of class '<em>No Space Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>No Space Segment</em>'.
	 * @generated
	 */
	NoSpaceSegment createNoSpaceSegment();

	/**
	 * Returns a new object of class '<em>Pop Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Pop Segment</em>'.
	 * @generated
	 */
	PopSegment createPopSegment();

	/**
	 * Returns a new object of class '<em>Produced EClass Locator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Produced EClass Locator</em>'.
	 * @generated
	 */
	ProducedEClassLocator createProducedEClassLocator();

	/**
	 * Returns a new object of class '<em>Push Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Push Segment</em>'.
	 * @generated
	 */
	PushSegment createPushSegment();

	/**
	 * Returns a new object of class '<em>Soft New Line Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Soft New Line Segment</em>'.
	 * @generated
	 */
	SoftNewLineSegment createSoftNewLineSegment();

	/**
	 * Returns a new object of class '<em>Soft Space Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Soft Space Segment</em>'.
	 * @generated
	 */
	SoftSpaceSegment createSoftSpaceSegment();

	/**
	 * Returns a new object of class '<em>String Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>String Segment</em>'.
	 * @generated
	 */
	StringSegment createStringSegment();

	/**
	 * Returns a new object of class '<em>Sub Idiom</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sub Idiom</em>'.
	 * @generated
	 */
	SubIdiom createSubIdiom();

	/**
	 * Returns a new object of class '<em>Value Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Value Segment</em>'.
	 * @generated
	 */
	ValueSegment createValueSegment();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	IdiomsPackage getIdiomsPackage();

} //IdiomsFactory
