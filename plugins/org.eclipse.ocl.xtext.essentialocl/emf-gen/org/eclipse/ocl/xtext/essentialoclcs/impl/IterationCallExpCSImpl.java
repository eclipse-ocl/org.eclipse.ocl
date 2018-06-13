/*******************************************************************************
 * Copyright (c) 2014, 2015 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.essentialoclcs.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage;
import org.eclipse.ocl.xtext.essentialoclcs.IterationCallExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.VariableCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Iteration Call Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.IterationCallExpCSImpl#getIterators <em>Iterators</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.IterationCallExpCSImpl#getReferredIteration <em>Referred Iteration</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class IterationCallExpCSImpl extends CallExpCSImpl implements IterationCallExpCS
{
	/**
	 * The cached value of the '{@link #getIterators() <em>Iterators</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIterators()
	 * @generated
	 * @ordered
	 */
	protected EList<VariableCS> iterators;

	/**
	 * The cached value of the '{@link #getReferredIteration() <em>Referred Iteration</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredIteration()
	 * @generated
	 * @ordered
	 */
	protected Iteration referredIteration;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IterationCallExpCSImpl()
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
		return EssentialOCLCSPackage.Literals.ITERATION_CALL_EXP_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Iteration getReferredIteration()
	{
		return referredIteration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReferredIteration(Iteration newReferredIteration)
	{
		Iteration oldReferredIteration = referredIteration;
		referredIteration = newReferredIteration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.ITERATION_CALL_EXP_CS__REFERRED_ITERATION, oldReferredIteration, referredIteration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<VariableCS> getIterators()
	{
		if (iterators == null)
		{
			iterators = new EObjectEList<VariableCS>(VariableCS.class, this, EssentialOCLCSPackage.ITERATION_CALL_EXP_CS__ITERATORS);
		}
		return iterators;
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
			case EssentialOCLCSPackage.ITERATION_CALL_EXP_CS__ITERATORS:
				return getIterators();
			case EssentialOCLCSPackage.ITERATION_CALL_EXP_CS__REFERRED_ITERATION:
				return getReferredIteration();
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
			case EssentialOCLCSPackage.ITERATION_CALL_EXP_CS__ITERATORS:
				getIterators().clear();
				getIterators().addAll((Collection<? extends VariableCS>)newValue);
				return;
			case EssentialOCLCSPackage.ITERATION_CALL_EXP_CS__REFERRED_ITERATION:
				setReferredIteration((Iteration)newValue);
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
			case EssentialOCLCSPackage.ITERATION_CALL_EXP_CS__ITERATORS:
				getIterators().clear();
				return;
			case EssentialOCLCSPackage.ITERATION_CALL_EXP_CS__REFERRED_ITERATION:
				setReferredIteration((Iteration)null);
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
			case EssentialOCLCSPackage.ITERATION_CALL_EXP_CS__ITERATORS:
				return iterators != null && !iterators.isEmpty();
			case EssentialOCLCSPackage.ITERATION_CALL_EXP_CS__REFERRED_ITERATION:
				return referredIteration != null;
		}
		return super.eIsSet(featureID);
	}

} //IterationCallExpCSImpl
