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
 * A representation of the model object '<em><b>Templateable Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.TemplateableElement#getOwnedBindings <em>Owned Bindings</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.TemplateableElement#getOwnedSignature <em>Owned Signature</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.TemplateableElement#getUnspecializedElement <em>Unspecialized Element</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getTemplateableElement()
 * @generated
 */
public interface TemplateableElement extends Element
{
	/**
	 * Returns the value of the '<em><b>Owned Bindings</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.TemplateBinding}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.TemplateBinding#getOwningElement <em>Owning Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The optional TemplateBindings from this TemplateableElement to one or more templates.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Bindings</em>' containment reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getTemplateableElement_OwnedBindings()
	 * @see org.eclipse.ocl.ocl.TemplateBinding#getOwningElement
	 * @generated
	 */
	List<TemplateBinding> getOwnedBindings();

	/**
	 * Returns the value of the '<em><b>Owned Signature</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.TemplateSignature#getOwningElement <em>Owning Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The optional TemplateSignature specifying the formal TemplateParameters for this TemplateableElement. If a TemplateableElement has a TemplateSignature, then it is a template.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Signature</em>' containment reference.
	 * @see #setOwnedSignature(TemplateSignature)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getTemplateableElement_OwnedSignature()
	 * @see org.eclipse.ocl.ocl.TemplateSignature#getOwningElement
	 * @generated
	 */
	TemplateSignature getOwnedSignature();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.TemplateableElement#getOwnedSignature <em>Owned Signature</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owned Signature</em>' containment reference.
	 * @see #getOwnedSignature()
	 * @generated
	 */
	void setOwnedSignature(TemplateSignature value);

	/**
	 * Returns the value of the '<em><b>Unspecialized Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unspecialized Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unspecialized Element</em>' reference.
	 * @see #setUnspecializedElement(TemplateableElement)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getTemplateableElement_UnspecializedElement()
	 * @generated
	 */
	TemplateableElement getUnspecializedElement();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.TemplateableElement#getUnspecializedElement <em>Unspecialized Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unspecialized Element</em>' reference.
	 * @see #getUnspecializedElement()
	 * @generated
	 */
	void setUnspecializedElement(TemplateableElement value);

} // TemplateableElement
