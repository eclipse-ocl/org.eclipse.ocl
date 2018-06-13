/*******************************************************************************
 * Copyright (c) 2010, 2015 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.xtext.basecs.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.xtext.basecs.AnnotationElementCS;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.ModelElementCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Element CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.ModelElementCSImpl#getOriginalXmiId <em>Original Xmi Id</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.ModelElementCSImpl#getOwnedAnnotations <em>Owned Annotations</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ModelElementCSImpl extends PivotableElementCSImpl implements ModelElementCS {
	/**
	 * The default value of the '{@link #getOriginalXmiId() <em>Original Xmi Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginalXmiId()
	 * @generated
	 * @ordered
	 */
	protected static final String ORIGINAL_XMI_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOriginalXmiId() <em>Original Xmi Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginalXmiId()
	 * @generated
	 * @ordered
	 */
	protected String originalXmiId = ORIGINAL_XMI_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOwnedAnnotations() <em>Owned Annotations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedAnnotations()
	 * @generated
	 * @ordered
	 */
	protected EList<AnnotationElementCS> ownedAnnotations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelElementCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BaseCSPackage.Literals.MODEL_ELEMENT_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<AnnotationElementCS> getOwnedAnnotations()
	{
		if (ownedAnnotations == null)
		{
			ownedAnnotations = new EObjectContainmentEList<AnnotationElementCS>(AnnotationElementCS.class, this, BaseCSPackage.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
		}
		return ownedAnnotations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getOriginalXmiId() {
		return originalXmiId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOriginalXmiId(String newOriginalXmiId) {
		String oldOriginalXmiId = originalXmiId;
		originalXmiId = newOriginalXmiId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.MODEL_ELEMENT_CS__ORIGINAL_XMI_ID, oldOriginalXmiId, originalXmiId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString()
	{
		return super.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID)
		{
			case BaseCSPackage.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
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
		switch (featureID)
		{
			case BaseCSPackage.MODEL_ELEMENT_CS__ORIGINAL_XMI_ID:
				return getOriginalXmiId();
			case BaseCSPackage.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
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
		switch (featureID)
		{
			case BaseCSPackage.MODEL_ELEMENT_CS__ORIGINAL_XMI_ID:
				setOriginalXmiId((String)newValue);
				return;
			case BaseCSPackage.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends AnnotationElementCS>)newValue);
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
			case BaseCSPackage.MODEL_ELEMENT_CS__ORIGINAL_XMI_ID:
				setOriginalXmiId(ORIGINAL_XMI_ID_EDEFAULT);
				return;
			case BaseCSPackage.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
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
			case BaseCSPackage.MODEL_ELEMENT_CS__ORIGINAL_XMI_ID:
				return ORIGINAL_XMI_ID_EDEFAULT == null ? originalXmiId != null : !ORIGINAL_XMI_ID_EDEFAULT.equals(originalXmiId);
			case BaseCSPackage.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
		}
		return super.eIsSet(featureID);
	}
} //ModelElementCSImpl
