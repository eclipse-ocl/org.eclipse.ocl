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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGAccumulator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstant;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreClassShadowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreDataTypeShadowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOppositePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcorePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOppositePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorShadowPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGFinalVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIfExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInteger;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqual2Exp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqualExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsInvalidExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsUndefinedExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLetExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterateCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGMapExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGMapPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOppositePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGReal;
import org.eclipse.ocl.examples.codegen.cgmodel.CGShadowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGShadowPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGString;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTemplateParameterExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTupleExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePartCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.generator.GenModelException;
import org.eclipse.ocl.examples.codegen.generator.GenModelHelper;
import org.eclipse.ocl.examples.codegen.generator.IterationHelper;
import org.eclipse.ocl.examples.codegen.library.NativeProperty;
import org.eclipse.ocl.examples.codegen.library.NativeStaticOperation;
import org.eclipse.ocl.examples.codegen.library.NativeVisitorOperation;
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
import org.eclipse.ocl.pivot.IteratorExp;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.MapLiteralExp;
import org.eclipse.ocl.pivot.MapLiteralPart;
import org.eclipse.ocl.pivot.NamedElement;
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
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.TuplePartId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
import org.eclipse.ocl.pivot.internal.ecore.EObjectOperation;
import org.eclipse.ocl.pivot.internal.library.CompositionProperty;
import org.eclipse.ocl.pivot.internal.library.ConstrainedOperation;
import org.eclipse.ocl.pivot.internal.library.ConstrainedProperty;
import org.eclipse.ocl.pivot.internal.library.EInvokeOperation;
import org.eclipse.ocl.pivot.internal.library.ExplicitNavigationProperty;
import org.eclipse.ocl.pivot.internal.library.ImplicitNonCompositionProperty;
import org.eclipse.ocl.pivot.internal.library.StaticProperty;
import org.eclipse.ocl.pivot.internal.library.StereotypeProperty;
import org.eclipse.ocl.pivot.internal.library.TuplePartProperty;
import org.eclipse.ocl.pivot.internal.manager.FinalAnalysis;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.LibraryIteration;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.library.LibraryProperty;
import org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInvalidOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsUndefinedOperation;
import org.eclipse.ocl.pivot.library.oclany.OclElementOclContainerProperty;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.Unlimited;
import org.eclipse.ocl.pivot.values.UnlimitedValue;

import com.google.common.collect.Iterables;

/**
 * The AS2CGVisitor performs the first stage of code generation by converting the Pivot AST to the CG AST.
 */
public class AS2CGVisitor extends AbstractExtendingVisitor<@Nullable CGNamedElement, @NonNull CodeGenAnalyzer>
{
	protected final @NonNull CodeGenerator codeGenerator;
	protected final @NonNull EnvironmentFactoryInternalExtension environmentFactory;
	protected final @NonNull PivotMetamodelManager metamodelManager;
	protected final @NonNull GenModelHelper genModelHelper;

	/**
	 * Non-null while a class conversion is in progress. Used to ensure to provide a host
	 * for native operations that realize OCL defined operations. Class conversions do not nest
	 * so no stack is needed although the API might imply that one is provided.
	 */
	private @Nullable CGClass currentClass = null;

	/**
	 * The native operations that are being converted and so do not yet appear as operations of
	 * the currentClass. The stack of partial conversions avoids an infinite number of operations
	 * being created for a recursive call.
	 */
	private final @NonNull Map<@NonNull Operation, @NonNull CGOperation> asFinalOperation2cgOperation = new HashMap<>();
	private final @NonNull Map<@NonNull Operation, @NonNull CGOperation> asVirtualOperation2cgOperation = new HashMap<>();

	public static final class CGTuplePartNameComparator implements Comparator<@NonNull CGTuplePart>
	{
		public static final @NonNull CGTuplePartNameComparator INSTANCE = new CGTuplePartNameComparator();

		@Override
		public int compare(@NonNull CGTuplePart o1, @NonNull CGTuplePart o2) {
			return ClassUtil.safeCompareTo(o1.getName(), o2.getName());
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

		public void putParameter(@NonNull VariableDeclaration aParameter, @NonNull CGParameter cgParameter) {
			cgParameters.put(aParameter, cgParameter);
			cgVariables.put(aParameter, cgParameter);
		}

		public void putVariable(@NonNull VariableDeclaration asVariable, @NonNull CGVariable cgVariable) {
			cgVariables.put(asVariable, cgVariable);
		}
	}

	/**
	 * Mapping from an AS Variable to a CG Variable, maintained as a stack that is pushed when inline operations are exapanded.
	 */
	private @NonNull Variables variablesStack = new Variables(null);

	public AS2CGVisitor(@NonNull CodeGenAnalyzer analyzer) {
		super(analyzer);
		codeGenerator = context.getCodeGenerator();
		environmentFactory = (EnvironmentFactoryInternalExtension) codeGenerator.getEnvironmentFactory();
		metamodelManager = environmentFactory.getMetamodelManager();
		genModelHelper = codeGenerator.getGenModelHelper();
	}

	protected void addParameter(@NonNull VariableDeclaration asVariable, @NonNull CGParameter cgParameter) {
		variablesStack.putParameter(asVariable, cgParameter);
	}

	public @Nullable CGVariable basicGetParameter(@NonNull Variable aParameter) {
		return variablesStack.getParameter(aParameter);
	}

	protected @NonNull CGValuedElement cachedOperationCall(@NonNull OperationCallExp element, @NonNull CGClass currentClass, CGValuedElement cgSource,
			@NonNull Operation asOperation, @Nullable Iterable<@NonNull Operation> asOverrideOperations) {
		List<@NonNull Operation> asNewOperations = new ArrayList<>();
		List<@NonNull CGCachedOperation> cgOperations = new ArrayList<>();
		if (asOverrideOperations != null) {
			assert Iterables.contains(asOverrideOperations, asOperation);
			for (@NonNull Operation asOverride : asOverrideOperations) {
				CGOperation cgOperation = asFinalOperation2cgOperation.get(asOverride);
				if (cgOperation == null) {
					cgOperation = createFinalCGOperationWithoutBody(asOverride);
					asNewOperations.add(asOverride);
				}
				cgOperations.add((CGCachedOperation) cgOperation);
			}
		}
		else {
			CGOperation cgOperation = asFinalOperation2cgOperation.get(asOperation);
			if (cgOperation == null) {
				cgOperation = createFinalCGOperationWithoutBody(asOperation);
				asNewOperations.add(asOperation);
			}
		}
		for (@NonNull Operation asNewOperation : asNewOperations) {
			CGOperation cgOperation = visitOperation(asNewOperation);
			currentClass.getOperations().add(cgOperation);
		}
		CGCachedOperationCallExp cgOperationCallExp = CGModelFactory.eINSTANCE.createCGCachedOperationCallExp();
		cgOperationCallExp.setSource(cgSource);
		cgOperationCallExp.setThisIsSelf(false);
		for (@NonNull OCLExpression pArgument : ClassUtil.nullFree(element.getOwnedArguments())) {
			CGValuedElement cgArgument = doVisit(CGValuedElement.class, pArgument);
			cgOperationCallExp.getArguments().add(cgArgument);
		}
		setAst(cgOperationCallExp, element);
		cgOperationCallExp.setReferredOperation(asOperation);
		if (asOverrideOperations != null) {
			CGOperation cgOperation = asVirtualOperation2cgOperation.get(asOperation);
			if (cgOperation == null) {
				cgOperation = createVirtualCGOperationWithoutBody(asOperation, cgOperations);
				//				asNewOperations.add(asOperation);
				currentClass.getOperations().add(cgOperation);
			}
		}
		return cgOperationCallExp;
	}

	protected @NonNull CGValuedElement constrainedOperationCall(@NonNull OperationCallExp element,
			CGValuedElement cgSource, @NonNull Operation finalOperation, @NonNull ConstrainedOperation constrainedOperation) {
		@NonNull CGLibraryOperationCallExp cgOperationCallExp = CGModelFactory.eINSTANCE.createCGLibraryOperationCallExp();
		cgOperationCallExp.setSource(cgSource);
		//		cgOperationCallExp.setThisIsSelf(false);
		for (@NonNull OCLExpression pArgument : ClassUtil.nullFree(element.getOwnedArguments())) {
			CGValuedElement cgArgument = doVisit(CGValuedElement.class, pArgument);
			cgOperationCallExp.getArguments().add(cgArgument);
		}
		setAst(cgOperationCallExp, element);
		cgOperationCallExp.setReferredOperation(finalOperation);
		cgOperationCallExp.setLibraryOperation(constrainedOperation);
		if (codeGenerator.addConstrainedOperation(finalOperation)) {
			//			CGNamedElement cgOperation = finalOperation.accept(this);
			//			if (cgOperation != null) {
			//				cgOperation.toString();
			//			}
		}
		return cgOperationCallExp;
	}

	protected @NonNull CGLetExp createCGLetExp(@NonNull TypedElement element, @NonNull CGFinalVariable cgVariable, @NonNull CGValuedElement cgIn) {
		CGLetExp cgLetExp = CGModelFactory.eINSTANCE.createCGLetExp();
		setAst(cgLetExp, element);
		cgLetExp.setInit(cgVariable);
		cgLetExp.setIn(cgIn);
		return cgLetExp;
	}

