/**
 * Copyright (c) 2014, 2016 Willink Transformations and others.
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
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Behavior;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.DynamicBehavior;
import org.eclipse.ocl.pivot.DynamicElement;
import org.eclipse.ocl.pivot.DynamicProperty;
import org.eclipse.ocl.pivot.DynamicType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StereotypeExtender;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.Transition;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dynamic Behavior</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.DynamicBehaviorImpl#getMetaType <em>Meta Type</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.DynamicBehaviorImpl#getOwnedDynamicProperties <em>Owned Dynamic Properties</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DynamicBehaviorImpl extends BehaviorImpl implements DynamicBehavior
{
	/**
	 * The cached value of the '{@link #getMetaType() <em>Meta Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetaType()
	 * @generated
	 * @ordered
	 */
	protected Type metaType;
	/**
	 * The cached value of the '{@link #getOwnedDynamicProperties() <em>Owned Dynamic Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedDynamicProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<DynamicProperty> ownedDynamicProperties;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DynamicBehaviorImpl()
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
		return PivotPackage.Literals.DYNAMIC_BEHAVIOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Type getMetaType()
	{
		if (metaType != null && metaType.eIsProxy())
		{
			InternalEObject oldMetaType = (InternalEObject)metaType;
			metaType = (Type)eResolveProxy(oldMetaType);
			if (metaType != oldMetaType)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.DYNAMIC_BEHAVIOR__META_TYPE, oldMetaType, metaType));
			}
		}
		return metaType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetMetaType()
	{
		return metaType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMetaType(Type newMetaType)
	{
		Type oldMetaType = metaType;
		metaType = newMetaType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.DYNAMIC_BEHAVIOR__META_TYPE, oldMetaType, metaType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("null")
	public @NonNull List<DynamicProperty> getOwnedDynamicProperties()
	{
		if (ownedDynamicProperties == null)
		{
			ownedDynamicProperties = new EObjectContainmentEList<DynamicProperty>(DynamicProperty.class, this, PivotPackage.DYNAMIC_BEHAVIOR__OWNED_DYNAMIC_PROPERTIES);
		}
		return ownedDynamicProperties;
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
			case PivotPackage.DYNAMIC_BEHAVIOR__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_CONSTRAINTS:
				return ((InternalEList<?>)getOwnedConstraints()).basicRemove(otherEnd, msgs);
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_BINDINGS:
				return ((InternalEList<?>)getOwnedBindings()).basicRemove(otherEnd, msgs);
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_SIGNATURE:
				return basicSetOwnedSignature(null, msgs);
			case PivotPackage.DYNAMIC_BEHAVIOR__EXTENDERS:
				return ((InternalEList<?>)getExtenders()).basicRemove(otherEnd, msgs);
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_BEHAVIORS:
				return ((InternalEList<?>)getOwnedBehaviors()).basicRemove(otherEnd, msgs);
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_INVARIANTS:
				return ((InternalEList<?>)getOwnedInvariants()).basicRemove(otherEnd, msgs);
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_OPERATIONS:
				return ((InternalEList<?>)getOwnedOperations()).basicRemove(otherEnd, msgs);
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_PROPERTIES:
				return ((InternalEList<?>)getOwnedProperties()).basicRemove(otherEnd, msgs);
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNING_PACKAGE:
				return basicSetOwningPackage(null, msgs);
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNING_TRANSITION:
				return basicSetOwningTransition(null, msgs);
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_DYNAMIC_PROPERTIES:
				return ((InternalEList<?>)getOwnedDynamicProperties()).basicRemove(otherEnd, msgs);
		}
		return eDynamicInverseRemove(otherEnd, featureID, msgs);
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
			case PivotPackage.DYNAMIC_BEHAVIOR__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.DYNAMIC_BEHAVIOR__NAME:
				return getName();
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_CONSTRAINTS:
				return getOwnedConstraints();
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_BINDINGS:
				return getOwnedBindings();
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_SIGNATURE:
				return getOwnedSignature();
			case PivotPackage.DYNAMIC_BEHAVIOR__UNSPECIALIZED_ELEMENT:
				return getUnspecializedElement();
			case PivotPackage.DYNAMIC_BEHAVIOR__EXTENDERS:
				return getExtenders();
			case PivotPackage.DYNAMIC_BEHAVIOR__INSTANCE_CLASS_NAME:
				return getInstanceClassName();
			case PivotPackage.DYNAMIC_BEHAVIOR__IS_ABSTRACT:
				return isIsAbstract();
			case PivotPackage.DYNAMIC_BEHAVIOR__IS_ACTIVE:
				return isIsActive();
			case PivotPackage.DYNAMIC_BEHAVIOR__IS_INTERFACE:
				return isIsInterface();
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_BEHAVIORS:
				return getOwnedBehaviors();
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_INVARIANTS:
				return getOwnedInvariants();
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_OPERATIONS:
				return getOwnedOperations();
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_PROPERTIES:
				return getOwnedProperties();
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNING_PACKAGE:
				return getOwningPackage();
			case PivotPackage.DYNAMIC_BEHAVIOR__SUPER_CLASSES:
				return getSuperClasses();
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNING_TRANSITION:
				return getOwningTransition();
			case PivotPackage.DYNAMIC_BEHAVIOR__META_TYPE:
				if (resolve) return getMetaType();
				return basicGetMetaType();
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_DYNAMIC_PROPERTIES:
				return getOwnedDynamicProperties();
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
			case PivotPackage.DYNAMIC_BEHAVIOR__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				getOwnedConstraints().addAll((Collection<? extends Constraint>)newValue);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_BINDINGS:
				getOwnedBindings().clear();
				getOwnedBindings().addAll((Collection<? extends TemplateBinding>)newValue);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignature)newValue);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__UNSPECIALIZED_ELEMENT:
				setUnspecializedElement((TemplateableElement)newValue);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__EXTENDERS:
				getExtenders().clear();
				getExtenders().addAll((Collection<? extends StereotypeExtender>)newValue);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__INSTANCE_CLASS_NAME:
				setInstanceClassName((String)newValue);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__IS_ABSTRACT:
				setIsAbstract((Boolean)newValue);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__IS_ACTIVE:
				setIsActive((Boolean)newValue);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__IS_INTERFACE:
				setIsInterface((Boolean)newValue);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_BEHAVIORS:
				getOwnedBehaviors().clear();
				getOwnedBehaviors().addAll((Collection<? extends Behavior>)newValue);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_INVARIANTS:
				getOwnedInvariants().clear();
				getOwnedInvariants().addAll((Collection<? extends Constraint>)newValue);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_OPERATIONS:
				getOwnedOperations().clear();
				getOwnedOperations().addAll((Collection<? extends Operation>)newValue);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_PROPERTIES:
				getOwnedProperties().clear();
				getOwnedProperties().addAll((Collection<? extends Property>)newValue);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNING_PACKAGE:
				setOwningPackage((org.eclipse.ocl.pivot.Package)newValue);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__SUPER_CLASSES:
				getSuperClasses().clear();
				getSuperClasses().addAll((Collection<? extends org.eclipse.ocl.pivot.Class>)newValue);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNING_TRANSITION:
				setOwningTransition((Transition)newValue);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__META_TYPE:
				setMetaType((Type)newValue);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_DYNAMIC_PROPERTIES:
				getOwnedDynamicProperties().clear();
				getOwnedDynamicProperties().addAll((Collection<? extends DynamicProperty>)newValue);
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
			case PivotPackage.DYNAMIC_BEHAVIOR__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_BINDINGS:
				getOwnedBindings().clear();
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignature)null);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__UNSPECIALIZED_ELEMENT:
				setUnspecializedElement((TemplateableElement)null);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__EXTENDERS:
				getExtenders().clear();
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__INSTANCE_CLASS_NAME:
				setInstanceClassName(INSTANCE_CLASS_NAME_EDEFAULT);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__IS_ABSTRACT:
				setIsAbstract(IS_ABSTRACT_EDEFAULT);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__IS_ACTIVE:
				setIsActive(IS_ACTIVE_EDEFAULT);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__IS_INTERFACE:
				setIsInterface(IS_INTERFACE_EDEFAULT);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_BEHAVIORS:
				getOwnedBehaviors().clear();
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_INVARIANTS:
				getOwnedInvariants().clear();
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_OPERATIONS:
				getOwnedOperations().clear();
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_PROPERTIES:
				getOwnedProperties().clear();
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNING_PACKAGE:
				setOwningPackage((org.eclipse.ocl.pivot.Package)null);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__SUPER_CLASSES:
				getSuperClasses().clear();
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNING_TRANSITION:
				setOwningTransition((Transition)null);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__META_TYPE:
				setMetaType((Type)null);
				return;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_DYNAMIC_PROPERTIES:
				getOwnedDynamicProperties().clear();
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
			case PivotPackage.DYNAMIC_BEHAVIOR__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.DYNAMIC_BEHAVIOR__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_CONSTRAINTS:
				return ownedConstraints != null && !ownedConstraints.isEmpty();
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_BINDINGS:
				return ownedBindings != null && !ownedBindings.isEmpty();
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_SIGNATURE:
				return ownedSignature != null;
			case PivotPackage.DYNAMIC_BEHAVIOR__UNSPECIALIZED_ELEMENT:
				return unspecializedElement != null;
			case PivotPackage.DYNAMIC_BEHAVIOR__EXTENDERS:
				return extenders != null && !extenders.isEmpty();
			case PivotPackage.DYNAMIC_BEHAVIOR__INSTANCE_CLASS_NAME:
				return INSTANCE_CLASS_NAME_EDEFAULT == null ? instanceClassName != null : !INSTANCE_CLASS_NAME_EDEFAULT.equals(instanceClassName);
			case PivotPackage.DYNAMIC_BEHAVIOR__IS_ABSTRACT:
				return ((eFlags & IS_ABSTRACT_EFLAG) != 0) != IS_ABSTRACT_EDEFAULT;
			case PivotPackage.DYNAMIC_BEHAVIOR__IS_ACTIVE:
				return ((eFlags & IS_ACTIVE_EFLAG) != 0) != IS_ACTIVE_EDEFAULT;
			case PivotPackage.DYNAMIC_BEHAVIOR__IS_INTERFACE:
				return ((eFlags & IS_INTERFACE_EFLAG) != 0) != IS_INTERFACE_EDEFAULT;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_BEHAVIORS:
				return ownedBehaviors != null && !ownedBehaviors.isEmpty();
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_INVARIANTS:
				return ownedInvariants != null && !ownedInvariants.isEmpty();
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_OPERATIONS:
				return ownedOperations != null && !ownedOperations.isEmpty();
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_PROPERTIES:
				return ownedProperties != null && !ownedProperties.isEmpty();
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNING_PACKAGE:
				return getOwningPackage() != null;
			case PivotPackage.DYNAMIC_BEHAVIOR__SUPER_CLASSES:
				return superClasses != null && !superClasses.isEmpty();
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNING_TRANSITION:
				return getOwningTransition() != null;
			case PivotPackage.DYNAMIC_BEHAVIOR__META_TYPE:
				return metaType != null;
			case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_DYNAMIC_PROPERTIES:
				return ownedDynamicProperties != null && !ownedDynamicProperties.isEmpty();
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
	{
		if (baseClass == DynamicElement.class)
		{
			switch (derivedFeatureID)
			{
				case PivotPackage.DYNAMIC_BEHAVIOR__META_TYPE: return PivotPackage.DYNAMIC_ELEMENT__META_TYPE;
				default: return -1;
			}
		}
		if (baseClass == DynamicType.class)
		{
			switch (derivedFeatureID)
			{
				case PivotPackage.DYNAMIC_BEHAVIOR__OWNED_DYNAMIC_PROPERTIES: return PivotPackage.DYNAMIC_TYPE__OWNED_DYNAMIC_PROPERTIES;
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
		if (baseClass == DynamicElement.class)
		{
			switch (baseFeatureID)
			{
				case PivotPackage.DYNAMIC_ELEMENT__META_TYPE: return PivotPackage.DYNAMIC_BEHAVIOR__META_TYPE;
				default: return -1;
			}
		}
		if (baseClass == DynamicType.class)
		{
			switch (baseFeatureID)
			{
				case PivotPackage.DYNAMIC_TYPE__OWNED_DYNAMIC_PROPERTIES: return PivotPackage.DYNAMIC_BEHAVIOR__OWNED_DYNAMIC_PROPERTIES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitDynamicBehavior(this);
	}
} //DynamicBehaviorImpl
