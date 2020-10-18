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
 * A representation of the model object '<em><b>Group Locator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A CompoundLocator identifies the smllest compound element composing the locators for the member elements.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.CompoundLocator#getOwnedLocators <em>Owned Locators</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getCompoundLocator()
 * @model
 * @generated
 */
public interface CompoundLocator
		extends Locator {

	/**
	 * Returns the value of the '<em><b>Owned Locators</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.xtext.idioms.Locator}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Locators</em>' containment reference list.
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getCompoundLocator_OwnedLocators()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Locator> getOwnedLocators();

} // CompoundLocator
