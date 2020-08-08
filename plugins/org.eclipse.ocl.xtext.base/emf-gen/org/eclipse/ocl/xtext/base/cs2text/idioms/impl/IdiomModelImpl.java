/**
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.xtext.base.cs2text.idioms.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.ocl.xtext.base.cs2text.idioms.Idiom;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomModel;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Locator;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Segment;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Idiom Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomModelImpl#getOwnedIdioms <em>Owned Idioms</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomModelImpl#getOwnedLocators <em>Owned Locators</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomModelImpl#getOwnedSegments <em>Owned Segments</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IdiomModelImpl extends EObjectImpl implements IdiomModel
{
	/**
	 * The number of structural features of the '<em>Idiom Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int IDIOM_MODEL_FEATURE_COUNT = 3;


	/**
	 * The cached value of the '{@link #getOwnedIdioms() <em>Owned Idioms</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedIdioms()
	 * @generated
	 * @ordered
	 */
	protected EList<Idiom> ownedIdioms;


	/**
	 * The cached value of the '{@link #getOwnedLocators() <em>Owned Locators</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedLocators()
	 * @generated
	 * @ordered
	 */
	protected EList<Locator> ownedLocators;


	/**
	 * The cached value of the '{@link #getOwnedSegments() <em>Owned Segments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSegments()
	 * @generated
	 * @ordered
	 */
	protected EList<Segment> ownedSegments;


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IdiomModelImpl()
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
		return IdiomsPackage.Literals.IDIOM_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Idiom> getOwnedIdioms()
	{
		if (ownedIdioms == null)
		{
			ownedIdioms = new EObjectContainmentEList<Idiom>(Idiom.class, this, 0);
		}
		return ownedIdioms;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Locator> getOwnedLocators()
	{
		if (ownedLocators == null)
		{
			ownedLocators = new EObjectContainmentEList<Locator>(Locator.class, this, 1);
		}
		return ownedLocators;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Segment> getOwnedSegments()
	{
		if (ownedSegments == null)
		{
			ownedSegments = new EObjectContainmentEList<Segment>(Segment.class, this, 2);
		}
		return ownedSegments;
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
			case 0:
				return ((InternalEList<?>)getOwnedIdioms()).basicRemove(otherEnd, msgs);
			case 1:
				return ((InternalEList<?>)getOwnedLocators()).basicRemove(otherEnd, msgs);
			case 2:
				return ((InternalEList<?>)getOwnedSegments()).basicRemove(otherEnd, msgs);
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
			case 0:
				return getOwnedIdioms();
			case 1:
				return getOwnedLocators();
			case 2:
				return getOwnedSegments();
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
			case 0:
				getOwnedIdioms().clear();
				getOwnedIdioms().addAll((Collection<? extends Idiom>)newValue);
				return;
			case 1:
				getOwnedLocators().clear();
				getOwnedLocators().addAll((Collection<? extends Locator>)newValue);
				return;
			case 2:
				getOwnedSegments().clear();
				getOwnedSegments().addAll((Collection<? extends Segment>)newValue);
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
			case 0:
				getOwnedIdioms().clear();
				return;
			case 1:
				getOwnedLocators().clear();
				return;
			case 2:
				getOwnedSegments().clear();
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
			case 0:
				return ownedIdioms != null && !ownedIdioms.isEmpty();
			case 1:
				return ownedLocators != null && !ownedLocators.isEmpty();
			case 2:
				return ownedSegments != null && !ownedSegments.isEmpty();
		}
		return super.eIsSet(featureID);
	}


} //IdiomModelImpl
