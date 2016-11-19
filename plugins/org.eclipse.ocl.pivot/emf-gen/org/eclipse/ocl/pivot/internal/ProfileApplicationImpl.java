/*******************************************************************************
 * Copyright (c) 2014, 2015 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink (CEA LIST) - Initial API and implementation
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
import org.eclipse.ocl.pivot.Profile;
import org.eclipse.ocl.pivot.ProfileApplication;
import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Profile Application</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ProfileApplicationImpl#getAppliedProfile <em>Applied Profile</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ProfileApplicationImpl#isIsStrict <em>Is Strict</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ProfileApplicationImpl#getOwningPackage <em>Owning Package</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProfileApplicationImpl extends ElementImpl implements ProfileApplication
{
	/**
	 * The cached value of the '{@link #getAppliedProfile() <em>Applied Profile</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAppliedProfile()
	 * @generated
	 * @ordered
	 */
	protected Profile appliedProfile;

	/**
	 * The default value of the '{@link #isIsStrict() <em>Is Strict</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsStrict()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_STRICT_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isIsStrict() <em>Is Strict</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsStrict()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_STRICT_EFLAG = 1 << 8;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProfileApplicationImpl()
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
		return PivotPackage.Literals.PROFILE_APPLICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Profile getAppliedProfile()
	{
		if (appliedProfile != null && appliedProfile.eIsProxy())
		{
			InternalEObject oldAppliedProfile = (InternalEObject)appliedProfile;
			appliedProfile = (Profile)eResolveProxy(oldAppliedProfile);
			if (appliedProfile != oldAppliedProfile)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.PROFILE_APPLICATION__APPLIED_PROFILE, oldAppliedProfile, appliedProfile));
			}
		}
		return appliedProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Profile basicGetAppliedProfile()
	{
		return appliedProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAppliedProfile(Profile newAppliedProfile, NotificationChain msgs)
	{
		Profile oldAppliedProfile = appliedProfile;
		appliedProfile = newAppliedProfile;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.PROFILE_APPLICATION__APPLIED_PROFILE, oldAppliedProfile, newAppliedProfile);
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
	public void setAppliedProfile(Profile newAppliedProfile)
	{
		if (newAppliedProfile != appliedProfile)
		{
			NotificationChain msgs = null;
			if (appliedProfile != null)
				msgs = ((InternalEObject)appliedProfile).eInverseRemove(this, PivotPackage.PROFILE__PROFILE_APPLICATIONS, Profile.class, msgs);
			if (newAppliedProfile != null)
				msgs = ((InternalEObject)newAppliedProfile).eInverseAdd(this, PivotPackage.PROFILE__PROFILE_APPLICATIONS, Profile.class, msgs);
			msgs = basicSetAppliedProfile(newAppliedProfile, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PROFILE_APPLICATION__APPLIED_PROFILE, newAppliedProfile, newAppliedProfile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsStrict()
	{
		return (eFlags & IS_STRICT_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsStrict(boolean newIsStrict)
	{
		boolean oldIsStrict = (eFlags & IS_STRICT_EFLAG) != 0;
		if (newIsStrict) eFlags |= IS_STRICT_EFLAG; else eFlags &= ~IS_STRICT_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PROFILE_APPLICATION__IS_STRICT, oldIsStrict, newIsStrict));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.eclipse.ocl.pivot.Package getOwningPackage()
	{
		if (eContainerFeatureID() != PivotPackage.PROFILE_APPLICATION__OWNING_PACKAGE) return null;
		return (org.eclipse.ocl.pivot.Package)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningPackage(org.eclipse.ocl.pivot.Package newOwningPackage, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningPackage, PivotPackage.PROFILE_APPLICATION__OWNING_PACKAGE, msgs);
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
		if (newOwningPackage != eInternalContainer() || (eContainerFeatureID() != PivotPackage.PROFILE_APPLICATION__OWNING_PACKAGE && newOwningPackage != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningPackage))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningPackage != null)
				msgs = ((InternalEObject)newOwningPackage).eInverseAdd(this, PivotPackage.PACKAGE__OWNED_PROFILE_APPLICATIONS, org.eclipse.ocl.pivot.Package.class, msgs);
			msgs = basicSetOwningPackage(newOwningPackage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PROFILE_APPLICATION__OWNING_PACKAGE, newOwningPackage, newOwningPackage));
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
			case PivotPackage.PROFILE_APPLICATION__ANNOTATING_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.PROFILE_APPLICATION__OWNED_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.PROFILE_APPLICATION__OWNED_EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case PivotPackage.PROFILE_APPLICATION__APPLIED_PROFILE:
				if (appliedProfile != null)
					msgs = ((InternalEObject)appliedProfile).eInverseRemove(this, PivotPackage.PROFILE__PROFILE_APPLICATIONS, Profile.class, msgs);
				return basicSetAppliedProfile((Profile)otherEnd, msgs);
			case PivotPackage.PROFILE_APPLICATION__OWNING_PACKAGE:
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
			case PivotPackage.PROFILE_APPLICATION__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.PROFILE_APPLICATION__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.PROFILE_APPLICATION__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.PROFILE_APPLICATION__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.PROFILE_APPLICATION__APPLIED_PROFILE:
				return basicSetAppliedProfile(null, msgs);
			case PivotPackage.PROFILE_APPLICATION__OWNING_PACKAGE:
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
			case PivotPackage.PROFILE_APPLICATION__OWNING_PACKAGE:
				return eInternalContainer().eInverseRemove(this, PivotPackage.PACKAGE__OWNED_PROFILE_APPLICATIONS, org.eclipse.ocl.pivot.Package.class, msgs);
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
			case PivotPackage.PROFILE_APPLICATION__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.PROFILE_APPLICATION__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.PROFILE_APPLICATION__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.PROFILE_APPLICATION__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.PROFILE_APPLICATION__APPLIED_PROFILE:
				if (resolve) return getAppliedProfile();
				return basicGetAppliedProfile();
			case PivotPackage.PROFILE_APPLICATION__IS_STRICT:
				return isIsStrict();
			case PivotPackage.PROFILE_APPLICATION__OWNING_PACKAGE:
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
			case PivotPackage.PROFILE_APPLICATION__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.PROFILE_APPLICATION__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.PROFILE_APPLICATION__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.PROFILE_APPLICATION__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.PROFILE_APPLICATION__APPLIED_PROFILE:
				setAppliedProfile((Profile)newValue);
				return;
			case PivotPackage.PROFILE_APPLICATION__IS_STRICT:
				setIsStrict((Boolean)newValue);
				return;
			case PivotPackage.PROFILE_APPLICATION__OWNING_PACKAGE:
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
			case PivotPackage.PROFILE_APPLICATION__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.PROFILE_APPLICATION__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.PROFILE_APPLICATION__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.PROFILE_APPLICATION__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.PROFILE_APPLICATION__APPLIED_PROFILE:
				setAppliedProfile((Profile)null);
				return;
			case PivotPackage.PROFILE_APPLICATION__IS_STRICT:
				setIsStrict(IS_STRICT_EDEFAULT);
				return;
			case PivotPackage.PROFILE_APPLICATION__OWNING_PACKAGE:
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
			case PivotPackage.PROFILE_APPLICATION__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.PROFILE_APPLICATION__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.PROFILE_APPLICATION__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.PROFILE_APPLICATION__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.PROFILE_APPLICATION__APPLIED_PROFILE:
				return appliedProfile != null;
			case PivotPackage.PROFILE_APPLICATION__IS_STRICT:
				return ((eFlags & IS_STRICT_EFLAG) != 0) != IS_STRICT_EDEFAULT;
			case PivotPackage.PROFILE_APPLICATION__OWNING_PACKAGE:
				return getOwningPackage() != null;
		}
		return eDynamicIsSet(featureID);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitProfileApplication(this);
	}

	@Override
	public String toString() {
		return super.toString();
	}
} //ProfileApplicationImpl
