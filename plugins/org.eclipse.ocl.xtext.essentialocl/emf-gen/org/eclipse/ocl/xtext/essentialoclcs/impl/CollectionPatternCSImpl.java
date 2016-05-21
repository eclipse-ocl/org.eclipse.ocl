/*******************************************************************************
 * Copyright (c) 2014, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.essentialoclcs.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.basecs.impl.TypedRefCSImpl;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;
import org.eclipse.ocl.xtext.essentialoclcs.CollectionPatternCS;
import org.eclipse.ocl.xtext.essentialoclcs.CollectionTypeCS;
import org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage;
import org.eclipse.ocl.xtext.essentialoclcs.ExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.PatternExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.util.EssentialOCLCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Collection Pattern CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.CollectionPatternCSImpl#getOwnedParts <em>Owned Parts</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.CollectionPatternCSImpl#getOwnedPatternGuard <em>Owned Pattern Guard</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.CollectionPatternCSImpl#getOwnedType <em>Owned Type</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.CollectionPatternCSImpl#getRestVariableName <em>Rest Variable Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CollectionPatternCSImpl extends TypedRefCSImpl implements CollectionPatternCS
{
	/**
	 * The cached value of the '{@link #getOwnedParts() <em>Owned Parts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedParts()
	 * @generated
	 * @ordered
	 */
	protected EList<PatternExpCS> ownedParts;

	/**
	 * The cached value of the '{@link #getOwnedPatternGuard() <em>Owned Pattern Guard</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedPatternGuard()
	 * @generated
	 * @ordered
	 */
	protected ExpCS ownedPatternGuard;

	/**
	 * The cached value of the '{@link #getOwnedType() <em>Owned Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedType()
	 * @generated
	 * @ordered
	 */
	protected CollectionTypeCS ownedType;

	/**
	 * The default value of the '{@link #getRestVariableName() <em>Rest Variable Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRestVariableName()
	 * @generated
	 * @ordered
	 */
	protected static final String REST_VARIABLE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRestVariableName() <em>Rest Variable Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRestVariableName()
	 * @generated
	 * @ordered
	 */
	protected String restVariableName = REST_VARIABLE_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CollectionPatternCSImpl()
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
		return EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CollectionTypeCS getOwnedType()
	{
		return ownedType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedType(CollectionTypeCS newOwnedType, NotificationChain msgs)
	{
		CollectionTypeCS oldOwnedType = ownedType;
		ownedType = newOwnedType;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.COLLECTION_PATTERN_CS__OWNED_TYPE, oldOwnedType, newOwnedType);
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
	public void setOwnedType(CollectionTypeCS newOwnedType)
	{
		if (newOwnedType != ownedType)
		{
			NotificationChain msgs = null;
			if (ownedType != null)
				msgs = ((InternalEObject)ownedType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.COLLECTION_PATTERN_CS__OWNED_TYPE, null, msgs);
			if (newOwnedType != null)
				msgs = ((InternalEObject)newOwnedType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.COLLECTION_PATTERN_CS__OWNED_TYPE, null, msgs);
			msgs = basicSetOwnedType(newOwnedType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.COLLECTION_PATTERN_CS__OWNED_TYPE, newOwnedType, newOwnedType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<PatternExpCS> getOwnedParts()
	{
		if (ownedParts == null)
		{
			ownedParts = new EObjectContainmentEList<PatternExpCS>(PatternExpCS.class, this, EssentialOCLCSPackage.COLLECTION_PATTERN_CS__OWNED_PARTS);
		}
		return ownedParts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getRestVariableName()
	{
		return restVariableName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRestVariableName(String newRestVariableName)
	{
		String oldRestVariableName = restVariableName;
		restVariableName = newRestVariableName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME, oldRestVariableName, restVariableName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExpCS getOwnedPatternGuard()
	{
		return ownedPatternGuard;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedPatternGuard(ExpCS newOwnedPatternGuard, NotificationChain msgs)
	{
		ExpCS oldOwnedPatternGuard = ownedPatternGuard;
		ownedPatternGuard = newOwnedPatternGuard;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.COLLECTION_PATTERN_CS__OWNED_PATTERN_GUARD, oldOwnedPatternGuard, newOwnedPatternGuard);
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
	public void setOwnedPatternGuard(ExpCS newOwnedPatternGuard)
	{
		if (newOwnedPatternGuard != ownedPatternGuard)
		{
			NotificationChain msgs = null;
			if (ownedPatternGuard != null)
				msgs = ((InternalEObject)ownedPatternGuard).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.COLLECTION_PATTERN_CS__OWNED_PATTERN_GUARD, null, msgs);
			if (newOwnedPatternGuard != null)
				msgs = ((InternalEObject)newOwnedPatternGuard).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.COLLECTION_PATTERN_CS__OWNED_PATTERN_GUARD, null, msgs);
			msgs = basicSetOwnedPatternGuard(newOwnedPatternGuard, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.COLLECTION_PATTERN_CS__OWNED_PATTERN_GUARD, newOwnedPatternGuard, newOwnedPatternGuard));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString()
	{
		return super.toString();
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
			case EssentialOCLCSPackage.COLLECTION_PATTERN_CS__OWNED_PARTS:
				return ((InternalEList<?>)getOwnedParts()).basicRemove(otherEnd, msgs);
			case EssentialOCLCSPackage.COLLECTION_PATTERN_CS__OWNED_PATTERN_GUARD:
				return basicSetOwnedPatternGuard(null, msgs);
			case EssentialOCLCSPackage.COLLECTION_PATTERN_CS__OWNED_TYPE:
				return basicSetOwnedType(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
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
			case EssentialOCLCSPackage.COLLECTION_PATTERN_CS__OWNED_PARTS:
				return getOwnedParts();
			case EssentialOCLCSPackage.COLLECTION_PATTERN_CS__OWNED_PATTERN_GUARD:
				return getOwnedPatternGuard();
			case EssentialOCLCSPackage.COLLECTION_PATTERN_CS__OWNED_TYPE:
				return getOwnedType();
			case EssentialOCLCSPackage.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME:
				return getRestVariableName();
		}
		return super.eGet(featureID, resolve, coreType);
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
			case EssentialOCLCSPackage.COLLECTION_PATTERN_CS__OWNED_PARTS:
				getOwnedParts().clear();
				getOwnedParts().addAll((Collection<? extends PatternExpCS>)newValue);
				return;
			case EssentialOCLCSPackage.COLLECTION_PATTERN_CS__OWNED_PATTERN_GUARD:
				setOwnedPatternGuard((ExpCS)newValue);
				return;
			case EssentialOCLCSPackage.COLLECTION_PATTERN_CS__OWNED_TYPE:
				setOwnedType((CollectionTypeCS)newValue);
				return;
			case EssentialOCLCSPackage.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME:
				setRestVariableName((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
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
			case EssentialOCLCSPackage.COLLECTION_PATTERN_CS__OWNED_PARTS:
				getOwnedParts().clear();
				return;
			case EssentialOCLCSPackage.COLLECTION_PATTERN_CS__OWNED_PATTERN_GUARD:
				setOwnedPatternGuard((ExpCS)null);
				return;
			case EssentialOCLCSPackage.COLLECTION_PATTERN_CS__OWNED_TYPE:
				setOwnedType((CollectionTypeCS)null);
				return;
			case EssentialOCLCSPackage.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME:
				setRestVariableName(REST_VARIABLE_NAME_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
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
			case EssentialOCLCSPackage.COLLECTION_PATTERN_CS__OWNED_PARTS:
				return ownedParts != null && !ownedParts.isEmpty();
			case EssentialOCLCSPackage.COLLECTION_PATTERN_CS__OWNED_PATTERN_GUARD:
				return ownedPatternGuard != null;
			case EssentialOCLCSPackage.COLLECTION_PATTERN_CS__OWNED_TYPE:
				return ownedType != null;
			case EssentialOCLCSPackage.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME:
				return REST_VARIABLE_NAME_EDEFAULT == null ? restVariableName != null : !REST_VARIABLE_NAME_EDEFAULT.equals(restVariableName);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <R> R accept(@NonNull BaseCSVisitor<R> visitor) {
		return (R) ((EssentialOCLCSVisitor<?>)visitor).visitCollectionPatternCS(this);
	}

} //CollectionPatternCSImpl
