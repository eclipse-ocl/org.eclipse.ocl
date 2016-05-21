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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;
import org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage;
import org.eclipse.ocl.xtext.essentialoclcs.ExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.IfThenExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.util.EssentialOCLCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>If Then Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.IfThenExpCSImpl#getOwnedCondition <em>Owned Condition</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.IfThenExpCSImpl#getOwnedThenExpression <em>Owned Then Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IfThenExpCSImpl extends ExpCSImpl implements IfThenExpCS
{
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
	protected IfThenExpCSImpl()
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
		return EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExpCS getOwnedCondition()
	{
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.IF_THEN_EXP_CS__OWNED_CONDITION, oldOwnedCondition, newOwnedCondition);
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
	public void setOwnedCondition(ExpCS newOwnedCondition)
	{
		if (newOwnedCondition != ownedCondition)
		{
			NotificationChain msgs = null;
			if (ownedCondition != null)
				msgs = ((InternalEObject)ownedCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.IF_THEN_EXP_CS__OWNED_CONDITION, null, msgs);
			if (newOwnedCondition != null)
				msgs = ((InternalEObject)newOwnedCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.IF_THEN_EXP_CS__OWNED_CONDITION, null, msgs);
			msgs = basicSetOwnedCondition(newOwnedCondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.IF_THEN_EXP_CS__OWNED_CONDITION, newOwnedCondition, newOwnedCondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExpCS getOwnedThenExpression()
	{
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, oldOwnedThenExpression, newOwnedThenExpression);
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
	public void setOwnedThenExpression(ExpCS newOwnedThenExpression)
	{
		if (newOwnedThenExpression != ownedThenExpression)
		{
			NotificationChain msgs = null;
			if (ownedThenExpression != null)
				msgs = ((InternalEObject)ownedThenExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, null, msgs);
			if (newOwnedThenExpression != null)
				msgs = ((InternalEObject)newOwnedThenExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, null, msgs);
			msgs = basicSetOwnedThenExpression(newOwnedThenExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, newOwnedThenExpression, newOwnedThenExpression));
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
			case EssentialOCLCSPackage.IF_THEN_EXP_CS__OWNED_CONDITION:
				return basicSetOwnedCondition(null, msgs);
			case EssentialOCLCSPackage.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION:
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
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch (featureID)
		{
			case EssentialOCLCSPackage.IF_THEN_EXP_CS__OWNED_CONDITION:
				return getOwnedCondition();
			case EssentialOCLCSPackage.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION:
				return getOwnedThenExpression();
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
			case EssentialOCLCSPackage.IF_THEN_EXP_CS__OWNED_CONDITION:
				setOwnedCondition((ExpCS)newValue);
				return;
			case EssentialOCLCSPackage.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION:
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
	public void eUnset(int featureID)
	{
		switch (featureID)
		{
			case EssentialOCLCSPackage.IF_THEN_EXP_CS__OWNED_CONDITION:
				setOwnedCondition((ExpCS)null);
				return;
			case EssentialOCLCSPackage.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION:
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
	public boolean eIsSet(int featureID)
	{
		switch (featureID)
		{
			case EssentialOCLCSPackage.IF_THEN_EXP_CS__OWNED_CONDITION:
				return ownedCondition != null;
			case EssentialOCLCSPackage.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION:
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
		return (R) ((EssentialOCLCSVisitor<?>)visitor).visitIfThenExpCS(this);
	}

} //IfThenExpCSImpl
