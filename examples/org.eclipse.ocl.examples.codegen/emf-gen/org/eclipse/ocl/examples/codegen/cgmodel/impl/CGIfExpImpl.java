/*******************************************************************************
 * Copyright (c) 2013, 2016 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.cgmodel.impl;

import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIfExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.util.CGModelVisitor;
import org.eclipse.ocl.examples.codegen.cse.AbstractPlace;
import org.eclipse.ocl.examples.codegen.cse.IfPlaces;
import org.eclipse.ocl.examples.codegen.utilities.EquivalenceUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CG If Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGIfExpImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGIfExpImpl#getThenExpression <em>Then Expression</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGIfExpImpl#getElseExpression <em>Else Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CGIfExpImpl extends CGValuedElementImpl implements CGIfExp {
	/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected CGValuedElement condition;

	/**
	 * The cached value of the '{@link #getThenExpression() <em>Then Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThenExpression()
	 * @generated
	 * @ordered
	 */
	protected CGValuedElement thenExpression;

	/**
	 * The cached value of the '{@link #getElseExpression() <em>Else Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElseExpression()
	 * @generated
	 * @ordered
	 */
	protected CGValuedElement elseExpression;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CGIfExpImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CGModelPackage.Literals.CG_IF_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CGValuedElement getCondition() {
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCondition(CGValuedElement newCondition, NotificationChain msgs) {
		CGValuedElement oldCondition = condition;
		condition = newCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CGModelPackage.CG_IF_EXP__CONDITION, oldCondition, newCondition);
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
	public void setCondition(CGValuedElement newCondition) {
		if (newCondition != condition) {
			NotificationChain msgs = null;
			if (condition != null)
				msgs = ((InternalEObject)condition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CGModelPackage.CG_IF_EXP__CONDITION, null, msgs);
			if (newCondition != null)
				msgs = ((InternalEObject)newCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CGModelPackage.CG_IF_EXP__CONDITION, null, msgs);
			msgs = basicSetCondition(newCondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGModelPackage.CG_IF_EXP__CONDITION, newCondition, newCondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CGValuedElement getThenExpression() {
		return thenExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetThenExpression(CGValuedElement newThenExpression, NotificationChain msgs) {
		CGValuedElement oldThenExpression = thenExpression;
		thenExpression = newThenExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CGModelPackage.CG_IF_EXP__THEN_EXPRESSION, oldThenExpression, newThenExpression);
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
	public void setThenExpression(CGValuedElement newThenExpression) {
		if (newThenExpression != thenExpression) {
			NotificationChain msgs = null;
			if (thenExpression != null)
				msgs = ((InternalEObject)thenExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CGModelPackage.CG_IF_EXP__THEN_EXPRESSION, null, msgs);
			if (newThenExpression != null)
				msgs = ((InternalEObject)newThenExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CGModelPackage.CG_IF_EXP__THEN_EXPRESSION, null, msgs);
			msgs = basicSetThenExpression(newThenExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGModelPackage.CG_IF_EXP__THEN_EXPRESSION, newThenExpression, newThenExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CGValuedElement getElseExpression() {
		return elseExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetElseExpression(CGValuedElement newElseExpression, NotificationChain msgs) {
		CGValuedElement oldElseExpression = elseExpression;
		elseExpression = newElseExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CGModelPackage.CG_IF_EXP__ELSE_EXPRESSION, oldElseExpression, newElseExpression);
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
	public void setElseExpression(CGValuedElement newElseExpression) {
		if (newElseExpression != elseExpression) {
			NotificationChain msgs = null;
			if (elseExpression != null)
				msgs = ((InternalEObject)elseExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CGModelPackage.CG_IF_EXP__ELSE_EXPRESSION, null, msgs);
			if (newElseExpression != null)
				msgs = ((InternalEObject)newElseExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CGModelPackage.CG_IF_EXP__ELSE_EXPRESSION, null, msgs);
			msgs = basicSetElseExpression(newElseExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGModelPackage.CG_IF_EXP__ELSE_EXPRESSION, newElseExpression, newElseExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CGModelPackage.CG_IF_EXP__CONDITION:
				return basicSetCondition(null, msgs);
			case CGModelPackage.CG_IF_EXP__THEN_EXPRESSION:
				return basicSetThenExpression(null, msgs);
			case CGModelPackage.CG_IF_EXP__ELSE_EXPRESSION:
				return basicSetElseExpression(null, msgs);
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
		switch (featureID) {
			case CGModelPackage.CG_IF_EXP__CONDITION:
				return getCondition();
			case CGModelPackage.CG_IF_EXP__THEN_EXPRESSION:
				return getThenExpression();
			case CGModelPackage.CG_IF_EXP__ELSE_EXPRESSION:
				return getElseExpression();
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
		switch (featureID) {
			case CGModelPackage.CG_IF_EXP__CONDITION:
				setCondition((CGValuedElement)newValue);
				return;
			case CGModelPackage.CG_IF_EXP__THEN_EXPRESSION:
				setThenExpression((CGValuedElement)newValue);
				return;
			case CGModelPackage.CG_IF_EXP__ELSE_EXPRESSION:
				setElseExpression((CGValuedElement)newValue);
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
		switch (featureID) {
			case CGModelPackage.CG_IF_EXP__CONDITION:
				setCondition((CGValuedElement)null);
				return;
			case CGModelPackage.CG_IF_EXP__THEN_EXPRESSION:
				setThenExpression((CGValuedElement)null);
				return;
			case CGModelPackage.CG_IF_EXP__ELSE_EXPRESSION:
				setElseExpression((CGValuedElement)null);
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
		switch (featureID) {
			case CGModelPackage.CG_IF_EXP__CONDITION:
				return condition != null;
			case CGModelPackage.CG_IF_EXP__THEN_EXPRESSION:
				return thenExpression != null;
			case CGModelPackage.CG_IF_EXP__ELSE_EXPRESSION:
				return elseExpression != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull CGModelVisitor<R> visitor) {
		return visitor.visitCGIfExp(this);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable CGInvalid getInvalidValue() {
		CGInvalid invalidValue = condition.getInvalidValue();
		if (invalidValue == null) {
			if (condition.isTrue()) {
				invalidValue = thenExpression.getInvalidValue();
			}
			else if (condition.isFalse()) {
				invalidValue = elseExpression.getInvalidValue();
			}
			else {
				invalidValue = elseExpression.getInvalidValue();
				if (invalidValue != null) {				// If both then and else invalid propagate then
					invalidValue = thenExpression.getInvalidValue();
				}
			}
		}
		return invalidValue;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable AbstractPlace getPlace(@NonNull Map<@Nullable CGElement, @NonNull AbstractPlace> element2place) {
		return IfPlaces.createIfPlaces(element2place, this);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isBoxed() {
		return thenExpression.isBoxed() && elseExpression.isBoxed();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isEcore() {
		return thenExpression.isEcore() && elseExpression.isEcore();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable Boolean isEquivalentToInternal(@NonNull CGValuedElement thatValue) {
		return (getClass() == thatValue.getClass()) ? EquivalenceUtil.isEquivalent(this, (CGIfExp)thatValue) : null;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isGlobal() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isNonInvalid() {
		if (condition.isTrue()) {
			return thenExpression.isNonInvalid();
		}
		else if (condition.isFalse()) {
			return elseExpression.isNonInvalid();
		}
		else {
			return thenExpression.isNonInvalid() && elseExpression.isNonInvalid();
		}
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isNonNull() {
		return condition.isTrue() ? thenExpression.isNonNull() : condition.isFalse() ? elseExpression.isNonNull() : thenExpression.isNonNull() && elseExpression.isNonNull();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isNull() {
		return condition.isTrue() ? thenExpression.isNull() : condition.isFalse() ? elseExpression.isNull() : thenExpression.isNull() && elseExpression.isNull();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isSettable() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isUnboxed() {
		return thenExpression.isUnboxed() && elseExpression.isUnboxed();
	}

} //CGIfExpImpl
