/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
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
import org.eclipse.ocl.xtext.basecs.NamedElementCS;
import org.eclipse.ocl.xtext.basecs.TypedRefCS;
import org.eclipse.ocl.xtext.basecs.impl.ModelElementCSImpl;
import org.eclipse.ocl.xtext.basecs.impl.NamedElementCSImpl;
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
	 * The number of structural features of the '<em>Let Variable CS</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LET_VARIABLE_CS_FEATURE_COUNT = ExpCSImpl.EXP_CS_FEATURE_COUNT + 5;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ExpCSImpl.EXP_CS_FEATURE_COUNT + 0, oldName, name));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExpCSImpl.EXP_CS_FEATURE_COUNT + 2, oldOwnedType, newOwnedType);
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
				msgs = ((InternalEObject)ownedType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (ExpCSImpl.EXP_CS_FEATURE_COUNT + 2), null, msgs);
			if (newOwnedType != null)
				msgs = ((InternalEObject)newOwnedType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (ExpCSImpl.EXP_CS_FEATURE_COUNT + 2), null, msgs);
			msgs = basicSetOwnedType(newOwnedType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpCSImpl.EXP_CS_FEATURE_COUNT + 2, newOwnedType, newOwnedType));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExpCSImpl.EXP_CS_FEATURE_COUNT + 1, oldOwnedInitExpression, newOwnedInitExpression);
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
				msgs = ((InternalEObject)ownedInitExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (ExpCSImpl.EXP_CS_FEATURE_COUNT + 1), null, msgs);
			if (newOwnedInitExpression != null)
				msgs = ((InternalEObject)newOwnedInitExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (ExpCSImpl.EXP_CS_FEATURE_COUNT + 1), null, msgs);
			msgs = basicSetOwnedInitExpression(newOwnedInitExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpCSImpl.EXP_CS_FEATURE_COUNT + 1, newOwnedInitExpression, newOwnedInitExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LetExpCS getOwningLetExpression() {
		if (eContainerFeatureID() != (ExpCSImpl.EXP_CS_FEATURE_COUNT + 4)) return null;
		return (LetExpCS)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningLetExpression(LetExpCS newOwningLetExpression, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningLetExpression, ExpCSImpl.EXP_CS_FEATURE_COUNT + 4, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningLetExpression(LetExpCS newOwningLetExpression) {
		if (newOwningLetExpression != eInternalContainer() || (eContainerFeatureID() != (ExpCSImpl.EXP_CS_FEATURE_COUNT + 4) && newOwningLetExpression != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningLetExpression))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningLetExpression != null)
				msgs = ((InternalEObject)newOwningLetExpression).eInverseAdd(this, ExpCSImpl.EXP_CS_FEATURE_COUNT + 2, LetExpCS.class, msgs);
			msgs = basicSetOwningLetExpression(newOwningLetExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpCSImpl.EXP_CS_FEATURE_COUNT + 4, newOwningLetExpression, newOwningLetExpression));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExpCSImpl.EXP_CS_FEATURE_COUNT + 3, oldOwnedRoundBracketedClause, newOwnedRoundBracketedClause);
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
				msgs = ((InternalEObject)ownedRoundBracketedClause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (ExpCSImpl.EXP_CS_FEATURE_COUNT + 3), null, msgs);
			if (newOwnedRoundBracketedClause != null)
				msgs = ((InternalEObject)newOwnedRoundBracketedClause).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (ExpCSImpl.EXP_CS_FEATURE_COUNT + 3), null, msgs);
			msgs = basicSetOwnedRoundBracketedClause(newOwnedRoundBracketedClause, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExpCSImpl.EXP_CS_FEATURE_COUNT + 3, newOwnedRoundBracketedClause, newOwnedRoundBracketedClause));
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
			case ExpCSImpl.EXP_CS_FEATURE_COUNT + 4:
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
			case ExpCSImpl.EXP_CS_FEATURE_COUNT + 1:
				return basicSetOwnedInitExpression(null, msgs);
			case ExpCSImpl.EXP_CS_FEATURE_COUNT + 2:
				return basicSetOwnedType(null, msgs);
			case ExpCSImpl.EXP_CS_FEATURE_COUNT + 3:
				return basicSetOwnedRoundBracketedClause(null, msgs);
			case ExpCSImpl.EXP_CS_FEATURE_COUNT + 4:
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
			case ExpCSImpl.EXP_CS_FEATURE_COUNT + 4:
				return eInternalContainer().eInverseRemove(this, ExpCSImpl.EXP_CS_FEATURE_COUNT + 2, LetExpCS.class, msgs);
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
			case ExpCSImpl.EXP_CS_FEATURE_COUNT + 0:
				return getName();
			case ExpCSImpl.EXP_CS_FEATURE_COUNT + 1:
				return getOwnedInitExpression();
			case ExpCSImpl.EXP_CS_FEATURE_COUNT + 2:
				return getOwnedType();
			case ExpCSImpl.EXP_CS_FEATURE_COUNT + 3:
				return getOwnedRoundBracketedClause();
			case ExpCSImpl.EXP_CS_FEATURE_COUNT + 4:
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
			case ExpCSImpl.EXP_CS_FEATURE_COUNT + 0:
				setName((String)newValue);
				return;
			case ExpCSImpl.EXP_CS_FEATURE_COUNT + 1:
				setOwnedInitExpression((ExpCS)newValue);
				return;
			case ExpCSImpl.EXP_CS_FEATURE_COUNT + 2:
				setOwnedType((TypedRefCS)newValue);
				return;
			case ExpCSImpl.EXP_CS_FEATURE_COUNT + 3:
				setOwnedRoundBracketedClause((RoundBracketedClauseCS)newValue);
				return;
			case ExpCSImpl.EXP_CS_FEATURE_COUNT + 4:
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
			case ExpCSImpl.EXP_CS_FEATURE_COUNT + 0:
				setName(NAME_EDEFAULT);
				return;
			case ExpCSImpl.EXP_CS_FEATURE_COUNT + 1:
				setOwnedInitExpression((ExpCS)null);
				return;
			case ExpCSImpl.EXP_CS_FEATURE_COUNT + 2:
				setOwnedType((TypedRefCS)null);
				return;
			case ExpCSImpl.EXP_CS_FEATURE_COUNT + 3:
				setOwnedRoundBracketedClause((RoundBracketedClauseCS)null);
				return;
			case ExpCSImpl.EXP_CS_FEATURE_COUNT + 4:
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
			case ExpCSImpl.EXP_CS_FEATURE_COUNT + 0:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ExpCSImpl.EXP_CS_FEATURE_COUNT + 1:
				return ownedInitExpression != null;
			case ExpCSImpl.EXP_CS_FEATURE_COUNT + 2:
				return ownedType != null;
			case ExpCSImpl.EXP_CS_FEATURE_COUNT + 3:
				return ownedRoundBracketedClause != null;
			case ExpCSImpl.EXP_CS_FEATURE_COUNT + 4:
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
		if (baseClass == NamedElementCS.class)
		{
			switch (derivedFeatureID)
			{
				case ExpCSImpl.EXP_CS_FEATURE_COUNT + 0: return ModelElementCSImpl.MODEL_ELEMENT_CS_FEATURE_COUNT + 0;
				default: return -1;
			}
		}
		if (baseClass == VariableCS.class)
		{
			switch (derivedFeatureID)
			{
				case ExpCSImpl.EXP_CS_FEATURE_COUNT + 1: return NamedElementCSImpl.NAMED_ELEMENT_CS_FEATURE_COUNT + 0;
				case ExpCSImpl.EXP_CS_FEATURE_COUNT + 2: return NamedElementCSImpl.NAMED_ELEMENT_CS_FEATURE_COUNT + 1;
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
		if (baseClass == NamedElementCS.class)
		{
			switch (baseFeatureID)
			{
				case ModelElementCSImpl.MODEL_ELEMENT_CS_FEATURE_COUNT + 0: return ExpCSImpl.EXP_CS_FEATURE_COUNT + 0;
				default: return -1;
			}
		}
		if (baseClass == VariableCS.class)
		{
			switch (baseFeatureID)
			{
				case NamedElementCSImpl.NAMED_ELEMENT_CS_FEATURE_COUNT + 0: return ExpCSImpl.EXP_CS_FEATURE_COUNT + 1;
				case NamedElementCSImpl.NAMED_ELEMENT_CS_FEATURE_COUNT + 1: return ExpCSImpl.EXP_CS_FEATURE_COUNT + 2;
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
