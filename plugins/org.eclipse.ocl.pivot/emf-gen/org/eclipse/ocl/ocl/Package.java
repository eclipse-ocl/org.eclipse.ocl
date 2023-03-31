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
 * A representation of the model object '<em><b>Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.Package#getURI <em>URI</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Package#getImportedPackages <em>Imported Packages</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Package#getNsPrefix <em>Ns Prefix</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Package#getOwnedClasses <em>Owned Classes</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Package#getOwnedInstances <em>Owned Instances</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Package#getOwnedPackages <em>Owned Packages</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Package#getOwnedProfileApplications <em>Owned Profile Applications</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Package#getOwningPackage <em>Owning Package</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getPackage()
 * @generated
 */
public interface Package extends Namespace
{
	/**
	 * Returns the value of the '<em><b>URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Provides an identifier for the package that can be used for many purposes. A URI is the universally unique identification of the package following the IETF URI specification, RFC 2396 http://www.ietf.org/rfc/rfc2396.txt and it must comply with those syntax rules.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>URI</em>' attribute.
	 * @see #setURI(String)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getPackage_URI()
	 * @generated
	 */
	String getURI();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Package#getURI <em>URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>URI</em>' attribute.
	 * @see #getURI()
	 * @generated
	 */
	void setURI(String value);

	/**
	 * Returns the value of the '<em><b>Imported Packages</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Package}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Imported Packages</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imported Packages</em>' reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getPackage_ImportedPackages()
	 * @generated
	 */
	List<Package> getImportedPackages();

	/**
	 * Returns the value of the '<em><b>Ns Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ns Prefix</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ns Prefix</em>' attribute.
	 * @see #setNsPrefix(String)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getPackage_NsPrefix()
	 * @generated
	 */
	String getNsPrefix();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Package#getNsPrefix <em>Ns Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ns Prefix</em>' attribute.
	 * @see #getNsPrefix()
	 * @generated
	 */
	void setNsPrefix(String value);

	/**
	 * Returns the value of the '<em><b>Owned Classes</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Class}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.Class#getOwningPackage <em>Owning Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References the packaged elements that are Types.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Classes</em>' containment reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getPackage_OwnedClasses()
	 * @see org.eclipse.ocl.ocl.Class#getOwningPackage
	 * @generated
	 */
	List<org.eclipse.ocl.ocl.Class> getOwnedClasses();

	/**
	 * Returns the value of the '<em><b>Owned Instances</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.InstanceSpecification}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.InstanceSpecification#getOwningPackage <em>Owning Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The instance specification that owns this slot.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Instances</em>' containment reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getPackage_OwnedInstances()
	 * @see org.eclipse.ocl.ocl.InstanceSpecification#getOwningPackage
	 * @generated
	 */
	List<InstanceSpecification> getOwnedInstances();

	/**
	 * Returns the value of the '<em><b>Owned Packages</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Package}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.Package#getOwningPackage <em>Owning Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References the packaged elements that are Packages.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Packages</em>' containment reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getPackage_OwnedPackages()
	 * @see org.eclipse.ocl.ocl.Package#getOwningPackage
	 * @generated
	 */
	List<Package> getOwnedPackages();

	/**
	 * Returns the value of the '<em><b>Owned Profile Applications</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.ProfileApplication}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.ProfileApplication#getOwningPackage <em>Owning Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References the ProfileApplications that indicate which profiles have been applied to the Package.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Profile Applications</em>' containment reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getPackage_OwnedProfileApplications()
	 * @see org.eclipse.ocl.ocl.ProfileApplication#getOwningPackage
	 * @generated
	 */
	List<ProfileApplication> getOwnedProfileApplications();

	/**
	 * Returns the value of the '<em><b>Owning Package</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.Package#getOwnedPackages <em>Owned Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References the Package that owns this Package.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owning Package</em>' container reference.
	 * @see #setOwningPackage(Package)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getPackage_OwningPackage()
	 * @see org.eclipse.ocl.ocl.Package#getOwnedPackages
	 * @generated
	 */
	Package getOwningPackage();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Package#getOwningPackage <em>Owning Package</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Package</em>' container reference.
	 * @see #getOwningPackage()
	 * @generated
	 */
	void setOwningPackage(Package value);

} // Package
