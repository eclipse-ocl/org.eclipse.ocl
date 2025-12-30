/*******************************************************************************
 * Copyright (c) 2014, 2022 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StereotypeExtender;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateArgument;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.WildcardType;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Wildcard Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.WildcardTypeImpl#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.WildcardTypeImpl#getOwningTemplateableElement <em>Owning Templateable Element</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.WildcardTypeImpl#getUpperBound <em>Upper Bound</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WildcardTypeImpl extends ClassImpl implements WildcardType
{
	/**
	 * The number of structural features of the '<em>Wildcard Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int WILDCARD_TYPE_FEATURE_COUNT = ClassImpl.CLASS_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Wildcard Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int WILDCARD_TYPE_OPERATION_COUNT = ClassImpl.CLASS_OPERATION_COUNT + 0;

	/**
	 * The cached value of the '{@link #getLowerBound() <em>Lower Bound</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLowerBound()
	 * @generated
	 * @ordered
	 */
	protected Type lowerBound;

	/**
	 * The cached value of the '{@link #getUpperBound() <em>Upper Bound</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpperBound()
	 * @generated
	 * @ordered
	 */
	protected Type upperBound;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WildcardTypeImpl()
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
		return PivotPackage.Literals.WILDCARD_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Type getLowerBound()
	{
		if (lowerBound != null && lowerBound.eIsProxy())
		{
			InternalEObject oldLowerBound = (InternalEObject)lowerBound;
			lowerBound = (Type)eResolveProxy(oldLowerBound);
			if (lowerBound != oldLowerBound)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 21, oldLowerBound, lowerBound));
			}
		}
		return lowerBound;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetLowerBound()
	{
		return lowerBound;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLowerBound(Type newLowerBound)
	{
		Type oldLowerBound = lowerBound;
		lowerBound = newLowerBound;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 21, oldLowerBound, lowerBound));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TemplateableElement getOwningTemplateableElement()
	{
		if (eContainerFeatureID() != (22)) return null;
		return (TemplateableElement)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningTemplateableElement(TemplateableElement newOwningTemplateableElement, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningTemplateableElement, 22, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningTemplateableElement(TemplateableElement newOwningTemplateableElement)
	{
		if (newOwningTemplateableElement != eInternalContainer() || (eContainerFeatureID() != (22) && newOwningTemplateableElement != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningTemplateableElement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningTemplateableElement != null)
				msgs = ((InternalEObject)newOwningTemplateableElement).eInverseAdd(this, 7, TemplateableElement.class, msgs);
			msgs = basicSetOwningTemplateableElement(newOwningTemplateableElement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 22, newOwningTemplateableElement, newOwningTemplateableElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Type getUpperBound()
	{
		if (upperBound != null && upperBound.eIsProxy())
		{
			InternalEObject oldUpperBound = (InternalEObject)upperBound;
			upperBound = (Type)eResolveProxy(oldUpperBound);
			if (upperBound != oldUpperBound)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 23, oldUpperBound, upperBound));
			}
		}
		return upperBound;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetUpperBound()
	{
		return upperBound;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUpperBound(Type newUpperBound)
	{
		Type oldUpperBound = upperBound;
		upperBound = newUpperBound;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 23, oldUpperBound, upperBound));
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
			case 0:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case 2:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case 3:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case 7:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedTemplateArguments()).basicAdd(otherEnd, msgs);
			case 8:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedTemplateParameters()).basicAdd(otherEnd, msgs);
			case 9:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedWildcards()).basicAdd(otherEnd, msgs);
			case 10:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExtenders()).basicAdd(otherEnd, msgs);
			case 17:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedOperations()).basicAdd(otherEnd, msgs);
			case 18:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedProperties()).basicAdd(otherEnd, msgs);
			case 19:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningPackage((org.eclipse.ocl.pivot.Package)otherEnd, msgs);
			case 22:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningTemplateableElement((TemplateableElement)otherEnd, msgs);
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
			case 0:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case 1:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case 2:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case 3:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case 5:
				return ((InternalEList<?>)getOwnedConstraints()).basicRemove(otherEnd, msgs);
			case 7:
				return ((InternalEList<?>)getOwnedTemplateArguments()).basicRemove(otherEnd, msgs);
			case 8:
				return ((InternalEList<?>)getOwnedTemplateParameters()).basicRemove(otherEnd, msgs);
			case 9:
				return ((InternalEList<?>)getOwnedWildcards()).basicRemove(otherEnd, msgs);
			case 10:
				return ((InternalEList<?>)getExtenders()).basicRemove(otherEnd, msgs);
			case 15:
				return ((InternalEList<?>)getOwnedBehaviors()).basicRemove(otherEnd, msgs);
			case 16:
				return ((InternalEList<?>)getOwnedInvariants()).basicRemove(otherEnd, msgs);
			case 17:
				return ((InternalEList<?>)getOwnedOperations()).basicRemove(otherEnd, msgs);
			case 18:
				return ((InternalEList<?>)getOwnedProperties()).basicRemove(otherEnd, msgs);
			case 19:
				return basicSetOwningPackage(null, msgs);
			case 22:
				return basicSetOwningTemplateableElement(null, msgs);
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
			case 19:
				return eInternalContainer().eInverseRemove(this, 9, org.eclipse.ocl.pivot.Package.class, msgs);
			case 22:
				return eInternalContainer().eInverseRemove(this, 7, TemplateableElement.class, msgs);
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
			case 0:
				return getAnnotatingComments();
			case 1:
				return getOwnedAnnotations();
			case 2:
				return getOwnedComments();
			case 3:
				return getOwnedExtensions();
			case 4:
				return getName();
			case 5:
				return getOwnedConstraints();
			case 6:
				if (resolve) return getGeneric();
				return basicGetGeneric();
			case 7:
				return getOwnedTemplateArguments();
			case 8:
				return getOwnedTemplateParameters();
			case 9:
				return getOwnedWildcards();
			case 10:
				return getExtenders();
			case 11:
				return getInstanceClassName();
			case 12:
				return isIsAbstract();
			case 13:
				return isIsActive();
			case 14:
				return isIsInterface();
			case 15:
				return getOwnedBehaviors();
			case 16:
				return getOwnedInvariants();
			case 17:
				return getOwnedOperations();
			case 18:
				return getOwnedProperties();
			case 19:
				return getOwningPackage();
			case 20:
				return getSuperClasses();
			case 21:
				if (resolve) return getLowerBound();
				return basicGetLowerBound();
			case 22:
				return getOwningTemplateableElement();
			case 23:
				if (resolve) return getUpperBound();
				return basicGetUpperBound();
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
			case 0:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 1:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case 2:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 3:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case 4:
				setName((String)newValue);
				return;
			case 5:
				getOwnedConstraints().clear();
				getOwnedConstraints().addAll((Collection<? extends Constraint>)newValue);
				return;
			case 6:
				setGeneric((TemplateableElement)newValue);
				return;
			case 7:
				getOwnedTemplateArguments().clear();
				getOwnedTemplateArguments().addAll((Collection<? extends TemplateArgument>)newValue);
				return;
			case 8:
				getOwnedTemplateParameters().clear();
				getOwnedTemplateParameters().addAll((Collection<? extends TemplateParameter>)newValue);
				return;
			case 9:
				getOwnedWildcards().clear();
				getOwnedWildcards().addAll((Collection<? extends WildcardType>)newValue);
				return;
			case 10:
				getExtenders().clear();
				getExtenders().addAll((Collection<? extends StereotypeExtender>)newValue);
				return;
			case 11:
				setInstanceClassName((String)newValue);
				return;
			case 12:
				setIsAbstract((Boolean)newValue);
				return;
			case 13:
				setIsActive((Boolean)newValue);
				return;
			case 14:
				setIsInterface((Boolean)newValue);
				return;
			case 15:
				getOwnedBehaviors().clear();
				getOwnedBehaviors().addAll((Collection<? extends Behavior>)newValue);
				return;
			case 16:
				getOwnedInvariants().clear();
				getOwnedInvariants().addAll((Collection<? extends Constraint>)newValue);
				return;
			case 17:
				getOwnedOperations().clear();
				getOwnedOperations().addAll((Collection<? extends Operation>)newValue);
				return;
			case 18:
				getOwnedProperties().clear();
				getOwnedProperties().addAll((Collection<? extends Property>)newValue);
				return;
			case 19:
				setOwningPackage((org.eclipse.ocl.pivot.Package)newValue);
				return;
			case 20:
				getSuperClasses().clear();
				getSuperClasses().addAll((Collection<? extends org.eclipse.ocl.pivot.Class>)newValue);
				return;
			case 21:
				setLowerBound((Type)newValue);
				return;
			case 22:
				setOwningTemplateableElement((TemplateableElement)newValue);
				return;
			case 23:
				setUpperBound((Type)newValue);
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
			case 0:
				getAnnotatingComments().clear();
				return;
			case 1:
				getOwnedAnnotations().clear();
				return;
			case 2:
				getOwnedComments().clear();
				return;
			case 3:
				getOwnedExtensions().clear();
				return;
			case 4:
				setName(NAME_EDEFAULT);
				return;
			case 5:
				getOwnedConstraints().clear();
				return;
			case 6:
				setGeneric((TemplateableElement)null);
				return;
			case 7:
				getOwnedTemplateArguments().clear();
				return;
			case 8:
				getOwnedTemplateParameters().clear();
				return;
			case 9:
				getOwnedWildcards().clear();
				return;
			case 10:
				getExtenders().clear();
				return;
			case 11:
				setInstanceClassName(INSTANCE_CLASS_NAME_EDEFAULT);
				return;
			case 12:
				setIsAbstract(IS_ABSTRACT_EDEFAULT);
				return;
			case 13:
				setIsActive(IS_ACTIVE_EDEFAULT);
				return;
			case 14:
				setIsInterface(IS_INTERFACE_EDEFAULT);
				return;
			case 15:
				getOwnedBehaviors().clear();
				return;
			case 16:
				getOwnedInvariants().clear();
				return;
			case 17:
				getOwnedOperations().clear();
				return;
			case 18:
				getOwnedProperties().clear();
				return;
			case 19:
				setOwningPackage((org.eclipse.ocl.pivot.Package)null);
				return;
			case 20:
				getSuperClasses().clear();
				return;
			case 21:
				setLowerBound((Type)null);
				return;
			case 22:
				setOwningTemplateableElement((TemplateableElement)null);
				return;
			case 23:
				setUpperBound((Type)null);
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
			case 0:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case 1:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case 2:
				return ownedComments != null && !ownedComments.isEmpty();
			case 3:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case 4:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case 5:
				return ownedConstraints != null && !ownedConstraints.isEmpty();
			case 6:
				return generic != null;
			case 7:
				return ownedTemplateArguments != null && !ownedTemplateArguments.isEmpty();
			case 8:
				return ownedTemplateParameters != null && !ownedTemplateParameters.isEmpty();
			case 9:
				return ownedWildcards != null && !ownedWildcards.isEmpty();
			case 10:
				return extenders != null && !extenders.isEmpty();
			case 11:
				return INSTANCE_CLASS_NAME_EDEFAULT == null ? instanceClassName != null : !INSTANCE_CLASS_NAME_EDEFAULT.equals(instanceClassName);
			case 12:
				return ((eFlags & IS_ABSTRACT_EFLAG) != 0) != IS_ABSTRACT_EDEFAULT;
			case 13:
				return ((eFlags & IS_ACTIVE_EFLAG) != 0) != IS_ACTIVE_EDEFAULT;
			case 14:
				return ((eFlags & IS_INTERFACE_EFLAG) != 0) != IS_INTERFACE_EDEFAULT;
			case 15:
				return ownedBehaviors != null && !ownedBehaviors.isEmpty();
			case 16:
				return ownedInvariants != null && !ownedInvariants.isEmpty();
			case 17:
				return ownedOperations != null && !ownedOperations.isEmpty();
			case 18:
				return ownedProperties != null && !ownedProperties.isEmpty();
			case 19:
				return getOwningPackage() != null;
			case 20:
				return superClasses != null && !superClasses.isEmpty();
			case 21:
				return lowerBound != null;
			case 22:
				return getOwningTemplateableElement() != null;
			case 23:
				return upperBound != null;
		}
		return eDynamicIsSet(featureID);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitWildcardType(this);
	}

	@Override
	public @NonNull TypeId computeId() {
		return IdManager.getWildcardId();
	}

} //WildcardTypeImpl
