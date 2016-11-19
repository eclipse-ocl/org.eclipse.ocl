/*******************************************************************************
 * Copyright (c) 2012, 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Behavior;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Pseudostate;
import org.eclipse.ocl.pivot.Region;
import org.eclipse.ocl.pivot.State;
import org.eclipse.ocl.pivot.StateMachine;
import org.eclipse.ocl.pivot.StereotypeExtender;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.Transition;
import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>State Machine</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.StateMachineImpl#getExtendedStateMachines <em>Extended State Machines</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.StateMachineImpl#getOwnedConnectionPoints <em>Owned Connection Points</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.StateMachineImpl#getOwnedRegions <em>Owned Regions</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.StateMachineImpl#getSubmachineStates <em>Submachine States</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StateMachineImpl extends BehaviorImpl implements StateMachine
{
	/**
	 * The cached value of the '{@link #getExtendedStateMachines() <em>Extended State Machines</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtendedStateMachines()
	 * @generated
	 * @ordered
	 */
	protected EList<StateMachine> extendedStateMachines;

	/**
	 * The cached value of the '{@link #getOwnedConnectionPoints() <em>Owned Connection Points</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedConnectionPoints()
	 * @generated
	 * @ordered
	 */
	protected EList<Pseudostate> ownedConnectionPoints;

	/**
	 * The cached value of the '{@link #getOwnedRegions() <em>Owned Regions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedRegions()
	 * @generated
	 * @ordered
	 */
	protected EList<Region> ownedRegions;

	/**
	 * The cached value of the '{@link #getSubmachineStates() <em>Submachine States</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubmachineStates()
	 * @generated
	 * @ordered
	 */
	protected EList<State> submachineStates;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StateMachineImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PivotPackage.Literals.STATE_MACHINE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<StateMachine> getExtendedStateMachines()
	{
		if (extendedStateMachines == null)
		{
			extendedStateMachines = new EObjectResolvingEList<StateMachine>(StateMachine.class, this, PivotPackage.STATE_MACHINE__EXTENDED_STATE_MACHINES);
		}
		return extendedStateMachines;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Pseudostate> getOwnedConnectionPoints()
	{
		if (ownedConnectionPoints == null)
		{
			ownedConnectionPoints = new EObjectContainmentWithInverseEList<Pseudostate>(Pseudostate.class, this, PivotPackage.STATE_MACHINE__OWNED_CONNECTION_POINTS, PivotPackage.PSEUDOSTATE__OWNING_STATE_MACHINE);
		}
		return ownedConnectionPoints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Region> getOwnedRegions()
	{
		if (ownedRegions == null)
		{
			ownedRegions = new EObjectContainmentWithInverseEList<Region>(Region.class, this, PivotPackage.STATE_MACHINE__OWNED_REGIONS, PivotPackage.REGION__OWNING_STATE_MACHINE);
		}
		return ownedRegions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<State> getSubmachineStates()
	{
		if (submachineStates == null)
		{
			submachineStates = new EObjectWithInverseResolvingEList<State>(State.class, this, PivotPackage.STATE_MACHINE__SUBMACHINE_STATES, PivotPackage.STATE__SUBMACHINES);
		}
		return submachineStates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case PivotPackage.STATE_MACHINE__ANNOTATING_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.STATE_MACHINE__OWNED_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.STATE_MACHINE__OWNED_EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case PivotPackage.STATE_MACHINE__OWNED_BINDINGS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedBindings()).basicAdd(otherEnd, msgs);
			case PivotPackage.STATE_MACHINE__OWNED_SIGNATURE:
				if (ownedSignature != null)
					msgs = ((InternalEObject)ownedSignature).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.STATE_MACHINE__OWNED_SIGNATURE, null, msgs);
				return basicSetOwnedSignature((TemplateSignature)otherEnd, msgs);
			case PivotPackage.STATE_MACHINE__EXTENDERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExtenders()).basicAdd(otherEnd, msgs);
			case PivotPackage.STATE_MACHINE__OWNED_OPERATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedOperations()).basicAdd(otherEnd, msgs);
			case PivotPackage.STATE_MACHINE__OWNED_PROPERTIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedProperties()).basicAdd(otherEnd, msgs);
			case PivotPackage.STATE_MACHINE__OWNING_PACKAGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningPackage((org.eclipse.ocl.pivot.Package)otherEnd, msgs);
			case PivotPackage.STATE_MACHINE__OWNING_TRANSITION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningTransition((Transition)otherEnd, msgs);
			case PivotPackage.STATE_MACHINE__OWNED_CONNECTION_POINTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedConnectionPoints()).basicAdd(otherEnd, msgs);
			case PivotPackage.STATE_MACHINE__OWNED_REGIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedRegions()).basicAdd(otherEnd, msgs);
			case PivotPackage.STATE_MACHINE__SUBMACHINE_STATES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSubmachineStates()).basicAdd(otherEnd, msgs);
		}
		return eDynamicInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case PivotPackage.STATE_MACHINE__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.STATE_MACHINE__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.STATE_MACHINE__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.STATE_MACHINE__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.STATE_MACHINE__OWNED_CONSTRAINTS:
				return ((InternalEList<?>)getOwnedConstraints()).basicRemove(otherEnd, msgs);
			case PivotPackage.STATE_MACHINE__OWNED_BINDINGS:
				return ((InternalEList<?>)getOwnedBindings()).basicRemove(otherEnd, msgs);
			case PivotPackage.STATE_MACHINE__OWNED_SIGNATURE:
				return basicSetOwnedSignature(null, msgs);
			case PivotPackage.STATE_MACHINE__EXTENDERS:
				return ((InternalEList<?>)getExtenders()).basicRemove(otherEnd, msgs);
			case PivotPackage.STATE_MACHINE__OWNED_BEHAVIORS:
				return ((InternalEList<?>)getOwnedBehaviors()).basicRemove(otherEnd, msgs);
			case PivotPackage.STATE_MACHINE__OWNED_INVARIANTS:
				return ((InternalEList<?>)getOwnedInvariants()).basicRemove(otherEnd, msgs);
			case PivotPackage.STATE_MACHINE__OWNED_OPERATIONS:
				return ((InternalEList<?>)getOwnedOperations()).basicRemove(otherEnd, msgs);
			case PivotPackage.STATE_MACHINE__OWNED_PROPERTIES:
				return ((InternalEList<?>)getOwnedProperties()).basicRemove(otherEnd, msgs);
			case PivotPackage.STATE_MACHINE__OWNING_PACKAGE:
				return basicSetOwningPackage(null, msgs);
			case PivotPackage.STATE_MACHINE__OWNING_TRANSITION:
				return basicSetOwningTransition(null, msgs);
			case PivotPackage.STATE_MACHINE__OWNED_CONNECTION_POINTS:
				return ((InternalEList<?>)getOwnedConnectionPoints()).basicRemove(otherEnd, msgs);
			case PivotPackage.STATE_MACHINE__OWNED_REGIONS:
				return ((InternalEList<?>)getOwnedRegions()).basicRemove(otherEnd, msgs);
			case PivotPackage.STATE_MACHINE__SUBMACHINE_STATES:
				return ((InternalEList<?>)getSubmachineStates()).basicRemove(otherEnd, msgs);
		}
		return eDynamicInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch (featureID)
		{
			case PivotPackage.STATE_MACHINE__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.STATE_MACHINE__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.STATE_MACHINE__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.STATE_MACHINE__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.STATE_MACHINE__NAME:
				return getName();
			case PivotPackage.STATE_MACHINE__OWNED_CONSTRAINTS:
				return getOwnedConstraints();
			case PivotPackage.STATE_MACHINE__OWNED_BINDINGS:
				return getOwnedBindings();
			case PivotPackage.STATE_MACHINE__OWNED_SIGNATURE:
				return getOwnedSignature();
			case PivotPackage.STATE_MACHINE__UNSPECIALIZED_ELEMENT:
				return getUnspecializedElement();
			case PivotPackage.STATE_MACHINE__EXTENDERS:
				return getExtenders();
			case PivotPackage.STATE_MACHINE__INSTANCE_CLASS_NAME:
				return getInstanceClassName();
			case PivotPackage.STATE_MACHINE__IS_ABSTRACT:
				return isIsAbstract();
			case PivotPackage.STATE_MACHINE__IS_ACTIVE:
				return isIsActive();
			case PivotPackage.STATE_MACHINE__IS_INTERFACE:
				return isIsInterface();
			case PivotPackage.STATE_MACHINE__OWNED_BEHAVIORS:
				return getOwnedBehaviors();
			case PivotPackage.STATE_MACHINE__OWNED_INVARIANTS:
				return getOwnedInvariants();
			case PivotPackage.STATE_MACHINE__OWNED_OPERATIONS:
				return getOwnedOperations();
			case PivotPackage.STATE_MACHINE__OWNED_PROPERTIES:
				return getOwnedProperties();
			case PivotPackage.STATE_MACHINE__OWNING_PACKAGE:
				return getOwningPackage();
			case PivotPackage.STATE_MACHINE__SUPER_CLASSES:
				return getSuperClasses();
			case PivotPackage.STATE_MACHINE__OWNING_TRANSITION:
				return getOwningTransition();
			case PivotPackage.STATE_MACHINE__EXTENDED_STATE_MACHINES:
				return getExtendedStateMachines();
			case PivotPackage.STATE_MACHINE__OWNED_CONNECTION_POINTS:
				return getOwnedConnectionPoints();
			case PivotPackage.STATE_MACHINE__OWNED_REGIONS:
				return getOwnedRegions();
			case PivotPackage.STATE_MACHINE__SUBMACHINE_STATES:
				return getSubmachineStates();
		}
		return eDynamicGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case PivotPackage.STATE_MACHINE__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.STATE_MACHINE__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.STATE_MACHINE__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.STATE_MACHINE__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.STATE_MACHINE__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.STATE_MACHINE__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				getOwnedConstraints().addAll((Collection<? extends Constraint>)newValue);
				return;
			case PivotPackage.STATE_MACHINE__OWNED_BINDINGS:
				getOwnedBindings().clear();
				getOwnedBindings().addAll((Collection<? extends TemplateBinding>)newValue);
				return;
			case PivotPackage.STATE_MACHINE__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignature)newValue);
				return;
			case PivotPackage.STATE_MACHINE__UNSPECIALIZED_ELEMENT:
				setUnspecializedElement((TemplateableElement)newValue);
				return;
			case PivotPackage.STATE_MACHINE__EXTENDERS:
				getExtenders().clear();
				getExtenders().addAll((Collection<? extends StereotypeExtender>)newValue);
				return;
			case PivotPackage.STATE_MACHINE__INSTANCE_CLASS_NAME:
				setInstanceClassName((String)newValue);
				return;
			case PivotPackage.STATE_MACHINE__IS_ABSTRACT:
				setIsAbstract((Boolean)newValue);
				return;
			case PivotPackage.STATE_MACHINE__IS_ACTIVE:
				setIsActive((Boolean)newValue);
				return;
			case PivotPackage.STATE_MACHINE__IS_INTERFACE:
				setIsInterface((Boolean)newValue);
				return;
			case PivotPackage.STATE_MACHINE__OWNED_BEHAVIORS:
				getOwnedBehaviors().clear();
				getOwnedBehaviors().addAll((Collection<? extends Behavior>)newValue);
				return;
			case PivotPackage.STATE_MACHINE__OWNED_INVARIANTS:
				getOwnedInvariants().clear();
				getOwnedInvariants().addAll((Collection<? extends Constraint>)newValue);
				return;
			case PivotPackage.STATE_MACHINE__OWNED_OPERATIONS:
				getOwnedOperations().clear();
				getOwnedOperations().addAll((Collection<? extends Operation>)newValue);
				return;
			case PivotPackage.STATE_MACHINE__OWNED_PROPERTIES:
				getOwnedProperties().clear();
				getOwnedProperties().addAll((Collection<? extends Property>)newValue);
				return;
			case PivotPackage.STATE_MACHINE__OWNING_PACKAGE:
				setOwningPackage((org.eclipse.ocl.pivot.Package)newValue);
				return;
			case PivotPackage.STATE_MACHINE__SUPER_CLASSES:
				getSuperClasses().clear();
				getSuperClasses().addAll((Collection<? extends org.eclipse.ocl.pivot.Class>)newValue);
				return;
			case PivotPackage.STATE_MACHINE__OWNING_TRANSITION:
				setOwningTransition((Transition)newValue);
				return;
			case PivotPackage.STATE_MACHINE__EXTENDED_STATE_MACHINES:
				getExtendedStateMachines().clear();
				getExtendedStateMachines().addAll((Collection<? extends StateMachine>)newValue);
				return;
			case PivotPackage.STATE_MACHINE__OWNED_CONNECTION_POINTS:
				getOwnedConnectionPoints().clear();
				getOwnedConnectionPoints().addAll((Collection<? extends Pseudostate>)newValue);
				return;
			case PivotPackage.STATE_MACHINE__OWNED_REGIONS:
				getOwnedRegions().clear();
				getOwnedRegions().addAll((Collection<? extends Region>)newValue);
				return;
			case PivotPackage.STATE_MACHINE__SUBMACHINE_STATES:
				getSubmachineStates().clear();
				getSubmachineStates().addAll((Collection<? extends State>)newValue);
				return;
		}
		eDynamicSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID)
	{
		switch (featureID)
		{
			case PivotPackage.STATE_MACHINE__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.STATE_MACHINE__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.STATE_MACHINE__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.STATE_MACHINE__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.STATE_MACHINE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.STATE_MACHINE__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				return;
			case PivotPackage.STATE_MACHINE__OWNED_BINDINGS:
				getOwnedBindings().clear();
				return;
			case PivotPackage.STATE_MACHINE__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignature)null);
				return;
			case PivotPackage.STATE_MACHINE__UNSPECIALIZED_ELEMENT:
				setUnspecializedElement((TemplateableElement)null);
				return;
			case PivotPackage.STATE_MACHINE__EXTENDERS:
				getExtenders().clear();
				return;
			case PivotPackage.STATE_MACHINE__INSTANCE_CLASS_NAME:
				setInstanceClassName(INSTANCE_CLASS_NAME_EDEFAULT);
				return;
			case PivotPackage.STATE_MACHINE__IS_ABSTRACT:
				setIsAbstract(IS_ABSTRACT_EDEFAULT);
				return;
			case PivotPackage.STATE_MACHINE__IS_ACTIVE:
				setIsActive(IS_ACTIVE_EDEFAULT);
				return;
			case PivotPackage.STATE_MACHINE__IS_INTERFACE:
				setIsInterface(IS_INTERFACE_EDEFAULT);
				return;
			case PivotPackage.STATE_MACHINE__OWNED_BEHAVIORS:
				getOwnedBehaviors().clear();
				return;
			case PivotPackage.STATE_MACHINE__OWNED_INVARIANTS:
				getOwnedInvariants().clear();
				return;
			case PivotPackage.STATE_MACHINE__OWNED_OPERATIONS:
				getOwnedOperations().clear();
				return;
			case PivotPackage.STATE_MACHINE__OWNED_PROPERTIES:
				getOwnedProperties().clear();
				return;
			case PivotPackage.STATE_MACHINE__OWNING_PACKAGE:
				setOwningPackage((org.eclipse.ocl.pivot.Package)null);
				return;
			case PivotPackage.STATE_MACHINE__SUPER_CLASSES:
				getSuperClasses().clear();
				return;
			case PivotPackage.STATE_MACHINE__OWNING_TRANSITION:
				setOwningTransition((Transition)null);
				return;
			case PivotPackage.STATE_MACHINE__EXTENDED_STATE_MACHINES:
				getExtendedStateMachines().clear();
				return;
			case PivotPackage.STATE_MACHINE__OWNED_CONNECTION_POINTS:
				getOwnedConnectionPoints().clear();
				return;
			case PivotPackage.STATE_MACHINE__OWNED_REGIONS:
				getOwnedRegions().clear();
				return;
			case PivotPackage.STATE_MACHINE__SUBMACHINE_STATES:
				getSubmachineStates().clear();
				return;
		}
		eDynamicUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch (featureID)
		{
			case PivotPackage.STATE_MACHINE__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.STATE_MACHINE__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.STATE_MACHINE__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.STATE_MACHINE__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.STATE_MACHINE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.STATE_MACHINE__OWNED_CONSTRAINTS:
				return ownedConstraints != null && !ownedConstraints.isEmpty();
			case PivotPackage.STATE_MACHINE__OWNED_BINDINGS:
				return ownedBindings != null && !ownedBindings.isEmpty();
			case PivotPackage.STATE_MACHINE__OWNED_SIGNATURE:
				return ownedSignature != null;
			case PivotPackage.STATE_MACHINE__UNSPECIALIZED_ELEMENT:
				return unspecializedElement != null;
			case PivotPackage.STATE_MACHINE__EXTENDERS:
				return extenders != null && !extenders.isEmpty();
			case PivotPackage.STATE_MACHINE__INSTANCE_CLASS_NAME:
				return INSTANCE_CLASS_NAME_EDEFAULT == null ? instanceClassName != null : !INSTANCE_CLASS_NAME_EDEFAULT.equals(instanceClassName);
			case PivotPackage.STATE_MACHINE__IS_ABSTRACT:
				return ((eFlags & IS_ABSTRACT_EFLAG) != 0) != IS_ABSTRACT_EDEFAULT;
			case PivotPackage.STATE_MACHINE__IS_ACTIVE:
				return ((eFlags & IS_ACTIVE_EFLAG) != 0) != IS_ACTIVE_EDEFAULT;
			case PivotPackage.STATE_MACHINE__IS_INTERFACE:
				return ((eFlags & IS_INTERFACE_EFLAG) != 0) != IS_INTERFACE_EDEFAULT;
			case PivotPackage.STATE_MACHINE__OWNED_BEHAVIORS:
				return ownedBehaviors != null && !ownedBehaviors.isEmpty();
			case PivotPackage.STATE_MACHINE__OWNED_INVARIANTS:
				return ownedInvariants != null && !ownedInvariants.isEmpty();
			case PivotPackage.STATE_MACHINE__OWNED_OPERATIONS:
				return ownedOperations != null && !ownedOperations.isEmpty();
			case PivotPackage.STATE_MACHINE__OWNED_PROPERTIES:
				return ownedProperties != null && !ownedProperties.isEmpty();
			case PivotPackage.STATE_MACHINE__OWNING_PACKAGE:
				return getOwningPackage() != null;
			case PivotPackage.STATE_MACHINE__SUPER_CLASSES:
				return superClasses != null && !superClasses.isEmpty();
			case PivotPackage.STATE_MACHINE__OWNING_TRANSITION:
				return getOwningTransition() != null;
			case PivotPackage.STATE_MACHINE__EXTENDED_STATE_MACHINES:
				return extendedStateMachines != null && !extendedStateMachines.isEmpty();
			case PivotPackage.STATE_MACHINE__OWNED_CONNECTION_POINTS:
				return ownedConnectionPoints != null && !ownedConnectionPoints.isEmpty();
			case PivotPackage.STATE_MACHINE__OWNED_REGIONS:
				return ownedRegions != null && !ownedRegions.isEmpty();
			case PivotPackage.STATE_MACHINE__SUBMACHINE_STATES:
				return submachineStates != null && !submachineStates.isEmpty();
		}
		return eDynamicIsSet(featureID);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitStateMachine(this);
	}
} //StateMachineImpl
