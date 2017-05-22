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
package org.eclipse.ocl.pivot.internal;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Slot;
import org.eclipse.ocl.pivot.ids.EnumerationId;
import org.eclipse.ocl.pivot.ids.EnumerationLiteralId;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enumeration Literal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.EnumerationLiteralImpl#getOwningEnumeration <em>Owning Enumeration</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.EnumerationLiteralImpl#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EnumerationLiteralImpl
		extends InstanceSpecificationImpl
		implements EnumerationLiteral {

	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final Number VALUE_EDEFAULT = (Number)PivotFactory.eINSTANCE.createFromString(PivotPackage.eINSTANCE.getInteger(), "0"); //$NON-NLS-1$
	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected Number value = VALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnumerationLiteralImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.ENUMERATION_LITERAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Enumeration getOwningEnumeration()
	{
		if (eContainerFeatureID() != PivotPackage.ENUMERATION_LITERAL__OWNING_ENUMERATION) return null;
		return (Enumeration)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningEnumeration(Enumeration newOwningEnumeration, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningEnumeration, PivotPackage.ENUMERATION_LITERAL__OWNING_ENUMERATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningEnumeration(Enumeration newOwningEnumeration)
	{
		if (newOwningEnumeration != eInternalContainer() || (eContainerFeatureID() != PivotPackage.ENUMERATION_LITERAL__OWNING_ENUMERATION && newOwningEnumeration != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningEnumeration))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningEnumeration != null)
				msgs = ((InternalEObject)newOwningEnumeration).eInverseAdd(this, PivotPackage.ENUMERATION__OWNED_LITERALS, Enumeration.class, msgs);
			msgs = basicSetOwningEnumeration(newOwningEnumeration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.ENUMERATION_LITERAL__OWNING_ENUMERATION, newOwningEnumeration, newOwningEnumeration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Number getValue()
	{
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setValue(Number newValue)
	{
		Number oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.ENUMERATION_LITERAL__VALUE, oldValue, value));
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
			case PivotPackage.ENUMERATION_LITERAL__ANNOTATING_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.ENUMERATION_LITERAL__OWNED_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.ENUMERATION_LITERAL__OWNED_EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case PivotPackage.ENUMERATION_LITERAL__OWNED_SLOTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedSlots()).basicAdd(otherEnd, msgs);
			case PivotPackage.ENUMERATION_LITERAL__OWNING_PACKAGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningPackage((org.eclipse.ocl.pivot.Package)otherEnd, msgs);
			case PivotPackage.ENUMERATION_LITERAL__OWNING_ENUMERATION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningEnumeration((Enumeration)otherEnd, msgs);
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
			case PivotPackage.ENUMERATION_LITERAL__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.ENUMERATION_LITERAL__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.ENUMERATION_LITERAL__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.ENUMERATION_LITERAL__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.ENUMERATION_LITERAL__OWNED_SLOTS:
				return ((InternalEList<?>)getOwnedSlots()).basicRemove(otherEnd, msgs);
			case PivotPackage.ENUMERATION_LITERAL__OWNED_SPECIFICATION:
				return basicSetOwnedSpecification(null, msgs);
			case PivotPackage.ENUMERATION_LITERAL__OWNING_PACKAGE:
				return basicSetOwningPackage(null, msgs);
			case PivotPackage.ENUMERATION_LITERAL__OWNING_ENUMERATION:
				return basicSetOwningEnumeration(null, msgs);
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
			case PivotPackage.ENUMERATION_LITERAL__OWNING_PACKAGE:
				return eInternalContainer().eInverseRemove(this, PivotPackage.PACKAGE__OWNED_INSTANCES, org.eclipse.ocl.pivot.Package.class, msgs);
			case PivotPackage.ENUMERATION_LITERAL__OWNING_ENUMERATION:
				return eInternalContainer().eInverseRemove(this, PivotPackage.ENUMERATION__OWNED_LITERALS, Enumeration.class, msgs);
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
			case PivotPackage.ENUMERATION_LITERAL__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.ENUMERATION_LITERAL__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.ENUMERATION_LITERAL__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.ENUMERATION_LITERAL__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.ENUMERATION_LITERAL__NAME:
				return getName();
			case PivotPackage.ENUMERATION_LITERAL__CLASSES:
				return getClasses();
			case PivotPackage.ENUMERATION_LITERAL__OWNED_SLOTS:
				return getOwnedSlots();
			case PivotPackage.ENUMERATION_LITERAL__OWNED_SPECIFICATION:
				return getOwnedSpecification();
			case PivotPackage.ENUMERATION_LITERAL__OWNING_PACKAGE:
				return getOwningPackage();
			case PivotPackage.ENUMERATION_LITERAL__OWNING_ENUMERATION:
				return getOwningEnumeration();
			case PivotPackage.ENUMERATION_LITERAL__VALUE:
				return getValue();
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
			case PivotPackage.ENUMERATION_LITERAL__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.ENUMERATION_LITERAL__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.ENUMERATION_LITERAL__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.ENUMERATION_LITERAL__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.ENUMERATION_LITERAL__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.ENUMERATION_LITERAL__CLASSES:
				getClasses().clear();
				getClasses().addAll((Collection<? extends org.eclipse.ocl.pivot.Class>)newValue);
				return;
			case PivotPackage.ENUMERATION_LITERAL__OWNED_SLOTS:
				getOwnedSlots().clear();
				getOwnedSlots().addAll((Collection<? extends Slot>)newValue);
				return;
			case PivotPackage.ENUMERATION_LITERAL__OWNED_SPECIFICATION:
				setOwnedSpecification((LanguageExpression)newValue);
				return;
			case PivotPackage.ENUMERATION_LITERAL__OWNING_PACKAGE:
				setOwningPackage((org.eclipse.ocl.pivot.Package)newValue);
				return;
			case PivotPackage.ENUMERATION_LITERAL__OWNING_ENUMERATION:
				setOwningEnumeration((Enumeration)newValue);
				return;
			case PivotPackage.ENUMERATION_LITERAL__VALUE:
				setValue((Number)newValue);
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
			case PivotPackage.ENUMERATION_LITERAL__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.ENUMERATION_LITERAL__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.ENUMERATION_LITERAL__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.ENUMERATION_LITERAL__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.ENUMERATION_LITERAL__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.ENUMERATION_LITERAL__CLASSES:
				getClasses().clear();
				return;
			case PivotPackage.ENUMERATION_LITERAL__OWNED_SLOTS:
				getOwnedSlots().clear();
				return;
			case PivotPackage.ENUMERATION_LITERAL__OWNED_SPECIFICATION:
				setOwnedSpecification((LanguageExpression)null);
				return;
			case PivotPackage.ENUMERATION_LITERAL__OWNING_PACKAGE:
				setOwningPackage((org.eclipse.ocl.pivot.Package)null);
				return;
			case PivotPackage.ENUMERATION_LITERAL__OWNING_ENUMERATION:
				setOwningEnumeration((Enumeration)null);
				return;
			case PivotPackage.ENUMERATION_LITERAL__VALUE:
				setValue(VALUE_EDEFAULT);
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
			case PivotPackage.ENUMERATION_LITERAL__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.ENUMERATION_LITERAL__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.ENUMERATION_LITERAL__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.ENUMERATION_LITERAL__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.ENUMERATION_LITERAL__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.ENUMERATION_LITERAL__CLASSES:
				return classes != null && !classes.isEmpty();
			case PivotPackage.ENUMERATION_LITERAL__OWNED_SLOTS:
				return ownedSlots != null && !ownedSlots.isEmpty();
			case PivotPackage.ENUMERATION_LITERAL__OWNED_SPECIFICATION:
				return ownedSpecification != null;
			case PivotPackage.ENUMERATION_LITERAL__OWNING_PACKAGE:
				return getOwningPackage() != null;
			case PivotPackage.ENUMERATION_LITERAL__OWNING_ENUMERATION:
				return getOwningEnumeration() != null;
			case PivotPackage.ENUMERATION_LITERAL__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String toString()
	{
		return super.toString();
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitEnumerationLiteral(this);
	}

	@Override
	public @NonNull Enumerator getEnumerator() {
		EObject eTarget = getESObject();
		if (eTarget instanceof EEnumLiteral) {
			return ClassUtil.nonNullEMF(((EEnumLiteral) eTarget).getInstance());
		}
		else {
			throw new UnsupportedOperationException();			// FIXME
		}
	}

	private /*@LazyNonNull*/ EnumerationLiteralId enumerationLiteralId = null;

	@Override
	public @NonNull EnumerationLiteralId getEnumerationLiteralId() {
		EnumerationLiteralId enumerationLiteralId2 = enumerationLiteralId;
		if (enumerationLiteralId2 == null) {
			String name = ClassUtil.nonNullModel(getName());
			EnumerationId enumerationId = getOwningEnumeration().getEnumerationId();
			enumerationLiteralId = enumerationLiteralId2 = enumerationId.getEnumerationLiteralId(name);
		}
		return enumerationLiteralId2;
	}
} //EnumerationLiteralImpl
