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

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.calling.AbstractEntryClassCallingConvention.EntryIsEqualOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGForeignOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.generator.GenModelException;
import org.eclipse.ocl.examples.codegen.generator.GenModelHelper;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.JavaStream.SubStream;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreCodeGenerator;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.evaluation.Executor.ExecutorExtension;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.AbstractOperation;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  ForeignOperationCallingConvention defines the support for the call of an operation realized by an
 *  implementation in the *Tables class.
 *   *  </br>
 *  e.g. as XXXTables.FOREIGN_qualified_class.FC_class.INSTANCE.evaluate(executor, arguments)
 */
public class ForeignOperationCallingConvention extends AbstractCachedOperationCallingConvention
{
	private static final @NonNull ForeignOperationCallingConvention INSTANCE = new ForeignOperationCallingConvention();

	public static @NonNull ForeignOperationCallingConvention getInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
		INSTANCE.logInstance(asOperation, maybeVirtual);
		return INSTANCE;
	}

	public static class ForeignConstructorOperationCallingConvention extends AbstractConstructorOperationCallingConvention
	{
		private static final @NonNull ForeignConstructorOperationCallingConvention INSTANCE = new ForeignConstructorOperationCallingConvention();

		public static @NonNull ForeignConstructorOperationCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			INSTANCE.logInstance(asClass);
			return INSTANCE;
		}

/*		@Override		/// super was final // XXX promote this - just defines lazy ownedContext
		protected final @NonNull CGOperation createOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgEntryClass, @NonNull Operation asOperation) {
			//
			// AS Class - yyy2zzz
			// AS Properties -
			// AS Operation - yyy2zzz
			// AS Operation.ownedParameters - x1, x2
			// AS Cache Operation - newInstance
			// AS Cache Operation.parameters - boxedValues
			// AS Cache ExpressionInOCL.ownedContext - this
			// AS Cache ExpressionInOCL.ownedParameters - x1, x2
			// CG Cache Operation - newInstance
			// CG Cache Operation.lets -
			//
			JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
			EnvironmentFactory environmentFactory = codeGenerator.getEnvironmentFactory();
			org.eclipse.ocl.pivot.@NonNull Class asEntryClass = CGUtil.getAST(cgEntryClass);
			//
			NameResolution ctorNameResolution = cgEntryClass.getNameResolution();
			String ctorName = ctorNameResolution.getResolvedName();
			Type asDummyType = environmentFactory.getStandardLibrary().getOclVoidType();
			Operation asEntryConstructor = PivotUtil.createOperation(ctorName, asDummyType, null, null);
			Parameter asBoxedValuesParameter = createBoxedValuesParameter(codeGenerator, PivotUtil.allParametersRequired(asOperation));
			asEntryConstructor.getOwnedParameters().add(asBoxedValuesParameter);
			asEntryClass.getOwnedOperations().add(asEntryConstructor);
			//
			//	Wrap a copy of the original constructor bodies in a let expression per constructor parameter.
			//
			ExpressionInOCL asExpressionInOCL = (ExpressionInOCL)asOperation.getBodyExpression();
			if (asExpressionInOCL != null) {
				ExpressionInOCL asEntryExpressionInOCL = EcoreUtil.copy(asExpressionInOCL);
				Variable asContextVariable = asEntryExpressionInOCL.getOwnedContext();
				if (asContextVariable == null) {			// QVTi FunctionBody has eager ownedContext, OCL ExpressionInOCL is lazy
					PivotHelper asHelper = codeGenerator.getASHelper();
					GlobalNameManager globalNameManager = analyzer.getGlobalNameManager();
					String thisName = globalNameManager.getThisNameResolution().getResolvedName();
					asContextVariable = asHelper.createParameterVariable(thisName, asEntryClass, true);
					asEntryExpressionInOCL.setOwnedContext(asContextVariable);
				}
				else {
					asContextVariable.setType(asEntryClass);		// Change copied context from context class to entry class
				}
				assert asContextVariable.isIsRequired();
				assert asContextVariable.getTypeValue() == null;
				asEntryConstructor.setBodyExpression(asEntryExpressionInOCL);
			}
			else {
				assert asOperation.getImplementationClass() != null;
			}
			//
			CGOperation cgConstructor = createCGOperation(analyzer, asEntryConstructor);
			cgConstructor.setCallingConvention(this);
			analyzer.initAst(cgConstructor, asEntryConstructor, true);
			ctorNameResolution.addCGElement(cgConstructor);
			analyzer.getOperationNameManager(cgConstructor, asEntryConstructor);
			//
			cgEntryClass.getOperations().add(cgConstructor);
			return cgConstructor;
		} */
	}

	public static class ForeignEntryClassCallingConvention extends AbstractEntryClassCallingConvention
	{
		private static final @NonNull ForeignEntryClassCallingConvention INSTANCE = new ForeignEntryClassCallingConvention();

		public static @NonNull ForeignEntryClassCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Operation asOperation, boolean maybeVirtual) {
			INSTANCE.logInstance(asOperation, maybeVirtual);
			return INSTANCE;
		}

