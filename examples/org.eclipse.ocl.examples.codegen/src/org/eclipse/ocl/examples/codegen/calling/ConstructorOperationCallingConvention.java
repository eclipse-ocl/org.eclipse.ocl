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
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.NestedNameManager;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqualExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsInvalidExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsUndefinedExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGThrowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyEqualOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyNotEqualOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsInvalidOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsUndefinedOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  ConstructorOperationCallingConvention defines the support for the call of a cache costructor.
 */
public class ConstructorOperationCallingConvention extends AbstractOperationCallingConvention
{
	public static final @NonNull ConstructorOperationCallingConvention INSTANCE = new ConstructorOperationCallingConvention();

	@Override
	protected void appendBody(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGValuedElement body) {
		js.append(" {\n");
		js.pushIndentation(null);
	//	cg2javaVisitor.appendReturn(body);
		if (js.appendLocalStatements(body)) {
			if (body instanceof CGThrowExp) {				// FIXME generalize
				body.accept(cg2javaVisitor);
			}
			else {
				CGInvalid cgInvalidValue = body.getInvalidValue();
				if (cgInvalidValue != null) {
					js.append("throw ");
					js.appendValueName(cgInvalidValue);
					js.append(";\n");
				}
			//	else {
			//		js.append("return ");
			//		js.appendValueName(body);
			//	}
			}
		}
		js.popIndentation();
		js.append("}\n");
	}

	@Override
	public @NonNull CGOperation createCGOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
//		return fallbackCreateCGOperationWithoutBody(as2cgVisitor, asOperation);
 	//	PivotMetamodelManager metamodelManager = analyzer.getMetamodelManager();
	//	LibraryFeature libraryOperation = metamodelManager.getImplementation(asOperation);
	//	assert !(libraryOperation instanceof EObjectOperation);
	//	assert !(libraryOperation instanceof ForeignOperation);
	//	assert !(libraryOperation instanceof ConstrainedOperation);
		CGLibraryOperation cgOperation = CGModelFactory.eINSTANCE.createCGLibraryOperation();
		initOperation(analyzer, cgOperation, asOperation);
	//	analyzer.addCGOperation(cgOperation);		-- avoid multiple mappings
		return cgOperation;
	}

	@Override
	public @NonNull CGCallExp createCGOperationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		if (libraryOperation instanceof OclAnyOclIsInvalidOperation) {
			CGIsInvalidExp cgIsInvalidExp = CGModelFactory.eINSTANCE.createCGIsInvalidExp();
			cgIsInvalidExp.setSource(cgSource);
			as2cgVisitor.initAst(cgIsInvalidExp, asOperationCallExp);
		//	as2cgVisitor.declareLazyName(cgIsInvalidExp);
			cgIsInvalidExp.setInvalidating(false);
			cgIsInvalidExp.setValidating(true);
			return cgIsInvalidExp;
		}
		if (libraryOperation instanceof OclAnyOclIsUndefinedOperation) {
			CGIsUndefinedExp cgIsUndefinedExp = CGModelFactory.eINSTANCE.createCGIsUndefinedExp();
			cgIsUndefinedExp.setSource(cgSource);
			as2cgVisitor.initAst(cgIsUndefinedExp, asOperationCallExp);
		//	as2cgVisitor.declareLazyName(cgIsUndefinedExp);
			cgIsUndefinedExp.setInvalidating(false);
			cgIsUndefinedExp.setValidating(true);
			return cgIsUndefinedExp;
		}
		if (libraryOperation instanceof OclAnyEqualOperation) {
			OCLExpression pArgument = PivotUtil.getOwnedArgument(asOperationCallExp, 0);
			CGValuedElement cgArgument = as2cgVisitor.doVisit(CGValuedElement.class, pArgument);
			CGIsEqualExp cgIsEqualExp = CGModelFactory.eINSTANCE.createCGIsEqualExp();
			cgIsEqualExp.setNotEquals(libraryOperation instanceof OclAnyNotEqualOperation);
			cgIsEqualExp.setSource(cgSource);
			cgIsEqualExp.setArgument(cgArgument);
			as2cgVisitor.initAst(cgIsEqualExp, asOperationCallExp);
		//	as2cgVisitor.declareLazyName(cgIsEqualExp);
			cgIsEqualExp.setInvalidating(false);
			cgIsEqualExp.setValidating(true);
			return cgIsEqualExp;
		}
		throw new IllegalStateException("Unsupported built-in " + libraryOperation);
	}

	@Override
	public void createCGParameters(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @Nullable ExpressionInOCL bodyExpression) {
		NestedNameManager nameManager = as2cgVisitor.getNameManager();
		assert bodyExpression == null;
		Operation asOperation = CGUtil.getAST(cgOperation);
		List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgOperation);
		if (!asOperation.isIsStatic()) {
//XXX			CGParameter cgParameter = nameManager.getSelfParameter();
			//			cgParameter.setTypeId(context.getTypeId(JavaConstants.getJavaTypeId(Object.class)));
			//			cgParameter.setRequired(contextVariable.isIsRequired());
//			cgParameters.add(cgParameter);
		}
		for (@NonNull Parameter parameterVariable : ClassUtil.nullFree(asOperation.getOwnedParameters())) {
			CGParameter cgParameter = nameManager.getParameter(parameterVariable, (String)null);
			cgParameters.add(cgParameter);
		}
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		throw new UnsupportedOperationException();		// XXX
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
		CGValuedElement body = cg2javaVisitor.getExpression(cgOperation.getBody());
		//
		js.append("protected ");
		js.appendValueName(cgOperation);
		appendParameterList(js, cgOperation);
		appendBody(cg2javaVisitor, js, body);
		return true;
	}

	@Override
	public boolean needsGeneration() {
		return true;
	}

	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperation cgOperation) {
	//	CGValuedElement body = cgOperation.getBody();
	//	assert body instanceof CGSequence;		-- no value
	}
}
