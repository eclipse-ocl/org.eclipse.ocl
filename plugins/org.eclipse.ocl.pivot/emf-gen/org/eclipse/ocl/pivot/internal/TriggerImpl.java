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
import org.eclipse.ocl.pivot.State;
import org.eclipse.ocl.pivot.Transition;
import org.eclipse.ocl.pivot.Trigger;
import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Trigger</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.TriggerImpl#getOwningState <em>Owning State</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.TriggerImpl#getOwningTransition <em>Owning Transition</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TriggerImpl extends NamedElementImpl implements Trigger
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TriggerImpl()
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
		return PivotPackage.Literals.TRIGGER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public State getOwningState()
	{
		if (eContainerFeatureID() != PivotPackage.TRIGGER__OWNING_STATE) return null;
		return (State)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningState(State newOwningState, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningState, PivotPackage.TRIGGER__OWNING_STATE, msgs);
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
		if (newOwningState != eInternalContainer() || (eContainerFeatureID() != PivotPackage.TRIGGER__OWNING_STATE && newOwningState != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningState))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningState != null)
				msgs = ((InternalEObject)newOwningState).eInverseAdd(this, PivotPackage.STATE__OWNED_DEFERRABLE_TRIGGERS, State.class, msgs);
			msgs = basicSetOwningState(newOwningState, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.TRIGGER__OWNING_STATE, newOwningState, newOwningState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Transition getOwningTransition()
	{
		if (eContainerFeatureID() != PivotPackage.TRIGGER__OWNING_TRANSITION) return null;
		return (Transition)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningTransition(Transition newOwningTransition, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningTransition, PivotPackage.TRIGGER__OWNING_TRANSITION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningTransition(Transition newOwningTransition)
	{
		if (newOwningTransition != eInternalContainer() || (eContainerFeatureID() != PivotPackage.TRIGGER__OWNING_TRANSITION && newOwningTransition != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningTransition))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningTransition != null)
				msgs = ((InternalEObject)newOwningTransition).eInverseAdd(this, PivotPackage.TRANSITION__OWNED_TRIGGERS, Transition.class, msgs);
			msgs = basicSetOwningTransition(newOwningTransition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.TRIGGER__OWNING_TRANSITION, newOwningTransition, newOwningTransition));
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
			case PivotPackage.TRIGGER__ANNOTATING_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.TRIGGER__OWNED_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.TRIGGER__OWNED_EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case PivotPackage.TRIGGER__OWNING_STATE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningState((State)otherEnd, msgs);
			case PivotPackage.TRIGGER__OWNING_TRANSITION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningTransition((Transition)otherEnd, msgs);
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
			case PivotPackage.TRIGGER__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.TRIGGER__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.TRIGGER__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.TRIGGER__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.TRIGGER__OWNING_STATE:
				return basicSetOwningState(null, msgs);
			case PivotPackage.TRIGGER__OWNING_TRANSITION:
				return basicSetOwningTransition(null, msgs);
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
			case PivotPackage.TRIGGER__OWNING_STATE:
				return eInternalContainer().eInverseRemove(this, PivotPackage.STATE__OWNED_DEFERRABLE_TRIGGERS, State.class, msgs);
			case PivotPackage.TRIGGER__OWNING_TRANSITION:
				return eInternalContainer().eInverseRemove(this, PivotPackage.TRANSITION__OWNED_TRIGGERS, Transition.class, msgs);
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
			case PivotPackage.TRIGGER__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.TRIGGER__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.TRIGGER__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.TRIGGER__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.TRIGGER__NAME:
				return getName();
			case PivotPackage.TRIGGER__OWNING_STATE:
				return getOwningState();
			case PivotPackage.TRIGGER__OWNING_TRANSITION:
				return getOwningTransition();
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
			case PivotPackage.TRIGGER__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.TRIGGER__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.TRIGGER__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.TRIGGER__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.TRIGGER__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.TRIGGER__OWNING_STATE:
				setOwningState((State)newValue);
				return;
			case PivotPackage.TRIGGER__OWNING_TRANSITION:
				setOwningTransition((Transition)newValue);
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
			case PivotPackage.TRIGGER__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.TRIGGER__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.TRIGGER__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.TRIGGER__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.TRIGGER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.TRIGGER__OWNING_STATE:
				setOwningState((State)null);
				return;
			case PivotPackage.TRIGGER__OWNING_TRANSITION:
				setOwningTransition((Transition)null);
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
			case PivotPackage.TRIGGER__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.TRIGGER__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.TRIGGER__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.TRIGGER__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.TRIGGER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.TRIGGER__OWNING_STATE:
				return getOwningState() != null;
			case PivotPackage.TRIGGER__OWNING_TRANSITION:
				return getOwningTransition() != null;
		}
		return eDynamicIsSet(featureID);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitTrigger(this);
	}
} //TriggerImpl
