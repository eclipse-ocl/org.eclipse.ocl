/*******************************************************************************
 * Copyright (c) 2010, 2015 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.PivotConstants;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expression In Ocl</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ExpressionInOCLImpl#getOwnedBody <em>Owned Body</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ExpressionInOCLImpl#getOwnedContext <em>Owned Context</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ExpressionInOCLImpl#getOwnedParameters <em>Owned Parameters</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ExpressionInOCLImpl#getOwnedResult <em>Owned Result</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExpressionInOCLImpl
extends LanguageExpressionImpl
implements ExpressionInOCL {

	/**
	 * The cached value of the '{@link #getOwnedBody() <em>Owned Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedBody()
	 * @generated
	 * @ordered
	 */
	protected OCLExpression ownedBody;

	/**
	 * The cached value of the '{@link #getOwnedContext() <em>Owned Context</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedContext()
	 * @generated
	 * @ordered
	 */
	protected Variable ownedContext;

	/**
	 * The cached value of the '{@link #getOwnedParameters() <em>Owned Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<Variable> ownedParameters;

	/**
	 * The cached value of the '{@link #getOwnedResult() <em>Owned Result</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedResult()
	 * @generated
	 * @ordered
	 */
	protected Variable ownedResult;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExpressionInOCLImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.EXPRESSION_IN_OCL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OCLExpression getOwnedBody() {
		return ownedBody;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedBody(OCLExpression newOwnedBody, NotificationChain msgs)
	{
		OCLExpression oldOwnedBody = ownedBody;
		ownedBody = newOwnedBody;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.EXPRESSION_IN_OCL__OWNED_BODY, oldOwnedBody, newOwnedBody);
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
	public void setOwnedBody(OCLExpression newOwnedBody) {
		if (newOwnedBody != ownedBody)
		{
			NotificationChain msgs = null;
			if (ownedBody != null)
				msgs = ((InternalEObject)ownedBody).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.EXPRESSION_IN_OCL__OWNED_BODY, null, msgs);
			if (newOwnedBody != null)
				msgs = ((InternalEObject)newOwnedBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PivotPackage.EXPRESSION_IN_OCL__OWNED_BODY, null, msgs);
			msgs = basicSetOwnedBody(newOwnedBody, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.EXPRESSION_IN_OCL__OWNED_BODY, newOwnedBody, newOwnedBody));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Variable getOwnedContext() {
		return ownedContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedContext(Variable newOwnedContext, NotificationChain msgs)
	{
		Variable oldOwnedContext = ownedContext;
		ownedContext = newOwnedContext;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.EXPRESSION_IN_OCL__OWNED_CONTEXT, oldOwnedContext, newOwnedContext);
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
	public void setOwnedContext(Variable newOwnedContext) {
		if (newOwnedContext != ownedContext)
		{
			NotificationChain msgs = null;
			if (ownedContext != null)
				msgs = ((InternalEObject)ownedContext).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.EXPRESSION_IN_OCL__OWNED_CONTEXT, null, msgs);
			if (newOwnedContext != null)
				msgs = ((InternalEObject)newOwnedContext).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PivotPackage.EXPRESSION_IN_OCL__OWNED_CONTEXT, null, msgs);
			msgs = basicSetOwnedContext(newOwnedContext, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.EXPRESSION_IN_OCL__OWNED_CONTEXT, newOwnedContext, newOwnedContext));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Variable getOwnedResult() {
		return ownedResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedResult(Variable newOwnedResult, NotificationChain msgs)
	{
		Variable oldOwnedResult = ownedResult;
		ownedResult = newOwnedResult;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.EXPRESSION_IN_OCL__OWNED_RESULT, oldOwnedResult, newOwnedResult);
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
	public void setOwnedResult(Variable newOwnedResult) {
		if (newOwnedResult != ownedResult)
		{
			NotificationChain msgs = null;
			if (ownedResult != null)
				msgs = ((InternalEObject)ownedResult).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.EXPRESSION_IN_OCL__OWNED_RESULT, null, msgs);
			if (newOwnedResult != null)
				msgs = ((InternalEObject)newOwnedResult).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PivotPackage.EXPRESSION_IN_OCL__OWNED_RESULT, null, msgs);
			msgs = basicSetOwnedResult(newOwnedResult, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.EXPRESSION_IN_OCL__OWNED_RESULT, newOwnedResult, newOwnedResult));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("null")
	public @NonNull List<Variable> getOwnedParameters()
	{
		if (ownedParameters == null)
		{
			ownedParameters = new EObjectContainmentEList<Variable>(Variable.class, this, PivotPackage.EXPRESSION_IN_OCL__OWNED_PARAMETERS);
		}
		return ownedParameters;
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
			case PivotPackage.EXPRESSION_IN_OCL__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.EXPRESSION_IN_OCL__OWNING_CONSTRAINT:
				return basicSetOwningConstraint(null, msgs);
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_BODY:
				return basicSetOwnedBody(null, msgs);
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_CONTEXT:
				return basicSetOwnedContext(null, msgs);
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_PARAMETERS:
				return ((InternalEList<?>)getOwnedParameters()).basicRemove(otherEnd, msgs);
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_RESULT:
				return basicSetOwnedResult(null, msgs);
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
			case PivotPackage.EXPRESSION_IN_OCL__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.EXPRESSION_IN_OCL__NAME:
				return getName();
			case PivotPackage.EXPRESSION_IN_OCL__IS_MANY:
				return isIsMany();
			case PivotPackage.EXPRESSION_IN_OCL__IS_REQUIRED:
				return isIsRequired();
			case PivotPackage.EXPRESSION_IN_OCL__TYPE:
				if (resolve) return getRawType();
				return basicGetType();
			case PivotPackage.EXPRESSION_IN_OCL__BODY:
				return getBody();
			case PivotPackage.EXPRESSION_IN_OCL__LANGUAGE:
				return getLanguage();
			case PivotPackage.EXPRESSION_IN_OCL__OWNING_CONSTRAINT:
				return getOwningConstraint();
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_BODY:
				return getOwnedBody();
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_CONTEXT:
				return getOwnedContext();
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_PARAMETERS:
				return getOwnedParameters();
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_RESULT:
				return getOwnedResult();
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
			case PivotPackage.EXPRESSION_IN_OCL__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.EXPRESSION_IN_OCL__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.EXPRESSION_IN_OCL__IS_REQUIRED:
				setIsRequired((Boolean)newValue);
				return;
			case PivotPackage.EXPRESSION_IN_OCL__TYPE:
				setType((Type)newValue);
				return;
			case PivotPackage.EXPRESSION_IN_OCL__BODY:
				setBody((String)newValue);
				return;
			case PivotPackage.EXPRESSION_IN_OCL__OWNING_CONSTRAINT:
				setOwningConstraint((Constraint)newValue);
				return;
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_BODY:
				setOwnedBody((OCLExpression)newValue);
				return;
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_CONTEXT:
				setOwnedContext((Variable)newValue);
				return;
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_PARAMETERS:
				getOwnedParameters().clear();
				getOwnedParameters().addAll((Collection<? extends Variable>)newValue);
				return;
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_RESULT:
				setOwnedResult((Variable)newValue);
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
			case PivotPackage.EXPRESSION_IN_OCL__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.EXPRESSION_IN_OCL__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.EXPRESSION_IN_OCL__IS_REQUIRED:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case PivotPackage.EXPRESSION_IN_OCL__TYPE:
				setType((Type)null);
				return;
			case PivotPackage.EXPRESSION_IN_OCL__BODY:
				setBody(BODY_EDEFAULT);
				return;
			case PivotPackage.EXPRESSION_IN_OCL__OWNING_CONSTRAINT:
				setOwningConstraint((Constraint)null);
				return;
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_BODY:
				setOwnedBody((OCLExpression)null);
				return;
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_CONTEXT:
				setOwnedContext((Variable)null);
				return;
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_PARAMETERS:
				getOwnedParameters().clear();
				return;
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_RESULT:
				setOwnedResult((Variable)null);
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
			case PivotPackage.EXPRESSION_IN_OCL__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.EXPRESSION_IN_OCL__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.EXPRESSION_IN_OCL__IS_MANY:
				return isIsMany() != IS_MANY_EDEFAULT;
			case PivotPackage.EXPRESSION_IN_OCL__IS_REQUIRED:
				return ((eFlags & IS_REQUIRED_EFLAG) != 0) != IS_REQUIRED_EDEFAULT;
			case PivotPackage.EXPRESSION_IN_OCL__TYPE:
				return type != null;
			case PivotPackage.EXPRESSION_IN_OCL__BODY:
				return BODY_EDEFAULT == null ? body != null : !BODY_EDEFAULT.equals(body);
			case PivotPackage.EXPRESSION_IN_OCL__LANGUAGE:
				return LANGUAGE_EDEFAULT == null ? getLanguage() != null : !LANGUAGE_EDEFAULT.equals(getLanguage());
			case PivotPackage.EXPRESSION_IN_OCL__OWNING_CONSTRAINT:
				return getOwningConstraint() != null;
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_BODY:
				return ownedBody != null;
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_CONTEXT:
				return ownedContext != null;
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_PARAMETERS:
				return ownedParameters != null && !ownedParameters.isEmpty();
			case PivotPackage.EXPRESSION_IN_OCL__OWNED_RESULT:
				return ownedResult != null;
		}
		return eDynamicIsSet(featureID);
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

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitExpressionInOCL(this);
	}

	@Override
	public String getLanguage() {
		return PivotConstants.OCL_LANGUAGE;
	}
} //ExpressionInOCLImpl
