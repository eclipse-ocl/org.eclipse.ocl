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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Region;
import org.eclipse.ocl.pivot.State;
import org.eclipse.ocl.pivot.StateMachine;
import org.eclipse.ocl.pivot.Transition;
import org.eclipse.ocl.pivot.Vertex;
import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Region</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.RegionImpl#getExtendedRegion <em>Extended Region</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.RegionImpl#getOwnedSubvertexes <em>Owned Subvertexes</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.RegionImpl#getOwnedTransitions <em>Owned Transitions</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.RegionImpl#getOwningState <em>Owning State</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.RegionImpl#getOwningStateMachine <em>Owning State Machine</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RegionImpl extends NamespaceImpl implements Region
{
	/**
	 * The cached value of the '{@link #getExtendedRegion() <em>Extended Region</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtendedRegion()
	 * @generated
	 * @ordered
	 */
	protected Region extendedRegion;

	/**
	 * The cached value of the '{@link #getOwnedSubvertexes() <em>Owned Subvertexes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSubvertexes()
	 * @generated
	 * @ordered
	 */
	protected EList<Vertex> ownedSubvertexes;

	/**
	 * The cached value of the '{@link #getOwnedTransitions() <em>Owned Transitions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedTransitions()
	 * @generated
	 * @ordered
	 */
	protected EList<Transition> ownedTransitions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RegionImpl()
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
		return PivotPackage.Literals.REGION;
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
			case PivotPackage.REGION__ANNOTATING_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.REGION__OWNED_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.REGION__OWNED_EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case PivotPackage.REGION__OWNED_SUBVERTEXES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedSubvertexes()).basicAdd(otherEnd, msgs);
			case PivotPackage.REGION__OWNED_TRANSITIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedTransitions()).basicAdd(otherEnd, msgs);
			case PivotPackage.REGION__OWNING_STATE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningState((State)otherEnd, msgs);
			case PivotPackage.REGION__OWNING_STATE_MACHINE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningStateMachine((StateMachine)otherEnd, msgs);
		}
		return eDynamicInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Region getExtendedRegion()
	{
		if (extendedRegion != null && extendedRegion.eIsProxy())
		{
			InternalEObject oldExtendedRegion = (InternalEObject)extendedRegion;
			extendedRegion = (Region)eResolveProxy(oldExtendedRegion);
			if (extendedRegion != oldExtendedRegion)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.REGION__EXTENDED_REGION, oldExtendedRegion, extendedRegion));
			}
		}
		return extendedRegion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Region basicGetExtendedRegion()
	{
		return extendedRegion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setExtendedRegion(Region newExtendedRegion)
	{
		Region oldExtendedRegion = extendedRegion;
		extendedRegion = newExtendedRegion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.REGION__EXTENDED_REGION, oldExtendedRegion, extendedRegion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Vertex> getOwnedSubvertexes()
	{
		if (ownedSubvertexes == null)
		{
			ownedSubvertexes = new EObjectContainmentWithInverseEList<Vertex>(Vertex.class, this, PivotPackage.REGION__OWNED_SUBVERTEXES, PivotPackage.VERTEX__OWNING_REGION);
		}
		return ownedSubvertexes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Transition> getOwnedTransitions()
	{
		if (ownedTransitions == null)
		{
			ownedTransitions = new EObjectContainmentWithInverseEList<Transition>(Transition.class, this, PivotPackage.REGION__OWNED_TRANSITIONS, PivotPackage.TRANSITION__OWNING_REGION);
		}
		return ownedTransitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public State getOwningState()
	{
		if (eContainerFeatureID() != PivotPackage.REGION__OWNING_STATE) return null;
		return (State)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningState(State newOwningState, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningState, PivotPackage.REGION__OWNING_STATE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningState(State newOwningState)
	{
		if (newOwningState != eInternalContainer() || (eContainerFeatureID() != PivotPackage.REGION__OWNING_STATE && newOwningState != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningState))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningState != null)
				msgs = ((InternalEObject)newOwningState).eInverseAdd(this, PivotPackage.STATE__OWNED_REGIONS, State.class, msgs);
			msgs = basicSetOwningState(newOwningState, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.REGION__OWNING_STATE, newOwningState, newOwningState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StateMachine getOwningStateMachine()
	{
		if (eContainerFeatureID() != PivotPackage.REGION__OWNING_STATE_MACHINE) return null;
		return (StateMachine)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningStateMachine(StateMachine newOwningStateMachine, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningStateMachine, PivotPackage.REGION__OWNING_STATE_MACHINE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningStateMachine(StateMachine newOwningStateMachine)
	{
		if (newOwningStateMachine != eInternalContainer() || (eContainerFeatureID() != PivotPackage.REGION__OWNING_STATE_MACHINE && newOwningStateMachine != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningStateMachine))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningStateMachine != null)
				msgs = ((InternalEObject)newOwningStateMachine).eInverseAdd(this, PivotPackage.STATE_MACHINE__OWNED_REGIONS, StateMachine.class, msgs);
			msgs = basicSetOwningStateMachine(newOwningStateMachine, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.REGION__OWNING_STATE_MACHINE, newOwningStateMachine, newOwningStateMachine));
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
			case PivotPackage.REGION__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.REGION__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.REGION__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.REGION__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.REGION__OWNED_CONSTRAINTS:
				return ((InternalEList<?>)getOwnedConstraints()).basicRemove(otherEnd, msgs);
			case PivotPackage.REGION__OWNED_SUBVERTEXES:
				return ((InternalEList<?>)getOwnedSubvertexes()).basicRemove(otherEnd, msgs);
			case PivotPackage.REGION__OWNED_TRANSITIONS:
				return ((InternalEList<?>)getOwnedTransitions()).basicRemove(otherEnd, msgs);
			case PivotPackage.REGION__OWNING_STATE:
				return basicSetOwningState(null, msgs);
			case PivotPackage.REGION__OWNING_STATE_MACHINE:
				return basicSetOwningStateMachine(null, msgs);
		}
		return eDynamicInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
	{
		switch (eContainerFeatureID())
		{
			case PivotPackage.REGION__OWNING_STATE:
				return eInternalContainer().eInverseRemove(this, PivotPackage.STATE__OWNED_REGIONS, State.class, msgs);
			case PivotPackage.REGION__OWNING_STATE_MACHINE:
				return eInternalContainer().eInverseRemove(this, PivotPackage.STATE_MACHINE__OWNED_REGIONS, StateMachine.class, msgs);
		}
		return eDynamicBasicRemoveFromContainer(msgs);
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
			case PivotPackage.REGION__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.REGION__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.REGION__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.REGION__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.REGION__NAME:
				return getName();
			case PivotPackage.REGION__OWNED_CONSTRAINTS:
				return getOwnedConstraints();
			case PivotPackage.REGION__EXTENDED_REGION:
				if (resolve) return getExtendedRegion();
				return basicGetExtendedRegion();
			case PivotPackage.REGION__OWNED_SUBVERTEXES:
				return getOwnedSubvertexes();
			case PivotPackage.REGION__OWNED_TRANSITIONS:
				return getOwnedTransitions();
			case PivotPackage.REGION__OWNING_STATE:
				return getOwningState();
			case PivotPackage.REGION__OWNING_STATE_MACHINE:
				return getOwningStateMachine();
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
			case PivotPackage.REGION__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.REGION__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.REGION__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.REGION__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.REGION__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.REGION__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				getOwnedConstraints().addAll((Collection<? extends Constraint>)newValue);
				return;
			case PivotPackage.REGION__EXTENDED_REGION:
				setExtendedRegion((Region)newValue);
				return;
			case PivotPackage.REGION__OWNED_SUBVERTEXES:
				getOwnedSubvertexes().clear();
				getOwnedSubvertexes().addAll((Collection<? extends Vertex>)newValue);
				return;
			case PivotPackage.REGION__OWNED_TRANSITIONS:
				getOwnedTransitions().clear();
				getOwnedTransitions().addAll((Collection<? extends Transition>)newValue);
				return;
			case PivotPackage.REGION__OWNING_STATE:
				setOwningState((State)newValue);
				return;
			case PivotPackage.REGION__OWNING_STATE_MACHINE:
				setOwningStateMachine((StateMachine)newValue);
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
			case PivotPackage.REGION__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.REGION__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.REGION__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.REGION__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.REGION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.REGION__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				return;
			case PivotPackage.REGION__EXTENDED_REGION:
				setExtendedRegion((Region)null);
				return;
			case PivotPackage.REGION__OWNED_SUBVERTEXES:
				getOwnedSubvertexes().clear();
				return;
			case PivotPackage.REGION__OWNED_TRANSITIONS:
				getOwnedTransitions().clear();
				return;
			case PivotPackage.REGION__OWNING_STATE:
				setOwningState((State)null);
				return;
			case PivotPackage.REGION__OWNING_STATE_MACHINE:
				setOwningStateMachine((StateMachine)null);
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
			case PivotPackage.REGION__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.REGION__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.REGION__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.REGION__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.REGION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.REGION__OWNED_CONSTRAINTS:
				return ownedConstraints != null && !ownedConstraints.isEmpty();
			case PivotPackage.REGION__EXTENDED_REGION:
				return extendedRegion != null;
			case PivotPackage.REGION__OWNED_SUBVERTEXES:
				return ownedSubvertexes != null && !ownedSubvertexes.isEmpty();
			case PivotPackage.REGION__OWNED_TRANSITIONS:
				return ownedTransitions != null && !ownedTransitions.isEmpty();
			case PivotPackage.REGION__OWNING_STATE:
				return getOwningState() != null;
			case PivotPackage.REGION__OWNING_STATE_MACHINE:
				return getOwningStateMachine() != null;
		}
		return eDynamicIsSet(featureID);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitRegion(this);
	}
} //RegionImpl
