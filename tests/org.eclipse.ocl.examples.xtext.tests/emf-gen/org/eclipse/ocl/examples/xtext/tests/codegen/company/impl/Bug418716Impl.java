/**
 * <copyright>
 * 
 * Copyright (c) 2015, 2020 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.ocl.examples.xtext.tests.codegen.company.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.ocl.examples.xtext.tests.codegen.company.Bug418716;
import org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Bug418716</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.impl.Bug418716Impl#getAttributeWithInitital <em>Attribute With Initital</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.impl.Bug418716Impl#getAttributeWithoutInitital <em>Attribute Without Initital</em>}</li>
 * </ul>
 *
 * @generated
 */
public class Bug418716Impl extends EObjectImpl implements Bug418716 {
	/**
	 * The number of structural features of the '<em>Bug418716</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BUG418716_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Bug418716</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BUG418716_OPERATION_COUNT = 0;

	/**
	 * The default value of the '{@link #getAttributeWithInitital() <em>Attribute With Initital</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributeWithInitital()
	 * @generated
	 * @ordered
	 */
	protected static final int ATTRIBUTE_WITH_INITITAL_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getAttributeWithInitital() <em>Attribute With Initital</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributeWithInitital()
	 * @generated
	 * @ordered
	 */
	protected int attributeWithInitital = ATTRIBUTE_WITH_INITITAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getAttributeWithoutInitital() <em>Attribute Without Initital</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributeWithoutInitital()
	 * @generated
	 * @ordered
	 */
	protected static final int ATTRIBUTE_WITHOUT_INITITAL_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getAttributeWithoutInitital() <em>Attribute Without Initital</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributeWithoutInitital()
	 * @generated
	 * @ordered
	 */
	protected int attributeWithoutInitital = ATTRIBUTE_WITHOUT_INITITAL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Bug418716Impl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CodegencompanyPackage.Literals.BUG418716;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getAttributeWithInitital() {
		return attributeWithInitital;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAttributeWithInitital(int newAttributeWithInitital) {
		int oldAttributeWithInitital = attributeWithInitital;
		attributeWithInitital = newAttributeWithInitital;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 0, oldAttributeWithInitital, attributeWithInitital));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getAttributeWithoutInitital() {
		return attributeWithoutInitital;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAttributeWithoutInitital(int newAttributeWithoutInitital) {
		int oldAttributeWithoutInitital = attributeWithoutInitital;
		attributeWithoutInitital = newAttributeWithoutInitital;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 1, oldAttributeWithoutInitital, attributeWithoutInitital));
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
				return getAttributeWithInitital();
			case 1:
				return getAttributeWithoutInitital();
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
			case 0:
				setAttributeWithInitital((Integer)newValue);
				return;
			case 1:
				setAttributeWithoutInitital((Integer)newValue);
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
				setAttributeWithInitital(ATTRIBUTE_WITH_INITITAL_EDEFAULT);
				return;
			case 1:
				setAttributeWithoutInitital(ATTRIBUTE_WITHOUT_INITITAL_EDEFAULT);
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
				return attributeWithInitital != ATTRIBUTE_WITH_INITITAL_EDEFAULT;
			case 1:
				return attributeWithoutInitital != ATTRIBUTE_WITHOUT_INITITAL_EDEFAULT;
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

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (AttributeWithInitital: "); //$NON-NLS-1$
		result.append(attributeWithInitital);
		result.append(", AttributeWithoutInitital: "); //$NON-NLS-1$
		result.append(attributeWithoutInitital);
		result.append(')');
		return result.toString();
	}

} //Bug418716Impl
