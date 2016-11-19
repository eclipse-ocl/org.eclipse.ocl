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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
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
import org.eclipse.ocl.pivot.StereotypeExtender;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.Transition;
import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Behavior</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.BehaviorImpl#getOwningTransition <em>Owning Transition</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class BehaviorImpl extends ClassImpl implements Behavior
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BehaviorImpl()
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
		return PivotPackage.Literals.BEHAVIOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Transition getOwningTransition()
	{
		if (eContainerFeatureID() != PivotPackage.BEHAVIOR__OWNING_TRANSITION) return null;
		return (Transition)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningTransition(Transition newOwningTransition, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningTransition, PivotPackage.BEHAVIOR__OWNING_TRANSITION, msgs);
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
		if (newOwningTransition != eInternalContainer() || (eContainerFeatureID() != PivotPackage.BEHAVIOR__OWNING_TRANSITION && newOwningTransition != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningTransition))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningTransition != null)
				msgs = ((InternalEObject)newOwningTransition).eInverseAdd(this, PivotPackage.TRANSITION__OWNED_EFFECT, Transition.class, msgs);
			msgs = basicSetOwningTransition(newOwningTransition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.BEHAVIOR__OWNING_TRANSITION, newOwningTransition, newOwningTransition));
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
			case PivotPackage.BEHAVIOR__ANNOTATING_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.BEHAVIOR__OWNED_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.BEHAVIOR__OWNED_EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case PivotPackage.BEHAVIOR__OWNED_BINDINGS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedBindings()).basicAdd(otherEnd, msgs);
			case PivotPackage.BEHAVIOR__OWNED_SIGNATURE:
				if (ownedSignature != null)
					msgs = ((InternalEObject)ownedSignature).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.BEHAVIOR__OWNED_SIGNATURE, null, msgs);
				return basicSetOwnedSignature((TemplateSignature)otherEnd, msgs);
			case PivotPackage.BEHAVIOR__EXTENDERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExtenders()).basicAdd(otherEnd, msgs);
			case PivotPackage.BEHAVIOR__OWNED_OPERATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedOperations()).basicAdd(otherEnd, msgs);
			case PivotPackage.BEHAVIOR__OWNED_PROPERTIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedProperties()).basicAdd(otherEnd, msgs);
			case PivotPackage.BEHAVIOR__OWNING_PACKAGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningPackage((org.eclipse.ocl.pivot.Package)otherEnd, msgs);
			case PivotPackage.BEHAVIOR__OWNING_TRANSITION:
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
			case PivotPackage.BEHAVIOR__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.BEHAVIOR__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.BEHAVIOR__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.BEHAVIOR__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.BEHAVIOR__OWNED_CONSTRAINTS:
				return ((InternalEList<?>)getOwnedConstraints()).basicRemove(otherEnd, msgs);
			case PivotPackage.BEHAVIOR__OWNED_BINDINGS:
				return ((InternalEList<?>)getOwnedBindings()).basicRemove(otherEnd, msgs);
			case PivotPackage.BEHAVIOR__OWNED_SIGNATURE:
				return basicSetOwnedSignature(null, msgs);
			case PivotPackage.BEHAVIOR__EXTENDERS:
				return ((InternalEList<?>)getExtenders()).basicRemove(otherEnd, msgs);
			case PivotPackage.BEHAVIOR__OWNED_BEHAVIORS:
				return ((InternalEList<?>)getOwnedBehaviors()).basicRemove(otherEnd, msgs);
			case PivotPackage.BEHAVIOR__OWNED_INVARIANTS:
				return ((InternalEList<?>)getOwnedInvariants()).basicRemove(otherEnd, msgs);
			case PivotPackage.BEHAVIOR__OWNED_OPERATIONS:
				return ((InternalEList<?>)getOwnedOperations()).basicRemove(otherEnd, msgs);
			case PivotPackage.BEHAVIOR__OWNED_PROPERTIES:
				return ((InternalEList<?>)getOwnedProperties()).basicRemove(otherEnd, msgs);
			case PivotPackage.BEHAVIOR__OWNING_PACKAGE:
				return basicSetOwningPackage(null, msgs);
			case PivotPackage.BEHAVIOR__OWNING_TRANSITION:
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
			case PivotPackage.BEHAVIOR__OWNING_PACKAGE:
				return eInternalContainer().eInverseRemove(this, PivotPackage.PACKAGE__OWNED_CLASSES, org.eclipse.ocl.pivot.Package.class, msgs);
			case PivotPackage.BEHAVIOR__OWNING_TRANSITION:
				return eInternalContainer().eInverseRemove(this, PivotPackage.TRANSITION__OWNED_EFFECT, Transition.class, msgs);
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
			case PivotPackage.BEHAVIOR__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.BEHAVIOR__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.BEHAVIOR__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.BEHAVIOR__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.BEHAVIOR__NAME:
				return getName();
			case PivotPackage.BEHAVIOR__OWNED_CONSTRAINTS:
				return getOwnedConstraints();
			case PivotPackage.BEHAVIOR__OWNED_BINDINGS:
				return getOwnedBindings();
			case PivotPackage.BEHAVIOR__OWNED_SIGNATURE:
				return getOwnedSignature();
			case PivotPackage.BEHAVIOR__UNSPECIALIZED_ELEMENT:
				return getUnspecializedElement();
			case PivotPackage.BEHAVIOR__EXTENDERS:
				return getExtenders();
			case PivotPackage.BEHAVIOR__INSTANCE_CLASS_NAME:
				return getInstanceClassName();
			case PivotPackage.BEHAVIOR__IS_ABSTRACT:
				return isIsAbstract();
			case PivotPackage.BEHAVIOR__IS_ACTIVE:
				return isIsActive();
			case PivotPackage.BEHAVIOR__IS_INTERFACE:
				return isIsInterface();
			case PivotPackage.BEHAVIOR__OWNED_BEHAVIORS:
				return getOwnedBehaviors();
			case PivotPackage.BEHAVIOR__OWNED_INVARIANTS:
				return getOwnedInvariants();
			case PivotPackage.BEHAVIOR__OWNED_OPERATIONS:
				return getOwnedOperations();
			case PivotPackage.BEHAVIOR__OWNED_PROPERTIES:
				return getOwnedProperties();
			case PivotPackage.BEHAVIOR__OWNING_PACKAGE:
				return getOwningPackage();
			case PivotPackage.BEHAVIOR__SUPER_CLASSES:
				return getSuperClasses();
			case PivotPackage.BEHAVIOR__OWNING_TRANSITION:
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
			case PivotPackage.BEHAVIOR__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.BEHAVIOR__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.BEHAVIOR__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.BEHAVIOR__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.BEHAVIOR__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.BEHAVIOR__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				getOwnedConstraints().addAll((Collection<? extends Constraint>)newValue);
				return;
			case PivotPackage.BEHAVIOR__OWNED_BINDINGS:
				getOwnedBindings().clear();
				getOwnedBindings().addAll((Collection<? extends TemplateBinding>)newValue);
				return;
			case PivotPackage.BEHAVIOR__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignature)newValue);
				return;
			case PivotPackage.BEHAVIOR__UNSPECIALIZED_ELEMENT:
				setUnspecializedElement((TemplateableElement)newValue);
				return;
			case PivotPackage.BEHAVIOR__EXTENDERS:
				getExtenders().clear();
				getExtenders().addAll((Collection<? extends StereotypeExtender>)newValue);
				return;
			case PivotPackage.BEHAVIOR__INSTANCE_CLASS_NAME:
				setInstanceClassName((String)newValue);
				return;
			case PivotPackage.BEHAVIOR__IS_ABSTRACT:
				setIsAbstract((Boolean)newValue);
				return;
			case PivotPackage.BEHAVIOR__IS_ACTIVE:
				setIsActive((Boolean)newValue);
				return;
			case PivotPackage.BEHAVIOR__IS_INTERFACE:
				setIsInterface((Boolean)newValue);
				return;
			case PivotPackage.BEHAVIOR__OWNED_BEHAVIORS:
				getOwnedBehaviors().clear();
				getOwnedBehaviors().addAll((Collection<? extends Behavior>)newValue);
				return;
			case PivotPackage.BEHAVIOR__OWNED_INVARIANTS:
				getOwnedInvariants().clear();
				getOwnedInvariants().addAll((Collection<? extends Constraint>)newValue);
				return;
			case PivotPackage.BEHAVIOR__OWNED_OPERATIONS:
				getOwnedOperations().clear();
				getOwnedOperations().addAll((Collection<? extends Operation>)newValue);
				return;
			case PivotPackage.BEHAVIOR__OWNED_PROPERTIES:
				getOwnedProperties().clear();
				getOwnedProperties().addAll((Collection<? extends Property>)newValue);
				return;
			case PivotPackage.BEHAVIOR__OWNING_PACKAGE:
				setOwningPackage((org.eclipse.ocl.pivot.Package)newValue);
				return;
			case PivotPackage.BEHAVIOR__SUPER_CLASSES:
				getSuperClasses().clear();
				getSuperClasses().addAll((Collection<? extends org.eclipse.ocl.pivot.Class>)newValue);
				return;
			case PivotPackage.BEHAVIOR__OWNING_TRANSITION:
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
			case PivotPackage.BEHAVIOR__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.BEHAVIOR__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.BEHAVIOR__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.BEHAVIOR__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.BEHAVIOR__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.BEHAVIOR__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				return;
			case PivotPackage.BEHAVIOR__OWNED_BINDINGS:
				getOwnedBindings().clear();
				return;
			case PivotPackage.BEHAVIOR__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignature)null);
				return;
			case PivotPackage.BEHAVIOR__UNSPECIALIZED_ELEMENT:
				setUnspecializedElement((TemplateableElement)null);
				return;
			case PivotPackage.BEHAVIOR__EXTENDERS:
				getExtenders().clear();
				return;
			case PivotPackage.BEHAVIOR__INSTANCE_CLASS_NAME:
				setInstanceClassName(INSTANCE_CLASS_NAME_EDEFAULT);
				return;
			case PivotPackage.BEHAVIOR__IS_ABSTRACT:
				setIsAbstract(IS_ABSTRACT_EDEFAULT);
				return;
			case PivotPackage.BEHAVIOR__IS_ACTIVE:
				setIsActive(IS_ACTIVE_EDEFAULT);
				return;
			case PivotPackage.BEHAVIOR__IS_INTERFACE:
				setIsInterface(IS_INTERFACE_EDEFAULT);
				return;
			case PivotPackage.BEHAVIOR__OWNED_BEHAVIORS:
				getOwnedBehaviors().clear();
				return;
			case PivotPackage.BEHAVIOR__OWNED_INVARIANTS:
				getOwnedInvariants().clear();
				return;
			case PivotPackage.BEHAVIOR__OWNED_OPERATIONS:
				getOwnedOperations().clear();
				return;
			case PivotPackage.BEHAVIOR__OWNED_PROPERTIES:
				getOwnedProperties().clear();
				return;
			case PivotPackage.BEHAVIOR__OWNING_PACKAGE:
				setOwningPackage((org.eclipse.ocl.pivot.Package)null);
				return;
			case PivotPackage.BEHAVIOR__SUPER_CLASSES:
				getSuperClasses().clear();
				return;
			case PivotPackage.BEHAVIOR__OWNING_TRANSITION:
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
			case PivotPackage.BEHAVIOR__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.BEHAVIOR__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.BEHAVIOR__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.BEHAVIOR__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.BEHAVIOR__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.BEHAVIOR__OWNED_CONSTRAINTS:
				return ownedConstraints != null && !ownedConstraints.isEmpty();
			case PivotPackage.BEHAVIOR__OWNED_BINDINGS:
				return ownedBindings != null && !ownedBindings.isEmpty();
			case PivotPackage.BEHAVIOR__OWNED_SIGNATURE:
				return ownedSignature != null;
			case PivotPackage.BEHAVIOR__UNSPECIALIZED_ELEMENT:
				return unspecializedElement != null;
			case PivotPackage.BEHAVIOR__EXTENDERS:
				return extenders != null && !extenders.isEmpty();
			case PivotPackage.BEHAVIOR__INSTANCE_CLASS_NAME:
				return INSTANCE_CLASS_NAME_EDEFAULT == null ? instanceClassName != null : !INSTANCE_CLASS_NAME_EDEFAULT.equals(instanceClassName);
			case PivotPackage.BEHAVIOR__IS_ABSTRACT:
				return ((eFlags & IS_ABSTRACT_EFLAG) != 0) != IS_ABSTRACT_EDEFAULT;
			case PivotPackage.BEHAVIOR__IS_ACTIVE:
				return ((eFlags & IS_ACTIVE_EFLAG) != 0) != IS_ACTIVE_EDEFAULT;
			case PivotPackage.BEHAVIOR__IS_INTERFACE:
				return ((eFlags & IS_INTERFACE_EFLAG) != 0) != IS_INTERFACE_EDEFAULT;
			case PivotPackage.BEHAVIOR__OWNED_BEHAVIORS:
				return ownedBehaviors != null && !ownedBehaviors.isEmpty();
			case PivotPackage.BEHAVIOR__OWNED_INVARIANTS:
				return ownedInvariants != null && !ownedInvariants.isEmpty();
			case PivotPackage.BEHAVIOR__OWNED_OPERATIONS:
				return ownedOperations != null && !ownedOperations.isEmpty();
			case PivotPackage.BEHAVIOR__OWNED_PROPERTIES:
				return ownedProperties != null && !ownedProperties.isEmpty();
			case PivotPackage.BEHAVIOR__OWNING_PACKAGE:
				return getOwningPackage() != null;
			case PivotPackage.BEHAVIOR__SUPER_CLASSES:
				return superClasses != null && !superClasses.isEmpty();
			case PivotPackage.BEHAVIOR__OWNING_TRANSITION:
				return getOwningTransition() != null;
		}
		return eDynamicIsSet(featureID);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitBehavior(this);
	}
} //BehaviorImpl
