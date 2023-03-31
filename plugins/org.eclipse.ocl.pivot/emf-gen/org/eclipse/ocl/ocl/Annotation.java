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
 * A representation of the model object '<em><b>Annotation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.Annotation#getOwnedContents <em>Owned Contents</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Annotation#getOwnedDetails <em>Owned Details</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Annotation#getReferences <em>References</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getAnnotation()
 * @generated
 */
public interface Annotation extends NamedElement
{
	/**
	 * Returns the value of the '<em><b>Owned Contents</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Element}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Contents</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Contents</em>' containment reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getAnnotation_OwnedContents()
	 * @generated
	 */
	List<Element> getOwnedContents();

	/**
	 * Returns the value of the '<em><b>Owned Details</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Detail}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Details</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Details</em>' containment reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getAnnotation_OwnedDetails()
	 * @generated
	 */
	List<Detail> getOwnedDetails();

	/**
	 * Returns the value of the '<em><b>References</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Element}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>References</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>References</em>' reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getAnnotation_References()
	 * @generated
	 */
	List<Element> getReferences();

} // Annotation
