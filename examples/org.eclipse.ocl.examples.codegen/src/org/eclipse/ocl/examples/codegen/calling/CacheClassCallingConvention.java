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
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 *  CacheClassCallingConvention defines the style of a nested Class whose instance caches a feature computation.
 */
public class CacheClassCallingConvention extends AbstractClassCallingConvention
{
	public static final @NonNull CacheClassCallingConvention INSTANCE = new CacheClassCallingConvention();

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGClass cgClass) {
		assert cgClass.getContainingPackage() == null;			// container is a cgClass
		String className = CGUtil.getName(cgClass);
		String title = cgClass.getName() + " provides the Java implementation to cache evaluations of\n";
		js.appendCommentWithOCL(title, cgClass.getAst());
		js.append("protected static class " + className);
		appendSuperTypes(js, cgClass);
		js.pushClassBody(className);
		generateProperties(cg2javaVisitor, js, cgClass);
		generateOperations(cg2javaVisitor, js, cgClass);
		js.popClassBody(false);
		return true;
	}

	@Override
	public @NonNull String getName(@NonNull CodeGenAnalyzer analyzer, @NonNull NamedElement asNamedElement) {
		Feature asFeature = (Feature)asNamedElement;
		return "CACHE_" + ClassUtil.nonNullState(asFeature.getOwningClass()).getName() + "_" + asFeature.getName();
	}
}
