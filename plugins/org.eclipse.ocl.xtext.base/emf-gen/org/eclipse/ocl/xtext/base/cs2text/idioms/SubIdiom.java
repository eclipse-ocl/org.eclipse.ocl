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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

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
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom#getLocator <em>Locator</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom#getSegments <em>Segments</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage#getSubIdiom()
 * @model
 * @generated
 */
public interface SubIdiom extends EObject
{
	/**
	 * Returns the value of the '<em><b>Locator</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Locator</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Locator</em>' reference.
	 * @see #setLocator(Locator)
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage#getSubIdiom_Locator()
	 * @model
	 * @generated
	 */
	Locator getLocator();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom#getLocator <em>Locator</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Locator</em>' reference.
	 * @see #getLocator()
	 * @generated
	 */
	void setLocator(Locator value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage#getSubIdiom_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Segments</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.xtext.base.cs2text.idioms.Segment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Segments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Segments</em>' reference list.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage#getSubIdiom_Segments()
	 * @model
	 * @generated
	 */
	EList<Segment> getSegments();
} // SubIdiom