//		@Override
//		protected @NonNull NameResolution getContextNameResolution(@NonNull GlobalNameManager globalNameManager) {
//			return globalNameManager.getSelfNameResolution();
//		}

		/**
		 * Return the Package within which the cache class support for asOperation shuld be supported.
		 */
		@Override
		protected org.eclipse.ocl.pivot.@NonNull Package getParentPackage(@NonNull CodeGenAnalyzer analyzer, @NonNull Feature asFeature) {	// XXX Regularly overridden
			return analyzer.getRootClassParentPackage(asFeature);
		}

		@Override
		protected void installConstructorOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgEntryClass, @NonNull Operation asOperation) {
			org.eclipse.ocl.pivot.Class asEntryClass = CGUtil.getAST(cgEntryClass);
			ForeignConstructorOperationCallingConvention callingConvention = ForeignConstructorOperationCallingConvention.getInstance(asEntryClass);
			callingConvention.createOperation(analyzer, cgEntryClass, asOperation);
		}

		@Override
		protected void installIsEqualOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgEntryClass, @NonNull Operation asOperation) {
			org.eclipse.ocl.pivot.Class asEntryClass = CGUtil.getAST(cgEntryClass);
			ForeignIsEqualOperationCallingConvention callingConvention = ForeignIsEqualOperationCallingConvention.getInstance(asEntryClass);
			callingConvention.createOperation(analyzer, cgEntryClass, asOperation);
		}
	}

	public static class ForeignEvaluateOperationCallingConvention extends AbstractEvaluateOperationCallingConvention
	{
		private static final @NonNull ForeignEvaluateOperationCallingConvention INSTANCE = new ForeignEvaluateOperationCallingConvention();

		@Override
		protected void generateUniqueComputationArguments(@NonNull CG2JavaVisitor cg2javaVisitor, boolean isFirst, @NonNull GlobalNameManager globalNameManager, @NonNull CGOperation cgOperation) {
			CGClass cgCacheClass = CGUtil.getContainingClass(cgOperation);
			org.eclipse.ocl.pivot.Class asCacheClass = CGUtil.getAST(cgCacheClass);
			Operation asOperation = cg2javaVisitor.getAnalyzer().getCachedOperation(asCacheClass);
			if (asOperation.isIsStatic()) {
				cg2javaVisitor.getJavaStream().append(globalNameManager.getRootObjectNameResolution().getResolvedName());
				isFirst = false;
			}
			super.generateUniqueComputationArguments(cg2javaVisitor, isFirst, globalNameManager, cgOperation);
		}

	//	@Override
	//	protected void generateUniqueComputationArguments(@NonNull JavaStream js, boolean isFirst, @NonNull GlobalNameManager globalNameManager, @NonNull CGOperation cgOperation) {
	//		js.append(globalNameManager.getRootObjectNameResolution().getResolvedName());
	//		super.generateUniqueComputationArguments(js, false, globalNameManager, cgOperation);
	//	}

		public static @NonNull ForeignEvaluateOperationCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			INSTANCE.logInstance(asClass);
			return INSTANCE;
		}

