/*******************************************************************************
 * Copyright (c) 2014, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.cgmodel.impl;

import java.lang.reflect.Method;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.util.CGModelVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CG Method Operation Call Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGNativeOperationCallExpImpl#getMethod <em>Method</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGNativeOperationCallExpImpl#isThisIsSelf <em>This Is Self</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGNativeOperationCallExpImpl#isValueIsBoxed <em>Value Is Boxed</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGNativeOperationCallExpImpl#isValueIsEcore <em>Value Is Ecore</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CGNativeOperationCallExpImpl extends CGOperationCallExpImpl implements CGNativeOperationCallExp {
	/**
	 * The number of structural features of the '<em>CG Native Operation Call Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CG_NATIVE_OPERATION_CALL_EXP_FEATURE_COUNT = CGOperationCallExpImpl.CG_OPERATION_CALL_EXP_FEATURE_COUNT + 4;

	/**
	 * The default value of the '{@link #getMethod() <em>Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethod()
	 * @generated
	 * @ordered
	 */
	protected static final Method METHOD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMethod() <em>Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethod()
	 * @generated
	 * @ordered
	 */
	protected Method method = METHOD_EDEFAULT;

	/**
	 * The default value of the '{@link #isThisIsSelf() <em>This Is Self</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isThisIsSelf()
	 * @generated
	 * @ordered
	 */
	protected static final boolean THIS_IS_SELF_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isThisIsSelf() <em>This Is Self</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isThisIsSelf()
	 * @generated
	 * @ordered
	 */
	protected boolean thisIsSelf = THIS_IS_SELF_EDEFAULT;

	/**
	 * The default value of the '{@link #isValueIsBoxed() <em>Value Is Boxed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isValueIsBoxed()
	 * @generated
	 * @ordered
	 */
	protected static final boolean VALUE_IS_BOXED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isValueIsBoxed() <em>Value Is Boxed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isValueIsBoxed()
	 * @generated
	 * @ordered
	 */
	protected boolean valueIsBoxed = VALUE_IS_BOXED_EDEFAULT;

	/**
	 * The default value of the '{@link #isValueIsEcore() <em>Value Is Ecore</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isValueIsEcore()
	 * @generated
	 * @ordered
	 */
	protected static final boolean VALUE_IS_ECORE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isValueIsEcore() <em>Value Is Ecore</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isValueIsEcore()
	 * @generated
	 * @ordered
	 */
	protected boolean valueIsEcore = VALUE_IS_ECORE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CGNativeOperationCallExpImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CGModelPackage.Literals.CG_NATIVE_OPERATION_CALL_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Method getMethod() {
		return method;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMethod(Method newMethod) {
		Method oldMethod = method;
		method = newMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 12, oldMethod, method));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isThisIsSelf() {
		return thisIsSelf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setThisIsSelf(boolean newThisIsSelf) {
		boolean oldThisIsSelf = thisIsSelf;
		thisIsSelf = newThisIsSelf;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 13, oldThisIsSelf, thisIsSelf));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isValueIsBoxed() {
		return valueIsBoxed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setValueIsBoxed(boolean newValueIsBoxed) {
		boolean oldValueIsBoxed = valueIsBoxed;
		valueIsBoxed = newValueIsBoxed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 14, oldValueIsBoxed, valueIsBoxed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isValueIsEcore() {
		return valueIsEcore;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setValueIsEcore(boolean newValueIsEcore) {
		boolean oldValueIsEcore = valueIsEcore;
		valueIsEcore = newValueIsEcore;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 15, oldValueIsEcore, valueIsEcore));
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case 12:
				return getMethod();
			case 13:
				return isThisIsSelf();
			case 14:
				return isValueIsBoxed();
			case 15:
				return isValueIsEcore();
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
		switch (featureID) {
			case 12:
				setMethod((Method)newValue);
				return;
			case 13:
				setThisIsSelf((Boolean)newValue);
				return;
			case 14:
				setValueIsBoxed((Boolean)newValue);
				return;
			case 15:
				setValueIsEcore((Boolean)newValue);
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
		switch (featureID) {
			case 12:
				setMethod(METHOD_EDEFAULT);
				return;
			case 13:
				setThisIsSelf(THIS_IS_SELF_EDEFAULT);
				return;
			case 14:
				setValueIsBoxed(VALUE_IS_BOXED_EDEFAULT);
				return;
			case 15:
				setValueIsEcore(VALUE_IS_ECORE_EDEFAULT);
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
		switch (featureID) {
			case 12:
				return METHOD_EDEFAULT == null ? method != null : !METHOD_EDEFAULT.equals(method);
			case 13:
				return thisIsSelf != THIS_IS_SELF_EDEFAULT;
			case 14:
				return valueIsBoxed != VALUE_IS_BOXED_EDEFAULT;
			case 15:
				return valueIsEcore != VALUE_IS_ECORE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull CGModelVisitor<R> visitor) {
		return visitor.visitCGNativeOperationCallExp(this);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isBoxed() {
		return valueIsBoxed;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isEcore() {
		return valueIsEcore;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isNonInvalid() {
		if (method == null) {
			return false;
		}
		return method.getExceptionTypes().length == 0;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isUnboxed() {
		return !valueIsBoxed && !valueIsEcore;
	}

} //CGMethodOperationCallExpImpl
