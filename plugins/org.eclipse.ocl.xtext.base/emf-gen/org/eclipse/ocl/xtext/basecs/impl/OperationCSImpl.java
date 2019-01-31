/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
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
	 * The number of structural features of the '<em>Operation CS</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OPERATION_CS_FEATURE_COUNT = FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 7;

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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 0, oldOwnedSignature, newOwnedSignature);
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
				msgs = ((InternalEObject)ownedSignature).eInverseRemove(this, ModelElementCSImpl.MODEL_ELEMENT_CS_FEATURE_COUNT + 1, TemplateSignatureCS.class, msgs);
			if (newOwnedSignature != null)
				msgs = ((InternalEObject)newOwnedSignature).eInverseAdd(this, ModelElementCSImpl.MODEL_ELEMENT_CS_FEATURE_COUNT + 1, TemplateSignatureCS.class, msgs);
			msgs = basicSetOwnedSignature(newOwnedSignature, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 0, newOwnedSignature, newOwnedSignature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StructuredClassCS getOwningClass() {
		if (eContainerFeatureID() != (FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 6)) return null;
		return (StructuredClassCS)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningClass(StructuredClassCS newOwningClass, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOwningClass, FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 6, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningClass(StructuredClassCS newOwningClass) {
		if (newOwningClass != eInternalContainer() || (eContainerFeatureID() != (FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 6) && newOwningClass != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningClass))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningClass != null)
				msgs = ((InternalEObject)newOwningClass).eInverseAdd(this, ClassCSImpl.CLASS_CS_FEATURE_COUNT + 3, StructuredClassCS.class, msgs);
			msgs = basicSetOwningClass(newOwningClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 6, newOwningClass, newOwningClass));
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
			ownedParameters = new EObjectContainmentWithInverseEList<ParameterCS>(ParameterCS.class, this, FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 3, TypedElementCSImpl.TYPED_ELEMENT_CS_FEATURE_COUNT + 0);
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
			ownedExceptions = new EObjectContainmentEList<TypedRefCS>(TypedRefCS.class, this, FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 2);
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
			ownedPreconditions = new EObjectContainmentEList<ConstraintCS>(ConstraintCS.class, this, FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 5);
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
			ownedPostconditions = new EObjectContainmentEList<ConstraintCS>(ConstraintCS.class, this, FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 4);
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
			ownedBodyExpressions = new EObjectContainmentEList<SpecificationCS>(SpecificationCS.class, this, FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 1);
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
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 0:
				if (ownedSignature != null)
					msgs = ((InternalEObject)ownedSignature).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 0), null, msgs);
				return basicSetOwnedSignature((TemplateSignatureCS)otherEnd, msgs);
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 3:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedParameters()).basicAdd(otherEnd, msgs);
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 6:
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
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 0:
				return basicSetOwnedSignature(null, msgs);
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 1:
				return ((InternalEList<?>)getOwnedBodyExpressions()).basicRemove(otherEnd, msgs);
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 2:
				return ((InternalEList<?>)getOwnedExceptions()).basicRemove(otherEnd, msgs);
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 3:
				return ((InternalEList<?>)getOwnedParameters()).basicRemove(otherEnd, msgs);
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 4:
				return ((InternalEList<?>)getOwnedPostconditions()).basicRemove(otherEnd, msgs);
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 5:
				return ((InternalEList<?>)getOwnedPreconditions()).basicRemove(otherEnd, msgs);
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 6:
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
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 6:
				return eInternalContainer().eInverseRemove(this, ClassCSImpl.CLASS_CS_FEATURE_COUNT + 3, StructuredClassCS.class, msgs);
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
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 0:
				return getOwnedSignature();
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 1:
				return getOwnedBodyExpressions();
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 2:
				return getOwnedExceptions();
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 3:
				return getOwnedParameters();
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 4:
				return getOwnedPostconditions();
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 5:
				return getOwnedPreconditions();
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 6:
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
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 0:
				setOwnedSignature((TemplateSignatureCS)newValue);
				return;
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 1:
				getOwnedBodyExpressions().clear();
				getOwnedBodyExpressions().addAll((Collection<? extends SpecificationCS>)newValue);
				return;
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 2:
				getOwnedExceptions().clear();
				getOwnedExceptions().addAll((Collection<? extends TypedRefCS>)newValue);
				return;
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 3:
				getOwnedParameters().clear();
				getOwnedParameters().addAll((Collection<? extends ParameterCS>)newValue);
				return;
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 4:
				getOwnedPostconditions().clear();
				getOwnedPostconditions().addAll((Collection<? extends ConstraintCS>)newValue);
				return;
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 5:
				getOwnedPreconditions().clear();
				getOwnedPreconditions().addAll((Collection<? extends ConstraintCS>)newValue);
				return;
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 6:
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
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 0:
				setOwnedSignature((TemplateSignatureCS)null);
				return;
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 1:
				getOwnedBodyExpressions().clear();
				return;
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 2:
				getOwnedExceptions().clear();
				return;
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 3:
				getOwnedParameters().clear();
				return;
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 4:
				getOwnedPostconditions().clear();
				return;
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 5:
				getOwnedPreconditions().clear();
				return;
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 6:
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
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 0:
				return ownedSignature != null;
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 1:
				return ownedBodyExpressions != null && !ownedBodyExpressions.isEmpty();
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 2:
				return ownedExceptions != null && !ownedExceptions.isEmpty();
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 3:
				return ownedParameters != null && !ownedParameters.isEmpty();
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 4:
				return ownedPostconditions != null && !ownedPostconditions.isEmpty();
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 5:
				return ownedPreconditions != null && !ownedPreconditions.isEmpty();
			case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 6:
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
				case FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 0: return ElementCSImpl.ELEMENT_CS_FEATURE_COUNT + 0;
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
				case ElementCSImpl.ELEMENT_CS_FEATURE_COUNT + 0: return FeatureCSImpl.FEATURE_CS_FEATURE_COUNT + 0;
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
