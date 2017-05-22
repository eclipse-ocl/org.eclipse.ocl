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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.ConnectionPointReference;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Pseudostate;
import org.eclipse.ocl.pivot.Region;
import org.eclipse.ocl.pivot.State;
import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connection Point Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ConnectionPointReferenceImpl#getEntries <em>Entries</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ConnectionPointReferenceImpl#getExits <em>Exits</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ConnectionPointReferenceImpl#getOwningState <em>Owning State</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConnectionPointReferenceImpl extends VertexImpl implements ConnectionPointReference
{
	/**
	 * The cached value of the '{@link #getEntries() <em>Entries</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntries()
	 * @generated
	 * @ordered
	 */
	protected EList<Pseudostate> entries;

	/**
	 * The cached value of the '{@link #getExits() <em>Exits</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExits()
	 * @generated
	 * @ordered
	 */
	protected EList<Pseudostate> exits;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectionPointReferenceImpl()
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
		return PivotPackage.Literals.CONNECTION_POINT_REFERENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Pseudostate> getEntries()
	{
		if (entries == null)
		{
			entries = new EObjectResolvingEList<Pseudostate>(Pseudostate.class, this, PivotPackage.CONNECTION_POINT_REFERENCE__ENTRIES);
		}
		return entries;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Pseudostate> getExits()
	{
		if (exits == null)
		{
			exits = new EObjectResolvingEList<Pseudostate>(Pseudostate.class, this, PivotPackage.CONNECTION_POINT_REFERENCE__EXITS);
		}
		return exits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public State getOwningState()
	{
		if (eContainerFeatureID() != PivotPackage.CONNECTION_POINT_REFERENCE__OWNING_STATE) return null;
		return (State)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningState(State newOwningState, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningState, PivotPackage.CONNECTION_POINT_REFERENCE__OWNING_STATE, msgs);
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
		if (newOwningState != eInternalContainer() || (eContainerFeatureID() != PivotPackage.CONNECTION_POINT_REFERENCE__OWNING_STATE && newOwningState != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningState))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningState != null)
				msgs = ((InternalEObject)newOwningState).eInverseAdd(this, PivotPackage.STATE__OWNED_CONNECTIONS, State.class, msgs);
			msgs = basicSetOwningState(newOwningState, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.CONNECTION_POINT_REFERENCE__OWNING_STATE, newOwningState, newOwningState));
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
			case PivotPackage.CONNECTION_POINT_REFERENCE__ANNOTATING_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNED_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNED_EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case PivotPackage.CONNECTION_POINT_REFERENCE__INCOMING_TRANSITIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncomingTransitions()).basicAdd(otherEnd, msgs);
			case PivotPackage.CONNECTION_POINT_REFERENCE__OUTGOING_TRANSITIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoingTransitions()).basicAdd(otherEnd, msgs);
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNING_REGION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningRegion((Region)otherEnd, msgs);
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNING_STATE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningState((State)otherEnd, msgs);
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
			case PivotPackage.CONNECTION_POINT_REFERENCE__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.CONNECTION_POINT_REFERENCE__INCOMING_TRANSITIONS:
				return ((InternalEList<?>)getIncomingTransitions()).basicRemove(otherEnd, msgs);
			case PivotPackage.CONNECTION_POINT_REFERENCE__OUTGOING_TRANSITIONS:
				return ((InternalEList<?>)getOutgoingTransitions()).basicRemove(otherEnd, msgs);
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNING_REGION:
				return basicSetOwningRegion(null, msgs);
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNING_STATE:
				return basicSetOwningState(null, msgs);
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
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNING_REGION:
				return eInternalContainer().eInverseRemove(this, PivotPackage.REGION__OWNED_SUBVERTEXES, Region.class, msgs);
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNING_STATE:
				return eInternalContainer().eInverseRemove(this, PivotPackage.STATE__OWNED_CONNECTIONS, State.class, msgs);
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
			case PivotPackage.CONNECTION_POINT_REFERENCE__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.CONNECTION_POINT_REFERENCE__NAME:
				return getName();
			case PivotPackage.CONNECTION_POINT_REFERENCE__INCOMING_TRANSITIONS:
				return getIncomingTransitions();
			case PivotPackage.CONNECTION_POINT_REFERENCE__OUTGOING_TRANSITIONS:
				return getOutgoingTransitions();
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNING_REGION:
				return getOwningRegion();
			case PivotPackage.CONNECTION_POINT_REFERENCE__ENTRIES:
				return getEntries();
			case PivotPackage.CONNECTION_POINT_REFERENCE__EXITS:
				return getExits();
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNING_STATE:
				return getOwningState();
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
			case PivotPackage.CONNECTION_POINT_REFERENCE__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.CONNECTION_POINT_REFERENCE__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNING_REGION:
				setOwningRegion((Region)newValue);
				return;
			case PivotPackage.CONNECTION_POINT_REFERENCE__ENTRIES:
				getEntries().clear();
				getEntries().addAll((Collection<? extends Pseudostate>)newValue);
				return;
			case PivotPackage.CONNECTION_POINT_REFERENCE__EXITS:
				getExits().clear();
				getExits().addAll((Collection<? extends Pseudostate>)newValue);
				return;
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNING_STATE:
				setOwningState((State)newValue);
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
			case PivotPackage.CONNECTION_POINT_REFERENCE__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.CONNECTION_POINT_REFERENCE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNING_REGION:
				setOwningRegion((Region)null);
				return;
			case PivotPackage.CONNECTION_POINT_REFERENCE__ENTRIES:
				getEntries().clear();
				return;
			case PivotPackage.CONNECTION_POINT_REFERENCE__EXITS:
				getExits().clear();
				return;
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNING_STATE:
				setOwningState((State)null);
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
			case PivotPackage.CONNECTION_POINT_REFERENCE__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.CONNECTION_POINT_REFERENCE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.CONNECTION_POINT_REFERENCE__INCOMING_TRANSITIONS:
				return incomingTransitions != null && !incomingTransitions.isEmpty();
			case PivotPackage.CONNECTION_POINT_REFERENCE__OUTGOING_TRANSITIONS:
				return outgoingTransitions != null && !outgoingTransitions.isEmpty();
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNING_REGION:
				return getOwningRegion() != null;
			case PivotPackage.CONNECTION_POINT_REFERENCE__ENTRIES:
				return entries != null && !entries.isEmpty();
			case PivotPackage.CONNECTION_POINT_REFERENCE__EXITS:
				return exits != null && !exits.isEmpty();
			case PivotPackage.CONNECTION_POINT_REFERENCE__OWNING_STATE:
				return getOwningState() != null;
		}
		return eDynamicIsSet(featureID);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitConnectionPointReference(this);
	}
} //ConnectionPointReferenceImpl
