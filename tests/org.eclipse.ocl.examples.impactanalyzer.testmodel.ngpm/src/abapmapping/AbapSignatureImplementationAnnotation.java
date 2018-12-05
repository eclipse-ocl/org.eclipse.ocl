/*******************************************************************************
 * Copyright (c) 2009, 2018 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     SAP AG - initial API and implementation
 ******************************************************************************
 */
package abapmapping;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abap Signature Implementation Annotation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link abapmapping.AbapSignatureImplementationAnnotation#getKind <em>Kind</em>}</li>
 * </ul>
 * </p>
 *
 * @see abapmapping.AbapmappingPackage#getAbapSignatureImplementationAnnotation()
 * @model
 * @generated
 */
public interface AbapSignatureImplementationAnnotation extends EObject {
	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link abapmapping.AbapSignatureKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see abapmapping.AbapSignatureKind
	 * @see #setKind(AbapSignatureKind)
	 * @see abapmapping.AbapmappingPackage#getAbapSignatureImplementationAnnotation_Kind()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	AbapSignatureKind getKind();

	/**
	 * Sets the value of the '{@link abapmapping.AbapSignatureImplementationAnnotation#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see abapmapping.AbapSignatureKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(AbapSignatureKind value);

} // AbapSignatureImplementationAnnotation
