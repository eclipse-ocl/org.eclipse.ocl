/*******************************************************************************
 * Copyright (c) 2010, 2014 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.xtext.basecs;

import org.eclipse.emf.common.util.EList;
import org.eclipse.ocl.pivot.Property;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.ReferenceCS#getReferredKeys <em>Referred Keys</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.ReferenceCS#getReferredOpposite <em>Referred Opposite</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage#getReferenceCS()
 * @model
 * @generated
 */
public interface ReferenceCS extends StructuralFeatureCS {
	/**
	 * Returns the value of the '<em><b>Referred Opposite</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Opposite</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referred Opposite</em>' reference.
	 * @see #setReferredOpposite(Property)
	 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage#getReferenceCS_ReferredOpposite()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://www.eclipse.org/ocl/2015/BaseCS!ReferenceCS!referredOpposite'"
	 * @generated
	 */
	Property getReferredOpposite();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.basecs.ReferenceCS#getReferredOpposite <em>Referred Opposite</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referred Opposite</em>' reference.
	 * @see #getReferredOpposite()
	 * @generated
	 */
	void setReferredOpposite(Property value);

	/**
	 * Returns the value of the '<em><b>Referred Keys</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.pivot.Property}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Keys</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referred Keys</em>' reference list.
	 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage#getReferenceCS_ReferredKeys()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://www.eclipse.org/ocl/2015/BaseCS!ReferenceCS!referredKeys'"
	 * @generated
	 */
	EList<Property> getReferredKeys();

} // ReferenceCS
