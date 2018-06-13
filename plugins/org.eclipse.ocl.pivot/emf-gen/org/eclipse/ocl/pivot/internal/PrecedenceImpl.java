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
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.AssociativityKind;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Precedence;
import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Precedence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.PrecedenceImpl#getAssociativity <em>Associativity</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.PrecedenceImpl#getOrder <em>Order</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PrecedenceImpl
		extends NamedElementImpl
		implements Precedence {

	/**
	 * The default value of the '{@link #getAssociativity() <em>Associativity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociativity()
	 * @generated
	 * @ordered
	 */
	protected static final AssociativityKind ASSOCIATIVITY_EDEFAULT = AssociativityKind.LEFT;

	/**
	 * The offset of the flags representing the value of the '{@link #getAssociativity() <em>Associativity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected static final int ASSOCIATIVITY_EFLAG_OFFSET = 8;

	/**
	 * The flags representing the default value of the '{@link #getAssociativity() <em>Associativity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected static final int ASSOCIATIVITY_EFLAG_DEFAULT = ASSOCIATIVITY_EDEFAULT.ordinal() << ASSOCIATIVITY_EFLAG_OFFSET;

	/**
	 * The array of enumeration values for '{@link AssociativityKind Associativity Kind}'
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	private static final AssociativityKind[] ASSOCIATIVITY_EFLAG_VALUES = AssociativityKind.values();

	/**
	 * The flag representing the value of the '{@link #getAssociativity() <em>Associativity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociativity()
	 * @generated
	 * @ordered
	 */
	protected static final int ASSOCIATIVITY_EFLAG = 1 << ASSOCIATIVITY_EFLAG_OFFSET;

	/**
	 * The default value of the '{@link #getOrder() <em>Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrder()
	 * @generated
	 * @ordered
	 */
	protected static final Number ORDER_EDEFAULT = (Number)PivotFactory.eINSTANCE.createFromString(PivotPackage.eINSTANCE.getInteger(), "0");

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PrecedenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.PRECEDENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AssociativityKind getAssociativity() {
		return ASSOCIATIVITY_EFLAG_VALUES[(eFlags & ASSOCIATIVITY_EFLAG) >>> ASSOCIATIVITY_EFLAG_OFFSET];
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAssociativity(AssociativityKind newAssociativity) {
		AssociativityKind oldAssociativity = ASSOCIATIVITY_EFLAG_VALUES[(eFlags & ASSOCIATIVITY_EFLAG) >>> ASSOCIATIVITY_EFLAG_OFFSET];
		if (newAssociativity == null) newAssociativity = ASSOCIATIVITY_EDEFAULT;
		eFlags = eFlags & ~ASSOCIATIVITY_EFLAG | newAssociativity.ordinal() << ASSOCIATIVITY_EFLAG_OFFSET;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PRECEDENCE__ASSOCIATIVITY, oldAssociativity, newAssociativity));
	}

	/**
	 * The cached value of the '{@link #getOrder() <em>Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrder()
	 * @generated
	 * @ordered
	 */
	protected Number order = ORDER_EDEFAULT; // FIXME a) use int, b) get not volatile to work from UML.

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Number getOrder() {
		return order;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOrder(Number newOrder)
	{
		Number oldOrder = order;
		order = newOrder;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.PRECEDENCE__ORDER, oldOrder, order));
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
			case PivotPackage.PRECEDENCE__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.PRECEDENCE__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.PRECEDENCE__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.PRECEDENCE__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.PRECEDENCE__NAME:
				return getName();
			case PivotPackage.PRECEDENCE__ASSOCIATIVITY:
				return getAssociativity();
			case PivotPackage.PRECEDENCE__ORDER:
				return getOrder();
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
			case PivotPackage.PRECEDENCE__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.PRECEDENCE__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.PRECEDENCE__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.PRECEDENCE__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.PRECEDENCE__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.PRECEDENCE__ASSOCIATIVITY:
				setAssociativity((AssociativityKind)newValue);
				return;
			case PivotPackage.PRECEDENCE__ORDER:
				setOrder((Number)newValue);
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
			case PivotPackage.PRECEDENCE__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.PRECEDENCE__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.PRECEDENCE__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.PRECEDENCE__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.PRECEDENCE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.PRECEDENCE__ASSOCIATIVITY:
				setAssociativity(ASSOCIATIVITY_EDEFAULT);
				return;
			case PivotPackage.PRECEDENCE__ORDER:
				setOrder(ORDER_EDEFAULT);
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
			case PivotPackage.PRECEDENCE__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.PRECEDENCE__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.PRECEDENCE__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.PRECEDENCE__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.PRECEDENCE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.PRECEDENCE__ASSOCIATIVITY:
				return (eFlags & ASSOCIATIVITY_EFLAG) != ASSOCIATIVITY_EFLAG_DEFAULT;
			case PivotPackage.PRECEDENCE__ORDER:
				return ORDER_EDEFAULT == null ? order != null : !ORDER_EDEFAULT.equals(order);
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
		return visitor.visitPrecedence(this);
	}

} //PrecedenceImpl
