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
import org.eclipse.ocl.examples.codegen.calling.AbstractCachedOperationCallingConvention.AbstractEvaluateOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.naming.NameManagerHelper;
import org.eclipse.ocl.examples.codegen.naming.NameResolution;
import org.eclipse.ocl.examples.codegen.naming.PackageNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.Executor;
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
	public static class CacheConstructorOperationCallingConvention extends AbstractConstructorOperationCallingConvention
	{
		private static final @NonNull CacheConstructorOperationCallingConvention INSTANCE = new CacheConstructorOperationCallingConvention();

		public static @NonNull CacheConstructorOperationCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			INSTANCE.logInstance(asClass);
			return INSTANCE;
		}

		@Override
		public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation) {
			//	Implemented as direct synthesis.
			//	Needs an ability to specify a super() invocation and no return type.
		}

		@Override
		public @NonNull CGOperation createOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgCacheClass, @NonNull Operation asOperation) {
			//
			// AS Class - yyy2zzz
			// AS Properties -
			// AS Operation - yyy2zzz
			// AS Operation.ownedParameters -
			// AS Cache Operation - yyy2zzz
			// AS Cache Operation.parameters -
			// AS Cache ExpressionInOCL.ownedContext -
			// AS Cache ExpressionInOCL.ownedParameters -
			// CG Cache Operation - yyy2zzz
			// CG Cache Operation.lets -
			//
			org.eclipse.ocl.pivot.@NonNull Class asCacheClass = CGUtil.getAST(cgCacheClass);
			//
			//	Create AS declaration for newInstance
			//
			String constructorName = PivotUtil.getName(asCacheClass);
			Operation asConstructorOperation = createASOperationDeclaration(analyzer, asCacheClass, asOperation,
				constructorName, ASResultStyle.VOID);
			//
			//	Create AS body for newInstance
			//
			//	not implemented
			//
			//	Create CG declaration for newInstance
			//
			CGOperation cgConstructorOperation = createCGOperationDeclaration(analyzer, cgCacheClass, asConstructorOperation,
				null, CGParameterStyle.BOXED_VALUES);
			return cgConstructorOperation;
		}

		@Override
		public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
			JavaStream js = cg2javaVisitor.getJavaStream();
			GlobalNameManager globalNameManager = cg2javaVisitor.getGlobalNameManager();
			String executorName = globalNameManager.getExecutorNameResolution().getResolvedName();
			String rootObjectName = globalNameManager.getRootObjectNameResolution().getResolvedName();
			js.append("public ");
			js.appendValueName(cgOperation);
			js.append("(");
			js.appendClassReference(true, Executor.class);
			js.append(" ");
			js.append(executorName);
			js.append(", ");
			js.appendClassReference(true, Object.class);
			js.append(" ");
			js.append(rootObjectName);
			js.append(") {\n");
			js.pushIndentation(null);
			js.append("super(");
			js.append(executorName);
			js.append(", ");
			js.append(rootObjectName);
			js.append(");\n");
			js.popIndentation();
			js.append("}\n");
			return true;
		}

		@Override
		protected @NonNull ASParameterStyle @NonNull [] getASParameterStyles(@NonNull TypedElement asOrigin) {
			return AS_PARAMETER_STYLES_BOXED_VALUES_ALL;
		}
	}

	public static class DefaultEvaluateOperationCallingConvention extends AbstractEvaluateOperationCallingConvention
	{
		private static final @NonNull DefaultEvaluateOperationCallingConvention INSTANCE = new DefaultEvaluateOperationCallingConvention();

		public static @NonNull DefaultEvaluateOperationCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			INSTANCE.logInstance(asClass);
			return INSTANCE;
		}

		@Override
		protected void generateUniqueComputationArguments(@NonNull CG2JavaVisitor cg2javaVisitor, boolean isFirst, @NonNull GlobalNameManager globalNameManager, @NonNull CGOperation cgOperation) {
			cg2javaVisitor.getJavaStream().append(globalNameManager.getRootObjectNameResolution().getResolvedName());
			super.generateUniqueComputationArguments(cg2javaVisitor, false, globalNameManager, cgOperation);
		}
	}

	public static class CacheNewInstanceOperationCallingConvention extends AbstractUncachedOperationCallingConvention
	{
		private static final @NonNull CacheNewInstanceOperationCallingConvention INSTANCE = new CacheNewInstanceOperationCallingConvention();

		public static @NonNull CacheNewInstanceOperationCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			INSTANCE.logInstance(asClass);
			return INSTANCE;
		}

		@Override
		public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation) {
			//	Implemented as direct synthesis.
			//	Needs an ability to specify a new T invocation.
		}

		public final @NonNull CGOperation createOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgConstructorClass, org.eclipse.ocl.pivot.@NonNull Class asCacheClass, @NonNull Operation asOperation) {
			//
			// AS Class - yyy2zzz
			// AS Properties -
			// AS Operation - yyy2zzz
			// AS Operation.ownedParameters -
			// AS Cache Operation - newInstance
			// AS Cache Operation.parameters - boxedValues
			// AS Cache ExpressionInOCL.ownedContext - this
			// AS Cache ExpressionInOCL.ownedParameters -
			// CG Cache Operation - newInstance
			// CG Cache Operation.lets -
			//
			JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
			GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
			org.eclipse.ocl.pivot.@NonNull Class asConstructorClass = CGUtil.getAST(cgConstructorClass);
			//
			//	Create AS declaration for newInstance
			//
			NameResolution newInstanceNameResolution = globalNameManager.getNewInstanceResolution();
			Operation asConstructorOperation = createASOperationDeclaration(analyzer, asConstructorClass, asOperation,
				newInstanceNameResolution.getResolvedName(), asCacheClass);
			//
			//	Create AS body for newInstance
			//
			//	not implemented
			//
			//	Create CG declaration for newInstance
			//
			CGOperation cgConstructorOperation = createCGOperationDeclaration(analyzer, cgConstructorClass, asConstructorOperation,
				newInstanceNameResolution, CGParameterStyle.BOXED_VALUES);
			return cgConstructorOperation;
		}

		@Override
		protected void generateJavaOperationBody(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
			JavaStream js = cg2javaVisitor.getJavaStream();
			js.append("return new ");
			js.appendClassReference(null, cgOperation);
			js.append("(");
			js.append(cg2javaVisitor.getGlobalNameManager().getExecutorNameResolution().getResolvedName());
			for (@NonNull CGParameter cgParameter : CGUtil.getParameters(cgOperation)) {
				js.append(", ");
				js.append("(@NonNull Object @NonNull [])");		// XXX conditionalize / parameterize
				js.appendValueName(cgParameter);
			}
			js.append(");\n");
		}

		@Override
		protected @NonNull ASParameterStyle @NonNull [] getASParameterStyles(@NonNull TypedElement asOrigin) {
			return AS_PARAMETER_STYLES_EXECUTOR_BOXED_VALUES_OPTIONAL;
		}
	}

	@Override
	public @NonNull CGClass createCGClass(@NonNull CodeGenAnalyzer analyzer, org.eclipse.ocl.pivot.@NonNull Class asClass) {
		CGClass cgClass = createCGClass();
		installCGRootClassParent(analyzer, cgClass, asClass);
		return cgClass;
	}

	public @NonNull CGClass createCacheClass(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation, org.eclipse.ocl.pivot.@NonNull Class asEntryClass, @NonNull AbstractCachedOperationCallingConvention operationCallingConvention) {
		JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
		LanguageSupport jLanguageSupport = codeGenerator.getLanguageSupport();
		//
		org.eclipse.ocl.pivot.@NonNull Package asPackage = getParentPackage(analyzer, asOperation);
		PackageNameManager packageNameManager = analyzer.getPackageNameManager(null, asPackage);
		String cacheClassName = packageNameManager.getUniqueClassName(getClassNamePrefix(), asOperation);
		org.eclipse.ocl.pivot.Class asCacheClass = AbstractLanguageSupport.getClass(asPackage, cacheClassName);
		org.eclipse.ocl.pivot.Class asCacheSuperClass = jLanguageSupport.getNativeClass(getSuperClass());
		asCacheClass.getSuperClasses().add(asCacheSuperClass);
		analyzer.addCachedOperation(asCacheClass, asOperation);
		//
		CGClass cgCacheClass = analyzer.generateClassDeclaration(asCacheClass, this);
		CGClass cgCacheSuperClass = analyzer.generateClassDeclaration(asCacheSuperClass, getSuperClassCallingConvention(asCacheSuperClass));
		cgCacheClass.getSuperTypes().add(cgCacheSuperClass);
		//
		operationCallingConvention.installConstructorOperation(analyzer, cgCacheClass, asEntryClass, asOperation);
		operationCallingConvention.installEvaluateOperation(analyzer, cgCacheClass, asEntryClass, asOperation);
		operationCallingConvention.installNewInstanceOperation(analyzer, cgCacheClass, asEntryClass, asOperation);
		//
		return cgCacheClass;
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGClass cgClass) {
		assert cgClass.getContainingPackage() == null;			// container is a cgClass
		if (isEmpty(cgClass)) {
			return true;
		}
		JavaStream js = cg2javaVisitor.getJavaStream();
		String className = CGUtil.getName(cgClass);
		String title = getTitle(cgClass);
		org.eclipse.ocl.pivot.Class asClass = CGUtil.getAST(cgClass);
		Operation asOperation = cg2javaVisitor.getAnalyzer().basicGetCachedOperation(asClass);
		js.appendCommentWithOCL(title, asOperation);
		js.append("public class " + className);		// Could be static if dynamic INSTANCE_CACHE accessible statically
		appendSuperTypes(js, cgClass);
		js.pushClassBody(className);
		generateProperties(cg2javaVisitor, cgClass);
		generateOperations(cg2javaVisitor, cgClass);
		js.popClassBody(false);
		return true;
	}

	protected @NonNull String getClassNamePrefix() {
		return NameManagerHelper.CACHE_CLASS_NAME_PREFIX;
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
}
