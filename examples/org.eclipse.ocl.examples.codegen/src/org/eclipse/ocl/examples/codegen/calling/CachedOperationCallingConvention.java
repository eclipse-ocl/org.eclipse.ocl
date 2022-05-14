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
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.internal.library.ConstrainedOperation;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  CachedOperationCallingConvention defines the support for the call of a Complete OCL-defined operation.
 *  If defined as part of an OCL stdlib, he operation is invoked when called. If defined as part of a
 *  Complete OCL document or OCLinEcore enrichment, the operations is invoked via a cache to avoid re-execution.
 *
 *  -- only used by QVTd
 */
public class CachedOperationCallingConvention extends ConstrainedOperationCallingConvention	// CF ConstrainedOperationCallingConvention
{
	public static final @NonNull CachedOperationCallingConvention INSTANCE = new CachedOperationCallingConvention();

	@Override
	public @NonNull CGOperation createCGOperationWithoutBody(@NonNull AS2CGVisitor as2cgVisitor, @NonNull Operation asOperation) {
		assert as2cgVisitor.getMetamodelManager().getImplementation(asOperation) instanceof ConstrainedOperation;
		org.eclipse.ocl.pivot.Package asPackage = PivotUtil.getOwningPackage(PivotUtil.getOwningClass(asOperation));
		assert !(asPackage instanceof Library);
		return CGModelFactory.eINSTANCE.createCGCachedOperation();
	}

	@Override
	public @NonNull CGValuedElement createCGOperationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation,
			@NonNull LibraryOperation libraryOperation, @Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		return super.createCGOperationCallExp(as2cgVisitor, cgOperation, libraryOperation, cgSource, asOperationCallExp);
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		return super.generateJavaCall(cg2javaVisitor, js, cgOperationCallExp);
	}

	@Override
	public void createCGParameters(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @Nullable ExpressionInOCL bodyExpression) {
		super.createCGParameters(as2cgVisitor, cgOperation, bodyExpression);
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
		return super.generateJavaDeclaration(cg2javaVisitor, js, cgOperation);
	}
}
