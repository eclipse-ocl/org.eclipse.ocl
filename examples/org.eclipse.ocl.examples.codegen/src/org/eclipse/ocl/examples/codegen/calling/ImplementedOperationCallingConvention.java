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
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Operation;

/**
 *  ImplementedOperationCallingConvention defines the support for the call of an operation implemented by a Java class.
 */
public class ImplementedOperationCallingConvention extends ExternalOperationCallingConvention
{
	private static final @NonNull ImplementedOperationCallingConvention INSTANCE = new ImplementedOperationCallingConvention();

	public static @NonNull ImplementedOperationCallingConvention getInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
		INSTANCE.logInstance(asOperation, maybeVirtual);
		return INSTANCE;
	}

	public static class ImplementedConstructorOperationCallingConvention extends ConstructorOperationCallingConvention
	{
		private static final @NonNull ImplementedConstructorOperationCallingConvention INSTANCE = new ImplementedConstructorOperationCallingConvention();

		public static @NonNull OperationCallingConvention getInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
			INSTANCE.logInstance(asOperation, maybeVirtual);
			return INSTANCE;
		}

		@Override
		public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgConstructor) {	// merge with super
			Operation asCacheOperation = CGUtil.getAST(cgConstructor);
			ExpressionInOCL asCacheExpressionInOCL = (ExpressionInOCL)asCacheOperation.getBodyExpression();
			assert (asCacheExpressionInOCL == null);





			//			asCacheOperation.setBodyExpression();
			super.createCGBody(analyzer, cgConstructor);
		}

		//	@Override
		//	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperationCallExp cgOperationCallExp) {
		// XXX Auto-generated method stub
		//	super.rewriteWithBoxingAndGuards(boxingAnalyzer, cgOperationCallExp);
		//	}
	}

	/**
	 *  ExternalEntryClassCallingConvention refines the standard EntryClassCallingConvention for the cache of a specific evaluation
	 *  to support a local class for an non-local facility.
	 */
	public static class ImplementedEntryClassCallingConvention extends ExternalEntryClassCallingConvention
	{
		private static final @NonNull ImplementedEntryClassCallingConvention INSTANCE = new ImplementedEntryClassCallingConvention();

		public static @NonNull ImplementedEntryClassCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			INSTANCE.logInstance(asClass);
			return INSTANCE;
		}

		@Override
		protected void installConstructorOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgEntryClass, @NonNull Operation asOperation) {
			org.eclipse.ocl.pivot.Class asEntryClass = CGUtil.getAST(cgEntryClass);
			ConstructorOperationCallingConvention callingConvention = ImplementedConstructorOperationCallingConvention.getInstance(asEntryClass);
			callingConvention.createOperation(analyzer, cgEntryClass, asOperation);
		}
	}

	@Override
	public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOuterOperation) {
		// direct synthesis
	}

	@Override
	public @NonNull CGOperation createCGOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
		assert asOperation.getImplementationClass() != null;
		assert asOperation.getBodyExpression() == null;
		CGCachedOperation cgOperation = CGModelFactory.eINSTANCE.createCGCachedOperation();
		analyzer.initAst(cgOperation, asOperation, true);
		CGClass cgRootClass = analyzer.getCGRootClass(asOperation);
		cgRootClass.getOperations().add(cgOperation);
		createCachingClassesAndInstance(analyzer, cgOperation);
		return cgOperation;
	}

	@Override
	public void createCGParameters(@NonNull ExecutableNameManager operationNameManager, @Nullable ExpressionInOCL bodyExpression) {
		assert bodyExpression == null;
		CGOperation cgOperation = (CGOperation)operationNameManager.getCGScope();
		List<CGParameter> cgParameters = cgOperation.getParameters();
		cgParameters.add(operationNameManager.getSelfParameter());
		//	for (@NonNull Variable asParameterVariable : ClassUtil.nullFree(bodyExpression.getOwnedParameters())) {
		//		CGParameter cgParameter = createCGParameter(operationNameManager, asParameterVariable);
		//		cgParameters.add(cgParameter);
		//	}
	}

//	@Override
//	protected @NonNull AbstractEntryClassCallingConvention getEntryClassCallingConvention(org.eclipse.ocl.pivot.@NonNull Class asClass) {
//		return ImplementedEntryClassCallingConvention.getInstance(asClass);
//	}

	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperationCallExp cgOperationCallExp) {
		// XXX Auto-generated method stub
		//	super.rewriteWithBoxingAndGuards(boxingAnalyzer, cgOperationCallExp);
	}
}
