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
package org.eclipse.ocl.xtext.basecs.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.ConstraintCS;
import org.eclipse.ocl.xtext.basecs.SpecificationCS;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constraint CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.ConstraintCSImpl#getOwnedMessageSpecification <em>Owned Message Specification</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.ConstraintCSImpl#getOwnedSpecification <em>Owned Specification</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.ConstraintCSImpl#getStereotype <em>Stereotype</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConstraintCSImpl extends NamedElementCSImpl implements ConstraintCS
{
	/**
	 * The cached value of the '{@link #getOwnedMessageSpecification() <em>Owned Message Specification</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedMessageSpecification()
	 * @generated
	 * @ordered
	 */
	protected SpecificationCS ownedMessageSpecification;

	/**
	 * The cached value of the '{@link #getOwnedSpecification() <em>Owned Specification</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSpecification()
	 * @generated
	 * @ordered
	 */
	protected SpecificationCS ownedSpecification;

	/**
	 * The default value of the '{@link #getStereotype() <em>Stereotype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStereotype()
	 * @generated
	 * @ordered
	 */
	protected static final String STEREOTYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStereotype() <em>Stereotype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStereotype()
	 * @generated
	 * @ordered
	 */
	protected String stereotype = STEREOTYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConstraintCSImpl()
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
		return BaseCSPackage.Literals.CONSTRAINT_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getStereotype()
	{
		return stereotype;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStereotype(String newStereotype)
	{
		String oldStereotype = stereotype;
		stereotype = newStereotype;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.CONSTRAINT_CS__STEREOTYPE, oldStereotype, stereotype));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SpecificationCS getOwnedSpecification()
	{
		return ownedSpecification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedSpecification(SpecificationCS newOwnedSpecification, NotificationChain msgs)
	{
		SpecificationCS oldOwnedSpecification = ownedSpecification;
		ownedSpecification = newOwnedSpecification;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BaseCSPackage.CONSTRAINT_CS__OWNED_SPECIFICATION, oldOwnedSpecification, newOwnedSpecification);
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
	public void setOwnedSpecification(SpecificationCS newOwnedSpecification)
	{
		if (newOwnedSpecification != ownedSpecification)
		{
			NotificationChain msgs = null;
			if (ownedSpecification != null)
				msgs = ((InternalEObject)ownedSpecification).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BaseCSPackage.CONSTRAINT_CS__OWNED_SPECIFICATION, null, msgs);
			if (newOwnedSpecification != null)
				msgs = ((InternalEObject)newOwnedSpecification).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BaseCSPackage.CONSTRAINT_CS__OWNED_SPECIFICATION, null, msgs);
			msgs = basicSetOwnedSpecification(newOwnedSpecification, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.CONSTRAINT_CS__OWNED_SPECIFICATION, newOwnedSpecification, newOwnedSpecification));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SpecificationCS getOwnedMessageSpecification()
	{
		return ownedMessageSpecification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedMessageSpecification(SpecificationCS newOwnedMessageSpecification, NotificationChain msgs)
	{
		SpecificationCS oldOwnedMessageSpecification = ownedMessageSpecification;
		ownedMessageSpecification = newOwnedMessageSpecification;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BaseCSPackage.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, oldOwnedMessageSpecification, newOwnedMessageSpecification);
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
	public void setOwnedMessageSpecification(SpecificationCS newOwnedMessageSpecification)
	{
		if (newOwnedMessageSpecification != ownedMessageSpecification)
		{
			NotificationChain msgs = null;
			if (ownedMessageSpecification != null)
				msgs = ((InternalEObject)ownedMessageSpecification).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BaseCSPackage.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, null, msgs);
			if (newOwnedMessageSpecification != null)
				msgs = ((InternalEObject)newOwnedMessageSpecification).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BaseCSPackage.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, null, msgs);
			msgs = basicSetOwnedMessageSpecification(newOwnedMessageSpecification, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, newOwnedMessageSpecification, newOwnedMessageSpecification));
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
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case BaseCSPackage.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION:
				return basicSetOwnedMessageSpecification(null, msgs);
			case BaseCSPackage.CONSTRAINT_CS__OWNED_SPECIFICATION:
				return basicSetOwnedSpecification(null, msgs);
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
			case BaseCSPackage.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION:
				return getOwnedMessageSpecification();
			case BaseCSPackage.CONSTRAINT_CS__OWNED_SPECIFICATION:
				return getOwnedSpecification();
			case BaseCSPackage.CONSTRAINT_CS__STEREOTYPE:
				return getStereotype();
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
			case BaseCSPackage.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION:
				setOwnedMessageSpecification((SpecificationCS)newValue);
				return;
			case BaseCSPackage.CONSTRAINT_CS__OWNED_SPECIFICATION:
				setOwnedSpecification((SpecificationCS)newValue);
				return;
			case BaseCSPackage.CONSTRAINT_CS__STEREOTYPE:
				setStereotype((String)newValue);
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
			case BaseCSPackage.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION:
				setOwnedMessageSpecification((SpecificationCS)null);
				return;
			case BaseCSPackage.CONSTRAINT_CS__OWNED_SPECIFICATION:
				setOwnedSpecification((SpecificationCS)null);
				return;
			case BaseCSPackage.CONSTRAINT_CS__STEREOTYPE:
				setStereotype(STEREOTYPE_EDEFAULT);
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
			case BaseCSPackage.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION:
				return ownedMessageSpecification != null;
			case BaseCSPackage.CONSTRAINT_CS__OWNED_SPECIFICATION:
				return ownedSpecification != null;
			case BaseCSPackage.CONSTRAINT_CS__STEREOTYPE:
				return STEREOTYPE_EDEFAULT == null ? stereotype != null : !STEREOTYPE_EDEFAULT.equals(stereotype);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull BaseCSVisitor<R> visitor) {
		return visitor.visitConstraintCS(this);
	}
} //ConstraintCSImpl
