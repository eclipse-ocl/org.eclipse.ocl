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

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Iterator Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.IteratorExp#getOwnedBody <em>Owned Body</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.pivot.PivotPackage#getIteratorExp()
 * @generated
 */
public interface IteratorExp extends LoopExp, ReferringElement
{

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
	 * @see org.eclipse.ocl.pivot.PivotPackage#getIteratorExp_OwnedBody()
	 * @generated
	 */
	OCLExpression getOwnedBody();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.IteratorExp#getOwnedBody <em>Owned Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owned Body</em>' containment reference.
	 * @see #getOwnedBody()
	 * @generated
	 */
	void setOwnedBody(OCLExpression value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	boolean validateClosureBodyTypeIsConformanttoIteratorType(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	boolean validateSortedByIteratorTypeIsComparable(DiagnosticChain diagnostics, Map<Object, Object> context);

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
	 * @generated
	 */
	boolean validateAnyHasOneIterator(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	boolean validateAnyTypeIsSourceElementType(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.3
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The result element type is the same as type of the body elements or element.
	 * <!-- end-model-doc -->
	 * @generated
	 */
	boolean validateClosureBodyElementTypeIsIteratorType(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	boolean validateAnyBodyTypeIsBoolean(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	boolean validateClosureHasOneIterator(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.4
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Each body element is assignable to the iterator.
	 * <!-- end-model-doc -->
	 * @generated
	 */
	boolean validateClosureResultElementTypeIsIteratorType(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The collection type for an OrderedSet or a Sequence source type is OrderedSet.
	 * For any other source the collection type is Set.
	 * <!-- end-model-doc -->
	 * @generated
	 */
	boolean validateClosureTypeIsUniqueCollection(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The element type is the flattened type of the body elements.
	 * <!-- end-model-doc -->
	 * @generated
	 */
	boolean validateCollectElementTypeIsFlattenedBodyType(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	boolean validateClosureSourceElementTypeIsBodyElementType(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The element type is the same as the source element type.
	 * <!-- end-model-doc -->
	 * @generated
	 */
	boolean validateClosureElementTypeIsSourceElementType(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The collection type for an OrderedSet or a Sequence type is a Sequence,
	 * the result type for any other collection type is a Bag.
	 *
	 * <!-- end-model-doc -->
	 * @generated
	 */
	boolean validateCollectTypeIsUnordered(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The collection type for an OrderedSet or a Sequence type is a Sequence, the result type for any other collection type is Bag.
	 * <!-- end-model-doc -->
	 * @generated
	 */
	boolean validateSortedByIsOrderedIfSourceIsOrdered(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The element type is the type of the body elements.
	 * <!-- end-model-doc -->
	 * @generated
	 */
	boolean validateSortedByElementTypeIsSourceElementType(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The type of each collection iterator variable must be the type of the elements of the source collection.
	 * <!-- end-model-doc -->
	 * @generated
	 */
	boolean validateIteratorTypeIsSourceElementType(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.6
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	default boolean validateIteratorTypeIsSourceKeyType(DiagnosticChain diagnostics, Map<Object, Object> context) { return true; }

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
} // IteratorExp
