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
package org.eclipse.ocl.xtext.basecs.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.MultiplicityCS;
import org.eclipse.ocl.xtext.basecs.TemplateBindingCS;
import org.eclipse.ocl.xtext.basecs.TemplateParameterSubstitutionCS;
import org.eclipse.ocl.xtext.basecs.TypedTypeRefCS;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Template Binding CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.TemplateBindingCSImpl#getOwnedMultiplicity <em>Owned Multiplicity</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.TemplateBindingCSImpl#getOwnedSubstitutions <em>Owned Substitutions</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.TemplateBindingCSImpl#getOwningElement <em>Owning Element</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TemplateBindingCSImpl extends ElementRefCSImpl implements TemplateBindingCS {
	/**
	 * The cached value of the '{@link #getOwnedMultiplicity() <em>Owned Multiplicity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedMultiplicity()
	 * @generated
	 * @ordered
	 */
	protected MultiplicityCS ownedMultiplicity;
	/**
	 * The cached value of the '{@link #getOwnedSubstitutions() <em>Owned Substitutions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSubstitutions()
	 * @generated
	 * @ordered
	 */
	protected EList<TemplateParameterSubstitutionCS> ownedSubstitutions;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TemplateBindingCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BaseCSPackage.Literals.TEMPLATE_BINDING_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MultiplicityCS getOwnedMultiplicity()
	{
		return ownedMultiplicity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedMultiplicity(MultiplicityCS newOwnedMultiplicity, NotificationChain msgs)
	{
		MultiplicityCS oldOwnedMultiplicity = ownedMultiplicity;
		ownedMultiplicity = newOwnedMultiplicity;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BaseCSPackage.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, oldOwnedMultiplicity, newOwnedMultiplicity);
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
	public void setOwnedMultiplicity(MultiplicityCS newOwnedMultiplicity)
	{
		if (newOwnedMultiplicity != ownedMultiplicity)
		{
			NotificationChain msgs = null;
			if (ownedMultiplicity != null)
				msgs = ((InternalEObject)ownedMultiplicity).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BaseCSPackage.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, null, msgs);
			if (newOwnedMultiplicity != null)
				msgs = ((InternalEObject)newOwnedMultiplicity).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BaseCSPackage.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, null, msgs);
			msgs = basicSetOwnedMultiplicity(newOwnedMultiplicity, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, newOwnedMultiplicity, newOwnedMultiplicity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TypedTypeRefCS getOwningElement() {
		if (eContainerFeatureID() != BaseCSPackage.TEMPLATE_BINDING_CS__OWNING_ELEMENT) return null;
		return (TypedTypeRefCS)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningElement(TypedTypeRefCS newOwningElement, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningElement, BaseCSPackage.TEMPLATE_BINDING_CS__OWNING_ELEMENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningElement(TypedTypeRefCS newOwningElement)
	{
		if (newOwningElement != eInternalContainer() || (eContainerFeatureID() != BaseCSPackage.TEMPLATE_BINDING_CS__OWNING_ELEMENT && newOwningElement != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningElement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningElement != null)
				msgs = ((InternalEObject)newOwningElement).eInverseAdd(this, BaseCSPackage.TYPED_TYPE_REF_CS__OWNED_BINDING, TypedTypeRefCS.class, msgs);
			msgs = basicSetOwningElement(newOwningElement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.TEMPLATE_BINDING_CS__OWNING_ELEMENT, newOwningElement, newOwningElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<TemplateParameterSubstitutionCS> getOwnedSubstitutions() {
		if (ownedSubstitutions == null)
		{
			ownedSubstitutions = new EObjectContainmentWithInverseEList<TemplateParameterSubstitutionCS>(TemplateParameterSubstitutionCS.class, this, BaseCSPackage.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, BaseCSPackage.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNING_BINDING);
		}
		return ownedSubstitutions;
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
			case BaseCSPackage.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedSubstitutions()).basicAdd(otherEnd, msgs);
			case BaseCSPackage.TEMPLATE_BINDING_CS__OWNING_ELEMENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningElement((TypedTypeRefCS)otherEnd, msgs);
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
			case BaseCSPackage.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY:
				return basicSetOwnedMultiplicity(null, msgs);
			case BaseCSPackage.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS:
				return ((InternalEList<?>)getOwnedSubstitutions()).basicRemove(otherEnd, msgs);
			case BaseCSPackage.TEMPLATE_BINDING_CS__OWNING_ELEMENT:
				return basicSetOwningElement(null, msgs);
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
			case BaseCSPackage.TEMPLATE_BINDING_CS__OWNING_ELEMENT:
				return eInternalContainer().eInverseRemove(this, BaseCSPackage.TYPED_TYPE_REF_CS__OWNED_BINDING, TypedTypeRefCS.class, msgs);
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
			case BaseCSPackage.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY:
				return getOwnedMultiplicity();
			case BaseCSPackage.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS:
				return getOwnedSubstitutions();
			case BaseCSPackage.TEMPLATE_BINDING_CS__OWNING_ELEMENT:
				return getOwningElement();
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
			case BaseCSPackage.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY:
				setOwnedMultiplicity((MultiplicityCS)newValue);
				return;
			case BaseCSPackage.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS:
				getOwnedSubstitutions().clear();
				getOwnedSubstitutions().addAll((Collection<? extends TemplateParameterSubstitutionCS>)newValue);
				return;
			case BaseCSPackage.TEMPLATE_BINDING_CS__OWNING_ELEMENT:
				setOwningElement((TypedTypeRefCS)newValue);
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
			case BaseCSPackage.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY:
				setOwnedMultiplicity((MultiplicityCS)null);
				return;
			case BaseCSPackage.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS:
				getOwnedSubstitutions().clear();
				return;
			case BaseCSPackage.TEMPLATE_BINDING_CS__OWNING_ELEMENT:
				setOwningElement((TypedTypeRefCS)null);
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
			case BaseCSPackage.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY:
				return ownedMultiplicity != null;
			case BaseCSPackage.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS:
				return ownedSubstitutions != null && !ownedSubstitutions.isEmpty();
			case BaseCSPackage.TEMPLATE_BINDING_CS__OWNING_ELEMENT:
				return getOwningElement() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull BaseCSVisitor<R> visitor) {
		return visitor.visitTemplateBindingCS(this);
	}
} //TemplateBindingCSImpl
