/*******************************************************************************
 * Copyright (c) 2010, 2019 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Precedence;
import org.eclipse.ocl.pivot.TemplateArgument;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ValueSpecification;
import org.eclipse.ocl.pivot.WildcardType;
import org.eclipse.ocl.pivot.internal.library.CompleteStandardLibrary;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.LibraryIterationOrOperation;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Iteration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.IterationImpl#getOwnedAccumulator <em>Owned Accumulator</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.IterationImpl#getOwnedIterators <em>Owned Iterators</em>}</li>
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
	public static final int ITERATION_OPERATION_COUNT = OperationImpl.OPERATION_OPERATION_COUNT + 1;

	/**
	 * The cached value of the '{@link #getOwnedAccumulator() <em>Owned Accumulator</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedAccumulator()
	 * @generated
	 * @ordered
	 */
	protected Parameter ownedAccumulator;

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
	public Parameter getOwnedAccumulator()
	{
		return ownedAccumulator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedAccumulator(Parameter newOwnedAccumulator, NotificationChain msgs)
	{
		Parameter oldOwnedAccumulator = ownedAccumulator;
		ownedAccumulator = newOwnedAccumulator;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, 28, oldOwnedAccumulator, newOwnedAccumulator);
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
	public void setOwnedAccumulator(Parameter newOwnedAccumulator)
	{
		if (newOwnedAccumulator != ownedAccumulator)
		{
			NotificationChain msgs = null;
			if (ownedAccumulator != null)
				msgs = ((InternalEObject)ownedAccumulator).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (28), null, msgs);
			if (newOwnedAccumulator != null)
				msgs = ((InternalEObject)newOwnedAccumulator).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (28), null, msgs);
			msgs = basicSetOwnedAccumulator(newOwnedAccumulator, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 28, newOwnedAccumulator, newOwnedAccumulator));
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
			ownedIterators = new EObjectContainmentEList<Parameter>(Parameter.class, this, 29);
		}
		return ownedIterators;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Type resolveBodyType(final OCLExpression argument)
	{
		System.out.println("resolveBodyType " + NameUtil.debugSimpleName(this) + " " + name + " " + NameUtil.debugSimpleName(implementation));
		/**
		 * argument.type
		 */
		final /*@NonInvalid*/ @Nullable Type type = argument.getType();
		if (type == null) {
			throw new InvalidValueException("Null body for \'Iteration::resolveBodyType(OCLExpression[1]) : Type[1]\'");
		}
		EnvironmentFactory environmentFactory = PivotUtil.getEnvironmentFactory(argument);
		LibraryIterationOrOperation implementation2 = (LibraryIterationOrOperation) implementation;
		if (implementation2 == null) {
			implementation2 = environmentFactory.getIterationOrOperationImplementation(this);
		}
		CompleteStandardLibrary standardLibrary = environmentFactory.getStandardLibrary();
		@SuppressWarnings("null") CallExp callExp = (@NonNull CallExp)argument.eContainer();
		return implementation2.resolveBodyType(standardLibrary, callExp, type);
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
			case 11:
				return ((InternalEList<?>)getOwnedConstraints()).basicRemove(otherEnd, msgs);
			case 13:
				return ((InternalEList<?>)getOwnedTemplateArguments()).basicRemove(otherEnd, msgs);
			case 14:
				return ((InternalEList<?>)getOwnedTemplateParameters()).basicRemove(otherEnd, msgs);
			case 15:
				return ((InternalEList<?>)getOwnedWildcards()).basicRemove(otherEnd, msgs);
			case 16:
				return basicSetBodyExpression(null, msgs);
			case 21:
				return ((InternalEList<?>)getOwnedParameters()).basicRemove(otherEnd, msgs);
			case 22:
				return ((InternalEList<?>)getOwnedPostconditions()).basicRemove(otherEnd, msgs);
			case 23:
				return ((InternalEList<?>)getOwnedPreconditions()).basicRemove(otherEnd, msgs);
			case 24:
				return basicSetOwningClass(null, msgs);
			case 28:
				return basicSetOwnedAccumulator(null, msgs);
			case 29:
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
				return isIsMany();
			case 6:
				return isIsRequired();
			case 7:
				if (resolve) return getType();
				return basicGetType();
			case 8:
				return getImplementation();
			case 9:
				return getImplementationClass();
			case 10:
				return isIsStatic();
			case 11:
				return getOwnedConstraints();
			case 12:
				if (resolve) return getGeneric();
				return basicGetGeneric();
			case 13:
				return getOwnedTemplateArguments();
			case 14:
				return getOwnedTemplateParameters();
			case 15:
				return getOwnedWildcards();
			case 16:
				return getBodyExpression();
			case 17:
				return isIsInvalidating();
			case 18:
				return isIsTransient();
			case 19:
				return isIsTypeof();
			case 20:
				return isIsValidating();
			case 21:
				return getOwnedParameters();
			case 22:
				return getOwnedPostconditions();
			case 23:
				return getOwnedPreconditions();
			case 24:
				return getOwningClass();
			case 25:
				if (resolve) return getPrecedence();
				return basicGetPrecedence();
			case 26:
				return getRaisedExceptions();
			case 27:
				return getRedefinedOperations();
			case 28:
				return getOwnedAccumulator();
			case 29:
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
			case 6:
				setIsRequired((Boolean)newValue);
				return;
			case 7:
				setType((Type)newValue);
				return;
			case 8:
				setImplementation((LibraryFeature)newValue);
				return;
			case 9:
				setImplementationClass((String)newValue);
				return;
			case 10:
				setIsStatic((Boolean)newValue);
				return;
			case 11:
				getOwnedConstraints().clear();
				getOwnedConstraints().addAll((Collection<? extends Constraint>)newValue);
				return;
			case 12:
				setGeneric((TemplateableElement)newValue);
				return;
			case 13:
				getOwnedTemplateArguments().clear();
				getOwnedTemplateArguments().addAll((Collection<? extends TemplateArgument>)newValue);
				return;
			case 14:
				getOwnedTemplateParameters().clear();
				getOwnedTemplateParameters().addAll((Collection<? extends TemplateParameter>)newValue);
				return;
			case 15:
				getOwnedWildcards().clear();
				getOwnedWildcards().addAll((Collection<? extends WildcardType>)newValue);
				return;
			case 16:
				setBodyExpression((LanguageExpression)newValue);
				return;
			case 17:
				setIsInvalidating((Boolean)newValue);
				return;
			case 18:
				setIsTransient((Boolean)newValue);
				return;
			case 19:
				setIsTypeof((Boolean)newValue);
				return;
			case 20:
				setIsValidating((Boolean)newValue);
				return;
			case 21:
				getOwnedParameters().clear();
				getOwnedParameters().addAll((Collection<? extends Parameter>)newValue);
				return;
			case 22:
				getOwnedPostconditions().clear();
				getOwnedPostconditions().addAll((Collection<? extends Constraint>)newValue);
				return;
			case 23:
				getOwnedPreconditions().clear();
				getOwnedPreconditions().addAll((Collection<? extends Constraint>)newValue);
				return;
			case 24:
				setOwningClass((org.eclipse.ocl.pivot.Class)newValue);
				return;
			case 25:
				setPrecedence((Precedence)newValue);
				return;
			case 26:
				getRaisedExceptions().clear();
				getRaisedExceptions().addAll((Collection<? extends Type>)newValue);
				return;
			case 27:
				getRedefinedOperations().clear();
				getRedefinedOperations().addAll((Collection<? extends Operation>)newValue);
				return;
			case 28:
				setOwnedAccumulator((Parameter)newValue);
				return;
			case 29:
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
			case 6:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case 7:
				setType((Type)null);
				return;
			case 8:
				setImplementation(IMPLEMENTATION_EDEFAULT);
				return;
			case 9:
				setImplementationClass(IMPLEMENTATION_CLASS_EDEFAULT);
				return;
			case 10:
				setIsStatic(IS_STATIC_EDEFAULT);
				return;
			case 11:
				getOwnedConstraints().clear();
				return;
			case 12:
				setGeneric((TemplateableElement)null);
				return;
			case 13:
				getOwnedTemplateArguments().clear();
				return;
			case 14:
				getOwnedTemplateParameters().clear();
				return;
			case 15:
				getOwnedWildcards().clear();
				return;
			case 16:
				setBodyExpression((LanguageExpression)null);
				return;
			case 17:
				setIsInvalidating(IS_INVALIDATING_EDEFAULT);
				return;
			case 18:
				setIsTransient(IS_TRANSIENT_EDEFAULT);
				return;
			case 19:
				setIsTypeof(IS_TYPEOF_EDEFAULT);
				return;
			case 20:
				setIsValidating(IS_VALIDATING_EDEFAULT);
				return;
			case 21:
				getOwnedParameters().clear();
				return;
			case 22:
				getOwnedPostconditions().clear();
				return;
			case 23:
				getOwnedPreconditions().clear();
				return;
			case 24:
				setOwningClass((org.eclipse.ocl.pivot.Class)null);
				return;
			case 25:
				setPrecedence((Precedence)null);
				return;
			case 26:
				getRaisedExceptions().clear();
				return;
			case 27:
				getRedefinedOperations().clear();
				return;
			case 28:
				setOwnedAccumulator((Parameter)null);
				return;
			case 29:
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
				return isIsMany() != IS_MANY_EDEFAULT;
			case 6:
				return ((eFlags & IS_REQUIRED_EFLAG) != 0) != IS_REQUIRED_EDEFAULT;
			case 7:
				return type != null;
			case 8:
				return IMPLEMENTATION_EDEFAULT == null ? implementation != null : !IMPLEMENTATION_EDEFAULT.equals(implementation);
			case 9:
				return IMPLEMENTATION_CLASS_EDEFAULT == null ? implementationClass != null : !IMPLEMENTATION_CLASS_EDEFAULT.equals(implementationClass);
			case 10:
				return ((eFlags & IS_STATIC_EFLAG) != 0) != IS_STATIC_EDEFAULT;
			case 11:
				return ownedConstraints != null && !ownedConstraints.isEmpty();
			case 12:
				return generic != null;
			case 13:
				return ownedTemplateArguments != null && !ownedTemplateArguments.isEmpty();
			case 14:
				return ownedTemplateParameters != null && !ownedTemplateParameters.isEmpty();
			case 15:
				return ownedWildcards != null && !ownedWildcards.isEmpty();
			case 16:
				return bodyExpression != null;
			case 17:
				return ((eFlags & IS_INVALIDATING_EFLAG) != 0) != IS_INVALIDATING_EDEFAULT;
			case 18:
				return ((eFlags & IS_TRANSIENT_EFLAG) != 0) != IS_TRANSIENT_EDEFAULT;
			case 19:
				return ((eFlags & IS_TYPEOF_EFLAG) != 0) != IS_TYPEOF_EDEFAULT;
			case 20:
				return ((eFlags & IS_VALIDATING_EFLAG) != 0) != IS_VALIDATING_EDEFAULT;
			case 21:
				return ownedParameters != null && !ownedParameters.isEmpty();
			case 22:
				return ownedPostconditions != null && !ownedPostconditions.isEmpty();
			case 23:
				return ownedPreconditions != null && !ownedPreconditions.isEmpty();
			case 24:
				return getOwningClass() != null;
			case 25:
				return precedence != null;
			case 26:
				return raisedExceptions != null && !raisedExceptions.isEmpty();
			case 27:
				return redefinedOperations != null && !redefinedOperations.isEmpty();
			case 28:
				return ownedAccumulator != null;
			case 29:
				return ownedIterators != null && !ownedIterators.isEmpty();
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException
	{
		switch (operationID)
		{
			case 0:
				return allOwnedElements();
			case 1:
				return getValue((Type)arguments.get(0), (String)arguments.get(1));
			case 2:
				return CompatibleBody((ValueSpecification)arguments.get(0));
			case 3:
				return validateNameIsNotNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 4:
				return validateTypeIsNotInvalid((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 5:
				return validateTypeIsNotNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 6:
				return validateCompatibleReturn((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 7:
				return validateLoadableImplementation((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 8:
				return validateUniquePostconditionName((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 9:
				return validateUniquePreconditionName((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 10:
				return resolveBodyType((OCLExpression)arguments.get(0));
		}
		return eDynamicInvoke(operationID, arguments);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitIteration(this);
	}

	@Override
	public void setImplementation(LibraryFeature newImplementation) {
		System.out.println("setImplementation " + NameUtil.debugSimpleName(this) + " " + name + " " + NameUtil.debugSimpleName(newImplementation));
		super.setImplementation(newImplementation);
	}

	@Override
	public void setName(String newName) {
		System.out.println("setName " + NameUtil.debugSimpleName(this) + " " + newName);
		super.setName(newName);
	}
} //IterationImpl
