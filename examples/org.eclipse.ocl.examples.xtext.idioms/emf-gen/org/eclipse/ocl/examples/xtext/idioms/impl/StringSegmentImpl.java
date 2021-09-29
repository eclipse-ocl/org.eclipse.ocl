/**
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.examples.xtext.idioms.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage;
import org.eclipse.ocl.examples.xtext.idioms.StringSegment;
import org.eclipse.xtext.util.Strings;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>String Segment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.StringSegmentImpl#isPrintable <em>Printable</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.StringSegmentImpl#getString <em>String</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StringSegmentImpl
		extends SegmentImpl
		implements StringSegment {

	/**
	 * The number of structural features of the '<em>String Segment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_SEGMENT_FEATURE_COUNT = SegmentImpl.SEGMENT_FEATURE_COUNT + 2;

	/**
	 * The default value of the '{@link #isPrintable() <em>Printable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrintable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PRINTABLE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isPrintable() <em>Printable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrintable()
	 * @generated
	 * @ordered
	 */
	protected boolean printable = PRINTABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getString() <em>String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getString()
	 * @generated
	 * @ordered
	 */
	protected static final String STRING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getString() <em>String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getString()
	 * @generated
	 * @ordered
	 */
	protected String string = STRING_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StringSegmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdiomsPackage.Literals.STRING_SEGMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isPrintable() {
		return printable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPrintable(boolean newPrintable) {
		boolean oldPrintable = printable;
		printable = newPrintable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 0, oldPrintable, printable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getString() {
		return string;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setString(String newString) {
		String oldString = string;
		string = newString;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 1, oldString, string));
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
			case 0:
				return isPrintable();
			case 1:
				return getString();
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
		switch (featureID)
		{
			case 0:
				setPrintable((Boolean)newValue);
				return;
			case 1:
				setString((String)newValue);
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
		switch (featureID)
		{
			case 0:
				setPrintable(PRINTABLE_EDEFAULT);
				return;
			case 1:
				setString(STRING_EDEFAULT);
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
		switch (featureID)
		{
			case 0:
				return printable != PRINTABLE_EDEFAULT;
			case 1:
				return STRING_EDEFAULT == null ? string != null : !STRING_EDEFAULT.equals(string);
		}
		return super.eIsSet(featureID);
	}

	@Override
	public String toString() {
		return printable
			? string
			: Strings.convertToJavaString(string);
	}
} //StringSegmentImpl
