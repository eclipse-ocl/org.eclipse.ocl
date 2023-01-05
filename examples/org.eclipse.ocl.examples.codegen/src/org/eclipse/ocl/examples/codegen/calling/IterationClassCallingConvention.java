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

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.ImportNameManager;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.naming.NameResolution;
import org.eclipse.ocl.examples.codegen.naming.PackageNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.utilities.AbstractLanguageSupport;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  IterationClassCallingConvention defines the nested Class that provudes the nested evaluate for a not-inlined iteration.
 */
public class IterationClassCallingConvention extends AbstractClassCallingConvention
{
	private static final @NonNull IterationClassCallingConvention INSTANCE = new IterationClassCallingConvention();

	public static @NonNull IterationClassCallingConvention getInstance(@NonNull Iteration asIteration) {
		INSTANCE.logInstance(asIteration);
		return INSTANCE;
	}

	public static class EvaluateOperationCallingConvention extends AbstractUncachedOperationCallingConvention
	{
		private static final @NonNull EvaluateOperationCallingConvention INSTANCE = new EvaluateOperationCallingConvention();

		public static @NonNull EvaluateOperationCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			INSTANCE.logInstance(asClass);
			return INSTANCE;
		}

		@Override
		public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgCacheOperation) {
			//	Implemented as direct synthesis
		}

		public final @NonNull CGOperation createOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgCacheClass) { //, @NonNull NewStatement asNewStatement) {
			JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
			GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
			EnvironmentFactory environmentFactory = codeGenerator.getEnvironmentFactory();
			org.eclipse.ocl.pivot.@NonNull Class asCacheClass = CGUtil.getAST(cgCacheClass);
			//
			//	Create AS declaration for evaluate
			//
			NameResolution basicEvaluateNameResolution = globalNameManager.getBasicEvaluateNameResolution();
			String basicEvaluateName = basicEvaluateNameResolution.getResolvedName();
			Type asReturnType = environmentFactory.getStandardLibrary().getBooleanType();
			Operation asCacheOperation = PivotUtil.createOperation(basicEvaluateName, asReturnType, null, null);
			asCacheOperation.setIsRequired(true);
			Parameter asBoxedValuesParameter = createBoxedValuesParameter(codeGenerator, false);
			asCacheOperation.getOwnedParameters().add(asBoxedValuesParameter);
			asCacheClass.getOwnedOperations().add(asCacheOperation);
			//
			//	Create AS body for evaluate
			//
			/*	directly synthesized for now */
			//
			//	Create CG declaration for evaluate
			//
			CGOperation cgCacheOperation = createCGOperation(analyzer, asCacheOperation);
			analyzer.initAst(cgCacheOperation, asCacheOperation, true);
			cgCacheOperation.setCallingConvention(this);
			basicEvaluateNameResolution.addCGElement(cgCacheOperation);
			ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(cgCacheOperation, asCacheOperation);
			List<@NonNull CGParameter> cgCacheParameters = CGUtil.getParametersList(cgCacheOperation);
			CGParameter cgIdResolverParameter = operationNameManager.getIdResolverParameter();
			cgCacheParameters.add(cgIdResolverParameter);
			CGParameter cgCacheBoxedValuesParameter = operationNameManager.getCGParameter(asBoxedValuesParameter, (String)null);
			globalNameManager.getBoxedValuesNameResolution().addCGElement(cgCacheBoxedValuesParameter);
			cgCacheParameters.add(cgCacheBoxedValuesParameter);
			//
			cgCacheClass.getOperations().add(cgCacheOperation);
			return cgCacheOperation;
		}

		@Override
		public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
			JavaStream js = cg2javaVisitor.getJavaStream();
			CodeGenAnalyzer analyzer = cg2javaVisitor.getAnalyzer();
			GlobalNameManager globalNameManager = analyzer.getGlobalNameManager();
			//
			CGClass cgCacheClass = CGUtil.getContainingClass(cgOperation);
			org.eclipse.ocl.pivot.Class asCacheClass = CGUtil.getAST(cgCacheClass);
		//	CreationCache creationCache = analyzer.getCreationCache(asCacheClass);
		//	CGExecutorType cgExecutorType = creationCache.getExecutorType();
			//
		//	List<@NonNull CGProperty> cgProperties = new ArrayList<>();
		//	for (@NonNull Property asProperty : creationCache.getProperties()) {
		//		CGProperty cgProperty = analyzer.getCGProperty(asProperty);
		//		cgProperties.add(cgProperty);
		//	}
			js.append("/**\n");
			js.append(" * The outer evaluation provides a type safe interface.\n");
			js.append(" */\n");
			js.append("public ");
			js.appendClassReference(true, CGExecutorType.class);
			js.append(" ");
			js.append(globalNameManager.getEvaluateName());
			js.append("(");
		//	boolean isFirst = true;
		//	for (@NonNull CGProperty cgProperty : cgProperties) {
		//		if (!isFirst) {
		//			js.append(", ");
		//		}
		//		js.appendDeclaration(cgProperty);
		//		isFirst = false;
		//	}
			js.append(") {\n");
			js.pushIndentation(null);
			js.append("return (");
			js.appendClassReference(true, CGExecutorType.class);
			js.append(")");
			js.append(globalNameManager.getEvaluationCacheName());
			js.append(".");
			js.append(globalNameManager.getGetCachedEvaluationResultName());
			js.append("(this, caller, new ");
			js.appendClassReference(false, Object.class);
			js.append("[]{");
		//	isFirst = true;
		//	for (@NonNull CGProperty cgProperty : cgProperties) {
		//		if (!isFirst) {
		//			js.append(", ");
		//		}
		//		js.appendValueName(cgProperty);
		//		isFirst = false;
		//	}
			js.append("});\n");
			js.popIndentation();
			js.append("}\n");
			return true;
		}
	}

	@Override
	public @NonNull CGClass createCGClass(@NonNull CodeGenAnalyzer analyzer, org.eclipse.ocl.pivot.@NonNull Class asClass) {
		CGClass cgClass = createCGClass();
		installCGRootClassParent(analyzer, cgClass, asClass);
		return cgClass;
	}

