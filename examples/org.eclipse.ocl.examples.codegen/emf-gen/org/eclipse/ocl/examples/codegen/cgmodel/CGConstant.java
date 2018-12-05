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

import org.eclipse.jdt.annotation.NonNull;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CG Global Constant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A CGConstant represents a constant value that may be shared by may CGConstantExp. The shared
 * value may be generated as a global constant. Inlineable constants need no global declaration.
 * 
 * Derived classes support distinct forms of constants.
 * <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGConstant()
 * @generated
 */
public interface CGConstant extends CGValuedElement {

	/**
	 * Return the constant (boxed) value of this element.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$1
	@NonNull Object getConstantValue();
} // CGGlobalConstant
