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
import org.eclipse.ocl.pivot.ids.TemplateParameterId;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Template Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A TemplateParameter exposes a ParameterableElement as a formal parameter of a template.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.TemplateParameter#getConstrainingClasses <em>Constraining Classes</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.TemplateParameter#getOwningTemplateableElement <em>Owning Templateable Element</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.pivot.PivotPackage#getTemplateParameter()
 * @generated
 */
public interface TemplateParameter extends Type {

	/**
	 * Returns the value of the '<em><b>Constraining Classes</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.pivot.Class}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraining Classes</em>' reference list.
	 * @see org.eclipse.ocl.pivot.PivotPackage#getTemplateParameter_ConstrainingClasses()
	 * @generated
	 */
	@NonNull List<org.eclipse.ocl.pivot.Class> getConstrainingClasses();

	/**
	 * Returns the value of the '<em><b>Owning Templateable Element</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.pivot.TemplateableElement#getOwnedTemplateParameters <em>Owned Template Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The TemplateSignature that owns this TemplateableElement.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owning Templateable Element</em>' container reference.
	 * @see #setOwningTemplateableElement(TemplateableElement)
	 * @see org.eclipse.ocl.pivot.PivotPackage#getTemplateParameter_OwningTemplateableElement()
	 * @see org.eclipse.ocl.pivot.TemplateableElement#getOwnedTemplateParameters
	 * @generated
	 */
	TemplateableElement getOwningTemplateableElement();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.TemplateParameter#getOwningTemplateableElement <em>Owning Templateable Element</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Templateable Element</em>' container reference.
	 * @see #getOwningTemplateableElement()
	 * @generated
	 */
	void setOwningTemplateableElement(TemplateableElement value);

	/**
	 * @since 7.0
	 */
	@Nullable List<org.eclipse.ocl.pivot.@NonNull Class> basicGetConstrainingClasses();

	@NonNull TemplateParameterId getTemplateParameterId();

	/**
	 * @since 7.0
	 */
	void resetConstrainingClasses();

} // TemplateParameter
