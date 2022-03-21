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
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.library.ConstrainedOperation;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 *  ConstrainedOperationCallingConvention defines the support for the call of a Complete OCL-defined operation.
 */
public class ConstrainedOperationCallingConvention extends AbstractOperationCallingConvention
{
	public static final @NonNull ConstrainedOperationCallingConvention INSTANCE = new ConstrainedOperationCallingConvention();

	@Override
	public @NonNull CGValuedElement createCGOperationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		OCLExpression asSource = asOperationCallExp.getOwnedSource();
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
	/*	LanguageExpression bodyExpression = asOperation.getBodyExpression();
		if (bodyExpression != null) {
			CGValuedElement cgOperationCallExp2 = as2cgVisitor.inlineOperationCall(asOperationCallExp, bodyExpression);
			if (cgOperationCallExp2 != null) {
				return cgOperationCallExp2;
			}
		} */
	/*	CGNativeOperationCallExp cgNativeOperationCallExp = CGModelFactory.eINSTANCE.createCGNativeOperationCallExp();
		cgNativeOperationCallExp.setSource(cgSource);
		cgNativeOperationCallExp.setThisIsSelf(true);
		for (@NonNull OCLExpression pArgument : ClassUtil.nullFree(asOperationCallExp.getOwnedArguments())) {
			CGValuedElement cgArgument = as2cgVisitor.doVisit(CGValuedElement.class, pArgument);
			cgNativeOperationCallExp.getArguments().add(cgArgument);
		}
		as2cgVisitor.setAst(cgNativeOperationCallExp, asOperationCallExp);
		cgNativeOperationCallExp.setReferredOperation(asOperation); */


		assert asSource != null;
			Type sourceType = ClassUtil.nonNullState(asSource.getType());
			Operation finalOperation = as2cgVisitor.getCodeGenerator().isFinal(asOperation, (org.eclipse.ocl.pivot.Class)sourceType);	// FIXME cast
			CGClass currentClass = as2cgVisitor.basicGetCurrentClass();
			if (finalOperation != null) {
				LanguageExpression bodyExpression = asOperation.getBodyExpression();
				if (bodyExpression != null) {
					CGValuedElement cgOperationCallExp2 = as2cgVisitor.inlineOperationCall(asOperationCallExp, bodyExpression);
					if (cgOperationCallExp2 != null) {
						return cgOperationCallExp2;
					} else if (currentClass != null) {
						return as2cgVisitor.cachedOperationCall(asOperationCallExp, currentClass, cgSource, finalOperation, null);
					} else {
						return constrainedOperationCall(as2cgVisitor, asOperationCallExp, cgSource, finalOperation, (ConstrainedOperation)libraryOperation);
					}
				}
			}
			if (currentClass != null) {
				Iterable<@NonNull Operation> overrides = as2cgVisitor.getMetamodelManager().getFinalAnalysis().getOverrides(asOperation);
				return as2cgVisitor.cachedOperationCall(asOperationCallExp, currentClass, cgSource, asOperation, overrides);
			} else {
				Operation baseOperation = asOperation;	// FIXME
				return constrainedOperationCall(as2cgVisitor, asOperationCallExp, cgSource, baseOperation, (ConstrainedOperation)libraryOperation);
			}
	//	}
	//	return cgNativeOperationCallExp;
	}

	@Override
	public boolean isBoxed() {
		return true;
	}
}
