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
package org.eclipse.ocl.xtext.essentialoclcs.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.xtext.basecs.PathNameCS;
import org.eclipse.ocl.xtext.basecs.impl.TypedRefCSImpl;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;
import org.eclipse.ocl.xtext.essentialoclcs.CurlyBracketedClauseCS;
import org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage;
import org.eclipse.ocl.xtext.essentialoclcs.ExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.TypeNameExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.util.EssentialOCLCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Name Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.TypeNameExpCSImpl#getElement <em>Element</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.TypeNameExpCSImpl#getOwnedCurlyBracketedClause <em>Owned Curly Bracketed Clause</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.TypeNameExpCSImpl#getOwnedPathName <em>Owned Path Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.TypeNameExpCSImpl#getOwnedPatternGuard <em>Owned Pattern Guard</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TypeNameExpCSImpl
		extends TypedRefCSImpl
		implements TypeNameExpCS {

	/**
	 * The cached value of the '{@link #getOwnedCurlyBracketedClause() <em>Owned Curly Bracketed Clause</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedCurlyBracketedClause()
	 * @generated
	 * @ordered
	 */
	protected CurlyBracketedClauseCS ownedCurlyBracketedClause;
	/**
	 * The cached value of the '{@link #getOwnedPathName() <em>Owned Path Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedPathName()
	 * @generated
	 * @ordered
	 */
	protected PathNameCS ownedPathName;
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeNameExpCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PathNameCS getOwnedPathName() {
		return ownedPathName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedPathName(PathNameCS newOwnedPathName, NotificationChain msgs)
	{
		PathNameCS oldOwnedPathName = ownedPathName;
		ownedPathName = newOwnedPathName;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, oldOwnedPathName, newOwnedPathName);
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
	public void setOwnedPathName(PathNameCS newOwnedPathName) {
		if (newOwnedPathName != ownedPathName)
		{
			NotificationChain msgs = null;
			if (ownedPathName != null)
				msgs = ((InternalEObject)ownedPathName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, null, msgs);
			if (newOwnedPathName != null)
				msgs = ((InternalEObject)newOwnedPathName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, null, msgs);
			msgs = basicSetOwnedPathName(newOwnedPathName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, newOwnedPathName, newOwnedPathName));
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
			case EssentialOCLCSPackage.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE:
				return basicSetOwnedCurlyBracketedClause(null, msgs);
			case EssentialOCLCSPackage.TYPE_NAME_EXP_CS__OWNED_PATH_NAME:
				return basicSetOwnedPathName(null, msgs);
			case EssentialOCLCSPackage.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD:
				return basicSetOwnedPatternGuard(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public NamedElement getNamedElement() {
		return getElement();
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
			case EssentialOCLCSPackage.TYPE_NAME_EXP_CS__ELEMENT:
				return getElement();
			case EssentialOCLCSPackage.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE:
				return getOwnedCurlyBracketedClause();
			case EssentialOCLCSPackage.TYPE_NAME_EXP_CS__OWNED_PATH_NAME:
				return getOwnedPathName();
			case EssentialOCLCSPackage.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD:
				return getOwnedPatternGuard();
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
			case EssentialOCLCSPackage.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE:
				setOwnedCurlyBracketedClause((CurlyBracketedClauseCS)newValue);
				return;
			case EssentialOCLCSPackage.TYPE_NAME_EXP_CS__OWNED_PATH_NAME:
				setOwnedPathName((PathNameCS)newValue);
				return;
			case EssentialOCLCSPackage.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD:
				setOwnedPatternGuard((ExpCS)newValue);
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
			case EssentialOCLCSPackage.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE:
				setOwnedCurlyBracketedClause((CurlyBracketedClauseCS)null);
				return;
			case EssentialOCLCSPackage.TYPE_NAME_EXP_CS__OWNED_PATH_NAME:
				setOwnedPathName((PathNameCS)null);
				return;
			case EssentialOCLCSPackage.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD:
				setOwnedPatternGuard((ExpCS)null);
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
			case EssentialOCLCSPackage.TYPE_NAME_EXP_CS__ELEMENT:
				return getElement() != null;
			case EssentialOCLCSPackage.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE:
				return ownedCurlyBracketedClause != null;
			case EssentialOCLCSPackage.TYPE_NAME_EXP_CS__OWNED_PATH_NAME:
				return ownedPathName != null;
			case EssentialOCLCSPackage.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD:
				return ownedPatternGuard != null;
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
		return (R) ((EssentialOCLCSVisitor<?>)visitor).visitTypeNameExpCS(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Type getElement() {
		if (ownedPathName == null) {
			return null;
		}
		return (Type) ownedPathName.getReferredElement();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CurlyBracketedClauseCS getOwnedCurlyBracketedClause()
	{
		return ownedCurlyBracketedClause;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedCurlyBracketedClause(CurlyBracketedClauseCS newOwnedCurlyBracketedClause, NotificationChain msgs)
	{
		CurlyBracketedClauseCS oldOwnedCurlyBracketedClause = ownedCurlyBracketedClause;
		ownedCurlyBracketedClause = newOwnedCurlyBracketedClause;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, oldOwnedCurlyBracketedClause, newOwnedCurlyBracketedClause);
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
	public void setOwnedCurlyBracketedClause(CurlyBracketedClauseCS newOwnedCurlyBracketedClause)
	{
		if (newOwnedCurlyBracketedClause != ownedCurlyBracketedClause)
		{
			NotificationChain msgs = null;
			if (ownedCurlyBracketedClause != null)
				msgs = ((InternalEObject)ownedCurlyBracketedClause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, null, msgs);
			if (newOwnedCurlyBracketedClause != null)
				msgs = ((InternalEObject)newOwnedCurlyBracketedClause).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, null, msgs);
			msgs = basicSetOwnedCurlyBracketedClause(newOwnedCurlyBracketedClause, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, newOwnedCurlyBracketedClause, newOwnedCurlyBracketedClause));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, oldOwnedPatternGuard, newOwnedPatternGuard);
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
				msgs = ((InternalEObject)ownedPatternGuard).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, null, msgs);
			if (newOwnedPatternGuard != null)
				msgs = ((InternalEObject)newOwnedPatternGuard).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, null, msgs);
			msgs = basicSetOwnedPatternGuard(newOwnedPatternGuard, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, newOwnedPatternGuard, newOwnedPatternGuard));
	}
} //TypeNameExpCSImpl
