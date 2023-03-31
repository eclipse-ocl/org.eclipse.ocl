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

import org.eclipse.ocl.ocl.CollectionRange;
import org.eclipse.ocl.ocl.Comment;
import org.eclipse.ocl.ocl.Element;
import org.eclipse.ocl.ocl.ElementExtension;
import org.eclipse.ocl.ocl.OCLASPackage;
import org.eclipse.ocl.ocl.OCLExpression;
import org.eclipse.ocl.ocl.OclElement;
import org.eclipse.ocl.ocl.Type;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Collection Range</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.internal.CollectionRangeImpl#getOwnedFirst <em>Owned First</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.CollectionRangeImpl#getOwnedLast <em>Owned Last</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CollectionRangeImpl extends CollectionLiteralPartImpl implements CollectionRange
{
	/**
	 * The number of structural features of the '<em>Collection Range</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_RANGE_FEATURE_COUNT = CollectionLiteralPartImpl.COLLECTION_LITERAL_PART_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Collection Range</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_RANGE_OPERATION_COUNT = CollectionLiteralPartImpl.COLLECTION_LITERAL_PART_OPERATION_COUNT + 0;


	/**
	 * The cached value of the '{@link #getOwnedFirst() <em>Owned First</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedFirst()
	 * @generated
	 * @ordered
	 */
	protected OCLExpression ownedFirst;

	/**
	 * The cached value of the '{@link #getOwnedLast() <em>Owned Last</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedLast()
	 * @generated
	 * @ordered
	 */
	protected OCLExpression ownedLast;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CollectionRangeImpl()
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
		return OCLASPackage.Literals.COLLECTION_RANGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OCLExpression getOwnedFirst()
	{
		return ownedFirst;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedFirst(OCLExpression newOwnedFirst, NotificationChain msgs)
	{
		OCLExpression oldOwnedFirst = ownedFirst;
		ownedFirst = newOwnedFirst;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, 10, oldOwnedFirst, newOwnedFirst);
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
	public void setOwnedFirst(OCLExpression newOwnedFirst)
	{
		if (newOwnedFirst != ownedFirst)
		{
			NotificationChain msgs = null;
			if (ownedFirst != null)
				msgs = ((InternalEObject)ownedFirst).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (10), null, msgs);
			if (newOwnedFirst != null)
				msgs = ((InternalEObject)newOwnedFirst).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (10), null, msgs);
			msgs = basicSetOwnedFirst(newOwnedFirst, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 10, newOwnedFirst, newOwnedFirst));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OCLExpression getOwnedLast()
	{
		return ownedLast;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedLast(OCLExpression newOwnedLast, NotificationChain msgs)
	{
		OCLExpression oldOwnedLast = ownedLast;
		ownedLast = newOwnedLast;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, 11, oldOwnedLast, newOwnedLast);
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
	public void setOwnedLast(OCLExpression newOwnedLast)
	{
		if (newOwnedLast != ownedLast)
		{
			NotificationChain msgs = null;
			if (ownedLast != null)
				msgs = ((InternalEObject)ownedLast).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (11), null, msgs);
			if (newOwnedLast != null)
				msgs = ((InternalEObject)newOwnedLast).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (11), null, msgs);
			msgs = basicSetOwnedLast(newOwnedLast, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 11, newOwnedLast, newOwnedLast));
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
			case 10:
				return basicSetOwnedFirst(null, msgs);
			case 11:
				return basicSetOwnedLast(null, msgs);
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
				return getOwnedFirst();
			case 11:
				return getOwnedLast();
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
				setOwnedFirst((OCLExpression)newValue);
				return;
			case 11:
				setOwnedLast((OCLExpression)newValue);
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
				setOwnedFirst((OCLExpression)null);
				return;
			case 11:
				setOwnedLast((OCLExpression)null);
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
				return ownedFirst != null;
			case 11:
				return ownedLast != null;
		}
		return eDynamicIsSet(featureID);
	}


} //CollectionRangeImpl
