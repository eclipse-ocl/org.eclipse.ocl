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

import org.eclipse.ocl.pivot.Element;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CG Named Element</b></em>'.
 * @extends org.eclipse.ocl.pivot.utilities.Nameable
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement#getAst <em>Ast</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGNamedElement()
 * @generated
 */
public interface CGNamedElement extends CGElement, org.eclipse.ocl.pivot.utilities.Nameable {
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
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGNamedElement_Name()
	 * @generated
	 */
	@Override
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Ast</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ast</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ast</em>' attribute.
	 * @see #setAst(Element)
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGNamedElement_Ast()
	 * @generated
	 */
	Element getAst();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement#getAst <em>Ast</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ast</em>' attribute.
	 * @see #getAst()
	 * @generated
	 */
	void setAst(Element value);

} // CGNamedElement
