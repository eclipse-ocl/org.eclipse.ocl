/**
 * <copyright>
 * 
 * Copyright (c) 2013 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.ocl.examples.codegen.cgmodel.impl;

import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElementId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.util.CGModelVisitor;
import org.eclipse.ocl.examples.codegen.cse.AbstractPlace;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CG Executor Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGExecutorOperationImpl#getUnderlyingOperationId <em>Underlying Operation Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CGExecutorOperationImpl extends CGValuedElementImpl implements CGExecutorOperation {
	/**
	 * The cached value of the '{@link #getUnderlyingOperationId() <em>Underlying Operation Id</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnderlyingOperationId()
	 * @generated
	 * @ordered
	 */
	protected CGElementId underlyingOperationId;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CGExecutorOperationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CGModelPackage.Literals.CG_EXECUTOR_OPERATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CGElementId getUnderlyingOperationId() {
		return underlyingOperationId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnderlyingOperationId(CGElementId newUnderlyingOperationId) {
		CGElementId oldUnderlyingOperationId = underlyingOperationId;
		underlyingOperationId = newUnderlyingOperationId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGModelPackage.CG_EXECUTOR_OPERATION__UNDERLYING_OPERATION_ID, oldUnderlyingOperationId, underlyingOperationId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CGModelPackage.CG_EXECUTOR_OPERATION__UNDERLYING_OPERATION_ID:
				return getUnderlyingOperationId();
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
			case CGModelPackage.CG_EXECUTOR_OPERATION__UNDERLYING_OPERATION_ID:
				setUnderlyingOperationId((CGElementId)newValue);
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
			case CGModelPackage.CG_EXECUTOR_OPERATION__UNDERLYING_OPERATION_ID:
				setUnderlyingOperationId((CGElementId)null);
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
			case CGModelPackage.CG_EXECUTOR_OPERATION__UNDERLYING_OPERATION_ID:
				return underlyingOperationId != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable <R> R accept(@NonNull CGModelVisitor<R> visitor) {
		return visitor.visitCGExecutorOperation(this);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable AbstractPlace getPlace(@NonNull Map<CGElement,AbstractPlace> element2place) {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @NonNull CGValuedElement getValue() {
		return this;
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
	public boolean isContext() {
		return true;
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
	public boolean isUnboxed() {
		return false;
	}

} //CGExecutorOperationImpl
