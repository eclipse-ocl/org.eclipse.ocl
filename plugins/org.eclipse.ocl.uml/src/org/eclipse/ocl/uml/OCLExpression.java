/*******************************************************************************
 * Copyright (c) 2007, 2018 IBM Corporation, Zeligsoft Inc., and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   Zeligsoft - Bug 207365
 *******************************************************************************/
package org.eclipse.ocl.uml;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.TypedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>OCL Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.ocl.uml.UMLPackage#getOCLExpression()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/uml2/2.0.0/UML originalName='OclExpression'"
 *        extendedMetaData="name='OclExpression'"
 * @generated
 */
public interface OCLExpression
		extends TypedElement,
		org.eclipse.ocl.expressions.OCLExpression<Classifier> {

	/**
	 * <!-- begin-user-doc -->
	 * @since 5.4
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean has_type(DiagnosticChain diagnostics, Map<Object, Object> context);
} // OCLExpression
