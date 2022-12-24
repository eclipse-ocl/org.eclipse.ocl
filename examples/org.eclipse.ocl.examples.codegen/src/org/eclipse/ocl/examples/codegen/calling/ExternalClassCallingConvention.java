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
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;

/**
 *  ExternalClassCallingConvention defines the style of a nested Class that augments an external Java class.
 */
public class ExternalClassCallingConvention extends AbstractClassCallingConvention
{
	private static final @NonNull ExternalClassCallingConvention INSTANCE = new ExternalClassCallingConvention();

	public static @NonNull ExternalClassCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		INSTANCE.logInstance(asClass);
		return INSTANCE;
	}

	@Override
	public @NonNull CGClass createCGClass(@NonNull CodeGenAnalyzer analyzer, org.eclipse.ocl.pivot.@NonNull Class asClass) {
		CGClass cgClass = createCGClass();
		installCGDefaultClassParent(analyzer, cgClass, asClass);
		return cgClass;
	}

	/**
	 * Generate the Java code for a Class declaration.
	 * Returns true if control flow continues, false if an exception throw has been synthesized.
	 */
	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGClass cgClass) {
		if (isEmpty(cgClass)) {
			return true;
		}
		JavaStream js = cg2javaVisitor.getJavaStream();
		js.appendOptionalBlankLine();
		String className = CGUtil.getName(cgClass);
		CGPackage cgContainingPackage = cgClass.getContainingPackage();
		assert cgContainingPackage == null;
		String title = cgClass.getName() + " provides the Java implementation for the additional non-Ecore features of";
		js.appendCommentWithOCL(title, cgClass.getAst());
		js.append("public static class " + className);
		appendSuperTypes(js, cgClass);
		js.pushClassBody(className);
		generateProperties(cg2javaVisitor, cgClass);
		generateOperations(cg2javaVisitor, cgClass);
		js.popClassBody(false);
		return true;
	}

	@Override
	public @NonNull String getName(@NonNull CodeGenAnalyzer analyzer, org.eclipse.ocl.pivot.@NonNull NamedElement asNamedElement) {
		return analyzer.getCodeGenerator().getExternalClassName((org.eclipse.ocl.pivot.Class)asNamedElement);
	}
}
