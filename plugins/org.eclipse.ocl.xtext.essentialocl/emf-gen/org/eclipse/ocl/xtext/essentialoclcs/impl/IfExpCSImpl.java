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
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;
import org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage;
import org.eclipse.ocl.xtext.essentialoclcs.ExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.IfExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.IfThenExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.util.EssentialOCLCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>If Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.IfExpCSImpl#isIsImplicit <em>Is Implicit</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.IfExpCSImpl#getOwnedCondition <em>Owned Condition</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.IfExpCSImpl#getOwnedElseExpression <em>Owned Else Expression</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.IfExpCSImpl#getOwnedIfThenExpressions <em>Owned If Then Expressions</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.IfExpCSImpl#getOwnedThenExpression <em>Owned Then Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IfExpCSImpl
		extends ExpCSImpl
		implements IfExpCS {

	/**
	 * The default value of the '{@link #isIsImplicit() <em>Is Implicit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsImplicit()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_IMPLICIT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsImplicit() <em>Is Implicit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsImplicit()
	 * @generated
	 * @ordered
	 */
	protected boolean isImplicit = IS_IMPLICIT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOwnedCondition() <em>Owned Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedCondition()
	 * @generated
	 * @ordered
	 */
	protected ExpCS ownedCondition;

	/**
	 * The cached value of the '{@link #getOwnedElseExpression() <em>Owned Else Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedElseExpression()
	 * @generated
	 * @ordered
	 */
	protected ExpCS ownedElseExpression;

	/**
	 * The cached value of the '{@link #getOwnedIfThenExpressions() <em>Owned If Then Expressions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedIfThenExpressions()
	 * @generated
	 * @ordered
	 */
	protected EList<IfThenExpCS> ownedIfThenExpressions;

	/**
	 * The cached value of the '{@link #getOwnedThenExpression() <em>Owned Then Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedThenExpression()
	 * @generated
	 * @ordered
	 */
	protected ExpCS ownedThenExpression;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IfExpCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EssentialOCLCSPackage.Literals.IF_EXP_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExpCS getOwnedCondition() {
		return ownedCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedCondition(ExpCS newOwnedCondition, NotificationChain msgs)
	{
		ExpCS oldOwnedCondition = ownedCondition;
		ownedCondition = newOwnedCondition;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.IF_EXP_CS__OWNED_CONDITION, oldOwnedCondition, newOwnedCondition);
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
	public void setOwnedCondition(ExpCS newOwnedCondition) {
		if (newOwnedCondition != ownedCondition)
		{
			NotificationChain msgs = null;
			if (ownedCondition != null)
				msgs = ((InternalEObject)ownedCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.IF_EXP_CS__OWNED_CONDITION, null, msgs);
			if (newOwnedCondition != null)
				msgs = ((InternalEObject)newOwnedCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.IF_EXP_CS__OWNED_CONDITION, null, msgs);
			msgs = basicSetOwnedCondition(newOwnedCondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.IF_EXP_CS__OWNED_CONDITION, newOwnedCondition, newOwnedCondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExpCS getOwnedThenExpression() {
		return ownedThenExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedThenExpression(ExpCS newOwnedThenExpression, NotificationChain msgs)
	{
		ExpCS oldOwnedThenExpression = ownedThenExpression;
		ownedThenExpression = newOwnedThenExpression;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.IF_EXP_CS__OWNED_THEN_EXPRESSION, oldOwnedThenExpression, newOwnedThenExpression);
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
	public void setOwnedThenExpression(ExpCS newOwnedThenExpression) {
		if (newOwnedThenExpression != ownedThenExpression)
		{
			NotificationChain msgs = null;
			if (ownedThenExpression != null)
				msgs = ((InternalEObject)ownedThenExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.IF_EXP_CS__OWNED_THEN_EXPRESSION, null, msgs);
			if (newOwnedThenExpression != null)
				msgs = ((InternalEObject)newOwnedThenExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.IF_EXP_CS__OWNED_THEN_EXPRESSION, null, msgs);
			msgs = basicSetOwnedThenExpression(newOwnedThenExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.IF_EXP_CS__OWNED_THEN_EXPRESSION, newOwnedThenExpression, newOwnedThenExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<IfThenExpCS> getOwnedIfThenExpressions()
	{
		if (ownedIfThenExpressions == null)
		{
			ownedIfThenExpressions = new EObjectContainmentEList<IfThenExpCS>(IfThenExpCS.class, this, EssentialOCLCSPackage.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS);
		}
		return ownedIfThenExpressions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExpCS getOwnedElseExpression() {
		return ownedElseExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedElseExpression(ExpCS newOwnedElseExpression, NotificationChain msgs)
	{
		ExpCS oldOwnedElseExpression = ownedElseExpression;
		ownedElseExpression = newOwnedElseExpression;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.IF_EXP_CS__OWNED_ELSE_EXPRESSION, oldOwnedElseExpression, newOwnedElseExpression);
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
	public void setOwnedElseExpression(ExpCS newOwnedElseExpression)
	{
		if (newOwnedElseExpression != ownedElseExpression)
		{
			NotificationChain msgs = null;
			if (ownedElseExpression != null)
				msgs = ((InternalEObject)ownedElseExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.IF_EXP_CS__OWNED_ELSE_EXPRESSION, null, msgs);
			if (newOwnedElseExpression != null)
				msgs = ((InternalEObject)newOwnedElseExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.IF_EXP_CS__OWNED_ELSE_EXPRESSION, null, msgs);
			msgs = basicSetOwnedElseExpression(newOwnedElseExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.IF_EXP_CS__OWNED_ELSE_EXPRESSION, newOwnedElseExpression, newOwnedElseExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsImplicit()
	{
		return isImplicit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsImplicit(boolean newIsImplicit)
	{
		boolean oldIsImplicit = isImplicit;
		isImplicit = newIsImplicit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.IF_EXP_CS__IS_IMPLICIT, oldIsImplicit, isImplicit));
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
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID)
		{
			case EssentialOCLCSPackage.IF_EXP_CS__OWNED_CONDITION:
				return basicSetOwnedCondition(null, msgs);
			case EssentialOCLCSPackage.IF_EXP_CS__OWNED_ELSE_EXPRESSION:
				return basicSetOwnedElseExpression(null, msgs);
			case EssentialOCLCSPackage.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS:
				return ((InternalEList<?>)getOwnedIfThenExpressions()).basicRemove(otherEnd, msgs);
			case EssentialOCLCSPackage.IF_EXP_CS__OWNED_THEN_EXPRESSION:
				return basicSetOwnedThenExpression(null, msgs);
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
			case EssentialOCLCSPackage.IF_EXP_CS__IS_IMPLICIT:
				return isIsImplicit();
			case EssentialOCLCSPackage.IF_EXP_CS__OWNED_CONDITION:
				return getOwnedCondition();
			case EssentialOCLCSPackage.IF_EXP_CS__OWNED_ELSE_EXPRESSION:
				return getOwnedElseExpression();
			case EssentialOCLCSPackage.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS:
				return getOwnedIfThenExpressions();
			case EssentialOCLCSPackage.IF_EXP_CS__OWNED_THEN_EXPRESSION:
				return getOwnedThenExpression();
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
	public void eSet(int featureID, Object newValue) {
		switch (featureID)
		{
			case EssentialOCLCSPackage.IF_EXP_CS__IS_IMPLICIT:
				setIsImplicit((Boolean)newValue);
				return;
			case EssentialOCLCSPackage.IF_EXP_CS__OWNED_CONDITION:
				setOwnedCondition((ExpCS)newValue);
				return;
			case EssentialOCLCSPackage.IF_EXP_CS__OWNED_ELSE_EXPRESSION:
				setOwnedElseExpression((ExpCS)newValue);
				return;
			case EssentialOCLCSPackage.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS:
				getOwnedIfThenExpressions().clear();
				getOwnedIfThenExpressions().addAll((Collection<? extends IfThenExpCS>)newValue);
				return;
			case EssentialOCLCSPackage.IF_EXP_CS__OWNED_THEN_EXPRESSION:
				setOwnedThenExpression((ExpCS)newValue);
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
			case EssentialOCLCSPackage.IF_EXP_CS__IS_IMPLICIT:
				setIsImplicit(IS_IMPLICIT_EDEFAULT);
				return;
			case EssentialOCLCSPackage.IF_EXP_CS__OWNED_CONDITION:
				setOwnedCondition((ExpCS)null);
				return;
			case EssentialOCLCSPackage.IF_EXP_CS__OWNED_ELSE_EXPRESSION:
				setOwnedElseExpression((ExpCS)null);
				return;
			case EssentialOCLCSPackage.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS:
				getOwnedIfThenExpressions().clear();
				return;
			case EssentialOCLCSPackage.IF_EXP_CS__OWNED_THEN_EXPRESSION:
				setOwnedThenExpression((ExpCS)null);
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
			case EssentialOCLCSPackage.IF_EXP_CS__IS_IMPLICIT:
				return isImplicit != IS_IMPLICIT_EDEFAULT;
			case EssentialOCLCSPackage.IF_EXP_CS__OWNED_CONDITION:
				return ownedCondition != null;
			case EssentialOCLCSPackage.IF_EXP_CS__OWNED_ELSE_EXPRESSION:
				return ownedElseExpression != null;
			case EssentialOCLCSPackage.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS:
				return ownedIfThenExpressions != null && !ownedIfThenExpressions.isEmpty();
			case EssentialOCLCSPackage.IF_EXP_CS__OWNED_THEN_EXPRESSION:
				return ownedThenExpression != null;
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
		return (R) ((EssentialOCLCSVisitor<?>)visitor).visitIfExpCS(this);
	}
} //IfExpCSImpl
