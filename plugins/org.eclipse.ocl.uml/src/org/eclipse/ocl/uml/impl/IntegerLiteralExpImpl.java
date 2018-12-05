/*******************************************************************************
 * Copyright (c) 2007, 2018 IBM Corporation, Zeligsoft Inc., and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   Zeligsoft - Bug 207365
 *******************************************************************************/
package org.eclipse.ocl.uml.impl;

import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ocl.expressions.ExpressionsPackage;
import org.eclipse.ocl.expressions.operations.IntegerLiteralExpOperations;
import org.eclipse.ocl.uml.IntegerLiteralExp;
import org.eclipse.ocl.uml.UMLPackage;
import org.eclipse.ocl.utilities.Visitor;
import org.eclipse.uml2.uml.Classifier;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Integer Literal Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.uml.impl.IntegerLiteralExpImpl#getIntegerSymbol <em>Integer Symbol</em>}</li>
 *   <li>{@link org.eclipse.ocl.uml.impl.IntegerLiteralExpImpl#getExtendedIntegerSymbol <em>Extended Integer Symbol</em>}</li>
 *   <li>{@link org.eclipse.ocl.uml.impl.IntegerLiteralExpImpl#getLongSymbol <em>Long Symbol</em>}</li>
 * </ul>
 *
 * @generated
 */
@SuppressWarnings("restriction")
public class IntegerLiteralExpImpl
extends NumericLiteralExpImpl
implements IntegerLiteralExp {

	/**
	 * The default value of the '{@link #getIntegerSymbol() <em>Integer Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntegerSymbol()
	 * @generated
	 * @ordered
	 */
	protected static final Integer INTEGER_SYMBOL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIntegerSymbol() <em>Integer Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntegerSymbol()
	 * @generated
	 * @ordered
	 */
	protected Integer integerSymbol = INTEGER_SYMBOL_EDEFAULT;

	/**
	 * The default value of the '{@link #getExtendedIntegerSymbol() <em>Extended Integer Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 4.0
	 * <!-- end-user-doc -->
	 * @see #getExtendedIntegerSymbol()
	 * @generated
	 * @ordered
	 */
	protected static final Long EXTENDED_INTEGER_SYMBOL_EDEFAULT = Long.valueOf(0L);

	/**
	 * The cached value of the '{@link #getExtendedIntegerSymbol() <em>Extended Integer Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 4.0
	 * <!-- end-user-doc -->
	 * @see #getExtendedIntegerSymbol()
	 * @generated
	 * @ordered
	 */
	protected Long extendedIntegerSymbol = EXTENDED_INTEGER_SYMBOL_EDEFAULT;

	/**
	 * The default value of the '{@link #getLongSymbol() <em>Long Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 4.0
	 * <!-- end-user-doc -->
	 * @see #getLongSymbol()
	 * @generated
	 * @ordered
	 */
	protected static final Long LONG_SYMBOL_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IntegerLiteralExpImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UMLPackage.Literals.INTEGER_LITERAL_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getIntegerSymbol() {
		return integerSymbol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIntegerSymbol(Integer newIntegerSymbol) {
		Integer oldIntegerSymbol = integerSymbol;
		integerSymbol = newIntegerSymbol;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
				UMLPackage.INTEGER_LITERAL_EXP__INTEGER_SYMBOL,
				oldIntegerSymbol, integerSymbol));
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 4.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Long getExtendedIntegerSymbol() {
		return extendedIntegerSymbol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 5.2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtendedIntegerSymbol(Long newExtendedIntegerSymbol) {
		Long oldExtendedIntegerSymbol = extendedIntegerSymbol;
		extendedIntegerSymbol = newExtendedIntegerSymbol;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
				UMLPackage.INTEGER_LITERAL_EXP__EXTENDED_INTEGER_SYMBOL,
				oldExtendedIntegerSymbol, extendedIntegerSymbol));
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 4.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Long getLongSymbol() {
		return extendedIntegerSymbol * (1L << Integer.SIZE)
				+ (integerSymbol != null
				? integerSymbol
					: 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 4.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setLongSymbol(Long newLongSymbol) {
		Integer oldIntegerSymbol = integerSymbol;
		Long oldExtendedIntegerSymbol = extendedIntegerSymbol;
		integerSymbol = (int) (newLongSymbol & ((1L << Integer.SIZE) - 1));
		extendedIntegerSymbol = (newLongSymbol >> Integer.SIZE)
				+ ((integerSymbol < 0)
						? 1
							: 0);
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
				UMLPackage.INTEGER_LITERAL_EXP__EXTENDED_INTEGER_SYMBOL,
				oldExtendedIntegerSymbol, extendedIntegerSymbol));
			eNotify(new ENotificationImpl(this, Notification.SET,
				UMLPackage.INTEGER_LITERAL_EXP__INTEGER_SYMBOL,
				oldIntegerSymbol, integerSymbol));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean checkIntegerType(DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return IntegerLiteralExpOperations.checkIntegerType(this, diagnostics,
			context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UMLPackage.INTEGER_LITERAL_EXP__INTEGER_SYMBOL :
				return getIntegerSymbol();
			case UMLPackage.INTEGER_LITERAL_EXP__EXTENDED_INTEGER_SYMBOL :
				return getExtendedIntegerSymbol();
			case UMLPackage.INTEGER_LITERAL_EXP__LONG_SYMBOL :
				return getLongSymbol();
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
			case UMLPackage.INTEGER_LITERAL_EXP__INTEGER_SYMBOL :
				setIntegerSymbol((Integer) newValue);
				return;
			case UMLPackage.INTEGER_LITERAL_EXP__EXTENDED_INTEGER_SYMBOL :
				setExtendedIntegerSymbol((Long) newValue);
				return;
			case UMLPackage.INTEGER_LITERAL_EXP__LONG_SYMBOL :
				setLongSymbol((Long) newValue);
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
			case UMLPackage.INTEGER_LITERAL_EXP__INTEGER_SYMBOL :
				setIntegerSymbol(INTEGER_SYMBOL_EDEFAULT);
				return;
			case UMLPackage.INTEGER_LITERAL_EXP__EXTENDED_INTEGER_SYMBOL :
				setExtendedIntegerSymbol(EXTENDED_INTEGER_SYMBOL_EDEFAULT);
				return;
			case UMLPackage.INTEGER_LITERAL_EXP__LONG_SYMBOL :
				setLongSymbol(LONG_SYMBOL_EDEFAULT);
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
			case UMLPackage.INTEGER_LITERAL_EXP__INTEGER_SYMBOL :
				return INTEGER_SYMBOL_EDEFAULT == null
				? integerSymbol != null
				: !INTEGER_SYMBOL_EDEFAULT.equals(integerSymbol);
			case UMLPackage.INTEGER_LITERAL_EXP__EXTENDED_INTEGER_SYMBOL :
				return EXTENDED_INTEGER_SYMBOL_EDEFAULT == null
				? extendedIntegerSymbol != null
				: !EXTENDED_INTEGER_SYMBOL_EDEFAULT
				.equals(extendedIntegerSymbol);
			case UMLPackage.INTEGER_LITERAL_EXP__LONG_SYMBOL :
				return LONG_SYMBOL_EDEFAULT == null
				? getLongSymbol() != null
				: !LONG_SYMBOL_EDEFAULT.equals(getLongSymbol());
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID,
			Class<?> baseClass) {
		if (baseClass == org.eclipse.ocl.expressions.IntegerLiteralExp.class) {
			switch (derivedFeatureID) {
				case UMLPackage.INTEGER_LITERAL_EXP__INTEGER_SYMBOL :
					return ExpressionsPackage.INTEGER_LITERAL_EXP__INTEGER_SYMBOL;
				case UMLPackage.INTEGER_LITERAL_EXP__EXTENDED_INTEGER_SYMBOL :
					return ExpressionsPackage.INTEGER_LITERAL_EXP__EXTENDED_INTEGER_SYMBOL;
				case UMLPackage.INTEGER_LITERAL_EXP__LONG_SYMBOL :
					return ExpressionsPackage.INTEGER_LITERAL_EXP__LONG_SYMBOL;
				default :
					return -1;
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
	public int eDerivedStructuralFeatureID(int baseFeatureID,
			Class<?> baseClass) {
		if (baseClass == org.eclipse.ocl.expressions.IntegerLiteralExp.class) {
			switch (baseFeatureID) {
				case ExpressionsPackage.INTEGER_LITERAL_EXP__INTEGER_SYMBOL :
					return UMLPackage.INTEGER_LITERAL_EXP__INTEGER_SYMBOL;
				case ExpressionsPackage.INTEGER_LITERAL_EXP__EXTENDED_INTEGER_SYMBOL :
					return UMLPackage.INTEGER_LITERAL_EXP__EXTENDED_INTEGER_SYMBOL;
				case ExpressionsPackage.INTEGER_LITERAL_EXP__LONG_SYMBOL :
					return UMLPackage.INTEGER_LITERAL_EXP__LONG_SYMBOL;
				default :
					return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T, U extends Visitor<T, ?, ?, ?, ?, ?, ?, ?, ?, ?>> T accept(U v) {
		return ((Visitor<T, Classifier, ?, ?, ?, ?, ?, ?, ?, ?>) v)
				.visitIntegerLiteralExp(this);
	}

} //IntegerLiteralExpImpl
