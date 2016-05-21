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

import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.emf.validation.validity.ValidatableNode;
import org.eclipse.ocl.examples.emf.validation.validity.ValidityPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ValidatableNode</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.emf.validation.validity.impl.ValidatableNodeImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.emf.validation.validity.impl.ValidatableNodeImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.emf.validation.validity.impl.ValidatableNodeImpl#getConstrainedObject <em>Constrained Object</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ValidatableNodeImpl extends AbstractNodeImpl implements ValidatableNode {
	/**
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<ValidatableNode> children;

	/**
	 * The cached value of the '{@link #getConstrainedObject() <em>Constrained Object</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstrainedObject()
	 * @generated
	 * @ordered
	 */
	protected EObject constrainedObject;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ValidatableNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ValidityPackage.Literals.VALIDATABLE_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValidatableNode getParent() {
		if (eContainerFeatureID() != ValidityPackage.VALIDATABLE_NODE__PARENT) return null;
		return (ValidatableNode)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParent(ValidatableNode newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParent, ValidityPackage.VALIDATABLE_NODE__PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(ValidatableNode newParent) {
		if (newParent != eInternalContainer() || (eContainerFeatureID() != ValidityPackage.VALIDATABLE_NODE__PARENT && newParent != null)) {
			if (EcoreUtil.isAncestor(this, newParent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParent != null)
				msgs = ((InternalEObject)newParent).eInverseAdd(this, ValidityPackage.VALIDATABLE_NODE__CHILDREN, ValidatableNode.class, msgs);
			msgs = basicSetParent(newParent, msgs);
			if (msgs != null) msgs.dispatch();
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT // Bug 414352 workaround
	 */
	@SuppressWarnings("null")
	public @NonNull EList<ValidatableNode> getChildren() {
		if (children == null) {
			children = new EObjectContainmentWithInverseEList<ValidatableNode>(ValidatableNode.class, this, ValidityPackage.VALIDATABLE_NODE__CHILDREN, ValidityPackage.VALIDATABLE_NODE__PARENT);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getConstrainedObject() {
		if (constrainedObject != null && constrainedObject.eIsProxy()) {
			InternalEObject oldConstrainedObject = (InternalEObject)constrainedObject;
			constrainedObject = eResolveProxy(oldConstrainedObject);
			if (constrainedObject != oldConstrainedObject) {
			}
		}
		return constrainedObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject basicGetConstrainedObject() {
		return constrainedObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstrainedObject(EObject newConstrainedObject) {
		constrainedObject = newConstrainedObject;
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
			case ValidityPackage.VALIDATABLE_NODE__PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParent((ValidatableNode)otherEnd, msgs);
			case ValidityPackage.VALIDATABLE_NODE__CHILDREN:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getChildren()).basicAdd(otherEnd, msgs);
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
			case ValidityPackage.VALIDATABLE_NODE__PARENT:
				return basicSetParent(null, msgs);
			case ValidityPackage.VALIDATABLE_NODE__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
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
			case ValidityPackage.VALIDATABLE_NODE__PARENT:
				return eInternalContainer().eInverseRemove(this, ValidityPackage.VALIDATABLE_NODE__CHILDREN, ValidatableNode.class, msgs);
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
			case ValidityPackage.VALIDATABLE_NODE__PARENT:
				return getParent();
			case ValidityPackage.VALIDATABLE_NODE__CHILDREN:
				return getChildren();
			case ValidityPackage.VALIDATABLE_NODE__CONSTRAINED_OBJECT:
				if (resolve) return getConstrainedObject();
				return basicGetConstrainedObject();
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
			case ValidityPackage.VALIDATABLE_NODE__PARENT:
				setParent((ValidatableNode)newValue);
				return;
			case ValidityPackage.VALIDATABLE_NODE__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends ValidatableNode>)newValue);
				return;
			case ValidityPackage.VALIDATABLE_NODE__CONSTRAINED_OBJECT:
				setConstrainedObject((EObject)newValue);
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
			case ValidityPackage.VALIDATABLE_NODE__PARENT:
				setParent((ValidatableNode)null);
				return;
			case ValidityPackage.VALIDATABLE_NODE__CHILDREN:
				getChildren().clear();
				return;
			case ValidityPackage.VALIDATABLE_NODE__CONSTRAINED_OBJECT:
				setConstrainedObject((EObject)null);
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
			case ValidityPackage.VALIDATABLE_NODE__PARENT:
				return getParent() != null;
			case ValidityPackage.VALIDATABLE_NODE__CHILDREN:
				return children != null && !children.isEmpty();
			case ValidityPackage.VALIDATABLE_NODE__CONSTRAINED_OBJECT:
				return constrainedObject != null;
		}
		return super.eIsSet(featureID);
	}
} //ValidatableNodeImpl
