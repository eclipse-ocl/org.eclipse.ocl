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
 * A representation of the model object '<em><b>Element Extension</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.ElementExtension#getBase <em>Base</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.ElementExtension#getIsApplied <em>Is Applied</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.ElementExtension#getIsRequired <em>Is Required</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.ElementExtension#getStereotype <em>Stereotype</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getElementExtension()
 * @generated
 */
public interface ElementExtension extends org.eclipse.ocl.ocl.Class
{
	/**
	 * Returns the value of the '<em><b>Base</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.Element#getOwnedExtensions <em>Owned Extensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base</em>' container reference.
	 * @see #setBase(Element)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getElementExtension_Base()
	 * @see org.eclipse.ocl.ocl.Element#getOwnedExtensions
	 * @generated
	 */
	Element getBase();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.ElementExtension#getBase <em>Base</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base</em>' container reference.
	 * @see #getBase()
	 * @generated
	 */
	void setBase(Element value);

	/**
	 * Returns the value of the '<em><b>Is Applied</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Applied</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Applied</em>' attribute.
	 * @see #setIsApplied(Boolean)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getElementExtension_IsApplied()
	 * @generated
	 */
	Boolean getIsApplied();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.ElementExtension#getIsApplied <em>Is Applied</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Applied</em>' attribute.
	 * @see #getIsApplied()
	 * @generated
	 */
	void setIsApplied(Boolean value);

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
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getElementExtension_IsRequired()
	 * @generated
	 */
	Boolean getIsRequired();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.ElementExtension#getIsRequired <em>Is Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Required</em>' attribute.
	 * @see #getIsRequired()
	 * @generated
	 */
	void setIsRequired(Boolean value);

	/**
	 * Returns the value of the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stereotype</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stereotype</em>' reference.
	 * @see #setStereotype(Stereotype)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getElementExtension_Stereotype()
	 * @generated
	 */
	Stereotype getStereotype();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.ElementExtension#getStereotype <em>Stereotype</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stereotype</em>' reference.
	 * @see #getStereotype()
	 * @generated
	 */
	void setStereotype(Stereotype value);

} // ElementExtension
