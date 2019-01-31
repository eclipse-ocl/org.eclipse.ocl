/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
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
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.LambdaTypeCS;
import org.eclipse.ocl.xtext.basecs.TemplateSignatureCS;
import org.eclipse.ocl.xtext.basecs.TemplateableElementCS;
import org.eclipse.ocl.xtext.basecs.TypedRefCS;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Lambda Type CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.LambdaTypeCSImpl#getOwnedSignature <em>Owned Signature</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.LambdaTypeCSImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.LambdaTypeCSImpl#getOwnedContextType <em>Owned Context Type</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.LambdaTypeCSImpl#getOwnedParameterTypes <em>Owned Parameter Types</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.LambdaTypeCSImpl#getOwnedResultType <em>Owned Result Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LambdaTypeCSImpl extends TypedRefCSImpl implements LambdaTypeCS
{
	/**
	 * The number of structural features of the '<em>Lambda Type CS</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LAMBDA_TYPE_CS_FEATURE_COUNT = TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 5;

	/**
	 * The cached value of the '{@link #getOwnedSignature() <em>Owned Signature</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSignature()
	 * @generated
	 * @ordered
	 */
	protected TemplateSignatureCS ownedSignature;

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
	 * The cached value of the '{@link #getOwnedContextType() <em>Owned Context Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedContextType()
	 * @generated
	 * @ordered
	 */
	protected TypedRefCS ownedContextType;

	/**
	 * The cached value of the '{@link #getOwnedParameterTypes() <em>Owned Parameter Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedParameterTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<TypedRefCS> ownedParameterTypes;

	/**
	 * The cached value of the '{@link #getOwnedResultType() <em>Owned Result Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedResultType()
	 * @generated
	 * @ordered
	 */
	protected TypedRefCS ownedResultType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LambdaTypeCSImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return BaseCSPackage.Literals.LAMBDA_TYPE_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TemplateSignatureCS getOwnedSignature()
	{
		return ownedSignature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedSignature(TemplateSignatureCS newOwnedSignature, NotificationChain msgs)
	{
		TemplateSignatureCS oldOwnedSignature = ownedSignature;
		ownedSignature = newOwnedSignature;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 0, oldOwnedSignature, newOwnedSignature);
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
	public void setOwnedSignature(TemplateSignatureCS newOwnedSignature)
	{
		if (newOwnedSignature != ownedSignature)
		{
			NotificationChain msgs = null;
			if (ownedSignature != null)
				msgs = ((InternalEObject)ownedSignature).eInverseRemove(this, ModelElementCSImpl.MODEL_ELEMENT_CS_FEATURE_COUNT + 1, TemplateSignatureCS.class, msgs);
			if (newOwnedSignature != null)
				msgs = ((InternalEObject)newOwnedSignature).eInverseAdd(this, ModelElementCSImpl.MODEL_ELEMENT_CS_FEATURE_COUNT + 1, TemplateSignatureCS.class, msgs);
			msgs = basicSetOwnedSignature(newOwnedSignature, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 0, newOwnedSignature, newOwnedSignature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName()
	{
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName)
	{
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 1, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TypedRefCS getOwnedContextType()
	{
		return ownedContextType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedContextType(TypedRefCS newOwnedContextType, NotificationChain msgs)
	{
		TypedRefCS oldOwnedContextType = ownedContextType;
		ownedContextType = newOwnedContextType;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 2, oldOwnedContextType, newOwnedContextType);
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
	public void setOwnedContextType(TypedRefCS newOwnedContextType)
	{
		if (newOwnedContextType != ownedContextType)
		{
			NotificationChain msgs = null;
			if (ownedContextType != null)
				msgs = ((InternalEObject)ownedContextType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 2), null, msgs);
			if (newOwnedContextType != null)
				msgs = ((InternalEObject)newOwnedContextType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 2), null, msgs);
			msgs = basicSetOwnedContextType(newOwnedContextType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 2, newOwnedContextType, newOwnedContextType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<TypedRefCS> getOwnedParameterTypes()
	{
		if (ownedParameterTypes == null)
		{
			ownedParameterTypes = new EObjectContainmentEList<TypedRefCS>(TypedRefCS.class, this, TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 3);
		}
		return ownedParameterTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TypedRefCS getOwnedResultType()
	{
		return ownedResultType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedResultType(TypedRefCS newOwnedResultType, NotificationChain msgs)
	{
		TypedRefCS oldOwnedResultType = ownedResultType;
		ownedResultType = newOwnedResultType;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 4, oldOwnedResultType, newOwnedResultType);
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
	public void setOwnedResultType(TypedRefCS newOwnedResultType)
	{
		if (newOwnedResultType != ownedResultType)
		{
			NotificationChain msgs = null;
			if (ownedResultType != null)
				msgs = ((InternalEObject)ownedResultType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 4), null, msgs);
			if (newOwnedResultType != null)
				msgs = ((InternalEObject)newOwnedResultType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 4), null, msgs);
			msgs = basicSetOwnedResultType(newOwnedResultType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 4, newOwnedResultType, newOwnedResultType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String toString() {
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
			case TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 0:
				if (ownedSignature != null)
					msgs = ((InternalEObject)ownedSignature).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 0), null, msgs);
				return basicSetOwnedSignature((TemplateSignatureCS)otherEnd, msgs);
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
			case TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 0:
				return basicSetOwnedSignature(null, msgs);
			case TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 2:
				return basicSetOwnedContextType(null, msgs);
			case TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 3:
				return ((InternalEList<?>)getOwnedParameterTypes()).basicRemove(otherEnd, msgs);
			case TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 4:
				return basicSetOwnedResultType(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch (featureID)
		{
			case TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 0:
				return getOwnedSignature();
			case TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 1:
				return getName();
			case TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 2:
				return getOwnedContextType();
			case TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 3:
				return getOwnedParameterTypes();
			case TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 4:
				return getOwnedResultType();
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
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 0:
				setOwnedSignature((TemplateSignatureCS)newValue);
				return;
			case TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 1:
				setName((String)newValue);
				return;
			case TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 2:
				setOwnedContextType((TypedRefCS)newValue);
				return;
			case TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 3:
				getOwnedParameterTypes().clear();
				getOwnedParameterTypes().addAll((Collection<? extends TypedRefCS>)newValue);
				return;
			case TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 4:
				setOwnedResultType((TypedRefCS)newValue);
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
	public void eUnset(int featureID)
	{
		switch (featureID)
		{
			case TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 0:
				setOwnedSignature((TemplateSignatureCS)null);
				return;
			case TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 1:
				setName(NAME_EDEFAULT);
				return;
			case TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 2:
				setOwnedContextType((TypedRefCS)null);
				return;
			case TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 3:
				getOwnedParameterTypes().clear();
				return;
			case TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 4:
				setOwnedResultType((TypedRefCS)null);
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
	public boolean eIsSet(int featureID)
	{
		switch (featureID)
		{
			case TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 0:
				return ownedSignature != null;
			case TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 1:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 2:
				return ownedContextType != null;
			case TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 3:
				return ownedParameterTypes != null && !ownedParameterTypes.isEmpty();
			case TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 4:
				return ownedResultType != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
	{
		if (baseClass == TemplateableElementCS.class)
		{
			switch (derivedFeatureID)
			{
				case TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 0: return ElementCSImpl.ELEMENT_CS_FEATURE_COUNT + 0;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
	{
		if (baseClass == TemplateableElementCS.class)
		{
			switch (baseFeatureID)
			{
				case ElementCSImpl.ELEMENT_CS_FEATURE_COUNT + 0: return TypedRefCSImpl.TYPED_REF_CS_FEATURE_COUNT + 0;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull BaseCSVisitor<R> visitor) {
		return visitor.visitLambdaTypeCS(this);
	}
} //LambdaTypeCSImpl
