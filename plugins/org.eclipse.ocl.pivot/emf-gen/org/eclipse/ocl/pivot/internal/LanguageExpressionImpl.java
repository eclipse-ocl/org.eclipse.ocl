/**
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Type;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Language Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.LanguageExpressionImpl#getBody <em>Body</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.LanguageExpressionImpl#getLanguage <em>Language</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.LanguageExpressionImpl#getOwningConstraint <em>Owning Constraint</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class LanguageExpressionImpl extends ValueSpecificationImpl implements LanguageExpression
{
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
	 * The default value of the '{@link #getLanguage() <em>Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLanguage()
	 * @generated
	 * @ordered
	 */
	protected static final String LANGUAGE_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LanguageExpressionImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PivotPackage.Literals.LANGUAGE_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getBody()
	{
		return body;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBody(String newBody)
	{
		String oldBody = body;
		body = newBody;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.LANGUAGE_EXPRESSION__BODY, oldBody, body));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLanguage()
	{
		throw new UnsupportedOperationException();  // FIXME Unimplemented http://www.eclipse.org/ocl/2015/Pivot!LanguageExpression!language
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Constraint getOwningConstraint()
	{
		if (eContainerFeatureID() != PivotPackage.LANGUAGE_EXPRESSION__OWNING_CONSTRAINT) return null;
		return (Constraint)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningConstraint(Constraint newOwningConstraint, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningConstraint, PivotPackage.LANGUAGE_EXPRESSION__OWNING_CONSTRAINT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningConstraint(Constraint newOwningConstraint)
	{
		if (newOwningConstraint != eInternalContainer() || (eContainerFeatureID() != PivotPackage.LANGUAGE_EXPRESSION__OWNING_CONSTRAINT && newOwningConstraint != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningConstraint))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningConstraint != null)
				msgs = ((InternalEObject)newOwningConstraint).eInverseAdd(this, PivotPackage.CONSTRAINT__OWNED_SPECIFICATION, Constraint.class, msgs);
			msgs = basicSetOwningConstraint(newOwningConstraint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.LANGUAGE_EXPRESSION__OWNING_CONSTRAINT, newOwningConstraint, newOwningConstraint));
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
			case PivotPackage.LANGUAGE_EXPRESSION__ANNOTATING_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.LANGUAGE_EXPRESSION__OWNED_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.LANGUAGE_EXPRESSION__OWNED_EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case PivotPackage.LANGUAGE_EXPRESSION__OWNING_CONSTRAINT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningConstraint((Constraint)otherEnd, msgs);
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
			case PivotPackage.LANGUAGE_EXPRESSION__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.LANGUAGE_EXPRESSION__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.LANGUAGE_EXPRESSION__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.LANGUAGE_EXPRESSION__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.LANGUAGE_EXPRESSION__OWNING_CONSTRAINT:
				return basicSetOwningConstraint(null, msgs);
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
			case PivotPackage.LANGUAGE_EXPRESSION__OWNING_CONSTRAINT:
				return eInternalContainer().eInverseRemove(this, PivotPackage.CONSTRAINT__OWNED_SPECIFICATION, Constraint.class, msgs);
		}
		return eDynamicBasicRemoveFromContainer(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch (featureID)
		{
			case PivotPackage.LANGUAGE_EXPRESSION__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.LANGUAGE_EXPRESSION__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.LANGUAGE_EXPRESSION__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.LANGUAGE_EXPRESSION__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.LANGUAGE_EXPRESSION__NAME:
				return getName();
			case PivotPackage.LANGUAGE_EXPRESSION__IS_MANY:
				return isIsMany();
			case PivotPackage.LANGUAGE_EXPRESSION__IS_REQUIRED:
				return isIsRequired();
			case PivotPackage.LANGUAGE_EXPRESSION__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case PivotPackage.LANGUAGE_EXPRESSION__BODY:
				return getBody();
			case PivotPackage.LANGUAGE_EXPRESSION__LANGUAGE:
				return getLanguage();
			case PivotPackage.LANGUAGE_EXPRESSION__OWNING_CONSTRAINT:
				return getOwningConstraint();
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
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case PivotPackage.LANGUAGE_EXPRESSION__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.LANGUAGE_EXPRESSION__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.LANGUAGE_EXPRESSION__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.LANGUAGE_EXPRESSION__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.LANGUAGE_EXPRESSION__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.LANGUAGE_EXPRESSION__IS_REQUIRED:
				setIsRequired((Boolean)newValue);
				return;
			case PivotPackage.LANGUAGE_EXPRESSION__TYPE:
				setType((Type)newValue);
				return;
			case PivotPackage.LANGUAGE_EXPRESSION__BODY:
				setBody((String)newValue);
				return;
			case PivotPackage.LANGUAGE_EXPRESSION__OWNING_CONSTRAINT:
				setOwningConstraint((Constraint)newValue);
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
	public void eUnset(int featureID)
	{
		switch (featureID)
		{
			case PivotPackage.LANGUAGE_EXPRESSION__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.LANGUAGE_EXPRESSION__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.LANGUAGE_EXPRESSION__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.LANGUAGE_EXPRESSION__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.LANGUAGE_EXPRESSION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.LANGUAGE_EXPRESSION__IS_REQUIRED:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case PivotPackage.LANGUAGE_EXPRESSION__TYPE:
				setType((Type)null);
				return;
			case PivotPackage.LANGUAGE_EXPRESSION__BODY:
				setBody(BODY_EDEFAULT);
				return;
			case PivotPackage.LANGUAGE_EXPRESSION__OWNING_CONSTRAINT:
				setOwningConstraint((Constraint)null);
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
	public boolean eIsSet(int featureID)
	{
		switch (featureID)
		{
			case PivotPackage.LANGUAGE_EXPRESSION__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.LANGUAGE_EXPRESSION__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.LANGUAGE_EXPRESSION__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.LANGUAGE_EXPRESSION__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.LANGUAGE_EXPRESSION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.LANGUAGE_EXPRESSION__IS_MANY:
				return isIsMany() != IS_MANY_EDEFAULT;
			case PivotPackage.LANGUAGE_EXPRESSION__IS_REQUIRED:
				return ((eFlags & IS_REQUIRED_EFLAG) != 0) != IS_REQUIRED_EDEFAULT;
			case PivotPackage.LANGUAGE_EXPRESSION__TYPE:
				return type != null;
			case PivotPackage.LANGUAGE_EXPRESSION__BODY:
				return BODY_EDEFAULT == null ? body != null : !BODY_EDEFAULT.equals(body);
			case PivotPackage.LANGUAGE_EXPRESSION__LANGUAGE:
				return LANGUAGE_EDEFAULT == null ? getLanguage() != null : !LANGUAGE_EDEFAULT.equals(getLanguage());
			case PivotPackage.LANGUAGE_EXPRESSION__OWNING_CONSTRAINT:
				return getOwningConstraint() != null;
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

} //LanguageExpressionImpl
