/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Iteration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.Iteration#getOwnedAccumulator <em>Owned Accumulator</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.Iteration#getOwnedIterators <em>Owned Iterators</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.pivot.PivotPackage#getIteration()
 * @generated
 */
public interface Iteration extends Operation
{
	/**
	 * Returns the value of the '<em><b>Owned Accumulator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Accumulator</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Accumulator</em>' containment reference.
	 * @see #setOwnedAccumulator(Parameter)
	 * @see org.eclipse.ocl.pivot.PivotPackage#getIteration_OwnedAccumulator()
	 * @generated
	 */
	Parameter getOwnedAccumulator();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.Iteration#getOwnedAccumulator <em>Owned Accumulator</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owned Accumulator</em>' containment reference.
	 * @see #getOwnedAccumulator()
	 * @generated
	 */
	void setOwnedAccumulator(Parameter value);

	/**
	 * Returns the value of the '<em><b>Owned Iterators</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.pivot.Parameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Iterator</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Iterators</em>' containment reference list.
	 * @see org.eclipse.ocl.pivot.PivotPackage#getIteration_OwnedIterators()
	 * @generated
	 */
	@NonNull List<Parameter> getOwnedIterators();

} // Iteration
