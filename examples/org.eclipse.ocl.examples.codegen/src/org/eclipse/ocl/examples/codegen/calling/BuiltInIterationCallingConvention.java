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
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGAccumulator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
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
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.library.LibraryIteration;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 *  BuiltInIterationCallingConvention defines the support for the call of an iteration for which a built-in IterationHelper
 *  supports generation of inline code.
 */
public class BuiltInIterationCallingConvention extends AbstractIterationCallingConvention
{
	private static final @NonNull BuiltInIterationCallingConvention INSTANCE = new BuiltInIterationCallingConvention();

	public static @NonNull BuiltInIterationCallingConvention getInstance(@NonNull Iteration asIteration) {
		INSTANCE.logInstance(asIteration);
		return INSTANCE;
	}

	@Override
	public @NonNull CGIterationCallExp createCGIterationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation,
				@NonNull LibraryIteration libraryIteration, @NonNull CGValuedElement cgSafeSource, @NonNull LoopExp asLoopExp) {
		ExecutableNameManager parentNameManager = analyzer.useExecutableNameManager((NamedElement)asLoopExp.eContainer());
		GlobalNameManager globalNameManager = parentNameManager.getGlobalNameManager();
		JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
		Iteration asIteration = PivotUtil.getReferredIteration(asLoopExp);
		IterationHelper iterationHelper = codeGenerator.getIterationHelper(asIteration);
		assert iterationHelper != null;
		CGIterationCallExp cgIterationCallExp = CGModelFactory.eINSTANCE.createCGBuiltInIterationCallExp();
		analyzer.initAst(cgIterationCallExp, asLoopExp, true);
		cgIterationCallExp.setReferredIteration(cgOperation);
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
		ExecutableNameManager iteratorNameManager = parentNameManager;				// Inlined Iterators in parent context
		for (@NonNull Variable iterator : PivotUtil.getOwnedIterators(asLoopExp)) {
			CGIterator cgIterator = iteratorNameManager.lazyGetIterator(iterator);
			setNullableIterator(analyzer, cgIterator, iterator);
			cgIterationCallExp.getIterators().add(cgIterator);
			globalNameManager.addSelfNameManager(cgIterator, iteratorNameManager);
		}
		for (@NonNull Variable coIterator : PivotUtil.getOwnedCoIterators(asLoopExp)) {
			CGIterator cgCoIterator = iteratorNameManager.lazyGetIterator(coIterator);
			setNullableIterator(analyzer, cgCoIterator, coIterator);
			cgIterationCallExp.getCoIterators().add(cgCoIterator);
			globalNameManager.addSelfNameManager(cgCoIterator, iteratorNameManager);
		}
		if (asLoopExp instanceof IterateExp) {
			Variable accumulator = PivotUtil.getOwnedResult((IterateExp)asLoopExp);
			CGIterator cgAccumulator = iteratorNameManager.lazyGetIterator(accumulator);
			//				cgBuiltInIterationCallExp.setRequired(true);
			setNullableIterator(analyzer, cgAccumulator, accumulator);
			((CGBuiltInIterationCallExp)cgIterationCallExp).setAccumulator(cgAccumulator);
			globalNameManager.addSelfNameManager(cgAccumulator, iteratorNameManager);
			CGValuedElement cgInitExpression = analyzer.createCGElement(CGValuedElement.class, accumulator.getOwnedInit());
			cgAccumulator.setInit(cgInitExpression);
		}
		else {
			CGBuiltInIterationCallExp cgBuiltInIterationCallExp = (CGBuiltInIterationCallExp) cgIterationCallExp;
			CGTypeId cgAccumulatorId = iterationHelper.getAccumulatorTypeId(analyzer, cgBuiltInIterationCallExp);
			if (cgAccumulatorId != null) {
				boolean isNonNullAccumulator = iterationHelper.isNonNullAccumulator(asLoopExp);
				CGAccumulator cgAccumulator = CGModelFactory.eINSTANCE.createCGAccumulator();
				cgAccumulator.setTypeId(cgAccumulatorId);
				if (isNonNullAccumulator) {
					cgAccumulator.setRequired(true);
				}
				if (!asIteration.isIsValidating()) {
					cgAccumulator.setNonInvalid();
				}
				cgBuiltInIterationCallExp.setAccumulator(cgAccumulator);
				globalNameManager.addSelfNameManager(cgAccumulator, iteratorNameManager);
			}
		}
		//
		//	Body
		//
		boolean isRequired = asLoopExp.isIsRequired();
		CGValuedElement cgBody = analyzer.createCGElement(CGValuedElement.class, asLoopExp.getOwnedBody());
		cgIterationCallExp.setBody(cgBody);
		cgIterationCallExp.setRequired(isRequired);
		return cgIterationCallExp;
	}

	@Override
	public @NonNull CGOperation createIteration(@NonNull CodeGenAnalyzer analyzer, @NonNull Iteration asIteration) {
		CGOperation cgOperation = createCGOperation(analyzer, asIteration);
		assert cgOperation.getCallingConvention() == null;
		cgOperation.setCallingConvention(this);
		assert cgOperation.getAst() == null;						// Lightweight createCGOperation just creates
		assert analyzer.basicGetCGElement(asIteration) == null;
		analyzer.initAst(cgOperation, asIteration, true);
		assert analyzer.basicGetCGElement(asIteration) != null;
	//	ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(cgOperation, asIteration);	// Needed to support downstream useOperationNameManager()
		assert cgOperation.eContainer() == null;
	//	CGClass cgClass = analyzer.getCGClass(PivotUtil.getOwningClass(asIteration));
	//	cgClass.getOperations().add(cgOperation);
	//	createCGParameters(operationNameManager, asExpressionInOCL);

	//	org.eclipse.ocl.pivot.@NonNull Package asParentPackage = analyzer.getRootClassParentPackage(asClass);
	//	IterationClassCallingConvention.getInstance(asIteration).createIterationClass(analyzer, null);


		return cgOperation;		// Redundant orphan
	}

	@Override
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
		Iteration asIteration = CGUtil.getAsIteration(cgIterationCallExp);
		Iteration2Java iterationHelper = codeGenerator.getIterationHelper(asIteration);
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
		js.appendClassCast(cgIterator, castBody1);			// XXX appendAssignWithCast
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

	private void setNullableIterator(@NonNull CodeGenAnalyzer analyzer, @NonNull CGIterator cgIterator, @NonNull Variable iterator) {
		cgIterator.setTypeId(analyzer.getCGTypeId(iterator.getTypeId()));
		cgIterator.setRequired(iterator.isIsRequired());
		cgIterator.setNonInvalid();
	}
}
