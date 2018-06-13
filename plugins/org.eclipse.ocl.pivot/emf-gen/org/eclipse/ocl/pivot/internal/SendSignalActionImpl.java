/*******************************************************************************
 * Copyright (c) 2010, 2015 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.SendSignalAction;
import org.eclipse.ocl.pivot.Signal;
import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Send Signal Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.SendSignalActionImpl#getSignal <em>Signal</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SendSignalActionImpl
		extends NamedElementImpl
		implements SendSignalAction {

	/**
	 * The cached value of the '{@link #getSignal() <em>Signal</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignal()
	 * @generated
	 * @ordered
	 */
	protected Signal signal;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SendSignalActionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.SEND_SIGNAL_ACTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Signal getSignal() {
		if (signal != null && signal.eIsProxy())
		{
			InternalEObject oldSignal = (InternalEObject)signal;
			signal = (Signal)eResolveProxy(oldSignal);
			if (signal != oldSignal)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.SEND_SIGNAL_ACTION__SIGNAL, oldSignal, signal));
			}
		}
		return signal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Signal basicGetSignal() {
		return signal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSignal(Signal newSignal) {
		Signal oldSignal = signal;
		signal = newSignal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.SEND_SIGNAL_ACTION__SIGNAL, oldSignal, signal));
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
			case PivotPackage.SEND_SIGNAL_ACTION__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.SEND_SIGNAL_ACTION__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.SEND_SIGNAL_ACTION__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.SEND_SIGNAL_ACTION__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.SEND_SIGNAL_ACTION__NAME:
				return getName();
			case PivotPackage.SEND_SIGNAL_ACTION__SIGNAL:
				if (resolve) return getSignal();
				return basicGetSignal();
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
			case PivotPackage.SEND_SIGNAL_ACTION__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.SEND_SIGNAL_ACTION__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.SEND_SIGNAL_ACTION__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.SEND_SIGNAL_ACTION__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.SEND_SIGNAL_ACTION__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.SEND_SIGNAL_ACTION__SIGNAL:
				setSignal((Signal)newValue);
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
			case PivotPackage.SEND_SIGNAL_ACTION__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.SEND_SIGNAL_ACTION__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.SEND_SIGNAL_ACTION__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.SEND_SIGNAL_ACTION__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.SEND_SIGNAL_ACTION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.SEND_SIGNAL_ACTION__SIGNAL:
				setSignal((Signal)null);
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
			case PivotPackage.SEND_SIGNAL_ACTION__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.SEND_SIGNAL_ACTION__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.SEND_SIGNAL_ACTION__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.SEND_SIGNAL_ACTION__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.SEND_SIGNAL_ACTION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.SEND_SIGNAL_ACTION__SIGNAL:
				return signal != null;
		}
		return eDynamicIsSet(featureID);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitSendSignalAction(this);
	}
} //SendSignalActionImpl
