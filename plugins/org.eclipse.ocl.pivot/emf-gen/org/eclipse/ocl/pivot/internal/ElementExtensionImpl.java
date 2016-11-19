/*******************************************************************************
 * Copyright (c) 2012, 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
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
import org.eclipse.ocl.pivot.Behavior;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Stereotype;
import org.eclipse.ocl.pivot.StereotypeExtender;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Applied Stereotype</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ElementExtensionImpl#getBase <em>Base</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ElementExtensionImpl#isIsApplied <em>Is Applied</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ElementExtensionImpl#isIsRequired <em>Is Required</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ElementExtensionImpl#getStereotype <em>Stereotype</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ElementExtensionImpl extends ClassImpl implements ElementExtension
{
	/**
	 * The default value of the '{@link #isIsApplied() <em>Is Applied</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsApplied()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_APPLIED_EDEFAULT = false;
	/**
	 * The flag representing the value of the '{@link #isIsApplied() <em>Is Applied</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsApplied()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_APPLIED_EFLAG = 1 << 11;
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
	protected static final int IS_REQUIRED_EFLAG = 1 << 12;
	/**
	 * The cached value of the '{@link #getStereotype() <em>Stereotype</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStereotype()
	 * @generated
	 * @ordered
	 */
	protected Stereotype stereotype;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ElementExtensionImpl()
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
		return PivotPackage.Literals.ELEMENT_EXTENSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Stereotype getStereotype()
	{
		if (stereotype != null && stereotype.eIsProxy())
		{
			InternalEObject oldStereotype = (InternalEObject)stereotype;
			stereotype = (Stereotype)eResolveProxy(oldStereotype);
			if (stereotype != oldStereotype)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.ELEMENT_EXTENSION__STEREOTYPE, oldStereotype, stereotype));
			}
		}
		return stereotype;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Stereotype basicGetStereotype()
	{
		return stereotype;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStereotype(Stereotype newStereotype)
	{
		Stereotype oldStereotype = stereotype;
		stereotype = newStereotype;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.ELEMENT_EXTENSION__STEREOTYPE, oldStereotype, stereotype));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Element getBase()
	{
		if (eContainerFeatureID() != PivotPackage.ELEMENT_EXTENSION__BASE) return null;
		return (Element)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBase(Element newBase, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newBase, PivotPackage.ELEMENT_EXTENSION__BASE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBase(Element newBase)
	{
		if (newBase != eInternalContainer() || (eContainerFeatureID() != PivotPackage.ELEMENT_EXTENSION__BASE && newBase != null))
		{
			if (EcoreUtil.isAncestor(this, newBase))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newBase != null)
				msgs = ((InternalEObject)newBase).eInverseAdd(this, PivotPackage.ELEMENT__OWNED_EXTENSIONS, Element.class, msgs);
			msgs = basicSetBase(newBase, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.ELEMENT_EXTENSION__BASE, newBase, newBase));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsApplied()
	{
		return (eFlags & IS_APPLIED_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsApplied(boolean newIsApplied)
	{
		boolean oldIsApplied = (eFlags & IS_APPLIED_EFLAG) != 0;
		if (newIsApplied) eFlags |= IS_APPLIED_EFLAG; else eFlags &= ~IS_APPLIED_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.ELEMENT_EXTENSION__IS_APPLIED, oldIsApplied, newIsApplied));
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
	@Override
	public void setIsRequired(boolean newIsRequired)
	{
		boolean oldIsRequired = (eFlags & IS_REQUIRED_EFLAG) != 0;
		if (newIsRequired) eFlags |= IS_REQUIRED_EFLAG; else eFlags &= ~IS_REQUIRED_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.ELEMENT_EXTENSION__IS_REQUIRED, oldIsRequired, newIsRequired));
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
			case PivotPackage.ELEMENT_EXTENSION__ANNOTATING_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.ELEMENT_EXTENSION__OWNED_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.ELEMENT_EXTENSION__OWNED_EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case PivotPackage.ELEMENT_EXTENSION__OWNED_BINDINGS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedBindings()).basicAdd(otherEnd, msgs);
			case PivotPackage.ELEMENT_EXTENSION__OWNED_SIGNATURE:
				if (ownedSignature != null)
					msgs = ((InternalEObject)ownedSignature).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.ELEMENT_EXTENSION__OWNED_SIGNATURE, null, msgs);
				return basicSetOwnedSignature((TemplateSignature)otherEnd, msgs);
			case PivotPackage.ELEMENT_EXTENSION__EXTENDERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExtenders()).basicAdd(otherEnd, msgs);
			case PivotPackage.ELEMENT_EXTENSION__OWNED_OPERATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedOperations()).basicAdd(otherEnd, msgs);
			case PivotPackage.ELEMENT_EXTENSION__OWNED_PROPERTIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedProperties()).basicAdd(otherEnd, msgs);
			case PivotPackage.ELEMENT_EXTENSION__OWNING_PACKAGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningPackage((org.eclipse.ocl.pivot.Package)otherEnd, msgs);
			case PivotPackage.ELEMENT_EXTENSION__BASE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetBase((Element)otherEnd, msgs);
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
			case PivotPackage.ELEMENT_EXTENSION__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.ELEMENT_EXTENSION__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.ELEMENT_EXTENSION__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.ELEMENT_EXTENSION__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.ELEMENT_EXTENSION__OWNED_CONSTRAINTS:
				return ((InternalEList<?>)getOwnedConstraints()).basicRemove(otherEnd, msgs);
			case PivotPackage.ELEMENT_EXTENSION__OWNED_BINDINGS:
				return ((InternalEList<?>)getOwnedBindings()).basicRemove(otherEnd, msgs);
			case PivotPackage.ELEMENT_EXTENSION__OWNED_SIGNATURE:
				return basicSetOwnedSignature(null, msgs);
			case PivotPackage.ELEMENT_EXTENSION__EXTENDERS:
				return ((InternalEList<?>)getExtenders()).basicRemove(otherEnd, msgs);
			case PivotPackage.ELEMENT_EXTENSION__OWNED_BEHAVIORS:
				return ((InternalEList<?>)getOwnedBehaviors()).basicRemove(otherEnd, msgs);
			case PivotPackage.ELEMENT_EXTENSION__OWNED_INVARIANTS:
				return ((InternalEList<?>)getOwnedInvariants()).basicRemove(otherEnd, msgs);
			case PivotPackage.ELEMENT_EXTENSION__OWNED_OPERATIONS:
				return ((InternalEList<?>)getOwnedOperations()).basicRemove(otherEnd, msgs);
			case PivotPackage.ELEMENT_EXTENSION__OWNED_PROPERTIES:
				return ((InternalEList<?>)getOwnedProperties()).basicRemove(otherEnd, msgs);
			case PivotPackage.ELEMENT_EXTENSION__OWNING_PACKAGE:
				return basicSetOwningPackage(null, msgs);
			case PivotPackage.ELEMENT_EXTENSION__BASE:
				return basicSetBase(null, msgs);
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
			case PivotPackage.ELEMENT_EXTENSION__OWNING_PACKAGE:
				return eInternalContainer().eInverseRemove(this, PivotPackage.PACKAGE__OWNED_CLASSES, org.eclipse.ocl.pivot.Package.class, msgs);
			case PivotPackage.ELEMENT_EXTENSION__BASE:
				return eInternalContainer().eInverseRemove(this, PivotPackage.ELEMENT__OWNED_EXTENSIONS, Element.class, msgs);
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
			case PivotPackage.ELEMENT_EXTENSION__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.ELEMENT_EXTENSION__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.ELEMENT_EXTENSION__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.ELEMENT_EXTENSION__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.ELEMENT_EXTENSION__NAME:
				return getName();
			case PivotPackage.ELEMENT_EXTENSION__OWNED_CONSTRAINTS:
				return getOwnedConstraints();
			case PivotPackage.ELEMENT_EXTENSION__OWNED_BINDINGS:
				return getOwnedBindings();
			case PivotPackage.ELEMENT_EXTENSION__OWNED_SIGNATURE:
				return getOwnedSignature();
			case PivotPackage.ELEMENT_EXTENSION__UNSPECIALIZED_ELEMENT:
				return getUnspecializedElement();
			case PivotPackage.ELEMENT_EXTENSION__EXTENDERS:
				return getExtenders();
			case PivotPackage.ELEMENT_EXTENSION__INSTANCE_CLASS_NAME:
				return getInstanceClassName();
			case PivotPackage.ELEMENT_EXTENSION__IS_ABSTRACT:
				return isIsAbstract();
			case PivotPackage.ELEMENT_EXTENSION__IS_ACTIVE:
				return isIsActive();
			case PivotPackage.ELEMENT_EXTENSION__IS_INTERFACE:
				return isIsInterface();
			case PivotPackage.ELEMENT_EXTENSION__OWNED_BEHAVIORS:
				return getOwnedBehaviors();
			case PivotPackage.ELEMENT_EXTENSION__OWNED_INVARIANTS:
				return getOwnedInvariants();
			case PivotPackage.ELEMENT_EXTENSION__OWNED_OPERATIONS:
				return getOwnedOperations();
			case PivotPackage.ELEMENT_EXTENSION__OWNED_PROPERTIES:
				return getOwnedProperties();
			case PivotPackage.ELEMENT_EXTENSION__OWNING_PACKAGE:
				return getOwningPackage();
			case PivotPackage.ELEMENT_EXTENSION__SUPER_CLASSES:
				return getSuperClasses();
			case PivotPackage.ELEMENT_EXTENSION__BASE:
				return getBase();
			case PivotPackage.ELEMENT_EXTENSION__IS_APPLIED:
				return isIsApplied();
			case PivotPackage.ELEMENT_EXTENSION__IS_REQUIRED:
				return isIsRequired();
			case PivotPackage.ELEMENT_EXTENSION__STEREOTYPE:
				if (resolve) return getStereotype();
				return basicGetStereotype();
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
			case PivotPackage.ELEMENT_EXTENSION__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.ELEMENT_EXTENSION__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.ELEMENT_EXTENSION__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.ELEMENT_EXTENSION__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.ELEMENT_EXTENSION__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.ELEMENT_EXTENSION__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				getOwnedConstraints().addAll((Collection<? extends Constraint>)newValue);
				return;
			case PivotPackage.ELEMENT_EXTENSION__OWNED_BINDINGS:
				getOwnedBindings().clear();
				getOwnedBindings().addAll((Collection<? extends TemplateBinding>)newValue);
				return;
			case PivotPackage.ELEMENT_EXTENSION__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignature)newValue);
				return;
			case PivotPackage.ELEMENT_EXTENSION__UNSPECIALIZED_ELEMENT:
				setUnspecializedElement((TemplateableElement)newValue);
				return;
			case PivotPackage.ELEMENT_EXTENSION__EXTENDERS:
				getExtenders().clear();
				getExtenders().addAll((Collection<? extends StereotypeExtender>)newValue);
				return;
			case PivotPackage.ELEMENT_EXTENSION__INSTANCE_CLASS_NAME:
				setInstanceClassName((String)newValue);
				return;
			case PivotPackage.ELEMENT_EXTENSION__IS_ABSTRACT:
				setIsAbstract((Boolean)newValue);
				return;
			case PivotPackage.ELEMENT_EXTENSION__IS_ACTIVE:
				setIsActive((Boolean)newValue);
				return;
			case PivotPackage.ELEMENT_EXTENSION__IS_INTERFACE:
				setIsInterface((Boolean)newValue);
				return;
			case PivotPackage.ELEMENT_EXTENSION__OWNED_BEHAVIORS:
				getOwnedBehaviors().clear();
				getOwnedBehaviors().addAll((Collection<? extends Behavior>)newValue);
				return;
			case PivotPackage.ELEMENT_EXTENSION__OWNED_INVARIANTS:
				getOwnedInvariants().clear();
				getOwnedInvariants().addAll((Collection<? extends Constraint>)newValue);
				return;
			case PivotPackage.ELEMENT_EXTENSION__OWNED_OPERATIONS:
				getOwnedOperations().clear();
				getOwnedOperations().addAll((Collection<? extends Operation>)newValue);
				return;
			case PivotPackage.ELEMENT_EXTENSION__OWNED_PROPERTIES:
				getOwnedProperties().clear();
				getOwnedProperties().addAll((Collection<? extends Property>)newValue);
				return;
			case PivotPackage.ELEMENT_EXTENSION__OWNING_PACKAGE:
				setOwningPackage((org.eclipse.ocl.pivot.Package)newValue);
				return;
			case PivotPackage.ELEMENT_EXTENSION__SUPER_CLASSES:
				getSuperClasses().clear();
				getSuperClasses().addAll((Collection<? extends org.eclipse.ocl.pivot.Class>)newValue);
				return;
			case PivotPackage.ELEMENT_EXTENSION__BASE:
				setBase((Element)newValue);
				return;
			case PivotPackage.ELEMENT_EXTENSION__IS_APPLIED:
				setIsApplied((Boolean)newValue);
				return;
			case PivotPackage.ELEMENT_EXTENSION__IS_REQUIRED:
				setIsRequired((Boolean)newValue);
				return;
			case PivotPackage.ELEMENT_EXTENSION__STEREOTYPE:
				setStereotype((Stereotype)newValue);
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
			case PivotPackage.ELEMENT_EXTENSION__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.ELEMENT_EXTENSION__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.ELEMENT_EXTENSION__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.ELEMENT_EXTENSION__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.ELEMENT_EXTENSION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.ELEMENT_EXTENSION__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				return;
			case PivotPackage.ELEMENT_EXTENSION__OWNED_BINDINGS:
				getOwnedBindings().clear();
				return;
			case PivotPackage.ELEMENT_EXTENSION__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignature)null);
				return;
			case PivotPackage.ELEMENT_EXTENSION__UNSPECIALIZED_ELEMENT:
				setUnspecializedElement((TemplateableElement)null);
				return;
			case PivotPackage.ELEMENT_EXTENSION__EXTENDERS:
				getExtenders().clear();
				return;
			case PivotPackage.ELEMENT_EXTENSION__INSTANCE_CLASS_NAME:
				setInstanceClassName(INSTANCE_CLASS_NAME_EDEFAULT);
				return;
			case PivotPackage.ELEMENT_EXTENSION__IS_ABSTRACT:
				setIsAbstract(IS_ABSTRACT_EDEFAULT);
				return;
			case PivotPackage.ELEMENT_EXTENSION__IS_ACTIVE:
				setIsActive(IS_ACTIVE_EDEFAULT);
				return;
			case PivotPackage.ELEMENT_EXTENSION__IS_INTERFACE:
				setIsInterface(IS_INTERFACE_EDEFAULT);
				return;
			case PivotPackage.ELEMENT_EXTENSION__OWNED_BEHAVIORS:
				getOwnedBehaviors().clear();
				return;
			case PivotPackage.ELEMENT_EXTENSION__OWNED_INVARIANTS:
				getOwnedInvariants().clear();
				return;
			case PivotPackage.ELEMENT_EXTENSION__OWNED_OPERATIONS:
				getOwnedOperations().clear();
				return;
			case PivotPackage.ELEMENT_EXTENSION__OWNED_PROPERTIES:
				getOwnedProperties().clear();
				return;
			case PivotPackage.ELEMENT_EXTENSION__OWNING_PACKAGE:
				setOwningPackage((org.eclipse.ocl.pivot.Package)null);
				return;
			case PivotPackage.ELEMENT_EXTENSION__SUPER_CLASSES:
				getSuperClasses().clear();
				return;
			case PivotPackage.ELEMENT_EXTENSION__BASE:
				setBase((Element)null);
				return;
			case PivotPackage.ELEMENT_EXTENSION__IS_APPLIED:
				setIsApplied(IS_APPLIED_EDEFAULT);
				return;
			case PivotPackage.ELEMENT_EXTENSION__IS_REQUIRED:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case PivotPackage.ELEMENT_EXTENSION__STEREOTYPE:
				setStereotype((Stereotype)null);
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
			case PivotPackage.ELEMENT_EXTENSION__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.ELEMENT_EXTENSION__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.ELEMENT_EXTENSION__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.ELEMENT_EXTENSION__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.ELEMENT_EXTENSION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.ELEMENT_EXTENSION__OWNED_CONSTRAINTS:
				return ownedConstraints != null && !ownedConstraints.isEmpty();
			case PivotPackage.ELEMENT_EXTENSION__OWNED_BINDINGS:
				return ownedBindings != null && !ownedBindings.isEmpty();
			case PivotPackage.ELEMENT_EXTENSION__OWNED_SIGNATURE:
				return ownedSignature != null;
			case PivotPackage.ELEMENT_EXTENSION__UNSPECIALIZED_ELEMENT:
				return unspecializedElement != null;
			case PivotPackage.ELEMENT_EXTENSION__EXTENDERS:
				return extenders != null && !extenders.isEmpty();
			case PivotPackage.ELEMENT_EXTENSION__INSTANCE_CLASS_NAME:
				return INSTANCE_CLASS_NAME_EDEFAULT == null ? instanceClassName != null : !INSTANCE_CLASS_NAME_EDEFAULT.equals(instanceClassName);
			case PivotPackage.ELEMENT_EXTENSION__IS_ABSTRACT:
				return ((eFlags & IS_ABSTRACT_EFLAG) != 0) != IS_ABSTRACT_EDEFAULT;
			case PivotPackage.ELEMENT_EXTENSION__IS_ACTIVE:
				return ((eFlags & IS_ACTIVE_EFLAG) != 0) != IS_ACTIVE_EDEFAULT;
			case PivotPackage.ELEMENT_EXTENSION__IS_INTERFACE:
				return ((eFlags & IS_INTERFACE_EFLAG) != 0) != IS_INTERFACE_EDEFAULT;
			case PivotPackage.ELEMENT_EXTENSION__OWNED_BEHAVIORS:
				return ownedBehaviors != null && !ownedBehaviors.isEmpty();
			case PivotPackage.ELEMENT_EXTENSION__OWNED_INVARIANTS:
				return ownedInvariants != null && !ownedInvariants.isEmpty();
			case PivotPackage.ELEMENT_EXTENSION__OWNED_OPERATIONS:
				return ownedOperations != null && !ownedOperations.isEmpty();
			case PivotPackage.ELEMENT_EXTENSION__OWNED_PROPERTIES:
				return ownedProperties != null && !ownedProperties.isEmpty();
			case PivotPackage.ELEMENT_EXTENSION__OWNING_PACKAGE:
				return getOwningPackage() != null;
			case PivotPackage.ELEMENT_EXTENSION__SUPER_CLASSES:
				return superClasses != null && !superClasses.isEmpty();
			case PivotPackage.ELEMENT_EXTENSION__BASE:
				return getBase() != null;
			case PivotPackage.ELEMENT_EXTENSION__IS_APPLIED:
				return ((eFlags & IS_APPLIED_EFLAG) != 0) != IS_APPLIED_EDEFAULT;
			case PivotPackage.ELEMENT_EXTENSION__IS_REQUIRED:
				return ((eFlags & IS_REQUIRED_EFLAG) != 0) != IS_REQUIRED_EDEFAULT;
			case PivotPackage.ELEMENT_EXTENSION__STEREOTYPE:
				return stereotype != null;
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
		return visitor.visitElementExtension(this);
	}

	@Override
	public @NonNull CompleteInheritance getInheritance(@NonNull StandardLibrary standardLibrary) {
		return stereotype.getInheritance(standardLibrary);
	}

	@Override
	public org.eclipse.ocl.pivot.Package getPackage() {
		return PivotUtil.getContainingPackage(eContainer());
	}
} //ElementExtensionImpl
