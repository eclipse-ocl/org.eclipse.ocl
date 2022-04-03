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

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGForeignOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  ForeignOperationCallingConvention defines the support for the call of an operation realized by an
 *  implementation in the *Tables class.
 *   *  </br>
 *  e.g. as XXXTables.FOREIGN_qualified_class.FC_class.INSTANCE.evaluate(executor, arguments)
 */
public class ForeignOperationCallingConvention extends AbstractOperationCallingConvention
{
	public static final @NonNull ForeignOperationCallingConvention INSTANCE = new ForeignOperationCallingConvention();

	@Override
	public @NonNull CGCallExp createCGOperationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
		assert cgSource == null;
		assert asOperation.isIsStatic();
		CodeGenAnalyzer analyzer = as2cgVisitor.getAnalyzer();
		analyzer.addForeignFeature(asOperation);
		boolean isRequired = asOperation.isIsRequired();
		CGForeignOperationCallExp cgForeignOperationCallExp = CGModelFactory.eINSTANCE.createCGForeignOperationCallExp();
		CGVariable executorVariable = as2cgVisitor.getExecutorVariable();
		cgForeignOperationCallExp.getArguments().add(as2cgVisitor.createCGVariableExp(executorVariable));
		init(as2cgVisitor, cgForeignOperationCallExp, asOperationCallExp, cgOperation, cgSource, isRequired);
		return cgForeignOperationCallExp;
	}

	@Override
	public void createParameters(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @Nullable ExpressionInOCL expressionInOCL) {
		CGParameter cgParameter = (CGParameter) as2cgVisitor.getExecutorVariable();
		cgOperation.getParameters().add(cgParameter);
		super.createParameters(as2cgVisitor, cgOperation, expressionInOCL);
	}

	@Override
	public @NonNull Boolean generateJava(@NonNull CG2JavaVisitor<?> cg2JavaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		CGForeignOperationCallExp cgForeignOperationCallExp = (CGForeignOperationCallExp)cgOperationCallExp;
		CodeGenAnalyzer analyzer = cg2JavaVisitor.getAnalyzer();
		JavaCodeGenerator codeGenerator = cg2JavaVisitor.getCodeGenerator();
		Operation asReferredOperation = CGUtil.getReferredOperation(cgForeignOperationCallExp);
		boolean isStatic = asReferredOperation.isIsStatic();
		CGValuedElement cgSource = null;
		List<CGValuedElement> cgArguments = cgForeignOperationCallExp.getArguments();
		//
		if (!isStatic) {
			cgSource = cg2JavaVisitor.getExpression(cgForeignOperationCallExp.getSource());
			if (!js.appendLocalStatements(cgSource)) {
				return false;
			}
		}
		for (@SuppressWarnings("null")@NonNull CGValuedElement cgArgument : cgArguments) {
			CGValuedElement argument = cg2JavaVisitor.getExpression(cgArgument);
			if (!js.appendLocalStatements(argument)) {
				return false;
			}
		}
		org.eclipse.ocl.pivot.Class asReferredClass = PivotUtil.getOwningClass(asReferredOperation);
		List<Parameter> asParameters = asReferredOperation.getOwnedParameters();
		//
		CGClass cgReferringClass = CGUtil.getContainingClass(cgForeignOperationCallExp);
		assert cgReferringClass != null;
		String flattenedClassName = codeGenerator.getQualifiedForeignClassName(asReferredClass);
		js.appendDeclaration(cgForeignOperationCallExp);
		js.append(" = ");
		js.append(flattenedClassName);
		js.append(".op_");
		js.append(PivotUtil.getName(asReferredOperation));
		js.append("(");
		if (!isStatic) {
			CGTypeId cgTypeId = analyzer.getTypeId(asReferredOperation.getOwningClass().getTypeId());
			TypeDescriptor sourceTypeDescriptor = codeGenerator.getUnboxedDescriptor(ClassUtil.nonNullState(cgTypeId.getElementId()));
			js.appendReferenceTo(sourceTypeDescriptor, cgSource);
		}
		int iMax = Math.min(asParameters.size(), cgArguments.size());
		for (int i = 0; i < iMax; i++) {
			if ((i > 0) || !isStatic) {
				js.append(", ");
			}
			CGValuedElement cgArgument = cgArguments.get(i);
			Parameter asParameter = asParameters.get(i);
			CGTypeId cgTypeId = analyzer.getTypeId(asParameter.getTypeId());
			TypeDescriptor parameterTypeDescriptor = codeGenerator.getUnboxedDescriptor(ClassUtil.nonNullState(cgTypeId.getElementId()));
			CGValuedElement argument = cg2JavaVisitor.getExpression(cgArgument);
			js.appendReferenceTo(parameterTypeDescriptor, argument);
		}
		js.append(");\n");
		return true;
	}

	@Override
	public boolean getExecutorIsParameter() {
		return true;
	}

	@Override
	public boolean isBoxed() {
		return true;
	}
}
