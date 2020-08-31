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
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment#getSupportClass <em>Support Class</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment#getSupportClassName <em>Support Class Name</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage#getCustomSegment()
 * @model
 * @generated
 */
public interface CustomSegment extends Segment
{
	/**
	 * Returns the value of the '<em><b>Support Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Support Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Support Class</em>' attribute.
	 * @see #setSupportClass(Class)
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage#getCustomSegment_SupportClass()
	 * @model
	 * @generated
	 */
	Class<?> getSupportClass();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment#getSupportClass <em>Support Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Support Class</em>' attribute.
	 * @see #getSupportClass()
	 * @generated
	 */
	void setSupportClass(Class<?> value);

	/**
	 * Returns the value of the '<em><b>Support Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Support Class Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Support Class Name</em>' attribute.
	 * @see #setSupportClassName(String)
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage#getCustomSegment_SupportClassName()
	 * @model
	 * @generated
	 */
	String getSupportClassName();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment#getSupportClassName <em>Support Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Support Class Name</em>' attribute.
	 * @see #getSupportClassName()
	 * @generated
	 */
	void setSupportClassName(String value);

} // CustomSegment
