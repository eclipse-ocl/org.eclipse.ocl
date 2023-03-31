/**
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.ocl.internal;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.ocl.ocl.Comment;
import org.eclipse.ocl.ocl.Constraint;
import org.eclipse.ocl.ocl.Element;
import org.eclipse.ocl.ocl.ElementExtension;
import org.eclipse.ocl.ocl.Iteration;
import org.eclipse.ocl.ocl.LanguageExpression;
import org.eclipse.ocl.ocl.OCLASPackage;
import org.eclipse.ocl.ocl.OclElement;
import org.eclipse.ocl.ocl.Operation;
import org.eclipse.ocl.ocl.Parameter;
import org.eclipse.ocl.ocl.Precedence;
import org.eclipse.ocl.ocl.TemplateBinding;
import org.eclipse.ocl.ocl.TemplateSignature;
import org.eclipse.ocl.ocl.TemplateableElement;
import org.eclipse.ocl.ocl.Type;

import org.eclipse.ocl.pivot.library.LibraryFeature;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Iteration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.internal.IterationImpl#getOwnedAccumulators <em>Owned Accumulators</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.internal.IterationImpl#getOwnedIterators <em>Owned Iterators</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IterationImpl extends OperationImpl implements Iteration
{
	/**
	 * The number of structural features of the '<em>Iteration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERATION_FEATURE_COUNT = OperationImpl.OPERATION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Iteration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERATION_OPERATION_COUNT = OperationImpl.OPERATION_OPERATION_COUNT + 0;


	/**
	 * The cached value of the '{@link #getOwnedAccumulators() <em>Owned Accumulators</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedAccumulators()
	 * @generated
	 * @ordered
	 */
	protected EList<Parameter> ownedAccumulators;

	/**
	 * The cached value of the '{@link #getOwnedIterators() <em>Owned Iterators</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedIterators()
	 * @generated
	 * @ordered
	 */
	protected EList<Parameter> ownedIterators;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IterationImpl()
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
		return OCLASPackage.Literals.ITERATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Parameter> getOwnedAccumulators()
	{
		if (ownedAccumulators == null)
		{
			ownedAccumulators = new EObjectContainmentEList<Parameter>(Parameter.class, this, 29);
		}
		return ownedAccumulators;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Parameter> getOwnedIterators()
	{
		if (ownedIterators == null)
		{
			ownedIterators = new EObjectContainmentEList<Parameter>(Parameter.class, this, 30);
		}
		return ownedIterators;
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
			case 2:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case 3:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case 4:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case 5:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case 13:
				return ((InternalEList<?>)getOwnedConstraints()).basicRemove(otherEnd, msgs);
			case 14:
				return ((InternalEList<?>)getOwnedBindings()).basicRemove(otherEnd, msgs);
			case 15:
				return basicSetOwnedSignature(null, msgs);
			case 17:
				return basicSetBodyExpression(null, msgs);
			case 22:
				return ((InternalEList<?>)getOwnedParameters()).basicRemove(otherEnd, msgs);
			case 23:
				return ((InternalEList<?>)getOwnedPostconditions()).basicRemove(otherEnd, msgs);
			case 24:
				return ((InternalEList<?>)getOwnedPreconditions()).basicRemove(otherEnd, msgs);
			case 25:
				return basicSetOwningClass(null, msgs);
			case 29:
				return ((InternalEList<?>)getOwnedAccumulators()).basicRemove(otherEnd, msgs);
			case 30:
				return ((InternalEList<?>)getOwnedIterators()).basicRemove(otherEnd, msgs);
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
				if (resolve) return getOclContainer();
				return basicGetOclContainer();
			case 1:
				return getOclContents();
			case 2:
				return getAnnotatingComments();
			case 3:
				return getOwnedAnnotations();
			case 4:
				return getOwnedComments();
			case 5:
				return getOwnedExtensions();
			case 6:
				return getName();
			case 7:
				return getIsMany();
			case 8:
				return getIsRequired();
			case 9:
				if (resolve) return getType();
				return basicGetType();
			case 10:
				return getImplementation();
			case 11:
				return getImplementationClass();
			case 12:
				return getIsStatic();
			case 13:
				return getOwnedConstraints();
			case 14:
				return getOwnedBindings();
			case 15:
				return getOwnedSignature();
			case 16:
				return getUnspecializedElement();
			case 17:
				return getBodyExpression();
			case 18:
				return getIsInvalidating();
			case 19:
				return getIsTransient();
			case 20:
				return getIsTypeof();
			case 21:
				return getIsValidating();
			case 22:
				return getOwnedParameters();
			case 23:
				return getOwnedPostconditions();
			case 24:
				return getOwnedPreconditions();
			case 25:
				return getOwningClass();
			case 26:
				if (resolve) return getPrecedence();
				return basicGetPrecedence();
			case 27:
				return getRaisedExceptions();
			case 28:
				return getRedefinedOperations();
			case 29:
				return getOwnedAccumulators();
			case 30:
				return getOwnedIterators();
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
				setOclContainer((OclElement)newValue);
				return;
			case 1:
				getOclContents().clear();
				getOclContents().addAll((Collection<? extends OclElement>)newValue);
				return;
			case 2:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 3:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case 4:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 5:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case 6:
				setName((String)newValue);
				return;
			case 8:
				setIsRequired((Boolean)newValue);
				return;
			case 9:
				setType((Type)newValue);
				return;
			case 10:
				setImplementation((LibraryFeature)newValue);
				return;
			case 11:
				setImplementationClass((String)newValue);
				return;
			case 12:
				setIsStatic((Boolean)newValue);
				return;
			case 13:
				getOwnedConstraints().clear();
				getOwnedConstraints().addAll((Collection<? extends Constraint>)newValue);
				return;
			case 14:
				getOwnedBindings().clear();
				getOwnedBindings().addAll((Collection<? extends TemplateBinding>)newValue);
				return;
			case 15:
				setOwnedSignature((TemplateSignature)newValue);
				return;
			case 16:
				setUnspecializedElement((TemplateableElement)newValue);
				return;
			case 17:
				setBodyExpression((LanguageExpression)newValue);
				return;
			case 18:
				setIsInvalidating((Boolean)newValue);
				return;
			case 19:
				setIsTransient((Boolean)newValue);
				return;
			case 20:
				setIsTypeof((Boolean)newValue);
				return;
			case 21:
				setIsValidating((Boolean)newValue);
				return;
			case 22:
				getOwnedParameters().clear();
				getOwnedParameters().addAll((Collection<? extends Parameter>)newValue);
				return;
			case 23:
				getOwnedPostconditions().clear();
				getOwnedPostconditions().addAll((Collection<? extends Constraint>)newValue);
				return;
			case 24:
				getOwnedPreconditions().clear();
				getOwnedPreconditions().addAll((Collection<? extends Constraint>)newValue);
				return;
			case 25:
				setOwningClass((org.eclipse.ocl.ocl.Class)newValue);
				return;
			case 26:
				setPrecedence((Precedence)newValue);
				return;
			case 27:
				getRaisedExceptions().clear();
				getRaisedExceptions().addAll((Collection<? extends Type>)newValue);
				return;
			case 28:
				getRedefinedOperations().clear();
				getRedefinedOperations().addAll((Collection<? extends Operation>)newValue);
				return;
			case 29:
				getOwnedAccumulators().clear();
				getOwnedAccumulators().addAll((Collection<? extends Parameter>)newValue);
				return;
			case 30:
				getOwnedIterators().clear();
				getOwnedIterators().addAll((Collection<? extends Parameter>)newValue);
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
				setOclContainer((OclElement)null);
				return;
			case 1:
				getOclContents().clear();
				return;
			case 2:
				getAnnotatingComments().clear();
				return;
			case 3:
				getOwnedAnnotations().clear();
				return;
			case 4:
				getOwnedComments().clear();
				return;
			case 5:
				getOwnedExtensions().clear();
				return;
			case 6:
				setName(NAME_EDEFAULT);
				return;
			case 8:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case 9:
				setType((Type)null);
				return;
			case 10:
				setImplementation(IMPLEMENTATION_EDEFAULT);
				return;
			case 11:
				setImplementationClass(IMPLEMENTATION_CLASS_EDEFAULT);
				return;
			case 12:
				setIsStatic(IS_STATIC_EDEFAULT);
				return;
			case 13:
				getOwnedConstraints().clear();
				return;
			case 14:
				getOwnedBindings().clear();
				return;
			case 15:
				setOwnedSignature((TemplateSignature)null);
				return;
			case 16:
				setUnspecializedElement((TemplateableElement)null);
				return;
			case 17:
				setBodyExpression((LanguageExpression)null);
				return;
			case 18:
				setIsInvalidating(IS_INVALIDATING_EDEFAULT);
				return;
			case 19:
				setIsTransient(IS_TRANSIENT_EDEFAULT);
				return;
			case 20:
				setIsTypeof(IS_TYPEOF_EDEFAULT);
				return;
			case 21:
				setIsValidating(IS_VALIDATING_EDEFAULT);
				return;
			case 22:
				getOwnedParameters().clear();
				return;
			case 23:
				getOwnedPostconditions().clear();
				return;
			case 24:
				getOwnedPreconditions().clear();
				return;
			case 25:
				setOwningClass((org.eclipse.ocl.ocl.Class)null);
				return;
			case 26:
				setPrecedence((Precedence)null);
				return;
			case 27:
				getRaisedExceptions().clear();
				return;
			case 28:
				getRedefinedOperations().clear();
				return;
			case 29:
				getOwnedAccumulators().clear();
				return;
			case 30:
				getOwnedIterators().clear();
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
				return oclContainer != null;
			case 1:
				return oclContents != null && !oclContents.isEmpty();
			case 2:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case 3:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case 4:
				return ownedComments != null && !ownedComments.isEmpty();
			case 5:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case 6:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case 7:
				return IS_MANY_EDEFAULT == null ? getIsMany() != null : !IS_MANY_EDEFAULT.equals(getIsMany());
			case 8:
				return IS_REQUIRED_EDEFAULT == null ? isRequired != null : !IS_REQUIRED_EDEFAULT.equals(isRequired);
			case 9:
				return type != null;
			case 10:
				return IMPLEMENTATION_EDEFAULT == null ? implementation != null : !IMPLEMENTATION_EDEFAULT.equals(implementation);
			case 11:
				return IMPLEMENTATION_CLASS_EDEFAULT == null ? implementationClass != null : !IMPLEMENTATION_CLASS_EDEFAULT.equals(implementationClass);
			case 12:
				return IS_STATIC_EDEFAULT == null ? isStatic != null : !IS_STATIC_EDEFAULT.equals(isStatic);
			case 13:
				return ownedConstraints != null && !ownedConstraints.isEmpty();
			case 14:
				return ownedBindings != null && !ownedBindings.isEmpty();
			case 15:
				return ownedSignature != null;
			case 16:
				return unspecializedElement != null;
			case 17:
				return bodyExpression != null;
			case 18:
				return IS_INVALIDATING_EDEFAULT == null ? isInvalidating != null : !IS_INVALIDATING_EDEFAULT.equals(isInvalidating);
			case 19:
				return IS_TRANSIENT_EDEFAULT == null ? isTransient != null : !IS_TRANSIENT_EDEFAULT.equals(isTransient);
			case 20:
				return IS_TYPEOF_EDEFAULT == null ? isTypeof != null : !IS_TYPEOF_EDEFAULT.equals(isTypeof);
			case 21:
				return IS_VALIDATING_EDEFAULT == null ? isValidating != null : !IS_VALIDATING_EDEFAULT.equals(isValidating);
			case 22:
				return ownedParameters != null && !ownedParameters.isEmpty();
			case 23:
				return ownedPostconditions != null && !ownedPostconditions.isEmpty();
			case 24:
				return ownedPreconditions != null && !ownedPreconditions.isEmpty();
			case 25:
				return getOwningClass() != null;
			case 26:
				return precedence != null;
			case 27:
				return raisedExceptions != null && !raisedExceptions.isEmpty();
			case 28:
				return redefinedOperations != null && !redefinedOperations.isEmpty();
			case 29:
				return ownedAccumulators != null && !ownedAccumulators.isEmpty();
			case 30:
				return ownedIterators != null && !ownedIterators.isEmpty();
		}
		return eDynamicIsSet(featureID);
	}


} //IterationImpl
