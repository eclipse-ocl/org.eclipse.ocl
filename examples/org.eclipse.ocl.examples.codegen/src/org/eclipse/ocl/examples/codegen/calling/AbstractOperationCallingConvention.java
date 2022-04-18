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
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 *  AbstractOperationCallingConvention defines the default support for an operation declaration or call.
 */
public abstract class AbstractOperationCallingConvention implements OperationCallingConvention
{

	@Override
	public void createParameters(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @Nullable ExpressionInOCL expressionInOCL) {
		if (expressionInOCL != null) {
			Variable contextVariable = expressionInOCL.getOwnedContext();
			if (contextVariable != null) {
				CGParameter cgParameter = as2cgVisitor.getSelfParameter(contextVariable);
				//			cgParameter.setTypeId(context.getTypeId(JavaConstants.getJavaTypeId(Object.class)));
				//			cgParameter.setRequired(contextVariable.isIsRequired());
				cgOperation.getParameters().add(cgParameter);
			}
			for (@NonNull Variable parameterVariable : ClassUtil.nullFree(expressionInOCL.getOwnedParameters())) {
				CGParameter cgParameter;
				if (cgOperation instanceof CGEcoreOperation) {
					cgParameter = as2cgVisitor.getParameter(parameterVariable, parameterVariable.getName());
				}
				else {
					cgParameter = as2cgVisitor.getParameter(parameterVariable, (String)null);
				}
				//			cgParameter.setTypeId(context.getTypeId(JavaConstants.getJavaTypeId(Object.class)));
				//			cgParameter.setRequired(parameterVariable.isIsRequired());
				cgOperation.getParameters().add(cgParameter);
			}
		}
		else {
			Operation asOperation = CGUtil.getAST(cgOperation);
			if (!asOperation.isIsStatic()) {
				CGParameter cgParameter = as2cgVisitor.getLocalContext().getSelfParameter();
				//			cgParameter.setTypeId(context.getTypeId(JavaConstants.getJavaTypeId(Object.class)));
				//			cgParameter.setRequired(contextVariable.isIsRequired());
				cgOperation.getParameters().add(cgParameter);
			}
			for (@NonNull Parameter parameterVariable : ClassUtil.nullFree(asOperation.getOwnedParameters())) {
				CGParameter cgParameter = as2cgVisitor.getParameter(parameterVariable, (String)null);
				cgOperation.getParameters().add(cgParameter);
			}
		}
	}

	@Override
	public @NonNull Boolean generateJava(@NonNull CG2JavaVisitor<?> cg2JavaVisitor, @NonNull JavaStream js,@NonNull CGOperationCallExp cgOperationCallExp) {
		throw new UnsupportedOperationException();		// XXX
	}

	protected void init(@NonNull AS2CGVisitor as2cgVisitor,
			@NonNull CGOperationCallExp cgOperationCallExp, @NonNull OperationCallExp asOperationCallExp,
			@NonNull CGOperation cgOperation, @Nullable CGValuedElement cgSource, boolean isRequired) {		// XXX wip eliminate isRequired
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
		boolean isRequired2 = asOperation.isIsRequired();
		Boolean ecoreIsRequired = as2cgVisitor.getCodeGenerator().isNonNull(asOperationCallExp);
		if (ecoreIsRequired != null) {
			isRequired2 = ecoreIsRequired;
		}
	// XXX	assert isRequired == isRequired2;
		cgOperationCallExp.setReferredOperation(asOperation);
		cgOperationCallExp.setAst(asOperationCallExp);
		TypeId asTypeId = asOperationCallExp.getTypeId();
		cgOperationCallExp.setTypeId(as2cgVisitor.getAnalyzer().getTypeId(asTypeId));
		cgOperationCallExp.setOperation(cgOperation);
		cgOperationCallExp.setInvalidating(asOperation.isIsInvalidating());
		cgOperationCallExp.setValidating(asOperation.isIsValidating());
		cgOperationCallExp.setRequired(isRequired);
		cgOperationCallExp.setSource(cgSource);
		for (@NonNull OCLExpression asArgument : ClassUtil.nullFree(asOperationCallExp.getOwnedArguments())) {
			CGValuedElement cgArgument = as2cgVisitor.doVisit(CGValuedElement.class, asArgument);
			cgOperationCallExp.getArguments().add(cgArgument);
		}
	//	as2cgVisitor.getNameManager().declareStandardName(cgOperationCallExp);
	}

	@Override
	public boolean isStatic(@NonNull CGOperation cgOperation) {
		Operation asOperation = CGUtil.getAST(cgOperation);
		return asOperation.isIsStatic();
	}

	@Override
	public @NonNull String toString() {
		return getClass().getSimpleName() + (isBoxed() ? " boxed" : "") + (isEcore() ? " ecore" : "") + (isUnboxed() ? " unboxed" : "");
	}
}
