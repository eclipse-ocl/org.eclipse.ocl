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

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.Grammar;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.TypedRule;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.UntypedRule;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Typed Rule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.TypedRuleImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.TypedRuleImpl#getSubrules <em>Subrules</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.TypedRuleImpl#getGrammar <em>Grammar</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class TypedRuleImpl extends AbstractRuleImpl implements TypedRule {
	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected EClassifier type;

	/**
	 * The cached value of the '{@link #getSubrules() <em>Subrules</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubrules()
	 * @generated
	 * @ordered
	 */
	protected EList<UntypedRule> subrules;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypedRuleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XBNFPackage.Literals.TYPED_RULE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClassifier getType() {
		if (type != null && type.eIsProxy()) {
			InternalEObject oldType = (InternalEObject)type;
			type = (EClassifier)eResolveProxy(oldType);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, XBNFPackage.TYPED_RULE__TYPE, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClassifier basicGetType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(EClassifier newType) {
		EClassifier oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XBNFPackage.TYPED_RULE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UntypedRule> getSubrules() {
		if (subrules == null) {
			subrules = new EObjectContainmentWithInverseEList<UntypedRule>(UntypedRule.class, this, XBNFPackage.TYPED_RULE__SUBRULES, XBNFPackage.UNTYPED_RULE__TYPED_RULE);
		}
		return subrules;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Grammar getGrammar() {
		if (eContainerFeatureID() != XBNFPackage.TYPED_RULE__GRAMMAR) return null;
		return (Grammar)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGrammar(Grammar newGrammar, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newGrammar, XBNFPackage.TYPED_RULE__GRAMMAR, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGrammar(Grammar newGrammar) {
		if (newGrammar != eInternalContainer() || (eContainerFeatureID() != XBNFPackage.TYPED_RULE__GRAMMAR && newGrammar != null)) {
			if (EcoreUtil.isAncestor(this, newGrammar))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGrammar != null)
				msgs = ((InternalEObject)newGrammar).eInverseAdd(this, XBNFPackage.GRAMMAR__RULES, Grammar.class, msgs);
			msgs = basicSetGrammar(newGrammar, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XBNFPackage.TYPED_RULE__GRAMMAR, newGrammar, newGrammar));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case XBNFPackage.TYPED_RULE__SUBRULES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSubrules()).basicAdd(otherEnd, msgs);
			case XBNFPackage.TYPED_RULE__GRAMMAR:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetGrammar((Grammar)otherEnd, msgs);
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
			case XBNFPackage.TYPED_RULE__SUBRULES:
				return ((InternalEList<?>)getSubrules()).basicRemove(otherEnd, msgs);
			case XBNFPackage.TYPED_RULE__GRAMMAR:
				return basicSetGrammar(null, msgs);
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
			case XBNFPackage.TYPED_RULE__GRAMMAR:
				return eInternalContainer().eInverseRemove(this, XBNFPackage.GRAMMAR__RULES, Grammar.class, msgs);
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
			case XBNFPackage.TYPED_RULE__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case XBNFPackage.TYPED_RULE__SUBRULES:
				return getSubrules();
			case XBNFPackage.TYPED_RULE__GRAMMAR:
				return getGrammar();
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
			case XBNFPackage.TYPED_RULE__TYPE:
				setType((EClassifier)newValue);
				return;
			case XBNFPackage.TYPED_RULE__SUBRULES:
				getSubrules().clear();
				getSubrules().addAll((Collection<? extends UntypedRule>)newValue);
				return;
			case XBNFPackage.TYPED_RULE__GRAMMAR:
				setGrammar((Grammar)newValue);
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
			case XBNFPackage.TYPED_RULE__TYPE:
				setType((EClassifier)null);
				return;
			case XBNFPackage.TYPED_RULE__SUBRULES:
				getSubrules().clear();
				return;
			case XBNFPackage.TYPED_RULE__GRAMMAR:
				setGrammar((Grammar)null);
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
			case XBNFPackage.TYPED_RULE__TYPE:
				return type != null;
			case XBNFPackage.TYPED_RULE__SUBRULES:
				return subrules != null && !subrules.isEmpty();
			case XBNFPackage.TYPED_RULE__GRAMMAR:
				return getGrammar() != null;
		}
		return super.eIsSet(featureID);
	}

} //TypedRuleImpl
