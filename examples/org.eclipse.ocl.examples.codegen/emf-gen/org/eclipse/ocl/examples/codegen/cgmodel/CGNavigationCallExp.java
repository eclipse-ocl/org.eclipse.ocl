/*******************************************************************************
 * Copyright (c) 2013, 2018 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.cgmodel;

import org.eclipse.ocl.pivot.Property;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CG Navigation Call Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp#getReferredProperty <em>Referred Property</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp#getCgProperty <em>Cg Property</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGNavigationCallExp()
 * @generated
 */
public interface CGNavigationCallExp extends CGSourcedCallExp {
	/**
	 * Returns the value of the '<em><b>Referred Property</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referred Property</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referred Property</em>' attribute.
	 * @see #setReferredProperty(Property)
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGNavigationCallExp_ReferredProperty()
	 * @generated
	 */
	Property getReferredProperty();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp#getReferredProperty <em>Referred Property</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referred Property</em>' attribute.
	 * @see #getReferredProperty()
	 * @generated
	 */
	void setReferredProperty(Property value);

	/**
	 * Returns the value of the '<em><b>Cg Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The this expression for the call when invoking a native or ecore implementation.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cg Property</em>' reference.
	 * @see #setCgProperty(CGProperty)
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGNavigationCallExp_CgProperty()
	 * @generated
	 */
	CGProperty getCgProperty();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp#getCgProperty <em>Cg Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cg Property</em>' reference.
	 * @see #getCgProperty()
	 * @generated
	 */
	void setCgProperty(CGProperty value);

} // CGNavigationCallExp
