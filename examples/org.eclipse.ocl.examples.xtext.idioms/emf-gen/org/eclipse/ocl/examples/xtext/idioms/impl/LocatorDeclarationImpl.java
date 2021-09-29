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
	 * The number of structural features of the '<em>Locator Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOCATOR_DECLARATION_FEATURE_COUNT = IdiomsElementImpl.IDIOMS_ELEMENT_FEATURE_COUNT + 3;

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
			eNotify(new ENotificationImpl(this, Notification.SET, 0, oldName, name));
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
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, 1, oldOwnedLocator, newOwnedLocator);
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
	public void setOwnedLocator(Locator newOwnedLocator) {
		if (newOwnedLocator != ownedLocator)
		{
			NotificationChain msgs = null;
			if (ownedLocator != null)
				msgs = ((InternalEObject)ownedLocator).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (1), null, msgs);
			if (newOwnedLocator != null)
				msgs = ((InternalEObject)newOwnedLocator).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (1), null, msgs);
			msgs = basicSetOwnedLocator(newOwnedLocator, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 1, newOwnedLocator, newOwnedLocator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IdiomsModel getOwningIdiomsModel() {
		if (eContainerFeatureID() != (2)) return null;
		return (IdiomsModel)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningIdiomsModel(
			IdiomsModel newOwningIdiomsModel, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOwningIdiomsModel, 2, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningIdiomsModel(IdiomsModel newOwningIdiomsModel) {
		if (newOwningIdiomsModel != eInternalContainer() || (eContainerFeatureID() != (2) && newOwningIdiomsModel != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningIdiomsModel))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningIdiomsModel != null)
				msgs = ((InternalEObject)newOwningIdiomsModel).eInverseAdd(this, 4, IdiomsModel.class, msgs);
			msgs = basicSetOwningIdiomsModel(newOwningIdiomsModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 2, newOwningIdiomsModel, newOwningIdiomsModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID)
		{
			case 2:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningIdiomsModel((IdiomsModel)otherEnd, msgs);
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
		switch (featureID)
		{
			case 1:
				return basicSetOwnedLocator(null, msgs);
			case 2:
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
		switch (eContainerFeatureID())
		{
			case 2:
				return eInternalContainer().eInverseRemove(this, 4, IdiomsModel.class, msgs);
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
		switch (featureID)
		{
			case 0:
				return getName();
			case 1:
				return getOwnedLocator();
			case 2:
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
		switch (featureID)
		{
			case 0:
				setName((String)newValue);
				return;
			case 1:
				setOwnedLocator((Locator)newValue);
				return;
			case 2:
				setOwningIdiomsModel((IdiomsModel)newValue);
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
		switch (featureID)
		{
			case 0:
				setName(NAME_EDEFAULT);
				return;
			case 1:
				setOwnedLocator((Locator)null);
				return;
			case 2:
				setOwningIdiomsModel((IdiomsModel)null);
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
		switch (featureID)
		{
			case 0:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case 1:
				return ownedLocator != null;
			case 2:
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
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: "); //$NON-NLS-1$
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //NamedLocatorImpl
