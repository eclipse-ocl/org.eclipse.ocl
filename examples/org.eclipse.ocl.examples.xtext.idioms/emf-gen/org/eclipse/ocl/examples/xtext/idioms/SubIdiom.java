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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sub Idiom</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A SubIdiom defines the fomattimg to be applied at some position inthe Xtext grammar.
 * A Locator matches the grammar location, then a sequence of virtual text strings may
 * form an outer value that wraps the inner value of the location with virtual segments
 * such as soft-spaces or indentation pushes.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.SubIdiom#isAll <em>All</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.SubIdiom#getOwnedLocator <em>Owned Locator</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.SubIdiom#getOwnedSegments <em>Owned Segments</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.SubIdiom#getOwningIdiom <em>Owning Idiom</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getSubIdiom()
 * @model
 * @generated
 */
public interface SubIdiom
		extends IdiomsElement {

	/**
	 * Returns the value of the '<em><b>All</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the segments apply once to  serialization of all located elements, or repeatedly to each occurrence.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>All</em>' attribute.
	 * @see #setAll(boolean)
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getSubIdiom_All()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isAll();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.idioms.SubIdiom#isAll <em>All</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>All</em>' attribute.
	 * @see #isAll()
	 * @generated
	 */
	void setAll(boolean value);

	/**
	 * Returns the value of the '<em><b>Owned Locator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Locator</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Locator</em>' containment reference.
	 * @see #setOwnedLocator(Locator)
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getSubIdiom_OwnedLocator()
	 * @model containment="true"
	 * @generated
	 */
	Locator getOwnedLocator();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.idioms.SubIdiom#getOwnedLocator <em>Owned Locator</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owned Locator</em>' containment reference.
	 * @see #getOwnedLocator()
	 * @generated
	 */
	void setOwnedLocator(Locator value);

	/**
	 * Returns the value of the '<em><b>Owned Segments</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.xtext.idioms.Segment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Segments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Segments</em>' containment reference list.
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getSubIdiom_OwnedSegments()
	 * @model containment="true"
	 * @generated
	 */
	EList<Segment> getOwnedSegments();

	/**
	 * Returns the value of the '<em><b>Owning Idiom</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.examples.xtext.idioms.Idiom#getOwnedSubIdioms <em>Owned Sub Idioms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Idiom</em>' container reference.
	 * @see #setOwningIdiom(Idiom)
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getSubIdiom_OwningIdiom()
	 * @see org.eclipse.ocl.examples.xtext.idioms.Idiom#getOwnedSubIdioms
	 * @model opposite="ownedSubIdioms" required="true" transient="false"
	 * @generated
	 */
	Idiom getOwningIdiom();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.idioms.SubIdiom#getOwningIdiom <em>Owning Idiom</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Idiom</em>' container reference.
	 * @see #getOwningIdiom()
	 * @generated
	 */
	void setOwningIdiom(Idiom value);
} // SubIdiom
