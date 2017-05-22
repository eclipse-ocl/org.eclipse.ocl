/*******************************************************************************
 * Copyright (c) 2013, 2016 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.cgmodel.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;

import org.eclipse.ocl.examples.codegen.cse.AbstractPlace;
import org.eclipse.ocl.examples.codegen.cse.ControlPlace;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CG Valued Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGValuedElementImpl#getDependsOn <em>Depends On</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGValuedElementImpl#getOwns <em>Owns</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class CGValuedElementImpl extends CGTypedElementImpl implements CGValuedElement
{
	/**
	 * The cached value of the '{@link #getDependsOn() <em>Depends On</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependsOn()
	 * @generated
	 * @ordered
	 */
	protected EList<CGValuedElement> dependsOn;

	/**
	 * The cached value of the '{@link #getOwns() <em>Owns</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwns()
	 * @generated
	 * @ordered
	 */
	protected EList<CGValuedElement> owns;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CGValuedElementImpl()
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
		return CGModelPackage.Literals.CG_VALUED_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<CGValuedElement> getDependsOn()
	{
		if (dependsOn == null) {
			dependsOn = new EObjectEList<CGValuedElement>(CGValuedElement.class, this, CGModelPackage.CG_VALUED_ELEMENT__DEPENDS_ON);
		}
		return dependsOn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<CGValuedElement> getOwns()
	{
		if (owns == null) {
			owns = new EObjectContainmentEList<CGValuedElement>(CGValuedElement.class, this, CGModelPackage.CG_VALUED_ELEMENT__OWNS);
		}
		return owns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID) {
			case CGModelPackage.CG_VALUED_ELEMENT__OWNS:
				return ((InternalEList<?>)getOwns()).basicRemove(otherEnd, msgs);
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
		switch (featureID) {
			case CGModelPackage.CG_VALUED_ELEMENT__DEPENDS_ON:
				return getDependsOn();
			case CGModelPackage.CG_VALUED_ELEMENT__OWNS:
				return getOwns();
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
		switch (featureID) {
			case CGModelPackage.CG_VALUED_ELEMENT__DEPENDS_ON:
				getDependsOn().clear();
				getDependsOn().addAll((Collection<? extends CGValuedElement>)newValue);
				return;
			case CGModelPackage.CG_VALUED_ELEMENT__OWNS:
				getOwns().clear();
				getOwns().addAll((Collection<? extends CGValuedElement>)newValue);
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
		switch (featureID) {
			case CGModelPackage.CG_VALUED_ELEMENT__DEPENDS_ON:
				getDependsOn().clear();
				return;
			case CGModelPackage.CG_VALUED_ELEMENT__OWNS:
				getOwns().clear();
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
		switch (featureID) {
			case CGModelPackage.CG_VALUED_ELEMENT__DEPENDS_ON:
				return dependsOn != null && !dependsOn.isEmpty();
			case CGModelPackage.CG_VALUED_ELEMENT__OWNS:
				return owns != null && !owns.isEmpty();
		}
		return super.eIsSet(featureID);
	}


	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable EClassifier getEcoreClassifier() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable CGInvalid getInvalidValue() {
		CGValuedElement sourceValue = getReferredValue();
		return sourceValue != this ? sourceValue.getInvalidValue() : null;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable AbstractPlace getPlace(@NonNull Map<@Nullable CGElement, @NonNull AbstractPlace> element2place) {
		return ControlPlace.createControlPlace(element2place, this);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @NonNull CGValuedElement getReferredValue() {
		return getThisValue();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @NonNull CGValuedElement getSourceValue() {
		CGValuedElement value = getThisValue();
		return value != this ? value.getSourceValue() : value;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @NonNull CGValuedElement getThisValue() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @NonNull CGValuedElement getNamedValue() {
		CGValuedElement value = getThisValue();
		return value != this ? value.getNamedValue() : value;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @NonNull CGValuedElement getTypedValue() {
		CGValuedElement value = getThisValue();
		return value != this ? value.getTypedValue() : value;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable String getValueName() {
		CGValuedElement value = getThisValue();
		return value != this ? value.getValueName() : valueName;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isAssertedNonNull() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isBoxed() {
		CGValuedElement referredValue = getReferredValue();
//		CGValuedElement value = getNamedValue();
		assert referredValue != this : "isBoxed must be overridden for a " + getClass().getSimpleName() + " since referredValue returns this";
		return referredValue.isBoxed();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isCaught() {
		return caught;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isCommonable() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isConstant() {
		CGValuedElement referredValue = getReferredValue();
		return (referredValue != this) && referredValue.isConstant();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isContext() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isEcore() {
		CGValuedElement referredValue = getReferredValue();
//		CGValuedElement value = getNamedValue();
		assert referredValue != this : "isEcore must be overridden for a " + getClass().getSimpleName() + " since referredValue returns this";
		return referredValue.isEcore();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable Boolean isEquivalentTo(@NonNull CGValuedElement thatValue) {
		return thatValue.isEquivalentToInternal(this);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isFalse() {
		CGValuedElement referredValue = getReferredValue();
		return (referredValue != this) && referredValue.isFalse();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isGlobal() {
		for (CGValuedElement cgElement : getDependsOn()) {
			if (!cgElement.isGlobal()) {
				return false;
			}
		}
		CGValuedElement referredValue = getNamedValue();
		return (referredValue != this) && referredValue.isGlobal();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isInlined() {
		CGValuedElement referredValue = getReferredValue();
		return (referredValue != this) && referredValue.isInlined();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isInvalid() {
		return getInvalidValue() != null;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isNonInvalid() {
		CGValuedElement referredValue = getReferredValue();
		return (referredValue != this) && referredValue.isNonInvalid();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isNonNull() {
		CGValuedElement referredValue = getReferredValue();
		return (referredValue != this) && referredValue.isNonNull();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isNull() {
		CGValuedElement referredValue = getReferredValue();
		return (referredValue != this) && referredValue.isNull();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isSettable() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isTrue() {
		CGValuedElement referredValue = getReferredValue();
		return (referredValue != this) && referredValue.isTrue();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isUnboxed() {
		CGValuedElement referredValue = getReferredValue();
//		CGValuedElement value = getNamedValue();
		assert referredValue != this : "isUnboxed must be overridden for a " + getClass().getSimpleName() + " since referredValue returns this";
		return referredValue.isUnboxed();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isUncommonable() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean rewriteAs(@NonNull CGValuedElement oldValue, @NonNull CGValuedElement newValue) {
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean caught = false;

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public void setCaught(boolean isCaught) {
		caught = isCaught;
	}

	/**
	 * @generated
	 */
	protected String valueName = null;

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public void setValueName(@NonNull String valueName) {
		this.valueName = valueName;
	}

} //CGValuedElementImpl