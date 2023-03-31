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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.ocl.ocl.AssociationClass;
import org.eclipse.ocl.ocl.AssociationClassCallExp;
import org.eclipse.ocl.ocl.Comment;
import org.eclipse.ocl.ocl.Element;
import org.eclipse.ocl.ocl.ElementExtension;
import org.eclipse.ocl.ocl.OCLASPackage;
import org.eclipse.ocl.ocl.OCLExpression;
import org.eclipse.ocl.ocl.OclElement;
import org.eclipse.ocl.ocl.Property;
import org.eclipse.ocl.ocl.Type;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Association Class Call Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.internal.AssociationClassCallExpImpl#getReferredAssociationClass <em>Referred Association Class</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AssociationClassCallExpImpl extends NavigationCallExpImpl implements AssociationClassCallExp
{
	/**
	 * The number of structural features of the '<em>Association Class Call Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASSOCIATION_CLASS_CALL_EXP_FEATURE_COUNT = NavigationCallExpImpl.NAVIGATION_CALL_EXP_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Association Class Call Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ASSOCIATION_CLASS_CALL_EXP_OPERATION_COUNT = NavigationCallExpImpl.NAVIGATION_CALL_EXP_OPERATION_COUNT + 0;


	/**
	 * The cached value of the '{@link #getReferredAssociationClass() <em>Referred Association Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredAssociationClass()
	 * @generated
	 * @ordered
	 */
	protected AssociationClass referredAssociationClass;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssociationClassCallExpImpl()
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
		return OCLASPackage.Literals.ASSOCIATION_CLASS_CALL_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AssociationClass getReferredAssociationClass()
	{
		if (referredAssociationClass != null && referredAssociationClass.eIsProxy())
		{
			InternalEObject oldReferredAssociationClass = (InternalEObject)referredAssociationClass;
			referredAssociationClass = (AssociationClass)eResolveProxy(oldReferredAssociationClass);
			if (referredAssociationClass != oldReferredAssociationClass)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 17, oldReferredAssociationClass, referredAssociationClass));
			}
		}
		return referredAssociationClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssociationClass basicGetReferredAssociationClass()
	{
		return referredAssociationClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReferredAssociationClass(AssociationClass newReferredAssociationClass)
	{
		AssociationClass oldReferredAssociationClass = referredAssociationClass;
		referredAssociationClass = newReferredAssociationClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 17, oldReferredAssociationClass, referredAssociationClass));
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
				return getIsSafe();
			case 13:
				return getOwnedSource();
			case 14:
				return getIsPre();
			case 15:
				if (resolve) return getNavigationSource();
				return basicGetNavigationSource();
			case 16:
				return getQualifiers();
			case 17:
				if (resolve) return getReferredAssociationClass();
				return basicGetReferredAssociationClass();
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
				setIsSafe((Boolean)newValue);
				return;
			case 13:
				setOwnedSource((OCLExpression)newValue);
				return;
			case 14:
				setIsPre((Boolean)newValue);
				return;
			case 15:
				setNavigationSource((Property)newValue);
				return;
			case 16:
				getQualifiers().clear();
				getQualifiers().addAll((Collection<? extends OCLExpression>)newValue);
				return;
			case 17:
				setReferredAssociationClass((AssociationClass)newValue);
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
				setIsSafe(IS_SAFE_EDEFAULT);
				return;
			case 13:
				setOwnedSource((OCLExpression)null);
				return;
			case 14:
				setIsPre(IS_PRE_EDEFAULT);
				return;
			case 15:
				setNavigationSource((Property)null);
				return;
			case 16:
				getQualifiers().clear();
				return;
			case 17:
				setReferredAssociationClass((AssociationClass)null);
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
				return IS_SAFE_EDEFAULT == null ? isSafe != null : !IS_SAFE_EDEFAULT.equals(isSafe);
			case 13:
				return ownedSource != null;
			case 14:
				return IS_PRE_EDEFAULT == null ? isPre != null : !IS_PRE_EDEFAULT.equals(isPre);
			case 15:
				return navigationSource != null;
			case 16:
				return qualifiers != null && !qualifiers.isEmpty();
			case 17:
				return referredAssociationClass != null;
		}
		return eDynamicIsSet(featureID);
	}


} //AssociationClassCallExpImpl
