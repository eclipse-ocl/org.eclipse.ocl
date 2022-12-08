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
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.library.LibraryIteration;

/**
 *  IterationCallingConvention defines a particular style of Iteration call with support for
 *  generation of a declaration or invocation.
 */
public interface IterationCallingConvention extends OperationCallingConvention
{
	/**
	 * Create the appropriate CGIterationCallExp for asLoopExp with cgSource.
	 */
	@NonNull CGIterationCallExp createCGIterationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation,
			@NonNull LibraryIteration libraryIteration, @NonNull CGValuedElement cgSafeSource, @NonNull LoopExp asLoopExp);

	@NonNull CGOperation createIteration(@NonNull CodeGenAnalyzer analyzer, @NonNull Iteration asIteration);

	/**
	 * Generate the Java code for an Iteration call.
	 * Returns true if control flow continues, false if an exception throw has been synthesized.
	 */
	boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGIterationCallExp cgIterationCallExp);

	/**
	 * Generate the Java code for an Operation declaration.
	 * Returns true if control flow continues, false if an exception throw has been synthesized.
	 */
//	boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation);

//	void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperation cgOperation);
//	void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperationCallExp cgOperationCallExp);
}
