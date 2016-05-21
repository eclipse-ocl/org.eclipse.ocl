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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.TemplateBindingCS;
import org.eclipse.ocl.xtext.basecs.TemplateParameterSubstitutionCS;
import org.eclipse.ocl.xtext.basecs.TypeRefCS;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Template Parameter Substitution CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.TemplateParameterSubstitutionCSImpl#getOwnedActualParameter <em>Owned Actual Parameter</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.TemplateParameterSubstitutionCSImpl#getOwningBinding <em>Owning Binding</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TemplateParameterSubstitutionCSImpl extends ModelElementCSImpl implements TemplateParameterSubstitutionCS {
	/**
	 * The cached value of the '{@link #getOwnedActualParameter() <em>Owned Actual Parameter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedActualParameter()
	 * @generated
	 * @ordered
	 */
	protected TypeRefCS ownedActualParameter;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TemplateParameterSubstitutionCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TemplateBindingCS getOwningBinding() {
		if (eContainerFeatureID() != BaseCSPackage.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNING_BINDING) return null;
		return (TemplateBindingCS)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningBinding(TemplateBindingCS newOwningBinding, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningBinding, BaseCSPackage.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNING_BINDING, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningBinding(TemplateBindingCS newOwningBinding) {
		if (newOwningBinding != eInternalContainer() || (eContainerFeatureID() != BaseCSPackage.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNING_BINDING && newOwningBinding != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningBinding))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningBinding != null)
				msgs = ((InternalEObject)newOwningBinding).eInverseAdd(this, BaseCSPackage.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, TemplateBindingCS.class, msgs);
			msgs = basicSetOwningBinding(newOwningBinding, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNING_BINDING, newOwningBinding, newOwningBinding));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 *
	public TemplateParameterCS getFormalTemplateParameter() {
		TemplateBindingCS templateBinding = getOwningTemplateBinding();
		int index = templateBinding.getOwnedParameterSubstitution().indexOf(this);
		if (index < 0) {
			return null;
		}
		TemplateBindableElementCS templateBindableElement = templateBinding.getOwningTemplateBindableElement();
		TemplateSignatureCS templateSignature = templateBindableElement.getTemplateSignature();
		if (templateSignature == null) {
			return null;
		}
		List<TemplateParameterCS> ownedTemplateParameters = templateSignature.getOwnedTemplateParameter();
		if (index >= ownedTemplateParameters.size()) {
			return null;
		}
		return ownedTemplateParameters.get(index);
	} */

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TypeRefCS getOwnedActualParameter()
	{
		return ownedActualParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedActualParameter(TypeRefCS newOwnedActualParameter, NotificationChain msgs)
	{
		TypeRefCS oldOwnedActualParameter = ownedActualParameter;
		ownedActualParameter = newOwnedActualParameter;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BaseCSPackage.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, oldOwnedActualParameter, newOwnedActualParameter);
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
	public void setOwnedActualParameter(TypeRefCS newOwnedActualParameter)
	{
		if (newOwnedActualParameter != ownedActualParameter)
		{
			NotificationChain msgs = null;
			if (ownedActualParameter != null)
				msgs = ((InternalEObject)ownedActualParameter).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BaseCSPackage.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, null, msgs);
			if (newOwnedActualParameter != null)
				msgs = ((InternalEObject)newOwnedActualParameter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BaseCSPackage.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, null, msgs);
			msgs = basicSetOwnedActualParameter(newOwnedActualParameter, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, newOwnedActualParameter, newOwnedActualParameter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID)
		{
			case BaseCSPackage.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNING_BINDING:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningBinding((TemplateBindingCS)otherEnd, msgs);
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
			case BaseCSPackage.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER:
				return basicSetOwnedActualParameter(null, msgs);
			case BaseCSPackage.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNING_BINDING:
				return basicSetOwningBinding(null, msgs);
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
			case BaseCSPackage.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNING_BINDING:
				return eInternalContainer().eInverseRemove(this, BaseCSPackage.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, TemplateBindingCS.class, msgs);
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
			case BaseCSPackage.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER:
				return getOwnedActualParameter();
			case BaseCSPackage.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNING_BINDING:
				return getOwningBinding();
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
			case BaseCSPackage.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER:
				setOwnedActualParameter((TypeRefCS)newValue);
				return;
			case BaseCSPackage.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNING_BINDING:
				setOwningBinding((TemplateBindingCS)newValue);
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
			case BaseCSPackage.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER:
				setOwnedActualParameter((TypeRefCS)null);
				return;
			case BaseCSPackage.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNING_BINDING:
				setOwningBinding((TemplateBindingCS)null);
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
			case BaseCSPackage.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER:
				return ownedActualParameter != null;
			case BaseCSPackage.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNING_BINDING:
				return getOwningBinding() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull BaseCSVisitor<R> visitor) {
		return visitor.visitTemplateParameterSubstitutionCS(this);
	}
} //TemplateParameterSubstitutionCSImpl
