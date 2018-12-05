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
import org.eclipse.ocl.examples.validity.test.ecoreTest.Eclass1;
import org.eclipse.ocl.examples.validity.test.ecoreTest.EcoreTestPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Eclass1</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.validity.test.ecoreTest.impl.Eclass1Impl#getEAttribute1 <em>EAttribute1</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.validity.test.ecoreTest.impl.Eclass1Impl#getEAttribute2 <em>EAttribute2</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.validity.test.ecoreTest.impl.Eclass1Impl#getClasses2 <em>Classes2</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Eclass1Impl extends MinimalEObjectImpl.Container implements Eclass1 {
	/**
	 * The default value of the '{@link #getEAttribute1() <em>EAttribute1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEAttribute1()
	 * @generated
	 * @ordered
	 */
	protected static final String EATTRIBUTE1_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEAttribute1() <em>EAttribute1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEAttribute1()
	 * @generated
	 * @ordered
	 */
	protected String eAttribute1 = EATTRIBUTE1_EDEFAULT;

	/**
	 * The default value of the '{@link #getEAttribute2() <em>EAttribute2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEAttribute2()
	 * @generated
	 * @ordered
	 */
	protected static final String EATTRIBUTE2_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEAttribute2() <em>EAttribute2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEAttribute2()
	 * @generated
	 * @ordered
	 */
	protected String eAttribute2 = EATTRIBUTE2_EDEFAULT;

	/**
	 * The cached value of the '{@link #getClasses2() <em>Classes2</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClasses2()
	 * @generated
	 * @ordered
	 */
	protected EList<EClass2> classes2;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Eclass1Impl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EcoreTestPackage.Literals.ECLASS1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEAttribute1() {
		return eAttribute1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEAttribute1(String newEAttribute1) {
		String oldEAttribute1 = eAttribute1;
		eAttribute1 = newEAttribute1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EcoreTestPackage.ECLASS1__EATTRIBUTE1, oldEAttribute1, eAttribute1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEAttribute2() {
		return eAttribute2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEAttribute2(String newEAttribute2) {
		String oldEAttribute2 = eAttribute2;
		eAttribute2 = newEAttribute2;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EcoreTestPackage.ECLASS1__EATTRIBUTE2, oldEAttribute2, eAttribute2));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EClass2> getClasses2() {
		if (classes2 == null) {
			classes2 = new EObjectContainmentEList<EClass2>(EClass2.class, this, EcoreTestPackage.ECLASS1__CLASSES2);
		}
		return classes2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EcoreTestPackage.ECLASS1__CLASSES2:
				return ((InternalEList<?>)getClasses2()).basicRemove(otherEnd, msgs);
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
			case EcoreTestPackage.ECLASS1__EATTRIBUTE1:
				return getEAttribute1();
			case EcoreTestPackage.ECLASS1__EATTRIBUTE2:
				return getEAttribute2();
			case EcoreTestPackage.ECLASS1__CLASSES2:
				return getClasses2();
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
			case EcoreTestPackage.ECLASS1__EATTRIBUTE1:
				setEAttribute1((String)newValue);
				return;
			case EcoreTestPackage.ECLASS1__EATTRIBUTE2:
				setEAttribute2((String)newValue);
				return;
			case EcoreTestPackage.ECLASS1__CLASSES2:
				getClasses2().clear();
				getClasses2().addAll((Collection<? extends EClass2>)newValue);
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
			case EcoreTestPackage.ECLASS1__EATTRIBUTE1:
				setEAttribute1(EATTRIBUTE1_EDEFAULT);
				return;
			case EcoreTestPackage.ECLASS1__EATTRIBUTE2:
				setEAttribute2(EATTRIBUTE2_EDEFAULT);
				return;
			case EcoreTestPackage.ECLASS1__CLASSES2:
				getClasses2().clear();
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
			case EcoreTestPackage.ECLASS1__EATTRIBUTE1:
				return EATTRIBUTE1_EDEFAULT == null ? eAttribute1 != null : !EATTRIBUTE1_EDEFAULT.equals(eAttribute1);
			case EcoreTestPackage.ECLASS1__EATTRIBUTE2:
				return EATTRIBUTE2_EDEFAULT == null ? eAttribute2 != null : !EATTRIBUTE2_EDEFAULT.equals(eAttribute2);
			case EcoreTestPackage.ECLASS1__CLASSES2:
				return classes2 != null && !classes2.isEmpty();
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
		result.append(" (eAttribute1: "); //$NON-NLS-1$
		result.append(eAttribute1);
		result.append(", eAttribute2: "); //$NON-NLS-1$
		result.append(eAttribute2);
		result.append(')');
		return result.toString();
	}

} //Eclass1Impl
