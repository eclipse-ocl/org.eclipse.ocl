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
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.ocl.ocl.AssociationClass;
import org.eclipse.ocl.ocl.Comment;
import org.eclipse.ocl.ocl.Element;
import org.eclipse.ocl.ocl.ElementExtension;
import org.eclipse.ocl.ocl.LanguageExpression;
import org.eclipse.ocl.ocl.OCLASPackage;
import org.eclipse.ocl.ocl.OclElement;
import org.eclipse.ocl.ocl.Property;
import org.eclipse.ocl.ocl.Type;

import org.eclipse.ocl.pivot.library.LibraryFeature;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.internal.PropertyImpl#getAssociationClass <em>Association Class</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.PropertyImpl#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.PropertyImpl#getDefaultValueString <em>Default Value String</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.PropertyImpl#getIsComposite <em>Is Composite</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.PropertyImpl#getIsDerived <em>Is Derived</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.PropertyImpl#getIsID <em>Is ID</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.PropertyImpl#getIsImplicit <em>Is Implicit</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.PropertyImpl#getIsReadOnly <em>Is Read Only</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.PropertyImpl#getIsResolveProxies <em>Is Resolve Proxies</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.PropertyImpl#getIsTransient <em>Is Transient</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.PropertyImpl#getIsUnsettable <em>Is Unsettable</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.PropertyImpl#getIsVolatile <em>Is Volatile</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.PropertyImpl#getKeys <em>Keys</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.PropertyImpl#getOpposite <em>Opposite</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.PropertyImpl#getOwnedExpression <em>Owned Expression</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.PropertyImpl#getOwningClass <em>Owning Class</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.PropertyImpl#getRedefinedProperties <em>Redefined Properties</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.PropertyImpl#getReferredProperty <em>Referred Property</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.PropertyImpl#getSubsettedProperty <em>Subsetted Property</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PropertyImpl extends FeatureImpl implements Property
{
	/**
	 * The number of structural features of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PROPERTY_FEATURE_COUNT = FeatureImpl.FEATURE_FEATURE_COUNT + 19;

	/**
	 * The number of operations of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PROPERTY_OPERATION_COUNT = FeatureImpl.FEATURE_OPERATION_COUNT + 0;


	/**
	 * The cached value of the '{@link #getAssociationClass() <em>Association Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociationClass()
	 * @generated
	 * @ordered
	 */
	protected AssociationClass associationClass;

	/**
	 * The default value of the '{@link #getDefaultValue() <em>Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultValue()
	 * @generated
	 * @ordered
	 */
	protected static final Object DEFAULT_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefaultValue() <em>Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultValue()
	 * @generated
	 * @ordered
	 */
	protected Object defaultValue = DEFAULT_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDefaultValueString() <em>Default Value String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultValueString()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_VALUE_STRING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefaultValueString() <em>Default Value String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultValueString()
	 * @generated
	 * @ordered
	 */
	protected String defaultValueString = DEFAULT_VALUE_STRING_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsComposite() <em>Is Composite</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsComposite()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_COMPOSITE_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getIsComposite() <em>Is Composite</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsComposite()
	 * @generated
	 * @ordered
	 */
	protected Boolean isComposite = IS_COMPOSITE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsDerived() <em>Is Derived</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsDerived()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_DERIVED_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getIsDerived() <em>Is Derived</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsDerived()
	 * @generated
	 * @ordered
	 */
	protected Boolean isDerived = IS_DERIVED_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsID() <em>Is ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsID()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_ID_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getIsID() <em>Is ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsID()
	 * @generated
	 * @ordered
	 */
	protected Boolean isID = IS_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsImplicit() <em>Is Implicit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsImplicit()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_IMPLICIT_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getIsImplicit() <em>Is Implicit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsImplicit()
	 * @generated
	 * @ordered
	 */
	protected Boolean isImplicit = IS_IMPLICIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsReadOnly() <em>Is Read Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsReadOnly()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_READ_ONLY_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getIsReadOnly() <em>Is Read Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsReadOnly()
	 * @generated
	 * @ordered
	 */
	protected Boolean isReadOnly = IS_READ_ONLY_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsResolveProxies() <em>Is Resolve Proxies</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsResolveProxies()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_RESOLVE_PROXIES_EDEFAULT = Boolean.TRUE;

	/**
	 * The cached value of the '{@link #getIsResolveProxies() <em>Is Resolve Proxies</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsResolveProxies()
	 * @generated
	 * @ordered
	 */
	protected Boolean isResolveProxies = IS_RESOLVE_PROXIES_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsTransient() <em>Is Transient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsTransient()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_TRANSIENT_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getIsTransient() <em>Is Transient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsTransient()
	 * @generated
	 * @ordered
	 */
	protected Boolean isTransient = IS_TRANSIENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsUnsettable() <em>Is Unsettable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsUnsettable()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_UNSETTABLE_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getIsUnsettable() <em>Is Unsettable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsUnsettable()
	 * @generated
	 * @ordered
	 */
	protected Boolean isUnsettable = IS_UNSETTABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsVolatile() <em>Is Volatile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsVolatile()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_VOLATILE_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getIsVolatile() <em>Is Volatile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsVolatile()
	 * @generated
	 * @ordered
	 */
	protected Boolean isVolatile = IS_VOLATILE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getKeys() <em>Keys</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKeys()
	 * @generated
	 * @ordered
	 */
	protected EList<Property> keys;

	/**
	 * The cached value of the '{@link #getOpposite() <em>Opposite</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOpposite()
	 * @generated
	 * @ordered
	 */
	protected Property opposite;

	/**
	 * The cached value of the '{@link #getOwnedExpression() <em>Owned Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedExpression()
	 * @generated
	 * @ordered
	 */
	protected LanguageExpression ownedExpression;

	/**
	 * The cached value of the '{@link #getRedefinedProperties() <em>Redefined Properties</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRedefinedProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<Property> redefinedProperties;

	/**
	 * The cached value of the '{@link #getReferredProperty() <em>Referred Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredProperty()
	 * @generated
	 * @ordered
	 */
	protected Property referredProperty;

	/**
	 * The cached value of the '{@link #getSubsettedProperty() <em>Subsetted Property</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubsettedProperty()
	 * @generated
	 * @ordered
	 */
	protected EList<Property> subsettedProperty;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PropertyImpl()
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
		return OCLASPackage.Literals.PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AssociationClass getAssociationClass()
	{
		if (associationClass != null && associationClass.eIsProxy())
		{
			InternalEObject oldAssociationClass = (InternalEObject)associationClass;
			associationClass = (AssociationClass)eResolveProxy(oldAssociationClass);
			if (associationClass != oldAssociationClass)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 13, oldAssociationClass, associationClass));
			}
		}
		return associationClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssociationClass basicGetAssociationClass()
	{
		return associationClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAssociationClass(AssociationClass newAssociationClass, NotificationChain msgs)
	{
		AssociationClass oldAssociationClass = associationClass;
		associationClass = newAssociationClass;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, 13, oldAssociationClass, newAssociationClass);
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
	public void setAssociationClass(AssociationClass newAssociationClass)
	{
		if (newAssociationClass != associationClass)
		{
			NotificationChain msgs = null;
			if (associationClass != null)
				msgs = ((InternalEObject)associationClass).eInverseRemove(this, 22, AssociationClass.class, msgs);
			if (newAssociationClass != null)
				msgs = ((InternalEObject)newAssociationClass).eInverseAdd(this, 22, AssociationClass.class, msgs);
			msgs = basicSetAssociationClass(newAssociationClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 13, newAssociationClass, newAssociationClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getDefaultValue()
	{
		return defaultValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDefaultValue(Object newDefaultValue)
	{
		Object oldDefaultValue = defaultValue;
		defaultValue = newDefaultValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 14, oldDefaultValue, defaultValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDefaultValueString()
	{
		return defaultValueString;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDefaultValueString(String newDefaultValueString)
	{
		String oldDefaultValueString = defaultValueString;
		defaultValueString = newDefaultValueString;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 15, oldDefaultValueString, defaultValueString));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getIsComposite()
	{
		return isComposite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsComposite(Boolean newIsComposite)
	{
		Boolean oldIsComposite = isComposite;
		isComposite = newIsComposite;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 16, oldIsComposite, isComposite));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getIsDerived()
	{
		return isDerived;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsDerived(Boolean newIsDerived)
	{
		Boolean oldIsDerived = isDerived;
		isDerived = newIsDerived;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 17, oldIsDerived, isDerived));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getIsID()
	{
		return isID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsID(Boolean newIsID)
	{
		Boolean oldIsID = isID;
		isID = newIsID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 18, oldIsID, isID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getIsImplicit()
	{
		return isImplicit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsImplicit(Boolean newIsImplicit)
	{
		Boolean oldIsImplicit = isImplicit;
		isImplicit = newIsImplicit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 19, oldIsImplicit, isImplicit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getIsReadOnly()
	{
		return isReadOnly;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsReadOnly(Boolean newIsReadOnly)
	{
		Boolean oldIsReadOnly = isReadOnly;
		isReadOnly = newIsReadOnly;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 20, oldIsReadOnly, isReadOnly));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getIsResolveProxies()
	{
		return isResolveProxies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsResolveProxies(Boolean newIsResolveProxies)
	{
		Boolean oldIsResolveProxies = isResolveProxies;
		isResolveProxies = newIsResolveProxies;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 21, oldIsResolveProxies, isResolveProxies));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getIsTransient()
	{
		return isTransient;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsTransient(Boolean newIsTransient)
	{
		Boolean oldIsTransient = isTransient;
		isTransient = newIsTransient;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 22, oldIsTransient, isTransient));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getIsUnsettable()
	{
		return isUnsettable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsUnsettable(Boolean newIsUnsettable)
	{
		Boolean oldIsUnsettable = isUnsettable;
		isUnsettable = newIsUnsettable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 23, oldIsUnsettable, isUnsettable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getIsVolatile()
	{
		return isVolatile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsVolatile(Boolean newIsVolatile)
	{
		Boolean oldIsVolatile = isVolatile;
		isVolatile = newIsVolatile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 24, oldIsVolatile, isVolatile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Property> getKeys()
	{
		if (keys == null)
		{
			keys = new EObjectResolvingEList<Property>(Property.class, this, 25);
		}
		return keys;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Property getOpposite()
	{
		if (opposite != null && opposite.eIsProxy())
		{
			InternalEObject oldOpposite = (InternalEObject)opposite;
			opposite = (Property)eResolveProxy(oldOpposite);
			if (opposite != oldOpposite)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 26, oldOpposite, opposite));
			}
		}
		return opposite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property basicGetOpposite()
	{
		return opposite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOpposite(Property newOpposite)
	{
		Property oldOpposite = opposite;
		opposite = newOpposite;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 26, oldOpposite, opposite));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LanguageExpression getOwnedExpression()
	{
		return ownedExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedExpression(LanguageExpression newOwnedExpression, NotificationChain msgs)
	{
		LanguageExpression oldOwnedExpression = ownedExpression;
		ownedExpression = newOwnedExpression;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, 27, oldOwnedExpression, newOwnedExpression);
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
	public void setOwnedExpression(LanguageExpression newOwnedExpression)
	{
		if (newOwnedExpression != ownedExpression)
		{
			NotificationChain msgs = null;
			if (ownedExpression != null)
				msgs = ((InternalEObject)ownedExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (27), null, msgs);
			if (newOwnedExpression != null)
				msgs = ((InternalEObject)newOwnedExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (27), null, msgs);
			msgs = basicSetOwnedExpression(newOwnedExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 27, newOwnedExpression, newOwnedExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.eclipse.ocl.ocl.Class getOwningClass()
	{
		if (eContainerFeatureID() != (28)) return null;
		return (org.eclipse.ocl.ocl.Class)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningClass(org.eclipse.ocl.ocl.Class newOwningClass, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningClass, 28, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningClass(org.eclipse.ocl.ocl.Class newOwningClass)
	{
		if (newOwningClass != eInternalContainer() || (eContainerFeatureID() != (28) && newOwningClass != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningClass))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningClass != null)
				msgs = ((InternalEObject)newOwningClass).eInverseAdd(this, 19, org.eclipse.ocl.ocl.Class.class, msgs);
			msgs = basicSetOwningClass(newOwningClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 28, newOwningClass, newOwningClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Property> getRedefinedProperties()
	{
		if (redefinedProperties == null)
		{
			redefinedProperties = new EObjectResolvingEList<Property>(Property.class, this, 29);
		}
		return redefinedProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Property getReferredProperty()
	{
		if (referredProperty != null && referredProperty.eIsProxy())
		{
			InternalEObject oldReferredProperty = (InternalEObject)referredProperty;
			referredProperty = (Property)eResolveProxy(oldReferredProperty);
			if (referredProperty != oldReferredProperty)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 30, oldReferredProperty, referredProperty));
			}
		}
		return referredProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property basicGetReferredProperty()
	{
		return referredProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReferredProperty(Property newReferredProperty)
	{
		Property oldReferredProperty = referredProperty;
		referredProperty = newReferredProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 30, oldReferredProperty, referredProperty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Property> getSubsettedProperty()
	{
		if (subsettedProperty == null)
		{
			subsettedProperty = new EObjectResolvingEList<Property>(Property.class, this, 31);
		}
		return subsettedProperty;
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
			case 2:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case 4:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case 5:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case 13:
				if (associationClass != null)
					msgs = ((InternalEObject)associationClass).eInverseRemove(this, 22, AssociationClass.class, msgs);
				return basicSetAssociationClass((AssociationClass)otherEnd, msgs);
			case 28:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningClass((org.eclipse.ocl.ocl.Class)otherEnd, msgs);
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
			case 2:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case 3:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case 4:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case 5:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case 13:
				return basicSetAssociationClass(null, msgs);
			case 27:
				return basicSetOwnedExpression(null, msgs);
			case 28:
				return basicSetOwningClass(null, msgs);
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
			case 28:
				return eInternalContainer().eInverseRemove(this, 19, org.eclipse.ocl.ocl.Class.class, msgs);
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
				return getIsMany();
			case 8:
				return getIsRequired();
			case 9:
				if (resolve) return getType();
				return basicGetType();
			case 10:
				return getImplementation();
			case 11:
				return getImplementationClass();
			case 12:
				return getIsStatic();
			case 13:
				if (resolve) return getAssociationClass();
				return basicGetAssociationClass();
			case 14:
				return getDefaultValue();
			case 15:
				return getDefaultValueString();
			case 16:
				return getIsComposite();
			case 17:
				return getIsDerived();
			case 18:
				return getIsID();
			case 19:
				return getIsImplicit();
			case 20:
				return getIsReadOnly();
			case 21:
				return getIsResolveProxies();
			case 22:
				return getIsTransient();
			case 23:
				return getIsUnsettable();
			case 24:
				return getIsVolatile();
			case 25:
				return getKeys();
			case 26:
				if (resolve) return getOpposite();
				return basicGetOpposite();
			case 27:
				return getOwnedExpression();
			case 28:
				return getOwningClass();
			case 29:
				return getRedefinedProperties();
			case 30:
				if (resolve) return getReferredProperty();
				return basicGetReferredProperty();
			case 31:
				return getSubsettedProperty();
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
			case 8:
				setIsRequired((Boolean)newValue);
				return;
			case 9:
				setType((Type)newValue);
				return;
			case 10:
				setImplementation((LibraryFeature)newValue);
				return;
			case 11:
				setImplementationClass((String)newValue);
				return;
			case 12:
				setIsStatic((Boolean)newValue);
				return;
			case 13:
				setAssociationClass((AssociationClass)newValue);
				return;
			case 14:
				setDefaultValue(newValue);
				return;
			case 15:
				setDefaultValueString((String)newValue);
				return;
			case 16:
				setIsComposite((Boolean)newValue);
				return;
			case 17:
				setIsDerived((Boolean)newValue);
				return;
			case 18:
				setIsID((Boolean)newValue);
				return;
			case 19:
				setIsImplicit((Boolean)newValue);
				return;
			case 20:
				setIsReadOnly((Boolean)newValue);
				return;
			case 21:
				setIsResolveProxies((Boolean)newValue);
				return;
			case 22:
				setIsTransient((Boolean)newValue);
				return;
			case 23:
				setIsUnsettable((Boolean)newValue);
				return;
			case 24:
				setIsVolatile((Boolean)newValue);
				return;
			case 25:
				getKeys().clear();
				getKeys().addAll((Collection<? extends Property>)newValue);
				return;
			case 26:
				setOpposite((Property)newValue);
				return;
			case 27:
				setOwnedExpression((LanguageExpression)newValue);
				return;
			case 28:
				setOwningClass((org.eclipse.ocl.ocl.Class)newValue);
				return;
			case 29:
				getRedefinedProperties().clear();
				getRedefinedProperties().addAll((Collection<? extends Property>)newValue);
				return;
			case 30:
				setReferredProperty((Property)newValue);
				return;
			case 31:
				getSubsettedProperty().clear();
				getSubsettedProperty().addAll((Collection<? extends Property>)newValue);
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
			case 8:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case 9:
				setType((Type)null);
				return;
			case 10:
				setImplementation(IMPLEMENTATION_EDEFAULT);
				return;
			case 11:
				setImplementationClass(IMPLEMENTATION_CLASS_EDEFAULT);
				return;
			case 12:
				setIsStatic(IS_STATIC_EDEFAULT);
				return;
			case 13:
				setAssociationClass((AssociationClass)null);
				return;
			case 14:
				setDefaultValue(DEFAULT_VALUE_EDEFAULT);
				return;
			case 15:
				setDefaultValueString(DEFAULT_VALUE_STRING_EDEFAULT);
				return;
			case 16:
				setIsComposite(IS_COMPOSITE_EDEFAULT);
				return;
			case 17:
				setIsDerived(IS_DERIVED_EDEFAULT);
				return;
			case 18:
				setIsID(IS_ID_EDEFAULT);
				return;
			case 19:
				setIsImplicit(IS_IMPLICIT_EDEFAULT);
				return;
			case 20:
				setIsReadOnly(IS_READ_ONLY_EDEFAULT);
				return;
			case 21:
				setIsResolveProxies(IS_RESOLVE_PROXIES_EDEFAULT);
				return;
			case 22:
				setIsTransient(IS_TRANSIENT_EDEFAULT);
				return;
			case 23:
				setIsUnsettable(IS_UNSETTABLE_EDEFAULT);
				return;
			case 24:
				setIsVolatile(IS_VOLATILE_EDEFAULT);
				return;
			case 25:
				getKeys().clear();
				return;
			case 26:
				setOpposite((Property)null);
				return;
			case 27:
				setOwnedExpression((LanguageExpression)null);
				return;
			case 28:
				setOwningClass((org.eclipse.ocl.ocl.Class)null);
				return;
			case 29:
				getRedefinedProperties().clear();
				return;
			case 30:
				setReferredProperty((Property)null);
				return;
			case 31:
				getSubsettedProperty().clear();
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
				return IS_MANY_EDEFAULT == null ? getIsMany() != null : !IS_MANY_EDEFAULT.equals(getIsMany());
			case 8:
				return IS_REQUIRED_EDEFAULT == null ? isRequired != null : !IS_REQUIRED_EDEFAULT.equals(isRequired);
			case 9:
				return type != null;
			case 10:
				return IMPLEMENTATION_EDEFAULT == null ? implementation != null : !IMPLEMENTATION_EDEFAULT.equals(implementation);
			case 11:
				return IMPLEMENTATION_CLASS_EDEFAULT == null ? implementationClass != null : !IMPLEMENTATION_CLASS_EDEFAULT.equals(implementationClass);
			case 12:
				return IS_STATIC_EDEFAULT == null ? isStatic != null : !IS_STATIC_EDEFAULT.equals(isStatic);
			case 13:
				return associationClass != null;
			case 14:
				return DEFAULT_VALUE_EDEFAULT == null ? defaultValue != null : !DEFAULT_VALUE_EDEFAULT.equals(defaultValue);
			case 15:
				return DEFAULT_VALUE_STRING_EDEFAULT == null ? defaultValueString != null : !DEFAULT_VALUE_STRING_EDEFAULT.equals(defaultValueString);
			case 16:
				return IS_COMPOSITE_EDEFAULT == null ? isComposite != null : !IS_COMPOSITE_EDEFAULT.equals(isComposite);
			case 17:
				return IS_DERIVED_EDEFAULT == null ? isDerived != null : !IS_DERIVED_EDEFAULT.equals(isDerived);
			case 18:
				return IS_ID_EDEFAULT == null ? isID != null : !IS_ID_EDEFAULT.equals(isID);
			case 19:
				return IS_IMPLICIT_EDEFAULT == null ? isImplicit != null : !IS_IMPLICIT_EDEFAULT.equals(isImplicit);
			case 20:
				return IS_READ_ONLY_EDEFAULT == null ? isReadOnly != null : !IS_READ_ONLY_EDEFAULT.equals(isReadOnly);
			case 21:
				return IS_RESOLVE_PROXIES_EDEFAULT == null ? isResolveProxies != null : !IS_RESOLVE_PROXIES_EDEFAULT.equals(isResolveProxies);
			case 22:
				return IS_TRANSIENT_EDEFAULT == null ? isTransient != null : !IS_TRANSIENT_EDEFAULT.equals(isTransient);
			case 23:
				return IS_UNSETTABLE_EDEFAULT == null ? isUnsettable != null : !IS_UNSETTABLE_EDEFAULT.equals(isUnsettable);
			case 24:
				return IS_VOLATILE_EDEFAULT == null ? isVolatile != null : !IS_VOLATILE_EDEFAULT.equals(isVolatile);
			case 25:
				return keys != null && !keys.isEmpty();
			case 26:
				return opposite != null;
			case 27:
				return ownedExpression != null;
			case 28:
				return getOwningClass() != null;
			case 29:
				return redefinedProperties != null && !redefinedProperties.isEmpty();
			case 30:
				return referredProperty != null;
			case 31:
				return subsettedProperty != null && !subsettedProperty.isEmpty();
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
		result.append(" (defaultValue: "); //$NON-NLS-1$
		result.append(defaultValue);
		result.append(", defaultValueString: "); //$NON-NLS-1$
		result.append(defaultValueString);
		result.append(", isComposite: "); //$NON-NLS-1$
		result.append(isComposite);
		result.append(", isDerived: "); //$NON-NLS-1$
		result.append(isDerived);
		result.append(", isID: "); //$NON-NLS-1$
		result.append(isID);
		result.append(", isImplicit: "); //$NON-NLS-1$
		result.append(isImplicit);
		result.append(", isReadOnly: "); //$NON-NLS-1$
		result.append(isReadOnly);
		result.append(", isResolveProxies: "); //$NON-NLS-1$
		result.append(isResolveProxies);
		result.append(", isTransient: "); //$NON-NLS-1$
		result.append(isTransient);
		result.append(", isUnsettable: "); //$NON-NLS-1$
		result.append(isUnsettable);
		result.append(", isVolatile: "); //$NON-NLS-1$
		result.append(isVolatile);
		result.append(')');
		return result.toString();
	}


} //PropertyImpl
