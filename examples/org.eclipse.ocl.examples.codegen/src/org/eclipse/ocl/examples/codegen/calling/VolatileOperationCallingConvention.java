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
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
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
public class VolatileOperationCallingConvention extends ConstrainedOperationCallingConvention	// CF ForeignOperationCallingConvention
{
	public static final @NonNull VolatileOperationCallingConvention INSTANCE = new VolatileOperationCallingConvention();

	@Override
	public @NonNull CGValuedElement createCGOperationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		OCLExpression asSource = asOperationCallExp.getOwnedSource();
	//	assert asSource != null;
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
		Operation asFinalOperation = null;	// FIXME cast
		if (asSource != null) {
			Type sourceType = asSource.getType();
			asFinalOperation = as2cgVisitor.getCodeGenerator().isFinal(asOperation, (org.eclipse.ocl.pivot.Class)sourceType);	// FIXME cast
		}
		CGClass currentClass = as2cgVisitor.getNameManager().findCGScope();
		assert currentClass == null;
		CGOperationCallExp cgCallExp = null;
		assert asFinalOperation != null;
		if (asFinalOperation != null) {
			LanguageExpression bodyExpression = asOperation.getBodyExpression();
			assert bodyExpression != null;
			if (bodyExpression != null) {
				CGValuedElement cgOperationCallExp2 = as2cgVisitor.inlineOperationCall(asOperationCallExp, bodyExpression);
				assert cgOperationCallExp2 == null;
				CGOperation cgFinalOperation = as2cgVisitor.getAnalyzer().getCGOperation(asOperation);
				cgCallExp = volatileOperationCall(as2cgVisitor, asOperationCallExp, cgSource, cgFinalOperation, (ConstrainedOperation)libraryOperation);
			}
		}
		if (cgCallExp == null) {
			Operation baseOperation = asOperation;	// FIXME
			CGOperation cgBaseOperation = cgOperation; //as2cgVisitor.getAnalyzer().getCGOperation(baseOperation);
			cgCallExp = volatileOperationCall(as2cgVisitor, asOperationCallExp, cgSource, cgBaseOperation, (ConstrainedOperation)libraryOperation);
		}
		cgCallExp.setReferredOperation(cgOperation);
		return cgCallExp;
	}

	@Override
	public @NonNull CGOperation createCGOperation(@NonNull CodeGenAnalyzer analyzer, @Nullable Type asSourceType, @NonNull Operation asOperation) {
		assert analyzer.getMetamodelManager().getImplementation(asOperation) instanceof ConstrainedOperation;
		org.eclipse.ocl.pivot.Package asPackage = PivotUtil.getOwningPackage(PivotUtil.getOwningClass(asOperation));
		assert !(asPackage instanceof Library);
		CGLibraryOperation cgOperation = CGModelFactory.eINSTANCE.createCGLibraryOperation();
		analyzer.installOperation(asOperation, cgOperation, this);
		return cgOperation;
	}

	@Override
	public void createCGParameters(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @Nullable ExpressionInOCL bodyExpression) {
		super.createCGParameters(as2cgVisitor, cgOperation, bodyExpression);
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
		return super.generateJavaDeclaration(cg2javaVisitor, js, cgOperation);
	}

	protected @NonNull CGOperationCallExp volatileOperationCall(@NonNull AS2CGVisitor as2cgVisitor, @NonNull OperationCallExp element,
			CGValuedElement cgSource, @NonNull CGOperation cgFinalOperation, @NonNull ConstrainedOperation constrainedOperation) {
		CGLibraryOperationCallExp cgOperationCallExp = CGModelFactory.eINSTANCE.createCGLibraryOperationCallExp();
		cgOperationCallExp.setLibraryOperation(constrainedOperation);
		if (cgSource != null) {
			cgOperationCallExp.getArguments().add(cgSource);
		}
		//		cgOperationCallExp.setThisIsSelf(false);
		for (@NonNull OCLExpression pArgument : ClassUtil.nullFree(element.getOwnedArguments())) {
			CGValuedElement cgArgument = as2cgVisitor.doVisit(CGValuedElement.class, pArgument);
			cgOperationCallExp.getArguments().add(cgArgument);
		}
		as2cgVisitor.initAst(cgOperationCallExp, element);
	//	as2cgVisitor.declareLazyName(cgOperationCallExp);
	//	cgOperationCallExp.setReferredOperation(finalOperation);
		cgOperationCallExp.setReferredOperation(cgFinalOperation);
		if (as2cgVisitor.getCodeGenerator().addConstrainedOperation(CGUtil.getAST(cgFinalOperation))) {
		}
		return cgOperationCallExp;
	}
}
