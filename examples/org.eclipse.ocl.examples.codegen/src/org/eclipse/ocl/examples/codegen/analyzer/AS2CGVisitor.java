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
package org.eclipse.ocl.examples.codegen.analyzer;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.NestedNameManager.JavaLocalContext;
import org.eclipse.ocl.examples.codegen.calling.ExecutorShadowPartCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.OperationCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.PropertyCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.SupportOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.cgmodel.CGAccumulator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstant;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreClassShadowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreDataTypeShadowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorShadowPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGFinalVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIfExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInteger;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqual2Exp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLetExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterateCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGMapExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGMapPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGReal;
import org.eclipse.ocl.examples.codegen.cgmodel.CGShadowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGShadowPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGString;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTemplateParameterExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTupleExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.generator.AbstractCodeGenerator;
import org.eclipse.ocl.examples.codegen.generator.GenModelHelper;
import org.eclipse.ocl.examples.codegen.generator.IterationHelper;
import org.eclipse.ocl.examples.codegen.generator.LocalContext;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.types.JavaTypeId;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.BooleanLiteralExp;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.CollectionItem;
import org.eclipse.ocl.pivot.CollectionLiteralExp;
import org.eclipse.ocl.pivot.CollectionLiteralPart;
import org.eclipse.ocl.pivot.CollectionRange;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.EnumLiteralExp;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.IntegerLiteralExp;
import org.eclipse.ocl.pivot.InvalidLiteralExp;
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.MapLiteralExp;
import org.eclipse.ocl.pivot.MapLiteralPart;
import org.eclipse.ocl.pivot.NullLiteralExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.OppositePropertyCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.RealLiteralExp;
import org.eclipse.ocl.pivot.ShadowExp;
import org.eclipse.ocl.pivot.ShadowPart;
import org.eclipse.ocl.pivot.StateExp;
import org.eclipse.ocl.pivot.StringLiteralExp;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralPart;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypeExp;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.UnlimitedNaturalLiteralExp;
import org.eclipse.ocl.pivot.UnspecifiedValueExp;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.ids.TuplePartId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
import org.eclipse.ocl.pivot.internal.manager.FinalAnalysis;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.library.LibraryIteration;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.library.LibraryProperty;
import org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.RealValue;
import org.eclipse.ocl.pivot.values.Unlimited;
import org.eclipse.ocl.pivot.values.UnlimitedValue;

/**
 * The AS2CGVisitor performs the first stage of code generation by converting the Pivot AST to the CG AST.
 */
public class AS2CGVisitor extends AbstractExtendingVisitor<@Nullable CGNamedElement, @NonNull JavaCodeGenerator>
{
	protected final @NonNull EnvironmentFactoryInternalExtension environmentFactory;
	protected final @NonNull GlobalNameManager globalNameManager;
	protected final @NonNull CodeGenAnalyzer analyzer;
	protected final @NonNull PivotMetamodelManager metamodelManager;
	protected final @NonNull GenModelHelper genModelHelper;

	private @Nullable LocalContext localContext = null;		// The current context

	public static final class CGTuplePartNameComparator implements Comparator<@NonNull CGTuplePart>
	{
		public static final @NonNull CGTuplePartNameComparator INSTANCE = new CGTuplePartNameComparator();

		@Override
		public int compare(@NonNull CGTuplePart o1, @NonNull CGTuplePart o2) {
			return ClassUtil.safeCompareTo(CGUtil.getAST(o1).getName(), CGUtil.getAST(o2).getName());
		}
	}

	protected static class Variables
	{
		private @Nullable Variables outerVariables;
		/*
		 * The AS to CG parameter map assists in construction of ExpressionInOcl before/without an Operation.
		 */
		private final @NonNull Map<@NonNull VariableDeclaration, @NonNull CGParameter> cgParameters = new HashMap<>();

		private @NonNull Map<@NonNull VariableDeclaration, @NonNull CGVariable> cgVariables = new HashMap<>();

		public Variables(@Nullable Variables outerVariables) {
			this.outerVariables = outerVariables;
		}

		public CGVariable getLocalVariable(@NonNull VariableDeclaration asVariable) {
			return cgVariables.get(asVariable);
		}

		public CGParameter getParameter(@NonNull VariableDeclaration asVariable) {
			CGParameter cgVariable = cgParameters.get(asVariable);
			if (cgVariable != null) {
				return cgVariable;
			}
			else if (outerVariables != null) {
				return outerVariables.getParameter(asVariable);
			}
			else {
				return null;
			}
		}

		public CGVariable getVariable(@NonNull VariableDeclaration asVariable) {
			CGVariable cgVariable = cgVariables.get(asVariable);
			if (cgVariable != null) {
				return cgVariable;
			}
			else if (outerVariables != null) {
				return outerVariables.getVariable(asVariable);
			}
			else {
				return null;
			}
		}

		public void putParameter(@NonNull VariableDeclaration asParameter, @NonNull CGParameter cgParameter) {
		//	System.out.println("putParameter: " + asParameter + " in " + asParameter.eContainer() + " => " + NameUtil.debugSimpleName(cgParameter));
			cgParameters.put(asParameter, cgParameter);
			cgVariables.put(asParameter, cgParameter);
		}

		public void putVariable(@NonNull VariableDeclaration asVariable, @NonNull CGVariable cgVariable) {
			cgVariables.put(asVariable, cgVariable);
		}
	}

	/**
	 * Mapping from an AS Variable to a CG Variable, maintained as a stack that is pushed when inline operations are expanded.
	 */
	private @NonNull Variables variablesStack = new Variables(null);	// XXX this is the only remaining state in AS2CG. Move to ?? LocalContext

	public AS2CGVisitor(@NonNull JavaCodeGenerator codeGenerator) {
		super(codeGenerator);
		environmentFactory = (EnvironmentFactoryInternalExtension) codeGenerator.getEnvironmentFactory();
		metamodelManager = environmentFactory.getMetamodelManager();
		analyzer = codeGenerator.getAnalyzer();
		globalNameManager = codeGenerator.getGlobalNameManager();
		genModelHelper = codeGenerator.getGenModelHelper();
	}

	protected void addParameter(@NonNull VariableDeclaration asVariable, @NonNull CGParameter cgParameter) {
		variablesStack.putParameter(asVariable, cgParameter);
	}

	public @Nullable CGClass basicGetCurrentClass() {
		return localContext != null ? localContext.getNameManager().findCGScope() : null;
	}

	public @Nullable CGVariable basicGetParameter(@NonNull Variable aParameter) {
		return variablesStack.getParameter(aParameter);
	}

	public @NonNull CGNativeOperationCallExp createCGBoxedNativeOperationCallExp(@Nullable CGValuedElement cgThis, @NonNull Method jMethod, @NonNull CGValuedElement... cgArguments) {
		CGNativeOperationCallExp cgCallExp = analyzer.createCGNativeOperationCallExp(jMethod, SupportOperationCallingConvention.INSTANCE);
		cgCallExp.setCgThis(cgThis);
		if (cgArguments != null) {
			List<CGValuedElement> cgArguments2 = cgCallExp.getCgArguments();
			for (@NonNull CGValuedElement cgArgument : cgArguments) {
				cgArguments2.add(cgArgument);
			}
		}
		cgCallExp.setRequired(context.getIsNonNull(jMethod) == Boolean.TRUE);
	//	cgCallExp.setInvalidating(false));
		Class<?> jReturnType = jMethod.getReturnType();
		assert jReturnType != null;
		cgCallExp.setTypeId(analyzer.getCGTypeId(new JavaTypeId(jReturnType)));		// XXX cache
		return cgCallExp;
	}

	public @NonNull CGFinalVariable createCGFinalVariable(@NonNull CGValuedElement cgInit) {
		NestedNameManager nameManager = getNameManager();
		NameResolution nameResolution = nameManager.getNameResolution(cgInit);
		CGFinalVariable cgVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		cgVariable.setAst(cgInit.getAst());
		cgVariable.setTypeId(cgInit.getTypeId());
		cgVariable.setInit(cgInit);
		nameResolution.addCGElement(cgVariable);
		return cgVariable;
	}

	protected @NonNull CGLetExp createCGLetExp(@NonNull TypedElement asElement, @NonNull CGFinalVariable cgVariable, @NonNull CGValuedElement cgIn) {
		CGLetExp cgLetExp = CGModelFactory.eINSTANCE.createCGLetExp();
		cgLetExp.setInit(cgVariable);
		cgLetExp.setIn(cgIn);
		initAst(cgLetExp, asElement);
		return cgLetExp;
	}

