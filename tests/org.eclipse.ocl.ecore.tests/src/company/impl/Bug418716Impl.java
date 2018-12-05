/*******************************************************************************
 * Copyright (c) 2016, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
/**
 */
package company.impl;

import company.Bug418716;
import company.CompanyPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Bug418716</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link company.impl.Bug418716Impl#getAttributeWithInitital <em>Attribute With Initital</em>}</li>
 *   <li>{@link company.impl.Bug418716Impl#getAttributeWithoutInitital <em>Attribute Without Initital</em>}</li>
 * </ul>
 *
 * @generated
 */
public class Bug418716Impl extends EObjectImpl implements Bug418716 {
	/**
	 * The cached setting delegate for the '{@link #getAttributeWithInitital() <em>Attribute With Initital</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributeWithInitital()
	 * @generated
	 * @ordered
	 */
	protected EStructuralFeature.Internal.SettingDelegate ATTRIBUTE_WITH_INITITAL__ESETTING_DELEGATE = ((EStructuralFeature.Internal)CompanyPackage.Literals.BUG418716__ATTRIBUTE_WITH_INITITAL).getSettingDelegate();

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
		return CompanyPackage.Literals.BUG418716;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getAttributeWithInitital() {
		return (Integer)ATTRIBUTE_WITH_INITITAL__ESETTING_DELEGATE.dynamicGet(this, null, 0, true, false);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttributeWithInitital(int newAttributeWithInitital) {
		ATTRIBUTE_WITH_INITITAL__ESETTING_DELEGATE.dynamicSet(this, null, 0, newAttributeWithInitital);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getAttributeWithoutInitital() {
		return attributeWithoutInitital;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttributeWithoutInitital(int newAttributeWithoutInitital) {
		int oldAttributeWithoutInitital = attributeWithoutInitital;
		attributeWithoutInitital = newAttributeWithoutInitital;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompanyPackage.BUG418716__ATTRIBUTE_WITHOUT_INITITAL, oldAttributeWithoutInitital, attributeWithoutInitital));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CompanyPackage.BUG418716__ATTRIBUTE_WITH_INITITAL:
				return getAttributeWithInitital();
			case CompanyPackage.BUG418716__ATTRIBUTE_WITHOUT_INITITAL:
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
			case CompanyPackage.BUG418716__ATTRIBUTE_WITH_INITITAL:
				setAttributeWithInitital((Integer)newValue);
				return;
			case CompanyPackage.BUG418716__ATTRIBUTE_WITHOUT_INITITAL:
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
			case CompanyPackage.BUG418716__ATTRIBUTE_WITH_INITITAL:
				ATTRIBUTE_WITH_INITITAL__ESETTING_DELEGATE.dynamicUnset(this, null, 0);
				return;
			case CompanyPackage.BUG418716__ATTRIBUTE_WITHOUT_INITITAL:
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
			case CompanyPackage.BUG418716__ATTRIBUTE_WITH_INITITAL:
				return ATTRIBUTE_WITH_INITITAL__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);
			case CompanyPackage.BUG418716__ATTRIBUTE_WITHOUT_INITITAL:
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

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (AttributeWithoutInitital: "); //$NON-NLS-1$
		result.append(attributeWithoutInitital);
		result.append(')');
		return result.toString();
	}

} //Bug418716Impl
