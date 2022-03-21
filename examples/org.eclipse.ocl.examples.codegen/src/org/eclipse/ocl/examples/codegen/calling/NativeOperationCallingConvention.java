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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 *  NativeOperationCallingConvention defines the support for the call of a native (Java) operation.
 *  </br>
 *  e.g. as anObject.anOperation(arguments)
 */
public class NativeOperationCallingConvention extends AbstractOperationCallingConvention
{
	public static final @NonNull NativeOperationCallingConvention INSTANCE = new NativeOperationCallingConvention();

	@Override
	public @NonNull CGCallExp createCGOperationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
	/*	LanguageExpression bodyExpression = asOperation.getBodyExpression();
		if (bodyExpression != null) {
			CGValuedElement cgOperationCallExp2 = as2cgVisitor.inlineOperationCall(asOperationCallExp, bodyExpression);
			if (cgOperationCallExp2 != null) {
				return cgOperationCallExp2;
			}
		} */
		CGNativeOperationCallExp cgNativeOperationCallExp = CGModelFactory.eINSTANCE.createCGNativeOperationCallExp();
		cgNativeOperationCallExp.setSource(cgSource);
		cgNativeOperationCallExp.setThisIsSelf(true);
		for (@NonNull OCLExpression pArgument : ClassUtil.nullFree(asOperationCallExp.getOwnedArguments())) {
			CGValuedElement cgArgument = as2cgVisitor.doVisit(CGValuedElement.class, pArgument);
			cgNativeOperationCallExp.getArguments().add(cgArgument);
		}
		as2cgVisitor.setAst(cgNativeOperationCallExp, asOperationCallExp);
		cgNativeOperationCallExp.setReferredOperation(asOperation);
		return cgNativeOperationCallExp;
	}

	@Override
	public boolean isUnboxed() {
		return true;
	}
}
