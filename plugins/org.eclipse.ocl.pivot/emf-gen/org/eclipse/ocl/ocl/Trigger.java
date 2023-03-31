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
 * A representation of the model object '<em><b>Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.Trigger#getOwningState <em>Owning State</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Trigger#getOwningTransition <em>Owning Transition</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getTrigger()
 * @generated
 */
public interface Trigger extends NamedElement
{
	/**
	 * Returns the value of the '<em><b>Owning State</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.State#getOwnedDeferrableTriggers <em>Owned Deferrable Triggers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owning State</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning State</em>' container reference.
	 * @see #setOwningState(State)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getTrigger_OwningState()
	 * @see org.eclipse.ocl.ocl.State#getOwnedDeferrableTriggers
	 * @generated
	 */
	State getOwningState();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Trigger#getOwningState <em>Owning State</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning State</em>' container reference.
	 * @see #getOwningState()
	 * @generated
	 */
	void setOwningState(State value);

	/**
	 * Returns the value of the '<em><b>Owning Transition</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.Transition#getOwnedTriggers <em>Owned Triggers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owning Transition</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Transition</em>' container reference.
	 * @see #setOwningTransition(Transition)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getTrigger_OwningTransition()
	 * @see org.eclipse.ocl.ocl.Transition#getOwnedTriggers
	 * @generated
	 */
	Transition getOwningTransition();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Trigger#getOwningTransition <em>Owning Transition</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Transition</em>' container reference.
	 * @see #getOwningTransition()
	 * @generated
	 */
	void setOwningTransition(Transition value);

} // Trigger
