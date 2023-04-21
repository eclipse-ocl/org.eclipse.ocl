/*******************************************************************************
 * Copyright (c) 2014, 2019 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.basecs.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.MultiplicityCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Multiplicity CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.MultiplicityCSImpl#isIsNonNullFree <em>Is Non Null Free</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.MultiplicityCSImpl#isIsNullFree <em>Is Null Free</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class MultiplicityCSImpl extends ElementCSImpl implements MultiplicityCS
{
	/**
	 * The number of structural features of the '<em>Multiplicity CS</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int MULTIPLICITY_CS_FEATURE_COUNT = ElementCSImpl.ELEMENT_CS_FEATURE_COUNT + 2;
	/**
	 * The default value of the '{@link #isIsNonNullFree() <em>Is Non Null Free</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsNonNullFree()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_NON_NULL_FREE_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isIsNonNullFree() <em>Is Non Null Free</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsNonNullFree()
	 * @generated
	 * @ordered
	 */
	protected boolean isNonNullFree = IS_NON_NULL_FREE_EDEFAULT;
	/**
	 * This is true if the Is Non Null Free attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isNonNullFreeESet;
	/**
	 * The default value of the '{@link #isIsNullFree() <em>Is Null Free</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsNullFree()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_NULL_FREE_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isIsNullFree() <em>Is Null Free</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsNullFree()
	 * @generated
	 * @ordered
	 */
	protected boolean isNullFree = IS_NULL_FREE_EDEFAULT;

	/**
	 * This is true if the Is Null Free attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isNullFreeESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MultiplicityCSImpl()
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
		return BaseCSPackage.Literals.MULTIPLICITY_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsNonNullFree()
	{
		return isNonNullFree;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsNonNullFree(boolean newIsNonNullFree)
	{
		boolean oldIsNonNullFree = isNonNullFree;
		isNonNullFree = newIsNonNullFree;
		boolean oldIsNonNullFreeESet = isNonNullFreeESet;
		isNonNullFreeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 2, oldIsNonNullFree, isNonNullFree, !oldIsNonNullFreeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetIsNonNullFree()
	{
		boolean oldIsNonNullFree = isNonNullFree;
		boolean oldIsNonNullFreeESet = isNonNullFreeESet;
		isNonNullFree = IS_NON_NULL_FREE_EDEFAULT;
		isNonNullFreeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, 2, oldIsNonNullFree, IS_NON_NULL_FREE_EDEFAULT, oldIsNonNullFreeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetIsNonNullFree()
	{
		return isNonNullFreeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsNullFree()
	{
		return isNullFree;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsNullFree(boolean newIsNullFree)
	{
		boolean oldIsNullFree = isNullFree;
		isNullFree = newIsNullFree;
		boolean oldIsNullFreeESet = isNullFreeESet;
		isNullFreeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 3, oldIsNullFree, isNullFree, !oldIsNullFreeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetIsNullFree()
	{
		boolean oldIsNullFree = isNullFree;
		boolean oldIsNullFreeESet = isNullFreeESet;
		isNullFree = IS_NULL_FREE_EDEFAULT;
		isNullFreeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, 3, oldIsNullFree, IS_NULL_FREE_EDEFAULT, oldIsNullFreeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetIsNullFree()
	{
		return isNullFreeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getLower()
	{
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getUpper()
	{
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
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
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch (featureID)
		{
			case 2:
				return isIsNonNullFree();
			case 3:
				return isIsNullFree();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case 2:
				setIsNonNullFree((Boolean)newValue);
				return;
			case 3:
				setIsNullFree((Boolean)newValue);
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
			case 2:
				unsetIsNonNullFree();
				return;
			case 3:
				unsetIsNullFree();
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
			case 2:
				return isSetIsNonNullFree();
			case 3:
				return isSetIsNullFree();
		}
		return super.eIsSet(featureID);
	}


} //MultiplicityCSImpl
