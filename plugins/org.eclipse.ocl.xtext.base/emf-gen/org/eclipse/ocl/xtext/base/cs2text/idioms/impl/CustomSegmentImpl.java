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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Segment;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Custom Segment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.CustomSegmentImpl#getDelegate <em>Delegate</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.CustomSegmentImpl#getDelegateClass <em>Delegate Class</em>}</li>
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
	 * The cached value of the '{@link #getDelegate() <em>Delegate</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelegate()
	 * @generated
	 * @ordered
	 */
	protected Segment delegate;


	/**
	 * The default value of the '{@link #getDelegateClass() <em>Delegate Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelegateClass()
	 * @generated
	 * @ordered
	 */
	protected static final String DELEGATE_CLASS_EDEFAULT = null;


	/**
	 * The cached value of the '{@link #getDelegateClass() <em>Delegate Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelegateClass()
	 * @generated
	 * @ordered
	 */
	protected String delegateClass = DELEGATE_CLASS_EDEFAULT;


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
	public Segment getDelegate()
	{
		if (delegate != null && delegate.eIsProxy())
		{
			InternalEObject oldDelegate = (InternalEObject)delegate;
			delegate = (Segment)eResolveProxy(oldDelegate);
			if (delegate != oldDelegate)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 1, oldDelegate, delegate));
			}
		}
		return delegate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Segment basicGetDelegate()
	{
		return delegate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDelegate(Segment newDelegate)
	{
		Segment oldDelegate = delegate;
		delegate = newDelegate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 1, oldDelegate, delegate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDelegateClass()
	{
		return delegateClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDelegateClass(String newDelegateClass)
	{
		String oldDelegateClass = delegateClass;
		delegateClass = newDelegateClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 2, oldDelegateClass, delegateClass));
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
				if (resolve) return getDelegate();
				return basicGetDelegate();
			case 2:
				return getDelegateClass();
		}
		return super.eGet(featureID, resolve, coreType);
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
			case 1:
				setDelegate((Segment)newValue);
				return;
			case 2:
				setDelegateClass((String)newValue);
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
				setDelegate((Segment)null);
				return;
			case 2:
				setDelegateClass(DELEGATE_CLASS_EDEFAULT);
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
				return delegate != null;
			case 2:
				return DELEGATE_CLASS_EDEFAULT == null ? delegateClass != null : !DELEGATE_CLASS_EDEFAULT.equals(delegateClass);
		}
		return super.eIsSet(featureID);
	}

	@Override
	public void serialize(SerializationNode serializationNode, UserElementSerializer serializer, SerializationBuilder serializationBuilder) {
		delegate.serialize(serializationNode, serializer, serializationBuilder);
	}

	public void setDelegate(@NonNull Class<? extends Segment> delegatedClass, Object ... objects) {
		try {
			if (objects.length == 0) {
				this.delegate = delegatedClass.newInstance();
			}
			else {
				Constructor<Segment> matchingConstructor = null;
				for (Constructor<?> constructor : delegatedClass.getConstructors()) {
					Class<?>[] parameterTypes = constructor.getParameterTypes();
					int iMax = parameterTypes.length;
					if (iMax == objects.length) {
						boolean gotIt = true;
						for (int i = 0; i < iMax; i++) {
							if (!parameterTypes[i].isAssignableFrom(objects[i].getClass())) {
								gotIt = false;
								break;
							}
						}
						if (gotIt) {
							@SuppressWarnings("unchecked")
							Constructor<Segment> castConstructor = (Constructor<Segment>) constructor;
							matchingConstructor = castConstructor;
						}
					}
				}
				if (matchingConstructor != null) {
					setDelegate(matchingConstructor.newInstance(objects));
				}
			}
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String toString() {
		return "delegate-to " + delegate;
	}
} //CustomSegmentImpl
