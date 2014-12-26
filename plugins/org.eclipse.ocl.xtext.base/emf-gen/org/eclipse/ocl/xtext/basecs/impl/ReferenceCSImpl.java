/*******************************************************************************
 * Copyright (c) 2010, 2012 E.D.Willink and others.
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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.ReferenceCS;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reference CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.ReferenceCSImpl#getReferredKeys <em>Referred Keys</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.ReferenceCSImpl#getReferredOpposite <em>Referred Opposite</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.ReferenceCSImpl#isIsComposes <em>Is Composes</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.ReferenceCSImpl#isIsResolve <em>Is Resolve</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReferenceCSImpl extends StructuralFeatureCSImpl implements ReferenceCS
{
	/**
	 * The cached value of the '{@link #getReferredKeys() <em>Referred Keys</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredKeys()
	 * @generated
	 * @ordered
	 */
	protected EList<Property> referredKeys;

	/**
	 * The cached value of the '{@link #getReferredOpposite() <em>Referred Opposite</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredOpposite()
	 * @generated
	 * @ordered
	 */
	protected Property referredOpposite;

	/**
	 * The default value of the '{@link #isIsComposes() <em>Is Composes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsComposes()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_COMPOSES_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsComposes() <em>Is Composes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsComposes()
	 * @generated
	 * @ordered
	 */
	protected boolean isComposes = IS_COMPOSES_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsResolve() <em>Is Resolve</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsResolve()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_RESOLVE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsResolve() <em>Is Resolve</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsResolve()
	 * @generated
	 * @ordered
	 */
	protected boolean isResolve = IS_RESOLVE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReferenceCSImpl()
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
		return BaseCSPackage.Literals.REFERENCE_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Property getReferredOpposite()
	{
		if (referredOpposite != null && referredOpposite.eIsProxy())
		{
			InternalEObject oldReferredOpposite = (InternalEObject)referredOpposite;
			referredOpposite = (Property)eResolveProxy(oldReferredOpposite);
			if (referredOpposite != oldReferredOpposite)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BaseCSPackage.REFERENCE_CS__REFERRED_OPPOSITE, oldReferredOpposite, referredOpposite));
			}
		}
		return referredOpposite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property basicGetReferredOpposite()
	{
		return referredOpposite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReferredOpposite(Property newReferredOpposite)
	{
		Property oldReferredOpposite = referredOpposite;
		referredOpposite = newReferredOpposite;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.REFERENCE_CS__REFERRED_OPPOSITE, oldReferredOpposite, referredOpposite));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsComposes()
	{
		return isComposes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsComposes(boolean newIsComposes)
	{
		boolean oldIsComposes = isComposes;
		isComposes = newIsComposes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.REFERENCE_CS__IS_COMPOSES, oldIsComposes, isComposes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsResolve()
	{
		return isResolve;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsResolve(boolean newIsResolve)
	{
		boolean oldIsResolve = isResolve;
		isResolve = newIsResolve;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.REFERENCE_CS__IS_RESOLVE, oldIsResolve, isResolve));
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
	public EList<Property> getReferredKeys()
	{
		if (referredKeys == null)
		{
			referredKeys = new EObjectResolvingEList<Property>(Property.class, this, BaseCSPackage.REFERENCE_CS__REFERRED_KEYS);
		}
		return referredKeys;
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
			case BaseCSPackage.REFERENCE_CS__REFERRED_KEYS:
				return getReferredKeys();
			case BaseCSPackage.REFERENCE_CS__REFERRED_OPPOSITE:
				if (resolve) return getReferredOpposite();
				return basicGetReferredOpposite();
			case BaseCSPackage.REFERENCE_CS__IS_COMPOSES:
				return isIsComposes();
			case BaseCSPackage.REFERENCE_CS__IS_RESOLVE:
				return isIsResolve();
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
			case BaseCSPackage.REFERENCE_CS__REFERRED_KEYS:
				getReferredKeys().clear();
				getReferredKeys().addAll((Collection<? extends Property>)newValue);
				return;
			case BaseCSPackage.REFERENCE_CS__REFERRED_OPPOSITE:
				setReferredOpposite((Property)newValue);
				return;
			case BaseCSPackage.REFERENCE_CS__IS_COMPOSES:
				setIsComposes((Boolean)newValue);
				return;
			case BaseCSPackage.REFERENCE_CS__IS_RESOLVE:
				setIsResolve((Boolean)newValue);
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
			case BaseCSPackage.REFERENCE_CS__REFERRED_KEYS:
				getReferredKeys().clear();
				return;
			case BaseCSPackage.REFERENCE_CS__REFERRED_OPPOSITE:
				setReferredOpposite((Property)null);
				return;
			case BaseCSPackage.REFERENCE_CS__IS_COMPOSES:
				setIsComposes(IS_COMPOSES_EDEFAULT);
				return;
			case BaseCSPackage.REFERENCE_CS__IS_RESOLVE:
				setIsResolve(IS_RESOLVE_EDEFAULT);
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
			case BaseCSPackage.REFERENCE_CS__REFERRED_KEYS:
				return referredKeys != null && !referredKeys.isEmpty();
			case BaseCSPackage.REFERENCE_CS__REFERRED_OPPOSITE:
				return referredOpposite != null;
			case BaseCSPackage.REFERENCE_CS__IS_COMPOSES:
				return isComposes != IS_COMPOSES_EDEFAULT;
			case BaseCSPackage.REFERENCE_CS__IS_RESOLVE:
				return isResolve != IS_RESOLVE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable <R> R accept(@NonNull BaseCSVisitor<R> visitor) {
		return visitor.visitReferenceCS(this);
	}
} //ReferenceCSImpl
