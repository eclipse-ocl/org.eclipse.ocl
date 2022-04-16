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
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.calling.LibraryOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  The JUnitOperationCallingConvention defines the support for an operation configued for use in a JUnit test.
 *  </br>
 *  e.g. as aModelManager.basicGetForeignPropertyValue(arguments)
 */
public class JUnitOperationCallingConvention extends LibraryOperationCallingConvention
{
	public static final @NonNull JUnitOperationCallingConvention INSTANCE = new JUnitOperationCallingConvention();

	@Override
	public @NonNull CGCallExp createCGOperationCallExp(
			@NonNull AS2CGVisitor as2cgVisitor,
			@NonNull CGOperation cgOperation,
			@NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource,
			@NonNull OperationCallExp asOperationCallExp) {
		// TODO Auto-generated method stub
		return super.createCGOperationCallExp(as2cgVisitor, cgOperation,
			libraryOperation, cgSource, asOperationCallExp);
	}

	@Override
	public void createParameters(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @Nullable ExpressionInOCL expressionInOCL) {
		assert expressionInOCL != null;
		Variable contextVariable = expressionInOCL.getOwnedContext();
		if (contextVariable != null) {
			contextVariable.setIsRequired(false); // May be null for test
		}
		JUnitLocalContext localContext = (JUnitLocalContext)as2cgVisitor.getLocalContext();
		List<CGParameter> cgParameters = cgOperation.getParameters();
		cgParameters.add(localContext.createExecutorParameter());
		cgParameters.add(localContext.createTypeIdParameter());
		if (contextVariable != null) {
			CGParameter cgContext = as2cgVisitor.getParameter(contextVariable, (String)null);			// getSelf ???
			cgParameters.add(cgContext);
		}
		for (@NonNull Variable parameterVariable : PivotUtil.getOwnedParameters(expressionInOCL)) {
			CGParameter cgParameter = as2cgVisitor.getParameter(parameterVariable, (String)null);
			cgParameters.add(cgParameter);
		}
	}

	@Override
	public @NonNull Boolean generateJava(
			@NonNull CG2JavaVisitor<?> cg2JavaVisitor, @NonNull JavaStream js,
			@NonNull CGOperationCallExp cgOperationCallExp) {
		// TODO Auto-generated method stub
		return super.generateJava(cg2JavaVisitor, js, cgOperationCallExp);
	}

	@Override
	public boolean isStatic(@NonNull CGOperation cgOperation) {
		// TODO Auto-generated method stub
		return super.isStatic(cgOperation);
	}

	@Override
	public boolean getExecutorIsParameter() {
		return true;
	}

	@Override
	protected void init(@NonNull AS2CGVisitor as2cgVisitor,
			@NonNull CGOperationCallExp cgOperationCallExp,
			@NonNull OperationCallExp asOperationCallExp,
			@NonNull CGOperation cgOperation,
			@Nullable CGValuedElement cgSource, boolean isRequired) {
		// TODO Auto-generated method stub
		super.init(as2cgVisitor, cgOperationCallExp, asOperationCallExp, cgOperation,
			cgSource, isRequired);
	}

	@Override
	public boolean isBoxed() {
		return true;
	}

	@Override
	public boolean isUnboxed() {
		return false;
	}
}
