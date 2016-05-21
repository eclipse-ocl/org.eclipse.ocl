/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.basecs.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.TypedRefCS;
import org.eclipse.ocl.xtext.basecs.WildcardTypeRefCS;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Wildcard Type Ref CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.WildcardTypeRefCSImpl#getOwnedExtends <em>Owned Extends</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.WildcardTypeRefCSImpl#getOwnedSuper <em>Owned Super</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WildcardTypeRefCSImpl extends TypeRefCSImpl implements WildcardTypeRefCS {
	/**
	 * The cached value of the '{@link #getOwnedExtends() <em>Owned Extends</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedExtends()
	 * @generated
	 * @ordered
	 */
	protected TypedRefCS ownedExtends;

	/**
	 * The cached value of the '{@link #getOwnedSuper() <em>Owned Super</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSuper()
	 * @generated
	 * @ordered
	 */
	protected TypedRefCS ownedSuper;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WildcardTypeRefCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TypedRefCS getOwnedExtends() {
		return ownedExtends;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedExtends(TypedRefCS newOwnedExtends, NotificationChain msgs)
	{
		TypedRefCS oldOwnedExtends = ownedExtends;
		ownedExtends = newOwnedExtends;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BaseCSPackage.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, oldOwnedExtends, newOwnedExtends);
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
	public void setOwnedExtends(TypedRefCS newOwnedExtends) {
		if (newOwnedExtends != ownedExtends)
		{
			NotificationChain msgs = null;
			if (ownedExtends != null)
				msgs = ((InternalEObject)ownedExtends).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BaseCSPackage.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, null, msgs);
			if (newOwnedExtends != null)
				msgs = ((InternalEObject)newOwnedExtends).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BaseCSPackage.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, null, msgs);
			msgs = basicSetOwnedExtends(newOwnedExtends, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, newOwnedExtends, newOwnedExtends));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TypedRefCS getOwnedSuper() {
		return ownedSuper;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedSuper(TypedRefCS newOwnedSuper, NotificationChain msgs)
	{
		TypedRefCS oldOwnedSuper = ownedSuper;
		ownedSuper = newOwnedSuper;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BaseCSPackage.WILDCARD_TYPE_REF_CS__OWNED_SUPER, oldOwnedSuper, newOwnedSuper);
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
	public void setOwnedSuper(TypedRefCS newOwnedSuper) {
		if (newOwnedSuper != ownedSuper)
		{
			NotificationChain msgs = null;
			if (ownedSuper != null)
				msgs = ((InternalEObject)ownedSuper).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BaseCSPackage.WILDCARD_TYPE_REF_CS__OWNED_SUPER, null, msgs);
			if (newOwnedSuper != null)
				msgs = ((InternalEObject)newOwnedSuper).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BaseCSPackage.WILDCARD_TYPE_REF_CS__OWNED_SUPER, null, msgs);
			msgs = basicSetOwnedSuper(newOwnedSuper, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.WILDCARD_TYPE_REF_CS__OWNED_SUPER, newOwnedSuper, newOwnedSuper));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID)
		{
			case BaseCSPackage.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS:
				return basicSetOwnedExtends(null, msgs);
			case BaseCSPackage.WILDCARD_TYPE_REF_CS__OWNED_SUPER:
				return basicSetOwnedSuper(null, msgs);
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
			case BaseCSPackage.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS:
				return getOwnedExtends();
			case BaseCSPackage.WILDCARD_TYPE_REF_CS__OWNED_SUPER:
				return getOwnedSuper();
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
			case BaseCSPackage.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS:
				setOwnedExtends((TypedRefCS)newValue);
				return;
			case BaseCSPackage.WILDCARD_TYPE_REF_CS__OWNED_SUPER:
				setOwnedSuper((TypedRefCS)newValue);
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
			case BaseCSPackage.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS:
				setOwnedExtends((TypedRefCS)null);
				return;
			case BaseCSPackage.WILDCARD_TYPE_REF_CS__OWNED_SUPER:
				setOwnedSuper((TypedRefCS)null);
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
			case BaseCSPackage.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS:
				return ownedExtends != null;
			case BaseCSPackage.WILDCARD_TYPE_REF_CS__OWNED_SUPER:
				return ownedSuper != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull BaseCSVisitor<R> visitor) {
		return visitor.visitWildcardTypeRefCS(this);
	}
} //WildcardTypeRefCSImpl
