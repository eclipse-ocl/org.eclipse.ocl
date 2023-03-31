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
 * A representation of the model object '<em><b>Profile</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.Profile#getProfileApplications <em>Profile Applications</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getProfile()
 * @generated
 */
public interface Profile extends org.eclipse.ocl.ocl.Package
{
	/**
	 * Returns the value of the '<em><b>Profile Applications</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.ProfileApplication}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.ProfileApplication#getAppliedProfile <em>Applied Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Profile Applications</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Profile Applications</em>' reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getProfile_ProfileApplications()
	 * @see org.eclipse.ocl.ocl.ProfileApplication#getAppliedProfile
	 * @generated
	 */
	List<ProfileApplication> getProfileApplications();

} // Profile
