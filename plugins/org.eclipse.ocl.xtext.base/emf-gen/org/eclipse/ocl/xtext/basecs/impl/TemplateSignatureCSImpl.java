/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.basecs.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.TemplateParameterCS;
import org.eclipse.ocl.xtext.basecs.TemplateSignatureCS;
import org.eclipse.ocl.xtext.basecs.TemplateableElementCS;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Template Signature CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.TemplateSignatureCSImpl#getOwnedParameters <em>Owned Parameters</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.TemplateSignatureCSImpl#getOwningElement <em>Owning Element</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TemplateSignatureCSImpl extends ModelElementCSImpl implements TemplateSignatureCS {
	/**
	 * The cached value of the '{@link #getOwnedParameters() <em>Owned Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<TemplateParameterCS> ownedParameters;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TemplateSignatureCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TemplateableElementCS getOwningElement() {
		if (eContainerFeatureID() != BaseCSPackage.TEMPLATE_SIGNATURE_CS__OWNING_ELEMENT) return null;
		return (TemplateableElementCS)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningElement(TemplateableElementCS newOwningElement, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningElement, BaseCSPackage.TEMPLATE_SIGNATURE_CS__OWNING_ELEMENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningElement(TemplateableElementCS newOwningElement) {
		if (newOwningElement != eInternalContainer() || (eContainerFeatureID() != BaseCSPackage.TEMPLATE_SIGNATURE_CS__OWNING_ELEMENT && newOwningElement != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningElement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningElement != null)
				msgs = ((InternalEObject)newOwningElement).eInverseAdd(this, BaseCSPackage.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, TemplateableElementCS.class, msgs);
			msgs = basicSetOwningElement(newOwningElement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.TEMPLATE_SIGNATURE_CS__OWNING_ELEMENT, newOwningElement, newOwningElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<TemplateParameterCS> getOwnedParameters() {
		if (ownedParameters == null)
		{
			ownedParameters = new EObjectContainmentWithInverseEList<TemplateParameterCS>(TemplateParameterCS.class, this, BaseCSPackage.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, BaseCSPackage.TEMPLATE_PARAMETER_CS__OWNING_SIGNATURE);
		}
		return ownedParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID)
		{
			case BaseCSPackage.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedParameters()).basicAdd(otherEnd, msgs);
			case BaseCSPackage.TEMPLATE_SIGNATURE_CS__OWNING_ELEMENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningElement((TemplateableElementCS)otherEnd, msgs);
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
		switch (featureID)
		{
			case BaseCSPackage.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS:
				return ((InternalEList<?>)getOwnedParameters()).basicRemove(otherEnd, msgs);
			case BaseCSPackage.TEMPLATE_SIGNATURE_CS__OWNING_ELEMENT:
				return basicSetOwningElement(null, msgs);
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
		switch (eContainerFeatureID())
		{
			case BaseCSPackage.TEMPLATE_SIGNATURE_CS__OWNING_ELEMENT:
				return eInternalContainer().eInverseRemove(this, BaseCSPackage.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, TemplateableElementCS.class, msgs);
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
			case BaseCSPackage.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS:
				return getOwnedParameters();
			case BaseCSPackage.TEMPLATE_SIGNATURE_CS__OWNING_ELEMENT:
				return getOwningElement();
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
			case BaseCSPackage.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS:
				getOwnedParameters().clear();
				getOwnedParameters().addAll((Collection<? extends TemplateParameterCS>)newValue);
				return;
			case BaseCSPackage.TEMPLATE_SIGNATURE_CS__OWNING_ELEMENT:
				setOwningElement((TemplateableElementCS)newValue);
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
			case BaseCSPackage.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS:
				getOwnedParameters().clear();
				return;
			case BaseCSPackage.TEMPLATE_SIGNATURE_CS__OWNING_ELEMENT:
				setOwningElement((TemplateableElementCS)null);
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
			case BaseCSPackage.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS:
				return ownedParameters != null && !ownedParameters.isEmpty();
			case BaseCSPackage.TEMPLATE_SIGNATURE_CS__OWNING_ELEMENT:
				return getOwningElement() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull BaseCSVisitor<R> visitor) {
		return visitor.visitTemplateSignatureCS(this);
	}
} //TemplateSignatureCSImpl
