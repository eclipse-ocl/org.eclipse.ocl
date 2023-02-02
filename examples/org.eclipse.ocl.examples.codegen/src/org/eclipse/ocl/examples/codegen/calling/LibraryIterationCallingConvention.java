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

import java.lang.reflect.Method;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterateCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.IterationHelper;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.JavaStream.SubStream;
import org.eclipse.ocl.examples.codegen.naming.ClassNameManager;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorMultipleIterationManager;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorMultipleMapIterationManager;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorSingleIterationManager;
import org.eclipse.ocl.pivot.library.AbstractBinaryOperation;
import org.eclipse.ocl.pivot.library.AbstractSimpleOperation;
import org.eclipse.ocl.pivot.library.LibraryIteration;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.MapValue;

/**
 *  LibraryIterationCallingConvention defines the support for the call of an iteration wrapping the iteration
 *  body as the evaluate method of a nested class.
 */
public class LibraryIterationCallingConvention extends AbstractIterationCallingConvention
{
	private static final @NonNull LibraryIterationCallingConvention INSTANCE = new LibraryIterationCallingConvention();

	public static @NonNull LibraryIterationCallingConvention getInstance(@NonNull Iteration asIteration) {
		INSTANCE.logInstance(asIteration);
		return INSTANCE;
	}

	protected static class ArgumentSubStream implements SubStream
	{
		private final @NonNull JavaStream js;
		private final int argIndex;

		protected ArgumentSubStream(@NonNull JavaStream js, int argIndex) {
			this.js = js;
			this.argIndex = argIndex;
		}

