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
 * A representation of the model object '<em><b>Segment Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A ReferredSegment references a SegmentDefinition in an optionally distinct IdiomsModel.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.ReferredSegment#getIdiomsModel <em>Idioms Model</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.ReferredSegment#getSegmentDeclaration <em>Segment Declaration</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getReferredSegment()
 * @model
 * @generated
 */
public interface ReferredSegment
		extends Segment {

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
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getReferredSegment_IdiomsModel()
	 * @model
	 * @generated
	 */
	IdiomsModel getIdiomsModel();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.idioms.ReferredSegment#getIdiomsModel <em>Idioms Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Idioms Model</em>' reference.
	 * @see #getIdiomsModel()
	 * @generated
	 */
	void setIdiomsModel(IdiomsModel value);

	/**
	 * Returns the value of the '<em><b>Segment Declaration</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Segment Declaration</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Segment Declaration</em>' reference.
	 * @see #setSegmentDeclaration(SegmentDeclaration)
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getReferredSegment_SegmentDeclaration()
	 * @model required="true"
	 * @generated
	 */
	SegmentDeclaration getSegmentDeclaration();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.idioms.ReferredSegment#getSegmentDeclaration <em>Segment Declaration</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Segment Declaration</em>' reference.
	 * @see #getSegmentDeclaration()
	 * @generated
	 */
	void setSegmentDeclaration(SegmentDeclaration value);

} // SegmentRef
