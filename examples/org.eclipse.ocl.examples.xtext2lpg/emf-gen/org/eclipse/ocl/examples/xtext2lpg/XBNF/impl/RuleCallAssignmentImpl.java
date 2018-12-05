/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext2lpg.XBNF.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractRule;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.RuleCall;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.RuleCallAssignment;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rule Call Assignment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.RuleCallAssignmentImpl#getReferredRule <em>Referred Rule</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.RuleCallAssignmentImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RuleCallAssignmentImpl extends AssignmentImpl implements RuleCallAssignment {
	/**
	 * The cached value of the '{@link #getReferredRule() <em>Referred Rule</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredRule()
	 * @generated
	 * @ordered
	 */
	protected AbstractRule referredRule;

	/**
	 * The cached setting delegate for the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected EStructuralFeature.Internal.SettingDelegate NAME__ESETTING_DELEGATE = ((EStructuralFeature.Internal)XBNFPackage.Literals.RULE_CALL__NAME).getSettingDelegate();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RuleCallAssignmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XBNFPackage.Literals.RULE_CALL_ASSIGNMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractRule getReferredRule() {
		if (referredRule != null && referredRule.eIsProxy()) {
			InternalEObject oldReferredRule = (InternalEObject)referredRule;
			referredRule = (AbstractRule)eResolveProxy(oldReferredRule);
			if (referredRule != oldReferredRule) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, XBNFPackage.RULE_CALL_ASSIGNMENT__REFERRED_RULE, oldReferredRule, referredRule));
			}
		}
		return referredRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractRule basicGetReferredRule() {
		return referredRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferredRule(AbstractRule newReferredRule) {
		AbstractRule oldReferredRule = referredRule;
		referredRule = newReferredRule;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XBNFPackage.RULE_CALL_ASSIGNMENT__REFERRED_RULE, oldReferredRule, referredRule));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return (String)NAME__ESETTING_DELEGATE.dynamicGet(this, null, 0, true, false);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case XBNFPackage.RULE_CALL_ASSIGNMENT__REFERRED_RULE:
				if (resolve) return getReferredRule();
				return basicGetReferredRule();
			case XBNFPackage.RULE_CALL_ASSIGNMENT__NAME:
				return getName();
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
			case XBNFPackage.RULE_CALL_ASSIGNMENT__REFERRED_RULE:
				setReferredRule((AbstractRule)newValue);
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
			case XBNFPackage.RULE_CALL_ASSIGNMENT__REFERRED_RULE:
				setReferredRule((AbstractRule)null);
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
			case XBNFPackage.RULE_CALL_ASSIGNMENT__REFERRED_RULE:
				return referredRule != null;
			case XBNFPackage.RULE_CALL_ASSIGNMENT__NAME:
				return NAME__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == RuleCall.class) {
			switch (derivedFeatureID) {
				case XBNFPackage.RULE_CALL_ASSIGNMENT__REFERRED_RULE: return XBNFPackage.RULE_CALL__REFERRED_RULE;
				case XBNFPackage.RULE_CALL_ASSIGNMENT__NAME: return XBNFPackage.RULE_CALL__NAME;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == RuleCall.class) {
			switch (baseFeatureID) {
				case XBNFPackage.RULE_CALL__REFERRED_RULE: return XBNFPackage.RULE_CALL_ASSIGNMENT__REFERRED_RULE;
				case XBNFPackage.RULE_CALL__NAME: return XBNFPackage.RULE_CALL_ASSIGNMENT__NAME;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //RuleCallAssignmentImpl
