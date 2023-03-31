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
 * A representation of the model object '<em><b>Complete Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.CompleteModel#getOrphanCompletePackage <em>Orphan Complete Package</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.CompleteModel#getOwnedCompletePackages <em>Owned Complete Packages</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.CompleteModel#getOwningCompleteEnvironment <em>Owning Complete Environment</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.CompleteModel#getPartialModels <em>Partial Models</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.CompleteModel#getPrimitiveCompletePackage <em>Primitive Complete Package</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getCompleteModel()
 * @generated
 */
public interface CompleteModel extends NamedElement
{
	/**
	 * Returns the value of the '<em><b>Orphan Complete Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orphan Complete Package</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orphan Complete Package</em>' reference.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getCompleteModel_OrphanCompletePackage()
	 * @generated
	 */
	OrphanCompletePackage getOrphanCompletePackage();

	/**
	 * Returns the value of the '<em><b>Owned Complete Packages</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.CompletePackage}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.CompletePackage#getOwningCompleteModel <em>Owning Complete Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Complete Packages</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Complete Packages</em>' containment reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getCompleteModel_OwnedCompletePackages()
	 * @see org.eclipse.ocl.ocl.CompletePackage#getOwningCompleteModel
	 * @generated
	 */
	List<CompletePackage> getOwnedCompletePackages();

	/**
	 * Returns the value of the '<em><b>Owning Complete Environment</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.CompleteEnvironment#getOwnedCompleteModel <em>Owned Complete Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owning Complete Environment</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Complete Environment</em>' container reference.
	 * @see #setOwningCompleteEnvironment(CompleteEnvironment)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getCompleteModel_OwningCompleteEnvironment()
	 * @see org.eclipse.ocl.ocl.CompleteEnvironment#getOwnedCompleteModel
	 * @generated
	 */
	CompleteEnvironment getOwningCompleteEnvironment();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.CompleteModel#getOwningCompleteEnvironment <em>Owning Complete Environment</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Complete Environment</em>' container reference.
	 * @see #getOwningCompleteEnvironment()
	 * @generated
	 */
	void setOwningCompleteEnvironment(CompleteEnvironment value);

	/**
	 * Returns the value of the '<em><b>Partial Models</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Model}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partial Models</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partial Models</em>' reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getCompleteModel_PartialModels()
	 * @generated
	 */
	List<Model> getPartialModels();

	/**
	 * Returns the value of the '<em><b>Primitive Complete Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Primitive Complete Package</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Primitive Complete Package</em>' reference.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getCompleteModel_PrimitiveCompletePackage()
	 * @generated
	 */
	PrimitiveCompletePackage getPrimitiveCompletePackage();

} // CompleteModel
