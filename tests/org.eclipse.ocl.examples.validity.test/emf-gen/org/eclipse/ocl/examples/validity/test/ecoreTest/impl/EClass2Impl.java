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
package org.eclipse.ocl.examples.validity.test.ecoreTest.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.examples.validity.test.ecoreTest.EClass2;
import org.eclipse.ocl.examples.validity.test.ecoreTest.EClass3;
import org.eclipse.ocl.examples.validity.test.ecoreTest.EcoreTestPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EClass2</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.validity.test.ecoreTest.impl.EClass2Impl#getEAttribute3 <em>EAttribute3</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.validity.test.ecoreTest.impl.EClass2Impl#getClasses3 <em>Classes3</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.validity.test.ecoreTest.impl.EClass2Impl#getEAttribute4 <em>EAttribute4</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EClass2Impl extends MinimalEObjectImpl.Container implements EClass2 {
	/**
	 * The default value of the '{@link #getEAttribute3() <em>EAttribute3</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEAttribute3()
	 * @generated
	 * @ordered
	 */
	protected static final short EATTRIBUTE3_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getEAttribute3() <em>EAttribute3</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEAttribute3()
	 * @generated
	 * @ordered
	 */
	protected short eAttribute3 = EATTRIBUTE3_EDEFAULT;

	/**
	 * The cached value of the '{@link #getClasses3() <em>Classes3</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClasses3()
	 * @generated
	 * @ordered
	 */
	protected EList<EClass3> classes3;

	/**
	 * The default value of the '{@link #getEAttribute4() <em>EAttribute4</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEAttribute4()
	 * @generated
	 * @ordered
	 */
	protected static final String EATTRIBUTE4_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEAttribute4() <em>EAttribute4</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEAttribute4()
	 * @generated
	 * @ordered
	 */
	protected String eAttribute4 = EATTRIBUTE4_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass2Impl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EcoreTestPackage.Literals.ECLASS2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public short getEAttribute3() {
		return eAttribute3;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEAttribute3(short newEAttribute3) {
		short oldEAttribute3 = eAttribute3;
		eAttribute3 = newEAttribute3;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EcoreTestPackage.ECLASS2__EATTRIBUTE3, oldEAttribute3, eAttribute3));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EClass3> getClasses3() {
		if (classes3 == null) {
			classes3 = new EObjectContainmentEList<EClass3>(EClass3.class, this, EcoreTestPackage.ECLASS2__CLASSES3);
		}
		return classes3;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEAttribute4() {
		return eAttribute4;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEAttribute4(String newEAttribute4) {
		String oldEAttribute4 = eAttribute4;
		eAttribute4 = newEAttribute4;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EcoreTestPackage.ECLASS2__EATTRIBUTE4, oldEAttribute4, eAttribute4));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EcoreTestPackage.ECLASS2__CLASSES3:
				return ((InternalEList<?>)getClasses3()).basicRemove(otherEnd, msgs);
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
			case EcoreTestPackage.ECLASS2__EATTRIBUTE3:
				return getEAttribute3();
			case EcoreTestPackage.ECLASS2__CLASSES3:
				return getClasses3();
			case EcoreTestPackage.ECLASS2__EATTRIBUTE4:
				return getEAttribute4();
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
			case EcoreTestPackage.ECLASS2__EATTRIBUTE3:
				setEAttribute3((Short)newValue);
				return;
			case EcoreTestPackage.ECLASS2__CLASSES3:
				getClasses3().clear();
				getClasses3().addAll((Collection<? extends EClass3>)newValue);
				return;
			case EcoreTestPackage.ECLASS2__EATTRIBUTE4:
				setEAttribute4((String)newValue);
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
			case EcoreTestPackage.ECLASS2__EATTRIBUTE3:
				setEAttribute3(EATTRIBUTE3_EDEFAULT);
				return;
			case EcoreTestPackage.ECLASS2__CLASSES3:
				getClasses3().clear();
				return;
			case EcoreTestPackage.ECLASS2__EATTRIBUTE4:
				setEAttribute4(EATTRIBUTE4_EDEFAULT);
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
			case EcoreTestPackage.ECLASS2__EATTRIBUTE3:
				return eAttribute3 != EATTRIBUTE3_EDEFAULT;
			case EcoreTestPackage.ECLASS2__CLASSES3:
				return classes3 != null && !classes3.isEmpty();
			case EcoreTestPackage.ECLASS2__EATTRIBUTE4:
				return EATTRIBUTE4_EDEFAULT == null ? eAttribute4 != null : !EATTRIBUTE4_EDEFAULT.equals(eAttribute4);
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
		result.append(" (eAttribute3: "); //$NON-NLS-1$
		result.append(eAttribute3);
		result.append(", eAttribute4: "); //$NON-NLS-1$
		result.append(eAttribute4);
		result.append(')');
		return result.toString();
	}

} //EClass2Impl
