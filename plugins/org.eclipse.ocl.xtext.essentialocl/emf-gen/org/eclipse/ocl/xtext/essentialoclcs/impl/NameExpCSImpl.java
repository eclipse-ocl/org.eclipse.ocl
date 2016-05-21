/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.xtext.essentialoclcs.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;
import org.eclipse.ocl.xtext.essentialoclcs.ShadowExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.ShadowPartCS;
import org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage;
import org.eclipse.ocl.xtext.essentialoclcs.IterateCallExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.IterationCallExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.NameExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.OperationCallExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.PropertyCallExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.TypeNameExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.VariableCS;
import org.eclipse.ocl.xtext.essentialoclcs.VariableExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.util.EssentialOCLCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Named Element Ref CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.NameExpCSImpl#getParts <em>Parts</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.NameExpCSImpl#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.NameExpCSImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.NameExpCSImpl#getIterators <em>Iterators</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.NameExpCSImpl#getReferredIteration <em>Referred Iteration</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.NameExpCSImpl#getAccumulators <em>Accumulators</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.NameExpCSImpl#getReferredOperation <em>Referred Operation</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.NameExpCSImpl#getReferredProperty <em>Referred Property</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.essentialoclcs.impl.NameExpCSImpl#getReferredVariable <em>Referred Variable</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NameExpCSImpl
		extends AssociationClassCallExpCSImpl
		implements NameExpCS {

	/**
	 * The cached value of the '{@link #getParts() <em>Parts</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParts()
	 * @generated
	 * @ordered
	 */
	protected EList<ShadowPartCS> parts;

	/**
	 * The cached value of the '{@link #getTypeName() <em>Type Name</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeName()
	 * @generated
	 * @ordered
	 */
	protected TypeNameExpCS typeName;

	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected String value = VALUE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getIterators() <em>Iterators</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIterators()
	 * @generated
	 * @ordered
	 */
	protected EList<VariableCS> iterators;

	/**
	 * The cached value of the '{@link #getReferredIteration() <em>Referred Iteration</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredIteration()
	 * @generated
	 * @ordered
	 */
	protected Iteration referredIteration;

	/**
	 * The cached value of the '{@link #getAccumulators() <em>Accumulators</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAccumulators()
	 * @generated
	 * @ordered
	 */
	protected EList<VariableCS> accumulators;

	/**
	 * The cached value of the '{@link #getReferredOperation() <em>Referred Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredOperation()
	 * @generated
	 * @ordered
	 */
	protected Operation referredOperation;

	/**
	 * The cached value of the '{@link #getReferredProperty() <em>Referred Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredProperty()
	 * @generated
	 * @ordered
	 */
	protected Property referredProperty;

	/**
	 * The cached value of the '{@link #getReferredVariable() <em>Referred Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredVariable()
	 * @generated
	 * @ordered
	 */
	protected Variable referredVariable;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NameExpCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EssentialOCLCSPackage.Literals.NAME_EXP_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TypeNameExpCS getTypeName()
	{
		return typeName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTypeName(TypeNameExpCS newTypeName)
	{
		TypeNameExpCS oldTypeName = typeName;
		typeName = newTypeName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.NAME_EXP_CS__TYPE_NAME, oldTypeName, typeName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ShadowPartCS> getParts()
	{
		if (parts == null)
		{
			parts = new EObjectEList<ShadowPartCS>(ShadowPartCS.class, this, EssentialOCLCSPackage.NAME_EXP_CS__PARTS);
		}
		return parts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getValue()
	{
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setValue(String newValue)
	{
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.NAME_EXP_CS__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Iteration getReferredIteration()
	{
		return referredIteration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReferredIteration(Iteration newReferredIteration)
	{
		Iteration oldReferredIteration = referredIteration;
		referredIteration = newReferredIteration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_ITERATION, oldReferredIteration, referredIteration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<VariableCS> getIterators()
	{
		if (iterators == null)
		{
			iterators = new EObjectEList<VariableCS>(VariableCS.class, this, EssentialOCLCSPackage.NAME_EXP_CS__ITERATORS);
		}
		return iterators;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<VariableCS> getAccumulators()
	{
		if (accumulators == null)
		{
			accumulators = new EObjectEList<VariableCS>(VariableCS.class, this, EssentialOCLCSPackage.NAME_EXP_CS__ACCUMULATORS);
		}
		return accumulators;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Operation getReferredOperation()
	{
		return referredOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReferredOperation(Operation newReferredOperation)
	{
		Operation oldReferredOperation = referredOperation;
		referredOperation = newReferredOperation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_OPERATION, oldReferredOperation, referredOperation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Property getReferredProperty()
	{
		return referredProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReferredProperty(Property newReferredProperty)
	{
		Property oldReferredProperty = referredProperty;
		referredProperty = newReferredProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_PROPERTY, oldReferredProperty, referredProperty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Variable getReferredVariable()
	{
		return referredVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReferredVariable(Variable newReferredVariable)
	{
		Variable oldReferredVariable = referredVariable;
		referredVariable = newReferredVariable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_VARIABLE, oldReferredVariable, referredVariable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
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
	public NamedElement getNamedElement() {
		Element element = getOwnedPathName().getReferredElement();
		return element instanceof NamedElement
			? (NamedElement) element
			: null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public NameExpCS getNameExp() {
		return this;
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
			case EssentialOCLCSPackage.NAME_EXP_CS__PARTS:
				return getParts();
			case EssentialOCLCSPackage.NAME_EXP_CS__TYPE_NAME:
				return getTypeName();
			case EssentialOCLCSPackage.NAME_EXP_CS__VALUE:
				return getValue();
			case EssentialOCLCSPackage.NAME_EXP_CS__ITERATORS:
				return getIterators();
			case EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_ITERATION:
				return getReferredIteration();
			case EssentialOCLCSPackage.NAME_EXP_CS__ACCUMULATORS:
				return getAccumulators();
			case EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_OPERATION:
				return getReferredOperation();
			case EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_PROPERTY:
				return getReferredProperty();
			case EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_VARIABLE:
				return getReferredVariable();
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
		switch (featureID)
		{
			case EssentialOCLCSPackage.NAME_EXP_CS__PARTS:
				getParts().clear();
				getParts().addAll((Collection<? extends ShadowPartCS>)newValue);
				return;
			case EssentialOCLCSPackage.NAME_EXP_CS__TYPE_NAME:
				setTypeName((TypeNameExpCS)newValue);
				return;
			case EssentialOCLCSPackage.NAME_EXP_CS__VALUE:
				setValue((String)newValue);
				return;
			case EssentialOCLCSPackage.NAME_EXP_CS__ITERATORS:
				getIterators().clear();
				getIterators().addAll((Collection<? extends VariableCS>)newValue);
				return;
			case EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_ITERATION:
				setReferredIteration((Iteration)newValue);
				return;
			case EssentialOCLCSPackage.NAME_EXP_CS__ACCUMULATORS:
				getAccumulators().clear();
				getAccumulators().addAll((Collection<? extends VariableCS>)newValue);
				return;
			case EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_OPERATION:
				setReferredOperation((Operation)newValue);
				return;
			case EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_PROPERTY:
				setReferredProperty((Property)newValue);
				return;
			case EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_VARIABLE:
				setReferredVariable((Variable)newValue);
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
			case EssentialOCLCSPackage.NAME_EXP_CS__PARTS:
				getParts().clear();
				return;
			case EssentialOCLCSPackage.NAME_EXP_CS__TYPE_NAME:
				setTypeName((TypeNameExpCS)null);
				return;
			case EssentialOCLCSPackage.NAME_EXP_CS__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case EssentialOCLCSPackage.NAME_EXP_CS__ITERATORS:
				getIterators().clear();
				return;
			case EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_ITERATION:
				setReferredIteration((Iteration)null);
				return;
			case EssentialOCLCSPackage.NAME_EXP_CS__ACCUMULATORS:
				getAccumulators().clear();
				return;
			case EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_OPERATION:
				setReferredOperation((Operation)null);
				return;
			case EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_PROPERTY:
				setReferredProperty((Property)null);
				return;
			case EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_VARIABLE:
				setReferredVariable((Variable)null);
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
			case EssentialOCLCSPackage.NAME_EXP_CS__PARTS:
				return parts != null && !parts.isEmpty();
			case EssentialOCLCSPackage.NAME_EXP_CS__TYPE_NAME:
				return typeName != null;
			case EssentialOCLCSPackage.NAME_EXP_CS__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
			case EssentialOCLCSPackage.NAME_EXP_CS__ITERATORS:
				return iterators != null && !iterators.isEmpty();
			case EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_ITERATION:
				return referredIteration != null;
			case EssentialOCLCSPackage.NAME_EXP_CS__ACCUMULATORS:
				return accumulators != null && !accumulators.isEmpty();
			case EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_OPERATION:
				return referredOperation != null;
			case EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_PROPERTY:
				return referredProperty != null;
			case EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_VARIABLE:
				return referredVariable != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
	{
		if (baseClass == ShadowExpCS.class)
		{
			switch (derivedFeatureID)
			{
				case EssentialOCLCSPackage.NAME_EXP_CS__PARTS: return EssentialOCLCSPackage.SHADOW_EXP_CS__PARTS;
				case EssentialOCLCSPackage.NAME_EXP_CS__TYPE_NAME: return EssentialOCLCSPackage.SHADOW_EXP_CS__TYPE_NAME;
				case EssentialOCLCSPackage.NAME_EXP_CS__VALUE: return EssentialOCLCSPackage.SHADOW_EXP_CS__VALUE;
				default: return -1;
			}
		}
		if (baseClass == IterationCallExpCS.class)
		{
			switch (derivedFeatureID)
			{
				case EssentialOCLCSPackage.NAME_EXP_CS__ITERATORS: return EssentialOCLCSPackage.ITERATION_CALL_EXP_CS__ITERATORS;
				case EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_ITERATION: return EssentialOCLCSPackage.ITERATION_CALL_EXP_CS__REFERRED_ITERATION;
				default: return -1;
			}
		}
		if (baseClass == IterateCallExpCS.class)
		{
			switch (derivedFeatureID)
			{
				case EssentialOCLCSPackage.NAME_EXP_CS__ACCUMULATORS: return EssentialOCLCSPackage.ITERATE_CALL_EXP_CS__ACCUMULATORS;
				default: return -1;
			}
		}
		if (baseClass == OperationCallExpCS.class)
		{
			switch (derivedFeatureID)
			{
				case EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_OPERATION: return EssentialOCLCSPackage.OPERATION_CALL_EXP_CS__REFERRED_OPERATION;
				default: return -1;
			}
		}
		if (baseClass == PropertyCallExpCS.class)
		{
			switch (derivedFeatureID)
			{
				case EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_PROPERTY: return EssentialOCLCSPackage.PROPERTY_CALL_EXP_CS__REFERRED_PROPERTY;
				default: return -1;
			}
		}
		if (baseClass == VariableExpCS.class)
		{
			switch (derivedFeatureID)
			{
				case EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_VARIABLE: return EssentialOCLCSPackage.VARIABLE_EXP_CS__REFERRED_VARIABLE;
				default: return -1;
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
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
	{
		if (baseClass == ShadowExpCS.class)
		{
			switch (baseFeatureID)
			{
				case EssentialOCLCSPackage.SHADOW_EXP_CS__PARTS: return EssentialOCLCSPackage.NAME_EXP_CS__PARTS;
				case EssentialOCLCSPackage.SHADOW_EXP_CS__TYPE_NAME: return EssentialOCLCSPackage.NAME_EXP_CS__TYPE_NAME;
				case EssentialOCLCSPackage.SHADOW_EXP_CS__VALUE: return EssentialOCLCSPackage.NAME_EXP_CS__VALUE;
				default: return -1;
			}
		}
		if (baseClass == IterationCallExpCS.class)
		{
			switch (baseFeatureID)
			{
				case EssentialOCLCSPackage.ITERATION_CALL_EXP_CS__ITERATORS: return EssentialOCLCSPackage.NAME_EXP_CS__ITERATORS;
				case EssentialOCLCSPackage.ITERATION_CALL_EXP_CS__REFERRED_ITERATION: return EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_ITERATION;
				default: return -1;
			}
		}
		if (baseClass == IterateCallExpCS.class)
		{
			switch (baseFeatureID)
			{
				case EssentialOCLCSPackage.ITERATE_CALL_EXP_CS__ACCUMULATORS: return EssentialOCLCSPackage.NAME_EXP_CS__ACCUMULATORS;
				default: return -1;
			}
		}
		if (baseClass == OperationCallExpCS.class)
		{
			switch (baseFeatureID)
			{
				case EssentialOCLCSPackage.OPERATION_CALL_EXP_CS__REFERRED_OPERATION: return EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_OPERATION;
				default: return -1;
			}
		}
		if (baseClass == PropertyCallExpCS.class)
		{
			switch (baseFeatureID)
			{
				case EssentialOCLCSPackage.PROPERTY_CALL_EXP_CS__REFERRED_PROPERTY: return EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_PROPERTY;
				default: return -1;
			}
		}
		if (baseClass == VariableExpCS.class)
		{
			switch (baseFeatureID)
			{
				case EssentialOCLCSPackage.VARIABLE_EXP_CS__REFERRED_VARIABLE: return EssentialOCLCSPackage.NAME_EXP_CS__REFERRED_VARIABLE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <R> R accept(@NonNull BaseCSVisitor<R> visitor) {
		return (R) ((EssentialOCLCSVisitor<?>)visitor).visitNameExpCS(this);
	}
} //NamedElementRefCSImpl
