/**
 * <copyright>
 *
 * Copyright (c) 2010 E.D.Willink and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: IndexExpCSImpl.java,v 1.2.2.2 2010/10/01 14:30:27 ewillink Exp $
 */
package org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.ocl.examples.pivot.NamedElement;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.EssentialOCLCSTPackage;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.ExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.IndexExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.NamedExpCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Index Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.impl.IndexExpCSImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.impl.IndexExpCSImpl#getFirstIndexes <em>First Indexes</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.impl.IndexExpCSImpl#getSecondIndexes <em>Second Indexes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IndexExpCSImpl extends ExpCSImpl implements IndexExpCS
{
	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected NamedExpCS name;

	/**
	 * The cached value of the '{@link #getFirstIndexes() <em>First Indexes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirstIndexes()
	 * @generated
	 * @ordered
	 */
	protected EList<ExpCS> firstIndexes;

	/**
	 * The cached value of the '{@link #getSecondIndexes() <em>Second Indexes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecondIndexes()
	 * @generated
	 * @ordered
	 */
	protected EList<ExpCS> secondIndexes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IndexExpCSImpl()
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
		return EssentialOCLCSTPackage.Literals.INDEX_EXP_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NamedExpCS getName()
	{
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetName(NamedExpCS newName, NotificationChain msgs)
	{
		NamedExpCS oldName = name;
		name = newName;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSTPackage.INDEX_EXP_CS__NAME, oldName, newName);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(NamedExpCS newName)
	{
		if (newName != name)
		{
			NotificationChain msgs = null;
			if (name != null)
				msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSTPackage.INDEX_EXP_CS__NAME, null, msgs);
			if (newName != null)
				msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSTPackage.INDEX_EXP_CS__NAME, null, msgs);
			msgs = basicSetName(newName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSTPackage.INDEX_EXP_CS__NAME, newName, newName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExpCS> getFirstIndexes()
	{
		if (firstIndexes == null)
		{
			firstIndexes = new EObjectContainmentEList<ExpCS>(ExpCS.class, this, EssentialOCLCSTPackage.INDEX_EXP_CS__FIRST_INDEXES);
		}
		return firstIndexes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExpCS> getSecondIndexes()
	{
		if (secondIndexes == null)
		{
			secondIndexes = new EObjectContainmentEList<ExpCS>(ExpCS.class, this, EssentialOCLCSTPackage.INDEX_EXP_CS__SECOND_INDEXES);
		}
		return secondIndexes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public NamedElement getNamedElement()
	{
		return getName().getNamedElement();
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
			case EssentialOCLCSTPackage.INDEX_EXP_CS__NAME:
				return basicSetName(null, msgs);
			case EssentialOCLCSTPackage.INDEX_EXP_CS__FIRST_INDEXES:
				return ((InternalEList<?>)getFirstIndexes()).basicRemove(otherEnd, msgs);
			case EssentialOCLCSTPackage.INDEX_EXP_CS__SECOND_INDEXES:
				return ((InternalEList<?>)getSecondIndexes()).basicRemove(otherEnd, msgs);
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
			case EssentialOCLCSTPackage.INDEX_EXP_CS__NAME:
				return getName();
			case EssentialOCLCSTPackage.INDEX_EXP_CS__FIRST_INDEXES:
				return getFirstIndexes();
			case EssentialOCLCSTPackage.INDEX_EXP_CS__SECOND_INDEXES:
				return getSecondIndexes();
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
			case EssentialOCLCSTPackage.INDEX_EXP_CS__NAME:
				setName((NamedExpCS)newValue);
				return;
			case EssentialOCLCSTPackage.INDEX_EXP_CS__FIRST_INDEXES:
				getFirstIndexes().clear();
				getFirstIndexes().addAll((Collection<? extends ExpCS>)newValue);
				return;
			case EssentialOCLCSTPackage.INDEX_EXP_CS__SECOND_INDEXES:
				getSecondIndexes().clear();
				getSecondIndexes().addAll((Collection<? extends ExpCS>)newValue);
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
			case EssentialOCLCSTPackage.INDEX_EXP_CS__NAME:
				setName((NamedExpCS)null);
				return;
			case EssentialOCLCSTPackage.INDEX_EXP_CS__FIRST_INDEXES:
				getFirstIndexes().clear();
				return;
			case EssentialOCLCSTPackage.INDEX_EXP_CS__SECOND_INDEXES:
				getSecondIndexes().clear();
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
			case EssentialOCLCSTPackage.INDEX_EXP_CS__NAME:
				return name != null;
			case EssentialOCLCSTPackage.INDEX_EXP_CS__FIRST_INDEXES:
				return firstIndexes != null && !firstIndexes.isEmpty();
			case EssentialOCLCSTPackage.INDEX_EXP_CS__SECOND_INDEXES:
				return secondIndexes != null && !secondIndexes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //IndexExpCSImpl
