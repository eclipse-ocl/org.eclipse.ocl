/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
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
	 * The number of structural features of the '<em>State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STATE_FEATURE_COUNT = NamespaceImpl.NAMESPACE_FEATURE_COUNT + 17;
	/**
	 * The number of operations of the '<em>State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STATE_OPERATION_COUNT = NamespaceImpl.NAMESPACE_OPERATION_COUNT + 0;
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
			incomingTransitions = new EObjectWithInverseResolvingEList<Transition>(Transition.class, this, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 0, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 6);
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
			outgoingTransitions = new EObjectWithInverseResolvingEList<Transition>(Transition.class, this, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 1, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 5);
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
		if (eContainerFeatureID() != (NamespaceImpl.NAMESPACE_FEATURE_COUNT + 2)) return null;
		return (Region)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningRegion(Region newOwningRegion, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningRegion, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 2, msgs);
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
		if (newOwningRegion != eInternalContainer() || (eContainerFeatureID() != (NamespaceImpl.NAMESPACE_FEATURE_COUNT + 2) && newOwningRegion != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningRegion))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningRegion != null)
				msgs = ((InternalEObject)newOwningRegion).eInverseAdd(this, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 1, Region.class, msgs);
			msgs = basicSetOwningRegion(newOwningRegion, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 2, newOwningRegion, newOwningRegion));
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
			ownedConnectionPoints = new EObjectContainmentWithInverseEList<Pseudostate>(Pseudostate.class, this, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 7, VertexImpl.VERTEX_FEATURE_COUNT + 1);
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
			ownedConnections = new EObjectContainmentWithInverseEList<ConnectionPointReference>(ConnectionPointReference.class, this, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 8, VertexImpl.VERTEX_FEATURE_COUNT + 2);
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
			ownedDeferrableTriggers = new EObjectContainmentWithInverseEList<Trigger>(Trigger.class, this, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 9, NamedElementImpl.NAMED_ELEMENT_FEATURE_COUNT + 0);
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 10, oldOwnedDoActivity, newOwnedDoActivity);
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
				msgs = ((InternalEObject)ownedDoActivity).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (NamespaceImpl.NAMESPACE_FEATURE_COUNT + 10), null, msgs);
			if (newOwnedDoActivity != null)
				msgs = ((InternalEObject)newOwnedDoActivity).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (NamespaceImpl.NAMESPACE_FEATURE_COUNT + 10), null, msgs);
			msgs = basicSetOwnedDoActivity(newOwnedDoActivity, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 10, newOwnedDoActivity, newOwnedDoActivity));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 11, oldOwnedEntry, newOwnedEntry);
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
				msgs = ((InternalEObject)ownedEntry).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (NamespaceImpl.NAMESPACE_FEATURE_COUNT + 11), null, msgs);
			if (newOwnedEntry != null)
				msgs = ((InternalEObject)newOwnedEntry).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (NamespaceImpl.NAMESPACE_FEATURE_COUNT + 11), null, msgs);
			msgs = basicSetOwnedEntry(newOwnedEntry, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 11, newOwnedEntry, newOwnedEntry));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 12, oldOwnedExit, newOwnedExit);
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
				msgs = ((InternalEObject)ownedExit).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (NamespaceImpl.NAMESPACE_FEATURE_COUNT + 12), null, msgs);
			if (newOwnedExit != null)
				msgs = ((InternalEObject)newOwnedExit).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (NamespaceImpl.NAMESPACE_FEATURE_COUNT + 12), null, msgs);
			msgs = basicSetOwnedExit(newOwnedExit, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 12, newOwnedExit, newOwnedExit));
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
			ownedRegions = new EObjectContainmentWithInverseEList<Region>(Region.class, this, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 13, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 3);
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 14, oldOwnedStateInvariant, newOwnedStateInvariant);
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
				msgs = ((InternalEObject)ownedStateInvariant).eInverseRemove(this, NamedElementImpl.NAMED_ELEMENT_FEATURE_COUNT + 6, Constraint.class, msgs);
			if (newOwnedStateInvariant != null)
				msgs = ((InternalEObject)newOwnedStateInvariant).eInverseAdd(this, NamedElementImpl.NAMED_ELEMENT_FEATURE_COUNT + 6, Constraint.class, msgs);
			msgs = basicSetOwnedStateInvariant(newOwnedStateInvariant, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 14, newOwnedStateInvariant, newOwnedStateInvariant));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 15, oldRedefinedState, redefinedState));
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
			eNotify(new ENotificationImpl(this, Notification.SET, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 15, oldRedefinedState, redefinedState));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 16, oldSubmachines, submachines));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 16, oldSubmachines, newSubmachines);
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
				msgs = ((InternalEObject)submachines).eInverseRemove(this, BehaviorImpl.BEHAVIOR_FEATURE_COUNT + 3, StateMachine.class, msgs);
			if (newSubmachines != null)
				msgs = ((InternalEObject)newSubmachines).eInverseAdd(this, BehaviorImpl.BEHAVIOR_FEATURE_COUNT + 3, StateMachine.class, msgs);
			msgs = basicSetSubmachines(newSubmachines, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 16, newSubmachines, newSubmachines));
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
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 0:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncomingTransitions()).basicAdd(otherEnd, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 1:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoingTransitions()).basicAdd(otherEnd, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 2:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningRegion((Region)otherEnd, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 7:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedConnectionPoints()).basicAdd(otherEnd, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 8:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedConnections()).basicAdd(otherEnd, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 9:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedDeferrableTriggers()).basicAdd(otherEnd, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 13:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedRegions()).basicAdd(otherEnd, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 14:
				if (ownedStateInvariant != null)
					msgs = ((InternalEObject)ownedStateInvariant).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (NamespaceImpl.NAMESPACE_FEATURE_COUNT + 14), null, msgs);
				return basicSetOwnedStateInvariant((Constraint)otherEnd, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 16:
				if (submachines != null)
					msgs = ((InternalEObject)submachines).eInverseRemove(this, BehaviorImpl.BEHAVIOR_FEATURE_COUNT + 3, StateMachine.class, msgs);
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
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 0:
				return ((InternalEList<?>)getIncomingTransitions()).basicRemove(otherEnd, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 1:
				return ((InternalEList<?>)getOutgoingTransitions()).basicRemove(otherEnd, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 2:
				return basicSetOwningRegion(null, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 7:
				return ((InternalEList<?>)getOwnedConnectionPoints()).basicRemove(otherEnd, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 8:
				return ((InternalEList<?>)getOwnedConnections()).basicRemove(otherEnd, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 9:
				return ((InternalEList<?>)getOwnedDeferrableTriggers()).basicRemove(otherEnd, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 10:
				return basicSetOwnedDoActivity(null, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 11:
				return basicSetOwnedEntry(null, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 12:
				return basicSetOwnedExit(null, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 13:
				return ((InternalEList<?>)getOwnedRegions()).basicRemove(otherEnd, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 14:
				return basicSetOwnedStateInvariant(null, msgs);
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 16:
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
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 2:
				return eInternalContainer().eInverseRemove(this, NamespaceImpl.NAMESPACE_FEATURE_COUNT + 1, Region.class, msgs);
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
				return getIncomingTransitions();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 1:
				return getOutgoingTransitions();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 2:
				return getOwningRegion();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 3:
				return isIsComposite();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 4:
				return isIsOrthogonal();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 5:
				return isIsSimple();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 6:
				return isIsSubmachineState();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 7:
				return getOwnedConnectionPoints();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 8:
				return getOwnedConnections();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 9:
				return getOwnedDeferrableTriggers();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 10:
				return getOwnedDoActivity();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 11:
				return getOwnedEntry();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 12:
				return getOwnedExit();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 13:
				return getOwnedRegions();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 14:
				return getOwnedStateInvariant();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 15:
				if (resolve) return getRedefinedState();
				return basicGetRedefinedState();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 16:
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
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 2:
				setOwningRegion((Region)newValue);
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 7:
				getOwnedConnectionPoints().clear();
				getOwnedConnectionPoints().addAll((Collection<? extends Pseudostate>)newValue);
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 8:
				getOwnedConnections().clear();
				getOwnedConnections().addAll((Collection<? extends ConnectionPointReference>)newValue);
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 9:
				getOwnedDeferrableTriggers().clear();
				getOwnedDeferrableTriggers().addAll((Collection<? extends Trigger>)newValue);
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 10:
				setOwnedDoActivity((Behavior)newValue);
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 11:
				setOwnedEntry((Behavior)newValue);
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 12:
				setOwnedExit((Behavior)newValue);
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 13:
				getOwnedRegions().clear();
				getOwnedRegions().addAll((Collection<? extends Region>)newValue);
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 14:
				setOwnedStateInvariant((Constraint)newValue);
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 15:
				setRedefinedState((State)newValue);
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 16:
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
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 2:
				setOwningRegion((Region)null);
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 7:
				getOwnedConnectionPoints().clear();
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 8:
				getOwnedConnections().clear();
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 9:
				getOwnedDeferrableTriggers().clear();
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 10:
				setOwnedDoActivity((Behavior)null);
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 11:
				setOwnedEntry((Behavior)null);
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 12:
				setOwnedExit((Behavior)null);
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 13:
				getOwnedRegions().clear();
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 14:
				setOwnedStateInvariant((Constraint)null);
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 15:
				setRedefinedState((State)null);
				return;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 16:
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
				return incomingTransitions != null && !incomingTransitions.isEmpty();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 1:
				return outgoingTransitions != null && !outgoingTransitions.isEmpty();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 2:
				return getOwningRegion() != null;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 3:
				return isIsComposite() != IS_COMPOSITE_EDEFAULT;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 4:
				return isIsOrthogonal() != IS_ORTHOGONAL_EDEFAULT;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 5:
				return isIsSimple() != IS_SIMPLE_EDEFAULT;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 6:
				return isIsSubmachineState() != IS_SUBMACHINE_STATE_EDEFAULT;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 7:
				return ownedConnectionPoints != null && !ownedConnectionPoints.isEmpty();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 8:
				return ownedConnections != null && !ownedConnections.isEmpty();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 9:
				return ownedDeferrableTriggers != null && !ownedDeferrableTriggers.isEmpty();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 10:
				return ownedDoActivity != null;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 11:
				return ownedEntry != null;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 12:
				return ownedExit != null;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 13:
				return ownedRegions != null && !ownedRegions.isEmpty();
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 14:
				return ownedStateInvariant != null;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 15:
				return redefinedState != null;
			case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 16:
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
				case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 0: return NamedElementImpl.NAMED_ELEMENT_FEATURE_COUNT + 0;
				case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 1: return NamedElementImpl.NAMED_ELEMENT_FEATURE_COUNT + 1;
				case NamespaceImpl.NAMESPACE_FEATURE_COUNT + 2: return NamedElementImpl.NAMED_ELEMENT_FEATURE_COUNT + 2;
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
				case NamedElementImpl.NAMED_ELEMENT_FEATURE_COUNT + 0: return NamespaceImpl.NAMESPACE_FEATURE_COUNT + 0;
				case NamedElementImpl.NAMED_ELEMENT_FEATURE_COUNT + 1: return NamespaceImpl.NAMESPACE_FEATURE_COUNT + 1;
				case NamedElementImpl.NAMED_ELEMENT_FEATURE_COUNT + 2: return NamespaceImpl.NAMESPACE_FEATURE_COUNT + 2;
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
