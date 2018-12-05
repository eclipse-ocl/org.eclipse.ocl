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
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractElement;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractRule;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.AbstractElementImpl#getDebug <em>Debug</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.AbstractElementImpl#getParentRule <em>Parent Rule</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractElementImpl extends MinimalEObjectImpl.Container implements AbstractElement {
	/**
	 * The default value of the '{@link #getDebug() <em>Debug</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDebug()
	 * @generated
	 * @ordered
	 */
	protected static final String DEBUG_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDebug() <em>Debug</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDebug()
	 * @generated
	 * @ordered
	 */
	protected String debug = DEBUG_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XBNFPackage.Literals.ABSTRACT_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDebug() {
		return debug;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDebug(String newDebug) {
		String oldDebug = debug;
		debug = newDebug;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XBNFPackage.ABSTRACT_ELEMENT__DEBUG, oldDebug, debug));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractRule getParentRule() {
		if (eContainerFeatureID() != XBNFPackage.ABSTRACT_ELEMENT__PARENT_RULE) return null;
		return (AbstractRule)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParentRule(AbstractRule newParentRule, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParentRule, XBNFPackage.ABSTRACT_ELEMENT__PARENT_RULE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentRule(AbstractRule newParentRule) {
		if (newParentRule != eInternalContainer() || (eContainerFeatureID() != XBNFPackage.ABSTRACT_ELEMENT__PARENT_RULE && newParentRule != null)) {
			if (EcoreUtil.isAncestor(this, newParentRule))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParentRule != null)
				msgs = ((InternalEObject)newParentRule).eInverseAdd(this, XBNFPackage.ABSTRACT_RULE__ELEMENT, AbstractRule.class, msgs);
			msgs = basicSetParentRule(newParentRule, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XBNFPackage.ABSTRACT_ELEMENT__PARENT_RULE, newParentRule, newParentRule));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case XBNFPackage.ABSTRACT_ELEMENT__PARENT_RULE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParentRule((AbstractRule)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case XBNFPackage.ABSTRACT_ELEMENT__PARENT_RULE:
				return basicSetParentRule(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case XBNFPackage.ABSTRACT_ELEMENT__PARENT_RULE:
				return eInternalContainer().eInverseRemove(this, XBNFPackage.ABSTRACT_RULE__ELEMENT, AbstractRule.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case XBNFPackage.ABSTRACT_ELEMENT__DEBUG:
				return getDebug();
			case XBNFPackage.ABSTRACT_ELEMENT__PARENT_RULE:
				return getParentRule();
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
			case XBNFPackage.ABSTRACT_ELEMENT__DEBUG:
				setDebug((String)newValue);
				return;
			case XBNFPackage.ABSTRACT_ELEMENT__PARENT_RULE:
				setParentRule((AbstractRule)newValue);
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
			case XBNFPackage.ABSTRACT_ELEMENT__DEBUG:
				setDebug(DEBUG_EDEFAULT);
				return;
			case XBNFPackage.ABSTRACT_ELEMENT__PARENT_RULE:
				setParentRule((AbstractRule)null);
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
			case XBNFPackage.ABSTRACT_ELEMENT__DEBUG:
				return DEBUG_EDEFAULT == null ? debug != null : !DEBUG_EDEFAULT.equals(debug);
			case XBNFPackage.ABSTRACT_ELEMENT__PARENT_RULE:
				return getParentRule() != null;
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
		result.append(" (debug: ");
		result.append(debug);
		result.append(')');
		return result.toString();
	}

} //AbstractElementImpl
