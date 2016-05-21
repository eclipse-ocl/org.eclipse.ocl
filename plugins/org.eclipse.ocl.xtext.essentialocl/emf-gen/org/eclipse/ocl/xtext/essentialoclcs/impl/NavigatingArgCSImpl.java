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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.basecs.TypedRefCS;
import org.eclipse.ocl.xtext.basecs.impl.ModelElementCSImpl;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;
import org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage;
import org.eclipse.ocl.xtext.essentialoclcs.ExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.NavigatingArgCS;
import org.eclipse.ocl.xtext.essentialoclcs.NavigationRole;
import org.eclipse.ocl.xtext.essentialoclcs.RoundBracketedClauseCS;
import org.eclipse.ocl.xtext.essentialoclcs.util.EssentialOCLCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Navigating Arg CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.NavigatingArgCSImpl#getOwnedInitExpression <em>Owned Init Expression</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.NavigatingArgCSImpl#getOwnedNameExpression <em>Owned Name Expression</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.NavigatingArgCSImpl#getOwnedType <em>Owned Type</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.NavigatingArgCSImpl#getOwningRoundBracketedClause <em>Owning Round Bracketed Clause</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.NavigatingArgCSImpl#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.NavigatingArgCSImpl#getRole <em>Role</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NavigatingArgCSImpl
		extends ModelElementCSImpl
		implements NavigatingArgCS {

	/**
	 * The cached value of the '{@link #getOwnedInitExpression() <em>Owned Init Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedInitExpression()
	 * @generated
	 * @ordered
	 */
	protected ExpCS ownedInitExpression;

	/**
	 * The cached value of the '{@link #getOwnedNameExpression() <em>Owned Name Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedNameExpression()
	 * @generated
	 * @ordered
	 */
	protected ExpCS ownedNameExpression;

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
	 * The default value of the '{@link #getPrefix() <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrefix()
	 * @generated
	 * @ordered
	 */
	protected static final String PREFIX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrefix() <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrefix()
	 * @generated
	 * @ordered
	 */
	protected String prefix = PREFIX_EDEFAULT;

	/**
	 * The default value of the '{@link #getRole() <em>Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRole()
	 * @generated
	 * @ordered
	 */
	protected static final NavigationRole ROLE_EDEFAULT = NavigationRole.ITERATOR;

	/**
	 * The cached value of the '{@link #getRole() <em>Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRole()
	 * @generated
	 * @ordered
	 */
	protected NavigationRole role = ROLE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NavigatingArgCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RoundBracketedClauseCS getOwningRoundBracketedClause()
	{
		if (eContainerFeatureID() != EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNING_ROUND_BRACKETED_CLAUSE) return null;
		return (RoundBracketedClauseCS)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningRoundBracketedClause(RoundBracketedClauseCS newOwningRoundBracketedClause, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningRoundBracketedClause, EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNING_ROUND_BRACKETED_CLAUSE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningRoundBracketedClause(RoundBracketedClauseCS newOwningRoundBracketedClause)
	{
		if (newOwningRoundBracketedClause != eInternalContainer() || (eContainerFeatureID() != EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNING_ROUND_BRACKETED_CLAUSE && newOwningRoundBracketedClause != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningRoundBracketedClause))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningRoundBracketedClause != null)
				msgs = ((InternalEObject)newOwningRoundBracketedClause).eInverseAdd(this, EssentialOCLCSPackage.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, RoundBracketedClauseCS.class, msgs);
			msgs = basicSetOwningRoundBracketedClause(newOwningRoundBracketedClause, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNING_ROUND_BRACKETED_CLAUSE, newOwningRoundBracketedClause, newOwningRoundBracketedClause));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NavigationRole getRole() {
		return role;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRole(NavigationRole newRole) {
		NavigationRole oldRole = role;
		role = newRole == null ? ROLE_EDEFAULT : newRole;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.NAVIGATING_ARG_CS__ROLE, oldRole, role));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPrefix() {
		return prefix;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPrefix(String newPrefix) {
		String oldPrefix = prefix;
		prefix = newPrefix;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.NAVIGATING_ARG_CS__PREFIX, oldPrefix, prefix));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExpCS getOwnedNameExpression() {
		return ownedNameExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedNameExpression(ExpCS newOwnedNameExpression, NotificationChain msgs)
	{
		ExpCS oldOwnedNameExpression = ownedNameExpression;
		ownedNameExpression = newOwnedNameExpression;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, oldOwnedNameExpression, newOwnedNameExpression);
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
	public void setOwnedNameExpression(ExpCS newOwnedNameExpression) {
		if (newOwnedNameExpression != ownedNameExpression)
		{
			NotificationChain msgs = null;
			if (ownedNameExpression != null)
				msgs = ((InternalEObject)ownedNameExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, null, msgs);
			if (newOwnedNameExpression != null)
				msgs = ((InternalEObject)newOwnedNameExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, null, msgs);
			msgs = basicSetOwnedNameExpression(newOwnedNameExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, newOwnedNameExpression, newOwnedNameExpression));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNED_TYPE, oldOwnedType, newOwnedType);
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
				msgs = ((InternalEObject)ownedType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNED_TYPE, null, msgs);
			if (newOwnedType != null)
				msgs = ((InternalEObject)newOwnedType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNED_TYPE, null, msgs);
			msgs = basicSetOwnedType(newOwnedType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNED_TYPE, newOwnedType, newOwnedType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExpCS getOwnedInitExpression() {
		return ownedInitExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedInitExpression(ExpCS newOwnedInitExpression, NotificationChain msgs)
	{
		ExpCS oldOwnedInitExpression = ownedInitExpression;
		ownedInitExpression = newOwnedInitExpression;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, oldOwnedInitExpression, newOwnedInitExpression);
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
	public void setOwnedInitExpression(ExpCS newOwnedInitExpression) {
		if (newOwnedInitExpression != ownedInitExpression)
		{
			NotificationChain msgs = null;
			if (ownedInitExpression != null)
				msgs = ((InternalEObject)ownedInitExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, null, msgs);
			if (newOwnedInitExpression != null)
				msgs = ((InternalEObject)newOwnedInitExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, null, msgs);
			msgs = basicSetOwnedInitExpression(newOwnedInitExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, newOwnedInitExpression, newOwnedInitExpression));
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNING_ROUND_BRACKETED_CLAUSE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningRoundBracketedClause((RoundBracketedClauseCS)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
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
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION:
				return basicSetOwnedInitExpression(null, msgs);
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION:
				return basicSetOwnedNameExpression(null, msgs);
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNED_TYPE:
				return basicSetOwnedType(null, msgs);
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNING_ROUND_BRACKETED_CLAUSE:
				return basicSetOwningRoundBracketedClause(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
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
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNING_ROUND_BRACKETED_CLAUSE:
				return eInternalContainer().eInverseRemove(this, EssentialOCLCSPackage.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, RoundBracketedClauseCS.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
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
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION:
				return getOwnedInitExpression();
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION:
				return getOwnedNameExpression();
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNED_TYPE:
				return getOwnedType();
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNING_ROUND_BRACKETED_CLAUSE:
				return getOwningRoundBracketedClause();
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__PREFIX:
				return getPrefix();
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__ROLE:
				return getRole();
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
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION:
				setOwnedInitExpression((ExpCS)newValue);
				return;
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION:
				setOwnedNameExpression((ExpCS)newValue);
				return;
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNED_TYPE:
				setOwnedType((TypedRefCS)newValue);
				return;
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNING_ROUND_BRACKETED_CLAUSE:
				setOwningRoundBracketedClause((RoundBracketedClauseCS)newValue);
				return;
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__PREFIX:
				setPrefix((String)newValue);
				return;
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__ROLE:
				setRole((NavigationRole)newValue);
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
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION:
				setOwnedInitExpression((ExpCS)null);
				return;
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION:
				setOwnedNameExpression((ExpCS)null);
				return;
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNED_TYPE:
				setOwnedType((TypedRefCS)null);
				return;
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNING_ROUND_BRACKETED_CLAUSE:
				setOwningRoundBracketedClause((RoundBracketedClauseCS)null);
				return;
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__PREFIX:
				setPrefix(PREFIX_EDEFAULT);
				return;
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__ROLE:
				setRole(ROLE_EDEFAULT);
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
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION:
				return ownedInitExpression != null;
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION:
				return ownedNameExpression != null;
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNED_TYPE:
				return ownedType != null;
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__OWNING_ROUND_BRACKETED_CLAUSE:
				return getOwningRoundBracketedClause() != null;
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__PREFIX:
				return PREFIX_EDEFAULT == null ? prefix != null : !PREFIX_EDEFAULT.equals(prefix);
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS__ROLE:
				return role != ROLE_EDEFAULT;
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
		return (R) ((EssentialOCLCSVisitor<?>)visitor).visitNavigatingArgCS(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void resetPivot() {
		super.resetPivot();
		setRole(null);
	}
} //NavigatingArgCSImpl
