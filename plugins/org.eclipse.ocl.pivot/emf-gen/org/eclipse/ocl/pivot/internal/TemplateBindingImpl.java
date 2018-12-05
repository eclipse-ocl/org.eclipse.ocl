/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Template Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.TemplateBindingImpl#getOwnedSubstitutions <em>Owned Substitutions</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.TemplateBindingImpl#getOwningElement <em>Owning Element</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.TemplateBindingImpl#getTemplateSignature <em>Template Signature</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TemplateBindingImpl
		extends ElementImpl
		implements TemplateBinding {

	/**
	 * The cached value of the '{@link #getOwnedSubstitutions() <em>Owned Substitutions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSubstitutions()
	 * @generated
	 * @ordered
	 */
	protected EList<TemplateParameterSubstitution> ownedSubstitutions;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TemplateBindingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.TEMPLATE_BINDING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<TemplateParameterSubstitution> getOwnedSubstitutions()
	{
		if (ownedSubstitutions == null)
		{
			ownedSubstitutions = new EObjectContainmentWithInverseEList<TemplateParameterSubstitution>(TemplateParameterSubstitution.class, this, PivotPackage.TEMPLATE_BINDING__OWNED_SUBSTITUTIONS, PivotPackage.TEMPLATE_PARAMETER_SUBSTITUTION__OWNING_BINDING);
		}
		return ownedSubstitutions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TemplateableElement getOwningElement() {
		if (eContainerFeatureID() != PivotPackage.TEMPLATE_BINDING__OWNING_ELEMENT) return null;
		return (TemplateableElement)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningElement(TemplateableElement newOwningElement, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningElement, PivotPackage.TEMPLATE_BINDING__OWNING_ELEMENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningElement(TemplateableElement newOwningElement)
	{
		if (newOwningElement != eInternalContainer() || (eContainerFeatureID() != PivotPackage.TEMPLATE_BINDING__OWNING_ELEMENT && newOwningElement != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningElement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningElement != null)
				msgs = ((InternalEObject)newOwningElement).eInverseAdd(this, PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_BINDINGS, TemplateableElement.class, msgs);
			msgs = basicSetOwningElement(newOwningElement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.TEMPLATE_BINDING__OWNING_ELEMENT, newOwningElement, newOwningElement));
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
			case PivotPackage.TEMPLATE_BINDING__ANNOTATING_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.TEMPLATE_BINDING__OWNED_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.TEMPLATE_BINDING__OWNED_EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case PivotPackage.TEMPLATE_BINDING__OWNED_SUBSTITUTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedSubstitutions()).basicAdd(otherEnd, msgs);
			case PivotPackage.TEMPLATE_BINDING__OWNING_ELEMENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningElement((TemplateableElement)otherEnd, msgs);
		}
		return eDynamicInverseAdd(otherEnd, featureID, msgs);
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
			case PivotPackage.TEMPLATE_BINDING__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.TEMPLATE_BINDING__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.TEMPLATE_BINDING__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.TEMPLATE_BINDING__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.TEMPLATE_BINDING__OWNED_SUBSTITUTIONS:
				return ((InternalEList<?>)getOwnedSubstitutions()).basicRemove(otherEnd, msgs);
			case PivotPackage.TEMPLATE_BINDING__OWNING_ELEMENT:
				return basicSetOwningElement(null, msgs);
		}
		return eDynamicInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(
			NotificationChain msgs) {
		switch (eContainerFeatureID())
		{
			case PivotPackage.TEMPLATE_BINDING__OWNING_ELEMENT:
				return eInternalContainer().eInverseRemove(this, PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_BINDINGS, TemplateableElement.class, msgs);
		}
		return eDynamicBasicRemoveFromContainer(msgs);
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
			case PivotPackage.TEMPLATE_BINDING__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.TEMPLATE_BINDING__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.TEMPLATE_BINDING__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.TEMPLATE_BINDING__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.TEMPLATE_BINDING__OWNED_SUBSTITUTIONS:
				return getOwnedSubstitutions();
			case PivotPackage.TEMPLATE_BINDING__OWNING_ELEMENT:
				return getOwningElement();
			case PivotPackage.TEMPLATE_BINDING__TEMPLATE_SIGNATURE:
				return getTemplateSignature();
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
			case PivotPackage.TEMPLATE_BINDING__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.TEMPLATE_BINDING__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.TEMPLATE_BINDING__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.TEMPLATE_BINDING__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.TEMPLATE_BINDING__OWNED_SUBSTITUTIONS:
				getOwnedSubstitutions().clear();
				getOwnedSubstitutions().addAll((Collection<? extends TemplateParameterSubstitution>)newValue);
				return;
			case PivotPackage.TEMPLATE_BINDING__OWNING_ELEMENT:
				setOwningElement((TemplateableElement)newValue);
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
			case PivotPackage.TEMPLATE_BINDING__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.TEMPLATE_BINDING__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.TEMPLATE_BINDING__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.TEMPLATE_BINDING__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.TEMPLATE_BINDING__OWNED_SUBSTITUTIONS:
				getOwnedSubstitutions().clear();
				return;
			case PivotPackage.TEMPLATE_BINDING__OWNING_ELEMENT:
				setOwningElement((TemplateableElement)null);
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
			case PivotPackage.TEMPLATE_BINDING__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.TEMPLATE_BINDING__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.TEMPLATE_BINDING__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.TEMPLATE_BINDING__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.TEMPLATE_BINDING__OWNED_SUBSTITUTIONS:
				return ownedSubstitutions != null && !ownedSubstitutions.isEmpty();
			case PivotPackage.TEMPLATE_BINDING__OWNING_ELEMENT:
				return getOwningElement() != null;
			case PivotPackage.TEMPLATE_BINDING__TEMPLATE_SIGNATURE:
				return getTemplateSignature() != null;
		}
		return eDynamicIsSet(featureID);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitTemplateBinding(this);
	}

	@Override
	public TemplateSignature getTemplateSignature()
	{
		for (TemplateParameterSubstitution templateParameterSubstitution : getOwnedSubstitutions()) {
			TemplateParameter formal = templateParameterSubstitution.getFormal();
			if (formal != null) {
				TemplateSignature templateSignature = formal.getOwningSignature();
				if (templateSignature != null) {
					return templateSignature;
				}
			}
		}
		return null;		// Never happens
	}
} //TemplateBindingImpl
