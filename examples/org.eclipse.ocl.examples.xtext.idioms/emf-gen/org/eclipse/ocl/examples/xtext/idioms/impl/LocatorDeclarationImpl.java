/**
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.examples.xtext.idioms.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsModel;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage;
import org.eclipse.ocl.examples.xtext.idioms.Locator;
import org.eclipse.ocl.examples.xtext.idioms.LocatorDeclaration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Named Locator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.LocatorDeclarationImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.LocatorDeclarationImpl#getOwnedLocator <em>Owned Locator</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.LocatorDeclarationImpl#getOwningIdiomsModel <em>Owning Idioms Model</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LocatorDeclarationImpl
		extends IdiomsElementImpl
		implements LocatorDeclaration {

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOwnedLocator() <em>Owned Locator</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedLocator()
	 * @generated
	 * @ordered
	 */
	protected Locator ownedLocator;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LocatorDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdiomsPackage.Literals.LOCATOR_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
				IdiomsPackage.LOCATOR_DECLARATION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Locator getOwnedLocator() {
		return ownedLocator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedLocator(Locator newOwnedLocator,
			NotificationChain msgs) {
		Locator oldOwnedLocator = ownedLocator;
		ownedLocator = newOwnedLocator;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
				Notification.SET,
				IdiomsPackage.LOCATOR_DECLARATION__OWNED_LOCATOR,
				oldOwnedLocator, newOwnedLocator);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwnedLocator(Locator newOwnedLocator) {
		if (newOwnedLocator != ownedLocator) {
			NotificationChain msgs = null;
			if (ownedLocator != null)
				msgs = ((InternalEObject) ownedLocator).eInverseRemove(this,
					EOPPOSITE_FEATURE_BASE
						- IdiomsPackage.LOCATOR_DECLARATION__OWNED_LOCATOR,
					null, msgs);
			if (newOwnedLocator != null)
				msgs = ((InternalEObject) newOwnedLocator).eInverseAdd(this,
					EOPPOSITE_FEATURE_BASE
						- IdiomsPackage.LOCATOR_DECLARATION__OWNED_LOCATOR,
					null, msgs);
			msgs = basicSetOwnedLocator(newOwnedLocator, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
				IdiomsPackage.LOCATOR_DECLARATION__OWNED_LOCATOR,
				newOwnedLocator, newOwnedLocator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IdiomsModel getOwningIdiomsModel() {
		if (eContainerFeatureID() != IdiomsPackage.LOCATOR_DECLARATION__OWNING_IDIOMS_MODEL)
			return null;
		return (IdiomsModel) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningIdiomsModel(
			IdiomsModel newOwningIdiomsModel, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newOwningIdiomsModel,
			IdiomsPackage.LOCATOR_DECLARATION__OWNING_IDIOMS_MODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningIdiomsModel(IdiomsModel newOwningIdiomsModel) {
		if (newOwningIdiomsModel != eInternalContainer()
			|| (eContainerFeatureID() != IdiomsPackage.LOCATOR_DECLARATION__OWNING_IDIOMS_MODEL
				&& newOwningIdiomsModel != null)) {
			if (EcoreUtil.isAncestor(this, newOwningIdiomsModel))
				throw new IllegalArgumentException(
					"Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningIdiomsModel != null)
				msgs = ((InternalEObject) newOwningIdiomsModel).eInverseAdd(
					this,
					IdiomsPackage.IDIOMS_MODEL__OWNED_LOCATOR_DECLARATIONS,
					IdiomsModel.class, msgs);
			msgs = basicSetOwningIdiomsModel(newOwningIdiomsModel, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
				IdiomsPackage.LOCATOR_DECLARATION__OWNING_IDIOMS_MODEL,
				newOwningIdiomsModel, newOwningIdiomsModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IdiomsPackage.LOCATOR_DECLARATION__OWNING_IDIOMS_MODEL :
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningIdiomsModel((IdiomsModel) otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IdiomsPackage.LOCATOR_DECLARATION__OWNED_LOCATOR :
				return basicSetOwnedLocator(null, msgs);
			case IdiomsPackage.LOCATOR_DECLARATION__OWNING_IDIOMS_MODEL :
				return basicSetOwningIdiomsModel(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(
			NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case IdiomsPackage.LOCATOR_DECLARATION__OWNING_IDIOMS_MODEL :
				return eInternalContainer().eInverseRemove(this,
					IdiomsPackage.IDIOMS_MODEL__OWNED_LOCATOR_DECLARATIONS,
					IdiomsModel.class, msgs);
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
			case IdiomsPackage.LOCATOR_DECLARATION__NAME :
				return getName();
			case IdiomsPackage.LOCATOR_DECLARATION__OWNED_LOCATOR :
				return getOwnedLocator();
			case IdiomsPackage.LOCATOR_DECLARATION__OWNING_IDIOMS_MODEL :
				return getOwningIdiomsModel();
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
			case IdiomsPackage.LOCATOR_DECLARATION__NAME :
				setName((String) newValue);
				return;
			case IdiomsPackage.LOCATOR_DECLARATION__OWNED_LOCATOR :
				setOwnedLocator((Locator) newValue);
				return;
			case IdiomsPackage.LOCATOR_DECLARATION__OWNING_IDIOMS_MODEL :
				setOwningIdiomsModel((IdiomsModel) newValue);
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
			case IdiomsPackage.LOCATOR_DECLARATION__NAME :
				setName(NAME_EDEFAULT);
				return;
			case IdiomsPackage.LOCATOR_DECLARATION__OWNED_LOCATOR :
				setOwnedLocator((Locator) null);
				return;
			case IdiomsPackage.LOCATOR_DECLARATION__OWNING_IDIOMS_MODEL :
				setOwningIdiomsModel((IdiomsModel) null);
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
			case IdiomsPackage.LOCATOR_DECLARATION__NAME :
				return NAME_EDEFAULT == null
					? name != null
					: !NAME_EDEFAULT.equals(name);
			case IdiomsPackage.LOCATOR_DECLARATION__OWNED_LOCATOR :
				return ownedLocator != null;
			case IdiomsPackage.LOCATOR_DECLARATION__OWNING_IDIOMS_MODEL :
				return getOwningIdiomsModel() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: "); //$NON-NLS-1$
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //NamedLocatorImpl
