/*******************************************************************************
 * Copyright (c) 2012, 2016 Willink Transformations and others.
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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.xtext.base.utilities.ElementUtil;
import org.eclipse.ocl.xtext.basecs.impl.ModelElementCSImpl;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;
import org.eclipse.ocl.xtext.essentialoclcs.CurlyBracketedClauseCS;
import org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage;
import org.eclipse.ocl.xtext.essentialoclcs.ExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.ShadowPartCS;
import org.eclipse.ocl.xtext.essentialoclcs.util.EssentialOCLCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constructor Part CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.ShadowPartCSImpl#getOwnedInitExpression <em>Owned Init Expression</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.ShadowPartCSImpl#getOwningCurlyBracketClause <em>Owning Curly Bracket Clause</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.ShadowPartCSImpl#getReferredProperty <em>Referred Property</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ShadowPartCSImpl
		extends ModelElementCSImpl
		implements ShadowPartCS {

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
	 * The cached value of the '{@link #getReferredProperty() <em>Referred Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredProperty()
	 * @generated
	 * @ordered
	 */
	protected Property referredProperty;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ShadowPartCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EssentialOCLCSPackage.Literals.SHADOW_PART_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CurlyBracketedClauseCS getOwningCurlyBracketClause()
	{
		if (eContainerFeatureID() != EssentialOCLCSPackage.SHADOW_PART_CS__OWNING_CURLY_BRACKET_CLAUSE) return null;
		return (CurlyBracketedClauseCS)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningCurlyBracketClause(CurlyBracketedClauseCS newOwningCurlyBracketClause, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningCurlyBracketClause, EssentialOCLCSPackage.SHADOW_PART_CS__OWNING_CURLY_BRACKET_CLAUSE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningCurlyBracketClause(CurlyBracketedClauseCS newOwningCurlyBracketClause)
	{
		if (newOwningCurlyBracketClause != eInternalContainer() || (eContainerFeatureID() != EssentialOCLCSPackage.SHADOW_PART_CS__OWNING_CURLY_BRACKET_CLAUSE && newOwningCurlyBracketClause != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningCurlyBracketClause))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningCurlyBracketClause != null)
				msgs = ((InternalEObject)newOwningCurlyBracketClause).eInverseAdd(this, EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, CurlyBracketedClauseCS.class, msgs);
			msgs = basicSetOwningCurlyBracketClause(newOwningCurlyBracketClause, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.SHADOW_PART_CS__OWNING_CURLY_BRACKET_CLAUSE, newOwningCurlyBracketClause, newOwningCurlyBracketClause));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Property getReferredProperty() {
		if (referredProperty != null && referredProperty.eIsProxy())
		{
			InternalEObject oldReferredProperty = (InternalEObject)referredProperty;
			referredProperty = (Property)eResolveProxy(oldReferredProperty);
			if (referredProperty != oldReferredProperty)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EssentialOCLCSPackage.SHADOW_PART_CS__REFERRED_PROPERTY, oldReferredProperty, referredProperty));
			}
		}
		return referredProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property basicGetReferredProperty()
	{
		return referredProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReferredProperty(Property newReferredProperty) {
		Property oldReferredProperty = referredProperty;
		referredProperty = newReferredProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.SHADOW_PART_CS__REFERRED_PROPERTY, oldReferredProperty, referredProperty));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, oldOwnedInitExpression, newOwnedInitExpression);
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
				msgs = ((InternalEObject)ownedInitExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, null, msgs);
			if (newOwnedInitExpression != null)
				msgs = ((InternalEObject)newOwnedInitExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, null, msgs);
			msgs = basicSetOwnedInitExpression(newOwnedInitExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, newOwnedInitExpression, newOwnedInitExpression));
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
			case EssentialOCLCSPackage.SHADOW_PART_CS__OWNING_CURLY_BRACKET_CLAUSE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningCurlyBracketClause((CurlyBracketedClauseCS)otherEnd, msgs);
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
			case EssentialOCLCSPackage.SHADOW_PART_CS__OWNED_INIT_EXPRESSION:
				return basicSetOwnedInitExpression(null, msgs);
			case EssentialOCLCSPackage.SHADOW_PART_CS__OWNING_CURLY_BRACKET_CLAUSE:
				return basicSetOwningCurlyBracketClause(null, msgs);
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
			case EssentialOCLCSPackage.SHADOW_PART_CS__OWNING_CURLY_BRACKET_CLAUSE:
				return eInternalContainer().eInverseRemove(this, EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, CurlyBracketedClauseCS.class, msgs);
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
			case EssentialOCLCSPackage.SHADOW_PART_CS__OWNED_INIT_EXPRESSION:
				return getOwnedInitExpression();
			case EssentialOCLCSPackage.SHADOW_PART_CS__OWNING_CURLY_BRACKET_CLAUSE:
				return getOwningCurlyBracketClause();
			case EssentialOCLCSPackage.SHADOW_PART_CS__REFERRED_PROPERTY:
				if (resolve) return getReferredProperty();
				return basicGetReferredProperty();
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
			case EssentialOCLCSPackage.SHADOW_PART_CS__OWNED_INIT_EXPRESSION:
				setOwnedInitExpression((ExpCS)newValue);
				return;
			case EssentialOCLCSPackage.SHADOW_PART_CS__OWNING_CURLY_BRACKET_CLAUSE:
				setOwningCurlyBracketClause((CurlyBracketedClauseCS)newValue);
				return;
			case EssentialOCLCSPackage.SHADOW_PART_CS__REFERRED_PROPERTY:
				setReferredProperty((Property)newValue);
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
			case EssentialOCLCSPackage.SHADOW_PART_CS__OWNED_INIT_EXPRESSION:
				setOwnedInitExpression((ExpCS)null);
				return;
			case EssentialOCLCSPackage.SHADOW_PART_CS__OWNING_CURLY_BRACKET_CLAUSE:
				setOwningCurlyBracketClause((CurlyBracketedClauseCS)null);
				return;
			case EssentialOCLCSPackage.SHADOW_PART_CS__REFERRED_PROPERTY:
				setReferredProperty((Property)null);
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
			case EssentialOCLCSPackage.SHADOW_PART_CS__OWNED_INIT_EXPRESSION:
				return ownedInitExpression != null;
			case EssentialOCLCSPackage.SHADOW_PART_CS__OWNING_CURLY_BRACKET_CLAUSE:
				return getOwningCurlyBracketClause() != null;
			case EssentialOCLCSPackage.SHADOW_PART_CS__REFERRED_PROPERTY:
				return referredProperty != null;
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
		return (R) ((EssentialOCLCSVisitor<?>)visitor).visitShadowPartCS(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getName() {
		if ((basicGetReferredProperty() == null) || basicGetReferredProperty().eIsProxy()) {
			return ElementUtil.getText(this, EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY);
		}
		else {
			return getReferredProperty().getName();
		}
	}
} //ConstructorPartCSImpl
