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
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;

/**
 *  ClassCallingConvention defines a particular style of Class declaration.
 */
public interface ClassCallingConvention extends CallingConvention
{
	/**
	 * Create the appropriate CGClass less properties and operation.
	 */
	@NonNull CGClass createCGClass(@NonNull AS2CGVisitor as2cgVisitor, org.eclipse.ocl.pivot.@NonNull Class asClass);

	/**
	 * Generate the Java code for a Class declaration.
	 * Returns true if control flow continues, false if an exception throw has been synthesized.
	 */
	boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGClass cgClass);
}
