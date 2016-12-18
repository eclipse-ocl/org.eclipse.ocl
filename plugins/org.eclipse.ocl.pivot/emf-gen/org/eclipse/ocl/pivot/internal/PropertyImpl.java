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

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.AssociationClass;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ValueSpecification;
import org.eclipse.ocl.pivot.ids.EnumerationId;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.classifier.ClassifierOclContainerOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionIncludesOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanAndOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation;
import org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation;
import org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation;
import org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.Value;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.PropertyImpl#getAssociationClass <em>Association Class</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.PropertyImpl#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.PropertyImpl#getDefaultValueString <em>Default Value String</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.PropertyImpl#isIsComposite <em>Is Composite</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.PropertyImpl#isIsDerived <em>Is Derived</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.PropertyImpl#isIsID <em>Is ID</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.PropertyImpl#isIsImplicit <em>Is Implicit</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.PropertyImpl#isIsReadOnly <em>Is Read Only</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.PropertyImpl#isIsResolveProxies <em>Is Resolve Proxies</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.PropertyImpl#isIsTransient <em>Is Transient</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.PropertyImpl#isIsUnsettable <em>Is Unsettable</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.PropertyImpl#isIsVolatile <em>Is Volatile</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.PropertyImpl#getKeys <em>Keys</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.PropertyImpl#getOpposite <em>Opposite</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.PropertyImpl#getOwnedExpression <em>Owned Expression</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.PropertyImpl#getOwningClass <em>Owning Class</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.PropertyImpl#getRedefinedProperties <em>Redefined Properties</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.PropertyImpl#getReferredProperty <em>Referred Property</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.PropertyImpl#getSubsettedProperty <em>Subsetted Property</em>}</li>
 * </ul>
 *
 * @generated
 */
