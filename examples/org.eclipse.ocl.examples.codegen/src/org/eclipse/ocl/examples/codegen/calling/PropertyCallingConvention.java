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
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;

/**
 *  PropertyCallingConvention defines a particular style of Property call with support for
 *  generation of a declaration or invocation.
 */
public interface PropertyCallingConvention extends CallingConvention
{
	/**
	 * Generate the Java code for a Property call.
	 * Returns true if control flow continues, false if an exception throw has been synthesized.
	 */
	boolean generateJavaCall(@NonNull CG2JavaVisitor<?> cg2JavaVisitor, @NonNull JavaStream js, @NonNull CGNavigationCallExp cgPropertyCallExp);

	/**
	 * Generate the Java code for a Property declaration.
	 * Returns true if control flow continues, false if an exception throw has been synthesized.
	 */
	boolean generateJavaDeclaration(@NonNull CG2JavaVisitor<?> cg2JavaVisitor, @NonNull JavaStream js, @NonNull CGProperty cgProperty);
}
