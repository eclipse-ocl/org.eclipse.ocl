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
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;

/**
 *  ExternalClassCallingConvention defines the style of a nested Class that augments an external Java class.
 */
public class ExternalClassCallingConvention extends AbstractClassCallingConvention
{
	public static final @NonNull ExternalClassCallingConvention INSTANCE = new ExternalClassCallingConvention();

	/**
	 * Generate the Java code for a Class declaration.
	 * Returns true if control flow continues, false if an exception throw has been synthesized.
	 */
	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGClass cgClass) {
		String className = CGUtil.getName(cgClass);
		CGPackage cgContainingPackage = cgClass.getContainingPackage();
		assert cgContainingPackage == null;
		String title = cgClass.getName() + " provides the Java implementation for the additional non-Ecore features of\n";
		js.appendCommentWithOCL(title, cgClass.getAst());
		js.append("public static class " + className);
		js.pushClassBody(className);
		boolean first = true;
		for (CGProperty cgProperty : cgClass.getProperties()) {
			if (!first) {
				js.append("\n");
			}
			cgProperty.accept(cg2javaVisitor);
			first = false;
		}
		for (CGOperation cgOperation : cgClass.getOperations()) {
			if (!first) {
				js.append("\n");
			}
			cgOperation.accept(cg2javaVisitor);
			first = false;
		}
		js.popClassBody(false);
		return true;
	}

	@Override
	public @NonNull String getName(@NonNull AS2CGVisitor as2cgVisitor, org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return as2cgVisitor.getCodeGenerator().getExternalClassName(asClass);
	}
}
