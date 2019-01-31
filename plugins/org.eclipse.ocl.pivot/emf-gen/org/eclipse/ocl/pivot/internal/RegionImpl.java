/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
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
	 * The number of structural features of the '<em>Region</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int REGION_FEATURE_COUNT = NamespaceImpl.NAMESPACE_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Region</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int REGION_OPERATION_COUNT = NamespaceImpl.NAMESPACE_OPERATION_COUNT + 0;

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
			case 0:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case 2:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case 3:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 1:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedSubvertexes()).basicAdd(otherEnd, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 2:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedTransitions()).basicAdd(otherEnd, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 3:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningState((State)otherEnd, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 4:
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 0, oldExtendedRegion, extendedRegion));
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
			eNotify(new ENotificationImpl(this, Notification.SET, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 0, oldExtendedRegion, extendedRegion));
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
			ownedSubvertexes = new EObjectContainmentWithInverseEList<Vertex>(Vertex.class, this, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 1, NamedElementImpl.NAMED_ELEMENT_FEATURE_COUNT + 2);
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
			ownedTransitions = new EObjectContainmentWithInverseEList<Transition>(Transition.class, this, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 2, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 4);
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
		if (eContainerFeatureID() != (NamespaceImpl.NAMESPACE_FEATURE_COUNT + 3)) return null;
		return (State)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningState(State newOwningState, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningState, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 3, msgs);
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
		if (newOwningState != eInternalContainer() || (eContainerFeatureID() != (NamespaceImpl.NAMESPACE_FEATURE_COUNT + 3) && newOwningState != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningState))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningState != null)
				msgs = ((InternalEObject)newOwningState).eInverseAdd(this, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 13, State.class, msgs);
			msgs = basicSetOwningState(newOwningState, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 3, newOwningState, newOwningState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StateMachine getOwningStateMachine()
	{
		if (eContainerFeatureID() != (NamespaceImpl.NAMESPACE_FEATURE_COUNT + 4)) return null;
		return (StateMachine)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningStateMachine(StateMachine newOwningStateMachine, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningStateMachine, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 4, msgs);
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
		if (newOwningStateMachine != eInternalContainer() || (eContainerFeatureID() != (NamespaceImpl.NAMESPACE_FEATURE_COUNT + 4) && newOwningStateMachine != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningStateMachine))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningStateMachine != null)
				msgs = ((InternalEObject)newOwningStateMachine).eInverseAdd(this, BehaviorImpl.BEHAVIOR_FEATURE_COUNT + 2, StateMachine.class, msgs);
			msgs = basicSetOwningStateMachine(newOwningStateMachine, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 4, newOwningStateMachine, newOwningStateMachine));
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
			case 0:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case 1:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case 2:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case 3:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case 5:
				return ((InternalEList<?>)getOwnedConstraints()).basicRemove(otherEnd, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 1:
				return ((InternalEList<?>)getOwnedSubvertexes()).basicRemove(otherEnd, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 2:
				return ((InternalEList<?>)getOwnedTransitions()).basicRemove(otherEnd, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 3:
				return basicSetOwningState(null, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 4:
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
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 3:
				return eInternalContainer().eInverseRemove(this, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 13, State.class, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 4:
				return eInternalContainer().eInverseRemove(this, BehaviorImpl.BEHAVIOR_FEATURE_COUNT + 2, StateMachine.class, msgs);
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
			case 0:
				return getAnnotatingComments();
			case 1:
				return getOwnedAnnotations();
			case 2:
				return getOwnedComments();
			case 3:
				return getOwnedExtensions();
			case 4:
				return getName();
			case 5:
				return getOwnedConstraints();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 0:
				if (resolve) return getExtendedRegion();
				return basicGetExtendedRegion();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 1:
				return getOwnedSubvertexes();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 2:
				return getOwnedTransitions();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 3:
				return getOwningState();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 4:
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
			case 0:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 1:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case 2:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 3:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case 4:
				setName((String)newValue);
				return;
			case 5:
				getOwnedConstraints().clear();
				getOwnedConstraints().addAll((Collection<? extends Constraint>)newValue);
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 0:
				setExtendedRegion((Region)newValue);
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 1:
				getOwnedSubvertexes().clear();
				getOwnedSubvertexes().addAll((Collection<? extends Vertex>)newValue);
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 2:
				getOwnedTransitions().clear();
				getOwnedTransitions().addAll((Collection<? extends Transition>)newValue);
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 3:
				setOwningState((State)newValue);
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 4:
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
			case 0:
				getAnnotatingComments().clear();
				return;
			case 1:
				getOwnedAnnotations().clear();
				return;
			case 2:
				getOwnedComments().clear();
				return;
			case 3:
				getOwnedExtensions().clear();
				return;
			case 4:
				setName(NAME_EDEFAULT);
				return;
			case 5:
				getOwnedConstraints().clear();
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 0:
				setExtendedRegion((Region)null);
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 1:
				getOwnedSubvertexes().clear();
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 2:
				getOwnedTransitions().clear();
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 3:
				setOwningState((State)null);
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 4:
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
			case 0:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case 1:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case 2:
				return ownedComments != null && !ownedComments.isEmpty();
			case 3:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case 4:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case 5:
				return ownedConstraints != null && !ownedConstraints.isEmpty();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 0:
				return extendedRegion != null;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 1:
				return ownedSubvertexes != null && !ownedSubvertexes.isEmpty();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 2:
				return ownedTransitions != null && !ownedTransitions.isEmpty();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 3:
				return getOwningState() != null;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 4:
				return getOwningStateMachine() != null;
		}
		return eDynamicIsSet(featureID);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitRegion(this);
	}
} //RegionImpl
