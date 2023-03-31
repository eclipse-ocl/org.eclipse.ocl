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

import org.eclipse.ocl.pivot.values.IntegerValue;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.Model#getExternalURI <em>External URI</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Model#getOwnedImports <em>Owned Imports</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Model#getOwnedPackages <em>Owned Packages</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Model#getXmiidVersion <em>Xmiid Version</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getModel()
 * @generated
 */
public interface Model extends Namespace
{
	/**
	 * Returns the value of the '<em><b>External URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External URI</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External URI</em>' attribute.
	 * @see #setExternalURI(String)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getModel_ExternalURI()
	 * @generated
	 */
	String getExternalURI();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Model#getExternalURI <em>External URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External URI</em>' attribute.
	 * @see #getExternalURI()
	 * @generated
	 */
	void setExternalURI(String value);

	/**
	 * Returns the value of the '<em><b>Owned Imports</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Import}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Imports</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Imports</em>' containment reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getModel_OwnedImports()
	 * @generated
	 */
	List<Import> getOwnedImports();

	/**
	 * Returns the value of the '<em><b>Owned Packages</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Package}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Packages</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Packages</em>' containment reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getModel_OwnedPackages()
	 * @generated
	 */
	List<org.eclipse.ocl.ocl.Package> getOwnedPackages();

	/**
	 * Returns the value of the '<em><b>Xmiid Version</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Xmiid Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Xmiid Version</em>' attribute.
	 * @see #setXmiidVersion(IntegerValue)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getModel_XmiidVersion()
	 * @generated
	 */
	IntegerValue getXmiidVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Model#getXmiidVersion <em>Xmiid Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xmiid Version</em>' attribute.
	 * @see #getXmiidVersion()
	 * @generated
	 */
	void setXmiidVersion(IntegerValue value);

} // Model
