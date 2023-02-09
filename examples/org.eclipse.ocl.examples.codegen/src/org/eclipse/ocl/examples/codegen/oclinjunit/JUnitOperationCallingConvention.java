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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.calling.LibraryOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.naming.NameResolution;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Variable;

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
		NameResolution evaluateNameResolution = analyzer.getGlobalNameManager().getEvaluateName();
		assert evaluateNameResolution.getResolvedName().equals(asOperation.getName());
		CGLibraryOperation cgOperation = CGModelFactory.eINSTANCE.createCGLibraryOperation();
		evaluateNameResolution.addCGElement(cgOperation);
		return cgOperation;
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		CGValuedElement body = cg2javaVisitor.getExpression(cgOperation.getBody());
		//
		js.append("// " + cgOperation.getCallingConvention() + "\n");
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
	protected @NonNull CGParameterStyle @NonNull [] getCGParameterStyles(@NonNull ExecutableNameManager operationNameManager) {
		Operation asOperation = (Operation)operationNameManager.getASScope();
		ExpressionInOCL expressionInOCL = (ExpressionInOCL)asOperation.getBodyExpression();
		Variable contextVariable = expressionInOCL.getOwnedContext();
		assert contextVariable != null;
		return CG_PARAMETER_STYLES_JUNIT_SELF;
	}

	@Override
	public boolean needsGeneration() {
		return true;
	}
}
