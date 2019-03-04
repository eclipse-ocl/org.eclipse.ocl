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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CG Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGOperationImpl#getPreconditions <em>Preconditions</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGOperationImpl#getPostconditions <em>Postconditions</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGOperationImpl#getContainingClass <em>Containing Class</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class CGOperationImpl extends CGCallableImpl implements CGOperation {
	/**
	 * The number of structural features of the '<em>CG Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CG_OPERATION_FEATURE_COUNT = CGCallableImpl.CG_CALLABLE_FEATURE_COUNT + 3;

	/**
	 * The cached value of the '{@link #getPreconditions() <em>Preconditions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreconditions()
	 * @generated
	 * @ordered
	 */
	protected EList<CGConstraint> preconditions;

	/**
	 * The cached value of the '{@link #getPostconditions() <em>Postconditions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPostconditions()
	 * @generated
	 * @ordered
	 */
	protected EList<CGConstraint> postconditions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CGOperationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CGModelPackage.Literals.CG_OPERATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("null")
	@Override
	public @NonNull List<CGConstraint> getPreconditions() {
		if (preconditions == null) {
			preconditions = new EObjectContainmentEList<CGConstraint>(CGConstraint.class, this, CGCallableImpl.CG_CALLABLE_FEATURE_COUNT + 0);
		}
		return preconditions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("null")
	@Override
	public @NonNull List<CGConstraint> getPostconditions() {
		if (postconditions == null) {
			postconditions = new EObjectContainmentEList<CGConstraint>(CGConstraint.class, this, CGCallableImpl.CG_CALLABLE_FEATURE_COUNT + 1);
		}
		return postconditions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CGClass getContainingClass() {
		if (eContainerFeatureID() != (CGCallableImpl.CG_CALLABLE_FEATURE_COUNT + 2)) return null;
		return (CGClass)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContainingClass(CGClass newContainingClass, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newContainingClass, CGCallableImpl.CG_CALLABLE_FEATURE_COUNT + 2, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setContainingClass(CGClass newContainingClass) {
		if (newContainingClass != eInternalContainer() || (eContainerFeatureID() != (CGCallableImpl.CG_CALLABLE_FEATURE_COUNT + 2) && newContainingClass != null)) {
			if (EcoreUtil.isAncestor(this, newContainingClass))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newContainingClass != null)
				msgs = ((InternalEObject)newContainingClass).eInverseAdd(this, CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 1, CGClass.class, msgs);
			msgs = basicSetContainingClass(newContainingClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGCallableImpl.CG_CALLABLE_FEATURE_COUNT + 2, newContainingClass, newContainingClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CGCallableImpl.CG_CALLABLE_FEATURE_COUNT + 2:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetContainingClass((CGClass)otherEnd, msgs);
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
			case CGCallableImpl.CG_CALLABLE_FEATURE_COUNT + 0:
				return ((InternalEList<?>)getPreconditions()).basicRemove(otherEnd, msgs);
			case CGCallableImpl.CG_CALLABLE_FEATURE_COUNT + 1:
				return ((InternalEList<?>)getPostconditions()).basicRemove(otherEnd, msgs);
			case CGCallableImpl.CG_CALLABLE_FEATURE_COUNT + 2:
				return basicSetContainingClass(null, msgs);
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
			case CGCallableImpl.CG_CALLABLE_FEATURE_COUNT + 2:
				return eInternalContainer().eInverseRemove(this, CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 1, CGClass.class, msgs);
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
			case CGCallableImpl.CG_CALLABLE_FEATURE_COUNT + 0:
				return getPreconditions();
			case CGCallableImpl.CG_CALLABLE_FEATURE_COUNT + 1:
				return getPostconditions();
			case CGCallableImpl.CG_CALLABLE_FEATURE_COUNT + 2:
				return getContainingClass();
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
			case CGCallableImpl.CG_CALLABLE_FEATURE_COUNT + 0:
				getPreconditions().clear();
				getPreconditions().addAll((Collection<? extends CGConstraint>)newValue);
				return;
			case CGCallableImpl.CG_CALLABLE_FEATURE_COUNT + 1:
				getPostconditions().clear();
				getPostconditions().addAll((Collection<? extends CGConstraint>)newValue);
				return;
			case CGCallableImpl.CG_CALLABLE_FEATURE_COUNT + 2:
				setContainingClass((CGClass)newValue);
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
			case CGCallableImpl.CG_CALLABLE_FEATURE_COUNT + 0:
				getPreconditions().clear();
				return;
			case CGCallableImpl.CG_CALLABLE_FEATURE_COUNT + 1:
				getPostconditions().clear();
				return;
			case CGCallableImpl.CG_CALLABLE_FEATURE_COUNT + 2:
				setContainingClass((CGClass)null);
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
			case CGCallableImpl.CG_CALLABLE_FEATURE_COUNT + 0:
				return preconditions != null && !preconditions.isEmpty();
			case CGCallableImpl.CG_CALLABLE_FEATURE_COUNT + 1:
				return postconditions != null && !postconditions.isEmpty();
			case CGCallableImpl.CG_CALLABLE_FEATURE_COUNT + 2:
				return getContainingClass() != null;
		}
		return super.eIsSet(featureID);
	}

} //CGOperationImpl