@SuppressWarnings("cast")
public class PropertyImpl
		extends FeatureImpl
		implements Property {

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
	 * The default value of the '{@link #isIsComposite() <em>Is Composite</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsComposite()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_COMPOSITE_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isIsComposite() <em>Is Composite</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsComposite()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_COMPOSITE_EFLAG = 1 << 10;

	/**
	 * The default value of the '{@link #isIsDerived() <em>Is Derived</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsDerived()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_DERIVED_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isIsDerived() <em>Is Derived</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsDerived()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_DERIVED_EFLAG = 1 << 11;

	/**
	 * The default value of the '{@link #isIsID() <em>Is ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsID()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_ID_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isIsID() <em>Is ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsID()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_ID_EFLAG = 1 << 12;

	/**
	 * The default value of the '{@link #isIsImplicit() <em>Is Implicit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsImplicit()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_IMPLICIT_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isIsImplicit() <em>Is Implicit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsImplicit()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_IMPLICIT_EFLAG = 1 << 13;

	/**
	 * The default value of the '{@link #isIsReadOnly() <em>Is Read Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsReadOnly()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_READ_ONLY_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isIsReadOnly() <em>Is Read Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsReadOnly()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_READ_ONLY_EFLAG = 1 << 14;

	/**
	 * The default value of the '{@link #isIsResolveProxies() <em>Is Resolve Proxies</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsResolveProxies()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_RESOLVE_PROXIES_EDEFAULT = true;

	/**
	 * The flag representing the value of the '{@link #isIsResolveProxies() <em>Is Resolve Proxies</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsResolveProxies()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_RESOLVE_PROXIES_EFLAG = 1 << 15;

	/**
	 * The default value of the '{@link #isIsTransient() <em>Is Transient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsTransient()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_TRANSIENT_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isIsTransient() <em>Is Transient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsTransient()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_TRANSIENT_EFLAG = 1 << 16;

	/**
	 * The default value of the '{@link #isIsUnsettable() <em>Is Unsettable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsUnsettable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_UNSETTABLE_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isIsUnsettable() <em>Is Unsettable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsUnsettable()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_UNSETTABLE_EFLAG = 1 << 17;

	/**
	 * The default value of the '{@link #isIsVolatile() <em>Is Volatile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsVolatile()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_VOLATILE_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isIsVolatile() <em>Is Volatile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsVolatile()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_VOLATILE_EFLAG = 1 << 18;

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
	protected PropertyImpl() {
		super();
		eFlags |= IS_RESOLVE_PROXIES_EFLAG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.PROPERTY;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.PROPERTY__ASSOCIATION_CLASS, oldAssociationClass, associationClass));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.PROPERTY__ASSOCIATION_CLASS, oldAssociationClass, newAssociationClass);
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
				msgs = ((InternalEObject)associationClass).eInverseRemove(this, PivotPackage.ASSOCIATION_CLASS__UNOWNED_ATTRIBUTES, AssociationClass.class, msgs);
			if (newAssociationClass != null)
				msgs = ((InternalEObject)newAssociationClass).eInverseAdd(this, PivotPackage.ASSOCIATION_CLASS__UNOWNED_ATTRIBUTES, AssociationClass.class, msgs);
			msgs = basicSetAssociationClass(newAssociationClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PROPERTY__ASSOCIATION_CLASS, newAssociationClass, newAssociationClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsReadOnly(boolean newIsReadOnly) {
		boolean oldIsReadOnly = (eFlags & IS_READ_ONLY_EFLAG) != 0;
		if (newIsReadOnly) eFlags |= IS_READ_ONLY_EFLAG; else eFlags &= ~IS_READ_ONLY_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PROPERTY__IS_READ_ONLY, oldIsReadOnly, newIsReadOnly));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsResolveProxies()
	{
		return (eFlags & IS_RESOLVE_PROXIES_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsComposite(boolean newIsComposite) {
		boolean oldIsComposite = (eFlags & IS_COMPOSITE_EFLAG) != 0;
		if (newIsComposite) eFlags |= IS_COMPOSITE_EFLAG; else eFlags &= ~IS_COMPOSITE_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PROPERTY__IS_COMPOSITE, oldIsComposite, newIsComposite));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsDerived()
	{
		return (eFlags & IS_DERIVED_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsDerived(boolean newIsDerived) {
		boolean oldIsDerived = (eFlags & IS_DERIVED_EFLAG) != 0;
		if (newIsDerived) eFlags |= IS_DERIVED_EFLAG; else eFlags &= ~IS_DERIVED_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PROPERTY__IS_DERIVED, oldIsDerived, newIsDerived));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsID()
	{
		return (eFlags & IS_ID_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Property getOpposite() {
		if (opposite != null && opposite.eIsProxy())
		{
			InternalEObject oldOpposite = (InternalEObject)opposite;
			opposite = (Property)eResolveProxy(oldOpposite);
			if (opposite != oldOpposite)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.PROPERTY__OPPOSITE, oldOpposite, opposite));
			}
		}
		return opposite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property basicGetOpposite() {
		return opposite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOpposite(Property newOpposite) {
		Property oldOpposite = opposite;
		opposite = newOpposite;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PROPERTY__OPPOSITE, oldOpposite, opposite));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.PROPERTY__OWNED_EXPRESSION, oldOwnedExpression, newOwnedExpression);
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
				msgs = ((InternalEObject)ownedExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.PROPERTY__OWNED_EXPRESSION, null, msgs);
			if (newOwnedExpression != null)
				msgs = ((InternalEObject)newOwnedExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PivotPackage.PROPERTY__OWNED_EXPRESSION, null, msgs);
			msgs = basicSetOwnedExpression(newOwnedExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PROPERTY__OWNED_EXPRESSION, newOwnedExpression, newOwnedExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsImplicit(boolean newIsImplicit)
	{
		boolean oldIsImplicit = (eFlags & IS_IMPLICIT_EFLAG) != 0;
		if (newIsImplicit) eFlags |= IS_IMPLICIT_EFLAG; else eFlags &= ~IS_IMPLICIT_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PROPERTY__IS_IMPLICIT, oldIsImplicit, newIsImplicit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsReadOnly()
	{
		return (eFlags & IS_READ_ONLY_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Object getDefaultValue()
	{
		String defaultValueString2 = defaultValueString;
		if ((defaultValue == null) && (defaultValueString2 != null)) {
			TypeId typeId = getTypeId();
			if (typeId instanceof EnumerationId) {
				defaultValue = NameUtil.getNameable(((Enumeration)type).getOwnedLiterals(), defaultValueString2);
				if (defaultValue == null) {
					throw new IllegalStateException("Unknown enumeration literal'" + defaultValueString2 + "' for '" + typeId + "'");
				}
			}
			else if (typeId == TypeId.BOOLEAN) {
				defaultValue = Boolean.valueOf(defaultValueString2);
			}
			else if (typeId == TypeId.STRING) {
				defaultValue = (String)defaultValueString2;
			}
			else if (typeId == TypeId.REAL) {
				defaultValue = ValueUtil.realValueOf(defaultValueString2);
			}
			else if (typeId == TypeId.INTEGER) {
				defaultValue = ValueUtil.integerValueOf(defaultValueString2);
			}
			else if (typeId == TypeId.UNLIMITED_NATURAL) {
				defaultValue = ValueUtil.unlimitedNaturalValueOf(defaultValueString2);
			}
			else {
				defaultValue = null;		// FIXME ?? caller's responsibility to help by providing a ResourceSet etc.
			}
		}
		return defaultValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void setDefaultValue(Object newDefaultValue)
	{
		Object oldDefaultValue = defaultValue;
		defaultValue = newDefaultValue;
		if (newDefaultValue != oldDefaultValue) {
			String newDefaultValueString = null;
			if (newDefaultValue instanceof String) {
				newDefaultValueString = (String)newDefaultValue;
			}
			else if (newDefaultValue instanceof Boolean) {
				newDefaultValueString = newDefaultValue.toString();
			}
			else if (newDefaultValue instanceof Value) {
				newDefaultValueString = newDefaultValue.toString();
			}
			else if (newDefaultValue instanceof EnumerationLiteral) {
				newDefaultValueString = ((EnumerationLiteral)newDefaultValue).getName();
			}
			else if (newDefaultValue instanceof EObject) {
				URI uri = EcoreUtil.getURI((EObject)newDefaultValue);
				newDefaultValueString = uri.toString(); 		// FIXME resolve to parent base URI
			}
			else {
				newDefaultValueString = newDefaultValue.toString();
			}
			setDefaultValueStringGen(newDefaultValueString);
		}
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
	public void setDefaultValueStringGen(String newDefaultValueString)
	{
		String oldDefaultValueString = defaultValueString;
		defaultValueString = newDefaultValueString;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PROPERTY__DEFAULT_VALUE_STRING, oldDefaultValueString, defaultValueString));
	}
	@Override
	public void setDefaultValueString(String newDefaultValueString)
	{
		String oldDefaultValueString = defaultValueString;
		setDefaultValueStringGen(newDefaultValueString);
		if ((oldDefaultValueString != defaultValueString) && ((oldDefaultValueString == null) || !oldDefaultValueString.equals(defaultValueString))) {
			defaultValue = null;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsComposite()
	{
		return (eFlags & IS_COMPOSITE_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsID(boolean newIsID)
	{
		boolean oldIsID = (eFlags & IS_ID_EFLAG) != 0;
		if (newIsID) eFlags |= IS_ID_EFLAG; else eFlags &= ~IS_ID_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PROPERTY__IS_ID, oldIsID, newIsID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsImplicit()
	{
		return (eFlags & IS_IMPLICIT_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("null")
	@Override
	public @NonNull List<Property> getKeys()
	{
		if (keys == null)
		{
			keys = new EObjectResolvingEList<Property>(Property.class, this, PivotPackage.PROPERTY__KEYS);
		}
		return keys;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsResolveProxies(boolean newIsResolveProxies)
	{
		boolean oldIsResolveProxies = (eFlags & IS_RESOLVE_PROXIES_EFLAG) != 0;
		if (newIsResolveProxies) eFlags |= IS_RESOLVE_PROXIES_EFLAG; else eFlags &= ~IS_RESOLVE_PROXIES_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PROPERTY__IS_RESOLVE_PROXIES, oldIsResolveProxies, newIsResolveProxies));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsTransient()
	{
		return (eFlags & IS_TRANSIENT_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsTransient(boolean newIsTransient)
	{
		boolean oldIsTransient = (eFlags & IS_TRANSIENT_EFLAG) != 0;
		if (newIsTransient) eFlags |= IS_TRANSIENT_EFLAG; else eFlags &= ~IS_TRANSIENT_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PROPERTY__IS_TRANSIENT, oldIsTransient, newIsTransient));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsUnsettable()
	{
		return (eFlags & IS_UNSETTABLE_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsUnsettable(boolean newIsUnsettable)
	{
		boolean oldIsUnsettable = (eFlags & IS_UNSETTABLE_EFLAG) != 0;
		if (newIsUnsettable) eFlags |= IS_UNSETTABLE_EFLAG; else eFlags &= ~IS_UNSETTABLE_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PROPERTY__IS_UNSETTABLE, oldIsUnsettable, newIsUnsettable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsVolatile()
	{
		return (eFlags & IS_VOLATILE_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsVolatile(boolean newIsVolatile)
	{
		boolean oldIsVolatile = (eFlags & IS_VOLATILE_EFLAG) != 0;
		if (newIsVolatile) eFlags |= IS_VOLATILE_EFLAG; else eFlags &= ~IS_VOLATILE_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PROPERTY__IS_VOLATILE, oldIsVolatile, newIsVolatile));
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
			subsettedProperty = new EObjectResolvingEList<Property>(Property.class, this, PivotPackage.PROPERTY__SUBSETTED_PROPERTY);
		}
		return subsettedProperty;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.PROPERTY__REFERRED_PROPERTY, oldReferredProperty, referredProperty));
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
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PROPERTY__REFERRED_PROPERTY, oldReferredProperty, referredProperty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isAttribute(final Property p)
	{
		/**
		 *
		 * let container : OclElement[?] = oclContainer()
		 * in
		 *   container.oclIsKindOf(Class) and
		 *   container.oclAsType(Class)
		 *   .ownedProperties->includes(self)
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ java.lang.@Nullable Object container = ClassifierOclContainerOperation.INSTANCE.evaluate(executor, this);
		/*@Caught*/ @NonNull Object CAUGHT_oclIsKindOf;
		try {
			final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_Class_0 = idResolver.getClass(PivotTables.CLSSid_Class, null);
			final /*@Thrown*/ boolean oclIsKindOf = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, container, TYP_Class_0).booleanValue();
			CAUGHT_oclIsKindOf = oclIsKindOf;
		}
		catch (Exception e) {
			CAUGHT_oclIsKindOf = ValueUtil.createInvalidValue(e);
		}
		/*@Caught*/ @NonNull Object CAUGHT_includes;
		try {
			final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_Class_1 = idResolver.getClass(PivotTables.CLSSid_Class, null);
			final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull Class oclAsType = ClassUtil.nonNullState((org.eclipse.ocl.pivot.Class)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, container, TYP_Class_1));
			final /*@Thrown*/ java.util.@NonNull List<Property> ownedProperties = oclAsType.getOwnedProperties();
			final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull OrderedSetValue BOXED_ownedProperties = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_Property, ownedProperties);
			final /*@Thrown*/ boolean includes = CollectionIncludesOperation.INSTANCE.evaluate(BOXED_ownedProperties, this).booleanValue();
			CAUGHT_includes = includes;
		}
		catch (Exception e) {
			CAUGHT_includes = ValueUtil.createInvalidValue(e);
		}
		final /*@Thrown*/ java.lang.@Nullable Boolean and = BooleanAndOperation.INSTANCE.evaluate(CAUGHT_oclIsKindOf, CAUGHT_includes);
		if (and == null) {
			throw new InvalidValueException("Null body for \'pivot::Property::isAttribute(Property[1]) : Boolean[1]\'");
		}
		return and;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateCompatibleDefaultExpression(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv CompatibleDefaultExpression:
		 *   let
		 *     severity : Integer[1] = 'Property::CompatibleDefaultExpression'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let result : Boolean[?] = ownedExpression <> null and
		 *         ownedExpression.oclAsType(ExpressionInOCL).ownedBody <> null implies
		 *         CompatibleBody(ownedExpression)
		 *       in
		 *         'Property::CompatibleDefaultExpression'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_Property_c_c_CompatibleDefaultExpression);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @Nullable Object CAUGHT_result;
			try {
				/*@Caught*/ @Nullable Object CAUGHT_and;
				try {
					/*@Caught*/ @NonNull Object CAUGHT_ne;
					try {
						final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable LanguageExpression ownedExpression = this.getOwnedExpression();
						final /*@Thrown*/ boolean ne = ownedExpression != null;
						CAUGHT_ne = ne;
					}
					catch (Exception e) {
						CAUGHT_ne = ValueUtil.createInvalidValue(e);
					}
					/*@Caught*/ @NonNull Object CAUGHT_ne_0;
					try {
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_ExpressionInOCL_0 = idResolver.getClass(PivotTables.CLSSid_ExpressionInOCL, null);
						final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable LanguageExpression ownedExpression_0 = this.getOwnedExpression();
						final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull ExpressionInOCL oclAsType = ClassUtil.nonNullState((ExpressionInOCL)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, ownedExpression_0, TYP_ExpressionInOCL_0));
						final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedBody = oclAsType.getOwnedBody();
						final /*@Thrown*/ boolean ne_0 = ownedBody != null;
						CAUGHT_ne_0 = ne_0;
					}
					catch (Exception e) {
						CAUGHT_ne_0 = ValueUtil.createInvalidValue(e);
					}
					final /*@Thrown*/ java.lang.@Nullable Boolean and = BooleanAndOperation.INSTANCE.evaluate(CAUGHT_ne, CAUGHT_ne_0);
					CAUGHT_and = and;
				}
				catch (Exception e) {
					CAUGHT_and = ValueUtil.createInvalidValue(e);
				}
				/*@Caught*/ @NonNull Object CAUGHT_CompatibleBody;
				try {
					final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable LanguageExpression ownedExpression_1 = this.getOwnedExpression();
					final /*@Thrown*/ boolean CompatibleBody = this.CompatibleBody(ownedExpression_1);
					CAUGHT_CompatibleBody = CompatibleBody;
				}
				catch (Exception e) {
					CAUGHT_CompatibleBody = ValueUtil.createInvalidValue(e);
				}
				final /*@Thrown*/ java.lang.@Nullable Boolean result = BooleanImpliesOperation.INSTANCE.evaluate(CAUGHT_and, CAUGHT_CompatibleBody);
				CAUGHT_result = result;
			}
			catch (Exception e) {
				CAUGHT_result = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_Property_c_c_CompatibleDefaultExpression, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
			symbol_0 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_0;
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
			case PivotPackage.PROPERTY__ANNOTATING_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.PROPERTY__OWNED_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.PROPERTY__OWNED_EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case PivotPackage.PROPERTY__ASSOCIATION_CLASS:
				if (associationClass != null)
					msgs = ((InternalEObject)associationClass).eInverseRemove(this, PivotPackage.ASSOCIATION_CLASS__UNOWNED_ATTRIBUTES, AssociationClass.class, msgs);
				return basicSetAssociationClass((AssociationClass)otherEnd, msgs);
			case PivotPackage.PROPERTY__OWNING_CLASS:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningClass((org.eclipse.ocl.pivot.Class)otherEnd, msgs);
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
			case PivotPackage.PROPERTY__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.PROPERTY__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.PROPERTY__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.PROPERTY__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.PROPERTY__ASSOCIATION_CLASS:
				return basicSetAssociationClass(null, msgs);
			case PivotPackage.PROPERTY__OWNED_EXPRESSION:
				return basicSetOwnedExpression(null, msgs);
			case PivotPackage.PROPERTY__OWNING_CLASS:
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
	public NotificationChain eBasicRemoveFromContainerFeature(
			NotificationChain msgs) {
		switch (eContainerFeatureID())
		{
			case PivotPackage.PROPERTY__OWNING_CLASS:
				return eInternalContainer().eInverseRemove(this, PivotPackage.CLASS__OWNED_PROPERTIES, org.eclipse.ocl.pivot.Class.class, msgs);
		}
		return eDynamicBasicRemoveFromContainer(msgs);
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
			case PivotPackage.PROPERTY__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.PROPERTY__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.PROPERTY__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.PROPERTY__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.PROPERTY__NAME:
				return getName();
			case PivotPackage.PROPERTY__IS_MANY:
				return isIsMany();
			case PivotPackage.PROPERTY__IS_REQUIRED:
				return isIsRequired();
			case PivotPackage.PROPERTY__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case PivotPackage.PROPERTY__IMPLEMENTATION:
				return getImplementation();
			case PivotPackage.PROPERTY__IMPLEMENTATION_CLASS:
				return getImplementationClass();
			case PivotPackage.PROPERTY__IS_STATIC:
				return isIsStatic();
			case PivotPackage.PROPERTY__ASSOCIATION_CLASS:
				if (resolve) return getAssociationClass();
				return basicGetAssociationClass();
			case PivotPackage.PROPERTY__DEFAULT_VALUE:
				return getDefaultValue();
			case PivotPackage.PROPERTY__DEFAULT_VALUE_STRING:
				return getDefaultValueString();
			case PivotPackage.PROPERTY__IS_COMPOSITE:
				return isIsComposite();
			case PivotPackage.PROPERTY__IS_DERIVED:
				return isIsDerived();
			case PivotPackage.PROPERTY__IS_ID:
				return isIsID();
			case PivotPackage.PROPERTY__IS_IMPLICIT:
				return isIsImplicit();
			case PivotPackage.PROPERTY__IS_READ_ONLY:
				return isIsReadOnly();
			case PivotPackage.PROPERTY__IS_RESOLVE_PROXIES:
				return isIsResolveProxies();
			case PivotPackage.PROPERTY__IS_TRANSIENT:
				return isIsTransient();
			case PivotPackage.PROPERTY__IS_UNSETTABLE:
				return isIsUnsettable();
			case PivotPackage.PROPERTY__IS_VOLATILE:
				return isIsVolatile();
			case PivotPackage.PROPERTY__KEYS:
				return getKeys();
			case PivotPackage.PROPERTY__OPPOSITE:
				if (resolve) return getOpposite();
				return basicGetOpposite();
			case PivotPackage.PROPERTY__OWNED_EXPRESSION:
				return getOwnedExpression();
			case PivotPackage.PROPERTY__OWNING_CLASS:
				return getOwningClass();
			case PivotPackage.PROPERTY__REDEFINED_PROPERTIES:
				return getRedefinedProperties();
			case PivotPackage.PROPERTY__REFERRED_PROPERTY:
				if (resolve) return getReferredProperty();
				return basicGetReferredProperty();
			case PivotPackage.PROPERTY__SUBSETTED_PROPERTY:
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
	public void eSet(int featureID, Object newValue) {
		switch (featureID)
		{
			case PivotPackage.PROPERTY__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.PROPERTY__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.PROPERTY__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.PROPERTY__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.PROPERTY__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.PROPERTY__IS_REQUIRED:
				setIsRequired((Boolean)newValue);
				return;
			case PivotPackage.PROPERTY__TYPE:
				setType((Type)newValue);
				return;
			case PivotPackage.PROPERTY__IMPLEMENTATION:
				setImplementation((LibraryFeature)newValue);
				return;
			case PivotPackage.PROPERTY__IMPLEMENTATION_CLASS:
				setImplementationClass((String)newValue);
				return;
			case PivotPackage.PROPERTY__IS_STATIC:
				setIsStatic((Boolean)newValue);
				return;
			case PivotPackage.PROPERTY__ASSOCIATION_CLASS:
				setAssociationClass((AssociationClass)newValue);
				return;
			case PivotPackage.PROPERTY__DEFAULT_VALUE:
				setDefaultValue(newValue);
				return;
			case PivotPackage.PROPERTY__DEFAULT_VALUE_STRING:
				setDefaultValueString((String)newValue);
				return;
			case PivotPackage.PROPERTY__IS_COMPOSITE:
				setIsComposite((Boolean)newValue);
				return;
			case PivotPackage.PROPERTY__IS_DERIVED:
				setIsDerived((Boolean)newValue);
				return;
			case PivotPackage.PROPERTY__IS_ID:
				setIsID((Boolean)newValue);
				return;
			case PivotPackage.PROPERTY__IS_IMPLICIT:
				setIsImplicit((Boolean)newValue);
				return;
			case PivotPackage.PROPERTY__IS_READ_ONLY:
				setIsReadOnly((Boolean)newValue);
				return;
			case PivotPackage.PROPERTY__IS_RESOLVE_PROXIES:
				setIsResolveProxies((Boolean)newValue);
				return;
			case PivotPackage.PROPERTY__IS_TRANSIENT:
				setIsTransient((Boolean)newValue);
				return;
			case PivotPackage.PROPERTY__IS_UNSETTABLE:
				setIsUnsettable((Boolean)newValue);
				return;
			case PivotPackage.PROPERTY__IS_VOLATILE:
				setIsVolatile((Boolean)newValue);
				return;
			case PivotPackage.PROPERTY__KEYS:
				getKeys().clear();
				getKeys().addAll((Collection<? extends Property>)newValue);
				return;
			case PivotPackage.PROPERTY__OPPOSITE:
				setOpposite((Property)newValue);
				return;
			case PivotPackage.PROPERTY__OWNED_EXPRESSION:
				setOwnedExpression((LanguageExpression)newValue);
				return;
			case PivotPackage.PROPERTY__OWNING_CLASS:
				setOwningClass((org.eclipse.ocl.pivot.Class)newValue);
				return;
			case PivotPackage.PROPERTY__REDEFINED_PROPERTIES:
				getRedefinedProperties().clear();
				getRedefinedProperties().addAll((Collection<? extends Property>)newValue);
				return;
			case PivotPackage.PROPERTY__REFERRED_PROPERTY:
				setReferredProperty((Property)newValue);
				return;
			case PivotPackage.PROPERTY__SUBSETTED_PROPERTY:
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
	public void eUnset(int featureID) {
		switch (featureID)
		{
			case PivotPackage.PROPERTY__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.PROPERTY__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.PROPERTY__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.PROPERTY__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.PROPERTY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.PROPERTY__IS_REQUIRED:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case PivotPackage.PROPERTY__TYPE:
				setType((Type)null);
				return;
			case PivotPackage.PROPERTY__IMPLEMENTATION:
				setImplementation(IMPLEMENTATION_EDEFAULT);
				return;
			case PivotPackage.PROPERTY__IMPLEMENTATION_CLASS:
				setImplementationClass(IMPLEMENTATION_CLASS_EDEFAULT);
				return;
			case PivotPackage.PROPERTY__IS_STATIC:
				setIsStatic(IS_STATIC_EDEFAULT);
				return;
			case PivotPackage.PROPERTY__ASSOCIATION_CLASS:
				setAssociationClass((AssociationClass)null);
				return;
			case PivotPackage.PROPERTY__DEFAULT_VALUE:
				setDefaultValue(DEFAULT_VALUE_EDEFAULT);
				return;
			case PivotPackage.PROPERTY__DEFAULT_VALUE_STRING:
				setDefaultValueString(DEFAULT_VALUE_STRING_EDEFAULT);
				return;
			case PivotPackage.PROPERTY__IS_COMPOSITE:
				setIsComposite(IS_COMPOSITE_EDEFAULT);
				return;
			case PivotPackage.PROPERTY__IS_DERIVED:
				setIsDerived(IS_DERIVED_EDEFAULT);
				return;
			case PivotPackage.PROPERTY__IS_ID:
				setIsID(IS_ID_EDEFAULT);
				return;
			case PivotPackage.PROPERTY__IS_IMPLICIT:
				setIsImplicit(IS_IMPLICIT_EDEFAULT);
				return;
			case PivotPackage.PROPERTY__IS_READ_ONLY:
				setIsReadOnly(IS_READ_ONLY_EDEFAULT);
				return;
			case PivotPackage.PROPERTY__IS_RESOLVE_PROXIES:
				setIsResolveProxies(IS_RESOLVE_PROXIES_EDEFAULT);
				return;
			case PivotPackage.PROPERTY__IS_TRANSIENT:
				setIsTransient(IS_TRANSIENT_EDEFAULT);
				return;
			case PivotPackage.PROPERTY__IS_UNSETTABLE:
				setIsUnsettable(IS_UNSETTABLE_EDEFAULT);
				return;
			case PivotPackage.PROPERTY__IS_VOLATILE:
				setIsVolatile(IS_VOLATILE_EDEFAULT);
				return;
			case PivotPackage.PROPERTY__KEYS:
				getKeys().clear();
				return;
			case PivotPackage.PROPERTY__OPPOSITE:
				setOpposite((Property)null);
				return;
			case PivotPackage.PROPERTY__OWNED_EXPRESSION:
				setOwnedExpression((LanguageExpression)null);
				return;
			case PivotPackage.PROPERTY__OWNING_CLASS:
				setOwningClass((org.eclipse.ocl.pivot.Class)null);
				return;
			case PivotPackage.PROPERTY__REDEFINED_PROPERTIES:
				getRedefinedProperties().clear();
				return;
			case PivotPackage.PROPERTY__REFERRED_PROPERTY:
				setReferredProperty((Property)null);
				return;
			case PivotPackage.PROPERTY__SUBSETTED_PROPERTY:
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
	public boolean eIsSet(int featureID) {
		switch (featureID)
		{
			case PivotPackage.PROPERTY__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.PROPERTY__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.PROPERTY__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.PROPERTY__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.PROPERTY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.PROPERTY__IS_MANY:
				return isIsMany() != IS_MANY_EDEFAULT;
			case PivotPackage.PROPERTY__IS_REQUIRED:
				return ((eFlags & IS_REQUIRED_EFLAG) != 0) != IS_REQUIRED_EDEFAULT;
			case PivotPackage.PROPERTY__TYPE:
				return type != null;
			case PivotPackage.PROPERTY__IMPLEMENTATION:
				return IMPLEMENTATION_EDEFAULT == null ? implementation != null : !IMPLEMENTATION_EDEFAULT.equals(implementation);
			case PivotPackage.PROPERTY__IMPLEMENTATION_CLASS:
				return IMPLEMENTATION_CLASS_EDEFAULT == null ? implementationClass != null : !IMPLEMENTATION_CLASS_EDEFAULT.equals(implementationClass);
			case PivotPackage.PROPERTY__IS_STATIC:
				return ((eFlags & IS_STATIC_EFLAG) != 0) != IS_STATIC_EDEFAULT;
			case PivotPackage.PROPERTY__ASSOCIATION_CLASS:
				return associationClass != null;
			case PivotPackage.PROPERTY__DEFAULT_VALUE:
				return DEFAULT_VALUE_EDEFAULT == null ? defaultValue != null : !DEFAULT_VALUE_EDEFAULT.equals(defaultValue);
			case PivotPackage.PROPERTY__DEFAULT_VALUE_STRING:
				return DEFAULT_VALUE_STRING_EDEFAULT == null ? defaultValueString != null : !DEFAULT_VALUE_STRING_EDEFAULT.equals(defaultValueString);
			case PivotPackage.PROPERTY__IS_COMPOSITE:
				return ((eFlags & IS_COMPOSITE_EFLAG) != 0) != IS_COMPOSITE_EDEFAULT;
			case PivotPackage.PROPERTY__IS_DERIVED:
				return ((eFlags & IS_DERIVED_EFLAG) != 0) != IS_DERIVED_EDEFAULT;
			case PivotPackage.PROPERTY__IS_ID:
				return ((eFlags & IS_ID_EFLAG) != 0) != IS_ID_EDEFAULT;
			case PivotPackage.PROPERTY__IS_IMPLICIT:
				return ((eFlags & IS_IMPLICIT_EFLAG) != 0) != IS_IMPLICIT_EDEFAULT;
			case PivotPackage.PROPERTY__IS_READ_ONLY:
				return ((eFlags & IS_READ_ONLY_EFLAG) != 0) != IS_READ_ONLY_EDEFAULT;
			case PivotPackage.PROPERTY__IS_RESOLVE_PROXIES:
				return ((eFlags & IS_RESOLVE_PROXIES_EFLAG) != 0) != IS_RESOLVE_PROXIES_EDEFAULT;
			case PivotPackage.PROPERTY__IS_TRANSIENT:
				return ((eFlags & IS_TRANSIENT_EFLAG) != 0) != IS_TRANSIENT_EDEFAULT;
			case PivotPackage.PROPERTY__IS_UNSETTABLE:
				return ((eFlags & IS_UNSETTABLE_EFLAG) != 0) != IS_UNSETTABLE_EDEFAULT;
			case PivotPackage.PROPERTY__IS_VOLATILE:
				return ((eFlags & IS_VOLATILE_EFLAG) != 0) != IS_VOLATILE_EDEFAULT;
			case PivotPackage.PROPERTY__KEYS:
				return keys != null && !keys.isEmpty();
			case PivotPackage.PROPERTY__OPPOSITE:
				return opposite != null;
			case PivotPackage.PROPERTY__OWNED_EXPRESSION:
				return ownedExpression != null;
			case PivotPackage.PROPERTY__OWNING_CLASS:
				return getOwningClass() != null;
			case PivotPackage.PROPERTY__REDEFINED_PROPERTIES:
				return redefinedProperties != null && !redefinedProperties.isEmpty();
			case PivotPackage.PROPERTY__REFERRED_PROPERTY:
				return referredProperty != null;
			case PivotPackage.PROPERTY__SUBSETTED_PROPERTY:
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
	@SuppressWarnings("unchecked")
	public Object eInvoke(int operationID, EList<?> arguments)
			throws InvocationTargetException {
		switch (operationID)
		{
			case PivotPackage.PROPERTY___ALL_OWNED_ELEMENTS:
				return allOwnedElements();
			case PivotPackage.PROPERTY___GET_VALUE__TYPE_STRING:
				return getValue((Type)arguments.get(0), (String)arguments.get(1));
			case PivotPackage.PROPERTY___COMPATIBLE_BODY__VALUESPECIFICATION:
				return CompatibleBody((ValueSpecification)arguments.get(0));
			case PivotPackage.PROPERTY___VALIDATE_NAME_IS_NOT_NULL__DIAGNOSTICCHAIN_MAP:
				return validateNameIsNotNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.PROPERTY___VALIDATE_TYPE_IS_NOT_INVALID__DIAGNOSTICCHAIN_MAP:
				return validateTypeIsNotInvalid((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.PROPERTY___VALIDATE_TYPE_IS_NOT_NULL__DIAGNOSTICCHAIN_MAP:
				return validateTypeIsNotNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.PROPERTY___IS_ATTRIBUTE__PROPERTY:
				return isAttribute((Property)arguments.get(0));
			case PivotPackage.PROPERTY___VALIDATE_COMPATIBLE_DEFAULT_EXPRESSION__DIAGNOSTICCHAIN_MAP:
				return validateCompatibleDefaultExpression((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
		}
		return eDynamicInvoke(operationID, arguments);
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
		return visitor.visitProperty(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.eclipse.ocl.pivot.Class getOwningClass() {
		if (eContainerFeatureID() != PivotPackage.PROPERTY__OWNING_CLASS) return null;
		return (org.eclipse.ocl.pivot.Class)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningClass(org.eclipse.ocl.pivot.Class newOwningClass, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningClass, PivotPackage.PROPERTY__OWNING_CLASS, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningClass(org.eclipse.ocl.pivot.Class newOwningClass)
	{
		if (newOwningClass != eInternalContainer() || (eContainerFeatureID() != PivotPackage.PROPERTY__OWNING_CLASS && newOwningClass != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningClass))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningClass != null)
				msgs = ((InternalEObject)newOwningClass).eInverseAdd(this, PivotPackage.CLASS__OWNED_PROPERTIES, org.eclipse.ocl.pivot.Class.class, msgs);
			msgs = basicSetOwningClass(newOwningClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PROPERTY__OWNING_CLASS, newOwningClass, newOwningClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("null")
	@Override
	public @NonNull List<Property> getRedefinedProperties()
	{
		if (redefinedProperties == null)
		{
			redefinedProperties = new EObjectResolvingEList<Property>(Property.class, this, PivotPackage.PROPERTY__REDEFINED_PROPERTIES);
		}
		return redefinedProperties;
	}

	@Override
	public @Nullable CompleteInheritance getInheritance(@NonNull StandardLibrary standardLibrary) {
		org.eclipse.ocl.pivot.Class owningType = getOwningClass();
		if (owningType != null) {
			return standardLibrary.getInheritance(owningType);
		}
		else {
			return null;
		}
	}

	private PropertyId propertyId = null;

	@Override
	public @NonNull PropertyId getPropertyId() {
		PropertyId propertyId2 = propertyId;
		if (propertyId2 == null) {
			synchronized (this) {
				propertyId2 = propertyId;
				if (propertyId2 == null) {
					String name = ClassUtil.nonNullModel(getName());
					TypeId typeId = getOwningClass().getTypeId();
					propertyId = propertyId2 = typeId.getPropertyId(name);
				}
			}
		}
		return propertyId2;
	}

	@Override
	public void initValue(@NonNull Object objectValue, @Nullable Object ecoreValue) {
		assert ValueUtil.isEcore(ecoreValue);
		EObject eTarget = getESObject();
		if (eTarget instanceof EStructuralFeature) {
			EStructuralFeature eFeature = (EStructuralFeature) eTarget;
			EObject eObject = ValueUtil.asNavigableObject(objectValue, eFeature, null);
			eObject.eSet(eFeature, ecoreValue);
			return;
		}
		throw new UnsupportedOperationException();
	}

	@Override
	public void unloaded(@NonNull ASResource asResource) {
		super.unloaded(asResource);
		Property asOpposite = getOpposite();
		if (asOpposite != null) {
			Resource eResource = asOpposite.eResource();
			if ((eResource != null) && (eResource != asResource)) {
				asOpposite.setOwningClass(null);
			}
			asOpposite.setType(null);
			asOpposite.setOpposite(null);
			setOpposite(null);
		}
		setType(null);				// Easier to set them all than just the base_xxx ones
	}
} //PropertyImpl