	public @NonNull CGVariable createCGVariable(@NonNull VariableDeclaration asVariable) {
		CGVariable cgVariable = variablesStack.getVariable(asVariable);
		if (cgVariable == null) {
			CGFinalVariable cgVariable2 = CGModelFactory.eINSTANCE.createCGFinalVariable();
			cgVariable = cgVariable2;
			variablesStack.putVariable(asVariable, cgVariable);
		}
		else {
			assert cgVariable.eContainer() == null;
		}
		initAst(cgVariable, asVariable);
		getNameManager().declarePreferredName(cgVariable);
		return cgVariable;
	}

	protected @NonNull CGVariable createCGVariable(@NonNull Variable contextVariable, @NonNull OCLExpression source) {
		CGVariable cgVariable = createCGVariable(contextVariable);
		CGValuedElement cgInit = doVisit(CGValuedElement.class, source);
		setCGVariableInit(cgVariable, cgInit);
		return cgVariable;
	}

	public @NonNull CGVariableExp createCGVariableExp(@NonNull VariableExp asVariableExp) {
		VariableDeclaration asVariable = PivotUtil.getReferredVariable(asVariableExp);
		CGVariableExp cgVariableExp = CGModelFactory.eINSTANCE.createCGVariableExp();
		initAst(cgVariableExp, asVariableExp);

		CGVariable cgVariable;
		if ((asVariable instanceof Parameter) && isThis((Parameter)asVariable)) {
			JavaLocalContext localContext = globalNameManager.findLocalContext(getCurrentClass());
			if (isQualifiedThis(asVariableExp, (Parameter)asVariable)) {
				cgVariable = localContext.getQualifiedThisVariable();
			}
			else {
				cgVariable = localContext.getThisParameter();
			}
		}
		else {
			cgVariable = getVariable(asVariable);
		}
		cgVariableExp.setReferredVariable(cgVariable);
	//	cgVariable.getNameResolution().addCGElement(cgVariableExp);
		return cgVariableExp;
	}

	protected void createParameters(@NonNull CGOperation cgOperation, @NonNull ExpressionInOCL expressionInOCL) {
		Variable contextVariable = expressionInOCL.getOwnedContext();
		if (contextVariable != null) {
			CGParameter cgParameter = getSelfParameter(contextVariable);
			//			cgParameter.setTypeId(analyzer.getTypeId(JavaConstants.getJavaTypeId(Object.class)));
			//			cgParameter.setRequired(contextVariable.isIsRequired());
			cgOperation.getParameters().add(cgParameter);
		}
		for (@NonNull Variable parameterVariable : ClassUtil.nullFree(expressionInOCL.getOwnedParameters())) {
			CGParameter cgParameter;
			if (cgOperation instanceof CGEcoreOperation) {
				cgParameter = getParameter(parameterVariable, parameterVariable.getName());
			}
			else {
				cgParameter = getParameter(parameterVariable, (String)null);
			}
			//			cgParameter.setTypeId(analyzer.getTypeId(JavaConstants.getJavaTypeId(Object.class)));
			//			cgParameter.setRequired(parameterVariable.isIsRequired());
			cgOperation.getParameters().add(cgParameter);
		}
	}

	/*protected public @NonNull CGOperation createVirtualCGOperationWithoutBody(@NonNull Operation asOperation, @NonNull List<@NonNull CGCachedOperation> cgOperations) {
		CGCachedOperation cgOperation = CGModelFactory.eINSTANCE.createCGCachedOperation();
		initAst(cgOperation, asOperation);
		LocalContext savedLocalContext = pushLocalContext(cgOperation, asOperation);
		cgOperation.setRequired(asOperation.isIsRequired());
		LanguageExpression specification = asOperation.getBodyExpression();
		if (specification != null) {
			Variables savedVariablesStack = variablesStack;
			try {
				ExpressionInOCL query = environmentFactory.parseSpecification(specification);
				variablesStack = new Variables(null);
				createParameters(cgOperation, query);
			} catch (ParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				variablesStack = savedVariablesStack;
			}
		}
		cgOperation.getFinalOperations().addAll(cgOperations);
		analyzer.addVirtualCGOperation(asOperation, cgOperation);
		popLocalContext(savedLocalContext);
		return cgOperation;
	} */

	public @NonNull <T extends CGElement> T doVisit(@NonNull Class<T> requiredClass, @Nullable Element asElement) {
		if (asElement == null) {
			throw new NullPointerException("null source for mapping to " + requiredClass.getName());
		}
		CGNamedElement cgElement = asElement.accept(this);
		if (cgElement == null) {
			throw new NullPointerException("null result of mapping to " + requiredClass.getName());
		}
		Class<? extends CGNamedElement> actualClass = cgElement.getClass();
		if (!requiredClass.isAssignableFrom(actualClass)) {
			throw new ClassCastException("cannot cast " + actualClass.getName() + " result of mapping to " + requiredClass.getName());
		}
		@SuppressWarnings("unchecked") T cgElement2 = (T) cgElement;
		return cgElement2;
	}

	/**
	 * Perform any actions / checks necessary once the visit is done.
	 */
	public void freeze() {
		assert localContext == null;
	}

	protected @NonNull CGIterationCallExp generateLoopExp(@NonNull CGValuedElement cgSource, @NonNull LoopExp asLoopExp) {
		Iteration asIteration = PivotUtil.getReferredIteration(asLoopExp);
		IterationHelper iterationHelper = context.getIterationHelper(asIteration);
		CGIterationCallExp cgIterationCallExp;
		if (iterationHelper != null) {
			cgIterationCallExp = CGModelFactory.eINSTANCE.createCGBuiltInIterationCallExp();
		}
		else {
			LibraryIteration libraryIteration = (LibraryIteration) metamodelManager.getImplementation(asIteration);
			CGLibraryIterationCallExp cgLibraryIterationCallExp = CGModelFactory.eINSTANCE.createCGLibraryIterationCallExp();
			cgLibraryIterationCallExp.setLibraryIteration(libraryIteration);
			cgIterationCallExp = cgLibraryIterationCallExp;
		}
		initAst(cgIterationCallExp, asLoopExp);
		cgIterationCallExp.setReferredIteration(asIteration);
		cgIterationCallExp.setInvalidating(asIteration.isIsInvalidating());
		cgIterationCallExp.setValidating(asIteration.isIsValidating());
		cgIterationCallExp.setSource(cgSource);
		//
		//	Iterators / co-iterators
		//
		LocalContext savedLocalContext = null;
		if (iterationHelper == null) {			// No helper: iterators are arguments of a nested context
			initAst(cgIterationCallExp, asLoopExp);
			savedLocalContext = pushLocalContext(cgIterationCallExp);
		}
		for (@NonNull Variable iterator : PivotUtil.getOwnedIterators(asLoopExp)) {
			CGIterator cgIterator = getIterator(iterator);
			if (iterationHelper != null) {
				setNullableIterator(cgIterator, iterator);
			}
			cgIterationCallExp.getIterators().add(cgIterator);
		}
		for (@NonNull Variable coIterator : PivotUtil.getOwnedCoIterators(asLoopExp)) {
			CGIterator cgCoIterator = getIterator(coIterator);
			if (iterationHelper != null) {
				setNullableIterator(cgCoIterator, coIterator);
			}
			cgIterationCallExp.getCoIterators().add(cgCoIterator);
		}
		if (asLoopExp instanceof IterateExp) {
			Variable accumulator = PivotUtil.getOwnedResult((IterateExp)asLoopExp);
			CGIterator cgAccumulator = getIterator(accumulator);
			if (iterationHelper != null) {
				//				cgBuiltInIterationCallExp.setNonNull();
				setNullableIterator(cgAccumulator, accumulator);
				((CGBuiltInIterationCallExp)cgIterationCallExp).setAccumulator(cgAccumulator);
			}
			else {
				((CGLibraryIterateCallExp)cgIterationCallExp).setResult(cgAccumulator);
			}
			CGValuedElement cgInitExpression = doVisit(CGValuedElement.class, accumulator.getOwnedInit());
			cgAccumulator.setInit(cgInitExpression);
		}
		else {
			if (iterationHelper != null) {
				CGBuiltInIterationCallExp cgBuiltInIterationCallExp = (CGBuiltInIterationCallExp) cgIterationCallExp;
				CGTypeId cgAccumulatorId = iterationHelper.getAccumulatorTypeId(analyzer, cgBuiltInIterationCallExp);
				if (cgAccumulatorId != null) {
					boolean isNonNullAccumulator = iterationHelper.isNonNullAccumulator(asLoopExp);
					CGAccumulator cgAccumulator = CGModelFactory.eINSTANCE.createCGAccumulator();
					cgAccumulator.setTypeId(cgAccumulatorId);
					if (isNonNullAccumulator) {
						cgAccumulator.setNonNull();
					}
					if (!asIteration.isIsValidating()) {
						cgAccumulator.setNonInvalid();
					}
					getNameManager().declarePreferredName(cgAccumulator);
					cgBuiltInIterationCallExp.setAccumulator(cgAccumulator);
				}
			}
		}
		if (iterationHelper != null) {			// Helper: iterators are part of invocation context
			initAst(cgIterationCallExp, asLoopExp);
			savedLocalContext = pushLocalContext(cgIterationCallExp);
		}
		//
		//	Body
		//
		boolean isRequired = asLoopExp.isIsRequired();
		CGValuedElement cgBody = doVisit(CGValuedElement.class, asLoopExp.getOwnedBody());
		cgIterationCallExp.setBody(cgBody);
		if (iterationHelper != null) {
			if (asIteration.getOwnedParameters().get(0).isIsRequired()) {
				cgBody.setRequired(true);
			}
			if (isRequired) {
				((CGBuiltInIterationCallExp)cgIterationCallExp).setNonNull();
			}
		}
		//			cgBuiltInIterationCallExp.setNonNull();
		cgIterationCallExp.setRequired(isRequired);
		popLocalContext(savedLocalContext);
		return cgIterationCallExp;
	}