		@Override
		public void append() {
			js.append(JavaConstants.SOURCE_AND_ARGUMENT_VALUES_NAME);
			js.append("[" + argIndex);
			js.append("]");
		}
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
	public @NonNull CGIterationCallExp createCGIterationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation,
				@NonNull LibraryIteration libraryIteration, @NonNull CGValuedElement cgSafeSource, @NonNull LoopExp asLoopExp) {
		ExecutableNameManager parentNameManager = analyzer.useExecutableNameManager((NamedElement)asLoopExp.eContainer());
		GlobalNameManager globalNameManager = parentNameManager.getGlobalNameManager();
		JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
		Iteration asIteration = PivotUtil.getReferredIteration(asLoopExp);
		IterationHelper iterationHelper = codeGenerator.getIterationHelper(asIteration);
		assert iterationHelper == null;
		CGLibraryIterationCallExp cgIterationCallExp = CGModelFactory.eINSTANCE.createCGLibraryIterationCallExp();
		cgIterationCallExp.setLibraryIteration(libraryIteration);
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
		ExecutableNameManager iteratorNameManager = childNameManager;				// Nested class Iterators in child context
		for (@NonNull Variable iterator : PivotUtil.getOwnedIterators(asLoopExp)) {
			CGIterator cgIterator = iteratorNameManager.lazyGetIterator(iterator);
			cgIterator.setRequired(false);
			cgIterationCallExp.getIterators().add(cgIterator);
			globalNameManager.addSelfNameManager(cgIterator, iteratorNameManager);
		}
		for (@NonNull Variable coIterator : PivotUtil.getOwnedCoIterators(asLoopExp)) {
			CGIterator cgCoIterator = iteratorNameManager.lazyGetIterator(coIterator);
			cgCoIterator.setRequired(false);
			cgIterationCallExp.getCoIterators().add(cgCoIterator);
			globalNameManager.addSelfNameManager(cgCoIterator, iteratorNameManager);
		}
		if (asLoopExp instanceof IterateExp) {
			Variable accumulator = PivotUtil.getOwnedResult((IterateExp)asLoopExp);
			CGIterator cgAccumulator = iteratorNameManager.lazyGetIterator(accumulator);
			((CGLibraryIterateCallExp)cgIterationCallExp).setResult(cgAccumulator);
			CGValuedElement cgInitExpression = analyzer.createCGElement(CGValuedElement.class, accumulator.getOwnedInit());
			cgAccumulator.setInit(cgInitExpression);
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
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGIterationCallExp cgIterationCallExp) {
		CGIterator iterateResult = (cgIterationCallExp instanceof CGLibraryIterateCallExp) ? ((CGLibraryIterateCallExp)cgIterationCallExp).getResult() : null;

		final CGValuedElement source = cg2javaVisitor.getExpression(cgIterationCallExp.getSource());
		final List<@NonNull CGIterator> iterators = CGUtil.getIteratorsList(cgIterationCallExp);
		final List<@NonNull CGIterator> coIterators = CGUtil.getCoIteratorsList(cgIterationCallExp);
		final CGValuedElement body = cg2javaVisitor.getExpression(cgIterationCallExp.getBody());
		final CGTypeId resultType = cgIterationCallExp.getTypeId();
		LoopExp asLoopExp = CGUtil.getAST(cgIterationCallExp);
		final Iteration referredIteration = PivotUtil.getReferredIteration(asLoopExp);
		final int arity = iterators.size();
		Type sourceType = ((CallExp)asLoopExp).getOwnedSource().getType();
		boolean isMap = sourceType instanceof MapType;
		final Class<?> managerClass; 	// FIXME ExecutorMultipleIterationManager
		final Class<?> operationClass;
		final boolean passesCoIterators;
		boolean useMultiple = isMap || (coIterators.size() > 0) || (arity > 1);
		if (isMap) {
			managerClass = ExecutorMultipleMapIterationManager.class;
			operationClass = AbstractSimpleOperation.class;
			passesCoIterators = true;
		}
		else if (useMultiple) {
			managerClass = ExecutorMultipleIterationManager.class;
			operationClass = AbstractSimpleOperation.class;
			passesCoIterators = true;
		}
		else {
			managerClass = ExecutorSingleIterationManager.class;
			operationClass = AbstractBinaryOperation.class;
			passesCoIterators = false;
		}
		GlobalNameManager globalNameManager = cg2javaVisitor.getGlobalNameManager();
		JavaCodeGenerator codeGenerator = cg2javaVisitor.getCodeGenerator();
		final LibraryIteration libraryIteration = ClassUtil.nonNullState(((CGLibraryIterationCallExp)cgIterationCallExp).getLibraryIteration());
		final Method actualMethod = libraryIteration.getEvaluateMethod(referredIteration);
		final Class<?> actualReturnClass = actualMethod.getReturnType();
		boolean actualIsNonNull = codeGenerator.getIsNonNull(actualMethod) == Boolean.TRUE;
		boolean expectedIsNonNull = cgIterationCallExp.isRequiredOrNonNull();
		final String astName = cgIterationCallExp.getResolvedName();
		final String bodyName = cg2javaVisitor.getVariantResolvedName(cgIterationCallExp, codeGenerator.getBODY_NameVariant());
		final String executorName = globalNameManager.getExecutorName();
		final String implementationName = cg2javaVisitor.getVariantResolvedName(cgIterationCallExp, codeGenerator.getIMPL_NameVariant());
		final String managerName = cg2javaVisitor.getVariantResolvedName(cgIterationCallExp, codeGenerator.getMGR_NameVariant());
		final String staticTypeName = cg2javaVisitor.getVariantResolvedName(cgIterationCallExp, codeGenerator.getTYPE_NameVariant());
		String accumulatorName;
		//
		JavaStream js = cg2javaVisitor.getJavaStream();
		//
		//	Pre-amble: hoisted assignments
		//
		if (!js.appendLocalStatements(source)) {
			return false;
		}
		//
		//	Dispatch: Determine static type
		//
		js.append("final ");
		js.appendClassReference(true, org.eclipse.ocl.pivot.Class.class);
		js.append(" " + staticTypeName + " = ");
		//		js.appendReferenceTo(evaluatorParameter);
		js.append(executorName);
		js.append(".getStaticTypeOfValue(null, ");
		js.appendValueName(source);
		js.append(");\n");
		//
		//	Dispatch: Determine dynamic operation
		//
		js.append("final ");
		js.appendClassReference(true, LibraryIteration.LibraryIterationExtension.class);
		js.append(" " + implementationName + " = (");
		js.appendClassReference(null, LibraryIteration.LibraryIterationExtension.class);
		js.append( ")" + staticTypeName + ".lookupImplementation(");
		js.appendReferenceTo(globalNameManager.useRootExecutableNameManager(cgIterationCallExp).lazyGetStandardLibraryVariable());
		js.append(", ");
		js.appendQualifiedLiteralName(referredIteration);
		js.append(");\n");
		//
		if (iterateResult != null) {
			CGValuedElement init = CGUtil.getInit(iterateResult);
			accumulatorName = init.getResolvedName();
			if (!js.appendLocalStatements(init)) {
				return false;
			}
			js.appendDeclaration(iterateResult);
			js.append(" = ");
			js.appendValueName(init);
			js.append(";\n");
			//
			/*			js.append("Object " + accumulatorName + " = " + implementationName + ".createAccumulatorValue(");
//			js.appendValueName(evaluatorParameter);
			js.append(JavaConstants.EVALUATOR_NAME);
			js.append(", ");
			js.appendValueName(resultType);
			js.append(", ");
			js.appendValueName(body.getTypeId());
			js.append(");\n"); */
		}
		else {
			accumulatorName = "ACC_" + astName;
			js.append("final ");
			js.appendIsRequired(true);
			js.append(" Object " + accumulatorName + " = " + implementationName + ".createAccumulatorValue(");
			//			js.appendValueName(evaluatorParameter);
			js.append(executorName);
			js.append(", ");
			js.appendValueName(resultType);
			js.append(", ");
			js.appendValueName(body.getTypeId());
			js.append(");\n");
		}
		//
		js.append("/**\n");
		js.append(" * Implementation of the iterator body.\n");
		js.append(" */\n");
		js.append("final ");
		js.appendClassReference(true, operationClass);
		js.append(" " + bodyName + " = new ");
		js.appendClassReference(null, operationClass);
		js.append("()");
		js.pushClassBody(String.valueOf(operationClass));
		js.appendOptionalBlankLine();					// XXX delete me
		js.appendCommentWithOCL(null, body.getAst());
		js.append("@Override\n");
		js.append("public ");
		js.appendIsRequired(false);
		js.append(" Object ");
		js.append(globalNameManager.getEvaluateName());
		js.append("(final ");
		//		js.appendDeclaration(evaluatorParameter);
		js.appendClassReference(true, Executor.class);
		js.append(" ");
		js.append(executorName);
		js.append(", ");
		js.append("final ");
		//		js.appendDeclaration(currentNameManager.getTypeIdParameter(cgIterateCallExp));
		js.appendClassReference(true, TypeId.class);
		js.append(" ");
		js.append(globalNameManager.getTypeIdNameResolution().getResolvedName());
		if (useMultiple) {
			js.append(", final ");
			js.appendIsRequired(false);
			js.append(" Object ");
			js.appendIsRequired(true);
			js.append(" [] ");
			js.append(JavaConstants.SOURCE_AND_ARGUMENT_VALUES_NAME);
		}
		else {
			if (iterateResult != null) {
				js.append(", ");
				js.appendDeclaration(iterateResult);
			}
			else {
				js.append(", final ");
				js.appendIsRequired(false);
				js.append(" Object ");
				js.appendValueName(source);
				//				js.appendDeclaration(source);
			}
			for (int i = 0; i < arity; i++) {
				CGIterator iterator = iterators.get(i);
				js.append(", final ");
				js.appendDeclaration(iterator);
//				js.appendIsRequired(false);
//				js.append(" Object ");
//				js.appendValueName(iterator);
			}
			if (coIterators.size() > 0) {
				for (int i = 0; i < arity; i++) {
					js.append(", final ");
					js.appendDeclaration(coIterators.get(i));
				}
			}
		}
		js.append(") {\n");
		js.pushIndentation(null);
		if (useMultiple) {
			int argIndex = 0;			// Skip source
			Boolean isRequired = codeGenerator.isRequired(source);
			if (isRequired == Boolean.TRUE) {
				js.appendSuppressWarningsNull(true);
			}
			js.append("final ");
			js.appendTypeDeclaration(source);
			js.append(" ");
			js.appendValueName(source);
			js.append(" = ");
			js.appendClassCast(source, isRequired, Object.class, new ArgumentSubStream(js, argIndex));
			js.append(";\n");
			argIndex++;
			for (int i = 0; i < arity; i++) {
				CGParameter iterator = iterators.get(i);
				isRequired = codeGenerator.isRequired(iterator);
				if (isRequired == Boolean.TRUE) {
					js.appendSuppressWarningsNull(true);
				}
				js.append("final ");
				js.appendDeclaration(iterator);
				js.append(" = ");
				js.appendClassCast(iterator, isRequired, Object.class, new ArgumentSubStream(js, argIndex));
				js.append(";\n");
				argIndex++;
			}
			if (coIterators.size() > 0) {
				for (int i = 0; i < arity; i++) {
					CGIterator coIterator = coIterators.get(i);
					Variable asCoIterator = CGUtil.getAST(coIterator);
					if (!asCoIterator.isIsImplicit()) {
						isRequired = codeGenerator.isRequired(coIterator);
						if (isRequired == Boolean.TRUE) {
							js.appendSuppressWarningsNull(true);
						}
						js.append("final ");
						js.appendDeclaration(coIterator);
						js.append(" = ");
						js.appendClassCast(coIterator, isRequired, Object.class, new ArgumentSubStream(js, argIndex));
						js.append(";\n");
					}
					argIndex++;
				}
			}
		}
		cg2javaVisitor.appendReturn(body);
		js.popIndentation();
		js.append("}\n");
		js.popClassBody(true);
		//
		//	Dispatch: Create execution manager
		//
		js.append("final ");
		js.appendClassReference(true, managerClass);
		js.append(" " + managerName + " = new ");
		js.appendClassReference(null, managerClass);
		js.append("(");
		//		js.appendReferenceTo(evaluatorParameter);
		js.append(executorName);
		js.append(", ");
		if (useMultiple) {
			js.append(arity + ", ");
		}
		if (passesCoIterators) {
			js.append((coIterators.size() > 0) + ", ");
		}
		js.appendValueName(resultType);
		js.append(", " + bodyName + ", ");
		js.appendReferenceTo(isMap ? MapValue.class : CollectionValue.class, source);
		//		js.appendValueName(source);
		js.append(", " + accumulatorName + ");\n");
		//
		//	Dispatch: Invoke iteration
		//
		boolean isRequiredNullCast = expectedIsNonNull && !actualIsNonNull;
		if (isRequiredNullCast) {
			js.appendSuppressWarningsNull(true);
		}
		js.appendDeclaration(cgIterationCallExp);
		js.append(" = ");
		//		if (isRequiredNullCast) {
		//			js.appendClassReference(null, ClassUtil.class);
		//			js.append(".nonNullState(");
		//		}
		SubStream castBody4 = new SubStream() {
			@Override
			public void append() {
				js.append(implementationName + ".evaluateIteration(" + managerName + ")");
				//				if (isRequiredNullCast) {
				//					js.append(")");
				//				}
			}
		};
		js.appendClassCast(cgIterationCallExp, isRequiredNullCast, actualReturnClass, castBody4);
		js.append(";\n");
		return true;
	}
}
