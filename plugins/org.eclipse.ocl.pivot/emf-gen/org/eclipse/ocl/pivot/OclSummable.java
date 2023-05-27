/**
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ocl Summable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The type OclSummable defines the sum and zero operations used by the Collection::sum iteration. Only types that provide derived
 * sum and zero implementations may be summed.
 * <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.ocl.pivot.PivotPackage#getOclSummable()
 * @generated
 */
public interface OclSummable extends EObject
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Return the sum of self and that.
	 *
	 * The sum operation should be associative.
	 * <!-- end-model-doc -->
	 * @generated
	 */
	SelfType sum(SelfType that);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Return the 'zero' value of self to initialize a summation.
	 *
	 * zero().sum(self) = self.
	 * <!-- end-model-doc -->
	 * @generated
	 */
	SelfType zero();

} // OclSummable
