/**
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.InstanceSpecification;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Slot;
import org.eclipse.ocl.pivot.ValueSpecification;
import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Slot</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.SlotImpl#getDefiningProperty <em>Defining Property</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.SlotImpl#getOwnedValues <em>Owned Values</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.SlotImpl#getOwningInstance <em>Owning Instance</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SlotImpl extends ElementImpl implements Slot
{
	/**
	 * The cached value of the '{@link #getDefiningProperty() <em>Defining Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefiningProperty()
	 * @generated
	 * @ordered
	 */
	protected Property definingProperty;

	/**
	 * The cached value of the '{@link #getOwnedValues() <em>Owned Values</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedValues()
	 * @generated
	 * @ordered
	 */
	protected EList<ValueSpecification> ownedValues;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SlotImpl()
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
		return PivotPackage.Literals.SLOT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Property getDefiningProperty()
	{
		if (definingProperty != null && definingProperty.eIsProxy())
		{
			InternalEObject oldDefiningProperty = (InternalEObject)definingProperty;
			definingProperty = (Property)eResolveProxy(oldDefiningProperty);
			if (definingProperty != oldDefiningProperty)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.SLOT__DEFINING_PROPERTY, oldDefiningProperty, definingProperty));
			}
		}
		return definingProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property basicGetDefiningProperty()
	{
		return definingProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDefiningProperty(Property newDefiningProperty)
	{
		Property oldDefiningProperty = definingProperty;
		definingProperty = newDefiningProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.SLOT__DEFINING_PROPERTY, oldDefiningProperty, definingProperty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<ValueSpecification> getOwnedValues()
	{
		if (ownedValues == null)
		{
			ownedValues = new EObjectContainmentEList<ValueSpecification>(ValueSpecification.class, this, PivotPackage.SLOT__OWNED_VALUES);
		}
		return ownedValues;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InstanceSpecification getOwningInstance()
	{
		if (eContainerFeatureID() != PivotPackage.SLOT__OWNING_INSTANCE) return null;
		return (InstanceSpecification)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningInstance(InstanceSpecification newOwningInstance, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningInstance, PivotPackage.SLOT__OWNING_INSTANCE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningInstance(InstanceSpecification newOwningInstance)
	{
		if (newOwningInstance != eInternalContainer() || (eContainerFeatureID() != PivotPackage.SLOT__OWNING_INSTANCE && newOwningInstance != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningInstance))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningInstance != null)
				msgs = ((InternalEObject)newOwningInstance).eInverseAdd(this, PivotPackage.INSTANCE_SPECIFICATION__OWNED_SLOTS, InstanceSpecification.class, msgs);
			msgs = basicSetOwningInstance(newOwningInstance, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.SLOT__OWNING_INSTANCE, newOwningInstance, newOwningInstance));
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
			case PivotPackage.SLOT__ANNOTATING_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.SLOT__OWNED_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.SLOT__OWNED_EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case PivotPackage.SLOT__OWNING_INSTANCE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningInstance((InstanceSpecification)otherEnd, msgs);
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
			case PivotPackage.SLOT__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.SLOT__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.SLOT__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.SLOT__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.SLOT__OWNED_VALUES:
				return ((InternalEList<?>)getOwnedValues()).basicRemove(otherEnd, msgs);
			case PivotPackage.SLOT__OWNING_INSTANCE:
				return basicSetOwningInstance(null, msgs);
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
			case PivotPackage.SLOT__OWNING_INSTANCE:
				return eInternalContainer().eInverseRemove(this, PivotPackage.INSTANCE_SPECIFICATION__OWNED_SLOTS, InstanceSpecification.class, msgs);
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
			case PivotPackage.SLOT__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.SLOT__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.SLOT__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.SLOT__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.SLOT__DEFINING_PROPERTY:
				if (resolve) return getDefiningProperty();
				return basicGetDefiningProperty();
			case PivotPackage.SLOT__OWNED_VALUES:
				return getOwnedValues();
			case PivotPackage.SLOT__OWNING_INSTANCE:
				return getOwningInstance();
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
			case PivotPackage.SLOT__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.SLOT__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.SLOT__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.SLOT__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.SLOT__DEFINING_PROPERTY:
				setDefiningProperty((Property)newValue);
				return;
			case PivotPackage.SLOT__OWNED_VALUES:
				getOwnedValues().clear();
				getOwnedValues().addAll((Collection<? extends ValueSpecification>)newValue);
				return;
			case PivotPackage.SLOT__OWNING_INSTANCE:
				setOwningInstance((InstanceSpecification)newValue);
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
			case PivotPackage.SLOT__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.SLOT__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.SLOT__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.SLOT__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.SLOT__DEFINING_PROPERTY:
				setDefiningProperty((Property)null);
				return;
			case PivotPackage.SLOT__OWNED_VALUES:
				getOwnedValues().clear();
				return;
			case PivotPackage.SLOT__OWNING_INSTANCE:
				setOwningInstance((InstanceSpecification)null);
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
			case PivotPackage.SLOT__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.SLOT__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.SLOT__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.SLOT__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.SLOT__DEFINING_PROPERTY:
				return definingProperty != null;
			case PivotPackage.SLOT__OWNED_VALUES:
				return ownedValues != null && !ownedValues.isEmpty();
			case PivotPackage.SLOT__OWNING_INSTANCE:
				return getOwningInstance() != null;
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitSlot(this);
	}

} //SlotImpl
