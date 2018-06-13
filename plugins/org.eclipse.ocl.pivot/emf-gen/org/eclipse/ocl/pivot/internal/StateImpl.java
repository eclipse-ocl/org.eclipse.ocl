/*******************************************************************************
 * Copyright (c) 2010, 2015 Willink Transformations and others.
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
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Behavior;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.ConnectionPointReference;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Pseudostate;
import org.eclipse.ocl.pivot.Region;
import org.eclipse.ocl.pivot.State;
import org.eclipse.ocl.pivot.StateMachine;
import org.eclipse.ocl.pivot.Transition;
import org.eclipse.ocl.pivot.Trigger;
import org.eclipse.ocl.pivot.Vertex;
import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.StateImpl#getIncomingTransitions <em>Incoming Transitions</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.StateImpl#getOutgoingTransitions <em>Outgoing Transitions</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.StateImpl#getOwningRegion <em>Owning Region</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.StateImpl#isIsComposite <em>Is Composite</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.StateImpl#isIsOrthogonal <em>Is Orthogonal</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.StateImpl#isIsSimple <em>Is Simple</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.StateImpl#isIsSubmachineState <em>Is Submachine State</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.StateImpl#getOwnedConnectionPoints <em>Owned Connection Points</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.StateImpl#getOwnedConnections <em>Owned Connections</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.StateImpl#getOwnedDeferrableTriggers <em>Owned Deferrable Triggers</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.StateImpl#getOwnedDoActivity <em>Owned Do Activity</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.StateImpl#getOwnedEntry <em>Owned Entry</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.StateImpl#getOwnedExit <em>Owned Exit</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.StateImpl#getOwnedRegions <em>Owned Regions</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.StateImpl#getOwnedStateInvariant <em>Owned State Invariant</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.StateImpl#getRedefinedState <em>Redefined State</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.StateImpl#getSubmachines <em>Submachines</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StateImpl
		extends NamespaceImpl
		implements State {

	/**
	 * The cached value of the '{@link #getIncomingTransitions() <em>Incoming Transitions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncomingTransitions()
	 * @generated
	 * @ordered
	 */
	protected EList<Transition> incomingTransitions;
	/**
	 * The cached value of the '{@link #getOutgoingTransitions() <em>Outgoing Transitions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutgoingTransitions()
	 * @generated
	 * @ordered
	 */
	protected EList<Transition> outgoingTransitions;
	/**
	 * The default value of the '{@link #isIsComposite() <em>Is Composite</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsComposite()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_COMPOSITE_EDEFAULT = false;
	/**
	 * The default value of the '{@link #isIsOrthogonal() <em>Is Orthogonal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsOrthogonal()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_ORTHOGONAL_EDEFAULT = false;
	/**
	 * The default value of the '{@link #isIsSimple() <em>Is Simple</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsSimple()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_SIMPLE_EDEFAULT = false;
	/**
	 * The default value of the '{@link #isIsSubmachineState() <em>Is Submachine State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsSubmachineState()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_SUBMACHINE_STATE_EDEFAULT = false;
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
	 * The cached value of the '{@link #getOwnedConnections() <em>Owned Connections</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedConnections()
	 * @generated
	 * @ordered
	 */
	protected EList<ConnectionPointReference> ownedConnections;
	/**
	 * The cached value of the '{@link #getOwnedDeferrableTriggers() <em>Owned Deferrable Triggers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedDeferrableTriggers()
	 * @generated
	 * @ordered
	 */
	protected EList<Trigger> ownedDeferrableTriggers;
	/**
	 * The cached value of the '{@link #getOwnedDoActivity() <em>Owned Do Activity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedDoActivity()
	 * @generated
	 * @ordered
	 */
	protected Behavior ownedDoActivity;
	/**
	 * The cached value of the '{@link #getOwnedEntry() <em>Owned Entry</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedEntry()
	 * @generated
	 * @ordered
	 */
	protected Behavior ownedEntry;
	/**
	 * The cached value of the '{@link #getOwnedExit() <em>Owned Exit</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedExit()
	 * @generated
	 * @ordered
	 */
	protected Behavior ownedExit;
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
	 * The cached value of the '{@link #getOwnedStateInvariant() <em>Owned State Invariant</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedStateInvariant()
	 * @generated
	 * @ordered
	 */
	protected Constraint ownedStateInvariant;
	/**
	 * The cached value of the '{@link #getRedefinedState() <em>Redefined State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRedefinedState()
	 * @generated
	 * @ordered
	 */
	protected State redefinedState;
	/**
	 * The cached value of the '{@link #getSubmachines() <em>Submachines</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubmachines()
	 * @generated
	 * @ordered
	 */
	protected StateMachine submachines;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.STATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Transition> getIncomingTransitions()
	{
		if (incomingTransitions == null)
		{
			incomingTransitions = new EObjectWithInverseResolvingEList<Transition>(Transition.class, this, PivotPackage.STATE__INCOMING_TRANSITIONS, PivotPackage.TRANSITION__TARGET);
		}
		return incomingTransitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Transition> getOutgoingTransitions()
	{
		if (outgoingTransitions == null)
		{
			outgoingTransitions = new EObjectWithInverseResolvingEList<Transition>(Transition.class, this, PivotPackage.STATE__OUTGOING_TRANSITIONS, PivotPackage.TRANSITION__SOURCE);
		}
		return outgoingTransitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Region getOwningRegion()
	{
		if (eContainerFeatureID() != PivotPackage.STATE__OWNING_REGION) return null;
		return (Region)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningRegion(Region newOwningRegion, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningRegion, PivotPackage.STATE__OWNING_REGION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningRegion(Region newOwningRegion)
	{
		if (newOwningRegion != eInternalContainer() || (eContainerFeatureID() != PivotPackage.STATE__OWNING_REGION && newOwningRegion != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningRegion))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningRegion != null)
				msgs = ((InternalEObject)newOwningRegion).eInverseAdd(this, PivotPackage.REGION__OWNED_SUBVERTEXES, Region.class, msgs);
			msgs = basicSetOwningRegion(newOwningRegion, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.STATE__OWNING_REGION, newOwningRegion, newOwningRegion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean isIsComposite()
	{
		return getOwnedRegions().size() > 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean isIsOrthogonal()
	{
		return getOwnedRegions().size() > 1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean isIsSimple()
	{
		return getOwnedRegions().size() == 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean isIsSubmachineState()
	{
		return eContainer() instanceof StateMachine;
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
			ownedConnectionPoints = new EObjectContainmentWithInverseEList<Pseudostate>(Pseudostate.class, this, PivotPackage.STATE__OWNED_CONNECTION_POINTS, PivotPackage.PSEUDOSTATE__OWNING_STATE);
		}
		return ownedConnectionPoints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<ConnectionPointReference> getOwnedConnections()
	{
		if (ownedConnections == null)
		{
			ownedConnections = new EObjectContainmentWithInverseEList<ConnectionPointReference>(ConnectionPointReference.class, this, PivotPackage.STATE__OWNED_CONNECTIONS, PivotPackage.CONNECTION_POINT_REFERENCE__OWNING_STATE);
		}
		return ownedConnections;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Trigger> getOwnedDeferrableTriggers()
	{
		if (ownedDeferrableTriggers == null)
		{
			ownedDeferrableTriggers = new EObjectContainmentWithInverseEList<Trigger>(Trigger.class, this, PivotPackage.STATE__OWNED_DEFERRABLE_TRIGGERS, PivotPackage.TRIGGER__OWNING_STATE);
		}
		return ownedDeferrableTriggers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Behavior getOwnedDoActivity()
	{
		return ownedDoActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedDoActivity(Behavior newOwnedDoActivity, NotificationChain msgs)
	{
		Behavior oldOwnedDoActivity = ownedDoActivity;
		ownedDoActivity = newOwnedDoActivity;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.STATE__OWNED_DO_ACTIVITY, oldOwnedDoActivity, newOwnedDoActivity);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwnedDoActivity(Behavior newOwnedDoActivity)
	{
		if (newOwnedDoActivity != ownedDoActivity)
		{
			NotificationChain msgs = null;
			if (ownedDoActivity != null)
				msgs = ((InternalEObject)ownedDoActivity).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.STATE__OWNED_DO_ACTIVITY, null, msgs);
			if (newOwnedDoActivity != null)
				msgs = ((InternalEObject)newOwnedDoActivity).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PivotPackage.STATE__OWNED_DO_ACTIVITY, null, msgs);
			msgs = basicSetOwnedDoActivity(newOwnedDoActivity, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.STATE__OWNED_DO_ACTIVITY, newOwnedDoActivity, newOwnedDoActivity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Behavior getOwnedEntry()
	{
		return ownedEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedEntry(Behavior newOwnedEntry, NotificationChain msgs)
	{
		Behavior oldOwnedEntry = ownedEntry;
		ownedEntry = newOwnedEntry;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.STATE__OWNED_ENTRY, oldOwnedEntry, newOwnedEntry);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwnedEntry(Behavior newOwnedEntry)
	{
		if (newOwnedEntry != ownedEntry)
		{
			NotificationChain msgs = null;
			if (ownedEntry != null)
				msgs = ((InternalEObject)ownedEntry).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.STATE__OWNED_ENTRY, null, msgs);
			if (newOwnedEntry != null)
				msgs = ((InternalEObject)newOwnedEntry).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PivotPackage.STATE__OWNED_ENTRY, null, msgs);
			msgs = basicSetOwnedEntry(newOwnedEntry, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.STATE__OWNED_ENTRY, newOwnedEntry, newOwnedEntry));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Behavior getOwnedExit()
	{
		return ownedExit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedExit(Behavior newOwnedExit, NotificationChain msgs)
	{
		Behavior oldOwnedExit = ownedExit;
		ownedExit = newOwnedExit;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.STATE__OWNED_EXIT, oldOwnedExit, newOwnedExit);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwnedExit(Behavior newOwnedExit)
	{
		if (newOwnedExit != ownedExit)
		{
			NotificationChain msgs = null;
			if (ownedExit != null)
				msgs = ((InternalEObject)ownedExit).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.STATE__OWNED_EXIT, null, msgs);
			if (newOwnedExit != null)
				msgs = ((InternalEObject)newOwnedExit).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PivotPackage.STATE__OWNED_EXIT, null, msgs);
			msgs = basicSetOwnedExit(newOwnedExit, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.STATE__OWNED_EXIT, newOwnedExit, newOwnedExit));
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
			ownedRegions = new EObjectContainmentWithInverseEList<Region>(Region.class, this, PivotPackage.STATE__OWNED_REGIONS, PivotPackage.REGION__OWNING_STATE);
		}
		return ownedRegions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Constraint getOwnedStateInvariant()
	{
		return ownedStateInvariant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedStateInvariant(Constraint newOwnedStateInvariant, NotificationChain msgs)
	{
		Constraint oldOwnedStateInvariant = ownedStateInvariant;
		ownedStateInvariant = newOwnedStateInvariant;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.STATE__OWNED_STATE_INVARIANT, oldOwnedStateInvariant, newOwnedStateInvariant);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwnedStateInvariant(Constraint newOwnedStateInvariant)
	{
		if (newOwnedStateInvariant != ownedStateInvariant)
		{
			NotificationChain msgs = null;
			if (ownedStateInvariant != null)
				msgs = ((InternalEObject)ownedStateInvariant).eInverseRemove(this, PivotPackage.CONSTRAINT__OWNING_STATE, Constraint.class, msgs);
			if (newOwnedStateInvariant != null)
				msgs = ((InternalEObject)newOwnedStateInvariant).eInverseAdd(this, PivotPackage.CONSTRAINT__OWNING_STATE, Constraint.class, msgs);
			msgs = basicSetOwnedStateInvariant(newOwnedStateInvariant, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.STATE__OWNED_STATE_INVARIANT, newOwnedStateInvariant, newOwnedStateInvariant));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public State getRedefinedState()
	{
		if (redefinedState != null && redefinedState.eIsProxy())
		{
			InternalEObject oldRedefinedState = (InternalEObject)redefinedState;
			redefinedState = (State)eResolveProxy(oldRedefinedState);
			if (redefinedState != oldRedefinedState)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.STATE__REDEFINED_STATE, oldRedefinedState, redefinedState));
			}
		}
		return redefinedState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public State basicGetRedefinedState()
	{
		return redefinedState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRedefinedState(State newRedefinedState)
	{
		State oldRedefinedState = redefinedState;
		redefinedState = newRedefinedState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.STATE__REDEFINED_STATE, oldRedefinedState, redefinedState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StateMachine getSubmachines()
	{
		if (submachines != null && submachines.eIsProxy())
		{
			InternalEObject oldSubmachines = (InternalEObject)submachines;
			submachines = (StateMachine)eResolveProxy(oldSubmachines);
			if (submachines != oldSubmachines)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.STATE__SUBMACHINES, oldSubmachines, submachines));
			}
		}
		return submachines;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateMachine basicGetSubmachines()
	{
		return submachines;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSubmachines(StateMachine newSubmachines, NotificationChain msgs)
	{
		StateMachine oldSubmachines = submachines;
		submachines = newSubmachines;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.STATE__SUBMACHINES, oldSubmachines, newSubmachines);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSubmachines(StateMachine newSubmachines)
	{
		if (newSubmachines != submachines)
		{
			NotificationChain msgs = null;
			if (submachines != null)
				msgs = ((InternalEObject)submachines).eInverseRemove(this, PivotPackage.STATE_MACHINE__SUBMACHINE_STATES, StateMachine.class, msgs);
			if (newSubmachines != null)
				msgs = ((InternalEObject)newSubmachines).eInverseAdd(this, PivotPackage.STATE_MACHINE__SUBMACHINE_STATES, StateMachine.class, msgs);
			msgs = basicSetSubmachines(newSubmachines, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.STATE__SUBMACHINES, newSubmachines, newSubmachines));
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
			case PivotPackage.STATE__ANNOTATING_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.STATE__OWNED_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.STATE__OWNED_EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case PivotPackage.STATE__INCOMING_TRANSITIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncomingTransitions()).basicAdd(otherEnd, msgs);
			case PivotPackage.STATE__OUTGOING_TRANSITIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoingTransitions()).basicAdd(otherEnd, msgs);
			case PivotPackage.STATE__OWNING_REGION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningRegion((Region)otherEnd, msgs);
			case PivotPackage.STATE__OWNED_CONNECTION_POINTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedConnectionPoints()).basicAdd(otherEnd, msgs);
			case PivotPackage.STATE__OWNED_CONNECTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedConnections()).basicAdd(otherEnd, msgs);
			case PivotPackage.STATE__OWNED_DEFERRABLE_TRIGGERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedDeferrableTriggers()).basicAdd(otherEnd, msgs);
			case PivotPackage.STATE__OWNED_REGIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedRegions()).basicAdd(otherEnd, msgs);
			case PivotPackage.STATE__OWNED_STATE_INVARIANT:
				if (ownedStateInvariant != null)
					msgs = ((InternalEObject)ownedStateInvariant).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.STATE__OWNED_STATE_INVARIANT, null, msgs);
				return basicSetOwnedStateInvariant((Constraint)otherEnd, msgs);
			case PivotPackage.STATE__SUBMACHINES:
				if (submachines != null)
					msgs = ((InternalEObject)submachines).eInverseRemove(this, PivotPackage.STATE_MACHINE__SUBMACHINE_STATES, StateMachine.class, msgs);
				return basicSetSubmachines((StateMachine)otherEnd, msgs);
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
			case PivotPackage.STATE__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.STATE__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.STATE__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.STATE__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.STATE__OWNED_CONSTRAINTS:
				return ((InternalEList<?>)getOwnedConstraints()).basicRemove(otherEnd, msgs);
			case PivotPackage.STATE__INCOMING_TRANSITIONS:
				return ((InternalEList<?>)getIncomingTransitions()).basicRemove(otherEnd, msgs);
			case PivotPackage.STATE__OUTGOING_TRANSITIONS:
				return ((InternalEList<?>)getOutgoingTransitions()).basicRemove(otherEnd, msgs);
			case PivotPackage.STATE__OWNING_REGION:
				return basicSetOwningRegion(null, msgs);
			case PivotPackage.STATE__OWNED_CONNECTION_POINTS:
				return ((InternalEList<?>)getOwnedConnectionPoints()).basicRemove(otherEnd, msgs);
			case PivotPackage.STATE__OWNED_CONNECTIONS:
				return ((InternalEList<?>)getOwnedConnections()).basicRemove(otherEnd, msgs);
			case PivotPackage.STATE__OWNED_DEFERRABLE_TRIGGERS:
				return ((InternalEList<?>)getOwnedDeferrableTriggers()).basicRemove(otherEnd, msgs);
			case PivotPackage.STATE__OWNED_DO_ACTIVITY:
				return basicSetOwnedDoActivity(null, msgs);
			case PivotPackage.STATE__OWNED_ENTRY:
				return basicSetOwnedEntry(null, msgs);
			case PivotPackage.STATE__OWNED_EXIT:
				return basicSetOwnedExit(null, msgs);
			case PivotPackage.STATE__OWNED_REGIONS:
				return ((InternalEList<?>)getOwnedRegions()).basicRemove(otherEnd, msgs);
			case PivotPackage.STATE__OWNED_STATE_INVARIANT:
				return basicSetOwnedStateInvariant(null, msgs);
			case PivotPackage.STATE__SUBMACHINES:
				return basicSetSubmachines(null, msgs);
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
			case PivotPackage.STATE__OWNING_REGION:
				return eInternalContainer().eInverseRemove(this, PivotPackage.REGION__OWNED_SUBVERTEXES, Region.class, msgs);
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
			case PivotPackage.STATE__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.STATE__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.STATE__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.STATE__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.STATE__NAME:
				return getName();
			case PivotPackage.STATE__OWNED_CONSTRAINTS:
				return getOwnedConstraints();
			case PivotPackage.STATE__INCOMING_TRANSITIONS:
				return getIncomingTransitions();
			case PivotPackage.STATE__OUTGOING_TRANSITIONS:
				return getOutgoingTransitions();
			case PivotPackage.STATE__OWNING_REGION:
				return getOwningRegion();
			case PivotPackage.STATE__IS_COMPOSITE:
				return isIsComposite();
			case PivotPackage.STATE__IS_ORTHOGONAL:
				return isIsOrthogonal();
			case PivotPackage.STATE__IS_SIMPLE:
				return isIsSimple();
			case PivotPackage.STATE__IS_SUBMACHINE_STATE:
				return isIsSubmachineState();
			case PivotPackage.STATE__OWNED_CONNECTION_POINTS:
				return getOwnedConnectionPoints();
			case PivotPackage.STATE__OWNED_CONNECTIONS:
				return getOwnedConnections();
			case PivotPackage.STATE__OWNED_DEFERRABLE_TRIGGERS:
				return getOwnedDeferrableTriggers();
			case PivotPackage.STATE__OWNED_DO_ACTIVITY:
				return getOwnedDoActivity();
			case PivotPackage.STATE__OWNED_ENTRY:
				return getOwnedEntry();
			case PivotPackage.STATE__OWNED_EXIT:
				return getOwnedExit();
			case PivotPackage.STATE__OWNED_REGIONS:
				return getOwnedRegions();
			case PivotPackage.STATE__OWNED_STATE_INVARIANT:
				return getOwnedStateInvariant();
			case PivotPackage.STATE__REDEFINED_STATE:
				if (resolve) return getRedefinedState();
				return basicGetRedefinedState();
			case PivotPackage.STATE__SUBMACHINES:
				if (resolve) return getSubmachines();
				return basicGetSubmachines();
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
			case PivotPackage.STATE__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.STATE__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.STATE__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.STATE__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.STATE__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.STATE__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				getOwnedConstraints().addAll((Collection<? extends Constraint>)newValue);
				return;
			case PivotPackage.STATE__OWNING_REGION:
				setOwningRegion((Region)newValue);
				return;
			case PivotPackage.STATE__OWNED_CONNECTION_POINTS:
				getOwnedConnectionPoints().clear();
				getOwnedConnectionPoints().addAll((Collection<? extends Pseudostate>)newValue);
				return;
			case PivotPackage.STATE__OWNED_CONNECTIONS:
				getOwnedConnections().clear();
				getOwnedConnections().addAll((Collection<? extends ConnectionPointReference>)newValue);
				return;
			case PivotPackage.STATE__OWNED_DEFERRABLE_TRIGGERS:
				getOwnedDeferrableTriggers().clear();
				getOwnedDeferrableTriggers().addAll((Collection<? extends Trigger>)newValue);
				return;
			case PivotPackage.STATE__OWNED_DO_ACTIVITY:
				setOwnedDoActivity((Behavior)newValue);
				return;
			case PivotPackage.STATE__OWNED_ENTRY:
				setOwnedEntry((Behavior)newValue);
				return;
			case PivotPackage.STATE__OWNED_EXIT:
				setOwnedExit((Behavior)newValue);
				return;
			case PivotPackage.STATE__OWNED_REGIONS:
				getOwnedRegions().clear();
				getOwnedRegions().addAll((Collection<? extends Region>)newValue);
				return;
			case PivotPackage.STATE__OWNED_STATE_INVARIANT:
				setOwnedStateInvariant((Constraint)newValue);
				return;
			case PivotPackage.STATE__REDEFINED_STATE:
				setRedefinedState((State)newValue);
				return;
			case PivotPackage.STATE__SUBMACHINES:
				setSubmachines((StateMachine)newValue);
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
			case PivotPackage.STATE__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.STATE__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.STATE__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.STATE__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.STATE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.STATE__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				return;
			case PivotPackage.STATE__OWNING_REGION:
				setOwningRegion((Region)null);
				return;
			case PivotPackage.STATE__OWNED_CONNECTION_POINTS:
				getOwnedConnectionPoints().clear();
				return;
			case PivotPackage.STATE__OWNED_CONNECTIONS:
				getOwnedConnections().clear();
				return;
			case PivotPackage.STATE__OWNED_DEFERRABLE_TRIGGERS:
				getOwnedDeferrableTriggers().clear();
				return;
			case PivotPackage.STATE__OWNED_DO_ACTIVITY:
				setOwnedDoActivity((Behavior)null);
				return;
			case PivotPackage.STATE__OWNED_ENTRY:
				setOwnedEntry((Behavior)null);
				return;
			case PivotPackage.STATE__OWNED_EXIT:
				setOwnedExit((Behavior)null);
				return;
			case PivotPackage.STATE__OWNED_REGIONS:
				getOwnedRegions().clear();
				return;
			case PivotPackage.STATE__OWNED_STATE_INVARIANT:
				setOwnedStateInvariant((Constraint)null);
				return;
			case PivotPackage.STATE__REDEFINED_STATE:
				setRedefinedState((State)null);
				return;
			case PivotPackage.STATE__SUBMACHINES:
				setSubmachines((StateMachine)null);
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
			case PivotPackage.STATE__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.STATE__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.STATE__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.STATE__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.STATE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.STATE__OWNED_CONSTRAINTS:
				return ownedConstraints != null && !ownedConstraints.isEmpty();
			case PivotPackage.STATE__INCOMING_TRANSITIONS:
				return incomingTransitions != null && !incomingTransitions.isEmpty();
			case PivotPackage.STATE__OUTGOING_TRANSITIONS:
				return outgoingTransitions != null && !outgoingTransitions.isEmpty();
			case PivotPackage.STATE__OWNING_REGION:
				return getOwningRegion() != null;
			case PivotPackage.STATE__IS_COMPOSITE:
				return isIsComposite() != IS_COMPOSITE_EDEFAULT;
			case PivotPackage.STATE__IS_ORTHOGONAL:
				return isIsOrthogonal() != IS_ORTHOGONAL_EDEFAULT;
			case PivotPackage.STATE__IS_SIMPLE:
				return isIsSimple() != IS_SIMPLE_EDEFAULT;
			case PivotPackage.STATE__IS_SUBMACHINE_STATE:
				return isIsSubmachineState() != IS_SUBMACHINE_STATE_EDEFAULT;
			case PivotPackage.STATE__OWNED_CONNECTION_POINTS:
				return ownedConnectionPoints != null && !ownedConnectionPoints.isEmpty();
			case PivotPackage.STATE__OWNED_CONNECTIONS:
				return ownedConnections != null && !ownedConnections.isEmpty();
			case PivotPackage.STATE__OWNED_DEFERRABLE_TRIGGERS:
				return ownedDeferrableTriggers != null && !ownedDeferrableTriggers.isEmpty();
			case PivotPackage.STATE__OWNED_DO_ACTIVITY:
				return ownedDoActivity != null;
			case PivotPackage.STATE__OWNED_ENTRY:
				return ownedEntry != null;
			case PivotPackage.STATE__OWNED_EXIT:
				return ownedExit != null;
			case PivotPackage.STATE__OWNED_REGIONS:
				return ownedRegions != null && !ownedRegions.isEmpty();
			case PivotPackage.STATE__OWNED_STATE_INVARIANT:
				return ownedStateInvariant != null;
			case PivotPackage.STATE__REDEFINED_STATE:
				return redefinedState != null;
			case PivotPackage.STATE__SUBMACHINES:
				return submachines != null;
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
	{
		if (baseClass == Vertex.class)
		{
			switch (derivedFeatureID)
			{
				case PivotPackage.STATE__INCOMING_TRANSITIONS: return PivotPackage.VERTEX__INCOMING_TRANSITIONS;
				case PivotPackage.STATE__OUTGOING_TRANSITIONS: return PivotPackage.VERTEX__OUTGOING_TRANSITIONS;
				case PivotPackage.STATE__OWNING_REGION: return PivotPackage.VERTEX__OWNING_REGION;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
	{
		if (baseClass == Vertex.class)
		{
			switch (baseFeatureID)
			{
				case PivotPackage.VERTEX__INCOMING_TRANSITIONS: return PivotPackage.STATE__INCOMING_TRANSITIONS;
				case PivotPackage.VERTEX__OUTGOING_TRANSITIONS: return PivotPackage.STATE__OUTGOING_TRANSITIONS;
				case PivotPackage.VERTEX__OWNING_REGION: return PivotPackage.STATE__OWNING_REGION;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitState(this);
	}
} //StateImpl
