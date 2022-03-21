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
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 *  AbstractOperationCallingConvention defines the default support for an operation declaration or call.
 */
public abstract class AbstractOperationCallingConvention implements OperationCallingConvention
{
	protected void init(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperationCallExp cgOperationCallExp,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp, boolean isRequired) {
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
		cgOperationCallExp.setReferredOperation(asOperation);
		as2cgVisitor.setAst(cgOperationCallExp, asOperationCallExp);
		cgOperationCallExp.setInvalidating(asOperation.isIsInvalidating());
		cgOperationCallExp.setValidating(asOperation.isIsValidating());
		cgOperationCallExp.setRequired(isRequired);
		cgOperationCallExp.setSource(cgSource);
		//		cgOperationCallExp.getDependsOn().add(cgSource);
		for (@NonNull OCLExpression asArgument : ClassUtil.nullFree(asOperationCallExp.getOwnedArguments())) {
			CGValuedElement cgArgument = as2cgVisitor.doVisit(CGValuedElement.class, asArgument);
			cgOperationCallExp.getArguments().add(cgArgument);
			//			cgOperationCallExp.getDependsOn().add(cgArgument);
		}
	}
}
