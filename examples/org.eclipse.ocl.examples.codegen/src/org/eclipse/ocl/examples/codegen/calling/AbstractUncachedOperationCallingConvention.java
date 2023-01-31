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

import java.lang.reflect.Method;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaLanguageSupport;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *
 * AbstractUncachedOperationCallingConvention defines the common functionality of an operation that may be called directly or may form
 * part of the internal functionality of a cached facility.
 */
public abstract class AbstractUncachedOperationCallingConvention extends AbstractOperationCallingConvention
{
	@Override
	public @NonNull CGOperation createCGOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
		return CGModelFactory.eINSTANCE.createCGCachedOperation();
	}

	@Override
	public @NonNull CGValuedElement createCGOperationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void createCGParameters(@NonNull ExecutableNameManager operationNameManager, @Nullable ExpressionInOCL expressionInOCL) {
//		throw new UnsupportedOperationException();
//		assert expressionInOCL != null;
		CodeGenAnalyzer analyzer = operationNameManager.getAnalyzer();
		CGOperation cgOperation = (CGOperation)operationNameManager.getCGScope();
		List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgOperation);
		if (expressionInOCL != null) {
			Variable asContextVariable = expressionInOCL.getOwnedContext();
			if (asContextVariable != null) {
				CGParameter cgParameter = analyzer.getSelfParameter(operationNameManager, asContextVariable);
				cgParameters.add(cgParameter);
				assertCGParameterStyles(CG_PARAMETER_STYLES_BODY_SELF_PARAMETERS, operationNameManager, expressionInOCL);
			}
			else {
				assertCGParameterStyles(CG_PARAMETER_STYLES_PARAMETERS, operationNameManager, expressionInOCL);
			}
			Iterable<@NonNull Variable> asParameterVariables = PivotUtil.getOwnedParameters(expressionInOCL);
			createCGParameters4asParameterVariables(operationNameManager, cgParameters, asParameterVariables);
		//	initCGParameters(operationNameManager, null);
		}
		else {
			Operation asOperation = CGUtil.getAST(cgOperation);
			if (!asOperation.isIsStatic()) {						// XXX Static is a derived CC
				CGParameter cgParameter = operationNameManager.getSelfParameter();
				cgParameters.add(cgParameter);
				assertCGParameterStyles(CG_PARAMETER_STYLES_SELF_PARAMETERS, operationNameManager, expressionInOCL);
			}
			else {
				assertCGParameterStyles(CG_PARAMETER_STYLES_PARAMETERS, operationNameManager, expressionInOCL);
			}
			List<@NonNull Parameter> asParameters = PivotUtilInternal.getOwnedParametersList(asOperation);
			createCGParameters4asParameters(operationNameManager, cgParameters, asParameters);
		}
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		Method jMethod =  JavaLanguageSupport.getOverriddenMethod(cgOperation);
		if (jMethod != null) {
			js.append("@Override\n");
		}
		js.append("public ");
		if (CGUtil.getAST(cgOperation).isIsStatic()) {
			js.append("static ");
		}
		js.appendTypeDeclaration(cgOperation);
		js.append(" ");
		js.appendValueName(cgOperation);
		appendParameterList(js, cgOperation);
		js.append(" {\n");
		js.pushIndentation(null);
		generateJavaOperationBody(cg2javaVisitor, cgOperation);
		js.popIndentation();
		js.append("}\n");
		return true;
	}

	protected void generateJavaOperationBody(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
		CGValuedElement body = cg2javaVisitor.getExpression(cgOperation.getBody());
		cg2javaVisitor.appendReturn(body);
	}

	@Override
	protected @NonNull CGParameterStyle @NonNull [] getCGParameterStyles(@NonNull ExecutableNameManager operationNameManager, @Nullable TypedElement zzasOrigin) {
		Operation asOperation = (Operation)operationNameManager.getASScope();
		ExpressionInOCL bodyExpression = (ExpressionInOCL)asOperation.getBodyExpression();
		if (bodyExpression != null) {
			Variable asContextVariable = bodyExpression.getOwnedContext();
			if (asContextVariable != null) {
				return CG_PARAMETER_STYLES_BODY_SELF_PARAMETERS;
			}
			else {
				return CG_PARAMETER_STYLES_PARAMETERS;
			}
		}
		else {
			if (!asOperation.isIsStatic()) {
				return CG_PARAMETER_STYLES_SELF_PARAMETERS;
			}
			else {
				return CG_PARAMETER_STYLES_PARAMETERS;
			}
		}
	}

	protected void installExpressionInOCLBody(@NonNull Operation asOperation, @NonNull ExpressionInOCL asExpressionInOCL, @NonNull OCLExpression asBody) {
		asExpressionInOCL.setOwnedBody(asBody);
		asExpressionInOCL.setType(asBody.getType());
		asExpressionInOCL.setIsRequired(asBody.isIsRequired());
		asOperation.setBodyExpression(asExpressionInOCL);
	}
}
