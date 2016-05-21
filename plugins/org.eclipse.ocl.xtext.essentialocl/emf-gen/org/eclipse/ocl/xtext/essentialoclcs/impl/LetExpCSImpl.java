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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;
import org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage;
import org.eclipse.ocl.xtext.essentialoclcs.ExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.LetExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.LetVariableCS;
import org.eclipse.ocl.xtext.essentialoclcs.util.EssentialOCLCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Let Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.LetExpCSImpl#isIsImplicit <em>Is Implicit</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.LetExpCSImpl#getOwnedInExpression <em>Owned In Expression</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.LetExpCSImpl#getOwnedVariables <em>Owned Variables</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LetExpCSImpl
		extends ExpCSImpl
		implements LetExpCS {

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
	 * The cached value of the '{@link #getOwnedInExpression() <em>Owned In Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedInExpression()
	 * @generated
	 * @ordered
	 */
	protected ExpCS ownedInExpression;

	/**
	 * The cached value of the '{@link #getOwnedVariables() <em>Owned Variables</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedVariables()
	 * @generated
	 * @ordered
	 */
	protected EList<LetVariableCS> ownedVariables;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LetExpCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EssentialOCLCSPackage.Literals.LET_EXP_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<LetVariableCS> getOwnedVariables() {
		if (ownedVariables == null)
		{
			ownedVariables = new EObjectContainmentWithInverseEList<LetVariableCS>(LetVariableCS.class, this, EssentialOCLCSPackage.LET_EXP_CS__OWNED_VARIABLES, EssentialOCLCSPackage.LET_VARIABLE_CS__OWNING_LET_EXPRESSION);
		}
		return ownedVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExpCS getOwnedInExpression() {
		return ownedInExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedInExpression(ExpCS newOwnedInExpression, NotificationChain msgs)
	{
		ExpCS oldOwnedInExpression = ownedInExpression;
		ownedInExpression = newOwnedInExpression;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.LET_EXP_CS__OWNED_IN_EXPRESSION, oldOwnedInExpression, newOwnedInExpression);
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
	public void setOwnedInExpression(ExpCS newOwnedInExpression) {
		if (newOwnedInExpression != ownedInExpression)
		{
			NotificationChain msgs = null;
			if (ownedInExpression != null)
				msgs = ((InternalEObject)ownedInExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.LET_EXP_CS__OWNED_IN_EXPRESSION, null, msgs);
			if (newOwnedInExpression != null)
				msgs = ((InternalEObject)newOwnedInExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.LET_EXP_CS__OWNED_IN_EXPRESSION, null, msgs);
			msgs = basicSetOwnedInExpression(newOwnedInExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.LET_EXP_CS__OWNED_IN_EXPRESSION, newOwnedInExpression, newOwnedInExpression));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.LET_EXP_CS__IS_IMPLICIT, oldIsImplicit, isImplicit));
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
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID)
		{
			case EssentialOCLCSPackage.LET_EXP_CS__OWNED_VARIABLES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedVariables()).basicAdd(otherEnd, msgs);
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
			case EssentialOCLCSPackage.LET_EXP_CS__OWNED_IN_EXPRESSION:
				return basicSetOwnedInExpression(null, msgs);
			case EssentialOCLCSPackage.LET_EXP_CS__OWNED_VARIABLES:
				return ((InternalEList<?>)getOwnedVariables()).basicRemove(otherEnd, msgs);
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
			case EssentialOCLCSPackage.LET_EXP_CS__IS_IMPLICIT:
				return isIsImplicit();
			case EssentialOCLCSPackage.LET_EXP_CS__OWNED_IN_EXPRESSION:
				return getOwnedInExpression();
			case EssentialOCLCSPackage.LET_EXP_CS__OWNED_VARIABLES:
				return getOwnedVariables();
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
			case EssentialOCLCSPackage.LET_EXP_CS__IS_IMPLICIT:
				setIsImplicit((Boolean)newValue);
				return;
			case EssentialOCLCSPackage.LET_EXP_CS__OWNED_IN_EXPRESSION:
				setOwnedInExpression((ExpCS)newValue);
				return;
			case EssentialOCLCSPackage.LET_EXP_CS__OWNED_VARIABLES:
				getOwnedVariables().clear();
				getOwnedVariables().addAll((Collection<? extends LetVariableCS>)newValue);
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
			case EssentialOCLCSPackage.LET_EXP_CS__IS_IMPLICIT:
				setIsImplicit(IS_IMPLICIT_EDEFAULT);
				return;
			case EssentialOCLCSPackage.LET_EXP_CS__OWNED_IN_EXPRESSION:
				setOwnedInExpression((ExpCS)null);
				return;
			case EssentialOCLCSPackage.LET_EXP_CS__OWNED_VARIABLES:
				getOwnedVariables().clear();
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
			case EssentialOCLCSPackage.LET_EXP_CS__IS_IMPLICIT:
				return isImplicit != IS_IMPLICIT_EDEFAULT;
			case EssentialOCLCSPackage.LET_EXP_CS__OWNED_IN_EXPRESSION:
				return ownedInExpression != null;
			case EssentialOCLCSPackage.LET_EXP_CS__OWNED_VARIABLES:
				return ownedVariables != null && !ownedVariables.isEmpty();
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
		return (R) ((EssentialOCLCSVisitor<?>)visitor).visitLetExpCS(this);
	}
} //LetExpCSImpl
