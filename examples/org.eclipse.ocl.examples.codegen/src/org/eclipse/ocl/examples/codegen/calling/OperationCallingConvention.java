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
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.library.LibraryOperation;

/**
 *  OperationCallingConvention defines a particular style of Operation call with support for
 *  generation of a declaration or invocation.
 */
public interface OperationCallingConvention extends CallingConvention
{
	/**
	 * Elaborate the CGOperation declaration with its body.
	 */
	void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation);

	/**
	 * Create the appropriate CGOperationCallExp for asOperationCallExp with cgSource.
	 */
	@NonNull CGValuedElement createCGOperationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp);

	@NonNull CGOperation createOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation, ExpressionInOCL asExpressionInOCL);

	/**
	 * Generate the body text of an Ecore Operation to the vistor's JavaStream.
	 *
	 * Return null if no body required or available.
	 */
	boolean generateEcoreBody(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation);

	/**
	 * Generate the Java code for an Operation call.
	 * Returns true if control flow continues, false if an exception throw has been synthesized.
	 */
	boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperationCallExp cgOperationCallExp);

	/**
	 * Generate the Java code for an Operation declaration.
	 * Returns true if control flow continues, false if an exception throw has been synthesized.
	 */
	boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation);

	void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperation cgOperation);
	void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperationCallExp cgOperationCallExp);
}
