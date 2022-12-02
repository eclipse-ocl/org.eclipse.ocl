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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.ImportNameManager;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.naming.NameManagerHelper;
import org.eclipse.ocl.examples.codegen.naming.PackageNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.utilities.AbstractLanguageSupport;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.LanguageSupport;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  VirtualOperationCallingConvention defines the constructor for a virtual dispatch table for a Complete OCL-defined operation.
 *  Creation of the operation also creates a parent class fpr the table and ans a sibling evaluate() operation.
 */
public class VirtualOperationCallingConvention extends AbstractCachedOperationCallingConvention2
{
	public static final @NonNull VirtualOperationCallingConvention INSTANCE = new VirtualOperationCallingConvention();

	/**
	 *  DispatchClassCallingConvention defines the nested Class whose instance realizes a virtual dispatch table.
	 */
	public static class DispatchClassCallingConvention extends CacheClassCallingConvention		// XXX ??? less inheritance
	{
		public static final @NonNull DispatchClassCallingConvention INSTANCE = new DispatchClassCallingConvention();

		@Override
		protected @NonNull String getTitle(@NonNull CGClass cgClass) {
			return "The instance of " + cgClass.getName() + " provides the virtual dispatch table for\n";
		}
	}

	/**
	 *  DispatchEvaluateOperationCallingConvention provides the type-safe evaluate() interface for a virtual operation dispatch.
	 */
	public static class DispatchEvaluateOperationCallingConvention extends AbstractEvaluateOperationCallingConvention
	{
		public static final @NonNull DispatchEvaluateOperationCallingConvention INSTANCE = new DispatchEvaluateOperationCallingConvention();

		@Override
		protected @Nullable Parameter createConstructorEvaluateOperationSelfParameter(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
			CodeGenerator codeGenerator = analyzer.getCodeGenerator();
			GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
			String objectName = globalNameManager.getObjectName();
			Parameter asEvaluateParameter = PivotUtil.createParameter(objectName, PivotUtil.getOwningClass(asOperation), true);
			return asEvaluateParameter;
		}

		@Override
		protected void generateJavaOperationBody(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
			CodeGenAnalyzer analyzer = cg2javaVisitor.getAnalyzer();
			GlobalNameManager globalNameManager = analyzer.getGlobalNameManager();
			List<@NonNull CGParameter> cgParameters = ClassUtil.nullFree(cgOperation.getParameters());
			Boolean isRequiredReturn = cgOperation.isRequired() ? true : null;
			js.append("return (");
			js.appendClassReference(isRequiredReturn, cgOperation);
			js.append(")");
			js.append(globalNameManager.getEvaluationCacheName());
			js.append(".");
			js.append(globalNameManager.getGetCachedEvaluationResultName());
		//	js.append("(this");
			js.append("(this, caller, new ");
			js.appendClassReference(false, Object.class);
			js.append("[]{");
			boolean isFirst = true;
			for (@NonNull CGParameter cgParameter : cgParameters) {
				if (!isFirst) {
					js.append(", ");
				}
				js.appendValueName(cgParameter);
				isFirst = false;
			}
			js.append("}");
			js.append(");\n");
		}
	}

	@Override
	public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation) {
		//	Implemented as direct synthesis.
		//	Needs an ability to specify a super() invocation and no return type.
	}

	@Override
	public @NonNull CGValuedElement createCGOperationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
