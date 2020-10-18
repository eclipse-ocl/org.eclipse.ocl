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
 * A representation of the model object '<em><b>Named Locator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A LocatorDeclaration makes a Locator available for re-use by a ReferredLocator.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.LocatorDeclaration#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.LocatorDeclaration#getOwnedLocator <em>Owned Locator</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.LocatorDeclaration#getOwningIdiomsModel <em>Owning Idioms Model</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getLocatorDeclaration()
 * @model
 * @generated
 */
public interface LocatorDeclaration
		extends IdiomsElement {

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getLocatorDeclaration_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.idioms.LocatorDeclaration#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Owned Locator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Locator</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Locator</em>' containment reference.
	 * @see #setOwnedLocator(Locator)
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getLocatorDeclaration_OwnedLocator()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Locator getOwnedLocator();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.idioms.LocatorDeclaration#getOwnedLocator <em>Owned Locator</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owned Locator</em>' containment reference.
	 * @see #getOwnedLocator()
	 * @generated
	 */
	void setOwnedLocator(Locator value);

	/**
	 * Returns the value of the '<em><b>Owning Idioms Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.examples.xtext.idioms.IdiomsModel#getOwnedLocatorDeclarations <em>Owned Locator Declarations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Idioms Model</em>' container reference.
	 * @see #setOwningIdiomsModel(IdiomsModel)
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getLocatorDeclaration_OwningIdiomsModel()
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsModel#getOwnedLocatorDeclarations
	 * @model opposite="ownedLocatorDeclarations" required="true" transient="false"
	 * @generated
	 */
	IdiomsModel getOwningIdiomsModel();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.idioms.LocatorDeclaration#getOwningIdiomsModel <em>Owning Idioms Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Idioms Model</em>' container reference.
	 * @see #getOwningIdiomsModel()
	 * @generated
	 */
	void setOwningIdiomsModel(IdiomsModel value);

} // NamedLocator