	protected @NonNull CGOperation createFinalCGOperationWithoutBody(@NonNull Operation asOperation) {
		CGOperation cgOperation = null;
		LibraryFeature libraryOperation = metamodelManager.getImplementation(asOperation);
		if ((libraryOperation instanceof NativeStaticOperation) || (libraryOperation instanceof NativeVisitorOperation)) {
			CGNativeOperation cgNativeOperation = CGModelFactory.eINSTANCE.createCGNativeOperation();
			cgOperation = cgNativeOperation;
		}
		else if (libraryOperation instanceof EObjectOperation) {
			EOperation eOperation = (EOperation) asOperation.getESObject();
			if (eOperation != null) {
				CGEcoreOperation cgEcoreOperation = CGModelFactory.eINSTANCE.createCGEcoreOperation();
				cgEcoreOperation.setEOperation(eOperation);
				cgOperation = cgEcoreOperation;
			}
		}
		else if (libraryOperation instanceof ConstrainedOperation) {
			org.eclipse.ocl.pivot.Package pPackage = asOperation.getOwningClass().getOwningPackage();
			cgOperation = pPackage instanceof Library ? CGModelFactory.eINSTANCE.createCGLibraryOperation()
				: CGModelFactory.eINSTANCE.createCGCachedOperation();
		}
		if (cgOperation == null) {
			cgOperation = CGModelFactory.eINSTANCE.createCGLibraryOperation();
		}
		setAst(cgOperation, asOperation);
		cgOperation.setRequired(asOperation.isIsRequired());
		CGOperation oldCGOperation = asFinalOperation2cgOperation.put(asOperation, cgOperation);
		assert oldCGOperation == null;
		return cgOperation;
	}

	protected @NonNull CGOperation createVirtualCGOperationWithoutBody(@NonNull Operation asOperation, @NonNull List<@NonNull CGCachedOperation> cgOperations) {
		CGCachedOperation cgOperation = CGModelFactory.eINSTANCE.createCGCachedOperation();
		setAst(cgOperation, asOperation);
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
		CGOperation oldCGOperation = asVirtualOperation2cgOperation.put(asOperation, cgOperation);
		assert oldCGOperation == null;
		return cgOperation;
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
		setAst(cgVariable, asVariable);
		//		cgVariable.setInit(doVisit(CGValuedElement.class, asVariable.getInitExpression()));
		return cgVariable;
	}

	protected CGVariable createCGVariable(@NonNull Variable contextVariable, @NonNull OCLExpression source) {
		CGVariable cgVariable = createCGVariable(contextVariable);
		cgVariable.setInit(doVisit(CGValuedElement.class, source));
		return cgVariable;
	}

	public CGVariableExp createCGVariableExp(@NonNull VariableExp asVariableExp, @Nullable VariableDeclaration referredVariable) {
		CGVariableExp cgVariableExp = CGModelFactory.eINSTANCE.createCGVariableExp();
		setAst(cgVariableExp, asVariableExp);
		if (referredVariable != null) {
			cgVariableExp.setReferredVariable(getVariable(referredVariable));
		}
		return cgVariableExp;
	}

	protected <T extends EObject> @NonNull T createCopy(@NonNull T anEObject) {
		return EcoreUtil.copy(anEObject);
	}

	/**
	 * Wrap asIn in a LetExp in which a clone of asInit is assigned to asVariable.
	 * @since 1.3
	 */
	protected @NonNull OCLExpression createLetExp(@Nullable Variable asVariable, @Nullable OCLExpression asInit, @NonNull OCLExpression asIn) {
		if ((asVariable == null) || (asInit == null)) {
			return asIn;
		}
		OCLExpression asInitClone = createCopy(asInit);
		asVariable.setOwnedInit(asInitClone);
		return PivotUtil.createLetExp(asVariable, asIn);
	}

	protected void createParameters(@NonNull CGOperation cgOperation, @NonNull ExpressionInOCL expressionInOCL) {
		Variable contextVariable = expressionInOCL.getOwnedContext();
		if (contextVariable != null) {
			CGParameter cgParameter = getParameter(contextVariable, null);
			//			cgParameter.setTypeId(context.getTypeId(JavaConstants.getJavaTypeId(Object.class)));
			//			cgParameter.setRequired(contextVariable.isIsRequired());
			cgOperation.getParameters().add(cgParameter);
		}
		for (@NonNull Variable parameterVariable : ClassUtil.nullFree(expressionInOCL.getOwnedParameters())) {
			CGParameter cgParameter = getParameter(parameterVariable, null);
			//			cgParameter.setTypeId(context.getTypeId(JavaConstants.getJavaTypeId(Object.class)));
			//			cgParameter.setRequired(parameterVariable.isIsRequired());
			cgOperation.getParameters().add(cgParameter);
		}
	}

