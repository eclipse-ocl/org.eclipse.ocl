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
 * A representation of the model object '<em><b>Complete Environment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.CompleteEnvironment#getOwnedCompleteModel <em>Owned Complete Model</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.CompleteEnvironment#getOwnedStandardLibrary <em>Owned Standard Library</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getCompleteEnvironment()
 * @generated
 */
public interface CompleteEnvironment extends Element
{
	/**
	 * Returns the value of the '<em><b>Owned Complete Model</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.CompleteModel#getOwningCompleteEnvironment <em>Owning Complete Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Complete Model</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Complete Model</em>' containment reference.
	 * @see #setOwnedCompleteModel(CompleteModel)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getCompleteEnvironment_OwnedCompleteModel()
	 * @see org.eclipse.ocl.ocl.CompleteModel#getOwningCompleteEnvironment
	 * @generated
	 */
	CompleteModel getOwnedCompleteModel();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.CompleteEnvironment#getOwnedCompleteModel <em>Owned Complete Model</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owned Complete Model</em>' containment reference.
	 * @see #getOwnedCompleteModel()
	 * @generated
	 */
	void setOwnedCompleteModel(CompleteModel value);

	/**
	 * Returns the value of the '<em><b>Owned Standard Library</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.StandardLibrary#getOwningCompleteEnvironment <em>Owning Complete Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Standard Library</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Standard Library</em>' containment reference.
	 * @see #setOwnedStandardLibrary(StandardLibrary)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getCompleteEnvironment_OwnedStandardLibrary()
	 * @see org.eclipse.ocl.ocl.StandardLibrary#getOwningCompleteEnvironment
	 * @generated
	 */
	StandardLibrary getOwnedStandardLibrary();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.CompleteEnvironment#getOwnedStandardLibrary <em>Owned Standard Library</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owned Standard Library</em>' containment reference.
	 * @see #getOwnedStandardLibrary()
	 * @generated
	 */
	void setOwnedStandardLibrary(StandardLibrary value);

} // CompleteEnvironment
