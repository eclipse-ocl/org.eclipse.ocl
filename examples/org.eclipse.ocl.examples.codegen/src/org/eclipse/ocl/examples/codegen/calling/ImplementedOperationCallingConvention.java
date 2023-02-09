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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
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

	public static class ImplementedConstructorOperationCallingConvention extends AbstractConstructorOperationCallingConvention
	{
		private static final @NonNull ImplementedConstructorOperationCallingConvention INSTANCE = new ImplementedConstructorOperationCallingConvention();

		public static @NonNull ImplementedConstructorOperationCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			INSTANCE.logInstance(asClass);
			return INSTANCE;
		}

		@Override
		public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgConstructor) {	// merge with super
			Operation asCacheOperation = CGUtil.getAST(cgConstructor);
			ExpressionInOCL asCacheExpressionInOCL = (ExpressionInOCL)asCacheOperation.getBodyExpression();
			assert (asCacheExpressionInOCL == null);
			super.createCGBody(analyzer, cgConstructor);
		}
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
			AbstractConstructorOperationCallingConvention callingConvention = ImplementedConstructorOperationCallingConvention.getInstance(asEntryClass);
			callingConvention.createOperation(analyzer, cgEntryClass, asOperation);
		}
	}

	@Override
	public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOuterOperation) {
		// direct synthesis
	}

//	@Override
//	protected @NonNull CGParameterStyle @NonNull [] getCGParameterStyles(@NonNull ExecutableNameManager operationNameManager) {
//		return CG_PARAMETER_STYLES_SELF;
//	}

	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperationCallExp cgOperationCallExp) {
		// XXX Auto-generated method stub
		//	super.rewriteWithBoxingAndGuards(boxingAnalyzer, cgOperationCallExp);
	}
}
