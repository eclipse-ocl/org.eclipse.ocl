/**
 * <copyright>
 *
 * Copyright (c) 2010 E.D.Willink and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: Iterator.java,v 1.1.2.2 2010/10/05 17:39:52 ewillink Exp $
 */
package org.eclipse.ocl.examples.pivot;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Iteration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.pivot.Iterator#getOwnedIterators <em>Owned Iterator</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.ocl.examples.pivot.PivotPackage#getIterator()
 * @model
 * @generated
 */
public interface Iterator
		extends Operation {

	/**
	 * Returns the value of the '<em><b>Owned Iterator</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.pivot.Parameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Iterator</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Iterator</em>' containment reference list.
	 * @see org.eclipse.ocl.examples.pivot.PivotPackage#getIterator_OwnedIterator()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Parameter> getOwnedIterators();

	/**
	 * Creates a new {@link org.eclipse.ocl.examples.pivot.Parameter} and appends it to the '<em><b>Owned Iterator</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return The new {@link org.eclipse.ocl.examples.pivot.Parameter}.
	 * @see #getOwnedIterators()
	 * @generated
	 */
	Parameter createOwnedIterator();

} // Iteration
