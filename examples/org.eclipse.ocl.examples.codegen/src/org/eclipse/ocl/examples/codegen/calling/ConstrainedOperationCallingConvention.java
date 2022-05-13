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
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
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
 *  ConstrainedOperationCallingConvention defines the support for the call of a Complete OCL-defined operation.
 *  If defined as part of an OCL stdlib, he operation is ioked when called. If defined as part of a
 *  Complete OCL document or OCLinEcore enrichment, the operations is invoked via a cache to avoid re-execution.
 */
public class ConstrainedOperationCallingConvention extends AbstractOperationCallingConvention	// CF ForeignOperationCallingConvention
{
	public static final @NonNull ConstrainedOperationCallingConvention INSTANCE = new ConstrainedOperationCallingConvention();

	protected void appendForeignOperationName(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		JavaCodeGenerator codeGenerator = cg2javaVisitor.getCodeGenerator();
		CGOperation cgOperation = CGUtil.getOperation(cgOperationCallExp);
		Operation asReferredOperation = CGUtil.getReferredOperation(cgOperationCallExp);
		org.eclipse.ocl.pivot.Class asReferredClass = PivotUtil.getOwningClass(asReferredOperation);
		CGClass cgReferringClass = CGUtil.getContainingClass(cgOperationCallExp);
		assert cgReferringClass != null;
		String flattenedClassName = codeGenerator.getQualifiedForeignClassName(asReferredClass);
		js.append(flattenedClassName);
		js.append(".");
		js.appendValueName(cgOperation);
	}

	@Override
	public @NonNull CGOperation createCGOperationWithoutBody(@NonNull AS2CGVisitor as2cgVisitor, @NonNull Operation asOperation) {
		assert as2cgVisitor.getMetamodelManager().getImplementation(asOperation) instanceof ConstrainedOperation;
		org.eclipse.ocl.pivot.Package asPackage = PivotUtil.getOwningPackage(PivotUtil.getOwningClass(asOperation));
		if (asPackage instanceof Library) {
			return CGModelFactory.eINSTANCE.createCGLibraryOperation();
		}
		else {
			return CGModelFactory.eINSTANCE.createCGCachedOperation();
		}
	}

	protected @NonNull CGOperationCallExp constrainedOperationCall(@NonNull AS2CGVisitor as2cgVisitor, @NonNull OperationCallExp element,
			CGValuedElement cgSource, @NonNull Operation finalOperation, @NonNull ConstrainedOperation constrainedOperation) {
		@NonNull CGLibraryOperationCallExp cgOperationCallExp = as2cgVisitor.createCGLibraryOperationCallExp(constrainedOperation);
		if (cgSource != null) {
			cgOperationCallExp.getCgArguments().add(cgSource);
		}
		//		cgOperationCallExp.setThisIsSelf(false);
		for (@NonNull OCLExpression pArgument : ClassUtil.nullFree(element.getOwnedArguments())) {
			CGValuedElement cgArgument = as2cgVisitor.doVisit(CGValuedElement.class, pArgument);
			cgOperationCallExp.getCgArguments().add(cgArgument);
		}
		as2cgVisitor.initAst(cgOperationCallExp, element);
	//	as2cgVisitor.declareLazyName(cgOperationCallExp);
		cgOperationCallExp.setReferredOperation(finalOperation);
		if (as2cgVisitor.getCodeGenerator().addConstrainedOperation(finalOperation)) {
		}
		return cgOperationCallExp;
	}

	@Override
	public @NonNull CGValuedElement createCGOperationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		OCLExpression asSource = asOperationCallExp.getOwnedSource();
	//	assert asSource != null;
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
		Operation finalOperation = null;	// FIXME cast
		if (asSource != null) {
			Type sourceType = asSource.getType();
			finalOperation = as2cgVisitor.getCodeGenerator().isFinal(asOperation, (org.eclipse.ocl.pivot.Class)sourceType);	// FIXME cast
		}
		CGClass currentClass = as2cgVisitor.basicGetCurrentClass();
		if (finalOperation != null) {
			LanguageExpression bodyExpression = asOperation.getBodyExpression();
			if (bodyExpression != null) {
				CGValuedElement cgOperationCallExp2 = as2cgVisitor.inlineOperationCall(asOperationCallExp, bodyExpression);
				if (cgOperationCallExp2 != null) {
					return cgOperationCallExp2;
				} else if (currentClass != null) {
					CGOperationCallExp cgCallExp = as2cgVisitor.cachedOperationCall(asOperationCallExp, currentClass, cgSource, finalOperation, null);
					cgCallExp.setCgOperation(cgOperation);
					return cgCallExp;
				} else {
					CGOperationCallExp cgCallExp = constrainedOperationCall(as2cgVisitor, asOperationCallExp, cgSource, finalOperation, (ConstrainedOperation)libraryOperation);
					cgCallExp.setCgOperation(cgOperation);
					return cgCallExp;
				}
			}
		}
		if (currentClass != null) {
			Iterable<@NonNull Operation> overrides = as2cgVisitor.getMetamodelManager().getFinalAnalysis().getOverrides(asOperation);
			return as2cgVisitor.cachedOperationCall(asOperationCallExp, currentClass, cgSource, asOperation, overrides);
		} else {
			Operation baseOperation = asOperation;	// FIXME
			CGOperationCallExp cgCallExp = constrainedOperationCall(as2cgVisitor, asOperationCallExp, cgSource, baseOperation, (ConstrainedOperation)libraryOperation);
			cgCallExp.setCgOperation(cgOperation);
			return cgCallExp;
		}
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		// XXX should have been ForeignOperationCallingConvention
		if (!generateLocals(cg2javaVisitor, js, cgOperationCallExp)) {
			return false;
		}
		js.appendDeclaration(cgOperationCallExp);
		js.append(" = ");
		appendForeignOperationName(cg2javaVisitor, js, cgOperationCallExp);
		js.append("(");
		generateArgumentList(cg2javaVisitor, js, cgOperationCallExp);
		js.append(");\n");
		return true;
	}
}
