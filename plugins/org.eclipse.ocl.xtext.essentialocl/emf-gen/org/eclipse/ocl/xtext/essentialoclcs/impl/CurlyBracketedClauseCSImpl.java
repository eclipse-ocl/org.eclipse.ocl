/*******************************************************************************
 * Copyright (c) 2014, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.essentialoclcs.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.basecs.impl.ContextLessElementCSImpl;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;
import org.eclipse.ocl.xtext.essentialoclcs.AbstractNameExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.ShadowPartCS;
import org.eclipse.ocl.xtext.essentialoclcs.CurlyBracketedClauseCS;
import org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage;
import org.eclipse.ocl.xtext.essentialoclcs.util.EssentialOCLCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Curly Bracketed Clause CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.CurlyBracketedClauseCSImpl#getOwnedParts <em>Owned Parts</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.CurlyBracketedClauseCSImpl#getOwningNameExp <em>Owning Name Exp</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.CurlyBracketedClauseCSImpl#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CurlyBracketedClauseCSImpl extends ContextLessElementCSImpl implements CurlyBracketedClauseCS
{
	/**
	 * The cached value of the '{@link #getOwnedParts() <em>Owned Parts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedParts()
	 * @generated
	 * @ordered
	 */
	protected EList<ShadowPartCS> ownedParts;

	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected String value = VALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CurlyBracketedClauseCSImpl()
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
		return EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AbstractNameExpCS getOwningNameExp()
	{
		if (eContainerFeatureID() != EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS__OWNING_NAME_EXP) return null;
		return (AbstractNameExpCS)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningNameExp(AbstractNameExpCS newOwningNameExp, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningNameExp, EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS__OWNING_NAME_EXP, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningNameExp(AbstractNameExpCS newOwningNameExp)
	{
		if (newOwningNameExp != eInternalContainer() || (eContainerFeatureID() != EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS__OWNING_NAME_EXP && newOwningNameExp != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningNameExp))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningNameExp != null)
				msgs = ((InternalEObject)newOwningNameExp).eInverseAdd(this, EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, AbstractNameExpCS.class, msgs);
			msgs = basicSetOwningNameExp(newOwningNameExp, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS__OWNING_NAME_EXP, newOwningNameExp, newOwningNameExp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ShadowPartCS> getOwnedParts()
	{
		if (ownedParts == null)
		{
			ownedParts = new EObjectContainmentWithInverseEList<ShadowPartCS>(ShadowPartCS.class, this, EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, EssentialOCLCSPackage.SHADOW_PART_CS__OWNING_CURLY_BRACKET_CLAUSE);
		}
		return ownedParts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getValue()
	{
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setValue(String newValue)
	{
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString()
	{
		return super.toString();
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
			case EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedParts()).basicAdd(otherEnd, msgs);
			case EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS__OWNING_NAME_EXP:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningNameExp((AbstractNameExpCS)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
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
			case EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS:
				return ((InternalEList<?>)getOwnedParts()).basicRemove(otherEnd, msgs);
			case EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS__OWNING_NAME_EXP:
				return basicSetOwningNameExp(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
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
			case EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS__OWNING_NAME_EXP:
				return eInternalContainer().eInverseRemove(this, EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, AbstractNameExpCS.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
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
			case EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS:
				return getOwnedParts();
			case EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS__OWNING_NAME_EXP:
				return getOwningNameExp();
			case EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS__VALUE:
				return getValue();
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
			case EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS:
				getOwnedParts().clear();
				getOwnedParts().addAll((Collection<? extends ShadowPartCS>)newValue);
				return;
			case EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS__OWNING_NAME_EXP:
				setOwningNameExp((AbstractNameExpCS)newValue);
				return;
			case EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS__VALUE:
				setValue((String)newValue);
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
			case EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS:
				getOwnedParts().clear();
				return;
			case EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS__OWNING_NAME_EXP:
				setOwningNameExp((AbstractNameExpCS)null);
				return;
			case EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS__VALUE:
				setValue(VALUE_EDEFAULT);
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
			case EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS:
				return ownedParts != null && !ownedParts.isEmpty();
			case EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS__OWNING_NAME_EXP:
				return getOwningNameExp() != null;
			case EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <R> R accept(@NonNull BaseCSVisitor<R> visitor) {
		return (R) ((EssentialOCLCSVisitor<?>)visitor).visitCurlyBracketedClauseCS(this);
	}

} //CurlyBracketedClauseCSImpl
