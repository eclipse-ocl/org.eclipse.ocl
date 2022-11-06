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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
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
public class CacheClassCallingConvention extends AbstractClassCallingConvention
{
	public static class CachedFeatureAdapter extends AdapterImpl
	{
		public static @NonNull Feature getFeature(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			for (Adapter eAdapter : asClass.eAdapters()) {
				if (eAdapter instanceof CachedFeatureAdapter) {
					return ((CachedFeatureAdapter)eAdapter).feature;
				}
			}
			throw new IllegalStateException("Missing CachedFeatureAdapter for " + asClass);
		}

		private @NonNull Feature feature;

		public CachedFeatureAdapter(@NonNull Feature feature) {
			this.feature = feature;
		}

		@Override
		public boolean isAdapterForType(Object type) {
			return type == CachedFeatureAdapter.class;
		}
	}

	public static final @NonNull CacheClassCallingConvention INSTANCE = new CacheClassCallingConvention();

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGClass cgClass) {
		assert cgClass.getContainingPackage() == null;			// container is a cgClass
		String className = CGUtil.getName(cgClass);
		String title = cgClass.getName() + " provides the Java implementation to cache evaluations of\n";
		org.eclipse.ocl.pivot.Class asClass = CGUtil.getAST(cgClass);
		Feature asFeature = CachedFeatureAdapter.getFeature(asClass);
		js.appendCommentWithOCL(title, asFeature);
		js.append("protected class " + className);
		appendSuperTypes(js, cgClass);
		js.pushClassBody(className);
		generateProperties(cg2javaVisitor, js, cgClass);
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
