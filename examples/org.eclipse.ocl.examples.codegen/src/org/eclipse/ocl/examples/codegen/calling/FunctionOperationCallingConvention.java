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
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Operation;

/**
 *  FunctionOperationCallingConvention defines the support for the call of a QVTi function.
 */
public abstract class FunctionOperationCallingConvention extends AbstractCachedOperationCallingConvention2	// cf ConstrainedOperationCallingConvention
{
	public static class FunctionEntryClassCallingConvention extends AbstractEntryClassCallingConvention
	{
		private static final @NonNull FunctionEntryClassCallingConvention INSTANCE = new FunctionEntryClassCallingConvention();

		public static @NonNull FunctionEntryClassCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Operation asOperation, boolean maybeVirtual) {
			INSTANCE.logInstance(asOperation, maybeVirtual);
			return INSTANCE;
		}
	}

	protected final org.eclipse.ocl.pivot.@NonNull Class createEntryClass(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation) {
		Operation asOperation = CGUtil.getAST(cgOperation);
		AbstractEntryClassCallingConvention callingConvention = getEntryClassCallingConvention(asOperation);
		return callingConvention.createEntryClass(analyzer, cgOperation);
	}

	protected @NonNull AbstractEntryClassCallingConvention getEntryClassCallingConvention(@NonNull Operation asOperation) {
		return FunctionEntryClassCallingConvention.getInstance(asOperation, false);
	}
}
