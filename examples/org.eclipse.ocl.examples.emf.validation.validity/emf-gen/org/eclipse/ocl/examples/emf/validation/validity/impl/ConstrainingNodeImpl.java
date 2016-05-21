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
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.emf.validation.validity.ConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.validity.ValidityPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ConstrainingNode</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.emf.validation.validity.impl.ConstrainingNodeImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.emf.validation.validity.impl.ConstrainingNodeImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.emf.validation.validity.impl.ConstrainingNodeImpl#getConstrainingObject <em>Constraining Object</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConstrainingNodeImpl extends AbstractNodeImpl implements ConstrainingNode {
	/**
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<ConstrainingNode> children;

	/**
	 * The default value of the '{@link #getConstrainingObject() <em>Constraining Object</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstrainingObject()
	 * @generated
	 * @ordered
	 */
	protected static final Object CONSTRAINING_OBJECT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConstrainingObject() <em>Constraining Object</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstrainingObject()
	 * @generated
	 * @ordered
	 */
	protected Object constrainingObject = CONSTRAINING_OBJECT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConstrainingNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ValidityPackage.Literals.CONSTRAINING_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstrainingNode getParent() {
		if (eContainerFeatureID() != ValidityPackage.CONSTRAINING_NODE__PARENT) return null;
		return (ConstrainingNode)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParent(ConstrainingNode newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParent, ValidityPackage.CONSTRAINING_NODE__PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(ConstrainingNode newParent) {
		if (newParent != eInternalContainer() || (eContainerFeatureID() != ValidityPackage.CONSTRAINING_NODE__PARENT && newParent != null)) {
			if (EcoreUtil.isAncestor(this, newParent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParent != null)
				msgs = ((InternalEObject)newParent).eInverseAdd(this, ValidityPackage.CONSTRAINING_NODE__CHILDREN, ConstrainingNode.class, msgs);
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
	public @NonNull EList<ConstrainingNode> getChildren() {
		if (children == null) {
			children = new EObjectContainmentWithInverseEList<ConstrainingNode>(ConstrainingNode.class, this, ValidityPackage.CONSTRAINING_NODE__CHILDREN, ValidityPackage.CONSTRAINING_NODE__PARENT);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getConstrainingObject() {
		return constrainingObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstrainingObject(Object newConstrainingObject) {
		constrainingObject = newConstrainingObject;
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
			case ValidityPackage.CONSTRAINING_NODE__PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParent((ConstrainingNode)otherEnd, msgs);
			case ValidityPackage.CONSTRAINING_NODE__CHILDREN:
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
			case ValidityPackage.CONSTRAINING_NODE__PARENT:
				return basicSetParent(null, msgs);
			case ValidityPackage.CONSTRAINING_NODE__CHILDREN:
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
			case ValidityPackage.CONSTRAINING_NODE__PARENT:
				return eInternalContainer().eInverseRemove(this, ValidityPackage.CONSTRAINING_NODE__CHILDREN, ConstrainingNode.class, msgs);
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
			case ValidityPackage.CONSTRAINING_NODE__PARENT:
				return getParent();
			case ValidityPackage.CONSTRAINING_NODE__CHILDREN:
				return getChildren();
			case ValidityPackage.CONSTRAINING_NODE__CONSTRAINING_OBJECT:
				return getConstrainingObject();
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
			case ValidityPackage.CONSTRAINING_NODE__PARENT:
				setParent((ConstrainingNode)newValue);
				return;
			case ValidityPackage.CONSTRAINING_NODE__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends ConstrainingNode>)newValue);
				return;
			case ValidityPackage.CONSTRAINING_NODE__CONSTRAINING_OBJECT:
				setConstrainingObject(newValue);
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
			case ValidityPackage.CONSTRAINING_NODE__PARENT:
				setParent((ConstrainingNode)null);
				return;
			case ValidityPackage.CONSTRAINING_NODE__CHILDREN:
				getChildren().clear();
				return;
			case ValidityPackage.CONSTRAINING_NODE__CONSTRAINING_OBJECT:
				setConstrainingObject(CONSTRAINING_OBJECT_EDEFAULT);
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
			case ValidityPackage.CONSTRAINING_NODE__PARENT:
				return getParent() != null;
			case ValidityPackage.CONSTRAINING_NODE__CHILDREN:
				return children != null && !children.isEmpty();
			case ValidityPackage.CONSTRAINING_NODE__CONSTRAINING_OBJECT:
				return CONSTRAINING_OBJECT_EDEFAULT == null ? constrainingObject != null : !CONSTRAINING_OBJECT_EDEFAULT.equals(constrainingObject);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String toString() {
		return super.toString();
	}
} //ConstrainingNodeImpl
