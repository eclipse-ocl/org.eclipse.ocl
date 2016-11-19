/*******************************************************************************
 * Copyright (c) 2012, 2015 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.OppositePropertyCallExp;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Opposite Property Call Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OppositePropertyCallExpImpl#getReferredProperty <em>Referred Property</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OppositePropertyCallExpImpl extends NavigationCallExpImpl implements OppositePropertyCallExp
{
	/**
	 * The cached value of the '{@link #getReferredProperty() <em>Referred Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredProperty()
	 * @generated
	 * @ordered
	 */
	protected Property referredProperty;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OppositePropertyCallExpImpl()
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
		return PivotPackage.Literals.OPPOSITE_PROPERTY_CALL_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Property getReferredProperty()
	{
		if (referredProperty != null && referredProperty.eIsProxy())
		{
			InternalEObject oldReferredProperty = (InternalEObject)referredProperty;
			referredProperty = (Property)eResolveProxy(oldReferredProperty);
			if (referredProperty != oldReferredProperty)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__REFERRED_PROPERTY, oldReferredProperty, referredProperty));
			}
		}
		return referredProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property basicGetReferredProperty()
	{
		return referredProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReferredProperty(Property newReferredProperty)
	{
		Property oldReferredProperty = referredProperty;
		referredProperty = newReferredProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__REFERRED_PROPERTY, oldReferredProperty, referredProperty));
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
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__NAME:
				return getName();
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__IS_MANY:
				return isIsMany();
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__IS_REQUIRED:
				return isIsRequired();
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__TYPE_VALUE:
				return getTypeValue();
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__IS_IMPLICIT:
				return isIsImplicit();
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__IS_SAFE:
				return isIsSafe();
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__OWNED_SOURCE:
				return getOwnedSource();
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__IS_PRE:
				return isIsPre();
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__NAVIGATION_SOURCE:
				if (resolve) return getNavigationSource();
				return basicGetNavigationSource();
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__QUALIFIERS:
				return getQualifiers();
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__REFERRED_PROPERTY:
				if (resolve) return getReferredProperty();
				return basicGetReferredProperty();
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
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__IS_REQUIRED:
				setIsRequired((Boolean)newValue);
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__TYPE:
				setType((Type)newValue);
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__TYPE_VALUE:
				setTypeValue((Type)newValue);
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__IS_IMPLICIT:
				setIsImplicit((Boolean)newValue);
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__IS_SAFE:
				setIsSafe((Boolean)newValue);
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__OWNED_SOURCE:
				setOwnedSource((OCLExpression)newValue);
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__IS_PRE:
				setIsPre((Boolean)newValue);
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__NAVIGATION_SOURCE:
				setNavigationSource((Property)newValue);
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__QUALIFIERS:
				getQualifiers().clear();
				getQualifiers().addAll((Collection<? extends OCLExpression>)newValue);
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__REFERRED_PROPERTY:
				setReferredProperty((Property)newValue);
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
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__IS_REQUIRED:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__TYPE:
				setType((Type)null);
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__TYPE_VALUE:
				setTypeValue((Type)null);
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__IS_IMPLICIT:
				setIsImplicit(IS_IMPLICIT_EDEFAULT);
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__IS_SAFE:
				setIsSafe(IS_SAFE_EDEFAULT);
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__OWNED_SOURCE:
				setOwnedSource((OCLExpression)null);
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__IS_PRE:
				setIsPre(IS_PRE_EDEFAULT);
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__NAVIGATION_SOURCE:
				setNavigationSource((Property)null);
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__QUALIFIERS:
				getQualifiers().clear();
				return;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__REFERRED_PROPERTY:
				setReferredProperty((Property)null);
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
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__IS_MANY:
				return isIsMany() != IS_MANY_EDEFAULT;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__IS_REQUIRED:
				return ((eFlags & IS_REQUIRED_EFLAG) != 0) != IS_REQUIRED_EDEFAULT;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__TYPE:
				return type != null;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__TYPE_VALUE:
				return typeValue != null;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__IS_IMPLICIT:
				return ((eFlags & IS_IMPLICIT_EFLAG) != 0) != IS_IMPLICIT_EDEFAULT;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__IS_SAFE:
				return ((eFlags & IS_SAFE_EFLAG) != 0) != IS_SAFE_EDEFAULT;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__OWNED_SOURCE:
				return ownedSource != null;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__IS_PRE:
				return ((eFlags & IS_PRE_EFLAG) != 0) != IS_PRE_EDEFAULT;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__NAVIGATION_SOURCE:
				return navigationSource != null;
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__QUALIFIERS:
				return qualifiers != null && !qualifiers.isEmpty();
			case PivotPackage.OPPOSITE_PROPERTY_CALL_EXP__REFERRED_PROPERTY:
				return referredProperty != null;
		}
		return eDynamicIsSet(featureID);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitOppositePropertyCallExp(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Element getReferredElement()
	{
		return getReferredProperty();
	}

} //OppositePropertyCallExpImpl
