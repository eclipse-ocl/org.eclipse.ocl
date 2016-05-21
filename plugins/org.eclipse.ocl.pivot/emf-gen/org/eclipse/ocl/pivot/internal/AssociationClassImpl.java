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
package org.eclipse.ocl.pivot.internal;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.AssociationClass;
import org.eclipse.ocl.pivot.Behavior;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StereotypeExtender;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Association Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.AssociationClassImpl#getUnownedAttributes <em>Unowned Attributes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AssociationClassImpl
		extends ClassImpl
		implements AssociationClass {

	/**
	 * The cached value of the '{@link #getUnownedAttributes() <em>Unowned Attributes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnownedAttributes()
	 * @generated
	 * @ordered
	 */
	protected EList<Property> unownedAttributes;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssociationClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.ASSOCIATION_CLASS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Property> getUnownedAttributes()
	{
		if (unownedAttributes == null)
		{
			unownedAttributes = new EObjectWithInverseResolvingEList<Property>(Property.class, this, PivotPackage.ASSOCIATION_CLASS__UNOWNED_ATTRIBUTES, PivotPackage.PROPERTY__ASSOCIATION_CLASS);
		}
		return unownedAttributes;
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
			case PivotPackage.ASSOCIATION_CLASS__ANNOTATING_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.ASSOCIATION_CLASS__OWNED_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.ASSOCIATION_CLASS__OWNED_EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case PivotPackage.ASSOCIATION_CLASS__OWNED_BINDINGS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedBindings()).basicAdd(otherEnd, msgs);
			case PivotPackage.ASSOCIATION_CLASS__OWNED_SIGNATURE:
				if (ownedSignature != null)
					msgs = ((InternalEObject)ownedSignature).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.ASSOCIATION_CLASS__OWNED_SIGNATURE, null, msgs);
				return basicSetOwnedSignature((TemplateSignature)otherEnd, msgs);
			case PivotPackage.ASSOCIATION_CLASS__EXTENDERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExtenders()).basicAdd(otherEnd, msgs);
			case PivotPackage.ASSOCIATION_CLASS__OWNED_OPERATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedOperations()).basicAdd(otherEnd, msgs);
			case PivotPackage.ASSOCIATION_CLASS__OWNED_PROPERTIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedProperties()).basicAdd(otherEnd, msgs);
			case PivotPackage.ASSOCIATION_CLASS__OWNING_PACKAGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningPackage((org.eclipse.ocl.pivot.Package)otherEnd, msgs);
			case PivotPackage.ASSOCIATION_CLASS__UNOWNED_ATTRIBUTES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getUnownedAttributes()).basicAdd(otherEnd, msgs);
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
			case PivotPackage.ASSOCIATION_CLASS__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.ASSOCIATION_CLASS__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.ASSOCIATION_CLASS__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.ASSOCIATION_CLASS__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.ASSOCIATION_CLASS__OWNED_CONSTRAINTS:
				return ((InternalEList<?>)getOwnedConstraints()).basicRemove(otherEnd, msgs);
			case PivotPackage.ASSOCIATION_CLASS__OWNED_BINDINGS:
				return ((InternalEList<?>)getOwnedBindings()).basicRemove(otherEnd, msgs);
			case PivotPackage.ASSOCIATION_CLASS__OWNED_SIGNATURE:
				return basicSetOwnedSignature(null, msgs);
			case PivotPackage.ASSOCIATION_CLASS__EXTENDERS:
				return ((InternalEList<?>)getExtenders()).basicRemove(otherEnd, msgs);
			case PivotPackage.ASSOCIATION_CLASS__OWNED_BEHAVIORS:
				return ((InternalEList<?>)getOwnedBehaviors()).basicRemove(otherEnd, msgs);
			case PivotPackage.ASSOCIATION_CLASS__OWNED_INVARIANTS:
				return ((InternalEList<?>)getOwnedInvariants()).basicRemove(otherEnd, msgs);
			case PivotPackage.ASSOCIATION_CLASS__OWNED_OPERATIONS:
				return ((InternalEList<?>)getOwnedOperations()).basicRemove(otherEnd, msgs);
			case PivotPackage.ASSOCIATION_CLASS__OWNED_PROPERTIES:
				return ((InternalEList<?>)getOwnedProperties()).basicRemove(otherEnd, msgs);
			case PivotPackage.ASSOCIATION_CLASS__OWNING_PACKAGE:
				return basicSetOwningPackage(null, msgs);
			case PivotPackage.ASSOCIATION_CLASS__UNOWNED_ATTRIBUTES:
				return ((InternalEList<?>)getUnownedAttributes()).basicRemove(otherEnd, msgs);
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
			case PivotPackage.ASSOCIATION_CLASS__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.ASSOCIATION_CLASS__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.ASSOCIATION_CLASS__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.ASSOCIATION_CLASS__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.ASSOCIATION_CLASS__NAME:
				return getName();
			case PivotPackage.ASSOCIATION_CLASS__OWNED_CONSTRAINTS:
				return getOwnedConstraints();
			case PivotPackage.ASSOCIATION_CLASS__OWNED_BINDINGS:
				return getOwnedBindings();
			case PivotPackage.ASSOCIATION_CLASS__OWNED_SIGNATURE:
				return getOwnedSignature();
			case PivotPackage.ASSOCIATION_CLASS__UNSPECIALIZED_ELEMENT:
				return getUnspecializedElement();
			case PivotPackage.ASSOCIATION_CLASS__EXTENDERS:
				return getExtenders();
			case PivotPackage.ASSOCIATION_CLASS__INSTANCE_CLASS_NAME:
				return getInstanceClassName();
			case PivotPackage.ASSOCIATION_CLASS__IS_ABSTRACT:
				return isIsAbstract();
			case PivotPackage.ASSOCIATION_CLASS__IS_ACTIVE:
				return isIsActive();
			case PivotPackage.ASSOCIATION_CLASS__IS_INTERFACE:
				return isIsInterface();
			case PivotPackage.ASSOCIATION_CLASS__OWNED_BEHAVIORS:
				return getOwnedBehaviors();
			case PivotPackage.ASSOCIATION_CLASS__OWNED_INVARIANTS:
				return getOwnedInvariants();
			case PivotPackage.ASSOCIATION_CLASS__OWNED_OPERATIONS:
				return getOwnedOperations();
			case PivotPackage.ASSOCIATION_CLASS__OWNED_PROPERTIES:
				return getOwnedProperties();
			case PivotPackage.ASSOCIATION_CLASS__OWNING_PACKAGE:
				return getOwningPackage();
			case PivotPackage.ASSOCIATION_CLASS__SUPER_CLASSES:
				return getSuperClasses();
			case PivotPackage.ASSOCIATION_CLASS__UNOWNED_ATTRIBUTES:
				return getUnownedAttributes();
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
			case PivotPackage.ASSOCIATION_CLASS__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.ASSOCIATION_CLASS__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.ASSOCIATION_CLASS__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.ASSOCIATION_CLASS__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.ASSOCIATION_CLASS__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.ASSOCIATION_CLASS__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				getOwnedConstraints().addAll((Collection<? extends Constraint>)newValue);
				return;
			case PivotPackage.ASSOCIATION_CLASS__OWNED_BINDINGS:
				getOwnedBindings().clear();
				getOwnedBindings().addAll((Collection<? extends TemplateBinding>)newValue);
				return;
			case PivotPackage.ASSOCIATION_CLASS__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignature)newValue);
				return;
			case PivotPackage.ASSOCIATION_CLASS__UNSPECIALIZED_ELEMENT:
				setUnspecializedElement((TemplateableElement)newValue);
				return;
			case PivotPackage.ASSOCIATION_CLASS__EXTENDERS:
				getExtenders().clear();
				getExtenders().addAll((Collection<? extends StereotypeExtender>)newValue);
				return;
			case PivotPackage.ASSOCIATION_CLASS__INSTANCE_CLASS_NAME:
				setInstanceClassName((String)newValue);
				return;
			case PivotPackage.ASSOCIATION_CLASS__IS_ABSTRACT:
				setIsAbstract((Boolean)newValue);
				return;
			case PivotPackage.ASSOCIATION_CLASS__IS_ACTIVE:
				setIsActive((Boolean)newValue);
				return;
			case PivotPackage.ASSOCIATION_CLASS__IS_INTERFACE:
				setIsInterface((Boolean)newValue);
				return;
			case PivotPackage.ASSOCIATION_CLASS__OWNED_BEHAVIORS:
				getOwnedBehaviors().clear();
				getOwnedBehaviors().addAll((Collection<? extends Behavior>)newValue);
				return;
			case PivotPackage.ASSOCIATION_CLASS__OWNED_INVARIANTS:
				getOwnedInvariants().clear();
				getOwnedInvariants().addAll((Collection<? extends Constraint>)newValue);
				return;
			case PivotPackage.ASSOCIATION_CLASS__OWNED_OPERATIONS:
				getOwnedOperations().clear();
				getOwnedOperations().addAll((Collection<? extends Operation>)newValue);
				return;
			case PivotPackage.ASSOCIATION_CLASS__OWNED_PROPERTIES:
				getOwnedProperties().clear();
				getOwnedProperties().addAll((Collection<? extends Property>)newValue);
				return;
			case PivotPackage.ASSOCIATION_CLASS__OWNING_PACKAGE:
				setOwningPackage((org.eclipse.ocl.pivot.Package)newValue);
				return;
			case PivotPackage.ASSOCIATION_CLASS__SUPER_CLASSES:
				getSuperClasses().clear();
				getSuperClasses().addAll((Collection<? extends org.eclipse.ocl.pivot.Class>)newValue);
				return;
			case PivotPackage.ASSOCIATION_CLASS__UNOWNED_ATTRIBUTES:
				getUnownedAttributes().clear();
				getUnownedAttributes().addAll((Collection<? extends Property>)newValue);
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
			case PivotPackage.ASSOCIATION_CLASS__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.ASSOCIATION_CLASS__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.ASSOCIATION_CLASS__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.ASSOCIATION_CLASS__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.ASSOCIATION_CLASS__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.ASSOCIATION_CLASS__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				return;
			case PivotPackage.ASSOCIATION_CLASS__OWNED_BINDINGS:
				getOwnedBindings().clear();
				return;
			case PivotPackage.ASSOCIATION_CLASS__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignature)null);
				return;
			case PivotPackage.ASSOCIATION_CLASS__UNSPECIALIZED_ELEMENT:
				setUnspecializedElement((TemplateableElement)null);
				return;
			case PivotPackage.ASSOCIATION_CLASS__EXTENDERS:
				getExtenders().clear();
				return;
			case PivotPackage.ASSOCIATION_CLASS__INSTANCE_CLASS_NAME:
				setInstanceClassName(INSTANCE_CLASS_NAME_EDEFAULT);
				return;
			case PivotPackage.ASSOCIATION_CLASS__IS_ABSTRACT:
				setIsAbstract(IS_ABSTRACT_EDEFAULT);
				return;
			case PivotPackage.ASSOCIATION_CLASS__IS_ACTIVE:
				setIsActive(IS_ACTIVE_EDEFAULT);
				return;
			case PivotPackage.ASSOCIATION_CLASS__IS_INTERFACE:
				setIsInterface(IS_INTERFACE_EDEFAULT);
				return;
			case PivotPackage.ASSOCIATION_CLASS__OWNED_BEHAVIORS:
				getOwnedBehaviors().clear();
				return;
			case PivotPackage.ASSOCIATION_CLASS__OWNED_INVARIANTS:
				getOwnedInvariants().clear();
				return;
			case PivotPackage.ASSOCIATION_CLASS__OWNED_OPERATIONS:
				getOwnedOperations().clear();
				return;
			case PivotPackage.ASSOCIATION_CLASS__OWNED_PROPERTIES:
				getOwnedProperties().clear();
				return;
			case PivotPackage.ASSOCIATION_CLASS__OWNING_PACKAGE:
				setOwningPackage((org.eclipse.ocl.pivot.Package)null);
				return;
			case PivotPackage.ASSOCIATION_CLASS__SUPER_CLASSES:
				getSuperClasses().clear();
				return;
			case PivotPackage.ASSOCIATION_CLASS__UNOWNED_ATTRIBUTES:
				getUnownedAttributes().clear();
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
			case PivotPackage.ASSOCIATION_CLASS__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.ASSOCIATION_CLASS__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.ASSOCIATION_CLASS__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.ASSOCIATION_CLASS__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.ASSOCIATION_CLASS__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.ASSOCIATION_CLASS__OWNED_CONSTRAINTS:
				return ownedConstraints != null && !ownedConstraints.isEmpty();
			case PivotPackage.ASSOCIATION_CLASS__OWNED_BINDINGS:
				return ownedBindings != null && !ownedBindings.isEmpty();
			case PivotPackage.ASSOCIATION_CLASS__OWNED_SIGNATURE:
				return ownedSignature != null;
			case PivotPackage.ASSOCIATION_CLASS__UNSPECIALIZED_ELEMENT:
				return unspecializedElement != null;
			case PivotPackage.ASSOCIATION_CLASS__EXTENDERS:
				return extenders != null && !extenders.isEmpty();
			case PivotPackage.ASSOCIATION_CLASS__INSTANCE_CLASS_NAME:
				return INSTANCE_CLASS_NAME_EDEFAULT == null ? instanceClassName != null : !INSTANCE_CLASS_NAME_EDEFAULT.equals(instanceClassName);
			case PivotPackage.ASSOCIATION_CLASS__IS_ABSTRACT:
				return ((eFlags & IS_ABSTRACT_EFLAG) != 0) != IS_ABSTRACT_EDEFAULT;
			case PivotPackage.ASSOCIATION_CLASS__IS_ACTIVE:
				return ((eFlags & IS_ACTIVE_EFLAG) != 0) != IS_ACTIVE_EDEFAULT;
			case PivotPackage.ASSOCIATION_CLASS__IS_INTERFACE:
				return ((eFlags & IS_INTERFACE_EFLAG) != 0) != IS_INTERFACE_EDEFAULT;
			case PivotPackage.ASSOCIATION_CLASS__OWNED_BEHAVIORS:
				return ownedBehaviors != null && !ownedBehaviors.isEmpty();
			case PivotPackage.ASSOCIATION_CLASS__OWNED_INVARIANTS:
				return ownedInvariants != null && !ownedInvariants.isEmpty();
			case PivotPackage.ASSOCIATION_CLASS__OWNED_OPERATIONS:
				return ownedOperations != null && !ownedOperations.isEmpty();
			case PivotPackage.ASSOCIATION_CLASS__OWNED_PROPERTIES:
				return ownedProperties != null && !ownedProperties.isEmpty();
			case PivotPackage.ASSOCIATION_CLASS__OWNING_PACKAGE:
				return getOwningPackage() != null;
			case PivotPackage.ASSOCIATION_CLASS__SUPER_CLASSES:
				return superClasses != null && !superClasses.isEmpty();
			case PivotPackage.ASSOCIATION_CLASS__UNOWNED_ATTRIBUTES:
				return unownedAttributes != null && !unownedAttributes.isEmpty();
		}
		return eDynamicIsSet(featureID);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitAssociationClass(this);
	}
} //AssociationClassImpl
