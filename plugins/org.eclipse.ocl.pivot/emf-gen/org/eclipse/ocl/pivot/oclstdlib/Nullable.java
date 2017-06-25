/**
 * Copyright (c) 2010, 2017 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
 * A representation of the model object '<em><b>Nullable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.oclstdlib.Nullable#getNonNullType <em>Non Null Type</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getNullable()
 * @generated
 */
public class Nullable<T extends Object> extends EObjectImpl implements EObject {
	/**
	 * The cached value of the '{@link #getNonNullType() <em>Non Null Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNonNullType()
	 * @generated
	 * @ordered
	 */
	protected Object nonNullType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Nullable() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OCLstdlibPackage.Literals.NULLABLE;
	}

	/**
	 * Returns the value of the '<em><b>Non Null Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The non-invalid, non-null type wrapped by this Nullable.
	 *
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Non Null Type</em>' reference.
	 * @see #setNonNullType(Object)
	 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getNullable_NonNullType()
	 * @generated
	 */
	public Object getNonNullType() {
		if (nonNullType != null && ((EObject)nonNullType).eIsProxy()) {
			InternalEObject oldNonNullType = (InternalEObject)nonNullType;
			nonNullType = (Object)eResolveProxy(oldNonNullType);
			if (nonNullType != oldNonNullType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OCLstdlibPackage.NULLABLE__NON_NULL_TYPE, oldNonNullType, nonNullType));
			}
		}
		return nonNullType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object basicGetNonNullType() {
		return nonNullType;
	}

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.oclstdlib.Nullable#getNonNullType <em>Non Null Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Non Null Type</em>' reference.
	 * @see #getNonNullType()
	 * @generated
	 */
	public void setNonNullType(Object newNonNullType) {
		Object oldNonNullType = nonNullType;
		nonNullType = newNonNullType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLstdlibPackage.NULLABLE__NON_NULL_TYPE, oldNonNullType, nonNullType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OCLstdlibPackage.NULLABLE__NON_NULL_TYPE:
				if (resolve) return getNonNullType();
				return basicGetNonNullType();
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
			case OCLstdlibPackage.NULLABLE__NON_NULL_TYPE:
				setNonNullType((Object)newValue);
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
			case OCLstdlibPackage.NULLABLE__NON_NULL_TYPE:
				setNonNullType((Object)null);
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
			case OCLstdlibPackage.NULLABLE__NON_NULL_TYPE:
				return nonNullType != null;
		}
		return super.eIsSet(featureID);
	}


} // Nullable
