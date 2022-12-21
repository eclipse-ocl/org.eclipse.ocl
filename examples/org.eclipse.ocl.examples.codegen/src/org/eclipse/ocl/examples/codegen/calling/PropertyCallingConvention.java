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
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.library.LibraryProperty;

/**
 *  PropertyCallingConvention defines a particular style of Property call with support for
 *  generation of a declaration or invocation.
 */
public interface PropertyCallingConvention extends CallingConvention
{
	/**
	 * Create the appropriate CGOperationCallExp for asOperationCallExp with cgSource, or return null
	 * if this OperationCallingConvention cannot handle it.
	 * @param cgOperation
	 */
	@NonNull CGValuedElement createCGNavigationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGProperty cgProperty, @NonNull LibraryProperty libraryProperty,
			@Nullable CGValuedElement cgSource, @NonNull NavigationCallExp asPropertyCallExp);

	/**
	 * Elaborate the CGProperty with the parameters appropriate to initExpression.
	 */
//	void createCGParameters(@NonNull ExecutableNameManager propertyNameManager, @Nullable ExpressionInOCL initExpression);

//	@NonNull CGProperty createCGProperty(@NonNull CodeGenAnalyzer analyzer, @NonNull TypedElement asTypedElement);

	void createImplementation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGProperty cgProperty);

	@NonNull CGProperty createProperty(@NonNull CodeGenAnalyzer codeGenAnalyzer, @NonNull Property asProperty, @Nullable ExpressionInOCL query);

	/**
	 * Generate the Java code for a Property assign.
	 * Returns true if control flow continues, false if an exception throw has been synthesized.
	 */
	boolean generateJavaAssign(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGValuedElement slotValue, @NonNull CGProperty cgProperty, @NonNull CGValuedElement initValue);

	/**
	 * Generate the Java code for a Property call.
	 * Returns true if control flow continues, false if an exception throw has been synthesized.
	 */
	boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGNavigationCallExp cgPropertyCallExp);

	/**
	 * Generate the Java code for a Property declaration.
	 * Returns true if control flow continues, false if an exception throw has been synthesized.
	 */
	boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGProperty cgProperty);

	boolean isInlined();

	void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGProperty cgProperty);
	void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGNavigationCallExp cgNavigationCallExp);
}
