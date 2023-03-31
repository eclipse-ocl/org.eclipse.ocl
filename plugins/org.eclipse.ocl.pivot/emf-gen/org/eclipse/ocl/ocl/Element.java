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
 * A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.Element#getAnnotatingComments <em>Annotating Comments</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Element#getOwnedAnnotations <em>Owned Annotations</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Element#getOwnedComments <em>Owned Comments</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Element#getOwnedExtensions <em>Owned Extensions</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getElement()
 * @generated
 */
public interface Element extends OclElement
{
	/**
	 * Returns the value of the '<em><b>Annotating Comments</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Comment}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.Comment#getAnnotatedElements <em>Annotated Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Annotating Comments</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Annotating Comments</em>' reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getElement_AnnotatingComments()
	 * @see org.eclipse.ocl.ocl.Comment#getAnnotatedElements
	 * @generated
	 */
	List<Comment> getAnnotatingComments();

	/**
	 * Returns the value of the '<em><b>Owned Annotations</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Element}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Annotations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Annotations</em>' containment reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getElement_OwnedAnnotations()
	 * @generated
	 */
	List<Element> getOwnedAnnotations();

	/**
	 * Returns the value of the '<em><b>Owned Comments</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Comment}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.Comment#getOwningElement <em>Owning Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Comments owned by this Element.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Comments</em>' containment reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getElement_OwnedComments()
	 * @see org.eclipse.ocl.ocl.Comment#getOwningElement
	 * @generated
	 */
	List<Comment> getOwnedComments();

	/**
	 * Returns the value of the '<em><b>Owned Extensions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.ElementExtension}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.ElementExtension#getBase <em>Base</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Extensions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Extensions</em>' containment reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getElement_OwnedExtensions()
	 * @see org.eclipse.ocl.ocl.ElementExtension#getBase
	 * @generated
	 */
	List<ElementExtension> getOwnedExtensions();

} // Element
