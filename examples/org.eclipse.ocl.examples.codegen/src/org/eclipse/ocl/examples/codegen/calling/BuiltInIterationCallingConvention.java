/*******************************************************************************
 * Copyright (c) 2022 Willink Transformation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.calling;

import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGAccumulator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterateCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.IterationHelper;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.Iteration2Java;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.JavaStream.SubStream;
import org.eclipse.ocl.examples.codegen.naming.ClassNameManager;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.internal.ecore.EObjectOperation;
import org.eclipse.ocl.pivot.internal.library.ConstrainedOperation;
import org.eclipse.ocl.pivot.internal.library.ForeignOperation;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.LibraryIteration;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 *  BuiltInIterationCallingConvention defines the support for the call of an iteration optionally realized by inline code.
 */
public class BuiltInIterationCallingConvention extends AbstractUncachedOperationCallingConvention
{
	private static final @NonNull BuiltInIterationCallingConvention INSTANCE = new BuiltInIterationCallingConvention();

	public static @NonNull BuiltInIterationCallingConvention getInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
		INSTANCE.logInstance(asOperation, maybeVirtual);
		return INSTANCE;
	}

	public @NonNull CGIterationCallExp createCGIterationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation,
				@NonNull LibraryIteration libraryIteration, @NonNull CGValuedElement cgSafeSource, @NonNull LoopExp asLoopExp) {
		ExecutableNameManager parentNameManager = analyzer.useExecutableNameManager((NamedElement)asLoopExp.eContainer());
		GlobalNameManager globalNameManager = parentNameManager.getGlobalNameManager();
		JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
		Iteration asIteration = PivotUtil.getReferredIteration(asLoopExp);
		IterationHelper iterationHelper = codeGenerator.getIterationHelper(asIteration);

		CGIterationCallExp cgIterationCallExp;
		if (iterationHelper != null) {
			cgIterationCallExp = CGModelFactory.eINSTANCE.createCGBuiltInIterationCallExp();
		}
		else {
			CGLibraryIterationCallExp cgLibraryIterationCallExp = CGModelFactory.eINSTANCE.createCGLibraryIterationCallExp();
			cgLibraryIterationCallExp.setLibraryIteration(libraryIteration);
			cgIterationCallExp = cgLibraryIterationCallExp;
		}
		analyzer.initAst(cgIterationCallExp, asLoopExp, true);




		cgIterationCallExp.setAsIteration(asIteration);
		cgIterationCallExp.setReferredIteration(cgOperation);
		cgIterationCallExp.setInvalidating(asIteration.isIsInvalidating());
		cgIterationCallExp.setValidating(asIteration.isIsValidating());
		cgIterationCallExp.setSource(cgSafeSource);
		globalNameManager.addSelfNameManager(cgSafeSource, parentNameManager);										// Source always evaluated in parent context
		assert cgIterationCallExp.getAst() == asLoopExp;
		ExecutableNameManager childNameManager = (ExecutableNameManager)globalNameManager.basicGetChildNameManager(cgIterationCallExp);
		if (childNameManager == null) {			//
			ClassNameManager classNameManager = parentNameManager.getClassNameManager();
			childNameManager = globalNameManager.createLoopNameManager(classNameManager, parentNameManager, cgIterationCallExp);
		}
		//
		//	Iterators / co-iterators
		//
		ExecutableNameManager iteratorNameManager = iterationHelper != null ? parentNameManager : childNameManager;	// Iterators conditionally in parent/child context
		for (@NonNull Variable iterator : PivotUtil.getOwnedIterators(asLoopExp)) {
			CGIterator cgIterator = iteratorNameManager.getIterator(iterator);
			if (iterationHelper != null) {
				setNullableIterator(analyzer, cgIterator, iterator);
			}
			cgIterationCallExp.getIterators().add(cgIterator);
			globalNameManager.addSelfNameManager(cgIterator, iteratorNameManager);
		}
		for (@NonNull Variable coIterator : PivotUtil.getOwnedCoIterators(asLoopExp)) {
			CGIterator cgCoIterator = iteratorNameManager.getIterator(coIterator);
			if (iterationHelper != null) {
				setNullableIterator(analyzer, cgCoIterator, coIterator);
			}
			cgIterationCallExp.getCoIterators().add(cgCoIterator);
			globalNameManager.addSelfNameManager(cgCoIterator, iteratorNameManager);
		}
		if (asLoopExp instanceof IterateExp) {
			Variable accumulator = PivotUtil.getOwnedResult((IterateExp)asLoopExp);
			CGIterator cgAccumulator = iteratorNameManager.getIterator(accumulator);
			if (iterationHelper != null) {
				//				cgBuiltInIterationCallExp.setNonNull();
				setNullableIterator(analyzer, cgAccumulator, accumulator);
				((CGBuiltInIterationCallExp)cgIterationCallExp).setAccumulator(cgAccumulator);
				globalNameManager.addSelfNameManager(cgAccumulator, iteratorNameManager);
			}
			else {
				((CGLibraryIterateCallExp)cgIterationCallExp).setResult(cgAccumulator);
			}
			CGValuedElement cgInitExpression = analyzer.createCGElement(CGValuedElement.class, accumulator.getOwnedInit());
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
					cgBuiltInIterationCallExp.setAccumulator(cgAccumulator);
					globalNameManager.addSelfNameManager(cgAccumulator, iteratorNameManager);
				}
			}
		}
		//
		//	Body
		//
		boolean isRequired = asLoopExp.isIsRequired();
		CGValuedElement cgBody = analyzer.createCGElement(CGValuedElement.class, asLoopExp.getOwnedBody());
		cgIterationCallExp.setBody(cgBody);
		if (iterationHelper != null) {
			if (asIteration.getOwnedParameters().get(0).isIsRequired()) {
				cgBody.setRequired(true);
			}
			if (isRequired) {
				((CGBuiltInIterationCallExp)cgIterationCallExp).setNonNull();
			}
		}
		cgIterationCallExp.setRequired(isRequired);
		return cgIterationCallExp;
	}

