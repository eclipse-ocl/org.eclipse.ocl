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
package org.eclipse.ocl.codegen.cgmodel;

import org.eclipse.ocl.pivot.ids.PartId;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CG Tuple Part Call Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.codegen.cgmodel.CGTuplePartCallExp#getAstPartId <em>Ast Part Id</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.codegen.cgmodel.CGModelPackage#getCGTuplePartCallExp()
 * @generated
 */
public interface CGTuplePartCallExp extends CGPropertyCallExp {
	/**
	 * Returns the value of the '<em><b>Ast Part Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ast Part Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ast Part Id</em>' attribute.
	 * @see #setAstPartId(PartId)
	 * @see org.eclipse.ocl.codegen.cgmodel.CGModelPackage#getCGTuplePartCallExp_AstPartId()
	 * @generated
	 */
	PartId getAstPartId();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.codegen.cgmodel.CGTuplePartCallExp#getAstPartId <em>Ast Part Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ast Part Id</em>' attribute.
	 * @see #getAstPartId()
	 * @generated
	 */
	void setAstPartId(PartId value);

} // CGTuplePartCallExp
