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

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
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
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGCachedOperationImpl#getVirtualOperations <em>Virtual Operations</em>}</li>
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
	 * The cached value of the '{@link #getVirtualOperations() <em>Virtual Operations</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVirtualOperations()
	 * @generated
	 * @ordered
	 */
	protected EList<CGCachedOperation> virtualOperations;

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
			finalOperations = new EObjectWithInverseResolvingEList.ManyInverse<CGCachedOperation>(CGCachedOperation.class, this, CGModelPackage.CG_CACHED_OPERATION__FINAL_OPERATIONS, CGModelPackage.CG_CACHED_OPERATION__VIRTUAL_OPERATIONS);
		}
		return finalOperations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<CGCachedOperation> getVirtualOperations() {
		if (virtualOperations == null) {
			virtualOperations = new EObjectWithInverseResolvingEList.ManyInverse<CGCachedOperation>(CGCachedOperation.class, this, CGModelPackage.CG_CACHED_OPERATION__VIRTUAL_OPERATIONS, CGModelPackage.CG_CACHED_OPERATION__FINAL_OPERATIONS);
		}
		return virtualOperations;
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
			case CGModelPackage.CG_CACHED_OPERATION__VIRTUAL_OPERATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getVirtualOperations()).basicAdd(otherEnd, msgs);
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
			case CGModelPackage.CG_CACHED_OPERATION__VIRTUAL_OPERATIONS:
				return ((InternalEList<?>)getVirtualOperations()).basicRemove(otherEnd, msgs);
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
			case CGModelPackage.CG_CACHED_OPERATION__VIRTUAL_OPERATIONS:
				return getVirtualOperations();
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
			case CGModelPackage.CG_CACHED_OPERATION__VIRTUAL_OPERATIONS:
				getVirtualOperations().clear();
				getVirtualOperations().addAll((Collection<? extends CGCachedOperation>)newValue);
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
			case CGModelPackage.CG_CACHED_OPERATION__VIRTUAL_OPERATIONS:
				getVirtualOperations().clear();
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
			case CGModelPackage.CG_CACHED_OPERATION__VIRTUAL_OPERATIONS:
				return virtualOperations != null && !virtualOperations.isEmpty();
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
