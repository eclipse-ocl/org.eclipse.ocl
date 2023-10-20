/**
 * Copyright (c) 2018, 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.xtext.idioms.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.idioms.GrammarDeclaration;
import org.eclipse.ocl.xtext.idioms.IdiomsPackage;
import org.eclipse.ocl.xtext.idioms.RuleLocator;
import org.eclipse.xtext.AbstractRule;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rule Locator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.idioms.impl.RuleLocatorImpl#getReferredGrammar <em>Referred Grammar</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.idioms.impl.RuleLocatorImpl#getReferredRule <em>Referred Rule</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RuleLocatorImpl extends LocatorImpl implements RuleLocator
{
	/**
	 * The number of structural features of the '<em>Rule Locator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RULE_LOCATOR_FEATURE_COUNT = LocatorImpl.LOCATOR_FEATURE_COUNT + 2;


	/**
	 * The cached value of the '{@link #getReferredGrammar() <em>Referred Grammar</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredGrammar()
	 * @generated
	 * @ordered
	 */
	protected GrammarDeclaration referredGrammar;


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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RuleLocatorImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return IdiomsPackage.Literals.RULE_LOCATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GrammarDeclaration getReferredGrammar()
	{
		if (referredGrammar != null && referredGrammar.eIsProxy())
		{
			InternalEObject oldReferredGrammar = (InternalEObject)referredGrammar;
			referredGrammar = (GrammarDeclaration)eResolveProxy(oldReferredGrammar);
			if (referredGrammar != oldReferredGrammar)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 1, oldReferredGrammar, referredGrammar));
			}
		}
		return referredGrammar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GrammarDeclaration basicGetReferredGrammar()
	{
		return referredGrammar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReferredGrammar(GrammarDeclaration newReferredGrammar)
	{
		GrammarDeclaration oldReferredGrammar = referredGrammar;
		referredGrammar = newReferredGrammar;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 1, oldReferredGrammar, referredGrammar));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AbstractRule getReferredRule()
	{
		if (referredRule != null && referredRule.eIsProxy())
		{
			InternalEObject oldReferredRule = (InternalEObject)referredRule;
			referredRule = (AbstractRule)eResolveProxy(oldReferredRule);
			if (referredRule != oldReferredRule)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 2, oldReferredRule, referredRule));
			}
		}
		return referredRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractRule basicGetReferredRule()
	{
		return referredRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReferredRule(AbstractRule newReferredRule)
	{
		AbstractRule oldReferredRule = referredRule;
		referredRule = newReferredRule;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 2, oldReferredRule, referredRule));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch (featureID)
		{
			case 1:
				if (resolve) return getReferredGrammar();
				return basicGetReferredGrammar();
			case 2:
				if (resolve) return getReferredRule();
				return basicGetReferredRule();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case 1:
				setReferredGrammar((GrammarDeclaration)newValue);
				return;
			case 2:
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
	public void eUnset(int featureID)
	{
		switch (featureID)
		{
			case 1:
				setReferredGrammar((GrammarDeclaration)null);
				return;
			case 2:
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
	public boolean eIsSet(int featureID)
	{
		switch (featureID)
		{
			case 1:
				return referredGrammar != null;
			case 2:
				return referredRule != null;
		}
		return super.eIsSet(featureID);
	}

	@Override
	public boolean covers(@NonNull AbstractRule rule) {
		return referredRule == rule;
	}

	@Override
	public String toString() {
		return /*"at rule " +*/ referredRule.getName();
	}
} //RuleLocatorImpl
