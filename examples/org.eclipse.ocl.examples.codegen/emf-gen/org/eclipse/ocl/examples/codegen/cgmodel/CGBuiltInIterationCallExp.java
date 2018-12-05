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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CG While Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp#getAccumulator <em>Accumulator</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGBuiltInIterationCallExp()
 * @generated
 */
public interface CGBuiltInIterationCallExp extends CGIterationCallExp {
	/**
	 * Returns the value of the '<em><b>Accumulator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Accumulator</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Accumulator</em>' containment reference.
	 * @see #setAccumulator(CGIterator)
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGBuiltInIterationCallExp_Accumulator()
	 * @generated
	 */
	CGIterator getAccumulator();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp#getAccumulator <em>Accumulator</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Accumulator</em>' containment reference.
	 * @see #getAccumulator()
	 * @generated
	 */
	void setAccumulator(CGIterator value);

	/**
	 * Set the non-null status.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Nul$17
	void setNonNull();

} // CGWhileExp
