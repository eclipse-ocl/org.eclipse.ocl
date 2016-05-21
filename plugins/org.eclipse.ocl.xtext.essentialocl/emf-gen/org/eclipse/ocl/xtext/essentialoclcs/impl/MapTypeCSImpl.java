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
package org.eclipse.ocl.xtext.essentialoclcs.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.basecs.TypedRefCS;

import org.eclipse.ocl.xtext.basecs.impl.TypedRefCSImpl;

import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;

import org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage;
import org.eclipse.ocl.xtext.essentialoclcs.MapTypeCS;

import org.eclipse.ocl.xtext.essentialoclcs.util.EssentialOCLCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Map Type CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.MapTypeCSImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.MapTypeCSImpl#getOwnedKeyType <em>Owned Key Type</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.MapTypeCSImpl#getOwnedValueType <em>Owned Value Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MapTypeCSImpl extends TypedRefCSImpl implements MapTypeCS
{
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
	 * The cached value of the '{@link #getOwnedKeyType() <em>Owned Key Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedKeyType()
	 * @generated
	 * @ordered
	 */
	protected TypedRefCS ownedKeyType;

	/**
	 * The cached value of the '{@link #getOwnedValueType() <em>Owned Value Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedValueType()
	 * @generated
	 * @ordered
	 */
	protected TypedRefCS ownedValueType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MapTypeCSImpl()
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
		return EssentialOCLCSPackage.Literals.MAP_TYPE_CS;
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
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.MAP_TYPE_CS__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TypedRefCS getOwnedKeyType()
	{
		return ownedKeyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedKeyType(TypedRefCS newOwnedKeyType, NotificationChain msgs)
	{
		TypedRefCS oldOwnedKeyType = ownedKeyType;
		ownedKeyType = newOwnedKeyType;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.MAP_TYPE_CS__OWNED_KEY_TYPE, oldOwnedKeyType, newOwnedKeyType);
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
	public void setOwnedKeyType(TypedRefCS newOwnedKeyType)
	{
		if (newOwnedKeyType != ownedKeyType)
		{
			NotificationChain msgs = null;
			if (ownedKeyType != null)
				msgs = ((InternalEObject)ownedKeyType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.MAP_TYPE_CS__OWNED_KEY_TYPE, null, msgs);
			if (newOwnedKeyType != null)
				msgs = ((InternalEObject)newOwnedKeyType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.MAP_TYPE_CS__OWNED_KEY_TYPE, null, msgs);
			msgs = basicSetOwnedKeyType(newOwnedKeyType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.MAP_TYPE_CS__OWNED_KEY_TYPE, newOwnedKeyType, newOwnedKeyType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TypedRefCS getOwnedValueType()
	{
		return ownedValueType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedValueType(TypedRefCS newOwnedValueType, NotificationChain msgs)
	{
		TypedRefCS oldOwnedValueType = ownedValueType;
		ownedValueType = newOwnedValueType;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.MAP_TYPE_CS__OWNED_VALUE_TYPE, oldOwnedValueType, newOwnedValueType);
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
	public void setOwnedValueType(TypedRefCS newOwnedValueType)
	{
		if (newOwnedValueType != ownedValueType)
		{
			NotificationChain msgs = null;
			if (ownedValueType != null)
				msgs = ((InternalEObject)ownedValueType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.MAP_TYPE_CS__OWNED_VALUE_TYPE, null, msgs);
			if (newOwnedValueType != null)
				msgs = ((InternalEObject)newOwnedValueType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.MAP_TYPE_CS__OWNED_VALUE_TYPE, null, msgs);
			msgs = basicSetOwnedValueType(newOwnedValueType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.MAP_TYPE_CS__OWNED_VALUE_TYPE, newOwnedValueType, newOwnedValueType));
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
			case EssentialOCLCSPackage.MAP_TYPE_CS__OWNED_KEY_TYPE:
				return basicSetOwnedKeyType(null, msgs);
			case EssentialOCLCSPackage.MAP_TYPE_CS__OWNED_VALUE_TYPE:
				return basicSetOwnedValueType(null, msgs);
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
			case EssentialOCLCSPackage.MAP_TYPE_CS__NAME:
				return getName();
			case EssentialOCLCSPackage.MAP_TYPE_CS__OWNED_KEY_TYPE:
				return getOwnedKeyType();
			case EssentialOCLCSPackage.MAP_TYPE_CS__OWNED_VALUE_TYPE:
				return getOwnedValueType();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case EssentialOCLCSPackage.MAP_TYPE_CS__NAME:
				setName((String)newValue);
				return;
			case EssentialOCLCSPackage.MAP_TYPE_CS__OWNED_KEY_TYPE:
				setOwnedKeyType((TypedRefCS)newValue);
				return;
			case EssentialOCLCSPackage.MAP_TYPE_CS__OWNED_VALUE_TYPE:
				setOwnedValueType((TypedRefCS)newValue);
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
			case EssentialOCLCSPackage.MAP_TYPE_CS__NAME:
				setName(NAME_EDEFAULT);
				return;
			case EssentialOCLCSPackage.MAP_TYPE_CS__OWNED_KEY_TYPE:
				setOwnedKeyType((TypedRefCS)null);
				return;
			case EssentialOCLCSPackage.MAP_TYPE_CS__OWNED_VALUE_TYPE:
				setOwnedValueType((TypedRefCS)null);
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
			case EssentialOCLCSPackage.MAP_TYPE_CS__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case EssentialOCLCSPackage.MAP_TYPE_CS__OWNED_KEY_TYPE:
				return ownedKeyType != null;
			case EssentialOCLCSPackage.MAP_TYPE_CS__OWNED_VALUE_TYPE:
				return ownedValueType != null;
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
		return (R) ((EssentialOCLCSVisitor<?>)visitor).visitMapTypeCS(this);
	}

} //MapTypeCSImpl
