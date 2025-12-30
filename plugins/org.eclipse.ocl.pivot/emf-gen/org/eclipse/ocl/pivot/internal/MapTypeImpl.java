/**
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Behavior;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StereotypeExtender;
import org.eclipse.ocl.pivot.TemplateArgument;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.WildcardType;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Map Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.MapTypeImpl#getEntryClass <em>Entry Class</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.MapTypeImpl#getKeyType <em>Key Type</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.MapTypeImpl#isKeysAreNullFree <em>Keys Are Null Free</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.MapTypeImpl#getValueType <em>Value Type</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.MapTypeImpl#isValuesAreNullFree <em>Values Are Null Free</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MapTypeImpl extends IterableTypeImpl implements MapType
{
	/**
	 * The number of structural features of the '<em>Map Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int MAP_TYPE_FEATURE_COUNT = IterableTypeImpl.ITERABLE_TYPE_FEATURE_COUNT + 5;
	/**
	 * The number of operations of the '<em>Map Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int MAP_TYPE_OPERATION_COUNT = IterableTypeImpl.ITERABLE_TYPE_OPERATION_COUNT + 0;
	/**
	 * The cached value of the '{@link #getEntryClass() <em>Entry Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * @since 1.7
	 * <!-- end-user-doc -->
	 * @see #getEntryClass()
	 * @generated
	 * @ordered
	 */
	protected org.eclipse.ocl.pivot.Class entryClass;
	/**
	 * The default value of the '{@link #isKeysAreNullFree() <em>Keys Are Null Free</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 1.6
	 * <!-- end-user-doc -->
	 * @see #isKeysAreNullFree()
	 * @generated
	 * @ordered
	 */
	protected static final boolean KEYS_ARE_NULL_FREE_EDEFAULT = true;
	/**
	 * The flag representing the value of the '{@link #isKeysAreNullFree() <em>Keys Are Null Free</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 1.6
	 * <!-- end-user-doc -->
	 * @see #isKeysAreNullFree()
	 * @generated
	 * @ordered
	 */
	protected static final int KEYS_ARE_NULL_FREE_EFLAG = 1 << 12;
	/**
	 * The default value of the '{@link #isValuesAreNullFree() <em>Values Are Null Free</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 1.6
	 * <!-- end-user-doc -->
	 * @see #isValuesAreNullFree()
	 * @generated
	 * @ordered
	 */
	protected static final boolean VALUES_ARE_NULL_FREE_EDEFAULT = true;
	/**
	 * The flag representing the value of the '{@link #isValuesAreNullFree() <em>Values Are Null Free</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 1.6
	 * <!-- end-user-doc -->
	 * @see #isValuesAreNullFree()
	 * @generated
	 * @ordered
	 */
	protected static final int VALUES_ARE_NULL_FREE_EFLAG = 1 << 13;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MapTypeImpl()
	{
		super();
		eFlags |= KEYS_ARE_NULL_FREE_EFLAG;
		eFlags |= VALUES_ARE_NULL_FREE_EFLAG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PivotPackage.Literals.MAP_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.eclipse.ocl.pivot.Class getEntryClass()
	{
		if (entryClass != null && entryClass.eIsProxy())
		{
			InternalEObject oldEntryClass = (InternalEObject)entryClass;
			entryClass = (org.eclipse.ocl.pivot.Class)eResolveProxy(oldEntryClass);
			if (entryClass != oldEntryClass)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 24, oldEntryClass, entryClass));
			}
		}
		return entryClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.ocl.pivot.Class basicGetEntryClass()
	{
		return entryClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEntryClass(org.eclipse.ocl.pivot.Class newEntryClass)
	{
		org.eclipse.ocl.pivot.Class oldEntryClass = entryClass;
		entryClass = newEntryClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 24, oldEntryClass, entryClass));
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
				if (resolve) return getBehavioralClass();
				return basicGetBehavioralClass();
			case 22:
				return isIsSerializable();
			case 23:
				return getValue();
			case 24:
				if (resolve) return getEntryClass();
				return basicGetEntryClass();
			case 25:
				return getKeyType();
			case 26:
				return isKeysAreNullFree();
			case 27:
				return getValueType();
			case 28:
				return isValuesAreNullFree();
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
				setBehavioralClass((org.eclipse.ocl.pivot.Class)newValue);
				return;
			case 22:
				setIsSerializable((Boolean)newValue);
				return;
			case 24:
				setEntryClass((org.eclipse.ocl.pivot.Class)newValue);
				return;
			case 25:
				setKeyType((Type)newValue);
				return;
			case 26:
				setKeysAreNullFree((Boolean)newValue);
				return;
			case 27:
				setValueType((Type)newValue);
				return;
			case 28:
				setValuesAreNullFree((Boolean)newValue);
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
				setBehavioralClass((org.eclipse.ocl.pivot.Class)null);
				return;
			case 22:
				setIsSerializable(IS_SERIALIZABLE_EDEFAULT);
				return;
			case 24:
				setEntryClass((org.eclipse.ocl.pivot.Class)null);
				return;
			case 25:
				setKeyType((Type)null);
				return;
			case 26:
				setKeysAreNullFree(KEYS_ARE_NULL_FREE_EDEFAULT);
				return;
			case 27:
				setValueType((Type)null);
				return;
			case 28:
				setValuesAreNullFree(VALUES_ARE_NULL_FREE_EDEFAULT);
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
				return behavioralClass != null;
			case 22:
				return ((eFlags & IS_SERIALIZABLE_EFLAG) != 0) != IS_SERIALIZABLE_EDEFAULT;
			case 23:
				return VALUE_EDEFAULT == null ? getValue() != null : !VALUE_EDEFAULT.equals(getValue());
			case 24:
				return entryClass != null;
			case 25:
				return getKeyType() != null;
			case 26:
				return ((eFlags & KEYS_ARE_NULL_FREE_EFLAG) != 0) != KEYS_ARE_NULL_FREE_EDEFAULT;
			case 27:
				return getValueType() != null;
			case 28:
				return ((eFlags & VALUES_ARE_NULL_FREE_EFLAG) != 0) != VALUES_ARE_NULL_FREE_EDEFAULT;
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

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitMapType(this);
	}

	@Override
	public @NonNull TypeId computeId() {
		if (getGeneric() == null) {
			return TypeId.MAP;
		}
		else {
			TypeId keyTypeId = getKeyType().getTypeId();
			TypeId valueTypeId = getValueType().getTypeId();
			if (entryClass != null){
				TypeId entryTypeId = entryClass.getTypeId();
				return TypeId.MAP.getSpecializedId(entryTypeId, keyTypeId, valueTypeId, isKeysAreNullFree(), isValuesAreNullFree());
			}
			else {
				return TypeId.MAP.getSpecializedId(keyTypeId, valueTypeId, isKeysAreNullFree(), isValuesAreNullFree());
			}
		}
	}

	@Override
	public Type getKeyType() {
		if (ownedTemplateParameters != null) {
			return ownedTemplateParameters.get(0);
		}
		else {
			return ownedTemplateArguments.get(0).getActual();
		}
	}

	@Override
	public Type getValueType() {
		if (ownedTemplateParameters != null) {
			return ownedTemplateParameters.get(1);
		}
		else {
			return ownedTemplateArguments.get(1).getActual();
		}
	}

	@Override
	public void setKeyType(Type newKeyType) {				// FIXME delete me once compatibility not needed
		System.err.println(eClass().getName() + ".setKeyType() is ignored");
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isKeysAreNullFree()
	{
		return (eFlags & KEYS_ARE_NULL_FREE_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setKeysAreNullFree(boolean newKeysAreNullFree)
	{
		boolean oldKeysAreNullFree = (eFlags & KEYS_ARE_NULL_FREE_EFLAG) != 0;
		if (newKeysAreNullFree) eFlags |= KEYS_ARE_NULL_FREE_EFLAG; else eFlags &= ~KEYS_ARE_NULL_FREE_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 26, oldKeysAreNullFree, newKeysAreNullFree));
	}

	@Override
	public void setValueType(Type newValueType) {			// FIXME delete me once compatibility not needed
		System.err.println(eClass().getName() + ".setValueType() is ignored");
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isValuesAreNullFree()
	{
		return (eFlags & VALUES_ARE_NULL_FREE_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setValuesAreNullFree(boolean newValuesAreNullFree)
	{
		boolean oldValuesAreNullFree = (eFlags & VALUES_ARE_NULL_FREE_EFLAG) != 0;
		if (newValuesAreNullFree) eFlags |= VALUES_ARE_NULL_FREE_EFLAG; else eFlags &= ~VALUES_ARE_NULL_FREE_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 28, oldValuesAreNullFree, newValuesAreNullFree));
	}

	@Override
	public @NonNull MapType getContainerType() {
		return generic != null ? (MapType)generic : this;
	}
} //MapTypeImpl
