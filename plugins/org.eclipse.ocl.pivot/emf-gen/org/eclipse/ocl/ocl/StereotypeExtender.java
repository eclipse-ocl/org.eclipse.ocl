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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stereotype Extender</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.StereotypeExtender#getClass_ <em>Class</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.StereotypeExtender#getIsRequired <em>Is Required</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.StereotypeExtender#getOwningStereotype <em>Owning Stereotype</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getStereotypeExtender()
 * @generated
 */
public interface StereotypeExtender extends Element
{
	/**
	 * Returns the value of the '<em><b>Class</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.Class#getExtenders <em>Extenders</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class</em>' reference.
	 * @see #setClass(org.eclipse.ocl.ocl.Class)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getStereotypeExtender_Class()
	 * @see org.eclipse.ocl.ocl.Class#getExtenders
	 * @generated
	 */
	org.eclipse.ocl.ocl.Class getClass_();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.StereotypeExtender#getClass_ <em>Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class</em>' reference.
	 * @see #getClass_()
	 * @generated
	 */
	void setClass(org.eclipse.ocl.ocl.Class value);

	/**
	 * Returns the value of the '<em><b>Is Required</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Required</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Required</em>' attribute.
	 * @see #setIsRequired(Boolean)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getStereotypeExtender_IsRequired()
	 * @generated
	 */
	Boolean getIsRequired();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.StereotypeExtender#getIsRequired <em>Is Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Required</em>' attribute.
	 * @see #getIsRequired()
	 * @generated
	 */
	void setIsRequired(Boolean value);

	/**
	 * Returns the value of the '<em><b>Owning Stereotype</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.Stereotype#getOwnedExtenders <em>Owned Extenders</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owning Stereotype</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Stereotype</em>' container reference.
	 * @see #setOwningStereotype(Stereotype)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getStereotypeExtender_OwningStereotype()
	 * @see org.eclipse.ocl.ocl.Stereotype#getOwnedExtenders
	 * @generated
	 */
	Stereotype getOwningStereotype();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.StereotypeExtender#getOwningStereotype <em>Owning Stereotype</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Stereotype</em>' container reference.
	 * @see #getOwningStereotype()
	 * @generated
	 */
	void setOwningStereotype(Stereotype value);

} // StereotypeExtender