/*		@Override
		protected void generateJavaOperationBody(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
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
			Operation asOriginalOperation = analyzer.getCachedOperation(asCacheClass);
			NameResolution contextObjectNameResolution = asOriginalOperation.isIsStatic() ? globalNameManager.getRootObjectNameResolution() : globalNameManager.getContextObjectNameResolution();
			js.append(contextObjectNameResolution.getResolvedName());
			//	js.append(globalNameManager.getIdResolverName());
			for (@NonNull CGParameter cgParameter : CGUtil.getParameters(cgOperation)) {
				js.append(", ");
				js.appendValueName(cgParameter);
			}
			js.append(")).");
			js.append(globalNameManager.getCachedResultNameResolution().getResolvedName());
			js.append(";\n");
		} */
	}

	public static class ForeignIsEqualOperationCallingConvention extends EntryIsEqualOperationCallingConvention
	{
		private static final @NonNull ForeignIsEqualOperationCallingConvention INSTANCE = new ForeignIsEqualOperationCallingConvention();

		public static @NonNull ForeignIsEqualOperationCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			INSTANCE.logInstance(asClass);
			return INSTANCE;
		}
	}

/*	@Override
	protected void appendDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
		appendCommentWithOCL(js, cgOperation);
		//
		js.append("public static ");
		boolean cgOperationIsInvalid = cgOperation.getInvalidValue() != null;
		js.appendIsCaught(!cgOperationIsInvalid, cgOperationIsInvalid);
		js.append(" ");
		js.appendClassReference(cgOperation.isRequired() ? true : null, cgOperation);
		js.append(" op_");
		js.appendValueName(cgOperation);
	} */

	protected void appendForeignOperationName(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperationCallExp cgOperationCallExp) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		JavaCodeGenerator codeGenerator = cg2javaVisitor.getCodeGenerator();
		CGOperation cgOperation = CGUtil.getOperation(cgOperationCallExp);
		Operation asReferredOperation = CGUtil.getAsOperation(cgOperationCallExp);
		org.eclipse.ocl.pivot.Class asReferredClass = PivotUtil.getOwningClass(asReferredOperation);
		CGClass cgReferringClass = CGUtil.getContainingClass(cgOperationCallExp);
		assert cgReferringClass != null;
		String flattenedClassName = codeGenerator.getQualifiedForeignClassName(asReferredClass);
		js.append(flattenedClassName);
		js.append(".");
		js.appendValueName(cgOperation);
	}

	@Override
	public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation) {
	}

	@Override
	public @NonNull CGOperation createCGOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
		EOperation eOperation = (EOperation) asOperation.getESObject();
		if ((eOperation != null) && !PivotUtil.isStatic(eOperation)) {
			try {
				GenModelHelper genModelHelper = analyzer.getGenModelHelper();
				genModelHelper.getGenOperation(eOperation);
				CGEcoreOperation cgEcoreOperation = CGModelFactory.eINSTANCE.createCGEcoreOperation();
				cgEcoreOperation.setEOperation(eOperation);
				return cgEcoreOperation;
			}
			catch (GenModelException e) {
				// No genmodel so fallback
			}
		}
		return CGModelFactory.eINSTANCE.createCGLibraryOperation();
	}

	@Override
	public @NonNull CGCallExp createCGOperationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
		boolean isRequired = asOperation.isIsRequired();
		assert cgSource == null;
		assert asOperation.isIsStatic();
		CGForeignOperationCallExp cgForeignOperationCallExp = CGModelFactory.eINSTANCE.createCGForeignOperationCallExp();
		initCallExp(analyzer, cgForeignOperationCallExp, asOperationCallExp, cgOperation, isRequired);
		ExecutableNameManager operationNameManager = analyzer.getGlobalNameManager().useOperationNameManager(cgOperation);
	//	CGVariable executorVariable = analyzer.getExecutorVariable(operationNameManager);
	//	cgForeignOperationCallExp.getArguments().add(analyzer.createCGVariableExp(executorVariable));
		//	addTypeIdArgument(as2cgVisitor, cgForeignOperationCallExp, asOperation.getTypeId());
		addExpressionInOCLParameters(analyzer, cgOperation, (ExpressionInOCL) asOperation.getBodyExpression());
		CGVariable executorVariable = analyzer.getExecutorVariable(operationNameManager);
		cgForeignOperationCallExp.getArguments().add(analyzer.createCGVariableExp(executorVariable));
		initCallArguments(analyzer, cgForeignOperationCallExp);
		return cgForeignOperationCallExp;
	}

	@Override
	public void createCGParameters(@NonNull ExecutableNameManager operationNameManager, @Nullable ExpressionInOCL expressionInOCL) {
		assert expressionInOCL != null;
		CGOperation cgOperation = (CGOperation)operationNameManager.getCGScope();
		List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgOperation);
		cgParameters.add(operationNameManager.getExecutorParameter());
		super.createCGParametersDefault(operationNameManager, expressionInOCL);
	}

	@Override
	public @NonNull CGOperation createOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation, @Nullable ExpressionInOCL asExpressionInOCL) {
		CGOperation cgOperation = createCGOperation(analyzer, asOperation);
		assert cgOperation.getCallingConvention() == null;
		cgOperation.setCallingConvention(this);
		assert cgOperation.getAst() == null;
		assert analyzer.basicGetCGElement(asOperation) == null;
		analyzer.initAst(cgOperation, asOperation, true);
		assert analyzer.basicGetCGElement(asOperation) != null;
		createCachingClassesAndInstance(analyzer, cgOperation);
		assert cgOperation.eContainer() == null;
		ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(cgOperation, asOperation);	// Needed to support downstream useOperationNameManager()
		CGClass cgClass = analyzer.getCGClass(PivotUtil.getOwningClass(asOperation));
		cgClass.getOperations().add(cgOperation);
		createCGParameters(operationNameManager, asExpressionInOCL);
		return cgOperation;
	}

	@Override
	public boolean generateEcoreBody(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
		CGValuedElement cgBody = cgOperation.getBody();
		assert cgBody == null;
		JavaStream js = cg2javaVisitor.getJavaStream();
		OCLinEcoreCodeGenerator codeGenerator = (OCLinEcoreCodeGenerator)cg2javaVisitor.getCodeGenerator();
		CodeGenAnalyzer analyzer = cg2javaVisitor.getAnalyzer();
		Operation asOperation = CGUtil.getAST(cgOperation);
		Property asProperty = analyzer.getCacheInstance(asOperation);
		String qualifiedSupportClassName = codeGenerator.getQualifiedSupportClassName();
		String returnClassName = cg2javaVisitor.getGenModelHelper().getOperationReturnType(asOperation);
		//
		js.appendClassReference(null, ExecutorExtension.class);
		js.append(" executor = (");
		js.appendClassReference(null, ExecutorExtension.class);
		js.append(")");
		js.appendClassReference(null, PivotUtil.class);
		js.append(".getExecutor(null);\n");
		//
		js.appendClassReference(null, qualifiedSupportClassName);
		js.append(" support = executor.getExecutionSupport(");
		js.appendClassReference(null, qualifiedSupportClassName);
		js.append(".class);\n");
		//
		js.appendClassReference(null, Object.class);
		js.append(" value = support.");
		js.append(asProperty.getName());
		js.append(".evaluate(");
		boolean isFirst = true;
		for (@NonNull Parameter asParameter : PivotUtil.getOwnedParameters(asOperation)) {
			if (!isFirst) {
				js.append(", ");
			}
			js.append(asParameter.getName());
			isFirst = false;
		}
		js.append(");\n");
		//
		js.appendClassReference(null, Object.class);
		js.append(" ecoreValue = executor.getIdResolver().ecoreValueOf(");			// XXX Collection
		js.appendClassReference(null, returnClassName);
		js.append(".class, value);\n");
		//
		js.append("return (");
		js.appendClassReference(null, returnClassName);
		js.append(")ecoreValue;\n");
		return true;
	}


