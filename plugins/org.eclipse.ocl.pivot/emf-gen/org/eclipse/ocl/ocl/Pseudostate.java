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
 * A representation of the model object '<em><b>Pseudostate</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.Pseudostate#getKind <em>Kind</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Pseudostate#getOwningState <em>Owning State</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Pseudostate#getOwningStateMachine <em>Owning State Machine</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getPseudostate()
 * @generated
 */
public interface Pseudostate extends Vertex
{
	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The default value is <code>"initial"</code>.
	 * The literals are from the enumeration {@link org.eclipse.ocl.ocl.PseudostateKind}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Determines the precise type of the Pseudostate and can be one of: entryPoint, exitPoint, initial, deepHistory, shallowHistory, join, fork, junction, terminate or choice.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see org.eclipse.ocl.ocl.PseudostateKind
	 * @see #setKind(PseudostateKind)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getPseudostate_Kind()
	 * @generated
	 */
	PseudostateKind getKind();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Pseudostate#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see org.eclipse.ocl.ocl.PseudostateKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(PseudostateKind value);

	/**
	 * Returns the value of the '<em><b>Owning State</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.State#getOwnedConnectionPoints <em>Owned Connection Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The State that owns this Pseudostate and in which it appears.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owning State</em>' container reference.
	 * @see #setOwningState(State)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getPseudostate_OwningState()
	 * @see org.eclipse.ocl.ocl.State#getOwnedConnectionPoints
	 * @generated
	 */
	State getOwningState();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Pseudostate#getOwningState <em>Owning State</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning State</em>' container reference.
	 * @see #getOwningState()
	 * @generated
	 */
	void setOwningState(State value);

	/**
	 * Returns the value of the '<em><b>Owning State Machine</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.StateMachine#getOwnedConnectionPoints <em>Owned Connection Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The StateMachine in which this Pseudostate is defined. This only applies to Pseudostates of the kind entryPoint or exitPoint.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owning State Machine</em>' container reference.
	 * @see #setOwningStateMachine(StateMachine)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getPseudostate_OwningStateMachine()
	 * @see org.eclipse.ocl.ocl.StateMachine#getOwnedConnectionPoints
	 * @generated
	 */
	StateMachine getOwningStateMachine();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Pseudostate#getOwningStateMachine <em>Owning State Machine</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning State Machine</em>' container reference.
	 * @see #getOwningStateMachine()
	 * @generated
	 */
	void setOwningStateMachine(StateMachine value);

} // Pseudostate
