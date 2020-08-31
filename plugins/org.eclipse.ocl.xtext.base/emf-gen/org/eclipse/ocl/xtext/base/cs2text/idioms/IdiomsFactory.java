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
import org.eclipse.jdt.annotation.NonNull;

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
	@NonNull IdiomsFactory eINSTANCE = org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Assignment Locator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Assignment Locator</em>'.
	 * @generated
	 */
	@NonNull AssignmentLocator createAssignmentLocator();

	/**
	 * Returns a new object of class '<em>Custom Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Custom Segment</em>'.
	 * @generated
	 */
	@NonNull CustomSegment createCustomSegment();

	/**
	 * Returns a new object of class '<em>Default Locator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Default Locator</em>'.
	 * @generated
	 */
	@NonNull DefaultLocator createDefaultLocator();

	/**
	 * Returns a new object of class '<em>Half New Line Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Half New Line Segment</em>'.
	 * @generated
	 */
	@NonNull HalfNewLineSegment createHalfNewLineSegment();

	/**
	 * Returns a new object of class '<em>Idiom</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Idiom</em>'.
	 * @generated
	 */
	@NonNull Idiom createIdiom();

	/**
	 * Returns a new object of class '<em>Idiom Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Idiom Model</em>'.
	 * @generated
	 */
	@NonNull IdiomModel createIdiomModel();

	/**
	 * Returns a new object of class '<em>Keyword Locator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Keyword Locator</em>'.
	 * @generated
	 */
	@NonNull KeywordLocator createKeywordLocator();

	/**
	 * Returns a new object of class '<em>New Line Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>New Line Segment</em>'.
	 * @generated
	 */
	@NonNull NewLineSegment createNewLineSegment();

	/**
	 * Returns a new object of class '<em>No Space Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>No Space Segment</em>'.
	 * @generated
	 */
	@NonNull NoSpaceSegment createNoSpaceSegment();

	/**
	 * Returns a new object of class '<em>Pop Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Pop Segment</em>'.
	 * @generated
	 */
	@NonNull PopSegment createPopSegment();

	/**
	 * Returns a new object of class '<em>Produced EClass Locator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Produced EClass Locator</em>'.
	 * @generated
	 */
	@NonNull ProducedEClassLocator createProducedEClassLocator();

	/**
	 * Returns a new object of class '<em>Push Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Push Segment</em>'.
	 * @generated
	 */
	@NonNull PushSegment createPushSegment();

	/**
	 * Returns a new object of class '<em>Soft New Line Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Soft New Line Segment</em>'.
	 * @generated
	 */
	@NonNull SoftNewLineSegment createSoftNewLineSegment();

	/**
	 * Returns a new object of class '<em>Soft Space Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Soft Space Segment</em>'.
	 * @generated
	 */
	@NonNull SoftSpaceSegment createSoftSpaceSegment();

	/**
	 * Returns a new object of class '<em>String Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>String Segment</em>'.
	 * @generated
	 */
	@NonNull StringSegment createStringSegment();

	/**
	 * Returns a new object of class '<em>Sub Idiom</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sub Idiom</em>'.
	 * @generated
	 */
	@NonNull SubIdiom createSubIdiom();

	/**
	 * Returns a new object of class '<em>Value Segment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Value Segment</em>'.
	 * @generated
	 */
	@NonNull ValueSegment createValueSegment();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	@NonNull IdiomsPackage getIdiomsPackage();

} //IdiomsFactory