/*	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperationCallExp cgOperationCallExp) {
		if (!generateLocals(cg2javaVisitor, cgOperationCallExp)) {
			return false;
		}
		js.appendDeclaration(cgOperationCallExp);
		js.append(" = ");
		js.append("appendForeignOperationName(cg2javaVisitor, cgOperationCallExp)");		// XXX
		js.append("(");
		generateArgumentList(cg2javaVisitor, cgOperationCallExp);
		js.append(");\n");
		return true;
	} */

	@Override
	protected void generateJavaCallArguments(@NonNull CG2JavaVisitor cg2javaVisitor, boolean isFirst, @NonNull Iterable<@NonNull CGValuedElement> cgArguments) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		int i = 0;
		for (@NonNull CGValuedElement cgArgument : cgArguments) {		// Skip first argument
			if (i >= 1) {
				if (i >= 2) {
					js.append(", ");
				}
				CGValuedElement argument = cg2javaVisitor.getExpression(cgArgument);
				js.appendValueName(argument);
			}
			i++;
		}
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		generateJavaClass(cg2javaVisitor, cgOperation);
		js.appendOptionalBlankLine();
		generateJavaFunction(cg2javaVisitor, cgOperation);
		return true;
	}

	/**
	 * Generate a nested class to provide the polymorphic invocation interface.
	 */
	protected void generateJavaClass(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		JavaCodeGenerator codeGenerator = cg2javaVisitor.getCodeGenerator();
		GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
		Iterable<@NonNull CGParameter> cgParameters = CGUtil.getParameters(cgOperation);
		String operationName = JavaConstants.EXTERNAL_OPERATION_PREFIX + cgOperation.getName();
		assert operationName != null;
		js.appendCommentWithOCL(null, cgOperation.getAst());
	//	assert false;		// XXX
		js.append("public static class ");
		js.append(operationName);
		js.append(" extends ");
		js.appendClassReference(null, AbstractOperation.class);
		js.pushClassBody(operationName);
		js.appendOptionalBlankLine();
		js.append("public static final ");
		js.appendIsRequired(true);
		js.append(" ");
		js.append(operationName);
		js.append(" ");
		js.append(globalNameManager.getInstanceName());
		js.append(" = new ");
		js.append(operationName);
		js.append("();\n");
		js.appendOptionalBlankLine();
		//
		js.append("@Override\n");
		js.append("public ");
		boolean cgOperationIsInvalid = cgOperation.getInvalidValue() != null;
		js.appendIsCaught(!cgOperationIsInvalid, cgOperationIsInvalid);
		js.append(" ");
		js.appendClassReference(cgOperation.isRequired() ? true : null, cgOperation);
		js.append(" ");
		js.append(globalNameManager.getEvaluateName());
		js.append("(");
		js.appendClassReference(true, Executor.class);
		js.append(" executor, ");
		js.appendClassReference(true, TypedElement.class);
		js.append(" typedElement, ");
		js.appendClassReference(false, Object.class);
		js.append(" ");
		js.appendIsRequired(true);
		js.append(" [] sourceAndArguments) {\n");
		js.pushIndentation(null);

		int i = 0;
		for (@NonNull CGParameter cgParameter : cgParameters) {
			CGTypeId cgTypeId = cgParameter.getTypeId();
			TypeId asTypeId = cgTypeId.getASTypeId();
			if (asTypeId == JavaConstants.EXECUTOR_TYPE_ID) {
			}
			else if (asTypeId == JavaConstants.TYPE_ID_TYPE_ID) {
			}
			else {
				final int ii = i++;
				js.append("@SuppressWarnings(\"null\")\n");
				js.appendDeclaration(cgParameter);
				js.append(" = ");
				SubStream castBody = new SubStream() {
					@Override
					public void append() {
						js.append("sourceAndArguments[" + ii + "]");
					}
				};
				js.appendClassCast(cgParameter, castBody);
				js.append(";\n");
			}
		}
		js.append("return ");
		js.appendValueName(cgOperation);
		js.append("(");
		boolean isFirst = true;
		for (@NonNull CGParameter cgParameter : cgParameters) {
			if (!isFirst) {
				js.append(", ");
			}
			CGTypeId cgTypeId = cgParameter.getTypeId();
			TypeId asTypeId = cgTypeId.getASTypeId();
			if (asTypeId == JavaConstants.EXECUTOR_TYPE_ID) {
				js.append("executor");
			}
			else if (asTypeId == JavaConstants.TYPE_ID_TYPE_ID) {
				js.append("typedElement.getTypeId()");
			}
			else {
				js.appendValueName(cgParameter);
			}
			isFirst = false;
		}
		js.append(");\n");
		js.popIndentation();
		js.append("}\n");
		js.popClassBody(false);
	}

	/**
	 * Generate a static function to implement the foreign operation.
	 */
	protected void generateJavaFunction(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		boolean cgOperationIsInvalid = cgOperation.getInvalidValue() != null;
		CGValuedElement body = cg2javaVisitor.getExpression(cgOperation.getBody());
		//
		Element ast = cgOperation.getAst();
		if (ast instanceof Operation) {
			LanguageExpression expressionInOCL = ((Operation)ast).getBodyExpression();
		//	if (ast instanceof Operation) {
		//		String title = PrettyPrinter.printName(ast);
				js.appendCommentWithOCL(null/*title+"\n"*/, expressionInOCL);
		//	}
		}
		js.append("public static ");
		js.appendIsCaught(!cgOperationIsInvalid, cgOperationIsInvalid);
		js.append(" ");
		js.appendClassReference(cgOperation.isRequired() ? true : null, cgOperation);
		js.append(" ");
		js.appendValueName(cgOperation);
		js.append("(");
		boolean isFirst = true;
		for (@NonNull CGParameter cgParameter : CGUtil.getParameters(cgOperation)) {
			if (!isFirst) {
				js.append(", ");
			}
			js.appendDeclaration(cgParameter);
			isFirst = false;
		}
		js.append(") {\n");
		js.pushIndentation(null);
		cg2javaVisitor.appendReturn(body);
		js.popIndentation();
		js.append("}\n");
	}

	@Override
	protected @NonNull AbstractCacheClassCallingConvention getCacheClassCallingConvention(@NonNull Operation asOperation) {
		return RootCacheClassCallingConvention.getInstance(asOperation, false);
	}

	@Override
	protected @NonNull AbstractEntryClassCallingConvention getEntryClassCallingConvention(@NonNull Operation asOperation) {
		return ForeignEntryClassCallingConvention.getInstance(asOperation, false);
	}

	@Override
	public void installEvaluateOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgCacheClass, @NonNull Class asEntryClass, @NonNull Operation asOperation) {
		org.eclipse.ocl.pivot.Class asCacheClass = CGUtil.getAST(cgCacheClass);
		ForeignEvaluateOperationCallingConvention callingConvention = ForeignEvaluateOperationCallingConvention.getInstance(asCacheClass);
		callingConvention.createOperation(analyzer, cgCacheClass, asOperation, asEntryClass);
	}

	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperation cgOperation) {
		CGLibraryOperation cgForeignOperation = (CGLibraryOperation)cgOperation;
		super.rewriteWithBoxingAndGuards(boxingAnalyzer, cgForeignOperation);
		boxingAnalyzer.rewriteAsBoxed(cgForeignOperation.getBody());
	}

