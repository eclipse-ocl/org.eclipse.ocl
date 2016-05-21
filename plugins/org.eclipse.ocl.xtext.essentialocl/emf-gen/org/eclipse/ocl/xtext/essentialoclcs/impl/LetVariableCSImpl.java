/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.essentialoclcs.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.NamedElementCS;
import org.eclipse.ocl.xtext.basecs.TypedRefCS;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;
import org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage;
import org.eclipse.ocl.xtext.essentialoclcs.ExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.LetExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.LetVariableCS;
import org.eclipse.ocl.xtext.essentialoclcs.RoundBracketedClauseCS;
import org.eclipse.ocl.xtext.essentialoclcs.VariableCS;
import org.eclipse.ocl.xtext.essentialoclcs.util.EssentialOCLCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variable CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.LetVariableCSImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.LetVariableCSImpl#getOwnedInitExpression <em>Owned Init Expression</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.LetVariableCSImpl#getOwnedType <em>Owned Type</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.LetVariableCSImpl#getOwnedRoundBracketedClause <em>Owned Round Bracketed Clause</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.LetVariableCSImpl#getOwningLetExpression <em>Owning Let Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LetVariableCSImpl
		extends ExpCSImpl
		implements LetVariableCS {

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;
	/**
	 * The cached value of the '{@link #getOwnedInitExpression() <em>Owned Init Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedInitExpression()
	 * @generated
	 * @ordered
	 */
	protected ExpCS ownedInitExpression;
	/**
	 * The cached value of the '{@link #getOwnedType() <em>Owned Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedType()
	 * @generated
	 * @ordered
	 */
	protected TypedRefCS ownedType;
	/**
	 * The cached value of the '{@link #getOwnedRoundBracketedClause() <em>Owned Round Bracketed Clause</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedRoundBracketedClause()
	 * @generated
	 * @ordered
	 */
	protected RoundBracketedClauseCS ownedRoundBracketedClause;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LetVariableCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EssentialOCLCSPackage.Literals.LET_VARIABLE_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName()
	{
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName)
	{
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.LET_VARIABLE_CS__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TypedRefCS getOwnedType()
	{
		return ownedType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedType(TypedRefCS newOwnedType, NotificationChain msgs)
	{
		TypedRefCS oldOwnedType = ownedType;
		ownedType = newOwnedType;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_TYPE, oldOwnedType, newOwnedType);
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
	public void setOwnedType(TypedRefCS newOwnedType)
	{
		if (newOwnedType != ownedType)
		{
			NotificationChain msgs = null;
			if (ownedType != null)
				msgs = ((InternalEObject)ownedType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_TYPE, null, msgs);
			if (newOwnedType != null)
				msgs = ((InternalEObject)newOwnedType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_TYPE, null, msgs);
			msgs = basicSetOwnedType(newOwnedType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_TYPE, newOwnedType, newOwnedType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExpCS getOwnedInitExpression()
	{
		return ownedInitExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedInitExpression(ExpCS newOwnedInitExpression, NotificationChain msgs)
	{
		ExpCS oldOwnedInitExpression = ownedInitExpression;
		ownedInitExpression = newOwnedInitExpression;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_INIT_EXPRESSION, oldOwnedInitExpression, newOwnedInitExpression);
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
	public void setOwnedInitExpression(ExpCS newOwnedInitExpression)
	{
		if (newOwnedInitExpression != ownedInitExpression)
		{
			NotificationChain msgs = null;
			if (ownedInitExpression != null)
				msgs = ((InternalEObject)ownedInitExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_INIT_EXPRESSION, null, msgs);
			if (newOwnedInitExpression != null)
				msgs = ((InternalEObject)newOwnedInitExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_INIT_EXPRESSION, null, msgs);
			msgs = basicSetOwnedInitExpression(newOwnedInitExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_INIT_EXPRESSION, newOwnedInitExpression, newOwnedInitExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LetExpCS getOwningLetExpression() {
		if (eContainerFeatureID() != EssentialOCLCSPackage.LET_VARIABLE_CS__OWNING_LET_EXPRESSION) return null;
		return (LetExpCS)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningLetExpression(LetExpCS newOwningLetExpression, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningLetExpression, EssentialOCLCSPackage.LET_VARIABLE_CS__OWNING_LET_EXPRESSION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningLetExpression(LetExpCS newOwningLetExpression) {
		if (newOwningLetExpression != eInternalContainer() || (eContainerFeatureID() != EssentialOCLCSPackage.LET_VARIABLE_CS__OWNING_LET_EXPRESSION && newOwningLetExpression != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningLetExpression))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningLetExpression != null)
				msgs = ((InternalEObject)newOwningLetExpression).eInverseAdd(this, EssentialOCLCSPackage.LET_EXP_CS__OWNED_VARIABLES, LetExpCS.class, msgs);
			msgs = basicSetOwningLetExpression(newOwningLetExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.LET_VARIABLE_CS__OWNING_LET_EXPRESSION, newOwningLetExpression, newOwningLetExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RoundBracketedClauseCS getOwnedRoundBracketedClause()
	{
		return ownedRoundBracketedClause;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedRoundBracketedClause(RoundBracketedClauseCS newOwnedRoundBracketedClause, NotificationChain msgs)
	{
		RoundBracketedClauseCS oldOwnedRoundBracketedClause = ownedRoundBracketedClause;
		ownedRoundBracketedClause = newOwnedRoundBracketedClause;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, oldOwnedRoundBracketedClause, newOwnedRoundBracketedClause);
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
	public void setOwnedRoundBracketedClause(RoundBracketedClauseCS newOwnedRoundBracketedClause)
	{
		if (newOwnedRoundBracketedClause != ownedRoundBracketedClause)
		{
			NotificationChain msgs = null;
			if (ownedRoundBracketedClause != null)
				msgs = ((InternalEObject)ownedRoundBracketedClause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, null, msgs);
			if (newOwnedRoundBracketedClause != null)
				msgs = ((InternalEObject)newOwnedRoundBracketedClause).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, null, msgs);
			msgs = basicSetOwnedRoundBracketedClause(newOwnedRoundBracketedClause, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, newOwnedRoundBracketedClause, newOwnedRoundBracketedClause));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID)
		{
			case EssentialOCLCSPackage.LET_VARIABLE_CS__OWNING_LET_EXPRESSION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningLetExpression((LetExpCS)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
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
			case EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_INIT_EXPRESSION:
				return basicSetOwnedInitExpression(null, msgs);
			case EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_TYPE:
				return basicSetOwnedType(null, msgs);
			case EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE:
				return basicSetOwnedRoundBracketedClause(null, msgs);
			case EssentialOCLCSPackage.LET_VARIABLE_CS__OWNING_LET_EXPRESSION:
				return basicSetOwningLetExpression(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
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
			case EssentialOCLCSPackage.LET_VARIABLE_CS__OWNING_LET_EXPRESSION:
				return eInternalContainer().eInverseRemove(this, EssentialOCLCSPackage.LET_EXP_CS__OWNED_VARIABLES, LetExpCS.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
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
			case EssentialOCLCSPackage.LET_VARIABLE_CS__NAME:
				return getName();
			case EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_INIT_EXPRESSION:
				return getOwnedInitExpression();
			case EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_TYPE:
				return getOwnedType();
			case EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE:
				return getOwnedRoundBracketedClause();
			case EssentialOCLCSPackage.LET_VARIABLE_CS__OWNING_LET_EXPRESSION:
				return getOwningLetExpression();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID)
		{
			case EssentialOCLCSPackage.LET_VARIABLE_CS__NAME:
				setName((String)newValue);
				return;
			case EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_INIT_EXPRESSION:
				setOwnedInitExpression((ExpCS)newValue);
				return;
			case EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_TYPE:
				setOwnedType((TypedRefCS)newValue);
				return;
			case EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE:
				setOwnedRoundBracketedClause((RoundBracketedClauseCS)newValue);
				return;
			case EssentialOCLCSPackage.LET_VARIABLE_CS__OWNING_LET_EXPRESSION:
				setOwningLetExpression((LetExpCS)newValue);
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
	public void eUnset(int featureID) {
		switch (featureID)
		{
			case EssentialOCLCSPackage.LET_VARIABLE_CS__NAME:
				setName(NAME_EDEFAULT);
				return;
			case EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_INIT_EXPRESSION:
				setOwnedInitExpression((ExpCS)null);
				return;
			case EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_TYPE:
				setOwnedType((TypedRefCS)null);
				return;
			case EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE:
				setOwnedRoundBracketedClause((RoundBracketedClauseCS)null);
				return;
			case EssentialOCLCSPackage.LET_VARIABLE_CS__OWNING_LET_EXPRESSION:
				setOwningLetExpression((LetExpCS)null);
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
	public boolean eIsSet(int featureID) {
		switch (featureID)
		{
			case EssentialOCLCSPackage.LET_VARIABLE_CS__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_INIT_EXPRESSION:
				return ownedInitExpression != null;
			case EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_TYPE:
				return ownedType != null;
			case EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE:
				return ownedRoundBracketedClause != null;
			case EssentialOCLCSPackage.LET_VARIABLE_CS__OWNING_LET_EXPRESSION:
				return getOwningLetExpression() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Nameable.class)
		{
			switch (derivedFeatureID)
			{
				default: return -1;
			}
		}
		if (baseClass == NamedElementCS.class)
		{
			switch (derivedFeatureID)
			{
				case EssentialOCLCSPackage.LET_VARIABLE_CS__NAME: return BaseCSPackage.NAMED_ELEMENT_CS__NAME;
				default: return -1;
			}
		}
		if (baseClass == VariableCS.class)
		{
			switch (derivedFeatureID)
			{
				case EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_INIT_EXPRESSION: return EssentialOCLCSPackage.VARIABLE_CS__OWNED_INIT_EXPRESSION;
				case EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_TYPE: return EssentialOCLCSPackage.VARIABLE_CS__OWNED_TYPE;
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
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == Nameable.class)
		{
			switch (baseFeatureID)
			{
				default: return -1;
			}
		}
		if (baseClass == NamedElementCS.class)
		{
			switch (baseFeatureID)
			{
				case BaseCSPackage.NAMED_ELEMENT_CS__NAME: return EssentialOCLCSPackage.LET_VARIABLE_CS__NAME;
				default: return -1;
			}
		}
		if (baseClass == VariableCS.class)
		{
			switch (baseFeatureID)
			{
				case EssentialOCLCSPackage.VARIABLE_CS__OWNED_INIT_EXPRESSION: return EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_INIT_EXPRESSION;
				case EssentialOCLCSPackage.VARIABLE_CS__OWNED_TYPE: return EssentialOCLCSPackage.LET_VARIABLE_CS__OWNED_TYPE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <R> R accept(@NonNull BaseCSVisitor<R> visitor) {
		return (R) ((EssentialOCLCSVisitor<?>)visitor).visitLetVariableCS(this);
	}
	
	@SuppressWarnings("cast")
	@Override
	public void resetPivot() {
		assert this instanceof ExpCSImpl;	// Enforce correct ordering of base classes
		super.resetPivot();
	}

	@Override
	public String toString() {
		return super.toString();
	}
} //VariableCSImpl
