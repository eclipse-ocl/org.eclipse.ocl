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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Behavior;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.CompleteEnvironment;
import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.StereotypeExtender;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.complete.CompleteInheritanceImpl;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.Unlimited;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Collection Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CollectionTypeImpl#getElementType <em>Element Type</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CollectionTypeImpl#isIsNullFree <em>Is Null Free</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CollectionTypeImpl#getLower <em>Lower</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CollectionTypeImpl#getUpper <em>Upper</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CollectionTypeImpl
		extends DataTypeImpl
		implements CollectionType {

	/**
	 * The cached value of the '{@link #getElementType() <em>Element Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementType()
	 * @generated
	 * @ordered
	 */
	protected Type elementType;

	/**
	 * The default value of the '{@link #isIsNullFree() <em>Is Null Free</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsNullFree()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_NULL_FREE_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isIsNullFree() <em>Is Null Free</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsNullFree()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_NULL_FREE_EFLAG = 1 << 12;

	/**
	 * The default value of the '{@link #getLower() <em>Lower</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLower()
	 * @generated
	 * @ordered
	 */
	protected static final Number LOWER_EDEFAULT = (Number)PivotFactory.eINSTANCE.createFromString(PivotPackage.eINSTANCE.getInteger(), "0"); //$NON-NLS-1$
	/**
	 * The cached value of the '{@link #getLower() <em>Lower</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLower()
	 * @generated
	 * @ordered
	 */
	protected Number lower = LOWER_EDEFAULT;
	/**
	 * The default value of the '{@link #getUpper() <em>Upper</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpper()
	 * @generated
	 * @ordered
	 */
	protected static final Number UPPER_EDEFAULT = (Number)PivotFactory.eINSTANCE.createFromString(PivotPackage.eINSTANCE.getUnlimitedNatural(), "*"); //$NON-NLS-1$
	/**
	 * The cached value of the '{@link #getUpper() <em>Upper</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpper()
	 * @generated
	 * @ordered
	 */
	protected Number upper = UPPER_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	protected CollectionTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.COLLECTION_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Type getElementType() {
		if (elementType != null && elementType.eIsProxy())
		{
			InternalEObject oldElementType = (InternalEObject)elementType;
			elementType = (Type)eResolveProxy(oldElementType);
			if (elementType != oldElementType)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.COLLECTION_TYPE__ELEMENT_TYPE, oldElementType, elementType));
			}
		}
		return elementType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetElementType() {
		return elementType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setElementType(Type newElementType) {
		Type oldElementType = elementType;
		elementType = newElementType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.COLLECTION_TYPE__ELEMENT_TYPE, oldElementType, elementType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsNullFree()
	{
		return (eFlags & IS_NULL_FREE_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsNullFree(boolean newIsNullFree)
	{
		boolean oldIsNullFree = (eFlags & IS_NULL_FREE_EFLAG) != 0;
		if (newIsNullFree) eFlags |= IS_NULL_FREE_EFLAG; else eFlags &= ~IS_NULL_FREE_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.COLLECTION_TYPE__IS_NULL_FREE, oldIsNullFree, newIsNullFree));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Number getLower()
	{
		return lower;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLower(Number newLower)
	{
		Number oldLower = lower;
		lower = newLower;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.COLLECTION_TYPE__LOWER, oldLower, lower));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Number getUpper()
	{
		return upper;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUpper(Number newUpper)
	{
		Number oldUpper = upper;
		upper = newUpper;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.COLLECTION_TYPE__UPPER, oldUpper, upper));
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
			case PivotPackage.COLLECTION_TYPE__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.COLLECTION_TYPE__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.COLLECTION_TYPE__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.COLLECTION_TYPE__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.COLLECTION_TYPE__NAME:
				return getName();
			case PivotPackage.COLLECTION_TYPE__OWNED_CONSTRAINTS:
				return getOwnedConstraints();
			case PivotPackage.COLLECTION_TYPE__OWNED_BINDINGS:
				return getOwnedBindings();
			case PivotPackage.COLLECTION_TYPE__OWNED_SIGNATURE:
				return getOwnedSignature();
			case PivotPackage.COLLECTION_TYPE__UNSPECIALIZED_ELEMENT:
				return getUnspecializedElement();
			case PivotPackage.COLLECTION_TYPE__EXTENDERS:
				return getExtenders();
			case PivotPackage.COLLECTION_TYPE__INSTANCE_CLASS_NAME:
				return getInstanceClassName();
			case PivotPackage.COLLECTION_TYPE__IS_ABSTRACT:
				return isIsAbstract();
			case PivotPackage.COLLECTION_TYPE__IS_ACTIVE:
				return isIsActive();
			case PivotPackage.COLLECTION_TYPE__IS_INTERFACE:
				return isIsInterface();
			case PivotPackage.COLLECTION_TYPE__OWNED_BEHAVIORS:
				return getOwnedBehaviors();
			case PivotPackage.COLLECTION_TYPE__OWNED_INVARIANTS:
				return getOwnedInvariants();
			case PivotPackage.COLLECTION_TYPE__OWNED_OPERATIONS:
				return getOwnedOperations();
			case PivotPackage.COLLECTION_TYPE__OWNED_PROPERTIES:
				return getOwnedProperties();
			case PivotPackage.COLLECTION_TYPE__OWNING_PACKAGE:
				return getOwningPackage();
			case PivotPackage.COLLECTION_TYPE__SUPER_CLASSES:
				return getSuperClasses();
			case PivotPackage.COLLECTION_TYPE__BEHAVIORAL_CLASS:
				if (resolve) return getBehavioralClass();
				return basicGetBehavioralClass();
			case PivotPackage.COLLECTION_TYPE__IS_SERIALIZABLE:
				return isIsSerializable();
			case PivotPackage.COLLECTION_TYPE__ELEMENT_TYPE:
				if (resolve) return getElementType();
				return basicGetElementType();
			case PivotPackage.COLLECTION_TYPE__IS_NULL_FREE:
				return isIsNullFree();
			case PivotPackage.COLLECTION_TYPE__LOWER:
				return getLower();
			case PivotPackage.COLLECTION_TYPE__UPPER:
				return getUpper();
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
			case PivotPackage.COLLECTION_TYPE__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.COLLECTION_TYPE__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.COLLECTION_TYPE__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.COLLECTION_TYPE__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.COLLECTION_TYPE__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.COLLECTION_TYPE__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				getOwnedConstraints().addAll((Collection<? extends Constraint>)newValue);
				return;
			case PivotPackage.COLLECTION_TYPE__OWNED_BINDINGS:
				getOwnedBindings().clear();
				getOwnedBindings().addAll((Collection<? extends TemplateBinding>)newValue);
				return;
			case PivotPackage.COLLECTION_TYPE__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignature)newValue);
				return;
			case PivotPackage.COLLECTION_TYPE__UNSPECIALIZED_ELEMENT:
				setUnspecializedElement((TemplateableElement)newValue);
				return;
			case PivotPackage.COLLECTION_TYPE__EXTENDERS:
				getExtenders().clear();
				getExtenders().addAll((Collection<? extends StereotypeExtender>)newValue);
				return;
			case PivotPackage.COLLECTION_TYPE__INSTANCE_CLASS_NAME:
				setInstanceClassName((String)newValue);
				return;
			case PivotPackage.COLLECTION_TYPE__IS_ABSTRACT:
				setIsAbstract((Boolean)newValue);
				return;
			case PivotPackage.COLLECTION_TYPE__IS_ACTIVE:
				setIsActive((Boolean)newValue);
				return;
			case PivotPackage.COLLECTION_TYPE__IS_INTERFACE:
				setIsInterface((Boolean)newValue);
				return;
			case PivotPackage.COLLECTION_TYPE__OWNED_BEHAVIORS:
				getOwnedBehaviors().clear();
				getOwnedBehaviors().addAll((Collection<? extends Behavior>)newValue);
				return;
			case PivotPackage.COLLECTION_TYPE__OWNED_INVARIANTS:
				getOwnedInvariants().clear();
				getOwnedInvariants().addAll((Collection<? extends Constraint>)newValue);
				return;
			case PivotPackage.COLLECTION_TYPE__OWNED_OPERATIONS:
				getOwnedOperations().clear();
				getOwnedOperations().addAll((Collection<? extends Operation>)newValue);
				return;
			case PivotPackage.COLLECTION_TYPE__OWNED_PROPERTIES:
				getOwnedProperties().clear();
				getOwnedProperties().addAll((Collection<? extends Property>)newValue);
				return;
			case PivotPackage.COLLECTION_TYPE__OWNING_PACKAGE:
				setOwningPackage((org.eclipse.ocl.pivot.Package)newValue);
				return;
			case PivotPackage.COLLECTION_TYPE__SUPER_CLASSES:
				getSuperClasses().clear();
				getSuperClasses().addAll((Collection<? extends org.eclipse.ocl.pivot.Class>)newValue);
				return;
			case PivotPackage.COLLECTION_TYPE__BEHAVIORAL_CLASS:
				setBehavioralClass((org.eclipse.ocl.pivot.Class)newValue);
				return;
			case PivotPackage.COLLECTION_TYPE__IS_SERIALIZABLE:
				setIsSerializable((Boolean)newValue);
				return;
			case PivotPackage.COLLECTION_TYPE__ELEMENT_TYPE:
				setElementType((Type)newValue);
				return;
			case PivotPackage.COLLECTION_TYPE__IS_NULL_FREE:
				setIsNullFree((Boolean)newValue);
				return;
			case PivotPackage.COLLECTION_TYPE__LOWER:
				setLower((Number)newValue);
				return;
			case PivotPackage.COLLECTION_TYPE__UPPER:
				setUpper((Number)newValue);
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
			case PivotPackage.COLLECTION_TYPE__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.COLLECTION_TYPE__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.COLLECTION_TYPE__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.COLLECTION_TYPE__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.COLLECTION_TYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.COLLECTION_TYPE__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				return;
			case PivotPackage.COLLECTION_TYPE__OWNED_BINDINGS:
				getOwnedBindings().clear();
				return;
			case PivotPackage.COLLECTION_TYPE__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignature)null);
				return;
			case PivotPackage.COLLECTION_TYPE__UNSPECIALIZED_ELEMENT:
				setUnspecializedElement((TemplateableElement)null);
				return;
			case PivotPackage.COLLECTION_TYPE__EXTENDERS:
				getExtenders().clear();
				return;
			case PivotPackage.COLLECTION_TYPE__INSTANCE_CLASS_NAME:
				setInstanceClassName(INSTANCE_CLASS_NAME_EDEFAULT);
				return;
			case PivotPackage.COLLECTION_TYPE__IS_ABSTRACT:
				setIsAbstract(IS_ABSTRACT_EDEFAULT);
				return;
			case PivotPackage.COLLECTION_TYPE__IS_ACTIVE:
				setIsActive(IS_ACTIVE_EDEFAULT);
				return;
			case PivotPackage.COLLECTION_TYPE__IS_INTERFACE:
				setIsInterface(IS_INTERFACE_EDEFAULT);
				return;
			case PivotPackage.COLLECTION_TYPE__OWNED_BEHAVIORS:
				getOwnedBehaviors().clear();
				return;
			case PivotPackage.COLLECTION_TYPE__OWNED_INVARIANTS:
				getOwnedInvariants().clear();
				return;
			case PivotPackage.COLLECTION_TYPE__OWNED_OPERATIONS:
				getOwnedOperations().clear();
				return;
			case PivotPackage.COLLECTION_TYPE__OWNED_PROPERTIES:
				getOwnedProperties().clear();
				return;
			case PivotPackage.COLLECTION_TYPE__OWNING_PACKAGE:
				setOwningPackage((org.eclipse.ocl.pivot.Package)null);
				return;
			case PivotPackage.COLLECTION_TYPE__SUPER_CLASSES:
				getSuperClasses().clear();
				return;
			case PivotPackage.COLLECTION_TYPE__BEHAVIORAL_CLASS:
				setBehavioralClass((org.eclipse.ocl.pivot.Class)null);
				return;
			case PivotPackage.COLLECTION_TYPE__IS_SERIALIZABLE:
				setIsSerializable(IS_SERIALIZABLE_EDEFAULT);
				return;
			case PivotPackage.COLLECTION_TYPE__ELEMENT_TYPE:
				setElementType((Type)null);
				return;
			case PivotPackage.COLLECTION_TYPE__IS_NULL_FREE:
				setIsNullFree(IS_NULL_FREE_EDEFAULT);
				return;
			case PivotPackage.COLLECTION_TYPE__LOWER:
				setLower(LOWER_EDEFAULT);
				return;
			case PivotPackage.COLLECTION_TYPE__UPPER:
				setUpper(UPPER_EDEFAULT);
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
			case PivotPackage.COLLECTION_TYPE__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.COLLECTION_TYPE__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.COLLECTION_TYPE__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.COLLECTION_TYPE__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.COLLECTION_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.COLLECTION_TYPE__OWNED_CONSTRAINTS:
				return ownedConstraints != null && !ownedConstraints.isEmpty();
			case PivotPackage.COLLECTION_TYPE__OWNED_BINDINGS:
				return ownedBindings != null && !ownedBindings.isEmpty();
			case PivotPackage.COLLECTION_TYPE__OWNED_SIGNATURE:
				return ownedSignature != null;
			case PivotPackage.COLLECTION_TYPE__UNSPECIALIZED_ELEMENT:
				return unspecializedElement != null;
			case PivotPackage.COLLECTION_TYPE__EXTENDERS:
				return extenders != null && !extenders.isEmpty();
			case PivotPackage.COLLECTION_TYPE__INSTANCE_CLASS_NAME:
				return INSTANCE_CLASS_NAME_EDEFAULT == null ? instanceClassName != null : !INSTANCE_CLASS_NAME_EDEFAULT.equals(instanceClassName);
			case PivotPackage.COLLECTION_TYPE__IS_ABSTRACT:
				return ((eFlags & IS_ABSTRACT_EFLAG) != 0) != IS_ABSTRACT_EDEFAULT;
			case PivotPackage.COLLECTION_TYPE__IS_ACTIVE:
				return ((eFlags & IS_ACTIVE_EFLAG) != 0) != IS_ACTIVE_EDEFAULT;
			case PivotPackage.COLLECTION_TYPE__IS_INTERFACE:
				return ((eFlags & IS_INTERFACE_EFLAG) != 0) != IS_INTERFACE_EDEFAULT;
			case PivotPackage.COLLECTION_TYPE__OWNED_BEHAVIORS:
				return ownedBehaviors != null && !ownedBehaviors.isEmpty();
			case PivotPackage.COLLECTION_TYPE__OWNED_INVARIANTS:
				return ownedInvariants != null && !ownedInvariants.isEmpty();
			case PivotPackage.COLLECTION_TYPE__OWNED_OPERATIONS:
				return ownedOperations != null && !ownedOperations.isEmpty();
			case PivotPackage.COLLECTION_TYPE__OWNED_PROPERTIES:
				return ownedProperties != null && !ownedProperties.isEmpty();
			case PivotPackage.COLLECTION_TYPE__OWNING_PACKAGE:
				return getOwningPackage() != null;
			case PivotPackage.COLLECTION_TYPE__SUPER_CLASSES:
				return superClasses != null && !superClasses.isEmpty();
			case PivotPackage.COLLECTION_TYPE__BEHAVIORAL_CLASS:
				return behavioralClass != null;
			case PivotPackage.COLLECTION_TYPE__IS_SERIALIZABLE:
				return ((eFlags & IS_SERIALIZABLE_EFLAG) != 0) != IS_SERIALIZABLE_EDEFAULT;
			case PivotPackage.COLLECTION_TYPE__ELEMENT_TYPE:
				return elementType != null;
			case PivotPackage.COLLECTION_TYPE__IS_NULL_FREE:
				return ((eFlags & IS_NULL_FREE_EFLAG) != 0) != IS_NULL_FREE_EDEFAULT;
			case PivotPackage.COLLECTION_TYPE__LOWER:
				return LOWER_EDEFAULT == null ? lower != null : !LOWER_EDEFAULT.equals(lower);
			case PivotPackage.COLLECTION_TYPE__UPPER:
				return UPPER_EDEFAULT == null ? upper != null : !UPPER_EDEFAULT.equals(upper);
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
		return visitor.visitCollectionType(this);
	}

	@Override
	public @NonNull TypeId computeId() {
		if (getUnspecializedElement() == null) {
			if (TypeId.COLLECTION_NAME.equals(name)) {
				return TypeId.COLLECTION;
			}
			else if (TypeId.UNIQUE_COLLECTION_NAME.equals(name)) {
				return TypeId.UNIQUE_COLLECTION;
			}
			else {
				String name2 = name;
				assert name2 != null;
				return IdManager.getCollectionTypeId(name2);		// e.g. UniqueCollection
			}
		}
		else {
			return TypeId.COLLECTION.getSpecializedId(getElementType().getTypeId());
		}
	}
	
	@Override
	public boolean conformsTo(@NonNull StandardLibrary standardLibrary, @NonNull Type type) {
		if (this == type) {
			return true;
		}
		if (type instanceof CollectionType) {
			return TypeUtil.conformsToCollectionType(standardLibrary, this, (CollectionType)type);
		}
		if (getUnspecializedElement() != null) {
			return ((Type)getUnspecializedElement()).conformsTo(standardLibrary, type);
		}
		return super.conformsTo(standardLibrary, type);
	}
	
	@Override
	public Type flattenedType() {
		return getElementType();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getCommonType(@NonNull IdResolver idResolver, @NonNull Type type) {
		CompleteEnvironment environment = idResolver.getEnvironment();
		StandardLibrary standardLibrary = environment.getOwnedStandardLibrary();
		CompleteInheritance thisInheritance = this.getInheritance(standardLibrary);
		CompleteInheritance thatInheritance = type.getInheritance(standardLibrary);
		CompleteInheritance commonInheritance = thisInheritance.getCommonInheritance(thatInheritance);
		org.eclipse.ocl.pivot.Class commonType = commonInheritance.getPivotClass();
		if (type instanceof CollectionType) {
			CollectionType thatCollectionType = (CollectionType)type;
			Type thisElementType = this.getElementType();
			Type thatElementType = ClassUtil.nonNullEMF(thatCollectionType.getElementType());
			boolean commonIsNullFree = this.isIsNullFree() && thatCollectionType.isIsNullFree();
			Type commonElementType = thisElementType.getCommonType(idResolver, thatElementType);
			if ((commonInheritance instanceof CompleteInheritanceImpl) && !((CompleteInheritanceImpl)commonInheritance).isIsAbstract()) {
				CollectionType commonCollectionType = (CollectionType)commonType;
				return environment.getCollectionType(commonCollectionType, commonElementType, commonIsNullFree, null, null);
			}
			else {
				if (isOrdered() && thatCollectionType.isOrdered()) {
					if (isUnique() && thatCollectionType.isUnique()) {
						return environment.getOrderedSetType(commonElementType, commonIsNullFree, null, null);
					}
					else {
						return environment.getSequenceType(commonElementType, commonIsNullFree, null, null);
					}
				}
				else {
					if (isUnique() && thatCollectionType.isUnique()) {
						return environment.getSetType(commonElementType, commonIsNullFree, null, null);
					}
					else {
						return environment.getBagType(commonElementType, commonIsNullFree, null, null);
					}
				}
			}
		}
		else {
			return commonType;
		}
	}

	@Override
	public @NonNull CollectionType getContainerType() {
		TemplateableElement unspecializedElement2 = unspecializedElement;
		return unspecializedElement2 != null ? (CollectionType)unspecializedElement2 : this;
	}
	
	@Override
	public @NonNull CollectionTypeId getTypeId() {
		return (CollectionTypeId) super.getTypeId();
	}

	@Override
	public boolean isEqualTo(@NonNull StandardLibrary standardLibrary, @NonNull Type type) {
		if (this == type) {
			return true;
		}
		if (!(type instanceof CollectionType)) {
			return false;
		}
		return TypeUtil.isEqualToCollectionType(standardLibrary, this, (CollectionType)type);
	}

	@Override
	public @NonNull IntegerValue getLowerValue() {
		Number lower2 = lower;
		assert lower2 != null;
		return ValueUtil.integerValueOf(lower2);
	}

	@Override
	public @NonNull UnlimitedNaturalValue getUpperValue() {
		Number upper2 = upper;
		assert upper2 != null;
		return ValueUtil.unlimitedNaturalValueOf(upper2);
	}

	@Override
	public void setLowerValue(@NonNull IntegerValue lower) {
		setLower(lower.intValue());
	}

	@Override
	public void setUpperValue(@NonNull UnlimitedNaturalValue upper) {
		setUpper(upper.isUnlimited() ? Unlimited.INSTANCE : upper.intValue());
	}
} //CollectionTypeImpl
