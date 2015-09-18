/**
 * Copyright (c) 2010, 2015 Willink Transformations and others.
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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jdt.annotation.NonNull;

import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.IfPatternExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.PatternExp;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Type;

import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>If Pattern Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.IfPatternExpImpl#getOwnedPattern <em>Owned Pattern</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.IfPatternExpImpl#getOwnedSource <em>Owned Source</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IfPatternExpImpl extends AbstractIfExpImpl implements IfPatternExp
{
	/**
	 * The cached value of the '{@link #getOwnedPattern() <em>Owned Pattern</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedPattern()
	 * @generated
	 * @ordered
	 */
	protected PatternExp ownedPattern;

	/**
	 * The cached value of the '{@link #getOwnedSource() <em>Owned Source</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSource()
	 * @generated
	 * @ordered
	 */
	protected OCLExpression ownedSource;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IfPatternExpImpl()
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
		return PivotPackage.Literals.IF_PATTERN_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PatternExp getOwnedPattern()
	{
		return ownedPattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedPattern(PatternExp newOwnedPattern, NotificationChain msgs)
	{
		PatternExp oldOwnedPattern = ownedPattern;
		ownedPattern = newOwnedPattern;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.IF_PATTERN_EXP__OWNED_PATTERN, oldOwnedPattern, newOwnedPattern);
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
	public void setOwnedPattern(PatternExp newOwnedPattern)
	{
		if (newOwnedPattern != ownedPattern)
		{
			NotificationChain msgs = null;
			if (ownedPattern != null)
				msgs = ((InternalEObject)ownedPattern).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.IF_PATTERN_EXP__OWNED_PATTERN, null, msgs);
			if (newOwnedPattern != null)
				msgs = ((InternalEObject)newOwnedPattern).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PivotPackage.IF_PATTERN_EXP__OWNED_PATTERN, null, msgs);
			msgs = basicSetOwnedPattern(newOwnedPattern, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.IF_PATTERN_EXP__OWNED_PATTERN, newOwnedPattern, newOwnedPattern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OCLExpression getOwnedSource()
	{
		return ownedSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedSource(OCLExpression newOwnedSource, NotificationChain msgs)
	{
		OCLExpression oldOwnedSource = ownedSource;
		ownedSource = newOwnedSource;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.IF_PATTERN_EXP__OWNED_SOURCE, oldOwnedSource, newOwnedSource);
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
	public void setOwnedSource(OCLExpression newOwnedSource)
	{
		if (newOwnedSource != ownedSource)
		{
			NotificationChain msgs = null;
			if (ownedSource != null)
				msgs = ((InternalEObject)ownedSource).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.IF_PATTERN_EXP__OWNED_SOURCE, null, msgs);
			if (newOwnedSource != null)
				msgs = ((InternalEObject)newOwnedSource).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PivotPackage.IF_PATTERN_EXP__OWNED_SOURCE, null, msgs);
			msgs = basicSetOwnedSource(newOwnedSource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.IF_PATTERN_EXP__OWNED_SOURCE, newOwnedSource, newOwnedSource));
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
			case PivotPackage.IF_PATTERN_EXP__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.IF_PATTERN_EXP__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.IF_PATTERN_EXP__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.IF_PATTERN_EXP__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.IF_PATTERN_EXP__OWNED_ELSE:
				return basicSetOwnedElse(null, msgs);
			case PivotPackage.IF_PATTERN_EXP__OWNED_THEN:
				return basicSetOwnedThen(null, msgs);
			case PivotPackage.IF_PATTERN_EXP__OWNED_PATTERN:
				return basicSetOwnedPattern(null, msgs);
			case PivotPackage.IF_PATTERN_EXP__OWNED_SOURCE:
				return basicSetOwnedSource(null, msgs);
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
			case PivotPackage.IF_PATTERN_EXP__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.IF_PATTERN_EXP__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.IF_PATTERN_EXP__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.IF_PATTERN_EXP__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.IF_PATTERN_EXP__NAME:
				return getName();
			case PivotPackage.IF_PATTERN_EXP__IS_MANY:
				return isIsMany();
			case PivotPackage.IF_PATTERN_EXP__IS_REQUIRED:
				return isIsRequired();
			case PivotPackage.IF_PATTERN_EXP__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case PivotPackage.IF_PATTERN_EXP__TYPE_VALUE:
				return getTypeValue();
			case PivotPackage.IF_PATTERN_EXP__IS_ELSE_IF:
				return isIsElseIf();
			case PivotPackage.IF_PATTERN_EXP__OWNED_ELSE:
				return getOwnedElse();
			case PivotPackage.IF_PATTERN_EXP__OWNED_THEN:
				return getOwnedThen();
			case PivotPackage.IF_PATTERN_EXP__OWNED_PATTERN:
				return getOwnedPattern();
			case PivotPackage.IF_PATTERN_EXP__OWNED_SOURCE:
				return getOwnedSource();
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
			case PivotPackage.IF_PATTERN_EXP__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.IF_PATTERN_EXP__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.IF_PATTERN_EXP__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.IF_PATTERN_EXP__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.IF_PATTERN_EXP__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.IF_PATTERN_EXP__IS_REQUIRED:
				setIsRequired((Boolean)newValue);
				return;
			case PivotPackage.IF_PATTERN_EXP__TYPE:
				setType((Type)newValue);
				return;
			case PivotPackage.IF_PATTERN_EXP__TYPE_VALUE:
				setTypeValue((Type)newValue);
				return;
			case PivotPackage.IF_PATTERN_EXP__IS_ELSE_IF:
				setIsElseIf((Boolean)newValue);
				return;
			case PivotPackage.IF_PATTERN_EXP__OWNED_ELSE:
				setOwnedElse((OCLExpression)newValue);
				return;
			case PivotPackage.IF_PATTERN_EXP__OWNED_THEN:
				setOwnedThen((OCLExpression)newValue);
				return;
			case PivotPackage.IF_PATTERN_EXP__OWNED_PATTERN:
				setOwnedPattern((PatternExp)newValue);
				return;
			case PivotPackage.IF_PATTERN_EXP__OWNED_SOURCE:
				setOwnedSource((OCLExpression)newValue);
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
			case PivotPackage.IF_PATTERN_EXP__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.IF_PATTERN_EXP__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.IF_PATTERN_EXP__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.IF_PATTERN_EXP__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.IF_PATTERN_EXP__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.IF_PATTERN_EXP__IS_REQUIRED:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case PivotPackage.IF_PATTERN_EXP__TYPE:
				setType((Type)null);
				return;
			case PivotPackage.IF_PATTERN_EXP__TYPE_VALUE:
				setTypeValue((Type)null);
				return;
			case PivotPackage.IF_PATTERN_EXP__IS_ELSE_IF:
				setIsElseIf(IS_ELSE_IF_EDEFAULT);
				return;
			case PivotPackage.IF_PATTERN_EXP__OWNED_ELSE:
				setOwnedElse((OCLExpression)null);
				return;
			case PivotPackage.IF_PATTERN_EXP__OWNED_THEN:
				setOwnedThen((OCLExpression)null);
				return;
			case PivotPackage.IF_PATTERN_EXP__OWNED_PATTERN:
				setOwnedPattern((PatternExp)null);
				return;
			case PivotPackage.IF_PATTERN_EXP__OWNED_SOURCE:
				setOwnedSource((OCLExpression)null);
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
			case PivotPackage.IF_PATTERN_EXP__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.IF_PATTERN_EXP__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.IF_PATTERN_EXP__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.IF_PATTERN_EXP__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.IF_PATTERN_EXP__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.IF_PATTERN_EXP__IS_MANY:
				return isIsMany() != IS_MANY_EDEFAULT;
			case PivotPackage.IF_PATTERN_EXP__IS_REQUIRED:
				return ((eFlags & IS_REQUIRED_EFLAG) != 0) != IS_REQUIRED_EDEFAULT;
			case PivotPackage.IF_PATTERN_EXP__TYPE:
				return type != null;
			case PivotPackage.IF_PATTERN_EXP__TYPE_VALUE:
				return typeValue != null;
			case PivotPackage.IF_PATTERN_EXP__IS_ELSE_IF:
				return ((eFlags & IS_ELSE_IF_EFLAG) != 0) != IS_ELSE_IF_EDEFAULT;
			case PivotPackage.IF_PATTERN_EXP__OWNED_ELSE:
				return ownedElse != null;
			case PivotPackage.IF_PATTERN_EXP__OWNED_THEN:
				return ownedThen != null;
			case PivotPackage.IF_PATTERN_EXP__OWNED_PATTERN:
				return ownedPattern != null;
			case PivotPackage.IF_PATTERN_EXP__OWNED_SOURCE:
				return ownedSource != null;
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitIfPatternExp(this);
	}

} //IfPatternExpImpl
