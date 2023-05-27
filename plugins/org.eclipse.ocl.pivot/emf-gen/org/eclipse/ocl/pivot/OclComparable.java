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

import org.eclipse.ocl.pivot.values.IntegerValue;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ocl Comparable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The type OclComparable defines the compareTo operation used by the sortedBy iteration. Only types that provide a derived
 * compareTo implementation may be sorted.
 *
 * <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.ocl.pivot.PivotPackage#getOclComparable()
 * @generated
 */
public interface OclComparable extends EObject
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Return -ve, 0, +ve according to whether self is less than, equal to , or greater than that.
	 *
	 * The compareTo operation should be commutative.
	 * <!-- end-model-doc -->
	 * @generated
	 */
	IntegerValue compareTo(SelfType that);

} // OclComparable
