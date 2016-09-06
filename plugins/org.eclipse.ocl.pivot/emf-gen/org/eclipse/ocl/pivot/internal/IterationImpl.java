/*******************************************************************************
 * Copyright (c) 2010, 2015 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Precedence;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Iteration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.IterationImpl#getOwnedAccumulators <em>Owned Accumulators</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.IterationImpl#getOwnedIterators <em>Owned Iterators</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IterationImpl extends OperationImpl implements Iteration
{
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
		return PivotPackage.Literals.ITERATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("null")
	public @NonNull List<Parameter> getOwnedIterators()
	{
		if (ownedIterators == null)
		{
			ownedIterators = new EObjectContainmentEList<Parameter>(Parameter.class, this, PivotPackage.ITERATION__OWNED_ITERATORS);
		}
		return ownedIterators;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("null")
	public @NonNull List<Parameter> getOwnedAccumulators()
	{
		if (ownedAccumulators == null)
		{
			ownedAccumulators = new EObjectContainmentEList<Parameter>(Parameter.class, this, PivotPackage.ITERATION__OWNED_ACCUMULATORS);
		}
		return ownedAccumulators;
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
			case PivotPackage.ITERATION__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.ITERATION__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.ITERATION__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.ITERATION__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.ITERATION__OWNED_CONSTRAINTS:
				return ((InternalEList<?>)getOwnedConstraints()).basicRemove(otherEnd, msgs);
			case PivotPackage.ITERATION__OWNED_BINDINGS:
				return ((InternalEList<?>)getOwnedBindings()).basicRemove(otherEnd, msgs);
			case PivotPackage.ITERATION__OWNED_SIGNATURE:
				return basicSetOwnedSignature(null, msgs);
			case PivotPackage.ITERATION__BODY_EXPRESSION:
				return basicSetBodyExpression(null, msgs);
			case PivotPackage.ITERATION__OWNED_PARAMETERS:
				return ((InternalEList<?>)getOwnedParameters()).basicRemove(otherEnd, msgs);
			case PivotPackage.ITERATION__OWNED_POSTCONDITIONS:
				return ((InternalEList<?>)getOwnedPostconditions()).basicRemove(otherEnd, msgs);
			case PivotPackage.ITERATION__OWNED_PRECONDITIONS:
				return ((InternalEList<?>)getOwnedPreconditions()).basicRemove(otherEnd, msgs);
			case PivotPackage.ITERATION__OWNING_CLASS:
				return basicSetOwningClass(null, msgs);
			case PivotPackage.ITERATION__OWNED_ACCUMULATORS:
				return ((InternalEList<?>)getOwnedAccumulators()).basicRemove(otherEnd, msgs);
			case PivotPackage.ITERATION__OWNED_ITERATORS:
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
			case PivotPackage.ITERATION__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.ITERATION__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.ITERATION__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.ITERATION__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.ITERATION__NAME:
				return getName();
			case PivotPackage.ITERATION__IS_MANY:
				return isIsMany();
			case PivotPackage.ITERATION__IS_REQUIRED:
				return isIsRequired();
			case PivotPackage.ITERATION__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case PivotPackage.ITERATION__IMPLEMENTATION:
				return getImplementation();
			case PivotPackage.ITERATION__IMPLEMENTATION_CLASS:
				return getImplementationClass();
			case PivotPackage.ITERATION__IS_STATIC:
				return isIsStatic();
			case PivotPackage.ITERATION__OWNED_CONSTRAINTS:
				return getOwnedConstraints();
			case PivotPackage.ITERATION__OWNED_BINDINGS:
				return getOwnedBindings();
			case PivotPackage.ITERATION__OWNED_SIGNATURE:
				return getOwnedSignature();
			case PivotPackage.ITERATION__UNSPECIALIZED_ELEMENT:
				return getUnspecializedElement();
			case PivotPackage.ITERATION__BODY_EXPRESSION:
				return getBodyExpression();
			case PivotPackage.ITERATION__IS_INVALIDATING:
				return isIsInvalidating();
			case PivotPackage.ITERATION__IS_TRANSIENT:
				return isIsTransient();
			case PivotPackage.ITERATION__IS_TYPEOF:
				return isIsTypeof();
			case PivotPackage.ITERATION__IS_VALIDATING:
				return isIsValidating();
			case PivotPackage.ITERATION__OWNED_PARAMETERS:
				return getOwnedParameters();
			case PivotPackage.ITERATION__OWNED_POSTCONDITIONS:
				return getOwnedPostconditions();
			case PivotPackage.ITERATION__OWNED_PRECONDITIONS:
				return getOwnedPreconditions();
			case PivotPackage.ITERATION__OWNING_CLASS:
				return getOwningClass();
			case PivotPackage.ITERATION__PRECEDENCE:
				if (resolve) return getPrecedence();
				return basicGetPrecedence();
			case PivotPackage.ITERATION__RAISED_EXCEPTIONS:
				return getRaisedExceptions();
			case PivotPackage.ITERATION__REDEFINED_OPERATIONS:
				return getRedefinedOperations();
			case PivotPackage.ITERATION__OWNED_ACCUMULATORS:
				return getOwnedAccumulators();
			case PivotPackage.ITERATION__OWNED_ITERATORS:
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
			case PivotPackage.ITERATION__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.ITERATION__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.ITERATION__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.ITERATION__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.ITERATION__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.ITERATION__IS_REQUIRED:
				setIsRequired((Boolean)newValue);
				return;
			case PivotPackage.ITERATION__TYPE:
				setType((Type)newValue);
				return;
			case PivotPackage.ITERATION__IMPLEMENTATION:
				setImplementation((LibraryFeature)newValue);
				return;
			case PivotPackage.ITERATION__IMPLEMENTATION_CLASS:
				setImplementationClass((String)newValue);
				return;
			case PivotPackage.ITERATION__IS_STATIC:
				setIsStatic((Boolean)newValue);
				return;
			case PivotPackage.ITERATION__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				getOwnedConstraints().addAll((Collection<? extends Constraint>)newValue);
				return;
			case PivotPackage.ITERATION__OWNED_BINDINGS:
				getOwnedBindings().clear();
				getOwnedBindings().addAll((Collection<? extends TemplateBinding>)newValue);
				return;
			case PivotPackage.ITERATION__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignature)newValue);
				return;
			case PivotPackage.ITERATION__UNSPECIALIZED_ELEMENT:
				setUnspecializedElement((TemplateableElement)newValue);
				return;
			case PivotPackage.ITERATION__BODY_EXPRESSION:
				setBodyExpression((LanguageExpression)newValue);
				return;
			case PivotPackage.ITERATION__IS_INVALIDATING:
				setIsInvalidating((Boolean)newValue);
				return;
			case PivotPackage.ITERATION__IS_TRANSIENT:
				setIsTransient((Boolean)newValue);
				return;
			case PivotPackage.ITERATION__IS_TYPEOF:
				setIsTypeof((Boolean)newValue);
				return;
			case PivotPackage.ITERATION__IS_VALIDATING:
				setIsValidating((Boolean)newValue);
				return;
			case PivotPackage.ITERATION__OWNED_PARAMETERS:
				getOwnedParameters().clear();
				getOwnedParameters().addAll((Collection<? extends Parameter>)newValue);
				return;
			case PivotPackage.ITERATION__OWNED_POSTCONDITIONS:
				getOwnedPostconditions().clear();
				getOwnedPostconditions().addAll((Collection<? extends Constraint>)newValue);
				return;
			case PivotPackage.ITERATION__OWNED_PRECONDITIONS:
				getOwnedPreconditions().clear();
				getOwnedPreconditions().addAll((Collection<? extends Constraint>)newValue);
				return;
			case PivotPackage.ITERATION__OWNING_CLASS:
				setOwningClass((org.eclipse.ocl.pivot.Class)newValue);
				return;
			case PivotPackage.ITERATION__PRECEDENCE:
				setPrecedence((Precedence)newValue);
				return;
			case PivotPackage.ITERATION__RAISED_EXCEPTIONS:
				getRaisedExceptions().clear();
				getRaisedExceptions().addAll((Collection<? extends Type>)newValue);
				return;
			case PivotPackage.ITERATION__REDEFINED_OPERATIONS:
				getRedefinedOperations().clear();
				getRedefinedOperations().addAll((Collection<? extends Operation>)newValue);
				return;
			case PivotPackage.ITERATION__OWNED_ACCUMULATORS:
				getOwnedAccumulators().clear();
				getOwnedAccumulators().addAll((Collection<? extends Parameter>)newValue);
				return;
			case PivotPackage.ITERATION__OWNED_ITERATORS:
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
			case PivotPackage.ITERATION__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.ITERATION__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.ITERATION__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.ITERATION__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.ITERATION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.ITERATION__IS_REQUIRED:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case PivotPackage.ITERATION__TYPE:
				setType((Type)null);
				return;
			case PivotPackage.ITERATION__IMPLEMENTATION:
				setImplementation(IMPLEMENTATION_EDEFAULT);
				return;
			case PivotPackage.ITERATION__IMPLEMENTATION_CLASS:
				setImplementationClass(IMPLEMENTATION_CLASS_EDEFAULT);
				return;
			case PivotPackage.ITERATION__IS_STATIC:
				setIsStatic(IS_STATIC_EDEFAULT);
				return;
			case PivotPackage.ITERATION__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				return;
			case PivotPackage.ITERATION__OWNED_BINDINGS:
				getOwnedBindings().clear();
				return;
			case PivotPackage.ITERATION__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignature)null);
				return;
			case PivotPackage.ITERATION__UNSPECIALIZED_ELEMENT:
				setUnspecializedElement((TemplateableElement)null);
				return;
			case PivotPackage.ITERATION__BODY_EXPRESSION:
				setBodyExpression((LanguageExpression)null);
				return;
			case PivotPackage.ITERATION__IS_INVALIDATING:
				setIsInvalidating(IS_INVALIDATING_EDEFAULT);
				return;
			case PivotPackage.ITERATION__IS_TRANSIENT:
				setIsTransient(IS_TRANSIENT_EDEFAULT);
				return;
			case PivotPackage.ITERATION__IS_TYPEOF:
				setIsTypeof(IS_TYPEOF_EDEFAULT);
				return;
			case PivotPackage.ITERATION__IS_VALIDATING:
				setIsValidating(IS_VALIDATING_EDEFAULT);
				return;
			case PivotPackage.ITERATION__OWNED_PARAMETERS:
				getOwnedParameters().clear();
				return;
			case PivotPackage.ITERATION__OWNED_POSTCONDITIONS:
				getOwnedPostconditions().clear();
				return;
			case PivotPackage.ITERATION__OWNED_PRECONDITIONS:
				getOwnedPreconditions().clear();
				return;
			case PivotPackage.ITERATION__OWNING_CLASS:
				setOwningClass((org.eclipse.ocl.pivot.Class)null);
				return;
			case PivotPackage.ITERATION__PRECEDENCE:
				setPrecedence((Precedence)null);
				return;
			case PivotPackage.ITERATION__RAISED_EXCEPTIONS:
				getRaisedExceptions().clear();
				return;
			case PivotPackage.ITERATION__REDEFINED_OPERATIONS:
				getRedefinedOperations().clear();
				return;
			case PivotPackage.ITERATION__OWNED_ACCUMULATORS:
				getOwnedAccumulators().clear();
				return;
			case PivotPackage.ITERATION__OWNED_ITERATORS:
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
			case PivotPackage.ITERATION__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.ITERATION__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.ITERATION__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.ITERATION__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.ITERATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.ITERATION__IS_MANY:
				return isIsMany() != IS_MANY_EDEFAULT;
			case PivotPackage.ITERATION__IS_REQUIRED:
				return ((eFlags & IS_REQUIRED_EFLAG) != 0) != IS_REQUIRED_EDEFAULT;
			case PivotPackage.ITERATION__TYPE:
				return type != null;
			case PivotPackage.ITERATION__IMPLEMENTATION:
				return IMPLEMENTATION_EDEFAULT == null ? implementation != null : !IMPLEMENTATION_EDEFAULT.equals(implementation);
			case PivotPackage.ITERATION__IMPLEMENTATION_CLASS:
				return IMPLEMENTATION_CLASS_EDEFAULT == null ? implementationClass != null : !IMPLEMENTATION_CLASS_EDEFAULT.equals(implementationClass);
			case PivotPackage.ITERATION__IS_STATIC:
				return ((eFlags & IS_STATIC_EFLAG) != 0) != IS_STATIC_EDEFAULT;
			case PivotPackage.ITERATION__OWNED_CONSTRAINTS:
				return ownedConstraints != null && !ownedConstraints.isEmpty();
			case PivotPackage.ITERATION__OWNED_BINDINGS:
				return ownedBindings != null && !ownedBindings.isEmpty();
			case PivotPackage.ITERATION__OWNED_SIGNATURE:
				return ownedSignature != null;
			case PivotPackage.ITERATION__UNSPECIALIZED_ELEMENT:
				return unspecializedElement != null;
			case PivotPackage.ITERATION__BODY_EXPRESSION:
				return bodyExpression != null;
			case PivotPackage.ITERATION__IS_INVALIDATING:
				return ((eFlags & IS_INVALIDATING_EFLAG) != 0) != IS_INVALIDATING_EDEFAULT;
			case PivotPackage.ITERATION__IS_TRANSIENT:
				return ((eFlags & IS_TRANSIENT_EFLAG) != 0) != IS_TRANSIENT_EDEFAULT;
			case PivotPackage.ITERATION__IS_TYPEOF:
				return ((eFlags & IS_TYPEOF_EFLAG) != 0) != IS_TYPEOF_EDEFAULT;
			case PivotPackage.ITERATION__IS_VALIDATING:
				return ((eFlags & IS_VALIDATING_EFLAG) != 0) != IS_VALIDATING_EDEFAULT;
			case PivotPackage.ITERATION__OWNED_PARAMETERS:
				return ownedParameters != null && !ownedParameters.isEmpty();
			case PivotPackage.ITERATION__OWNED_POSTCONDITIONS:
				return ownedPostconditions != null && !ownedPostconditions.isEmpty();
			case PivotPackage.ITERATION__OWNED_PRECONDITIONS:
				return ownedPreconditions != null && !ownedPreconditions.isEmpty();
			case PivotPackage.ITERATION__OWNING_CLASS:
				return getOwningClass() != null;
			case PivotPackage.ITERATION__PRECEDENCE:
				return precedence != null;
			case PivotPackage.ITERATION__RAISED_EXCEPTIONS:
				return raisedExceptions != null && !raisedExceptions.isEmpty();
			case PivotPackage.ITERATION__REDEFINED_OPERATIONS:
				return redefinedOperations != null && !redefinedOperations.isEmpty();
			case PivotPackage.ITERATION__OWNED_ACCUMULATORS:
				return ownedAccumulators != null && !ownedAccumulators.isEmpty();
			case PivotPackage.ITERATION__OWNED_ITERATORS:
				return ownedIterators != null && !ownedIterators.isEmpty();
		}
		return eDynamicIsSet(featureID);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitIteration(this);
	}

} //IterationImpl
