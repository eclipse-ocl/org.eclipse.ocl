/*******************************************************************************
 * Copyright (c) 2010, 2012 E.D.Willink and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.xtext.oclstdlibcs.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Precedence;
import org.eclipse.ocl.xtext.basecs.impl.OperationCSImpl;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;
import org.eclipse.ocl.xtext.oclstdlibcs.JavaClassCS;
import org.eclipse.ocl.xtext.oclstdlibcs.JavaImplementationCS;
import org.eclipse.ocl.xtext.oclstdlibcs.LibOperationCS;
import org.eclipse.ocl.xtext.oclstdlibcs.OCLstdlibCSPackage;
import org.eclipse.ocl.xtext.oclstdlibcs.util.OCLstdlibCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Lib Operation CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.oclstdlibcs.impl.LibOperationCSImpl#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.oclstdlibcs.impl.LibOperationCSImpl#isIsInvalidating <em>Is Invalidating</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.oclstdlibcs.impl.LibOperationCSImpl#isIsValidating <em>Is Validating</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.oclstdlibcs.impl.LibOperationCSImpl#getPrecedence <em>Precedence</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LibOperationCSImpl
		extends OperationCSImpl
		implements LibOperationCS {

	/**
	 * The cached value of the '{@link #getImplementation() <em>Implementation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementation()
	 * @generated
	 * @ordered
	 */
	protected JavaClassCS implementation;

	/**
	 * The default value of the '{@link #isIsInvalidating() <em>Is Invalidating</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsInvalidating()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_INVALIDATING_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsInvalidating() <em>Is Invalidating</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsInvalidating()
	 * @generated
	 * @ordered
	 */
	protected boolean isInvalidating = IS_INVALIDATING_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsValidating() <em>Is Validating</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsValidating()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_VALIDATING_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsValidating() <em>Is Validating</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsValidating()
	 * @generated
	 * @ordered
	 */
	protected boolean isValidating = IS_VALIDATING_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPrecedence() <em>Precedence</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecedence()
	 * @generated
	 * @ordered
	 */
	protected Precedence precedence;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LibOperationCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OCLstdlibCSPackage.Literals.LIB_OPERATION_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public JavaClassCS getImplementation() {
		if (implementation != null && implementation.eIsProxy())
		{
			InternalEObject oldImplementation = (InternalEObject)implementation;
			implementation = (JavaClassCS)eResolveProxy(oldImplementation);
			if (implementation != oldImplementation)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OCLstdlibCSPackage.LIB_OPERATION_CS__IMPLEMENTATION, oldImplementation, implementation));
			}
		}
		return implementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClassCS basicGetImplementation()
	{
		return implementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setImplementation(JavaClassCS newImplementation)
	{
		JavaClassCS oldImplementation = implementation;
		implementation = newImplementation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLstdlibCSPackage.LIB_OPERATION_CS__IMPLEMENTATION, oldImplementation, implementation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Precedence getPrecedence() {
		if (precedence != null && precedence.eIsProxy())
		{
			InternalEObject oldPrecedence = (InternalEObject)precedence;
			precedence = (Precedence)eResolveProxy(oldPrecedence);
			if (precedence != oldPrecedence)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OCLstdlibCSPackage.LIB_OPERATION_CS__PRECEDENCE, oldPrecedence, precedence));
			}
		}
		return precedence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Precedence basicGetPrecedence() {
		return precedence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPrecedence(Precedence newPrecedence) {
		Precedence oldPrecedence = precedence;
		precedence = newPrecedence;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLstdlibCSPackage.LIB_OPERATION_CS__PRECEDENCE, oldPrecedence, precedence));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsInvalidating()
	{
		return isInvalidating;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsInvalidating(boolean newIsInvalidating)
	{
		boolean oldIsInvalidating = isInvalidating;
		isInvalidating = newIsInvalidating;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLstdlibCSPackage.LIB_OPERATION_CS__IS_INVALIDATING, oldIsInvalidating, isInvalidating));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsValidating()
	{
		return isValidating;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsValidating(boolean newIsValidating)
	{
		boolean oldIsValidating = isValidating;
		isValidating = newIsValidating;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OCLstdlibCSPackage.LIB_OPERATION_CS__IS_VALIDATING, oldIsValidating, isValidating));
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
			case OCLstdlibCSPackage.LIB_OPERATION_CS__IMPLEMENTATION:
				if (resolve) return getImplementation();
				return basicGetImplementation();
			case OCLstdlibCSPackage.LIB_OPERATION_CS__IS_INVALIDATING:
				return isIsInvalidating();
			case OCLstdlibCSPackage.LIB_OPERATION_CS__IS_VALIDATING:
				return isIsValidating();
			case OCLstdlibCSPackage.LIB_OPERATION_CS__PRECEDENCE:
				if (resolve) return getPrecedence();
				return basicGetPrecedence();
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
			case OCLstdlibCSPackage.LIB_OPERATION_CS__IMPLEMENTATION:
				setImplementation((JavaClassCS)newValue);
				return;
			case OCLstdlibCSPackage.LIB_OPERATION_CS__IS_INVALIDATING:
				setIsInvalidating((Boolean)newValue);
				return;
			case OCLstdlibCSPackage.LIB_OPERATION_CS__IS_VALIDATING:
				setIsValidating((Boolean)newValue);
				return;
			case OCLstdlibCSPackage.LIB_OPERATION_CS__PRECEDENCE:
				setPrecedence((Precedence)newValue);
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
			case OCLstdlibCSPackage.LIB_OPERATION_CS__IMPLEMENTATION:
				setImplementation((JavaClassCS)null);
				return;
			case OCLstdlibCSPackage.LIB_OPERATION_CS__IS_INVALIDATING:
				setIsInvalidating(IS_INVALIDATING_EDEFAULT);
				return;
			case OCLstdlibCSPackage.LIB_OPERATION_CS__IS_VALIDATING:
				setIsValidating(IS_VALIDATING_EDEFAULT);
				return;
			case OCLstdlibCSPackage.LIB_OPERATION_CS__PRECEDENCE:
				setPrecedence((Precedence)null);
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
			case OCLstdlibCSPackage.LIB_OPERATION_CS__IMPLEMENTATION:
				return implementation != null;
			case OCLstdlibCSPackage.LIB_OPERATION_CS__IS_INVALIDATING:
				return isInvalidating != IS_INVALIDATING_EDEFAULT;
			case OCLstdlibCSPackage.LIB_OPERATION_CS__IS_VALIDATING:
				return isValidating != IS_VALIDATING_EDEFAULT;
			case OCLstdlibCSPackage.LIB_OPERATION_CS__PRECEDENCE:
				return precedence != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == JavaImplementationCS.class)
		{
			switch (derivedFeatureID)
			{
				case OCLstdlibCSPackage.LIB_OPERATION_CS__IMPLEMENTATION: return OCLstdlibCSPackage.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == JavaImplementationCS.class)
		{
			switch (baseFeatureID)
			{
				case OCLstdlibCSPackage.JAVA_IMPLEMENTATION_CS__IMPLEMENTATION: return OCLstdlibCSPackage.LIB_OPERATION_CS__IMPLEMENTATION;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public @Nullable <R> R accept(@NonNull BaseCSVisitor<R> visitor) {
		return (R) ((OCLstdlibCSVisitor<?>)visitor).visitLibOperationCS(this);
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
} //LibOperationCSImpl
