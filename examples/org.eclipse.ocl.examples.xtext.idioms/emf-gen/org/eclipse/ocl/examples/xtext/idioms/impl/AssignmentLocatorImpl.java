/**
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.examples.xtext.idioms.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ocl.examples.xtext.idioms.AssignmentLocator;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Assignment Locator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.AssignmentLocatorImpl#getEPackage <em>EPackage</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.AssignmentLocatorImpl#getEClass <em>EClass</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.AssignmentLocatorImpl#getEStructuralFeature <em>EStructural Feature</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AssignmentLocatorImpl
		extends LocatorImpl
		implements AssignmentLocator {

	/**
	 * The cached value of the '{@link #getEPackage() <em>EPackage</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEPackage()
	 * @generated
	 * @ordered
	 */
	protected EPackage ePackage;

	/**
	 * The cached value of the '{@link #getEClass() <em>EClass</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEClass()
	 * @generated
	 * @ordered
	 */
	protected EClass eClass;

	/**
	 * The cached value of the '{@link #getEStructuralFeature() <em>EStructural Feature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEStructuralFeature()
	 * @generated
	 * @ordered
	 */
	protected EStructuralFeature eStructuralFeature;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssignmentLocatorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdiomsPackage.Literals.ASSIGNMENT_LOCATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EPackage getEPackage() {
		if (ePackage != null && ePackage.eIsProxy()) {
			InternalEObject oldEPackage = (InternalEObject) ePackage;
			ePackage = (EPackage) eResolveProxy(oldEPackage);
			if (ePackage != oldEPackage) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
						IdiomsPackage.ASSIGNMENT_LOCATOR__EPACKAGE, oldEPackage,
						ePackage));
			}
		}
		return ePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EPackage basicGetEPackage() {
		return ePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEPackage(EPackage newEPackage) {
		EPackage oldEPackage = ePackage;
		ePackage = newEPackage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
				IdiomsPackage.ASSIGNMENT_LOCATOR__EPACKAGE, oldEPackage,
				ePackage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEClass() {
		if (eClass != null && eClass.eIsProxy()) {
			InternalEObject oldEClass = (InternalEObject) eClass;
			eClass = (EClass) eResolveProxy(oldEClass);
			if (eClass != oldEClass) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
						IdiomsPackage.ASSIGNMENT_LOCATOR__ECLASS, oldEClass,
						eClass));
			}
		}
		return eClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass basicGetEClass() {
		return eClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEClass(EClass newEClass) {
		EClass oldEClass = eClass;
		eClass = newEClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
				IdiomsPackage.ASSIGNMENT_LOCATOR__ECLASS, oldEClass, eClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EStructuralFeature getEStructuralFeature() {
		if (eStructuralFeature != null && eStructuralFeature.eIsProxy()) {
			InternalEObject oldEStructuralFeature = (InternalEObject) eStructuralFeature;
			eStructuralFeature = (EStructuralFeature) eResolveProxy(
				oldEStructuralFeature);
			if (eStructuralFeature != oldEStructuralFeature) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
						IdiomsPackage.ASSIGNMENT_LOCATOR__ESTRUCTURAL_FEATURE,
						oldEStructuralFeature, eStructuralFeature));
			}
		}
		return eStructuralFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EStructuralFeature basicGetEStructuralFeature() {
		return eStructuralFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEStructuralFeature(
			EStructuralFeature newEStructuralFeature) {
		EStructuralFeature oldEStructuralFeature = eStructuralFeature;
		eStructuralFeature = newEStructuralFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
				IdiomsPackage.ASSIGNMENT_LOCATOR__ESTRUCTURAL_FEATURE,
				oldEStructuralFeature, eStructuralFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IdiomsPackage.ASSIGNMENT_LOCATOR__EPACKAGE :
				if (resolve)
					return getEPackage();
				return basicGetEPackage();
			case IdiomsPackage.ASSIGNMENT_LOCATOR__ECLASS :
				if (resolve)
					return getEClass();
				return basicGetEClass();
			case IdiomsPackage.ASSIGNMENT_LOCATOR__ESTRUCTURAL_FEATURE :
				if (resolve)
					return getEStructuralFeature();
				return basicGetEStructuralFeature();
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
			case IdiomsPackage.ASSIGNMENT_LOCATOR__EPACKAGE :
				setEPackage((EPackage) newValue);
				return;
			case IdiomsPackage.ASSIGNMENT_LOCATOR__ECLASS :
				setEClass((EClass) newValue);
				return;
			case IdiomsPackage.ASSIGNMENT_LOCATOR__ESTRUCTURAL_FEATURE :
				setEStructuralFeature((EStructuralFeature) newValue);
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
			case IdiomsPackage.ASSIGNMENT_LOCATOR__EPACKAGE :
				setEPackage((EPackage) null);
				return;
			case IdiomsPackage.ASSIGNMENT_LOCATOR__ECLASS :
				setEClass((EClass) null);
				return;
			case IdiomsPackage.ASSIGNMENT_LOCATOR__ESTRUCTURAL_FEATURE :
				setEStructuralFeature((EStructuralFeature) null);
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
			case IdiomsPackage.ASSIGNMENT_LOCATOR__EPACKAGE :
				return ePackage != null;
			case IdiomsPackage.ASSIGNMENT_LOCATOR__ECLASS :
				return eClass != null;
			case IdiomsPackage.ASSIGNMENT_LOCATOR__ESTRUCTURAL_FEATURE :
				return eStructuralFeature != null;
		}
		return super.eIsSet(featureID);
	}

	@Override
	public String toString() {
		EStructuralFeature eStructuralFeature = getEStructuralFeature();
		return eStructuralFeature.getEContainingClass().getName() + "::"
			+ eStructuralFeature.getName();
	}
} //AssignmentLocatorImpl
