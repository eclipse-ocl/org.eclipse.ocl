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
import org.eclipse.ocl.examples.xtext.idioms.IdiomsImport;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsModel;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Import</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.IdiomsImportImpl#getAs <em>As</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.IdiomsImportImpl#getIdiomsModel <em>Idioms Model</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IdiomsImportImpl
		extends IdiomsElementImpl
		implements IdiomsImport {

	/**
	 * The number of structural features of the '<em>Import</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int IDIOMS_IMPORT_FEATURE_COUNT = IdiomsElementImpl.IDIOMS_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The default value of the '{@link #getAs() <em>As</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAs()
	 * @generated
	 * @ordered
	 */
	protected static final String AS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAs() <em>As</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAs()
	 * @generated
	 * @ordered
	 */
	protected String as = AS_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IdiomsImportImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdiomsPackage.Literals.IDIOMS_IMPORT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getAs() {
		return as;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAs(String newAs) {
		String oldAs = as;
		as = newAs;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 0, oldAs, as));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 1, oldIdiomsModel, idiomsModel));
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
			eNotify(new ENotificationImpl(this, Notification.SET, 1, oldIdiomsModel, idiomsModel));
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
				return getAs();
			case 1:
				if (resolve) return getIdiomsModel();
				return basicGetIdiomsModel();
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
				setAs((String)newValue);
				return;
			case 1:
				setIdiomsModel((IdiomsModel)newValue);
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
				setAs(AS_EDEFAULT);
				return;
			case 1:
				setIdiomsModel((IdiomsModel)null);
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
				return AS_EDEFAULT == null ? as != null : !AS_EDEFAULT.equals(as);
			case 1:
				return idiomsModel != null;
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
		result.append(" (as: "); //$NON-NLS-1$
		result.append(as);
		result.append(')');
		return result.toString();
	}

} //IdiomsImportImpl
