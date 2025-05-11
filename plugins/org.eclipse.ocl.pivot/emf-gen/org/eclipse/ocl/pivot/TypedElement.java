/*******************************************************************************
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.ids.TypeId;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Typed Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A TypedElement is a NamedElement that may have a Type specified for it.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.TypedElement#isIsMany <em>Is Many</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.pivot.PivotPackage#getTypedElement()
 * @generated
 */
public interface TypedElement extends NamedElement, TypeUsage {

	/**
	 * Returns the value of the '<em><b>Is Many</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Many</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Many</em>' attribute.
	 * @see org.eclipse.ocl.pivot.PivotPackage#getTypedElement_IsMany()
	 * @generated
	 */
	boolean isIsMany();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	boolean CompatibleBody(ValueSpecification bodySpecification);

	/**
	 * Return a unique StandardLibrary-independent TemplateParameter-independent identifier for this typed element.
	 *
	 * @since 1.18
	 */
	@Deprecated /* @deprecated no longer different to getTypeId() */
	default @NonNull TypeId getNormalizedTypeId() { return getTypeId(); }

	/**
	 * Return a unique StandardLibrary-independent identifier for this typed element.
	 */
	@NonNull TypeId getTypeId();
} // TypedElement
