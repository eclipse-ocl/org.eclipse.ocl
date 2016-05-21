/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.basecs.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.PathNameCS;
import org.eclipse.ocl.xtext.basecs.TemplateBindingCS;
import org.eclipse.ocl.xtext.basecs.TypedTypeRefCS;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Typed Type Ref CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.TypedTypeRefCSImpl#isIsTypeof <em>Is Typeof</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.TypedTypeRefCSImpl#getOwnedBinding <em>Owned Binding</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.TypedTypeRefCSImpl#getOwnedPathName <em>Owned Path Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.TypedTypeRefCSImpl#getReferredType <em>Referred Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TypedTypeRefCSImpl extends TypedRefCSImpl implements TypedTypeRefCS {
	/**
	 * The default value of the '{@link #isIsTypeof() <em>Is Typeof</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsTypeof()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_TYPEOF_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isIsTypeof() <em>Is Typeof</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsTypeof()
	 * @generated
	 * @ordered
	 */
	protected boolean isTypeof = IS_TYPEOF_EDEFAULT;
	/**
	 * The cached value of the '{@link #getOwnedBinding() <em>Owned Binding</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedBinding()
	 * @generated
	 * @ordered
	 */
	protected TemplateBindingCS ownedBinding;
	/**
	 * The cached value of the '{@link #getOwnedPathName() <em>Owned Path Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedPathName()
	 * @generated
	 * @ordered
	 */
	protected PathNameCS ownedPathName;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypedTypeRefCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BaseCSPackage.Literals.TYPED_TYPE_REF_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PathNameCS getOwnedPathName()
	{
		return ownedPathName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedPathName(PathNameCS newOwnedPathName, NotificationChain msgs)
	{
		PathNameCS oldOwnedPathName = ownedPathName;
		ownedPathName = newOwnedPathName;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BaseCSPackage.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, oldOwnedPathName, newOwnedPathName);
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
	public void setOwnedPathName(PathNameCS newOwnedPathName)
	{
		if (newOwnedPathName != ownedPathName)
		{
			NotificationChain msgs = null;
			if (ownedPathName != null)
				msgs = ((InternalEObject)ownedPathName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BaseCSPackage.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, null, msgs);
			if (newOwnedPathName != null)
				msgs = ((InternalEObject)newOwnedPathName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BaseCSPackage.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, null, msgs);
			msgs = basicSetOwnedPathName(newOwnedPathName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, newOwnedPathName, newOwnedPathName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TemplateBindingCS getOwnedBinding()
	{
		return ownedBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedBinding(TemplateBindingCS newOwnedBinding, NotificationChain msgs)
	{
		TemplateBindingCS oldOwnedBinding = ownedBinding;
		ownedBinding = newOwnedBinding;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BaseCSPackage.TYPED_TYPE_REF_CS__OWNED_BINDING, oldOwnedBinding, newOwnedBinding);
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
	public void setOwnedBinding(TemplateBindingCS newOwnedBinding)
	{
		if (newOwnedBinding != ownedBinding)
		{
			NotificationChain msgs = null;
			if (ownedBinding != null)
				msgs = ((InternalEObject)ownedBinding).eInverseRemove(this, BaseCSPackage.TEMPLATE_BINDING_CS__OWNING_ELEMENT, TemplateBindingCS.class, msgs);
			if (newOwnedBinding != null)
				msgs = ((InternalEObject)newOwnedBinding).eInverseAdd(this, BaseCSPackage.TEMPLATE_BINDING_CS__OWNING_ELEMENT, TemplateBindingCS.class, msgs);
			msgs = basicSetOwnedBinding(newOwnedBinding, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.TYPED_TYPE_REF_CS__OWNED_BINDING, newOwnedBinding, newOwnedBinding));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsTypeof()
	{
		return isTypeof;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsTypeof(boolean newIsTypeof)
	{
		boolean oldIsTypeof = isTypeof;
		isTypeof = newIsTypeof;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.TYPED_TYPE_REF_CS__IS_TYPEOF, oldIsTypeof, isTypeof));
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case BaseCSPackage.TYPED_TYPE_REF_CS__OWNED_BINDING:
				if (ownedBinding != null)
					msgs = ((InternalEObject)ownedBinding).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BaseCSPackage.TYPED_TYPE_REF_CS__OWNED_BINDING, null, msgs);
				return basicSetOwnedBinding((TemplateBindingCS)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case BaseCSPackage.TYPED_TYPE_REF_CS__OWNED_BINDING:
				return basicSetOwnedBinding(null, msgs);
			case BaseCSPackage.TYPED_TYPE_REF_CS__OWNED_PATH_NAME:
				return basicSetOwnedPathName(null, msgs);
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
			case BaseCSPackage.TYPED_TYPE_REF_CS__IS_TYPEOF:
				return isIsTypeof();
			case BaseCSPackage.TYPED_TYPE_REF_CS__OWNED_BINDING:
				return getOwnedBinding();
			case BaseCSPackage.TYPED_TYPE_REF_CS__OWNED_PATH_NAME:
				return getOwnedPathName();
			case BaseCSPackage.TYPED_TYPE_REF_CS__REFERRED_TYPE:
				return getReferredType();
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
			case BaseCSPackage.TYPED_TYPE_REF_CS__IS_TYPEOF:
				setIsTypeof((Boolean)newValue);
				return;
			case BaseCSPackage.TYPED_TYPE_REF_CS__OWNED_BINDING:
				setOwnedBinding((TemplateBindingCS)newValue);
				return;
			case BaseCSPackage.TYPED_TYPE_REF_CS__OWNED_PATH_NAME:
				setOwnedPathName((PathNameCS)newValue);
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
			case BaseCSPackage.TYPED_TYPE_REF_CS__IS_TYPEOF:
				setIsTypeof(IS_TYPEOF_EDEFAULT);
				return;
			case BaseCSPackage.TYPED_TYPE_REF_CS__OWNED_BINDING:
				setOwnedBinding((TemplateBindingCS)null);
				return;
			case BaseCSPackage.TYPED_TYPE_REF_CS__OWNED_PATH_NAME:
				setOwnedPathName((PathNameCS)null);
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
			case BaseCSPackage.TYPED_TYPE_REF_CS__IS_TYPEOF:
				return isTypeof != IS_TYPEOF_EDEFAULT;
			case BaseCSPackage.TYPED_TYPE_REF_CS__OWNED_BINDING:
				return ownedBinding != null;
			case BaseCSPackage.TYPED_TYPE_REF_CS__OWNED_PATH_NAME:
				return ownedPathName != null;
			case BaseCSPackage.TYPED_TYPE_REF_CS__REFERRED_TYPE:
				return getReferredType() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull BaseCSVisitor<R> visitor) {
		return visitor.visitTypedTypeRefCS(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Type getReferredType() {
		if (ownedPathName == null) {
			return null;
		}
		Element referredElement = ownedPathName.getReferredElement();
		return (Type) referredElement;
	}
} //TypedTypeRefCSImpl
