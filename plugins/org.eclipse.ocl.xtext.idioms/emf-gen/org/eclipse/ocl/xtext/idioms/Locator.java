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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.xtext.AbstractRule;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Locator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Locator identifies where a SubIdiom applies Segment formatting.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.idioms.Locator#getOwningSubIdiom <em>Owning Sub Idiom</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.xtext.idioms.IdiomsPackage#getLocator()
 * @model abstract="true"
 * @generated
 */
public interface Locator extends IdiomsElement {

	/**
	 * Returns the value of the '<em><b>Owning Sub Idiom</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.xtext.idioms.SubIdiom#getOwnedLocator <em>Owned Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owning Sub Idiom</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Sub Idiom</em>' container reference.
	 * @see #setOwningSubIdiom(SubIdiom)
	 * @see org.eclipse.ocl.xtext.idioms.IdiomsPackage#getLocator_OwningSubIdiom()
	 * @see org.eclipse.ocl.xtext.idioms.SubIdiom#getOwnedLocator
	 * @model opposite="ownedLocator" transient="false"
	 * @generated
	 */
	SubIdiom getOwningSubIdiom();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.idioms.Locator#getOwningSubIdiom <em>Owning Sub Idiom</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Sub Idiom</em>' container reference.
	 * @see #getOwningSubIdiom()
	 * @generated
	 */
	void setOwningSubIdiom(SubIdiom value);

	default boolean covers(@NonNull String string) {
		return false;
	}

	default boolean covers(@NonNull AbstractRule rule) {
		return false;
	}
} // Locator