/*	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperationCallExp cgOperationCallExp) {
		CGForeignOperationCallExp cgForeignOperationCallExp = (CGForeignOperationCallExp)cgOperationCallExp;
		CodeGenAnalyzer analyzer = boxingAnalyzer.getAnalyzer();
		if ("specializeIn".equals(cgOperationCallExp.getReferredOperation().getName())) {
			getClass();		// XXX
		}
		CGOperation cgOperation = CGUtil.getOperation(cgForeignOperationCallExp);
		Operation asOperation = CGUtil.getAST(cgOperation);
		List<@NonNull CGValuedElement> cgArguments = CGUtil.getArgumentsList(cgForeignOperationCallExp);
		List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgOperation);
		int iMax = cgArguments.size();
		assert iMax == cgParameters.size();


		boolean isBoxed = true;
		boolean isEcore = false;
		boolean isUnboxed = false;
		assert isBoxed == cgForeignOperationCallExp.isBoxed();
		assert isEcore == cgForeignOperationCallExp.isEcore();
		assert isUnboxed == cgForeignOperationCallExp.isUnboxed();
		CGValuedElement cgThis = cgForeignOperationCallExp.getCgThis();
		EOperation eOperation = null;
		//
		//	Guard / cast this
		// XXX cgThis / cgSource
		boolean isStatic = cgThis != null; // XXXcallingConvention.isStatic(cgOperation);
		if (isStatic) {
			assert cgThis != null;
			OperationId operationId = asOperation.getOperationId();
			boolean sourceMayBeNull = analyzer.hasOclVoidOperation(operationId);		// FIXME redundant since LibraryOperationCallingConvention.createParaeters invokes hasOclVoidOperation
			if (!sourceMayBeNull && cgThis.isNull()) {
	//			CGInvalid cgInvalid = context.getInvalid("null value1 for source parameter");
				CGInvalid cgInvalid = analyzer.getInvalid("''" + asOperation.getOwningClass().getName() + "'' rather than ''OclVoid'' value required");
				CGConstantExp cgLiteralExp = analyzer.createCGConstantExp(CGUtil.getAST(cgForeignOperationCallExp), cgInvalid);
				CGUtil.replace(cgForeignOperationCallExp, cgLiteralExp);
				return;
			}
			boxingAnalyzer.rewriteAsBoxed(cgThis);
			if (!sourceMayBeNull && !cgThis.isNonNull()) {
	//			rewriteAsGuarded(cgThis, false, "value3 for source parameter");
				boxingAnalyzer.rewriteAsGuarded(cgThis, false, "''" + asOperation.getOwningClass().getName() + "'' rather than ''OclVoid'' value required");
			}
		}
		//
		//	Null check boxed arguments
		//
		if (!asOperation.isIsValidating()) {
			for (int i = 0; i < iMax; i++) {			// Avoid CME from rewrite
				CGValuedElement cgArgument = cgArguments.get(i);
				CGParameter cgParameter = cgParameters.get(i);
				if (cgParameter.isRequired()) {
					if (cgArgument.isNull()) {
	//					CGInvalid cgInvalid = context.getInvalid("null value2 for " + asParameter.getName() + " parameter");
						StringBuilder s= new StringBuilder();
						s.append("''");
						if (cgParameter.isIsSelf()) {
							s.append(asOperation.getOwningClass().getName());
						}
						else {
							s.append(cgParameter.getTypeId());
						}
						s.append("'' rather than ''OclVoid'' value required");
						CGInvalid cgInvalid = analyzer.getInvalid(s.toString());
						CGConstantExp cgLiteralExp = analyzer.createCGConstantExp(CGUtil.getAST(cgForeignOperationCallExp), cgInvalid);
						CGUtil.replace(cgForeignOperationCallExp, cgLiteralExp);
						return;
					}
				}
			}
		}
		//
		//	Guard / cast arguments
		//
		for (int i = 0; i < iMax; i++) {			// Avoid CME from rewrite
			CGValuedElement cgArgument = cgArguments.get(i);
			CGParameter cgParameter = cgParameters.get(i);
			boxingAnalyzer.rewriteAsBoxed(cgArgument);
			if (cgParameter.isRequired() && !cgArgument.isNonNull()) {
				//	rewriteAsGuarded(cgArgument, false, "value4 for " + asParameter.getName() + " parameter");
				boxingAnalyzer.rewriteAsGuarded(cgArgument, false, "''" + cgParameter.getTypeId() + "'' rather than ''OclVoid'' value required");
			}
		}
	} */
}
