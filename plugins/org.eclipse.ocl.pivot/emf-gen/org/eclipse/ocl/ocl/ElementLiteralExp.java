/**
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.ocl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element Literal Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.ElementLiteralExp#getReferredElement <em>Referred Element</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getElementLiteralExp()
 * @generated
 */
public interface ElementLiteralExp extends LiteralExp
{
	/**
	 * Returns the value of the '<em><b>Referred Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This is an Object to avoid injecting a confusing opposite into EObject 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Referred Element</em>' attribute.
	 * @see #setReferredElement(EObject)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getElementLiteralExp_ReferredElement()
	 * @generated
	 */
	EObject getReferredElement();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.ElementLiteralExp#getReferredElement <em>Referred Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referred Element</em>' attribute.
	 * @see #getReferredElement()
	 * @generated
	 */
	void setReferredElement(EObject value);

} // ElementLiteralExp
