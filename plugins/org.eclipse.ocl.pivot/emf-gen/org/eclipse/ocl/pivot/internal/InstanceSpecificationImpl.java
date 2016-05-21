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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.InstanceSpecification;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Slot;
import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Instance Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.InstanceSpecificationImpl#getClasses <em>Classes</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.InstanceSpecificationImpl#getOwnedSlots <em>Owned Slots</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.InstanceSpecificationImpl#getOwnedSpecification <em>Owned Specification</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.InstanceSpecificationImpl#getOwningPackage <em>Owning Package</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InstanceSpecificationImpl extends NamedElementImpl implements InstanceSpecification
{
	/**
	 * The cached value of the '{@link #getClasses() <em>Classes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<org.eclipse.ocl.pivot.Class> classes;

	/**
	 * The cached value of the '{@link #getOwnedSlots() <em>Owned Slots</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSlots()
	 * @generated
	 * @ordered
	 */
	protected EList<Slot> ownedSlots;

	/**
	 * The cached value of the '{@link #getOwnedSpecification() <em>Owned Specification</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSpecification()
	 * @generated
	 * @ordered
	 */
	protected LanguageExpression ownedSpecification;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InstanceSpecificationImpl()
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
		return PivotPackage.Literals.INSTANCE_SPECIFICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<org.eclipse.ocl.pivot.Class> getClasses()
	{
		if (classes == null)
		{
			classes = new EObjectResolvingEList<org.eclipse.ocl.pivot.Class>(org.eclipse.ocl.pivot.Class.class, this, PivotPackage.INSTANCE_SPECIFICATION__CLASSES);
		}
		return classes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Slot> getOwnedSlots()
	{
		if (ownedSlots == null)
		{
			ownedSlots = new EObjectContainmentWithInverseEList<Slot>(Slot.class, this, PivotPackage.INSTANCE_SPECIFICATION__OWNED_SLOTS, PivotPackage.SLOT__OWNING_INSTANCE);
		}
		return ownedSlots;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LanguageExpression getOwnedSpecification()
	{
		return ownedSpecification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedSpecification(LanguageExpression newOwnedSpecification, NotificationChain msgs)
	{
		LanguageExpression oldOwnedSpecification = ownedSpecification;
		ownedSpecification = newOwnedSpecification;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.INSTANCE_SPECIFICATION__OWNED_SPECIFICATION, oldOwnedSpecification, newOwnedSpecification);
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
	public void setOwnedSpecification(LanguageExpression newOwnedSpecification)
	{
		if (newOwnedSpecification != ownedSpecification)
		{
			NotificationChain msgs = null;
			if (ownedSpecification != null)
				msgs = ((InternalEObject)ownedSpecification).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.INSTANCE_SPECIFICATION__OWNED_SPECIFICATION, null, msgs);
			if (newOwnedSpecification != null)
				msgs = ((InternalEObject)newOwnedSpecification).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PivotPackage.INSTANCE_SPECIFICATION__OWNED_SPECIFICATION, null, msgs);
			msgs = basicSetOwnedSpecification(newOwnedSpecification, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.INSTANCE_SPECIFICATION__OWNED_SPECIFICATION, newOwnedSpecification, newOwnedSpecification));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.eclipse.ocl.pivot.Package getOwningPackage()
	{
		if (eContainerFeatureID() != PivotPackage.INSTANCE_SPECIFICATION__OWNING_PACKAGE) return null;
		return (org.eclipse.ocl.pivot.Package)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningPackage(org.eclipse.ocl.pivot.Package newOwningPackage, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningPackage, PivotPackage.INSTANCE_SPECIFICATION__OWNING_PACKAGE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningPackage(org.eclipse.ocl.pivot.Package newOwningPackage)
	{
		if (newOwningPackage != eInternalContainer() || (eContainerFeatureID() != PivotPackage.INSTANCE_SPECIFICATION__OWNING_PACKAGE && newOwningPackage != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningPackage))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningPackage != null)
				msgs = ((InternalEObject)newOwningPackage).eInverseAdd(this, PivotPackage.PACKAGE__OWNED_INSTANCES, org.eclipse.ocl.pivot.Package.class, msgs);
			msgs = basicSetOwningPackage(newOwningPackage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.INSTANCE_SPECIFICATION__OWNING_PACKAGE, newOwningPackage, newOwningPackage));
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
			case PivotPackage.INSTANCE_SPECIFICATION__ANNOTATING_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_SLOTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedSlots()).basicAdd(otherEnd, msgs);
			case PivotPackage.INSTANCE_SPECIFICATION__OWNING_PACKAGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningPackage((org.eclipse.ocl.pivot.Package)otherEnd, msgs);
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
			case PivotPackage.INSTANCE_SPECIFICATION__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_SLOTS:
				return ((InternalEList<?>)getOwnedSlots()).basicRemove(otherEnd, msgs);
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_SPECIFICATION:
				return basicSetOwnedSpecification(null, msgs);
			case PivotPackage.INSTANCE_SPECIFICATION__OWNING_PACKAGE:
				return basicSetOwningPackage(null, msgs);
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
			case PivotPackage.INSTANCE_SPECIFICATION__OWNING_PACKAGE:
				return eInternalContainer().eInverseRemove(this, PivotPackage.PACKAGE__OWNED_INSTANCES, org.eclipse.ocl.pivot.Package.class, msgs);
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
			case PivotPackage.INSTANCE_SPECIFICATION__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.INSTANCE_SPECIFICATION__NAME:
				return getName();
			case PivotPackage.INSTANCE_SPECIFICATION__CLASSES:
				return getClasses();
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_SLOTS:
				return getOwnedSlots();
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_SPECIFICATION:
				return getOwnedSpecification();
			case PivotPackage.INSTANCE_SPECIFICATION__OWNING_PACKAGE:
				return getOwningPackage();
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
			case PivotPackage.INSTANCE_SPECIFICATION__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.INSTANCE_SPECIFICATION__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.INSTANCE_SPECIFICATION__CLASSES:
				getClasses().clear();
				getClasses().addAll((Collection<? extends org.eclipse.ocl.pivot.Class>)newValue);
				return;
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_SLOTS:
				getOwnedSlots().clear();
				getOwnedSlots().addAll((Collection<? extends Slot>)newValue);
				return;
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_SPECIFICATION:
				setOwnedSpecification((LanguageExpression)newValue);
				return;
			case PivotPackage.INSTANCE_SPECIFICATION__OWNING_PACKAGE:
				setOwningPackage((org.eclipse.ocl.pivot.Package)newValue);
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
			case PivotPackage.INSTANCE_SPECIFICATION__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.INSTANCE_SPECIFICATION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.INSTANCE_SPECIFICATION__CLASSES:
				getClasses().clear();
				return;
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_SLOTS:
				getOwnedSlots().clear();
				return;
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_SPECIFICATION:
				setOwnedSpecification((LanguageExpression)null);
				return;
			case PivotPackage.INSTANCE_SPECIFICATION__OWNING_PACKAGE:
				setOwningPackage((org.eclipse.ocl.pivot.Package)null);
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
			case PivotPackage.INSTANCE_SPECIFICATION__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.INSTANCE_SPECIFICATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.INSTANCE_SPECIFICATION__CLASSES:
				return classes != null && !classes.isEmpty();
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_SLOTS:
				return ownedSlots != null && !ownedSlots.isEmpty();
			case PivotPackage.INSTANCE_SPECIFICATION__OWNED_SPECIFICATION:
				return ownedSpecification != null;
			case PivotPackage.INSTANCE_SPECIFICATION__OWNING_PACKAGE:
				return getOwningPackage() != null;
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitInstanceSpecification(this);
	}

} //InstanceSpecificationImpl
