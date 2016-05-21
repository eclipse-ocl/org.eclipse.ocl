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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.ConstraintCS;
import org.eclipse.ocl.xtext.basecs.PathNameCS;
import org.eclipse.ocl.xtext.basecs.TemplateSignatureCS;
import org.eclipse.ocl.xtext.basecs.TemplateableElementCS;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;
import org.eclipse.ocl.xtext.completeoclcs.ClassifierContextDeclCS;
import org.eclipse.ocl.xtext.completeoclcs.CompleteOCLCSPackage;
import org.eclipse.ocl.xtext.completeoclcs.DefCS;
import org.eclipse.ocl.xtext.completeoclcs.util.CompleteOCLCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Classifier Context Decl CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.completeoclcs.impl.ClassifierContextDeclCSImpl#getOwnedSignature <em>Owned Signature</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.completeoclcs.impl.ClassifierContextDeclCSImpl#getOwnedDefinitions <em>Owned Definitions</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.completeoclcs.impl.ClassifierContextDeclCSImpl#getOwnedInvariants <em>Owned Invariants</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.completeoclcs.impl.ClassifierContextDeclCSImpl#getReferredClass <em>Referred Class</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.completeoclcs.impl.ClassifierContextDeclCSImpl#getSelfName <em>Self Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ClassifierContextDeclCSImpl
		extends ContextDeclCSImpl
		implements ClassifierContextDeclCS {

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
	 * The cached value of the '{@link #getOwnedDefinitions() <em>Owned Definitions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedDefinitions()
	 * @generated
	 * @ordered
	 */
	protected EList<DefCS> ownedDefinitions;

	/**
	 * The cached value of the '{@link #getOwnedInvariants() <em>Owned Invariants</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedInvariants()
	 * @generated
	 * @ordered
	 */
	protected EList<ConstraintCS> ownedInvariants;

	/**
	 * The default value of the '{@link #getSelfName() <em>Self Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSelfName()
	 * @generated
	 * @ordered
	 */
	protected static final String SELF_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSelfName() <em>Self Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSelfName()
	 * @generated
	 * @ordered
	 */
	protected String selfName = SELF_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassifierContextDeclCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CompleteOCLCSPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__OWNED_SIGNATURE, oldOwnedSignature, newOwnedSignature);
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
			eNotify(new ENotificationImpl(this, Notification.SET, CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__OWNED_SIGNATURE, newOwnedSignature, newOwnedSignature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSelfName() {
		return selfName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSelfName(String newSelfName) {
		String oldSelfName = selfName;
		selfName = newSelfName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__SELF_NAME, oldSelfName, selfName));
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
			case CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__OWNED_SIGNATURE:
				return getOwnedSignature();
			case CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS:
				return getOwnedDefinitions();
			case CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS:
				return getOwnedInvariants();
			case CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__REFERRED_CLASS:
				return getReferredClass();
			case CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__SELF_NAME:
				return getSelfName();
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
			case CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignatureCS)newValue);
				return;
			case CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS:
				getOwnedDefinitions().clear();
				getOwnedDefinitions().addAll((Collection<? extends DefCS>)newValue);
				return;
			case CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS:
				getOwnedInvariants().clear();
				getOwnedInvariants().addAll((Collection<? extends ConstraintCS>)newValue);
				return;
			case CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__SELF_NAME:
				setSelfName((String)newValue);
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
			case CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignatureCS)null);
				return;
			case CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS:
				getOwnedDefinitions().clear();
				return;
			case CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS:
				getOwnedInvariants().clear();
				return;
			case CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__SELF_NAME:
				setSelfName(SELF_NAME_EDEFAULT);
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
			case CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__OWNED_SIGNATURE:
				return ownedSignature != null;
			case CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS:
				return ownedDefinitions != null && !ownedDefinitions.isEmpty();
			case CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS:
				return ownedInvariants != null && !ownedInvariants.isEmpty();
			case CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__REFERRED_CLASS:
				return getReferredClass() != null;
			case CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__SELF_NAME:
				return SELF_NAME_EDEFAULT == null ? selfName != null : !SELF_NAME_EDEFAULT.equals(selfName);
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
				case CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__OWNED_SIGNATURE: return BaseCSPackage.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE;
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
				case BaseCSPackage.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE: return CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__OWNED_SIGNATURE;
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
		return (R) ((CompleteOCLCSVisitor<?>)visitor).visitClassifierContextDeclCS(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public org.eclipse.ocl.pivot.Class getReferredClass() {
		PathNameCS ownedPathName2 = ownedPathName;
		if (ownedPathName2 == null) {
			return null;
		}
		CS2AS.setElementType(ownedPathName2, PivotPackage.Literals.TYPE, this, null);
		return (org.eclipse.ocl.pivot.Class) ownedPathName2.getReferredElement();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ConstraintCS> getOwnedInvariants() {
		if (ownedInvariants == null)
		{
			ownedInvariants = new EObjectContainmentEList<ConstraintCS>(ConstraintCS.class, this, CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS);
		}
		return ownedInvariants;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<DefCS> getOwnedDefinitions() {
		if (ownedDefinitions == null)
		{
			ownedDefinitions = new EObjectContainmentWithInverseEList<DefCS>(DefCS.class, this, CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS, CompleteOCLCSPackage.DEF_CS__OWNING_CLASSIFIER_CONTEXT_DECL);
		}
		return ownedDefinitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
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
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID)
		{
			case CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__OWNED_SIGNATURE:
				if (ownedSignature != null)
					msgs = ((InternalEObject)ownedSignature).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__OWNED_SIGNATURE, null, msgs);
				return basicSetOwnedSignature((TemplateSignatureCS)otherEnd, msgs);
			case CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedDefinitions()).basicAdd(otherEnd, msgs);
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
			case CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__OWNED_SIGNATURE:
				return basicSetOwnedSignature(null, msgs);
			case CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__OWNED_DEFINITIONS:
				return ((InternalEList<?>)getOwnedDefinitions()).basicRemove(otherEnd, msgs);
			case CompleteOCLCSPackage.CLASSIFIER_CONTEXT_DECL_CS__OWNED_INVARIANTS:
				return ((InternalEList<?>)getOwnedInvariants()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}
} //ClassifierContextDeclCSImpl
