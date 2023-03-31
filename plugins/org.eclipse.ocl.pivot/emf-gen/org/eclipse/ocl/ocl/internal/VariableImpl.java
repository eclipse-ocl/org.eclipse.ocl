/**
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.ocl.internal;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.ocl.ocl.Comment;
import org.eclipse.ocl.ocl.Element;
import org.eclipse.ocl.ocl.ElementExtension;
import org.eclipse.ocl.ocl.OCLASPackage;
import org.eclipse.ocl.ocl.OCLExpression;
import org.eclipse.ocl.ocl.OclElement;
import org.eclipse.ocl.ocl.Parameter;
import org.eclipse.ocl.ocl.Type;
import org.eclipse.ocl.ocl.Variable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.internal.VariableImpl#getIsImplicit <em>Is Implicit</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.VariableImpl#getOwnedInit <em>Owned Init</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.VariableImpl#getRepresentedParameter <em>Represented Parameter</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VariableImpl extends VariableDeclarationImpl implements Variable
{
	/**
	 * The number of structural features of the '<em>Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VARIABLE_FEATURE_COUNT = VariableDeclarationImpl.VARIABLE_DECLARATION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VARIABLE_OPERATION_COUNT = VariableDeclarationImpl.VARIABLE_DECLARATION_OPERATION_COUNT + 0;


	/**
	 * The default value of the '{@link #getIsImplicit() <em>Is Implicit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsImplicit()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_IMPLICIT_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getIsImplicit() <em>Is Implicit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsImplicit()
	 * @generated
	 * @ordered
	 */
	protected Boolean isImplicit = IS_IMPLICIT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOwnedInit() <em>Owned Init</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedInit()
	 * @generated
	 * @ordered
	 */
	protected OCLExpression ownedInit;

	/**
	 * The cached value of the '{@link #getRepresentedParameter() <em>Represented Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepresentedParameter()
	 * @generated
	 * @ordered
	 */
	protected Parameter representedParameter;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VariableImpl()
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
		return OCLASPackage.Literals.VARIABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getIsImplicit()
	{
		return isImplicit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsImplicit(Boolean newIsImplicit)
	{
		Boolean oldIsImplicit = isImplicit;
		isImplicit = newIsImplicit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 11, oldIsImplicit, isImplicit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OCLExpression getOwnedInit()
	{
		return ownedInit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedInit(OCLExpression newOwnedInit, NotificationChain msgs)
	{
		OCLExpression oldOwnedInit = ownedInit;
		ownedInit = newOwnedInit;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, 12, oldOwnedInit, newOwnedInit);
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
	public void setOwnedInit(OCLExpression newOwnedInit)
	{
		if (newOwnedInit != ownedInit)
		{
			NotificationChain msgs = null;
			if (ownedInit != null)
				msgs = ((InternalEObject)ownedInit).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (12), null, msgs);
			if (newOwnedInit != null)
				msgs = ((InternalEObject)newOwnedInit).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (12), null, msgs);
			msgs = basicSetOwnedInit(newOwnedInit, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 12, newOwnedInit, newOwnedInit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Parameter getRepresentedParameter()
	{
		if (representedParameter != null && representedParameter.eIsProxy())
		{
			InternalEObject oldRepresentedParameter = (InternalEObject)representedParameter;
			representedParameter = (Parameter)eResolveProxy(oldRepresentedParameter);
			if (representedParameter != oldRepresentedParameter)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 13, oldRepresentedParameter, representedParameter));
			}
		}
		return representedParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Parameter basicGetRepresentedParameter()
	{
		return representedParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRepresentedParameter(Parameter newRepresentedParameter)
	{
		Parameter oldRepresentedParameter = representedParameter;
		representedParameter = newRepresentedParameter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 13, oldRepresentedParameter, representedParameter));
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
			case 2:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case 3:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case 4:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case 5:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case 12:
				return basicSetOwnedInit(null, msgs);
		}
		return eDynamicInverseRemove(otherEnd, featureID, msgs);
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
			case 0:
				if (resolve) return getOclContainer();
				return basicGetOclContainer();
			case 1:
				return getOclContents();
			case 2:
				return getAnnotatingComments();
			case 3:
				return getOwnedAnnotations();
			case 4:
				return getOwnedComments();
			case 5:
				return getOwnedExtensions();
			case 6:
				return getName();
			case 7:
				return getIsMany();
			case 8:
				return getIsRequired();
			case 9:
				if (resolve) return getType();
				return basicGetType();
			case 10:
				return getTypeValue();
			case 11:
				return getIsImplicit();
			case 12:
				return getOwnedInit();
			case 13:
				if (resolve) return getRepresentedParameter();
				return basicGetRepresentedParameter();
		}
		return eDynamicGet(featureID, resolve, coreType);
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
			case 0:
				setOclContainer((OclElement)newValue);
				return;
			case 1:
				getOclContents().clear();
				getOclContents().addAll((Collection<? extends OclElement>)newValue);
				return;
			case 2:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 3:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case 4:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 5:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case 6:
				setName((String)newValue);
				return;
			case 8:
				setIsRequired((Boolean)newValue);
				return;
			case 9:
				setType((Type)newValue);
				return;
			case 10:
				setTypeValue((Type)newValue);
				return;
			case 11:
				setIsImplicit((Boolean)newValue);
				return;
			case 12:
				setOwnedInit((OCLExpression)newValue);
				return;
			case 13:
				setRepresentedParameter((Parameter)newValue);
				return;
		}
		eDynamicSet(featureID, newValue);
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
			case 0:
				setOclContainer((OclElement)null);
				return;
			case 1:
				getOclContents().clear();
				return;
			case 2:
				getAnnotatingComments().clear();
				return;
			case 3:
				getOwnedAnnotations().clear();
				return;
			case 4:
				getOwnedComments().clear();
				return;
			case 5:
				getOwnedExtensions().clear();
				return;
			case 6:
				setName(NAME_EDEFAULT);
				return;
			case 8:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case 9:
				setType((Type)null);
				return;
			case 10:
				setTypeValue((Type)null);
				return;
			case 11:
				setIsImplicit(IS_IMPLICIT_EDEFAULT);
				return;
			case 12:
				setOwnedInit((OCLExpression)null);
				return;
			case 13:
				setRepresentedParameter((Parameter)null);
				return;
		}
		eDynamicUnset(featureID);
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
			case 0:
				return oclContainer != null;
			case 1:
				return oclContents != null && !oclContents.isEmpty();
			case 2:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case 3:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case 4:
				return ownedComments != null && !ownedComments.isEmpty();
			case 5:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case 6:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case 7:
				return IS_MANY_EDEFAULT == null ? getIsMany() != null : !IS_MANY_EDEFAULT.equals(getIsMany());
			case 8:
				return IS_REQUIRED_EDEFAULT == null ? isRequired != null : !IS_REQUIRED_EDEFAULT.equals(isRequired);
			case 9:
				return type != null;
			case 10:
				return typeValue != null;
			case 11:
				return IS_IMPLICIT_EDEFAULT == null ? isImplicit != null : !IS_IMPLICIT_EDEFAULT.equals(isImplicit);
			case 12:
				return ownedInit != null;
			case 13:
				return representedParameter != null;
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString()
	{
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (isImplicit: "); //$NON-NLS-1$
		result.append(isImplicit);
		result.append(')');
		return result.toString();
	}


} //VariableImpl
