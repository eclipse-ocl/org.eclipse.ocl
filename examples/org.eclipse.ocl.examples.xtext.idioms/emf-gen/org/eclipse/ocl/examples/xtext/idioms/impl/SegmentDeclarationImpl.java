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
import org.eclipse.ocl.examples.xtext.idioms.Segment;
import org.eclipse.ocl.examples.xtext.idioms.SegmentDeclaration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Segment Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.SegmentDeclarationImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.SegmentDeclarationImpl#getOwnedSegment <em>Owned Segment</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.SegmentDeclarationImpl#getOwningIdiomsModel <em>Owning Idioms Model</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SegmentDeclarationImpl
		extends IdiomsElementImpl
		implements SegmentDeclaration {

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
	 * The cached value of the '{@link #getOwnedSegment() <em>Owned Segment</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSegment()
	 * @generated
	 * @ordered
	 */
	protected Segment ownedSegment;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SegmentDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdiomsPackage.Literals.SEGMENT_DECLARATION;
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
				IdiomsPackage.SEGMENT_DECLARATION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Segment getOwnedSegment() {
		return ownedSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedSegment(Segment newOwnedSegment,
			NotificationChain msgs) {
		Segment oldOwnedSegment = ownedSegment;
		ownedSegment = newOwnedSegment;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
				Notification.SET,
				IdiomsPackage.SEGMENT_DECLARATION__OWNED_SEGMENT,
				oldOwnedSegment, newOwnedSegment);
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
	public void setOwnedSegment(Segment newOwnedSegment) {
		if (newOwnedSegment != ownedSegment) {
			NotificationChain msgs = null;
			if (ownedSegment != null)
				msgs = ((InternalEObject) ownedSegment).eInverseRemove(this,
					EOPPOSITE_FEATURE_BASE
						- IdiomsPackage.SEGMENT_DECLARATION__OWNED_SEGMENT,
					null, msgs);
			if (newOwnedSegment != null)
				msgs = ((InternalEObject) newOwnedSegment).eInverseAdd(this,
					EOPPOSITE_FEATURE_BASE
						- IdiomsPackage.SEGMENT_DECLARATION__OWNED_SEGMENT,
					null, msgs);
			msgs = basicSetOwnedSegment(newOwnedSegment, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
				IdiomsPackage.SEGMENT_DECLARATION__OWNED_SEGMENT,
				newOwnedSegment, newOwnedSegment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IdiomsModel getOwningIdiomsModel() {
		if (eContainerFeatureID() != IdiomsPackage.SEGMENT_DECLARATION__OWNING_IDIOMS_MODEL)
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
			IdiomsPackage.SEGMENT_DECLARATION__OWNING_IDIOMS_MODEL, msgs);
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
			|| (eContainerFeatureID() != IdiomsPackage.SEGMENT_DECLARATION__OWNING_IDIOMS_MODEL
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
					IdiomsPackage.IDIOMS_MODEL__OWNED_SEGMENT_DECLARATIONS,
					IdiomsModel.class, msgs);
			msgs = basicSetOwningIdiomsModel(newOwningIdiomsModel, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
				IdiomsPackage.SEGMENT_DECLARATION__OWNING_IDIOMS_MODEL,
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
			case IdiomsPackage.SEGMENT_DECLARATION__OWNING_IDIOMS_MODEL :
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
			case IdiomsPackage.SEGMENT_DECLARATION__OWNED_SEGMENT :
				return basicSetOwnedSegment(null, msgs);
			case IdiomsPackage.SEGMENT_DECLARATION__OWNING_IDIOMS_MODEL :
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
			case IdiomsPackage.SEGMENT_DECLARATION__OWNING_IDIOMS_MODEL :
				return eInternalContainer().eInverseRemove(this,
					IdiomsPackage.IDIOMS_MODEL__OWNED_SEGMENT_DECLARATIONS,
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
			case IdiomsPackage.SEGMENT_DECLARATION__NAME :
				return getName();
			case IdiomsPackage.SEGMENT_DECLARATION__OWNED_SEGMENT :
				return getOwnedSegment();
			case IdiomsPackage.SEGMENT_DECLARATION__OWNING_IDIOMS_MODEL :
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
			case IdiomsPackage.SEGMENT_DECLARATION__NAME :
				setName((String) newValue);
				return;
			case IdiomsPackage.SEGMENT_DECLARATION__OWNED_SEGMENT :
				setOwnedSegment((Segment) newValue);
				return;
			case IdiomsPackage.SEGMENT_DECLARATION__OWNING_IDIOMS_MODEL :
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
			case IdiomsPackage.SEGMENT_DECLARATION__NAME :
				setName(NAME_EDEFAULT);
				return;
			case IdiomsPackage.SEGMENT_DECLARATION__OWNED_SEGMENT :
				setOwnedSegment((Segment) null);
				return;
			case IdiomsPackage.SEGMENT_DECLARATION__OWNING_IDIOMS_MODEL :
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
			case IdiomsPackage.SEGMENT_DECLARATION__NAME :
				return NAME_EDEFAULT == null
					? name != null
					: !NAME_EDEFAULT.equals(name);
			case IdiomsPackage.SEGMENT_DECLARATION__OWNED_SEGMENT :
				return ownedSegment != null;
			case IdiomsPackage.SEGMENT_DECLARATION__OWNING_IDIOMS_MODEL :
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

} //SegmentDeclarationImpl
