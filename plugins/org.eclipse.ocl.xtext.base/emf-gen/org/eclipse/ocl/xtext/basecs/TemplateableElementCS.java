/*******************************************************************************
 * Copyright (c) 2010, 2014 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.basecs;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Templateable Element CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.TemplateableElementCS#getOwnedSignature <em>Owned Signature</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage#getTemplateableElementCS()
 * @model abstract="true"
 * @generated
 */
public interface TemplateableElementCS extends ElementCS {
	/**
	 * Returns the value of the '<em><b>Owned Signature</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.xtext.basecs.TemplateSignatureCS#getOwningElement <em>Owning Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Template Signature</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Signature</em>' containment reference.
	 * @see #setOwnedSignature(TemplateSignatureCS)
	 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage#getTemplateableElementCS_OwnedSignature()
	 * @see org.eclipse.ocl.xtext.basecs.TemplateSignatureCS#getOwningElement
	 * @model opposite="owningElement" containment="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://www.eclipse.org/ocl/2015/BaseCS!TemplateableElementCS!ownedSignature'"
	 * @generated
	 */
	TemplateSignatureCS getOwnedSignature();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.basecs.TemplateableElementCS#getOwnedSignature <em>Owned Signature</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owned Signature</em>' containment reference.
	 * @see #getOwnedSignature()
	 * @generated
	 */
	void setOwnedSignature(TemplateSignatureCS value);

} // TemplateableElementCS
