/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.completeoclcs.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.ConstraintCS;
import org.eclipse.ocl.xtext.basecs.ParameterCS;
import org.eclipse.ocl.xtext.basecs.TemplateSignatureCS;
import org.eclipse.ocl.xtext.basecs.TemplateableElementCS;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;
import org.eclipse.ocl.xtext.completeoclcs.CompleteOCLCSPackage;
import org.eclipse.ocl.xtext.completeoclcs.OperationContextDeclCS;
import org.eclipse.ocl.xtext.completeoclcs.util.CompleteOCLCSVisitor;
import org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSFactory;
import org.eclipse.ocl.xtext.essentialoclcs.ExpSpecificationCS;
import org.eclipse.ocl.xtext.essentialoclcs.VariableCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation Context Decl CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.completeoclcs.impl.OperationContextDeclCSImpl#getOwnedSignature <em>Owned Signature</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.completeoclcs.impl.OperationContextDeclCSImpl#getOwnedBodies <em>Owned Bodies</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.completeoclcs.impl.OperationContextDeclCSImpl#getOwnedParameters <em>Owned Parameters</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.completeoclcs.impl.OperationContextDeclCSImpl#getOwnedPostconditions <em>Owned Postconditions</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.completeoclcs.impl.OperationContextDeclCSImpl#getOwnedPreconditions <em>Owned Preconditions</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.completeoclcs.impl.OperationContextDeclCSImpl#getOwnedResult <em>Owned Result</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.completeoclcs.impl.OperationContextDeclCSImpl#getReferredOperation <em>Referred Operation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OperationContextDeclCSImpl
		extends FeatureContextDeclCSImpl
		implements OperationContextDeclCS {

	/**
	 * The cached value of the '{@link #getOwnedSignature() <em>Owned Signature</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSignature()
	 * @generated
	 * @ordered
	 */
	protected TemplateSignatureCS ownedSignature;

	/**
	 * The cached value of the '{@link #getOwnedBodies() <em>Owned Bodies</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedBodies()
	 * @generated
	 * @ordered
	 */
	protected EList<ExpSpecificationCS> ownedBodies;

	/**
	 * The cached value of the '{@link #getOwnedParameters() <em>Owned Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<ParameterCS> ownedParameters;

	/**
	 * The cached value of the '{@link #getOwnedPostconditions() <em>Owned Postconditions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedPostconditions()
	 * @generated
	 * @ordered
	 */
	protected EList<ConstraintCS> ownedPostconditions;

	/**
	 * The cached value of the '{@link #getOwnedPreconditions() <em>Owned Preconditions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedPreconditions()
	 * @generated
	 * @ordered
	 */
	protected EList<ConstraintCS> ownedPreconditions;

	/**
	 * The cached value of the '{@link #getOwnedResult() <em>Owned Result</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedResult()
	 * @generated
	 * @ordered
	 */
	protected VariableCS ownedResult;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationContextDeclCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CompleteOCLCSPackage.Literals.OPERATION_CONTEXT_DECL_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TemplateSignatureCS getOwnedSignature()
	{
		return ownedSignature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedSignature(TemplateSignatureCS newOwnedSignature, NotificationChain msgs)
	{
		TemplateSignatureCS oldOwnedSignature = ownedSignature;
		ownedSignature = newOwnedSignature;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_SIGNATURE, oldOwnedSignature, newOwnedSignature);
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
	public void setOwnedSignature(TemplateSignatureCS newOwnedSignature)
	{
		if (newOwnedSignature != ownedSignature)
		{
			NotificationChain msgs = null;
			if (ownedSignature != null)
				msgs = ((InternalEObject)ownedSignature).eInverseRemove(this, BaseCSPackage.TEMPLATE_SIGNATURE_CS__OWNING_ELEMENT, TemplateSignatureCS.class, msgs);
			if (newOwnedSignature != null)
				msgs = ((InternalEObject)newOwnedSignature).eInverseAdd(this, BaseCSPackage.TEMPLATE_SIGNATURE_CS__OWNING_ELEMENT, TemplateSignatureCS.class, msgs);
			msgs = basicSetOwnedSignature(newOwnedSignature, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_SIGNATURE, newOwnedSignature, newOwnedSignature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ParameterCS> getOwnedParameters() {
		if (ownedParameters == null)
		{
			ownedParameters = new EObjectContainmentEList<ParameterCS>(ParameterCS.class, this, CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS);
		}
		return ownedParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public VariableCS getOwnedResult() {
		if (ownedResult == null) {
			VariableCS resultVariable = EssentialOCLCSFactory.eINSTANCE
				.createVariableCS();
			resultVariable.setName(PivotConstants.RESULT_NAME);
			// FIXME			resultVariable.setType(getType());
			setOwnedResult(resultVariable);
		}
		return ownedResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedResult(VariableCS newOwnedResult, NotificationChain msgs)
	{
		VariableCS oldOwnedResult = ownedResult;
		ownedResult = newOwnedResult;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_RESULT, oldOwnedResult, newOwnedResult);
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
	public void setOwnedResult(VariableCS newOwnedResult) {
		if (newOwnedResult != ownedResult)
		{
			NotificationChain msgs = null;
			if (ownedResult != null)
				msgs = ((InternalEObject)ownedResult).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_RESULT, null, msgs);
			if (newOwnedResult != null)
				msgs = ((InternalEObject)newOwnedResult).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_RESULT, null, msgs);
			msgs = basicSetOwnedResult(newOwnedResult, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_RESULT, newOwnedResult, newOwnedResult));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ConstraintCS> getOwnedPreconditions() {
		if (ownedPreconditions == null)
		{
			ownedPreconditions = new EObjectContainmentEList<ConstraintCS>(ConstraintCS.class, this, CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS);
		}
		return ownedPreconditions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ConstraintCS> getOwnedPostconditions() {
		if (ownedPostconditions == null)
		{
			ownedPostconditions = new EObjectContainmentEList<ConstraintCS>(ConstraintCS.class, this, CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS);
		}
		return ownedPostconditions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ExpSpecificationCS> getOwnedBodies() {
		if (ownedBodies == null)
		{
			ownedBodies = new EObjectContainmentEList<ExpSpecificationCS>(ExpSpecificationCS.class, this, CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES);
		}
		return ownedBodies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_SIGNATURE:
				if (ownedSignature != null)
					msgs = ((InternalEObject)ownedSignature).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_SIGNATURE, null, msgs);
				return basicSetOwnedSignature((TemplateSignatureCS)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
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
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_SIGNATURE:
				return basicSetOwnedSignature(null, msgs);
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES:
				return ((InternalEList<?>)getOwnedBodies()).basicRemove(otherEnd, msgs);
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS:
				return ((InternalEList<?>)getOwnedParameters()).basicRemove(otherEnd, msgs);
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS:
				return ((InternalEList<?>)getOwnedPostconditions()).basicRemove(otherEnd, msgs);
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS:
				return ((InternalEList<?>)getOwnedPreconditions()).basicRemove(otherEnd, msgs);
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_RESULT:
				return basicSetOwnedResult(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
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
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_SIGNATURE:
				return getOwnedSignature();
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES:
				return getOwnedBodies();
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS:
				return getOwnedParameters();
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS:
				return getOwnedPostconditions();
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS:
				return getOwnedPreconditions();
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_RESULT:
				return getOwnedResult();
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__REFERRED_OPERATION:
				return getReferredOperation();
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
	public void eSet(int featureID, Object newValue) {
		switch (featureID)
		{
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignatureCS)newValue);
				return;
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES:
				getOwnedBodies().clear();
				getOwnedBodies().addAll((Collection<? extends ExpSpecificationCS>)newValue);
				return;
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS:
				getOwnedParameters().clear();
				getOwnedParameters().addAll((Collection<? extends ParameterCS>)newValue);
				return;
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS:
				getOwnedPostconditions().clear();
				getOwnedPostconditions().addAll((Collection<? extends ConstraintCS>)newValue);
				return;
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS:
				getOwnedPreconditions().clear();
				getOwnedPreconditions().addAll((Collection<? extends ConstraintCS>)newValue);
				return;
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_RESULT:
				setOwnedResult((VariableCS)newValue);
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
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignatureCS)null);
				return;
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES:
				getOwnedBodies().clear();
				return;
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS:
				getOwnedParameters().clear();
				return;
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS:
				getOwnedPostconditions().clear();
				return;
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS:
				getOwnedPreconditions().clear();
				return;
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_RESULT:
				setOwnedResult((VariableCS)null);
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
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_SIGNATURE:
				return ownedSignature != null;
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_BODIES:
				return ownedBodies != null && !ownedBodies.isEmpty();
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_PARAMETERS:
				return ownedParameters != null && !ownedParameters.isEmpty();
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_POSTCONDITIONS:
				return ownedPostconditions != null && !ownedPostconditions.isEmpty();
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_PRECONDITIONS:
				return ownedPreconditions != null && !ownedPreconditions.isEmpty();
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_RESULT:
				return ownedResult != null;
			case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__REFERRED_OPERATION:
				return getReferredOperation() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
	{
		if (baseClass == TemplateableElementCS.class)
		{
			switch (derivedFeatureID)
			{
				case CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_SIGNATURE: return BaseCSPackage.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE;
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
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
	{
		if (baseClass == TemplateableElementCS.class)
		{
			switch (baseFeatureID)
			{
				case BaseCSPackage.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE: return CompleteOCLCSPackage.OPERATION_CONTEXT_DECL_CS__OWNED_SIGNATURE;
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
	public <R> R accept(@NonNull BaseCSVisitor<R> visitor) {
		return (R) ((CompleteOCLCSVisitor<?>)visitor).visitOperationContextDeclCS(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Operation getReferredOperation() {
		if (ownedPathName == null) {
			return null;
		}
		return (Operation) ownedPathName.getReferredElement();
	}
} //OperationContextDeclCSImpl
