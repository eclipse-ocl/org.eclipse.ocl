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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ocl Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.OclElement#getOclContainer <em>Ocl Container</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.OclElement#getOclContents <em>Ocl Contents</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getOclElement()
 * @generated
 */
public interface OclElement extends EObject
{
	/**
	 * Returns the value of the '<em><b>Ocl Container</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The object for which self is a composed content or null if there is no such object.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ocl Container</em>' reference.
	 * @see #setOclContainer(OclElement)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getOclElement_OclContainer()
	 * @generated
	 */
	OclElement getOclContainer();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.OclElement#getOclContainer <em>Ocl Container</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ocl Container</em>' reference.
	 * @see #getOclContainer()
	 * @generated
	 */
	void setOclContainer(OclElement value);

	/**
	 * Returns the value of the '<em><b>Ocl Contents</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.OclElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The composed contents of self.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ocl Contents</em>' reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getOclElement_OclContents()
	 * @generated
	 */
	List<OclElement> getOclContents();

} // OclElement
