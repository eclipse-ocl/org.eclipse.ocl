/**
 * Copyright (c) 2020, 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.xtext.idioms;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Segment Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A SegmentDeclaration makes a Segment available for re-use by a ReferredSegment.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.idioms.SegmentDeclaration#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.idioms.SegmentDeclaration#getOwnedSegment <em>Owned Segment</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.idioms.SegmentDeclaration#getOwningIdiomsModel <em>Owning Idioms Model</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.xtext.idioms.IdiomsPackage#getSegmentDeclaration()
 * @model
 * @generated
 */
public interface SegmentDeclaration
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
	 * @see org.eclipse.ocl.xtext.idioms.IdiomsPackage#getSegmentDeclaration_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.idioms.SegmentDeclaration#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Owned Segment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Segment</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Segment</em>' containment reference.
	 * @see #setOwnedSegment(Segment)
	 * @see org.eclipse.ocl.xtext.idioms.IdiomsPackage#getSegmentDeclaration_OwnedSegment()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Segment getOwnedSegment();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.idioms.SegmentDeclaration#getOwnedSegment <em>Owned Segment</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owned Segment</em>' containment reference.
	 * @see #getOwnedSegment()
	 * @generated
	 */
	void setOwnedSegment(Segment value);

	/**
	 * Returns the value of the '<em><b>Owning Idioms Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.xtext.idioms.IdiomsModel#getOwnedSegmentDeclarations <em>Owned Segment Declarations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Idioms Model</em>' container reference.
	 * @see #setOwningIdiomsModel(IdiomsModel)
	 * @see org.eclipse.ocl.xtext.idioms.IdiomsPackage#getSegmentDeclaration_OwningIdiomsModel()
	 * @see org.eclipse.ocl.xtext.idioms.IdiomsModel#getOwnedSegmentDeclarations
	 * @model opposite="ownedSegmentDeclarations" required="true" transient="false"
	 * @generated
	 */
	IdiomsModel getOwningIdiomsModel();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.idioms.SegmentDeclaration#getOwningIdiomsModel <em>Owning Idioms Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Idioms Model</em>' container reference.
	 * @see #getOwningIdiomsModel()
	 * @generated
	 */
	void setOwningIdiomsModel(IdiomsModel value);

} // SegmentDeclaration
