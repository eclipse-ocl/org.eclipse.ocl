/*******************************************************************************
 * Copyright (c) 2023 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.analyzer;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.cgmodel.util.AbstractExtendingCGModelVisitor;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Operation;

/**
 * Perform a tree descent to propagate the parent requirement for a reified invalid to the variable expressions that
 * may reference that invlaid and so may require that the source let-vaible catches the invalid.
 */
public class FieldingValidationVisitor extends AbstractExtendingCGModelVisitor<Object, @NonNull FieldingAnalyzer>
{
	/**
	 * True if invalids must be reified for access by a validating operation.
	 */
	protected boolean isValidating;

	public FieldingValidationVisitor(@NonNull FieldingAnalyzer context, boolean isValidating) {
		super(context);
		this.isValidating = isValidating;
	}

	public @NonNull FieldingAnalyzer getFieldingAnalyzer() {
		return context;
	}

	@Override
	public @NonNull String toString() {
		return isValidating ? "VALIDATING" : "NOT-VALIDATING";
	}

	@Override
	public Object visiting(@NonNull CGElement visitable) {
		throw new UnsupportedOperationException(getClass().getSimpleName() + ": " + visitable.getClass().getSimpleName());
	}

	/**
	 * By default the need for validaion just propgates down the tree.
	 */
	@Override
	public Object visitCGElement(@NonNull CGElement cgElement) {
		for (@NonNull CGElement cgChild : cgElement.getChildren()) {
			visit(cgChild);
		}
		return null;
	}

	/**
	 * All children of a validating operation must be caught.
	 *
	@Override
	public Object visitCGIsUndefinedExp(@NonNull CGIsUndefinedExp cgIsUndefinedExp) {
		CGValuedElement cgSource = CGUtil.getSource(cgIsUndefinedExp);
		context.getMustBeCaughtVisitor().visit(cgSource);
		cgIsUndefinedExp.setCaught(false);
		return ReturnState.IS_VALID;
	} */

/*	@Override
	public Object visitCGIterationCallExp(@NonNull CGIterationCallExp cgIterationCallExp) {
		CGOperation cgIteration = CGUtil.getIteration(cgIterationCallExp);
		Operation asIteration = CGUtil.getAST(cgIteration);
		String s = String.valueOf(cgIterationCallExp.getAst());
	//	System.out.println("\t" + s);
		if (s.contains("select(")) {
			getClass();		// XXX
		}
		FieldingValidationVisitor mustBeThrownVisitor = context.getMustBeThrownVisitor();
		mustBeThrownVisitor.visit(CGUtil.getSource(cgIterationCallExp));
		for (CGIterator cgIterator : CGUtil.getIterators(cgIterationCallExp)) {
			mustBeThrownVisitor.visit(cgIterator);
		}
		for (CGIterator cgCoIterator : CGUtil.getCoIterators(cgIterationCallExp)) {
			mustBeThrownVisitor.visit(cgCoIterator);
		}
		FieldingValidationVisitor bodyAnalysisVisitor = asIteration.isIsValidating() ? context.getMustBeCaughtVisitor() : mustBeThrownVisitor;
		CGValuedElement cgBody = CGUtil.getBody(cgIterationCallExp);
		boolean isValid = cgBody.isNonInvalid();
		ReturnState returnState = bodyAnalysisVisitor.visit(cgBody);
		assert isValid || returnState.isSuitableFor(asIteration.isIsValidating() ? ReturnState.IS_CAUGHT : ReturnState.IS_THROWN);
		// Although individual body evaluations may be caught and accumulated, the accumulated result is thrown.
		cgIterationCallExp.setCaught(false);
		return ReturnState.IS_THROWN;
	} */

	/**
	 * All children of a validating operation must be caught.
	 * The result of an invalidating operation is thrown.
	 */
	@Override
	public Object visitCGOperationCallExp(@NonNull CGOperationCallExp cgOperationCallExp) {
		CGOperation cgOperation = CGUtil.getOperation(cgOperationCallExp);
		Operation asOperation = CGUtil.getAST(cgOperation);
		if (isValidating == asOperation.isIsValidating()) {
			return super.visitCGOperationCallExp(cgOperationCallExp);
		}
		else if (isValidating) {
			return context.getNotValidatingVisitor().visitCGOperationCallExp(cgOperationCallExp);
		}
		else {
			return context.getIsValidatingVisitor().visitCGOperationCallExp(cgOperationCallExp);
		}
	}

	@Override
	public Object visitCGVariableExp(@NonNull CGVariableExp cgVariableExp) {
		if (isValidating) {
			CGVariable cgVariable = CGUtil.getReferredVariable(cgVariableExp);
			context.addValidated(cgVariable);
		}
		return null;
	}
}