/*******************************************************************************
 * Copyright (c) 2014, 2018 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.validity.test.ecoreTest2.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.ocl.examples.validity.test.ecoreTest2.Eclass5;
import org.eclipse.ocl.examples.validity.test.ecoreTest2.EcoreTest2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Eclass5</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.validity.test.ecoreTest2.impl.Eclass5Impl#getEAttribute5 <em>EAttribute5</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Eclass5Impl extends MinimalEObjectImpl.Container implements Eclass5 {
	/**
	 * The default value of the '{@link #getEAttribute5() <em>EAttribute5</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEAttribute5()
	 * @generated
	 * @ordered
	 */
	protected static final String EATTRIBUTE5_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEAttribute5() <em>EAttribute5</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEAttribute5()
	 * @generated
	 * @ordered
	 */
	protected String eAttribute5 = EATTRIBUTE5_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Eclass5Impl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EcoreTest2Package.Literals.ECLASS5;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEAttribute5() {
		return eAttribute5;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEAttribute5(String newEAttribute5) {
		String oldEAttribute5 = eAttribute5;
		eAttribute5 = newEAttribute5;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EcoreTest2Package.ECLASS5__EATTRIBUTE5, oldEAttribute5, eAttribute5));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EcoreTest2Package.ECLASS5__EATTRIBUTE5:
				return getEAttribute5();
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
			case EcoreTest2Package.ECLASS5__EATTRIBUTE5:
				setEAttribute5((String)newValue);
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
			case EcoreTest2Package.ECLASS5__EATTRIBUTE5:
				setEAttribute5(EATTRIBUTE5_EDEFAULT);
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
			case EcoreTest2Package.ECLASS5__EATTRIBUTE5:
				return EATTRIBUTE5_EDEFAULT == null ? eAttribute5 != null : !EATTRIBUTE5_EDEFAULT.equals(eAttribute5);
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
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (eAttribute5: "); //$NON-NLS-1$
		result.append(eAttribute5);
		result.append(')');
		return result.toString();
	}

} //Eclass5Impl
