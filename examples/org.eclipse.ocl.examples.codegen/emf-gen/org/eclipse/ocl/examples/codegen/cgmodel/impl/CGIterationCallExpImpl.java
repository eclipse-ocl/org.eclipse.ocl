/*******************************************************************************
 * Copyright (c) 2013, 2019 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.cgmodel.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cse.AbstractPlace;
import org.eclipse.ocl.examples.codegen.cse.InnerStackPlace;
import org.eclipse.ocl.examples.codegen.utilities.EquivalenceUtil;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CG Iteration Call Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGIterationCallExpImpl#getReferredIteration <em>Referred Iteration</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGIterationCallExpImpl#getIterators <em>Iterators</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGIterationCallExpImpl#getCoIterators <em>Co Iterators</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGIterationCallExpImpl#getBody <em>Body</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGIterationCallExpImpl#getAsIteration <em>As Iteration</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class CGIterationCallExpImpl extends CGSourcedCallExpImpl implements CGIterationCallExp {
	/**
	 * The number of structural features of the '<em>CG Iteration Call Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CG_ITERATION_CALL_EXP_FEATURE_COUNT = CGSourcedCallExpImpl.CG_SOURCED_CALL_EXP_FEATURE_COUNT + 5;

	/**
	 * The cached value of the '{@link #getReferredIteration() <em>Referred Iteration</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredIteration()
	 * @generated
	 * @ordered
	 */
	protected CGOperation referredIteration;

	/**
	 * The cached value of the '{@link #getIterators() <em>Iterators</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIterators()
	 * @generated
	 * @ordered
	 */
	protected EList<CGIterator> iterators;

	/**
	 * The cached value of the '{@link #getCoIterators() <em>Co Iterators</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoIterators()
	 * @generated
	 * @ordered
	 */
	protected EList<CGIterator> coIterators;

	/**
	 * The cached value of the '{@link #getBody() <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBody()
	 * @generated
	 * @ordered
	 */
	protected CGValuedElement body;

	/**
	 * The default value of the '{@link #getAsIteration() <em>As Iteration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAsIteration()
	 * @generated
	 * @ordered
	 */
	protected static final Iteration AS_ITERATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAsIteration() <em>As Iteration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAsIteration()
	 * @generated
	 * @ordered
	 */
	protected Iteration asIteration = AS_ITERATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CGIterationCallExpImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CGModelPackage.Literals.CG_ITERATION_CALL_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CGOperation getReferredIteration() {
		return referredIteration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReferredIteration(CGOperation newReferredIteration) {
		CGOperation oldReferredIteration = referredIteration;
		referredIteration = newReferredIteration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 8, oldReferredIteration, referredIteration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Iteration getAsIteration() {
		return asIteration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAsIteration(Iteration newAsIteration) {
		Iteration oldAsIteration = asIteration;
		asIteration = newAsIteration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 12, oldAsIteration, asIteration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("null")
	public @NonNull List<CGIterator> getIterators() {
		if (iterators == null) {
			iterators = new EObjectContainmentEList<CGIterator>(CGIterator.class, this, 9);
		}
		return iterators;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CGValuedElement getBody() {
		return body;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBody(CGValuedElement newBody, NotificationChain msgs) {
		CGValuedElement oldBody = body;
		body = newBody;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, 11, oldBody, newBody);
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
	public void setBody(CGValuedElement newBody) {
		if (newBody != body) {
			NotificationChain msgs = null;
			if (body != null)
				msgs = ((InternalEObject)body).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (11), null, msgs);
			if (newBody != null)
				msgs = ((InternalEObject)newBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (11), null, msgs);
			msgs = basicSetBody(newBody, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 11, newBody, newBody));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<CGIterator> getCoIterators() {
		if (coIterators == null) {
			coIterators = new EObjectContainmentEList<CGIterator>(CGIterator.class, this, 10);
		}
		return coIterators;
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
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable AbstractPlace getPlace(@NonNull CodeGenAnalyzer analyzer, @NonNull Map<@Nullable CGElement, @NonNull AbstractPlace> element2place) {
		return InnerStackPlace.createInnerStackPlace(analyzer, element2place, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case 9:
				return ((InternalEList<?>)getIterators()).basicRemove(otherEnd, msgs);
			case 10:
				return ((InternalEList<?>)getCoIterators()).basicRemove(otherEnd, msgs);
			case 11:
				return basicSetBody(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case 8:
				return getReferredIteration();
			case 9:
				return getIterators();
			case 10:
				return getCoIterators();
			case 11:
				return getBody();
			case 12:
				return getAsIteration();
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
		switch (featureID) {
			case 8:
				setReferredIteration((CGOperation)newValue);
				return;
			case 9:
				getIterators().clear();
				getIterators().addAll((Collection<? extends CGIterator>)newValue);
				return;
			case 10:
				getCoIterators().clear();
				getCoIterators().addAll((Collection<? extends CGIterator>)newValue);
				return;
			case 11:
				setBody((CGValuedElement)newValue);
				return;
			case 12:
				setAsIteration((Iteration)newValue);
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
			case 8:
				setReferredIteration((CGOperation)null);
				return;
			case 9:
				getIterators().clear();
				return;
			case 10:
				getCoIterators().clear();
				return;
			case 11:
				setBody((CGValuedElement)null);
				return;
			case 12:
				setAsIteration(AS_ITERATION_EDEFAULT);
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
			case 8:
				return referredIteration != null;
			case 9:
				return iterators != null && !iterators.isEmpty();
			case 10:
				return coIterators != null && !coIterators.isEmpty();
			case 11:
				return body != null;
			case 12:
				return AS_ITERATION_EDEFAULT == null ? asIteration != null : !AS_ITERATION_EDEFAULT.equals(asIteration);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable Boolean isEquivalentToInternal(@NonNull CGValuedElement thatValue) {
		return (getClass() == thatValue.getClass()) ? EquivalenceUtil.isEquivalent(this, (CGIterationCallExp)thatValue) : null;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isNonInvalid() {
		if (asIteration == null) {
			return false;
		}
		if (asIteration.isIsValidating()) {
			if (asIteration.isIsInvalidating()) {
				// e.g AND, forAll - nonInvalid if all inputs nonInvalid
			}
			else {
				return true;				// e.g oclIsInvalid
			}
		}
		else {
			if (asIteration.isIsInvalidating()) {
				return false;				// e.g divide-by-zero
			}
			else {
				// normal use case - nonInvalid if all inputs nonInvalid
			}
		}
		if (!source.isRequiredOrNonNull() || !source.isNonInvalid()) {
			return false;
		}
		for (@NonNull CGValuedElement iterator : ClassUtil.nullFree(getIterators())) {
			if (!iterator.isRequiredOrNonNull() || !iterator.isNonInvalid()) {
				return false;
			}
		}
		if ((body == null) || !body.isRequiredOrNonNull() || !body.isNonInvalid()) {
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isNonNull() {
		return (asIteration != null) && asIteration.isIsRequired();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isNull() {
		return false;
	}

} //CGIterationCallExpImpl
