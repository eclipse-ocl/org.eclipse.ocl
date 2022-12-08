/*******************************************************************************
 * Copyright (c) 2013, 2019 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.cgmodel.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPropertyAssignment;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.util.CGModelVisitor;
import org.eclipse.ocl.examples.codegen.utilities.EquivalenceUtil;
import org.eclipse.ocl.pivot.Property;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CG Property Assignment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGPropertyAssignmentImpl#getReferredProperty <em>Referred Property</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGPropertyAssignmentImpl#getOwnedInitValue <em>Owned Init Value</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGPropertyAssignmentImpl#getOwnedSlotValue <em>Owned Slot Value</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGPropertyAssignmentImpl#getAsProperty <em>As Property</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CGPropertyAssignmentImpl extends CGValuedElementImpl implements CGPropertyAssignment {
	/**
	 * The number of structural features of the '<em>CG Property Assignment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CG_PROPERTY_ASSIGNMENT_FEATURE_COUNT = CGValuedElementImpl.CG_VALUED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The cached value of the '{@link #getReferredProperty() <em>Referred Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredProperty()
	 * @generated
	 * @ordered
	 */
	protected CGProperty referredProperty;

	/**
	 * The cached value of the '{@link #getOwnedInitValue() <em>Owned Init Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedInitValue()
	 * @generated
	 * @ordered
	 */
	protected CGValuedElement ownedInitValue;

	/**
	 * The cached value of the '{@link #getOwnedSlotValue() <em>Owned Slot Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSlotValue()
	 * @generated
	 * @ordered
	 */
	protected CGValuedElement ownedSlotValue;

	/**
	 * The default value of the '{@link #getAsProperty() <em>As Property</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAsProperty()
	 * @generated
	 * @ordered
	 */
	protected static final Property AS_PROPERTY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAsProperty() <em>As Property</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAsProperty()
	 * @generated
	 * @ordered
	 */
	protected Property asProperty = AS_PROPERTY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CGPropertyAssignmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CGModelPackage.Literals.CG_PROPERTY_ASSIGNMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CGProperty getReferredProperty() {
		return referredProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReferredProperty(CGProperty newReferredProperty) {
		CGProperty oldReferredProperty = referredProperty;
		referredProperty = newReferredProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 6, oldReferredProperty, referredProperty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Property getAsProperty() {
		return asProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAsProperty(Property newAsProperty) {
		Property oldAsProperty = asProperty;
		asProperty = newAsProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 9, oldAsProperty, asProperty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CGValuedElement getOwnedInitValue() {
		return ownedInitValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedInitValue(CGValuedElement newOwnedInitValue, NotificationChain msgs) {
		CGValuedElement oldOwnedInitValue = ownedInitValue;
		ownedInitValue = newOwnedInitValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, 7, oldOwnedInitValue, newOwnedInitValue);
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
	public void setOwnedInitValue(CGValuedElement newOwnedInitValue) {
		if (newOwnedInitValue != ownedInitValue) {
			NotificationChain msgs = null;
			if (ownedInitValue != null)
				msgs = ((InternalEObject)ownedInitValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (7), null, msgs);
			if (newOwnedInitValue != null)
				msgs = ((InternalEObject)newOwnedInitValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (7), null, msgs);
			msgs = basicSetOwnedInitValue(newOwnedInitValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 7, newOwnedInitValue, newOwnedInitValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CGValuedElement getOwnedSlotValue() {
		return ownedSlotValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedSlotValue(CGValuedElement newOwnedSlotValue, NotificationChain msgs) {
		CGValuedElement oldOwnedSlotValue = ownedSlotValue;
		ownedSlotValue = newOwnedSlotValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, 8, oldOwnedSlotValue, newOwnedSlotValue);
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
	public void setOwnedSlotValue(CGValuedElement newOwnedSlotValue) {
		if (newOwnedSlotValue != ownedSlotValue) {
			NotificationChain msgs = null;
			if (ownedSlotValue != null)
				msgs = ((InternalEObject)ownedSlotValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (8), null, msgs);
			if (newOwnedSlotValue != null)
				msgs = ((InternalEObject)newOwnedSlotValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (8), null, msgs);
			msgs = basicSetOwnedSlotValue(newOwnedSlotValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 8, newOwnedSlotValue, newOwnedSlotValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case 7:
				return basicSetOwnedInitValue(null, msgs);
			case 8:
				return basicSetOwnedSlotValue(null, msgs);
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
			case 6:
				return getReferredProperty();
			case 7:
				return getOwnedInitValue();
			case 8:
				return getOwnedSlotValue();
			case 9:
				return getAsProperty();
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
			case 6:
				setReferredProperty((CGProperty)newValue);
				return;
			case 7:
				setOwnedInitValue((CGValuedElement)newValue);
				return;
			case 8:
				setOwnedSlotValue((CGValuedElement)newValue);
				return;
			case 9:
				setAsProperty((Property)newValue);
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
			case 6:
				setReferredProperty((CGProperty)null);
				return;
			case 7:
				setOwnedInitValue((CGValuedElement)null);
				return;
			case 8:
				setOwnedSlotValue((CGValuedElement)null);
				return;
			case 9:
				setAsProperty(AS_PROPERTY_EDEFAULT);
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
			case 6:
				return referredProperty != null;
			case 7:
				return ownedInitValue != null;
			case 8:
				return ownedSlotValue != null;
			case 9:
				return AS_PROPERTY_EDEFAULT == null ? asProperty != null : !AS_PROPERTY_EDEFAULT.equals(asProperty);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <R> R accept(@NonNull CGModelVisitor<R> visitor) {
		return visitor.visitCGPropertyAssignment(this);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable EClassifier getEcoreClassifier() {
		return ownedInitValue != null ? ownedInitValue.getEcoreClassifier() : null;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isBoxed() {
		return (ownedInitValue != null) && ownedInitValue.isBoxed();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isEcore() {
		return (ownedInitValue != null) && ownedInitValue.isEcore();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable Boolean isEquivalentToInternal(@NonNull CGValuedElement thatValue) {
		return (getClass() == thatValue.getClass()) ? EquivalenceUtil.isEquivalent(this, (CGPropertyAssignment)thatValue) : null;
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
	public boolean isUnboxed() {
		return (ownedInitValue != null) && ownedInitValue.isUnboxed();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String toString() {
		return super.toString();
	}
} //CGPropertyAssignmentImpl
