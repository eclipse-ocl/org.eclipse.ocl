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
package org.eclipse.ocl.ocl.internal;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.ocl.ocl.Behavior;
import org.eclipse.ocl.ocl.Comment;
import org.eclipse.ocl.ocl.Constraint;
import org.eclipse.ocl.ocl.DataType;
import org.eclipse.ocl.ocl.Element;
import org.eclipse.ocl.ocl.ElementExtension;
import org.eclipse.ocl.ocl.OCLASPackage;
import org.eclipse.ocl.ocl.OclElement;
import org.eclipse.ocl.ocl.Operation;
import org.eclipse.ocl.ocl.Property;
import org.eclipse.ocl.ocl.StereotypeExtender;
import org.eclipse.ocl.ocl.TemplateBinding;
import org.eclipse.ocl.ocl.TemplateSignature;
import org.eclipse.ocl.ocl.TemplateableElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.internal.DataTypeImpl#getBehavioralClass <em>Behavioral Class</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.DataTypeImpl#getIsSerializable <em>Is Serializable</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.DataTypeImpl#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DataTypeImpl extends ClassImpl implements DataType
{
	/**
	 * The number of structural features of the '<em>Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DATA_TYPE_FEATURE_COUNT = ClassImpl.CLASS_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DATA_TYPE_OPERATION_COUNT = ClassImpl.CLASS_OPERATION_COUNT + 0;


	/**
	 * The cached value of the '{@link #getBehavioralClass() <em>Behavioral Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBehavioralClass()
	 * @generated
	 * @ordered
	 */
	protected org.eclipse.ocl.ocl.Class behavioralClass;

	/**
	 * The default value of the '{@link #getIsSerializable() <em>Is Serializable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsSerializable()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_SERIALIZABLE_EDEFAULT = Boolean.TRUE;

	/**
	 * The cached value of the '{@link #getIsSerializable() <em>Is Serializable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsSerializable()
	 * @generated
	 * @ordered
	 */
	protected Boolean isSerializable = IS_SERIALIZABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = ""; //$NON-NLS-1$

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataTypeImpl()
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
		return OCLASPackage.Literals.DATA_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.eclipse.ocl.ocl.Class getBehavioralClass()
	{
		if (behavioralClass != null && behavioralClass.eIsProxy())
		{
			InternalEObject oldBehavioralClass = (InternalEObject)behavioralClass;
			behavioralClass = (org.eclipse.ocl.ocl.Class)eResolveProxy(oldBehavioralClass);
			if (behavioralClass != oldBehavioralClass)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 22, oldBehavioralClass, behavioralClass));
			}
		}
		return behavioralClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.ocl.ocl.Class basicGetBehavioralClass()
	{
		return behavioralClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBehavioralClass(org.eclipse.ocl.ocl.Class newBehavioralClass)
	{
		org.eclipse.ocl.ocl.Class oldBehavioralClass = behavioralClass;
		behavioralClass = newBehavioralClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 22, oldBehavioralClass, behavioralClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getIsSerializable()
	{
		return isSerializable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsSerializable(Boolean newIsSerializable)
	{
		Boolean oldIsSerializable = isSerializable;
		isSerializable = newIsSerializable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 23, oldIsSerializable, isSerializable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getValue()
	{
		// TODO: implement this method to return the 'Value' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
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
				if (resolve) return getOclContainer();
				return basicGetOclContainer();
			case 1:
				return getOclContents();
			case 2:
				return getAnnotatingComments();
			case 3:
				return getOwnedAnnotations();
			case 4:
				return getOwnedComments();
			case 5:
				return getOwnedExtensions();
			case 6:
				return getName();
			case 7:
				return getOwnedConstraints();
			case 8:
				return getOwnedBindings();
			case 9:
				return getOwnedSignature();
			case 10:
				return getUnspecializedElement();
			case 11:
				return getExtenders();
			case 12:
				return getInstanceClassName();
			case 13:
				return getIsAbstract();
			case 14:
				return getIsActive();
			case 15:
				return getIsInterface();
			case 16:
				return getOwnedBehaviors();
			case 17:
				return getOwnedInvariants();
			case 18:
				return getOwnedOperations();
			case 19:
				return getOwnedProperties();
			case 20:
				return getOwningPackage();
			case 21:
				return getSuperClasses();
			case 22:
				if (resolve) return getBehavioralClass();
				return basicGetBehavioralClass();
			case 23:
				return getIsSerializable();
			case 24:
				return getValue();
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
				setOclContainer((OclElement)newValue);
				return;
			case 1:
				getOclContents().clear();
				getOclContents().addAll((Collection<? extends OclElement>)newValue);
				return;
			case 2:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 3:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case 4:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 5:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case 6:
				setName((String)newValue);
				return;
			case 7:
				getOwnedConstraints().clear();
				getOwnedConstraints().addAll((Collection<? extends Constraint>)newValue);
				return;
			case 8:
				getOwnedBindings().clear();
				getOwnedBindings().addAll((Collection<? extends TemplateBinding>)newValue);
				return;
			case 9:
				setOwnedSignature((TemplateSignature)newValue);
				return;
			case 10:
				setUnspecializedElement((TemplateableElement)newValue);
				return;
			case 11:
				getExtenders().clear();
				getExtenders().addAll((Collection<? extends StereotypeExtender>)newValue);
				return;
			case 12:
				setInstanceClassName((String)newValue);
				return;
			case 13:
				setIsAbstract((Boolean)newValue);
				return;
			case 14:
				setIsActive((Boolean)newValue);
				return;
			case 15:
				setIsInterface((Boolean)newValue);
				return;
			case 16:
				getOwnedBehaviors().clear();
				getOwnedBehaviors().addAll((Collection<? extends Behavior>)newValue);
				return;
			case 17:
				getOwnedInvariants().clear();
				getOwnedInvariants().addAll((Collection<? extends Constraint>)newValue);
				return;
			case 18:
				getOwnedOperations().clear();
				getOwnedOperations().addAll((Collection<? extends Operation>)newValue);
				return;
			case 19:
				getOwnedProperties().clear();
				getOwnedProperties().addAll((Collection<? extends Property>)newValue);
				return;
			case 20:
				setOwningPackage((org.eclipse.ocl.ocl.Package)newValue);
				return;
			case 21:
				getSuperClasses().clear();
				getSuperClasses().addAll((Collection<? extends org.eclipse.ocl.ocl.Class>)newValue);
				return;
			case 22:
				setBehavioralClass((org.eclipse.ocl.ocl.Class)newValue);
				return;
			case 23:
				setIsSerializable((Boolean)newValue);
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
				setOclContainer((OclElement)null);
				return;
			case 1:
				getOclContents().clear();
				return;
			case 2:
				getAnnotatingComments().clear();
				return;
			case 3:
				getOwnedAnnotations().clear();
				return;
			case 4:
				getOwnedComments().clear();
				return;
			case 5:
				getOwnedExtensions().clear();
				return;
			case 6:
				setName(NAME_EDEFAULT);
				return;
			case 7:
				getOwnedConstraints().clear();
				return;
			case 8:
				getOwnedBindings().clear();
				return;
			case 9:
				setOwnedSignature((TemplateSignature)null);
				return;
			case 10:
				setUnspecializedElement((TemplateableElement)null);
				return;
			case 11:
				getExtenders().clear();
				return;
			case 12:
				setInstanceClassName(INSTANCE_CLASS_NAME_EDEFAULT);
				return;
			case 13:
				setIsAbstract(IS_ABSTRACT_EDEFAULT);
				return;
			case 14:
				setIsActive(IS_ACTIVE_EDEFAULT);
				return;
			case 15:
				setIsInterface(IS_INTERFACE_EDEFAULT);
				return;
			case 16:
				getOwnedBehaviors().clear();
				return;
			case 17:
				getOwnedInvariants().clear();
				return;
			case 18:
				getOwnedOperations().clear();
				return;
			case 19:
				getOwnedProperties().clear();
				return;
			case 20:
				setOwningPackage((org.eclipse.ocl.ocl.Package)null);
				return;
			case 21:
				getSuperClasses().clear();
				return;
			case 22:
				setBehavioralClass((org.eclipse.ocl.ocl.Class)null);
				return;
			case 23:
				setIsSerializable(IS_SERIALIZABLE_EDEFAULT);
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
				return oclContainer != null;
			case 1:
				return oclContents != null && !oclContents.isEmpty();
			case 2:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case 3:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case 4:
				return ownedComments != null && !ownedComments.isEmpty();
			case 5:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case 6:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case 7:
				return ownedConstraints != null && !ownedConstraints.isEmpty();
			case 8:
				return ownedBindings != null && !ownedBindings.isEmpty();
			case 9:
				return ownedSignature != null;
			case 10:
				return unspecializedElement != null;
			case 11:
				return extenders != null && !extenders.isEmpty();
			case 12:
				return INSTANCE_CLASS_NAME_EDEFAULT == null ? instanceClassName != null : !INSTANCE_CLASS_NAME_EDEFAULT.equals(instanceClassName);
			case 13:
				return IS_ABSTRACT_EDEFAULT == null ? isAbstract != null : !IS_ABSTRACT_EDEFAULT.equals(isAbstract);
			case 14:
				return IS_ACTIVE_EDEFAULT == null ? isActive != null : !IS_ACTIVE_EDEFAULT.equals(isActive);
			case 15:
				return IS_INTERFACE_EDEFAULT == null ? isInterface != null : !IS_INTERFACE_EDEFAULT.equals(isInterface);
			case 16:
				return ownedBehaviors != null && !ownedBehaviors.isEmpty();
			case 17:
				return ownedInvariants != null && !ownedInvariants.isEmpty();
			case 18:
				return ownedOperations != null && !ownedOperations.isEmpty();
			case 19:
				return ownedProperties != null && !ownedProperties.isEmpty();
			case 20:
				return getOwningPackage() != null;
			case 21:
				return superClasses != null && !superClasses.isEmpty();
			case 22:
				return behavioralClass != null;
			case 23:
				return IS_SERIALIZABLE_EDEFAULT == null ? isSerializable != null : !IS_SERIALIZABLE_EDEFAULT.equals(isSerializable);
			case 24:
				return VALUE_EDEFAULT == null ? getValue() != null : !VALUE_EDEFAULT.equals(getValue());
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString()
	{
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (isSerializable: "); //$NON-NLS-1$
		result.append(isSerializable);
		result.append(')');
		return result.toString();
	}


} //DataTypeImpl
