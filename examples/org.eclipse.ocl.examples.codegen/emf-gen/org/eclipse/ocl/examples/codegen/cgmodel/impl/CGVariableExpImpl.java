/*******************************************************************************
 * Copyright (c) 2013, 2016 CEA LIST and others.
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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.cgmodel.util.CGModelVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CG Variable Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGVariableExpImpl#getReferredVariable <em>Referred Variable</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CGVariableExpImpl extends CGValuedElementImpl implements CGVariableExp {
	/**
	 * The cached value of the '{@link #getReferredVariable() <em>Referred Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredVariable()
	 * @generated
	 * @ordered
	 */
	protected CGVariable referredVariable;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CGVariableExpImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CGModelPackage.Literals.CG_VARIABLE_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CGVariable getReferredVariable() {
		return referredVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReferredVariable(CGVariable newReferredVariable) {
		CGVariable oldReferredVariable = referredVariable;
		referredVariable = newReferredVariable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGModelPackage.CG_VARIABLE_EXP__REFERRED_VARIABLE, oldReferredVariable, referredVariable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CGModelPackage.CG_VARIABLE_EXP__REFERRED_VARIABLE:
				return getReferredVariable();
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
			case CGModelPackage.CG_VARIABLE_EXP__REFERRED_VARIABLE:
				setReferredVariable((CGVariable)newValue);
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
			case CGModelPackage.CG_VARIABLE_EXP__REFERRED_VARIABLE:
				setReferredVariable((CGVariable)null);
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
			case CGModelPackage.CG_VARIABLE_EXP__REFERRED_VARIABLE:
				return referredVariable != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull CGModelVisitor<R> visitor) {
		return visitor.visitCGVariableExp(this);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable EClassifier getEcoreClassifier() {
		return referredVariable != null ? referredVariable.getEcoreClassifier() : null;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @NonNull CGValuedElement getSourceValue() {
		return referredVariable != null ? referredVariable.getSourceValue() : this;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @NonNull CGValuedElement getNamedValue() {
		return referredVariable != null ? referredVariable.getNamedValue() : this;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @NonNull CGValuedElement getReferredValue() {
		return referredVariable != null ? referredVariable : this;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @NonNull CGValuedElement getTypedValue() {
		return referredVariable != null ? referredVariable.getTypedValue() : this;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable String getValueName() {
		return referredVariable != null ? referredVariable.getValueName() : null;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isBoxed() {
		return (referredVariable != null) && referredVariable.isBoxed();
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
		return (referredVariable != null) && referredVariable.isEcore();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable Boolean isEquivalentTo(@NonNull CGValuedElement thatValue) {
		return referredVariable != null ? thatValue.isEquivalentTo(referredVariable) : super.isEquivalentTo(thatValue);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable Boolean isEquivalentToInternal(@NonNull CGValuedElement thatValue) {
		return referredVariable != null ? thatValue.isEquivalentToInternal(referredVariable) : null;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isUnboxed() {
		return (referredVariable != null) && referredVariable.isUnboxed();
	}

} //CGVariableExpImpl
