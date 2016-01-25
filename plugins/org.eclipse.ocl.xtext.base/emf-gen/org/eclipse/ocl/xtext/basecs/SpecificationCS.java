/*******************************************************************************
 * Copyright (c) 2011, 2014 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.basecs;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Specification CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.SpecificationCS#getExprString <em>Expr String</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage#getSpecificationCS()
 * @model
 * @generated
 */
public interface SpecificationCS extends ModelElementCS
{
	/**
	 * Returns the value of the '<em><b>Expr String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expr String</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expr String</em>' attribute.
	 * @see #setExprString(String)
	 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage#getSpecificationCS_ExprString()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://www.eclipse.org/ocl/2015/BaseCS!SpecificationCS!exprString'"
	 * @generated
	 */
	String getExprString();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.basecs.SpecificationCS#getExprString <em>Expr String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expr String</em>' attribute.
	 * @see #getExprString()
	 * @generated
	 */
	void setExprString(String value);

} // SpecificationCS
