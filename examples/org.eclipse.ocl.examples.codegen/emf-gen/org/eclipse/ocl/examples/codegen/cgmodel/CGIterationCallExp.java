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
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp#getReferredIteration <em>Referred Iteration</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp#getIterators <em>Iterators</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp#getCoIterators <em>Co Iterators</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp#getBody <em>Body</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp#getAsIteration <em>As Iteration</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGIterationCallExp()
 * @generated
 */
public interface CGIterationCallExp extends CGSourcedCallExp {
	/**
	 * Returns the value of the '<em><b>Referred Iteration</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referred Iteration</em>' reference.
	 * @see #setReferredIteration(CGOperation)
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGIterationCallExp_ReferredIteration()
	 * @generated
	 */
	CGOperation getReferredIteration();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp#getReferredIteration <em>Referred Iteration</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referred Iteration</em>' reference.
	 * @see #getReferredIteration()
	 * @generated
	 */
	void setReferredIteration(CGOperation value);

	/**
	 * Returns the value of the '<em><b>As Iteration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>As Iteration</em>' attribute.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGIterationCallExp_AsIteration()
	 * @generated
	 */
	Iteration getAsIteration();

	/**
	 * Returns the value of the '<em><b>Iterators</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.codegen.cgmodel.CGIterator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Iterators</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Iterators</em>' containment reference list.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGIterationCallExp_Iterators()
	 * @generated
	 */
	@NonNull List<CGIterator> getIterators();

	/**
	 * Returns the value of the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Body</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body</em>' containment reference.
	 * @see #setBody(CGValuedElement)
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGIterationCallExp_Body()
	 * @generated
	 */
	CGValuedElement getBody();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp#getBody <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Body</em>' containment reference.
	 * @see #getBody()
	 * @generated
	 */
	void setBody(CGValuedElement value);

	/**
	 * Returns the value of the '<em><b>Co Iterators</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.codegen.cgmodel.CGIterator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Co Iterators</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Co Iterators</em>' containment reference list.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGIterationCallExp_CoIterators()
	 * @generated
	 */
	List<CGIterator> getCoIterators();

} // CGIterationCallExp
