/*******************************************************************************
 * Copyright (c) 2010, 2015 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ParameterImpl#isIsTypeof <em>Is Typeof</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ParameterImpl#getOwningOperation <em>Owning Operation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ParameterImpl
		extends VariableDeclarationImpl
		implements Parameter {

	/**
	 * The default value of the '{@link #isIsTypeof() <em>Is Typeof</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsTypeof()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_TYPEOF_EDEFAULT = false;
	/**
	 * The flag representing the value of the '{@link #isIsTypeof() <em>Is Typeof</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsTypeof()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_TYPEOF_EFLAG = 1 << 10;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ParameterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.PARAMETER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsTypeof()
	{
		return (eFlags & IS_TYPEOF_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsTypeof(boolean newIsTypeof)
	{
		boolean oldIsTypeof = (eFlags & IS_TYPEOF_EFLAG) != 0;
		if (newIsTypeof) eFlags |= IS_TYPEOF_EFLAG; else eFlags &= ~IS_TYPEOF_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PARAMETER__IS_TYPEOF, oldIsTypeof, newIsTypeof));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Operation getOwningOperation()
	{
		if (eContainerFeatureID() != PivotPackage.PARAMETER__OWNING_OPERATION) return null;
		return (Operation)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningOperation(Operation newOwningOperation, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningOperation, PivotPackage.PARAMETER__OWNING_OPERATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningOperation(Operation newOwningOperation)
	{
		if (newOwningOperation != eInternalContainer() || (eContainerFeatureID() != PivotPackage.PARAMETER__OWNING_OPERATION && newOwningOperation != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningOperation))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningOperation != null)
				msgs = ((InternalEObject)newOwningOperation).eInverseAdd(this, PivotPackage.OPERATION__OWNED_PARAMETERS, Operation.class, msgs);
			msgs = basicSetOwningOperation(newOwningOperation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PARAMETER__OWNING_OPERATION, newOwningOperation, newOwningOperation));
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
			case PivotPackage.PARAMETER__ANNOTATING_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.PARAMETER__OWNED_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.PARAMETER__OWNED_EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case PivotPackage.PARAMETER__OWNING_OPERATION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningOperation((Operation)otherEnd, msgs);
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
			case PivotPackage.PARAMETER__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.PARAMETER__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.PARAMETER__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.PARAMETER__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.PARAMETER__OWNING_OPERATION:
				return basicSetOwningOperation(null, msgs);
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
			case PivotPackage.PARAMETER__OWNING_OPERATION:
				return eInternalContainer().eInverseRemove(this, PivotPackage.OPERATION__OWNED_PARAMETERS, Operation.class, msgs);
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
			case PivotPackage.PARAMETER__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.PARAMETER__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.PARAMETER__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.PARAMETER__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.PARAMETER__NAME:
				return getName();
			case PivotPackage.PARAMETER__IS_MANY:
				return isIsMany();
			case PivotPackage.PARAMETER__IS_REQUIRED:
				return isIsRequired();
			case PivotPackage.PARAMETER__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case PivotPackage.PARAMETER__CACHE_NEEDED:
				return isCacheNeeded();
			case PivotPackage.PARAMETER__TYPE_VALUE:
				return getTypeValue();
			case PivotPackage.PARAMETER__IS_TYPEOF:
				return isIsTypeof();
			case PivotPackage.PARAMETER__OWNING_OPERATION:
				return getOwningOperation();
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
			case PivotPackage.PARAMETER__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.PARAMETER__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.PARAMETER__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.PARAMETER__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.PARAMETER__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.PARAMETER__IS_REQUIRED:
				setIsRequired((Boolean)newValue);
				return;
			case PivotPackage.PARAMETER__TYPE:
				setType((Type)newValue);
				return;
			case PivotPackage.PARAMETER__CACHE_NEEDED:
				setCacheNeeded((Boolean)newValue);
				return;
			case PivotPackage.PARAMETER__TYPE_VALUE:
				setTypeValue((Type)newValue);
				return;
			case PivotPackage.PARAMETER__IS_TYPEOF:
				setIsTypeof((Boolean)newValue);
				return;
			case PivotPackage.PARAMETER__OWNING_OPERATION:
				setOwningOperation((Operation)newValue);
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
			case PivotPackage.PARAMETER__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.PARAMETER__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.PARAMETER__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.PARAMETER__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.PARAMETER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.PARAMETER__IS_REQUIRED:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case PivotPackage.PARAMETER__TYPE:
				setType((Type)null);
				return;
			case PivotPackage.PARAMETER__CACHE_NEEDED:
				setCacheNeeded(CACHE_NEEDED_EDEFAULT);
				return;
			case PivotPackage.PARAMETER__TYPE_VALUE:
				setTypeValue((Type)null);
				return;
			case PivotPackage.PARAMETER__IS_TYPEOF:
				setIsTypeof(IS_TYPEOF_EDEFAULT);
				return;
			case PivotPackage.PARAMETER__OWNING_OPERATION:
				setOwningOperation((Operation)null);
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
			case PivotPackage.PARAMETER__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.PARAMETER__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.PARAMETER__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.PARAMETER__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.PARAMETER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.PARAMETER__IS_MANY:
				return isIsMany() != IS_MANY_EDEFAULT;
			case PivotPackage.PARAMETER__IS_REQUIRED:
				return ((eFlags & IS_REQUIRED_EFLAG) != 0) != IS_REQUIRED_EDEFAULT;
			case PivotPackage.PARAMETER__TYPE:
				return type != null;
			case PivotPackage.PARAMETER__CACHE_NEEDED:
				return ((eFlags & CACHE_NEEDED_EFLAG) != 0) != CACHE_NEEDED_EDEFAULT;
			case PivotPackage.PARAMETER__TYPE_VALUE:
				return typeValue != null;
			case PivotPackage.PARAMETER__IS_TYPEOF:
				return ((eFlags & IS_TYPEOF_EFLAG) != 0) != IS_TYPEOF_EDEFAULT;
			case PivotPackage.PARAMETER__OWNING_OPERATION:
				return getOwningOperation() != null;
		}
		return eDynamicIsSet(featureID);
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
		return visitor.visitParameter(this);
	}

} //ParameterImpl
