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
import org.eclipse.ocl.pivot.NamedElement;
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
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGOperationCallExpImpl#getReferredOperation <em>Referred Operation</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGOperationCallExpImpl#getArguments <em>Arguments</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGOperationCallExpImpl#getAsOperation <em>As Operation</em>}</li>
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
	 * The cached value of the '{@link #getReferredOperation() <em>Referred Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredOperation()
	 * @generated
	 * @ordered
	 */
	protected CGOperation referredOperation;

	/**
	 * The cached value of the '{@link #getArguments() <em>Arguments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArguments()
	 * @generated
	 * @ordered
	 */
	protected EList<CGValuedElement> arguments;

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
	public CGOperation getReferredOperation() {
		return referredOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReferredOperation(CGOperation newReferredOperation) {
		CGOperation oldReferredOperation = referredOperation;
		referredOperation = newReferredOperation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 8, oldReferredOperation, referredOperation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<CGValuedElement> getArguments() {
		if (arguments == null) {
			arguments = new EObjectContainmentEList<CGValuedElement>(CGValuedElement.class, this, 9);
		}
		return arguments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Operation getAsOperation() {
		return asOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAsOperation(Operation newAsOperation) {
		Operation oldAsOperation = asOperation;
		asOperation = newAsOperation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 10, oldAsOperation, asOperation));
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
			case 9:
				return ((InternalEList<?>)getArguments()).basicRemove(otherEnd, msgs);
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
				return getReferredOperation();
			case 9:
				return getArguments();
			case 10:
				return getAsOperation();
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
				setReferredOperation((CGOperation)newValue);
				return;
			case 9:
				getArguments().clear();
				getArguments().addAll((Collection<? extends CGValuedElement>)newValue);
				return;
			case 10:
				setAsOperation((Operation)newValue);
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
				setReferredOperation((CGOperation)null);
				return;
			case 9:
				getArguments().clear();
				return;
			case 10:
				setAsOperation(AS_OPERATION_EDEFAULT);
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
				return referredOperation != null;
			case 9:
				return arguments != null && !arguments.isEmpty();
			case 10:
				return AS_OPERATION_EDEFAULT == null ? asOperation != null : !AS_OPERATION_EDEFAULT.equals(asOperation);
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
		if (asOperation == null) {
			return false;
		}
		if (asOperation.isIsValidating()) {
			if (asOperation.isIsInvalidating()) {
				// e.g AND, forAll - nonInvalid if all inputs nonInvalid
			}
			else {
				return true;				// e.g oclIsInvalid
			}
		}
		else {
			if (asOperation.isIsInvalidating()) {
				return false;				// e.g divide-by-zero
			}
			else {
				// normal use case - nonInvalid if all inputs nonInvalid
			}
		}
		for (@NonNull CGValuedElement argument : ClassUtil.nullFree(getArguments())) {
			if (!argument.isRequiredOrNonNull() || !argument.isNonInvalid()) {
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
		return ((referredOperation != null) && referredOperation.isRequired()) || ((asOperation != null) && asOperation.isIsRequired());
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isNull() {
		return false;
	}

	/**
	 * The default value of the '{@link #getAsOperation() <em>As Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAsOperation()
	 * @generated
	 * @ordered
	 */
	protected static final Operation AS_OPERATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAsOperation() <em>As Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAsOperation()
	 * @generated
	 * @ordered
	 */
	protected Operation asOperation = AS_OPERATION_EDEFAULT;

	@Override
	public void setCaught(boolean isCaught) {
		if (isCaught) {
			if ((ast != null) && ((NamedElement)ast).getName().equals("first")) {
				getClass();		// XXX
			}
		}
		super.setCaught(isCaught);
	}
} //CGOperationCallExpImpl
