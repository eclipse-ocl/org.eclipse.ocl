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
import org.eclipse.ocl.pivot.Behavior;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Region;
import org.eclipse.ocl.pivot.Transition;
import org.eclipse.ocl.pivot.TransitionKind;
import org.eclipse.ocl.pivot.Trigger;
import org.eclipse.ocl.pivot.Vertex;
import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.TransitionImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.TransitionImpl#getOwnedEffect <em>Owned Effect</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.TransitionImpl#getOwnedGuard <em>Owned Guard</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.TransitionImpl#getOwnedTriggers <em>Owned Triggers</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.TransitionImpl#getOwningRegion <em>Owning Region</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.TransitionImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.TransitionImpl#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TransitionImpl extends NamespaceImpl implements Transition
{
	/**
	 * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected static final TransitionKind KIND_EDEFAULT = TransitionKind.EXTERNAL;

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
	 * The array of enumeration values for '{@link TransitionKind Transition Kind}'
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	private static final TransitionKind[] KIND_EFLAG_VALUES = TransitionKind.values();

	/**
	 * The flags representing the value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected static final int KIND_EFLAG = 0x3 << KIND_EFLAG_OFFSET;

	/**
	 * The cached value of the '{@link #getOwnedEffect() <em>Owned Effect</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedEffect()
	 * @generated
	 * @ordered
	 */
	protected Behavior ownedEffect;

	/**
	 * The cached value of the '{@link #getOwnedGuard() <em>Owned Guard</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedGuard()
	 * @generated
	 * @ordered
	 */
	protected Constraint ownedGuard;

	/**
	 * The cached value of the '{@link #getOwnedTriggers() <em>Owned Triggers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedTriggers()
	 * @generated
	 * @ordered
	 */
	protected EList<Trigger> ownedTriggers;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected Vertex source;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected Vertex target;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TransitionImpl()
	{
		super();
		eFlags |= KIND_EFLAG_DEFAULT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PivotPackage.Literals.TRANSITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TransitionKind getKind()
	{
		return KIND_EFLAG_VALUES[(eFlags & KIND_EFLAG) >>> KIND_EFLAG_OFFSET];
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setKind(TransitionKind newKind)
	{
		TransitionKind oldKind = KIND_EFLAG_VALUES[(eFlags & KIND_EFLAG) >>> KIND_EFLAG_OFFSET];
		if (newKind == null) newKind = KIND_EDEFAULT;
		eFlags = eFlags & ~KIND_EFLAG | newKind.ordinal() << KIND_EFLAG_OFFSET;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.TRANSITION__KIND, oldKind, newKind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Behavior getOwnedEffect()
	{
		return ownedEffect;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedEffect(Behavior newOwnedEffect, NotificationChain msgs)
	{
		Behavior oldOwnedEffect = ownedEffect;
		ownedEffect = newOwnedEffect;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.TRANSITION__OWNED_EFFECT, oldOwnedEffect, newOwnedEffect);
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
	public void setOwnedEffect(Behavior newOwnedEffect)
	{
		if (newOwnedEffect != ownedEffect)
		{
			NotificationChain msgs = null;
			if (ownedEffect != null)
				msgs = ((InternalEObject)ownedEffect).eInverseRemove(this, PivotPackage.BEHAVIOR__OWNING_TRANSITION, Behavior.class, msgs);
			if (newOwnedEffect != null)
				msgs = ((InternalEObject)newOwnedEffect).eInverseAdd(this, PivotPackage.BEHAVIOR__OWNING_TRANSITION, Behavior.class, msgs);
			msgs = basicSetOwnedEffect(newOwnedEffect, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.TRANSITION__OWNED_EFFECT, newOwnedEffect, newOwnedEffect));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Constraint getOwnedGuard()
	{
		return ownedGuard;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedGuard(Constraint newOwnedGuard, NotificationChain msgs)
	{
		Constraint oldOwnedGuard = ownedGuard;
		ownedGuard = newOwnedGuard;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.TRANSITION__OWNED_GUARD, oldOwnedGuard, newOwnedGuard);
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
	public void setOwnedGuard(Constraint newOwnedGuard)
	{
		if (newOwnedGuard != ownedGuard)
		{
			NotificationChain msgs = null;
			if (ownedGuard != null)
				msgs = ((InternalEObject)ownedGuard).eInverseRemove(this, PivotPackage.CONSTRAINT__OWNING_TRANSITION, Constraint.class, msgs);
			if (newOwnedGuard != null)
				msgs = ((InternalEObject)newOwnedGuard).eInverseAdd(this, PivotPackage.CONSTRAINT__OWNING_TRANSITION, Constraint.class, msgs);
			msgs = basicSetOwnedGuard(newOwnedGuard, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.TRANSITION__OWNED_GUARD, newOwnedGuard, newOwnedGuard));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Trigger> getOwnedTriggers()
	{
		if (ownedTriggers == null)
		{
			ownedTriggers = new EObjectContainmentWithInverseEList<Trigger>(Trigger.class, this, PivotPackage.TRANSITION__OWNED_TRIGGERS, PivotPackage.TRIGGER__OWNING_TRANSITION);
		}
		return ownedTriggers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Region getOwningRegion()
	{
		if (eContainerFeatureID() != PivotPackage.TRANSITION__OWNING_REGION) return null;
		return (Region)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningRegion(Region newOwningRegion, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningRegion, PivotPackage.TRANSITION__OWNING_REGION, msgs);
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
		if (newOwningRegion != eInternalContainer() || (eContainerFeatureID() != PivotPackage.TRANSITION__OWNING_REGION && newOwningRegion != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningRegion))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningRegion != null)
				msgs = ((InternalEObject)newOwningRegion).eInverseAdd(this, PivotPackage.REGION__OWNED_TRANSITIONS, Region.class, msgs);
			msgs = basicSetOwningRegion(newOwningRegion, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.TRANSITION__OWNING_REGION, newOwningRegion, newOwningRegion));
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
			case PivotPackage.TRANSITION__ANNOTATING_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.TRANSITION__OWNED_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.TRANSITION__OWNED_EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case PivotPackage.TRANSITION__OWNED_EFFECT:
				if (ownedEffect != null)
					msgs = ((InternalEObject)ownedEffect).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.TRANSITION__OWNED_EFFECT, null, msgs);
				return basicSetOwnedEffect((Behavior)otherEnd, msgs);
			case PivotPackage.TRANSITION__OWNED_GUARD:
				if (ownedGuard != null)
					msgs = ((InternalEObject)ownedGuard).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.TRANSITION__OWNED_GUARD, null, msgs);
				return basicSetOwnedGuard((Constraint)otherEnd, msgs);
			case PivotPackage.TRANSITION__OWNED_TRIGGERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedTriggers()).basicAdd(otherEnd, msgs);
			case PivotPackage.TRANSITION__OWNING_REGION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningRegion((Region)otherEnd, msgs);
			case PivotPackage.TRANSITION__SOURCE:
				if (source != null)
					msgs = ((InternalEObject)source).eInverseRemove(this, PivotPackage.VERTEX__OUTGOING_TRANSITIONS, Vertex.class, msgs);
				return basicSetSource((Vertex)otherEnd, msgs);
			case PivotPackage.TRANSITION__TARGET:
				if (target != null)
					msgs = ((InternalEObject)target).eInverseRemove(this, PivotPackage.VERTEX__INCOMING_TRANSITIONS, Vertex.class, msgs);
				return basicSetTarget((Vertex)otherEnd, msgs);
		}
		return eDynamicInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Vertex getSource()
	{
		if (source != null && source.eIsProxy())
		{
			InternalEObject oldSource = (InternalEObject)source;
			source = (Vertex)eResolveProxy(oldSource);
			if (source != oldSource)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.TRANSITION__SOURCE, oldSource, source));
			}
		}
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Vertex basicGetSource()
	{
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSource(Vertex newSource, NotificationChain msgs)
	{
		Vertex oldSource = source;
		source = newSource;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.TRANSITION__SOURCE, oldSource, newSource);
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
	public void setSource(Vertex newSource)
	{
		if (newSource != source)
		{
			NotificationChain msgs = null;
			if (source != null)
				msgs = ((InternalEObject)source).eInverseRemove(this, PivotPackage.VERTEX__OUTGOING_TRANSITIONS, Vertex.class, msgs);
			if (newSource != null)
				msgs = ((InternalEObject)newSource).eInverseAdd(this, PivotPackage.VERTEX__OUTGOING_TRANSITIONS, Vertex.class, msgs);
			msgs = basicSetSource(newSource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.TRANSITION__SOURCE, newSource, newSource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Vertex getTarget()
	{
		if (target != null && target.eIsProxy())
		{
			InternalEObject oldTarget = (InternalEObject)target;
			target = (Vertex)eResolveProxy(oldTarget);
			if (target != oldTarget)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.TRANSITION__TARGET, oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Vertex basicGetTarget()
	{
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTarget(Vertex newTarget, NotificationChain msgs)
	{
		Vertex oldTarget = target;
		target = newTarget;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.TRANSITION__TARGET, oldTarget, newTarget);
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
	public void setTarget(Vertex newTarget)
	{
		if (newTarget != target)
		{
			NotificationChain msgs = null;
			if (target != null)
				msgs = ((InternalEObject)target).eInverseRemove(this, PivotPackage.VERTEX__INCOMING_TRANSITIONS, Vertex.class, msgs);
			if (newTarget != null)
				msgs = ((InternalEObject)newTarget).eInverseAdd(this, PivotPackage.VERTEX__INCOMING_TRANSITIONS, Vertex.class, msgs);
			msgs = basicSetTarget(newTarget, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.TRANSITION__TARGET, newTarget, newTarget));
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
			case PivotPackage.TRANSITION__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.TRANSITION__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.TRANSITION__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.TRANSITION__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.TRANSITION__OWNED_CONSTRAINTS:
				return ((InternalEList<?>)getOwnedConstraints()).basicRemove(otherEnd, msgs);
			case PivotPackage.TRANSITION__OWNED_EFFECT:
				return basicSetOwnedEffect(null, msgs);
			case PivotPackage.TRANSITION__OWNED_GUARD:
				return basicSetOwnedGuard(null, msgs);
			case PivotPackage.TRANSITION__OWNED_TRIGGERS:
				return ((InternalEList<?>)getOwnedTriggers()).basicRemove(otherEnd, msgs);
			case PivotPackage.TRANSITION__OWNING_REGION:
				return basicSetOwningRegion(null, msgs);
			case PivotPackage.TRANSITION__SOURCE:
				return basicSetSource(null, msgs);
			case PivotPackage.TRANSITION__TARGET:
				return basicSetTarget(null, msgs);
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
			case PivotPackage.TRANSITION__OWNING_REGION:
				return eInternalContainer().eInverseRemove(this, PivotPackage.REGION__OWNED_TRANSITIONS, Region.class, msgs);
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
			case PivotPackage.TRANSITION__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.TRANSITION__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.TRANSITION__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.TRANSITION__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.TRANSITION__NAME:
				return getName();
			case PivotPackage.TRANSITION__OWNED_CONSTRAINTS:
				return getOwnedConstraints();
			case PivotPackage.TRANSITION__KIND:
				return getKind();
			case PivotPackage.TRANSITION__OWNED_EFFECT:
				return getOwnedEffect();
			case PivotPackage.TRANSITION__OWNED_GUARD:
				return getOwnedGuard();
			case PivotPackage.TRANSITION__OWNED_TRIGGERS:
				return getOwnedTriggers();
			case PivotPackage.TRANSITION__OWNING_REGION:
				return getOwningRegion();
			case PivotPackage.TRANSITION__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
			case PivotPackage.TRANSITION__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
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
			case PivotPackage.TRANSITION__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.TRANSITION__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.TRANSITION__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.TRANSITION__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.TRANSITION__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.TRANSITION__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				getOwnedConstraints().addAll((Collection<? extends Constraint>)newValue);
				return;
			case PivotPackage.TRANSITION__KIND:
				setKind((TransitionKind)newValue);
				return;
			case PivotPackage.TRANSITION__OWNED_EFFECT:
				setOwnedEffect((Behavior)newValue);
				return;
			case PivotPackage.TRANSITION__OWNED_GUARD:
				setOwnedGuard((Constraint)newValue);
				return;
			case PivotPackage.TRANSITION__OWNED_TRIGGERS:
				getOwnedTriggers().clear();
				getOwnedTriggers().addAll((Collection<? extends Trigger>)newValue);
				return;
			case PivotPackage.TRANSITION__OWNING_REGION:
				setOwningRegion((Region)newValue);
				return;
			case PivotPackage.TRANSITION__SOURCE:
				setSource((Vertex)newValue);
				return;
			case PivotPackage.TRANSITION__TARGET:
				setTarget((Vertex)newValue);
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
			case PivotPackage.TRANSITION__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.TRANSITION__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.TRANSITION__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.TRANSITION__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.TRANSITION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.TRANSITION__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				return;
			case PivotPackage.TRANSITION__KIND:
				setKind(KIND_EDEFAULT);
				return;
			case PivotPackage.TRANSITION__OWNED_EFFECT:
				setOwnedEffect((Behavior)null);
				return;
			case PivotPackage.TRANSITION__OWNED_GUARD:
				setOwnedGuard((Constraint)null);
				return;
			case PivotPackage.TRANSITION__OWNED_TRIGGERS:
				getOwnedTriggers().clear();
				return;
			case PivotPackage.TRANSITION__OWNING_REGION:
				setOwningRegion((Region)null);
				return;
			case PivotPackage.TRANSITION__SOURCE:
				setSource((Vertex)null);
				return;
			case PivotPackage.TRANSITION__TARGET:
				setTarget((Vertex)null);
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
			case PivotPackage.TRANSITION__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.TRANSITION__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.TRANSITION__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.TRANSITION__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.TRANSITION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.TRANSITION__OWNED_CONSTRAINTS:
				return ownedConstraints != null && !ownedConstraints.isEmpty();
			case PivotPackage.TRANSITION__KIND:
				return (eFlags & KIND_EFLAG) != KIND_EFLAG_DEFAULT;
			case PivotPackage.TRANSITION__OWNED_EFFECT:
				return ownedEffect != null;
			case PivotPackage.TRANSITION__OWNED_GUARD:
				return ownedGuard != null;
			case PivotPackage.TRANSITION__OWNED_TRIGGERS:
				return ownedTriggers != null && !ownedTriggers.isEmpty();
			case PivotPackage.TRANSITION__OWNING_REGION:
				return getOwningRegion() != null;
			case PivotPackage.TRANSITION__SOURCE:
				return source != null;
			case PivotPackage.TRANSITION__TARGET:
				return target != null;
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
		return visitor.visitTransition(this);
	}
} //TransitionImpl
