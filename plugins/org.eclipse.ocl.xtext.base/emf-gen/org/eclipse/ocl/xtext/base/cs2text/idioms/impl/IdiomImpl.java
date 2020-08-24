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

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Idiom;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomMatch;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage;
import org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Idiom</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomImpl#getOwnedSubIdioms <em>Owned Sub Idioms</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IdiomImpl extends EObjectImpl implements Idiom
{
	/**
	 * The number of structural features of the '<em>Idiom</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int IDIOM_FEATURE_COUNT = 2;


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
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;


	/**
	 * The cached value of the '{@link #getOwnedSubIdioms() <em>Owned Sub Idioms</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSubIdioms()
	 * @generated
	 * @ordered
	 */
	protected EList<SubIdiom> ownedSubIdioms;


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IdiomImpl()
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
		return IdiomsPackage.Literals.IDIOM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName()
	{
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName)
	{
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 0, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<SubIdiom> getOwnedSubIdioms()
	{
		if (ownedSubIdioms == null)
		{
			ownedSubIdioms = new EObjectContainmentEList<SubIdiom>(SubIdiom.class, this, 1);
		}
		return ownedSubIdioms;
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
			case 1:
				return ((InternalEList<?>)getOwnedSubIdioms()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
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
			case 0:
				return getName();
			case 1:
				return getOwnedSubIdioms();
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
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case 0:
				setName((String)newValue);
				return;
			case 1:
				getOwnedSubIdioms().clear();
				getOwnedSubIdioms().addAll((Collection<? extends SubIdiom>)newValue);
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
			case 0:
				setName(NAME_EDEFAULT);
				return;
			case 1:
				getOwnedSubIdioms().clear();
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
			case 0:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case 1:
				return ownedSubIdioms != null && !ownedSubIdioms.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	protected @NonNull IdiomMatch createIdiomMatch(@NonNull SerializationNode serializationNode) {
		return new IdiomMatch(this, serializationNode);
	}

	public @NonNull SubIdiom getSubidiom(int subIdiomIndex) {
		return ownedSubIdioms.get(subIdiomIndex);
	}

/*	public @NonNull SubIdiom @NonNull [] getSubIdioms() {
		return subIdioms;
	} */

	@Override
	public @Nullable IdiomMatch firstMatch(SerializationNode serializationNode, SerializationRuleAnalysis serializationRule) {
		if (!ownedSubIdioms.get(0).matches(serializationNode, serializationRule)) {
			return null;
		}
		IdiomMatch idiomMatch = createIdiomMatch(serializationNode);
	//	idiomMatch.nextMatch(serializationNode, serializationRule);		// Opportunity for X ... X formatting
		return idiomMatch;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("{");
		boolean isFirst = true;
		for (@NonNull SubIdiom subIdiom: ownedSubIdioms) {
			if (!isFirst) {
				s.append(",");
			}
			s.append(subIdiom.toString());
			isFirst = false;
		}
		s.append("}");
		return s.toString();
	}

	public static class DebugIdiom extends IdiomImpl
	{
		@Override
		protected @NonNull IdiomMatch createIdiomMatch(@NonNull SerializationNode serializationNode) {
			return new IdiomMatch(this, serializationNode) {

				@Override
				public boolean installIn(@NonNull Map<@NonNull SerializationNode, @NonNull SubIdiom> serializationNode2subIdiom) {
					return super.installIn(serializationNode2subIdiom);
				}

				@Override
				public boolean nextMatch(@NonNull SerializationNode serializationNode, @NonNull SerializationRuleAnalysis serializationRule) {
					return super.nextMatch(serializationNode, serializationRule);
				}

			};
		}

		@Override
		public @Nullable IdiomMatch firstMatch(SerializationNode serializationNode, SerializationRuleAnalysis serializationRule) {
			IdiomMatch firstMatch = super.firstMatch(serializationNode, serializationRule);
			if (firstMatch != null) {
				getClass(); 		// XXX debugging
			}
			return firstMatch;
		}

	}

} //IdiomImpl
