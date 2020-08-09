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
package org.eclipse.ocl.xtext.base.cs2text.idioms;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Custom Segment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment#getDelegate <em>Delegate</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment#getDelegateClass <em>Delegate Class</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage#getCustomSegment()
 * @model
 * @generated
 */
public interface CustomSegment extends Segment
{
	/**
	 * Returns the value of the '<em><b>Delegate</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Delegate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Delegate</em>' reference.
	 * @see #setDelegate(Segment)
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage#getCustomSegment_Delegate()
	 * @model required="true" transient="true" derived="true"
	 * @generated
	 */
	Segment getDelegate();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment#getDelegate <em>Delegate</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Delegate</em>' reference.
	 * @see #getDelegate()
	 * @generated
	 */
	void setDelegate(Segment value);

	/**
	 * Returns the value of the '<em><b>Delegate Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Delegate Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Delegate Class</em>' attribute.
	 * @see #setDelegateClass(String)
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage#getCustomSegment_DelegateClass()
	 * @model required="true"
	 * @generated
	 */
	String getDelegateClass();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment#getDelegateClass <em>Delegate Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Delegate Class</em>' attribute.
	 * @see #getDelegateClass()
	 * @generated
	 */
	void setDelegateClass(String value);

} // CustomSegment
