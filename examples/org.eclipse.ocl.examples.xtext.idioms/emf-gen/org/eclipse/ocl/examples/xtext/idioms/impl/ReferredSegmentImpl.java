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
import org.eclipse.ocl.examples.xtext.idioms.SegmentDeclaration;
import org.eclipse.ocl.examples.xtext.idioms.ReferredSegment;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Segment Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.ReferredSegmentImpl#getIdiomsModel <em>Idioms Model</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.ReferredSegmentImpl#getSegmentDeclaration <em>Segment Declaration</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReferredSegmentImpl
		extends SegmentImpl
		implements ReferredSegment {

	/**
	 * The number of structural features of the '<em>Referred Segment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int REFERRED_SEGMENT_FEATURE_COUNT = SegmentImpl.SEGMENT_FEATURE_COUNT + 2;

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
	 * The cached value of the '{@link #getSegmentDeclaration() <em>Segment Declaration</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSegmentDeclaration()
	 * @generated
	 * @ordered
	 */
	protected SegmentDeclaration segmentDeclaration;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReferredSegmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdiomsPackage.Literals.REFERRED_SEGMENT;
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
	public SegmentDeclaration getSegmentDeclaration() {
		if (segmentDeclaration != null && segmentDeclaration.eIsProxy())
		{
			InternalEObject oldSegmentDeclaration = (InternalEObject)segmentDeclaration;
			segmentDeclaration = (SegmentDeclaration)eResolveProxy(oldSegmentDeclaration);
			if (segmentDeclaration != oldSegmentDeclaration)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 1, oldSegmentDeclaration, segmentDeclaration));
			}
		}
		return segmentDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SegmentDeclaration basicGetSegmentDeclaration() {
		return segmentDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSegmentDeclaration(
			SegmentDeclaration newSegmentDeclaration) {
		SegmentDeclaration oldSegmentDeclaration = segmentDeclaration;
		segmentDeclaration = newSegmentDeclaration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 1, oldSegmentDeclaration, segmentDeclaration));
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
				if (resolve) return getSegmentDeclaration();
				return basicGetSegmentDeclaration();
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
				setSegmentDeclaration((SegmentDeclaration)newValue);
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
				setSegmentDeclaration((SegmentDeclaration)null);
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
				return segmentDeclaration != null;
		}
		return super.eIsSet(featureID);
	}

} //SegmentRefImpl
