/*******************************************************************************
 * Copyright (c) 2008, 2018 Zeligsoft Inc. and others.
 *  All rights reserved.   This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *
 *  Contributors:
 *    Zeligsoft - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.uml.internal.operations;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.ocl.uml.OCLExpression;
import org.eclipse.ocl.utilities.Visitor;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.internal.operations.NamedElementOperations;

/**
 * <!-- begin-user-doc -->
 * A static utility class that provides operations related to '<em><b>OCL Expression</b></em>' model objects.
 * @since 2.0
 * <!-- end-user-doc -->
 *
 * <p>
 * The following operations are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.utilities.TypedElement#getName() <em>Get Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.utilities.TypedElement#getType() <em>Get Type</em>}</li>
 *   <li>{@link org.eclipse.ocl.utilities.TypedElement#setName(java.lang.String) <em>Set Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.utilities.TypedElement#setType(java.lang.Object) <em>Set Type</em>}</li>
 *   <li>{@link org.eclipse.ocl.utilities.Visitable#accept(org.eclipse.ocl.utilities.Visitor) <em>Accept</em>}</li>
 *   <li>{@link org.eclipse.ocl.uml.OCLExpression#has_type(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>Has type</em>}</li>
 * </ul>
 *
 * @generated
 */
@SuppressWarnings("restriction")
public class OCLExpressionOperations
		extends NamedElementOperations {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OCLExpressionOperations() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public static String getName(OCLExpression oclExpression) {
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public static Classifier getType(OCLExpression oclExpression) {
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public static void setName(OCLExpression oclExpression, String name) {
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public static void setType(OCLExpression oclExpression, Classifier type) {
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public static <T, U extends Visitor<T, ?, ?, ?, ?, ?, ?, ?, ?, ?>> T accept(
			OCLExpression oclExpression, U v) {
		throw new UnsupportedOperationException();
	}

	/**
	 * This dummy operation is a corollary of declaring has_type as a Constraint rather than
	 * an Ecore constraints EAnnotation in the UML model. It is not used. The manual implementation
	 * is in UMLValidator. See Bug 533128.
	 *
	 * @since 5.4
	 * @generated NOT
	 */
	public static boolean has_type(OCLExpression oclExpression,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

} // OCLExpressionOperations