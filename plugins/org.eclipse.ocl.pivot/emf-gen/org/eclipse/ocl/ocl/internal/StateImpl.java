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
package org.eclipse.ocl.ocl.internal;

import java.util.Collection;
import java.util.List;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.ocl.Behavior;
import org.eclipse.ocl.ocl.Comment;
import org.eclipse.ocl.ocl.ConnectionPointReference;
import org.eclipse.ocl.ocl.Constraint;
import org.eclipse.ocl.ocl.Element;
import org.eclipse.ocl.ocl.ElementExtension;
import org.eclipse.ocl.ocl.NamedElement;
import org.eclipse.ocl.ocl.Namespace;
import org.eclipse.ocl.ocl.OCLASPackage;
import org.eclipse.ocl.ocl.OclElement;
import org.eclipse.ocl.ocl.Pseudostate;
import org.eclipse.ocl.ocl.Region;
import org.eclipse.ocl.ocl.State;

import org.eclipse.ocl.ocl.StateMachine;
import org.eclipse.ocl.ocl.Transition;
import org.eclipse.ocl.ocl.Trigger;
import org.eclipse.ocl.ocl.Vertex;
import org.eclipse.ocl.pivot.internal.utilities.PivotObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.internal.StateImpl#getOclContainer <em>Ocl Container</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.StateImpl#getOclContents <em>Ocl Contents</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.StateImpl#getAnnotatingComments <em>Annotating Comments</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.StateImpl#getOwnedAnnotations <em>Owned Annotations</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.StateImpl#getOwnedComments <em>Owned Comments</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.StateImpl#getOwnedExtensions <em>Owned Extensions</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.StateImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.StateImpl#getOwnedConstraints <em>Owned Constraints</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.StateImpl#getIncomingTransitions <em>Incoming Transitions</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.StateImpl#getOutgoingTransitions <em>Outgoing Transitions</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.StateImpl#getOwningRegion <em>Owning Region</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.StateImpl#getIsComposite <em>Is Composite</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.StateImpl#getIsOrthogonal <em>Is Orthogonal</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.StateImpl#getIsSimple <em>Is Simple</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.StateImpl#getIsSubmachineState <em>Is Submachine State</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.StateImpl#getOwnedConnectionPoints <em>Owned Connection Points</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.StateImpl#getOwnedConnections <em>Owned Connections</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.StateImpl#getOwnedDeferrableTriggers <em>Owned Deferrable Triggers</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.StateImpl#getOwnedDoActivity <em>Owned Do Activity</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.StateImpl#getOwnedEntry <em>Owned Entry</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.StateImpl#getOwnedExit <em>Owned Exit</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.StateImpl#getOwnedRegions <em>Owned Regions</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.StateImpl#getOwnedStateInvariant <em>Owned State Invariant</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.StateImpl#getRedefinedState <em>Redefined State</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.StateImpl#getSubmachines <em>Submachines</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StateImpl extends PivotObjectImpl implements State
{
	/**
	 * The number of structural features of the '<em>State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STATE_FEATURE_COUNT = 25;

	/**
	 * The number of operations of the '<em>State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STATE_OPERATION_COUNT = 0;


	/**
	 * The cached value of the '{@link #getOclContainer() <em>Ocl Container</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOclContainer()
	 * @generated
	 * @ordered
	 */
	protected OclElement oclContainer;

	/**
	 * The cached value of the '{@link #getOclContents() <em>Ocl Contents</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOclContents()
	 * @generated
	 * @ordered
	 */
	protected EList<OclElement> oclContents;

	/**
	 * The cached value of the '{@link #getAnnotatingComments() <em>Annotating Comments</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnnotatingComments()
	 * @generated
	 * @ordered
	 */
	protected EList<Comment> annotatingComments;

	/**
	 * The cached value of the '{@link #getOwnedAnnotations() <em>Owned Annotations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedAnnotations()
	 * @generated
	 * @ordered
	 */
	protected EList<Element> ownedAnnotations;

	/**
	 * The cached value of the '{@link #getOwnedComments() <em>Owned Comments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedComments()
	 * @generated
	 * @ordered
	 */
	protected EList<Comment> ownedComments;

	/**
	 * The cached value of the '{@link #getOwnedExtensions() <em>Owned Extensions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedExtensions()
	 * @generated
	 * @ordered
	 */
	protected EList<ElementExtension> ownedExtensions;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOwnedConstraints() <em>Owned Constraints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList<Constraint> ownedConstraints;

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
	 * The default value of the '{@link #getIsComposite() <em>Is Composite</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsComposite()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_COMPOSITE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getIsOrthogonal() <em>Is Orthogonal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsOrthogonal()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_ORTHOGONAL_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getIsSimple() <em>Is Simple</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsSimple()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_SIMPLE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getIsSubmachineState() <em>Is Submachine State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsSubmachineState()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_SUBMACHINE_STATE_EDEFAULT = null;

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
	protected StateImpl()
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
		return OCLASPackage.Literals.STATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OclElement getOclContainer()
	{
		if (oclContainer != null && oclContainer.eIsProxy())
		{
			InternalEObject oldOclContainer = (InternalEObject)oclContainer;
			oclContainer = (OclElement)eResolveProxy(oldOclContainer);
			if (oclContainer != oldOclContainer)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 0, oldOclContainer, oclContainer));
			}
		}
		return oclContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclElement basicGetOclContainer()
	{
		return oclContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOclContainer(OclElement newOclContainer)
	{
		OclElement oldOclContainer = oclContainer;
		oclContainer = newOclContainer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 0, oldOclContainer, oclContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<OclElement> getOclContents()
	{
		if (oclContents == null)
		{
			oclContents = new EObjectResolvingEList<OclElement>(OclElement.class, this, 1);
		}
		return oclContents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Comment> getAnnotatingComments()
	{
		if (annotatingComments == null)
		{
			annotatingComments = new EObjectWithInverseResolvingEList.ManyInverse<Comment>(Comment.class, this, 2, 6);
		}
		return annotatingComments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Element> getOwnedAnnotations()
	{
		if (ownedAnnotations == null)
		{
			ownedAnnotations = new EObjectContainmentEList<Element>(Element.class, this, 3);
		}
		return ownedAnnotations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Comment> getOwnedComments()
	{
		if (ownedComments == null)
		{
			ownedComments = new EObjectContainmentWithInverseEList<Comment>(Comment.class, this, 4, 8);
		}
		return ownedComments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<ElementExtension> getOwnedExtensions()
	{
		if (ownedExtensions == null)
		{
			ownedExtensions = new EObjectContainmentWithInverseEList<ElementExtension>(ElementExtension.class, this, 5, 22);
		}
		return ownedExtensions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName()
	{
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName)
	{
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 6, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Constraint> getOwnedConstraints()
	{
		if (ownedConstraints == null)
		{
			ownedConstraints = new EObjectContainmentEList<Constraint>(Constraint.class, this, 7);
		}
		return ownedConstraints;
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
			incomingTransitions = new EObjectWithInverseResolvingEList<Transition>(Transition.class, this, 8, 14);
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
			outgoingTransitions = new EObjectWithInverseResolvingEList<Transition>(Transition.class, this, 9, 13);
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
		if (eContainerFeatureID() != (10)) return null;
		return (Region)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningRegion(Region newOwningRegion, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningRegion, 10, msgs);
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
		if (newOwningRegion != eInternalContainer() || (eContainerFeatureID() != (10) && newOwningRegion != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningRegion))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningRegion != null)
				msgs = ((InternalEObject)newOwningRegion).eInverseAdd(this, 9, Region.class, msgs);
			msgs = basicSetOwningRegion(newOwningRegion, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 10, newOwningRegion, newOwningRegion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getIsComposite()
	{
		// TODO: implement this method to return the 'Is Composite' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getIsOrthogonal()
	{
		// TODO: implement this method to return the 'Is Orthogonal' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getIsSimple()
	{
		// TODO: implement this method to return the 'Is Simple' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getIsSubmachineState()
	{
		// TODO: implement this method to return the 'Is Submachine State' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
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
			ownedConnectionPoints = new EObjectContainmentWithInverseEList<Pseudostate>(Pseudostate.class, this, 15, 11);
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
			ownedConnections = new EObjectContainmentWithInverseEList<ConnectionPointReference>(ConnectionPointReference.class, this, 16, 12);
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
			ownedDeferrableTriggers = new EObjectContainmentWithInverseEList<Trigger>(Trigger.class, this, 17, 7);
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, 18, oldOwnedDoActivity, newOwnedDoActivity);
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
				msgs = ((InternalEObject)ownedDoActivity).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (18), null, msgs);
			if (newOwnedDoActivity != null)
				msgs = ((InternalEObject)newOwnedDoActivity).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (18), null, msgs);
			msgs = basicSetOwnedDoActivity(newOwnedDoActivity, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 18, newOwnedDoActivity, newOwnedDoActivity));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, 19, oldOwnedEntry, newOwnedEntry);
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
				msgs = ((InternalEObject)ownedEntry).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (19), null, msgs);
			if (newOwnedEntry != null)
				msgs = ((InternalEObject)newOwnedEntry).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (19), null, msgs);
			msgs = basicSetOwnedEntry(newOwnedEntry, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 19, newOwnedEntry, newOwnedEntry));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, 20, oldOwnedExit, newOwnedExit);
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
				msgs = ((InternalEObject)ownedExit).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (20), null, msgs);
			if (newOwnedExit != null)
				msgs = ((InternalEObject)newOwnedExit).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (20), null, msgs);
			msgs = basicSetOwnedExit(newOwnedExit, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 20, newOwnedExit, newOwnedExit));
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
			ownedRegions = new EObjectContainmentWithInverseEList<Region>(Region.class, this, 21, 11);
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, 22, oldOwnedStateInvariant, newOwnedStateInvariant);
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
				msgs = ((InternalEObject)ownedStateInvariant).eInverseRemove(this, 13, Constraint.class, msgs);
			if (newOwnedStateInvariant != null)
				msgs = ((InternalEObject)newOwnedStateInvariant).eInverseAdd(this, 13, Constraint.class, msgs);
			msgs = basicSetOwnedStateInvariant(newOwnedStateInvariant, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 22, newOwnedStateInvariant, newOwnedStateInvariant));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 23, oldRedefinedState, redefinedState));
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
			eNotify(new ENotificationImpl(this, Notification.SET, 23, oldRedefinedState, redefinedState));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 24, oldSubmachines, submachines));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, 24, oldSubmachines, newSubmachines);
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
				msgs = ((InternalEObject)submachines).eInverseRemove(this, 26, StateMachine.class, msgs);
			if (newSubmachines != null)
				msgs = ((InternalEObject)newSubmachines).eInverseAdd(this, 26, StateMachine.class, msgs);
			msgs = basicSetSubmachines(newSubmachines, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 24, newSubmachines, newSubmachines));
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
			case 2:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case 4:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case 5:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case 8:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncomingTransitions()).basicAdd(otherEnd, msgs);
			case 9:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoingTransitions()).basicAdd(otherEnd, msgs);
			case 10:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningRegion((Region)otherEnd, msgs);
			case 15:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedConnectionPoints()).basicAdd(otherEnd, msgs);
			case 16:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedConnections()).basicAdd(otherEnd, msgs);
			case 17:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedDeferrableTriggers()).basicAdd(otherEnd, msgs);
			case 21:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedRegions()).basicAdd(otherEnd, msgs);
			case 22:
				if (ownedStateInvariant != null)
					msgs = ((InternalEObject)ownedStateInvariant).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (22), null, msgs);
				return basicSetOwnedStateInvariant((Constraint)otherEnd, msgs);
			case 24:
				if (submachines != null)
					msgs = ((InternalEObject)submachines).eInverseRemove(this, 26, StateMachine.class, msgs);
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
			case 2:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case 3:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case 4:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case 5:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case 7:
				return ((InternalEList<?>)getOwnedConstraints()).basicRemove(otherEnd, msgs);
			case 8:
				return ((InternalEList<?>)getIncomingTransitions()).basicRemove(otherEnd, msgs);
			case 9:
				return ((InternalEList<?>)getOutgoingTransitions()).basicRemove(otherEnd, msgs);
			case 10:
				return basicSetOwningRegion(null, msgs);
			case 15:
				return ((InternalEList<?>)getOwnedConnectionPoints()).basicRemove(otherEnd, msgs);
			case 16:
				return ((InternalEList<?>)getOwnedConnections()).basicRemove(otherEnd, msgs);
			case 17:
				return ((InternalEList<?>)getOwnedDeferrableTriggers()).basicRemove(otherEnd, msgs);
			case 18:
				return basicSetOwnedDoActivity(null, msgs);
			case 19:
				return basicSetOwnedEntry(null, msgs);
			case 20:
				return basicSetOwnedExit(null, msgs);
			case 21:
				return ((InternalEList<?>)getOwnedRegions()).basicRemove(otherEnd, msgs);
			case 22:
				return basicSetOwnedStateInvariant(null, msgs);
			case 24:
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
			case 10:
				return eInternalContainer().eInverseRemove(this, 9, Region.class, msgs);
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
				if (resolve) return getOclContainer();
				return basicGetOclContainer();
			case 1:
				return getOclContents();
			case 2:
				return getAnnotatingComments();
			case 3:
				return getOwnedAnnotations();
			case 4:
				return getOwnedComments();
			case 5:
				return getOwnedExtensions();
			case 6:
				return getName();
			case 7:
				return getOwnedConstraints();
			case 8:
				return getIncomingTransitions();
			case 9:
				return getOutgoingTransitions();
			case 10:
				return getOwningRegion();
			case 11:
				return getIsComposite();
			case 12:
				return getIsOrthogonal();
			case 13:
				return getIsSimple();
			case 14:
				return getIsSubmachineState();
			case 15:
				return getOwnedConnectionPoints();
			case 16:
				return getOwnedConnections();
			case 17:
				return getOwnedDeferrableTriggers();
			case 18:
				return getOwnedDoActivity();
			case 19:
				return getOwnedEntry();
			case 20:
				return getOwnedExit();
			case 21:
				return getOwnedRegions();
			case 22:
				return getOwnedStateInvariant();
			case 23:
				if (resolve) return getRedefinedState();
				return basicGetRedefinedState();
			case 24:
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
				setOclContainer((OclElement)newValue);
				return;
			case 1:
				getOclContents().clear();
				getOclContents().addAll((Collection<? extends OclElement>)newValue);
				return;
			case 2:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 3:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case 4:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 5:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case 6:
				setName((String)newValue);
				return;
			case 7:
				getOwnedConstraints().clear();
				getOwnedConstraints().addAll((Collection<? extends Constraint>)newValue);
				return;
			case 10:
				setOwningRegion((Region)newValue);
				return;
			case 15:
				getOwnedConnectionPoints().clear();
				getOwnedConnectionPoints().addAll((Collection<? extends Pseudostate>)newValue);
				return;
			case 16:
				getOwnedConnections().clear();
				getOwnedConnections().addAll((Collection<? extends ConnectionPointReference>)newValue);
				return;
			case 17:
				getOwnedDeferrableTriggers().clear();
				getOwnedDeferrableTriggers().addAll((Collection<? extends Trigger>)newValue);
				return;
			case 18:
				setOwnedDoActivity((Behavior)newValue);
				return;
			case 19:
				setOwnedEntry((Behavior)newValue);
				return;
			case 20:
				setOwnedExit((Behavior)newValue);
				return;
			case 21:
				getOwnedRegions().clear();
				getOwnedRegions().addAll((Collection<? extends Region>)newValue);
				return;
			case 22:
				setOwnedStateInvariant((Constraint)newValue);
				return;
			case 23:
				setRedefinedState((State)newValue);
				return;
			case 24:
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
				setOclContainer((OclElement)null);
				return;
			case 1:
				getOclContents().clear();
				return;
			case 2:
				getAnnotatingComments().clear();
				return;
			case 3:
				getOwnedAnnotations().clear();
				return;
			case 4:
				getOwnedComments().clear();
				return;
			case 5:
				getOwnedExtensions().clear();
				return;
			case 6:
				setName(NAME_EDEFAULT);
				return;
			case 7:
				getOwnedConstraints().clear();
				return;
			case 10:
				setOwningRegion((Region)null);
				return;
			case 15:
				getOwnedConnectionPoints().clear();
				return;
			case 16:
				getOwnedConnections().clear();
				return;
			case 17:
				getOwnedDeferrableTriggers().clear();
				return;
			case 18:
				setOwnedDoActivity((Behavior)null);
				return;
			case 19:
				setOwnedEntry((Behavior)null);
				return;
			case 20:
				setOwnedExit((Behavior)null);
				return;
			case 21:
				getOwnedRegions().clear();
				return;
			case 22:
				setOwnedStateInvariant((Constraint)null);
				return;
			case 23:
				setRedefinedState((State)null);
				return;
			case 24:
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
				return oclContainer != null;
			case 1:
				return oclContents != null && !oclContents.isEmpty();
			case 2:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case 3:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case 4:
				return ownedComments != null && !ownedComments.isEmpty();
			case 5:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case 6:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case 7:
				return ownedConstraints != null && !ownedConstraints.isEmpty();
			case 8:
				return incomingTransitions != null && !incomingTransitions.isEmpty();
			case 9:
				return outgoingTransitions != null && !outgoingTransitions.isEmpty();
			case 10:
				return getOwningRegion() != null;
			case 11:
				return IS_COMPOSITE_EDEFAULT == null ? getIsComposite() != null : !IS_COMPOSITE_EDEFAULT.equals(getIsComposite());
			case 12:
				return IS_ORTHOGONAL_EDEFAULT == null ? getIsOrthogonal() != null : !IS_ORTHOGONAL_EDEFAULT.equals(getIsOrthogonal());
			case 13:
				return IS_SIMPLE_EDEFAULT == null ? getIsSimple() != null : !IS_SIMPLE_EDEFAULT.equals(getIsSimple());
			case 14:
				return IS_SUBMACHINE_STATE_EDEFAULT == null ? getIsSubmachineState() != null : !IS_SUBMACHINE_STATE_EDEFAULT.equals(getIsSubmachineState());
			case 15:
				return ownedConnectionPoints != null && !ownedConnectionPoints.isEmpty();
			case 16:
				return ownedConnections != null && !ownedConnections.isEmpty();
			case 17:
				return ownedDeferrableTriggers != null && !ownedDeferrableTriggers.isEmpty();
			case 18:
				return ownedDoActivity != null;
			case 19:
				return ownedEntry != null;
			case 20:
				return ownedExit != null;
			case 21:
				return ownedRegions != null && !ownedRegions.isEmpty();
			case 22:
				return ownedStateInvariant != null;
			case 23:
				return redefinedState != null;
			case 24:
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
		if (baseClass == OclElement.class)
		{
			switch (derivedFeatureID)
			{
				case 0: return 0;
				case 1: return 1;
				default: return -1;
			}
		}
		if (baseClass == Element.class)
		{
			switch (derivedFeatureID)
			{
				case 2: return 2;
				case 3: return 3;
				case 4: return 4;
				case 5: return 5;
				default: return -1;
			}
		}
		if (baseClass == NamedElement.class)
		{
			switch (derivedFeatureID)
			{
				case 6: return 6;
				default: return -1;
			}
		}
		if (baseClass == Namespace.class)
		{
			switch (derivedFeatureID)
			{
				case 7: return 7;
				default: return -1;
			}
		}
		if (baseClass == Vertex.class)
		{
			switch (derivedFeatureID)
			{
				case 8: return 7;
				case 9: return 8;
				case 10: return 9;
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
		if (baseClass == OclElement.class)
		{
			switch (baseFeatureID)
			{
				case 0: return 0;
				case 1: return 1;
				default: return -1;
			}
		}
		if (baseClass == Element.class)
		{
			switch (baseFeatureID)
			{
				case 2: return 2;
				case 3: return 3;
				case 4: return 4;
				case 5: return 5;
				default: return -1;
			}
		}
		if (baseClass == NamedElement.class)
		{
			switch (baseFeatureID)
			{
				case 6: return 6;
				default: return -1;
			}
		}
		if (baseClass == Namespace.class)
		{
			switch (baseFeatureID)
			{
				case 7: return 7;
				default: return -1;
			}
		}
		if (baseClass == Vertex.class)
		{
			switch (baseFeatureID)
			{
				case 7: return 8;
				case 8: return 9;
				case 9: return 10;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString()
	{
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: "); //$NON-NLS-1$
		result.append(name);
		result.append(')');
		return result.toString();
	}


} //StateImpl
