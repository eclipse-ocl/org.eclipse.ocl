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
 * A representation of the model object '<em><b>CG Accumulator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A CGAccumulator is a mutable possibly invalid variable for use as an accumulator.
 * <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGAccumulator()
 * @generated
 */
public interface CGAccumulator extends CGIterator {

	/**
	 * Set the non-invalid status.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$38
	void setNonInvalid(boolean nonInvalid);
} // CGAccumulator
