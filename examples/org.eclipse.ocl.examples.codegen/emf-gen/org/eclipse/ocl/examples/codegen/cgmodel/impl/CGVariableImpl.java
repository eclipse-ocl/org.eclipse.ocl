/*******************************************************************************
 * Copyright (c) 2013, 2015 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.cgmodel.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CG Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGVariableImpl#getInit <em>Init</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGVariableImpl#isCacheNeeded <em>Cache Needed</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class CGVariableImpl extends CGValuedElementImpl implements CGVariable {
	/**
	 * The cached value of the '{@link #getInit() <em>Init</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInit()
	 * @generated
	 * @ordered
	 */
	protected CGValuedElement init;

	/**
	 * The default value of the '{@link #isCacheNeeded() <em>Cache Needed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCacheNeeded()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CACHE_NEEDED_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isCacheNeeded() <em>Cache Needed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCacheNeeded()
	 * @generated
	 * @ordered
	 */
	protected boolean cacheNeeded = CACHE_NEEDED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CGVariableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CGModelPackage.Literals.CG_VARIABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CGValuedElement getInit() {
		return init;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInit(CGValuedElement newInit, NotificationChain msgs) {
		CGValuedElement oldInit = init;
		init = newInit;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CGModelPackage.CG_VARIABLE__INIT, oldInit, newInit);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInit(CGValuedElement newInit) {
		if (newInit != init) {
			NotificationChain msgs = null;
			if (init != null)
				msgs = ((InternalEObject)init).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CGModelPackage.CG_VARIABLE__INIT, null, msgs);
			if (newInit != null)
				msgs = ((InternalEObject)newInit).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CGModelPackage.CG_VARIABLE__INIT, null, msgs);
			msgs = basicSetInit(newInit, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGModelPackage.CG_VARIABLE__INIT, newInit, newInit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isCacheNeeded() {
		return cacheNeeded;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCacheNeeded(boolean newCacheNeeded) {
		boolean oldCacheNeeded = cacheNeeded;
		cacheNeeded = newCacheNeeded;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGModelPackage.CG_VARIABLE__CACHE_NEEDED, oldCacheNeeded, cacheNeeded));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return super.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CGModelPackage.CG_VARIABLE__INIT:
				return basicSetInit(null, msgs);
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
			case CGModelPackage.CG_VARIABLE__INIT:
				return getInit();
			case CGModelPackage.CG_VARIABLE__CACHE_NEEDED:
				return isCacheNeeded();
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
			case CGModelPackage.CG_VARIABLE__INIT:
				setInit((CGValuedElement)newValue);
				return;
			case CGModelPackage.CG_VARIABLE__CACHE_NEEDED:
				setCacheNeeded((Boolean)newValue);
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
			case CGModelPackage.CG_VARIABLE__INIT:
				setInit((CGValuedElement)null);
				return;
			case CGModelPackage.CG_VARIABLE__CACHE_NEEDED:
				setCacheNeeded(CACHE_NEEDED_EDEFAULT);
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
			case CGModelPackage.CG_VARIABLE__INIT:
				return init != null;
			case CGModelPackage.CG_VARIABLE__CACHE_NEEDED:
				return cacheNeeded != CACHE_NEEDED_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable EClassifier getEcoreClassifier() {
		return init != null ? init.getEcoreClassifier() : null;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable CGInvalid getInvalidValue() {
		return !nonInvalid ? super.getInvalidValue() : null;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @NonNull CGValuedElement getNamedValue() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @NonNull CGValuedElement getReferredValue() {
		return init != null ? init : this;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @NonNull CGValuedElement getSourceValue() {
		return init != null ? init.getSourceValue() : this;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @NonNull CGValuedElement getTypedValue() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isBoxed() {
		return (init != null) && init.isBoxed();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isCommonable() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isEcore() {
		return (init != null) && init.isEcore();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable Boolean isEquivalentTo(@NonNull CGValuedElement thatValue) {
		return init != null ? thatValue.isEquivalentTo(init) : super.isEquivalentTo(thatValue);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable Boolean isEquivalentToInternal(@NonNull CGValuedElement thatValue) {
		return init != null ? thatValue.isEquivalentToInternal(init) : null;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isNonInvalid() {
		return nonInvalid || super.isNonInvalid();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isNonNull() {
		return nonNull || super.isNonNull();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isNull() {
		return !nonNull && super.isNull();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isUnboxed() {
		return (init != null) && init.isUnboxed();
	}

	/**
	 * @generated
	 */
	protected boolean nonInvalid = false;

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public void setNonInvalid() {
		nonInvalid = true;
	}

	/**
	 * @generated
	 */
	protected boolean nonNull = false;

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public void setNonNull() {
		nonNull = true;
	}

} //CGVariableImpl
