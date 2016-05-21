/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.oclstdlibcs.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.basecs.impl.NamedElementCSImpl;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;
import org.eclipse.ocl.xtext.oclstdlibcs.OCLstdlibCSPackage;
import org.eclipse.ocl.xtext.oclstdlibcs.PrecedenceCS;
import org.eclipse.ocl.xtext.oclstdlibcs.util.OCLstdlibCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Precedence CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.oclstdlibcs.impl.PrecedenceCSImpl#isIsRightAssociative <em>Is Right Associative</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PrecedenceCSImpl
		extends NamedElementCSImpl
		implements PrecedenceCS {

	/**
	 * The default value of the '{@link #isIsRightAssociative() <em>Is Right Associative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsRightAssociative()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_RIGHT_ASSOCIATIVE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsRightAssociative() <em>Is Right Associative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsRightAssociative()
	 * @generated
	 * @ordered
	 */
	protected boolean isRightAssociative = IS_RIGHT_ASSOCIATIVE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PrecedenceCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OCLstdlibCSPackage.Literals.PRECEDENCE_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsRightAssociative() {
		return isRightAssociative;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsRightAssociative(boolean newIsRightAssociative) {
		boolean oldIsRightAssociative = isRightAssociative;
		isRightAssociative = newIsRightAssociative;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLstdlibCSPackage.PRECEDENCE_CS__IS_RIGHT_ASSOCIATIVE, oldIsRightAssociative, isRightAssociative));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID)
		{
			case OCLstdlibCSPackage.PRECEDENCE_CS__IS_RIGHT_ASSOCIATIVE:
				return isIsRightAssociative();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID)
		{
			case OCLstdlibCSPackage.PRECEDENCE_CS__IS_RIGHT_ASSOCIATIVE:
				setIsRightAssociative((Boolean)newValue);
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
	public void eUnset(int featureID) {
		switch (featureID)
		{
			case OCLstdlibCSPackage.PRECEDENCE_CS__IS_RIGHT_ASSOCIATIVE:
				setIsRightAssociative(IS_RIGHT_ASSOCIATIVE_EDEFAULT);
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
	public boolean eIsSet(int featureID) {
		switch (featureID)
		{
			case OCLstdlibCSPackage.PRECEDENCE_CS__IS_RIGHT_ASSOCIATIVE:
				return isRightAssociative != IS_RIGHT_ASSOCIATIVE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <R> R accept(@NonNull BaseCSVisitor<R> visitor) {
		return (R) ((OCLstdlibCSVisitor<?>)visitor).visitPrecedenceCS(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString()
	{
		return super.toString();
	}
} //PrecedenceCSImpl
