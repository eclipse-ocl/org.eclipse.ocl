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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.ocl.ocl.Comment;
import org.eclipse.ocl.ocl.Element;
import org.eclipse.ocl.ocl.ElementExtension;
import org.eclipse.ocl.ocl.OCLASPackage;
import org.eclipse.ocl.ocl.OclElement;
import org.eclipse.ocl.ocl.Profile;
import org.eclipse.ocl.ocl.ProfileApplication;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Profile Application</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.internal.ProfileApplicationImpl#getAppliedProfile <em>Applied Profile</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.ProfileApplicationImpl#getIsStrict <em>Is Strict</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.ProfileApplicationImpl#getOwningPackage <em>Owning Package</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProfileApplicationImpl extends ElementImpl implements ProfileApplication
{
	/**
	 * The number of structural features of the '<em>Profile Application</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PROFILE_APPLICATION_FEATURE_COUNT = ElementImpl.ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Profile Application</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PROFILE_APPLICATION_OPERATION_COUNT = ElementImpl.ELEMENT_OPERATION_COUNT + 0;


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
	 * The default value of the '{@link #getIsStrict() <em>Is Strict</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsStrict()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_STRICT_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getIsStrict() <em>Is Strict</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsStrict()
	 * @generated
	 * @ordered
	 */
	protected Boolean isStrict = IS_STRICT_EDEFAULT;

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
		return OCLASPackage.Literals.PROFILE_APPLICATION;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 6, oldAppliedProfile, appliedProfile));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, 6, oldAppliedProfile, newAppliedProfile);
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
				msgs = ((InternalEObject)appliedProfile).eInverseRemove(this, 16, Profile.class, msgs);
			if (newAppliedProfile != null)
				msgs = ((InternalEObject)newAppliedProfile).eInverseAdd(this, 16, Profile.class, msgs);
			msgs = basicSetAppliedProfile(newAppliedProfile, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 6, newAppliedProfile, newAppliedProfile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getIsStrict()
	{
		return isStrict;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsStrict(Boolean newIsStrict)
	{
		Boolean oldIsStrict = isStrict;
		isStrict = newIsStrict;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 7, oldIsStrict, isStrict));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.eclipse.ocl.ocl.Package getOwningPackage()
	{
		if (eContainerFeatureID() != (8)) return null;
		return (org.eclipse.ocl.ocl.Package)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningPackage(org.eclipse.ocl.ocl.Package newOwningPackage, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningPackage, 8, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningPackage(org.eclipse.ocl.ocl.Package newOwningPackage)
	{
		if (newOwningPackage != eInternalContainer() || (eContainerFeatureID() != (8) && newOwningPackage != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningPackage))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningPackage != null)
				msgs = ((InternalEObject)newOwningPackage).eInverseAdd(this, 14, org.eclipse.ocl.ocl.Package.class, msgs);
			msgs = basicSetOwningPackage(newOwningPackage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 8, newOwningPackage, newOwningPackage));
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
			case 6:
				if (appliedProfile != null)
					msgs = ((InternalEObject)appliedProfile).eInverseRemove(this, 16, Profile.class, msgs);
				return basicSetAppliedProfile((Profile)otherEnd, msgs);
			case 8:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningPackage((org.eclipse.ocl.ocl.Package)otherEnd, msgs);
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
			case 6:
				return basicSetAppliedProfile(null, msgs);
			case 8:
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
			case 8:
				return eInternalContainer().eInverseRemove(this, 14, org.eclipse.ocl.ocl.Package.class, msgs);
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
				if (resolve) return getAppliedProfile();
				return basicGetAppliedProfile();
			case 7:
				return getIsStrict();
			case 8:
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
				setAppliedProfile((Profile)newValue);
				return;
			case 7:
				setIsStrict((Boolean)newValue);
				return;
			case 8:
				setOwningPackage((org.eclipse.ocl.ocl.Package)newValue);
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
				setAppliedProfile((Profile)null);
				return;
			case 7:
				setIsStrict(IS_STRICT_EDEFAULT);
				return;
			case 8:
				setOwningPackage((org.eclipse.ocl.ocl.Package)null);
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
				return appliedProfile != null;
			case 7:
				return IS_STRICT_EDEFAULT == null ? isStrict != null : !IS_STRICT_EDEFAULT.equals(isStrict);
			case 8:
				return getOwningPackage() != null;
		}
		return eDynamicIsSet(featureID);
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
		result.append(" (isStrict: "); //$NON-NLS-1$
		result.append(isStrict);
		result.append(')');
		return result.toString();
	}


} //ProfileApplicationImpl
