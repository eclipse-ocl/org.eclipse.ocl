/**
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.examples.xtext.idioms;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Import</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An IdiomsImport supports import of an (inherited) IdiomsModel so that its so that its Locators and Segments can be referenced.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.IdiomsImport#getAs <em>As</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.IdiomsImport#getIdiomsModel <em>Idioms Model</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getIdiomsImport()
 * @model
 * @generated
 */
public interface IdiomsImport
		extends IdiomsElement {

	/**
	 * Returns the value of the '<em><b>As</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>As</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>As</em>' attribute.
	 * @see #setAs(String)
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getIdiomsImport_As()
	 * @model
	 * @generated
	 */
	String getAs();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.idioms.IdiomsImport#getAs <em>As</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>As</em>' attribute.
	 * @see #getAs()
	 * @generated
	 */
	void setAs(String value);

	/**
	 * Returns the value of the '<em><b>Idioms Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Idioms Model</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Idioms Model</em>' reference.
	 * @see #setIdiomsModel(IdiomsModel)
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getIdiomsImport_IdiomsModel()
	 * @model required="true"
	 * @generated
	 */
	IdiomsModel getIdiomsModel();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.idioms.IdiomsImport#getIdiomsModel <em>Idioms Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Idioms Model</em>' reference.
	 * @see #getIdiomsModel()
	 * @generated
	 */
	void setIdiomsModel(IdiomsModel value);

} // IdiomsImport
