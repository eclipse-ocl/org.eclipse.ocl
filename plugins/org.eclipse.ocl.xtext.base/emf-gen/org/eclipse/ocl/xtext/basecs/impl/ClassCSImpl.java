/*******************************************************************************
 * Copyright (c) 2010, 2015 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.xtext.basecs.impl;

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
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.ClassCS;
import org.eclipse.ocl.xtext.basecs.ConstraintCS;
import org.eclipse.ocl.xtext.basecs.PackageCS;
import org.eclipse.ocl.xtext.basecs.TemplateSignatureCS;
import org.eclipse.ocl.xtext.basecs.TemplateableElementCS;
import org.eclipse.ocl.xtext.basecs.TypeCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Classifier CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.ClassCSImpl#getOwnedSignature <em>Owned Signature</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.ClassCSImpl#getInstanceClassName <em>Instance Class Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.ClassCSImpl#getOwnedConstraints <em>Owned Constraints</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.ClassCSImpl#getOwningPackage <em>Owning Package</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ClassCSImpl extends NamedElementCSImpl implements ClassCS {
	/**
	 * The cached value of the '{@link #getOwnedSignature() <em>Owned Signature</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSignature()
	 * @generated
	 * @ordered
	 */
	protected TemplateSignatureCS ownedSignature;
	/**
	 * The default value of the '{@link #getInstanceClassName() <em>Instance Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstanceClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String INSTANCE_CLASS_NAME_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getInstanceClassName() <em>Instance Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstanceClassName()
	 * @generated
	 * @ordered
	 */
	protected String instanceClassName = INSTANCE_CLASS_NAME_EDEFAULT;
	/**
	 * The cached value of the '{@link #getOwnedConstraints() <em>Owned Constraints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList<ConstraintCS> ownedConstraints;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BaseCSPackage.Literals.CLASS_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TemplateSignatureCS getOwnedSignature() {
		return ownedSignature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedSignature(TemplateSignatureCS newOwnedSignature, NotificationChain msgs)
	{
		TemplateSignatureCS oldOwnedSignature = ownedSignature;
		ownedSignature = newOwnedSignature;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BaseCSPackage.CLASS_CS__OWNED_SIGNATURE, oldOwnedSignature, newOwnedSignature);
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
	public void setOwnedSignature(TemplateSignatureCS newOwnedSignature) {
		if (newOwnedSignature != ownedSignature)
		{
			NotificationChain msgs = null;
			if (ownedSignature != null)
				msgs = ((InternalEObject)ownedSignature).eInverseRemove(this, BaseCSPackage.TEMPLATE_SIGNATURE_CS__OWNING_ELEMENT, TemplateSignatureCS.class, msgs);
			if (newOwnedSignature != null)
				msgs = ((InternalEObject)newOwnedSignature).eInverseAdd(this, BaseCSPackage.TEMPLATE_SIGNATURE_CS__OWNING_ELEMENT, TemplateSignatureCS.class, msgs);
			msgs = basicSetOwnedSignature(newOwnedSignature, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.CLASS_CS__OWNED_SIGNATURE, newOwnedSignature, newOwnedSignature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PackageCS getOwningPackage() {
		if (eContainerFeatureID() != BaseCSPackage.CLASS_CS__OWNING_PACKAGE) return null;
		return (PackageCS)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningPackage(PackageCS newOwningPackage, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningPackage, BaseCSPackage.CLASS_CS__OWNING_PACKAGE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningPackage(PackageCS newOwningPackage)
	{
		if (newOwningPackage != eInternalContainer() || (eContainerFeatureID() != BaseCSPackage.CLASS_CS__OWNING_PACKAGE && newOwningPackage != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningPackage))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningPackage != null)
				msgs = ((InternalEObject)newOwningPackage).eInverseAdd(this, BaseCSPackage.PACKAGE_CS__OWNED_CLASSES, PackageCS.class, msgs);
			msgs = basicSetOwningPackage(newOwningPackage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.CLASS_CS__OWNING_PACKAGE, newOwningPackage, newOwningPackage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getInstanceClassName()
	{
		return instanceClassName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInstanceClassName(String newInstanceClassName)
	{
		String oldInstanceClassName = instanceClassName;
		instanceClassName = newInstanceClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.CLASS_CS__INSTANCE_CLASS_NAME, oldInstanceClassName, instanceClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ConstraintCS> getOwnedConstraints()
	{
		if (ownedConstraints == null)
		{
			ownedConstraints = new EObjectContainmentEList<ConstraintCS>(ConstraintCS.class, this, BaseCSPackage.CLASS_CS__OWNED_CONSTRAINTS);
		}
		return ownedConstraints;
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
	 * @generated NOT
	 */
	public TemplateSignatureCS getTemplateSignature() {
		return getOwnedSignature();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID)
		{
			case BaseCSPackage.CLASS_CS__OWNED_SIGNATURE:
				if (ownedSignature != null)
					msgs = ((InternalEObject)ownedSignature).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BaseCSPackage.CLASS_CS__OWNED_SIGNATURE, null, msgs);
				return basicSetOwnedSignature((TemplateSignatureCS)otherEnd, msgs);
			case BaseCSPackage.CLASS_CS__OWNING_PACKAGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningPackage((PackageCS)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID)
		{
			case BaseCSPackage.CLASS_CS__OWNED_SIGNATURE:
				return basicSetOwnedSignature(null, msgs);
			case BaseCSPackage.CLASS_CS__OWNED_CONSTRAINTS:
				return ((InternalEList<?>)getOwnedConstraints()).basicRemove(otherEnd, msgs);
			case BaseCSPackage.CLASS_CS__OWNING_PACKAGE:
				return basicSetOwningPackage(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID())
		{
			case BaseCSPackage.CLASS_CS__OWNING_PACKAGE:
				return eInternalContainer().eInverseRemove(this, BaseCSPackage.PACKAGE_CS__OWNED_CLASSES, PackageCS.class, msgs);
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
			case BaseCSPackage.CLASS_CS__OWNED_SIGNATURE:
				return getOwnedSignature();
			case BaseCSPackage.CLASS_CS__INSTANCE_CLASS_NAME:
				return getInstanceClassName();
			case BaseCSPackage.CLASS_CS__OWNED_CONSTRAINTS:
				return getOwnedConstraints();
			case BaseCSPackage.CLASS_CS__OWNING_PACKAGE:
				return getOwningPackage();
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
	public void eSet(int featureID, Object newValue) {
		switch (featureID)
		{
			case BaseCSPackage.CLASS_CS__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignatureCS)newValue);
				return;
			case BaseCSPackage.CLASS_CS__INSTANCE_CLASS_NAME:
				setInstanceClassName((String)newValue);
				return;
			case BaseCSPackage.CLASS_CS__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				getOwnedConstraints().addAll((Collection<? extends ConstraintCS>)newValue);
				return;
			case BaseCSPackage.CLASS_CS__OWNING_PACKAGE:
				setOwningPackage((PackageCS)newValue);
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
			case BaseCSPackage.CLASS_CS__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignatureCS)null);
				return;
			case BaseCSPackage.CLASS_CS__INSTANCE_CLASS_NAME:
				setInstanceClassName(INSTANCE_CLASS_NAME_EDEFAULT);
				return;
			case BaseCSPackage.CLASS_CS__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				return;
			case BaseCSPackage.CLASS_CS__OWNING_PACKAGE:
				setOwningPackage((PackageCS)null);
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
			case BaseCSPackage.CLASS_CS__OWNED_SIGNATURE:
				return ownedSignature != null;
			case BaseCSPackage.CLASS_CS__INSTANCE_CLASS_NAME:
				return INSTANCE_CLASS_NAME_EDEFAULT == null ? instanceClassName != null : !INSTANCE_CLASS_NAME_EDEFAULT.equals(instanceClassName);
			case BaseCSPackage.CLASS_CS__OWNED_CONSTRAINTS:
				return ownedConstraints != null && !ownedConstraints.isEmpty();
			case BaseCSPackage.CLASS_CS__OWNING_PACKAGE:
				return getOwningPackage() != null;
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
		if (baseClass == TypeCS.class)
		{
			switch (derivedFeatureID)
			{
				default: return -1;
			}
		}
		if (baseClass == TemplateableElementCS.class)
		{
			switch (derivedFeatureID)
			{
				case BaseCSPackage.CLASS_CS__OWNED_SIGNATURE: return BaseCSPackage.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE;
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
		if (baseClass == TypeCS.class)
		{
			switch (baseFeatureID)
			{
				default: return -1;
			}
		}
		if (baseClass == TemplateableElementCS.class)
		{
			switch (baseFeatureID)
			{
				case BaseCSPackage.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE: return BaseCSPackage.CLASS_CS__OWNED_SIGNATURE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}
} //ClassifierCSImpl
