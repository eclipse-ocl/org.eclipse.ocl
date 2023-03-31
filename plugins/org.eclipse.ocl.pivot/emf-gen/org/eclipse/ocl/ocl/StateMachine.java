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
 * A representation of the model object '<em><b>State Machine</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.StateMachine#getExtendedStateMachines <em>Extended State Machines</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.StateMachine#getOwnedConnectionPoints <em>Owned Connection Points</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.StateMachine#getOwnedRegions <em>Owned Regions</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.StateMachine#getSubmachineStates <em>Submachine States</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getStateMachine()
 * @generated
 */
public interface StateMachine extends Behavior
{
	/**
	 * Returns the value of the '<em><b>Extended State Machines</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.StateMachine}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The StateMachines of which this is an extension.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Extended State Machines</em>' reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getStateMachine_ExtendedStateMachines()
	 * @generated
	 */
	List<StateMachine> getExtendedStateMachines();

	/**
	 * Returns the value of the '<em><b>Owned Connection Points</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Pseudostate}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.Pseudostate#getOwningStateMachine <em>Owning State Machine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The connection points defined for this StateMachine. They represent the interface of the StateMachine when used as part of submachine State
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Connection Points</em>' containment reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getStateMachine_OwnedConnectionPoints()
	 * @see org.eclipse.ocl.ocl.Pseudostate#getOwningStateMachine
	 * @generated
	 */
	List<Pseudostate> getOwnedConnectionPoints();

	/**
	 * Returns the value of the '<em><b>Owned Regions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Region}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.Region#getOwningStateMachine <em>Owning State Machine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Regions owned directly by the StateMachine.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Regions</em>' containment reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getStateMachine_OwnedRegions()
	 * @see org.eclipse.ocl.ocl.Region#getOwningStateMachine
	 * @generated
	 */
	List<Region> getOwnedRegions();

	/**
	 * Returns the value of the '<em><b>Submachine States</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.State}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.State#getSubmachines <em>Submachines</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References the submachine(s) in case of a submachine State. Multiple machines are referenced in case of a concurrent State.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Submachine States</em>' reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getStateMachine_SubmachineStates()
	 * @see org.eclipse.ocl.ocl.State#getSubmachines
	 * @generated
	 */
	List<State> getSubmachineStates();

} // StateMachine
