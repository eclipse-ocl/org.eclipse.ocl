/**
 * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot;

import java.util.Map;
import org.eclipse.emf.common.util.DiagnosticChain;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Iterator Variable</b></em>'.
 * @since 1.3
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.IteratorVariable#getSpecializedIterator <em>Specialized Iterator</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.pivot.PivotPackage#getIteratorVariable()
 * @generated
 */
public interface IteratorVariable extends Variable
{

	/**
	 * Returns the value of the '<em><b>Specialized Iterator</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specialization of this iterator using the calling context types for use in conjunction with the parent CallExp's ownedSpecializedBody.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Specialized Iterator</em>' reference.
	 * @see #setSpecializedIterator(IteratorVariable)
	 * @see org.eclipse.ocl.pivot.PivotPackage#getIteratorVariable_SpecializedIterator()
	 * @generated
	 */
	IteratorVariable getSpecializedIterator();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.IteratorVariable#getSpecializedIterator <em>Specialized Iterator</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Specialized Iterator</em>' reference.
	 * @see #getSpecializedIterator()
	 * @generated
	 */
	void setSpecializedIterator(IteratorVariable value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Iterator variable has no initializer.
	 * <!-- end-model-doc -->
	 * @generated
	 */
	boolean validateHasNoInitializer(DiagnosticChain diagnostics, Map<Object, Object> context);
} // IteratorVariable