/*	public boolean canHandle(@NonNull LibraryOperation libraryOperation) {
		if (libraryOperation instanceof OclAnyOclIsInvalidOperation) {
			return true;
		}
		else if (libraryOperation instanceof OclAnyOclIsUndefinedOperation) {
			return true;
		}
		else if (libraryOperation instanceof OclAnyEqualOperation) {
			return true;
		}
		return false;
	} */

	@Override
	public @NonNull CGOperation createCGOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
		assert asOperation instanceof Iteration;
//		return fallbackCreateCGOperationWithoutBody(as2cgVisitor, asOperation);
 		PivotMetamodelManager metamodelManager = analyzer.getMetamodelManager();
		LibraryFeature libraryOperation = metamodelManager.getImplementation(asOperation);
		assert !(libraryOperation instanceof EObjectOperation);
		assert !(libraryOperation instanceof ForeignOperation);
		assert !(libraryOperation instanceof ConstrainedOperation);
		return CGModelFactory.eINSTANCE.createCGLibraryOperation();
	}

	@Override
	public @NonNull CGCallExp createCGOperationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		throw new UnsupportedOperationException();		// XXX
/*		if (libraryOperation instanceof OclAnyOclIsInvalidOperation) {
			CGIsInvalidExp cgIsInvalidExp = CGModelFactory.eINSTANCE.createCGIsInvalidExp();
			cgIsInvalidExp.setSource(cgSource);
			as2cgVisitor.initAst(cgIsInvalidExp, asOperationCallExp);
		//	as2cgVisitor.declareLazyName(cgIsInvalidExp);
			cgIsInvalidExp.setInvalidating(false);
			cgIsInvalidExp.setValidating(true);
			return cgIsInvalidExp;
		}
		if (libraryOperation instanceof OclAnyOclIsUndefinedOperation) {
			CGIsUndefinedExp cgIsUndefinedExp = CGModelFactory.eINSTANCE.createCGIsUndefinedExp();
			cgIsUndefinedExp.setSource(cgSource);
			as2cgVisitor.initAst(cgIsUndefinedExp, asOperationCallExp);
		//	as2cgVisitor.declareLazyName(cgIsUndefinedExp);
			cgIsUndefinedExp.setInvalidating(false);
			cgIsUndefinedExp.setValidating(true);
			return cgIsUndefinedExp;
		}
		if (libraryOperation instanceof OclAnyEqualOperation) {
			OCLExpression pArgument = PivotUtil.getOwnedArgument(asOperationCallExp, 0);
			CGValuedElement cgArgument = as2cgVisitor.doVisit(CGValuedElement.class, pArgument);
			CGIsEqualExp cgIsEqualExp = CGModelFactory.eINSTANCE.createCGIsEqualExp();
			cgIsEqualExp.setNotEquals(libraryOperation instanceof OclAnyNotEqualOperation);
			cgIsEqualExp.setSource(cgSource);
			cgIsEqualExp.setArgument(cgArgument);
			as2cgVisitor.initAst(cgIsEqualExp, asOperationCallExp);
		//	as2cgVisitor.declareLazyName(cgIsEqualExp);
			cgIsEqualExp.setInvalidating(false);
			cgIsEqualExp.setValidating(true);
			return cgIsEqualExp;
		}
		throw new IllegalStateException("Unsupported built-in " + libraryOperation); */
	}

	@Override
	public void createCGParameters(@NonNull ExecutableNameManager operationNameManager, @Nullable ExpressionInOCL bodyExpression) {
		CGOperation cgOperation = (CGOperation)operationNameManager.getCGScope();
	//	Iteration asIteration = (Iteration)CGUtil.getAST(cgOperation);
		CGParameter cgParameter = operationNameManager.getSelfParameter();
		//			cgParameter.setTypeId(context.getTypeId(JavaConstants.getJavaTypeId(Object.class)));
		//			cgParameter.setRequired(contextVariable.isIsRequired());
		cgOperation.getParameters().add(cgParameter);
	//	for (@NonNull Parameter parameterVariable : ClassUtil.nullFree(asIteration.getOwnedParameters())) {
	//		CGParameter cgParameter = nameManager.getParameter(parameterVariable, (String)null);		-- creates a bad (unsupported) global LambdaTypeId
	//		cgOperation.getParameters().add(cgParameter);
	//	}
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperationCallExp cgOperationCallExp) {
		throw new UnsupportedOperationException("Should be a CGIterationCallExp");
	}

	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGIterationCallExp cgIterationCallExp) {
		CGBuiltInIterationCallExp cgBuiltInIterationCallExp = (CGBuiltInIterationCallExp)cgIterationCallExp;;
		JavaCodeGenerator codeGenerator = cg2javaVisitor.getCodeGenerator();
		JavaStream js = cg2javaVisitor.getJavaStream();
		CGValuedElement cgSource = cg2javaVisitor.getExpression(cgIterationCallExp.getSource());
		CGValuedElement cgBody = cg2javaVisitor.getExpression(cgIterationCallExp.getBody());
		CGIterator cgAccumulator = cgBuiltInIterationCallExp.getAccumulator();
		CGIterator cgIterator = CGUtil.getIterator(cgIterationCallExp, 0);
		CGIterator cgCoIterator = cgIterationCallExp.getCoIterators().size() > 0 ? cgIterationCallExp.getCoIterators().get(0) : null;
		String iteratorName = cg2javaVisitor.getVariantResolvedName(cgIterator, codeGenerator.getITER_NameVariant());
		Iteration2Java iterationHelper = codeGenerator.getIterationHelper(ClassUtil.nonNullState(cgIterationCallExp.getAsIteration()));
		assert iterationHelper != null;
		boolean flowContinues = false;
		boolean isMap = cgSource.getASTypeId() instanceof MapTypeId;
		//
		if (!js.appendLocalStatements(cgSource)) {
			return false;
		}
		//
		//	Declare and initialize accumulator
		//
		if (cgAccumulator != null) {
			CGValuedElement cgInit = cgAccumulator.getInit();
			if (cgInit != null) {
				if (!js.appendLocalStatements(cgInit)) {
					return false;
				}
			}
			cgAccumulator.toString();
			js.appendDeclaration(cgAccumulator);
			js.append(" = ");
			iterationHelper.appendAccumulatorInit(js, cgBuiltInIterationCallExp);
			js.append(";\n");
		}
		//
		//	Declare iterator
		//
		js.appendClassReference(cgIterator.isRequired(), Iterator.class, false, Object.class); //, getJavaClass(cgIterator));
		js.append(" " + iteratorName + " = ");
		js.appendAtomicReferenceTo(cgSource);
		js.append(".iterator();\n");
		if (!isMap && (cgCoIterator != null)) {
			js.appendDeclaration(cgCoIterator);
			js.append(" = ");
			js.appendClassReference(null, ValueUtil.class);
			js.append(".ONE_VALUE;\n");
		}
		//
		//	Declare body result
		//
		js.appendDeclaration(cgIterationCallExp);
		js.append(";\n");
		//
		//	Declare loop head
		//
		js.append("while (true) {\n");
		js.pushIndentation(null);
		//
		//	Terminate loop once done
		//
		js.append("if (!" + iteratorName + ".hasNext()) {\n");
		js.pushIndentation(null);
		if (iterationHelper.appendFinalValue(js, cgBuiltInIterationCallExp)) {
			js.append("break;\n");
			flowContinues = true;
		}
		js.popIndentation();
		js.append("}\n");
		//
		// Declare iterator advance.
		//
		cg2javaVisitor.appendSuppressWarningsNull(cgIterator, Boolean.FALSE);
		js.appendDeclaration(cgIterator);
		js.append(" = ");
		SubStream castBody1 = new SubStream() {
			@Override
			public void append() {
				js.append(iteratorName + ".next()");
			}
		};
		js.appendClassCast(cgIterator, castBody1);
		js.append(";\n");
		//
		// Declare coiterator/key access.
		//
		if (isMap && (cgCoIterator != null)) { // && !isImplicit
			Variable asCoIterator = CGUtil.getAST(cgCoIterator);
			if (!asCoIterator.isIsImplicit()) {
				if (cgCoIterator.isRequired()) {
					js.appendSuppressWarningsNull(true);
				}
				js.appendDeclaration(cgCoIterator);
				js.append(" = ");
				SubStream castBody2 = new SubStream() {
					@Override
					public void append() {
						js.appendReferenceTo(cgSource);
						js.append(".at(");
						js.appendReferenceTo(cgIterator);
						js.append(")");
					}
				};
				js.appendClassCast(cgCoIterator, castBody2);
				js.append(";\n");
			}
		}
		//
		// Declare iteration body.
		//
		js.appendCommentWithOCL(null, cgBody.getAst());
		if (js.appendLocalStatements(cgBody)) {
			js.append("//\n");
			if (iterationHelper.appendUpdate(js, cgBuiltInIterationCallExp)) {
				flowContinues = true;
			}
		}
		if (!isMap && (cgCoIterator != null)) {
			js.appendReferenceTo(cgCoIterator);
			js.append(" = ");
			js.appendReferenceTo(cgCoIterator);
			js.append(".addInteger(");
			js.appendClassReference(null, ValueUtil.class);
			js.append(".ONE_VALUE);\n");
		}
		//
		//	Declare loop tail
		//
		js.popIndentation();
		js.append("}\n");
		return flowContinues;
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
		throw new UnsupportedOperationException();		// Built-in operations are declared inline
	}

	@Override
	public boolean needsGeneration() {
		return false;
	}

	private void setNullableIterator(@NonNull CodeGenAnalyzer analyzer, @NonNull CGIterator cgIterator, @NonNull Variable iterator) {
		cgIterator.setTypeId(analyzer.getCGTypeId(iterator.getTypeId()));
		cgIterator.setRequired(iterator.isIsRequired());
		if (iterator.isIsRequired()) {
			cgIterator.setNonNull();
		}
		cgIterator.setNonInvalid();
	}
}