/*	public @NonNull CreationCache createCreationCache(@NonNull ClassNameManager classNameManager) { //, @NonNull NewStatement asNewStatement) {
		CodeGenAnalyzer analyzer = classNameManager.getAnalyzer();
		JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
		ImportNameManager importNameManager = codeGenerator.getImportNameManager();
		LanguageSupport jLanguageSupport = codeGenerator.getLanguageSupport();
		org.eclipse.ocl.pivot.Class asClass = classNameManager.getASClass();
		org.eclipse.ocl.pivot.@NonNull Package asParentPackage = getParentPackage(analyzer, asClass);
		//
		PackageNameManager packageNameManager = analyzer.getPackageNameManager(null, asParentPackage);
		String entryClassName = packageNameManager.getUniqueClassName(NameManagerHelper.RULE_CACHE_CLASS_NAME_PREFIX, asClass);
		org.eclipse.ocl.pivot.Class asCacheClass = AbstractLanguageSupport.getClass(asParentPackage, entryClassName);
		org.eclipse.ocl.pivot.Class asCacheClassSuperClass = jLanguageSupport.getNativeClass(/*isIncremental ? AbstractEvaluationOperation.Incremental.class :* / AbstractEvaluationOperation.class);
		asCacheClass.getSuperClasses().add(asCacheClassSuperClass);
		importNameManager.reserveLocalName(PivotUtil.getName(asCacheClass));
		//
		CGClass cgCacheClass = analyzer.generateClassDeclaration(asCacheClass, this);
		CGClass cgCacheSuperClass = analyzer.generateClassDeclaration(asCacheClassSuperClass, null);
		cgCacheClass.getSuperTypes().add(cgCacheSuperClass);
		//
		EvaluateOperationCallingConvention.getInstance(asCacheClass).createOperation(analyzer, cgCacheClass); //, asNewStatement);
		return creationCache;
	} */

	public @NonNull CGClass createIterationClass(@NonNull CodeGenAnalyzer analyzer, org.eclipse.ocl.pivot.@NonNull Package asParentPackage) {
		JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
		ImportNameManager importNameManager = codeGenerator.getImportNameManager();
		org.eclipse.ocl.pivot.Class asClass = ClassUtil.nonNullState(NameUtil.getNameable(asParentPackage.getOwningPackage().getOwnedClasses(), asParentPackage.getName()));
		//
		PackageNameManager packageNameManager = analyzer.getPackageNameManager(null, asParentPackage);
		String iterationClassName = packageNameManager.getUniqueClassName("ITERATION_", asClass);
		org.eclipse.ocl.pivot.Class asIterationClass = AbstractLanguageSupport.getClass(asParentPackage, iterationClassName);
		importNameManager.reserveLocalName(PivotUtil.getName(asIterationClass));
		//
		CGClass cgIterationClass = analyzer.generateClassDeclaration(asIterationClass, this);
		return cgIterationClass;
	}

	/**
	 * Generate the Java code for a Class declaration.
	 * Returns true if control flow continues, false if an exception throw has been synthesized.
	 */
	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGClass cgCacheClass) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		CodeGenAnalyzer analyzer = cg2javaVisitor.getAnalyzer();
		org.eclipse.ocl.pivot.Class asCacheClass = CGUtil.getAST(cgCacheClass);
	//	CreationCache creationCache = analyzer.getCreationCache(asCacheClass);
		//	if (isEmpty(cgCacheClass)) {
		//		return true;
		//	}
		js.appendOptionalBlankLine();
		String className = CGUtil.getName(cgCacheClass);
		CGPackage cgContainingPackage = cgCacheClass.getContainingPackage();
		assert cgContainingPackage == null;
		String title = "The instance of " + cgCacheClass.getName() + " caches the result of each distinct creation of\n";
		js.appendCommentWithOCL(title, null); //creationCache.getASEntryClass());
		js.append("private class " + className);
		appendSuperTypes(js, cgCacheClass);
		js.pushClassBody(className);
		generateProperties(cg2javaVisitor, cgCacheClass);
		generateOperations(cg2javaVisitor, cgCacheClass);
		js.popClassBody(false);
		return true;
	}

	protected org.eclipse.ocl.pivot.@NonNull Package getParentPackage(@NonNull CodeGenAnalyzer analyzer, org.eclipse.ocl.pivot.@NonNull Class asClass) {
		CGClass cgClass = analyzer.getCGRootClass(asClass);
		org.eclipse.ocl.pivot.Class asRootClass = CGUtil.getAST(cgClass);
		return AbstractLanguageSupport.getCachePackage(asRootClass);
	}
}