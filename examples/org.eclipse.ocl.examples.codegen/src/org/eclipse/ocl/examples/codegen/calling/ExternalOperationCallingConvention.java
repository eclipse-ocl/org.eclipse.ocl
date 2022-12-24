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
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.naming.NameResolution;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  ExternalOperationCallingConvention defines the support for the call of a Complete OCL operation implemented by a Java class.
 */
public class ExternalOperationCallingConvention extends AbstractCachedOperationCallingConvention
{
	private static final @NonNull ExternalOperationCallingConvention INSTANCE = new ExternalOperationCallingConvention();

	public static @NonNull ExternalOperationCallingConvention getInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
		INSTANCE.logInstance(asOperation, maybeVirtual);
		return INSTANCE;
	}

	public static class ExternalCacheClassCallingConvention extends AbstractCacheClassCallingConvention
	{
		private static final @NonNull ExternalCacheClassCallingConvention INSTANCE = new ExternalCacheClassCallingConvention();

		public static @NonNull ExternalCacheClassCallingConvention getInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
			INSTANCE.logInstance(asOperation, maybeVirtual);
			return INSTANCE;
		}

		public static class ExternalEvaluateOperationCallingConvention extends AbstractEvaluateOperationCallingConvention
		{
			private static final @NonNull ExternalEvaluateOperationCallingConvention INSTANCE = new ExternalEvaluateOperationCallingConvention();

			public static @NonNull ExternalEvaluateOperationCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
				INSTANCE.logInstance(asClass);
				return INSTANCE;
			}

			@Override
			protected void createASParameters(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asCacheEvaluateOperation, @NonNull Operation asOperation) {
				GlobalNameManager globalNameManager = analyzer.getGlobalNameManager();
				String selfName = globalNameManager.getSelfNameResolution().getResolvedName();
				Parameter asEvaluateParameter = PivotUtil.createParameter(selfName, PivotUtil.getOwningClass(asOperation), true);
				List<@NonNull Parameter> asCacheEvaluateParameters = PivotUtilInternal.getOwnedParametersList(asCacheEvaluateOperation);
				asCacheEvaluateParameters.add(asEvaluateParameter);
				super.createASParameters(analyzer, asCacheEvaluateOperation, asOperation);
			}
		}

		@Override
		protected org.eclipse.ocl.pivot.@NonNull Package getParentPackage(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
			return analyzer.getRootClassParentPackage(asOperation);
		}

		@Override
		protected void installEvaluateOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgCacheClass, org.eclipse.ocl.pivot.@NonNull Class asEntryClass, @NonNull Operation asOperation) {
			org.eclipse.ocl.pivot.Class asCacheClass = CGUtil.getAST(cgCacheClass);
			ExternalEvaluateOperationCallingConvention callingConvention = ExternalEvaluateOperationCallingConvention.getInstance(asCacheClass);
			callingConvention.createOperation(analyzer, cgCacheClass, asOperation, asEntryClass);
		}
	}

	public static class ExternalEntryClassCallingConvention extends AbstractEntryClassCallingConvention
	{
		private static final @NonNull ExternalEntryClassCallingConvention INSTANCE = new ExternalEntryClassCallingConvention();

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
		protected org.eclipse.ocl.pivot.@NonNull Package getParentPackage(@NonNull CodeGenAnalyzer analyzer, @NonNull Feature asFeature) {
			return analyzer.getRootClassParentPackage(asFeature);
		}
	}

	@Override
	public @NonNull CGOperation createCGOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
		return CGModelFactory.eINSTANCE.createCGCachedOperation();
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
	public @NonNull CGOperation createOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation, @Nullable ExpressionInOCL asExpressionInOCL) {
		assert asOperation.getImplementationClass() == null;
		CGOperation cgOperation = createCGOperation(analyzer, asOperation);
		analyzer.initAst(cgOperation, asOperation, true);
		CGClass cgRootClass = analyzer.getCGRootClass(asOperation);
		cgRootClass.getOperations().add(cgOperation);
		createCachingClassesAndInstance(analyzer, cgOperation);
		cgOperation.setCallingConvention(this);
		assert asOperation == cgOperation.getAst();
		assert analyzer.basicGetCGElement(asOperation) != null;
		ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(cgOperation, asOperation);	// Needed to support downstream useOperationNameManager()
		assert cgOperation.eContainer() != null;
		createCGParameters(operationNameManager, asExpressionInOCL);
		return cgOperation;
	}

	@Override
	protected @NonNull AbstractCacheClassCallingConvention getCacheClassCallingConvention(@NonNull Operation asOperation) {
		return ExternalCacheClassCallingConvention.getInstance(asOperation, false);
	}

	@Override
	protected @NonNull AbstractEntryClassCallingConvention getEntryClassCallingConvention(@NonNull Operation asOperation) {
		return ExternalEntryClassCallingConvention.getInstance(asOperation, false);
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
