/*******************************************************************************
 * Copyright (c) 2013, 2019 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.cgmodel.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.util.CGModelVisitor;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.TypeId;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CG Executor Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGExecutorTypeImpl#getUnderlyingTypeId <em>Underlying Type Id</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CGExecutorTypeImpl extends CGValuedElementImpl implements CGExecutorType {
	/**
	 * The number of structural features of the '<em>CG Executor Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CG_EXECUTOR_TYPE_FEATURE_COUNT = CGValuedElementImpl.CG_VALUED_ELEMENT_FEATURE_COUNT + 1;
	/**
	 * The cached value of the '{@link #getUnderlyingTypeId() <em>Underlying Type Id</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnderlyingTypeId()
	 * @generated
	 * @ordered
	 */
	protected CGTypeId underlyingTypeId;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CGExecutorTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CGModelPackage.Literals.CG_EXECUTOR_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CGTypeId getUnderlyingTypeId() {
		return underlyingTypeId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUnderlyingTypeId(CGTypeId newUnderlyingTypeId) {
		CGTypeId oldUnderlyingTypeId = underlyingTypeId;
		underlyingTypeId = newUnderlyingTypeId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 6, oldUnderlyingTypeId, underlyingTypeId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case 6:
				return getUnderlyingTypeId();
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
			case 6:
				setUnderlyingTypeId((CGTypeId)newValue);
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
			case 6:
				setUnderlyingTypeId((CGTypeId)null);
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
			case 6:
				return underlyingTypeId != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull CGModelVisitor<R> visitor) {
		return visitor.visitCGExecutorType(this);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable TypeId getASTypeId() {
		return ast instanceof Type ? ((Type) ast).getTypeId() : null;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable CGInvalid getInvalidValue() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isBoxed() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isConstant() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isEcore() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable Boolean isEquivalentToInternal(@NonNull CGValuedElement thatValue) {
		if (!thatValue.isNonInvalid()) {
			return null;
		}
		CGValuedElement value = thatValue.getNamedValue();
		if (this == value) {
			return Boolean.TRUE;
		}
		if (!value.isConstant()) {
			return null;
		}
		else if (value instanceof CGExecutorType) {
			return getASTypeId() == ((CGExecutorType)thatValue).getASTypeId();
		}
		else {
			return Boolean.FALSE;
		}
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isGlobal() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isNonInvalid() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isNonNull() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isNull() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isUnboxed() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isUncommonable() {
		return false;
	}

} //CGExecutorTypeImpl
