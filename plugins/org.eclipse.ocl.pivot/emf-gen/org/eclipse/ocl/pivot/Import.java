/*******************************************************************************
 * Copyright (c) 2012, 2014 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Import</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.Import#getImportedNamespace <em>Imported Namespace</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.pivot.PivotPackage#getImport()
 * @generated
 */
public interface Import extends NamedElement
{
	/**
	 * Returns the value of the '<em><b>Imported Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Imported Namespace</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imported Namespace</em>' reference.
	 * @see #setImportedNamespace(Namespace)
	 * @see org.eclipse.ocl.pivot.PivotPackage#getImport_ImportedNamespace()
	 * @generated
	 */
	Namespace getImportedNamespace();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.Import#getImportedNamespace <em>Imported Namespace</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Imported Namespace</em>' reference.
	 * @see #getImportedNamespace()
	 * @generated
	 */
	void setImportedNamespace(Namespace value);

} // Import
