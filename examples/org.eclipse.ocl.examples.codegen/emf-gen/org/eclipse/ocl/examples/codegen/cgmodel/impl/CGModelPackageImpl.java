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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.ocl.examples.codegen.cgmodel.CGAccumulator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGAssertNonNullExp;
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
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOppositeProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOppositePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorProperty;
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
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
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
import org.eclipse.ocl.examples.codegen.cgmodel.CGString;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTemplateParameterExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGText;
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
import org.eclipse.ocl.pivot.utilities.Nameable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CGModelPackageImpl extends EPackageImpl implements CGModelPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgAccumulatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgAssertNonNullExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgBooleanEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgBoxExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgBuiltInIterationCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgCachedOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgCachedOperationCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgCallableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgCastExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgCatchExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgCollectionExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgCollectionPartEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgConstantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgConstantExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgShadowExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgShadowPartEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgEcoreClassShadowExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgEcoreDataTypeShadowExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgEcoreExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgEcoreOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgEcoreOperationCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgEcoreOppositePropertyCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgEcorePropertyCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgElementIdEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgExecutorCompositionPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgExecutorNavigationPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgExecutorOppositePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgExecutorOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgExecutorOperationCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgExecutorOppositePropertyCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgExecutorPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgExecutorPropertyCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgExecutorShadowPartEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgExecutorTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgFinalVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgGuardExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgIfExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgIntegerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgInvalidEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgIsEqualExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgIsEqual2ExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgIsInvalidExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgIsKindOfExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgIsUndefinedExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgIterationCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgIteratorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgLetExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgLibraryIterateCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgLibraryIterationCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgLibraryOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgLibraryOperationCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgLibraryPropertyCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgLocalVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgMapExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgMapPartEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgNamedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgNativeOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgNativeOperationCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgNativePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgNativePropertyCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgNavigationCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgNullEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgNumberEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgOperationCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgOppositePropertyCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgPackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgPropertyCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgRealEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgSettableVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgStringEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgTemplateParameterExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgTextEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgThrowExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgTupleExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgTuplePartEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgTuplePartCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgTypeIdEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgTypeExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgTypedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgUnboxExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgUnlimitedEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgValuedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cgVariableExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nameableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType elementEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType elementIdEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType enumerationLiteralIdEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType iterationEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType fieldEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType libraryIterationEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType libraryOperationEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType libraryPropertyEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType methodEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType numberEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType objectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType operationEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType propertyEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType tuplePartIdEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType typeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType typeIdEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CGModelPackageImpl() {
		super(eNS_URI, CGModelFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link CGModelPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CGModelPackage init() {
		if (isInited) return (CGModelPackage)EPackage.Registry.INSTANCE.getEPackage(CGModelPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredCGModelPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		CGModelPackageImpl theCGModelPackage = registeredCGModelPackage instanceof CGModelPackageImpl ? (CGModelPackageImpl)registeredCGModelPackage : new CGModelPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theCGModelPackage.createPackageContents();

		// Initialize created meta-data
		theCGModelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCGModelPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CGModelPackage.eNS_URI, theCGModelPackage);
		return theCGModelPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGAccumulator() {
		return cgAccumulatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGAssertNonNullExp() {
		return cgAssertNonNullExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGBoolean() {
		return cgBooleanEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGBoolean_BooleanValue() {
		return (EAttribute)cgBooleanEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGBoxExp() {
		return cgBoxExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGBuiltInIterationCallExp() {
		return cgBuiltInIterationCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGBuiltInIterationCallExp_Accumulator() {
		return (EReference)cgBuiltInIterationCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGCachedOperation() {
		return cgCachedOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGCachedOperation_FinalOperations() {
		return (EReference)cgCachedOperationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGCachedOperation_VirtualOperations() {
		return (EReference)cgCachedOperationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGCachedOperationCallExp() {
		return cgCachedOperationCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGCachedOperationCallExp_Method() {
		return (EAttribute)cgCachedOperationCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGCachedOperationCallExp_ThisIsSelf() {
		return (EAttribute)cgCachedOperationCallExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGCallable() {
		return cgCallableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGCallable_Parameters() {
		return (EReference)cgCallableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGCallable_Body() {
		return (EReference)cgCallableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGCallExp() {
		return cgCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGCallExp_Invalidating() {
		return (EAttribute)cgCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGCallExp_Validating() {
		return (EAttribute)cgCallExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGCallExp_Source() {
		return (EReference)cgCallExpEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGCastExp() {
		return cgCastExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGCastExp_ExecutorType() {
		return (EReference)cgCastExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGCatchExp() {
		return cgCatchExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGClass() {
		return cgClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGClass_ContainingPackage() {
		return (EReference)cgClassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGClass_Operations() {
		return (EReference)cgClassEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGClass_Properties() {
		return (EReference)cgClassEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGClass_Invariants() {
		return (EReference)cgClassEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGClass_SuperTypes() {
		return (EReference)cgClassEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGClass_Interface() {
		return (EAttribute)cgClassEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGClass_TemplateParameters() {
		return (EReference)cgClassEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGClass_Classes() {
		return (EReference)cgClassEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGClass_ContainingClass() {
		return (EReference)cgClassEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGCollectionExp() {
		return cgCollectionExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGCollectionExp_Parts() {
		return (EReference)cgCollectionExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGCollectionPart() {
		return cgCollectionPartEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGCollectionPart_First() {
		return (EReference)cgCollectionPartEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGCollectionPart_Last() {
		return (EReference)cgCollectionPartEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGCollectionPart_CollectionExp() {
		return (EReference)cgCollectionPartEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGConstant() {
		return cgConstantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGConstantExp() {
		return cgConstantExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGConstantExp_ReferredConstant() {
		return (EReference)cgConstantExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGConstraint() {
		return cgConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGShadowExp() {
		return cgShadowExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGShadowExp_Parts() {
		return (EReference)cgShadowExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGShadowExp_ExecutorType() {
		return (EReference)cgShadowExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGShadowPart() {
		return cgShadowPartEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGShadowPart_Init() {
		return (EReference)cgShadowPartEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGShadowPart_ShadowExp() {
		return (EReference)cgShadowPartEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGShadowPart_ExecutorPart() {
		return (EReference)cgShadowPartEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGEcoreClassShadowExp() {
		return cgEcoreClassShadowExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGEcoreClassShadowExp_EClass() {
		return (EReference)cgEcoreClassShadowExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGEcoreDataTypeShadowExp() {
		return cgEcoreDataTypeShadowExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGEcoreDataTypeShadowExp_EDataType() {
		return (EReference)cgEcoreDataTypeShadowExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGEcoreExp() {
		return cgEcoreExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGEcoreExp_EClassifier() {
		return (EReference)cgEcoreExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGEcoreOperation() {
		return cgEcoreOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGEcoreOperation_EOperation() {
		return (EReference)cgEcoreOperationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGEcoreOperationCallExp() {
		return cgEcoreOperationCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGEcoreOperationCallExp_EOperation() {
		return (EReference)cgEcoreOperationCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGEcoreOppositePropertyCallExp() {
		return cgEcoreOppositePropertyCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGEcoreOppositePropertyCallExp_EStructuralFeature() {
		return (EReference)cgEcoreOppositePropertyCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGEcorePropertyCallExp() {
		return cgEcorePropertyCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGEcorePropertyCallExp_EStructuralFeature() {
		return (EReference)cgEcorePropertyCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGElement() {
		return cgElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGElementId() {
		return cgElementIdEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGElementId_ElementId() {
		return (EAttribute)cgElementIdEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGExecutorCompositionProperty() {
		return cgExecutorCompositionPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGExecutorNavigationProperty() {
		return cgExecutorNavigationPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGExecutorOppositeProperty() {
		return cgExecutorOppositePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGExecutorOperation() {
		return cgExecutorOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGExecutorOperation_UnderlyingOperationId() {
		return (EReference)cgExecutorOperationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGExecutorOperationCallExp() {
		return cgExecutorOperationCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGExecutorOperationCallExp_ExecutorOperation() {
		return (EReference)cgExecutorOperationCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGExecutorOppositePropertyCallExp() {
		return cgExecutorOppositePropertyCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGExecutorOppositePropertyCallExp_ExecutorProperty() {
		return (EReference)cgExecutorOppositePropertyCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGExecutorProperty() {
		return cgExecutorPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGExecutorProperty_UnderlyingPropertyId() {
		return (EReference)cgExecutorPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGExecutorPropertyCallExp() {
		return cgExecutorPropertyCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGExecutorPropertyCallExp_ExecutorProperty() {
		return (EReference)cgExecutorPropertyCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGExecutorShadowPart() {
		return cgExecutorShadowPartEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGExecutorType() {
		return cgExecutorTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGExecutorType_UnderlyingTypeId() {
		return (EReference)cgExecutorTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGFinalVariable() {
		return cgFinalVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGGuardExp() {
		return cgGuardExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGGuardExp_Message() {
		return (EAttribute)cgGuardExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGGuardExp_Safe() {
		return (EAttribute)cgGuardExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGIfExp() {
		return cgIfExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGIfExp_Condition() {
		return (EReference)cgIfExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGIfExp_ThenExpression() {
		return (EReference)cgIfExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGIfExp_ElseExpression() {
		return (EReference)cgIfExpEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGInteger() {
		return cgIntegerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGInvalid() {
		return cgInvalidEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGInvalid_MessageTemplate() {
		return (EAttribute)cgInvalidEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGInvalid_Bindings() {
		return (EAttribute)cgInvalidEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGIsEqualExp() {
		return cgIsEqualExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGIsEqualExp_Argument() {
		return (EReference)cgIsEqualExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGIsEqualExp_NotEquals() {
		return (EAttribute)cgIsEqualExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGIsEqual2Exp() {
		return cgIsEqual2ExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGIsEqual2Exp_Argument() {
		return (EReference)cgIsEqual2ExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGIsInvalidExp() {
		return cgIsInvalidExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGIsKindOfExp() {
		return cgIsKindOfExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGIsKindOfExp_ExecutorType() {
		return (EReference)cgIsKindOfExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGIsUndefinedExp() {
		return cgIsUndefinedExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGIterationCallExp() {
		return cgIterationCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGIterationCallExp_ReferredIteration() {
		return (EAttribute)cgIterationCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGIterationCallExp_Iterators() {
		return (EReference)cgIterationCallExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGIterationCallExp_Body() {
		return (EReference)cgIterationCallExpEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGIterationCallExp_CoIterators() {
		return (EReference)cgIterationCallExpEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGIterator() {
		return cgIteratorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGLetExp() {
		return cgLetExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGLetExp_Init() {
		return (EReference)cgLetExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGLetExp_In() {
		return (EReference)cgLetExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGLibraryIterateCallExp() {
		return cgLibraryIterateCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGLibraryIterateCallExp_Result() {
		return (EReference)cgLibraryIterateCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGLibraryIterationCallExp() {
		return cgLibraryIterationCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGLibraryIterationCallExp_LibraryIteration() {
		return (EAttribute)cgLibraryIterationCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGLibraryOperation() {
		return cgLibraryOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGLibraryOperationCallExp() {
		return cgLibraryOperationCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGLibraryOperationCallExp_LibraryOperation() {
		return (EAttribute)cgLibraryOperationCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGLibraryPropertyCallExp() {
		return cgLibraryPropertyCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGLibraryPropertyCallExp_LibraryProperty() {
		return (EAttribute)cgLibraryPropertyCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGLocalVariable() {
		return cgLocalVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGMapExp() {
		return cgMapExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGMapExp_Parts() {
		return (EReference)cgMapExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGMapPart() {
		return cgMapPartEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGMapPart_Key() {
		return (EReference)cgMapPartEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGMapPart_Value() {
		return (EReference)cgMapPartEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGMapPart_MapExp() {
		return (EReference)cgMapPartEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGModel() {
		return cgModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGModel_Globals() {
		return (EReference)cgModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGModel_Packages() {
		return (EReference)cgModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGNamedElement() {
		return cgNamedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGNamedElement_Ast() {
		return (EAttribute)cgNamedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGNamedElement_Name() {
		return (EAttribute)cgNamedElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGNativeOperation() {
		return cgNativeOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGNativeOperationCallExp() {
		return cgNativeOperationCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGNativeOperationCallExp_Method() {
		return (EAttribute)cgNativeOperationCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGNativeOperationCallExp_ThisIsSelf() {
		return (EAttribute)cgNativeOperationCallExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGNativeProperty() {
		return cgNativePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGNativePropertyCallExp() {
		return cgNativePropertyCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGNativePropertyCallExp_Field() {
		return (EAttribute)cgNativePropertyCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGNavigationCallExp() {
		return cgNavigationCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGNavigationCallExp_ReferredProperty() {
		return (EAttribute)cgNavigationCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGNull() {
		return cgNullEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGNumber() {
		return cgNumberEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGNumber_NumericValue() {
		return (EAttribute)cgNumberEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGOperation() {
		return cgOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGOperation_Preconditions() {
		return (EReference)cgOperationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGOperation_Postconditions() {
		return (EReference)cgOperationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGOperation_ContainingClass() {
		return (EReference)cgOperationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGOperationCallExp() {
		return cgOperationCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGOperationCallExp_Arguments() {
		return (EReference)cgOperationCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGOperationCallExp_ReferredOperation() {
		return (EAttribute)cgOperationCallExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGOppositePropertyCallExp() {
		return cgOppositePropertyCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGPackage() {
		return cgPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGPackage_Classes() {
		return (EReference)cgPackageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGPackage_Packages() {
		return (EReference)cgPackageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGPackage_ContainingPackage() {
		return (EReference)cgPackageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGParameter() {
		return cgParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGParameter_Callable() {
		return (EReference)cgParameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGProperty() {
		return cgPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGProperty_ContainingClass() {
		return (EReference)cgPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGProperty_Body() {
		return (EReference)cgPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGPropertyCallExp() {
		return cgPropertyCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGReal() {
		return cgRealEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGSettableVariable() {
		return cgSettableVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGString() {
		return cgStringEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGString_StringValue() {
		return (EAttribute)cgStringEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGTemplateParameterExp() {
		return cgTemplateParameterExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGTemplateParameterExp_TemplateableElement() {
		return (EReference)cgTemplateParameterExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGTemplateParameterExp_Index() {
		return (EAttribute)cgTemplateParameterExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGText() {
		return cgTextEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGText_TextValue() {
		return (EAttribute)cgTextEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGThrowExp() {
		return cgThrowExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGTupleExp() {
		return cgTupleExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGTupleExp_Parts() {
		return (EReference)cgTupleExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGTuplePart() {
		return cgTuplePartEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGTuplePart_Init() {
		return (EReference)cgTuplePartEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGTuplePart_TupleExp() {
		return (EReference)cgTuplePartEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGTuplePartCallExp() {
		return cgTuplePartCallExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGTuplePartCallExp_AstTuplePartId() {
		return (EAttribute)cgTuplePartCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGTypeId() {
		return cgTypeIdEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGTypeExp() {
		return cgTypeExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGTypeExp_ExecutorType() {
		return (EReference)cgTypeExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGTypedElement() {
		return cgTypedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGTypedElement_TypeId() {
		return (EReference)cgTypedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCGTypedElement_Required() {
		return (EAttribute)cgTypedElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGUnboxExp() {
		return cgUnboxExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGUnlimited() {
		return cgUnlimitedEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGValuedElement() {
		return cgValuedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGValuedElement_DependsOn() {
		return (EReference)cgValuedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGValuedElement_Owns() {
		return (EReference)cgValuedElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGVariable() {
		return cgVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGVariable_Init() {
		return (EReference)cgVariableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCGVariableExp() {
		return cgVariableExpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCGVariableExp_ReferredVariable() {
		return (EReference)cgVariableExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNameable() {
		return nameableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getElement() {
		return elementEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getElementId() {
		return elementIdEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getEnumerationLiteralId() {
		return enumerationLiteralIdEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getIteration() {
		return iterationEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getField() {
		return fieldEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getLibraryIteration() {
		return libraryIterationEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getLibraryOperation() {
		return libraryOperationEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getLibraryProperty() {
		return libraryPropertyEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getMethod() {
		return methodEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getNumber() {
		return numberEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getObject() {
		return objectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getOperation() {
		return operationEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getProperty() {
		return propertyEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getTuplePartId() {
		return tuplePartIdEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getType() {
		return typeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getTypeId() {
		return typeIdEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CGModelFactory getCGModelFactory() {
		return (CGModelFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		cgAccumulatorEClass = createEClass(0);

		cgAssertNonNullExpEClass = createEClass(1);

		cgBooleanEClass = createEClass(2);
		createEAttribute(cgBooleanEClass, 6);

		cgBoxExpEClass = createEClass(3);

		cgBuiltInIterationCallExpEClass = createEClass(4);
		createEReference(cgBuiltInIterationCallExpEClass, 13);

		cgCachedOperationEClass = createEClass(5);
		createEReference(cgCachedOperationEClass, 11);
		createEReference(cgCachedOperationEClass, 12);

		cgCachedOperationCallExpEClass = createEClass(6);
		createEAttribute(cgCachedOperationCallExpEClass, 11);
		createEAttribute(cgCachedOperationCallExpEClass, 12);

		cgCallableEClass = createEClass(7);
		createEReference(cgCallableEClass, 6);
		createEReference(cgCallableEClass, 7);

		cgCallExpEClass = createEClass(8);
		createEAttribute(cgCallExpEClass, 6);
		createEAttribute(cgCallExpEClass, 7);
		createEReference(cgCallExpEClass, 8);

		cgCastExpEClass = createEClass(9);
		createEReference(cgCastExpEClass, 9);

		cgCatchExpEClass = createEClass(10);

		cgClassEClass = createEClass(11);
		createEReference(cgClassEClass, 2);
		createEReference(cgClassEClass, 3);
		createEReference(cgClassEClass, 4);
		createEReference(cgClassEClass, 5);
		createEReference(cgClassEClass, 6);
		createEAttribute(cgClassEClass, 7);
		createEReference(cgClassEClass, 8);
		createEReference(cgClassEClass, 9);
		createEReference(cgClassEClass, 10);

		cgCollectionExpEClass = createEClass(12);
		createEReference(cgCollectionExpEClass, 6);

		cgCollectionPartEClass = createEClass(13);
		createEReference(cgCollectionPartEClass, 6);
		createEReference(cgCollectionPartEClass, 7);
		createEReference(cgCollectionPartEClass, 8);

		cgConstantEClass = createEClass(14);

		cgConstantExpEClass = createEClass(15);
		createEReference(cgConstantExpEClass, 6);

		cgConstraintEClass = createEClass(16);

		cgEcoreClassShadowExpEClass = createEClass(17);
		createEReference(cgEcoreClassShadowExpEClass, 8);

		cgEcoreDataTypeShadowExpEClass = createEClass(18);
		createEReference(cgEcoreDataTypeShadowExpEClass, 8);

		cgEcoreExpEClass = createEClass(19);
		createEReference(cgEcoreExpEClass, 9);

		cgEcoreOperationEClass = createEClass(20);
		createEReference(cgEcoreOperationEClass, 11);

		cgEcoreOperationCallExpEClass = createEClass(21);
		createEReference(cgEcoreOperationCallExpEClass, 11);

		cgEcoreOppositePropertyCallExpEClass = createEClass(22);
		createEReference(cgEcoreOppositePropertyCallExpEClass, 10);

		cgEcorePropertyCallExpEClass = createEClass(23);
		createEReference(cgEcorePropertyCallExpEClass, 10);

		cgElementEClass = createEClass(24);

		cgElementIdEClass = createEClass(25);
		createEAttribute(cgElementIdEClass, 6);

		cgExecutorCompositionPropertyEClass = createEClass(26);

		cgExecutorNavigationPropertyEClass = createEClass(27);

		cgExecutorOppositePropertyEClass = createEClass(28);

		cgExecutorOperationEClass = createEClass(29);
		createEReference(cgExecutorOperationEClass, 6);

		cgExecutorOperationCallExpEClass = createEClass(30);
		createEReference(cgExecutorOperationCallExpEClass, 11);

		cgExecutorOppositePropertyCallExpEClass = createEClass(31);
		createEReference(cgExecutorOppositePropertyCallExpEClass, 10);

		cgExecutorPropertyEClass = createEClass(32);
		createEReference(cgExecutorPropertyEClass, 6);

		cgExecutorPropertyCallExpEClass = createEClass(33);
		createEReference(cgExecutorPropertyCallExpEClass, 10);

		cgExecutorShadowPartEClass = createEClass(34);

		cgExecutorTypeEClass = createEClass(35);
		createEReference(cgExecutorTypeEClass, 6);

		cgFinalVariableEClass = createEClass(36);

		cgGuardExpEClass = createEClass(37);
		createEAttribute(cgGuardExpEClass, 9);
		createEAttribute(cgGuardExpEClass, 10);

		cgIfExpEClass = createEClass(38);
		createEReference(cgIfExpEClass, 6);
		createEReference(cgIfExpEClass, 7);
		createEReference(cgIfExpEClass, 8);

		cgIntegerEClass = createEClass(39);

		cgInvalidEClass = createEClass(40);
		createEAttribute(cgInvalidEClass, 6);
		createEAttribute(cgInvalidEClass, 7);

		cgIsEqualExpEClass = createEClass(41);
		createEReference(cgIsEqualExpEClass, 9);
		createEAttribute(cgIsEqualExpEClass, 10);

		cgIsEqual2ExpEClass = createEClass(42);
		createEReference(cgIsEqual2ExpEClass, 9);

		cgIsInvalidExpEClass = createEClass(43);

		cgIsKindOfExpEClass = createEClass(44);
		createEReference(cgIsKindOfExpEClass, 9);

		cgIsUndefinedExpEClass = createEClass(45);

		cgIterationCallExpEClass = createEClass(46);
		createEAttribute(cgIterationCallExpEClass, 9);
		createEReference(cgIterationCallExpEClass, 10);
		createEReference(cgIterationCallExpEClass, 11);
		createEReference(cgIterationCallExpEClass, 12);

		cgIteratorEClass = createEClass(47);

		cgLetExpEClass = createEClass(48);
		createEReference(cgLetExpEClass, 6);
		createEReference(cgLetExpEClass, 7);

		cgLibraryIterateCallExpEClass = createEClass(49);
		createEReference(cgLibraryIterateCallExpEClass, 14);

		cgLibraryIterationCallExpEClass = createEClass(50);
		createEAttribute(cgLibraryIterationCallExpEClass, 13);

		cgLibraryOperationEClass = createEClass(51);

		cgLibraryOperationCallExpEClass = createEClass(52);
		createEAttribute(cgLibraryOperationCallExpEClass, 11);

		cgLibraryPropertyCallExpEClass = createEClass(53);
		createEAttribute(cgLibraryPropertyCallExpEClass, 10);

		cgLocalVariableEClass = createEClass(54);

		cgMapExpEClass = createEClass(55);
		createEReference(cgMapExpEClass, 6);

		cgMapPartEClass = createEClass(56);
		createEReference(cgMapPartEClass, 6);
		createEReference(cgMapPartEClass, 7);
		createEReference(cgMapPartEClass, 8);

		cgModelEClass = createEClass(57);
		createEReference(cgModelEClass, 2);
		createEReference(cgModelEClass, 3);

		cgNamedElementEClass = createEClass(58);
		createEAttribute(cgNamedElementEClass, 0);
		createEAttribute(cgNamedElementEClass, 1);

		cgNativeOperationEClass = createEClass(59);

		cgNativeOperationCallExpEClass = createEClass(60);
		createEAttribute(cgNativeOperationCallExpEClass, 11);
		createEAttribute(cgNativeOperationCallExpEClass, 12);

		cgNativePropertyEClass = createEClass(61);

		cgNativePropertyCallExpEClass = createEClass(62);
		createEAttribute(cgNativePropertyCallExpEClass, 10);

		cgNavigationCallExpEClass = createEClass(63);
		createEAttribute(cgNavigationCallExpEClass, 9);

		cgNullEClass = createEClass(64);

		cgNumberEClass = createEClass(65);
		createEAttribute(cgNumberEClass, 6);

		cgOperationEClass = createEClass(66);
		createEReference(cgOperationEClass, 8);
		createEReference(cgOperationEClass, 9);
		createEReference(cgOperationEClass, 10);

		cgOperationCallExpEClass = createEClass(67);
		createEReference(cgOperationCallExpEClass, 9);
		createEAttribute(cgOperationCallExpEClass, 10);

		cgOppositePropertyCallExpEClass = createEClass(68);

		cgPackageEClass = createEClass(69);
		createEReference(cgPackageEClass, 2);
		createEReference(cgPackageEClass, 3);
		createEReference(cgPackageEClass, 4);

		cgParameterEClass = createEClass(70);
		createEReference(cgParameterEClass, 7);

		cgPropertyEClass = createEClass(71);
		createEReference(cgPropertyEClass, 6);
		createEReference(cgPropertyEClass, 7);

		cgPropertyCallExpEClass = createEClass(72);

		cgRealEClass = createEClass(73);

		cgSettableVariableEClass = createEClass(74);

		cgShadowExpEClass = createEClass(75);
		createEReference(cgShadowExpEClass, 6);
		createEReference(cgShadowExpEClass, 7);

		cgShadowPartEClass = createEClass(76);
		createEReference(cgShadowPartEClass, 6);
		createEReference(cgShadowPartEClass, 7);
		createEReference(cgShadowPartEClass, 8);

		cgStringEClass = createEClass(77);
		createEAttribute(cgStringEClass, 6);

		cgTemplateParameterExpEClass = createEClass(78);
		createEReference(cgTemplateParameterExpEClass, 6);
		createEAttribute(cgTemplateParameterExpEClass, 7);

		cgTextEClass = createEClass(79);
		createEAttribute(cgTextEClass, 6);

		cgThrowExpEClass = createEClass(80);

		cgTupleExpEClass = createEClass(81);
		createEReference(cgTupleExpEClass, 6);

		cgTuplePartEClass = createEClass(82);
		createEReference(cgTuplePartEClass, 6);
		createEReference(cgTuplePartEClass, 7);

		cgTuplePartCallExpEClass = createEClass(83);
		createEAttribute(cgTuplePartCallExpEClass, 10);

		cgTypeIdEClass = createEClass(84);

		cgTypeExpEClass = createEClass(85);
		createEReference(cgTypeExpEClass, 6);

		cgTypedElementEClass = createEClass(86);
		createEReference(cgTypedElementEClass, 2);
		createEAttribute(cgTypedElementEClass, 3);

		cgUnboxExpEClass = createEClass(87);

		cgUnlimitedEClass = createEClass(88);

		cgValuedElementEClass = createEClass(89);
		createEReference(cgValuedElementEClass, 4);
		createEReference(cgValuedElementEClass, 5);

		cgVariableEClass = createEClass(90);
		createEReference(cgVariableEClass, 6);

		cgVariableExpEClass = createEClass(91);
		createEReference(cgVariableExpEClass, 6);

		nameableEClass = createEClass(92);

		// Create data types
		elementEDataType = createEDataType(93);
		elementIdEDataType = createEDataType(94);
		enumerationLiteralIdEDataType = createEDataType(95);
		iterationEDataType = createEDataType(96);
		fieldEDataType = createEDataType(97);
		libraryIterationEDataType = createEDataType(98);
		libraryOperationEDataType = createEDataType(99);
		libraryPropertyEDataType = createEDataType(100);
		methodEDataType = createEDataType(101);
		numberEDataType = createEDataType(102);
		objectEDataType = createEDataType(103);
		operationEDataType = createEDataType(104);
		propertyEDataType = createEDataType(105);
		tuplePartIdEDataType = createEDataType(106);
		typeEDataType = createEDataType(107);
		typeIdEDataType = createEDataType(108);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		cgAccumulatorEClass.getESuperTypes().add(this.getCGIterator());
		cgAssertNonNullExpEClass.getESuperTypes().add(this.getCGCallExp());
		cgBooleanEClass.getESuperTypes().add(this.getCGConstant());
		cgBoxExpEClass.getESuperTypes().add(this.getCGCallExp());
		cgBuiltInIterationCallExpEClass.getESuperTypes().add(this.getCGIterationCallExp());
		cgCachedOperationEClass.getESuperTypes().add(this.getCGOperation());
		cgCachedOperationCallExpEClass.getESuperTypes().add(this.getCGOperationCallExp());
		cgCallableEClass.getESuperTypes().add(this.getCGValuedElement());
		cgCallExpEClass.getESuperTypes().add(this.getCGValuedElement());
		cgCastExpEClass.getESuperTypes().add(this.getCGCallExp());
		cgCatchExpEClass.getESuperTypes().add(this.getCGCallExp());
		cgClassEClass.getESuperTypes().add(this.getCGNamedElement());
		cgCollectionExpEClass.getESuperTypes().add(this.getCGValuedElement());
		cgCollectionPartEClass.getESuperTypes().add(this.getCGValuedElement());
		cgConstantEClass.getESuperTypes().add(this.getCGValuedElement());
		cgConstantExpEClass.getESuperTypes().add(this.getCGValuedElement());
		cgConstraintEClass.getESuperTypes().add(this.getCGCallable());
		cgEcoreClassShadowExpEClass.getESuperTypes().add(this.getCGShadowExp());
		cgEcoreDataTypeShadowExpEClass.getESuperTypes().add(this.getCGShadowExp());
		cgEcoreExpEClass.getESuperTypes().add(this.getCGCallExp());
		cgEcoreOperationEClass.getESuperTypes().add(this.getCGOperation());
		cgEcoreOperationCallExpEClass.getESuperTypes().add(this.getCGOperationCallExp());
		cgEcoreOppositePropertyCallExpEClass.getESuperTypes().add(this.getCGOppositePropertyCallExp());
		cgEcorePropertyCallExpEClass.getESuperTypes().add(this.getCGPropertyCallExp());
		cgElementIdEClass.getESuperTypes().add(this.getCGConstant());
		cgExecutorCompositionPropertyEClass.getESuperTypes().add(this.getCGExecutorProperty());
		cgExecutorNavigationPropertyEClass.getESuperTypes().add(this.getCGExecutorProperty());
		cgExecutorOppositePropertyEClass.getESuperTypes().add(this.getCGExecutorProperty());
		cgExecutorOperationEClass.getESuperTypes().add(this.getCGValuedElement());
		cgExecutorOperationCallExpEClass.getESuperTypes().add(this.getCGOperationCallExp());
		cgExecutorOppositePropertyCallExpEClass.getESuperTypes().add(this.getCGOppositePropertyCallExp());
		cgExecutorPropertyEClass.getESuperTypes().add(this.getCGValuedElement());
		cgExecutorPropertyCallExpEClass.getESuperTypes().add(this.getCGPropertyCallExp());
		cgExecutorShadowPartEClass.getESuperTypes().add(this.getCGExecutorProperty());
		cgExecutorTypeEClass.getESuperTypes().add(this.getCGValuedElement());
		cgFinalVariableEClass.getESuperTypes().add(this.getCGVariable());
		cgGuardExpEClass.getESuperTypes().add(this.getCGCallExp());
		cgIfExpEClass.getESuperTypes().add(this.getCGValuedElement());
		cgIntegerEClass.getESuperTypes().add(this.getCGNumber());
		cgInvalidEClass.getESuperTypes().add(this.getCGConstant());
		cgIsEqualExpEClass.getESuperTypes().add(this.getCGCallExp());
		cgIsEqual2ExpEClass.getESuperTypes().add(this.getCGCallExp());
		cgIsInvalidExpEClass.getESuperTypes().add(this.getCGCallExp());
		cgIsKindOfExpEClass.getESuperTypes().add(this.getCGCallExp());
		cgIsUndefinedExpEClass.getESuperTypes().add(this.getCGCallExp());
		cgIterationCallExpEClass.getESuperTypes().add(this.getCGCallExp());
		cgIteratorEClass.getESuperTypes().add(this.getCGParameter());
		cgLetExpEClass.getESuperTypes().add(this.getCGValuedElement());
		cgLibraryIterateCallExpEClass.getESuperTypes().add(this.getCGLibraryIterationCallExp());
		cgLibraryIterationCallExpEClass.getESuperTypes().add(this.getCGIterationCallExp());
		cgLibraryOperationEClass.getESuperTypes().add(this.getCGOperation());
		cgLibraryOperationCallExpEClass.getESuperTypes().add(this.getCGOperationCallExp());
		cgLibraryPropertyCallExpEClass.getESuperTypes().add(this.getCGPropertyCallExp());
		cgLocalVariableEClass.getESuperTypes().add(this.getCGVariable());
		cgMapExpEClass.getESuperTypes().add(this.getCGValuedElement());
		cgMapPartEClass.getESuperTypes().add(this.getCGValuedElement());
		cgModelEClass.getESuperTypes().add(this.getCGNamedElement());
		cgNamedElementEClass.getESuperTypes().add(this.getCGElement());
		cgNativeOperationEClass.getESuperTypes().add(this.getCGOperation());
		cgNativeOperationCallExpEClass.getESuperTypes().add(this.getCGOperationCallExp());
		cgNativePropertyEClass.getESuperTypes().add(this.getCGProperty());
		cgNativePropertyCallExpEClass.getESuperTypes().add(this.getCGPropertyCallExp());
		cgNavigationCallExpEClass.getESuperTypes().add(this.getCGCallExp());
		cgNullEClass.getESuperTypes().add(this.getCGConstant());
		cgNumberEClass.getESuperTypes().add(this.getCGConstant());
		cgOperationEClass.getESuperTypes().add(this.getCGCallable());
		cgOperationCallExpEClass.getESuperTypes().add(this.getCGCallExp());
		cgOppositePropertyCallExpEClass.getESuperTypes().add(this.getCGNavigationCallExp());
		cgPackageEClass.getESuperTypes().add(this.getCGNamedElement());
		cgParameterEClass.getESuperTypes().add(this.getCGVariable());
		cgPropertyEClass.getESuperTypes().add(this.getCGValuedElement());
		cgPropertyCallExpEClass.getESuperTypes().add(this.getCGNavigationCallExp());
		cgRealEClass.getESuperTypes().add(this.getCGNumber());
		cgSettableVariableEClass.getESuperTypes().add(this.getCGVariable());
		cgShadowExpEClass.getESuperTypes().add(this.getCGValuedElement());
		cgShadowPartEClass.getESuperTypes().add(this.getCGValuedElement());
		cgStringEClass.getESuperTypes().add(this.getCGConstant());
		cgTemplateParameterExpEClass.getESuperTypes().add(this.getCGValuedElement());
		cgTextEClass.getESuperTypes().add(this.getCGConstant());
		cgThrowExpEClass.getESuperTypes().add(this.getCGCallExp());
		cgTupleExpEClass.getESuperTypes().add(this.getCGValuedElement());
		cgTuplePartEClass.getESuperTypes().add(this.getCGValuedElement());
		cgTuplePartCallExpEClass.getESuperTypes().add(this.getCGPropertyCallExp());
		cgTypeIdEClass.getESuperTypes().add(this.getCGElementId());
		cgTypeExpEClass.getESuperTypes().add(this.getCGValuedElement());
		cgTypedElementEClass.getESuperTypes().add(this.getCGNamedElement());
		cgUnboxExpEClass.getESuperTypes().add(this.getCGCallExp());
		cgUnlimitedEClass.getESuperTypes().add(this.getCGConstant());
		cgValuedElementEClass.getESuperTypes().add(this.getCGTypedElement());
		cgVariableEClass.getESuperTypes().add(this.getCGValuedElement());
		cgVariableExpEClass.getESuperTypes().add(this.getCGValuedElement());

		// Initialize classes and features; add operations and parameters
		initEClass(cgAccumulatorEClass, CGAccumulator.class, "CGAccumulator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgAssertNonNullExpEClass, CGAssertNonNullExp.class, "CGAssertNonNullExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgBooleanEClass, CGBoolean.class, "CGBoolean", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCGBoolean_BooleanValue(), ecorePackage.getEBoolean(), "booleanValue", null, 1, 1, CGBoolean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgBoxExpEClass, CGBoxExp.class, "CGBoxExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgBuiltInIterationCallExpEClass, CGBuiltInIterationCallExp.class, "CGBuiltInIterationCallExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGBuiltInIterationCallExp_Accumulator(), this.getCGIterator(), null, "accumulator", null, 0, 1, CGBuiltInIterationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgCachedOperationEClass, CGCachedOperation.class, "CGCachedOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGCachedOperation_FinalOperations(), this.getCGCachedOperation(), this.getCGCachedOperation_VirtualOperations(), "finalOperations", null, 0, -1, CGCachedOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCGCachedOperation_VirtualOperations(), this.getCGCachedOperation(), this.getCGCachedOperation_FinalOperations(), "virtualOperations", null, 0, -1, CGCachedOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgCachedOperationCallExpEClass, CGCachedOperationCallExp.class, "CGCachedOperationCallExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCGCachedOperationCallExp_Method(), this.getMethod(), "method", null, 1, 1, CGCachedOperationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCGCachedOperationCallExp_ThisIsSelf(), ecorePackage.getEBoolean(), "thisIsSelf", null, 1, 1, CGCachedOperationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgCallableEClass, CGCallable.class, "CGCallable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGCallable_Parameters(), this.getCGParameter(), this.getCGParameter_Callable(), "parameters", null, 0, -1, CGCallable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCGCallable_Body(), this.getCGValuedElement(), null, "body", null, 0, 1, CGCallable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgCallExpEClass, CGCallExp.class, "CGCallExp", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCGCallExp_Invalidating(), ecorePackage.getEBoolean(), "invalidating", "false", 1, 1, CGCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCGCallExp_Validating(), ecorePackage.getEBoolean(), "validating", "false", 1, 1, CGCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCGCallExp_Source(), this.getCGValuedElement(), null, "source", null, 0, 1, CGCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgCastExpEClass, CGCastExp.class, "CGCastExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGCastExp_ExecutorType(), this.getCGExecutorType(), null, "executorType", null, 1, 1, CGCastExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgCatchExpEClass, CGCatchExp.class, "CGCatchExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgClassEClass, CGClass.class, "CGClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGClass_ContainingPackage(), this.getCGPackage(), this.getCGPackage_Classes(), "containingPackage", null, 0, 1, CGClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCGClass_Operations(), this.getCGOperation(), this.getCGOperation_ContainingClass(), "operations", null, 0, -1, CGClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCGClass_Properties(), this.getCGProperty(), this.getCGProperty_ContainingClass(), "properties", null, 0, -1, CGClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCGClass_Invariants(), this.getCGConstraint(), null, "invariants", null, 0, -1, CGClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCGClass_SuperTypes(), this.getCGClass(), null, "superTypes", null, 0, -1, CGClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCGClass_Interface(), ecorePackage.getEBoolean(), "interface", "false", 1, 1, CGClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCGClass_TemplateParameters(), this.getCGClass(), null, "templateParameters", null, 0, -1, CGClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCGClass_Classes(), this.getCGClass(), this.getCGClass_ContainingClass(), "classes", null, 0, -1, CGClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCGClass_ContainingClass(), this.getCGClass(), this.getCGClass_Classes(), "containingClass", null, 0, 1, CGClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgCollectionExpEClass, CGCollectionExp.class, "CGCollectionExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGCollectionExp_Parts(), this.getCGCollectionPart(), this.getCGCollectionPart_CollectionExp(), "parts", null, 0, -1, CGCollectionExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgCollectionPartEClass, CGCollectionPart.class, "CGCollectionPart", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGCollectionPart_First(), this.getCGValuedElement(), null, "first", null, 1, 1, CGCollectionPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCGCollectionPart_Last(), this.getCGValuedElement(), null, "last", null, 1, 1, CGCollectionPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCGCollectionPart_CollectionExp(), this.getCGCollectionExp(), this.getCGCollectionExp_Parts(), "collectionExp", null, 1, 1, CGCollectionPart.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgConstantEClass, CGConstant.class, "CGConstant", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgConstantExpEClass, CGConstantExp.class, "CGConstantExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGConstantExp_ReferredConstant(), this.getCGValuedElement(), null, "referredConstant", null, 1, 1, CGConstantExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgConstraintEClass, CGConstraint.class, "CGConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgEcoreClassShadowExpEClass, CGEcoreClassShadowExp.class, "CGEcoreClassShadowExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGEcoreClassShadowExp_EClass(), ecorePackage.getEClass(), null, "eClass", null, 1, 1, CGEcoreClassShadowExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgEcoreDataTypeShadowExpEClass, CGEcoreDataTypeShadowExp.class, "CGEcoreDataTypeShadowExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGEcoreDataTypeShadowExp_EDataType(), ecorePackage.getEDataType(), null, "eDataType", null, 1, 1, CGEcoreDataTypeShadowExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgEcoreExpEClass, CGEcoreExp.class, "CGEcoreExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGEcoreExp_EClassifier(), ecorePackage.getEClassifier(), null, "eClassifier", null, 0, 1, CGEcoreExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgEcoreOperationEClass, CGEcoreOperation.class, "CGEcoreOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGEcoreOperation_EOperation(), ecorePackage.getEOperation(), null, "eOperation", null, 0, 1, CGEcoreOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgEcoreOperationCallExpEClass, CGEcoreOperationCallExp.class, "CGEcoreOperationCallExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGEcoreOperationCallExp_EOperation(), ecorePackage.getEOperation(), null, "eOperation", null, 1, 1, CGEcoreOperationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgEcoreOppositePropertyCallExpEClass, CGEcoreOppositePropertyCallExp.class, "CGEcoreOppositePropertyCallExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGEcoreOppositePropertyCallExp_EStructuralFeature(), ecorePackage.getEStructuralFeature(), null, "eStructuralFeature", null, 1, 1, CGEcoreOppositePropertyCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgEcorePropertyCallExpEClass, CGEcorePropertyCallExp.class, "CGEcorePropertyCallExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGEcorePropertyCallExp_EStructuralFeature(), ecorePackage.getEStructuralFeature(), null, "eStructuralFeature", null, 1, 1, CGEcorePropertyCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgElementEClass, CGElement.class, "CGElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgElementIdEClass, CGElementId.class, "CGElementId", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCGElementId_ElementId(), this.getElementId(), "elementId", null, 1, 1, CGElementId.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgExecutorCompositionPropertyEClass, CGExecutorCompositionProperty.class, "CGExecutorCompositionProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgExecutorNavigationPropertyEClass, CGExecutorNavigationProperty.class, "CGExecutorNavigationProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgExecutorOppositePropertyEClass, CGExecutorOppositeProperty.class, "CGExecutorOppositeProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgExecutorOperationEClass, CGExecutorOperation.class, "CGExecutorOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGExecutorOperation_UnderlyingOperationId(), this.getCGElementId(), null, "underlyingOperationId", null, 0, 1, CGExecutorOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgExecutorOperationCallExpEClass, CGExecutorOperationCallExp.class, "CGExecutorOperationCallExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGExecutorOperationCallExp_ExecutorOperation(), this.getCGExecutorOperation(), null, "executorOperation", null, 1, 1, CGExecutorOperationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgExecutorOppositePropertyCallExpEClass, CGExecutorOppositePropertyCallExp.class, "CGExecutorOppositePropertyCallExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGExecutorOppositePropertyCallExp_ExecutorProperty(), this.getCGExecutorProperty(), null, "executorProperty", null, 1, 1, CGExecutorOppositePropertyCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgExecutorPropertyEClass, CGExecutorProperty.class, "CGExecutorProperty", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGExecutorProperty_UnderlyingPropertyId(), this.getCGElementId(), null, "underlyingPropertyId", null, 0, 1, CGExecutorProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgExecutorPropertyCallExpEClass, CGExecutorPropertyCallExp.class, "CGExecutorPropertyCallExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGExecutorPropertyCallExp_ExecutorProperty(), this.getCGExecutorProperty(), null, "executorProperty", null, 1, 1, CGExecutorPropertyCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgExecutorShadowPartEClass, CGExecutorShadowPart.class, "CGExecutorShadowPart", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgExecutorTypeEClass, CGExecutorType.class, "CGExecutorType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGExecutorType_UnderlyingTypeId(), this.getCGTypeId(), null, "underlyingTypeId", null, 0, 1, CGExecutorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgFinalVariableEClass, CGFinalVariable.class, "CGFinalVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgGuardExpEClass, CGGuardExp.class, "CGGuardExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCGGuardExp_Message(), ecorePackage.getEString(), "message", null, 1, 1, CGGuardExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCGGuardExp_Safe(), ecorePackage.getEBoolean(), "safe", "false", 1, 1, CGGuardExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgIfExpEClass, CGIfExp.class, "CGIfExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGIfExp_Condition(), this.getCGValuedElement(), null, "condition", null, 1, 1, CGIfExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCGIfExp_ThenExpression(), this.getCGValuedElement(), null, "thenExpression", null, 1, 1, CGIfExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCGIfExp_ElseExpression(), this.getCGValuedElement(), null, "elseExpression", null, 1, 1, CGIfExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgIntegerEClass, CGInteger.class, "CGInteger", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgInvalidEClass, CGInvalid.class, "CGInvalid", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCGInvalid_MessageTemplate(), ecorePackage.getEString(), "messageTemplate", null, 0, 1, CGInvalid.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCGInvalid_Bindings(), this.getObject(), "bindings", null, 0, -1, CGInvalid.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgIsEqualExpEClass, CGIsEqualExp.class, "CGIsEqualExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGIsEqualExp_Argument(), this.getCGValuedElement(), null, "argument", null, 0, 1, CGIsEqualExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCGIsEqualExp_NotEquals(), ecorePackage.getEBoolean(), "notEquals", "false", 1, 1, CGIsEqualExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgIsEqual2ExpEClass, CGIsEqual2Exp.class, "CGIsEqual2Exp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGIsEqual2Exp_Argument(), this.getCGValuedElement(), null, "argument", null, 0, 1, CGIsEqual2Exp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgIsInvalidExpEClass, CGIsInvalidExp.class, "CGIsInvalidExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgIsKindOfExpEClass, CGIsKindOfExp.class, "CGIsKindOfExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGIsKindOfExp_ExecutorType(), this.getCGExecutorType(), null, "executorType", null, 1, 1, CGIsKindOfExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgIsUndefinedExpEClass, CGIsUndefinedExp.class, "CGIsUndefinedExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgIterationCallExpEClass, CGIterationCallExp.class, "CGIterationCallExp", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCGIterationCallExp_ReferredIteration(), this.getIteration(), "referredIteration", null, 1, 1, CGIterationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCGIterationCallExp_Iterators(), this.getCGIterator(), null, "iterators", null, 0, -1, CGIterationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCGIterationCallExp_Body(), this.getCGValuedElement(), null, "body", null, 1, 1, CGIterationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCGIterationCallExp_CoIterators(), this.getCGIterator(), null, "coIterators", null, 0, -1, CGIterationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgIteratorEClass, CGIterator.class, "CGIterator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgLetExpEClass, CGLetExp.class, "CGLetExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGLetExp_Init(), this.getCGVariable(), null, "init", null, 1, 1, CGLetExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCGLetExp_In(), this.getCGValuedElement(), null, "in", null, 1, 1, CGLetExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgLibraryIterateCallExpEClass, CGLibraryIterateCallExp.class, "CGLibraryIterateCallExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGLibraryIterateCallExp_Result(), this.getCGIterator(), null, "result", null, 0, 1, CGLibraryIterateCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgLibraryIterationCallExpEClass, CGLibraryIterationCallExp.class, "CGLibraryIterationCallExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCGLibraryIterationCallExp_LibraryIteration(), this.getLibraryIteration(), "libraryIteration", null, 1, 1, CGLibraryIterationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgLibraryOperationEClass, CGLibraryOperation.class, "CGLibraryOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgLibraryOperationCallExpEClass, CGLibraryOperationCallExp.class, "CGLibraryOperationCallExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCGLibraryOperationCallExp_LibraryOperation(), this.getLibraryOperation(), "libraryOperation", null, 1, 1, CGLibraryOperationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgLibraryPropertyCallExpEClass, CGLibraryPropertyCallExp.class, "CGLibraryPropertyCallExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCGLibraryPropertyCallExp_LibraryProperty(), this.getLibraryProperty(), "libraryProperty", null, 1, 1, CGLibraryPropertyCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgLocalVariableEClass, CGLocalVariable.class, "CGLocalVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgMapExpEClass, CGMapExp.class, "CGMapExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGMapExp_Parts(), this.getCGMapPart(), this.getCGMapPart_MapExp(), "parts", null, 0, -1, CGMapExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgMapPartEClass, CGMapPart.class, "CGMapPart", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGMapPart_Key(), this.getCGValuedElement(), null, "key", null, 1, 1, CGMapPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCGMapPart_Value(), this.getCGValuedElement(), null, "value", null, 1, 1, CGMapPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCGMapPart_MapExp(), this.getCGMapExp(), this.getCGMapExp_Parts(), "mapExp", null, 1, 1, CGMapPart.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgModelEClass, CGModel.class, "CGModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGModel_Globals(), this.getCGConstant(), null, "globals", null, 0, -1, CGModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCGModel_Packages(), this.getCGPackage(), null, "packages", null, 0, -1, CGModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(cgNamedElementEClass, CGNamedElement.class, "CGNamedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCGNamedElement_Ast(), this.getElement(), "ast", null, 1, 1, CGNamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCGNamedElement_Name(), ecorePackage.getEString(), "name", null, 1, 1, CGNamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgNativeOperationEClass, CGNativeOperation.class, "CGNativeOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgNativeOperationCallExpEClass, CGNativeOperationCallExp.class, "CGNativeOperationCallExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCGNativeOperationCallExp_Method(), this.getMethod(), "method", null, 1, 1, CGNativeOperationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCGNativeOperationCallExp_ThisIsSelf(), ecorePackage.getEBoolean(), "thisIsSelf", null, 1, 1, CGNativeOperationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgNativePropertyEClass, CGNativeProperty.class, "CGNativeProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgNativePropertyCallExpEClass, CGNativePropertyCallExp.class, "CGNativePropertyCallExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCGNativePropertyCallExp_Field(), this.getField(), "field", null, 1, 1, CGNativePropertyCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgNavigationCallExpEClass, CGNavigationCallExp.class, "CGNavigationCallExp", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCGNavigationCallExp_ReferredProperty(), this.getProperty(), "referredProperty", null, 1, 1, CGNavigationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgNullEClass, CGNull.class, "CGNull", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgNumberEClass, CGNumber.class, "CGNumber", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCGNumber_NumericValue(), this.getNumber(), "numericValue", null, 1, 1, CGNumber.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgOperationEClass, CGOperation.class, "CGOperation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGOperation_Preconditions(), this.getCGConstraint(), null, "preconditions", null, 0, -1, CGOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCGOperation_Postconditions(), this.getCGConstraint(), null, "postconditions", null, 0, -1, CGOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCGOperation_ContainingClass(), this.getCGClass(), this.getCGClass_Operations(), "containingClass", null, 1, 1, CGOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgOperationCallExpEClass, CGOperationCallExp.class, "CGOperationCallExp", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGOperationCallExp_Arguments(), this.getCGValuedElement(), null, "arguments", null, 0, -1, CGOperationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCGOperationCallExp_ReferredOperation(), this.getOperation(), "referredOperation", null, 1, 1, CGOperationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgOppositePropertyCallExpEClass, CGOppositePropertyCallExp.class, "CGOppositePropertyCallExp", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgPackageEClass, CGPackage.class, "CGPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGPackage_Classes(), this.getCGClass(), this.getCGClass_ContainingPackage(), "classes", null, 0, -1, CGPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCGPackage_Packages(), this.getCGPackage(), this.getCGPackage_ContainingPackage(), "packages", null, 0, -1, CGPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCGPackage_ContainingPackage(), this.getCGPackage(), this.getCGPackage_Packages(), "containingPackage", null, 0, 1, CGPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgParameterEClass, CGParameter.class, "CGParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGParameter_Callable(), this.getCGCallable(), this.getCGCallable_Parameters(), "callable", null, 0, 1, CGParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgPropertyEClass, CGProperty.class, "CGProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGProperty_ContainingClass(), this.getCGClass(), this.getCGClass_Properties(), "containingClass", null, 1, 1, CGProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCGProperty_Body(), this.getCGValuedElement(), null, "body", null, 0, 1, CGProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgPropertyCallExpEClass, CGPropertyCallExp.class, "CGPropertyCallExp", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgRealEClass, CGReal.class, "CGReal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgSettableVariableEClass, CGSettableVariable.class, "CGSettableVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgShadowExpEClass, CGShadowExp.class, "CGShadowExp", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGShadowExp_Parts(), this.getCGShadowPart(), this.getCGShadowPart_ShadowExp(), "parts", null, 0, -1, CGShadowExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCGShadowExp_ExecutorType(), this.getCGExecutorType(), null, "executorType", null, 1, 1, CGShadowExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgShadowPartEClass, CGShadowPart.class, "CGShadowPart", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGShadowPart_Init(), this.getCGValuedElement(), null, "init", null, 1, 1, CGShadowPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCGShadowPart_ShadowExp(), this.getCGShadowExp(), this.getCGShadowExp_Parts(), "shadowExp", null, 1, 1, CGShadowPart.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCGShadowPart_ExecutorPart(), this.getCGExecutorShadowPart(), null, "executorPart", null, 1, 1, CGShadowPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgStringEClass, CGString.class, "CGString", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCGString_StringValue(), ecorePackage.getEString(), "stringValue", null, 1, 1, CGString.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgTemplateParameterExpEClass, CGTemplateParameterExp.class, "CGTemplateParameterExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGTemplateParameterExp_TemplateableElement(), this.getCGValuedElement(), null, "templateableElement", null, 1, 1, CGTemplateParameterExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCGTemplateParameterExp_Index(), ecorePackage.getEInt(), "index", null, 1, 1, CGTemplateParameterExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgTextEClass, CGText.class, "CGText", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCGText_TextValue(), ecorePackage.getEString(), "textValue", null, 1, 1, CGText.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgThrowExpEClass, CGThrowExp.class, "CGThrowExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgTupleExpEClass, CGTupleExp.class, "CGTupleExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGTupleExp_Parts(), this.getCGTuplePart(), this.getCGTuplePart_TupleExp(), "parts", null, 0, -1, CGTupleExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgTuplePartEClass, CGTuplePart.class, "CGTuplePart", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGTuplePart_Init(), this.getCGValuedElement(), null, "init", null, 1, 1, CGTuplePart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCGTuplePart_TupleExp(), this.getCGTupleExp(), this.getCGTupleExp_Parts(), "tupleExp", null, 1, 1, CGTuplePart.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgTuplePartCallExpEClass, CGTuplePartCallExp.class, "CGTuplePartCallExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCGTuplePartCallExp_AstTuplePartId(), this.getTuplePartId(), "astTuplePartId", null, 1, 1, CGTuplePartCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgTypeIdEClass, CGTypeId.class, "CGTypeId", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgTypeExpEClass, CGTypeExp.class, "CGTypeExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGTypeExp_ExecutorType(), this.getCGExecutorType(), null, "executorType", null, 1, 1, CGTypeExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgTypedElementEClass, CGTypedElement.class, "CGTypedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGTypedElement_TypeId(), this.getCGTypeId(), null, "typeId", null, 1, 1, CGTypedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCGTypedElement_Required(), ecorePackage.getEBoolean(), "required", "false", 1, 1, CGTypedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgUnboxExpEClass, CGUnboxExp.class, "CGUnboxExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgUnlimitedEClass, CGUnlimited.class, "CGUnlimited", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cgValuedElementEClass, CGValuedElement.class, "CGValuedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGValuedElement_DependsOn(), this.getCGValuedElement(), null, "dependsOn", null, 0, -1, CGValuedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCGValuedElement_Owns(), this.getCGValuedElement(), null, "owns", null, 0, -1, CGValuedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgVariableEClass, CGVariable.class, "CGVariable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGVariable_Init(), this.getCGValuedElement(), null, "init", null, 0, 1, CGVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cgVariableExpEClass, CGVariableExp.class, "CGVariableExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCGVariableExp_ReferredVariable(), this.getCGVariable(), null, "referredVariable", null, 1, 1, CGVariableExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nameableEClass, Nameable.class, "Nameable", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		// Initialize data types
		initEDataType(elementEDataType, Element.class, "Element", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(elementIdEDataType, ElementId.class, "ElementId", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(enumerationLiteralIdEDataType, EnumerationLiteralId.class, "EnumerationLiteralId", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(iterationEDataType, Iteration.class, "Iteration", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(fieldEDataType, Field.class, "Field", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(libraryIterationEDataType, LibraryIteration.class, "LibraryIteration", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(libraryOperationEDataType, LibraryOperation.class, "LibraryOperation", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(libraryPropertyEDataType, LibraryProperty.class, "LibraryProperty", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(methodEDataType, Method.class, "Method", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(numberEDataType, Number.class, "Number", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(objectEDataType, Object.class, "Object", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(operationEDataType, Operation.class, "Operation", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(propertyEDataType, Property.class, "Property", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(tuplePartIdEDataType, TuplePartId.class, "TuplePartId", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(typeEDataType, Type.class, "Type", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(typeIdEDataType, TypeId.class, "TypeId", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/OCL/Import
		createImportAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/OCL/Import</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createImportAnnotations() {
		String source = "http://www.eclipse.org/OCL/Import";
		addAnnotation
		  (this,
		   source,
		   new String[] {
			   "ecore", "http://www.eclipse.org/emf/2002/Ecore"
		   });
	}

} //CGModelPackageImpl
