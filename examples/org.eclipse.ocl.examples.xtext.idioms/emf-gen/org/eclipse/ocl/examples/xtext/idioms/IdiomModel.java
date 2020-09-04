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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Idiom Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The IdiomModel prpvodes the root of the Idiom Model configuring an Xtext grammar and importing
 * IdiomModels for other used Xtext grammars. Locators and Segemnts are shared by the Idioms.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.IdiomModel#getImports <em>Imports</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.IdiomModel#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.IdiomModel#getOwnedIdioms <em>Owned Idioms</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.IdiomModel#getOwnedLocators <em>Owned Locators</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.IdiomModel#getOwnedSegments <em>Owned Segments</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getIdiomModel()
 * @model
 * @generated
 */
public interface IdiomModel extends EObject
{
	/**
	 * Returns the value of the '<em><b>Imports</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.xtext.idioms.IdiomModel}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Imports</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The IdiomModels for extended grammars.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Imports</em>' reference list.
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getIdiomModel_Imports()
	 * @model
	 * @generated
	 */
	EList<IdiomModel> getImports();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A convenience name for the IdiomModel.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getIdiomModel_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.idioms.IdiomModel#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Owned Idioms</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.xtext.idioms.Idiom}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Idioms</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Idioms to be applied in lowest priority first order; an earlier matching idiom
	 * inhibits the formatting of a later/imported SubIdiom's non-empty segements.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Idioms</em>' containment reference list.
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getIdiomModel_OwnedIdioms()
	 * @model containment="true"
	 * @generated
	 */
	EList<Idiom> getOwnedIdioms();

	/**
	 * Returns the value of the '<em><b>Owned Locators</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.xtext.idioms.Locator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Locators</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The locators that SubIdioms use to anchor themselves to grammar terms.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Locators</em>' containment reference list.
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getIdiomModel_OwnedLocators()
	 * @model containment="true"
	 * @generated
	 */
	EList<Locator> getOwnedLocators();

	/**
	 * Returns the value of the '<em><b>Owned Segments</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.xtext.idioms.Segment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Segments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The segments that a SubIdiom may use to format an inner value as its outer value.
	 * BEWARE due to an EMF bug, the same segment cannot be used more than once per SubIdiom;
	 * it may therefore be neccessary to format an inner value as SoftSpaceA+Value+SoftSpaceB
	 * rather than SoftSpace+Value+SoftSpace.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Segments</em>' containment reference list.
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getIdiomModel_OwnedSegments()
	 * @model containment="true"
	 * @generated
	 */
	EList<Segment> getOwnedSegments();

} // IdiomModel
