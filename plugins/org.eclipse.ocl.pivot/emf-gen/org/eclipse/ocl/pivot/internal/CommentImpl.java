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
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Comment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CommentImpl#getAnnotatedElements <em>Annotated Elements</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CommentImpl#getBody <em>Body</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CommentImpl#getOwningElement <em>Owning Element</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CommentImpl
		extends ElementImpl
		implements Comment {

	/**
	 * The cached value of the '{@link #getAnnotatedElements() <em>Annotated Elements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnnotatedElements()
	 * @generated
	 * @ordered
	 */
	protected EList<Element> annotatedElements;

	/**
	 * The default value of the '{@link #getBody() <em>Body</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBody()
	 * @generated
	 * @ordered
	 */
	protected static final String BODY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBody() <em>Body</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBody()
	 * @generated
	 * @ordered
	 */
	protected String body = BODY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CommentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.COMMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Element> getAnnotatedElements()
	{
		if (annotatedElements == null)
		{
			annotatedElements = new EObjectWithInverseResolvingEList.ManyInverse<Element>(Element.class, this, PivotPackage.COMMENT__ANNOTATED_ELEMENTS, PivotPackage.ELEMENT__ANNOTATING_COMMENTS);
		}
		return annotatedElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getBody() {
		return body;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBody(String newBody) {
		String oldBody = body;
		body = newBody;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.COMMENT__BODY, oldBody, body));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Element getOwningElement()
	{
		if (eContainerFeatureID() != PivotPackage.COMMENT__OWNING_ELEMENT) return null;
		return (Element)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningElement(Element newOwningElement, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningElement, PivotPackage.COMMENT__OWNING_ELEMENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningElement(Element newOwningElement)
	{
		if (newOwningElement != eInternalContainer() || (eContainerFeatureID() != PivotPackage.COMMENT__OWNING_ELEMENT && newOwningElement != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningElement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningElement != null)
				msgs = ((InternalEObject)newOwningElement).eInverseAdd(this, PivotPackage.ELEMENT__OWNED_COMMENTS, Element.class, msgs);
			msgs = basicSetOwningElement(newOwningElement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.COMMENT__OWNING_ELEMENT, newOwningElement, newOwningElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case PivotPackage.COMMENT__ANNOTATING_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.COMMENT__OWNED_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.COMMENT__OWNED_EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case PivotPackage.COMMENT__ANNOTATED_ELEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatedElements()).basicAdd(otherEnd, msgs);
			case PivotPackage.COMMENT__OWNING_ELEMENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningElement((Element)otherEnd, msgs);
		}
		return eDynamicInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case PivotPackage.COMMENT__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.COMMENT__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.COMMENT__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.COMMENT__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.COMMENT__ANNOTATED_ELEMENTS:
				return ((InternalEList<?>)getAnnotatedElements()).basicRemove(otherEnd, msgs);
			case PivotPackage.COMMENT__OWNING_ELEMENT:
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
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
	{
		switch (eContainerFeatureID())
		{
			case PivotPackage.COMMENT__OWNING_ELEMENT:
				return eInternalContainer().eInverseRemove(this, PivotPackage.ELEMENT__OWNED_COMMENTS, Element.class, msgs);
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
			case PivotPackage.COMMENT__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.COMMENT__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.COMMENT__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.COMMENT__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.COMMENT__ANNOTATED_ELEMENTS:
				return getAnnotatedElements();
			case PivotPackage.COMMENT__BODY:
				return getBody();
			case PivotPackage.COMMENT__OWNING_ELEMENT:
				return getOwningElement();
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
			case PivotPackage.COMMENT__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.COMMENT__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.COMMENT__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.COMMENT__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.COMMENT__ANNOTATED_ELEMENTS:
				getAnnotatedElements().clear();
				getAnnotatedElements().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.COMMENT__BODY:
				setBody((String)newValue);
				return;
			case PivotPackage.COMMENT__OWNING_ELEMENT:
				setOwningElement((Element)newValue);
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
			case PivotPackage.COMMENT__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.COMMENT__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.COMMENT__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.COMMENT__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.COMMENT__ANNOTATED_ELEMENTS:
				getAnnotatedElements().clear();
				return;
			case PivotPackage.COMMENT__BODY:
				setBody(BODY_EDEFAULT);
				return;
			case PivotPackage.COMMENT__OWNING_ELEMENT:
				setOwningElement((Element)null);
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
			case PivotPackage.COMMENT__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.COMMENT__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.COMMENT__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.COMMENT__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.COMMENT__ANNOTATED_ELEMENTS:
				return annotatedElements != null && !annotatedElements.isEmpty();
			case PivotPackage.COMMENT__BODY:
				return BODY_EDEFAULT == null ? body != null : !BODY_EDEFAULT.equals(body);
			case PivotPackage.COMMENT__OWNING_ELEMENT:
				return getOwningElement() != null;
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
		return visitor.visitComment(this);
	}

} //CommentImpl
