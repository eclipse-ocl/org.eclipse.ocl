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
 * A representation of the model object '<em><b>Idiom Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomModel#getIdioms <em>Idioms</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage#getIdiomModel()
 * @model
 * @generated
 */
public interface IdiomModel extends EObject
{
	/**
	 * Returns the value of the '<em><b>Idioms</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.xtext.base.cs2text.idioms.Idiom}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Idioms</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Idioms</em>' containment reference list.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage#getIdiomModel_Idioms()
	 * @model containment="true"
	 * @generated
	 */
	EList<Idiom> getIdioms();

} // IdiomModel
