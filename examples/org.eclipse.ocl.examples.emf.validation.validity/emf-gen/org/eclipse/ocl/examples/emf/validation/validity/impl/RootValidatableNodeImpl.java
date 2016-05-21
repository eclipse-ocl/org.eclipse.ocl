/*******************************************************************************
 * Copyright (c) 2013, 2016 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *	E.D.Willink (CEA LIST) - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.emf.validation.validity.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ocl.examples.emf.validation.validity.RootNode;
import org.eclipse.ocl.examples.emf.validation.validity.RootValidatableNode;
import org.eclipse.ocl.examples.emf.validation.validity.ValidityPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Root ValidatableNode</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.emf.validation.validity.impl.RootValidatableNodeImpl#getRootNode <em>Root Node</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RootValidatableNodeImpl extends ValidatableNodeImpl implements RootValidatableNode {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RootValidatableNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ValidityPackage.Literals.ROOT_VALIDATABLE_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RootNode getRootNode() {
		if (eContainerFeatureID() != ValidityPackage.ROOT_VALIDATABLE_NODE__ROOT_NODE) return null;
		return (RootNode)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRootNode(RootNode newRootNode, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newRootNode, ValidityPackage.ROOT_VALIDATABLE_NODE__ROOT_NODE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRootNode(RootNode newRootNode) {
		if (newRootNode != eInternalContainer() || (eContainerFeatureID() != ValidityPackage.ROOT_VALIDATABLE_NODE__ROOT_NODE && newRootNode != null)) {
			if (EcoreUtil.isAncestor(this, newRootNode))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newRootNode != null)
				msgs = ((InternalEObject)newRootNode).eInverseAdd(this, ValidityPackage.ROOT_NODE__VALIDATABLE_NODES, RootNode.class, msgs);
			msgs = basicSetRootNode(newRootNode, msgs);
			if (msgs != null) msgs.dispatch();
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ValidityPackage.ROOT_VALIDATABLE_NODE__ROOT_NODE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetRootNode((RootNode)otherEnd, msgs);
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
			case ValidityPackage.ROOT_VALIDATABLE_NODE__ROOT_NODE:
				return basicSetRootNode(null, msgs);
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
			case ValidityPackage.ROOT_VALIDATABLE_NODE__ROOT_NODE:
				return eInternalContainer().eInverseRemove(this, ValidityPackage.ROOT_NODE__VALIDATABLE_NODES, RootNode.class, msgs);
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
			case ValidityPackage.ROOT_VALIDATABLE_NODE__ROOT_NODE:
				return getRootNode();
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
			case ValidityPackage.ROOT_VALIDATABLE_NODE__ROOT_NODE:
				setRootNode((RootNode)newValue);
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
			case ValidityPackage.ROOT_VALIDATABLE_NODE__ROOT_NODE:
				setRootNode((RootNode)null);
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
			case ValidityPackage.ROOT_VALIDATABLE_NODE__ROOT_NODE:
				return getRootNode() != null;
		}
		return super.eIsSet(featureID);
	}

} //RootValidatableNodeImpl
