/*******************************************************************************
 * Copyright (c) 2012, 2015 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.basecs.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.BaseCSTables;
import org.eclipse.ocl.xtext.basecs.PathElementCS;
import org.eclipse.ocl.xtext.basecs.PathNameCS;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Simple Named Element Ref CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.PathElementCSImpl#getElementType <em>Element Type</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.PathElementCSImpl#getOwningPathName <em>Owning Path Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.PathElementCSImpl#getReferredElement <em>Referred Element</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.PathElementCSImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PathElementCSImpl extends ElementCSImpl implements PathElementCS
{
	/**
	 * The cached value of the '{@link #getElementType() <em>Element Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementType()
	 * @generated
	 * @ordered
	 */
	protected EClassifier elementType;

	/**
	 * The cached value of the '{@link #getReferredElement() <em>Referred Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredElement()
	 * @generated
	 * @ordered
	 */
	protected Element referredElement;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PathElementCSImpl()
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
		return BaseCSPackage.Literals.PATH_ELEMENT_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PathNameCS getOwningPathName()
	{
		if (eContainerFeatureID() != BaseCSPackage.PATH_ELEMENT_CS__OWNING_PATH_NAME) return null;
		return (PathNameCS)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningPathName(PathNameCS newOwningPathName, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningPathName, BaseCSPackage.PATH_ELEMENT_CS__OWNING_PATH_NAME, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningPathName(PathNameCS newOwningPathName)
	{
		if (newOwningPathName != eInternalContainer() || (eContainerFeatureID() != BaseCSPackage.PATH_ELEMENT_CS__OWNING_PATH_NAME && newOwningPathName != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningPathName))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningPathName != null)
				msgs = ((InternalEObject)newOwningPathName).eInverseAdd(this, BaseCSPackage.PATH_NAME_CS__OWNED_PATH_ELEMENTS, PathNameCS.class, msgs);
			msgs = basicSetOwningPathName(newOwningPathName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.PATH_ELEMENT_CS__OWNING_PATH_NAME, newOwningPathName, newOwningPathName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReferredElement(Element newReferredElement)
	{
		Element oldReferredElement = referredElement;
		referredElement = newReferredElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.PATH_ELEMENT_CS__REFERRED_ELEMENT, oldReferredElement, referredElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName()
	{
		/**
		 * referredElement.oclAsType(NamedElement).name
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_NamedElement_0 = idResolver.getClass(BaseCSTables.CLSSid_NamedElement, null);
		@SuppressWarnings("null")
		final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull Element referredElement = this.getReferredElement();
		final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull NamedElement oclAsType = ClassUtil.nonNullState((NamedElement)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, referredElement, TYP_NamedElement_0));
		final /*@Thrown*/ java.lang.@Nullable String name = oclAsType.getName();
		if (name == null) {
		    throw new InvalidValueException("Null body for \'basecs::PathElementCS::name\'");
		}
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName)
	{
		// TODO: implement this method to set the 'Name' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClassifier getElementType()
	{
		if (elementType != null && elementType.eIsProxy())
		{
			InternalEObject oldElementType = (InternalEObject)elementType;
			elementType = (EClassifier)eResolveProxy(oldElementType);
			if (elementType != oldElementType)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BaseCSPackage.PATH_ELEMENT_CS__ELEMENT_TYPE, oldElementType, elementType));
			}
		}
		return elementType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClassifier basicGetElementType()
	{
		return elementType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setElementType(EClassifier newElementType)
	{
		EClassifier oldElementType = elementType;
		elementType = newElementType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BaseCSPackage.PATH_ELEMENT_CS__ELEMENT_TYPE, oldElementType, elementType));
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
			case BaseCSPackage.PATH_ELEMENT_CS__OWNING_PATH_NAME:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningPathName((PathNameCS)otherEnd, msgs);
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
			case BaseCSPackage.PATH_ELEMENT_CS__OWNING_PATH_NAME:
				return basicSetOwningPathName(null, msgs);
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
			case BaseCSPackage.PATH_ELEMENT_CS__OWNING_PATH_NAME:
				return eInternalContainer().eInverseRemove(this, BaseCSPackage.PATH_NAME_CS__OWNED_PATH_ELEMENTS, PathNameCS.class, msgs);
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
			case BaseCSPackage.PATH_ELEMENT_CS__ELEMENT_TYPE:
				if (resolve) return getElementType();
				return basicGetElementType();
			case BaseCSPackage.PATH_ELEMENT_CS__OWNING_PATH_NAME:
				return getOwningPathName();
			case BaseCSPackage.PATH_ELEMENT_CS__REFERRED_ELEMENT:
				if (resolve) return getReferredElement();
				return basicGetReferredElement();
			case BaseCSPackage.PATH_ELEMENT_CS__NAME:
				return getName();
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
			case BaseCSPackage.PATH_ELEMENT_CS__ELEMENT_TYPE:
				setElementType((EClassifier)newValue);
				return;
			case BaseCSPackage.PATH_ELEMENT_CS__OWNING_PATH_NAME:
				setOwningPathName((PathNameCS)newValue);
				return;
			case BaseCSPackage.PATH_ELEMENT_CS__REFERRED_ELEMENT:
				setReferredElement((Element)newValue);
				return;
			case BaseCSPackage.PATH_ELEMENT_CS__NAME:
				setName((String)newValue);
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
			case BaseCSPackage.PATH_ELEMENT_CS__ELEMENT_TYPE:
				setElementType((EClassifier)null);
				return;
			case BaseCSPackage.PATH_ELEMENT_CS__OWNING_PATH_NAME:
				setOwningPathName((PathNameCS)null);
				return;
			case BaseCSPackage.PATH_ELEMENT_CS__REFERRED_ELEMENT:
				setReferredElement((Element)null);
				return;
			case BaseCSPackage.PATH_ELEMENT_CS__NAME:
				setName(NAME_EDEFAULT);
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
			case BaseCSPackage.PATH_ELEMENT_CS__ELEMENT_TYPE:
				return elementType != null;
			case BaseCSPackage.PATH_ELEMENT_CS__OWNING_PATH_NAME:
				return getOwningPathName() != null;
			case BaseCSPackage.PATH_ELEMENT_CS__REFERRED_ELEMENT:
				return referredElement != null;
			case BaseCSPackage.PATH_ELEMENT_CS__NAME:
				return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
		}
		return super.eIsSet(featureID);
	}
	
	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull BaseCSVisitor<R> visitor) {
		return visitor.visitPathElementCS(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Element getReferredElement() {
		if (referredElement != null && referredElement.eIsProxy())
		{
			InternalEObject oldReferredElement = (InternalEObject)referredElement;
			referredElement = (Element)eResolveProxy(oldReferredElement);
			if (referredElement != oldReferredElement)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BaseCSPackage.PATH_ELEMENT_CS__REFERRED_ELEMENT, oldReferredElement, referredElement));
			}
		}
		return referredElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Element basicGetReferredElement()
	{
		return referredElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Element getPivot() {
		return getReferredElement();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean isType() {
		return elementType == PivotPackage.Literals.TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void resetPivot() {}
} //SimpleNamedElementRefCSImpl
