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

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.naming.NameResolution;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.utilities.AbstractLanguageSupport;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  ExternalOperationCallingConvention defines the support for the call of a Complete OCL operation implemented by a Java class.
 */
public class ExternalOperationCallingConvention extends FunctionOperationCallingConvention
{
	private static final @NonNull ExternalOperationCallingConvention INSTANCE = new ExternalOperationCallingConvention();

	public static @NonNull ExternalOperationCallingConvention getInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
		INSTANCE.logInstance(asOperation, maybeVirtual);
		return INSTANCE;
	}

	/**
	 *  ExternalEntryClassCallingConvention refines the standard EntryClassCallingConvention for the cache of a specific evaluation
	 *  to support a local class for an non-local facility.
	 */
	public static class ExternalEntryClassCallingConvention extends EntryClassCallingConvention
	{
		private static final @NonNull ExternalEntryClassCallingConvention INSTANCE = new ExternalEntryClassCallingConvention();

		//		public static @NonNull ExternalEntryClassCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		//			INSTANCE.logInstance(asClass);
		//		}

		public static @NonNull ExternalEntryClassCallingConvention getInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
			INSTANCE.logInstance(asOperation, maybeVirtual);
			return INSTANCE;
		}

		@Override
		protected @NonNull Class getContextClass(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgCacheClass) {
			org.eclipse.ocl.pivot.Class asCacheClass = CGUtil.getAST(cgCacheClass);
			Operation asOperation = analyzer.getCachedOperation(asCacheClass);
			return PivotUtil.getOwningClass(asOperation);
		}

		@Override
		protected @NonNull NameResolution getContextNameResolution(@NonNull GlobalNameManager globalNameManager) {
			return globalNameManager.getSelfNameResolution();
		}

		@Override
		protected org.eclipse.ocl.pivot.@NonNull Package getParentPackage(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
			org.eclipse.ocl.pivot.Class asClass = PivotUtil.getOwningClass(asOperation);
			CGClass cgClass = analyzer.getCGRootClass(asClass);
			org.eclipse.ocl.pivot.Class asRootClass = CGUtil.getAST(cgClass);
			return AbstractLanguageSupport.getCachePackage(asRootClass);
		}
	}

	public static class ExternalEvaluateOperationCallingConvention extends AbstractEvaluateOperationCallingConvention
	{
		private static final @NonNull ExternalEvaluateOperationCallingConvention INSTANCE = new ExternalEvaluateOperationCallingConvention();

		//		public static @NonNull OperationCallingConvention getInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
		//			INSTANCE.logInstance(asOperation, maybeVirtual);
		//		}

		public static @NonNull ExternalEvaluateOperationCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			INSTANCE.logInstance(asClass);
			return INSTANCE;
		}

		@Override
		protected @Nullable Parameter createConstructorEvaluateOperationSelfParameter(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
			CodeGenerator codeGenerator = analyzer.getCodeGenerator();
			GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
			NameResolution selfNameResolution = globalNameManager.getSelfNameResolution();
			Parameter asEvaluateParameter = PivotUtil.createParameter(selfNameResolution.getResolvedName(), PivotUtil.getOwningClass(asOperation), true);
			return asEvaluateParameter;
		}

		@Override
		protected void generateJavaOperationBody(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
			CodeGenAnalyzer analyzer = cg2javaVisitor.getAnalyzer();
			GlobalNameManager globalNameManager = analyzer.getGlobalNameManager();
			Operation asOperation = CGUtil.getAST(cgOperation);
			org.eclipse.ocl.pivot.Class asConstructorClass = PivotUtil.getOwningClass(asOperation);
			org.eclipse.ocl.pivot.Class asCacheClass = analyzer.getEntryClass(asConstructorClass);
			CGClass cgCacheClass = analyzer.getCGClass(asCacheClass);
			js.append("return ((");
			js.appendClassReference(cgCacheClass);
			js.append(")getUniqueComputation(");
			boolean isFirst = true;
			for (@NonNull CGParameter cgParameter : CGUtil.getParameters(cgOperation)) {
				if (!isFirst) {
					js.append(", ");
				}
				js.appendValueName(cgParameter);
				isFirst = false;
			}
			js.append(")).");
			js.append(globalNameManager.getCachedResultNameResolution().getResolvedName());
			js.append(";\n");
		}
	}

	@Override
	public @NonNull CGOperation createCGOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
		assert asOperation.getImplementationClass() == null;
		CGCachedOperation cgOperation = CGModelFactory.eINSTANCE.createCGCachedOperation();
		analyzer.initAst(cgOperation, asOperation, true);
		CGClass cgRootClass = analyzer.getCGRootClass(asOperation);
		cgRootClass.getOperations().add(cgOperation);
		ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(cgOperation, asOperation);
		org.eclipse.ocl.pivot.Class asEntryClass = createEntryClass(operationNameManager, asOperation);
		org.eclipse.ocl.pivot.Class asCacheClass = createCacheClass(operationNameManager, asEntryClass);
		createCacheInstance(operationNameManager, asCacheClass, asEntryClass);
		return cgOperation;
	}

	@Override
	public @NonNull CGValuedElement createCGOperationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		Operation asOperation = CGUtil.getAST(cgOperation);
		//	assert QVTimperativeUtil.basicGetShadowExp(asFunction) == null;
		CGOperationCallExp cgOperationCallExp = CGModelFactory.eINSTANCE.createCGCachedOperationCallExp();
		initCallExp(analyzer, cgOperationCallExp, asOperationCallExp, cgOperation, asOperation.isIsRequired());
		assert cgSource != null;
		cgOperationCallExp.getArguments().add(cgSource);
		initCallArguments(analyzer, cgOperationCallExp);
		return cgOperationCallExp;
	}

	@Override
	public void createCGParameters(@NonNull ExecutableNameManager operationNameManager, @Nullable ExpressionInOCL bodyExpression) {
		assert bodyExpression != null;
		CGOperation cgOperation = (CGOperation)operationNameManager.getCGScope();
		List<CGParameter> cgParameters = cgOperation.getParameters();
		cgParameters.add(operationNameManager.getSelfParameter());
		for (@NonNull Variable asParameterVariable : ClassUtil.nullFree(bodyExpression.getOwnedParameters())) {
			CGParameter cgParameter = createCGParameter(operationNameManager, asParameterVariable);
			cgParameters.add(cgParameter);
		}
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		return generateJavaEvaluateCall(cg2javaVisitor, js, cgOperationCallExp);
	}

	@Override
	protected org.eclipse.ocl.pivot.@NonNull Package getCachePackage(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
		org.eclipse.ocl.pivot.Class asClass = PivotUtil.getOwningClass(asOperation);
		CGClass cgClass = analyzer.getCGRootClass(asClass);
		org.eclipse.ocl.pivot.Class asRootClass = CGUtil.getAST(cgClass);
		return AbstractLanguageSupport.getCachePackage(asRootClass);
	}

	@Override
	protected @NonNull EntryClassCallingConvention getEntryClassCallingConvention(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		//		return ExternalEntryClassCallingConvention.getInstance(asClass);
		throw new UnsupportedOperationException();
	}

	@Override
	protected @NonNull EntryClassCallingConvention getEntryClassCallingConvention(@NonNull Operation asOperation) {
		return ExternalEntryClassCallingConvention.getInstance(asOperation, false);
	}

	@Override
	protected @NonNull AbstractEvaluateOperationCallingConvention getEvaluateOperationCallingConvention(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return ExternalEvaluateOperationCallingConvention.getInstance(asClass);
	}

	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperation cgOperation) {
		// TODO Auto-generated method stub
		super.rewriteWithBoxingAndGuards(boxingAnalyzer, cgOperation);
	}

	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperationCallExp cgOperationCallExp) {
		if (cgOperationCallExp.eContainer() != cgOperationCallExp.getReferredOperation()) {
			super.rewriteWithBoxingAndGuards(boxingAnalyzer, cgOperationCallExp);
		}
		else {
			// the inner call does not need boxing or guarding
		}
	}
}
