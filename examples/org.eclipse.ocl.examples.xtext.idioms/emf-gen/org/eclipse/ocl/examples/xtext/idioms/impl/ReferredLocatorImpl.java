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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsModel;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage;
import org.eclipse.ocl.examples.xtext.idioms.ReferredLocator;
import org.eclipse.ocl.examples.xtext.idioms.LocatorDeclaration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Referred Locator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.ReferredLocatorImpl#getIdiomsModel <em>Idioms Model</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.ReferredLocatorImpl#getLocatorDeclaration <em>Locator Declaration</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReferredLocatorImpl
		extends LocatorImpl
		implements ReferredLocator {

	/**
	 * The number of structural features of the '<em>Referred Locator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int REFERRED_LOCATOR_FEATURE_COUNT = LocatorImpl.LOCATOR_FEATURE_COUNT + 2;

	/**
	 * The cached value of the '{@link #getIdiomsModel() <em>Idioms Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdiomsModel()
	 * @generated
	 * @ordered
	 */
	protected IdiomsModel idiomsModel;

	/**
	 * The cached value of the '{@link #getLocatorDeclaration() <em>Locator Declaration</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocatorDeclaration()
	 * @generated
	 * @ordered
	 */
	protected LocatorDeclaration locatorDeclaration;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReferredLocatorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdiomsPackage.Literals.REFERRED_LOCATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IdiomsModel getIdiomsModel() {
		if (idiomsModel != null && idiomsModel.eIsProxy())
		{
			InternalEObject oldIdiomsModel = (InternalEObject)idiomsModel;
			idiomsModel = (IdiomsModel)eResolveProxy(oldIdiomsModel);
			if (idiomsModel != oldIdiomsModel)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 0, oldIdiomsModel, idiomsModel));
			}
		}
		return idiomsModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdiomsModel basicGetIdiomsModel() {
		return idiomsModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIdiomsModel(IdiomsModel newIdiomsModel) {
		IdiomsModel oldIdiomsModel = idiomsModel;
		idiomsModel = newIdiomsModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 0, oldIdiomsModel, idiomsModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LocatorDeclaration getLocatorDeclaration() {
		if (locatorDeclaration != null && locatorDeclaration.eIsProxy())
		{
			InternalEObject oldLocatorDeclaration = (InternalEObject)locatorDeclaration;
			locatorDeclaration = (LocatorDeclaration)eResolveProxy(oldLocatorDeclaration);
			if (locatorDeclaration != oldLocatorDeclaration)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 1, oldLocatorDeclaration, locatorDeclaration));
			}
		}
		return locatorDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LocatorDeclaration basicGetLocatorDeclaration() {
		return locatorDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLocatorDeclaration(
			LocatorDeclaration newLocatorDeclaration) {
		LocatorDeclaration oldLocatorDeclaration = locatorDeclaration;
		locatorDeclaration = newLocatorDeclaration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 1, oldLocatorDeclaration, locatorDeclaration));
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
				if (resolve) return getIdiomsModel();
				return basicGetIdiomsModel();
			case 1:
				if (resolve) return getLocatorDeclaration();
				return basicGetLocatorDeclaration();
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
				setIdiomsModel((IdiomsModel)newValue);
				return;
			case 1:
				setLocatorDeclaration((LocatorDeclaration)newValue);
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
				setIdiomsModel((IdiomsModel)null);
				return;
			case 1:
				setLocatorDeclaration((LocatorDeclaration)null);
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
				return idiomsModel != null;
			case 1:
				return locatorDeclaration != null;
		}
		return super.eIsSet(featureID);
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (idiomsModel != null) {
			s.append(idiomsModel.getName());
			s.append("::");
		}
		s.append(locatorDeclaration.getName());
		return s.toString();
	}

} //ReferredLocatorImpl
