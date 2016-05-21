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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;
import org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage;
import org.eclipse.ocl.xtext.essentialoclcs.ExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.InfixExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.util.EssentialOCLCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Binary Expression CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.InfixExpCSImpl#getArgument <em>Argument</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.InfixExpCSImpl#getOwnedLeft <em>Owned Left</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InfixExpCSImpl
		extends OperatorExpCSImpl
		implements InfixExpCS {

	/**
	 * The cached value of the '{@link #getArgument() <em>Argument</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArgument()
	 * @generated
	 * @ordered
	 */
	protected ExpCS argument;

	/**
	 * The cached value of the '{@link #getOwnedLeft() <em>Owned Left</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedLeft()
	 * @generated
	 * @ordered
	 */
	protected ExpCS ownedLeft;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InfixExpCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EssentialOCLCSPackage.Literals.INFIX_EXP_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExpCS getOwnedLeft()
	{
		return ownedLeft;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedLeft(ExpCS newOwnedLeft, NotificationChain msgs)
	{
		ExpCS oldOwnedLeft = ownedLeft;
		ownedLeft = newOwnedLeft;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.INFIX_EXP_CS__OWNED_LEFT, oldOwnedLeft, newOwnedLeft);
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
	public void setOwnedLeft(ExpCS newOwnedLeft)
	{
		if (newOwnedLeft != ownedLeft)
		{
			NotificationChain msgs = null;
			if (ownedLeft != null)
				msgs = ((InternalEObject)ownedLeft).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.INFIX_EXP_CS__OWNED_LEFT, null, msgs);
			if (newOwnedLeft != null)
				msgs = ((InternalEObject)newOwnedLeft).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.INFIX_EXP_CS__OWNED_LEFT, null, msgs);
			msgs = basicSetOwnedLeft(newOwnedLeft, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.INFIX_EXP_CS__OWNED_LEFT, newOwnedLeft, newOwnedLeft));
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
			case EssentialOCLCSPackage.INFIX_EXP_CS__OWNED_LEFT:
				return basicSetOwnedLeft(null, msgs);
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
			case EssentialOCLCSPackage.INFIX_EXP_CS__ARGUMENT:
				return getArgument();
			case EssentialOCLCSPackage.INFIX_EXP_CS__OWNED_LEFT:
				return getOwnedLeft();
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
			case EssentialOCLCSPackage.INFIX_EXP_CS__OWNED_LEFT:
				setOwnedLeft((ExpCS)newValue);
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
			case EssentialOCLCSPackage.INFIX_EXP_CS__OWNED_LEFT:
				setOwnedLeft((ExpCS)null);
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
			case EssentialOCLCSPackage.INFIX_EXP_CS__ARGUMENT:
				return argument != null;
			case EssentialOCLCSPackage.INFIX_EXP_CS__OWNED_LEFT:
				return ownedLeft != null;
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
		return (R) ((EssentialOCLCSVisitor<?>)visitor).visitInfixExpCS(this);
	}
	
	private boolean hasArgument = false;

	@Override
	public ExpCS getArgument() {
		if ((argument == null) && !hasArgument) {
			hasArgument = true;
			ExpCS localRight = getLocalRight();
			if (localRight != null) {
				argument = getExpressionForRight(localRight);
			}
		}
		return argument;
	}

	@Override
	public @Nullable ExpCS getLocalLeft() {
		if ((localLeft == null) && !hasLocalLeft) {
			hasLocalLeft = true;
			ExpCS ownedSource = getOwnedLeft();
			if (ownedSource != null) {
				localLeft = ownedSource.getLocalRightmostDescendant();
			}
		}
		return localLeft;
	}

	@Override
	public @NonNull ExpCS getLocalLeftmostDescendant() {
		ExpCS ownedSource = getOwnedLeft();
		return ownedSource != null ? ownedSource.getLocalLeftmostDescendant() : this;
	}

	@Override
	public ExpCS getSource() {
		if ((source == null) && !hasSource) {
			hasSource = true;
			ExpCS localLeft = getLocalLeft();
			if (localLeft != null) {
				source = getExpressionForLeft(localLeft);
			}
		}
		return source;
	}
	
	@Override
	public boolean isLocalRightAncestorOf(@NonNull ExpCS csExp) {	// csExp should be to the right of this for associativity resolution
		return !csExp.isLocalLeftAncestorOf(this);
	}

	@Override
	public void resetPivot() {
		super.resetPivot();
		argument = null;
		hasArgument = false;
	}
} //BinaryExpressionCSImpl
