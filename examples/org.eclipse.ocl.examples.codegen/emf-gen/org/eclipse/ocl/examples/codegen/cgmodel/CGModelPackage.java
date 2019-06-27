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
package org.eclipse.ocl.examples.codegen.cgmodel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * The CGModel provides a Code Generation friendly form of the OCL Abstract Syntax pivot model, with distinct classes
 * such as LibraryPropertyCallExp and EcorePropertyCallExp for distinct purposes. Additional classes
 * such as BoxExp, CatchExp and CastExp support rewrite optimizations.
 * <p>
 * References to the Abstract Syntax model are expressed as attributes with datatype values in order to avoid
 * confusion as to whether the new or old pivot mosdel is in use while code generating the pivot model.
 * <!-- end-model-doc -->
 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory
 * @generated
 */
public interface CGModelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "cgmodel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/ocl/1.0.0/CG";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "ch";

	/**
	 * The package content type ID.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eCONTENT_TYPE = "org.eclipse.ocl.examples.codegen.cgmodel";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("null")
	@NonNull CGModelPackage eINSTANCE = org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl.init();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGAccumulator <em>CG Accumulator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Accumulator</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGAccumulator
	 * @generated
	 */
	EClass getCGAccumulator();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGAssertNonNullExp <em>CG Assert Non Null Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Assert Non Null Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGAssertNonNullExp
	 * @generated
	 */
	EClass getCGAssertNonNullExp();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGBoolean <em>CG Boolean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Boolean</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGBoolean
	 * @generated
	 */
	EClass getCGBoolean();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGBoolean#isBooleanValue <em>Boolean Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Boolean Value</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGBoolean#isBooleanValue()
	 * @see #getCGBoolean()
	 * @generated
	 */
	EAttribute getCGBoolean_BooleanValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGBoxExp <em>CG Box Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Box Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGBoxExp
	 * @generated
	 */
	EClass getCGBoxExp();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp <em>CG Built In Iteration Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Built In Iteration Call Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp
	 * @generated
	 */
	EClass getCGBuiltInIterationCallExp();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp#getAccumulator <em>Accumulator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Accumulator</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp#getAccumulator()
	 * @see #getCGBuiltInIterationCallExp()
	 * @generated
	 */
	EReference getCGBuiltInIterationCallExp_Accumulator();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation <em>CG Cached Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Cached Operation</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation
	 * @generated
	 */
	EClass getCGCachedOperation();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation#getFinalOperations <em>Final Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Final Operations</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation#getFinalOperations()
	 * @see #getCGCachedOperation()
	 * @generated
	 */
	EReference getCGCachedOperation_FinalOperations();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation#getVirtualOperations <em>Virtual Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Virtual Operations</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation#getVirtualOperations()
	 * @see #getCGCachedOperation()
	 * @generated
	 */
	EReference getCGCachedOperation_VirtualOperations();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperationCallExp <em>CG Cached Operation Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Cached Operation Call Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperationCallExp
	 * @generated
	 */
	EClass getCGCachedOperationCallExp();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperationCallExp#getMethod <em>Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Method</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperationCallExp#getMethod()
	 * @see #getCGCachedOperationCallExp()
	 * @generated
	 */
	EAttribute getCGCachedOperationCallExp_Method();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperationCallExp#isThisIsSelf <em>This Is Self</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>This Is Self</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperationCallExp#isThisIsSelf()
	 * @see #getCGCachedOperationCallExp()
	 * @generated
	 */
	EAttribute getCGCachedOperationCallExp_ThisIsSelf();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGCallable <em>CG Callable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Callable</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGCallable
	 * @generated
	 */
	EClass getCGCallable();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGCallable#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGCallable#getParameters()
	 * @see #getCGCallable()
	 * @generated
	 */
	EReference getCGCallable_Parameters();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGCallable#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGCallable#getBody()
	 * @see #getCGCallable()
	 * @generated
	 */
	EReference getCGCallable_Body();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGClass <em>CG Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Class</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGClass
	 * @generated
	 */
	EClass getCGClass();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGClass#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Operations</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGClass#getOperations()
	 * @see #getCGClass()
	 * @generated
	 */
	EReference getCGClass_Operations();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGClass#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Properties</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGClass#getProperties()
	 * @see #getCGClass()
	 * @generated
	 */
	EReference getCGClass_Properties();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGClass#getInvariants <em>Invariants</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Invariants</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGClass#getInvariants()
	 * @see #getCGClass()
	 * @generated
	 */
	EReference getCGClass_Invariants();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGClass#getSuperTypes <em>Super Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Super Types</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGClass#getSuperTypes()
	 * @see #getCGClass()
	 * @generated
	 */
	EReference getCGClass_SuperTypes();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGClass#isInterface <em>Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interface</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGClass#isInterface()
	 * @see #getCGClass()
	 * @generated
	 */
	EAttribute getCGClass_Interface();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGClass#getTemplateParameters <em>Template Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Template Parameters</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGClass#getTemplateParameters()
	 * @see #getCGClass()
	 * @generated
	 */
	EReference getCGClass_TemplateParameters();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGClass#getClasses <em>Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Classes</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGClass#getClasses()
	 * @see #getCGClass()
	 * @generated
	 */
	EReference getCGClass_Classes();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGClass#getContainingClass <em>Containing Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Containing Class</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGClass#getContainingClass()
	 * @see #getCGClass()
	 * @generated
	 */
	EReference getCGClass_ContainingClass();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp <em>CG Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Call Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp
	 * @generated
	 */
	EClass getCGCallExp();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp#isInvalidating <em>Invalidating</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Invalidating</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp#isInvalidating()
	 * @see #getCGCallExp()
	 * @generated
	 */
	EAttribute getCGCallExp_Invalidating();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp#isValidating <em>Validating</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Validating</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp#isValidating()
	 * @see #getCGCallExp()
	 * @generated
	 */
	EAttribute getCGCallExp_Validating();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Source</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp#getSource()
	 * @see #getCGCallExp()
	 * @generated
	 */
	EReference getCGCallExp_Source();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGCastExp <em>CG Cast Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Cast Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGCastExp
	 * @generated
	 */
	EClass getCGCastExp();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGCastExp#getExecutorType <em>Executor Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Executor Type</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGCastExp#getExecutorType()
	 * @see #getCGCastExp()
	 * @generated
	 */
	EReference getCGCastExp_ExecutorType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGCatchExp <em>CG Catch Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Catch Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGCatchExp
	 * @generated
	 */
	EClass getCGCatchExp();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionExp <em>CG Collection Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Collection Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionExp
	 * @generated
	 */
	EClass getCGCollectionExp();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionExp#getParts <em>Parts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parts</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionExp#getParts()
	 * @see #getCGCollectionExp()
	 * @generated
	 */
	EReference getCGCollectionExp_Parts();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionPart <em>CG Collection Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Collection Part</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionPart
	 * @generated
	 */
	EClass getCGCollectionPart();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionPart#getFirst <em>First</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>First</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionPart#getFirst()
	 * @see #getCGCollectionPart()
	 * @generated
	 */
	EReference getCGCollectionPart_First();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionPart#getLast <em>Last</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Last</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionPart#getLast()
	 * @see #getCGCollectionPart()
	 * @generated
	 */
	EReference getCGCollectionPart_Last();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionPart#getCollectionExp <em>Collection Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Collection Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionPart#getCollectionExp()
	 * @see #getCGCollectionPart()
	 * @generated
	 */
	EReference getCGCollectionPart_CollectionExp();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGClass#getContainingPackage <em>Containing Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Containing Package</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGClass#getContainingPackage()
	 * @see #getCGClass()
	 * @generated
	 */
	EReference getCGClass_ContainingPackage();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGConstant <em>CG Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Constant</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGConstant
	 * @generated
	 */
	EClass getCGConstant();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGFinalVariable <em>CG Final Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Final Variable</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGFinalVariable
	 * @generated
	 */
	EClass getCGFinalVariable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGGuardExp <em>CG Guard Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Guard Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGGuardExp
	 * @generated
	 */
	EClass getCGGuardExp();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGGuardExp#getMessage <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGGuardExp#getMessage()
	 * @see #getCGGuardExp()
	 * @generated
	 */
	EAttribute getCGGuardExp_Message();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGGuardExp#isSafe <em>Safe</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Safe</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGGuardExp#isSafe()
	 * @see #getCGGuardExp()
	 * @generated
	 */
	EAttribute getCGGuardExp_Safe();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperationCallExp <em>CG Ecore Operation Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Ecore Operation Call Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperationCallExp
	 * @generated
	 */
	EClass getCGEcoreOperationCallExp();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperationCallExp#getEOperation <em>EOperation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EOperation</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperationCallExp#getEOperation()
	 * @see #getCGEcoreOperationCallExp()
	 * @generated
	 */
	EReference getCGEcoreOperationCallExp_EOperation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOppositePropertyCallExp <em>CG Ecore Opposite Property Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Ecore Opposite Property Call Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOppositePropertyCallExp
	 * @generated
	 */
	EClass getCGEcoreOppositePropertyCallExp();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOppositePropertyCallExp#getEStructuralFeature <em>EStructural Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EStructural Feature</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOppositePropertyCallExp#getEStructuralFeature()
	 * @see #getCGEcoreOppositePropertyCallExp()
	 * @generated
	 */
	EReference getCGEcoreOppositePropertyCallExp_EStructuralFeature();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGEcorePropertyCallExp <em>CG Ecore Property Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Ecore Property Call Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGEcorePropertyCallExp
	 * @generated
	 */
	EClass getCGEcorePropertyCallExp();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGEcorePropertyCallExp#getEStructuralFeature <em>EStructural Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EStructural Feature</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGEcorePropertyCallExp#getEStructuralFeature()
	 * @see #getCGEcorePropertyCallExp()
	 * @generated
	 */
	EReference getCGEcorePropertyCallExp_EStructuralFeature();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGInteger <em>CG Integer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Integer</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGInteger
	 * @generated
	 */
	EClass getCGInteger();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid <em>CG Invalid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Invalid</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid
	 * @generated
	 */
	EClass getCGInvalid();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid#getMessageTemplate <em>Message Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message Template</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid#getMessageTemplate()
	 * @see #getCGInvalid()
	 * @generated
	 */
	EAttribute getCGInvalid_MessageTemplate();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid#getBindings <em>Bindings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Bindings</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid#getBindings()
	 * @see #getCGInvalid()
	 * @generated
	 */
	EAttribute getCGInvalid_Bindings();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqualExp <em>CG Is Equal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Is Equal Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqualExp
	 * @generated
	 */
	EClass getCGIsEqualExp();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqualExp#getArgument <em>Argument</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Argument</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqualExp#getArgument()
	 * @see #getCGIsEqualExp()
	 * @generated
	 */
	EReference getCGIsEqualExp_Argument();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqualExp#isNotEquals <em>Not Equals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Not Equals</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqualExp#isNotEquals()
	 * @see #getCGIsEqualExp()
	 * @generated
	 */
	EAttribute getCGIsEqualExp_NotEquals();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqual2Exp <em>CG Is Equal2 Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Is Equal2 Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqual2Exp
	 * @generated
	 */
	EClass getCGIsEqual2Exp();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqual2Exp#getArgument <em>Argument</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Argument</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqual2Exp#getArgument()
	 * @see #getCGIsEqual2Exp()
	 * @generated
	 */
	EReference getCGIsEqual2Exp_Argument();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIsInvalidExp <em>CG Is Invalid Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Is Invalid Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGIsInvalidExp
	 * @generated
	 */
	EClass getCGIsInvalidExp();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIsKindOfExp <em>CG Is Kind Of Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Is Kind Of Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGIsKindOfExp
	 * @generated
	 */
	EClass getCGIsKindOfExp();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIsKindOfExp#getExecutorType <em>Executor Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Executor Type</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGIsKindOfExp#getExecutorType()
	 * @see #getCGIsKindOfExp()
	 * @generated
	 */
	EReference getCGIsKindOfExp_ExecutorType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIsUndefinedExp <em>CG Is Undefined Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Is Undefined Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGIsUndefinedExp
	 * @generated
	 */
	EClass getCGIsUndefinedExp();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp <em>CG Iteration Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Iteration Call Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp
	 * @generated
	 */
	EClass getCGIterationCallExp();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp#getReferredIteration <em>Referred Iteration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Referred Iteration</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp#getReferredIteration()
	 * @see #getCGIterationCallExp()
	 * @generated
	 */
	EAttribute getCGIterationCallExp_ReferredIteration();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp#getIterators <em>Iterators</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Iterators</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp#getIterators()
	 * @see #getCGIterationCallExp()
	 * @generated
	 */
	EReference getCGIterationCallExp_Iterators();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp#getBody()
	 * @see #getCGIterationCallExp()
	 * @generated
	 */
	EReference getCGIterationCallExp_Body();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp#getCoIterators <em>Co Iterators</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Co Iterators</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp#getCoIterators()
	 * @see #getCGIterationCallExp()
	 * @generated
	 */
	EReference getCGIterationCallExp_CoIterators();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIterator <em>CG Iterator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Iterator</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGIterator
	 * @generated
	 */
	EClass getCGIterator();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperationCallExp <em>CG Library Operation Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Library Operation Call Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperationCallExp
	 * @generated
	 */
	EClass getCGLibraryOperationCallExp();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperationCallExp#getLibraryOperation <em>Library Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Library Operation</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperationCallExp#getLibraryOperation()
	 * @see #getCGLibraryOperationCallExp()
	 * @generated
	 */
	EAttribute getCGLibraryOperationCallExp_LibraryOperation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryPropertyCallExp <em>CG Library Property Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Library Property Call Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryPropertyCallExp
	 * @generated
	 */
	EClass getCGLibraryPropertyCallExp();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryPropertyCallExp#getLibraryProperty <em>Library Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Library Property</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryPropertyCallExp#getLibraryProperty()
	 * @see #getCGLibraryPropertyCallExp()
	 * @generated
	 */
	EAttribute getCGLibraryPropertyCallExp_LibraryProperty();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp <em>CG Constant Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Constant Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp
	 * @generated
	 */
	EClass getCGConstantExp();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp#getReferredConstant <em>Referred Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referred Constant</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp#getReferredConstant()
	 * @see #getCGConstantExp()
	 * @generated
	 */
	EReference getCGConstantExp_ReferredConstant();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint <em>CG Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Constraint</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint
	 * @generated
	 */
	EClass getCGConstraint();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGShadowExp <em>CG Shadow Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Shadow Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGShadowExp
	 * @generated
	 */
	EClass getCGShadowExp();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGShadowExp#getParts <em>Parts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parts</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGShadowExp#getParts()
	 * @see #getCGShadowExp()
	 * @generated
	 */
	EReference getCGShadowExp_Parts();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGShadowExp#getExecutorType <em>Executor Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Executor Type</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGShadowExp#getExecutorType()
	 * @see #getCGShadowExp()
	 * @generated
	 */
	EReference getCGShadowExp_ExecutorType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGShadowPart <em>CG Shadow Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Shadow Part</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGShadowPart
	 * @generated
	 */
	EClass getCGShadowPart();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGShadowPart#getInit <em>Init</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Init</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGShadowPart#getInit()
	 * @see #getCGShadowPart()
	 * @generated
	 */
	EReference getCGShadowPart_Init();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGShadowPart#getShadowExp <em>Shadow Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Shadow Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGShadowPart#getShadowExp()
	 * @see #getCGShadowPart()
	 * @generated
	 */
	EReference getCGShadowPart_ShadowExp();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGShadowPart#getExecutorPart <em>Executor Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Executor Part</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGShadowPart#getExecutorPart()
	 * @see #getCGShadowPart()
	 * @generated
	 */
	EReference getCGShadowPart_ExecutorPart();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreClassShadowExp <em>CG Ecore Class Shadow Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Ecore Class Shadow Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreClassShadowExp
	 * @generated
	 */
	EClass getCGEcoreClassShadowExp();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreClassShadowExp#getEClass <em>EClass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EClass</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreClassShadowExp#getEClass()
	 * @see #getCGEcoreClassShadowExp()
	 * @generated
	 */
	EReference getCGEcoreClassShadowExp_EClass();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreDataTypeShadowExp <em>CG Ecore Data Type Shadow Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Ecore Data Type Shadow Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreDataTypeShadowExp
	 * @generated
	 */
	EClass getCGEcoreDataTypeShadowExp();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreDataTypeShadowExp#getEDataType <em>EData Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EData Type</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreDataTypeShadowExp#getEDataType()
	 * @see #getCGEcoreDataTypeShadowExp()
	 * @generated
	 */
	EReference getCGEcoreDataTypeShadowExp_EDataType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreExp <em>CG Ecore Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Ecore Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreExp
	 * @generated
	 */
	EClass getCGEcoreExp();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreExp#getEClassifier <em>EClassifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EClassifier</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreExp#getEClassifier()
	 * @see #getCGEcoreExp()
	 * @generated
	 */
	EReference getCGEcoreExp_EClassifier();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperation <em>CG Ecore Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Ecore Operation</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperation
	 * @generated
	 */
	EClass getCGEcoreOperation();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperation#getEOperation <em>EOperation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EOperation</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperation#getEOperation()
	 * @see #getCGEcoreOperation()
	 * @generated
	 */
	EReference getCGEcoreOperation_EOperation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGLetExp <em>CG Let Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Let Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGLetExp
	 * @generated
	 */
	EClass getCGLetExp();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGLetExp#getInit <em>Init</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Init</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGLetExp#getInit()
	 * @see #getCGLetExp()
	 * @generated
	 */
	EReference getCGLetExp_Init();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGLetExp#getIn <em>In</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>In</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGLetExp#getIn()
	 * @see #getCGLetExp()
	 * @generated
	 */
	EReference getCGLetExp_In();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterateCallExp <em>CG Library Iterate Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Library Iterate Call Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterateCallExp
	 * @generated
	 */
	EClass getCGLibraryIterateCallExp();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterateCallExp#getResult <em>Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Result</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterateCallExp#getResult()
	 * @see #getCGLibraryIterateCallExp()
	 * @generated
	 */
	EReference getCGLibraryIterateCallExp_Result();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterationCallExp <em>CG Library Iteration Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Library Iteration Call Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterationCallExp
	 * @generated
	 */
	EClass getCGLibraryIterationCallExp();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterationCallExp#getLibraryIteration <em>Library Iteration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Library Iteration</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterationCallExp#getLibraryIteration()
	 * @see #getCGLibraryIterationCallExp()
	 * @generated
	 */
	EAttribute getCGLibraryIterationCallExp_LibraryIteration();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperation <em>CG Library Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Library Operation</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperation
	 * @generated
	 */
	EClass getCGLibraryOperation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGLocalVariable <em>CG Local Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Local Variable</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGLocalVariable
	 * @generated
	 */
	EClass getCGLocalVariable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGMapExp <em>CG Map Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Map Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGMapExp
	 * @generated
	 */
	EClass getCGMapExp();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGMapExp#getParts <em>Parts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parts</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGMapExp#getParts()
	 * @see #getCGMapExp()
	 * @generated
	 */
	EReference getCGMapExp_Parts();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGMapPart <em>CG Map Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Map Part</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGMapPart
	 * @generated
	 */
	EClass getCGMapPart();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGMapPart#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Key</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGMapPart#getKey()
	 * @see #getCGMapPart()
	 * @generated
	 */
	EReference getCGMapPart_Key();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGMapPart#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGMapPart#getValue()
	 * @see #getCGMapPart()
	 * @generated
	 */
	EReference getCGMapPart_Value();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGMapPart#getMapExp <em>Map Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Map Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGMapPart#getMapExp()
	 * @see #getCGMapPart()
	 * @generated
	 */
	EReference getCGMapPart_MapExp();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGModel <em>CG Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Model</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModel
	 * @generated
	 */
	EClass getCGModel();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGModel#getGlobals <em>Globals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Globals</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModel#getGlobals()
	 * @see #getCGModel()
	 * @generated
	 */
	EReference getCGModel_Globals();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGModel#getPackages <em>Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Packages</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModel#getPackages()
	 * @see #getCGModel()
	 * @generated
	 */
	EReference getCGModel_Packages();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGElement <em>CG Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Element</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGElement
	 * @generated
	 */
	EClass getCGElement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGElementId <em>CG Element Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Element Id</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGElementId
	 * @generated
	 */
	EClass getCGElementId();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGElementId#getElementId <em>Element Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Element Id</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGElementId#getElementId()
	 * @see #getCGElementId()
	 * @generated
	 */
	EAttribute getCGElementId_ElementId();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorCompositionProperty <em>CG Executor Composition Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Executor Composition Property</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorCompositionProperty
	 * @generated
	 */
	EClass getCGExecutorCompositionProperty();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorNavigationProperty <em>CG Executor Navigation Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Executor Navigation Property</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorNavigationProperty
	 * @generated
	 */
	EClass getCGExecutorNavigationProperty();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOppositeProperty <em>CG Executor Opposite Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Executor Opposite Property</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOppositeProperty
	 * @generated
	 */
	EClass getCGExecutorOppositeProperty();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOperation <em>CG Executor Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Executor Operation</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOperation
	 * @generated
	 */
	EClass getCGExecutorOperation();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOperation#getUnderlyingOperationId <em>Underlying Operation Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Underlying Operation Id</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOperation#getUnderlyingOperationId()
	 * @see #getCGExecutorOperation()
	 * @generated
	 */
	EReference getCGExecutorOperation_UnderlyingOperationId();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOperationCallExp <em>CG Executor Operation Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Executor Operation Call Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOperationCallExp
	 * @generated
	 */
	EClass getCGExecutorOperationCallExp();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOperationCallExp#getExecutorOperation <em>Executor Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Executor Operation</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOperationCallExp#getExecutorOperation()
	 * @see #getCGExecutorOperationCallExp()
	 * @generated
	 */
	EReference getCGExecutorOperationCallExp_ExecutorOperation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOppositePropertyCallExp <em>CG Executor Opposite Property Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Executor Opposite Property Call Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOppositePropertyCallExp
	 * @generated
	 */
	EClass getCGExecutorOppositePropertyCallExp();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOppositePropertyCallExp#getExecutorProperty <em>Executor Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Executor Property</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOppositePropertyCallExp#getExecutorProperty()
	 * @see #getCGExecutorOppositePropertyCallExp()
	 * @generated
	 */
	EReference getCGExecutorOppositePropertyCallExp_ExecutorProperty();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorProperty <em>CG Executor Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Executor Property</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorProperty
	 * @generated
	 */
	EClass getCGExecutorProperty();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorProperty#getUnderlyingPropertyId <em>Underlying Property Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Underlying Property Id</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorProperty#getUnderlyingPropertyId()
	 * @see #getCGExecutorProperty()
	 * @generated
	 */
	EReference getCGExecutorProperty_UnderlyingPropertyId();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorPropertyCallExp <em>CG Executor Property Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Executor Property Call Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorPropertyCallExp
	 * @generated
	 */
	EClass getCGExecutorPropertyCallExp();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorPropertyCallExp#getExecutorProperty <em>Executor Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Executor Property</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorPropertyCallExp#getExecutorProperty()
	 * @see #getCGExecutorPropertyCallExp()
	 * @generated
	 */
	EReference getCGExecutorPropertyCallExp_ExecutorProperty();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorShadowPart <em>CG Executor Shadow Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Executor Shadow Part</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorShadowPart
	 * @generated
	 */
	EClass getCGExecutorShadowPart();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType <em>CG Executor Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Executor Type</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType
	 * @generated
	 */
	EClass getCGExecutorType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType#getUnderlyingTypeId <em>Underlying Type Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Underlying Type Id</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType#getUnderlyingTypeId()
	 * @see #getCGExecutorType()
	 * @generated
	 */
	EReference getCGExecutorType_UnderlyingTypeId();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIfExp <em>CG If Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG If Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGIfExp
	 * @generated
	 */
	EClass getCGIfExp();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIfExp#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Condition</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGIfExp#getCondition()
	 * @see #getCGIfExp()
	 * @generated
	 */
	EReference getCGIfExp_Condition();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIfExp#getThenExpression <em>Then Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Then Expression</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGIfExp#getThenExpression()
	 * @see #getCGIfExp()
	 * @generated
	 */
	EReference getCGIfExp_ThenExpression();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGIfExp#getElseExpression <em>Else Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Else Expression</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGIfExp#getElseExpression()
	 * @see #getCGIfExp()
	 * @generated
	 */
	EReference getCGIfExp_ElseExpression();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement <em>CG Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Named Element</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement
	 * @generated
	 */
	EClass getCGNamedElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement#getAst <em>Ast</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ast</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement#getAst()
	 * @see #getCGNamedElement()
	 * @generated
	 */
	EAttribute getCGNamedElement_Ast();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement#getName()
	 * @see #getCGNamedElement()
	 * @generated
	 */
	EAttribute getCGNamedElement_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperation <em>CG Native Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Native Operation</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperation
	 * @generated
	 */
	EClass getCGNativeOperation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp <em>CG Native Operation Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Native Operation Call Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp
	 * @generated
	 */
	EClass getCGNativeOperationCallExp();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp#getMethod <em>Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Method</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp#getMethod()
	 * @see #getCGNativeOperationCallExp()
	 * @generated
	 */
	EAttribute getCGNativeOperationCallExp_Method();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp#isThisIsSelf <em>This Is Self</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>This Is Self</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp#isThisIsSelf()
	 * @see #getCGNativeOperationCallExp()
	 * @generated
	 */
	EAttribute getCGNativeOperationCallExp_ThisIsSelf();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNativeProperty <em>CG Native Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Native Property</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGNativeProperty
	 * @generated
	 */
	EClass getCGNativeProperty();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNativePropertyCallExp <em>CG Native Property Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Native Property Call Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGNativePropertyCallExp
	 * @generated
	 */
	EClass getCGNativePropertyCallExp();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNativePropertyCallExp#getField <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Field</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGNativePropertyCallExp#getField()
	 * @see #getCGNativePropertyCallExp()
	 * @generated
	 */
	EAttribute getCGNativePropertyCallExp_Field();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp <em>CG Navigation Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Navigation Call Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp
	 * @generated
	 */
	EClass getCGNavigationCallExp();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp#getReferredProperty <em>Referred Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Referred Property</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp#getReferredProperty()
	 * @see #getCGNavigationCallExp()
	 * @generated
	 */
	EAttribute getCGNavigationCallExp_ReferredProperty();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNull <em>CG Null</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Null</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGNull
	 * @generated
	 */
	EClass getCGNull();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNumber <em>CG Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Number</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGNumber
	 * @generated
	 */
	EClass getCGNumber();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGNumber#getNumericValue <em>Numeric Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Numeric Value</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGNumber#getNumericValue()
	 * @see #getCGNumber()
	 * @generated
	 */
	EAttribute getCGNumber_NumericValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGOperation <em>CG Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Operation</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGOperation
	 * @generated
	 */
	EClass getCGOperation();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGOperation#getPreconditions <em>Preconditions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Preconditions</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGOperation#getPreconditions()
	 * @see #getCGOperation()
	 * @generated
	 */
	EReference getCGOperation_Preconditions();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGOperation#getPostconditions <em>Postconditions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Postconditions</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGOperation#getPostconditions()
	 * @see #getCGOperation()
	 * @generated
	 */
	EReference getCGOperation_Postconditions();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGOperation#getContainingClass <em>Containing Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Containing Class</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGOperation#getContainingClass()
	 * @see #getCGOperation()
	 * @generated
	 */
	EReference getCGOperation_ContainingClass();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp <em>CG Operation Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Operation Call Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp
	 * @generated
	 */
	EClass getCGOperationCallExp();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp#getReferredOperation <em>Referred Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Referred Operation</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp#getReferredOperation()
	 * @see #getCGOperationCallExp()
	 * @generated
	 */
	EAttribute getCGOperationCallExp_ReferredOperation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGOppositePropertyCallExp <em>CG Opposite Property Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Opposite Property Call Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGOppositePropertyCallExp
	 * @generated
	 */
	EClass getCGOppositePropertyCallExp();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp#getArguments <em>Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Arguments</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp#getArguments()
	 * @see #getCGOperationCallExp()
	 * @generated
	 */
	EReference getCGOperationCallExp_Arguments();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGPackage <em>CG Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Package</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGPackage
	 * @generated
	 */
	EClass getCGPackage();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGPackage#getClasses <em>Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Classes</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGPackage#getClasses()
	 * @see #getCGPackage()
	 * @generated
	 */
	EReference getCGPackage_Classes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGPackage#getPackages <em>Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Packages</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGPackage#getPackages()
	 * @see #getCGPackage()
	 * @generated
	 */
	EReference getCGPackage_Packages();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGPackage#getContainingPackage <em>Containing Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Containing Package</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGPackage#getContainingPackage()
	 * @see #getCGPackage()
	 * @generated
	 */
	EReference getCGPackage_ContainingPackage();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGParameter <em>CG Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Parameter</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGParameter
	 * @generated
	 */
	EClass getCGParameter();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGParameter#getCallable <em>Callable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Callable</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGParameter#getCallable()
	 * @see #getCGParameter()
	 * @generated
	 */
	EReference getCGParameter_Callable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGProperty <em>CG Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Property</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGProperty
	 * @generated
	 */
	EClass getCGProperty();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGProperty#getContainingClass <em>Containing Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Containing Class</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGProperty#getContainingClass()
	 * @see #getCGProperty()
	 * @generated
	 */
	EReference getCGProperty_ContainingClass();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGProperty#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGProperty#getBody()
	 * @see #getCGProperty()
	 * @generated
	 */
	EReference getCGProperty_Body();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGPropertyCallExp <em>CG Property Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Property Call Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGPropertyCallExp
	 * @generated
	 */
	EClass getCGPropertyCallExp();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGReal <em>CG Real</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Real</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGReal
	 * @generated
	 */
	EClass getCGReal();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGSettableVariable <em>CG Settable Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Settable Variable</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGSettableVariable
	 * @generated
	 */
	EClass getCGSettableVariable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGString <em>CG String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG String</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGString
	 * @generated
	 */
	EClass getCGString();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGString#getStringValue <em>String Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>String Value</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGString#getStringValue()
	 * @see #getCGString()
	 * @generated
	 */
	EAttribute getCGString_StringValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGTemplateParameterExp <em>CG Template Parameter Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Template Parameter Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGTemplateParameterExp
	 * @generated
	 */
	EClass getCGTemplateParameterExp();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGTemplateParameterExp#getTemplateableElement <em>Templateable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Templateable Element</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGTemplateParameterExp#getTemplateableElement()
	 * @see #getCGTemplateParameterExp()
	 * @generated
	 */
	EReference getCGTemplateParameterExp_TemplateableElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGTemplateParameterExp#getIndex <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Index</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGTemplateParameterExp#getIndex()
	 * @see #getCGTemplateParameterExp()
	 * @generated
	 */
	EAttribute getCGTemplateParameterExp_Index();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGText <em>CG Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Text</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGText
	 * @generated
	 */
	EClass getCGText();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGText#getTextValue <em>Text Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Value</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGText#getTextValue()
	 * @see #getCGText()
	 * @generated
	 */
	EAttribute getCGText_TextValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGThrowExp <em>CG Throw Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Throw Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGThrowExp
	 * @generated
	 */
	EClass getCGThrowExp();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGTupleExp <em>CG Tuple Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Tuple Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGTupleExp
	 * @generated
	 */
	EClass getCGTupleExp();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGTupleExp#getParts <em>Parts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parts</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGTupleExp#getParts()
	 * @see #getCGTupleExp()
	 * @generated
	 */
	EReference getCGTupleExp_Parts();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePart <em>CG Tuple Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Tuple Part</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePart
	 * @generated
	 */
	EClass getCGTuplePart();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePart#getInit <em>Init</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Init</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePart#getInit()
	 * @see #getCGTuplePart()
	 * @generated
	 */
	EReference getCGTuplePart_Init();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePart#getTupleExp <em>Tuple Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Tuple Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePart#getTupleExp()
	 * @see #getCGTuplePart()
	 * @generated
	 */
	EReference getCGTuplePart_TupleExp();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePartCallExp <em>CG Tuple Part Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Tuple Part Call Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePartCallExp
	 * @generated
	 */
	EClass getCGTuplePartCallExp();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePartCallExp#getAstTuplePartId <em>Ast Tuple Part Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ast Tuple Part Id</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePartCallExp#getAstTuplePartId()
	 * @see #getCGTuplePartCallExp()
	 * @generated
	 */
	EAttribute getCGTuplePartCallExp_AstTuplePartId();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId <em>CG Type Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Type Id</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId
	 * @generated
	 */
	EClass getCGTypeId();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGTypeExp <em>CG Type Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Type Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGTypeExp
	 * @generated
	 */
	EClass getCGTypeExp();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGTypeExp#getExecutorType <em>Executor Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Executor Type</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGTypeExp#getExecutorType()
	 * @see #getCGTypeExp()
	 * @generated
	 */
	EReference getCGTypeExp_ExecutorType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGTypedElement <em>CG Typed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Typed Element</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGTypedElement
	 * @generated
	 */
	EClass getCGTypedElement();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGTypedElement#getTypeId <em>Type Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type Id</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGTypedElement#getTypeId()
	 * @see #getCGTypedElement()
	 * @generated
	 */
	EReference getCGTypedElement_TypeId();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGTypedElement#isRequired <em>Required</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Required</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGTypedElement#isRequired()
	 * @see #getCGTypedElement()
	 * @generated
	 */
	EAttribute getCGTypedElement_Required();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGUnboxExp <em>CG Unbox Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Unbox Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGUnboxExp
	 * @generated
	 */
	EClass getCGUnboxExp();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGUnlimited <em>CG Unlimited</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Unlimited</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGUnlimited
	 * @generated
	 */
	EClass getCGUnlimited();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement <em>CG Valued Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Valued Element</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement
	 * @generated
	 */
	EClass getCGValuedElement();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement#getDependsOn <em>Depends On</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Depends On</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement#getDependsOn()
	 * @see #getCGValuedElement()
	 * @generated
	 */
	EReference getCGValuedElement_DependsOn();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement#getOwns <em>Owns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owns</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement#getOwns()
	 * @see #getCGValuedElement()
	 * @generated
	 */
	EReference getCGValuedElement_Owns();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGVariable <em>CG Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Variable</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGVariable
	 * @generated
	 */
	EClass getCGVariable();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGVariable#getInit <em>Init</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Init</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGVariable#getInit()
	 * @see #getCGVariable()
	 * @generated
	 */
	EReference getCGVariable_Init();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp <em>CG Variable Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CG Variable Exp</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp
	 * @generated
	 */
	EClass getCGVariableExp();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp#getReferredVariable <em>Referred Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referred Variable</em>'.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp#getReferredVariable()
	 * @see #getCGVariableExp()
	 * @generated
	 */
	EReference getCGVariableExp_ReferredVariable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.utilities.Nameable <em>Nameable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Nameable</em>'.
	 * @see org.eclipse.ocl.pivot.utilities.Nameable
	 * @generated
	 */
	EClass getNameable();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.pivot.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Element</em>'.
	 * @see org.eclipse.ocl.pivot.Element
	 * @generated
	 */
	EDataType getElement();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.pivot.ids.ElementId <em>Element Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Element Id</em>'.
	 * @see org.eclipse.ocl.pivot.ids.ElementId
	 * @generated
	 */
	EDataType getElementId();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.pivot.ids.EnumerationLiteralId <em>Enumeration Literal Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Enumeration Literal Id</em>'.
	 * @see org.eclipse.ocl.pivot.ids.EnumerationLiteralId
	 * @generated
	 */
	EDataType getEnumerationLiteralId();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.pivot.Iteration <em>Iteration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Iteration</em>'.
	 * @see org.eclipse.ocl.pivot.Iteration
	 * @generated
	 */
	EDataType getIteration();

	/**
	 * Returns the meta object for data type '{@link java.lang.reflect.Field <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Field</em>'.
	 * @see java.lang.reflect.Field
	 * @generated
	 */
	EDataType getField();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.pivot.library.LibraryIteration <em>Library Iteration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Library Iteration</em>'.
	 * @see org.eclipse.ocl.pivot.library.LibraryIteration
	 * @generated
	 */
	EDataType getLibraryIteration();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.pivot.library.LibraryOperation <em>Library Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Library Operation</em>'.
	 * @see org.eclipse.ocl.pivot.library.LibraryOperation
	 * @generated
	 */
	EDataType getLibraryOperation();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.pivot.library.LibraryProperty <em>Library Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Library Property</em>'.
	 * @see org.eclipse.ocl.pivot.library.LibraryProperty
	 * @generated
	 */
	EDataType getLibraryProperty();

	/**
	 * Returns the meta object for data type '{@link java.lang.reflect.Method <em>Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Method</em>'.
	 * @see java.lang.reflect.Method
	 * @generated
	 */
	EDataType getMethod();

	/**
	 * Returns the meta object for data type '{@link java.lang.Number <em>Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Number</em>'.
	 * @see java.lang.Number
	 * @generated
	 */
	EDataType getNumber();

	/**
	 * Returns the meta object for data type '{@link java.lang.Object <em>Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Object</em>'.
	 * @see java.lang.Object
	 * @generated
	 */
	EDataType getObject();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.pivot.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Operation</em>'.
	 * @see org.eclipse.ocl.pivot.Operation
	 * @generated
	 */
	EDataType getOperation();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.pivot.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Property</em>'.
	 * @see org.eclipse.ocl.pivot.Property
	 * @generated
	 */
	EDataType getProperty();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.pivot.ids.TuplePartId <em>Tuple Part Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Tuple Part Id</em>'.
	 * @see org.eclipse.ocl.pivot.ids.TuplePartId
	 * @generated
	 */
	EDataType getTuplePartId();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.pivot.Type <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Type</em>'.
	 * @see org.eclipse.ocl.pivot.Type
	 * @generated
	 */
	EDataType getType();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.pivot.ids.TypeId <em>Type Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Type Id</em>'.
	 * @see org.eclipse.ocl.pivot.ids.TypeId
	 * @generated
	 */
	EDataType getTypeId();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CGModelFactory getCGModelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGAccumulatorImpl <em>CG Accumulator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGAccumulatorImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGAccumulator()
		 * @generated
		 */
		EClass CG_ACCUMULATOR = eINSTANCE.getCGAccumulator();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGAssertNonNullExpImpl <em>CG Assert Non Null Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGAssertNonNullExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGAssertNonNullExp()
		 * @generated
		 */
		EClass CG_ASSERT_NON_NULL_EXP = eINSTANCE.getCGAssertNonNullExp();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGBooleanImpl <em>CG Boolean</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGBooleanImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGBoolean()
		 * @generated
		 */
		EClass CG_BOOLEAN = eINSTANCE.getCGBoolean();

		/**
		 * The meta object literal for the '<em><b>Boolean Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_BOOLEAN__BOOLEAN_VALUE = eINSTANCE.getCGBoolean_BooleanValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGBoxExpImpl <em>CG Box Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGBoxExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGBoxExp()
		 * @generated
		 */
		EClass CG_BOX_EXP = eINSTANCE.getCGBoxExp();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGBuiltInIterationCallExpImpl <em>CG Built In Iteration Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGBuiltInIterationCallExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGBuiltInIterationCallExp()
		 * @generated
		 */
		EClass CG_BUILT_IN_ITERATION_CALL_EXP = eINSTANCE.getCGBuiltInIterationCallExp();

		/**
		 * The meta object literal for the '<em><b>Accumulator</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_BUILT_IN_ITERATION_CALL_EXP__ACCUMULATOR = eINSTANCE.getCGBuiltInIterationCallExp_Accumulator();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGCachedOperationImpl <em>CG Cached Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGCachedOperationImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGCachedOperation()
		 * @generated
		 */
		EClass CG_CACHED_OPERATION = eINSTANCE.getCGCachedOperation();

		/**
		 * The meta object literal for the '<em><b>Final Operations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_CACHED_OPERATION__FINAL_OPERATIONS = eINSTANCE.getCGCachedOperation_FinalOperations();

		/**
		 * The meta object literal for the '<em><b>Virtual Operations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_CACHED_OPERATION__VIRTUAL_OPERATIONS = eINSTANCE.getCGCachedOperation_VirtualOperations();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGCachedOperationCallExpImpl <em>CG Cached Operation Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGCachedOperationCallExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGCachedOperationCallExp()
		 * @generated
		 */
		EClass CG_CACHED_OPERATION_CALL_EXP = eINSTANCE.getCGCachedOperationCallExp();

		/**
		 * The meta object literal for the '<em><b>Method</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_CACHED_OPERATION_CALL_EXP__METHOD = eINSTANCE.getCGCachedOperationCallExp_Method();

		/**
		 * The meta object literal for the '<em><b>This Is Self</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_CACHED_OPERATION_CALL_EXP__THIS_IS_SELF = eINSTANCE.getCGCachedOperationCallExp_ThisIsSelf();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGCallableImpl <em>CG Callable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGCallableImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGCallable()
		 * @generated
		 */
		EClass CG_CALLABLE = eINSTANCE.getCGCallable();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_CALLABLE__PARAMETERS = eINSTANCE.getCGCallable_Parameters();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_CALLABLE__BODY = eINSTANCE.getCGCallable_Body();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGClassImpl <em>CG Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGClassImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGClass()
		 * @generated
		 */
		EClass CG_CLASS = eINSTANCE.getCGClass();

		/**
		 * The meta object literal for the '<em><b>Operations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_CLASS__OPERATIONS = eINSTANCE.getCGClass_Operations();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_CLASS__PROPERTIES = eINSTANCE.getCGClass_Properties();

		/**
		 * The meta object literal for the '<em><b>Invariants</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_CLASS__INVARIANTS = eINSTANCE.getCGClass_Invariants();

		/**
		 * The meta object literal for the '<em><b>Super Types</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_CLASS__SUPER_TYPES = eINSTANCE.getCGClass_SuperTypes();

		/**
		 * The meta object literal for the '<em><b>Interface</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_CLASS__INTERFACE = eINSTANCE.getCGClass_Interface();

		/**
		 * The meta object literal for the '<em><b>Template Parameters</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_CLASS__TEMPLATE_PARAMETERS = eINSTANCE.getCGClass_TemplateParameters();

		/**
		 * The meta object literal for the '<em><b>Classes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_CLASS__CLASSES = eINSTANCE.getCGClass_Classes();

		/**
		 * The meta object literal for the '<em><b>Containing Class</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_CLASS__CONTAINING_CLASS = eINSTANCE.getCGClass_ContainingClass();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGCallExpImpl <em>CG Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGCallExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGCallExp()
		 * @generated
		 */
		EClass CG_CALL_EXP = eINSTANCE.getCGCallExp();

		/**
		 * The meta object literal for the '<em><b>Invalidating</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_CALL_EXP__INVALIDATING = eINSTANCE.getCGCallExp_Invalidating();

		/**
		 * The meta object literal for the '<em><b>Validating</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_CALL_EXP__VALIDATING = eINSTANCE.getCGCallExp_Validating();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_CALL_EXP__SOURCE = eINSTANCE.getCGCallExp_Source();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGCastExpImpl <em>CG Cast Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGCastExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGCastExp()
		 * @generated
		 */
		EClass CG_CAST_EXP = eINSTANCE.getCGCastExp();

		/**
		 * The meta object literal for the '<em><b>Executor Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_CAST_EXP__EXECUTOR_TYPE = eINSTANCE.getCGCastExp_ExecutorType();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGCatchExpImpl <em>CG Catch Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGCatchExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGCatchExp()
		 * @generated
		 */
		EClass CG_CATCH_EXP = eINSTANCE.getCGCatchExp();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGCollectionExpImpl <em>CG Collection Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGCollectionExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGCollectionExp()
		 * @generated
		 */
		EClass CG_COLLECTION_EXP = eINSTANCE.getCGCollectionExp();

		/**
		 * The meta object literal for the '<em><b>Parts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_COLLECTION_EXP__PARTS = eINSTANCE.getCGCollectionExp_Parts();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGCollectionPartImpl <em>CG Collection Part</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGCollectionPartImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGCollectionPart()
		 * @generated
		 */
		EClass CG_COLLECTION_PART = eINSTANCE.getCGCollectionPart();

		/**
		 * The meta object literal for the '<em><b>First</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_COLLECTION_PART__FIRST = eINSTANCE.getCGCollectionPart_First();

		/**
		 * The meta object literal for the '<em><b>Last</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_COLLECTION_PART__LAST = eINSTANCE.getCGCollectionPart_Last();

		/**
		 * The meta object literal for the '<em><b>Collection Exp</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_COLLECTION_PART__COLLECTION_EXP = eINSTANCE.getCGCollectionPart_CollectionExp();

		/**
		 * The meta object literal for the '<em><b>Containing Package</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_CLASS__CONTAINING_PACKAGE = eINSTANCE.getCGClass_ContainingPackage();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGConstantImpl <em>CG Constant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGConstantImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGConstant()
		 * @generated
		 */
		EClass CG_CONSTANT = eINSTANCE.getCGConstant();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGFinalVariableImpl <em>CG Final Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGFinalVariableImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGFinalVariable()
		 * @generated
		 */
		EClass CG_FINAL_VARIABLE = eINSTANCE.getCGFinalVariable();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGGuardExpImpl <em>CG Guard Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGGuardExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGGuardExp()
		 * @generated
		 */
		EClass CG_GUARD_EXP = eINSTANCE.getCGGuardExp();

		/**
		 * The meta object literal for the '<em><b>Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_GUARD_EXP__MESSAGE = eINSTANCE.getCGGuardExp_Message();

		/**
		 * The meta object literal for the '<em><b>Safe</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_GUARD_EXP__SAFE = eINSTANCE.getCGGuardExp_Safe();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGEcoreOperationCallExpImpl <em>CG Ecore Operation Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGEcoreOperationCallExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGEcoreOperationCallExp()
		 * @generated
		 */
		EClass CG_ECORE_OPERATION_CALL_EXP = eINSTANCE.getCGEcoreOperationCallExp();

		/**
		 * The meta object literal for the '<em><b>EOperation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_ECORE_OPERATION_CALL_EXP__EOPERATION = eINSTANCE.getCGEcoreOperationCallExp_EOperation();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGEcoreOppositePropertyCallExpImpl <em>CG Ecore Opposite Property Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGEcoreOppositePropertyCallExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGEcoreOppositePropertyCallExp()
		 * @generated
		 */
		EClass CG_ECORE_OPPOSITE_PROPERTY_CALL_EXP = eINSTANCE.getCGEcoreOppositePropertyCallExp();

		/**
		 * The meta object literal for the '<em><b>EStructural Feature</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_ECORE_OPPOSITE_PROPERTY_CALL_EXP__ESTRUCTURAL_FEATURE = eINSTANCE.getCGEcoreOppositePropertyCallExp_EStructuralFeature();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGEcorePropertyCallExpImpl <em>CG Ecore Property Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGEcorePropertyCallExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGEcorePropertyCallExp()
		 * @generated
		 */
		EClass CG_ECORE_PROPERTY_CALL_EXP = eINSTANCE.getCGEcorePropertyCallExp();

		/**
		 * The meta object literal for the '<em><b>EStructural Feature</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_ECORE_PROPERTY_CALL_EXP__ESTRUCTURAL_FEATURE = eINSTANCE.getCGEcorePropertyCallExp_EStructuralFeature();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGIntegerImpl <em>CG Integer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGIntegerImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGInteger()
		 * @generated
		 */
		EClass CG_INTEGER = eINSTANCE.getCGInteger();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGInvalidImpl <em>CG Invalid</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGInvalidImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGInvalid()
		 * @generated
		 */
		EClass CG_INVALID = eINSTANCE.getCGInvalid();

		/**
		 * The meta object literal for the '<em><b>Message Template</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_INVALID__MESSAGE_TEMPLATE = eINSTANCE.getCGInvalid_MessageTemplate();

		/**
		 * The meta object literal for the '<em><b>Bindings</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_INVALID__BINDINGS = eINSTANCE.getCGInvalid_Bindings();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGIsEqualExpImpl <em>CG Is Equal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGIsEqualExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGIsEqualExp()
		 * @generated
		 */
		EClass CG_IS_EQUAL_EXP = eINSTANCE.getCGIsEqualExp();

		/**
		 * The meta object literal for the '<em><b>Argument</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_IS_EQUAL_EXP__ARGUMENT = eINSTANCE.getCGIsEqualExp_Argument();

		/**
		 * The meta object literal for the '<em><b>Not Equals</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_IS_EQUAL_EXP__NOT_EQUALS = eINSTANCE.getCGIsEqualExp_NotEquals();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGIsEqual2ExpImpl <em>CG Is Equal2 Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGIsEqual2ExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGIsEqual2Exp()
		 * @generated
		 */
		EClass CG_IS_EQUAL2_EXP = eINSTANCE.getCGIsEqual2Exp();

		/**
		 * The meta object literal for the '<em><b>Argument</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_IS_EQUAL2_EXP__ARGUMENT = eINSTANCE.getCGIsEqual2Exp_Argument();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGIsInvalidExpImpl <em>CG Is Invalid Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGIsInvalidExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGIsInvalidExp()
		 * @generated
		 */
		EClass CG_IS_INVALID_EXP = eINSTANCE.getCGIsInvalidExp();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGIsKindOfExpImpl <em>CG Is Kind Of Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGIsKindOfExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGIsKindOfExp()
		 * @generated
		 */
		EClass CG_IS_KIND_OF_EXP = eINSTANCE.getCGIsKindOfExp();

		/**
		 * The meta object literal for the '<em><b>Executor Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_IS_KIND_OF_EXP__EXECUTOR_TYPE = eINSTANCE.getCGIsKindOfExp_ExecutorType();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGIsUndefinedExpImpl <em>CG Is Undefined Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGIsUndefinedExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGIsUndefinedExp()
		 * @generated
		 */
		EClass CG_IS_UNDEFINED_EXP = eINSTANCE.getCGIsUndefinedExp();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGIterationCallExpImpl <em>CG Iteration Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGIterationCallExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGIterationCallExp()
		 * @generated
		 */
		EClass CG_ITERATION_CALL_EXP = eINSTANCE.getCGIterationCallExp();

		/**
		 * The meta object literal for the '<em><b>Referred Iteration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_ITERATION_CALL_EXP__REFERRED_ITERATION = eINSTANCE.getCGIterationCallExp_ReferredIteration();

		/**
		 * The meta object literal for the '<em><b>Iterators</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_ITERATION_CALL_EXP__ITERATORS = eINSTANCE.getCGIterationCallExp_Iterators();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_ITERATION_CALL_EXP__BODY = eINSTANCE.getCGIterationCallExp_Body();

		/**
		 * The meta object literal for the '<em><b>Co Iterators</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_ITERATION_CALL_EXP__CO_ITERATORS = eINSTANCE.getCGIterationCallExp_CoIterators();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGIteratorImpl <em>CG Iterator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGIteratorImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGIterator()
		 * @generated
		 */
		EClass CG_ITERATOR = eINSTANCE.getCGIterator();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGLibraryOperationCallExpImpl <em>CG Library Operation Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGLibraryOperationCallExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGLibraryOperationCallExp()
		 * @generated
		 */
		EClass CG_LIBRARY_OPERATION_CALL_EXP = eINSTANCE.getCGLibraryOperationCallExp();

		/**
		 * The meta object literal for the '<em><b>Library Operation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_LIBRARY_OPERATION_CALL_EXP__LIBRARY_OPERATION = eINSTANCE.getCGLibraryOperationCallExp_LibraryOperation();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGLibraryPropertyCallExpImpl <em>CG Library Property Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGLibraryPropertyCallExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGLibraryPropertyCallExp()
		 * @generated
		 */
		EClass CG_LIBRARY_PROPERTY_CALL_EXP = eINSTANCE.getCGLibraryPropertyCallExp();

		/**
		 * The meta object literal for the '<em><b>Library Property</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_LIBRARY_PROPERTY_CALL_EXP__LIBRARY_PROPERTY = eINSTANCE.getCGLibraryPropertyCallExp_LibraryProperty();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGConstantExpImpl <em>CG Constant Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGConstantExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGConstantExp()
		 * @generated
		 */
		EClass CG_CONSTANT_EXP = eINSTANCE.getCGConstantExp();

		/**
		 * The meta object literal for the '<em><b>Referred Constant</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_CONSTANT_EXP__REFERRED_CONSTANT = eINSTANCE.getCGConstantExp_ReferredConstant();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGConstraintImpl <em>CG Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGConstraintImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGConstraint()
		 * @generated
		 */
		EClass CG_CONSTRAINT = eINSTANCE.getCGConstraint();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGShadowExpImpl <em>CG Shadow Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGShadowExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGShadowExp()
		 * @generated
		 */
		EClass CG_SHADOW_EXP = eINSTANCE.getCGShadowExp();

		/**
		 * The meta object literal for the '<em><b>Parts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_SHADOW_EXP__PARTS = eINSTANCE.getCGShadowExp_Parts();

		/**
		 * The meta object literal for the '<em><b>Executor Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_SHADOW_EXP__EXECUTOR_TYPE = eINSTANCE.getCGShadowExp_ExecutorType();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGShadowPartImpl <em>CG Shadow Part</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGShadowPartImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGShadowPart()
		 * @generated
		 */
		EClass CG_SHADOW_PART = eINSTANCE.getCGShadowPart();

		/**
		 * The meta object literal for the '<em><b>Init</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_SHADOW_PART__INIT = eINSTANCE.getCGShadowPart_Init();

		/**
		 * The meta object literal for the '<em><b>Shadow Exp</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_SHADOW_PART__SHADOW_EXP = eINSTANCE.getCGShadowPart_ShadowExp();

		/**
		 * The meta object literal for the '<em><b>Executor Part</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_SHADOW_PART__EXECUTOR_PART = eINSTANCE.getCGShadowPart_ExecutorPart();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGEcoreClassShadowExpImpl <em>CG Ecore Class Shadow Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGEcoreClassShadowExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGEcoreClassShadowExp()
		 * @generated
		 */
		EClass CG_ECORE_CLASS_SHADOW_EXP = eINSTANCE.getCGEcoreClassShadowExp();

		/**
		 * The meta object literal for the '<em><b>EClass</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_ECORE_CLASS_SHADOW_EXP__ECLASS = eINSTANCE.getCGEcoreClassShadowExp_EClass();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGEcoreDataTypeShadowExpImpl <em>CG Ecore Data Type Shadow Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGEcoreDataTypeShadowExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGEcoreDataTypeShadowExp()
		 * @generated
		 */
		EClass CG_ECORE_DATA_TYPE_SHADOW_EXP = eINSTANCE.getCGEcoreDataTypeShadowExp();

		/**
		 * The meta object literal for the '<em><b>EData Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_ECORE_DATA_TYPE_SHADOW_EXP__EDATA_TYPE = eINSTANCE.getCGEcoreDataTypeShadowExp_EDataType();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGEcoreExpImpl <em>CG Ecore Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGEcoreExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGEcoreExp()
		 * @generated
		 */
		EClass CG_ECORE_EXP = eINSTANCE.getCGEcoreExp();

		/**
		 * The meta object literal for the '<em><b>EClassifier</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_ECORE_EXP__ECLASSIFIER = eINSTANCE.getCGEcoreExp_EClassifier();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGEcoreOperationImpl <em>CG Ecore Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGEcoreOperationImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGEcoreOperation()
		 * @generated
		 */
		EClass CG_ECORE_OPERATION = eINSTANCE.getCGEcoreOperation();

		/**
		 * The meta object literal for the '<em><b>EOperation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_ECORE_OPERATION__EOPERATION = eINSTANCE.getCGEcoreOperation_EOperation();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGLetExpImpl <em>CG Let Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGLetExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGLetExp()
		 * @generated
		 */
		EClass CG_LET_EXP = eINSTANCE.getCGLetExp();

		/**
		 * The meta object literal for the '<em><b>Init</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_LET_EXP__INIT = eINSTANCE.getCGLetExp_Init();

		/**
		 * The meta object literal for the '<em><b>In</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_LET_EXP__IN = eINSTANCE.getCGLetExp_In();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGLibraryIterateCallExpImpl <em>CG Library Iterate Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGLibraryIterateCallExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGLibraryIterateCallExp()
		 * @generated
		 */
		EClass CG_LIBRARY_ITERATE_CALL_EXP = eINSTANCE.getCGLibraryIterateCallExp();

		/**
		 * The meta object literal for the '<em><b>Result</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_LIBRARY_ITERATE_CALL_EXP__RESULT = eINSTANCE.getCGLibraryIterateCallExp_Result();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGLibraryIterationCallExpImpl <em>CG Library Iteration Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGLibraryIterationCallExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGLibraryIterationCallExp()
		 * @generated
		 */
		EClass CG_LIBRARY_ITERATION_CALL_EXP = eINSTANCE.getCGLibraryIterationCallExp();

		/**
		 * The meta object literal for the '<em><b>Library Iteration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_LIBRARY_ITERATION_CALL_EXP__LIBRARY_ITERATION = eINSTANCE.getCGLibraryIterationCallExp_LibraryIteration();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGLibraryOperationImpl <em>CG Library Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGLibraryOperationImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGLibraryOperation()
		 * @generated
		 */
		EClass CG_LIBRARY_OPERATION = eINSTANCE.getCGLibraryOperation();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGLocalVariableImpl <em>CG Local Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGLocalVariableImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGLocalVariable()
		 * @generated
		 */
		EClass CG_LOCAL_VARIABLE = eINSTANCE.getCGLocalVariable();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGMapExpImpl <em>CG Map Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGMapExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGMapExp()
		 * @generated
		 */
		EClass CG_MAP_EXP = eINSTANCE.getCGMapExp();

		/**
		 * The meta object literal for the '<em><b>Parts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_MAP_EXP__PARTS = eINSTANCE.getCGMapExp_Parts();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGMapPartImpl <em>CG Map Part</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGMapPartImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGMapPart()
		 * @generated
		 */
		EClass CG_MAP_PART = eINSTANCE.getCGMapPart();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_MAP_PART__KEY = eINSTANCE.getCGMapPart_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_MAP_PART__VALUE = eINSTANCE.getCGMapPart_Value();

		/**
		 * The meta object literal for the '<em><b>Map Exp</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_MAP_PART__MAP_EXP = eINSTANCE.getCGMapPart_MapExp();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelImpl <em>CG Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGModel()
		 * @generated
		 */
		EClass CG_MODEL = eINSTANCE.getCGModel();

		/**
		 * The meta object literal for the '<em><b>Globals</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_MODEL__GLOBALS = eINSTANCE.getCGModel_Globals();

		/**
		 * The meta object literal for the '<em><b>Packages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_MODEL__PACKAGES = eINSTANCE.getCGModel_Packages();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGElementImpl <em>CG Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGElementImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGElement()
		 * @generated
		 */
		EClass CG_ELEMENT = eINSTANCE.getCGElement();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGElementIdImpl <em>CG Element Id</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGElementIdImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGElementId()
		 * @generated
		 */
		EClass CG_ELEMENT_ID = eINSTANCE.getCGElementId();

		/**
		 * The meta object literal for the '<em><b>Element Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_ELEMENT_ID__ELEMENT_ID = eINSTANCE.getCGElementId_ElementId();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGExecutorCompositionPropertyImpl <em>CG Executor Composition Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGExecutorCompositionPropertyImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGExecutorCompositionProperty()
		 * @generated
		 */
		EClass CG_EXECUTOR_COMPOSITION_PROPERTY = eINSTANCE.getCGExecutorCompositionProperty();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGExecutorNavigationPropertyImpl <em>CG Executor Navigation Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGExecutorNavigationPropertyImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGExecutorNavigationProperty()
		 * @generated
		 */
		EClass CG_EXECUTOR_NAVIGATION_PROPERTY = eINSTANCE.getCGExecutorNavigationProperty();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGExecutorOppositePropertyImpl <em>CG Executor Opposite Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGExecutorOppositePropertyImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGExecutorOppositeProperty()
		 * @generated
		 */
		EClass CG_EXECUTOR_OPPOSITE_PROPERTY = eINSTANCE.getCGExecutorOppositeProperty();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGExecutorOperationImpl <em>CG Executor Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGExecutorOperationImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGExecutorOperation()
		 * @generated
		 */
		EClass CG_EXECUTOR_OPERATION = eINSTANCE.getCGExecutorOperation();

		/**
		 * The meta object literal for the '<em><b>Underlying Operation Id</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_EXECUTOR_OPERATION__UNDERLYING_OPERATION_ID = eINSTANCE.getCGExecutorOperation_UnderlyingOperationId();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGExecutorOperationCallExpImpl <em>CG Executor Operation Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGExecutorOperationCallExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGExecutorOperationCallExp()
		 * @generated
		 */
		EClass CG_EXECUTOR_OPERATION_CALL_EXP = eINSTANCE.getCGExecutorOperationCallExp();

		/**
		 * The meta object literal for the '<em><b>Executor Operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_EXECUTOR_OPERATION_CALL_EXP__EXECUTOR_OPERATION = eINSTANCE.getCGExecutorOperationCallExp_ExecutorOperation();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGExecutorOppositePropertyCallExpImpl <em>CG Executor Opposite Property Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGExecutorOppositePropertyCallExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGExecutorOppositePropertyCallExp()
		 * @generated
		 */
		EClass CG_EXECUTOR_OPPOSITE_PROPERTY_CALL_EXP = eINSTANCE.getCGExecutorOppositePropertyCallExp();

		/**
		 * The meta object literal for the '<em><b>Executor Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_EXECUTOR_OPPOSITE_PROPERTY_CALL_EXP__EXECUTOR_PROPERTY = eINSTANCE.getCGExecutorOppositePropertyCallExp_ExecutorProperty();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGExecutorPropertyImpl <em>CG Executor Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGExecutorPropertyImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGExecutorProperty()
		 * @generated
		 */
		EClass CG_EXECUTOR_PROPERTY = eINSTANCE.getCGExecutorProperty();

		/**
		 * The meta object literal for the '<em><b>Underlying Property Id</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_EXECUTOR_PROPERTY__UNDERLYING_PROPERTY_ID = eINSTANCE.getCGExecutorProperty_UnderlyingPropertyId();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGExecutorPropertyCallExpImpl <em>CG Executor Property Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGExecutorPropertyCallExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGExecutorPropertyCallExp()
		 * @generated
		 */
		EClass CG_EXECUTOR_PROPERTY_CALL_EXP = eINSTANCE.getCGExecutorPropertyCallExp();

		/**
		 * The meta object literal for the '<em><b>Executor Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_EXECUTOR_PROPERTY_CALL_EXP__EXECUTOR_PROPERTY = eINSTANCE.getCGExecutorPropertyCallExp_ExecutorProperty();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGExecutorShadowPartImpl <em>CG Executor Shadow Part</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGExecutorShadowPartImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGExecutorShadowPart()
		 * @generated
		 */
		EClass CG_EXECUTOR_SHADOW_PART = eINSTANCE.getCGExecutorShadowPart();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGExecutorTypeImpl <em>CG Executor Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGExecutorTypeImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGExecutorType()
		 * @generated
		 */
		EClass CG_EXECUTOR_TYPE = eINSTANCE.getCGExecutorType();

		/**
		 * The meta object literal for the '<em><b>Underlying Type Id</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_EXECUTOR_TYPE__UNDERLYING_TYPE_ID = eINSTANCE.getCGExecutorType_UnderlyingTypeId();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGIfExpImpl <em>CG If Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGIfExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGIfExp()
		 * @generated
		 */
		EClass CG_IF_EXP = eINSTANCE.getCGIfExp();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_IF_EXP__CONDITION = eINSTANCE.getCGIfExp_Condition();

		/**
		 * The meta object literal for the '<em><b>Then Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_IF_EXP__THEN_EXPRESSION = eINSTANCE.getCGIfExp_ThenExpression();

		/**
		 * The meta object literal for the '<em><b>Else Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_IF_EXP__ELSE_EXPRESSION = eINSTANCE.getCGIfExp_ElseExpression();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGNamedElementImpl <em>CG Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGNamedElementImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGNamedElement()
		 * @generated
		 */
		EClass CG_NAMED_ELEMENT = eINSTANCE.getCGNamedElement();

		/**
		 * The meta object literal for the '<em><b>Ast</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_NAMED_ELEMENT__AST = eINSTANCE.getCGNamedElement_Ast();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_NAMED_ELEMENT__NAME = eINSTANCE.getCGNamedElement_Name();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGNativeOperationImpl <em>CG Native Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGNativeOperationImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGNativeOperation()
		 * @generated
		 */
		EClass CG_NATIVE_OPERATION = eINSTANCE.getCGNativeOperation();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGNativeOperationCallExpImpl <em>CG Native Operation Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGNativeOperationCallExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGNativeOperationCallExp()
		 * @generated
		 */
		EClass CG_NATIVE_OPERATION_CALL_EXP = eINSTANCE.getCGNativeOperationCallExp();

		/**
		 * The meta object literal for the '<em><b>Method</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_NATIVE_OPERATION_CALL_EXP__METHOD = eINSTANCE.getCGNativeOperationCallExp_Method();

		/**
		 * The meta object literal for the '<em><b>This Is Self</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_NATIVE_OPERATION_CALL_EXP__THIS_IS_SELF = eINSTANCE.getCGNativeOperationCallExp_ThisIsSelf();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGNativePropertyImpl <em>CG Native Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGNativePropertyImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGNativeProperty()
		 * @generated
		 */
		EClass CG_NATIVE_PROPERTY = eINSTANCE.getCGNativeProperty();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGNativePropertyCallExpImpl <em>CG Native Property Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGNativePropertyCallExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGNativePropertyCallExp()
		 * @generated
		 */
		EClass CG_NATIVE_PROPERTY_CALL_EXP = eINSTANCE.getCGNativePropertyCallExp();

		/**
		 * The meta object literal for the '<em><b>Field</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_NATIVE_PROPERTY_CALL_EXP__FIELD = eINSTANCE.getCGNativePropertyCallExp_Field();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGNavigationCallExpImpl <em>CG Navigation Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGNavigationCallExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGNavigationCallExp()
		 * @generated
		 */
		EClass CG_NAVIGATION_CALL_EXP = eINSTANCE.getCGNavigationCallExp();

		/**
		 * The meta object literal for the '<em><b>Referred Property</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_NAVIGATION_CALL_EXP__REFERRED_PROPERTY = eINSTANCE.getCGNavigationCallExp_ReferredProperty();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGNullImpl <em>CG Null</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGNullImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGNull()
		 * @generated
		 */
		EClass CG_NULL = eINSTANCE.getCGNull();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGNumberImpl <em>CG Number</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGNumberImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGNumber()
		 * @generated
		 */
		EClass CG_NUMBER = eINSTANCE.getCGNumber();

		/**
		 * The meta object literal for the '<em><b>Numeric Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_NUMBER__NUMERIC_VALUE = eINSTANCE.getCGNumber_NumericValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGOperationImpl <em>CG Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGOperationImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGOperation()
		 * @generated
		 */
		EClass CG_OPERATION = eINSTANCE.getCGOperation();

		/**
		 * The meta object literal for the '<em><b>Preconditions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_OPERATION__PRECONDITIONS = eINSTANCE.getCGOperation_Preconditions();

		/**
		 * The meta object literal for the '<em><b>Postconditions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_OPERATION__POSTCONDITIONS = eINSTANCE.getCGOperation_Postconditions();

		/**
		 * The meta object literal for the '<em><b>Containing Class</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_OPERATION__CONTAINING_CLASS = eINSTANCE.getCGOperation_ContainingClass();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGOperationCallExpImpl <em>CG Operation Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGOperationCallExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGOperationCallExp()
		 * @generated
		 */
		EClass CG_OPERATION_CALL_EXP = eINSTANCE.getCGOperationCallExp();

		/**
		 * The meta object literal for the '<em><b>Referred Operation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_OPERATION_CALL_EXP__REFERRED_OPERATION = eINSTANCE.getCGOperationCallExp_ReferredOperation();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGOppositePropertyCallExpImpl <em>CG Opposite Property Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGOppositePropertyCallExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGOppositePropertyCallExp()
		 * @generated
		 */
		EClass CG_OPPOSITE_PROPERTY_CALL_EXP = eINSTANCE.getCGOppositePropertyCallExp();

		/**
		 * The meta object literal for the '<em><b>Arguments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_OPERATION_CALL_EXP__ARGUMENTS = eINSTANCE.getCGOperationCallExp_Arguments();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGPackageImpl <em>CG Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGPackageImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGPackage()
		 * @generated
		 */
		EClass CG_PACKAGE = eINSTANCE.getCGPackage();

		/**
		 * The meta object literal for the '<em><b>Classes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_PACKAGE__CLASSES = eINSTANCE.getCGPackage_Classes();

		/**
		 * The meta object literal for the '<em><b>Packages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_PACKAGE__PACKAGES = eINSTANCE.getCGPackage_Packages();

		/**
		 * The meta object literal for the '<em><b>Containing Package</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_PACKAGE__CONTAINING_PACKAGE = eINSTANCE.getCGPackage_ContainingPackage();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGParameterImpl <em>CG Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGParameterImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGParameter()
		 * @generated
		 */
		EClass CG_PARAMETER = eINSTANCE.getCGParameter();

		/**
		 * The meta object literal for the '<em><b>Callable</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_PARAMETER__CALLABLE = eINSTANCE.getCGParameter_Callable();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGPropertyImpl <em>CG Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGPropertyImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGProperty()
		 * @generated
		 */
		EClass CG_PROPERTY = eINSTANCE.getCGProperty();

		/**
		 * The meta object literal for the '<em><b>Containing Class</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_PROPERTY__CONTAINING_CLASS = eINSTANCE.getCGProperty_ContainingClass();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_PROPERTY__BODY = eINSTANCE.getCGProperty_Body();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGPropertyCallExpImpl <em>CG Property Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGPropertyCallExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGPropertyCallExp()
		 * @generated
		 */
		EClass CG_PROPERTY_CALL_EXP = eINSTANCE.getCGPropertyCallExp();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGRealImpl <em>CG Real</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGRealImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGReal()
		 * @generated
		 */
		EClass CG_REAL = eINSTANCE.getCGReal();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGSettableVariableImpl <em>CG Settable Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGSettableVariableImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGSettableVariable()
		 * @generated
		 */
		EClass CG_SETTABLE_VARIABLE = eINSTANCE.getCGSettableVariable();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGStringImpl <em>CG String</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGStringImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGString()
		 * @generated
		 */
		EClass CG_STRING = eINSTANCE.getCGString();

		/**
		 * The meta object literal for the '<em><b>String Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_STRING__STRING_VALUE = eINSTANCE.getCGString_StringValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGTemplateParameterExpImpl <em>CG Template Parameter Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGTemplateParameterExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGTemplateParameterExp()
		 * @generated
		 */
		EClass CG_TEMPLATE_PARAMETER_EXP = eINSTANCE.getCGTemplateParameterExp();

		/**
		 * The meta object literal for the '<em><b>Templateable Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_TEMPLATE_PARAMETER_EXP__TEMPLATEABLE_ELEMENT = eINSTANCE.getCGTemplateParameterExp_TemplateableElement();

		/**
		 * The meta object literal for the '<em><b>Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_TEMPLATE_PARAMETER_EXP__INDEX = eINSTANCE.getCGTemplateParameterExp_Index();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGTextImpl <em>CG Text</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGTextImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGText()
		 * @generated
		 */
		EClass CG_TEXT = eINSTANCE.getCGText();

		/**
		 * The meta object literal for the '<em><b>Text Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_TEXT__TEXT_VALUE = eINSTANCE.getCGText_TextValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGThrowExpImpl <em>CG Throw Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGThrowExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGThrowExp()
		 * @generated
		 */
		EClass CG_THROW_EXP = eINSTANCE.getCGThrowExp();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGTupleExpImpl <em>CG Tuple Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGTupleExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGTupleExp()
		 * @generated
		 */
		EClass CG_TUPLE_EXP = eINSTANCE.getCGTupleExp();

		/**
		 * The meta object literal for the '<em><b>Parts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_TUPLE_EXP__PARTS = eINSTANCE.getCGTupleExp_Parts();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGTuplePartImpl <em>CG Tuple Part</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGTuplePartImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGTuplePart()
		 * @generated
		 */
		EClass CG_TUPLE_PART = eINSTANCE.getCGTuplePart();

		/**
		 * The meta object literal for the '<em><b>Init</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_TUPLE_PART__INIT = eINSTANCE.getCGTuplePart_Init();

		/**
		 * The meta object literal for the '<em><b>Tuple Exp</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_TUPLE_PART__TUPLE_EXP = eINSTANCE.getCGTuplePart_TupleExp();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGTuplePartCallExpImpl <em>CG Tuple Part Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGTuplePartCallExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGTuplePartCallExp()
		 * @generated
		 */
		EClass CG_TUPLE_PART_CALL_EXP = eINSTANCE.getCGTuplePartCallExp();

		/**
		 * The meta object literal for the '<em><b>Ast Tuple Part Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_TUPLE_PART_CALL_EXP__AST_TUPLE_PART_ID = eINSTANCE.getCGTuplePartCallExp_AstTuplePartId();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGTypeIdImpl <em>CG Type Id</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGTypeIdImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGTypeId()
		 * @generated
		 */
		EClass CG_TYPE_ID = eINSTANCE.getCGTypeId();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGTypeExpImpl <em>CG Type Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGTypeExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGTypeExp()
		 * @generated
		 */
		EClass CG_TYPE_EXP = eINSTANCE.getCGTypeExp();

		/**
		 * The meta object literal for the '<em><b>Executor Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_TYPE_EXP__EXECUTOR_TYPE = eINSTANCE.getCGTypeExp_ExecutorType();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGTypedElementImpl <em>CG Typed Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGTypedElementImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGTypedElement()
		 * @generated
		 */
		EClass CG_TYPED_ELEMENT = eINSTANCE.getCGTypedElement();

		/**
		 * The meta object literal for the '<em><b>Type Id</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_TYPED_ELEMENT__TYPE_ID = eINSTANCE.getCGTypedElement_TypeId();

		/**
		 * The meta object literal for the '<em><b>Required</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CG_TYPED_ELEMENT__REQUIRED = eINSTANCE.getCGTypedElement_Required();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGUnboxExpImpl <em>CG Unbox Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGUnboxExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGUnboxExp()
		 * @generated
		 */
		EClass CG_UNBOX_EXP = eINSTANCE.getCGUnboxExp();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGUnlimitedImpl <em>CG Unlimited</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGUnlimitedImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGUnlimited()
		 * @generated
		 */
		EClass CG_UNLIMITED = eINSTANCE.getCGUnlimited();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGValuedElementImpl <em>CG Valued Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGValuedElementImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGValuedElement()
		 * @generated
		 */
		EClass CG_VALUED_ELEMENT = eINSTANCE.getCGValuedElement();

		/**
		 * The meta object literal for the '<em><b>Depends On</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_VALUED_ELEMENT__DEPENDS_ON = eINSTANCE.getCGValuedElement_DependsOn();

		/**
		 * The meta object literal for the '<em><b>Owns</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_VALUED_ELEMENT__OWNS = eINSTANCE.getCGValuedElement_Owns();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGVariableImpl <em>CG Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGVariableImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGVariable()
		 * @generated
		 */
		EClass CG_VARIABLE = eINSTANCE.getCGVariable();

		/**
		 * The meta object literal for the '<em><b>Init</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_VARIABLE__INIT = eINSTANCE.getCGVariable_Init();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGVariableExpImpl <em>CG Variable Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGVariableExpImpl
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getCGVariableExp()
		 * @generated
		 */
		EClass CG_VARIABLE_EXP = eINSTANCE.getCGVariableExp();

		/**
		 * The meta object literal for the '<em><b>Referred Variable</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CG_VARIABLE_EXP__REFERRED_VARIABLE = eINSTANCE.getCGVariableExp_ReferredVariable();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.utilities.Nameable <em>Nameable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.utilities.Nameable
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getNameable()
		 * @generated
		 */
		EClass NAMEABLE = eINSTANCE.getNameable();

		/**
		 * The meta object literal for the '<em>Element</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.Element
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getElement()
		 * @generated
		 */
		EDataType ELEMENT = eINSTANCE.getElement();

		/**
		 * The meta object literal for the '<em>Element Id</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.ids.ElementId
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getElementId()
		 * @generated
		 */
		EDataType ELEMENT_ID = eINSTANCE.getElementId();

		/**
		 * The meta object literal for the '<em>Enumeration Literal Id</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.ids.EnumerationLiteralId
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getEnumerationLiteralId()
		 * @generated
		 */
		EDataType ENUMERATION_LITERAL_ID = eINSTANCE.getEnumerationLiteralId();

		/**
		 * The meta object literal for the '<em>Iteration</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.Iteration
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getIteration()
		 * @generated
		 */
		EDataType ITERATION = eINSTANCE.getIteration();

		/**
		 * The meta object literal for the '<em>Field</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.reflect.Field
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getField()
		 * @generated
		 */
		EDataType FIELD = eINSTANCE.getField();

		/**
		 * The meta object literal for the '<em>Library Iteration</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.library.LibraryIteration
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getLibraryIteration()
		 * @generated
		 */
		EDataType LIBRARY_ITERATION = eINSTANCE.getLibraryIteration();

		/**
		 * The meta object literal for the '<em>Library Operation</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.library.LibraryOperation
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getLibraryOperation()
		 * @generated
		 */
		EDataType LIBRARY_OPERATION = eINSTANCE.getLibraryOperation();

		/**
		 * The meta object literal for the '<em>Library Property</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.library.LibraryProperty
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getLibraryProperty()
		 * @generated
		 */
		EDataType LIBRARY_PROPERTY = eINSTANCE.getLibraryProperty();

		/**
		 * The meta object literal for the '<em>Method</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.reflect.Method
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getMethod()
		 * @generated
		 */
		EDataType METHOD = eINSTANCE.getMethod();

		/**
		 * The meta object literal for the '<em>Number</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Number
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getNumber()
		 * @generated
		 */
		EDataType NUMBER = eINSTANCE.getNumber();

		/**
		 * The meta object literal for the '<em>Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Object
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getObject()
		 * @generated
		 */
		EDataType OBJECT = eINSTANCE.getObject();

		/**
		 * The meta object literal for the '<em>Operation</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.Operation
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getOperation()
		 * @generated
		 */
		EDataType OPERATION = eINSTANCE.getOperation();

		/**
		 * The meta object literal for the '<em>Property</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.Property
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getProperty()
		 * @generated
		 */
		EDataType PROPERTY = eINSTANCE.getProperty();

		/**
		 * The meta object literal for the '<em>Tuple Part Id</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.ids.TuplePartId
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getTuplePartId()
		 * @generated
		 */
		EDataType TUPLE_PART_ID = eINSTANCE.getTuplePartId();

		/**
		 * The meta object literal for the '<em>Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.Type
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getType()
		 * @generated
		 */
		EDataType TYPE = eINSTANCE.getType();

		/**
		 * The meta object literal for the '<em>Type Id</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.ids.TypeId
		 * @see org.eclipse.ocl.examples.codegen.cgmodel.impl.CGModelPackageImpl#getTypeId()
		 * @generated
		 */
		EDataType TYPE_ID = eINSTANCE.getTypeId();

	}

} //CGModelPackage
