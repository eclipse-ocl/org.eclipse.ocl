/*******************************************************************************
 * Copyright (c) 2005, 2024 IBM Corporation, Zeligsoft Inc., and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   Zeligsoft - Bug 243976
 *******************************************************************************/
package org.eclipse.ocl.cst.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ocl.cst.BooleanLiteralExpCS;
import org.eclipse.ocl.cst.CSTPackage;
import org.eclipse.ocl.cst.LiteralExpCS;
import org.eclipse.ocl.cst.PrimitiveLiteralExpCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Boolean Literal Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.cst.impl.BooleanLiteralExpCSImpl#getSymbol <em>Symbol</em>}</li>
 *   <li>{@link org.eclipse.ocl.cst.impl.BooleanLiteralExpCSImpl#getBooleanSymbol <em>Boolean Symbol</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BooleanLiteralExpCSImpl
		extends SimpleNameCSImpl
		implements BooleanLiteralExpCS {

	/**
	 * The default value of the '{@link #getSymbol() <em>Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 3.0
	 * <!-- end-user-doc -->
	 * @see #getSymbol()
	 * @generated
	 * @ordered
	 */
	protected static final String SYMBOL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSymbol() <em>Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 3.0
	 * <!-- end-user-doc -->
	 * @see #getSymbol()
	 * @generated
	 * @ordered
	 */
	protected String symbol = SYMBOL_EDEFAULT;

	/**
	 * The default value of the '{@link #getBooleanSymbol() <em>Boolean Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBooleanSymbol()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean BOOLEAN_SYMBOL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBooleanSymbol() <em>Boolean Symbol</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBooleanSymbol()
	 * @generated
	 * @ordered
	 */
	protected Boolean booleanSymbol = BOOLEAN_SYMBOL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BooleanLiteralExpCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CSTPackage.Literals.BOOLEAN_LITERAL_EXP_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSymbol() {
		return symbol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSymbol(String newSymbol) {
		String oldSymbol = symbol;
		symbol = newSymbol;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
				CSTPackage.BOOLEAN_LITERAL_EXP_CS__SYMBOL, oldSymbol, symbol));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getBooleanSymbol() {
		return booleanSymbol;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBooleanSymbol(Boolean newBooleanSymbol) {
		Boolean oldBooleanSymbol = booleanSymbol;
		booleanSymbol = newBooleanSymbol;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
				CSTPackage.BOOLEAN_LITERAL_EXP_CS__BOOLEAN_SYMBOL,
				oldBooleanSymbol, booleanSymbol));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CSTPackage.BOOLEAN_LITERAL_EXP_CS__SYMBOL :
				return getSymbol();
			case CSTPackage.BOOLEAN_LITERAL_EXP_CS__BOOLEAN_SYMBOL :
				return getBooleanSymbol();
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
			case CSTPackage.BOOLEAN_LITERAL_EXP_CS__SYMBOL :
				setSymbol((String) newValue);
				return;
			case CSTPackage.BOOLEAN_LITERAL_EXP_CS__BOOLEAN_SYMBOL :
				setBooleanSymbol((Boolean) newValue);
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
			case CSTPackage.BOOLEAN_LITERAL_EXP_CS__SYMBOL :
				setSymbol(SYMBOL_EDEFAULT);
				return;
			case CSTPackage.BOOLEAN_LITERAL_EXP_CS__BOOLEAN_SYMBOL :
				setBooleanSymbol(BOOLEAN_SYMBOL_EDEFAULT);
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
			case CSTPackage.BOOLEAN_LITERAL_EXP_CS__SYMBOL :
				return SYMBOL_EDEFAULT == null
					? symbol != null
					: !SYMBOL_EDEFAULT.equals(symbol);
			case CSTPackage.BOOLEAN_LITERAL_EXP_CS__BOOLEAN_SYMBOL :
				return BOOLEAN_SYMBOL_EDEFAULT == null
					? booleanSymbol != null
					: !BOOLEAN_SYMBOL_EDEFAULT.equals(booleanSymbol);
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
		if (baseClass == LiteralExpCS.class) {
			switch (derivedFeatureID) {
				default :
					return -1;
			}
		}
		if (baseClass == PrimitiveLiteralExpCS.class) {
			switch (derivedFeatureID) {
				case CSTPackage.BOOLEAN_LITERAL_EXP_CS__SYMBOL :
					return CSTPackage.PRIMITIVE_LITERAL_EXP_CS__SYMBOL;
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
		if (baseClass == LiteralExpCS.class) {
			switch (baseFeatureID) {
				default :
					return -1;
			}
		}
		if (baseClass == PrimitiveLiteralExpCS.class) {
			switch (baseFeatureID) {
				case CSTPackage.PRIMITIVE_LITERAL_EXP_CS__SYMBOL :
					return CSTPackage.BOOLEAN_LITERAL_EXP_CS__SYMBOL;
				default :
					return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (symbol: "); //$NON-NLS-1$
		result.append(symbol);
		result.append(", booleanSymbol: "); //$NON-NLS-1$
		result.append(booleanSymbol);
		result.append(')');
		return result.toString();
	}

} //BooleanLiteralExpCSImpl
