/*******************************************************************************
 * Copyright (c) 2013, 2018 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.cgmodel;

import org.eclipse.ocl.pivot.library.LibraryProperty;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CG Library Property Call Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryPropertyCallExp#getLibraryProperty <em>Library Property</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGLibraryPropertyCallExp()
 * @generated
 */
public interface CGLibraryPropertyCallExp extends CGPropertyCallExp {
	/**
	 * Returns the value of the '<em><b>Library Property</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Library Property</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Library Property</em>' attribute.
	 * @see #setLibraryProperty(LibraryProperty)
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGLibraryPropertyCallExp_LibraryProperty()
	 * @generated
	 */
	LibraryProperty getLibraryProperty();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryPropertyCallExp#getLibraryProperty <em>Library Property</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Library Property</em>' attribute.
	 * @see #getLibraryProperty()
	 * @generated
	 */
	void setLibraryProperty(LibraryProperty value);

} // CGLibraryPropertyCallExp
