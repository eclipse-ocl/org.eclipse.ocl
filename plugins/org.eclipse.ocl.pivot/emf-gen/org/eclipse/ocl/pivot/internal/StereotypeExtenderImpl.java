/*******************************************************************************
 * Copyright (c) 2014, 2015 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink (CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Stereotype;
import org.eclipse.ocl.pivot.StereotypeExtender;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Extension</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.StereotypeExtenderImpl#getClass_ <em>Class</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.StereotypeExtenderImpl#isIsRequired <em>Is Required</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.StereotypeExtenderImpl#getOwningStereotype <em>Owning Stereotype</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StereotypeExtenderImpl extends ElementImpl implements StereotypeExtender
{
	/**
	 * The cached value of the '{@link #getClass_() <em>Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClass_()
	 * @generated
	 * @ordered
	 */
	protected org.eclipse.ocl.pivot.Class class_;

	/**
	 * The default value of the '{@link #isIsRequired() <em>Is Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsRequired()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_REQUIRED_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isIsRequired() <em>Is Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsRequired()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_REQUIRED_EFLAG = 1 << 8;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StereotypeExtenderImpl()
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
		return PivotPackage.Literals.STEREOTYPE_EXTENDER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsRequired(boolean newIsRequired)
	{
		boolean oldIsRequired = (eFlags & IS_REQUIRED_EFLAG) != 0;
		if (newIsRequired) eFlags |= IS_REQUIRED_EFLAG; else eFlags &= ~IS_REQUIRED_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.STEREOTYPE_EXTENDER__IS_REQUIRED, oldIsRequired, newIsRequired));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Stereotype getOwningStereotype()
	{
		if (eContainerFeatureID() != PivotPackage.STEREOTYPE_EXTENDER__OWNING_STEREOTYPE) return null;
		return (Stereotype)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningStereotype(Stereotype newOwningStereotype, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningStereotype, PivotPackage.STEREOTYPE_EXTENDER__OWNING_STEREOTYPE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningStereotype(Stereotype newOwningStereotype)
	{
		if (newOwningStereotype != eInternalContainer() || (eContainerFeatureID() != PivotPackage.STEREOTYPE_EXTENDER__OWNING_STEREOTYPE && newOwningStereotype != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningStereotype))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningStereotype != null)
				msgs = ((InternalEObject)newOwningStereotype).eInverseAdd(this, PivotPackage.STEREOTYPE__OWNED_EXTENDERS, Stereotype.class, msgs);
			msgs = basicSetOwningStereotype(newOwningStereotype, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.STEREOTYPE_EXTENDER__OWNING_STEREOTYPE, newOwningStereotype, newOwningStereotype));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.eclipse.ocl.pivot.Class getClass_()
	{
		if (class_ != null && class_.eIsProxy())
		{
			InternalEObject oldClass = (InternalEObject)class_;
			class_ = (org.eclipse.ocl.pivot.Class)eResolveProxy(oldClass);
			if (class_ != oldClass)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.STEREOTYPE_EXTENDER__CLASS, oldClass, class_));
			}
		}
		return class_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.ocl.pivot.Class basicGetClass()
	{
		return class_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetClass(org.eclipse.ocl.pivot.Class newClass, NotificationChain msgs)
	{
		org.eclipse.ocl.pivot.Class oldClass = class_;
		class_ = newClass;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.STEREOTYPE_EXTENDER__CLASS, oldClass, newClass);
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
	public void setClass(org.eclipse.ocl.pivot.Class newClass)
	{
		if (newClass != class_)
		{
			NotificationChain msgs = null;
			if (class_ != null)
				msgs = ((InternalEObject)class_).eInverseRemove(this, PivotPackage.CLASS__EXTENDERS, org.eclipse.ocl.pivot.Class.class, msgs);
			if (newClass != null)
				msgs = ((InternalEObject)newClass).eInverseAdd(this, PivotPackage.CLASS__EXTENDERS, org.eclipse.ocl.pivot.Class.class, msgs);
			msgs = basicSetClass(newClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.STEREOTYPE_EXTENDER__CLASS, newClass, newClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsRequired()
	{
		return (eFlags & IS_REQUIRED_EFLAG) != 0;
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
			case PivotPackage.STEREOTYPE_EXTENDER__ANNOTATING_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.STEREOTYPE_EXTENDER__OWNED_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.STEREOTYPE_EXTENDER__OWNED_EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case PivotPackage.STEREOTYPE_EXTENDER__CLASS:
				if (class_ != null)
					msgs = ((InternalEObject)class_).eInverseRemove(this, PivotPackage.CLASS__EXTENDERS, org.eclipse.ocl.pivot.Class.class, msgs);
				return basicSetClass((org.eclipse.ocl.pivot.Class)otherEnd, msgs);
			case PivotPackage.STEREOTYPE_EXTENDER__OWNING_STEREOTYPE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningStereotype((Stereotype)otherEnd, msgs);
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
			case PivotPackage.STEREOTYPE_EXTENDER__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.STEREOTYPE_EXTENDER__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.STEREOTYPE_EXTENDER__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.STEREOTYPE_EXTENDER__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.STEREOTYPE_EXTENDER__CLASS:
				return basicSetClass(null, msgs);
			case PivotPackage.STEREOTYPE_EXTENDER__OWNING_STEREOTYPE:
				return basicSetOwningStereotype(null, msgs);
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
			case PivotPackage.STEREOTYPE_EXTENDER__OWNING_STEREOTYPE:
				return eInternalContainer().eInverseRemove(this, PivotPackage.STEREOTYPE__OWNED_EXTENDERS, Stereotype.class, msgs);
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
			case PivotPackage.STEREOTYPE_EXTENDER__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.STEREOTYPE_EXTENDER__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.STEREOTYPE_EXTENDER__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.STEREOTYPE_EXTENDER__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.STEREOTYPE_EXTENDER__CLASS:
				if (resolve) return getClass_();
				return basicGetClass();
			case PivotPackage.STEREOTYPE_EXTENDER__IS_REQUIRED:
				return isIsRequired();
			case PivotPackage.STEREOTYPE_EXTENDER__OWNING_STEREOTYPE:
				return getOwningStereotype();
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
			case PivotPackage.STEREOTYPE_EXTENDER__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.STEREOTYPE_EXTENDER__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.STEREOTYPE_EXTENDER__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.STEREOTYPE_EXTENDER__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.STEREOTYPE_EXTENDER__CLASS:
				setClass((org.eclipse.ocl.pivot.Class)newValue);
				return;
			case PivotPackage.STEREOTYPE_EXTENDER__IS_REQUIRED:
				setIsRequired((Boolean)newValue);
				return;
			case PivotPackage.STEREOTYPE_EXTENDER__OWNING_STEREOTYPE:
				setOwningStereotype((Stereotype)newValue);
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
			case PivotPackage.STEREOTYPE_EXTENDER__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.STEREOTYPE_EXTENDER__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.STEREOTYPE_EXTENDER__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.STEREOTYPE_EXTENDER__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.STEREOTYPE_EXTENDER__CLASS:
				setClass((org.eclipse.ocl.pivot.Class)null);
				return;
			case PivotPackage.STEREOTYPE_EXTENDER__IS_REQUIRED:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case PivotPackage.STEREOTYPE_EXTENDER__OWNING_STEREOTYPE:
				setOwningStereotype((Stereotype)null);
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
			case PivotPackage.STEREOTYPE_EXTENDER__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.STEREOTYPE_EXTENDER__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.STEREOTYPE_EXTENDER__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.STEREOTYPE_EXTENDER__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.STEREOTYPE_EXTENDER__CLASS:
				return class_ != null;
			case PivotPackage.STEREOTYPE_EXTENDER__IS_REQUIRED:
				return ((eFlags & IS_REQUIRED_EFLAG) != 0) != IS_REQUIRED_EDEFAULT;
			case PivotPackage.STEREOTYPE_EXTENDER__OWNING_STEREOTYPE:
				return getOwningStereotype() != null;
		}
		return eDynamicIsSet(featureID);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitStereotypeExtender(this);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public void unloaded(@NonNull ASResource asResource) {
		super.unloaded(asResource);
		setClass(null);
	}
} //TypeExtensionImpl