	public @NonNull <T extends CGElement> T doVisit(@NonNull Class<T> requiredClass, @Nullable Element pElement) {
		if (pElement == null) {
			throw new NullPointerException("null source for mapping to " + requiredClass.getName());
		}
		CGNamedElement cgElement = pElement.accept(this);
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

	protected @NonNull CGIterationCallExp generateIterateExp(@NonNull CGValuedElement cgSource, @NonNull IterateExp element) {
		Iteration asIteration = element.getReferredIteration();
		LibraryIteration libraryIteration = null;
		if (asIteration != null) {
			libraryIteration = (LibraryIteration) metamodelManager.getImplementation(asIteration);
			IterationHelper iterationHelper = codeGenerator.getIterationHelper(asIteration);
			if (iterationHelper != null) {
				CGBuiltInIterationCallExp cgBuiltInIterationCallExp = CGModelFactory.eINSTANCE.createCGBuiltInIterationCallExp();
				cgBuiltInIterationCallExp.setReferredIteration(asIteration);
				cgBuiltInIterationCallExp.setSource(cgSource);
				for (@NonNull Variable iterator : PivotUtil.getOwnedIterators(element)) {
					CGIterator cgIterator = getNullableIterator(iterator);
					cgBuiltInIterationCallExp.getIterators().add(cgIterator);
				}
				for (@NonNull Variable coIterator : PivotUtil.getOwnedCoIterators(element)) {
					CGIterator cgCoIterator = getNullableIterator(coIterator);
					cgBuiltInIterationCallExp.getCoIterators().add(cgCoIterator);
				}
				if (asIteration.getOwnedParameters().get(0).isIsRequired()) {
					cgBuiltInIterationCallExp.getBody().setRequired(true);
				}
				cgBuiltInIterationCallExp.setInvalidating(false);
				cgBuiltInIterationCallExp.setValidating(false);
				//				cgBuiltInIterationCallExp.setNonNull();
				setAst(cgBuiltInIterationCallExp, element);
				@SuppressWarnings("null")@NonNull Variable accumulator = element.getOwnedResult();
				CGIterator cgAccumulator = getNullableIterator(accumulator);
				cgAccumulator.setInit(doVisit(CGValuedElement.class, accumulator.getOwnedInit()));
				cgBuiltInIterationCallExp.setAccumulator(cgAccumulator);
				cgBuiltInIterationCallExp.setBody(doVisit(CGValuedElement.class, element.getOwnedBody()));
				/*			CGTypeId cgAccumulatorId = iterationHelper.getAccumulatorTypeId(context, cgBuiltInIterationCallExp);
				if (cgAccumulatorId != null) {
					CGIterator cgAccumulator = CGModelFactory.eINSTANCE.createCGIterator();
					cgAccumulator.setName("accumulator");
					cgAccumulator.setTypeId(cgAccumulatorId);
//					cgAccumulator.setRequired(true);
					cgAccumulator.setNonNull();
					cgAccumulator.setNonInvalid();
					cgBuiltInIterationCallExp.setAccumulator(cgAccumulator);
//					variablesStack.putVariable(asVariable, cgAccumulator);
//					cgAccumulator.setNonInvalid();
				} */
				return cgBuiltInIterationCallExp;
			}
		}
		CGLibraryIterateCallExp cgLibraryIterateCallExp = CGModelFactory.eINSTANCE.createCGLibraryIterateCallExp();
		cgLibraryIterateCallExp.setLibraryIteration(libraryIteration);
		cgLibraryIterateCallExp.setReferredIteration(asIteration);
		setAst(cgLibraryIterateCallExp, element);
		if (asIteration != null) {
			cgLibraryIterateCallExp.setInvalidating(asIteration.isIsInvalidating());
			cgLibraryIterateCallExp.setValidating(asIteration.isIsValidating());
		}
		cgLibraryIterateCallExp.setSource(cgSource);
		for (@NonNull Variable iterator : PivotUtil.getOwnedIterators(element)) {
			cgLibraryIterateCallExp.getIterators().add(getIterator(iterator));
		}
		for (@NonNull Variable coIterator : PivotUtil.getOwnedCoIterators(element)) {
			cgLibraryIterateCallExp.getCoIterators().add(getIterator(coIterator));
		}
		Variable result = element.getOwnedResult();
		if (result != null) {
			CGIterator cgResult = getIterator(result);
			cgLibraryIterateCallExp.setResult(cgResult);
			CGValuedElement cgInitExpression = doVisit(CGValuedElement.class, result.getOwnedInit());
			cgResult.setInit(cgInitExpression);
		}
		cgLibraryIterateCallExp.setBody(doVisit(CGValuedElement.class, element.getOwnedBody()));
		if (asIteration != null) {
			cgLibraryIterateCallExp.setRequired(asIteration.isIsRequired());
		}
		//		cgIterationCallExp.setOperation(getOperation(element.getReferredOperation()));
		return cgLibraryIterateCallExp;
	}

	protected @NonNull CGIterator getNullableIterator(@NonNull Variable iterator) {
		CGIterator cgIterator = getIterator(iterator);
		cgIterator.setTypeId(context.getTypeId(iterator.getTypeId()));
		cgIterator.setRequired(iterator.isIsRequired());
		if (iterator.isIsRequired()) {
			cgIterator.setNonNull();
		}
		cgIterator.setNonInvalid();
		return cgIterator;
	}

	protected @NonNull CGIterationCallExp generateIteratorExp(@NonNull CGValuedElement cgSource, @NonNull IteratorExp element) {
		Iteration asIteration = ClassUtil.nonNullState(element.getReferredIteration());
		LibraryIteration libraryIteration = (LibraryIteration) metamodelManager.getImplementation(asIteration);
		IterationHelper iterationHelper = codeGenerator.getIterationHelper(asIteration);
		boolean isRequired = element.isIsRequired();
		if (iterationHelper != null) {
			boolean isNonNullAccumulator = iterationHelper.isNonNullAccumulator(element);
			CGBuiltInIterationCallExp cgBuiltInIterationCallExp = CGModelFactory.eINSTANCE.createCGBuiltInIterationCallExp();
			cgBuiltInIterationCallExp.setReferredIteration(asIteration);
			cgBuiltInIterationCallExp.setSource(cgSource);
			for (@NonNull Variable iterator : PivotUtil.getOwnedIterators(element)) {
				CGIterator cgIterator = getNullableIterator(iterator);
				cgBuiltInIterationCallExp.getIterators().add(cgIterator);
			}
			for (@NonNull Variable coIterator : PivotUtil.getOwnedCoIterators(element)) {
				CGIterator cgCoIterator = getNullableIterator(coIterator);
				cgBuiltInIterationCallExp.getCoIterators().add(cgCoIterator);
			}
			cgBuiltInIterationCallExp.setInvalidating(false);
			cgBuiltInIterationCallExp.setValidating(false);
			//			cgBuiltInIterationCallExp.setNonNull();
			setAst(cgBuiltInIterationCallExp, element);
			cgBuiltInIterationCallExp.setBody(doVisit(CGValuedElement.class, element.getOwnedBody()));
			if (asIteration.getOwnedParameters().get(0).isIsRequired()) {
				cgBuiltInIterationCallExp.getBody().setRequired(true);
			}
			CGTypeId cgAccumulatorId = iterationHelper.getAccumulatorTypeId(context, cgBuiltInIterationCallExp);
			if (cgAccumulatorId != null) {
				CGAccumulator cgAccumulator = CGModelFactory.eINSTANCE.createCGAccumulator();
				cgAccumulator.setName("accumulator");
				cgAccumulator.setTypeId(cgAccumulatorId);
				if (isNonNullAccumulator) {
					cgAccumulator.setNonNull();
				}
				if (!asIteration.isIsValidating()) {
					cgAccumulator.setNonInvalid();
				}
				cgBuiltInIterationCallExp.setAccumulator(cgAccumulator);
				//				variablesStack.putVariable(asVariable, cgAccumulator);
				//				cgAccumulator.setNonInvalid();
			}
			cgBuiltInIterationCallExp.setRequired(isRequired);
			if (isRequired) {
				cgBuiltInIterationCallExp.setNonNull();
			}
			return cgBuiltInIterationCallExp;
		}
		CGLibraryIterationCallExp cgLibraryIterationCallExp = CGModelFactory.eINSTANCE.createCGLibraryIterationCallExp();
		cgLibraryIterationCallExp.setLibraryIteration(libraryIteration);
		cgLibraryIterationCallExp.setReferredIteration(asIteration);
		setAst(cgLibraryIterationCallExp, element);
		cgLibraryIterationCallExp.setInvalidating(asIteration.isIsInvalidating());
		cgLibraryIterationCallExp.setValidating(asIteration.isIsValidating());
		cgLibraryIterationCallExp.setSource(cgSource);
		for (@NonNull Variable iterator : PivotUtil.getOwnedIterators(element)) {
			cgLibraryIterationCallExp.getIterators().add(getIterator(iterator));
		}
		for (@NonNull Variable coIterator : PivotUtil.getOwnedCoIterators(element)) {
			cgLibraryIterationCallExp.getCoIterators().add(getIterator(coIterator));
		}
		cgLibraryIterationCallExp.setBody(doVisit(CGValuedElement.class, element.getOwnedBody()));
		cgLibraryIterationCallExp.setRequired(isRequired);
		//		cgIterationCallExp.setOperation(getOperation(element.getReferredOperation()));
		return cgLibraryIterationCallExp;
	}

	protected @NonNull CGValuedElement generateOperationCallExp(@Nullable CGValuedElement cgSource, @NonNull OperationCallExp element) {
		Operation asOperation = ClassUtil.nonNullState(element.getReferredOperation());
		boolean isRequired = asOperation.isIsRequired();
		OCLExpression pSource = element.getOwnedSource();
		LibraryFeature libraryOperation = metamodelManager.getImplementation(asOperation);
		CGOperationCallExp cgOperationCallExp = null;
		if (libraryOperation instanceof OclAnyOclIsInvalidOperation) {
			CGIsInvalidExp cgIsInvalidExp = CGModelFactory.eINSTANCE.createCGIsInvalidExp();
			cgIsInvalidExp.setSource(cgSource);
			setAst(cgIsInvalidExp, element);
			cgIsInvalidExp.setInvalidating(false);
			cgIsInvalidExp.setValidating(true);
			return cgIsInvalidExp;
		}
		else if (libraryOperation instanceof OclAnyOclIsUndefinedOperation) {
			CGIsUndefinedExp cgIsUndefinedExp = CGModelFactory.eINSTANCE.createCGIsUndefinedExp();
			cgIsUndefinedExp.setSource(cgSource);
			setAst(cgIsUndefinedExp, element);
			cgIsUndefinedExp.setInvalidating(false);
			cgIsUndefinedExp.setValidating(true);
			return cgIsUndefinedExp;
		}
		else if (libraryOperation instanceof OclAnyEqualOperation) {
			OCLExpression pArgument = PivotUtil.getOwnedArgument(element, 0);
			CGValuedElement cgArgument = doVisit(CGValuedElement.class, pArgument);
			CGIsEqualExp cgIsEqualExp = CGModelFactory.eINSTANCE.createCGIsEqualExp();
			cgIsEqualExp.setNotEquals(libraryOperation instanceof OclAnyNotEqualOperation);
			cgIsEqualExp.setSource(cgSource);
			cgIsEqualExp.setArgument(cgArgument);
			setAst(cgIsEqualExp, element);
			cgIsEqualExp.setInvalidating(false);
			cgIsEqualExp.setValidating(true);
			return cgIsEqualExp;
		}
		else if (libraryOperation instanceof NativeStaticOperation) {
			LanguageExpression bodyExpression = asOperation.getBodyExpression();
			if (bodyExpression != null) {
				CGValuedElement cgOperationCallExp2 = inlineOperationCall(element, bodyExpression);
				if (cgOperationCallExp2 != null) {
					return cgOperationCallExp2;
				}
			}
			CGNativeOperationCallExp cgNativeOperationCallExp = CGModelFactory.eINSTANCE.createCGNativeOperationCallExp();
			cgNativeOperationCallExp.setSource(cgSource);
			cgNativeOperationCallExp.setThisIsSelf(true);
			for (@NonNull OCLExpression pArgument : ClassUtil.nullFree(element.getOwnedArguments())) {
				CGValuedElement cgArgument = doVisit(CGValuedElement.class, pArgument);
				cgNativeOperationCallExp.getArguments().add(cgArgument);
			}
			setAst(cgNativeOperationCallExp, element);
			cgNativeOperationCallExp.setReferredOperation(asOperation);
			return cgNativeOperationCallExp;
		}
		else if (libraryOperation instanceof NativeVisitorOperation) {
			LanguageExpression bodyExpression = asOperation.getBodyExpression();
			if (bodyExpression != null) {
				CGValuedElement cgOperationCallExp2 = inlineOperationCall(element, bodyExpression);
				if (cgOperationCallExp2 != null) {
					return cgOperationCallExp2;
				}
			}
			CGNativeOperationCallExp cgNativeOperationCallExp = CGModelFactory.eINSTANCE.createCGNativeOperationCallExp();
			cgNativeOperationCallExp.setSource(cgSource);
			cgNativeOperationCallExp.setThisIsSelf(true);
			for (@NonNull OCLExpression pArgument : ClassUtil.nullFree(element.getOwnedArguments())) {
				CGValuedElement cgArgument = doVisit(CGValuedElement.class, pArgument);
				cgNativeOperationCallExp.getArguments().add(cgArgument);
			}
			setAst(cgNativeOperationCallExp, element);
			cgNativeOperationCallExp.setReferredOperation(asOperation);
			return cgNativeOperationCallExp;
		}
		else if (libraryOperation instanceof ConstrainedOperation) {
			if (pSource != null) {
				Type sourceType = ClassUtil.nonNullState(pSource.getType());
				Operation finalOperation = codeGenerator.isFinal(asOperation, (org.eclipse.ocl.pivot.Class)sourceType);	// FIXME cast
				CGClass currentClass2 = currentClass;
				if (finalOperation != null) {
					LanguageExpression bodyExpression = asOperation.getBodyExpression();
					if (bodyExpression != null) {
						CGValuedElement cgOperationCallExp2 = inlineOperationCall(element, bodyExpression);
						if (cgOperationCallExp2 != null) {
							return cgOperationCallExp2;
						} else if (currentClass2 != null) {
							return cachedOperationCall(element, currentClass2, cgSource, finalOperation, null);
						} else {
							return constrainedOperationCall(element, cgSource, finalOperation, (ConstrainedOperation)libraryOperation);
						}
					}
				}
				if (currentClass2 != null) {
					Iterable<@NonNull Operation> overrides = environmentFactory.getMetamodelManager().getFinalAnalysis().getOverrides(asOperation);
					return cachedOperationCall(element, currentClass2, cgSource, asOperation, overrides);
				} else {
					Operation baseOperation = asOperation;	// FIXME
					return constrainedOperationCall(element, cgSource, baseOperation, (ConstrainedOperation)libraryOperation);
				}
			}
		}
		else if ((libraryOperation instanceof EObjectOperation) || (libraryOperation instanceof EInvokeOperation)) {
			EOperation eOperation = (EOperation) asOperation.getESObject();
			if (eOperation != null) {
				try {
					genModelHelper.getOperationAccessor(asOperation);
					CGEcoreOperationCallExp cgEcoreOperationCallExp = CGModelFactory.eINSTANCE.createCGEcoreOperationCallExp();
					cgEcoreOperationCallExp.setEOperation(eOperation);
					Boolean ecoreIsRequired = codeGenerator.isNonNull(element);
					if (ecoreIsRequired != null) {
						isRequired = ecoreIsRequired;
					}
					cgOperationCallExp = cgEcoreOperationCallExp;
				} catch (GenModelException e) {
					codeGenerator.addProblem(e);
					org.eclipse.ocl.pivot.Class asType = asOperation.getOwningClass();
					String className = asType.getInstanceClassName();
					if (className != null) {
						CGNativeOperationCallExp cgNativeOperationCallExp = CGModelFactory.eINSTANCE.createCGNativeOperationCallExp();
						cgNativeOperationCallExp.setSource(cgSource);
						cgNativeOperationCallExp.setThisIsSelf(true);
						for (@NonNull OCLExpression pArgument : ClassUtil.nullFree(element.getOwnedArguments())) {
							CGValuedElement cgArgument = doVisit(CGValuedElement.class, pArgument);
							cgNativeOperationCallExp.getArguments().add(cgArgument);
						}
						setAst(cgNativeOperationCallExp, element);
						cgNativeOperationCallExp.setReferredOperation(asOperation);
						cgNativeOperationCallExp.setInvalidating(asOperation.isIsInvalidating());
						cgNativeOperationCallExp.setValidating(asOperation.isIsValidating());
						cgNativeOperationCallExp.setRequired(isRequired);
						return cgNativeOperationCallExp;
					}
				}
			}
		}
		else {
			//FIXME BUG 458774			LanguageExpression bodyExpression = asOperation.getBodyExpression();
			//			if (bodyExpression != null) {
			//				CGValuedElement cgOperationCallExp2 = inlineOperationCall(element, bodyExpression);
			//				if (cgOperationCallExp2 != null) {
			//					return cgOperationCallExp2;
			//				}
			//			}
			CGLibraryOperationCallExp cgLibraryOperationCallExp = CGModelFactory.eINSTANCE.createCGLibraryOperationCallExp();
			cgLibraryOperationCallExp.setLibraryOperation((LibraryOperation) libraryOperation);
			cgLibraryOperationCallExp.setReferredOperation(asOperation);
			cgOperationCallExp = cgLibraryOperationCallExp;
		}
		if (cgOperationCallExp == null) {
			CGExecutorOperationCallExp cgExecutorOperationCallExp = CGModelFactory.eINSTANCE.createCGExecutorOperationCallExp();
			CGExecutorOperation cgExecutorOperation = context.createExecutorOperation(asOperation);
			cgExecutorOperationCallExp.setExecutorOperation(cgExecutorOperation);
			cgExecutorOperationCallExp.getOwns().add(cgExecutorOperation);
			cgOperationCallExp = cgExecutorOperationCallExp;
		}
		cgOperationCallExp.setReferredOperation(asOperation);
		setAst(cgOperationCallExp, element);
		cgOperationCallExp.setInvalidating(asOperation.isIsInvalidating());
		cgOperationCallExp.setValidating(asOperation.isIsValidating());
		cgOperationCallExp.setRequired(isRequired);
		cgOperationCallExp.setSource(cgSource);
		//		cgOperationCallExp.getDependsOn().add(cgSource);
		for (@NonNull OCLExpression pArgument : ClassUtil.nullFree(element.getOwnedArguments())) {
			CGValuedElement cgArgument = doVisit(CGValuedElement.class, pArgument);
			cgOperationCallExp.getArguments().add(cgArgument);
			//			cgOperationCallExp.getDependsOn().add(cgArgument);
		}
		//		cgOperationCallExp.setOperation(getOperation(element.getReferredOperation()));
		return cgOperationCallExp;
	}

	protected @NonNull CGValuedElement generateOppositePropertyCallExp(@NonNull CGValuedElement cgSource, @NonNull OppositePropertyCallExp element) {
		Property asOppositeProperty = ClassUtil.nonNullModel(element.getReferredProperty());
		Property asProperty = ClassUtil.nonNullModel(asOppositeProperty.getOpposite());
		boolean isRequired = asProperty.isIsRequired();
		LibraryProperty libraryProperty = metamodelManager.getImplementation(element, null, asProperty);
		CGOppositePropertyCallExp cgPropertyCallExp = null;
		if (isEcoreProperty(libraryProperty)) {
			EStructuralFeature eStructuralFeature = (EStructuralFeature) asProperty.getESObject();
			if (eStructuralFeature != null) {
				try {
					genModelHelper.getGetAccessor(eStructuralFeature);
					CGEcoreOppositePropertyCallExp cgEcorePropertyCallExp = CGModelFactory.eINSTANCE.createCGEcoreOppositePropertyCallExp();
					cgEcorePropertyCallExp.setEStructuralFeature(eStructuralFeature);
					Boolean ecoreIsRequired = codeGenerator.isNonNull(asProperty);
					if (ecoreIsRequired != null) {
						isRequired = ecoreIsRequired;
					}
					cgPropertyCallExp = cgEcorePropertyCallExp;
				} catch (GenModelException e) {
					codeGenerator.addProblem(e);
				}
			}
		}
		else {
	//		throw new UnsupportedOperationException();
		}
		if (cgPropertyCallExp == null) {
			CGExecutorOppositePropertyCallExp cgExecutorPropertyCallExp = CGModelFactory.eINSTANCE.createCGExecutorOppositePropertyCallExp();
			CGExecutorProperty cgExecutorProperty = context.createExecutorOppositeProperty(asProperty);
			cgExecutorPropertyCallExp.setExecutorProperty(cgExecutorProperty);
			cgExecutorPropertyCallExp.getOwns().add(cgExecutorProperty);
			cgPropertyCallExp = cgExecutorPropertyCallExp;
		}
		cgPropertyCallExp.setReferredProperty(asProperty);
		setAst(cgPropertyCallExp, element);
		cgPropertyCallExp.setRequired(isRequired);
		cgPropertyCallExp.setSource(cgSource);
		return cgPropertyCallExp;
	}

	protected @NonNull CGValuedElement generatePropertyCallExp(@NonNull CGValuedElement cgSource, @NonNull PropertyCallExp element) {
		Property asProperty = ClassUtil.nonNullModel(element.getReferredProperty());
		boolean isRequired = asProperty.isIsRequired();
		LibraryProperty libraryProperty = metamodelManager.getImplementation(element, null, asProperty);
		CGPropertyCallExp cgPropertyCallExp = null;
		if (libraryProperty instanceof NativeProperty) {
			CGNativePropertyCallExp cgNativePropertyCallExp = CGModelFactory.eINSTANCE.createCGNativePropertyCallExp();
			cgPropertyCallExp = cgNativePropertyCallExp;
		}
		else if (isEcoreProperty(libraryProperty)) {
			EStructuralFeature eStructuralFeature = (EStructuralFeature) asProperty.getESObject();
			if (eStructuralFeature != null) {
				try {
					genModelHelper.getGetAccessor(eStructuralFeature);
					CGEcorePropertyCallExp cgEcorePropertyCallExp = CGModelFactory.eINSTANCE.createCGEcorePropertyCallExp();
					cgEcorePropertyCallExp.setEStructuralFeature(eStructuralFeature);
					//					Boolean ecoreIsRequired = codeGenerator.isNonNull(asProperty);
					//					if (ecoreIsRequired != null) {
					//						isRequired = ecoreIsRequired;
					//					}
					isRequired = asProperty.isIsRequired();
					cgPropertyCallExp = cgEcorePropertyCallExp;
				} catch (GenModelException e) {
					codeGenerator.addProblem(e);		// FIXME drop through to better default
				}
			}
		}
		else if (libraryProperty instanceof TuplePartProperty) {
			CGTuplePartCallExp cgTuplePartCallExp = CGModelFactory.eINSTANCE.createCGTuplePartCallExp();
			cgTuplePartCallExp.setAstTuplePartId(IdManager.getTuplePartId(asProperty));
			cgPropertyCallExp = cgTuplePartCallExp;
		}
		else if (libraryProperty instanceof OclElementOclContainerProperty) {
			CGEcorePropertyCallExp cgEcorePropertyCallExp = CGModelFactory.eINSTANCE.createCGEcorePropertyCallExp();
			cgEcorePropertyCallExp.setEStructuralFeature(OCLstdlibPackage.Literals.OCL_ELEMENT__OCL_CONTAINER);
			cgPropertyCallExp = cgEcorePropertyCallExp;
		}
		else {
			CGLibraryPropertyCallExp cgLibraryPropertyCallExp = CGModelFactory.eINSTANCE.createCGLibraryPropertyCallExp();
			cgLibraryPropertyCallExp.setLibraryProperty(libraryProperty);
			cgPropertyCallExp = cgLibraryPropertyCallExp;
		}
		if (cgPropertyCallExp == null) {
			CGExecutorPropertyCallExp cgExecutorPropertyCallExp = CGModelFactory.eINSTANCE.createCGExecutorPropertyCallExp();
			CGExecutorProperty cgExecutorProperty = context.createExecutorProperty(asProperty);
			cgExecutorPropertyCallExp.setExecutorProperty(cgExecutorProperty);
			cgExecutorPropertyCallExp.getOwns().add(cgExecutorProperty);
			cgPropertyCallExp = cgExecutorPropertyCallExp;
		}
		cgPropertyCallExp.setReferredProperty(asProperty);
		setAst(cgPropertyCallExp, element);
		cgPropertyCallExp.setRequired(isRequired || codeGenerator.isPrimitive(cgPropertyCallExp));
		cgPropertyCallExp.setSource(cgSource);
		return cgPropertyCallExp;
	}

	protected @NonNull CGValuedElement generateSafeExclusion(@NonNull CallExp callExp, @NonNull CGValuedElement cgSource) {
		CGLibraryOperationCallExp cgOperationCallExp = CGModelFactory.eINSTANCE.createCGLibraryOperationCallExp();
		cgOperationCallExp.setLibraryOperation(CollectionExcludingOperation.INSTANCE);
		StandardLibraryInternal standardLibrary = environmentFactory.getStandardLibrary();
		CollectionType collectionType = standardLibrary.getCollectionType();
		Operation asOperation = NameUtil.getNameable(collectionType.getOwnedOperations(), "excluding");		// FIXME Promote QVTd's StandardLibraryHelper
		cgOperationCallExp.setReferredOperation(asOperation);
		OCLExpression asSource = callExp.getOwnedSource();
		setAst(cgOperationCallExp, asSource.getTypeId(), "safe_" + callExp.getName() + "_sources"/*nameManagerContext.getSymbolName(callExp, "safe")*/);
		//		cgOperationCallExp.setAst(asSource);
		cgOperationCallExp.setRequired(true);
		cgOperationCallExp.setSource(cgSource);
		CGConstantExp cgArgument = CGModelFactory.eINSTANCE.createCGConstantExp();
		//		cgArgument.setAst(element);
		cgArgument.setReferredConstant(context.getNull());
		cgArgument.setTypeId(context.getTypeId(TypeId.OCL_VOID));
		cgOperationCallExp.getArguments().add(cgArgument);
		return cgOperationCallExp;
	}

	protected @NonNull CGValuedElement generateSafeNavigationGuard(@NonNull CallExp callExp, @NonNull CGFinalVariable cgVariable, @NonNull CGValuedElement cgUnsafeExp) {
		//
		CGVariableExp cgVariableExp = CGModelFactory.eINSTANCE.createCGVariableExp();
		setAst(cgVariableExp, ClassUtil.nonNullModel(callExp.getOwnedSource()));
		cgVariableExp.setReferredVariable(cgVariable);
		//
		CGConstantExp cgNullExpression = context.createCGConstantExp(callExp, context.getNull());
		setAst(cgNullExpression, callExp);
		//
		CGIsEqual2Exp cgCondition = CGModelFactory.eINSTANCE.createCGIsEqual2Exp();
		cgCondition.setSource(cgVariableExp);
		cgCondition.setArgument(cgNullExpression);
		setAst(cgCondition, callExp);
		cgCondition.setTypeId(context.getTypeId(TypeId.BOOLEAN));
		cgCondition.setInvalidating(false);
		cgCondition.setValidating(true);
		//
		CGConstantExp cgThenExpression = context.createCGConstantExp(callExp, context.getNull());
		setAst(cgThenExpression, callExp);
		//
		CGIfExp cgIfExp = CGModelFactory.eINSTANCE.createCGIfExp();
		setAst(cgIfExp, callExp);
		cgIfExp.setName(cgVariable.getName());
		cgIfExp.setCondition(cgCondition);
		cgIfExp.setThenExpression(cgThenExpression);
		cgIfExp.setElseExpression(cgUnsafeExp);
		//
		CGLetExp cgLetExp = createCGLetExp(callExp, cgVariable, cgIfExp);
		return cgLetExp;
	}

	protected @NonNull CGFinalVariable generateSafeVariable(@NonNull CGValuedElement cgSource, String nameHint) {
		CGFinalVariable cgVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
		//			variablesStack.putVariable(asVariable, cgVariable);
		//			setAst(cgVariable, asVariable);
		cgVariable.setInit(cgSource);
		cgVariable.setAst(cgSource.getAst());
		cgVariable.setTypeId(cgSource.getTypeId());
		cgVariable.setName(nameHint);
		return cgVariable;
	}

	protected @NonNull CGVariableExp generateSafeVariableExp(@NonNull OCLExpression element, @NonNull CGFinalVariable cgVariable) {
		CGVariableExp cgVariableExp = CGModelFactory.eINSTANCE.createCGVariableExp();
		setAst(cgVariableExp, element);
		cgVariableExp.setReferredVariable(cgVariable);
		return cgVariableExp;
	}

	public @NonNull CodeGenAnalyzer getAnalyzer() {
		return context;
	}

	public @NonNull CGIterator getIterator(@NonNull VariableDeclaration asVariable) {
		CGParameter cgParameter = (CGParameter) variablesStack.getVariable(asVariable);
		if (cgParameter == null) {
			cgParameter = CGModelFactory.eINSTANCE.createCGIterator();
			setAst(cgParameter, asVariable);
			cgParameter.setTypeId(context.getTypeId(TypeId.OCL_VOID));			// FIXME Java-specific type of polymorphic operation parameter
			variablesStack.putVariable(asVariable, cgParameter);
		}
		return (CGIterator) cgParameter;
	}

	public @NonNull CGVariable getLocalVariable(@NonNull VariableDeclaration asVariable) {
		CGVariable cgVariable = variablesStack.getLocalVariable(asVariable);
		if (cgVariable == null) {
			cgVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
			setAst(cgVariable, asVariable);
			variablesStack.putVariable(asVariable, cgVariable);
		}
		return cgVariable;
	}

	@Deprecated // add explicitName argument
	public @NonNull CGParameter getParameter(@NonNull Variable aParameter) {
		return getParameter(aParameter, null);
	}
	public @NonNull CGParameter getParameter(@NonNull Variable aParameter, @Nullable String explicitName) {
		CGParameter cgParameter = variablesStack.getParameter(aParameter);
		if (cgParameter == null) {
			cgParameter = CGModelFactory.eINSTANCE.createCGParameter();
			if (explicitName == null) {
				context.setNames(cgParameter, aParameter);
			}
			else {
				cgParameter.setName(aParameter.getName());
				cgParameter.setValueName(explicitName);
			}
			setAst(cgParameter, aParameter);
			//			cgParameter.setTypeId(context.getTypeId(aParameter.getTypeId()));
			addParameter(aParameter, cgParameter);
			cgParameter.setRequired(aParameter.isIsRequired());
			if (aParameter.isIsRequired()) {
				cgParameter.setNonNull();
			}
		}
		return cgParameter;
	}

	/**
	 * Return all final operations directly referenced by opaqueExpression, or null if none.
	 * @since 1.3
	 */
	protected @Nullable Iterable<@NonNull Operation> getReferencedFinalOperations(@NonNull FinalAnalysis finalAnalysis, @NonNull LanguageExpression specification) {
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
		Set<@NonNull Operation> referencedOperations = null;
		for (EObject crossReference : EcoreUtil.ExternalCrossReferencer.find(prototype).keySet()) {
			if (crossReference instanceof Operation) {
				Operation operation = (Operation) crossReference;
				if (finalAnalysis.isFinal(operation)) {
					if (referencedOperations == null) {
						referencedOperations = new HashSet<>();
					}
					referencedOperations.add(operation);
				}
			}
		}
		return referencedOperations;
	}

	protected @Nullable Iterable<@NonNull Operation> getReferencedNonFinalOperations(@NonNull FinalAnalysis finalAnalysis, @NonNull LanguageExpression specification) {
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
		Set<@NonNull Operation> referencedOperations = null;
		for (EObject crossReference : EcoreUtil.ExternalCrossReferencer.find(prototype).keySet()) {
			if (crossReference instanceof Operation) {
				Operation operation = (Operation) crossReference;
				if (!finalAnalysis.isFinal(operation)) {
					if (referencedOperations == null) {
						referencedOperations = new HashSet<>();
					}
					referencedOperations.add(operation);
				}
			}
		}
		return referencedOperations;
	}

	@Deprecated
	public @NonNull CGParameter getSelfParameter(@NonNull Variable aParameter) {
		return getParameter(aParameter, null);
	}

	/**
	 * Return all final operations transitively referenced by opaqueExpression, or null if none.
	 * @since 1.3
	 */
	protected void getTransitivelyReferencedFinalOperations(@NonNull Set<@NonNull Operation> alreadyReferencedFinalOperations, @NonNull FinalAnalysis finalAnalysis, @NonNull LanguageExpression expressionInOCL) {
		Iterable<@NonNull Operation> newlyReferencedFinalOperations = getReferencedFinalOperations(finalAnalysis, expressionInOCL);
		if (newlyReferencedFinalOperations != null) {
			for (@NonNull Operation newlyReferencedFinalOperation : newlyReferencedFinalOperations) {
				if (alreadyReferencedFinalOperations.add(newlyReferencedFinalOperation)) {
					LanguageExpression anotherExpressionInOCL = newlyReferencedFinalOperation.getBodyExpression();
					if (anotherExpressionInOCL != null) {
						getTransitivelyReferencedFinalOperations(alreadyReferencedFinalOperations, finalAnalysis, anotherExpressionInOCL);
					}
				}
			}
		}
	}

	public @NonNull CGVariable getVariable(@NonNull VariableDeclaration asVariable) {
		CGVariable cgVariable = variablesStack.getVariable(asVariable);
		if (cgVariable == null) {
			cgVariable = CGModelFactory.eINSTANCE.createCGFinalVariable();
			setAst(cgVariable, asVariable);
			variablesStack.putVariable(asVariable, cgVariable);
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

	protected @Nullable CGValuedElement inlineOperationCall(@NonNull OperationCallExp callExp, @NonNull LanguageExpression specification) {
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
		getTransitivelyReferencedFinalOperations(referencedFinalOperations, finalAnalysis, specification);
		if (referencedFinalOperations.contains(callExp.getReferredOperation())) {
			return null;	// Avoid an infinite inlining recursion.
		}
		Iterable<@NonNull Operation> referencedNonFinalOperations = getReferencedNonFinalOperations(finalAnalysis, specification);
		if (referencedNonFinalOperations != null) {
			return null;	// Simple heavy heuristic
		}
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
		}
	}

	@SuppressWarnings("deprecation")
	protected boolean isEcoreProperty(@NonNull LibraryProperty libraryProperty) {
		return (libraryProperty instanceof ExplicitNavigationProperty)
				|| (libraryProperty instanceof CompositionProperty)
				|| (libraryProperty instanceof ImplicitNonCompositionProperty)		// FIXME surely this isn't Ecore
				|| (libraryProperty instanceof StaticProperty)
				|| (libraryProperty instanceof StereotypeProperty)
				|| (libraryProperty instanceof ConstrainedProperty)
				|| (libraryProperty instanceof org.eclipse.ocl.pivot.internal.ecore.EObjectProperty);
	}

	/**
	 * Pop the CGClass from the stack ensuring that it's at the top
	 * of the stack
	 */
	protected void popCurrentClass(@NonNull CGClass cgClass) {
		assert currentClass == cgClass;
		currentClass = null;
	}

	protected void pushCurrentClass(@NonNull CGClass cgClass) {
		assert currentClass == null;
		currentClass = cgClass;
	}

	protected void setAst(@NonNull CGNamedElement cgElement, @NonNull Element asElement) {
		cgElement.setAst(asElement);
	}

	protected void setAst(@NonNull CGNamedElement cgElement, @NonNull NamedElement asElement) {
		cgElement.setAst(asElement);
		cgElement.setName(asElement.getName());
	}

	protected void setAst(@NonNull CGTypedElement cgElement, @NonNull TypeId typeId, String symbolName) {
		cgElement.setTypeId(context.getTypeId(typeId));
		cgElement.setName(symbolName);
	}

	protected void setAst(@NonNull CGTypedElement cgElement, @NonNull TypedElement asElement) {
		cgElement.setAst(asElement);
		TypeId asTypeId = asElement.getTypeId();
		cgElement.setTypeId(context.getTypeId(asTypeId));
		cgElement.setName(asElement.getName());
	}

	/*	@Override
	public @Nullable CGElement visitAssociationClassCallExp(@NonNull AssociationClassCallExp element) {
//		CGElement thisAnalysis = context.getCurrentAnalysis();
		return super.visitAssociationClassCallExp(element);
	} */

	@Override
	public @Nullable CGConstantExp visitBooleanLiteralExp(@NonNull BooleanLiteralExp element) {
		CGConstant constant = context.getBoolean(element.isBooleanSymbol());
		CGConstantExp cgLiteralExp = context.createCGConstantExp(element, constant);
		setAst(cgLiteralExp, element);
		return cgLiteralExp;
	}

	/**
	 * @since 1.3
	 */
	@Override
	public @Nullable CGClass visitClass(org.eclipse.ocl.pivot.@NonNull Class element) {
		CGClass cgClass = CGModelFactory.eINSTANCE.createCGClass();
		pushCurrentClass(cgClass);
		setAst(cgClass, element);
		for (@NonNull Constraint asConstraint : ClassUtil.nullFree(element.getOwnedInvariants())) {
			CGConstraint cgConstraint = doVisit(CGConstraint.class, asConstraint);
			cgClass.getInvariants().add(cgConstraint);
		}
		for (@NonNull Operation asOperation : ClassUtil.nullFree(element.getOwnedOperations())) {
			CGOperation cgOperation = doVisit(CGOperation.class, asOperation);
			cgClass.getOperations().add(cgOperation);
		}
		for (@NonNull Property asProperty : ClassUtil.nullFree(element.getOwnedProperties())) {
			CGProperty cgProperty = doVisit(CGProperty.class, asProperty);
			cgClass.getProperties().add(cgProperty);
		}
		popCurrentClass(cgClass);
		return cgClass;
	}

	@Override
	public @Nullable CGCollectionPart visitCollectionItem(@NonNull CollectionItem element) {
		CGCollectionPart cgCollectionPart = CGModelFactory.eINSTANCE.createCGCollectionPart();
		setAst(cgCollectionPart, element);
		cgCollectionPart.setFirst(doVisit(CGValuedElement.class, element.getOwnedItem()));
		return cgCollectionPart;
	}

	@Override
	public @Nullable CGCollectionExp visitCollectionLiteralExp(@NonNull CollectionLiteralExp element) {
		CGCollectionExp cgCollectionExp = CGModelFactory.eINSTANCE.createCGCollectionExp();
		setAst(cgCollectionExp, element);
		cgCollectionExp.setName(element.getKind().getName());
		List<CGCollectionPart> cgParts = cgCollectionExp.getParts();
		for (@NonNull CollectionLiteralPart asPart : ClassUtil.nullFree(element.getOwnedParts())) {
			cgParts.add(doVisit(CGCollectionPart.class, asPart));
		}
		context.getTypeId(element.getTypeId());
		return cgCollectionExp;
	}

	@Override
	public @Nullable CGCollectionPart visitCollectionRange(@NonNull CollectionRange element) {
		CGCollectionPart cgCollectionPart = CGModelFactory.eINSTANCE.createCGCollectionPart();
		setAst(cgCollectionPart, element);
		cgCollectionPart.setFirst(doVisit(CGValuedElement.class, element.getOwnedFirst()));
		cgCollectionPart.setLast(doVisit(CGValuedElement.class, element.getOwnedLast()));
		cgCollectionPart.setTypeId(context.getTypeId(TypeId.INTEGER_RANGE));
		return cgCollectionPart;
	}

	@Override
	public @Nullable CGConstraint visitConstraint(@NonNull Constraint element) {
		CGConstraint cgConstraint = CGModelFactory.eINSTANCE.createCGConstraint();
		setAst(cgConstraint, element);
		LanguageExpression specification = element.getOwnedSpecification();
		if (specification != null) {
			try {
				ExpressionInOCL query = environmentFactory.parseSpecification(specification);
				Variable contextVariable = query.getOwnedContext();
				if (contextVariable != null) {
					CGParameter cgParameter = getParameter(contextVariable, null);
					cgConstraint.getParameters().add(cgParameter);
				}
				for (@NonNull Variable parameterVariable : ClassUtil.nullFree(query.getOwnedParameters())) {
					CGParameter cgParameter = getParameter(parameterVariable, null);
					cgConstraint.getParameters().add(cgParameter);
				}
				cgConstraint.setBody(doVisit(CGValuedElement.class, query.getOwnedBody()));
			} catch (ParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cgConstraint;
	}

	@Override
	public @Nullable CGConstantExp visitEnumLiteralExp(@NonNull EnumLiteralExp element) {
		//		CGConstant constant = context.getEnumerationLiteral(element.getReferredEnumLiteral());
		CGConstant constant = context.getElementId(element.getReferredLiteral().getEnumerationLiteralId());
		CGConstantExp cgLiteralExp = context.createCGConstantExp(element, constant);
		setAst(cgLiteralExp, element);
		return cgLiteralExp;
	}

	@Override
	public @Nullable CGValuedElement visitExpressionInOCL(@NonNull ExpressionInOCL query) {
		assert query.getOwnedBody() != null;
		Variable contextVariable = query.getOwnedContext();
		if (contextVariable != null) {
			CGVariable cgContext = getParameter(contextVariable, null);
			cgContext.setNonInvalid();
			//			cgContext.setNonNull();
		}
		for (@NonNull Variable parameterVariable : ClassUtil.nullFree(query.getOwnedParameters())) {
			@SuppressWarnings("unused") CGVariable cgParameter = getParameter(parameterVariable, null);
		}
		CGValuedElement cgBody = doVisit(CGValuedElement.class, query.getOwnedBody());
		//		cgOperation.getDependsOn().add(cgBody);
		return cgBody;
	}

	@Override
	public @NonNull CGIfExp visitIfExp(@NonNull IfExp element) {
		CGIfExp cgIfExp = CGModelFactory.eINSTANCE.createCGIfExp();
		setAst(cgIfExp, element);
		CGValuedElement cgCondition = doVisit(CGValuedElement.class, element.getOwnedCondition());
		CGValuedElement cgThenExpression = doVisit(CGValuedElement.class, element.getOwnedThen());
		CGValuedElement cgElseExpression = doVisit(CGValuedElement.class, element.getOwnedElse());
		cgIfExp.setCondition(cgCondition);
		cgIfExp.setThenExpression(cgThenExpression);
		cgIfExp.setElseExpression(cgElseExpression);
		//		cgIfExp.getDependsOn().add(cgCondition);
		//		cgIfExp.getDependsOn().add(cgThenExpression);
		//		cgIfExp.getDependsOn().add(cgElseExpression);
		return cgIfExp;
	}

	@Override
	public @Nullable CGConstantExp visitIntegerLiteralExp(@NonNull IntegerLiteralExp element) {
		Number integerSymbol = element.getIntegerSymbol();
		CGInteger constant = context.getInteger(integerSymbol != null ? integerSymbol : 0);
		CGConstantExp cgLiteralExp = context.createCGConstantExp(element, constant);
		setAst(cgLiteralExp, element);
		return cgLiteralExp;
	}

	@Override
	public @Nullable CGConstantExp visitInvalidLiteralExp(@NonNull InvalidLiteralExp element) {
		CGConstantExp cgLiteralExp = context.createCGConstantExp(element, context.getInvalid());
		setAst(cgLiteralExp, element);
		return cgLiteralExp;
	}

	@Override
	public final @NonNull CGValuedElement visitIterateExp(@NonNull IterateExp element) {
		CGValuedElement cgSource = doVisit(CGValuedElement.class, element.getOwnedSource());
		if (!element.isIsSafe()) {
			return generateIterateExp(cgSource, element);
		}
		CGValuedElement cgSafe = generateSafeExclusion(element, cgSource);
		return generateIterateExp(cgSafe, element);
	}

	@Override
	public final @NonNull CGValuedElement visitIteratorExp(@NonNull IteratorExp element) {
		CGValuedElement cgSource = doVisit(CGValuedElement.class, element.getOwnedSource());
		if (!element.isIsSafe()) {
			return generateIteratorExp(cgSource, element);
		}
		CGValuedElement cgSafe = generateSafeExclusion(element, cgSource);
		return generateIteratorExp(cgSafe, element);
	}

	@Override
	public @Nullable CGLetExp visitLetExp(@NonNull LetExp element) {
		Variable variable = element.getOwnedVariable();
		CGValuedElement initExpression = doVisit(CGValuedElement.class, variable.getOwnedInit());
		initExpression.setName(variable.getName());
		CGFinalVariable cgVariable = (CGFinalVariable) createCGVariable(variable);		// FIXME Lose cast
		cgVariable.setInit(initExpression);
		//		initExpression.setVariableValue(cgVariable);
		//		variables.put(variable, cgVariable);
		CGValuedElement inExpression = doVisit(CGValuedElement.class, element.getOwnedIn());
		return createCGLetExp(element, cgVariable, inExpression);
	}

	@Override
	public @Nullable CGNamedElement visitMapLiteralExp(@NonNull MapLiteralExp element) {
		CGMapExp cgMapExp = CGModelFactory.eINSTANCE.createCGMapExp();
		setAst(cgMapExp, element);
		cgMapExp.setName("Map");
		List<@NonNull CGMapPart> cgParts = ClassUtil.nullFree(cgMapExp.getParts());
		for (@NonNull MapLiteralPart asPart : ClassUtil.nullFree(element.getOwnedParts())) {
			cgParts.add(doVisit(CGMapPart.class, asPart));
		}
		context.getTypeId(element.getTypeId());
		return cgMapExp;
	}

	@Override
	public @Nullable CGNamedElement visitMapLiteralPart(@NonNull MapLiteralPart element) {
		CGMapPart cgMapPart = CGModelFactory.eINSTANCE.createCGMapPart();
		cgMapPart.setAst(element);
		cgMapPart.setKey(doVisit(CGValuedElement.class, element.getOwnedKey()));
		cgMapPart.setValue(doVisit(CGValuedElement.class, element.getOwnedValue()));
		cgMapPart.setTypeId(context.getTypeId(TypeId.MAP_ENTRY));
		return cgMapPart;
	}

	/*	@Override
	public @Nullable CGElement visitMessageExp(@NonNull MessageExp element) {
//		CGElement thisAnalysis = context.getCurrentAnalysis();
		return super.visitMessageExp(element);
	} */

	@Override
	public @Nullable CGConstantExp visitNullLiteralExp(@NonNull NullLiteralExp element) {
		CGConstantExp cgLiteralExp = context.createCGConstantExp(element, context.getNull());
		setAst(cgLiteralExp, element);
		return cgLiteralExp;
	}

	@Override
	public @Nullable CGOperation visitOperation(@NonNull Operation asOperation) {
		CGOperation cgOperation = asFinalOperation2cgOperation.get(asOperation);
		if (cgOperation == null) {
			cgOperation = createFinalCGOperationWithoutBody(asOperation);
		}
		LanguageExpression specification = asOperation.getBodyExpression();
		if (specification != null) {
			try {
				ExpressionInOCL query = environmentFactory.parseSpecification(specification);
				createParameters(cgOperation, query);
				cgOperation.setBody(doVisit(CGValuedElement.class, query.getOwnedBody()));
			} catch (ParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cgOperation;
	}

	@Override
	public final @NonNull CGValuedElement visitOperationCallExp(@NonNull OperationCallExp element) {
		OCLExpression pSource = element.getOwnedSource();
		if (pSource == null) {
			return generateOperationCallExp(null, element);
		}
		CGValuedElement cgSource = doVisit(CGValuedElement.class, pSource);
		if (!element.isIsSafe()) {// && !cgSource.isNonNull()) {
			return generateOperationCallExp(cgSource, element);
		}
		Type sourceType = pSource.getType();
		if (sourceType instanceof CollectionType) {
			if (element.isIsSafe()) {
				cgSource = generateSafeExclusion(element, cgSource);
			}
			return generateOperationCallExp(cgSource, element);
		}
		else {
			String operationName = element.getReferredOperation().getName();
			CGFinalVariable cgVariable = generateSafeVariable(cgSource, "safe_" + operationName + "_source");
			CGVariableExp cgVariableExp = generateSafeVariableExp(pSource, cgVariable);
			CGValuedElement cgUnsafeExp = generateOperationCallExp(cgVariableExp, element);
			return generateSafeNavigationGuard(element, cgVariable, cgUnsafeExp);
		}
	}

	@Override
	public final @NonNull CGValuedElement visitOppositePropertyCallExp(@NonNull OppositePropertyCallExp element) {
		OCLExpression asSource = ClassUtil.nonNullModel(element.getOwnedSource());
		CGValuedElement cgSource = doVisit(CGValuedElement.class, asSource);
		if (!element.isIsSafe()) {
			return generateOppositePropertyCallExp(cgSource, element);
		}
		CGFinalVariable cgVariable = generateSafeVariable(cgSource, "safe_" + element.getReferredProperty().getName() + "_source");
		CGVariableExp cgVariableExp = generateSafeVariableExp(asSource, cgVariable);
		CGValuedElement cgUnsafeExp = generateOppositePropertyCallExp(cgVariableExp, element);
		return generateSafeNavigationGuard(element, cgVariable, cgUnsafeExp);
	}

	@Override
	public @Nullable CGNamedElement visitPackage(org.eclipse.ocl.pivot.@NonNull Package element) {
		CGPackage cgPackage = CGModelFactory.eINSTANCE.createCGPackage();
		setAst(cgPackage, element);
		for (org.eclipse.ocl.pivot.@NonNull Class asType : ClassUtil.nullFree(element.getOwnedClasses())) {
			CGClass cgClass = doVisit(CGClass.class, asType);
			cgPackage.getClasses().add(cgClass);
		}
		return cgPackage;
	}

	@Override
	public @Nullable CGProperty visitProperty(@NonNull Property element) {
		CGProperty cgProperty = null;
		LibraryFeature propertyImplementation = element.getImplementation();
		if (propertyImplementation instanceof NativeProperty) {
			CGNativeProperty cgNativeProperty = CGModelFactory.eINSTANCE.createCGNativeProperty();
			cgNativeProperty = CGModelFactory.eINSTANCE.createCGNativeProperty();
			if (!element.isIsReadOnly()) {
				cgNativeProperty.setSettable();
			}
			else {
				cgNativeProperty.setNonNull();
			}
			cgProperty = cgNativeProperty;
		}
		else {
			cgProperty = CGModelFactory.eINSTANCE.createCGProperty();
		}
		setAst(cgProperty, element);
		cgProperty.setRequired(element.isIsRequired());
		LanguageExpression specification = element.getOwnedExpression();
		if (specification != null) {
			try {
				ExpressionInOCL query = environmentFactory.parseSpecification(specification);
				Variable contextVariable = query.getOwnedContext();
				if (contextVariable != null) {
					getParameter(contextVariable, null);
				}
				cgProperty.setBody(doVisit(CGValuedElement.class, query.getOwnedBody()));
			} catch (ParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cgProperty;
	}

	@Override
	public final @NonNull CGValuedElement visitPropertyCallExp(@NonNull PropertyCallExp element) {
		OCLExpression asSource = ClassUtil.nonNullModel(element.getOwnedSource());
		CGValuedElement cgSource = doVisit(CGValuedElement.class, asSource);
		if (!element.isIsSafe()) {
			return generatePropertyCallExp(cgSource, element);
		}
		CGFinalVariable cgVariable = generateSafeVariable(cgSource, "safe_" + element.getReferredProperty().getName() + "_source");
		CGVariableExp cgVariableExp = generateSafeVariableExp(asSource, cgVariable);
		CGValuedElement cgUnsafeExp = generatePropertyCallExp(cgVariableExp, element);
		return generateSafeNavigationGuard(element, cgVariable, cgUnsafeExp);
	}

	@Override
	public @Nullable CGConstantExp visitRealLiteralExp(@NonNull RealLiteralExp element) {
		Number realSymbol = element.getRealSymbol();
		@SuppressWarnings("null")
		CGReal cgReal = context.getReal(realSymbol != null ? realSymbol : Double.valueOf(0.0));
		CGConstantExp cgLiteralExp = context.createCGConstantExp(element, cgReal);
		setAst(cgLiteralExp, element);
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
			CGExecutorType cgExecutorType = context.createExecutorType(ClassUtil.nonNullState(element.getType()));
			cgShadowExp.setExecutorType(cgExecutorType);
			cgShadowExp.getOwns().add(cgExecutorType);
			setAst(cgShadowExp, element);
			//			context.setNames(cgShadowExp, element);
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
		setAst(cgShadowPart, element);
		cgShadowPart.setInit(doVisit(CGValuedElement.class, element.getOwnedInit()));
		Property referredProperty = element.getReferredProperty();
		if (referredProperty != null) {
			CGExecutorShadowPart cgExecutorShadowPart = context.createExecutorShadowPart(referredProperty);
			cgShadowPart.setExecutorPart(cgExecutorShadowPart);
		}
		return cgShadowPart;
	}

	@Override
	public @Nullable CGNamedElement visitStateExp(@NonNull StateExp element) {
		//		CGElement thisAnalysis = context.getCurrentAnalysis();
		return super.visitStateExp(element);
	}

	@Override
	public @Nullable CGConstantExp visitStringLiteralExp(@NonNull StringLiteralExp element) {
		String stringSymbol = element.getStringSymbol();
		CGString cgString = context.getString(stringSymbol != null ? stringSymbol : "");
		CGConstantExp cgLiteralExp = context.createCGConstantExp(element, cgString);
		return cgLiteralExp;
	}

	@Override
	public @Nullable CGTupleExp visitTupleLiteralExp(@NonNull TupleLiteralExp element) {
		CGTupleExp cgTupleExp = CGModelFactory.eINSTANCE.createCGTupleExp();
		setAst(cgTupleExp, element);
		List<@NonNull CGTuplePart> cgParts = new ArrayList<>();
		for (@NonNull TupleLiteralPart asPart : ClassUtil.nullFree(element.getOwnedParts())) {
			cgParts.add(doVisit(CGTuplePart.class, asPart));
		}
		Collections.sort(cgParts, CGTuplePartNameComparator.INSTANCE);
		cgTupleExp.getParts().addAll(cgParts);
		context.getTypeId(element.getTypeId());
		return cgTupleExp;
	}

	@Override
	public @Nullable CGTuplePart visitTupleLiteralPart(@NonNull TupleLiteralPart element) {
		CGTuplePart cgTuplePart = CGModelFactory.eINSTANCE.createCGTuplePart();
		setAst(cgTuplePart, element);
		cgTuplePart.setInit(doVisit(CGValuedElement.class, element.getOwnedInit()));
		TuplePartId partId = element.getPartId();
		if (partId != null) {
			context.getElementId(partId);
		}
		return cgTuplePart;
	}

	@Override
	public @Nullable CGValuedElement visitTypeExp(@NonNull TypeExp pTypeExp) {
		Type referredType = PivotUtil.getReferredType(pTypeExp);
		if (!(referredType instanceof TemplateParameter)) {
			CGTypeExp cgTypeExp = CGModelFactory.eINSTANCE.createCGTypeExp();
			//		setPivot(cgTypeExp, pTypeExp);
			cgTypeExp.setAst(pTypeExp);
			CGExecutorType cgExecutorType = context.createExecutorType(referredType);
			cgTypeExp.setExecutorType(cgExecutorType);
			cgTypeExp.getOwns().add(cgExecutorType);
			//		cgTypeExp.setReferredType(codeGenerator.getGlobalContext().getLocalContext(cgTypeExp).getExecutorType(pTypeExp.getReferredType()));
			TypeId asTypeId = pTypeExp.getTypeId();
			cgTypeExp.setTypeId(context.getTypeId(asTypeId)); //-- no need to reify the metaclassid
			cgTypeExp.setName(cgExecutorType.getName());
			return cgTypeExp;
		}
		TemplateParameter referredTemplateParameter = (TemplateParameter)referredType;
		TemplateSignature templateSignature = PivotUtil.getOwningSignature(referredTemplateParameter);
		TemplateableElement asTemplateableElement = PivotUtil.getOwningElement(templateSignature);
		CGValuedElement cgTemplateableElement;
		if (asTemplateableElement instanceof Type) {
			cgTemplateableElement = context.createExecutorType((Type)asTemplateableElement);
		}
		else if (asTemplateableElement instanceof Operation) {
			cgTemplateableElement = context.createExecutorOperation((Operation)asTemplateableElement);
		}
		else {
			codeGenerator.addProblem(new UnsupportedOperationException("visitTypeExp for non-Type Templateparameter"));
			return null;
		}
		int index = templateSignature.getOwnedParameters().indexOf(referredTemplateParameter);
		CGTemplateParameterExp cgTemplateParameterExp = CGModelFactory.eINSTANCE.createCGTemplateParameterExp();
		cgTemplateParameterExp.setIndex(index);
		//		setPivot(cgTypeExp, pTypeExp);
		cgTemplateParameterExp.setAst(pTypeExp);
		cgTemplateParameterExp.setTemplateableElement(cgTemplateableElement);
		cgTemplateParameterExp.getOwns().add(cgTemplateableElement);
		//		cgTypeExp.setReferredType(codeGenerator.getGlobalContext().getLocalContext(cgTypeExp).getExecutorType(pTypeExp.getReferredType()));
		TypeId asTypeId = pTypeExp.getTypeId();
		cgTemplateParameterExp.setTypeId(context.getTypeId(asTypeId)); //-- no need to reify the metaclassid
		return cgTemplateParameterExp;
	}

	@Override
	public @Nullable CGConstantExp visitUnlimitedNaturalLiteralExp(@NonNull UnlimitedNaturalLiteralExp element) {
		Number unlimitedNaturalSymbol = element.getUnlimitedNaturalSymbol();
		CGConstantExp cgLiteralExp;
		if (unlimitedNaturalSymbol instanceof UnlimitedValue) {
			cgLiteralExp = context.createCGConstantExp(element, context.getUnlimited());
		}
		else if (unlimitedNaturalSymbol instanceof Unlimited) {
			cgLiteralExp = context.createCGConstantExp(element, context.getUnlimited());
		}
		else if (unlimitedNaturalSymbol != null) {
			cgLiteralExp = context.createCGConstantExp(element, context.getInteger(unlimitedNaturalSymbol));
		}
		else {
			cgLiteralExp = context.createCGConstantExp(element, context.getInteger(0));
		}
		setAst(cgLiteralExp, element);
		return cgLiteralExp;
	}

	@Override
	public @Nullable CGNamedElement visitUnspecifiedValueExp(@NonNull UnspecifiedValueExp element) {
		//		CGElement thisAnalysis = context.getCurrentAnalysis();
		return super.visitUnspecifiedValueExp(element);
	}

	@Override
	public @Nullable CGValuedElement visitVariableExp(@NonNull VariableExp asVariableExp) {
		VariableDeclaration referredVariable = asVariableExp.getReferredVariable();
		CGVariableExp cgVariableExp = createCGVariableExp(asVariableExp, referredVariable);
		return cgVariableExp;
	}

	@Override
	@Nullable
	public CGValuedElement visiting(@NonNull Visitable visitable) {
		throw new UnsupportedOperationException(getClass().getSimpleName() + ": " + visitable.getClass().getSimpleName());
	}
}
