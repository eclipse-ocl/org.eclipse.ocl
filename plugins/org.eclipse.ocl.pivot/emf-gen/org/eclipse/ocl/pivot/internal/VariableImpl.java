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
package org.eclipse.ocl.pivot.internal;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ValueSpecification;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.VariableImpl#isIsImplicit <em>Is Implicit</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.VariableImpl#getOwnedInit <em>Owned Init</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.VariableImpl#getRepresentedParameter <em>Represented Parameter</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VariableImpl
extends VariableDeclarationImpl
implements Variable {

	/**
	 * The default value of the '{@link #isIsImplicit() <em>Is Implicit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsImplicit()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_IMPLICIT_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isIsImplicit() <em>Is Implicit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsImplicit()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_IMPLICIT_EFLAG = 1 << 9;

	/**
	 * The cached value of the '{@link #getOwnedInit() <em>Owned Init</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedInit()
	 * @generated
	 * @ordered
	 */
	protected OCLExpression ownedInit;

	/**
	 * The cached value of the '{@link #getRepresentedParameter() <em>Represented Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepresentedParameter()
	 * @generated
	 * @ordered
	 */
	protected Parameter representedParameter;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VariableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.VARIABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsImplicit()
	{
		return (eFlags & IS_IMPLICIT_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OCLExpression getOwnedInit() {
		return ownedInit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedInit(OCLExpression newOwnedInit, NotificationChain msgs)
	{
		OCLExpression oldOwnedInit = ownedInit;
		ownedInit = newOwnedInit;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.VARIABLE__OWNED_INIT, oldOwnedInit, newOwnedInit);
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
	public void setOwnedInit(OCLExpression newOwnedInit) {
		if (newOwnedInit != ownedInit)
		{
			NotificationChain msgs = null;
			if (ownedInit != null)
				msgs = ((InternalEObject)ownedInit).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.VARIABLE__OWNED_INIT, null, msgs);
			if (newOwnedInit != null)
				msgs = ((InternalEObject)newOwnedInit).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PivotPackage.VARIABLE__OWNED_INIT, null, msgs);
			msgs = basicSetOwnedInit(newOwnedInit, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.VARIABLE__OWNED_INIT, newOwnedInit, newOwnedInit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Parameter getRepresentedParameter() {
		if (representedParameter != null && representedParameter.eIsProxy())
		{
			InternalEObject oldRepresentedParameter = (InternalEObject)representedParameter;
			representedParameter = (Parameter)eResolveProxy(oldRepresentedParameter);
			if (representedParameter != oldRepresentedParameter)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.VARIABLE__REPRESENTED_PARAMETER, oldRepresentedParameter, representedParameter));
			}
		}
		return representedParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Parameter basicGetRepresentedParameter() {
		return representedParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRepresentedParameter(Parameter newRepresentedParameter) {
		Parameter oldRepresentedParameter = representedParameter;
		representedParameter = newRepresentedParameter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.VARIABLE__REPRESENTED_PARAMETER, oldRepresentedParameter, representedParameter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateCompatibleInitialiserType(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 * inv CompatibleInitialiserType: true
		 */
		return ValueUtil.TRUE_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsImplicit(boolean newIsImplicit)
	{
		boolean oldIsImplicit = (eFlags & IS_IMPLICIT_EFLAG) != 0;
		if (newIsImplicit) eFlags |= IS_IMPLICIT_EFLAG; else eFlags &= ~IS_IMPLICIT_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.VARIABLE__IS_IMPLICIT, oldIsImplicit, newIsImplicit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID)
		{
			case PivotPackage.VARIABLE__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.VARIABLE__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.VARIABLE__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.VARIABLE__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.VARIABLE__OWNED_INIT:
				return basicSetOwnedInit(null, msgs);
		}
		return eDynamicInverseRemove(otherEnd, featureID, msgs);
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
			case PivotPackage.VARIABLE__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.VARIABLE__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.VARIABLE__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.VARIABLE__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.VARIABLE__NAME:
				return getName();
			case PivotPackage.VARIABLE__IS_MANY:
				return isIsMany();
			case PivotPackage.VARIABLE__IS_REQUIRED:
				return isIsRequired();
			case PivotPackage.VARIABLE__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case PivotPackage.VARIABLE__TYPE_VALUE:
				return getTypeValue();
			case PivotPackage.VARIABLE__IS_IMPLICIT:
				return isIsImplicit();
			case PivotPackage.VARIABLE__OWNED_INIT:
				return getOwnedInit();
			case PivotPackage.VARIABLE__REPRESENTED_PARAMETER:
				if (resolve) return getRepresentedParameter();
				return basicGetRepresentedParameter();
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
	public void eSet(int featureID, Object newValue) {
		switch (featureID)
		{
			case PivotPackage.VARIABLE__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.VARIABLE__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.VARIABLE__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.VARIABLE__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.VARIABLE__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.VARIABLE__IS_REQUIRED:
				setIsRequired((Boolean)newValue);
				return;
			case PivotPackage.VARIABLE__TYPE:
				setType((Type)newValue);
				return;
			case PivotPackage.VARIABLE__TYPE_VALUE:
				setTypeValue((Type)newValue);
				return;
			case PivotPackage.VARIABLE__IS_IMPLICIT:
				setIsImplicit((Boolean)newValue);
				return;
			case PivotPackage.VARIABLE__OWNED_INIT:
				setOwnedInit((OCLExpression)newValue);
				return;
			case PivotPackage.VARIABLE__REPRESENTED_PARAMETER:
				setRepresentedParameter((Parameter)newValue);
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
	public void eUnset(int featureID) {
		switch (featureID)
		{
			case PivotPackage.VARIABLE__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.VARIABLE__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.VARIABLE__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.VARIABLE__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.VARIABLE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.VARIABLE__IS_REQUIRED:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case PivotPackage.VARIABLE__TYPE:
				setType((Type)null);
				return;
			case PivotPackage.VARIABLE__TYPE_VALUE:
				setTypeValue((Type)null);
				return;
			case PivotPackage.VARIABLE__IS_IMPLICIT:
				setIsImplicit(IS_IMPLICIT_EDEFAULT);
				return;
			case PivotPackage.VARIABLE__OWNED_INIT:
				setOwnedInit((OCLExpression)null);
				return;
			case PivotPackage.VARIABLE__REPRESENTED_PARAMETER:
				setRepresentedParameter((Parameter)null);
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
	public boolean eIsSet(int featureID) {
		switch (featureID)
		{
			case PivotPackage.VARIABLE__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.VARIABLE__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.VARIABLE__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.VARIABLE__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.VARIABLE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.VARIABLE__IS_MANY:
				return isIsMany() != IS_MANY_EDEFAULT;
			case PivotPackage.VARIABLE__IS_REQUIRED:
				return ((eFlags & IS_REQUIRED_EFLAG) != 0) != IS_REQUIRED_EDEFAULT;
			case PivotPackage.VARIABLE__TYPE:
				return type != null;
			case PivotPackage.VARIABLE__TYPE_VALUE:
				return typeValue != null;
			case PivotPackage.VARIABLE__IS_IMPLICIT:
				return ((eFlags & IS_IMPLICIT_EFLAG) != 0) != IS_IMPLICIT_EDEFAULT;
			case PivotPackage.VARIABLE__OWNED_INIT:
				return ownedInit != null;
			case PivotPackage.VARIABLE__REPRESENTED_PARAMETER:
				return representedParameter != null;
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException
	{
		switch (operationID)
		{
			case PivotPackage.VARIABLE___ALL_OWNED_ELEMENTS:
				return allOwnedElements();
			case PivotPackage.VARIABLE___GET_VALUE__TYPE_STRING:
				return getValue((Type)arguments.get(0), (String)arguments.get(1));
			case PivotPackage.VARIABLE___COMPATIBLE_BODY__VALUESPECIFICATION:
				return CompatibleBody((ValueSpecification)arguments.get(0));
			case PivotPackage.VARIABLE___VALIDATE_NAME_IS_NOT_NULL__DIAGNOSTICCHAIN_MAP:
				return validateNameIsNotNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.VARIABLE___VALIDATE_TYPE_IS_NOT_INVALID__DIAGNOSTICCHAIN_MAP:
				return validateTypeIsNotInvalid((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.VARIABLE___VALIDATE_TYPE_IS_NOT_NULL__DIAGNOSTICCHAIN_MAP:
				return validateTypeIsNotNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.VARIABLE___VALIDATE_COMPATIBLE_INITIALISER_TYPE__DIAGNOSTICCHAIN_MAP:
				return validateCompatibleInitialiserType((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
		}
		return eDynamicInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String toString()
	{
		return super.toString();
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitVariable(this);
	}
} //VariableImpl
