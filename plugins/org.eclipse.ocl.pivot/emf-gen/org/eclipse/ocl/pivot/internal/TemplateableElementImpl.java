/*******************************************************************************
 * Copyright (c) 2010, 2015 Willink Transformations and others.
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
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Templateable Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.TemplateableElementImpl#getOwnedBindings <em>Owned Bindings</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.TemplateableElementImpl#getOwnedSignature <em>Owned Signature</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.TemplateableElementImpl#getUnspecializedElement <em>Unspecialized Element</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class TemplateableElementImpl
		extends ElementImpl
		implements TemplateableElement {

	/**
	 * The cached value of the '{@link #getOwnedBindings() <em>Owned Bindings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedBindings()
	 * @generated
	 * @ordered
	 */
	protected EList<TemplateBinding> ownedBindings;

	/**
	 * The cached value of the '{@link #getOwnedSignature() <em>Owned Signature</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSignature()
	 * @generated
	 * @ordered
	 */
	protected TemplateSignature ownedSignature;

	/**
	 * The cached value of the '{@link #getUnspecializedElement() <em>Unspecialized Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnspecializedElement()
	 * @generated
	 * @ordered
	 */
	protected TemplateableElement unspecializedElement;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TemplateableElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.TEMPLATEABLE_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<TemplateBinding> getOwnedBindings()
	{
		if (ownedBindings == null)
		{
			ownedBindings = new EObjectContainmentWithInverseEList<TemplateBinding>(TemplateBinding.class, this, PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_BINDINGS, PivotPackage.TEMPLATE_BINDING__OWNING_ELEMENT);
		}
		return ownedBindings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TemplateSignature getOwnedSignature() {
		return ownedSignature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedSignature(TemplateSignature newOwnedSignature, NotificationChain msgs)
	{
		TemplateSignature oldOwnedSignature = ownedSignature;
		ownedSignature = newOwnedSignature;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_SIGNATURE, oldOwnedSignature, newOwnedSignature);
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
	public void setOwnedSignature(
			TemplateSignature newOwnedSignature) {
		if (newOwnedSignature != ownedSignature)
		{
			NotificationChain msgs = null;
			if (ownedSignature != null)
				msgs = ((InternalEObject)ownedSignature).eInverseRemove(this, PivotPackage.TEMPLATE_SIGNATURE__OWNING_ELEMENT, TemplateSignature.class, msgs);
			if (newOwnedSignature != null)
				msgs = ((InternalEObject)newOwnedSignature).eInverseAdd(this, PivotPackage.TEMPLATE_SIGNATURE__OWNING_ELEMENT, TemplateSignature.class, msgs);
			msgs = basicSetOwnedSignature(newOwnedSignature, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_SIGNATURE, newOwnedSignature, newOwnedSignature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TemplateableElement getUnspecializedElement()
	{
		return unspecializedElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUnspecializedElement(TemplateableElement newUnspecializedElement)
	{
		TemplateableElement oldUnspecializedElement = unspecializedElement;
		unspecializedElement = newUnspecializedElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.TEMPLATEABLE_ELEMENT__UNSPECIALIZED_ELEMENT, oldUnspecializedElement, unspecializedElement));
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
			case PivotPackage.TEMPLATEABLE_ELEMENT__ANNOTATING_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_BINDINGS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedBindings()).basicAdd(otherEnd, msgs);
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_SIGNATURE:
				if (ownedSignature != null)
					msgs = ((InternalEObject)ownedSignature).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_SIGNATURE, null, msgs);
				return basicSetOwnedSignature((TemplateSignature)otherEnd, msgs);
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
			case PivotPackage.TEMPLATEABLE_ELEMENT__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_BINDINGS:
				return ((InternalEList<?>)getOwnedBindings()).basicRemove(otherEnd, msgs);
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_SIGNATURE:
				return basicSetOwnedSignature(null, msgs);
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
			case PivotPackage.TEMPLATEABLE_ELEMENT__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_BINDINGS:
				return getOwnedBindings();
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_SIGNATURE:
				return getOwnedSignature();
			case PivotPackage.TEMPLATEABLE_ELEMENT__UNSPECIALIZED_ELEMENT:
				return getUnspecializedElement();
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
			case PivotPackage.TEMPLATEABLE_ELEMENT__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_BINDINGS:
				getOwnedBindings().clear();
				getOwnedBindings().addAll((Collection<? extends TemplateBinding>)newValue);
				return;
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignature)newValue);
				return;
			case PivotPackage.TEMPLATEABLE_ELEMENT__UNSPECIALIZED_ELEMENT:
				setUnspecializedElement((TemplateableElement)newValue);
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
			case PivotPackage.TEMPLATEABLE_ELEMENT__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_BINDINGS:
				getOwnedBindings().clear();
				return;
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignature)null);
				return;
			case PivotPackage.TEMPLATEABLE_ELEMENT__UNSPECIALIZED_ELEMENT:
				setUnspecializedElement((TemplateableElement)null);
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
			case PivotPackage.TEMPLATEABLE_ELEMENT__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_BINDINGS:
				return ownedBindings != null && !ownedBindings.isEmpty();
			case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_SIGNATURE:
				return ownedSignature != null;
			case PivotPackage.TEMPLATEABLE_ELEMENT__UNSPECIALIZED_ELEMENT:
				return unspecializedElement != null;
		}
		return eDynamicIsSet(featureID);
	}

} //TemplateableElementImpl
