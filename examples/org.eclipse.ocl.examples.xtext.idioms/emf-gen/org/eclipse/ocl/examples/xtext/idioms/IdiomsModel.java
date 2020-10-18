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
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

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
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.IdiomsModel#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.IdiomsModel#getNames <em>Names</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.IdiomsModel#getOwnedIdioms <em>Owned Idioms</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.IdiomsModel#getOwnedImports <em>Owned Imports</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.IdiomsModel#getOwnedLocatorDeclarations <em>Owned Locator Declarations</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.IdiomsModel#getOwnedSegmentDeclarations <em>Owned Segment Declarations</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.IdiomsModel#getOwnedWiths <em>Owned Withs</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getIdiomsModel()
 * @model
 * @generated
 */
public interface IdiomsModel
		extends IdiomsElement {

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
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getIdiomsModel_Name()
	 * @model required="true" changeable="false" derived="true"
	 * @generated
	 */
	String getName();

	/**
	 * Returns the value of the '<em><b>Names</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A convenience name for the IdiomModel.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Names</em>' attribute list.
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getIdiomsModel_Names()
	 * @model required="true"
	 * @generated
	 */
	EList<String> getNames();

	/**
	 * Returns the value of the '<em><b>Owned Imports</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.xtext.idioms.EPackageImport}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The IdiomModels for extended grammars.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Imports</em>' containment reference list.
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getIdiomsModel_OwnedImports()
	 * @model containment="true"
	 * @generated
	 */
	EList<EPackageImport> getOwnedImports();

	/**
	 * Returns the value of the '<em><b>Owned Locator Declarations</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.xtext.idioms.LocatorDeclaration}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.examples.xtext.idioms.LocatorDeclaration#getOwningIdiomsModel <em>Owning Idioms Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The locators that SubIdioms use to anchor themselves to grammar terms.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Locator Declarations</em>' containment reference list.
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getIdiomsModel_OwnedLocatorDeclarations()
	 * @see org.eclipse.ocl.examples.xtext.idioms.LocatorDeclaration#getOwningIdiomsModel
	 * @model opposite="owningIdiomsModel" containment="true"
	 * @generated
	 */
	EList<LocatorDeclaration> getOwnedLocatorDeclarations();

	/**
	 * Returns the value of the '<em><b>Owned Segment Declarations</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.xtext.idioms.SegmentDeclaration}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.examples.xtext.idioms.SegmentDeclaration#getOwningIdiomsModel <em>Owning Idioms Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The segments that a SubIdiom may use to format an inner value as its outer value.
	 * BEWARE due to an EMF bug, the same segment cannot be used more than once per SubIdiom;
	 * it may therefore be neccessary to format an inner value as SoftSpaceA+Value+SoftSpaceB
	 * rather than SoftSpace+Value+SoftSpace.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Segment Declarations</em>' containment reference list.
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getIdiomsModel_OwnedSegmentDeclarations()
	 * @see org.eclipse.ocl.examples.xtext.idioms.SegmentDeclaration#getOwningIdiomsModel
	 * @model opposite="owningIdiomsModel" containment="true"
	 * @generated
	 */
	EList<SegmentDeclaration> getOwnedSegmentDeclarations();

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
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getIdiomsModel_OwnedIdioms()
	 * @model containment="true"
	 * @generated
	 */
	EList<Idiom> getOwnedIdioms();

	/**
	 * Returns the value of the '<em><b>Owned Withs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.xtext.idioms.IdiomsImport}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The IdiomModels for extended grammars.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Withs</em>' containment reference list.
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getIdiomsModel_OwnedWiths()
	 * @model containment="true"
	 * @generated
	 */
	EList<IdiomsImport> getOwnedWiths();

	@Nullable
	LocatorDeclaration getOwnedLocator(@Nullable String name);

	@Nullable
	SegmentDeclaration getOwnedSegment(@Nullable String name);

	@Nullable
	IdiomsModel getIdiomsModel(@Nullable String name);

	//	@NonNull Map<@NonNull String, @Nullable IdiomsModel> getName2idiomsModel();

	@NonNull
	Iterable<@NonNull IdiomsModel> getIdiomsModels();

} // IdiomModel
