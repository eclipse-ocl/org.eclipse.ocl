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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Profile Application</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.ProfileApplication#getAppliedProfile <em>Applied Profile</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.ProfileApplication#getIsStrict <em>Is Strict</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.ProfileApplication#getOwningPackage <em>Owning Package</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getProfileApplication()
 * @generated
 */
public interface ProfileApplication extends Element
{
	/**
	 * Returns the value of the '<em><b>Applied Profile</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.Profile#getProfileApplications <em>Profile Applications</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References the Profiles that are applied to a Package through this ProfileApplication.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Applied Profile</em>' reference.
	 * @see #setAppliedProfile(Profile)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getProfileApplication_AppliedProfile()
	 * @see org.eclipse.ocl.ocl.Profile#getProfileApplications
	 * @generated
	 */
	Profile getAppliedProfile();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.ProfileApplication#getAppliedProfile <em>Applied Profile</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Applied Profile</em>' reference.
	 * @see #getAppliedProfile()
	 * @generated
	 */
	void setAppliedProfile(Profile value);

	/**
	 * Returns the value of the '<em><b>Is Strict</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies that the Profile filtering rules for the metaclasses of the referenced metamodel shall be strictly applied.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Strict</em>' attribute.
	 * @see #setIsStrict(Boolean)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getProfileApplication_IsStrict()
	 * @generated
	 */
	Boolean getIsStrict();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.ProfileApplication#getIsStrict <em>Is Strict</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Strict</em>' attribute.
	 * @see #getIsStrict()
	 * @generated
	 */
	void setIsStrict(Boolean value);

	/**
	 * Returns the value of the '<em><b>Owning Package</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.Package#getOwnedProfileApplications <em>Owned Profile Applications</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The package that owns the profile application.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owning Package</em>' container reference.
	 * @see #setOwningPackage(org.eclipse.ocl.ocl.Package)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getProfileApplication_OwningPackage()
	 * @see org.eclipse.ocl.ocl.Package#getOwnedProfileApplications
	 * @generated
	 */
	org.eclipse.ocl.ocl.Package getOwningPackage();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.ProfileApplication#getOwningPackage <em>Owning Package</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Package</em>' container reference.
	 * @see #getOwningPackage()
	 * @generated
	 */
	void setOwningPackage(org.eclipse.ocl.ocl.Package value);

} // ProfileApplication
