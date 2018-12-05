/*******************************************************************************
 * Copyright (c) 2016, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
/**
 */
package org.eclipse.ocl.examples.xtext.tests.bug477283.b;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.examples.xtext.tests.bug477283.a.asub.ASub;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>B</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.bug477283.b.B#getRef <em>Ref</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.xtext.tests.bug477283.b.Bug477283BPackage#getB()
 * @model
 * @generated
 */
public interface B extends EObject {
	/**
	 * Returns the value of the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ref</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ref</em>' reference.
	 * @see #setRef(ASub)
	 * @see org.eclipse.ocl.examples.xtext.tests.bug477283.b.Bug477283BPackage#getB_Ref()
	 * @model
	 * @generated
	 */
	ASub getRef();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.tests.bug477283.b.B#getRef <em>Ref</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ref</em>' reference.
	 * @see #getRef()
	 * @generated
	 */
	void setRef(ASub value);

} // B
