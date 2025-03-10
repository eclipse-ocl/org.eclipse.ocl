/*******************************************************************************
 * Copyright (c) 2010, 2025 Willink Transformations and others.
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
import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Iterate Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.IterateExp#getOwnedBodies <em>Owned Bodies</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.IterateExp#getOwnedBody <em>Owned Body</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.IterateExp#getOwnedResult <em>Owned Result</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.pivot.PivotPackage#getIterateExp()
 * @generated
 */
public interface IterateExp extends LoopExp, ReferringElement
{
	/**
	 * Returns the value of the '<em><b>Owned Bodies</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.pivot.OCLExpression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Bodies</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Bodies</em>' containment reference list.
	 * @see org.eclipse.ocl.pivot.PivotPackage#getIterateExp_OwnedBodies()
	 * @generated
	 */
	List<OCLExpression> getOwnedBodies();

	/**
	 * Returns the value of the '<em><b>Owned Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Body</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Body</em>' containment reference.
	 * @see #setOwnedBody(OCLExpression)
	 * @see org.eclipse.ocl.pivot.PivotPackage#getIterateExp_OwnedBody()
	 * @generated
	 */
	OCLExpression getOwnedBody();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.IterateExp#getOwnedBody <em>Owned Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owned Body</em>' containment reference.
	 * @see #getOwnedBody()
	 * @generated
	 */
	void setOwnedBody(OCLExpression value);

	/**
	 * Returns the value of the '<em><b>Owned Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Result</em>' containment reference.
	 * @see #setOwnedResult(Variable)
	 * @see org.eclipse.ocl.pivot.PivotPackage#getIterateExp_OwnedResult()
	 * @generated
	 */
	Variable getOwnedResult();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.IterateExp#getOwnedResult <em>Owned Result</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owned Result</em>' containment reference.
	 * @see #getOwnedResult()
	 * @generated
	 */
	void setOwnedResult(Variable value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The type of the iterate is the type of the result variable.
	 * <!-- end-model-doc -->
	 * @generated
	 */
	boolean validateTypeIsResultType(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Safe navigation is necessary when an iterator cannot be null and the source collection is not null-free.
	 * <!-- end-model-doc -->
	 * @generated
	 */
	boolean validateUnsafeSourceCanNotBeNull(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The type of the body expression must conform to the declared type of the result variable.
	 * <!-- end-model-doc -->
	 * @generated
	 */
	boolean validateBodyTypeConformsToResultType(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A result variable must have an init expression.
	 * <!-- end-model-doc -->
	 * @generated
	 */
	boolean validateOneInitializer(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Safe navigation is not necessary when an iterator can be null.
	 * <!-- end-model-doc -->
	 * @generated
	 */
	boolean validateSafeIteratorIsRequired(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Safe navigation is not necessary when the source collection is null-free.
	 * <!-- end-model-doc -->
	 * @generated
	 */
	@Override
	boolean validateSafeSourceCanBeNull(DiagnosticChain diagnostics, Map<Object, Object> context);

} // IterateExp
