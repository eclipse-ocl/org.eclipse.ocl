/*******************************************************************************
 * Copyright (c) 2013, 2019 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.cgmodel.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGAccumulator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGAssertNonNullExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBoolean;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBoxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCastExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCatchExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreClassShadowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreDataTypeShadowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOppositePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcorePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElementId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorCompositionProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorNavigationProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOppositeProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOppositePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorShadowPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGFinalVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGGuardExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIfExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInteger;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqual2Exp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqualExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsInvalidExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsKindOfExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsUndefinedExp;
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
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGReal;
import org.eclipse.ocl.examples.codegen.cgmodel.CGSettableVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGShadowPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGString;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTemplateParameterExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGText;
import org.eclipse.ocl.examples.codegen.cgmodel.CGThrowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTupleExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePartCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGUnboxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGUnlimited;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.EnumerationLiteralId;
import org.eclipse.ocl.pivot.ids.TuplePartId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.LibraryIteration;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.library.LibraryProperty;
import org.eclipse.ocl.pivot.utilities.StringUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CGModelFactoryImpl extends EFactoryImpl implements CGModelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CGModelFactory init() {
		try {
			CGModelFactory theCGModelFactory = (CGModelFactory)EPackage.Registry.INSTANCE.getEFactory(CGModelPackage.eNS_URI);
			if (theCGModelFactory != null) {
				return theCGModelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CGModelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CGModelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case 0: return createCGAccumulator();
			case 1: return createCGAssertNonNullExp();
			case 2: return createCGBoolean();
			case 3: return createCGBoxExp();
			case 4: return createCGBuiltInIterationCallExp();
			case 5: return createCGCachedOperation();
			case 6: return createCGCachedOperationCallExp();
			case 9: return createCGCastExp();
			case 10: return createCGCatchExp();
			case 11: return createCGClass();
			case 12: return createCGCollectionExp();
			case 13: return createCGCollectionPart();
			case 15: return createCGConstantExp();
			case 16: return createCGConstraint();
			case 17: return createCGEcoreClassShadowExp();
			case 18: return createCGEcoreDataTypeShadowExp();
			case 19: return createCGEcoreExp();
			case 20: return createCGEcoreOperation();
			case 21: return createCGEcoreOperationCallExp();
			case 22: return createCGEcoreOppositePropertyCallExp();
			case 23: return createCGEcorePropertyCallExp();
			case 25: return createCGElementId();
			case 26: return createCGExecutorCompositionProperty();
			case 27: return createCGExecutorNavigationProperty();
			case 28: return createCGExecutorOppositeProperty();
			case 29: return createCGExecutorOperation();
			case 30: return createCGExecutorOperationCallExp();
			case 31: return createCGExecutorOppositePropertyCallExp();
			case 33: return createCGExecutorPropertyCallExp();
			case 34: return createCGExecutorShadowPart();
			case 35: return createCGExecutorType();
			case 36: return createCGFinalVariable();
			case 37: return createCGGuardExp();
			case 38: return createCGIfExp();
			case 39: return createCGInteger();
			case 40: return createCGInvalid();
			case 41: return createCGIsEqualExp();
			case 42: return createCGIsEqual2Exp();
			case 43: return createCGIsInvalidExp();
			case 44: return createCGIsKindOfExp();
			case 45: return createCGIsUndefinedExp();
			case 47: return createCGIterator();
			case 48: return createCGLetExp();
			case 49: return createCGLibraryIterateCallExp();
			case 50: return createCGLibraryIterationCallExp();
			case 51: return createCGLibraryOperation();
			case 52: return createCGLibraryOperationCallExp();
			case 53: return createCGLibraryPropertyCallExp();
			case 54: return createCGLocalVariable();
			case 55: return createCGMapExp();
			case 56: return createCGMapPart();
			case 57: return createCGModel();
			case 59: return createCGNativeOperation();
			case 60: return createCGNativeOperationCallExp();
			case 61: return createCGNativeProperty();
			case 62: return createCGNativePropertyCallExp();
			case 64: return createCGNull();
			case 69: return createCGPackage();
			case 70: return createCGParameter();
			case 71: return createCGProperty();
			case 73: return createCGReal();
			case 74: return createCGSettableVariable();
			case 76: return createCGShadowPart();
			case 77: return createCGString();
			case 78: return createCGTemplateParameterExp();
			case 79: return createCGText();
			case 80: return createCGThrowExp();
			case 81: return createCGTupleExp();
			case 82: return createCGTuplePart();
			case 83: return createCGTuplePartCallExp();
			case 84: return createCGTypeId();
			case 85: return createCGTypeExp();
			case 87: return createCGUnboxExp();
			case 88: return createCGUnlimited();
			case 91: return createCGVariableExp();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case 93:
				return createElementFromString(eDataType, initialValue);
			case 94:
				return createElementIdFromString(eDataType, initialValue);
			case 95:
				return createEnumerationLiteralIdFromString(eDataType, initialValue);
			case 96:
				return createIterationFromString(eDataType, initialValue);
			case 97:
				return createFieldFromString(eDataType, initialValue);
			case 98:
				return createLibraryIterationFromString(eDataType, initialValue);
			case 99:
				return createLibraryOperationFromString(eDataType, initialValue);
			case 100:
				return createLibraryPropertyFromString(eDataType, initialValue);
			case 101:
				return createMethodFromString(eDataType, initialValue);
			case 102:
				return createNumberFromString(eDataType, initialValue);
			case 103:
				return createObjectFromString(eDataType, initialValue);
			case 104:
				return createOperationFromString(eDataType, initialValue);
			case 105:
				return createPropertyFromString(eDataType, initialValue);
			case 106:
				return createTuplePartIdFromString(eDataType, initialValue);
			case 107:
				return createTypeFromString(eDataType, initialValue);
			case 108:
				return createTypeIdFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case 93:
				return convertElementToString(eDataType, instanceValue);
			case 94:
				return convertElementIdToString(eDataType, instanceValue);
			case 95:
				return convertEnumerationLiteralIdToString(eDataType, instanceValue);
			case 96:
				return convertIterationToString(eDataType, instanceValue);
			case 97:
				return convertFieldToString(eDataType, instanceValue);
			case 98:
				return convertLibraryIterationToString(eDataType, instanceValue);
			case 99:
				return convertLibraryOperationToString(eDataType, instanceValue);
			case 100:
				return convertLibraryPropertyToString(eDataType, instanceValue);
			case 101:
				return convertMethodToString(eDataType, instanceValue);
			case 102:
				return convertNumberToString(eDataType, instanceValue);
			case 103:
				return convertObjectToString(eDataType, instanceValue);
			case 104:
				return convertOperationToString(eDataType, instanceValue);
			case 105:
				return convertPropertyToString(eDataType, instanceValue);
			case 106:
				return convertTuplePartIdToString(eDataType, instanceValue);
			case 107:
				return convertTypeToString(eDataType, instanceValue);
			case 108:
				return convertTypeIdToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGAccumulator createCGAccumulator() {
		CGAccumulatorImpl cgAccumulator = new CGAccumulatorImpl();
		return cgAccumulator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGAssertNonNullExp createCGAssertNonNullExp() {
		CGAssertNonNullExpImpl cgAssertNonNullExp = new CGAssertNonNullExpImpl();
		return cgAssertNonNullExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGBoolean createCGBoolean() {
		CGBooleanImpl cgBoolean = new CGBooleanImpl();
		return cgBoolean;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGBoxExp createCGBoxExp() {
		CGBoxExpImpl cgBoxExp = new CGBoxExpImpl();
		return cgBoxExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGBuiltInIterationCallExp createCGBuiltInIterationCallExp() {
		CGBuiltInIterationCallExpImpl cgBuiltInIterationCallExp = new CGBuiltInIterationCallExpImpl();
		return cgBuiltInIterationCallExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGCachedOperation createCGCachedOperation() {
		CGCachedOperationImpl cgCachedOperation = new CGCachedOperationImpl();
		return cgCachedOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGCachedOperationCallExp createCGCachedOperationCallExp() {
		CGCachedOperationCallExpImpl cgCachedOperationCallExp = new CGCachedOperationCallExpImpl();
		return cgCachedOperationCallExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGCastExp createCGCastExp() {
		CGCastExpImpl cgCastExp = new CGCastExpImpl();
		return cgCastExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGCatchExp createCGCatchExp() {
		CGCatchExpImpl cgCatchExp = new CGCatchExpImpl();
		return cgCatchExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGClass createCGClass() {
		CGClassImpl cgClass = new CGClassImpl();
		return cgClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGCollectionExp createCGCollectionExp() {
		CGCollectionExpImpl cgCollectionExp = new CGCollectionExpImpl();
		return cgCollectionExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGCollectionPart createCGCollectionPart() {
		CGCollectionPartImpl cgCollectionPart = new CGCollectionPartImpl();
		return cgCollectionPart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGConstantExp createCGConstantExp() {
		CGConstantExpImpl cgConstantExp = new CGConstantExpImpl();
		return cgConstantExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGConstraint createCGConstraint() {
		CGConstraintImpl cgConstraint = new CGConstraintImpl();
		return cgConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGShadowPart createCGShadowPart() {
		CGShadowPartImpl cgShadowPart = new CGShadowPartImpl();
		return cgShadowPart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGEcoreClassShadowExp createCGEcoreClassShadowExp() {
		CGEcoreClassShadowExpImpl cgEcoreClassShadowExp = new CGEcoreClassShadowExpImpl();
		return cgEcoreClassShadowExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGEcoreDataTypeShadowExp createCGEcoreDataTypeShadowExp() {
		CGEcoreDataTypeShadowExpImpl cgEcoreDataTypeShadowExp = new CGEcoreDataTypeShadowExpImpl();
		return cgEcoreDataTypeShadowExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGEcoreExp createCGEcoreExp() {
		CGEcoreExpImpl cgEcoreExp = new CGEcoreExpImpl();
		return cgEcoreExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGEcoreOperation createCGEcoreOperation() {
		CGEcoreOperationImpl cgEcoreOperation = new CGEcoreOperationImpl();
		return cgEcoreOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGEcoreOperationCallExp createCGEcoreOperationCallExp() {
		CGEcoreOperationCallExpImpl cgEcoreOperationCallExp = new CGEcoreOperationCallExpImpl();
		return cgEcoreOperationCallExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGEcoreOppositePropertyCallExp createCGEcoreOppositePropertyCallExp() {
		CGEcoreOppositePropertyCallExpImpl cgEcoreOppositePropertyCallExp = new CGEcoreOppositePropertyCallExpImpl();
		return cgEcoreOppositePropertyCallExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGEcorePropertyCallExp createCGEcorePropertyCallExp() {
		CGEcorePropertyCallExpImpl cgEcorePropertyCallExp = new CGEcorePropertyCallExpImpl();
		return cgEcorePropertyCallExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGElementId createCGElementId() {
		CGElementIdImpl cgElementId = new CGElementIdImpl();
		return cgElementId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGExecutorCompositionProperty createCGExecutorCompositionProperty() {
		CGExecutorCompositionPropertyImpl cgExecutorCompositionProperty = new CGExecutorCompositionPropertyImpl();
		return cgExecutorCompositionProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGExecutorNavigationProperty createCGExecutorNavigationProperty() {
		CGExecutorNavigationPropertyImpl cgExecutorNavigationProperty = new CGExecutorNavigationPropertyImpl();
		return cgExecutorNavigationProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGExecutorOppositeProperty createCGExecutorOppositeProperty() {
		CGExecutorOppositePropertyImpl cgExecutorOppositeProperty = new CGExecutorOppositePropertyImpl();
		return cgExecutorOppositeProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGExecutorOperation createCGExecutorOperation() {
		CGExecutorOperationImpl cgExecutorOperation = new CGExecutorOperationImpl();
		return cgExecutorOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGExecutorOperationCallExp createCGExecutorOperationCallExp() {
		CGExecutorOperationCallExpImpl cgExecutorOperationCallExp = new CGExecutorOperationCallExpImpl();
		return cgExecutorOperationCallExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGExecutorOppositePropertyCallExp createCGExecutorOppositePropertyCallExp() {
		CGExecutorOppositePropertyCallExpImpl cgExecutorOppositePropertyCallExp = new CGExecutorOppositePropertyCallExpImpl();
		return cgExecutorOppositePropertyCallExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGExecutorPropertyCallExp createCGExecutorPropertyCallExp() {
		CGExecutorPropertyCallExpImpl cgExecutorPropertyCallExp = new CGExecutorPropertyCallExpImpl();
		return cgExecutorPropertyCallExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGExecutorShadowPart createCGExecutorShadowPart() {
		CGExecutorShadowPartImpl cgExecutorShadowPart = new CGExecutorShadowPartImpl();
		return cgExecutorShadowPart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGExecutorType createCGExecutorType() {
		CGExecutorTypeImpl cgExecutorType = new CGExecutorTypeImpl();
		return cgExecutorType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGFinalVariable createCGFinalVariable() {
		CGFinalVariableImpl cgFinalVariable = new CGFinalVariableImpl();
		return cgFinalVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGGuardExp createCGGuardExp() {
		CGGuardExpImpl cgGuardExp = new CGGuardExpImpl();
		return cgGuardExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGIfExp createCGIfExp() {
		CGIfExpImpl cgIfExp = new CGIfExpImpl();
		return cgIfExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGInteger createCGInteger() {
		CGIntegerImpl cgInteger = new CGIntegerImpl();
		return cgInteger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGInvalid createCGInvalid() {
		CGInvalidImpl cgInvalid = new CGInvalidImpl();
		return cgInvalid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGIsEqualExp createCGIsEqualExp() {
		CGIsEqualExpImpl cgIsEqualExp = new CGIsEqualExpImpl();
		return cgIsEqualExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGIsEqual2Exp createCGIsEqual2Exp() {
		CGIsEqual2ExpImpl cgIsEqual2Exp = new CGIsEqual2ExpImpl();
		return cgIsEqual2Exp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGIsInvalidExp createCGIsInvalidExp() {
		CGIsInvalidExpImpl cgIsInvalidExp = new CGIsInvalidExpImpl();
		return cgIsInvalidExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGIsKindOfExp createCGIsKindOfExp() {
		CGIsKindOfExpImpl cgIsKindOfExp = new CGIsKindOfExpImpl();
		return cgIsKindOfExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGIsUndefinedExp createCGIsUndefinedExp() {
		CGIsUndefinedExpImpl cgIsUndefinedExp = new CGIsUndefinedExpImpl();
		return cgIsUndefinedExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGIterator createCGIterator() {
		CGIteratorImpl cgIterator = new CGIteratorImpl();
		return cgIterator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGLetExp createCGLetExp() {
		CGLetExpImpl cgLetExp = new CGLetExpImpl();
		return cgLetExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGLibraryIterateCallExp createCGLibraryIterateCallExp() {
		CGLibraryIterateCallExpImpl cgLibraryIterateCallExp = new CGLibraryIterateCallExpImpl();
		return cgLibraryIterateCallExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGLibraryIterationCallExp createCGLibraryIterationCallExp() {
		CGLibraryIterationCallExpImpl cgLibraryIterationCallExp = new CGLibraryIterationCallExpImpl();
		return cgLibraryIterationCallExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGLibraryOperation createCGLibraryOperation() {
		CGLibraryOperationImpl cgLibraryOperation = new CGLibraryOperationImpl();
		return cgLibraryOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGLibraryOperationCallExp createCGLibraryOperationCallExp() {
		CGLibraryOperationCallExpImpl cgLibraryOperationCallExp = new CGLibraryOperationCallExpImpl();
		return cgLibraryOperationCallExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGLibraryPropertyCallExp createCGLibraryPropertyCallExp() {
		CGLibraryPropertyCallExpImpl cgLibraryPropertyCallExp = new CGLibraryPropertyCallExpImpl();
		return cgLibraryPropertyCallExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGLocalVariable createCGLocalVariable() {
		CGLocalVariableImpl cgLocalVariable = new CGLocalVariableImpl();
		return cgLocalVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGMapExp createCGMapExp() {
		CGMapExpImpl cgMapExp = new CGMapExpImpl();
		return cgMapExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGMapPart createCGMapPart() {
		CGMapPartImpl cgMapPart = new CGMapPartImpl();
		return cgMapPart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGModel createCGModel() {
		CGModelImpl cgModel = new CGModelImpl();
		return cgModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGNativeOperation createCGNativeOperation() {
		CGNativeOperationImpl cgNativeOperation = new CGNativeOperationImpl();
		return cgNativeOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGNativeOperationCallExp createCGNativeOperationCallExp() {
		CGNativeOperationCallExpImpl cgNativeOperationCallExp = new CGNativeOperationCallExpImpl();
		return cgNativeOperationCallExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGNativeProperty createCGNativeProperty() {
		CGNativePropertyImpl cgNativeProperty = new CGNativePropertyImpl();
		return cgNativeProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGNativePropertyCallExp createCGNativePropertyCallExp() {
		CGNativePropertyCallExpImpl cgNativePropertyCallExp = new CGNativePropertyCallExpImpl();
		return cgNativePropertyCallExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGNull createCGNull() {
		CGNullImpl cgNull = new CGNullImpl();
		return cgNull;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGPackage createCGPackage() {
		CGPackageImpl cgPackage = new CGPackageImpl();
		return cgPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGParameter createCGParameter() {
		CGParameterImpl cgParameter = new CGParameterImpl();
		return cgParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGProperty createCGProperty() {
		CGPropertyImpl cgProperty = new CGPropertyImpl();
		return cgProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGReal createCGReal() {
		CGRealImpl cgReal = new CGRealImpl();
		return cgReal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGSettableVariable createCGSettableVariable() {
		CGSettableVariableImpl cgSettableVariable = new CGSettableVariableImpl();
		return cgSettableVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGString createCGString() {
		CGStringImpl cgString = new CGStringImpl();
		return cgString;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGTemplateParameterExp createCGTemplateParameterExp() {
		CGTemplateParameterExpImpl cgTemplateParameterExp = new CGTemplateParameterExpImpl();
		return cgTemplateParameterExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGText createCGText() {
		CGTextImpl cgText = new CGTextImpl();
		return cgText;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGThrowExp createCGThrowExp() {
		CGThrowExpImpl cgThrowExp = new CGThrowExpImpl();
		return cgThrowExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGTupleExp createCGTupleExp() {
		CGTupleExpImpl cgTupleExp = new CGTupleExpImpl();
		return cgTupleExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGTuplePart createCGTuplePart() {
		CGTuplePartImpl cgTuplePart = new CGTuplePartImpl();
		return cgTuplePart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGTuplePartCallExp createCGTuplePartCallExp() {
		CGTuplePartCallExpImpl cgTuplePartCallExp = new CGTuplePartCallExpImpl();
		return cgTuplePartCallExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGTypeId createCGTypeId() {
		CGTypeIdImpl cgTypeId = new CGTypeIdImpl();
		return cgTypeId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGTypeExp createCGTypeExp() {
		CGTypeExpImpl cgTypeExp = new CGTypeExpImpl();
		return cgTypeExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGUnboxExp createCGUnboxExp() {
		CGUnboxExpImpl cgUnboxExp = new CGUnboxExpImpl();
		return cgUnboxExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGUnlimited createCGUnlimited() {
		CGUnlimitedImpl cgUnlimited = new CGUnlimitedImpl();
		return cgUnlimited;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull CGVariableExp createCGVariableExp() {
		CGVariableExpImpl cgVariableExp = new CGVariableExpImpl();
		return cgVariableExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Element createElementFromString(EDataType eDataType, String initialValue) {
		return (Element)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertElementToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ElementId createElementIdFromString(EDataType eDataType, String initialValue) {
		return (ElementId)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertElementIdToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumerationLiteralId createEnumerationLiteralIdFromString(EDataType eDataType, String initialValue) {
		return (EnumerationLiteralId)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEnumerationLiteralIdToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Iteration createIterationFromString(EDataType eDataType, String initialValue) {
		return (Iteration)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertIterationToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Field createFieldFromString(EDataType eDataType, String initialValue) {
		return (Field)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertFieldToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LibraryIteration createLibraryIterationFromString(EDataType eDataType, String initialValue) {
		return (LibraryIteration)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLibraryIterationToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LibraryOperation createLibraryOperationFromString(EDataType eDataType, String initialValue) {
		return (LibraryOperation)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLibraryOperationToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LibraryProperty createLibraryPropertyFromString(EDataType eDataType, String initialValue) {
		return (LibraryProperty)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLibraryPropertyToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method createMethodFromString(EDataType eDataType, String initialValue) {
		return (Method)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMethodToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Number createNumberFromString(EDataType eDataType, String aValue) {
		try {
			assert aValue != null;
			return StringUtil.createNumberFromString(aValue);
		}
		catch (NumberFormatException e) {
			//			return throwInvalidValueException(e, EvaluatorMessages.InvalidInteger, aValue);
		}
		return (Number)super.createFromString(eDataType, aValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNumberToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createObjectFromString(EDataType eDataType, String initialValue) {
		return super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertObjectToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation createOperationFromString(EDataType eDataType, String initialValue) {
		return (Operation)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOperationToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property createPropertyFromString(EDataType eDataType, String initialValue) {
		return (Property)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPropertyToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TuplePartId createTuplePartIdFromString(EDataType eDataType, String initialValue) {
		return (TuplePartId)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTuplePartIdToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type createTypeFromString(EDataType eDataType, String initialValue) {
		return (Type)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeId createTypeIdFromString(EDataType eDataType, String initialValue) {
		return (TypeId)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeIdToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("null")
	public @NonNull CGModelPackage getCGModelPackage() {
		return (CGModelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CGModelPackage getPackage() {
		return CGModelPackage.eINSTANCE;
	}

} //CGModelFactoryImpl
