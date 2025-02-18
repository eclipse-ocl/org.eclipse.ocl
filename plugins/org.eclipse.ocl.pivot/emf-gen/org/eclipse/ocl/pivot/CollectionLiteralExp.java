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
import org.eclipse.jdt.annotation.NonNull;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Collection Literal Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.CollectionLiteralExp#getKind <em>Kind</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.CollectionLiteralExp#getOwnedParts <em>Owned Parts</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.pivot.PivotPackage#getCollectionLiteralExp()
 * @generated
 */
public interface CollectionLiteralExp
		extends LiteralExp {

	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.ocl.pivot.CollectionKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see org.eclipse.ocl.pivot.CollectionKind
	 * @see #setKind(CollectionKind)
	 * @see org.eclipse.ocl.pivot.PivotPackage#getCollectionLiteralExp_Kind()
	 * @generated
	 */
	CollectionKind getKind();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.CollectionLiteralExp#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see org.eclipse.ocl.pivot.CollectionKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(CollectionKind value);

	/**
	 * Returns the value of the '<em><b>Owned Parts</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.pivot.CollectionLiteralPart}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Part</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Parts</em>' containment reference list.
	 * @see org.eclipse.ocl.pivot.PivotPackage#getCollectionLiteralExp_OwnedParts()
	 * @generated
	 */
	@NonNull List<CollectionLiteralPart> getOwnedParts();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 'Collection' is an abstract class on the M1 level and has no M0 instances.
	 * <!-- end-model-doc -->
	 * @generated
	 */
	boolean validateCollectionKindIsConcrete(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The type of a collection literal expression is determined by the collection kind selection and the common
	 * supertype of all elements. Note that the definition below defines only an upper bound on the elementType. The usage of
	 * the CollectionLiteralExp defines a lower bound. If the elementType is not explicitly specified, the elementType must be
	 * chosen to ensure the well-formedness of the elements of the CollectionLiteralExp and the usage of the
	 * CollectionLiteralExp.
	 *
	 * For instance in
	 * acc : Set(Real) = Set{1}->excluding(-1)
	 * Set{1} is well formed for any type Set(T) where T ≤ UnlimitedNatural. Well-formedness of the excluding operation call
	 * requires T ≤ Integer, and well-formedness of the initializer requires Real ≤ T. The overall expression is therefore only
	 * well-formed if Real ≤ T ≤ Integer. Either Set(Real) or Set(Integer) are well-formed. The most general type, Set(Real), is
	 * recommended since it minimizes type conversions and can often be easily deduced by considering the result type.
	 *
	 * <!-- end-model-doc -->
	 * @generated
	 */
	boolean validateSetKindIsSet(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	boolean validateOrderedSetKindIsOrderedSet(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	boolean validateSequenceKindIsSequence(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	boolean validateBagKindIsBag(DiagnosticChain diagnostics, Map<Object, Object> context);

} // CollectionLiteralExp
