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
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.basecs.impl.ContextLessElementCSImpl;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;
import org.eclipse.ocl.xtext.essentialoclcs.AbstractNameExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage;
import org.eclipse.ocl.xtext.essentialoclcs.ExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.SquareBracketedClauseCS;
import org.eclipse.ocl.xtext.essentialoclcs.util.EssentialOCLCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Square Bracketed Clause CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.SquareBracketedClauseCSImpl#getOwnedTerms <em>Owned Terms</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.SquareBracketedClauseCSImpl#getOwningNameExp <em>Owning Name Exp</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SquareBracketedClauseCSImpl extends ContextLessElementCSImpl implements SquareBracketedClauseCS
{
	/**
	 * The cached value of the '{@link #getOwnedTerms() <em>Owned Terms</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedTerms()
	 * @generated
	 * @ordered
	 */
	protected EList<ExpCS> ownedTerms;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SquareBracketedClauseCSImpl()
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
		return EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AbstractNameExpCS getOwningNameExp()
	{
		if (eContainerFeatureID() != EssentialOCLCSPackage.SQUARE_BRACKETED_CLAUSE_CS__OWNING_NAME_EXP) return null;
		return (AbstractNameExpCS)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningNameExp(AbstractNameExpCS newOwningNameExp, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningNameExp, EssentialOCLCSPackage.SQUARE_BRACKETED_CLAUSE_CS__OWNING_NAME_EXP, msgs);
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
		if (newOwningNameExp != eInternalContainer() || (eContainerFeatureID() != EssentialOCLCSPackage.SQUARE_BRACKETED_CLAUSE_CS__OWNING_NAME_EXP && newOwningNameExp != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningNameExp))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningNameExp != null)
				msgs = ((InternalEObject)newOwningNameExp).eInverseAdd(this, EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, AbstractNameExpCS.class, msgs);
			msgs = basicSetOwningNameExp(newOwningNameExp, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.SQUARE_BRACKETED_CLAUSE_CS__OWNING_NAME_EXP, newOwningNameExp, newOwningNameExp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ExpCS> getOwnedTerms()
	{
		if (ownedTerms == null)
		{
			ownedTerms = new EObjectContainmentEList<ExpCS>(ExpCS.class, this, EssentialOCLCSPackage.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
		}
		return ownedTerms;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case EssentialOCLCSPackage.SQUARE_BRACKETED_CLAUSE_CS__OWNING_NAME_EXP:
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
			case EssentialOCLCSPackage.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS:
				return ((InternalEList<?>)getOwnedTerms()).basicRemove(otherEnd, msgs);
			case EssentialOCLCSPackage.SQUARE_BRACKETED_CLAUSE_CS__OWNING_NAME_EXP:
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
			case EssentialOCLCSPackage.SQUARE_BRACKETED_CLAUSE_CS__OWNING_NAME_EXP:
				return eInternalContainer().eInverseRemove(this, EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, AbstractNameExpCS.class, msgs);
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
			case EssentialOCLCSPackage.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS:
				return getOwnedTerms();
			case EssentialOCLCSPackage.SQUARE_BRACKETED_CLAUSE_CS__OWNING_NAME_EXP:
				return getOwningNameExp();
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
			case EssentialOCLCSPackage.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS:
				getOwnedTerms().clear();
				getOwnedTerms().addAll((Collection<? extends ExpCS>)newValue);
				return;
			case EssentialOCLCSPackage.SQUARE_BRACKETED_CLAUSE_CS__OWNING_NAME_EXP:
				setOwningNameExp((AbstractNameExpCS)newValue);
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
			case EssentialOCLCSPackage.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS:
				getOwnedTerms().clear();
				return;
			case EssentialOCLCSPackage.SQUARE_BRACKETED_CLAUSE_CS__OWNING_NAME_EXP:
				setOwningNameExp((AbstractNameExpCS)null);
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
			case EssentialOCLCSPackage.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS:
				return ownedTerms != null && !ownedTerms.isEmpty();
			case EssentialOCLCSPackage.SQUARE_BRACKETED_CLAUSE_CS__OWNING_NAME_EXP:
				return getOwningNameExp() != null;
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
		return (R) ((EssentialOCLCSVisitor<?>)visitor).visitSquareBracketedClauseCS(this);
	}

} //SquareBracketedClauseCSImpl
