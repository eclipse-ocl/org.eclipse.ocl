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
 * A representation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.Library#getOwnedPrecedences <em>Owned Precedences</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getLibrary()
 * @generated
 */
public interface Library extends org.eclipse.ocl.ocl.Package
{
	/**
	 * Returns the value of the '<em><b>Owned Precedences</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Precedence}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Precedences</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Precedences</em>' containment reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getLibrary_OwnedPrecedences()
	 * @generated
	 */
	List<Precedence> getOwnedPrecedences();

} // Library
