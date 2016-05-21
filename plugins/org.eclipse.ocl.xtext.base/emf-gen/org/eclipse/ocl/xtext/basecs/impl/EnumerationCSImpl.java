/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.basecs.impl;

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
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.EnumerationCS;
import org.eclipse.ocl.xtext.basecs.EnumerationLiteralCS;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enum CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.EnumerationCSImpl#isIsSerializable <em>Is Serializable</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.EnumerationCSImpl#getOwnedLiterals <em>Owned Literals</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EnumerationCSImpl extends ClassCSImpl implements EnumerationCS
{
	/**
	 * The default value of the '{@link #isIsSerializable() <em>Is Serializable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsSerializable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_SERIALIZABLE_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isIsSerializable() <em>Is Serializable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsSerializable()
	 * @generated
	 * @ordered
	 */
	protected boolean isSerializable = IS_SERIALIZABLE_EDEFAULT;
	/**
	 * The cached value of the '{@link #getOwnedLiterals() <em>Owned Literals</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedLiterals()
	 * @generated
	 * @ordered
	 */
	protected EList<EnumerationLiteralCS> ownedLiterals;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnumerationCSImpl()
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
		return BaseCSPackage.Literals.ENUMERATION_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsSerializable()
	{
		return isSerializable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsSerializable(boolean newIsSerializable)
	{
		boolean oldIsSerializable = isSerializable;
		isSerializable = newIsSerializable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.ENUMERATION_CS__IS_SERIALIZABLE, oldIsSerializable, isSerializable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<EnumerationLiteralCS> getOwnedLiterals()
	{
		if (ownedLiterals == null)
		{
			ownedLiterals = new EObjectContainmentEList<EnumerationLiteralCS>(EnumerationLiteralCS.class, this, BaseCSPackage.ENUMERATION_CS__OWNED_LITERALS);
		}
		return ownedLiterals;
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
			case BaseCSPackage.ENUMERATION_CS__OWNED_LITERALS:
				return ((InternalEList<?>)getOwnedLiterals()).basicRemove(otherEnd, msgs);
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
			case BaseCSPackage.ENUMERATION_CS__IS_SERIALIZABLE:
				return isIsSerializable();
			case BaseCSPackage.ENUMERATION_CS__OWNED_LITERALS:
				return getOwnedLiterals();
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
			case BaseCSPackage.ENUMERATION_CS__IS_SERIALIZABLE:
				setIsSerializable((Boolean)newValue);
				return;
			case BaseCSPackage.ENUMERATION_CS__OWNED_LITERALS:
				getOwnedLiterals().clear();
				getOwnedLiterals().addAll((Collection<? extends EnumerationLiteralCS>)newValue);
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
			case BaseCSPackage.ENUMERATION_CS__IS_SERIALIZABLE:
				setIsSerializable(IS_SERIALIZABLE_EDEFAULT);
				return;
			case BaseCSPackage.ENUMERATION_CS__OWNED_LITERALS:
				getOwnedLiterals().clear();
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
			case BaseCSPackage.ENUMERATION_CS__IS_SERIALIZABLE:
				return isSerializable != IS_SERIALIZABLE_EDEFAULT;
			case BaseCSPackage.ENUMERATION_CS__OWNED_LITERALS:
				return ownedLiterals != null && !ownedLiterals.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull BaseCSVisitor<R> visitor) {
		return visitor.visitEnumerationCS(this);
	}
} //EnumCSImpl