//		throw new UnsupportedOperationException();
		CGCachedOperationCallExp cgOperationCallExp = CGModelFactory.eINSTANCE.createCGCachedOperationCallExp();
	//	cgOperationCallExp.setSource(cgSource);
		if (cgSource != null) {
			cgOperationCallExp.getArguments().add(cgSource);
		}
		cgOperationCallExp.setThisIsSelf(false);
		for (@NonNull OCLExpression pArgument : ClassUtil.nullFree(asOperationCallExp.getOwnedArguments())) {
			CGValuedElement cgArgument = analyzer.createCGElement(CGValuedElement.class, pArgument);
			cgOperationCallExp.getArguments().add(cgArgument);
		}
		cgOperationCallExp.setAst(asOperationCallExp);
		cgOperationCallExp.setTypeId(analyzer.getCGTypeId(asOperationCallExp.getTypeId()));
		cgOperationCallExp.setReferredOperation(cgOperation);
		return cgOperationCallExp;
	}

	@Override
	public @NonNull CGOperation createCGOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
		assert analyzer.basicGetCGOperation(asOperation) == null;		// XXX
		CGCachedOperation cgDispatchOperation = createDispatchOperation(analyzer, asOperation);
	//	cgDispatchOperation.setRequired(asOperation.isIsRequired());
	//	cgDispatchOperation.setCallingConvention(this);
	//	analyzer.initAst(cgDispatchOperation, asOperation, false);				// XXX redundant wrt caller
		Iterable<@NonNull Operation> asOverrideOperations = analyzer.addVirtualCGOperations(asOperation, cgDispatchOperation);
		List<@NonNull CGCachedOperation> cgOverrideOperations = new ArrayList<>();
		for (@NonNull Operation asOverrideOperation : asOverrideOperations) {
			CGOperation cgOverrideOperation = analyzer.generateNonVirtualOperationDeclaration(asOverrideOperation);
			cgOverrideOperations.add((CGCachedOperation)cgOverrideOperation);		// XXX Bad cast
		}
		cgDispatchOperation.getFinalOperations().addAll(cgOverrideOperations);
		Operation asDispatchOperation = CGUtil.getAST(cgDispatchOperation);
		org.eclipse.ocl.pivot.Class asDispatchClass = PivotUtil.getOwningClass(asDispatchOperation);
		ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(cgDispatchOperation, asDispatchOperation);	// Needed to support downstream useOperationNameManager()
		/*Property asConstructorInstance =*/ createConstructorInstance(operationNameManager, asDispatchClass, asDispatchClass);
		return cgDispatchOperation;
	}

	@Override
	public void createCGParameters(@NonNull ExecutableNameManager operationNameManager,@Nullable ExpressionInOCL bodyExpression) {
		// TODO Auto-generated method stub
		//super.createCGParameters(operationNameManager, bodyExpression); in createDispatchConstructor
	}

	public @NonNull CGCachedOperation createDispatchConstructor(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgConstructorClass) {
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
		CGCachedOperation cgConstructorOperation = /*(CGCachedOperation) createCGOperation(analyzer, asConstructorOperation);*/
			CGModelFactory.eINSTANCE.createCGCachedOperation();
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

	protected final @NonNull CGCachedOperation createDispatchOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
//		ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(null, asOperation);
//		CodeGenAnalyzer analyzer = operationNameManager.getAnalyzer();
		JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
		//	ImperativeTransformation asTransformation = codeGenerator.getTransformation();
	//	GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
		ImportNameManager importNameManager = codeGenerator.getImportNameManager();
		LanguageSupport jLanguageSupport = codeGenerator.getLanguageSupport();
	//	CGOperation cgOperation = (CGOperation)operationNameManager.getCGScope();
	//	Operation asOperation = CGUtil.getAST(cgOperation);
		//	assert QVTimperativeUtil.basicGetShadowExp(asOperation) == null;
		//
		org.eclipse.ocl.pivot.@NonNull Package asPackage = getCachePackage(analyzer, asOperation);
		PackageNameManager packageNameManager = analyzer.getPackageNameManager(null, asPackage);
		String dispatchClassName = packageNameManager.getUniqueClassName(NameManagerHelper.DISPATCH_CLASS_NAME_PREFIX, asOperation);
		org.eclipse.ocl.pivot.Class asDispatchClass = AbstractLanguageSupport.getClass(asPackage, dispatchClassName);
		analyzer.addCachedOperation(asDispatchClass, asOperation);
		org.eclipse.ocl.pivot.Class asCacheSuperClass = jLanguageSupport.getNativeClass("org.eclipse.qvtd.runtime.evaluation.AbstractDispatchOperation2");
		asDispatchClass.getSuperClasses().add(asCacheSuperClass);
		importNameManager.reserveLocalName(PivotUtil.getName(asDispatchClass));
		//
		CGClass cgDispatchClass = analyzer.generateClassDeclaration(asDispatchClass, DispatchClassCallingConvention.INSTANCE);
		CGClass cgSuperClass = analyzer.generateClassDeclaration(asCacheSuperClass, getClassCallingConvention());
		cgDispatchClass.getSuperTypes().add(cgSuperClass);
		//
		CGCachedOperation cgDispatchOperation = createDispatchConstructor(analyzer, cgDispatchClass);
	//	GetResultOperationCallingConvention.INSTANCE.createCacheGetResultOperation(analyzer, cgDispatchClass, asOperation);
		getEvaluateOperationCallingConvention().createOperation(analyzer, cgDispatchClass, asOperation, asDispatchClass);
	//	IsEqualOperationCallingConvention.INSTANCE.createCacheIsEqualOperation(analyzer, cgCacheClass, asOperation);
		return cgDispatchOperation;
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		return generateJavaEvaluateCall(cg2javaVisitor, js, cgOperationCallExp);
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
		CodeGenAnalyzer analyzer = cg2javaVisitor.getAnalyzer();
		//	js.appendCommentWithOCL(title, asFeature);
		js.append("private ");
		js.appendValueName(cgOperation);
		js.append("() {\n");
		js.pushIndentation(null);
		for (@NonNull CGCachedOperation cgFinalOperation : ClassUtil.nullFree(((CGCachedOperation)cgOperation).getFinalOperations())) {
			Operation asFinalOperation = CGUtil.getAST(cgFinalOperation);
			Property asCacheConstructorInstance = analyzer.getCacheConstructorInstance(asFinalOperation);
			CGProperty cgCacheConstructorInstance = analyzer.getCGProperty(asCacheConstructorInstance);
			js.append("install(");
			js.appendClassReference(null, cgFinalOperation.getParameters().get(0));
			js.append(".class, ");
			js.append(cgCacheConstructorInstance.getResolvedName());
			js.append(");\n");
		}
		js.popIndentation();
		js.append("}\n");
		return true;
	}

	@Override
	protected org.eclipse.ocl.pivot.@NonNull Package getCachePackage(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
		org.eclipse.ocl.pivot.Class asClass = PivotUtil.getOwningClass(asOperation);
		CGClass cgClass = analyzer.getCGRootClass(asClass);
		org.eclipse.ocl.pivot.Class asRootClass = CGUtil.getAST(cgClass);
		return AbstractLanguageSupport.getCachePackage(asRootClass);
	}

	@Override
	protected @NonNull AbstractEvaluateOperationCallingConvention getEvaluateOperationCallingConvention() {
		return DispatchEvaluateOperationCallingConvention.INSTANCE;
	}

	// Default guards and boxes all terms. Derived implementations for unboxed/ecore/simple-boxed
	@Override		// XXX review for all derived implementations
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperationCallExp cgOperationCallExp) {
		CGOperation cgOperation = CGUtil.getOperation(cgOperationCallExp);
		Operation asOperation = CGUtil.getAST(cgOperation);
	//	Operation referredOperation = cgLibraryOperationCallExp.getReferredOperation();
		org.eclipse.ocl.pivot.Class asClass = asOperation.getOwningClass();
		if ("_unqualified_env_Class".equals(asOperation.getName())) {
			getClass();		// XXX
		}
		OperationId operationId = asOperation.getOperationId();
		CodeGenAnalyzer analyzer = boxingAnalyzer.getAnalyzer();
		GlobalNameManager globalNameManager = analyzer.getGlobalNameManager();
		boolean sourceMayBeNull = analyzer.hasOclVoidOperation(operationId);

		Operation asBaseOperation = analyzer.getOriginalOperation(cgOperation);
		List<@NonNull CGValuedElement> cgSourceAndArguments = CGUtil.getArgumentsList(cgOperationCallExp);
	//	List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgOperation);
		List<@NonNull Parameter> asParameters = PivotUtilInternal.getOwnedParametersList(asBaseOperation);
		int maxSourceAndArgument = cgSourceAndArguments.size();
		int maxParameter = asParameters.size();
		assert maxSourceAndArgument == (maxParameter + 1);
		for (int i = 0; i < maxSourceAndArgument; i++) {			// Avoid CME from rewrite
			CGValuedElement cgArgument = cgSourceAndArguments.get(i);
			if (i == 0) {
				CGValuedElement cgSource = cgArgument;
				if (!sourceMayBeNull) {
					if (cgSource.isNull()) {
//						CGInvalid cgInvalid = context.getInvalid("null value1 for source parameter");
						CGInvalid cgInvalid = analyzer.getCGInvalid("''" + asClass.getName() + "'' rather than ''OclVoid'' value required");
						CGConstantExp cgLiteralExp = analyzer.createCGConstantExp(CGUtil.getAST(cgOperationCallExp), cgInvalid);
						globalNameManager.replace(cgOperationCallExp, cgLiteralExp);
						return;
					}
				}
			}
			else if (!asOperation.isIsValidating()) {
				Parameter asParameter = asParameters.get(i-1);
				if (asParameter.isIsRequired()) {
					if (cgArgument.isNull()) {
	//					CGInvalid cgInvalid = context.getInvalid("null value2 for " + asParameter.getName() + " parameter");
						CGInvalid cgInvalid = analyzer.getCGInvalid("''" + asParameter.getType().getName() + "'' rather than ''OclVoid'' value required");
						CGConstantExp cgLiteralExp = analyzer.createCGConstantExp(CGUtil.getAST(cgOperationCallExp), cgInvalid);
						globalNameManager.replace(cgOperationCallExp, cgLiteralExp);
						return;
					}
				}
			}
		}

		for (int i = 0; i < maxSourceAndArgument; i++) {			// Avoid CME from rewrite
			CGValuedElement cgArgument = cgSourceAndArguments.get(i);
			boxingAnalyzer.rewriteAsBoxed(cgArgument);
			if (i == 0) {
				if (!sourceMayBeNull && !cgArgument.isNonNull()) {
//					rewriteAsGuarded(cgSource, false, "value3 for source parameter");
					boxingAnalyzer.rewriteAsGuarded(cgArgument, false, "''" + asClass.getName() + "'' rather than ''OclVoid'' value required");
				}
			}
			else {
				Parameter asParameter = asParameters.get(i-1);
				if (asParameter.isIsRequired()) {
			//	Parameter asParameter = CGUtil.basicGetParameter(cgParameter);
			//	if ((asParameter != null) && asParameter.isIsRequired() && !cgArgument.isNonNull()) {
			//	if (cgParameter.isRequired() && !cgArgument.isNonNull()) {
//					rewriteAsGuarded(cgArgument, false, "value4 for " + asParameter.getName() + " parameter");
					boxingAnalyzer.rewriteAsGuarded(cgArgument, false, "''" + asParameter.getTypeId() + "'' rather than ''OclVoid'' elementId");
				}
			}
		}
	}
}
