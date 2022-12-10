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
import org.eclipse.ocl.examples.codegen.calling.AbstractCachedOperationCallingConvention2.CacheConstructorCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.AbstractCachedOperationCallingConvention2.EvaluateOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.AbstractCachedOperationCallingConvention2.NewInstanceOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.ImportNameManager;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.naming.ClassNameManager;
import org.eclipse.ocl.examples.codegen.naming.ClassableNameManager;
import org.eclipse.ocl.examples.codegen.naming.NameManagerHelper;
import org.eclipse.ocl.examples.codegen.naming.PackageNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.utilities.AbstractLanguageSupport;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.LanguageSupport;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.qvtd.runtime.internal.evaluation.AbstractComputationConstructor;

/**
 *  CacheClassCallingConvention defines the style of a nested Class whose instance caches a feature computation.
 */
public abstract class AbstractCacheClassCallingConvention extends AbstractClassCallingConvention
{
	@Override
	public @NonNull CGClass createCGClass(@NonNull CodeGenAnalyzer analyzer, org.eclipse.ocl.pivot.@NonNull Class asClass) {
		CGClass cgClass = createCGClass();
		installCGRootClassParent(analyzer, cgClass, asClass);
		return cgClass;
	}

	public @NonNull CGClass createCacheClass(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation, org.eclipse.ocl.pivot.@NonNull Class asEntryClass, @NonNull AbstractCachedOperationCallingConvention2 operationCallingConvention) {
		JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
		ImportNameManager importNameManager = codeGenerator.getImportNameManager();
		LanguageSupport jLanguageSupport = codeGenerator.getLanguageSupport();
		//
		org.eclipse.ocl.pivot.@NonNull Package asPackage = getParentPackage(analyzer, asOperation);
		PackageNameManager packageNameManager = analyzer.getPackageNameManager(null, asPackage);
		String cacheClassName = packageNameManager.getUniqueClassName(getClassNamePrefix(), asOperation);
		org.eclipse.ocl.pivot.Class asCacheClass = AbstractLanguageSupport.getClass(asPackage, cacheClassName);
		org.eclipse.ocl.pivot.Class asCacheSuperClass = jLanguageSupport.getNativeClass(getSuperClass());
		asCacheClass.getSuperClasses().add(asCacheSuperClass);
		importNameManager.reserveLocalName(PivotUtil.getName(asCacheClass));
		analyzer.addCachedOperation(asCacheClass, asOperation);
		//
		CGClass cgCacheClass = analyzer.generateClassDeclaration(asCacheClass, this);
		CGClass cgCacheSuperClass = analyzer.generateClassDeclaration(asCacheSuperClass, getSuperClassCallingConvention(asCacheSuperClass));
		cgCacheClass.getSuperTypes().add(cgCacheSuperClass);
		//
		installConstructorOperation(analyzer, cgCacheClass, asEntryClass, asOperation, operationCallingConvention);
		installEvaluateOperation(analyzer, cgCacheClass, asEntryClass, asOperation);
		installNewInstanceOperation(analyzer, cgCacheClass, asEntryClass, asOperation);
		//
		return cgCacheClass;
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGClass cgClass) {
		assert cgClass.getContainingPackage() == null;			// container is a cgClass
		if (isEmpty(cgClass)) {
			return true;
		}
		js.append("\n");
		String className = CGUtil.getName(cgClass);
		String title = getTitle(cgClass);
		org.eclipse.ocl.pivot.Class asClass = CGUtil.getAST(cgClass);
		Operation asOperation = cg2javaVisitor.getAnalyzer().basicGetCachedOperation(asClass);
		js.appendCommentWithOCL(title, asOperation);
		js.append("protected class " + className);
		appendSuperTypes(js, cgClass);
		js.pushClassBody(className);
		generateProperties(cg2javaVisitor, js, cgClass);
		generateOperations(cg2javaVisitor, js, cgClass);
		js.popClassBody(false);
		return true;
	}

	protected @NonNull String getClassNamePrefix() {
		return NameManagerHelper.CACHE_CLASS_NAME_PREFIX;
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

	protected abstract org.eclipse.ocl.pivot.@NonNull Package getParentPackage(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation);

	protected @NonNull Class<?> getSuperClass() {
		return AbstractComputationConstructor.class;
	}

	public @NonNull ClassCallingConvention getSuperClassCallingConvention(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return ContextClassCallingConvention.getInstance(asClass);
	}

	protected @NonNull String getTitle(@NonNull CGClass cgClass) {
		return "The instance of " + cgClass.getName() + " caches all known evaluations of\n";
	}

	protected void installConstructorOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgCacheClass, org.eclipse.ocl.pivot.@NonNull Class asEntryClass, @NonNull Operation asOperation, @NonNull AbstractCachedOperationCallingConvention2 operationCallingConvention) {
		org.eclipse.ocl.pivot.Class asCacheClass = CGUtil.getAST(cgCacheClass);
		CacheConstructorCallingConvention.getInstance(asCacheClass).createConstructor(analyzer, cgCacheClass, operationCallingConvention);
	}

	protected void installEvaluateOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgCacheClass, org.eclipse.ocl.pivot.@NonNull Class asEntryClass, @NonNull Operation asOperation) {
		org.eclipse.ocl.pivot.Class asCacheClass = CGUtil.getAST(cgCacheClass);
		EvaluateOperationCallingConvention.getInstance(asCacheClass).createOperation(analyzer, cgCacheClass, asOperation, asEntryClass);
	}

	protected void installNewInstanceOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgCacheClass, org.eclipse.ocl.pivot.@NonNull Class asEntryClass, @NonNull Operation asOperation) {
		org.eclipse.ocl.pivot.Class asCacheClass = CGUtil.getAST(cgCacheClass);
		NewInstanceOperationCallingConvention.getInstance(asCacheClass).createOperation(analyzer, cgCacheClass, asEntryClass);
	}
}
