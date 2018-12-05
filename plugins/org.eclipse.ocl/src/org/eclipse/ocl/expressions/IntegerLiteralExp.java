/*******************************************************************************
 * Copyright (c) 2006, 2018 IBM Corporation, Zeligsoft Inc., and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   IBM - Initial API and implementation
 *   Zeligsoft - Bug 207365
 *******************************************************************************/
package org.eclipse.ocl.expressions;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Integer Literal Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.expressions.IntegerLiteralExp#getIntegerSymbol <em>Integer Symbol</em>}</li>
 *   <li>{@link org.eclipse.ocl.expressions.IntegerLiteralExp#getLongSymbol <em>Long Symbol</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.expressions.ExpressionsPackage#getIntegerLiteralExp()
 * @model features="extendedIntegerSymbol" 
 *        extendedIntegerSymbolDefault="0" extendedIntegerSymbolDataType="org.eclipse.emf.ecore.ELongObject" extendedIntegerSymbolSuppressedGetVisibility="true" extendedIntegerSymbolSuppressedSetVisibility="true"
 * @generated
 */
public interface IntegerLiteralExp<C>
		extends NumericLiteralExp<C> {

	/**
	 * Returns the value of the '<em><b>Integer Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Integer Symbol</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Integer Symbol</em>' attribute.
	 * @see #setIntegerSymbol(Integer)
	 * @see org.eclipse.ocl.expressions.ExpressionsPackage#getIntegerLiteralExp_IntegerSymbol()
	 * @model
	 * @generated
	 */
	Integer getIntegerSymbol();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.expressions.IntegerLiteralExp#getIntegerSymbol <em>Integer Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Integer Symbol</em>' attribute.
	 * @see #getIntegerSymbol()
	 * @generated
	 */
	void setIntegerSymbol(Integer value);

	/**
	 * Returns the value of the '<em><b>Long Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Long Symbol</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * @since 3.2
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Long Symbol</em>' attribute.
	 * @see #setLongSymbol(Long)
	 * @see org.eclipse.ocl.expressions.ExpressionsPackage#getIntegerLiteralExp_LongSymbol()
	 * @model transient="true" volatile="true" derived="true"
	 * @generated
	 */
	Long getLongSymbol();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.expressions.IntegerLiteralExp#getLongSymbol <em>Long Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 3.2
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Long Symbol</em>' attribute.
	 * @see #getLongSymbol()
	 * @generated
	 */
	void setLongSymbol(Long value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * self.type.name = 'Integer'
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean checkIntegerType(DiagnosticChain diagnostics,
			Map<Object, Object> context);

} // IntegerLiteralExp
