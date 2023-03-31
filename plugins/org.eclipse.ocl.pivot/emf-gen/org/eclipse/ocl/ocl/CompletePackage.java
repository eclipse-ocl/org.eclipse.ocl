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
 * A representation of the model object '<em><b>Complete Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.CompletePackage#getOwnedCompleteClasses <em>Owned Complete Classes</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.CompletePackage#getOwnedCompletePackages <em>Owned Complete Packages</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.CompletePackage#getOwningCompleteModel <em>Owning Complete Model</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.CompletePackage#getOwningCompletePackage <em>Owning Complete Package</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.CompletePackage#getPartialPackages <em>Partial Packages</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getCompletePackage()
 * @generated
 */
public interface CompletePackage extends NamedElement
{
	/**
	 * Returns the value of the '<em><b>Owned Complete Classes</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.CompleteClass}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.CompleteClass#getOwningCompletePackage <em>Owning Complete Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Complete Classes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Complete Classes</em>' containment reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getCompletePackage_OwnedCompleteClasses()
	 * @see org.eclipse.ocl.ocl.CompleteClass#getOwningCompletePackage
	 * @generated
	 */
	List<CompleteClass> getOwnedCompleteClasses();

	/**
	 * Returns the value of the '<em><b>Owned Complete Packages</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.CompletePackage}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.CompletePackage#getOwningCompletePackage <em>Owning Complete Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Complete Packages</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Complete Packages</em>' containment reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getCompletePackage_OwnedCompletePackages()
	 * @see org.eclipse.ocl.ocl.CompletePackage#getOwningCompletePackage
	 * @generated
	 */
	List<CompletePackage> getOwnedCompletePackages();

	/**
	 * Returns the value of the '<em><b>Owning Complete Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.CompleteModel#getOwnedCompletePackages <em>Owned Complete Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owning Complete Model</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Complete Model</em>' container reference.
	 * @see #setOwningCompleteModel(CompleteModel)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getCompletePackage_OwningCompleteModel()
	 * @see org.eclipse.ocl.ocl.CompleteModel#getOwnedCompletePackages
	 * @generated
	 */
	CompleteModel getOwningCompleteModel();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.CompletePackage#getOwningCompleteModel <em>Owning Complete Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Complete Model</em>' container reference.
	 * @see #getOwningCompleteModel()
	 * @generated
	 */
	void setOwningCompleteModel(CompleteModel value);

	/**
	 * Returns the value of the '<em><b>Owning Complete Package</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.CompletePackage#getOwnedCompletePackages <em>Owned Complete Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owning Complete Package</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Complete Package</em>' container reference.
	 * @see #setOwningCompletePackage(CompletePackage)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getCompletePackage_OwningCompletePackage()
	 * @see org.eclipse.ocl.ocl.CompletePackage#getOwnedCompletePackages
	 * @generated
	 */
	CompletePackage getOwningCompletePackage();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.CompletePackage#getOwningCompletePackage <em>Owning Complete Package</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Complete Package</em>' container reference.
	 * @see #getOwningCompletePackage()
	 * @generated
	 */
	void setOwningCompletePackage(CompletePackage value);

	/**
	 * Returns the value of the '<em><b>Partial Packages</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Package}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partial Packages</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partial Packages</em>' reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getCompletePackage_PartialPackages()
	 * @generated
	 */
	List<org.eclipse.ocl.ocl.Package> getPartialPackages();

} // CompletePackage
