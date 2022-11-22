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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.naming.ClassNameManager;
import org.eclipse.ocl.examples.codegen.naming.ClassableNameManager;
import org.eclipse.ocl.examples.codegen.naming.PackageNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 *  CacheClassCallingConvention defines the style of a nested Class whose instance caches a feature computation.
 */
public class ConstructorClassCallingConvention extends AbstractClassCallingConvention
{
	public static final @NonNull ConstructorClassCallingConvention INSTANCE = new ConstructorClassCallingConvention();

	@Override
	public @NonNull CGClass createCGClass(@NonNull CodeGenAnalyzer analyzer, org.eclipse.ocl.pivot.@NonNull Class asClass) {
		CGClass cgClass = createCGClass();
	//	installCGCacheClassParent(analyzer, cgClass, asClass);
		CGClass cgRootClass = analyzer.getCGRootClass(asClass);
		cgRootClass.getClasses().add(cgClass);
		return cgClass;
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGClass cgClass) {
		assert cgClass.getContainingPackage() == null;			// container is a cgClass
		String className = CGUtil.getName(cgClass);
		String title = cgClass.getName() + " provides the Java implementation to construct the singleton to cache an evaluation of\n";
		js.appendCommentWithOCL(title, cgClass.getAst());
		js.append("protected class " + className);
		appendSuperTypes(js, cgClass);
		js.pushClassBody(className);
	//	generateProperties(cg2javaVisitor, js, cgClass);
		generateOperations(cg2javaVisitor, js, cgClass);
		js.popClassBody(false);
		return true;
	}

	@Override
	public @NonNull ClassableNameManager getClassableNameManager(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgClass) {
		org.eclipse.ocl.pivot.Class asClass = CGUtil.getAST(cgClass);
		EObject eContainer = asClass.eContainer();
		if (eContainer instanceof org.eclipse.ocl.pivot.Package) {
			PackageNameManager packageNameManager = analyzer.getPackageNameManager(null, (org.eclipse.ocl.pivot.Package)eContainer);
			packageNameManager.getCGPackage().getClasses().add(cgClass);
			return packageNameManager;
		}
		else if (eContainer instanceof org.eclipse.ocl.pivot.Class) {
			ClassNameManager classNameManager = analyzer.getClassNameManager(null, (org.eclipse.ocl.pivot.Class)eContainer);
			classNameManager.getCGClass().getClasses().add(cgClass);
			return classNameManager;
		}
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull String getName(@NonNull CodeGenAnalyzer analyzer, @NonNull NamedElement asNamedElement) {
		if (asNamedElement instanceof Feature) {
			Feature asFeature = (Feature)asNamedElement;
			return /*"CACHE_" +*/ ClassUtil.nonNullState(asFeature.getOwningClass()).getName() + "_" + asFeature.getName();
		}
		else {
			return /*"CACHE_" +*/ asNamedElement.getName();
		}
	}
}
