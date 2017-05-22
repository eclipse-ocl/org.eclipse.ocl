/*******************************************************************************
 * Copyright (c) 2010, 2017 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.essentialoclcs.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.basecs.MultiplicityCS;
import org.eclipse.ocl.xtext.basecs.TypedRefCS;
import org.eclipse.ocl.xtext.basecs.impl.TypedRefCSImpl;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;
import org.eclipse.ocl.xtext.essentialoclcs.CollectionTypeCS;
import org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage;
import org.eclipse.ocl.xtext.essentialoclcs.util.EssentialOCLCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Collection Type CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.CollectionTypeCSImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.CollectionTypeCSImpl#getOwnedCollectionMultiplicity <em>Owned Collection Multiplicity</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.CollectionTypeCSImpl#getOwnedType <em>Owned Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CollectionTypeCSImpl
extends TypedRefCSImpl
implements CollectionTypeCS {

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
	 * The cached value of the '{@link #getOwnedCollectionMultiplicity() <em>Owned Collection Multiplicity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedCollectionMultiplicity()
	 * @generated
	 * @ordered
	 */
	protected MultiplicityCS ownedCollectionMultiplicity;

	/**
	 * The cached value of the '{@link #getOwnedType() <em>Owned Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedType()
	 * @generated
	 * @ordered
	 */
	protected TypedRefCS ownedType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CollectionTypeCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.COLLECTION_TYPE_CS__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MultiplicityCS getOwnedCollectionMultiplicity()
	{
		return ownedCollectionMultiplicity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedCollectionMultiplicity(MultiplicityCS newOwnedCollectionMultiplicity, NotificationChain msgs)
	{
		MultiplicityCS oldOwnedCollectionMultiplicity = ownedCollectionMultiplicity;
		ownedCollectionMultiplicity = newOwnedCollectionMultiplicity;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, oldOwnedCollectionMultiplicity, newOwnedCollectionMultiplicity);
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
	public void setOwnedCollectionMultiplicity(MultiplicityCS newOwnedCollectionMultiplicity)
	{
		if (newOwnedCollectionMultiplicity != ownedCollectionMultiplicity)
		{
			NotificationChain msgs = null;
			if (ownedCollectionMultiplicity != null)
				msgs = ((InternalEObject)ownedCollectionMultiplicity).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, null, msgs);
			if (newOwnedCollectionMultiplicity != null)
				msgs = ((InternalEObject)newOwnedCollectionMultiplicity).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, null, msgs);
			msgs = basicSetOwnedCollectionMultiplicity(newOwnedCollectionMultiplicity, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, newOwnedCollectionMultiplicity, newOwnedCollectionMultiplicity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TypedRefCS getOwnedType() {
		return ownedType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedType(TypedRefCS newOwnedType,
			NotificationChain msgs) {
		TypedRefCS oldOwnedType = ownedType;
		ownedType = newOwnedType;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.COLLECTION_TYPE_CS__OWNED_TYPE, oldOwnedType, newOwnedType);
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
	public void setOwnedType(TypedRefCS newOwnedType) {
		if (newOwnedType != ownedType)
		{
			NotificationChain msgs = null;
			if (ownedType != null)
				msgs = ((InternalEObject)ownedType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.COLLECTION_TYPE_CS__OWNED_TYPE, null, msgs);
			if (newOwnedType != null)
				msgs = ((InternalEObject)newOwnedType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.COLLECTION_TYPE_CS__OWNED_TYPE, null, msgs);
			msgs = basicSetOwnedType(newOwnedType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.COLLECTION_TYPE_CS__OWNED_TYPE, newOwnedType, newOwnedType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return super.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID)
		{
			case EssentialOCLCSPackage.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY:
				return basicSetOwnedCollectionMultiplicity(null, msgs);
			case EssentialOCLCSPackage.COLLECTION_TYPE_CS__OWNED_TYPE:
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID)
		{
			case EssentialOCLCSPackage.COLLECTION_TYPE_CS__NAME:
				return getName();
			case EssentialOCLCSPackage.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY:
				return getOwnedCollectionMultiplicity();
			case EssentialOCLCSPackage.COLLECTION_TYPE_CS__OWNED_TYPE:
				return getOwnedType();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID)
		{
			case EssentialOCLCSPackage.COLLECTION_TYPE_CS__NAME:
				setName((String)newValue);
				return;
			case EssentialOCLCSPackage.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY:
				setOwnedCollectionMultiplicity((MultiplicityCS)newValue);
				return;
			case EssentialOCLCSPackage.COLLECTION_TYPE_CS__OWNED_TYPE:
				setOwnedType((TypedRefCS)newValue);
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
	public void eUnset(int featureID) {
		switch (featureID)
		{
			case EssentialOCLCSPackage.COLLECTION_TYPE_CS__NAME:
				setName(NAME_EDEFAULT);
				return;
			case EssentialOCLCSPackage.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY:
				setOwnedCollectionMultiplicity((MultiplicityCS)null);
				return;
			case EssentialOCLCSPackage.COLLECTION_TYPE_CS__OWNED_TYPE:
				setOwnedType((TypedRefCS)null);
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
	public boolean eIsSet(int featureID) {
		switch (featureID)
		{
			case EssentialOCLCSPackage.COLLECTION_TYPE_CS__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case EssentialOCLCSPackage.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY:
				return ownedCollectionMultiplicity != null;
			case EssentialOCLCSPackage.COLLECTION_TYPE_CS__OWNED_TYPE:
				return ownedType != null;
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
		return (R) ((EssentialOCLCSVisitor<?>)visitor).visitCollectionTypeCS(this);
	}
} //CollectionTypeCSImpl
