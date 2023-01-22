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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
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
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGNativeOperationCallExpImpl#isValueIsBoxed <em>Value Is Boxed</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGNativeOperationCallExpImpl#isValueIsEcore <em>Value Is Ecore</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGNativeOperationCallExpImpl#getCgThis <em>Cg This</em>}</li>
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
	 * The cached value of the '{@link #getCgThis() <em>Cg This</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCgThis()
	 * @generated
	 * @ordered
	 */
	protected CGValuedElement cgThis;

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
			eNotify(new ENotificationImpl(this, Notification.SET, 10, oldMethod, method));
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
			eNotify(new ENotificationImpl(this, Notification.SET, 11, oldValueIsBoxed, valueIsBoxed));
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
			eNotify(new ENotificationImpl(this, Notification.SET, 12, oldValueIsEcore, valueIsEcore));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CGValuedElement getCgThis() {
		return cgThis;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCgThis(CGValuedElement newCgThis, NotificationChain msgs) {
		CGValuedElement oldCgThis = cgThis;
		cgThis = newCgThis;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, 13, oldCgThis, newCgThis);
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
	public void setCgThis(CGValuedElement newCgThis) {
		if (newCgThis != cgThis) {
			NotificationChain msgs = null;
			if (cgThis != null)
				msgs = ((InternalEObject)cgThis).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (13), null, msgs);
			if (newCgThis != null)
				msgs = ((InternalEObject)newCgThis).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (13), null, msgs);
			msgs = basicSetCgThis(newCgThis, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 13, newCgThis, newCgThis));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case 13:
				return basicSetCgThis(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
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
			case 10:
				return getMethod();
			case 11:
				return isValueIsBoxed();
			case 12:
				return isValueIsEcore();
			case 13:
				return getCgThis();
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
			case 10:
				setMethod((Method)newValue);
				return;
			case 11:
				setValueIsBoxed((Boolean)newValue);
				return;
			case 12:
				setValueIsEcore((Boolean)newValue);
				return;
			case 13:
				setCgThis((CGValuedElement)newValue);
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
			case 10:
				setMethod(METHOD_EDEFAULT);
				return;
			case 11:
				setValueIsBoxed(VALUE_IS_BOXED_EDEFAULT);
				return;
			case 12:
				setValueIsEcore(VALUE_IS_ECORE_EDEFAULT);
				return;
			case 13:
				setCgThis((CGValuedElement)null);
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
			case 10:
				return METHOD_EDEFAULT == null ? method != null : !METHOD_EDEFAULT.equals(method);
			case 11:
				return valueIsBoxed != VALUE_IS_BOXED_EDEFAULT;
			case 12:
				return valueIsEcore != VALUE_IS_ECORE_EDEFAULT;
			case 13:
				return cgThis != null;
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
	 * @generated NOT XXX
	 */
	@Override
	public boolean isNonInvalid() {
		Method jMethod = method;
		if (jMethod != null) {
			Class<?>[] jExceptionTypes = jMethod.getExceptionTypes();
			if (jExceptionTypes.length == 1) {
				// All methods may throw an abstract VirtualMachineError, so hijack the throw of just a VirtualMachineError
				// as a @NoThrow CG-wise indication. Add another exception if VirtualMachineError really needs a mention.
				return jExceptionTypes[0] == VirtualMachineError.class;
			}
		}
		return false;
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
