/**
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.MapLiteralPart;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Map Literal Part</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.MapLiteralPartImpl#getOwnedKey <em>Owned Key</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.MapLiteralPartImpl#getOwnedValue <em>Owned Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MapLiteralPartImpl extends ElementImpl implements MapLiteralPart
{
	/**
	 * The cached value of the '{@link #getOwnedKey() <em>Owned Key</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedKey()
	 * @generated
	 * @ordered
	 */
	protected OCLExpression ownedKey;

	/**
	 * The cached value of the '{@link #getOwnedValue() <em>Owned Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedValue()
	 * @generated
	 * @ordered
	 */
	protected OCLExpression ownedValue;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MapLiteralPartImpl()
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
		return PivotPackage.Literals.MAP_LITERAL_PART;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OCLExpression getOwnedKey()
	{
		return ownedKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedKey(OCLExpression newOwnedKey, NotificationChain msgs)
	{
		OCLExpression oldOwnedKey = ownedKey;
		ownedKey = newOwnedKey;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.MAP_LITERAL_PART__OWNED_KEY, oldOwnedKey, newOwnedKey);
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
	public void setOwnedKey(OCLExpression newOwnedKey)
	{
		if (newOwnedKey != ownedKey)
		{
			NotificationChain msgs = null;
			if (ownedKey != null)
				msgs = ((InternalEObject)ownedKey).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.MAP_LITERAL_PART__OWNED_KEY, null, msgs);
			if (newOwnedKey != null)
				msgs = ((InternalEObject)newOwnedKey).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PivotPackage.MAP_LITERAL_PART__OWNED_KEY, null, msgs);
			msgs = basicSetOwnedKey(newOwnedKey, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.MAP_LITERAL_PART__OWNED_KEY, newOwnedKey, newOwnedKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OCLExpression getOwnedValue()
	{
		return ownedValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedValue(OCLExpression newOwnedValue, NotificationChain msgs)
	{
		OCLExpression oldOwnedValue = ownedValue;
		ownedValue = newOwnedValue;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.MAP_LITERAL_PART__OWNED_VALUE, oldOwnedValue, newOwnedValue);
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
	public void setOwnedValue(OCLExpression newOwnedValue)
	{
		if (newOwnedValue != ownedValue)
		{
			NotificationChain msgs = null;
			if (ownedValue != null)
				msgs = ((InternalEObject)ownedValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.MAP_LITERAL_PART__OWNED_VALUE, null, msgs);
			if (newOwnedValue != null)
				msgs = ((InternalEObject)newOwnedValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PivotPackage.MAP_LITERAL_PART__OWNED_VALUE, null, msgs);
			msgs = basicSetOwnedValue(newOwnedValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.MAP_LITERAL_PART__OWNED_VALUE, newOwnedValue, newOwnedValue));
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
			case PivotPackage.MAP_LITERAL_PART__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.MAP_LITERAL_PART__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.MAP_LITERAL_PART__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.MAP_LITERAL_PART__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.MAP_LITERAL_PART__OWNED_KEY:
				return basicSetOwnedKey(null, msgs);
			case PivotPackage.MAP_LITERAL_PART__OWNED_VALUE:
				return basicSetOwnedValue(null, msgs);
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
			case PivotPackage.MAP_LITERAL_PART__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.MAP_LITERAL_PART__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.MAP_LITERAL_PART__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.MAP_LITERAL_PART__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.MAP_LITERAL_PART__OWNED_KEY:
				return getOwnedKey();
			case PivotPackage.MAP_LITERAL_PART__OWNED_VALUE:
				return getOwnedValue();
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
			case PivotPackage.MAP_LITERAL_PART__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.MAP_LITERAL_PART__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.MAP_LITERAL_PART__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.MAP_LITERAL_PART__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.MAP_LITERAL_PART__OWNED_KEY:
				setOwnedKey((OCLExpression)newValue);
				return;
			case PivotPackage.MAP_LITERAL_PART__OWNED_VALUE:
				setOwnedValue((OCLExpression)newValue);
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
			case PivotPackage.MAP_LITERAL_PART__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.MAP_LITERAL_PART__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.MAP_LITERAL_PART__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.MAP_LITERAL_PART__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.MAP_LITERAL_PART__OWNED_KEY:
				setOwnedKey((OCLExpression)null);
				return;
			case PivotPackage.MAP_LITERAL_PART__OWNED_VALUE:
				setOwnedValue((OCLExpression)null);
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
			case PivotPackage.MAP_LITERAL_PART__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.MAP_LITERAL_PART__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.MAP_LITERAL_PART__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.MAP_LITERAL_PART__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.MAP_LITERAL_PART__OWNED_KEY:
				return ownedKey != null;
			case PivotPackage.MAP_LITERAL_PART__OWNED_VALUE:
				return ownedValue != null;
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitMapLiteralPart(this);
	}

} //MapLiteralPartImpl
