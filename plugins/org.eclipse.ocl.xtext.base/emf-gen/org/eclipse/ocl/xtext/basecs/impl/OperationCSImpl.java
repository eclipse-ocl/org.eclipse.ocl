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

package org.eclipse.ocl.xtext.basecs.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.ConstraintCS;
import org.eclipse.ocl.xtext.basecs.OperationCS;
import org.eclipse.ocl.xtext.basecs.ParameterCS;
import org.eclipse.ocl.xtext.basecs.SpecificationCS;
import org.eclipse.ocl.xtext.basecs.StructuredClassCS;
import org.eclipse.ocl.xtext.basecs.TemplateSignatureCS;
import org.eclipse.ocl.xtext.basecs.TemplateableElementCS;
import org.eclipse.ocl.xtext.basecs.TypedRefCS;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.OperationCSImpl#getOwnedSignature <em>Owned Signature</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.OperationCSImpl#getOwnedBodyExpressions <em>Owned Body Expressions</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.OperationCSImpl#getOwnedExceptions <em>Owned Exceptions</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.OperationCSImpl#getOwnedParameters <em>Owned Parameters</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.OperationCSImpl#getOwnedPostconditions <em>Owned Postconditions</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.OperationCSImpl#getOwnedPreconditions <em>Owned Preconditions</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.OperationCSImpl#getOwningClass <em>Owning Class</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OperationCSImpl extends FeatureCSImpl implements OperationCS {
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
	 * The cached value of the '{@link #getOwnedBodyExpressions() <em>Owned Body Expressions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedBodyExpressions()
	 * @generated
	 * @ordered
	 */
	protected EList<SpecificationCS> ownedBodyExpressions;

	/**
	 * The cached value of the '{@link #getOwnedExceptions() <em>Owned Exceptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedExceptions()
	 * @generated
	 * @ordered
	 */
	protected EList<TypedRefCS> ownedExceptions;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BaseCSPackage.Literals.OPERATION_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TemplateSignatureCS getOwnedSignature() {
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BaseCSPackage.OPERATION_CS__OWNED_SIGNATURE, oldOwnedSignature, newOwnedSignature);
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
	public void setOwnedSignature(TemplateSignatureCS newOwnedSignature) {
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
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.OPERATION_CS__OWNED_SIGNATURE, newOwnedSignature, newOwnedSignature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StructuredClassCS getOwningClass() {
		if (eContainerFeatureID() != BaseCSPackage.OPERATION_CS__OWNING_CLASS) return null;
		return (StructuredClassCS)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningClass(StructuredClassCS newOwningClass, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOwningClass, BaseCSPackage.OPERATION_CS__OWNING_CLASS, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningClass(StructuredClassCS newOwningClass) {
		if (newOwningClass != eInternalContainer() || (eContainerFeatureID() != BaseCSPackage.OPERATION_CS__OWNING_CLASS && newOwningClass != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningClass))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningClass != null)
				msgs = ((InternalEObject)newOwningClass).eInverseAdd(this, BaseCSPackage.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, StructuredClassCS.class, msgs);
			msgs = basicSetOwningClass(newOwningClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.OPERATION_CS__OWNING_CLASS, newOwningClass, newOwningClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ParameterCS> getOwnedParameters()
	{
		if (ownedParameters == null)
		{
			ownedParameters = new EObjectContainmentWithInverseEList<ParameterCS>(ParameterCS.class, this, BaseCSPackage.OPERATION_CS__OWNED_PARAMETERS, BaseCSPackage.PARAMETER_CS__OWNING_OPERATION);
		}
		return ownedParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<TypedRefCS> getOwnedExceptions()
	{
		if (ownedExceptions == null)
		{
			ownedExceptions = new EObjectContainmentEList<TypedRefCS>(TypedRefCS.class, this, BaseCSPackage.OPERATION_CS__OWNED_EXCEPTIONS);
		}
		return ownedExceptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ConstraintCS> getOwnedPreconditions()
	{
		if (ownedPreconditions == null)
		{
			ownedPreconditions = new EObjectContainmentEList<ConstraintCS>(ConstraintCS.class, this, BaseCSPackage.OPERATION_CS__OWNED_PRECONDITIONS);
		}
		return ownedPreconditions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ConstraintCS> getOwnedPostconditions()
	{
		if (ownedPostconditions == null)
		{
			ownedPostconditions = new EObjectContainmentEList<ConstraintCS>(ConstraintCS.class, this, BaseCSPackage.OPERATION_CS__OWNED_POSTCONDITIONS);
		}
		return ownedPostconditions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<SpecificationCS> getOwnedBodyExpressions()
	{
		if (ownedBodyExpressions == null)
		{
			ownedBodyExpressions = new EObjectContainmentEList<SpecificationCS>(SpecificationCS.class, this, BaseCSPackage.OPERATION_CS__OWNED_BODY_EXPRESSIONS);
		}
		return ownedBodyExpressions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public TemplateSignatureCS getTemplateSignature() {
		return getOwnedSignature();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID)
		{
			case BaseCSPackage.OPERATION_CS__OWNED_SIGNATURE:
				if (ownedSignature != null)
					msgs = ((InternalEObject)ownedSignature).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BaseCSPackage.OPERATION_CS__OWNED_SIGNATURE, null, msgs);
				return basicSetOwnedSignature((TemplateSignatureCS)otherEnd, msgs);
			case BaseCSPackage.OPERATION_CS__OWNED_PARAMETERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedParameters()).basicAdd(otherEnd, msgs);
			case BaseCSPackage.OPERATION_CS__OWNING_CLASS:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningClass((StructuredClassCS)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID)
		{
			case BaseCSPackage.OPERATION_CS__OWNED_SIGNATURE:
				return basicSetOwnedSignature(null, msgs);
			case BaseCSPackage.OPERATION_CS__OWNED_BODY_EXPRESSIONS:
				return ((InternalEList<?>)getOwnedBodyExpressions()).basicRemove(otherEnd, msgs);
			case BaseCSPackage.OPERATION_CS__OWNED_EXCEPTIONS:
				return ((InternalEList<?>)getOwnedExceptions()).basicRemove(otherEnd, msgs);
			case BaseCSPackage.OPERATION_CS__OWNED_PARAMETERS:
				return ((InternalEList<?>)getOwnedParameters()).basicRemove(otherEnd, msgs);
			case BaseCSPackage.OPERATION_CS__OWNED_POSTCONDITIONS:
				return ((InternalEList<?>)getOwnedPostconditions()).basicRemove(otherEnd, msgs);
			case BaseCSPackage.OPERATION_CS__OWNED_PRECONDITIONS:
				return ((InternalEList<?>)getOwnedPreconditions()).basicRemove(otherEnd, msgs);
			case BaseCSPackage.OPERATION_CS__OWNING_CLASS:
				return basicSetOwningClass(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID())
		{
			case BaseCSPackage.OPERATION_CS__OWNING_CLASS:
				return eInternalContainer().eInverseRemove(this, BaseCSPackage.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, StructuredClassCS.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
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
			case BaseCSPackage.OPERATION_CS__OWNED_SIGNATURE:
				return getOwnedSignature();
			case BaseCSPackage.OPERATION_CS__OWNED_BODY_EXPRESSIONS:
				return getOwnedBodyExpressions();
			case BaseCSPackage.OPERATION_CS__OWNED_EXCEPTIONS:
				return getOwnedExceptions();
			case BaseCSPackage.OPERATION_CS__OWNED_PARAMETERS:
				return getOwnedParameters();
			case BaseCSPackage.OPERATION_CS__OWNED_POSTCONDITIONS:
				return getOwnedPostconditions();
			case BaseCSPackage.OPERATION_CS__OWNED_PRECONDITIONS:
				return getOwnedPreconditions();
			case BaseCSPackage.OPERATION_CS__OWNING_CLASS:
				return getOwningClass();
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
			case BaseCSPackage.OPERATION_CS__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignatureCS)newValue);
				return;
			case BaseCSPackage.OPERATION_CS__OWNED_BODY_EXPRESSIONS:
				getOwnedBodyExpressions().clear();
				getOwnedBodyExpressions().addAll((Collection<? extends SpecificationCS>)newValue);
				return;
			case BaseCSPackage.OPERATION_CS__OWNED_EXCEPTIONS:
				getOwnedExceptions().clear();
				getOwnedExceptions().addAll((Collection<? extends TypedRefCS>)newValue);
				return;
			case BaseCSPackage.OPERATION_CS__OWNED_PARAMETERS:
				getOwnedParameters().clear();
				getOwnedParameters().addAll((Collection<? extends ParameterCS>)newValue);
				return;
			case BaseCSPackage.OPERATION_CS__OWNED_POSTCONDITIONS:
				getOwnedPostconditions().clear();
				getOwnedPostconditions().addAll((Collection<? extends ConstraintCS>)newValue);
				return;
			case BaseCSPackage.OPERATION_CS__OWNED_PRECONDITIONS:
				getOwnedPreconditions().clear();
				getOwnedPreconditions().addAll((Collection<? extends ConstraintCS>)newValue);
				return;
			case BaseCSPackage.OPERATION_CS__OWNING_CLASS:
				setOwningClass((StructuredClassCS)newValue);
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
			case BaseCSPackage.OPERATION_CS__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignatureCS)null);
				return;
			case BaseCSPackage.OPERATION_CS__OWNED_BODY_EXPRESSIONS:
				getOwnedBodyExpressions().clear();
				return;
			case BaseCSPackage.OPERATION_CS__OWNED_EXCEPTIONS:
				getOwnedExceptions().clear();
				return;
			case BaseCSPackage.OPERATION_CS__OWNED_PARAMETERS:
				getOwnedParameters().clear();
				return;
			case BaseCSPackage.OPERATION_CS__OWNED_POSTCONDITIONS:
				getOwnedPostconditions().clear();
				return;
			case BaseCSPackage.OPERATION_CS__OWNED_PRECONDITIONS:
				getOwnedPreconditions().clear();
				return;
			case BaseCSPackage.OPERATION_CS__OWNING_CLASS:
				setOwningClass((StructuredClassCS)null);
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
			case BaseCSPackage.OPERATION_CS__OWNED_SIGNATURE:
				return ownedSignature != null;
			case BaseCSPackage.OPERATION_CS__OWNED_BODY_EXPRESSIONS:
				return ownedBodyExpressions != null && !ownedBodyExpressions.isEmpty();
			case BaseCSPackage.OPERATION_CS__OWNED_EXCEPTIONS:
				return ownedExceptions != null && !ownedExceptions.isEmpty();
			case BaseCSPackage.OPERATION_CS__OWNED_PARAMETERS:
				return ownedParameters != null && !ownedParameters.isEmpty();
			case BaseCSPackage.OPERATION_CS__OWNED_POSTCONDITIONS:
				return ownedPostconditions != null && !ownedPostconditions.isEmpty();
			case BaseCSPackage.OPERATION_CS__OWNED_PRECONDITIONS:
				return ownedPreconditions != null && !ownedPreconditions.isEmpty();
			case BaseCSPackage.OPERATION_CS__OWNING_CLASS:
				return getOwningClass() != null;
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
		if (baseClass == TemplateableElementCS.class)
		{
			switch (derivedFeatureID)
			{
				case BaseCSPackage.OPERATION_CS__OWNED_SIGNATURE: return BaseCSPackage.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE;
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
		if (baseClass == TemplateableElementCS.class)
		{
			switch (baseFeatureID)
			{
				case BaseCSPackage.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE: return BaseCSPackage.OPERATION_CS__OWNED_SIGNATURE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull BaseCSVisitor<R> visitor) {
		return visitor.visitOperationCS(this);
	}
} //OperationCSImpl
