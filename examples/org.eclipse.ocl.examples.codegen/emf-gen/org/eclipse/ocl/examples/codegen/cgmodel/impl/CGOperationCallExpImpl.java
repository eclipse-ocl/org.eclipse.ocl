/*******************************************************************************
 * Copyright (c) 2013, 2022 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.cgmodel.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.utilities.EquivalenceUtil;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CG Operation Call Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGOperationCallExpImpl#getCgArguments <em>Cg Arguments</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGOperationCallExpImpl#getReferredOperation <em>Referred Operation</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGOperationCallExpImpl#getCgThis <em>Cg This</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class CGOperationCallExpImpl extends CGCallExpImpl implements CGOperationCallExp {
	/**
	 * The number of structural features of the '<em>CG Operation Call Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CG_OPERATION_CALL_EXP_FEATURE_COUNT = CGCallExpImpl.CG_CALL_EXP_FEATURE_COUNT + 3;

	/**
	 * The cached value of the '{@link #getCgArguments() <em>Cg Arguments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCgArguments()
	 * @generated
	 * @ordered
	 */
	protected EList<CGValuedElement> cgArguments;

	/**
	 * The default value of the '{@link #getReferredOperation() <em>Referred Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredOperation()
	 * @generated
	 * @ordered
	 */
	protected static final Operation REFERRED_OPERATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReferredOperation() <em>Referred Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredOperation()
	 * @generated
	 * @ordered
	 */
	protected Operation referredOperation = REFERRED_OPERATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCgThis() <em>Cg This</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCgThis()
	 * @generated
	 * @ordered
	 */
	protected CGValuedElement cgThis;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CGOperationCallExpImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CGModelPackage.Literals.CG_OPERATION_CALL_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<CGValuedElement> getCgArguments() {
		if (cgArguments == null) {
			cgArguments = new EObjectContainmentEList<CGValuedElement>(CGValuedElement.class, this, 8);
		}
		return cgArguments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Operation getReferredOperation() {
		return referredOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReferredOperation(Operation newReferredOperation) {
		Operation oldReferredOperation = referredOperation;
		referredOperation = newReferredOperation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 9, oldReferredOperation, referredOperation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CGValuedElement getCgThis() {
		return cgThis;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCgThis(CGValuedElement newCgThis, NotificationChain msgs) {
		CGValuedElement oldCgThis = cgThis;
		cgThis = newCgThis;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, 10, oldCgThis, newCgThis);
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
	public void setCgThis(CGValuedElement newCgThis) {
		if (newCgThis != cgThis) {
			NotificationChain msgs = null;
			if (cgThis != null)
				msgs = ((InternalEObject)cgThis).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (10), null, msgs);
			if (newCgThis != null)
				msgs = ((InternalEObject)newCgThis).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (10), null, msgs);
			msgs = basicSetCgThis(newCgThis, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 10, newCgThis, newCgThis));
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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case 8:
				return ((InternalEList<?>)getCgArguments()).basicRemove(otherEnd, msgs);
			case 10:
				return basicSetCgThis(null, msgs);
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
			case 8:
				return getCgArguments();
			case 9:
				return getReferredOperation();
			case 10:
				return getCgThis();
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
		switch (featureID) {
			case 8:
				getCgArguments().clear();
				getCgArguments().addAll((Collection<? extends CGValuedElement>)newValue);
				return;
			case 9:
				setReferredOperation((Operation)newValue);
				return;
			case 10:
				setCgThis((CGValuedElement)newValue);
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
			case 8:
				getCgArguments().clear();
				return;
			case 9:
				setReferredOperation(REFERRED_OPERATION_EDEFAULT);
				return;
			case 10:
				setCgThis((CGValuedElement)null);
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
			case 8:
				return cgArguments != null && !cgArguments.isEmpty();
			case 9:
				return REFERRED_OPERATION_EDEFAULT == null ? referredOperation != null : !REFERRED_OPERATION_EDEFAULT.equals(referredOperation);
			case 10:
				return cgThis != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable Boolean isEquivalentToInternal(@NonNull CGValuedElement thatValue) {
		return (getClass() == thatValue.getClass()) ? EquivalenceUtil.isEquivalent(this, (CGOperationCallExp)thatValue) : null;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isNonInvalid() {
		if (referredOperation == null) {
			return false;
		}
		if (referredOperation.isIsValidating()) {
			if (referredOperation.isIsInvalidating()) {
				// e.g AND, forAll - nonInvalid if all inputs nonInvalid
			}
			else {
				return true;				// e.g oclIsInvalid
			}
		}
		else {
			if (referredOperation.isIsInvalidating()) {
				return false;				// e.g divide-by-zero
			}
			else {
				// normal use case - nonInvalid if all inputs nonInvalid
			}
		}
		if (referredOperation.isIsStatic()) {
			if (cgThis != null) {
				return false;
			}
		}
		else if (!cgThis.isNonNull() || !cgThis.isNonInvalid()) {
			return false;
		}
		for (@NonNull CGValuedElement argument : ClassUtil.nullFree(getCgArguments())) {
			if (!argument.isNonNull() || !argument.isNonInvalid()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isNonNull() {
		return cgOperation != null ? cgOperation.isRequired() : referredOperation != null ? referredOperation.isIsRequired() : isRequired();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isNull() {
		return false;
	}

	private /*@LazyNonNull*/ CGOperation cgOperation;

	@Override
	public @NonNull CGOperation getOperation() {
		return ClassUtil.nonNullState(cgOperation);
	}

	@Override
	public void setOperation(@NonNull CGOperation cgOperation) {
		assert this.cgOperation == null;
		this.cgOperation = cgOperation;
	}
} //CGOperationCallExpImpl
