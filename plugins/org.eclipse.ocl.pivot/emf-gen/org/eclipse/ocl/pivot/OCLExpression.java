/*******************************************************************************
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ocl Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.OCLExpression#getTypeValue <em>Type Value</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.pivot.PivotPackage#getOCLExpression()
 * @generated
 */
public interface OCLExpression extends TypedElement {

	/**
	 * Returns the value of the '<em><b>Type Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Value</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * FIXME Bug 577902 Generalize to knownValue such as an EnnumerationLiteral or Integer
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * When oclType() returns a Class value with a known actual type, the typeValue propagates the known type.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type Value</em>' reference.
	 * @see #setTypeValue(Type)
	 * @see org.eclipse.ocl.pivot.PivotPackage#getOCLExpression_TypeValue()
	 * @generated
	 */
	Type getTypeValue();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.OCLExpression#getTypeValue <em>Type Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Value</em>' reference.
	 * @see #getTypeValue()
	 * @generated
	 */
	void setTypeValue(Type value);

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.3
	 * <!-- end-user-doc -->
	 * @generated
	 */
	boolean isNonNull();

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.3
	 * <!-- end-user-doc -->
	 * @generated
	 */
	boolean isNull();

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.3
	 * <!-- end-user-doc -->
	 * @generated
	 */
	boolean validateTypeIsNotNull(DiagnosticChain diagnostics, Map<Object, Object> context);

} // OCLExpression

