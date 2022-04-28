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
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Operation;

/**
 *  The SupportOperationCallingConvention defines the support for the call of a native (Java) operation
 *  defined by a Java method and using boxed source and arguments.
 *  </br>
 *  e.g. as aModelManager.basicGetForeignPropertyValue(arguments)
 */
public class SupportOperationCallingConvention extends NativeOperationCallingConvention
{
	public static final @NonNull SupportOperationCallingConvention INSTANCE = new SupportOperationCallingConvention();

	@Override
	public boolean mayThrowException() {
		return false;
	}

	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperationCallExp cgOperationCallExp) {
		CGNativeOperationCallExp cgNativeOperationCallExp = (CGNativeOperationCallExp)cgOperationCallExp;
		CGOperation cgOperation = CGUtil.getOperation(cgNativeOperationCallExp);
		Operation asOperation = CGUtil.getAST(cgOperation);
		// No boxing for cgThis
		List<@NonNull CGValuedElement> cgArguments = CGUtil.getArgumentsList(cgNativeOperationCallExp);
		int iMax = cgArguments.size();
		for (int i = 0; i < iMax; i++) {			// Avoid CME from rewrite
			CGValuedElement cgArgument = cgArguments.get(i);
		//	if (i == 0) {
		//		boxingAnalyzer.rewriteAsGuarded(cgArgument, boxingAnalyzer.isSafe(cgNativeOperationCallExp), "source for '" + asOperation + "'");
		//	}
			boxingAnalyzer.rewriteAsBoxed(cgArgument);
		}
	}
}
