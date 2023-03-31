/**
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.ocl;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Template Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.TemplateParameter#getConstrainingClasses <em>Constraining Classes</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.TemplateParameter#getOwningSignature <em>Owning Signature</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getTemplateParameter()
 * @generated
 */
public interface TemplateParameter extends Type
{
	/**
	 * Returns the value of the '<em><b>Constraining Classes</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Class}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraining Classes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraining Classes</em>' reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getTemplateParameter_ConstrainingClasses()
	 * @generated
	 */
	List<org.eclipse.ocl.ocl.Class> getConstrainingClasses();

	/**
	 * Returns the value of the '<em><b>Owning Signature</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.TemplateSignature#getOwnedParameters <em>Owned Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The TemplateSignature that owns this TemplateParameter.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owning Signature</em>' container reference.
	 * @see #setOwningSignature(TemplateSignature)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getTemplateParameter_OwningSignature()
	 * @see org.eclipse.ocl.ocl.TemplateSignature#getOwnedParameters
	 * @generated
	 */
	TemplateSignature getOwningSignature();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.TemplateParameter#getOwningSignature <em>Owning Signature</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Signature</em>' container reference.
	 * @see #getOwningSignature()
	 * @generated
	 */
	void setOwningSignature(TemplateSignature value);

} // TemplateParameter
