/*******************************************************************************
 * Copyright (c) 2011, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.markupcs.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ocl.xtext.markup.MarkupToTree;
import org.eclipse.ocl.xtext.markupcs.CompoundElement;
import org.eclipse.ocl.xtext.markupcs.MarkupElement;
import org.eclipse.ocl.xtext.markupcs.MarkupPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.markupcs.impl.MarkupElementImpl#getOwner <em>Owner</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.markupcs.impl.MarkupElementImpl#getUniqueId <em>Unique Id</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MarkupElementImpl extends MinimalEObjectImpl.Container implements MarkupElement {
	/**
	 * The default value of the '{@link #getUniqueId() <em>Unique Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUniqueId()
	 * @generated
	 * @ordered
	 */
	protected static final int UNIQUE_ID_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getUniqueId() <em>Unique Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUniqueId()
	 * @generated
	 * @ordered
	 */
	protected int uniqueId = UNIQUE_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MarkupElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MarkupPackage.Literals.MARKUP_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CompoundElement getOwner() {
		if (eContainerFeatureID() != MarkupPackage.MARKUP_ELEMENT__OWNER) return null;
		return (CompoundElement)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwner(CompoundElement newOwner, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOwner, MarkupPackage.MARKUP_ELEMENT__OWNER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwner(CompoundElement newOwner) {
		if (newOwner != eInternalContainer() || (eContainerFeatureID() != MarkupPackage.MARKUP_ELEMENT__OWNER && newOwner != null)) {
			if (EcoreUtil.isAncestor(this, newOwner))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwner != null)
				msgs = ((InternalEObject)newOwner).eInverseAdd(this, MarkupPackage.COMPOUND_ELEMENT__ELEMENTS, CompoundElement.class, msgs);
			msgs = basicSetOwner(newOwner, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MarkupPackage.MARKUP_ELEMENT__OWNER, newOwner, newOwner));
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getUniqueId() {
		return uniqueId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		MarkupToTree toString = new MarkupToTree();
		return String.valueOf(toString.doSwitch(this));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MarkupPackage.MARKUP_ELEMENT__OWNER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwner((CompoundElement)otherEnd, msgs);
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
			case MarkupPackage.MARKUP_ELEMENT__OWNER:
				return basicSetOwner(null, msgs);
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
			case MarkupPackage.MARKUP_ELEMENT__OWNER:
				return eInternalContainer().eInverseRemove(this, MarkupPackage.COMPOUND_ELEMENT__ELEMENTS, CompoundElement.class, msgs);
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
			case MarkupPackage.MARKUP_ELEMENT__OWNER:
				return getOwner();
			case MarkupPackage.MARKUP_ELEMENT__UNIQUE_ID:
				return getUniqueId();
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
			case MarkupPackage.MARKUP_ELEMENT__OWNER:
				setOwner((CompoundElement)newValue);
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
			case MarkupPackage.MARKUP_ELEMENT__OWNER:
				setOwner((CompoundElement)null);
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
			case MarkupPackage.MARKUP_ELEMENT__OWNER:
				return getOwner() != null;
			case MarkupPackage.MARKUP_ELEMENT__UNIQUE_ID:
				return uniqueId != UNIQUE_ID_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}
} //MarkupElementImpl
