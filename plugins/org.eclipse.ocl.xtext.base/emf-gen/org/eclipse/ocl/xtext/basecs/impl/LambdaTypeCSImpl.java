/*******************************************************************************
 * Copyright (c) 2011, 2016 Willink Transformations and others.
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
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.Nameable;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BaseCSPackage.LAMBDA_TYPE_CS__OWNED_SIGNATURE, oldOwnedSignature, newOwnedSignature);
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
				msgs = ((InternalEObject)ownedSignature).eInverseRemove(this, BaseCSPackage.TEMPLATE_SIGNATURE_CS__OWNING_ELEMENT, TemplateSignatureCS.class, msgs);
			if (newOwnedSignature != null)
				msgs = ((InternalEObject)newOwnedSignature).eInverseAdd(this, BaseCSPackage.TEMPLATE_SIGNATURE_CS__OWNING_ELEMENT, TemplateSignatureCS.class, msgs);
			msgs = basicSetOwnedSignature(newOwnedSignature, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.LAMBDA_TYPE_CS__OWNED_SIGNATURE, newOwnedSignature, newOwnedSignature));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.LAMBDA_TYPE_CS__NAME, oldName, name));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BaseCSPackage.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE, oldOwnedContextType, newOwnedContextType);
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
				msgs = ((InternalEObject)ownedContextType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BaseCSPackage.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE, null, msgs);
			if (newOwnedContextType != null)
				msgs = ((InternalEObject)newOwnedContextType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BaseCSPackage.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE, null, msgs);
			msgs = basicSetOwnedContextType(newOwnedContextType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE, newOwnedContextType, newOwnedContextType));
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
			ownedParameterTypes = new EObjectContainmentEList<TypedRefCS>(TypedRefCS.class, this, BaseCSPackage.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES);
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BaseCSPackage.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE, oldOwnedResultType, newOwnedResultType);
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
				msgs = ((InternalEObject)ownedResultType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BaseCSPackage.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE, null, msgs);
			if (newOwnedResultType != null)
				msgs = ((InternalEObject)newOwnedResultType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BaseCSPackage.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE, null, msgs);
			msgs = basicSetOwnedResultType(newOwnedResultType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE, newOwnedResultType, newOwnedResultType));
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
			case BaseCSPackage.LAMBDA_TYPE_CS__OWNED_SIGNATURE:
				if (ownedSignature != null)
					msgs = ((InternalEObject)ownedSignature).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BaseCSPackage.LAMBDA_TYPE_CS__OWNED_SIGNATURE, null, msgs);
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
			case BaseCSPackage.LAMBDA_TYPE_CS__OWNED_SIGNATURE:
				return basicSetOwnedSignature(null, msgs);
			case BaseCSPackage.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE:
				return basicSetOwnedContextType(null, msgs);
			case BaseCSPackage.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES:
				return ((InternalEList<?>)getOwnedParameterTypes()).basicRemove(otherEnd, msgs);
			case BaseCSPackage.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE:
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
			case BaseCSPackage.LAMBDA_TYPE_CS__OWNED_SIGNATURE:
				return getOwnedSignature();
			case BaseCSPackage.LAMBDA_TYPE_CS__NAME:
				return getName();
			case BaseCSPackage.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE:
				return getOwnedContextType();
			case BaseCSPackage.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES:
				return getOwnedParameterTypes();
			case BaseCSPackage.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE:
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
			case BaseCSPackage.LAMBDA_TYPE_CS__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignatureCS)newValue);
				return;
			case BaseCSPackage.LAMBDA_TYPE_CS__NAME:
				setName((String)newValue);
				return;
			case BaseCSPackage.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE:
				setOwnedContextType((TypedRefCS)newValue);
				return;
			case BaseCSPackage.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES:
				getOwnedParameterTypes().clear();
				getOwnedParameterTypes().addAll((Collection<? extends TypedRefCS>)newValue);
				return;
			case BaseCSPackage.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE:
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
			case BaseCSPackage.LAMBDA_TYPE_CS__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignatureCS)null);
				return;
			case BaseCSPackage.LAMBDA_TYPE_CS__NAME:
				setName(NAME_EDEFAULT);
				return;
			case BaseCSPackage.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE:
				setOwnedContextType((TypedRefCS)null);
				return;
			case BaseCSPackage.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES:
				getOwnedParameterTypes().clear();
				return;
			case BaseCSPackage.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE:
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
			case BaseCSPackage.LAMBDA_TYPE_CS__OWNED_SIGNATURE:
				return ownedSignature != null;
			case BaseCSPackage.LAMBDA_TYPE_CS__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case BaseCSPackage.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE:
				return ownedContextType != null;
			case BaseCSPackage.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES:
				return ownedParameterTypes != null && !ownedParameterTypes.isEmpty();
			case BaseCSPackage.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE:
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
				case BaseCSPackage.LAMBDA_TYPE_CS__OWNED_SIGNATURE: return BaseCSPackage.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE;
				default: return -1;
			}
		}
		if (baseClass == Nameable.class)
		{
			switch (derivedFeatureID)
			{
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
				case BaseCSPackage.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE: return BaseCSPackage.LAMBDA_TYPE_CS__OWNED_SIGNATURE;
				default: return -1;
			}
		}
		if (baseClass == Nameable.class)
		{
			switch (baseFeatureID)
			{
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
