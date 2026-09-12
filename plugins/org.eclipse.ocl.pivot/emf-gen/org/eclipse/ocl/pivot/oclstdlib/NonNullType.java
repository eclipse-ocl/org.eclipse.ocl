/**
 * Copyright (c) 2010, 2026 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.oclstdlib;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Non Null Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 *
 * NonNullType wraps a nullable Type for use as a non-null type.
 *
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.oclstdlib.NonNullType#getNullableType <em>Nullable Type</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getNonNullType()
 * @generated
 */
public abstract class NonNullType<T> extends EObjectImpl implements EObject {
	/**
	 * The number of structural features of the '<em>Non Null Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NON_NULL_TYPE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Non Null Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NON_NULL_TYPE_OPERATION_COUNT = 0;


	/**
	 * The cached value of the '{@link #getNullableType() <em>Nullable Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNullableType()
	 * @generated
	 * @ordered
	 */
	protected T nullableType;


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NonNullType() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OCLstdlibPackage.Literals.NON_NULL_TYPE;
	}

	/**
	 * Returns the value of the '<em><b>Nullable Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *
	 * The nullable type wrapped as non-null by this type.
	 *
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Nullable Type</em>' reference.
	 * @see #setNullableType(Object)
	 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getNonNullType_NullableType()
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public T getNullableType() {
		if (nullableType != null && ((EObject)nullableType).eIsProxy()) {
			InternalEObject oldNullableType = (InternalEObject)nullableType;
			nullableType = (T)eResolveProxy(oldNullableType);
			if (nullableType != oldNullableType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 0, oldNullableType, nullableType));
			}
		}
		return nullableType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public T basicGetNullableType() {
		return nullableType;
	}

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.oclstdlib.NonNullType#getNullableType <em>Nullable Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nullable Type</em>' reference.
	 * @see #getNullableType()
	 * @generated
	 */
	public void setNullableType(T newNullableType) {
		T oldNullableType = nullableType;
		nullableType = newNullableType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 0, oldNullableType, nullableType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case 0:
				if (resolve) return getNullableType();
				return basicGetNullableType();
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
			case 0:
				setNullableType((T)newValue);
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
			case 0:
				setNullableType((T)null);
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
			case 0:
				return nullableType != null;
		}
		return super.eIsSet(featureID);
	}


} // NonNullType
