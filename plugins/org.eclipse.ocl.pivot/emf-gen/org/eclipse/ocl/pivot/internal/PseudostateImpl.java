/*******************************************************************************
 * Copyright (c) 2012, 2015 Willink Transformations and others.
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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Pseudostate;
import org.eclipse.ocl.pivot.PseudostateKind;
import org.eclipse.ocl.pivot.Region;
import org.eclipse.ocl.pivot.State;
import org.eclipse.ocl.pivot.StateMachine;
import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pseudostate</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.PseudostateImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.PseudostateImpl#getOwningState <em>Owning State</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.PseudostateImpl#getOwningStateMachine <em>Owning State Machine</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PseudostateImpl extends VertexImpl implements Pseudostate
{
	/**
	 * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected static final PseudostateKind KIND_EDEFAULT = PseudostateKind.INITIAL;

	/**
	 * The offset of the flags representing the value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected static final int KIND_EFLAG_OFFSET = 8;

	/**
	 * The flags representing the default value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected static final int KIND_EFLAG_DEFAULT = KIND_EDEFAULT.ordinal() << KIND_EFLAG_OFFSET;

	/**
	 * The array of enumeration values for '{@link PseudostateKind Pseudostate Kind}'
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	private static final PseudostateKind[] KIND_EFLAG_VALUES = PseudostateKind.values();

	/**
	 * The flags representing the value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected static final int KIND_EFLAG = 0xf << KIND_EFLAG_OFFSET;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PseudostateImpl()
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
		return PivotPackage.Literals.PSEUDOSTATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PseudostateKind getKind()
	{
		return KIND_EFLAG_VALUES[(eFlags & KIND_EFLAG) >>> KIND_EFLAG_OFFSET];
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setKind(PseudostateKind newKind)
	{
		PseudostateKind oldKind = KIND_EFLAG_VALUES[(eFlags & KIND_EFLAG) >>> KIND_EFLAG_OFFSET];
		if (newKind == null) newKind = KIND_EDEFAULT;
		eFlags = eFlags & ~KIND_EFLAG | newKind.ordinal() << KIND_EFLAG_OFFSET;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PSEUDOSTATE__KIND, oldKind, newKind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public State getOwningState()
	{
		if (eContainerFeatureID() != PivotPackage.PSEUDOSTATE__OWNING_STATE) return null;
		return (State)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningState(State newOwningState, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningState, PivotPackage.PSEUDOSTATE__OWNING_STATE, msgs);
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
		if (newOwningState != eInternalContainer() || (eContainerFeatureID() != PivotPackage.PSEUDOSTATE__OWNING_STATE && newOwningState != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningState))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningState != null)
				msgs = ((InternalEObject)newOwningState).eInverseAdd(this, PivotPackage.STATE__OWNED_CONNECTION_POINTS, State.class, msgs);
			msgs = basicSetOwningState(newOwningState, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PSEUDOSTATE__OWNING_STATE, newOwningState, newOwningState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StateMachine getOwningStateMachine()
	{
		if (eContainerFeatureID() != PivotPackage.PSEUDOSTATE__OWNING_STATE_MACHINE) return null;
		return (StateMachine)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningStateMachine(StateMachine newOwningStateMachine, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningStateMachine, PivotPackage.PSEUDOSTATE__OWNING_STATE_MACHINE, msgs);
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
		if (newOwningStateMachine != eInternalContainer() || (eContainerFeatureID() != PivotPackage.PSEUDOSTATE__OWNING_STATE_MACHINE && newOwningStateMachine != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningStateMachine))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningStateMachine != null)
				msgs = ((InternalEObject)newOwningStateMachine).eInverseAdd(this, PivotPackage.STATE_MACHINE__OWNED_CONNECTION_POINTS, StateMachine.class, msgs);
			msgs = basicSetOwningStateMachine(newOwningStateMachine, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PSEUDOSTATE__OWNING_STATE_MACHINE, newOwningStateMachine, newOwningStateMachine));
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
			case PivotPackage.PSEUDOSTATE__ANNOTATING_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.PSEUDOSTATE__OWNED_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.PSEUDOSTATE__OWNED_EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case PivotPackage.PSEUDOSTATE__INCOMING_TRANSITIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncomingTransitions()).basicAdd(otherEnd, msgs);
			case PivotPackage.PSEUDOSTATE__OUTGOING_TRANSITIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoingTransitions()).basicAdd(otherEnd, msgs);
			case PivotPackage.PSEUDOSTATE__OWNING_REGION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningRegion((Region)otherEnd, msgs);
			case PivotPackage.PSEUDOSTATE__OWNING_STATE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningState((State)otherEnd, msgs);
			case PivotPackage.PSEUDOSTATE__OWNING_STATE_MACHINE:
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
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case PivotPackage.PSEUDOSTATE__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.PSEUDOSTATE__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.PSEUDOSTATE__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.PSEUDOSTATE__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.PSEUDOSTATE__INCOMING_TRANSITIONS:
				return ((InternalEList<?>)getIncomingTransitions()).basicRemove(otherEnd, msgs);
			case PivotPackage.PSEUDOSTATE__OUTGOING_TRANSITIONS:
				return ((InternalEList<?>)getOutgoingTransitions()).basicRemove(otherEnd, msgs);
			case PivotPackage.PSEUDOSTATE__OWNING_REGION:
				return basicSetOwningRegion(null, msgs);
			case PivotPackage.PSEUDOSTATE__OWNING_STATE:
				return basicSetOwningState(null, msgs);
			case PivotPackage.PSEUDOSTATE__OWNING_STATE_MACHINE:
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
			case PivotPackage.PSEUDOSTATE__OWNING_REGION:
				return eInternalContainer().eInverseRemove(this, PivotPackage.REGION__OWNED_SUBVERTEXES, Region.class, msgs);
			case PivotPackage.PSEUDOSTATE__OWNING_STATE:
				return eInternalContainer().eInverseRemove(this, PivotPackage.STATE__OWNED_CONNECTION_POINTS, State.class, msgs);
			case PivotPackage.PSEUDOSTATE__OWNING_STATE_MACHINE:
				return eInternalContainer().eInverseRemove(this, PivotPackage.STATE_MACHINE__OWNED_CONNECTION_POINTS, StateMachine.class, msgs);
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
			case PivotPackage.PSEUDOSTATE__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.PSEUDOSTATE__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.PSEUDOSTATE__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.PSEUDOSTATE__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.PSEUDOSTATE__NAME:
				return getName();
			case PivotPackage.PSEUDOSTATE__INCOMING_TRANSITIONS:
				return getIncomingTransitions();
			case PivotPackage.PSEUDOSTATE__OUTGOING_TRANSITIONS:
				return getOutgoingTransitions();
			case PivotPackage.PSEUDOSTATE__OWNING_REGION:
				return getOwningRegion();
			case PivotPackage.PSEUDOSTATE__KIND:
				return getKind();
			case PivotPackage.PSEUDOSTATE__OWNING_STATE:
				return getOwningState();
			case PivotPackage.PSEUDOSTATE__OWNING_STATE_MACHINE:
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
			case PivotPackage.PSEUDOSTATE__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.PSEUDOSTATE__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.PSEUDOSTATE__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.PSEUDOSTATE__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.PSEUDOSTATE__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.PSEUDOSTATE__OWNING_REGION:
				setOwningRegion((Region)newValue);
				return;
			case PivotPackage.PSEUDOSTATE__KIND:
				setKind((PseudostateKind)newValue);
				return;
			case PivotPackage.PSEUDOSTATE__OWNING_STATE:
				setOwningState((State)newValue);
				return;
			case PivotPackage.PSEUDOSTATE__OWNING_STATE_MACHINE:
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
			case PivotPackage.PSEUDOSTATE__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.PSEUDOSTATE__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.PSEUDOSTATE__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.PSEUDOSTATE__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.PSEUDOSTATE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.PSEUDOSTATE__OWNING_REGION:
				setOwningRegion((Region)null);
				return;
			case PivotPackage.PSEUDOSTATE__KIND:
				setKind(KIND_EDEFAULT);
				return;
			case PivotPackage.PSEUDOSTATE__OWNING_STATE:
				setOwningState((State)null);
				return;
			case PivotPackage.PSEUDOSTATE__OWNING_STATE_MACHINE:
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
			case PivotPackage.PSEUDOSTATE__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.PSEUDOSTATE__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.PSEUDOSTATE__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.PSEUDOSTATE__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.PSEUDOSTATE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.PSEUDOSTATE__INCOMING_TRANSITIONS:
				return incomingTransitions != null && !incomingTransitions.isEmpty();
			case PivotPackage.PSEUDOSTATE__OUTGOING_TRANSITIONS:
				return outgoingTransitions != null && !outgoingTransitions.isEmpty();
			case PivotPackage.PSEUDOSTATE__OWNING_REGION:
				return getOwningRegion() != null;
			case PivotPackage.PSEUDOSTATE__KIND:
				return (eFlags & KIND_EFLAG) != KIND_EFLAG_DEFAULT;
			case PivotPackage.PSEUDOSTATE__OWNING_STATE:
				return getOwningState() != null;
			case PivotPackage.PSEUDOSTATE__OWNING_STATE_MACHINE:
				return getOwningStateMachine() != null;
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String toString()
	{
		return super.toString();
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitPseudostate(this);
	}
} //PseudostateImpl
