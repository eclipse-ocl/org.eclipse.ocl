/*******************************************************************************
 * Copyright (c) 2013, 2022 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
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
import org.eclipse.ocl.examples.codegen.cgmodel.CGAccumulator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGAssertNonNullExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBodiedProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBoolean;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBoxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCastExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCatchExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstant;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstrainedProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreClassShadowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreDataTypeShadowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOppositePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcorePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElementId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorCompositionProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorNavigationProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOppositeProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOppositePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorShadowPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGFinalVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGForeignOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGForeignProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGForeignPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGGuardExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIfExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIndexExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInlinedOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInteger;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqual2Exp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqualExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsInvalidExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsKindOfExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsUndefinedExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLetExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterateCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLocalVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGMapExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGMapPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModel;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNumber;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOppositePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGReal;
import org.eclipse.ocl.examples.codegen.cgmodel.CGSettableVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGShadowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGShadowPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGSourcedCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGString;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTemplateParameterExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGThrowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTupleExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePartCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGUnboxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGUnlimited;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
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
			case 0: {
				CGAccumulator cgAccumulator = (CGAccumulator)theEObject;
				T result = caseCGAccumulator(cgAccumulator);
				if (result == null) result = caseCGIterator(cgAccumulator);
				if (result == null) result = caseCGParameter(cgAccumulator);
				if (result == null) result = caseCGVariable(cgAccumulator);
				if (result == null) result = caseCGValuedElement(cgAccumulator);
				if (result == null) result = caseCGTypedElement(cgAccumulator);
				if (result == null) result = caseCGNamedElement(cgAccumulator);
				if (result == null) result = caseCGElement(cgAccumulator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 1: {
				CGAssertNonNullExp cgAssertNonNullExp = (CGAssertNonNullExp)theEObject;
				T result = caseCGAssertNonNullExp(cgAssertNonNullExp);
				if (result == null) result = caseCGSourcedCallExp(cgAssertNonNullExp);
				if (result == null) result = caseCGCallExp(cgAssertNonNullExp);
				if (result == null) result = caseCGValuedElement(cgAssertNonNullExp);
				if (result == null) result = caseCGTypedElement(cgAssertNonNullExp);
				if (result == null) result = caseCGNamedElement(cgAssertNonNullExp);
				if (result == null) result = caseCGElement(cgAssertNonNullExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 2: {
				CGBodiedProperty cgBodiedProperty = (CGBodiedProperty)theEObject;
				T result = caseCGBodiedProperty(cgBodiedProperty);
				if (result == null) result = caseCGProperty(cgBodiedProperty);
				if (result == null) result = caseCGValuedElement(cgBodiedProperty);
				if (result == null) result = caseCGTypedElement(cgBodiedProperty);
				if (result == null) result = caseCGNamedElement(cgBodiedProperty);
				if (result == null) result = caseCGElement(cgBodiedProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 3: {
				CGBoolean cgBoolean = (CGBoolean)theEObject;
				T result = caseCGBoolean(cgBoolean);
				if (result == null) result = caseCGConstant(cgBoolean);
				if (result == null) result = caseCGValuedElement(cgBoolean);
				if (result == null) result = caseCGTypedElement(cgBoolean);
				if (result == null) result = caseCGNamedElement(cgBoolean);
				if (result == null) result = caseCGElement(cgBoolean);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 4: {
				CGBoxExp cgBoxExp = (CGBoxExp)theEObject;
				T result = caseCGBoxExp(cgBoxExp);
				if (result == null) result = caseCGSourcedCallExp(cgBoxExp);
				if (result == null) result = caseCGCallExp(cgBoxExp);
				if (result == null) result = caseCGValuedElement(cgBoxExp);
				if (result == null) result = caseCGTypedElement(cgBoxExp);
				if (result == null) result = caseCGNamedElement(cgBoxExp);
				if (result == null) result = caseCGElement(cgBoxExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 5: {
				CGBuiltInIterationCallExp cgBuiltInIterationCallExp = (CGBuiltInIterationCallExp)theEObject;
				T result = caseCGBuiltInIterationCallExp(cgBuiltInIterationCallExp);
				if (result == null) result = caseCGIterationCallExp(cgBuiltInIterationCallExp);
				if (result == null) result = caseCGSourcedCallExp(cgBuiltInIterationCallExp);
				if (result == null) result = caseCGCallExp(cgBuiltInIterationCallExp);
				if (result == null) result = caseCGValuedElement(cgBuiltInIterationCallExp);
				if (result == null) result = caseCGTypedElement(cgBuiltInIterationCallExp);
				if (result == null) result = caseCGNamedElement(cgBuiltInIterationCallExp);
				if (result == null) result = caseCGElement(cgBuiltInIterationCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 6: {
				CGCachedOperation cgCachedOperation = (CGCachedOperation)theEObject;
				T result = caseCGCachedOperation(cgCachedOperation);
				if (result == null) result = caseCGOperation(cgCachedOperation);
				if (result == null) result = caseCGCallable(cgCachedOperation);
				if (result == null) result = caseCGValuedElement(cgCachedOperation);
				if (result == null) result = caseCGTypedElement(cgCachedOperation);
				if (result == null) result = caseCGNamedElement(cgCachedOperation);
				if (result == null) result = caseCGElement(cgCachedOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 7: {
				CGCachedOperationCallExp cgCachedOperationCallExp = (CGCachedOperationCallExp)theEObject;
				T result = caseCGCachedOperationCallExp(cgCachedOperationCallExp);
				if (result == null) result = caseCGOperationCallExp(cgCachedOperationCallExp);
				if (result == null) result = caseCGCallExp(cgCachedOperationCallExp);
				if (result == null) result = caseCGValuedElement(cgCachedOperationCallExp);
				if (result == null) result = caseCGTypedElement(cgCachedOperationCallExp);
				if (result == null) result = caseCGNamedElement(cgCachedOperationCallExp);
				if (result == null) result = caseCGElement(cgCachedOperationCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 8: {
				CGCallable cgCallable = (CGCallable)theEObject;
				T result = caseCGCallable(cgCallable);
				if (result == null) result = caseCGValuedElement(cgCallable);
				if (result == null) result = caseCGTypedElement(cgCallable);
				if (result == null) result = caseCGNamedElement(cgCallable);
				if (result == null) result = caseCGElement(cgCallable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 9: {
				CGCallExp cgCallExp = (CGCallExp)theEObject;
				T result = caseCGCallExp(cgCallExp);
				if (result == null) result = caseCGValuedElement(cgCallExp);
				if (result == null) result = caseCGTypedElement(cgCallExp);
				if (result == null) result = caseCGNamedElement(cgCallExp);
				if (result == null) result = caseCGElement(cgCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 10: {
				CGCastExp cgCastExp = (CGCastExp)theEObject;
				T result = caseCGCastExp(cgCastExp);
				if (result == null) result = caseCGSourcedCallExp(cgCastExp);
				if (result == null) result = caseCGCallExp(cgCastExp);
				if (result == null) result = caseCGValuedElement(cgCastExp);
				if (result == null) result = caseCGTypedElement(cgCastExp);
				if (result == null) result = caseCGNamedElement(cgCastExp);
				if (result == null) result = caseCGElement(cgCastExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 11: {
				CGCatchExp cgCatchExp = (CGCatchExp)theEObject;
				T result = caseCGCatchExp(cgCatchExp);
				if (result == null) result = caseCGSourcedCallExp(cgCatchExp);
				if (result == null) result = caseCGCallExp(cgCatchExp);
				if (result == null) result = caseCGValuedElement(cgCatchExp);
				if (result == null) result = caseCGTypedElement(cgCatchExp);
				if (result == null) result = caseCGNamedElement(cgCatchExp);
				if (result == null) result = caseCGElement(cgCatchExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 12: {
				CGClass cgClass = (CGClass)theEObject;
				T result = caseCGClass(cgClass);
				if (result == null) result = caseCGNamedElement(cgClass);
				if (result == null) result = caseCGElement(cgClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 13: {
				CGCollectionExp cgCollectionExp = (CGCollectionExp)theEObject;
				T result = caseCGCollectionExp(cgCollectionExp);
				if (result == null) result = caseCGValuedElement(cgCollectionExp);
				if (result == null) result = caseCGTypedElement(cgCollectionExp);
				if (result == null) result = caseCGNamedElement(cgCollectionExp);
				if (result == null) result = caseCGElement(cgCollectionExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 14: {
				CGCollectionPart cgCollectionPart = (CGCollectionPart)theEObject;
				T result = caseCGCollectionPart(cgCollectionPart);
				if (result == null) result = caseCGValuedElement(cgCollectionPart);
				if (result == null) result = caseCGTypedElement(cgCollectionPart);
				if (result == null) result = caseCGNamedElement(cgCollectionPart);
				if (result == null) result = caseCGElement(cgCollectionPart);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 15: {
				CGConstant cgConstant = (CGConstant)theEObject;
				T result = caseCGConstant(cgConstant);
				if (result == null) result = caseCGValuedElement(cgConstant);
				if (result == null) result = caseCGTypedElement(cgConstant);
				if (result == null) result = caseCGNamedElement(cgConstant);
				if (result == null) result = caseCGElement(cgConstant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 16: {
				CGConstantExp cgConstantExp = (CGConstantExp)theEObject;
				T result = caseCGConstantExp(cgConstantExp);
				if (result == null) result = caseCGValuedElement(cgConstantExp);
				if (result == null) result = caseCGTypedElement(cgConstantExp);
				if (result == null) result = caseCGNamedElement(cgConstantExp);
				if (result == null) result = caseCGElement(cgConstantExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 17: {
				CGConstrainedProperty cgConstrainedProperty = (CGConstrainedProperty)theEObject;
				T result = caseCGConstrainedProperty(cgConstrainedProperty);
				if (result == null) result = caseCGBodiedProperty(cgConstrainedProperty);
				if (result == null) result = caseCGProperty(cgConstrainedProperty);
				if (result == null) result = caseCGValuedElement(cgConstrainedProperty);
				if (result == null) result = caseCGTypedElement(cgConstrainedProperty);
				if (result == null) result = caseCGNamedElement(cgConstrainedProperty);
				if (result == null) result = caseCGElement(cgConstrainedProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 18: {
				CGConstraint cgConstraint = (CGConstraint)theEObject;
				T result = caseCGConstraint(cgConstraint);
				if (result == null) result = caseCGCallable(cgConstraint);
				if (result == null) result = caseCGValuedElement(cgConstraint);
				if (result == null) result = caseCGTypedElement(cgConstraint);
				if (result == null) result = caseCGNamedElement(cgConstraint);
				if (result == null) result = caseCGElement(cgConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 19: {
				CGEcoreClassShadowExp cgEcoreClassShadowExp = (CGEcoreClassShadowExp)theEObject;
				T result = caseCGEcoreClassShadowExp(cgEcoreClassShadowExp);
				if (result == null) result = caseCGShadowExp(cgEcoreClassShadowExp);
				if (result == null) result = caseCGValuedElement(cgEcoreClassShadowExp);
				if (result == null) result = caseCGTypedElement(cgEcoreClassShadowExp);
				if (result == null) result = caseCGNamedElement(cgEcoreClassShadowExp);
				if (result == null) result = caseCGElement(cgEcoreClassShadowExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 20: {
				CGEcoreContainerAssignment cgEcoreContainerAssignment = (CGEcoreContainerAssignment)theEObject;
				T result = caseCGEcoreContainerAssignment(cgEcoreContainerAssignment);
				if (result == null) result = caseCGPropertyAssignment(cgEcoreContainerAssignment);
				if (result == null) result = caseCGValuedElement(cgEcoreContainerAssignment);
				if (result == null) result = caseCGTypedElement(cgEcoreContainerAssignment);
				if (result == null) result = caseCGNamedElement(cgEcoreContainerAssignment);
				if (result == null) result = caseCGElement(cgEcoreContainerAssignment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 21: {
				CGEcorePropertyAssignment cgEcorePropertyAssignment = (CGEcorePropertyAssignment)theEObject;
				T result = caseCGEcorePropertyAssignment(cgEcorePropertyAssignment);
				if (result == null) result = caseCGPropertyAssignment(cgEcorePropertyAssignment);
				if (result == null) result = caseCGValuedElement(cgEcorePropertyAssignment);
				if (result == null) result = caseCGTypedElement(cgEcorePropertyAssignment);
				if (result == null) result = caseCGNamedElement(cgEcorePropertyAssignment);
				if (result == null) result = caseCGElement(cgEcorePropertyAssignment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 22: {
				CGEcoreDataTypeShadowExp cgEcoreDataTypeShadowExp = (CGEcoreDataTypeShadowExp)theEObject;
				T result = caseCGEcoreDataTypeShadowExp(cgEcoreDataTypeShadowExp);
				if (result == null) result = caseCGShadowExp(cgEcoreDataTypeShadowExp);
				if (result == null) result = caseCGValuedElement(cgEcoreDataTypeShadowExp);
				if (result == null) result = caseCGTypedElement(cgEcoreDataTypeShadowExp);
				if (result == null) result = caseCGNamedElement(cgEcoreDataTypeShadowExp);
				if (result == null) result = caseCGElement(cgEcoreDataTypeShadowExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 23: {
				CGEcoreExp cgEcoreExp = (CGEcoreExp)theEObject;
				T result = caseCGEcoreExp(cgEcoreExp);
				if (result == null) result = caseCGSourcedCallExp(cgEcoreExp);
				if (result == null) result = caseCGCallExp(cgEcoreExp);
				if (result == null) result = caseCGValuedElement(cgEcoreExp);
				if (result == null) result = caseCGTypedElement(cgEcoreExp);
				if (result == null) result = caseCGNamedElement(cgEcoreExp);
				if (result == null) result = caseCGElement(cgEcoreExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 24: {
				CGEcoreOperation cgEcoreOperation = (CGEcoreOperation)theEObject;
				T result = caseCGEcoreOperation(cgEcoreOperation);
				if (result == null) result = caseCGOperation(cgEcoreOperation);
				if (result == null) result = caseCGCallable(cgEcoreOperation);
				if (result == null) result = caseCGValuedElement(cgEcoreOperation);
				if (result == null) result = caseCGTypedElement(cgEcoreOperation);
				if (result == null) result = caseCGNamedElement(cgEcoreOperation);
				if (result == null) result = caseCGElement(cgEcoreOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 25: {
				CGEcoreOperationCallExp cgEcoreOperationCallExp = (CGEcoreOperationCallExp)theEObject;
				T result = caseCGEcoreOperationCallExp(cgEcoreOperationCallExp);
				if (result == null) result = caseCGOperationCallExp(cgEcoreOperationCallExp);
				if (result == null) result = caseCGCallExp(cgEcoreOperationCallExp);
				if (result == null) result = caseCGValuedElement(cgEcoreOperationCallExp);
				if (result == null) result = caseCGTypedElement(cgEcoreOperationCallExp);
				if (result == null) result = caseCGNamedElement(cgEcoreOperationCallExp);
				if (result == null) result = caseCGElement(cgEcoreOperationCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 26: {
				CGEcoreOppositePropertyCallExp cgEcoreOppositePropertyCallExp = (CGEcoreOppositePropertyCallExp)theEObject;
				T result = caseCGEcoreOppositePropertyCallExp(cgEcoreOppositePropertyCallExp);
				if (result == null) result = caseCGOppositePropertyCallExp(cgEcoreOppositePropertyCallExp);
				if (result == null) result = caseCGNavigationCallExp(cgEcoreOppositePropertyCallExp);
				if (result == null) result = caseCGSourcedCallExp(cgEcoreOppositePropertyCallExp);
				if (result == null) result = caseCGCallExp(cgEcoreOppositePropertyCallExp);
				if (result == null) result = caseCGValuedElement(cgEcoreOppositePropertyCallExp);
				if (result == null) result = caseCGTypedElement(cgEcoreOppositePropertyCallExp);
				if (result == null) result = caseCGNamedElement(cgEcoreOppositePropertyCallExp);
				if (result == null) result = caseCGElement(cgEcoreOppositePropertyCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 27: {
				CGEcorePropertyCallExp cgEcorePropertyCallExp = (CGEcorePropertyCallExp)theEObject;
				T result = caseCGEcorePropertyCallExp(cgEcorePropertyCallExp);
				if (result == null) result = caseCGPropertyCallExp(cgEcorePropertyCallExp);
				if (result == null) result = caseCGNavigationCallExp(cgEcorePropertyCallExp);
				if (result == null) result = caseCGSourcedCallExp(cgEcorePropertyCallExp);
				if (result == null) result = caseCGCallExp(cgEcorePropertyCallExp);
				if (result == null) result = caseCGValuedElement(cgEcorePropertyCallExp);
				if (result == null) result = caseCGTypedElement(cgEcorePropertyCallExp);
				if (result == null) result = caseCGNamedElement(cgEcorePropertyCallExp);
				if (result == null) result = caseCGElement(cgEcorePropertyCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 28: {
				CGElement cgElement = (CGElement)theEObject;
				T result = caseCGElement(cgElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 29: {
				CGElementId cgElementId = (CGElementId)theEObject;
				T result = caseCGElementId(cgElementId);
				if (result == null) result = caseCGConstant(cgElementId);
				if (result == null) result = caseCGValuedElement(cgElementId);
				if (result == null) result = caseCGTypedElement(cgElementId);
				if (result == null) result = caseCGNamedElement(cgElementId);
				if (result == null) result = caseCGElement(cgElementId);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 30: {
				CGExecutorCompositionProperty cgExecutorCompositionProperty = (CGExecutorCompositionProperty)theEObject;
				T result = caseCGExecutorCompositionProperty(cgExecutorCompositionProperty);
				if (result == null) result = caseCGExecutorProperty(cgExecutorCompositionProperty);
				if (result == null) result = caseCGProperty(cgExecutorCompositionProperty);
				if (result == null) result = caseCGValuedElement(cgExecutorCompositionProperty);
				if (result == null) result = caseCGTypedElement(cgExecutorCompositionProperty);
				if (result == null) result = caseCGNamedElement(cgExecutorCompositionProperty);
				if (result == null) result = caseCGElement(cgExecutorCompositionProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 31: {
				CGExecutorNavigationProperty cgExecutorNavigationProperty = (CGExecutorNavigationProperty)theEObject;
				T result = caseCGExecutorNavigationProperty(cgExecutorNavigationProperty);
				if (result == null) result = caseCGExecutorProperty(cgExecutorNavigationProperty);
				if (result == null) result = caseCGProperty(cgExecutorNavigationProperty);
				if (result == null) result = caseCGValuedElement(cgExecutorNavigationProperty);
				if (result == null) result = caseCGTypedElement(cgExecutorNavigationProperty);
				if (result == null) result = caseCGNamedElement(cgExecutorNavigationProperty);
				if (result == null) result = caseCGElement(cgExecutorNavigationProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 32: {
				CGExecutorOppositeProperty cgExecutorOppositeProperty = (CGExecutorOppositeProperty)theEObject;
				T result = caseCGExecutorOppositeProperty(cgExecutorOppositeProperty);
				if (result == null) result = caseCGExecutorProperty(cgExecutorOppositeProperty);
				if (result == null) result = caseCGProperty(cgExecutorOppositeProperty);
				if (result == null) result = caseCGValuedElement(cgExecutorOppositeProperty);
				if (result == null) result = caseCGTypedElement(cgExecutorOppositeProperty);
				if (result == null) result = caseCGNamedElement(cgExecutorOppositeProperty);
				if (result == null) result = caseCGElement(cgExecutorOppositeProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 33: {
				CGExecutorOppositePropertyCallExp cgExecutorOppositePropertyCallExp = (CGExecutorOppositePropertyCallExp)theEObject;
				T result = caseCGExecutorOppositePropertyCallExp(cgExecutorOppositePropertyCallExp);
				if (result == null) result = caseCGOppositePropertyCallExp(cgExecutorOppositePropertyCallExp);
				if (result == null) result = caseCGNavigationCallExp(cgExecutorOppositePropertyCallExp);
				if (result == null) result = caseCGSourcedCallExp(cgExecutorOppositePropertyCallExp);
				if (result == null) result = caseCGCallExp(cgExecutorOppositePropertyCallExp);
				if (result == null) result = caseCGValuedElement(cgExecutorOppositePropertyCallExp);
				if (result == null) result = caseCGTypedElement(cgExecutorOppositePropertyCallExp);
				if (result == null) result = caseCGNamedElement(cgExecutorOppositePropertyCallExp);
				if (result == null) result = caseCGElement(cgExecutorOppositePropertyCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 34: {
				CGExecutorProperty cgExecutorProperty = (CGExecutorProperty)theEObject;
				T result = caseCGExecutorProperty(cgExecutorProperty);
				if (result == null) result = caseCGProperty(cgExecutorProperty);
				if (result == null) result = caseCGValuedElement(cgExecutorProperty);
				if (result == null) result = caseCGTypedElement(cgExecutorProperty);
				if (result == null) result = caseCGNamedElement(cgExecutorProperty);
				if (result == null) result = caseCGElement(cgExecutorProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 35: {
				CGExecutorPropertyCallExp cgExecutorPropertyCallExp = (CGExecutorPropertyCallExp)theEObject;
				T result = caseCGExecutorPropertyCallExp(cgExecutorPropertyCallExp);
				if (result == null) result = caseCGPropertyCallExp(cgExecutorPropertyCallExp);
				if (result == null) result = caseCGNavigationCallExp(cgExecutorPropertyCallExp);
				if (result == null) result = caseCGSourcedCallExp(cgExecutorPropertyCallExp);
				if (result == null) result = caseCGCallExp(cgExecutorPropertyCallExp);
				if (result == null) result = caseCGValuedElement(cgExecutorPropertyCallExp);
				if (result == null) result = caseCGTypedElement(cgExecutorPropertyCallExp);
				if (result == null) result = caseCGNamedElement(cgExecutorPropertyCallExp);
				if (result == null) result = caseCGElement(cgExecutorPropertyCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 36: {
				CGExecutorShadowPart cgExecutorShadowPart = (CGExecutorShadowPart)theEObject;
				T result = caseCGExecutorShadowPart(cgExecutorShadowPart);
				if (result == null) result = caseCGExecutorProperty(cgExecutorShadowPart);
				if (result == null) result = caseCGProperty(cgExecutorShadowPart);
				if (result == null) result = caseCGValuedElement(cgExecutorShadowPart);
				if (result == null) result = caseCGTypedElement(cgExecutorShadowPart);
				if (result == null) result = caseCGNamedElement(cgExecutorShadowPart);
				if (result == null) result = caseCGElement(cgExecutorShadowPart);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 37: {
				CGExecutorType cgExecutorType = (CGExecutorType)theEObject;
				T result = caseCGExecutorType(cgExecutorType);
				if (result == null) result = caseCGValuedElement(cgExecutorType);
				if (result == null) result = caseCGTypedElement(cgExecutorType);
				if (result == null) result = caseCGNamedElement(cgExecutorType);
				if (result == null) result = caseCGElement(cgExecutorType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 38: {
				CGFinalVariable cgFinalVariable = (CGFinalVariable)theEObject;
				T result = caseCGFinalVariable(cgFinalVariable);
				if (result == null) result = caseCGVariable(cgFinalVariable);
				if (result == null) result = caseCGValuedElement(cgFinalVariable);
				if (result == null) result = caseCGTypedElement(cgFinalVariable);
				if (result == null) result = caseCGNamedElement(cgFinalVariable);
				if (result == null) result = caseCGElement(cgFinalVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 39: {
				CGForeignOperationCallExp cgForeignOperationCallExp = (CGForeignOperationCallExp)theEObject;
				T result = caseCGForeignOperationCallExp(cgForeignOperationCallExp);
				if (result == null) result = caseCGOperationCallExp(cgForeignOperationCallExp);
				if (result == null) result = caseCGCallExp(cgForeignOperationCallExp);
				if (result == null) result = caseCGValuedElement(cgForeignOperationCallExp);
				if (result == null) result = caseCGTypedElement(cgForeignOperationCallExp);
				if (result == null) result = caseCGNamedElement(cgForeignOperationCallExp);
				if (result == null) result = caseCGElement(cgForeignOperationCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 40: {
				CGForeignProperty cgForeignProperty = (CGForeignProperty)theEObject;
				T result = caseCGForeignProperty(cgForeignProperty);
				if (result == null) result = caseCGBodiedProperty(cgForeignProperty);
				if (result == null) result = caseCGProperty(cgForeignProperty);
				if (result == null) result = caseCGValuedElement(cgForeignProperty);
				if (result == null) result = caseCGTypedElement(cgForeignProperty);
				if (result == null) result = caseCGNamedElement(cgForeignProperty);
				if (result == null) result = caseCGElement(cgForeignProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 41: {
				CGForeignPropertyCallExp cgForeignPropertyCallExp = (CGForeignPropertyCallExp)theEObject;
				T result = caseCGForeignPropertyCallExp(cgForeignPropertyCallExp);
				if (result == null) result = caseCGPropertyCallExp(cgForeignPropertyCallExp);
				if (result == null) result = caseCGNavigationCallExp(cgForeignPropertyCallExp);
				if (result == null) result = caseCGSourcedCallExp(cgForeignPropertyCallExp);
				if (result == null) result = caseCGCallExp(cgForeignPropertyCallExp);
				if (result == null) result = caseCGValuedElement(cgForeignPropertyCallExp);
				if (result == null) result = caseCGTypedElement(cgForeignPropertyCallExp);
				if (result == null) result = caseCGNamedElement(cgForeignPropertyCallExp);
				if (result == null) result = caseCGElement(cgForeignPropertyCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 42: {
				CGGuardExp cgGuardExp = (CGGuardExp)theEObject;
				T result = caseCGGuardExp(cgGuardExp);
				if (result == null) result = caseCGSourcedCallExp(cgGuardExp);
				if (result == null) result = caseCGCallExp(cgGuardExp);
				if (result == null) result = caseCGValuedElement(cgGuardExp);
				if (result == null) result = caseCGTypedElement(cgGuardExp);
				if (result == null) result = caseCGNamedElement(cgGuardExp);
				if (result == null) result = caseCGElement(cgGuardExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 43: {
				CGIfExp cgIfExp = (CGIfExp)theEObject;
				T result = caseCGIfExp(cgIfExp);
				if (result == null) result = caseCGValuedElement(cgIfExp);
				if (result == null) result = caseCGTypedElement(cgIfExp);
				if (result == null) result = caseCGNamedElement(cgIfExp);
				if (result == null) result = caseCGElement(cgIfExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 44: {
				CGIndexExp cgIndexExp = (CGIndexExp)theEObject;
				T result = caseCGIndexExp(cgIndexExp);
				if (result == null) result = caseCGSourcedCallExp(cgIndexExp);
				if (result == null) result = caseCGCallExp(cgIndexExp);
				if (result == null) result = caseCGValuedElement(cgIndexExp);
				if (result == null) result = caseCGTypedElement(cgIndexExp);
				if (result == null) result = caseCGNamedElement(cgIndexExp);
				if (result == null) result = caseCGElement(cgIndexExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 45: {
				CGInlinedOperation cgInlinedOperation = (CGInlinedOperation)theEObject;
				T result = caseCGInlinedOperation(cgInlinedOperation);
				if (result == null) result = caseCGOperation(cgInlinedOperation);
				if (result == null) result = caseCGCallable(cgInlinedOperation);
				if (result == null) result = caseCGValuedElement(cgInlinedOperation);
				if (result == null) result = caseCGTypedElement(cgInlinedOperation);
				if (result == null) result = caseCGNamedElement(cgInlinedOperation);
				if (result == null) result = caseCGElement(cgInlinedOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 46: {
				CGInteger cgInteger = (CGInteger)theEObject;
				T result = caseCGInteger(cgInteger);
				if (result == null) result = caseCGNumber(cgInteger);
				if (result == null) result = caseCGConstant(cgInteger);
				if (result == null) result = caseCGValuedElement(cgInteger);
				if (result == null) result = caseCGTypedElement(cgInteger);
				if (result == null) result = caseCGNamedElement(cgInteger);
				if (result == null) result = caseCGElement(cgInteger);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 47: {
				CGInvalid cgInvalid = (CGInvalid)theEObject;
				T result = caseCGInvalid(cgInvalid);
				if (result == null) result = caseCGConstant(cgInvalid);
				if (result == null) result = caseCGValuedElement(cgInvalid);
				if (result == null) result = caseCGTypedElement(cgInvalid);
				if (result == null) result = caseCGNamedElement(cgInvalid);
				if (result == null) result = caseCGElement(cgInvalid);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 48: {
				CGIsEqualExp cgIsEqualExp = (CGIsEqualExp)theEObject;
				T result = caseCGIsEqualExp(cgIsEqualExp);
				if (result == null) result = caseCGSourcedCallExp(cgIsEqualExp);
				if (result == null) result = caseCGCallExp(cgIsEqualExp);
				if (result == null) result = caseCGValuedElement(cgIsEqualExp);
				if (result == null) result = caseCGTypedElement(cgIsEqualExp);
				if (result == null) result = caseCGNamedElement(cgIsEqualExp);
				if (result == null) result = caseCGElement(cgIsEqualExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 49: {
				CGIsEqual2Exp cgIsEqual2Exp = (CGIsEqual2Exp)theEObject;
				T result = caseCGIsEqual2Exp(cgIsEqual2Exp);
				if (result == null) result = caseCGSourcedCallExp(cgIsEqual2Exp);
				if (result == null) result = caseCGCallExp(cgIsEqual2Exp);
				if (result == null) result = caseCGValuedElement(cgIsEqual2Exp);
				if (result == null) result = caseCGTypedElement(cgIsEqual2Exp);
				if (result == null) result = caseCGNamedElement(cgIsEqual2Exp);
				if (result == null) result = caseCGElement(cgIsEqual2Exp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 50: {
				CGIsInvalidExp cgIsInvalidExp = (CGIsInvalidExp)theEObject;
				T result = caseCGIsInvalidExp(cgIsInvalidExp);
				if (result == null) result = caseCGSourcedCallExp(cgIsInvalidExp);
				if (result == null) result = caseCGCallExp(cgIsInvalidExp);
				if (result == null) result = caseCGValuedElement(cgIsInvalidExp);
				if (result == null) result = caseCGTypedElement(cgIsInvalidExp);
				if (result == null) result = caseCGNamedElement(cgIsInvalidExp);
				if (result == null) result = caseCGElement(cgIsInvalidExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 51: {
				CGIsKindOfExp cgIsKindOfExp = (CGIsKindOfExp)theEObject;
				T result = caseCGIsKindOfExp(cgIsKindOfExp);
				if (result == null) result = caseCGSourcedCallExp(cgIsKindOfExp);
				if (result == null) result = caseCGCallExp(cgIsKindOfExp);
				if (result == null) result = caseCGValuedElement(cgIsKindOfExp);
				if (result == null) result = caseCGTypedElement(cgIsKindOfExp);
				if (result == null) result = caseCGNamedElement(cgIsKindOfExp);
				if (result == null) result = caseCGElement(cgIsKindOfExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 52: {
				CGIsUndefinedExp cgIsUndefinedExp = (CGIsUndefinedExp)theEObject;
				T result = caseCGIsUndefinedExp(cgIsUndefinedExp);
				if (result == null) result = caseCGSourcedCallExp(cgIsUndefinedExp);
				if (result == null) result = caseCGCallExp(cgIsUndefinedExp);
				if (result == null) result = caseCGValuedElement(cgIsUndefinedExp);
				if (result == null) result = caseCGTypedElement(cgIsUndefinedExp);
				if (result == null) result = caseCGNamedElement(cgIsUndefinedExp);
				if (result == null) result = caseCGElement(cgIsUndefinedExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 53: {
				CGIterationCallExp cgIterationCallExp = (CGIterationCallExp)theEObject;
				T result = caseCGIterationCallExp(cgIterationCallExp);
				if (result == null) result = caseCGSourcedCallExp(cgIterationCallExp);
				if (result == null) result = caseCGCallExp(cgIterationCallExp);
				if (result == null) result = caseCGValuedElement(cgIterationCallExp);
				if (result == null) result = caseCGTypedElement(cgIterationCallExp);
				if (result == null) result = caseCGNamedElement(cgIterationCallExp);
				if (result == null) result = caseCGElement(cgIterationCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 54: {
				CGIterator cgIterator = (CGIterator)theEObject;
				T result = caseCGIterator(cgIterator);
				if (result == null) result = caseCGParameter(cgIterator);
				if (result == null) result = caseCGVariable(cgIterator);
				if (result == null) result = caseCGValuedElement(cgIterator);
				if (result == null) result = caseCGTypedElement(cgIterator);
				if (result == null) result = caseCGNamedElement(cgIterator);
				if (result == null) result = caseCGElement(cgIterator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 55: {
				CGLetExp cgLetExp = (CGLetExp)theEObject;
				T result = caseCGLetExp(cgLetExp);
				if (result == null) result = caseCGValuedElement(cgLetExp);
				if (result == null) result = caseCGTypedElement(cgLetExp);
				if (result == null) result = caseCGNamedElement(cgLetExp);
				if (result == null) result = caseCGElement(cgLetExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 56: {
				CGLibraryIterateCallExp cgLibraryIterateCallExp = (CGLibraryIterateCallExp)theEObject;
				T result = caseCGLibraryIterateCallExp(cgLibraryIterateCallExp);
				if (result == null) result = caseCGLibraryIterationCallExp(cgLibraryIterateCallExp);
				if (result == null) result = caseCGIterationCallExp(cgLibraryIterateCallExp);
				if (result == null) result = caseCGSourcedCallExp(cgLibraryIterateCallExp);
				if (result == null) result = caseCGCallExp(cgLibraryIterateCallExp);
				if (result == null) result = caseCGValuedElement(cgLibraryIterateCallExp);
				if (result == null) result = caseCGTypedElement(cgLibraryIterateCallExp);
				if (result == null) result = caseCGNamedElement(cgLibraryIterateCallExp);
				if (result == null) result = caseCGElement(cgLibraryIterateCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 57: {
				CGLibraryIterationCallExp cgLibraryIterationCallExp = (CGLibraryIterationCallExp)theEObject;
				T result = caseCGLibraryIterationCallExp(cgLibraryIterationCallExp);
				if (result == null) result = caseCGIterationCallExp(cgLibraryIterationCallExp);
				if (result == null) result = caseCGSourcedCallExp(cgLibraryIterationCallExp);
				if (result == null) result = caseCGCallExp(cgLibraryIterationCallExp);
				if (result == null) result = caseCGValuedElement(cgLibraryIterationCallExp);
				if (result == null) result = caseCGTypedElement(cgLibraryIterationCallExp);
				if (result == null) result = caseCGNamedElement(cgLibraryIterationCallExp);
				if (result == null) result = caseCGElement(cgLibraryIterationCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 58: {
				CGLibraryOperation cgLibraryOperation = (CGLibraryOperation)theEObject;
				T result = caseCGLibraryOperation(cgLibraryOperation);
				if (result == null) result = caseCGOperation(cgLibraryOperation);
				if (result == null) result = caseCGCallable(cgLibraryOperation);
				if (result == null) result = caseCGValuedElement(cgLibraryOperation);
				if (result == null) result = caseCGTypedElement(cgLibraryOperation);
				if (result == null) result = caseCGNamedElement(cgLibraryOperation);
				if (result == null) result = caseCGElement(cgLibraryOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 59: {
				CGLibraryOperationCallExp cgLibraryOperationCallExp = (CGLibraryOperationCallExp)theEObject;
				T result = caseCGLibraryOperationCallExp(cgLibraryOperationCallExp);
				if (result == null) result = caseCGOperationCallExp(cgLibraryOperationCallExp);
				if (result == null) result = caseCGCallExp(cgLibraryOperationCallExp);
				if (result == null) result = caseCGValuedElement(cgLibraryOperationCallExp);
				if (result == null) result = caseCGTypedElement(cgLibraryOperationCallExp);
				if (result == null) result = caseCGNamedElement(cgLibraryOperationCallExp);
				if (result == null) result = caseCGElement(cgLibraryOperationCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 60: {
				CGLibraryPropertyCallExp cgLibraryPropertyCallExp = (CGLibraryPropertyCallExp)theEObject;
				T result = caseCGLibraryPropertyCallExp(cgLibraryPropertyCallExp);
				if (result == null) result = caseCGPropertyCallExp(cgLibraryPropertyCallExp);
				if (result == null) result = caseCGNavigationCallExp(cgLibraryPropertyCallExp);
				if (result == null) result = caseCGSourcedCallExp(cgLibraryPropertyCallExp);
				if (result == null) result = caseCGCallExp(cgLibraryPropertyCallExp);
				if (result == null) result = caseCGValuedElement(cgLibraryPropertyCallExp);
				if (result == null) result = caseCGTypedElement(cgLibraryPropertyCallExp);
				if (result == null) result = caseCGNamedElement(cgLibraryPropertyCallExp);
				if (result == null) result = caseCGElement(cgLibraryPropertyCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 61: {
				CGLocalVariable cgLocalVariable = (CGLocalVariable)theEObject;
				T result = caseCGLocalVariable(cgLocalVariable);
				if (result == null) result = caseCGVariable(cgLocalVariable);
				if (result == null) result = caseCGValuedElement(cgLocalVariable);
				if (result == null) result = caseCGTypedElement(cgLocalVariable);
				if (result == null) result = caseCGNamedElement(cgLocalVariable);
				if (result == null) result = caseCGElement(cgLocalVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 62: {
				CGMapExp cgMapExp = (CGMapExp)theEObject;
				T result = caseCGMapExp(cgMapExp);
				if (result == null) result = caseCGValuedElement(cgMapExp);
				if (result == null) result = caseCGTypedElement(cgMapExp);
				if (result == null) result = caseCGNamedElement(cgMapExp);
				if (result == null) result = caseCGElement(cgMapExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 63: {
				CGMapPart cgMapPart = (CGMapPart)theEObject;
				T result = caseCGMapPart(cgMapPart);
				if (result == null) result = caseCGValuedElement(cgMapPart);
				if (result == null) result = caseCGTypedElement(cgMapPart);
				if (result == null) result = caseCGNamedElement(cgMapPart);
				if (result == null) result = caseCGElement(cgMapPart);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 64: {
				CGModel cgModel = (CGModel)theEObject;
				T result = caseCGModel(cgModel);
				if (result == null) result = caseCGNamedElement(cgModel);
				if (result == null) result = caseCGElement(cgModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 65: {
				CGNamedElement cgNamedElement = (CGNamedElement)theEObject;
				T result = caseCGNamedElement(cgNamedElement);
				if (result == null) result = caseCGElement(cgNamedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 66: {
				CGNativeOperation cgNativeOperation = (CGNativeOperation)theEObject;
				T result = caseCGNativeOperation(cgNativeOperation);
				if (result == null) result = caseCGOperation(cgNativeOperation);
				if (result == null) result = caseCGCallable(cgNativeOperation);
				if (result == null) result = caseCGValuedElement(cgNativeOperation);
				if (result == null) result = caseCGTypedElement(cgNativeOperation);
				if (result == null) result = caseCGNamedElement(cgNativeOperation);
				if (result == null) result = caseCGElement(cgNativeOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 67: {
				CGNativeOperationCallExp cgNativeOperationCallExp = (CGNativeOperationCallExp)theEObject;
				T result = caseCGNativeOperationCallExp(cgNativeOperationCallExp);
				if (result == null) result = caseCGOperationCallExp(cgNativeOperationCallExp);
				if (result == null) result = caseCGCallExp(cgNativeOperationCallExp);
				if (result == null) result = caseCGValuedElement(cgNativeOperationCallExp);
				if (result == null) result = caseCGTypedElement(cgNativeOperationCallExp);
				if (result == null) result = caseCGNamedElement(cgNativeOperationCallExp);
				if (result == null) result = caseCGElement(cgNativeOperationCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 68: {
				CGNativeProperty cgNativeProperty = (CGNativeProperty)theEObject;
				T result = caseCGNativeProperty(cgNativeProperty);
				if (result == null) result = caseCGBodiedProperty(cgNativeProperty);
				if (result == null) result = caseCGProperty(cgNativeProperty);
				if (result == null) result = caseCGValuedElement(cgNativeProperty);
				if (result == null) result = caseCGTypedElement(cgNativeProperty);
				if (result == null) result = caseCGNamedElement(cgNativeProperty);
				if (result == null) result = caseCGElement(cgNativeProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 69: {
				CGNativePropertyCallExp cgNativePropertyCallExp = (CGNativePropertyCallExp)theEObject;
				T result = caseCGNativePropertyCallExp(cgNativePropertyCallExp);
				if (result == null) result = caseCGPropertyCallExp(cgNativePropertyCallExp);
				if (result == null) result = caseCGNavigationCallExp(cgNativePropertyCallExp);
				if (result == null) result = caseCGSourcedCallExp(cgNativePropertyCallExp);
				if (result == null) result = caseCGCallExp(cgNativePropertyCallExp);
				if (result == null) result = caseCGValuedElement(cgNativePropertyCallExp);
				if (result == null) result = caseCGTypedElement(cgNativePropertyCallExp);
				if (result == null) result = caseCGNamedElement(cgNativePropertyCallExp);
				if (result == null) result = caseCGElement(cgNativePropertyCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 70: {
				CGNavigationCallExp cgNavigationCallExp = (CGNavigationCallExp)theEObject;
				T result = caseCGNavigationCallExp(cgNavigationCallExp);
				if (result == null) result = caseCGSourcedCallExp(cgNavigationCallExp);
				if (result == null) result = caseCGCallExp(cgNavigationCallExp);
				if (result == null) result = caseCGValuedElement(cgNavigationCallExp);
				if (result == null) result = caseCGTypedElement(cgNavigationCallExp);
				if (result == null) result = caseCGNamedElement(cgNavigationCallExp);
				if (result == null) result = caseCGElement(cgNavigationCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 71: {
				CGNull cgNull = (CGNull)theEObject;
				T result = caseCGNull(cgNull);
				if (result == null) result = caseCGConstant(cgNull);
				if (result == null) result = caseCGValuedElement(cgNull);
				if (result == null) result = caseCGTypedElement(cgNull);
				if (result == null) result = caseCGNamedElement(cgNull);
				if (result == null) result = caseCGElement(cgNull);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 72: {
				CGNumber cgNumber = (CGNumber)theEObject;
				T result = caseCGNumber(cgNumber);
				if (result == null) result = caseCGConstant(cgNumber);
				if (result == null) result = caseCGValuedElement(cgNumber);
				if (result == null) result = caseCGTypedElement(cgNumber);
				if (result == null) result = caseCGNamedElement(cgNumber);
				if (result == null) result = caseCGElement(cgNumber);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 73: {
				CGOperation cgOperation = (CGOperation)theEObject;
				T result = caseCGOperation(cgOperation);
				if (result == null) result = caseCGCallable(cgOperation);
				if (result == null) result = caseCGValuedElement(cgOperation);
				if (result == null) result = caseCGTypedElement(cgOperation);
				if (result == null) result = caseCGNamedElement(cgOperation);
				if (result == null) result = caseCGElement(cgOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 74: {
				CGOperationCallExp cgOperationCallExp = (CGOperationCallExp)theEObject;
				T result = caseCGOperationCallExp(cgOperationCallExp);
				if (result == null) result = caseCGCallExp(cgOperationCallExp);
				if (result == null) result = caseCGValuedElement(cgOperationCallExp);
				if (result == null) result = caseCGTypedElement(cgOperationCallExp);
				if (result == null) result = caseCGNamedElement(cgOperationCallExp);
				if (result == null) result = caseCGElement(cgOperationCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 75: {
				CGOppositePropertyCallExp cgOppositePropertyCallExp = (CGOppositePropertyCallExp)theEObject;
				T result = caseCGOppositePropertyCallExp(cgOppositePropertyCallExp);
				if (result == null) result = caseCGNavigationCallExp(cgOppositePropertyCallExp);
				if (result == null) result = caseCGSourcedCallExp(cgOppositePropertyCallExp);
				if (result == null) result = caseCGCallExp(cgOppositePropertyCallExp);
				if (result == null) result = caseCGValuedElement(cgOppositePropertyCallExp);
				if (result == null) result = caseCGTypedElement(cgOppositePropertyCallExp);
				if (result == null) result = caseCGNamedElement(cgOppositePropertyCallExp);
				if (result == null) result = caseCGElement(cgOppositePropertyCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 76: {
				CGPackage cgPackage = (CGPackage)theEObject;
				T result = caseCGPackage(cgPackage);
				if (result == null) result = caseCGNamedElement(cgPackage);
				if (result == null) result = caseCGElement(cgPackage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 77: {
				CGParameter cgParameter = (CGParameter)theEObject;
				T result = caseCGParameter(cgParameter);
				if (result == null) result = caseCGVariable(cgParameter);
				if (result == null) result = caseCGValuedElement(cgParameter);
				if (result == null) result = caseCGTypedElement(cgParameter);
				if (result == null) result = caseCGNamedElement(cgParameter);
				if (result == null) result = caseCGElement(cgParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 78: {
				CGProperty cgProperty = (CGProperty)theEObject;
				T result = caseCGProperty(cgProperty);
				if (result == null) result = caseCGValuedElement(cgProperty);
				if (result == null) result = caseCGTypedElement(cgProperty);
				if (result == null) result = caseCGNamedElement(cgProperty);
				if (result == null) result = caseCGElement(cgProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 79: {
				CGPropertyAssignment cgPropertyAssignment = (CGPropertyAssignment)theEObject;
				T result = caseCGPropertyAssignment(cgPropertyAssignment);
				if (result == null) result = caseCGValuedElement(cgPropertyAssignment);
				if (result == null) result = caseCGTypedElement(cgPropertyAssignment);
				if (result == null) result = caseCGNamedElement(cgPropertyAssignment);
				if (result == null) result = caseCGElement(cgPropertyAssignment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 80: {
				CGPropertyCallExp cgPropertyCallExp = (CGPropertyCallExp)theEObject;
				T result = caseCGPropertyCallExp(cgPropertyCallExp);
				if (result == null) result = caseCGNavigationCallExp(cgPropertyCallExp);
				if (result == null) result = caseCGSourcedCallExp(cgPropertyCallExp);
				if (result == null) result = caseCGCallExp(cgPropertyCallExp);
				if (result == null) result = caseCGValuedElement(cgPropertyCallExp);
				if (result == null) result = caseCGTypedElement(cgPropertyCallExp);
				if (result == null) result = caseCGNamedElement(cgPropertyCallExp);
				if (result == null) result = caseCGElement(cgPropertyCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 81: {
				CGReal cgReal = (CGReal)theEObject;
				T result = caseCGReal(cgReal);
				if (result == null) result = caseCGNumber(cgReal);
				if (result == null) result = caseCGConstant(cgReal);
				if (result == null) result = caseCGValuedElement(cgReal);
				if (result == null) result = caseCGTypedElement(cgReal);
				if (result == null) result = caseCGNamedElement(cgReal);
				if (result == null) result = caseCGElement(cgReal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 82: {
				CGSequence cgSequence = (CGSequence)theEObject;
				T result = caseCGSequence(cgSequence);
				if (result == null) result = caseCGValuedElement(cgSequence);
				if (result == null) result = caseCGTypedElement(cgSequence);
				if (result == null) result = caseCGNamedElement(cgSequence);
				if (result == null) result = caseCGElement(cgSequence);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 83: {
				CGSettableVariable cgSettableVariable = (CGSettableVariable)theEObject;
				T result = caseCGSettableVariable(cgSettableVariable);
				if (result == null) result = caseCGVariable(cgSettableVariable);
				if (result == null) result = caseCGValuedElement(cgSettableVariable);
				if (result == null) result = caseCGTypedElement(cgSettableVariable);
				if (result == null) result = caseCGNamedElement(cgSettableVariable);
				if (result == null) result = caseCGElement(cgSettableVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 84: {
				CGShadowExp cgShadowExp = (CGShadowExp)theEObject;
				T result = caseCGShadowExp(cgShadowExp);
				if (result == null) result = caseCGValuedElement(cgShadowExp);
				if (result == null) result = caseCGTypedElement(cgShadowExp);
				if (result == null) result = caseCGNamedElement(cgShadowExp);
				if (result == null) result = caseCGElement(cgShadowExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 85: {
				CGShadowPart cgShadowPart = (CGShadowPart)theEObject;
				T result = caseCGShadowPart(cgShadowPart);
				if (result == null) result = caseCGValuedElement(cgShadowPart);
				if (result == null) result = caseCGTypedElement(cgShadowPart);
				if (result == null) result = caseCGNamedElement(cgShadowPart);
				if (result == null) result = caseCGElement(cgShadowPart);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 86: {
				CGSourcedCallExp cgSourcedCallExp = (CGSourcedCallExp)theEObject;
				T result = caseCGSourcedCallExp(cgSourcedCallExp);
				if (result == null) result = caseCGCallExp(cgSourcedCallExp);
				if (result == null) result = caseCGValuedElement(cgSourcedCallExp);
				if (result == null) result = caseCGTypedElement(cgSourcedCallExp);
				if (result == null) result = caseCGNamedElement(cgSourcedCallExp);
				if (result == null) result = caseCGElement(cgSourcedCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 87: {
				CGString cgString = (CGString)theEObject;
				T result = caseCGString(cgString);
				if (result == null) result = caseCGConstant(cgString);
				if (result == null) result = caseCGValuedElement(cgString);
				if (result == null) result = caseCGTypedElement(cgString);
				if (result == null) result = caseCGNamedElement(cgString);
				if (result == null) result = caseCGElement(cgString);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 88: {
				CGTemplateParameterExp cgTemplateParameterExp = (CGTemplateParameterExp)theEObject;
				T result = caseCGTemplateParameterExp(cgTemplateParameterExp);
				if (result == null) result = caseCGValuedElement(cgTemplateParameterExp);
				if (result == null) result = caseCGTypedElement(cgTemplateParameterExp);
				if (result == null) result = caseCGNamedElement(cgTemplateParameterExp);
				if (result == null) result = caseCGElement(cgTemplateParameterExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 89: {
				CGThrowExp cgThrowExp = (CGThrowExp)theEObject;
				T result = caseCGThrowExp(cgThrowExp);
				if (result == null) result = caseCGSourcedCallExp(cgThrowExp);
				if (result == null) result = caseCGCallExp(cgThrowExp);
				if (result == null) result = caseCGValuedElement(cgThrowExp);
				if (result == null) result = caseCGTypedElement(cgThrowExp);
				if (result == null) result = caseCGNamedElement(cgThrowExp);
				if (result == null) result = caseCGElement(cgThrowExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 90: {
				CGTupleExp cgTupleExp = (CGTupleExp)theEObject;
				T result = caseCGTupleExp(cgTupleExp);
				if (result == null) result = caseCGValuedElement(cgTupleExp);
				if (result == null) result = caseCGTypedElement(cgTupleExp);
				if (result == null) result = caseCGNamedElement(cgTupleExp);
				if (result == null) result = caseCGElement(cgTupleExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 91: {
				CGTuplePart cgTuplePart = (CGTuplePart)theEObject;
				T result = caseCGTuplePart(cgTuplePart);
				if (result == null) result = caseCGValuedElement(cgTuplePart);
				if (result == null) result = caseCGTypedElement(cgTuplePart);
				if (result == null) result = caseCGNamedElement(cgTuplePart);
				if (result == null) result = caseCGElement(cgTuplePart);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 92: {
				CGTuplePartCallExp cgTuplePartCallExp = (CGTuplePartCallExp)theEObject;
				T result = caseCGTuplePartCallExp(cgTuplePartCallExp);
				if (result == null) result = caseCGPropertyCallExp(cgTuplePartCallExp);
				if (result == null) result = caseCGNavigationCallExp(cgTuplePartCallExp);
				if (result == null) result = caseCGSourcedCallExp(cgTuplePartCallExp);
				if (result == null) result = caseCGCallExp(cgTuplePartCallExp);
				if (result == null) result = caseCGValuedElement(cgTuplePartCallExp);
				if (result == null) result = caseCGTypedElement(cgTuplePartCallExp);
				if (result == null) result = caseCGNamedElement(cgTuplePartCallExp);
				if (result == null) result = caseCGElement(cgTuplePartCallExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 93: {
				CGTypeId cgTypeId = (CGTypeId)theEObject;
				T result = caseCGTypeId(cgTypeId);
				if (result == null) result = caseCGElementId(cgTypeId);
				if (result == null) result = caseCGConstant(cgTypeId);
				if (result == null) result = caseCGValuedElement(cgTypeId);
				if (result == null) result = caseCGTypedElement(cgTypeId);
				if (result == null) result = caseCGNamedElement(cgTypeId);
				if (result == null) result = caseCGElement(cgTypeId);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 94: {
				CGTypeExp cgTypeExp = (CGTypeExp)theEObject;
				T result = caseCGTypeExp(cgTypeExp);
				if (result == null) result = caseCGValuedElement(cgTypeExp);
				if (result == null) result = caseCGTypedElement(cgTypeExp);
				if (result == null) result = caseCGNamedElement(cgTypeExp);
				if (result == null) result = caseCGElement(cgTypeExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 95: {
				CGTypedElement cgTypedElement = (CGTypedElement)theEObject;
				T result = caseCGTypedElement(cgTypedElement);
				if (result == null) result = caseCGNamedElement(cgTypedElement);
				if (result == null) result = caseCGElement(cgTypedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 96: {
				CGUnboxExp cgUnboxExp = (CGUnboxExp)theEObject;
				T result = caseCGUnboxExp(cgUnboxExp);
				if (result == null) result = caseCGSourcedCallExp(cgUnboxExp);
				if (result == null) result = caseCGCallExp(cgUnboxExp);
				if (result == null) result = caseCGValuedElement(cgUnboxExp);
				if (result == null) result = caseCGTypedElement(cgUnboxExp);
				if (result == null) result = caseCGNamedElement(cgUnboxExp);
				if (result == null) result = caseCGElement(cgUnboxExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 97: {
				CGUnlimited cgUnlimited = (CGUnlimited)theEObject;
				T result = caseCGUnlimited(cgUnlimited);
				if (result == null) result = caseCGConstant(cgUnlimited);
				if (result == null) result = caseCGValuedElement(cgUnlimited);
				if (result == null) result = caseCGTypedElement(cgUnlimited);
				if (result == null) result = caseCGNamedElement(cgUnlimited);
				if (result == null) result = caseCGElement(cgUnlimited);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 98: {
				CGValuedElement cgValuedElement = (CGValuedElement)theEObject;
				T result = caseCGValuedElement(cgValuedElement);
				if (result == null) result = caseCGTypedElement(cgValuedElement);
				if (result == null) result = caseCGNamedElement(cgValuedElement);
				if (result == null) result = caseCGElement(cgValuedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 99: {
				CGVariable cgVariable = (CGVariable)theEObject;
				T result = caseCGVariable(cgVariable);
				if (result == null) result = caseCGValuedElement(cgVariable);
				if (result == null) result = caseCGTypedElement(cgVariable);
				if (result == null) result = caseCGNamedElement(cgVariable);
				if (result == null) result = caseCGElement(cgVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 100: {
				CGVariableExp cgVariableExp = (CGVariableExp)theEObject;
				T result = caseCGVariableExp(cgVariableExp);
				if (result == null) result = caseCGValuedElement(cgVariableExp);
				if (result == null) result = caseCGTypedElement(cgVariableExp);
				if (result == null) result = caseCGNamedElement(cgVariableExp);
				if (result == null) result = caseCGElement(cgVariableExp);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 101: {
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
	 * Returns the result of interpreting the object as an instance of '<em>CG Bodied Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Bodied Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGBodiedProperty(CGBodiedProperty object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>CG Constrained Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Constrained Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGConstrainedProperty(CGConstrainedProperty object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>CG Sourced Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Sourced Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGSourcedCallExp(CGSourcedCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Foreign Operation Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Foreign Operation Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGForeignOperationCallExp(CGForeignOperationCallExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Foreign Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Foreign Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGForeignProperty(CGForeignProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Foreign Property Call Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Foreign Property Call Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGForeignPropertyCallExp(CGForeignPropertyCallExp object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>CG Ecore Container Assignment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Ecore Container Assignment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGEcoreContainerAssignment(CGEcoreContainerAssignment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Ecore Property Assignment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Ecore Property Assignment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGEcorePropertyAssignment(CGEcorePropertyAssignment object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>CG Index Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Index Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGIndexExp(CGIndexExp object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CG Inlined Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Inlined Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGInlinedOperation(CGInlinedOperation object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>CG Property Assignment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Property Assignment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGPropertyAssignment(CGPropertyAssignment object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>CG Sequence</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Sequence</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGSequence(CGSequence object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>CG Template Parameter Exp</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CG Template Parameter Exp</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCGTemplateParameterExp(CGTemplateParameterExp object) {
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
