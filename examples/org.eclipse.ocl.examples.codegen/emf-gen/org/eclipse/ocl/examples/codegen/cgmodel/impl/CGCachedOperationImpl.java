/**
 * <copyright>
 *
 * Copyright (c) 2015, 2017 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *
 * </copyright>
 */
package org.eclipse.ocl.examples.codegen.cgmodel.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.util.CGModelVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CG Cached Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGCachedOperationImpl#getFinalOperations <em>Final Operations</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGCachedOperationImpl#getVirtualOperation <em>Virtual Operation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CGCachedOperationImpl extends CGOperationImpl implements CGCachedOperation {
	/**
	 * The cached value of the '{@link #getFinalOperations() <em>Final Operations</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFinalOperations()
	 * @generated
	 * @ordered
	 */
	protected EList<CGCachedOperation> finalOperations;

	/**
	 * The cached value of the '{@link #getVirtualOperation() <em>Virtual Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVirtualOperation()
	 * @generated
	 * @ordered
	 */
	protected CGCachedOperation virtualOperation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CGCachedOperationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CGModelPackage.Literals.CG_CACHED_OPERATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<CGCachedOperation> getFinalOperations() {
		if (finalOperations == null) {
			finalOperations = new EObjectWithInverseResolvingEList<CGCachedOperation>(CGCachedOperation.class, this, CGModelPackage.CG_CACHED_OPERATION__FINAL_OPERATIONS, CGModelPackage.CG_CACHED_OPERATION__VIRTUAL_OPERATION);
		}
		return finalOperations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CGCachedOperation getVirtualOperation() {
		if (virtualOperation != null && virtualOperation.eIsProxy()) {
			InternalEObject oldVirtualOperation = (InternalEObject)virtualOperation;
			virtualOperation = (CGCachedOperation)eResolveProxy(oldVirtualOperation);
			if (virtualOperation != oldVirtualOperation) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CGModelPackage.CG_CACHED_OPERATION__VIRTUAL_OPERATION, oldVirtualOperation, virtualOperation));
			}
		}
		return virtualOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CGCachedOperation basicGetVirtualOperation() {
		return virtualOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVirtualOperation(CGCachedOperation newVirtualOperation, NotificationChain msgs) {
		CGCachedOperation oldVirtualOperation = virtualOperation;
		virtualOperation = newVirtualOperation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CGModelPackage.CG_CACHED_OPERATION__VIRTUAL_OPERATION, oldVirtualOperation, newVirtualOperation);
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
	public void setVirtualOperation(CGCachedOperation newVirtualOperation) {
		if (newVirtualOperation != virtualOperation) {
			NotificationChain msgs = null;
			if (virtualOperation != null)
				msgs = ((InternalEObject)virtualOperation).eInverseRemove(this, CGModelPackage.CG_CACHED_OPERATION__FINAL_OPERATIONS, CGCachedOperation.class, msgs);
			if (newVirtualOperation != null)
				msgs = ((InternalEObject)newVirtualOperation).eInverseAdd(this, CGModelPackage.CG_CACHED_OPERATION__FINAL_OPERATIONS, CGCachedOperation.class, msgs);
			msgs = basicSetVirtualOperation(newVirtualOperation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGModelPackage.CG_CACHED_OPERATION__VIRTUAL_OPERATION, newVirtualOperation, newVirtualOperation));
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
			case CGModelPackage.CG_CACHED_OPERATION__FINAL_OPERATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFinalOperations()).basicAdd(otherEnd, msgs);
			case CGModelPackage.CG_CACHED_OPERATION__VIRTUAL_OPERATION:
				if (virtualOperation != null)
					msgs = ((InternalEObject)virtualOperation).eInverseRemove(this, CGModelPackage.CG_CACHED_OPERATION__FINAL_OPERATIONS, CGCachedOperation.class, msgs);
				return basicSetVirtualOperation((CGCachedOperation)otherEnd, msgs);
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
			case CGModelPackage.CG_CACHED_OPERATION__FINAL_OPERATIONS:
				return ((InternalEList<?>)getFinalOperations()).basicRemove(otherEnd, msgs);
			case CGModelPackage.CG_CACHED_OPERATION__VIRTUAL_OPERATION:
				return basicSetVirtualOperation(null, msgs);
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
			case CGModelPackage.CG_CACHED_OPERATION__FINAL_OPERATIONS:
				return getFinalOperations();
			case CGModelPackage.CG_CACHED_OPERATION__VIRTUAL_OPERATION:
				if (resolve) return getVirtualOperation();
				return basicGetVirtualOperation();
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
			case CGModelPackage.CG_CACHED_OPERATION__FINAL_OPERATIONS:
				getFinalOperations().clear();
				getFinalOperations().addAll((Collection<? extends CGCachedOperation>)newValue);
				return;
			case CGModelPackage.CG_CACHED_OPERATION__VIRTUAL_OPERATION:
				setVirtualOperation((CGCachedOperation)newValue);
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
			case CGModelPackage.CG_CACHED_OPERATION__FINAL_OPERATIONS:
				getFinalOperations().clear();
				return;
			case CGModelPackage.CG_CACHED_OPERATION__VIRTUAL_OPERATION:
				setVirtualOperation((CGCachedOperation)null);
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
			case CGModelPackage.CG_CACHED_OPERATION__FINAL_OPERATIONS:
				return finalOperations != null && !finalOperations.isEmpty();
			case CGModelPackage.CG_CACHED_OPERATION__VIRTUAL_OPERATION:
				return virtualOperation != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull CGModelVisitor<R> visitor) {
		return visitor.visitCGCachedOperation(this);
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
	public boolean isEcore() {
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

} //CGCachedOperationImpl
