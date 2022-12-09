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
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.library.ConstrainedOperation;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  VolatileOperationCallingConvention defines the support for the call of a Complete OCL-defined operation
 *  that is evaluated on demand without any caching to suprress re-evaluation.
 *
 *  If defined as part of an OCL stdlib, he operation is ioked when called. If defined as part of a
 *  Complete OCL document or OCLinEcore enrichment, the operations is invoked via a cache to avoid re-execution.
 *
 *  -- only used by QVTd
 */
public class VolatileOperationCallingConvention extends AbstractOperationCallingConvention
{
	private static final @NonNull VolatileOperationCallingConvention INSTANCE = new VolatileOperationCallingConvention();

	public static @NonNull OperationCallingConvention getInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
		INSTANCE.logInstance(asOperation, maybeVirtual);
		return INSTANCE;
	}

	protected void appendForeignOperationName(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		JavaCodeGenerator codeGenerator = cg2javaVisitor.getCodeGenerator();
		CGOperation cgOperation = CGUtil.getOperation(cgOperationCallExp);
		Operation asReferredOperation = CGUtil.getAsOperation(cgOperationCallExp);
		org.eclipse.ocl.pivot.Class asReferredClass = PivotUtil.getOwningClass(asReferredOperation);
		CGClass cgReferringClass = CGUtil.getContainingClass(cgOperationCallExp);
		assert cgReferringClass != null;
		String flattenedClassName = codeGenerator.getQualifiedForeignClassName(asReferredClass);
		js.append(flattenedClassName);
		js.append(".");
		js.appendValueName(cgOperation);
	}

	@Override
	public @NonNull CGValuedElement createCGOperationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		OCLExpression asSource = asOperationCallExp.getOwnedSource();
	//	assert asSource != null;
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
		Operation asFinalOperation = null;	// FIXME cast
		if (asSource != null) {
			Type sourceType = asSource.getType();
			asFinalOperation = analyzer.getCodeGenerator().isFinal(asOperation, (org.eclipse.ocl.pivot.Class)sourceType);	// FIXME cast
		}
	//	CGClass currentClass = analyzer.getNameManager().getCGClass();
		CGOperationCallExp cgCallExp = null;
		assert asFinalOperation != null;
		if (asFinalOperation != null) {
			LanguageExpression bodyExpression = asOperation.getBodyExpression();
			assert bodyExpression != null;
			if (bodyExpression != null) {
				CGValuedElement cgOperationCallExp2 = analyzer.inlineOperationCall(asOperationCallExp, bodyExpression);
				assert cgOperationCallExp2 == null;
				CGOperation cgFinalOperation = analyzer.getCGOperation(asOperation);
				cgCallExp = volatileOperationCall(analyzer, asOperationCallExp, cgSource, cgFinalOperation, (ConstrainedOperation)libraryOperation);
			}
		}
		if (cgCallExp == null) {
			Operation baseOperation = asOperation;	// FIXME
			CGOperation cgBaseOperation = cgOperation; //as2cgVisitor.getAnalyzer().getCGOperation(baseOperation);
			cgCallExp = volatileOperationCall(analyzer, asOperationCallExp, cgSource, cgBaseOperation, (ConstrainedOperation)libraryOperation);
		}
		cgCallExp.setReferredOperation(cgOperation);
		return cgCallExp;
	}

	@Override
	public @NonNull CGOperation createCGOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
		assert analyzer.getMetamodelManager().getImplementation(asOperation) instanceof ConstrainedOperation;
		org.eclipse.ocl.pivot.Package asPackage = PivotUtil.getOwningPackage(PivotUtil.getOwningClass(asOperation));
		assert !(asPackage instanceof Library);
		return CGModelFactory.eINSTANCE.createCGLibraryOperation();
	}

	@Override
	public void createCGParameters(@NonNull ExecutableNameManager operationNameManager, @Nullable ExpressionInOCL bodyExpression) {
		super.createCGParameters(operationNameManager, bodyExpression);
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		// XXX should have been ForeignOperationCallingConvention
		if (!generateLocals(cg2javaVisitor, js, cgOperationCallExp)) {
			return false;
		}


	/*	Operation pOperation = cgOperationCallExp.getReferredOperation();
		boolean thisIsSelf = cgOperationCallExp.isThisIsSelf();
	//	CGValuedElement source = cg2javaVisitor.getExpression(cgOperationCallExp.getSource());
		List<CGValuedElement> cgArguments = cgOperationCallExp.getCgArguments();
		List<Parameter> pParameters = pOperation.getOwnedParameters();
		//
	//	if (!js.appendLocalStatements(source)) {
	//		return false;
	//	}
		for (@SuppressWarnings("null")@NonNull CGValuedElement cgArgument : cgArguments) {
			CGValuedElement argument = cg2javaVisitor.getExpression(cgArgument);
			if (!js.appendLocalStatements(argument)) {
				return false;
			}
		} */




		js.appendDeclaration(cgOperationCallExp);
		js.append(" = ");
		appendForeignOperationName(cg2javaVisitor, js, cgOperationCallExp);
		js.append("(");
		generateArgumentList(cg2javaVisitor, js, cgOperationCallExp);
		js.append(");\n");
		return true;
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
		throw new UnsupportedOperationException();
/*		CGValuedElement body = cg2javaVisitor.getExpression(cgOperation.getBody());
		//
		appendDeclaration(cg2javaVisitor, js, cgOperation);
		appendParameterList(js, cgOperation);
		appendBody(cg2javaVisitor, js, body);
		return true; */
	}

	protected @NonNull CGOperationCallExp volatileOperationCall(@NonNull CodeGenAnalyzer analyzer, @NonNull OperationCallExp element,
			CGValuedElement cgSource, @NonNull CGOperation cgFinalOperation, @NonNull ConstrainedOperation constrainedOperation) {
		CGLibraryOperationCallExp cgOperationCallExp = CGModelFactory.eINSTANCE.createCGLibraryOperationCallExp();
		cgOperationCallExp.setLibraryOperation(constrainedOperation);
		if (cgSource != null) {
			cgOperationCallExp.getArguments().add(cgSource);
		}
		//		cgOperationCallExp.setThisIsSelf(false);
		for (@NonNull OCLExpression pArgument : ClassUtil.nullFree(element.getOwnedArguments())) {
			CGValuedElement cgArgument = analyzer.createCGElement(CGValuedElement.class, pArgument);
			cgOperationCallExp.getArguments().add(cgArgument);
		}
		analyzer.initAst(cgOperationCallExp, element, true);
	//	as2cgVisitor.declareLazyName(cgOperationCallExp);
	//	cgOperationCallExp.setReferredOperation(finalOperation);
		cgOperationCallExp.setReferredOperation(cgFinalOperation);
		if (analyzer.getCodeGenerator().addConstrainedOperation(CGUtil.getAST(cgFinalOperation))) {
		}
		return cgOperationCallExp;
	}
}
