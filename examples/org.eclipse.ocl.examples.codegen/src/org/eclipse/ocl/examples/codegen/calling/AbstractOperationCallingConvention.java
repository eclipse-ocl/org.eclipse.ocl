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
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.internal.library.ConstrainedOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 *  AbstractOperationCallingConvention defines the default support for an operation declaration or call.
 */
public abstract class AbstractOperationCallingConvention implements OperationCallingConvention
{
	protected @NonNull CGCallExp constrainedOperationCall(@NonNull AS2CGVisitor as2cgVisitor, @NonNull OperationCallExp element,
			CGValuedElement cgSource, @NonNull Operation finalOperation, @NonNull ConstrainedOperation constrainedOperation) {
		@NonNull CGLibraryOperationCallExp cgOperationCallExp = CGModelFactory.eINSTANCE.createCGLibraryOperationCallExp();
		cgOperationCallExp.setSource(cgSource);
		//		cgOperationCallExp.setThisIsSelf(false);
		for (@NonNull OCLExpression pArgument : ClassUtil.nullFree(element.getOwnedArguments())) {
			CGValuedElement cgArgument = as2cgVisitor.doVisit(CGValuedElement.class, pArgument);
			cgOperationCallExp.getArguments().add(cgArgument);
		}
		as2cgVisitor.setAst(cgOperationCallExp, element);
		cgOperationCallExp.setReferredOperation(finalOperation);
		cgOperationCallExp.setLibraryOperation(constrainedOperation);
		if (as2cgVisitor.getCodeGenerator().addConstrainedOperation(finalOperation)) {
			//			CGNamedElement cgOperation = finalOperation.accept(this);
			//			if (cgOperation != null) {
			//				cgOperation.toString();
			//			}
		}
		return cgOperationCallExp;
	}
}
