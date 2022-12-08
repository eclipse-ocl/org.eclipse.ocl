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
package org.eclipse.ocl.examples.codegen.oclinjunit;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.calling.LibraryOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.naming.NameResolution;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  The JUnitOperationCallingConvention defines the support for an operation configured for use in a JUnit test.
 *  </br>
 *  e.g. as TestClass.INSTANCE.evaluate(executor, typeId, self, arguments)
 */
public class JUnitOperationCallingConvention extends LibraryOperationCallingConvention
{
	private static final @NonNull JUnitOperationCallingConvention INSTANCE = new JUnitOperationCallingConvention();

	public static @NonNull JUnitOperationCallingConvention getInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
		INSTANCE.logInstance(asOperation, maybeVirtual);
		return INSTANCE;
	}

	@Override
	public @NonNull CGOperation createCGOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
		NameResolution evaluateNameResolution = analyzer.getGlobalNameManager().getEvaluateNameResolution();
		assert evaluateNameResolution.getResolvedName().equals(asOperation.getName());
		CGLibraryOperation cgOperation = CGModelFactory.eINSTANCE.createCGLibraryOperation();
		evaluateNameResolution.addCGElement(cgOperation);
		return cgOperation;
	}

	@Override
	public void createCGParameters(@NonNull ExecutableNameManager operationNameManager, @Nullable ExpressionInOCL expressionInOCL) {
		assert expressionInOCL != null;
		CodeGenAnalyzer analyzer = operationNameManager.getAnalyzer();
		JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
		CGOperation cgOperation = (CGOperation)operationNameManager.getCGScope();
		Variable contextVariable = expressionInOCL.getOwnedContext();
	//	if (contextVariable != null) {
	//		contextVariable.setIsRequired(false); 				// May be null for test
	//	}
		List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgOperation);
		cgParameters.add(codeGenerator.createExecutorParameter());
		cgParameters.add(codeGenerator.createTypeIdParameter());
		if (contextVariable != null) {
			CGParameter cgContext = operationNameManager.getCGParameter(contextVariable, (String)null);			// XXX getSelf ???
			cgContext.setIsSelf(true);
			cgContext.setTypeId(analyzer.getCGTypeId(TypeId.OCL_VOID));			// JUnit evaluate overrides
			cgContext.setRequired(false);										//  self : Object[?]
			analyzer.getGlobalNameManager().getSelfNameResolution().addCGElement(cgContext);
			cgParameters.add(cgContext);
		}
		Iterable<@NonNull Variable> asParameterVariables = PivotUtil.getOwnedParameters(expressionInOCL);
		createCGParameters4asParameterVariables(operationNameManager, cgParameters, asParameterVariables);
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		CGValuedElement body = cg2javaVisitor.getExpression(cgOperation.getBody());
		//
		js.append("@Override\n");
		js.append("public ");
		boolean cgOperationIsInvalid = cgOperation.getInvalidValue() != null;
		js.appendIsCaught(!cgOperationIsInvalid, cgOperationIsInvalid);
		js.append(" ");
		js.appendClassReference(Boolean.FALSE, cgOperation);			// Overrides @Nullable Boolean
		js.append(" ");
		js.appendValueName(cgOperation);
		appendParameterList(js, cgOperation);
		appendBody(cg2javaVisitor, body);
		return true;
	}

	@Override
	public boolean needsGeneration() {
		return true;
	}
}
