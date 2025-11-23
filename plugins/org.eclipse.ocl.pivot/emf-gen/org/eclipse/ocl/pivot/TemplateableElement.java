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
import org.eclipse.jdt.annotation.Nullable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Templateable Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A TemplateableElement is an Element that can optionally be defined as a specialization of a generic TemplateableElement.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.TemplateableElement#getGeneric <em>Generic</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.TemplateableElement#getOwnedTemplateArguments <em>Owned Template Arguments</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.TemplateableElement#getOwnedTemplateParameters <em>Owned Template Parameters</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.pivot.PivotPackage#getTemplateableElement()
 * @generated
 */
public interface TemplateableElement
		extends Element {

	/**
	 * Returns the value of the '<em><b>Owned Template Arguments</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.pivot.TemplateArgument}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.pivot.TemplateArgument#getOwningTemplateableElement <em>Owning Templateable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The optional TemplateBindings from this TemplateableElement to one or more templates.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Template Arguments</em>' containment reference list.
	 * @see org.eclipse.ocl.pivot.PivotPackage#getTemplateableElement_OwnedTemplateArguments()
	 * @see org.eclipse.ocl.pivot.TemplateArgument#getOwningTemplateableElement
	 * @generated
	 */
	List<TemplateArgument> getOwnedTemplateArguments();

	/**
	 * Returns the value of the '<em><b>Owned Template Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.pivot.TemplateParameter}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.pivot.TemplateParameter#getOwningTemplateableElement <em>Owning Templateable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The formal parameters that are owned by this TemplateableElement.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Template Parameters</em>' containment reference list.
	 * @see org.eclipse.ocl.pivot.PivotPackage#getTemplateableElement_OwnedTemplateParameters()
	 * @see org.eclipse.ocl.pivot.TemplateParameter#getOwningTemplateableElement
	 * @generated
	 */
	List<TemplateParameter> getOwnedTemplateParameters();

	/**
	 * Returns the value of the '<em><b>Generic</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unspecialized Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generic</em>' reference.
	 * @see #setGeneric(TemplateableElement)
	 * @see org.eclipse.ocl.pivot.PivotPackage#getTemplateableElement_Generic()
	 * @generated
	 */
	TemplateableElement getGeneric();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.TemplateableElement#getGeneric <em>Generic</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Generic</em>' reference.
	 * @see #getGeneric()
	 * @generated
	 */
	void setGeneric(TemplateableElement value);

	/**
	 * @since 7.0
	 */
	@Nullable List<@NonNull TemplateArgument> basicGetOwnedTemplateArguments();

	/**
	 * @since 7.0
	 */
	@Nullable List<@NonNull TemplateParameter> basicGetOwnedTemplateParameters();
} // TemplateableElement
