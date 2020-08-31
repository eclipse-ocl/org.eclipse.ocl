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
package org.eclipse.ocl.xtext.base.cs2text.idioms.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedKeywordSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.UnassignedKeywordSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage;
import org.eclipse.ocl.xtext.base.cs2text.idioms.KeywordLocator;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;
import org.eclipse.xtext.util.Strings;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Keyword Locator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.KeywordLocatorImpl#getString <em>String</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.KeywordLocatorImpl#getInEClass <em>In EClass</em>}</li>
 * </ul>
 *
 * @generated
 */
public class KeywordLocatorImpl extends LocatorImpl implements KeywordLocator
{
	/**
	 * The number of structural features of the '<em>Keyword Locator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int KEYWORD_LOCATOR_FEATURE_COUNT = LocatorImpl.LOCATOR_FEATURE_COUNT + 2;


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
	 * The cached value of the '{@link #getInEClass() <em>In EClass</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInEClass()
	 * @generated
	 * @ordered
	 */
	protected EClass inEClass;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected KeywordLocatorImpl()
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
		return IdiomsPackage.Literals.KEYWORD_LOCATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getString()
	{
		return string;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setString(String newString)
	{
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
	public EClass getInEClass()
	{
		if (inEClass != null && inEClass.eIsProxy())
		{
			InternalEObject oldInEClass = (InternalEObject)inEClass;
			inEClass = (EClass)eResolveProxy(oldInEClass);
			if (inEClass != oldInEClass)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 2, oldInEClass, inEClass));
			}
		}
		return inEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass basicGetInEClass()
	{
		return inEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInEClass(EClass newInEClass)
	{
		EClass oldInEClass = inEClass;
		inEClass = newInEClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 2, oldInEClass, inEClass));
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
			case 1:
				return getString();
			case 2:
				if (resolve) return getInEClass();
				return basicGetInEClass();
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
			case 1:
				setString((String)newValue);
				return;
			case 2:
				setInEClass((EClass)newValue);
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
			case 1:
				setString(STRING_EDEFAULT);
				return;
			case 2:
				setInEClass((EClass)null);
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
			case 1:
				return STRING_EDEFAULT == null ? string != null : !STRING_EDEFAULT.equals(string);
			case 2:
				return inEClass != null;
		}
		return super.eIsSet(featureID);
	}

	@Override
	public boolean matches(SerializationNode serializationNode, SerializationRuleAnalysis serializationRule) {
		String value = null;
		if (serializationNode instanceof AssignedKeywordSerializationNode) {
			value = ((AssignedKeywordSerializationNode)serializationNode).getValue();
		}
		else if (serializationNode instanceof UnassignedKeywordSerializationNode) {
			value = ((UnassignedKeywordSerializationNode)serializationNode).getValue();
		}
		if (!string.equals(value)) {
			return false;
		}
		if (":".equals(string) && (inEClass != null)) {
			getClass();
		}
		if ((inEClass != null) && !XtextGrammarUtil.isSuperTypeOf(inEClass, serializationRule.getProducedEClass())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("'");
		s.append(Strings.convertToJavaString(string));
		s.append("'");
		if (inEClass != null) {
			s.append(" in ");
			s.append(inEClass.getEPackage().getName());
			s.append("::");
			s.append(inEClass.getName());
		}
		return s.toString();
	}
} //KeywordLocatorImpl
