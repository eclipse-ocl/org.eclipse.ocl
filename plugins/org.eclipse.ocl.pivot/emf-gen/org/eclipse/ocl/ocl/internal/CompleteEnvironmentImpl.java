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
import org.eclipse.ocl.ocl.CompleteEnvironment;
import org.eclipse.ocl.ocl.CompleteModel;
import org.eclipse.ocl.ocl.Element;
import org.eclipse.ocl.ocl.ElementExtension;
import org.eclipse.ocl.ocl.OCLASPackage;
import org.eclipse.ocl.ocl.OclElement;
import org.eclipse.ocl.ocl.StandardLibrary;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Complete Environment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.internal.CompleteEnvironmentImpl#getOwnedCompleteModel <em>Owned Complete Model</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.CompleteEnvironmentImpl#getOwnedStandardLibrary <em>Owned Standard Library</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CompleteEnvironmentImpl extends ElementImpl implements CompleteEnvironment
{
	/**
	 * The number of structural features of the '<em>Complete Environment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPLETE_ENVIRONMENT_FEATURE_COUNT = ElementImpl.ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Complete Environment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPLETE_ENVIRONMENT_OPERATION_COUNT = ElementImpl.ELEMENT_OPERATION_COUNT + 0;


	/**
	 * The cached value of the '{@link #getOwnedCompleteModel() <em>Owned Complete Model</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedCompleteModel()
	 * @generated
	 * @ordered
	 */
	protected CompleteModel ownedCompleteModel;

	/**
	 * The cached value of the '{@link #getOwnedStandardLibrary() <em>Owned Standard Library</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedStandardLibrary()
	 * @generated
	 * @ordered
	 */
	protected StandardLibrary ownedStandardLibrary;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompleteEnvironmentImpl()
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
		return OCLASPackage.Literals.COMPLETE_ENVIRONMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CompleteModel getOwnedCompleteModel()
	{
		return ownedCompleteModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedCompleteModel(CompleteModel newOwnedCompleteModel, NotificationChain msgs)
	{
		CompleteModel oldOwnedCompleteModel = ownedCompleteModel;
		ownedCompleteModel = newOwnedCompleteModel;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, 6, oldOwnedCompleteModel, newOwnedCompleteModel);
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
	public void setOwnedCompleteModel(CompleteModel newOwnedCompleteModel)
	{
		if (newOwnedCompleteModel != ownedCompleteModel)
		{
			NotificationChain msgs = null;
			if (ownedCompleteModel != null)
				msgs = ((InternalEObject)ownedCompleteModel).eInverseRemove(this, 9, CompleteModel.class, msgs);
			if (newOwnedCompleteModel != null)
				msgs = ((InternalEObject)newOwnedCompleteModel).eInverseAdd(this, 9, CompleteModel.class, msgs);
			msgs = basicSetOwnedCompleteModel(newOwnedCompleteModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 6, newOwnedCompleteModel, newOwnedCompleteModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StandardLibrary getOwnedStandardLibrary()
	{
		return ownedStandardLibrary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedStandardLibrary(StandardLibrary newOwnedStandardLibrary, NotificationChain msgs)
	{
		StandardLibrary oldOwnedStandardLibrary = ownedStandardLibrary;
		ownedStandardLibrary = newOwnedStandardLibrary;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, 7, oldOwnedStandardLibrary, newOwnedStandardLibrary);
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
	public void setOwnedStandardLibrary(StandardLibrary newOwnedStandardLibrary)
	{
		if (newOwnedStandardLibrary != ownedStandardLibrary)
		{
			NotificationChain msgs = null;
			if (ownedStandardLibrary != null)
				msgs = ((InternalEObject)ownedStandardLibrary).eInverseRemove(this, 6, StandardLibrary.class, msgs);
			if (newOwnedStandardLibrary != null)
				msgs = ((InternalEObject)newOwnedStandardLibrary).eInverseAdd(this, 6, StandardLibrary.class, msgs);
			msgs = basicSetOwnedStandardLibrary(newOwnedStandardLibrary, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 7, newOwnedStandardLibrary, newOwnedStandardLibrary));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case 2:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case 4:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case 5:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case 6:
				if (ownedCompleteModel != null)
					msgs = ((InternalEObject)ownedCompleteModel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (6), null, msgs);
				return basicSetOwnedCompleteModel((CompleteModel)otherEnd, msgs);
			case 7:
				if (ownedStandardLibrary != null)
					msgs = ((InternalEObject)ownedStandardLibrary).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (7), null, msgs);
				return basicSetOwnedStandardLibrary((StandardLibrary)otherEnd, msgs);
		}
		return eDynamicInverseAdd(otherEnd, featureID, msgs);
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
			case 6:
				return basicSetOwnedCompleteModel(null, msgs);
			case 7:
				return basicSetOwnedStandardLibrary(null, msgs);
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
				return getOwnedCompleteModel();
			case 7:
				return getOwnedStandardLibrary();
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
				setOwnedCompleteModel((CompleteModel)newValue);
				return;
			case 7:
				setOwnedStandardLibrary((StandardLibrary)newValue);
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
				setOwnedCompleteModel((CompleteModel)null);
				return;
			case 7:
				setOwnedStandardLibrary((StandardLibrary)null);
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
				return ownedCompleteModel != null;
			case 7:
				return ownedStandardLibrary != null;
		}
		return eDynamicIsSet(featureID);
	}


} //CompleteEnvironmentImpl