	protected @NonNull CGValuedElement generateOperationCallExp(@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		OCLExpression asSource = asOperationCallExp.getOwnedSource();
		Type asSourceType = asSource != null ? asSource.getType() : null;
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
		CGOperation cgOperation = generateOperationDeclaration(asSourceType, asOperation, false);
		OperationCallingConvention callingConvention = cgOperation.getCallingConvention();
		LibraryOperation libraryOperation = (LibraryOperation)metamodelManager.getImplementation(asOperation);
		CGValuedElement cgCallExp = callingConvention.createCGOperationCallExp(this, cgOperation, libraryOperation, cgSource, asOperationCallExp);
	//	if (cgCallExp instanceof CGOperationCallExp) {		// inlined code is not a CallExp
	//		CGOperationCallExp cgOperationCallExp = (CGOperationCallExp)cgCallExp;
		//	cgOperationCallExp.setCallingConvention(callingConvention);
	//		cgOperationCallExp.setOperation(cgOperation);
	//	}
	//	assert !(cgCallExp instanceof CGOperationCallExp) || (((CGOperationCallExp)cgCallExp).getCgOperation() == cgOperation);
		return cgCallExp;								// inlined code is not a CallExp
	}

	/**
	 * Generate / share the CG declaration for asOperation.
	 * @param asSourceType
	 */
	public @NonNull CGOperation generateOperationDeclaration(@Nullable Type asSourceType, @NonNull Operation asOperation, boolean isFinal) {	// XXX rationalize as generateOperationDeclaration with later createImplementation
		if (!isFinal) {
			CGOperation cgVirtualOperation = analyzer.basicGetVirtualCGOperation(asOperation);
			if (cgVirtualOperation != null) {
				return cgVirtualOperation;
			}
		}
		CGOperation cgOperation = analyzer.basicGetCGOperation(asOperation);
		if (cgOperation == null) {
			org.eclipse.ocl.pivot.Class asClass = PivotUtil.getOwningClass(asOperation);
			CGClass cgClass = analyzer.basicGetCGClass(asClass);
			if (cgClass == null) {
				cgClass = CGModelFactory.eINSTANCE.createCGClass();
				cgClass.setAst(asClass);
				cgClass.setName(asClass.getName());
				analyzer.addCGClass(cgClass);
			}
			else {
				assert cgClass.getAst() == asClass;
			}
			LocalContext savedPreClassContext = pushLocalContext(cgClass);
			try {
				OperationCallingConvention callingConvention = context.getCallingConvention(asOperation, isFinal);
				cgOperation = callingConvention.createCGOperation(this, asSourceType, asOperation);
				assert cgOperation.getAst() != null;
				assert cgOperation.getCallingConvention() == callingConvention;
				getNameManager().declarePreferredName(cgOperation);
				LocalContext savedClassContext = pushLocalContext(cgOperation);
				ExpressionInOCL asExpressionInOCL = null;
				LanguageExpression asSpecification = asOperation.getBodyExpression();
				if (asSpecification != null) {
					try {
						asExpressionInOCL = environmentFactory.parseSpecification(asSpecification);
					} catch (ParserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				callingConvention.createCGParameters(this, cgOperation, asExpressionInOCL);
				popLocalContext(savedClassContext);
			} finally {
				popLocalContext(savedPreClassContext);
			}
		}
		return cgOperation;
	}

	protected @NonNull CGValuedElement generateOppositePropertyCallExp(@NonNull CGValuedElement cgSource, @NonNull OppositePropertyCallExp asOppositePropertyCallExp) {
		Property asProperty = PivotUtil.getReferredProperty(asOppositePropertyCallExp);
		CGProperty cgProperty = generatePropertyDeclaration(asProperty);
		PropertyCallingConvention callingConvention = cgProperty.getCallingConvention();
		LibraryProperty libraryProperty = metamodelManager.getImplementation(null, null, asProperty);
		return callingConvention.createCGNavigationCallExp(this, cgProperty, libraryProperty, cgSource, asOppositePropertyCallExp);
	}

	protected @NonNull CGValuedElement generatePropertyCallExp(@Nullable CGValuedElement cgSource, @NonNull PropertyCallExp asPropertyCallExp) {
		Property asProperty = PivotUtil.getReferredProperty(asPropertyCallExp);
		CGProperty cgProperty = generatePropertyDeclaration(asProperty);
		PropertyCallingConvention callingConvention = cgProperty.getCallingConvention();
		LibraryProperty libraryProperty = metamodelManager.getImplementation(null, null, asProperty);
		return callingConvention.createCGNavigationCallExp(this, cgProperty, libraryProperty, cgSource, asPropertyCallExp);
	}

	/**
	 * Generate / share the CG declaration for asProperty.
	 */
	protected final @NonNull CGProperty generatePropertyDeclaration(@NonNull Property asProperty) {
		CGProperty cgProperty = analyzer.basicGetCGProperty(asProperty);
		if (cgProperty == null) {
			PropertyCallingConvention callingConvention = context.getCallingConvention(asProperty);
			cgProperty = callingConvention.createCGProperty(this, asProperty);
			cgProperty.setAst(asProperty);
			cgProperty.setTypeId(analyzer.getCGTypeId(asProperty.getTypeId()));
			cgProperty.setRequired(asProperty.isIsRequired());
			cgProperty.setCallingConvention(callingConvention);
			analyzer.addCGProperty(cgProperty);
			getNameManager().declarePreferredName(cgProperty);
			LocalContext savedLocalContext = pushLocalContext(cgProperty);
			ExpressionInOCL query = null;
			LanguageExpression specification = asProperty.getOwnedExpression();
			if (specification != null) {
				try {
					query = environmentFactory.parseSpecification(specification);		// Redundant already parsed
				} catch (ParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			callingConvention.createCGParameters(this, cgProperty, query);
			popLocalContext(savedLocalContext);
		}
		return cgProperty;
	}

	protected @NonNull CGValuedElement generateSafeExclusion(@NonNull CallExp callExp, @NonNull CGValuedElement cgSource) {
		CGLibraryOperationCallExp cgOperationCallExp = CGModelFactory.eINSTANCE.createCGLibraryOperationCallExp();
		cgOperationCallExp.setLibraryOperation(CollectionExcludingOperation.INSTANCE);
		StandardLibraryInternal standardLibrary = environmentFactory.getStandardLibrary();
		Operation asExcludingOperation = standardLibrary.getCollectionExcludingOperation();
		OCLExpression asSource = callExp.getOwnedSource();
		assert asSource != null;
		cgOperationCallExp.setReferredOperation(asExcludingOperation);
		CGOperation cgOperation = generateOperationDeclaration(asSource.getType(), asExcludingOperation, true);
		cgOperationCallExp.setCgOperation(cgOperation);
		cgOperationCallExp.setTypeId(analyzer.getCGTypeId(asSource.getTypeId()));
		cgOperationCallExp.setRequired(true);
		cgOperationCallExp.getCgArguments().add(cgSource);
		CGConstantExp cgArgument = CGModelFactory.eINSTANCE.createCGConstantExp();
		cgArgument.setReferredConstant(analyzer.getCGNull());
		cgArgument.setTypeId(analyzer.getCGTypeId(TypeId.OCL_VOID));
		cgOperationCallExp.getCgArguments().add(cgArgument);
		return cgOperationCallExp;
	}

	protected @NonNull CGIfExp generateSafeNavigationGuard(@NonNull CallExp callExp, @NonNull CGVariableExp cgVariableExp, @NonNull CGValuedElement cgUnsafeExp) {
		CGConstantExp cgNullExpression = analyzer.createCGConstantExp(callExp, analyzer.getCGNull());
		//
		CGIsEqual2Exp cgCondition = CGModelFactory.eINSTANCE.createCGIsEqual2Exp();
		cgCondition.setAst(callExp);
		cgCondition.setTypeId(analyzer.getCGTypeId(TypeId.BOOLEAN));
		cgCondition.setSource(cgVariableExp);
		cgCondition.setArgument(cgNullExpression);
		cgCondition.setInvalidating(false);
		cgCondition.setValidating(true);
		//
		CGConstantExp cgThenExpression = analyzer.createCGConstantExp(callExp, analyzer.getCGNull());
		//
		CGIfExp cgIfExp = analyzer.createCGIfExp(cgCondition, cgThenExpression, cgUnsafeExp);
		initAst(cgIfExp, callExp);
		//
		return cgIfExp;
	}

	public @NonNull CodeGenAnalyzer getAnalyzer() {
		return analyzer;
	}

	public @NonNull JavaCodeGenerator getCodeGenerator() {
		return context;
	}

	public @NonNull CGClass getCurrentClass() {
		return ClassUtil.nonNullState(basicGetCurrentClass());
	}

	public @NonNull EnvironmentFactoryInternalExtension getEnvironmentFactory() {
		return environmentFactory;
	}

	public @NonNull CGParameter getExecutorParameter() {
		LocalContext localContext = getLocalContext();
		return ((JavaLocalContext)localContext).getExecutorParameter();
	}

	public @NonNull CGVariable getExecutorVariable() {
		LocalContext localContext = getLocalContext();
		return ((JavaLocalContext)localContext).getExecutorVariable();
	}

	public @NonNull GenModelHelper getGenModelHelper() {
		return genModelHelper;
	}

	protected @Nullable CGValuedElement getInitExpression(@NonNull PropertyCallExp asPropertyCallExp) {
		Property asProperty = PivotUtil.getReferredProperty(asPropertyCallExp);
		return getInitExpression(asProperty);
	}

	public @Nullable CGValuedElement getInitExpression(/*@NonNull CGParameter cgSelfParameter,*/ @NonNull Property asProperty) {
		LanguageExpression specification = asProperty.getOwnedExpression();
		Object defaultValue = asProperty.getDefaultValue();
		CGValuedElement initExpression = null;
		if (specification != null) {
			try {
				ExpressionInOCL query = environmentFactory.parseSpecification(specification);
				Variable contextVariable = query.getOwnedContext();
				if (contextVariable != null) {
					getParameter(contextVariable, (String)null);
				}
				initExpression = doVisit(CGValuedElement.class, query.getOwnedBody());
			} catch (ParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			CGConstant cgConstant;
			if (defaultValue instanceof Boolean) {
				cgConstant = analyzer.getCGBoolean(((Boolean)defaultValue).booleanValue());
			}
			else if (defaultValue instanceof IntegerValue) {		// ?? Long etc
				cgConstant = analyzer.getCGInteger(((IntegerValue)defaultValue).asNumber());
			}
			else if (defaultValue instanceof RealValue) {
				cgConstant = analyzer.getCGReal(((RealValue)defaultValue).asNumber());
			}
			else if (defaultValue instanceof String) {
				cgConstant = analyzer.getCGString((String)defaultValue);
			}
			else if (defaultValue instanceof Number) {
				cgConstant = analyzer.getCGReal((Number)defaultValue);
			}
			else {
				cgConstant = null;
			}
			if (cgConstant != null) {
				initExpression = analyzer.createCGConstantExp(asProperty, cgConstant);

			}
		}
		return initExpression;
	}

	public @NonNull CGIterator getIterator(@NonNull VariableDeclaration asVariable) {
		CGIterator cgIterator = (CGIterator) variablesStack.getVariable(asVariable);
		if (cgIterator == null) {
			cgIterator = CGModelFactory.eINSTANCE.createCGIterator();
			cgIterator.setAst(asVariable);
			cgIterator.setTypeId(analyzer.getCGTypeId(TypeId.OCL_VOID));			// FIXME Java-specific type of polymorphic operation parameter
			getNameManager().declarePreferredName(cgIterator);
			variablesStack.putVariable(asVariable, cgIterator);
		}
		return cgIterator;
	}

	public @NonNull JavaLocalContext getLocalContext() {
		assert localContext != null;
		return (JavaLocalContext)localContext;
	}

	public @NonNull CGVariable getLocalVariable(@NonNull VariableDeclaration asVariable) {
		CGVariable cgVariable = variablesStack.getLocalVariable(asVariable);
		if (cgVariable == null) {
			cgVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
			initAst(cgVariable, asVariable);
			getNameManager().declareLazyName(cgVariable);
			variablesStack.putVariable(asVariable, cgVariable);
		}
		return cgVariable;
	}

	public @NonNull PivotMetamodelManager getMetamodelManager() {
		return metamodelManager;
	}

	public @NonNull NestedNameManager getNameManager() {
		return getLocalContext().getNameManager();
	}

/*	protected @NonNull CGIterator getNullableIterator(@NonNull Variable iterator) {
		CGIterator cgIterator = getIterator(iterator);
		cgIterator.setTypeId(analyzer.getTypeId(iterator.getTypeId()));
		cgIterator.setRequired(iterator.isIsRequired());
		if (iterator.isIsRequired()) {
			cgIterator.setNonNull();
		}
		cgIterator.setNonInvalid();
		return cgIterator;
	} */

	@Deprecated // add explicitName argument
	public @NonNull CGParameter getParameter(@NonNull Variable aParameter) {
		return getParameter(aParameter, (String)null);
	}
	public @NonNull CGParameter getParameter(@NonNull VariableDeclaration aParameter, @Nullable String explicitName) {
		CGParameter cgParameter = variablesStack.getParameter(aParameter);
		if (cgParameter == null) {
			cgParameter = CGModelFactory.eINSTANCE.createCGParameter();
			initAst(cgParameter, aParameter);
			if (explicitName == null) {
			//	analyzer.setNames(cgParameter, aParameter);

			//	String name = analyzer.getGlobalNameManager().getNameHint(aParameter);
				//	String name = globalNameManager.helper.getNameHint(anObject);
				//	cgValue.setName(name);
				//	cgValue.setValueName(name);
				getNameManager().declarePreferredName(cgParameter);


			//	NameResolution nameResolution = cgParameter.getNameResolution();
			//	nameResolution.setResolvedName(parameterVariable.getName());
			//	getNameManager().addNameResolution(nameResolution);
			}
			else {
				assert explicitName.equals(aParameter.getName());
				Operation asOperation = PivotUtil.getContainingOperation(aParameter);
				Constraint asConstraint = PivotUtil.getContainingConstraint(aParameter);
				assert ((asOperation != null) && (asOperation.getESObject() instanceof EOperation)) || ((asConstraint != null) && (asConstraint.getESObject() instanceof EOperation));
			//	assert is-ecore-parameter
			//	cgParameter.setName(explicitName);
			//	cgParameter.setValueName(explicitName);
				NestedNameManager nameManager = getNameManager();
				/*NameResolution nameResolution =*/ nameManager.declareReservedName(cgParameter, explicitName);
			//	nameResolution.setResolvedName(explicitName);
			}
			//			cgParameter.setTypeId(analyzer.getTypeId(aParameter.getTypeId()));
			addParameter(aParameter, cgParameter);
			cgParameter.setRequired(aParameter.isIsRequired());
			if (aParameter.isIsRequired()) {
				cgParameter.setNonNull();
			}
		}
		return cgParameter;
	}
	public @NonNull CGParameter getParameter(@NonNull Variable aParameter, @NonNull NameResolution nameResolution) {
		CGParameter cgParameter = variablesStack.getParameter(aParameter);
		if (cgParameter == null) {
			cgParameter = CGModelFactory.eINSTANCE.createCGParameter();
			cgParameter.setName(aParameter.getName());
			nameResolution.addCGElement(cgParameter);
			initAst(cgParameter, aParameter);
			getNameManager().declareLazyName(cgParameter);
			addParameter(aParameter, cgParameter);
			cgParameter.setRequired(aParameter.isIsRequired());
			if (aParameter.isIsRequired()) {
				cgParameter.setNonNull();
			}
		}
		return cgParameter;
	}

	public @NonNull CGParameter getSelfParameter(@NonNull VariableDeclaration aParameter) {
		CGParameter cgParameter = variablesStack.getParameter(aParameter);
		if (cgParameter == null) {
			cgParameter = CGModelFactory.eINSTANCE.createCGParameter();
			initAst(cgParameter, aParameter);
			globalNameManager.getSelfNameResolution().addCGElement(cgParameter);
			addParameter(aParameter, cgParameter);
			cgParameter.setRequired(aParameter.isIsRequired());
			if (aParameter.isIsRequired()) {
				cgParameter.setNonNull();
			}
		}
		return cgParameter;
	}

	public @NonNull CGParameter getThisParameter(@NonNull VariableDeclaration aParameter) {
		CGParameter cgParameter = variablesStack.getParameter(aParameter);
		if (cgParameter == null) {
			cgParameter = CGModelFactory.eINSTANCE.createCGParameter();
			initAst(cgParameter, aParameter);
			globalNameManager.getThisNameResolution().addCGElement(cgParameter);
			addParameter(aParameter, cgParameter);
			cgParameter.setRequired(aParameter.isIsRequired());
			if (aParameter.isIsRequired()) {
				cgParameter.setNonNull();
			}
			cgParameter.setIsSelf(true);
		}
		return cgParameter;
	}

	public @NonNull CGParameter getTypeIdParameter() {
		LocalContext localContext = getLocalContext();
		CGParameter typeIdParameter = ((JavaLocalContext)localContext).getTypeIdParameter();
		assert typeIdParameter.eContainer() == null;
		addParameter(CGUtil.getAST(typeIdParameter), typeIdParameter);
		return typeIdParameter;
	}

	public @NonNull CGVariable getVariable(@NonNull VariableDeclaration asVariable) {
		CGVariable cgVariable = variablesStack.getVariable(asVariable);
		if (cgVariable == null) {
			cgVariable = createCGVariable(asVariable);
			if (asVariable.isIsRequired()) {
				cgVariable.setNonInvalid();
				cgVariable.setNonNull();
			}
		}
		return cgVariable;
	}

	public @NonNull Variables getVariablesStack() {
		return variablesStack;
	}

	public void initAst(@NonNull CGValuedElement cgElement, @NonNull TypedElement asElement) {
		cgElement.setAst(asElement);
		TypeId asTypeId = asElement.getTypeId();
		cgElement.setTypeId(analyzer.getCGTypeId(asTypeId));
	}

	@Nullable
	public CGValuedElement inlineOperationCall(@NonNull OperationCallExp callExp, @NonNull LanguageExpression specification) {
		ExpressionInOCL prototype = null;
		try {
			prototype = environmentFactory.parseSpecification(specification);
		}
		catch (ParserException e) {
			// FIXME log error
			e.printStackTrace();
		}
		if (prototype == null) {
			return null;
		}
		FinalAnalysis finalAnalysis = metamodelManager.getFinalAnalysis();
		Set<@NonNull Operation> referencedFinalOperations = new HashSet<>();
		((AbstractCodeGenerator)context).getTransitivelyReferencedFinalOperations(referencedFinalOperations, finalAnalysis, specification);
		if (referencedFinalOperations.contains(callExp.getReferredOperation())) {
			return null;	// Avoid an infinite inlining recursion.
		}
		Iterable<@NonNull Operation> referencedNonFinalOperations = ((AbstractCodeGenerator)context).getReferencedNonFinalOperations(finalAnalysis, specification);
		if (referencedNonFinalOperations != null) {
			return null;	// Simple heavy heuristic
		}
		throw new UnsupportedOperationException();
	/*/	assert false;
		ExpressionInOCL asClone = createCopy(prototype);
		OCLExpression asExpression = ClassUtil.nonNullState(asClone.getOwnedBody());
		List<@NonNull OCLExpression> asArguments = ClassUtil.nullFree(callExp.getOwnedArguments());
		int argumentsSize = asArguments.size();
		if (argumentsSize > 0) {
			List<@NonNull Parameter> asParameters = ClassUtil.nullFree(callExp.getReferredOperation().getOwnedParameters());
			List<@NonNull Variable> asParameterVariables = ClassUtil.nullFree(asClone.getOwnedParameters());
			List<@NonNull Variable> asVariables = new ArrayList<>(asParameterVariables);
			asParameterVariables.clear();				// Defeat child-stealing detector
			for (@NonNull Variable asVariable : asVariables) {
				Parameter asParameter = asVariable.getRepresentedParameter();
				if (asParameter != null) {
					int index = asParameters.indexOf(asParameter);
					if ((0 <= index) && (index < argumentsSize)) {
						asExpression = createLetExp(asVariable, asArguments.get(index), asExpression);
					}
				}
			}
		}
		Variable asVariable = asClone.getOwnedContext();
		asClone.setOwnedContext(null);				// Defeat child-stealing detector
		asExpression = createLetExp(asVariable, callExp.getOwnedSource(), asExpression);
		ASResource asResource = (ASResource) specification.eResource();
		try {
			boolean wasUpdating = asResource.setUpdating(true);			// FIXME Avoid immutable change
			asResource.getContents().add(asExpression);					// Ensure that asExpression is not a Resource-less orphan; needed for FlowAnalysis
			asResource.setUpdating(wasUpdating);
			return doVisit(CGValuedElement.class, asExpression);
		}
		finally {
			boolean wasUpdating = asResource.setUpdating(true);			// FIXME Avoid immutable change
			asResource.getContents().remove(asExpression);
			asResource.setUpdating(wasUpdating);
		} */
	}

	/**
	 * Return true if the asVariableExp reference to asParameter is a reference to 'this' and needs mapping to the qualifiedThisVariable equivalent.
	 */
	protected boolean isQualifiedThis(@NonNull VariableExp asVariableExp, @NonNull Parameter asParameter) {
		return false;
	}

	/**
	 * Return true if asParameter is a 'this' parameter.
	 */
	protected boolean isThis(@NonNull Parameter asParameter) {
		return JavaConstants.THIS_NAME.equals(asParameter.getName());
	}

	public void popLocalContext(@Nullable LocalContext savedLocalContext) {
		localContext = savedLocalContext;
	}

	public @Nullable LocalContext pushLocalContext(@NonNull CGNamedElement cgElement) {
		LocalContext savedLocalContext = localContext;
		LocalContext localContext2 = globalNameManager.basicGetLocalContext(cgElement);
		if (localContext2 == null) {
			localContext2 = globalNameManager.initLocalContext(savedLocalContext, cgElement);
		}
		localContext = localContext2;
		return savedLocalContext;
	}

	protected void setCGVariableInit(@NonNull CGVariable cgVariable, @NonNull CGValuedElement cgInit) {
	//	NameResolution variableNameResolution = cgVariable.getNameResolution();
		if (cgInit.basicGetNameResolution() == null) {
			//
			//	Propagate the variable name resolution to its initializer and intervening lets.
			//
			CGValuedElement cgElement = cgInit;
			while (cgElement.basicGetNameResolution() == null) {
			//	variableNameResolution.addCGElement(cgElement);			// XXX
				if (cgElement instanceof CGLetExp) {
					cgElement = CGUtil.getIn((CGLetExp)cgElement);
				}
				else {
					break;
				}
			}
		}
		cgVariable.setInit(cgInit);
	}

	private void setNullableIterator(@NonNull CGIterator cgIterator, @NonNull Variable iterator) {
		cgIterator.setTypeId(analyzer.getCGTypeId(iterator.getTypeId()));
		cgIterator.setRequired(iterator.isIsRequired());
		if (iterator.isIsRequired()) {
			cgIterator.setNonNull();
		}
		cgIterator.setNonInvalid();
	}

	/*	@Override
	public @Nullable CGElement visitAssociationClassCallExp(@NonNull AssociationClassCallExp element) {
//		CGElement thisAnalysis = analyzer.getCurrentAnalysis();
		return super.visitAssociationClassCallExp(element);
	} */

	@Override
	public @Nullable CGConstantExp visitBooleanLiteralExp(@NonNull BooleanLiteralExp element) {
		CGConstant constant = analyzer.getCGBoolean(element.isBooleanSymbol());
		CGConstantExp cgLiteralExp = analyzer.createCGConstantExp(element, constant);
		initAst(cgLiteralExp, element);
		return cgLiteralExp;
	}

	/**
	 * @since 1.3
	 */
	@Override
	public @NonNull CGClass visitClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		CGClass cgClass = analyzer.basicGetCGClass(asClass);
		if (cgClass == null) {
			cgClass = CGModelFactory.eINSTANCE.createCGClass();
			cgClass.setAst(asClass);
			cgClass.setName(asClass.getName());
			analyzer.addCGClass(cgClass);
		}
		else {
			assert cgClass.getAst() == asClass;
		}
		LocalContext savedLocalContext = pushLocalContext(cgClass);
		for (@NonNull Constraint asConstraint : ClassUtil.nullFree(asClass.getOwnedInvariants())) {
			CGConstraint cgConstraint = doVisit(CGConstraint.class, asConstraint);
			cgClass.getInvariants().add(cgConstraint);
		}
		for (@NonNull Operation asOperation : ClassUtil.nullFree(asClass.getOwnedOperations())) {
			CGOperation cgOperation = doVisit(CGOperation.class, asOperation);
			cgClass.getOperations().add(cgOperation);
		}
		for (@NonNull Property asProperty : ClassUtil.nullFree(asClass.getOwnedProperties())) {
			CGProperty cgProperty = doVisit(CGProperty.class, asProperty);
			cgClass.getProperties().add(cgProperty);
		}
		popLocalContext(savedLocalContext);
		return cgClass;
	}

	@Override
	public @Nullable CGCollectionPart visitCollectionItem(@NonNull CollectionItem asElement) {
		CGCollectionPart cgCollectionPart = CGModelFactory.eINSTANCE.createCGCollectionPart();
		initAst(cgCollectionPart, asElement);
		cgCollectionPart.setFirst(doVisit(CGValuedElement.class, asElement.getOwnedItem()));
		return cgCollectionPart;
	}

	@Override
	public @Nullable CGCollectionExp visitCollectionLiteralExp(@NonNull CollectionLiteralExp asCollectionLiteralExp) {
		CGCollectionExp cgCollectionExp = CGModelFactory.eINSTANCE.createCGCollectionExp();
		initAst(cgCollectionExp, asCollectionLiteralExp);
		List<CGCollectionPart> cgParts = cgCollectionExp.getParts();
		for (@NonNull CollectionLiteralPart asPart : ClassUtil.nullFree(asCollectionLiteralExp.getOwnedParts())) {
			cgParts.add(doVisit(CGCollectionPart.class, asPart));
		}
		return cgCollectionExp;
	}

	@Override
	public @Nullable CGCollectionPart visitCollectionRange(@NonNull CollectionRange asElement) {
		CGCollectionPart cgCollectionPart = CGModelFactory.eINSTANCE.createCGCollectionPart();
		cgCollectionPart.setAst(asElement);
		cgCollectionPart.setTypeId(analyzer.getCGTypeId(TypeId.INTEGER_RANGE));
		cgCollectionPart.setFirst(doVisit(CGValuedElement.class, asElement.getOwnedFirst()));
		cgCollectionPart.setLast(doVisit(CGValuedElement.class, asElement.getOwnedLast()));
		return cgCollectionPart;
	}

	@Override
	public @Nullable CGConstraint visitConstraint(@NonNull Constraint asConstraint) {
		CGConstraint cgConstraint = CGModelFactory.eINSTANCE.createCGConstraint();
		LanguageExpression specification = asConstraint.getOwnedSpecification();
		if (specification != null) {
			assert cgConstraint.basicGetNameResolution() == null;
			cgConstraint.setAst(asConstraint);
			getNameManager().declarePreferredName(cgConstraint);
			LocalContext savedLocalContext = pushLocalContext(cgConstraint);
			try {
				ExpressionInOCL query = environmentFactory.parseSpecification(specification);
				Variable contextVariable = query.getOwnedContext();
				if (contextVariable != null) {
					CGParameter cgParameter = getParameter(contextVariable, (String)null);
					cgConstraint.getParameters().add(cgParameter);
				}
				for (@NonNull Variable parameterVariable : ClassUtil.nullFree(query.getOwnedParameters())) {
					CGParameter cgParameter = getParameter(parameterVariable, (String)null);
					cgConstraint.getParameters().add(cgParameter);
				}
				cgConstraint.setBody(doVisit(CGValuedElement.class, query.getOwnedBody()));
			} catch (ParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				popLocalContext(savedLocalContext);
			}
		}
		return cgConstraint;
	}

	@Override
	public @Nullable CGConstantExp visitEnumLiteralExp(@NonNull EnumLiteralExp element) {
		//		CGConstant constant = analyzer.getEnumerationLiteral(element.getReferredEnumLiteral());
		CGConstant constant = analyzer.getCGElementId(element.getReferredLiteral().getEnumerationLiteralId());
		CGConstantExp cgLiteralExp = analyzer.createCGConstantExp(element, constant);
		initAst(cgLiteralExp, element);
		return cgLiteralExp;
	}

	@Override
	public @Nullable CGValuedElement visitExpressionInOCL(@NonNull ExpressionInOCL query) {
		assert query.getOwnedBody() != null;
		Variable contextVariable = query.getOwnedContext();
		if (contextVariable != null) {
			CGVariable cgContext = getParameter(contextVariable, (String)null);
			cgContext.setNonInvalid();
			//			cgContext.setNonNull();
		}
		for (@NonNull Variable parameterVariable : ClassUtil.nullFree(query.getOwnedParameters())) {
			@SuppressWarnings("unused") CGVariable cgParameter = getParameter(parameterVariable, (String)null);
		}
		CGValuedElement cgBody = doVisit(CGValuedElement.class, query.getOwnedBody());
		//		cgOperation.getDependsOn().add(cgBody);
		return cgBody;
	}

	@Override
	public @NonNull CGIfExp visitIfExp(@NonNull IfExp asIfExp) {
		CGValuedElement cgCondition = doVisit(CGValuedElement.class, asIfExp.getOwnedCondition());
		CGValuedElement cgThenExpression = doVisit(CGValuedElement.class, asIfExp.getOwnedThen());
		CGValuedElement cgElseExpression = doVisit(CGValuedElement.class, asIfExp.getOwnedElse());
		CGIfExp cgIfExp = analyzer.createCGIfExp(cgCondition, cgThenExpression, cgElseExpression);
		initAst(cgIfExp, asIfExp);
		return cgIfExp;
	}

	@Override
	public @Nullable CGConstantExp visitIntegerLiteralExp(@NonNull IntegerLiteralExp element) {
		Number integerSymbol = element.getIntegerSymbol();
		CGInteger constant = analyzer.getCGInteger(integerSymbol != null ? integerSymbol : 0);
		CGConstantExp cgLiteralExp = analyzer.createCGConstantExp(element, constant);
		initAst(cgLiteralExp, element);
		return cgLiteralExp;
	}

	@Override
	public @Nullable CGConstantExp visitInvalidLiteralExp(@NonNull InvalidLiteralExp element) {
		CGConstantExp cgLiteralExp = analyzer.createCGConstantExp(element, analyzer.getCGInvalid());
		initAst(cgLiteralExp, element);
		return cgLiteralExp;
	}

	@Override
	public @Nullable CGLetExp visitLetExp(@NonNull LetExp asLetExp) {
		Variable asVariable = PivotUtil.getOwnedVariable(asLetExp);
		CGFinalVariable cgVariable = (CGFinalVariable) createCGVariable(asVariable);		// FIXME Lose cast
		CGValuedElement cgInit = doVisit(CGValuedElement.class, asVariable.getOwnedInit());
		setCGVariableInit(cgVariable, cgInit);;
		CGValuedElement cgIn = doVisit(CGValuedElement.class, asLetExp.getOwnedIn());
		CGLetExp cgLetExp = createCGLetExp(asLetExp, cgVariable, cgIn);
		NameResolution inNameResolution = cgIn.basicGetNameResolution();
		if (inNameResolution != null) {
			inNameResolution.addCGElement(cgLetExp);
		}
		return cgLetExp;
	}

	@Override
	public final @NonNull CGValuedElement visitLoopExp(@NonNull LoopExp element) {
		CGValuedElement cgSource = doVisit(CGValuedElement.class, element.getOwnedSource());
		CGValuedElement cgSafeSource = element.isIsSafe() ? generateSafeExclusion(element, cgSource) : cgSource;
		return generateLoopExp(cgSafeSource, element);
	}

	@Override
	public @Nullable CGNamedElement visitMapLiteralExp(@NonNull MapLiteralExp element) {
		CGMapExp cgMapExp = CGModelFactory.eINSTANCE.createCGMapExp();
		initAst(cgMapExp, element);
		List<@NonNull CGMapPart> cgParts = ClassUtil.nullFree(cgMapExp.getParts());
		for (@NonNull MapLiteralPart asPart : ClassUtil.nullFree(element.getOwnedParts())) {
			cgParts.add(doVisit(CGMapPart.class, asPart));
		}
		analyzer.getCGTypeId(element.getTypeId());
		return cgMapExp;
	}

	@Override
	public @Nullable CGNamedElement visitMapLiteralPart(@NonNull MapLiteralPart element) {
		CGMapPart cgMapPart = CGModelFactory.eINSTANCE.createCGMapPart();
		cgMapPart.setAst(element);
		cgMapPart.setTypeId(analyzer.getCGTypeId(TypeId.MAP_ENTRY));
		cgMapPart.setKey(doVisit(CGValuedElement.class, element.getOwnedKey()));
		cgMapPart.setValue(doVisit(CGValuedElement.class, element.getOwnedValue()));
		return cgMapPart;
	}

	/*	@Override
	public @Nullable CGElement visitMessageExp(@NonNull MessageExp element) {
//		CGElement thisAnalysis = analyzer.getCurrentAnalysis();
		return super.visitMessageExp(element);
	} */

	@Override
	public @Nullable CGConstantExp visitNullLiteralExp(@NonNull NullLiteralExp element) {
		CGConstantExp cgLiteralExp = analyzer.createCGConstantExp(element, analyzer.getCGNull());
		initAst(cgLiteralExp, element);
		return cgLiteralExp;
	}

	@Override
	public @Nullable CGOperation visitOperation(@NonNull Operation asOperation) {
		if (asOperation.toString().contains("allOwnedElements")) {
			getClass();		// XXX
		}
		CGOperation cgOperation = generateOperationDeclaration(null, asOperation, false);
		LocalContext savedLocalContext = pushLocalContext(cgOperation);
		LanguageExpression specification = asOperation.getBodyExpression();
		if (specification instanceof ExpressionInOCL) {			// Should already be parsed
			cgOperation.setBody(doVisit(CGValuedElement.class, ((ExpressionInOCL)specification).getOwnedBody()));
		}
		popLocalContext(savedLocalContext);
		return cgOperation;
	}

	@Override
	public final @NonNull CGValuedElement visitOperationCallExp(@NonNull OperationCallExp asOperationCallExp) {
		OCLExpression asSource = asOperationCallExp.getOwnedSource();
		if (asSource == null) {
			return generateOperationCallExp(null, asOperationCallExp);
		}
		CGValuedElement cgSource = doVisit(CGValuedElement.class, asSource);
		if (!asOperationCallExp.isIsSafe()) {// && !cgSource.isNonNull()) {
			return generateOperationCallExp(cgSource, asOperationCallExp);
		}
		Type sourceType = asSource.getType();
		if (sourceType instanceof CollectionType) {
			if (asOperationCallExp.isIsSafe()) {
				cgSource = generateSafeExclusion(asOperationCallExp, cgSource);
			}
			return generateOperationCallExp(cgSource, asOperationCallExp);
		}
		boolean hasVariable = cgSource instanceof CGVariableExp;
		CGVariable cgVariable = hasVariable ? CGUtil.getReferredVariable((CGVariableExp)cgSource) : createCGFinalVariable(cgSource);
		CGVariableExp cgVariableExp1 = analyzer.createCGVariableExp(cgVariable);
		CGVariableExp cgVariableExp2 = analyzer.createCGVariableExp(cgVariable);
		CGValuedElement cgUnsafeExp = generateOperationCallExp(cgVariableExp1, asOperationCallExp);
		CGIfExp cgIfExp = generateSafeNavigationGuard(asOperationCallExp, cgVariableExp2, cgUnsafeExp);
		return hasVariable ? cgIfExp : createCGLetExp(asOperationCallExp, (CGFinalVariable)cgVariable, cgIfExp);
	}

	@Override
	public final @NonNull CGValuedElement visitOppositePropertyCallExp(@NonNull OppositePropertyCallExp asOppositePropertyCallExp) {
		OCLExpression asSource = ClassUtil.nonNullModel(asOppositePropertyCallExp.getOwnedSource());
		CGValuedElement cgSource = doVisit(CGValuedElement.class, asSource);
		if (!asOppositePropertyCallExp.isIsSafe()) {
			return generateOppositePropertyCallExp(cgSource, asOppositePropertyCallExp);
		}
		boolean hasVariable = cgSource instanceof CGVariableExp;
		CGVariable cgVariable = hasVariable ? CGUtil.getReferredVariable((CGVariableExp)cgSource) : createCGFinalVariable(cgSource);
		CGVariableExp cgVariableExp1 = analyzer.createCGVariableExp(cgVariable);
		CGVariableExp cgVariableExp2 = analyzer.createCGVariableExp(cgVariable);
		CGValuedElement cgUnsafeExp = generateOppositePropertyCallExp(cgVariableExp1, asOppositePropertyCallExp);
		CGIfExp cgIfExp = generateSafeNavigationGuard(asOppositePropertyCallExp, cgVariableExp2, cgUnsafeExp);
		return hasVariable ? cgIfExp : createCGLetExp(asOppositePropertyCallExp, (CGFinalVariable)cgVariable, cgIfExp);
	}

	@Override
	public @Nullable CGNamedElement visitPackage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		CGPackage cgPackage = CGModelFactory.eINSTANCE.createCGPackage();
		cgPackage.setAst(asPackage);
		cgPackage.setName(asPackage.getName());			// XXX nameResolution
		for (org.eclipse.ocl.pivot.@NonNull Class asType : ClassUtil.nullFree(asPackage.getOwnedClasses())) {
			CGClass cgClass = doVisit(CGClass.class, asType);
			cgPackage.getClasses().add(cgClass);
		}
		return cgPackage;
	}

	@Override
	public final @NonNull CGProperty visitProperty(@NonNull Property asProperty) {
		CGProperty cgProperty = generatePropertyDeclaration(asProperty);
		PropertyCallingConvention callingConvention = cgProperty.getCallingConvention();
		LocalContext savedLocalContext = pushLocalContext(cgProperty);
		callingConvention.createImplementation(this, getLocalContext(), cgProperty);
		popLocalContext(savedLocalContext);
		return cgProperty;
	}

	@Override
	public final @NonNull CGValuedElement visitPropertyCallExp(@NonNull PropertyCallExp asPropertyCallExp) {
		OCLExpression asSource = asPropertyCallExp.getOwnedSource();
		if (asSource == null) {
			return generatePropertyCallExp(null, asPropertyCallExp);
		}
		CGValuedElement cgSource = doVisit(CGValuedElement.class, asSource);
		if (!asPropertyCallExp.isIsSafe()) {
			return generatePropertyCallExp(cgSource, asPropertyCallExp);
		}
		boolean hasVariable = cgSource instanceof CGVariableExp;
		CGVariable cgVariable = hasVariable ? CGUtil.getReferredVariable((CGVariableExp)cgSource) : createCGFinalVariable(cgSource);
		CGVariableExp cgVariableExp1 = analyzer.createCGVariableExp(cgVariable);
		CGVariableExp cgVariableExp2 = analyzer.createCGVariableExp(cgVariable);
		CGValuedElement cgUnsafeExp = generatePropertyCallExp(cgVariableExp1, asPropertyCallExp);
		CGIfExp cgIfExp = generateSafeNavigationGuard(asPropertyCallExp, cgVariableExp2, cgUnsafeExp);
		return hasVariable ? cgIfExp : createCGLetExp(asPropertyCallExp, (CGFinalVariable)cgVariable, cgIfExp);
	}

	@Override
	public @Nullable CGConstantExp visitRealLiteralExp(@NonNull RealLiteralExp element) {
		Number realSymbol = element.getRealSymbol();
		@SuppressWarnings("null")
		CGReal cgReal = analyzer.getCGReal(realSymbol != null ? realSymbol : Double.valueOf(0.0));
		CGConstantExp cgLiteralExp = analyzer.createCGConstantExp(element, cgReal);
		initAst(cgLiteralExp, element);
		return cgLiteralExp;
	}

	@Override
	public @Nullable CGShadowExp visitShadowExp(@NonNull ShadowExp element) {
		CGShadowExp cgShadowExp = null;
		Type type = element.getType();
		if (type != null) {
			CompleteClass completeClass = environmentFactory.getCompleteModel().getCompleteClass(type);
			EObject eTarget = null;
			for (org.eclipse.ocl.pivot.@NonNull Class partialClass : ClassUtil.nullFree(completeClass.getPartialClasses())) {
				eTarget = partialClass.getESObject();
				if (eTarget != null) {
					break;
				}
			}
			if (eTarget instanceof EDataType) {
				CGEcoreDataTypeShadowExp cgEShadowExp = CGModelFactory.eINSTANCE.createCGEcoreDataTypeShadowExp();
				cgEShadowExp.setEDataType((EDataType)eTarget);
				cgShadowExp = cgEShadowExp;
			}
			else if (eTarget instanceof EClass) {
				CGEcoreClassShadowExp cgEShadowExp = CGModelFactory.eINSTANCE.createCGEcoreClassShadowExp();
				cgEShadowExp.setEClass((EClass)eTarget);
				cgShadowExp = cgEShadowExp;
			}
		}
		if (cgShadowExp != null) {
			CGExecutorType cgExecutorType = analyzer.createExecutorType(ClassUtil.nonNullState(element.getType()));
			cgShadowExp.setExecutorType(cgExecutorType);
			cgShadowExp.getOwns().add(cgExecutorType);
			initAst(cgShadowExp, element);
			List<@NonNull ShadowPart> asParts = new ArrayList<>(ClassUtil.nullFree(element.getOwnedParts()));
			Collections.sort(asParts, NameUtil.NAMEABLE_COMPARATOR);
			List<@NonNull CGShadowPart> cgParts = ClassUtil.nullFree(cgShadowExp.getParts());		// Ensure deterministic CGShadowPart order
			for (@NonNull ShadowPart asPart : asParts) {
				cgParts.add(doVisit(CGShadowPart.class, asPart));
			}
		}
		return cgShadowExp;
	}

	@Override
	public @Nullable CGShadowPart visitShadowPart(@NonNull ShadowPart element) {
		CGShadowPart cgShadowPart = CGModelFactory.eINSTANCE.createCGShadowPart();
		initAst(cgShadowPart, element);
		cgShadowPart.setInit(doVisit(CGValuedElement.class, element.getOwnedInit()));
		Property referredProperty = element.getReferredProperty();
		if (referredProperty != null) {
			CGExecutorShadowPart cgExecutorShadowPart = analyzer.createExecutorShadowPart(referredProperty);
			cgExecutorShadowPart.setCallingConvention(ExecutorShadowPartCallingConvention.INSTANCE);
			cgShadowPart.setExecutorPart(cgExecutorShadowPart);
		}
		return cgShadowPart;
	}

	@Override
	public @Nullable CGNamedElement visitStateExp(@NonNull StateExp element) {
		//		CGElement thisAnalysis = analyzer.getCurrentAnalysis();
		return super.visitStateExp(element);
	}

	@Override
	public @Nullable CGConstantExp visitStringLiteralExp(@NonNull StringLiteralExp element) {
		String stringSymbol = element.getStringSymbol();
		CGString cgString = analyzer.getCGString(stringSymbol != null ? stringSymbol : "");
		CGConstantExp cgLiteralExp = analyzer.createCGConstantExp(element, cgString);
		return cgLiteralExp;
	}

	@Override
	public @Nullable CGTupleExp visitTupleLiteralExp(@NonNull TupleLiteralExp element) {
		CGTupleExp cgTupleExp = CGModelFactory.eINSTANCE.createCGTupleExp();
		initAst(cgTupleExp, element);
		List<@NonNull CGTuplePart> cgParts = new ArrayList<>();
		for (@NonNull TupleLiteralPart asPart : ClassUtil.nullFree(element.getOwnedParts())) {
			cgParts.add(doVisit(CGTuplePart.class, asPart));
		}
		Collections.sort(cgParts, CGTuplePartNameComparator.INSTANCE);
		cgTupleExp.getParts().addAll(cgParts);
		analyzer.getCGTypeId(element.getTypeId());
		return cgTupleExp;
	}

	@Override
	public @Nullable CGTuplePart visitTupleLiteralPart(@NonNull TupleLiteralPart element) {
		CGTuplePart cgTuplePart = CGModelFactory.eINSTANCE.createCGTuplePart();
		initAst(cgTuplePart, element);
		cgTuplePart.setInit(doVisit(CGValuedElement.class, element.getOwnedInit()));
		TuplePartId partId = element.getPartId();
		if (partId != null) {
			analyzer.getCGElementId(partId);
		}
		return cgTuplePart;
	}

	@Override
	public @Nullable CGValuedElement visitTypeExp(@NonNull TypeExp asTypeExp) {
		Type referredType = PivotUtil.getReferredType(asTypeExp);
		if (!(referredType instanceof TemplateParameter)) {
			CGTypeExp cgTypeExp = CGModelFactory.eINSTANCE.createCGTypeExp();
			initAst(cgTypeExp, asTypeExp);
			CGExecutorType cgExecutorType = analyzer.createExecutorType(referredType);
			cgTypeExp.setExecutorType(cgExecutorType);
			cgTypeExp.getOwns().add(cgExecutorType);
			return cgTypeExp;
		}
		TemplateParameter referredTemplateParameter = (TemplateParameter)referredType;
		TemplateSignature templateSignature = PivotUtil.getOwningSignature(referredTemplateParameter);
		TemplateableElement asTemplateableElement = PivotUtil.getOwningElement(templateSignature);
		CGValuedElement cgTemplateableElement;
		if (asTemplateableElement instanceof Type) {
			cgTemplateableElement = analyzer.createExecutorType((Type)asTemplateableElement);
		}
	//	else if (asTemplateableElement instanceof Operation) {
	//		cgTemplateableElement = analyzer.createExecutorOperation((Operation)asTemplateableElement);
	//	}
		else {
			context.addProblem(new UnsupportedOperationException("visitTypeExp for non-Type Templateparameter"));
			return null;
		}
		int index = templateSignature.getOwnedParameters().indexOf(referredTemplateParameter);
		CGTemplateParameterExp cgTemplateParameterExp = CGModelFactory.eINSTANCE.createCGTemplateParameterExp();
		cgTemplateParameterExp.setIndex(index);
		initAst(cgTemplateParameterExp, asTypeExp);
		cgTemplateParameterExp.setTemplateableElement(cgTemplateableElement);
		cgTemplateParameterExp.getOwns().add(cgTemplateableElement);
		return cgTemplateParameterExp;
	}

	@Override
	public @Nullable CGConstantExp visitUnlimitedNaturalLiteralExp(@NonNull UnlimitedNaturalLiteralExp element) {
		Number unlimitedNaturalSymbol = element.getUnlimitedNaturalSymbol();
		CGConstantExp cgLiteralExp;
		if (unlimitedNaturalSymbol instanceof UnlimitedValue) {
			cgLiteralExp = analyzer.createCGConstantExp(element, analyzer.getCGUnlimited());
		}
		else if (unlimitedNaturalSymbol instanceof Unlimited) {
			cgLiteralExp = analyzer.createCGConstantExp(element, analyzer.getCGUnlimited());
		}
		else if (unlimitedNaturalSymbol != null) {
			cgLiteralExp = analyzer.createCGConstantExp(element, analyzer.getCGInteger(unlimitedNaturalSymbol));
		}
		else {
			cgLiteralExp = analyzer.createCGConstantExp(element, analyzer.getCGInteger(0));
		}
		initAst(cgLiteralExp, element);
		return cgLiteralExp;
	}

	@Override
	public @Nullable CGNamedElement visitUnspecifiedValueExp(@NonNull UnspecifiedValueExp element) {
		//		CGElement thisAnalysis = analyzer.getCurrentAnalysis();
		return super.visitUnspecifiedValueExp(element);
	}

	@Override
	public @Nullable CGValuedElement visitVariableExp(@NonNull VariableExp asVariableExp) {
		CGVariableExp cgVariableExp = createCGVariableExp(asVariableExp);
		return cgVariableExp;
	}

	@Override

	public @Nullable CGValuedElement visiting(@NonNull Visitable visitable) {
		throw new UnsupportedOperationException(getClass().getSimpleName() + ": " + visitable.getClass().getSimpleName());
	}
}
