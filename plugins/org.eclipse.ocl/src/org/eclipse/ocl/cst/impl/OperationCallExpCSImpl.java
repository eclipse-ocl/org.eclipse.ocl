/*******************************************************************************
 * Copyright (c) 2005, 2024 IBM Corporation, Zeligsoft Inc., and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   Zeligsoft - Bug 243976
 *******************************************************************************/
package org.eclipse.ocl.cst.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ocl.cst.CSTPackage;
import org.eclipse.ocl.cst.OperationCallExpCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation Call Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.cst.impl.OperationCallExpCSImpl#getIsAtomic <em>Is Atomic</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OperationCallExpCSImpl
		extends FeatureCallExpCSImpl
		implements OperationCallExpCS {

	/**
	 * The default value of the '{@link #getIsAtomic() <em>Is Atomic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 3.0
	 * <!-- end-user-doc -->
	 * @see #getIsAtomic()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_ATOMIC_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getIsAtomic() <em>Is Atomic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 3.0
	 * <!-- end-user-doc -->
	 * @see #getIsAtomic()
	 * @generated
	 * @ordered
	 */
	protected Boolean isAtomic = IS_ATOMIC_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationCallExpCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CSTPackage.Literals.OPERATION_CALL_EXP_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 3.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getIsAtomic() {
		return isAtomic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 3.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsAtomic(Boolean newIsAtomic) {
		Boolean oldIsAtomic = isAtomic;
		isAtomic = newIsAtomic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
				CSTPackage.OPERATION_CALL_EXP_CS__IS_ATOMIC, oldIsAtomic,
				isAtomic));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CSTPackage.OPERATION_CALL_EXP_CS__IS_ATOMIC :
				return getIsAtomic();
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
			case CSTPackage.OPERATION_CALL_EXP_CS__IS_ATOMIC :
				setIsAtomic((Boolean) newValue);
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
			case CSTPackage.OPERATION_CALL_EXP_CS__IS_ATOMIC :
				setIsAtomic(IS_ATOMIC_EDEFAULT);
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
			case CSTPackage.OPERATION_CALL_EXP_CS__IS_ATOMIC :
				return IS_ATOMIC_EDEFAULT == null
					? isAtomic != null
					: !IS_ATOMIC_EDEFAULT.equals(isAtomic);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (isAtomic: "); //$NON-NLS-1$
		result.append(isAtomic);
		result.append(')');
		return result.toString();
	}

} //OperationCallExpCSImpl
