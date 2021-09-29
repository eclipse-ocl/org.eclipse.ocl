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

import java.util.Collection;
import java.util.regex.Pattern;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.idioms.Idiom;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage;
import org.eclipse.ocl.examples.xtext.idioms.SubIdiom;
import org.eclipse.ocl.examples.xtext.serializer.SerializationUtils;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Idiom</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.IdiomImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.IdiomImpl#getForEPackage <em>For EPackage</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.IdiomImpl#getForEClass <em>For EClass</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.IdiomImpl#getInRuleRegex <em>In Rule Regex</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.IdiomImpl#isMixin <em>Mixin</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.IdiomImpl#getOwnedSubIdioms <em>Owned Sub Idioms</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IdiomImpl
		extends IdiomsElementImpl
		implements Idiom {

	/**
	 * The number of structural features of the '<em>Idiom</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int IDIOM_FEATURE_COUNT = IdiomsElementImpl.IDIOMS_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getForEPackage() <em>For EPackage</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForEPackage()
	 * @generated
	 * @ordered
	 */
	protected EPackage forEPackage;

	/**
	 * The cached value of the '{@link #getForEClass() <em>For EClass</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForEClass()
	 * @generated
	 * @ordered
	 */
	protected EClass forEClass;

	/**
	 * The default value of the '{@link #getInRuleRegex() <em>In Rule Regex</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInRuleRegex()
	 * @generated
	 * @ordered
	 */
	protected static final String IN_RULE_REGEX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInRuleRegex() <em>In Rule Regex</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInRuleRegex()
	 * @generated
	 * @ordered
	 */
	protected String inRuleRegex = IN_RULE_REGEX_EDEFAULT;

	/**
	 * The default value of the '{@link #isMixin() <em>Mixin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMixin()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MIXIN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMixin() <em>Mixin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMixin()
	 * @generated
	 * @ordered
	 */
	protected boolean mixin = MIXIN_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOwnedSubIdioms() <em>Owned Sub Idioms</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSubIdioms()
	 * @generated
	 * @ordered
	 */
	protected EList<SubIdiom> ownedSubIdioms;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IdiomImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdiomsPackage.Literals.IDIOM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 0, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EPackage getForEPackage() {
		if (forEPackage != null && forEPackage.eIsProxy())
		{
			InternalEObject oldForEPackage = (InternalEObject)forEPackage;
			forEPackage = (EPackage)eResolveProxy(oldForEPackage);
			if (forEPackage != oldForEPackage)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 1, oldForEPackage, forEPackage));
			}
		}
		return forEPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EPackage basicGetForEPackage() {
		return forEPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setForEPackage(EPackage newForEPackage) {
		EPackage oldForEPackage = forEPackage;
		forEPackage = newForEPackage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 1, oldForEPackage, forEPackage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getForEClass() {
		if (forEClass != null && forEClass.eIsProxy())
		{
			InternalEObject oldForEClass = (InternalEObject)forEClass;
			forEClass = (EClass)eResolveProxy(oldForEClass);
			if (forEClass != oldForEClass)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 2, oldForEClass, forEClass));
			}
		}
		return forEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass basicGetForEClass() {
		return forEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setForEClass(EClass newForEClass) {
		EClass oldForEClass = forEClass;
		forEClass = newForEClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 2, oldForEClass, forEClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getInRuleRegex() {
		return inRuleRegex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInRuleRegex(String newInRuleRegex) {
		String oldInRuleRegex = inRuleRegex;
		inRuleRegex = newInRuleRegex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 3, oldInRuleRegex, inRuleRegex));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isMixin() {
		return mixin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMixin(boolean newMixin) {
		boolean oldMixin = mixin;
		mixin = newMixin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 4, oldMixin, mixin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<SubIdiom> getOwnedSubIdioms() {
		if (ownedSubIdioms == null)
		{
			ownedSubIdioms = new EObjectContainmentWithInverseEList<SubIdiom>(SubIdiom.class, this, 5, 3);
		}
		return ownedSubIdioms;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID)
		{
			case 5:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedSubIdioms()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID)
		{
			case 5:
				return ((InternalEList<?>)getOwnedSubIdioms()).basicRemove(otherEnd, msgs);
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
		switch (featureID)
		{
			case 0:
				return getName();
			case 1:
				if (resolve) return getForEPackage();
				return basicGetForEPackage();
			case 2:
				if (resolve) return getForEClass();
				return basicGetForEClass();
			case 3:
				return getInRuleRegex();
			case 4:
				return isMixin();
			case 5:
				return getOwnedSubIdioms();
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
		switch (featureID)
		{
			case 0:
				setName((String)newValue);
				return;
			case 1:
				setForEPackage((EPackage)newValue);
				return;
			case 2:
				setForEClass((EClass)newValue);
				return;
			case 3:
				setInRuleRegex((String)newValue);
				return;
			case 4:
				setMixin((Boolean)newValue);
				return;
			case 5:
				getOwnedSubIdioms().clear();
				getOwnedSubIdioms().addAll((Collection<? extends SubIdiom>)newValue);
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
		switch (featureID)
		{
			case 0:
				setName(NAME_EDEFAULT);
				return;
			case 1:
				setForEPackage((EPackage)null);
				return;
			case 2:
				setForEClass((EClass)null);
				return;
			case 3:
				setInRuleRegex(IN_RULE_REGEX_EDEFAULT);
				return;
			case 4:
				setMixin(MIXIN_EDEFAULT);
				return;
			case 5:
				getOwnedSubIdioms().clear();
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
		switch (featureID)
		{
			case 0:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case 1:
				return forEPackage != null;
			case 2:
				return forEClass != null;
			case 3:
				return IN_RULE_REGEX_EDEFAULT == null ? inRuleRegex != null : !IN_RULE_REGEX_EDEFAULT.equals(inRuleRegex);
			case 4:
				return mixin != MIXIN_EDEFAULT;
			case 5:
				return ownedSubIdioms != null && !ownedSubIdioms.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	public @NonNull SubIdiom getSubidiom(int subIdiomIndex) {
		return SerializationUtils
			.nonNullState(getOwnedSubIdioms().get(subIdiomIndex));
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(name);
		if (forEClass != null) {
			s.append(" for ");
			s.append(forEClass.getEPackage().getName());
			s.append("::");
			s.append(forEClass.getName());
		}
		if (inRuleRegex != null) {
			s.append(" in ");
			s.append(inRuleRegex);
		}
		s.append("{");
		boolean isFirst = true;
		for (SubIdiom subIdiom : getOwnedSubIdioms()) {
			if (!isFirst) {
				s.append(",");
			}
			s.append(subIdiom.toString());
			isFirst = false;
		}
		s.append("}");
		return s.toString();
	}

	private @Nullable Pattern regexPattern = null;

	@Override
	public @Nullable Pattern getRegexPattern() {
		if ((regexPattern == null) && (inRuleRegex != null)) {
			regexPattern = Pattern.compile(inRuleRegex);
		}
		return regexPattern;
	}
} //IdiomImpl
