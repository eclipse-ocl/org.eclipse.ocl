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
import org.eclipse.ocl.examples.codegen.calling.AbstractCachedOperationCallingConvention2.AbstractEvaluateOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.ImportNameManager;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.naming.NameManagerHelper;
import org.eclipse.ocl.examples.codegen.naming.NameResolution;
import org.eclipse.ocl.examples.codegen.naming.PackageNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.utilities.AbstractLanguageSupport;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.LanguageSupport;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.qvtd.runtime.internal.evaluation.AbstractComputationConstructor;

/**
 *  CacheClassCallingConvention defines the style of a nested Class whose instance caches a feature computation.
 */
public abstract class AbstractCacheClassCallingConvention extends AbstractClassCallingConvention
{
	public static class CacheConstructorCallingConvention extends AbstractCachedOperationCallingConvention
	{
		private static final @NonNull CacheConstructorCallingConvention INSTANCE = new CacheConstructorCallingConvention();

//		public static @NonNull OperationCallingConvention getInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
//			INSTANCE.logInstance(asOperation, maybeVirtual);
//		}

		public static @NonNull CacheConstructorCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			INSTANCE.logInstance(asClass);
			return INSTANCE;
		}

		@Override
		public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation) {
			//	Implemented as direct synthesis.
			//	Needs an ability to specify a super() invocation and no return type.
		}

		public @NonNull CGOperation createConstructor(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgConstructorClass, @NonNull AbstractCachedOperationCallingConvention2 operationCallingConvention) {
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
			JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
			EnvironmentFactory environmentFactory = codeGenerator.getEnvironmentFactory();
			GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
			org.eclipse.ocl.pivot.@NonNull Class asConstructorClass = CGUtil.getAST(cgConstructorClass);
			//
			//	Create AS declaration for newInstance
			//
			String constructorName = PivotUtil.getName(asConstructorClass);
			Type asReturnType = environmentFactory.getStandardLibrary().getOclVoidType();
			Operation asConstructorOperation = PivotUtil.createOperation(constructorName, asReturnType, null, null);
			asConstructorOperation.setIsRequired(true);
			Parameter asBoxedValuesParameter = createBoxedValuesParameter(codeGenerator);
			asConstructorOperation.getOwnedParameters().add(asBoxedValuesParameter);
			asConstructorClass.getOwnedOperations().add(asConstructorOperation);
			//
			//	Create AS body for newInstance
			//
			//	not implemented
			//
			//	Create CG declaration for newInstance
			//
			CGOperation cgConstructorOperation = createCGOperation(analyzer, asConstructorOperation);
			analyzer.initAst(cgConstructorOperation, asConstructorOperation, true);
			cgConstructorOperation.setCallingConvention(this);
			//	newInstanceNameResolution.addCGElement(cgConstructorOperation);
			ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(cgConstructorOperation, asConstructorOperation);
			List<@NonNull CGParameter> cgCacheParameters = CGUtil.getParametersList(cgConstructorOperation);
			CGParameter cgConstructorBoxedValuesParameter = operationNameManager.getCGParameter(asBoxedValuesParameter, (String)null);
			globalNameManager.getBoxedValuesNameResolution().addCGElement(cgConstructorBoxedValuesParameter);
			cgCacheParameters.add(cgConstructorBoxedValuesParameter);
			//
			cgConstructorClass.getOperations().add(cgConstructorOperation);
			//
			//	Create CG body
			//
			//	createCGBody(analyzer, cgConstructorOperation);
			return cgConstructorOperation;
		}

		@Override
		public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
			//	js.appendCommentWithOCL(title, asFeature);
			js.append("public ");
			js.appendValueName(cgOperation);
			js.append("() {\n");
			js.pushIndentation(null);
			js.append("super(");
			js.append(CGUtil.getContextCGClass(cgOperation).getName());
			js.append(".this.");
			js.append(cg2javaVisitor.getGlobalNameManager().getIdResolverName());
			js.append(");\n");
			js.popIndentation();
			js.append("}\n");
			return true;
		}
	}

	public static class EvaluateOperationCallingConvention extends AbstractEvaluateOperationCallingConvention
	{
		private static final @NonNull EvaluateOperationCallingConvention INSTANCE = new EvaluateOperationCallingConvention();

		public static @NonNull EvaluateOperationCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			INSTANCE.logInstance(asClass);
			return INSTANCE;
		}

		@Override
		protected void generateJavaOperationBody(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
			CodeGenAnalyzer analyzer = cg2javaVisitor.getAnalyzer();
			GlobalNameManager globalNameManager = analyzer.getGlobalNameManager();
			Operation asOperation = CGUtil.getAST(cgOperation);
			org.eclipse.ocl.pivot.Class asConstructorClass = PivotUtil.getOwningClass(asOperation);
			org.eclipse.ocl.pivot.Class asCacheClass = analyzer.getEntryClass(asConstructorClass);
			CGClass cgCacheClass = analyzer.getCGClass(asCacheClass);
			js.append("return ((");
			js.appendClassReference(cgCacheClass);
			js.append(")getUniqueComputation(");
			//	js.append(QVTiCGUtil.getContainingCGTransformation(cgOperation).getName());
			js.append("transformation");		// XXX
			//	js.append(globalNameManager.getIdResolverName());
			for (@NonNull CGParameter cgParameter : CGUtil.getParameters(cgOperation)) {
				js.append(", ");
				js.appendValueName(cgParameter);
			}
			js.append(")).");
			js.append(globalNameManager.getCachedResultNameResolution().getResolvedName());
			js.append(";\n");
		}
	}

	public static class NewInstanceOperationCallingConvention extends AbstractCachedOperationCallingConvention
	{
		private static final @NonNull NewInstanceOperationCallingConvention INSTANCE = new NewInstanceOperationCallingConvention();

//		public static @NonNull OperationCallingConvention getInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
//			INSTANCE.logInstance(asOperation, maybeVirtual);
//		}

		public static @NonNull NewInstanceOperationCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			INSTANCE.logInstance(asClass);
			return INSTANCE;
		}

		@Override
		public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation) {
			//	Implemented as direct synthesis.
			//	Needs an ability to specify a new T invocation.
		}

		public final @NonNull CGOperation createOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgConstructorClass, org.eclipse.ocl.pivot.@NonNull Class asCacheClass) {
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
			String newInstanceName = newInstanceNameResolution.getResolvedName();
			Operation asConstructorOperation = PivotUtil.createOperation(newInstanceName, asCacheClass, null, null);
			asConstructorOperation.setIsRequired(true);
			Parameter asBoxedValuesParameter = createBoxedValuesParameter(codeGenerator);
			asConstructorOperation.getOwnedParameters().add(asBoxedValuesParameter);
			asConstructorClass.getOwnedOperations().add(asConstructorOperation);
			//
			//	Create AS body for newInstance
			//
			//	not implemented
			//
			//	Create CG declaration for newInstance
			//
			CGOperation cgConstructorOperation = createCGOperation(analyzer, asConstructorOperation);
			analyzer.initAst(cgConstructorOperation, asConstructorOperation, true);
			cgConstructorOperation.setCallingConvention(this);
			newInstanceNameResolution.addCGElement(cgConstructorOperation);
			ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(cgConstructorOperation, asConstructorOperation);
			List<@NonNull CGParameter> cgCacheParameters = CGUtil.getParametersList(cgConstructorOperation);
			CGParameter cgConstructorBoxedValuesParameter = operationNameManager.getCGParameter(asBoxedValuesParameter, (String)null);
			globalNameManager.getBoxedValuesNameResolution().addCGElement(cgConstructorBoxedValuesParameter);
			cgCacheParameters.add(cgConstructorBoxedValuesParameter);
			//
			cgConstructorClass.getOperations().add(cgConstructorOperation);
			//
			//	Create CG body
			//
			//	createCGBody(analyzer, cgConstructorOperation);
			return cgConstructorOperation;
		}

		@Override
		protected void generateJavaOperationBody(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
			js.append("return new ");
			js.appendClassReference(null, cgOperation);
			js.append("(");
			boolean isFirst = true;
			for (@NonNull CGParameter cgParameter : CGUtil.getParameters(cgOperation)) {
				if (!isFirst) {
					js.append(", ");
				}
				js.appendValueName(cgParameter);
				isFirst = false;
			}
			js.append(");\n");
		}
	}

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
		CacheConstructorCallingConvention callingConvention = CacheConstructorCallingConvention.getInstance(asCacheClass);
		callingConvention.createConstructor(analyzer, cgCacheClass, operationCallingConvention);
	}

	protected void installEvaluateOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgCacheClass, org.eclipse.ocl.pivot.@NonNull Class asEntryClass, @NonNull Operation asOperation) {
		org.eclipse.ocl.pivot.Class asCacheClass = CGUtil.getAST(cgCacheClass);
		EvaluateOperationCallingConvention instance = EvaluateOperationCallingConvention.getInstance(asCacheClass);
		instance.createOperation(analyzer, cgCacheClass, asOperation, asEntryClass);
	}

	protected void installNewInstanceOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgCacheClass, org.eclipse.ocl.pivot.@NonNull Class asEntryClass, @NonNull Operation asOperation) {
		org.eclipse.ocl.pivot.Class asCacheClass = CGUtil.getAST(cgCacheClass);
		NewInstanceOperationCallingConvention callingConvention = NewInstanceOperationCallingConvention.getInstance(asCacheClass);
		callingConvention.createOperation(analyzer, cgCacheClass, asEntryClass);
	}
}
