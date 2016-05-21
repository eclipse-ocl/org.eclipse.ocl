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

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.basecs.ConstraintCS;
import org.eclipse.ocl.xtext.basecs.PathNameCS;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;
import org.eclipse.ocl.xtext.completeoclcs.CompleteOCLCSPackage;
import org.eclipse.ocl.xtext.completeoclcs.PropertyContextDeclCS;
import org.eclipse.ocl.xtext.completeoclcs.util.CompleteOCLCSVisitor;
import org.eclipse.ocl.xtext.essentialoclcs.ExpSpecificationCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property Context Decl CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.completeoclcs.impl.PropertyContextDeclCSImpl#getOwnedDefaultExpressions <em>Owned Default Expressions</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.completeoclcs.impl.PropertyContextDeclCSImpl#getOwnedDerivedInvariants <em>Owned Derived Invariants</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.completeoclcs.impl.PropertyContextDeclCSImpl#getReferredProperty <em>Referred Property</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PropertyContextDeclCSImpl
		extends FeatureContextDeclCSImpl
		implements PropertyContextDeclCS {

	/**
	 * The cached value of the '{@link #getOwnedDefaultExpressions() <em>Owned Default Expressions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedDefaultExpressions()
	 * @generated
	 * @ordered
	 */
	protected EList<ExpSpecificationCS> ownedDefaultExpressions;

	/**
	 * The cached value of the '{@link #getOwnedDerivedInvariants() <em>Owned Derived Invariants</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedDerivedInvariants()
	 * @generated
	 * @ordered
	 */
	protected EList<ConstraintCS> ownedDerivedInvariants;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PropertyContextDeclCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CompleteOCLCSPackage.Literals.PROPERTY_CONTEXT_DECL_CS;
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
			case CompleteOCLCSPackage.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS:
				return getOwnedDefaultExpressions();
			case CompleteOCLCSPackage.PROPERTY_CONTEXT_DECL_CS__OWNED_DERIVED_INVARIANTS:
				return getOwnedDerivedInvariants();
			case CompleteOCLCSPackage.PROPERTY_CONTEXT_DECL_CS__REFERRED_PROPERTY:
				return getReferredProperty();
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
			case CompleteOCLCSPackage.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS:
				getOwnedDefaultExpressions().clear();
				getOwnedDefaultExpressions().addAll((Collection<? extends ExpSpecificationCS>)newValue);
				return;
			case CompleteOCLCSPackage.PROPERTY_CONTEXT_DECL_CS__OWNED_DERIVED_INVARIANTS:
				getOwnedDerivedInvariants().clear();
				getOwnedDerivedInvariants().addAll((Collection<? extends ConstraintCS>)newValue);
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
			case CompleteOCLCSPackage.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS:
				getOwnedDefaultExpressions().clear();
				return;
			case CompleteOCLCSPackage.PROPERTY_CONTEXT_DECL_CS__OWNED_DERIVED_INVARIANTS:
				getOwnedDerivedInvariants().clear();
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
			case CompleteOCLCSPackage.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS:
				return ownedDefaultExpressions != null && !ownedDefaultExpressions.isEmpty();
			case CompleteOCLCSPackage.PROPERTY_CONTEXT_DECL_CS__OWNED_DERIVED_INVARIANTS:
				return ownedDerivedInvariants != null && !ownedDerivedInvariants.isEmpty();
			case CompleteOCLCSPackage.PROPERTY_CONTEXT_DECL_CS__REFERRED_PROPERTY:
				return getReferredProperty() != null;
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
		return (R) ((CompleteOCLCSVisitor<?>)visitor).visitPropertyContextDeclCS(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Property getReferredProperty() {
		PathNameCS ownedPathName2 = ownedPathName;
		if (ownedPathName2 == null) {
			return null;
		}
		CS2AS.setElementType(ownedPathName2, PivotPackage.Literals.PROPERTY,
			this, null);
		return (Property) ownedPathName2.getReferredElement();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ExpSpecificationCS> getOwnedDefaultExpressions() {
		if (ownedDefaultExpressions == null)
		{
			ownedDefaultExpressions = new EObjectContainmentEList<ExpSpecificationCS>(ExpSpecificationCS.class, this, CompleteOCLCSPackage.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS);
		}
		return ownedDefaultExpressions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ConstraintCS> getOwnedDerivedInvariants() {
		if (ownedDerivedInvariants == null)
		{
			ownedDerivedInvariants = new EObjectContainmentEList<ConstraintCS>(ConstraintCS.class, this, CompleteOCLCSPackage.PROPERTY_CONTEXT_DECL_CS__OWNED_DERIVED_INVARIANTS);
		}
		return ownedDerivedInvariants;
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
			case CompleteOCLCSPackage.PROPERTY_CONTEXT_DECL_CS__OWNED_DEFAULT_EXPRESSIONS:
				return ((InternalEList<?>)getOwnedDefaultExpressions()).basicRemove(otherEnd, msgs);
			case CompleteOCLCSPackage.PROPERTY_CONTEXT_DECL_CS__OWNED_DERIVED_INVARIANTS:
				return ((InternalEList<?>)getOwnedDerivedInvariants()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}
} //PropertyContextDeclCSImpl
