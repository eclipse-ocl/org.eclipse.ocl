/*******************************************************************************
 * Copyright (c) 2015 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
/**
 */
package org.eclipse.ocl.xtext.essentialoclcs.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.xtext.basecs.PathNameCS;
import org.eclipse.ocl.xtext.essentialoclcs.AbstractNameExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.CurlyBracketedClauseCS;
import org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage;
import org.eclipse.ocl.xtext.essentialoclcs.RoundBracketedClauseCS;
import org.eclipse.ocl.xtext.essentialoclcs.SquareBracketedClauseCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Name Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.AbstractNameExpCSImpl#isIsPre <em>Is Pre</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.AbstractNameExpCSImpl#getOwnedCurlyBracketedClause <em>Owned Curly Bracketed Clause</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.AbstractNameExpCSImpl#getOwnedPathName <em>Owned Path Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.AbstractNameExpCSImpl#getOwnedRoundBracketedClause <em>Owned Round Bracketed Clause</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.AbstractNameExpCSImpl#getOwnedSquareBracketedClauses <em>Owned Square Bracketed Clauses</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.AbstractNameExpCSImpl#getSourceType <em>Source Type</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.AbstractNameExpCSImpl#getSourceTypeValue <em>Source Type Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractNameExpCSImpl extends ExpCSImpl implements AbstractNameExpCS
{
	/**
	 * The default value of the '{@link #isIsPre() <em>Is Pre</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsPre()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_PRE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsPre() <em>Is Pre</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsPre()
	 * @generated
	 * @ordered
	 */
	protected boolean isPre = IS_PRE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOwnedCurlyBracketedClause() <em>Owned Curly Bracketed Clause</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedCurlyBracketedClause()
	 * @generated
	 * @ordered
	 */
	protected CurlyBracketedClauseCS ownedCurlyBracketedClause;

	/**
	 * The cached value of the '{@link #getOwnedPathName() <em>Owned Path Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedPathName()
	 * @generated
	 * @ordered
	 */
	protected PathNameCS ownedPathName;

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
	 * The cached value of the '{@link #getOwnedSquareBracketedClauses() <em>Owned Square Bracketed Clauses</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSquareBracketedClauses()
	 * @generated
	 * @ordered
	 */
	protected EList<SquareBracketedClauseCS> ownedSquareBracketedClauses;

	/**
	 * The cached value of the '{@link #getSourceType() <em>Source Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceType()
	 * @generated
	 * @ordered
	 */
	protected Type sourceType;

	/**
	 * The cached value of the '{@link #getSourceTypeValue() <em>Source Type Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceTypeValue()
	 * @generated
	 * @ordered
	 */
	protected Type sourceTypeValue;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractNameExpCSImpl()
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
		return EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PathNameCS getOwnedPathName()
	{
		return ownedPathName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedPathName(PathNameCS newOwnedPathName, NotificationChain msgs)
	{
		PathNameCS oldOwnedPathName = ownedPathName;
		ownedPathName = newOwnedPathName;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, oldOwnedPathName, newOwnedPathName);
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
	public void setOwnedPathName(PathNameCS newOwnedPathName)
	{
		if (newOwnedPathName != ownedPathName)
		{
			NotificationChain msgs = null;
			if (ownedPathName != null)
				msgs = ((InternalEObject)ownedPathName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, null, msgs);
			if (newOwnedPathName != null)
				msgs = ((InternalEObject)newOwnedPathName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, null, msgs);
			msgs = basicSetOwnedPathName(newOwnedPathName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, newOwnedPathName, newOwnedPathName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsPre()
	{
		return isPre;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsPre(boolean newIsPre)
	{
		boolean oldIsPre = isPre;
		isPre = newIsPre;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__IS_PRE, oldIsPre, isPre));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CurlyBracketedClauseCS getOwnedCurlyBracketedClause()
	{
		return ownedCurlyBracketedClause;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedCurlyBracketedClause(CurlyBracketedClauseCS newOwnedCurlyBracketedClause, NotificationChain msgs)
	{
		CurlyBracketedClauseCS oldOwnedCurlyBracketedClause = ownedCurlyBracketedClause;
		ownedCurlyBracketedClause = newOwnedCurlyBracketedClause;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, oldOwnedCurlyBracketedClause, newOwnedCurlyBracketedClause);
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
	public void setOwnedCurlyBracketedClause(CurlyBracketedClauseCS newOwnedCurlyBracketedClause)
	{
		if (newOwnedCurlyBracketedClause != ownedCurlyBracketedClause)
		{
			NotificationChain msgs = null;
			if (ownedCurlyBracketedClause != null)
				msgs = ((InternalEObject)ownedCurlyBracketedClause).eInverseRemove(this, EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS__OWNING_NAME_EXP, CurlyBracketedClauseCS.class, msgs);
			if (newOwnedCurlyBracketedClause != null)
				msgs = ((InternalEObject)newOwnedCurlyBracketedClause).eInverseAdd(this, EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS__OWNING_NAME_EXP, CurlyBracketedClauseCS.class, msgs);
			msgs = basicSetOwnedCurlyBracketedClause(newOwnedCurlyBracketedClause, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, newOwnedCurlyBracketedClause, newOwnedCurlyBracketedClause));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, oldOwnedRoundBracketedClause, newOwnedRoundBracketedClause);
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
				msgs = ((InternalEObject)ownedRoundBracketedClause).eInverseRemove(this, EssentialOCLCSPackage.ROUND_BRACKETED_CLAUSE_CS__OWNING_NAME_EXP, RoundBracketedClauseCS.class, msgs);
			if (newOwnedRoundBracketedClause != null)
				msgs = ((InternalEObject)newOwnedRoundBracketedClause).eInverseAdd(this, EssentialOCLCSPackage.ROUND_BRACKETED_CLAUSE_CS__OWNING_NAME_EXP, RoundBracketedClauseCS.class, msgs);
			msgs = basicSetOwnedRoundBracketedClause(newOwnedRoundBracketedClause, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, newOwnedRoundBracketedClause, newOwnedRoundBracketedClause));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<SquareBracketedClauseCS> getOwnedSquareBracketedClauses()
	{
		if (ownedSquareBracketedClauses == null)
		{
			ownedSquareBracketedClauses = new EObjectContainmentWithInverseEList<SquareBracketedClauseCS>(SquareBracketedClauseCS.class, this, EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, EssentialOCLCSPackage.SQUARE_BRACKETED_CLAUSE_CS__OWNING_NAME_EXP);
		}
		return ownedSquareBracketedClauses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Type getSourceType()
	{
		return sourceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSourceType(Type newSourceType)
	{
		Type oldSourceType = sourceType;
		sourceType = newSourceType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__SOURCE_TYPE, oldSourceType, sourceType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Type getSourceTypeValue()
	{
		return sourceTypeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSourceTypeValue(Type newSourceTypeValue)
	{
		Type oldSourceTypeValue = sourceTypeValue;
		sourceTypeValue = newSourceTypeValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__SOURCE_TYPE_VALUE, oldSourceTypeValue, sourceTypeValue));
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
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE:
				if (ownedCurlyBracketedClause != null)
					msgs = ((InternalEObject)ownedCurlyBracketedClause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, null, msgs);
				return basicSetOwnedCurlyBracketedClause((CurlyBracketedClauseCS)otherEnd, msgs);
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE:
				if (ownedRoundBracketedClause != null)
					msgs = ((InternalEObject)ownedRoundBracketedClause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, null, msgs);
				return basicSetOwnedRoundBracketedClause((RoundBracketedClauseCS)otherEnd, msgs);
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedSquareBracketedClauses()).basicAdd(otherEnd, msgs);
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
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE:
				return basicSetOwnedCurlyBracketedClause(null, msgs);
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME:
				return basicSetOwnedPathName(null, msgs);
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE:
				return basicSetOwnedRoundBracketedClause(null, msgs);
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES:
				return ((InternalEList<?>)getOwnedSquareBracketedClauses()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
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
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__IS_PRE:
				return isIsPre();
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE:
				return getOwnedCurlyBracketedClause();
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME:
				return getOwnedPathName();
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE:
				return getOwnedRoundBracketedClause();
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES:
				return getOwnedSquareBracketedClauses();
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__SOURCE_TYPE:
				return getSourceType();
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__SOURCE_TYPE_VALUE:
				return getSourceTypeValue();
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
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__IS_PRE:
				setIsPre((Boolean)newValue);
				return;
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE:
				setOwnedCurlyBracketedClause((CurlyBracketedClauseCS)newValue);
				return;
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME:
				setOwnedPathName((PathNameCS)newValue);
				return;
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE:
				setOwnedRoundBracketedClause((RoundBracketedClauseCS)newValue);
				return;
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES:
				getOwnedSquareBracketedClauses().clear();
				getOwnedSquareBracketedClauses().addAll((Collection<? extends SquareBracketedClauseCS>)newValue);
				return;
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__SOURCE_TYPE:
				setSourceType((Type)newValue);
				return;
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__SOURCE_TYPE_VALUE:
				setSourceTypeValue((Type)newValue);
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
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__IS_PRE:
				setIsPre(IS_PRE_EDEFAULT);
				return;
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE:
				setOwnedCurlyBracketedClause((CurlyBracketedClauseCS)null);
				return;
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME:
				setOwnedPathName((PathNameCS)null);
				return;
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE:
				setOwnedRoundBracketedClause((RoundBracketedClauseCS)null);
				return;
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES:
				getOwnedSquareBracketedClauses().clear();
				return;
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__SOURCE_TYPE:
				setSourceType((Type)null);
				return;
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__SOURCE_TYPE_VALUE:
				setSourceTypeValue((Type)null);
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
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__IS_PRE:
				return isPre != IS_PRE_EDEFAULT;
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE:
				return ownedCurlyBracketedClause != null;
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME:
				return ownedPathName != null;
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE:
				return ownedRoundBracketedClause != null;
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES:
				return ownedSquareBracketedClauses != null && !ownedSquareBracketedClauses.isEmpty();
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__SOURCE_TYPE:
				return sourceType != null;
			case EssentialOCLCSPackage.ABSTRACT_NAME_EXP_CS__SOURCE_TYPE_VALUE:
				return sourceTypeValue != null;
		}
		return super.eIsSet(featureID);
	}


} //AbstractNameExpCSImpl
