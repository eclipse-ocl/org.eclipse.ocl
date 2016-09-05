/*******************************************************************************
 * Copyright (c) 2014, 2016 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.cgmodel.impl;

import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import org.eclipse.ocl.examples.codegen.cgmodel.CGCallable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;

import org.eclipse.ocl.examples.codegen.cgmodel.util.CGModelVisitor;

import org.eclipse.ocl.examples.codegen.cse.AbstractPlace;
import org.eclipse.ocl.examples.codegen.cse.StackPlace;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Variable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CG Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGParameterImpl#getCallable <em>Callable</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CGParameterImpl extends CGVariableImpl implements CGParameter {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CGParameterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CGModelPackage.Literals.CG_PARAMETER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CGCallable getCallable() {
		if (eContainerFeatureID() != CGModelPackage.CG_PARAMETER__CALLABLE) return null;
		return (CGCallable)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCallable(CGCallable newCallable, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCallable, CGModelPackage.CG_PARAMETER__CALLABLE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCallable(CGCallable newCallable) {
		if (newCallable != eInternalContainer() || (eContainerFeatureID() != CGModelPackage.CG_PARAMETER__CALLABLE && newCallable != null)) {
			if (EcoreUtil.isAncestor(this, newCallable))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCallable != null)
				msgs = ((InternalEObject)newCallable).eInverseAdd(this, CGModelPackage.CG_CALLABLE__PARAMETERS, CGCallable.class, msgs);
			msgs = basicSetCallable(newCallable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGModelPackage.CG_PARAMETER__CALLABLE, newCallable, newCallable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CGModelPackage.CG_PARAMETER__CALLABLE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetCallable((CGCallable)otherEnd, msgs);
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
		switch (featureID) {
			case CGModelPackage.CG_PARAMETER__CALLABLE:
				return basicSetCallable(null, msgs);
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
		switch (eContainerFeatureID()) {
			case CGModelPackage.CG_PARAMETER__CALLABLE:
				return eInternalContainer().eInverseRemove(this, CGModelPackage.CG_CALLABLE__PARAMETERS, CGCallable.class, msgs);
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
		switch (featureID) {
			case CGModelPackage.CG_PARAMETER__CALLABLE:
				return getCallable();
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
		switch (featureID) {
			case CGModelPackage.CG_PARAMETER__CALLABLE:
				setCallable((CGCallable)newValue);
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
		switch (featureID) {
			case CGModelPackage.CG_PARAMETER__CALLABLE:
				setCallable((CGCallable)null);
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
		switch (featureID) {
			case CGModelPackage.CG_PARAMETER__CALLABLE:
				return getCallable() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull CGModelVisitor<R> visitor) {
		return visitor.visitCGParameter(this);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable EClassifier getEcoreClassifier() {
		if (ast instanceof Variable) {
			Parameter asParameter = ((Variable)ast).getRepresentedParameter();
			if (asParameter != null) {
				EObject eObject = asParameter.getESObject();
				if (eObject instanceof ETypedElement) {
					return ((ETypedElement)eObject).getEType();
				}
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable CGInvalid getInvalidValue() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable AbstractPlace getPlace(@NonNull Map<@Nullable CGElement, @NonNull AbstractPlace> element2place) {
		return StackPlace.createStackPlace(element2place, this);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isBoxed() {
		CGCallable callable = getCallable();
		return (init != null) ? init.isBoxed() : callable != null ? callable.isBoxed() : true;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isConstant() {
		return (init != null) && init.isConstant();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isEcore() {
		CGCallable callable = getCallable();
		return (init != null) ? init.isEcore() : callable != null ? callable.isEcore() : false;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isGlobal() {
		return (init != null) && init.isGlobal();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isNonInvalid() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isUnboxed() {
		CGCallable callable = getCallable();
		return (init != null) ? init.isUnboxed() : callable != null ? callable.isUnboxed() : false;
	}

} //CGParameterImpl
