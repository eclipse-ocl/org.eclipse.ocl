/*******************************************************************************
 * Copyright (c) 2005, 2024 IBM Corporation, Zeligsoft Inc., and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   Zeligsoft - Bug 243976
 *******************************************************************************/
package org.eclipse.ocl.cst.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.cst.CSTPackage;
import org.eclipse.ocl.cst.ClassifierContextDeclCS;
import org.eclipse.ocl.cst.InvOrDefCS;
import org.eclipse.ocl.cst.PathNameCS;
import org.eclipse.ocl.cst.SimpleNameCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Classifier Context Decl CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.cst.impl.ClassifierContextDeclCSImpl#getPathNameCS <em>Path Name CS</em>}</li>
 *   <li>{@link org.eclipse.ocl.cst.impl.ClassifierContextDeclCSImpl#getConstraints <em>Constraints</em>}</li>
 *   <li>{@link org.eclipse.ocl.cst.impl.ClassifierContextDeclCSImpl#getSimpleNameCS <em>Simple Name CS</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ClassifierContextDeclCSImpl
		extends ContextDeclCSImpl
		implements ClassifierContextDeclCS {

	/**
	 * The cached value of the '{@link #getPathNameCS() <em>Path Name CS</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPathNameCS()
	 * @generated
	 * @ordered
	 */
	protected PathNameCS pathNameCS;

	/**
	 * The cached value of the '{@link #getConstraints() <em>Constraints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * @since 1.3
	 * <!-- end-user-doc -->
	 * @see #getConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList<InvOrDefCS> constraints;

	/**
	 * The cached value of the '{@link #getSimpleNameCS() <em>Simple Name CS</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * @since 3.0
	 * <!-- end-user-doc -->
	 * @see #getSimpleNameCS()
	 * @generated
	 * @ordered
	 */
	protected SimpleNameCS simpleNameCS;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassifierContextDeclCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CSTPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PathNameCS getPathNameCS() {
		return pathNameCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPathNameCS(PathNameCS newPathNameCS,
			NotificationChain msgs) {
		PathNameCS oldPathNameCS = pathNameCS;
		pathNameCS = newPathNameCS;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
				Notification.SET,
				CSTPackage.CLASSIFIER_CONTEXT_DECL_CS__PATH_NAME_CS,
				oldPathNameCS, newPathNameCS);
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
	public void setPathNameCS(PathNameCS newPathNameCS) {
		if (newPathNameCS != pathNameCS) {
			NotificationChain msgs = null;
			if (pathNameCS != null)
				msgs = ((InternalEObject) pathNameCS).eInverseRemove(this,
					EOPPOSITE_FEATURE_BASE
						- CSTPackage.CLASSIFIER_CONTEXT_DECL_CS__PATH_NAME_CS,
					null, msgs);
			if (newPathNameCS != null)
				msgs = ((InternalEObject) newPathNameCS).eInverseAdd(this,
					EOPPOSITE_FEATURE_BASE
						- CSTPackage.CLASSIFIER_CONTEXT_DECL_CS__PATH_NAME_CS,
					null, msgs);
			msgs = basicSetPathNameCS(newPathNameCS, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
				CSTPackage.CLASSIFIER_CONTEXT_DECL_CS__PATH_NAME_CS,
				newPathNameCS, newPathNameCS));
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.3
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<InvOrDefCS> getConstraints() {
		if (constraints == null) {
			constraints = new EObjectContainmentEList<InvOrDefCS>(
				InvOrDefCS.class, this,
				CSTPackage.CLASSIFIER_CONTEXT_DECL_CS__CONSTRAINTS);
		}
		return constraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 3.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SimpleNameCS getSimpleNameCS() {
		return simpleNameCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 3.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSimpleNameCS(SimpleNameCS newSimpleNameCS,
			NotificationChain msgs) {
		SimpleNameCS oldSimpleNameCS = simpleNameCS;
		simpleNameCS = newSimpleNameCS;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
				Notification.SET,
				CSTPackage.CLASSIFIER_CONTEXT_DECL_CS__SIMPLE_NAME_CS,
				oldSimpleNameCS, newSimpleNameCS);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 3.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSimpleNameCS(SimpleNameCS newSimpleNameCS) {
		if (newSimpleNameCS != simpleNameCS) {
			NotificationChain msgs = null;
			if (simpleNameCS != null)
				msgs = ((InternalEObject) simpleNameCS).eInverseRemove(this,
					EOPPOSITE_FEATURE_BASE
						- CSTPackage.CLASSIFIER_CONTEXT_DECL_CS__SIMPLE_NAME_CS,
					null, msgs);
			if (newSimpleNameCS != null)
				msgs = ((InternalEObject) newSimpleNameCS).eInverseAdd(this,
					EOPPOSITE_FEATURE_BASE
						- CSTPackage.CLASSIFIER_CONTEXT_DECL_CS__SIMPLE_NAME_CS,
					null, msgs);
			msgs = basicSetSimpleNameCS(newSimpleNameCS, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
				CSTPackage.CLASSIFIER_CONTEXT_DECL_CS__SIMPLE_NAME_CS,
				newSimpleNameCS, newSimpleNameCS));
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
			case CSTPackage.CLASSIFIER_CONTEXT_DECL_CS__PATH_NAME_CS :
				return basicSetPathNameCS(null, msgs);
			case CSTPackage.CLASSIFIER_CONTEXT_DECL_CS__CONSTRAINTS :
				return ((InternalEList<?>) getConstraints())
					.basicRemove(otherEnd, msgs);
			case CSTPackage.CLASSIFIER_CONTEXT_DECL_CS__SIMPLE_NAME_CS :
				return basicSetSimpleNameCS(null, msgs);
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
			case CSTPackage.CLASSIFIER_CONTEXT_DECL_CS__PATH_NAME_CS :
				return getPathNameCS();
			case CSTPackage.CLASSIFIER_CONTEXT_DECL_CS__CONSTRAINTS :
				return getConstraints();
			case CSTPackage.CLASSIFIER_CONTEXT_DECL_CS__SIMPLE_NAME_CS :
				return getSimpleNameCS();
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
			case CSTPackage.CLASSIFIER_CONTEXT_DECL_CS__PATH_NAME_CS :
				setPathNameCS((PathNameCS) newValue);
				return;
			case CSTPackage.CLASSIFIER_CONTEXT_DECL_CS__CONSTRAINTS :
				getConstraints().clear();
				getConstraints()
					.addAll((Collection<? extends InvOrDefCS>) newValue);
				return;
			case CSTPackage.CLASSIFIER_CONTEXT_DECL_CS__SIMPLE_NAME_CS :
				setSimpleNameCS((SimpleNameCS) newValue);
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
			case CSTPackage.CLASSIFIER_CONTEXT_DECL_CS__PATH_NAME_CS :
				setPathNameCS((PathNameCS) null);
				return;
			case CSTPackage.CLASSIFIER_CONTEXT_DECL_CS__CONSTRAINTS :
				getConstraints().clear();
				return;
			case CSTPackage.CLASSIFIER_CONTEXT_DECL_CS__SIMPLE_NAME_CS :
				setSimpleNameCS((SimpleNameCS) null);
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
			case CSTPackage.CLASSIFIER_CONTEXT_DECL_CS__PATH_NAME_CS :
				return pathNameCS != null;
			case CSTPackage.CLASSIFIER_CONTEXT_DECL_CS__CONSTRAINTS :
				return constraints != null && !constraints.isEmpty();
			case CSTPackage.CLASSIFIER_CONTEXT_DECL_CS__SIMPLE_NAME_CS :
				return simpleNameCS != null;
		}
		return super.eIsSet(featureID);
	}

} //ClassifierContextDeclCSImpl
