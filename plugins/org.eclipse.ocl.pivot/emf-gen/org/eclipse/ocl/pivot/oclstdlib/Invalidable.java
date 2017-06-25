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
 * A representation of the model object '<em><b>Invalidable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.oclstdlib.Invalidable#getNonInvalidType <em>Non Invalid Type</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getInvalidable()
 * @generated
 */
public class Invalidable<T extends Object> extends EObjectImpl implements EObject {
	/**
	 * The cached value of the '{@link #getNonInvalidType() <em>Non Invalid Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNonInvalidType()
	 * @generated
	 * @ordered
	 */
	protected Object nonInvalidType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Invalidable() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OCLstdlibPackage.Literals.INVALIDABLE;
	}

	/**
	 * Returns the value of the '<em><b>Non Invalid Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The non-invalid, maybe-null type wrapped by this Invalidable.
	 *
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Non Invalid Type</em>' reference.
	 * @see #setNonInvalidType(Object)
	 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage#getInvalidable_NonInvalidType()
	 * @generated
	 */
	public Object getNonInvalidType() {
		if (nonInvalidType != null && ((EObject)nonInvalidType).eIsProxy()) {
			InternalEObject oldNonInvalidType = (InternalEObject)nonInvalidType;
			nonInvalidType = (Object)eResolveProxy(oldNonInvalidType);
			if (nonInvalidType != oldNonInvalidType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OCLstdlibPackage.INVALIDABLE__NON_INVALID_TYPE, oldNonInvalidType, nonInvalidType));
			}
		}
		return nonInvalidType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object basicGetNonInvalidType() {
		return nonInvalidType;
	}

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.oclstdlib.Invalidable#getNonInvalidType <em>Non Invalid Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Non Invalid Type</em>' reference.
	 * @see #getNonInvalidType()
	 * @generated
	 */
	public void setNonInvalidType(Object newNonInvalidType) {
		Object oldNonInvalidType = nonInvalidType;
		nonInvalidType = newNonInvalidType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLstdlibPackage.INVALIDABLE__NON_INVALID_TYPE, oldNonInvalidType, nonInvalidType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OCLstdlibPackage.INVALIDABLE__NON_INVALID_TYPE:
				if (resolve) return getNonInvalidType();
				return basicGetNonInvalidType();
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
			case OCLstdlibPackage.INVALIDABLE__NON_INVALID_TYPE:
				setNonInvalidType((Object)newValue);
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
			case OCLstdlibPackage.INVALIDABLE__NON_INVALID_TYPE:
				setNonInvalidType((Object)null);
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
			case OCLstdlibPackage.INVALIDABLE__NON_INVALID_TYPE:
				return nonInvalidType != null;
		}
		return super.eIsSet(featureID);
	}


} // Invalidable
