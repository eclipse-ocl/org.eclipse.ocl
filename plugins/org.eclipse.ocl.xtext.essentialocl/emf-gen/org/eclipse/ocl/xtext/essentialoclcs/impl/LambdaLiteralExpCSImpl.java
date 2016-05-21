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
import org.eclipse.ocl.xtext.essentialoclcs.LambdaLiteralExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.util.EssentialOCLCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Lambda Literal Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.LambdaLiteralExpCSImpl#getOwnedExpressionCS <em>Owned Expression CS</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LambdaLiteralExpCSImpl extends LiteralExpCSImpl implements LambdaLiteralExpCS
{
	/**
	 * The cached value of the '{@link #getOwnedExpressionCS() <em>Owned Expression CS</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedExpressionCS()
	 * @generated
	 * @ordered
	 */
	protected ExpCS ownedExpressionCS;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LambdaLiteralExpCSImpl()
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
		return EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExpCS getOwnedExpressionCS()
	{
		return ownedExpressionCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedExpressionCS(ExpCS newOwnedExpressionCS, NotificationChain msgs)
	{
		ExpCS oldOwnedExpressionCS = ownedExpressionCS;
		ownedExpressionCS = newOwnedExpressionCS;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, oldOwnedExpressionCS, newOwnedExpressionCS);
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
	public void setOwnedExpressionCS(ExpCS newOwnedExpressionCS)
	{
		if (newOwnedExpressionCS != ownedExpressionCS)
		{
			NotificationChain msgs = null;
			if (ownedExpressionCS != null)
				msgs = ((InternalEObject)ownedExpressionCS).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, null, msgs);
			if (newOwnedExpressionCS != null)
				msgs = ((InternalEObject)newOwnedExpressionCS).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, null, msgs);
			msgs = basicSetOwnedExpressionCS(newOwnedExpressionCS, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, newOwnedExpressionCS, newOwnedExpressionCS));
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
			case EssentialOCLCSPackage.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS:
				return basicSetOwnedExpressionCS(null, msgs);
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
			case EssentialOCLCSPackage.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS:
				return getOwnedExpressionCS();
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
			case EssentialOCLCSPackage.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS:
				setOwnedExpressionCS((ExpCS)newValue);
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
			case EssentialOCLCSPackage.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS:
				setOwnedExpressionCS((ExpCS)null);
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
			case EssentialOCLCSPackage.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS:
				return ownedExpressionCS != null;
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
		return (R) ((EssentialOCLCSVisitor<?>)visitor).visitLambdaLiteralExpCS(this);
	}

} //LambdaLiteralExpCSImpl
