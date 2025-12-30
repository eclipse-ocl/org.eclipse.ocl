/*******************************************************************************
 * Copyright (c) 2011, 2022 Willink Transformations and others.
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
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Behavior;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.LambdaParameter;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StereotypeExtender;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateArgument;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.WildcardType;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Lambda Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.LambdaTypeImpl#getOwnedContext <em>Owned Context</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.LambdaTypeImpl#getOwnedParameters <em>Owned Parameters</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.LambdaTypeImpl#getOwnedResult <em>Owned Result</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LambdaTypeImpl extends DataTypeImpl implements LambdaType
{
	/**
	 * The number of structural features of the '<em>Lambda Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LAMBDA_TYPE_FEATURE_COUNT = DataTypeImpl.DATA_TYPE_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Lambda Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LAMBDA_TYPE_OPERATION_COUNT = DataTypeImpl.DATA_TYPE_OPERATION_COUNT + 0;

	/**
	 * The cached value of the '{@link #getOwnedContext() <em>Owned Context</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedContext()
	 * @generated
	 * @ordered
	 */
	protected LambdaParameter ownedContext;

	/**
	 * The cached value of the '{@link #getOwnedParameters() <em>Owned Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<LambdaParameter> ownedParameters;

	/**
	 * The cached value of the '{@link #getOwnedResult() <em>Owned Result</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedResult()
	 * @generated
	 * @ordered
	 */
	protected LambdaParameter ownedResult;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LambdaTypeImpl()
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
		return PivotPackage.Literals.LAMBDA_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LambdaParameter getOwnedContext()
	{
		return ownedContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedContext(LambdaParameter newOwnedContext, NotificationChain msgs)
	{
		LambdaParameter oldOwnedContext = ownedContext;
		ownedContext = newOwnedContext;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, 24, oldOwnedContext, newOwnedContext);
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
	public void setOwnedContext(LambdaParameter newOwnedContext)
	{
		if (newOwnedContext != ownedContext)
		{
			NotificationChain msgs = null;
			if (ownedContext != null)
				msgs = ((InternalEObject)ownedContext).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (24), null, msgs);
			if (newOwnedContext != null)
				msgs = ((InternalEObject)newOwnedContext).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (24), null, msgs);
			msgs = basicSetOwnedContext(newOwnedContext, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 24, newOwnedContext, newOwnedContext));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<LambdaParameter> getOwnedParameters()
	{
		if (ownedParameters == null)
		{
			ownedParameters = new EObjectContainmentEList<LambdaParameter>(LambdaParameter.class, this, 25);
		}
		return ownedParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LambdaParameter getOwnedResult()
	{
		return ownedResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedResult(LambdaParameter newOwnedResult, NotificationChain msgs)
	{
		LambdaParameter oldOwnedResult = ownedResult;
		ownedResult = newOwnedResult;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, 26, oldOwnedResult, newOwnedResult);
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
	public void setOwnedResult(LambdaParameter newOwnedResult)
	{
		if (newOwnedResult != ownedResult)
		{
			NotificationChain msgs = null;
			if (ownedResult != null)
				msgs = ((InternalEObject)ownedResult).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (26), null, msgs);
			if (newOwnedResult != null)
				msgs = ((InternalEObject)newOwnedResult).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (26), null, msgs);
			msgs = basicSetOwnedResult(newOwnedResult, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 26, newOwnedResult, newOwnedResult));
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
			case 0:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case 1:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case 2:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case 3:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case 5:
				return ((InternalEList<?>)getOwnedConstraints()).basicRemove(otherEnd, msgs);
			case 7:
				return ((InternalEList<?>)getOwnedTemplateArguments()).basicRemove(otherEnd, msgs);
			case 8:
				return ((InternalEList<?>)getOwnedTemplateParameters()).basicRemove(otherEnd, msgs);
			case 9:
				return ((InternalEList<?>)getOwnedWildcards()).basicRemove(otherEnd, msgs);
			case 10:
				return ((InternalEList<?>)getExtenders()).basicRemove(otherEnd, msgs);
			case 15:
				return ((InternalEList<?>)getOwnedBehaviors()).basicRemove(otherEnd, msgs);
			case 16:
				return ((InternalEList<?>)getOwnedInvariants()).basicRemove(otherEnd, msgs);
			case 17:
				return ((InternalEList<?>)getOwnedOperations()).basicRemove(otherEnd, msgs);
			case 18:
				return ((InternalEList<?>)getOwnedProperties()).basicRemove(otherEnd, msgs);
			case 19:
				return basicSetOwningPackage(null, msgs);
			case 24:
				return basicSetOwnedContext(null, msgs);
			case 25:
				return ((InternalEList<?>)getOwnedParameters()).basicRemove(otherEnd, msgs);
			case 26:
				return basicSetOwnedResult(null, msgs);
		}
		return eDynamicInverseRemove(otherEnd, featureID, msgs);
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
				return getAnnotatingComments();
			case 1:
				return getOwnedAnnotations();
			case 2:
				return getOwnedComments();
			case 3:
				return getOwnedExtensions();
			case 4:
				return getName();
			case 5:
				return getOwnedConstraints();
			case 6:
				if (resolve) return getGeneric();
				return basicGetGeneric();
			case 7:
				return getOwnedTemplateArguments();
			case 8:
				return getOwnedTemplateParameters();
			case 9:
				return getOwnedWildcards();
			case 10:
				return getExtenders();
			case 11:
				return getInstanceClassName();
			case 12:
				return isIsAbstract();
			case 13:
				return isIsActive();
			case 14:
				return isIsInterface();
			case 15:
				return getOwnedBehaviors();
			case 16:
				return getOwnedInvariants();
			case 17:
				return getOwnedOperations();
			case 18:
				return getOwnedProperties();
			case 19:
				return getOwningPackage();
			case 20:
				return getSuperClasses();
			case 21:
				if (resolve) return getBehavioralClass();
				return basicGetBehavioralClass();
			case 22:
				return isIsSerializable();
			case 23:
				return getValue();
			case 24:
				return getOwnedContext();
			case 25:
				return getOwnedParameters();
			case 26:
				return getOwnedResult();
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
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case 0:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 1:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case 2:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 3:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case 4:
				setName((String)newValue);
				return;
			case 5:
				getOwnedConstraints().clear();
				getOwnedConstraints().addAll((Collection<? extends Constraint>)newValue);
				return;
			case 6:
				setGeneric((TemplateableElement)newValue);
				return;
			case 7:
				getOwnedTemplateArguments().clear();
				getOwnedTemplateArguments().addAll((Collection<? extends TemplateArgument>)newValue);
				return;
			case 8:
				getOwnedTemplateParameters().clear();
				getOwnedTemplateParameters().addAll((Collection<? extends TemplateParameter>)newValue);
				return;
			case 9:
				getOwnedWildcards().clear();
				getOwnedWildcards().addAll((Collection<? extends WildcardType>)newValue);
				return;
			case 10:
				getExtenders().clear();
				getExtenders().addAll((Collection<? extends StereotypeExtender>)newValue);
				return;
			case 11:
				setInstanceClassName((String)newValue);
				return;
			case 12:
				setIsAbstract((Boolean)newValue);
				return;
			case 13:
				setIsActive((Boolean)newValue);
				return;
			case 14:
				setIsInterface((Boolean)newValue);
				return;
			case 15:
				getOwnedBehaviors().clear();
				getOwnedBehaviors().addAll((Collection<? extends Behavior>)newValue);
				return;
			case 16:
				getOwnedInvariants().clear();
				getOwnedInvariants().addAll((Collection<? extends Constraint>)newValue);
				return;
			case 17:
				getOwnedOperations().clear();
				getOwnedOperations().addAll((Collection<? extends Operation>)newValue);
				return;
			case 18:
				getOwnedProperties().clear();
				getOwnedProperties().addAll((Collection<? extends Property>)newValue);
				return;
			case 19:
				setOwningPackage((org.eclipse.ocl.pivot.Package)newValue);
				return;
			case 20:
				getSuperClasses().clear();
				getSuperClasses().addAll((Collection<? extends org.eclipse.ocl.pivot.Class>)newValue);
				return;
			case 21:
				setBehavioralClass((org.eclipse.ocl.pivot.Class)newValue);
				return;
			case 22:
				setIsSerializable((Boolean)newValue);
				return;
			case 24:
				setOwnedContext((LambdaParameter)newValue);
				return;
			case 25:
				getOwnedParameters().clear();
				getOwnedParameters().addAll((Collection<? extends LambdaParameter>)newValue);
				return;
			case 26:
				setOwnedResult((LambdaParameter)newValue);
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
	public void eUnset(int featureID)
	{
		switch (featureID)
		{
			case 0:
				getAnnotatingComments().clear();
				return;
			case 1:
				getOwnedAnnotations().clear();
				return;
			case 2:
				getOwnedComments().clear();
				return;
			case 3:
				getOwnedExtensions().clear();
				return;
			case 4:
				setName(NAME_EDEFAULT);
				return;
			case 5:
				getOwnedConstraints().clear();
				return;
			case 6:
				setGeneric((TemplateableElement)null);
				return;
			case 7:
				getOwnedTemplateArguments().clear();
				return;
			case 8:
				getOwnedTemplateParameters().clear();
				return;
			case 9:
				getOwnedWildcards().clear();
				return;
			case 10:
				getExtenders().clear();
				return;
			case 11:
				setInstanceClassName(INSTANCE_CLASS_NAME_EDEFAULT);
				return;
			case 12:
				setIsAbstract(IS_ABSTRACT_EDEFAULT);
				return;
			case 13:
				setIsActive(IS_ACTIVE_EDEFAULT);
				return;
			case 14:
				setIsInterface(IS_INTERFACE_EDEFAULT);
				return;
			case 15:
				getOwnedBehaviors().clear();
				return;
			case 16:
				getOwnedInvariants().clear();
				return;
			case 17:
				getOwnedOperations().clear();
				return;
			case 18:
				getOwnedProperties().clear();
				return;
			case 19:
				setOwningPackage((org.eclipse.ocl.pivot.Package)null);
				return;
			case 20:
				getSuperClasses().clear();
				return;
			case 21:
				setBehavioralClass((org.eclipse.ocl.pivot.Class)null);
				return;
			case 22:
				setIsSerializable(IS_SERIALIZABLE_EDEFAULT);
				return;
			case 24:
				setOwnedContext((LambdaParameter)null);
				return;
			case 25:
				getOwnedParameters().clear();
				return;
			case 26:
				setOwnedResult((LambdaParameter)null);
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
	public boolean eIsSet(int featureID)
	{
		switch (featureID)
		{
			case 0:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case 1:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case 2:
				return ownedComments != null && !ownedComments.isEmpty();
			case 3:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case 4:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case 5:
				return ownedConstraints != null && !ownedConstraints.isEmpty();
			case 6:
				return generic != null;
			case 7:
				return ownedTemplateArguments != null && !ownedTemplateArguments.isEmpty();
			case 8:
				return ownedTemplateParameters != null && !ownedTemplateParameters.isEmpty();
			case 9:
				return ownedWildcards != null && !ownedWildcards.isEmpty();
			case 10:
				return extenders != null && !extenders.isEmpty();
			case 11:
				return INSTANCE_CLASS_NAME_EDEFAULT == null ? instanceClassName != null : !INSTANCE_CLASS_NAME_EDEFAULT.equals(instanceClassName);
			case 12:
				return ((eFlags & IS_ABSTRACT_EFLAG) != 0) != IS_ABSTRACT_EDEFAULT;
			case 13:
				return ((eFlags & IS_ACTIVE_EFLAG) != 0) != IS_ACTIVE_EDEFAULT;
			case 14:
				return ((eFlags & IS_INTERFACE_EFLAG) != 0) != IS_INTERFACE_EDEFAULT;
			case 15:
				return ownedBehaviors != null && !ownedBehaviors.isEmpty();
			case 16:
				return ownedInvariants != null && !ownedInvariants.isEmpty();
			case 17:
				return ownedOperations != null && !ownedOperations.isEmpty();
			case 18:
				return ownedProperties != null && !ownedProperties.isEmpty();
			case 19:
				return getOwningPackage() != null;
			case 20:
				return superClasses != null && !superClasses.isEmpty();
			case 21:
				return behavioralClass != null;
			case 22:
				return ((eFlags & IS_SERIALIZABLE_EFLAG) != 0) != IS_SERIALIZABLE_EDEFAULT;
			case 23:
				return VALUE_EDEFAULT == null ? getValue() != null : !VALUE_EDEFAULT.equals(getValue());
			case 24:
				return ownedContext != null;
			case 25:
				return ownedParameters != null && !ownedParameters.isEmpty();
			case 26:
				return ownedResult != null;
		}
		return eDynamicIsSet(featureID);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitLambdaType(this);
	}

	@Override
	public @NonNull TypeId computeId() {
		return IdManager.getLambdaTypeId(this);
	}

	@Override
	public @NonNull Type getContextType() {
		return PivotUtil.getType(ownedContext);
	}

	@Override
	public @NonNull Type getResultType() {
		return PivotUtil.getType(ownedResult);
	}
} //LambdaTypeImpl
