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
package org.eclipse.ocl.codegen.cgmodel;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Iteration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CG Iteration Call Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.codegen.cgmodel.CGIterationCallExp#getReferredIteration <em>Referred Iteration</em>}</li>
 *   <li>{@link org.eclipse.ocl.codegen.cgmodel.CGIterationCallExp#getIterators <em>Iterators</em>}</li>
 *   <li>{@link org.eclipse.ocl.codegen.cgmodel.CGIterationCallExp#getBodies <em>Bodies</em>}</li>
 *   <li>{@link org.eclipse.ocl.codegen.cgmodel.CGIterationCallExp#getCoIterators <em>Co Iterators</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.codegen.cgmodel.CGModelPackage#getCGIterationCallExp()
 * @generated
 */
public interface CGIterationCallExp extends CGCallExp {
	/**
	 * Returns the value of the '<em><b>Referred Iteration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referred Iteration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referred Iteration</em>' attribute.
	 * @see #setReferredIteration(Iteration)
	 * @see org.eclipse.ocl.codegen.cgmodel.CGModelPackage#getCGIterationCallExp_ReferredIteration()
	 * @generated
	 */
	Iteration getReferredIteration();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.codegen.cgmodel.CGIterationCallExp#getReferredIteration <em>Referred Iteration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referred Iteration</em>' attribute.
	 * @see #getReferredIteration()
	 * @generated
	 */
	void setReferredIteration(Iteration value);

	/**
	 * Returns the value of the '<em><b>Iterators</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.codegen.cgmodel.CGIterator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Iterators</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Iterators</em>' containment reference list.
	 * @see org.eclipse.ocl.codegen.cgmodel.CGModelPackage#getCGIterationCallExp_Iterators()
	 * @generated
	 */
	@NonNull List<CGIterator> getIterators();

	/**
	 * Returns the value of the '<em><b>Bodies</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.codegen.cgmodel.CGValuedElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bodies</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bodies</em>' containment reference list.
	 * @see org.eclipse.ocl.codegen.cgmodel.CGModelPackage#getCGIterationCallExp_Bodies()
	 * @generated
	 */
	List<CGValuedElement> getBodies();

	/**
	 * Returns the value of the '<em><b>Co Iterators</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.codegen.cgmodel.CGIterator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Co Iterators</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Co Iterators</em>' containment reference list.
	 * @see org.eclipse.ocl.codegen.cgmodel.CGModelPackage#getCGIterationCallExp_CoIterators()
	 * @generated
	 */
	List<CGIterator> getCoIterators();

} // CGIterationCallExp
