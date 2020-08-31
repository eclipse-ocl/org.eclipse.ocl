/**
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.xtext.base.cs2text.idioms.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment;
import org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegmentSupport;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Custom Segment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.CustomSegmentImpl#getSupportClass <em>Support Class</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.CustomSegmentImpl#getSupportClassName <em>Support Class Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CustomSegmentImpl extends SegmentImpl implements CustomSegment
{
	/**
	 * The number of structural features of the '<em>Custom Segment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_SEGMENT_FEATURE_COUNT = SegmentImpl.SEGMENT_FEATURE_COUNT + 2;


	/**
	 * The cached value of the '{@link #getSupportClass() <em>Support Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupportClass()
	 * @generated
	 * @ordered
	 */
	protected Class<?> supportClass;


	/**
	 * The default value of the '{@link #getSupportClassName() <em>Support Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupportClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String SUPPORT_CLASS_NAME_EDEFAULT = null;


	/**
	 * The cached value of the '{@link #getSupportClassName() <em>Support Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupportClassName()
	 * @generated
	 * @ordered
	 */
	protected String supportClassName = SUPPORT_CLASS_NAME_EDEFAULT;


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CustomSegmentImpl()
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
		return IdiomsPackage.Literals.CUSTOM_SEGMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Class<?> getSupportClass()
	{
		return supportClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSupportClass(Class<?> newSupportClass)
	{
		Class<?> oldSupportClass = supportClass;
		supportClass = newSupportClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 1, oldSupportClass, supportClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSupportClassName()
	{
		return supportClassName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSupportClassName(String newSupportClassName)
	{
		String oldSupportClassName = supportClassName;
		supportClassName = newSupportClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 2, oldSupportClassName, supportClassName));
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
			case 1:
				return getSupportClass();
			case 2:
				return getSupportClassName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case 1:
				setSupportClass((Class<?>)newValue);
				return;
			case 2:
				setSupportClassName((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
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
			case 1:
				setSupportClass((Class<?>)null);
				return;
			case 2:
				setSupportClassName(SUPPORT_CLASS_NAME_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
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
			case 1:
				return supportClass != null;
			case 2:
				return SUPPORT_CLASS_NAME_EDEFAULT == null ? supportClassName != null : !SUPPORT_CLASS_NAME_EDEFAULT.equals(supportClassName);
		}
		return super.eIsSet(featureID);
	}

	private @Nullable CustomSegmentSupport support = null;

	protected @Nullable CustomSegmentSupport getSupport(Object contextObject) {
		if (support == null) {
			Class<?> supportClass = this.supportClass;
			if ((supportClass == null) && (supportClassName != null)) {
				ClassLoader classLoader = contextObject.getClass().getClassLoader();
				try {
					supportClass = classLoader.loadClass(supportClassName);
				} catch (ClassNotFoundException e) {
					return null;
				}
			}
			if (supportClass != null) {
				try {
					support = (CustomSegmentSupport) supportClass.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					return null;
				}
			}
		}
		return support;
	}

	@Override
	public void serialize(SerializationStep serializationStep, UserElementSerializer serializer, SerializationBuilder serializationBuilder) {
		assert serializationBuilder != null;
		EObject eObject = serializer.getElement();
		CustomSegmentSupport support = getSupport(eObject);
		if (support == null) {
			String className = supportClass != null ? supportClass.getName() : supportClassName;
			serializationBuilder.appendError("\n\n«missing " + className + "»\n\n");
		}
		else {
			support.serialize(serializationStep, serializer, serializationBuilder);
		}
	}

	@Override
	public String toString() {
		return "supported by " + (support != null ? support.getClass().getName() : supportClass != null ? supportClass.getName() : supportClassName);
	}
} //CustomSegmentImpl
