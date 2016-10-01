/*******************************************************************************
 * Copyright (c) 2013, 2016 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.cgmodel.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.*;
import org.eclipse.ocl.pivot.utilities.Nameable;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage
 * @generated
 */
public class CGModelSwitch<@Nullable T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static CGModelPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CGModelSwitch() {
		if (modelPackage == null) {
			modelPackage = CGModelPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case CGModelPackage.CG_ACCUMULATOR: {
				CGAccumulator cgAccumulator = (CGAccumulator)theEObject;
				T result = caseCGAccumulator(cgAccumulator);
				if (result == null) result = caseCGIterator(cgAccumulator);
				if (result == null) result = caseCGParameter(cgAccumulator);
				if (result == null) result = caseCGVariable(cgAccumulator);
				if (result == null) result = caseCGValuedElement(cgAccumulator);
				if (result == null) result = caseCGTypedElement(cgAccumulator);
				if (result == null) result = caseCGNamedElement(cgAccumulator);
				if (result == null) result = caseCGElement(cgAccumulator);
				if (result == null) result = caseNameable(cgAccumulator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_ASSERT_NON_NULL_EXP: {
				CGAssertNonNullExp cgAssertNonNullExp = (CGAssertNonNullExp)theEObject;
				T result = caseCGAssertNonNullExp(cgAssertNonNullExp);
				if (result == null) result = caseCGCallExp(cgAssertNonNullExp);
				if (result == null) result = caseCGValuedElement(cgAssertNonNullExp);
				if (result == null) result = caseCGTypedElement(cgAssertNonNullExp);
				if (result == null) result = caseCGNamedElement(cgAssertNonNullExp);
				if (result == null) result = caseCGElement(cgAssertNonNullExp);
				if (result == null) result = caseNameable(cgAssertNonNullExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_BOOLEAN: {
				CGBoolean cgBoolean = (CGBoolean)theEObject;
				T result = caseCGBoolean(cgBoolean);
				if (result == null) result = caseCGConstant(cgBoolean);
				if (result == null) result = caseCGValuedElement(cgBoolean);
				if (result == null) result = caseCGTypedElement(cgBoolean);
				if (result == null) result = caseCGNamedElement(cgBoolean);
				if (result == null) result = caseCGElement(cgBoolean);
				if (result == null) result = caseNameable(cgBoolean);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_BOX_EXP: {
				CGBoxExp cgBoxExp = (CGBoxExp)theEObject;
				T result = caseCGBoxExp(cgBoxExp);
				if (result == null) result = caseCGCallExp(cgBoxExp);
				if (result == null) result = caseCGValuedElement(cgBoxExp);
				if (result == null) result = caseCGTypedElement(cgBoxExp);
				if (result == null) result = caseCGNamedElement(cgBoxExp);
				if (result == null) result = caseCGElement(cgBoxExp);
				if (result == null) result = caseNameable(cgBoxExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_BUILT_IN_ITERATION_CALL_EXP: {
				CGBuiltInIterationCallExp cgBuiltInIterationCallExp = (CGBuiltInIterationCallExp)theEObject;
				T result = caseCGBuiltInIterationCallExp(cgBuiltInIterationCallExp);
				if (result == null) result = caseCGIterationCallExp(cgBuiltInIterationCallExp);
				if (result == null) result = caseCGCallExp(cgBuiltInIterationCallExp);
				if (result == null) result = caseCGValuedElement(cgBuiltInIterationCallExp);
				if (result == null) result = caseCGTypedElement(cgBuiltInIterationCallExp);
				if (result == null) result = caseCGNamedElement(cgBuiltInIterationCallExp);
				if (result == null) result = caseCGElement(cgBuiltInIterationCallExp);
				if (result == null) result = caseNameable(cgBuiltInIterationCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_CACHED_OPERATION: {
				CGCachedOperation cgCachedOperation = (CGCachedOperation)theEObject;
				T result = caseCGCachedOperation(cgCachedOperation);
				if (result == null) result = caseCGOperation(cgCachedOperation);
				if (result == null) result = caseCGCallable(cgCachedOperation);
				if (result == null) result = caseCGValuedElement(cgCachedOperation);
				if (result == null) result = caseCGTypedElement(cgCachedOperation);
				if (result == null) result = caseCGNamedElement(cgCachedOperation);
				if (result == null) result = caseCGElement(cgCachedOperation);
				if (result == null) result = caseNameable(cgCachedOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_CACHED_OPERATION_CALL_EXP: {
				CGCachedOperationCallExp cgCachedOperationCallExp = (CGCachedOperationCallExp)theEObject;
				T result = caseCGCachedOperationCallExp(cgCachedOperationCallExp);
				if (result == null) result = caseCGOperationCallExp(cgCachedOperationCallExp);
				if (result == null) result = caseCGCallExp(cgCachedOperationCallExp);
				if (result == null) result = caseCGValuedElement(cgCachedOperationCallExp);
				if (result == null) result = caseCGTypedElement(cgCachedOperationCallExp);
				if (result == null) result = caseCGNamedElement(cgCachedOperationCallExp);
				if (result == null) result = caseCGElement(cgCachedOperationCallExp);
				if (result == null) result = caseNameable(cgCachedOperationCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_CALLABLE: {
				CGCallable cgCallable = (CGCallable)theEObject;
				T result = caseCGCallable(cgCallable);
				if (result == null) result = caseCGValuedElement(cgCallable);
				if (result == null) result = caseCGTypedElement(cgCallable);
				if (result == null) result = caseCGNamedElement(cgCallable);
				if (result == null) result = caseCGElement(cgCallable);
				if (result == null) result = caseNameable(cgCallable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_CALL_EXP: {
				CGCallExp cgCallExp = (CGCallExp)theEObject;
				T result = caseCGCallExp(cgCallExp);
				if (result == null) result = caseCGValuedElement(cgCallExp);
				if (result == null) result = caseCGTypedElement(cgCallExp);
				if (result == null) result = caseCGNamedElement(cgCallExp);
				if (result == null) result = caseCGElement(cgCallExp);
				if (result == null) result = caseNameable(cgCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_CAST_EXP: {
				CGCastExp cgCastExp = (CGCastExp)theEObject;
				T result = caseCGCastExp(cgCastExp);
				if (result == null) result = caseCGCallExp(cgCastExp);
				if (result == null) result = caseCGValuedElement(cgCastExp);
				if (result == null) result = caseCGTypedElement(cgCastExp);
				if (result == null) result = caseCGNamedElement(cgCastExp);
				if (result == null) result = caseCGElement(cgCastExp);
				if (result == null) result = caseNameable(cgCastExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_CATCH_EXP: {
				CGCatchExp cgCatchExp = (CGCatchExp)theEObject;
				T result = caseCGCatchExp(cgCatchExp);
				if (result == null) result = caseCGCallExp(cgCatchExp);
				if (result == null) result = caseCGValuedElement(cgCatchExp);
				if (result == null) result = caseCGTypedElement(cgCatchExp);
				if (result == null) result = caseCGNamedElement(cgCatchExp);
				if (result == null) result = caseCGElement(cgCatchExp);
				if (result == null) result = caseNameable(cgCatchExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_CLASS: {
				CGClass cgClass = (CGClass)theEObject;
				T result = caseCGClass(cgClass);
				if (result == null) result = caseCGNamedElement(cgClass);
				if (result == null) result = caseCGElement(cgClass);
				if (result == null) result = caseNameable(cgClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_COLLECTION_EXP: {
				CGCollectionExp cgCollectionExp = (CGCollectionExp)theEObject;
				T result = caseCGCollectionExp(cgCollectionExp);
				if (result == null) result = caseCGValuedElement(cgCollectionExp);
				if (result == null) result = caseCGTypedElement(cgCollectionExp);
				if (result == null) result = caseCGNamedElement(cgCollectionExp);
				if (result == null) result = caseCGElement(cgCollectionExp);
				if (result == null) result = caseNameable(cgCollectionExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_COLLECTION_PART: {
				CGCollectionPart cgCollectionPart = (CGCollectionPart)theEObject;
				T result = caseCGCollectionPart(cgCollectionPart);
				if (result == null) result = caseCGValuedElement(cgCollectionPart);
				if (result == null) result = caseCGTypedElement(cgCollectionPart);
				if (result == null) result = caseCGNamedElement(cgCollectionPart);
				if (result == null) result = caseCGElement(cgCollectionPart);
				if (result == null) result = caseNameable(cgCollectionPart);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_CONSTANT: {
				CGConstant cgConstant = (CGConstant)theEObject;
				T result = caseCGConstant(cgConstant);
				if (result == null) result = caseCGValuedElement(cgConstant);
				if (result == null) result = caseCGTypedElement(cgConstant);
				if (result == null) result = caseCGNamedElement(cgConstant);
				if (result == null) result = caseCGElement(cgConstant);
				if (result == null) result = caseNameable(cgConstant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_CONSTANT_EXP: {
				CGConstantExp cgConstantExp = (CGConstantExp)theEObject;
				T result = caseCGConstantExp(cgConstantExp);
				if (result == null) result = caseCGValuedElement(cgConstantExp);
				if (result == null) result = caseCGTypedElement(cgConstantExp);
				if (result == null) result = caseCGNamedElement(cgConstantExp);
				if (result == null) result = caseCGElement(cgConstantExp);
				if (result == null) result = caseNameable(cgConstantExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_CONSTRAINT: {
				CGConstraint cgConstraint = (CGConstraint)theEObject;
				T result = caseCGConstraint(cgConstraint);
				if (result == null) result = caseCGCallable(cgConstraint);
				if (result == null) result = caseCGValuedElement(cgConstraint);
				if (result == null) result = caseCGTypedElement(cgConstraint);
				if (result == null) result = caseCGNamedElement(cgConstraint);
				if (result == null) result = caseCGElement(cgConstraint);
				if (result == null) result = caseNameable(cgConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_ECORE_CLASS_SHADOW_EXP: {
				CGEcoreClassShadowExp cgEcoreClassShadowExp = (CGEcoreClassShadowExp)theEObject;
				T result = caseCGEcoreClassShadowExp(cgEcoreClassShadowExp);
				if (result == null) result = caseCGShadowExp(cgEcoreClassShadowExp);
				if (result == null) result = caseCGValuedElement(cgEcoreClassShadowExp);
				if (result == null) result = caseCGTypedElement(cgEcoreClassShadowExp);
				if (result == null) result = caseCGNamedElement(cgEcoreClassShadowExp);
				if (result == null) result = caseCGElement(cgEcoreClassShadowExp);
				if (result == null) result = caseNameable(cgEcoreClassShadowExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_ECORE_DATA_TYPE_SHADOW_EXP: {
				CGEcoreDataTypeShadowExp cgEcoreDataTypeShadowExp = (CGEcoreDataTypeShadowExp)theEObject;
				T result = caseCGEcoreDataTypeShadowExp(cgEcoreDataTypeShadowExp);
				if (result == null) result = caseCGShadowExp(cgEcoreDataTypeShadowExp);
				if (result == null) result = caseCGValuedElement(cgEcoreDataTypeShadowExp);
				if (result == null) result = caseCGTypedElement(cgEcoreDataTypeShadowExp);
				if (result == null) result = caseCGNamedElement(cgEcoreDataTypeShadowExp);
				if (result == null) result = caseCGElement(cgEcoreDataTypeShadowExp);
				if (result == null) result = caseNameable(cgEcoreDataTypeShadowExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_ECORE_EXP: {
				CGEcoreExp cgEcoreExp = (CGEcoreExp)theEObject;
				T result = caseCGEcoreExp(cgEcoreExp);
				if (result == null) result = caseCGCallExp(cgEcoreExp);
				if (result == null) result = caseCGValuedElement(cgEcoreExp);
				if (result == null) result = caseCGTypedElement(cgEcoreExp);
				if (result == null) result = caseCGNamedElement(cgEcoreExp);
				if (result == null) result = caseCGElement(cgEcoreExp);
				if (result == null) result = caseNameable(cgEcoreExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_ECORE_OPERATION: {
				CGEcoreOperation cgEcoreOperation = (CGEcoreOperation)theEObject;
				T result = caseCGEcoreOperation(cgEcoreOperation);
				if (result == null) result = caseCGOperation(cgEcoreOperation);
				if (result == null) result = caseCGCallable(cgEcoreOperation);
				if (result == null) result = caseCGValuedElement(cgEcoreOperation);
				if (result == null) result = caseCGTypedElement(cgEcoreOperation);
				if (result == null) result = caseCGNamedElement(cgEcoreOperation);
				if (result == null) result = caseCGElement(cgEcoreOperation);
				if (result == null) result = caseNameable(cgEcoreOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_ECORE_OPERATION_CALL_EXP: {
				CGEcoreOperationCallExp cgEcoreOperationCallExp = (CGEcoreOperationCallExp)theEObject;
				T result = caseCGEcoreOperationCallExp(cgEcoreOperationCallExp);
				if (result == null) result = caseCGOperationCallExp(cgEcoreOperationCallExp);
				if (result == null) result = caseCGCallExp(cgEcoreOperationCallExp);
				if (result == null) result = caseCGValuedElement(cgEcoreOperationCallExp);
				if (result == null) result = caseCGTypedElement(cgEcoreOperationCallExp);
				if (result == null) result = caseCGNamedElement(cgEcoreOperationCallExp);
				if (result == null) result = caseCGElement(cgEcoreOperationCallExp);
				if (result == null) result = caseNameable(cgEcoreOperationCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_ECORE_OPPOSITE_PROPERTY_CALL_EXP: {
				CGEcoreOppositePropertyCallExp cgEcoreOppositePropertyCallExp = (CGEcoreOppositePropertyCallExp)theEObject;
				T result = caseCGEcoreOppositePropertyCallExp(cgEcoreOppositePropertyCallExp);
				if (result == null) result = caseCGOppositePropertyCallExp(cgEcoreOppositePropertyCallExp);
				if (result == null) result = caseCGNavigationCallExp(cgEcoreOppositePropertyCallExp);
				if (result == null) result = caseCGCallExp(cgEcoreOppositePropertyCallExp);
				if (result == null) result = caseCGValuedElement(cgEcoreOppositePropertyCallExp);
				if (result == null) result = caseCGTypedElement(cgEcoreOppositePropertyCallExp);
				if (result == null) result = caseCGNamedElement(cgEcoreOppositePropertyCallExp);
				if (result == null) result = caseCGElement(cgEcoreOppositePropertyCallExp);
				if (result == null) result = caseNameable(cgEcoreOppositePropertyCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_ECORE_PROPERTY_CALL_EXP: {
				CGEcorePropertyCallExp cgEcorePropertyCallExp = (CGEcorePropertyCallExp)theEObject;
				T result = caseCGEcorePropertyCallExp(cgEcorePropertyCallExp);
				if (result == null) result = caseCGPropertyCallExp(cgEcorePropertyCallExp);
				if (result == null) result = caseCGNavigationCallExp(cgEcorePropertyCallExp);
				if (result == null) result = caseCGCallExp(cgEcorePropertyCallExp);
				if (result == null) result = caseCGValuedElement(cgEcorePropertyCallExp);
				if (result == null) result = caseCGTypedElement(cgEcorePropertyCallExp);
				if (result == null) result = caseCGNamedElement(cgEcorePropertyCallExp);
				if (result == null) result = caseCGElement(cgEcorePropertyCallExp);
				if (result == null) result = caseNameable(cgEcorePropertyCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_ELEMENT: {
				CGElement cgElement = (CGElement)theEObject;
				T result = caseCGElement(cgElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_ELEMENT_ID: {
				CGElementId cgElementId = (CGElementId)theEObject;
				T result = caseCGElementId(cgElementId);
				if (result == null) result = caseCGConstant(cgElementId);
				if (result == null) result = caseCGValuedElement(cgElementId);
				if (result == null) result = caseCGTypedElement(cgElementId);
				if (result == null) result = caseCGNamedElement(cgElementId);
				if (result == null) result = caseCGElement(cgElementId);
				if (result == null) result = caseNameable(cgElementId);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_EXECUTOR_COMPOSITION_PROPERTY: {
				CGExecutorCompositionProperty cgExecutorCompositionProperty = (CGExecutorCompositionProperty)theEObject;
				T result = caseCGExecutorCompositionProperty(cgExecutorCompositionProperty);
				if (result == null) result = caseCGExecutorProperty(cgExecutorCompositionProperty);
				if (result == null) result = caseCGValuedElement(cgExecutorCompositionProperty);
				if (result == null) result = caseCGTypedElement(cgExecutorCompositionProperty);
				if (result == null) result = caseCGNamedElement(cgExecutorCompositionProperty);
				if (result == null) result = caseCGElement(cgExecutorCompositionProperty);
				if (result == null) result = caseNameable(cgExecutorCompositionProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_EXECUTOR_NAVIGATION_PROPERTY: {
				CGExecutorNavigationProperty cgExecutorNavigationProperty = (CGExecutorNavigationProperty)theEObject;
				T result = caseCGExecutorNavigationProperty(cgExecutorNavigationProperty);
				if (result == null) result = caseCGExecutorProperty(cgExecutorNavigationProperty);
				if (result == null) result = caseCGValuedElement(cgExecutorNavigationProperty);
				if (result == null) result = caseCGTypedElement(cgExecutorNavigationProperty);
				if (result == null) result = caseCGNamedElement(cgExecutorNavigationProperty);
				if (result == null) result = caseCGElement(cgExecutorNavigationProperty);
				if (result == null) result = caseNameable(cgExecutorNavigationProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_EXECUTOR_OPPOSITE_PROPERTY: {
				CGExecutorOppositeProperty cgExecutorOppositeProperty = (CGExecutorOppositeProperty)theEObject;
				T result = caseCGExecutorOppositeProperty(cgExecutorOppositeProperty);
				if (result == null) result = caseCGExecutorProperty(cgExecutorOppositeProperty);
				if (result == null) result = caseCGValuedElement(cgExecutorOppositeProperty);
				if (result == null) result = caseCGTypedElement(cgExecutorOppositeProperty);
				if (result == null) result = caseCGNamedElement(cgExecutorOppositeProperty);
				if (result == null) result = caseCGElement(cgExecutorOppositeProperty);
				if (result == null) result = caseNameable(cgExecutorOppositeProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_EXECUTOR_OPERATION: {
				CGExecutorOperation cgExecutorOperation = (CGExecutorOperation)theEObject;
				T result = caseCGExecutorOperation(cgExecutorOperation);
				if (result == null) result = caseCGValuedElement(cgExecutorOperation);
				if (result == null) result = caseCGTypedElement(cgExecutorOperation);
				if (result == null) result = caseCGNamedElement(cgExecutorOperation);
				if (result == null) result = caseCGElement(cgExecutorOperation);
				if (result == null) result = caseNameable(cgExecutorOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_EXECUTOR_OPERATION_CALL_EXP: {
				CGExecutorOperationCallExp cgExecutorOperationCallExp = (CGExecutorOperationCallExp)theEObject;
				T result = caseCGExecutorOperationCallExp(cgExecutorOperationCallExp);
				if (result == null) result = caseCGOperationCallExp(cgExecutorOperationCallExp);
				if (result == null) result = caseCGCallExp(cgExecutorOperationCallExp);
				if (result == null) result = caseCGValuedElement(cgExecutorOperationCallExp);
				if (result == null) result = caseCGTypedElement(cgExecutorOperationCallExp);
				if (result == null) result = caseCGNamedElement(cgExecutorOperationCallExp);
				if (result == null) result = caseCGElement(cgExecutorOperationCallExp);
				if (result == null) result = caseNameable(cgExecutorOperationCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_EXECUTOR_OPPOSITE_PROPERTY_CALL_EXP: {
				CGExecutorOppositePropertyCallExp cgExecutorOppositePropertyCallExp = (CGExecutorOppositePropertyCallExp)theEObject;
				T result = caseCGExecutorOppositePropertyCallExp(cgExecutorOppositePropertyCallExp);
				if (result == null) result = caseCGOppositePropertyCallExp(cgExecutorOppositePropertyCallExp);
				if (result == null) result = caseCGNavigationCallExp(cgExecutorOppositePropertyCallExp);
				if (result == null) result = caseCGCallExp(cgExecutorOppositePropertyCallExp);
				if (result == null) result = caseCGValuedElement(cgExecutorOppositePropertyCallExp);
				if (result == null) result = caseCGTypedElement(cgExecutorOppositePropertyCallExp);
				if (result == null) result = caseCGNamedElement(cgExecutorOppositePropertyCallExp);
				if (result == null) result = caseCGElement(cgExecutorOppositePropertyCallExp);
				if (result == null) result = caseNameable(cgExecutorOppositePropertyCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_EXECUTOR_PROPERTY: {
				CGExecutorProperty cgExecutorProperty = (CGExecutorProperty)theEObject;
				T result = caseCGExecutorProperty(cgExecutorProperty);
				if (result == null) result = caseCGValuedElement(cgExecutorProperty);
				if (result == null) result = caseCGTypedElement(cgExecutorProperty);
				if (result == null) result = caseCGNamedElement(cgExecutorProperty);
				if (result == null) result = caseCGElement(cgExecutorProperty);
				if (result == null) result = caseNameable(cgExecutorProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_EXECUTOR_PROPERTY_CALL_EXP: {
				CGExecutorPropertyCallExp cgExecutorPropertyCallExp = (CGExecutorPropertyCallExp)theEObject;
				T result = caseCGExecutorPropertyCallExp(cgExecutorPropertyCallExp);
				if (result == null) result = caseCGPropertyCallExp(cgExecutorPropertyCallExp);
				if (result == null) result = caseCGNavigationCallExp(cgExecutorPropertyCallExp);
				if (result == null) result = caseCGCallExp(cgExecutorPropertyCallExp);
				if (result == null) result = caseCGValuedElement(cgExecutorPropertyCallExp);
				if (result == null) result = caseCGTypedElement(cgExecutorPropertyCallExp);
				if (result == null) result = caseCGNamedElement(cgExecutorPropertyCallExp);
				if (result == null) result = caseCGElement(cgExecutorPropertyCallExp);
				if (result == null) result = caseNameable(cgExecutorPropertyCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_EXECUTOR_SHADOW_PART: {
				CGExecutorShadowPart cgExecutorShadowPart = (CGExecutorShadowPart)theEObject;
				T result = caseCGExecutorShadowPart(cgExecutorShadowPart);
				if (result == null) result = caseCGExecutorProperty(cgExecutorShadowPart);
				if (result == null) result = caseCGValuedElement(cgExecutorShadowPart);
				if (result == null) result = caseCGTypedElement(cgExecutorShadowPart);
				if (result == null) result = caseCGNamedElement(cgExecutorShadowPart);
				if (result == null) result = caseCGElement(cgExecutorShadowPart);
				if (result == null) result = caseNameable(cgExecutorShadowPart);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_EXECUTOR_TYPE: {
				CGExecutorType cgExecutorType = (CGExecutorType)theEObject;
				T result = caseCGExecutorType(cgExecutorType);
				if (result == null) result = caseCGValuedElement(cgExecutorType);
				if (result == null) result = caseCGTypedElement(cgExecutorType);
				if (result == null) result = caseCGNamedElement(cgExecutorType);
				if (result == null) result = caseCGElement(cgExecutorType);
				if (result == null) result = caseNameable(cgExecutorType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_FINAL_VARIABLE: {
				CGFinalVariable cgFinalVariable = (CGFinalVariable)theEObject;
				T result = caseCGFinalVariable(cgFinalVariable);
				if (result == null) result = caseCGVariable(cgFinalVariable);
				if (result == null) result = caseCGValuedElement(cgFinalVariable);
				if (result == null) result = caseCGTypedElement(cgFinalVariable);
				if (result == null) result = caseCGNamedElement(cgFinalVariable);
				if (result == null) result = caseCGElement(cgFinalVariable);
				if (result == null) result = caseNameable(cgFinalVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_GUARD_EXP: {
				CGGuardExp cgGuardExp = (CGGuardExp)theEObject;
				T result = caseCGGuardExp(cgGuardExp);
				if (result == null) result = caseCGCallExp(cgGuardExp);
				if (result == null) result = caseCGValuedElement(cgGuardExp);
				if (result == null) result = caseCGTypedElement(cgGuardExp);
				if (result == null) result = caseCGNamedElement(cgGuardExp);
				if (result == null) result = caseCGElement(cgGuardExp);
				if (result == null) result = caseNameable(cgGuardExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_IF_EXP: {
				CGIfExp cgIfExp = (CGIfExp)theEObject;
				T result = caseCGIfExp(cgIfExp);
				if (result == null) result = caseCGValuedElement(cgIfExp);
				if (result == null) result = caseCGTypedElement(cgIfExp);
				if (result == null) result = caseCGNamedElement(cgIfExp);
				if (result == null) result = caseCGElement(cgIfExp);
				if (result == null) result = caseNameable(cgIfExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_INTEGER: {
				CGInteger cgInteger = (CGInteger)theEObject;
				T result = caseCGInteger(cgInteger);
				if (result == null) result = caseCGNumber(cgInteger);
				if (result == null) result = caseCGConstant(cgInteger);
				if (result == null) result = caseCGValuedElement(cgInteger);
				if (result == null) result = caseCGTypedElement(cgInteger);
				if (result == null) result = caseCGNamedElement(cgInteger);
				if (result == null) result = caseCGElement(cgInteger);
				if (result == null) result = caseNameable(cgInteger);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_INVALID: {
				CGInvalid cgInvalid = (CGInvalid)theEObject;
				T result = caseCGInvalid(cgInvalid);
				if (result == null) result = caseCGConstant(cgInvalid);
				if (result == null) result = caseCGValuedElement(cgInvalid);
				if (result == null) result = caseCGTypedElement(cgInvalid);
				if (result == null) result = caseCGNamedElement(cgInvalid);
				if (result == null) result = caseCGElement(cgInvalid);
				if (result == null) result = caseNameable(cgInvalid);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_IS_EQUAL_EXP: {
				CGIsEqualExp cgIsEqualExp = (CGIsEqualExp)theEObject;
				T result = caseCGIsEqualExp(cgIsEqualExp);
				if (result == null) result = caseCGCallExp(cgIsEqualExp);
				if (result == null) result = caseCGValuedElement(cgIsEqualExp);
				if (result == null) result = caseCGTypedElement(cgIsEqualExp);
				if (result == null) result = caseCGNamedElement(cgIsEqualExp);
				if (result == null) result = caseCGElement(cgIsEqualExp);
				if (result == null) result = caseNameable(cgIsEqualExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_IS_EQUAL2_EXP: {
				CGIsEqual2Exp cgIsEqual2Exp = (CGIsEqual2Exp)theEObject;
				T result = caseCGIsEqual2Exp(cgIsEqual2Exp);
				if (result == null) result = caseCGCallExp(cgIsEqual2Exp);
				if (result == null) result = caseCGValuedElement(cgIsEqual2Exp);
				if (result == null) result = caseCGTypedElement(cgIsEqual2Exp);
				if (result == null) result = caseCGNamedElement(cgIsEqual2Exp);
				if (result == null) result = caseCGElement(cgIsEqual2Exp);
				if (result == null) result = caseNameable(cgIsEqual2Exp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_IS_INVALID_EXP: {
				CGIsInvalidExp cgIsInvalidExp = (CGIsInvalidExp)theEObject;
				T result = caseCGIsInvalidExp(cgIsInvalidExp);
				if (result == null) result = caseCGCallExp(cgIsInvalidExp);
				if (result == null) result = caseCGValuedElement(cgIsInvalidExp);
				if (result == null) result = caseCGTypedElement(cgIsInvalidExp);
				if (result == null) result = caseCGNamedElement(cgIsInvalidExp);
				if (result == null) result = caseCGElement(cgIsInvalidExp);
				if (result == null) result = caseNameable(cgIsInvalidExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_IS_KIND_OF_EXP: {
				CGIsKindOfExp cgIsKindOfExp = (CGIsKindOfExp)theEObject;
				T result = caseCGIsKindOfExp(cgIsKindOfExp);
				if (result == null) result = caseCGCallExp(cgIsKindOfExp);
				if (result == null) result = caseCGValuedElement(cgIsKindOfExp);
				if (result == null) result = caseCGTypedElement(cgIsKindOfExp);
				if (result == null) result = caseCGNamedElement(cgIsKindOfExp);
				if (result == null) result = caseCGElement(cgIsKindOfExp);
				if (result == null) result = caseNameable(cgIsKindOfExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_IS_UNDEFINED_EXP: {
				CGIsUndefinedExp cgIsUndefinedExp = (CGIsUndefinedExp)theEObject;
				T result = caseCGIsUndefinedExp(cgIsUndefinedExp);
				if (result == null) result = caseCGCallExp(cgIsUndefinedExp);
				if (result == null) result = caseCGValuedElement(cgIsUndefinedExp);
				if (result == null) result = caseCGTypedElement(cgIsUndefinedExp);
				if (result == null) result = caseCGNamedElement(cgIsUndefinedExp);
				if (result == null) result = caseCGElement(cgIsUndefinedExp);
				if (result == null) result = caseNameable(cgIsUndefinedExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_ITERATION_CALL_EXP: {
				CGIterationCallExp cgIterationCallExp = (CGIterationCallExp)theEObject;
				T result = caseCGIterationCallExp(cgIterationCallExp);
				if (result == null) result = caseCGCallExp(cgIterationCallExp);
				if (result == null) result = caseCGValuedElement(cgIterationCallExp);
				if (result == null) result = caseCGTypedElement(cgIterationCallExp);
				if (result == null) result = caseCGNamedElement(cgIterationCallExp);
				if (result == null) result = caseCGElement(cgIterationCallExp);
				if (result == null) result = caseNameable(cgIterationCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_ITERATOR: {
				CGIterator cgIterator = (CGIterator)theEObject;
				T result = caseCGIterator(cgIterator);
				if (result == null) result = caseCGParameter(cgIterator);
				if (result == null) result = caseCGVariable(cgIterator);
				if (result == null) result = caseCGValuedElement(cgIterator);
				if (result == null) result = caseCGTypedElement(cgIterator);
				if (result == null) result = caseCGNamedElement(cgIterator);
				if (result == null) result = caseCGElement(cgIterator);
				if (result == null) result = caseNameable(cgIterator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_LET_EXP: {
				CGLetExp cgLetExp = (CGLetExp)theEObject;
				T result = caseCGLetExp(cgLetExp);
				if (result == null) result = caseCGValuedElement(cgLetExp);
				if (result == null) result = caseCGTypedElement(cgLetExp);
				if (result == null) result = caseCGNamedElement(cgLetExp);
				if (result == null) result = caseCGElement(cgLetExp);
				if (result == null) result = caseNameable(cgLetExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_LIBRARY_ITERATE_CALL_EXP: {
				CGLibraryIterateCallExp cgLibraryIterateCallExp = (CGLibraryIterateCallExp)theEObject;
				T result = caseCGLibraryIterateCallExp(cgLibraryIterateCallExp);
				if (result == null) result = caseCGLibraryIterationCallExp(cgLibraryIterateCallExp);
				if (result == null) result = caseCGIterationCallExp(cgLibraryIterateCallExp);
				if (result == null) result = caseCGCallExp(cgLibraryIterateCallExp);
				if (result == null) result = caseCGValuedElement(cgLibraryIterateCallExp);
				if (result == null) result = caseCGTypedElement(cgLibraryIterateCallExp);
				if (result == null) result = caseCGNamedElement(cgLibraryIterateCallExp);
				if (result == null) result = caseCGElement(cgLibraryIterateCallExp);
				if (result == null) result = caseNameable(cgLibraryIterateCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_LIBRARY_ITERATION_CALL_EXP: {
				CGLibraryIterationCallExp cgLibraryIterationCallExp = (CGLibraryIterationCallExp)theEObject;
				T result = caseCGLibraryIterationCallExp(cgLibraryIterationCallExp);
				if (result == null) result = caseCGIterationCallExp(cgLibraryIterationCallExp);
				if (result == null) result = caseCGCallExp(cgLibraryIterationCallExp);
				if (result == null) result = caseCGValuedElement(cgLibraryIterationCallExp);
				if (result == null) result = caseCGTypedElement(cgLibraryIterationCallExp);
				if (result == null) result = caseCGNamedElement(cgLibraryIterationCallExp);
				if (result == null) result = caseCGElement(cgLibraryIterationCallExp);
				if (result == null) result = caseNameable(cgLibraryIterationCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_LIBRARY_OPERATION: {
				CGLibraryOperation cgLibraryOperation = (CGLibraryOperation)theEObject;
				T result = caseCGLibraryOperation(cgLibraryOperation);
				if (result == null) result = caseCGOperation(cgLibraryOperation);
				if (result == null) result = caseCGCallable(cgLibraryOperation);
				if (result == null) result = caseCGValuedElement(cgLibraryOperation);
				if (result == null) result = caseCGTypedElement(cgLibraryOperation);
				if (result == null) result = caseCGNamedElement(cgLibraryOperation);
				if (result == null) result = caseCGElement(cgLibraryOperation);
				if (result == null) result = caseNameable(cgLibraryOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_LIBRARY_OPERATION_CALL_EXP: {
				CGLibraryOperationCallExp cgLibraryOperationCallExp = (CGLibraryOperationCallExp)theEObject;
				T result = caseCGLibraryOperationCallExp(cgLibraryOperationCallExp);
				if (result == null) result = caseCGOperationCallExp(cgLibraryOperationCallExp);
				if (result == null) result = caseCGCallExp(cgLibraryOperationCallExp);
				if (result == null) result = caseCGValuedElement(cgLibraryOperationCallExp);
				if (result == null) result = caseCGTypedElement(cgLibraryOperationCallExp);
				if (result == null) result = caseCGNamedElement(cgLibraryOperationCallExp);
				if (result == null) result = caseCGElement(cgLibraryOperationCallExp);
				if (result == null) result = caseNameable(cgLibraryOperationCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_LIBRARY_PROPERTY_CALL_EXP: {
				CGLibraryPropertyCallExp cgLibraryPropertyCallExp = (CGLibraryPropertyCallExp)theEObject;
				T result = caseCGLibraryPropertyCallExp(cgLibraryPropertyCallExp);
				if (result == null) result = caseCGPropertyCallExp(cgLibraryPropertyCallExp);
				if (result == null) result = caseCGNavigationCallExp(cgLibraryPropertyCallExp);
				if (result == null) result = caseCGCallExp(cgLibraryPropertyCallExp);
				if (result == null) result = caseCGValuedElement(cgLibraryPropertyCallExp);
				if (result == null) result = caseCGTypedElement(cgLibraryPropertyCallExp);
				if (result == null) result = caseCGNamedElement(cgLibraryPropertyCallExp);
				if (result == null) result = caseCGElement(cgLibraryPropertyCallExp);
				if (result == null) result = caseNameable(cgLibraryPropertyCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_LOCAL_VARIABLE: {
				CGLocalVariable cgLocalVariable = (CGLocalVariable)theEObject;
				T result = caseCGLocalVariable(cgLocalVariable);
				if (result == null) result = caseCGVariable(cgLocalVariable);
				if (result == null) result = caseCGValuedElement(cgLocalVariable);
				if (result == null) result = caseCGTypedElement(cgLocalVariable);
				if (result == null) result = caseCGNamedElement(cgLocalVariable);
				if (result == null) result = caseCGElement(cgLocalVariable);
				if (result == null) result = caseNameable(cgLocalVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_MAP_EXP: {
				CGMapExp cgMapExp = (CGMapExp)theEObject;
				T result = caseCGMapExp(cgMapExp);
				if (result == null) result = caseCGValuedElement(cgMapExp);
				if (result == null) result = caseCGTypedElement(cgMapExp);
				if (result == null) result = caseCGNamedElement(cgMapExp);
				if (result == null) result = caseCGElement(cgMapExp);
				if (result == null) result = caseNameable(cgMapExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_MAP_PART: {
				CGMapPart cgMapPart = (CGMapPart)theEObject;
				T result = caseCGMapPart(cgMapPart);
				if (result == null) result = caseCGValuedElement(cgMapPart);
				if (result == null) result = caseCGTypedElement(cgMapPart);
				if (result == null) result = caseCGNamedElement(cgMapPart);
				if (result == null) result = caseCGElement(cgMapPart);
				if (result == null) result = caseNameable(cgMapPart);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_MODEL: {
				CGModel cgModel = (CGModel)theEObject;
				T result = caseCGModel(cgModel);
				if (result == null) result = caseCGNamedElement(cgModel);
				if (result == null) result = caseCGElement(cgModel);
				if (result == null) result = caseNameable(cgModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_NAMED_ELEMENT: {
				CGNamedElement cgNamedElement = (CGNamedElement)theEObject;
				T result = caseCGNamedElement(cgNamedElement);
				if (result == null) result = caseCGElement(cgNamedElement);
				if (result == null) result = caseNameable(cgNamedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_NATIVE_OPERATION: {
				CGNativeOperation cgNativeOperation = (CGNativeOperation)theEObject;
				T result = caseCGNativeOperation(cgNativeOperation);
				if (result == null) result = caseCGOperation(cgNativeOperation);
				if (result == null) result = caseCGCallable(cgNativeOperation);
				if (result == null) result = caseCGValuedElement(cgNativeOperation);
				if (result == null) result = caseCGTypedElement(cgNativeOperation);
				if (result == null) result = caseCGNamedElement(cgNativeOperation);
				if (result == null) result = caseCGElement(cgNativeOperation);
				if (result == null) result = caseNameable(cgNativeOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_NATIVE_OPERATION_CALL_EXP: {
				CGNativeOperationCallExp cgNativeOperationCallExp = (CGNativeOperationCallExp)theEObject;
				T result = caseCGNativeOperationCallExp(cgNativeOperationCallExp);
				if (result == null) result = caseCGOperationCallExp(cgNativeOperationCallExp);
				if (result == null) result = caseCGCallExp(cgNativeOperationCallExp);
				if (result == null) result = caseCGValuedElement(cgNativeOperationCallExp);
				if (result == null) result = caseCGTypedElement(cgNativeOperationCallExp);
				if (result == null) result = caseCGNamedElement(cgNativeOperationCallExp);
				if (result == null) result = caseCGElement(cgNativeOperationCallExp);
				if (result == null) result = caseNameable(cgNativeOperationCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_NATIVE_PROPERTY: {
				CGNativeProperty cgNativeProperty = (CGNativeProperty)theEObject;
				T result = caseCGNativeProperty(cgNativeProperty);
				if (result == null) result = caseCGProperty(cgNativeProperty);
				if (result == null) result = caseCGValuedElement(cgNativeProperty);
				if (result == null) result = caseCGTypedElement(cgNativeProperty);
				if (result == null) result = caseCGNamedElement(cgNativeProperty);
				if (result == null) result = caseCGElement(cgNativeProperty);
				if (result == null) result = caseNameable(cgNativeProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_NATIVE_PROPERTY_CALL_EXP: {
				CGNativePropertyCallExp cgNativePropertyCallExp = (CGNativePropertyCallExp)theEObject;
				T result = caseCGNativePropertyCallExp(cgNativePropertyCallExp);
				if (result == null) result = caseCGPropertyCallExp(cgNativePropertyCallExp);
				if (result == null) result = caseCGNavigationCallExp(cgNativePropertyCallExp);
				if (result == null) result = caseCGCallExp(cgNativePropertyCallExp);
				if (result == null) result = caseCGValuedElement(cgNativePropertyCallExp);
				if (result == null) result = caseCGTypedElement(cgNativePropertyCallExp);
				if (result == null) result = caseCGNamedElement(cgNativePropertyCallExp);
				if (result == null) result = caseCGElement(cgNativePropertyCallExp);
				if (result == null) result = caseNameable(cgNativePropertyCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_NAVIGATION_CALL_EXP: {
				CGNavigationCallExp cgNavigationCallExp = (CGNavigationCallExp)theEObject;
				T result = caseCGNavigationCallExp(cgNavigationCallExp);
				if (result == null) result = caseCGCallExp(cgNavigationCallExp);
				if (result == null) result = caseCGValuedElement(cgNavigationCallExp);
				if (result == null) result = caseCGTypedElement(cgNavigationCallExp);
				if (result == null) result = caseCGNamedElement(cgNavigationCallExp);
				if (result == null) result = caseCGElement(cgNavigationCallExp);
				if (result == null) result = caseNameable(cgNavigationCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_NULL: {
				CGNull cgNull = (CGNull)theEObject;
				T result = caseCGNull(cgNull);
				if (result == null) result = caseCGConstant(cgNull);
				if (result == null) result = caseCGValuedElement(cgNull);
				if (result == null) result = caseCGTypedElement(cgNull);
				if (result == null) result = caseCGNamedElement(cgNull);
				if (result == null) result = caseCGElement(cgNull);
				if (result == null) result = caseNameable(cgNull);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_NUMBER: {
				CGNumber cgNumber = (CGNumber)theEObject;
				T result = caseCGNumber(cgNumber);
				if (result == null) result = caseCGConstant(cgNumber);
				if (result == null) result = caseCGValuedElement(cgNumber);
				if (result == null) result = caseCGTypedElement(cgNumber);
				if (result == null) result = caseCGNamedElement(cgNumber);
				if (result == null) result = caseCGElement(cgNumber);
				if (result == null) result = caseNameable(cgNumber);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_OPERATION: {
				CGOperation cgOperation = (CGOperation)theEObject;
				T result = caseCGOperation(cgOperation);
				if (result == null) result = caseCGCallable(cgOperation);
				if (result == null) result = caseCGValuedElement(cgOperation);
				if (result == null) result = caseCGTypedElement(cgOperation);
				if (result == null) result = caseCGNamedElement(cgOperation);
				if (result == null) result = caseCGElement(cgOperation);
				if (result == null) result = caseNameable(cgOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_OPERATION_CALL_EXP: {
				CGOperationCallExp cgOperationCallExp = (CGOperationCallExp)theEObject;
				T result = caseCGOperationCallExp(cgOperationCallExp);
				if (result == null) result = caseCGCallExp(cgOperationCallExp);
				if (result == null) result = caseCGValuedElement(cgOperationCallExp);
				if (result == null) result = caseCGTypedElement(cgOperationCallExp);
				if (result == null) result = caseCGNamedElement(cgOperationCallExp);
				if (result == null) result = caseCGElement(cgOperationCallExp);
				if (result == null) result = caseNameable(cgOperationCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_OPPOSITE_PROPERTY_CALL_EXP: {
				CGOppositePropertyCallExp cgOppositePropertyCallExp = (CGOppositePropertyCallExp)theEObject;
				T result = caseCGOppositePropertyCallExp(cgOppositePropertyCallExp);
				if (result == null) result = caseCGNavigationCallExp(cgOppositePropertyCallExp);
				if (result == null) result = caseCGCallExp(cgOppositePropertyCallExp);
				if (result == null) result = caseCGValuedElement(cgOppositePropertyCallExp);
				if (result == null) result = caseCGTypedElement(cgOppositePropertyCallExp);
				if (result == null) result = caseCGNamedElement(cgOppositePropertyCallExp);
				if (result == null) result = caseCGElement(cgOppositePropertyCallExp);
				if (result == null) result = caseNameable(cgOppositePropertyCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_PACKAGE: {
				CGPackage cgPackage = (CGPackage)theEObject;
				T result = caseCGPackage(cgPackage);
				if (result == null) result = caseCGNamedElement(cgPackage);
				if (result == null) result = caseCGElement(cgPackage);
				if (result == null) result = caseNameable(cgPackage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_PARAMETER: {
				CGParameter cgParameter = (CGParameter)theEObject;
				T result = caseCGParameter(cgParameter);
				if (result == null) result = caseCGVariable(cgParameter);
				if (result == null) result = caseCGValuedElement(cgParameter);
				if (result == null) result = caseCGTypedElement(cgParameter);
				if (result == null) result = caseCGNamedElement(cgParameter);
				if (result == null) result = caseCGElement(cgParameter);
				if (result == null) result = caseNameable(cgParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_PROPERTY: {
				CGProperty cgProperty = (CGProperty)theEObject;
				T result = caseCGProperty(cgProperty);
				if (result == null) result = caseCGValuedElement(cgProperty);
				if (result == null) result = caseCGTypedElement(cgProperty);
				if (result == null) result = caseCGNamedElement(cgProperty);
				if (result == null) result = caseCGElement(cgProperty);
				if (result == null) result = caseNameable(cgProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_PROPERTY_CALL_EXP: {
				CGPropertyCallExp cgPropertyCallExp = (CGPropertyCallExp)theEObject;
				T result = caseCGPropertyCallExp(cgPropertyCallExp);
				if (result == null) result = caseCGNavigationCallExp(cgPropertyCallExp);
				if (result == null) result = caseCGCallExp(cgPropertyCallExp);
				if (result == null) result = caseCGValuedElement(cgPropertyCallExp);
				if (result == null) result = caseCGTypedElement(cgPropertyCallExp);
				if (result == null) result = caseCGNamedElement(cgPropertyCallExp);
				if (result == null) result = caseCGElement(cgPropertyCallExp);
				if (result == null) result = caseNameable(cgPropertyCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_REAL: {
				CGReal cgReal = (CGReal)theEObject;
				T result = caseCGReal(cgReal);
				if (result == null) result = caseCGNumber(cgReal);
				if (result == null) result = caseCGConstant(cgReal);
				if (result == null) result = caseCGValuedElement(cgReal);
				if (result == null) result = caseCGTypedElement(cgReal);
				if (result == null) result = caseCGNamedElement(cgReal);
				if (result == null) result = caseCGElement(cgReal);
				if (result == null) result = caseNameable(cgReal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_SETTABLE_VARIABLE: {
				CGSettableVariable cgSettableVariable = (CGSettableVariable)theEObject;
				T result = caseCGSettableVariable(cgSettableVariable);
				if (result == null) result = caseCGVariable(cgSettableVariable);
				if (result == null) result = caseCGValuedElement(cgSettableVariable);
				if (result == null) result = caseCGTypedElement(cgSettableVariable);
				if (result == null) result = caseCGNamedElement(cgSettableVariable);
				if (result == null) result = caseCGElement(cgSettableVariable);
				if (result == null) result = caseNameable(cgSettableVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_SHADOW_EXP: {
				CGShadowExp cgShadowExp = (CGShadowExp)theEObject;
				T result = caseCGShadowExp(cgShadowExp);
				if (result == null) result = caseCGValuedElement(cgShadowExp);
				if (result == null) result = caseCGTypedElement(cgShadowExp);
				if (result == null) result = caseCGNamedElement(cgShadowExp);
				if (result == null) result = caseCGElement(cgShadowExp);
				if (result == null) result = caseNameable(cgShadowExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_SHADOW_PART: {
				CGShadowPart cgShadowPart = (CGShadowPart)theEObject;
				T result = caseCGShadowPart(cgShadowPart);
				if (result == null) result = caseCGValuedElement(cgShadowPart);
				if (result == null) result = caseCGTypedElement(cgShadowPart);
				if (result == null) result = caseCGNamedElement(cgShadowPart);
				if (result == null) result = caseCGElement(cgShadowPart);
				if (result == null) result = caseNameable(cgShadowPart);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_STRING: {
				CGString cgString = (CGString)theEObject;
				T result = caseCGString(cgString);
				if (result == null) result = caseCGConstant(cgString);
				if (result == null) result = caseCGValuedElement(cgString);
				if (result == null) result = caseCGTypedElement(cgString);
				if (result == null) result = caseCGNamedElement(cgString);
				if (result == null) result = caseCGElement(cgString);
				if (result == null) result = caseNameable(cgString);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_TEXT: {
				CGText cgText = (CGText)theEObject;
				T result = caseCGText(cgText);
				if (result == null) result = caseCGConstant(cgText);
				if (result == null) result = caseCGValuedElement(cgText);
				if (result == null) result = caseCGTypedElement(cgText);
				if (result == null) result = caseCGNamedElement(cgText);
				if (result == null) result = caseCGElement(cgText);
				if (result == null) result = caseNameable(cgText);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_THROW_EXP: {
				CGThrowExp cgThrowExp = (CGThrowExp)theEObject;
				T result = caseCGThrowExp(cgThrowExp);
				if (result == null) result = caseCGCallExp(cgThrowExp);
				if (result == null) result = caseCGValuedElement(cgThrowExp);
				if (result == null) result = caseCGTypedElement(cgThrowExp);
				if (result == null) result = caseCGNamedElement(cgThrowExp);
				if (result == null) result = caseCGElement(cgThrowExp);
				if (result == null) result = caseNameable(cgThrowExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_TUPLE_EXP: {
				CGTupleExp cgTupleExp = (CGTupleExp)theEObject;
				T result = caseCGTupleExp(cgTupleExp);
				if (result == null) result = caseCGValuedElement(cgTupleExp);
				if (result == null) result = caseCGTypedElement(cgTupleExp);
				if (result == null) result = caseCGNamedElement(cgTupleExp);
				if (result == null) result = caseCGElement(cgTupleExp);
				if (result == null) result = caseNameable(cgTupleExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_TUPLE_PART: {
				CGTuplePart cgTuplePart = (CGTuplePart)theEObject;
				T result = caseCGTuplePart(cgTuplePart);
				if (result == null) result = caseCGValuedElement(cgTuplePart);
				if (result == null) result = caseCGTypedElement(cgTuplePart);
				if (result == null) result = caseCGNamedElement(cgTuplePart);
				if (result == null) result = caseCGElement(cgTuplePart);
				if (result == null) result = caseNameable(cgTuplePart);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_TUPLE_PART_CALL_EXP: {
				CGTuplePartCallExp cgTuplePartCallExp = (CGTuplePartCallExp)theEObject;
				T result = caseCGTuplePartCallExp(cgTuplePartCallExp);
				if (result == null) result = caseCGPropertyCallExp(cgTuplePartCallExp);
				if (result == null) result = caseCGNavigationCallExp(cgTuplePartCallExp);
				if (result == null) result = caseCGCallExp(cgTuplePartCallExp);
				if (result == null) result = caseCGValuedElement(cgTuplePartCallExp);
				if (result == null) result = caseCGTypedElement(cgTuplePartCallExp);
				if (result == null) result = caseCGNamedElement(cgTuplePartCallExp);
				if (result == null) result = caseCGElement(cgTuplePartCallExp);
				if (result == null) result = caseNameable(cgTuplePartCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_TYPE_ID: {
				CGTypeId cgTypeId = (CGTypeId)theEObject;
				T result = caseCGTypeId(cgTypeId);
				if (result == null) result = caseCGElementId(cgTypeId);
				if (result == null) result = caseCGConstant(cgTypeId);
				if (result == null) result = caseCGValuedElement(cgTypeId);
				if (result == null) result = caseCGTypedElement(cgTypeId);
				if (result == null) result = caseCGNamedElement(cgTypeId);
				if (result == null) result = caseCGElement(cgTypeId);
				if (result == null) result = caseNameable(cgTypeId);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_TYPE_EXP: {
				CGTypeExp cgTypeExp = (CGTypeExp)theEObject;
				T result = caseCGTypeExp(cgTypeExp);
				if (result == null) result = caseCGValuedElement(cgTypeExp);
				if (result == null) result = caseCGTypedElement(cgTypeExp);
				if (result == null) result = caseCGNamedElement(cgTypeExp);
				if (result == null) result = caseCGElement(cgTypeExp);
				if (result == null) result = caseNameable(cgTypeExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_TYPED_ELEMENT: {
				CGTypedElement cgTypedElement = (CGTypedElement)theEObject;
				T result = caseCGTypedElement(cgTypedElement);
				if (result == null) result = caseCGNamedElement(cgTypedElement);
				if (result == null) result = caseCGElement(cgTypedElement);
				if (result == null) result = caseNameable(cgTypedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_UNBOX_EXP: {
				CGUnboxExp cgUnboxExp = (CGUnboxExp)theEObject;
				T result = caseCGUnboxExp(cgUnboxExp);
				if (result == null) result = caseCGCallExp(cgUnboxExp);
				if (result == null) result = caseCGValuedElement(cgUnboxExp);
				if (result == null) result = caseCGTypedElement(cgUnboxExp);
				if (result == null) result = caseCGNamedElement(cgUnboxExp);
				if (result == null) result = caseCGElement(cgUnboxExp);
				if (result == null) result = caseNameable(cgUnboxExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_UNLIMITED: {
				CGUnlimited cgUnlimited = (CGUnlimited)theEObject;
				T result = caseCGUnlimited(cgUnlimited);
				if (result == null) result = caseCGConstant(cgUnlimited);
				if (result == null) result = caseCGValuedElement(cgUnlimited);
				if (result == null) result = caseCGTypedElement(cgUnlimited);
				if (result == null) result = caseCGNamedElement(cgUnlimited);
				if (result == null) result = caseCGElement(cgUnlimited);
				if (result == null) result = caseNameable(cgUnlimited);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_VALUED_ELEMENT: {
				CGValuedElement cgValuedElement = (CGValuedElement)theEObject;
				T result = caseCGValuedElement(cgValuedElement);
				if (result == null) result = caseCGTypedElement(cgValuedElement);
				if (result == null) result = caseCGNamedElement(cgValuedElement);
				if (result == null) result = caseCGElement(cgValuedElement);
				if (result == null) result = caseNameable(cgValuedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_VARIABLE: {
				CGVariable cgVariable = (CGVariable)theEObject;
				T result = caseCGVariable(cgVariable);
				if (result == null) result = caseCGValuedElement(cgVariable);
				if (result == null) result = caseCGTypedElement(cgVariable);
				if (result == null) result = caseCGNamedElement(cgVariable);
				if (result == null) result = caseCGElement(cgVariable);
				if (result == null) result = caseNameable(cgVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.CG_VARIABLE_EXP: {
				CGVariableExp cgVariableExp = (CGVariableExp)theEObject;
				T result = caseCGVariableExp(cgVariableExp);
				if (result == null) result = caseCGValuedElement(cgVariableExp);
				if (result == null) result = caseCGTypedElement(cgVariableExp);
				if (result == null) result = caseCGNamedElement(cgVariableExp);
				if (result == null) result = caseCGElement(cgVariableExp);
				if (result == null) result = caseNameable(cgVariableExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CGModelPackage.NAMEABLE: {
				Nameable nameable = (Nameable)theEObject;
				T result = caseNameable(nameable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Accumulator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Accumulator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGAccumulator(CGAccumulator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Assert Non Null Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Assert Non Null Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGAssertNonNullExp(CGAssertNonNullExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Boolean</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Boolean</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGBoolean(CGBoolean object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Box Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Box Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGBoxExp(CGBoxExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Built In Iteration Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Built In Iteration Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGBuiltInIterationCallExp(CGBuiltInIterationCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Cached Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Cached Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGCachedOperation(CGCachedOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Cached Operation Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Cached Operation Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGCachedOperationCallExp(CGCachedOperationCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Callable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Callable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGCallable(CGCallable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGClass(CGClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGCallExp(CGCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Cast Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Cast Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGCastExp(CGCastExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Catch Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Catch Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGCatchExp(CGCatchExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Collection Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Collection Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGCollectionExp(CGCollectionExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Collection Part</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Collection Part</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGCollectionPart(CGCollectionPart object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Constant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Constant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGConstant(CGConstant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Final Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Final Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGFinalVariable(CGFinalVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Guard Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Guard Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGGuardExp(CGGuardExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Ecore Operation Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Ecore Operation Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGEcoreOperationCallExp(CGEcoreOperationCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Ecore Opposite Property Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Ecore Opposite Property Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGEcoreOppositePropertyCallExp(CGEcoreOppositePropertyCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Ecore Property Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Ecore Property Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGEcorePropertyCallExp(CGEcorePropertyCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Integer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Integer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGInteger(CGInteger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Invalid</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Invalid</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGInvalid(CGInvalid object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Is Equal Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Is Equal Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGIsEqualExp(CGIsEqualExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Is Equal2 Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Is Equal2 Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGIsEqual2Exp(CGIsEqual2Exp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Is Invalid Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Is Invalid Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGIsInvalidExp(CGIsInvalidExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Is Kind Of Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Is Kind Of Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGIsKindOfExp(CGIsKindOfExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Is Undefined Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Is Undefined Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGIsUndefinedExp(CGIsUndefinedExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Iteration Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Iteration Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGIterationCallExp(CGIterationCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Iterator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Iterator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGIterator(CGIterator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Library Operation Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Library Operation Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGLibraryOperationCallExp(CGLibraryOperationCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Library Property Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Library Property Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGLibraryPropertyCallExp(CGLibraryPropertyCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Constant Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Constant Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGConstantExp(CGConstantExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGConstraint(CGConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Shadow Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Shadow Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGShadowExp(CGShadowExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Shadow Part</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Shadow Part</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGShadowPart(CGShadowPart object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Ecore Class Shadow Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Ecore Class Shadow Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGEcoreClassShadowExp(CGEcoreClassShadowExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Ecore Data Type Shadow Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Ecore Data Type Shadow Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGEcoreDataTypeShadowExp(CGEcoreDataTypeShadowExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Ecore Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Ecore Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGEcoreExp(CGEcoreExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Ecore Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Ecore Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGEcoreOperation(CGEcoreOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Let Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Let Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGLetExp(CGLetExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Library Iterate Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Library Iterate Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGLibraryIterateCallExp(CGLibraryIterateCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Library Iteration Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Library Iteration Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGLibraryIterationCallExp(CGLibraryIterationCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Library Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Library Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGLibraryOperation(CGLibraryOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Local Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Local Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGLocalVariable(CGLocalVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Map Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Map Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGMapExp(CGMapExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Map Part</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Map Part</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGMapPart(CGMapPart object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGModel(CGModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGElement(CGElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Element Id</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Element Id</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGElementId(CGElementId object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Executor Composition Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Executor Composition Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGExecutorCompositionProperty(CGExecutorCompositionProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Executor Navigation Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Executor Navigation Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGExecutorNavigationProperty(CGExecutorNavigationProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Executor Opposite Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Executor Opposite Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGExecutorOppositeProperty(CGExecutorOppositeProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Executor Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Executor Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGExecutorOperation(CGExecutorOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Executor Operation Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Executor Operation Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGExecutorOperationCallExp(CGExecutorOperationCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Executor Opposite Property Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Executor Opposite Property Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGExecutorOppositePropertyCallExp(CGExecutorOppositePropertyCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Executor Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Executor Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGExecutorProperty(CGExecutorProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Executor Property Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Executor Property Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGExecutorPropertyCallExp(CGExecutorPropertyCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Executor Shadow Part</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Executor Shadow Part</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGExecutorShadowPart(CGExecutorShadowPart object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Executor Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Executor Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGExecutorType(CGExecutorType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG If Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG If Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGIfExp(CGIfExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGNamedElement(CGNamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Native Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Native Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGNativeOperation(CGNativeOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Native Operation Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Native Operation Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGNativeOperationCallExp(CGNativeOperationCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Native Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Native Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGNativeProperty(CGNativeProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Native Property Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Native Property Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGNativePropertyCallExp(CGNativePropertyCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Navigation Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Navigation Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGNavigationCallExp(CGNavigationCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Null</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Null</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGNull(CGNull object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Number</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Number</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGNumber(CGNumber object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGOperation(CGOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Operation Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Operation Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGOperationCallExp(CGOperationCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Opposite Property Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Opposite Property Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGOppositePropertyCallExp(CGOppositePropertyCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGPackage(CGPackage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGParameter(CGParameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGProperty(CGProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Property Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Property Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGPropertyCallExp(CGPropertyCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Real</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Real</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGReal(CGReal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Settable Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Settable Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGSettableVariable(CGSettableVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG String</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG String</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGString(CGString object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Text</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Text</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGText(CGText object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Throw Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Throw Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGThrowExp(CGThrowExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Tuple Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Tuple Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGTupleExp(CGTupleExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Tuple Part</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Tuple Part</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGTuplePart(CGTuplePart object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Tuple Part Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Tuple Part Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGTuplePartCallExp(CGTuplePartCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Type Id</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Type Id</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGTypeId(CGTypeId object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Type Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Type Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGTypeExp(CGTypeExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Typed Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Typed Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGTypedElement(CGTypedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Unbox Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Unbox Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGUnboxExp(CGUnboxExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Unlimited</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Unlimited</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGUnlimited(CGUnlimited object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Valued Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Valued Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGValuedElement(CGValuedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGVariable(CGVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Variable Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Variable Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGVariableExp(CGVariableExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Nameable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Nameable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNameable(Nameable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //CGModelSwitch
