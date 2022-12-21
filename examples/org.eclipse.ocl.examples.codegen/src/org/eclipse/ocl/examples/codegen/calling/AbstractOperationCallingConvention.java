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
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.types.JavaTypeId;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.naming.NameResolution;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrinter;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.LanguageSupport;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  AbstractOperationCallingConvention defines the default support for an operation declaration or call.
 */
public abstract class AbstractOperationCallingConvention extends AbstractCallingConvention implements OperationCallingConvention
{
	protected void addExpressionInOCLParameters(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation, @NonNull ExpressionInOCL expressionInOCL) {
		ExecutableNameManager operationNameManager = analyzer.getGlobalNameManager().useOperationNameManager(cgOperation);
		List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgOperation);
		Variable contextVariable = expressionInOCL.getOwnedContext();
	//	assert isStatic(cgOperation) == (contextVariable == null);
		if (contextVariable != null) {
			cgParameters.add(analyzer.getSelfParameter(operationNameManager, contextVariable));
		}
		boolean hasExternalNames = cgOperation instanceof CGEcoreOperation;		// Ecore has genmodel-defined names
		for (@NonNull Variable parameterVariable : ClassUtil.nullFree(expressionInOCL.getOwnedParameters())) {
			String name = hasExternalNames ? parameterVariable.getName() : null;
			CGParameter cgParameter = operationNameManager.getCGParameter(parameterVariable, name);
			cgParameters.add(cgParameter);
		}
	}
//	protected void addSelfArgument(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperationCallExp cgOperationCallExp, Operation asOperation) {
//		CodeGenAnalyzer analyzer = as2cgVisitor.getAnalyzer();
//		CGVariable selfVariable = as2cgVisitor.getSelfParameter(asOperation.getBodyExpression());
//		cgOperationCallExp.getCgArguments().add(analyzer.createCGVariableExp(selfVariable));
//	}


	protected void addTypeIdArgument(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperationCallExp cgOperationCallExp, @NonNull TypeId asTypeId) {
		List<CGValuedElement> cgArguments = cgOperationCallExp.getArguments();
		CGTypeId cgTypeId = analyzer.getCGTypeId(asTypeId);
		cgArguments.add(analyzer.createCGConstantExp(cgTypeId));
	}

	protected void appendBody(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGValuedElement body) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		js.append(" {\n");
		js.pushIndentation(null);
		cg2javaVisitor.appendReturn(body);
		js.popIndentation();
		js.append("}\n");
	}

	protected void appendCommentWithOCL(@NonNull  JavaStream js, @NonNull CGOperation cgOperation) {
		Operation asOperation = CGUtil.getAST(cgOperation);
		LanguageExpression expressionInOCL = asOperation.getBodyExpression();
		String title = PrettyPrinter.printName(asOperation);
		js.appendCommentWithOCL(title + "\n", expressionInOCL);
	}

/*	protected void appendDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
		js.append("generateJavaDeclaration " + this);
	//	return true;
	//	assert false : "Missing overload for " + cgOperation.getCallingConvention().getClass().getSimpleName();
		boolean isExternal = false;
		Element ast = cgOperation.getAst();
		assert (ast instanceof Operation); {
			Operation asOperation = (Operation)ast;
			isExternal = cg2javaVisitor.getAnalyzer().isExternal(asOperation);//cgOperation instanceof CGFo;
			LanguageExpression expressionInOCL = asOperation.getBodyExpression();
			if (ast instanceof Operation) {
				String title = PrettyPrinter.printName(asOperation);
				js.appendCommentWithOCL(title+"\n", expressionInOCL);
			}
		}
		//
		//
		if (!isExternal) {
			js.append("@Override\n");
		}
		js.append("public ");
		if (isExternal) {
			js.append("static ");
		}
		boolean cgOperationIsInvalid = cgOperation.getInvalidValue() != null;
		js.appendIsCaught(!cgOperationIsInvalid, cgOperationIsInvalid);
		js.append(" ");
		js.appendClassReference(cgOperation.isRequired() ? true : null, cgOperation);
		js.append(" ");
		if (isExternal) {
			js.append("op_");
		}
		js.appendValueName(cgOperation);
	} */

/*	Original merged function  temporaily retained in case derivations need review.
  	protected @NonNull CGOperation createCGOperationWithoutBody(@NonNull AS2CGVisitor as2cgVisitor, @NonNull Operation asOperation) {
		PivotMetamodelManager metamodelManager = as2cgVisitor.getMetamodelManager();
		GenModelHelper genModelHelper = as2cgVisitor.getGenModelHelper();
		CGOperation cgOperation = null;
		LibraryFeature libraryOperation = metamodelManager.getImplementation(asOperation);
		if ((libraryOperation instanceof NativeStaticOperation) || (libraryOperation instanceof NativeVisitorOperation)) {
			CGNativeOperation cgNativeOperation = CGModelFactory.eINSTANCE.createCGNativeOperation();
			cgOperation = cgNativeOperation;
		}
		else if (libraryOperation instanceof EObjectOperation) {
			EOperation eOperation = (EOperation) asOperation.getESObject();
			if (eOperation != null) {
				boolean isForeign = PivotUtil.isStatic(eOperation);
				if (!isForeign) {
					try {
						genModelHelper.getGenOperation(eOperation);
					}
					catch (GenModelException e) {
						isForeign = true;
					}
				}
				if (!isForeign) {
					CGEcoreOperation cgEcoreOperation = CGModelFactory.eINSTANCE.createCGEcoreOperation();
					cgEcoreOperation.setEOperation(eOperation);
					cgOperation = cgEcoreOperation;
				}
				else {
					cgOperation = CGModelFactory.eINSTANCE.createCGLibraryOperation();
				}
			}
		}
		else if (libraryOperation instanceof ForeignOperation) {
			EOperation eOperation = (EOperation) asOperation.getESObject();
			if (eOperation != null) {
				boolean isForeign = PivotUtil.isStatic(eOperation);
				if (isForeign) {
					cgOperation = CGModelFactory.eINSTANCE.createCGLibraryOperation();
				//	CGParameter cgParameter = context.get    getSelfParameter(contextVariable);
				//	cgOperation.getParameters().add(cgParameter);
				}
				else {
					try {
						genModelHelper.getGenOperation(eOperation);
						CGEcoreOperation cgEcoreOperation = CGModelFactory.eINSTANCE.createCGEcoreOperation();
						cgEcoreOperation.setEOperation(eOperation);
						cgOperation = cgEcoreOperation;
					}
					catch (GenModelException e) {
						cgOperation = CGModelFactory.eINSTANCE.createCGLibraryOperation();
					}
				}
			}
		}
		else if (libraryOperation instanceof ConstrainedOperation) {
			org.eclipse.ocl.pivot.Package pPackage = asOperation.getOwningClass().getOwningPackage();
			cgOperation = pPackage instanceof Library ? CGModelFactory.eINSTANCE.createCGLibraryOperation()
				: CGModelFactory.eINSTANCE.createCGCachedOperation();
		}
		if (cgOperation == null) {
			cgOperation = CGModelFactory.eINSTANCE.createCGLibraryOperation();
		}
		return cgOperation;
	} */

	protected void appendParameterList(@NonNull JavaStream js, @NonNull CGOperation cgOperation) {
		js.append("(");
		boolean isFirst = true;
		for (@NonNull CGParameter cgParameter : CGUtil.getParameters(cgOperation)) {
			if (!isFirst) {
				js.append(", ");
			}
			js.appendDeclaration(cgParameter);
			isFirst = false;
		}
		js.append(")");
	}

	@Override
	public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation) {  // XXX perhaps always override
		Element asOperation = cgOperation.getAst();
		ExpressionInOCL asSpecification = (ExpressionInOCL) (asOperation instanceof ExpressionInOCL ? asOperation : ((Operation)asOperation).getBodyExpression());
		assert (asSpecification != null);
		OCLExpression asExpression = PivotUtil.getOwnedBody(asSpecification);
		CGValuedElement cgBody = analyzer.createCGElement(CGValuedElement.class, asExpression);
		cgOperation.setBody(cgBody);
	//	System.out.println("setBody " + NameUtil.debugSimpleName(cgOperation) + " : " + cgBody);
	}

	protected @NonNull CGParameter createCGParameter(@NonNull ExecutableNameManager operationNameManager, @NonNull Variable asParameterVariable) {
		return operationNameManager.getCGParameter(asParameterVariable, (String)null);
	}

	@Override
	public /*final*/ void createCGParameters(@NonNull ExecutableNameManager operationNameManager, @Nullable ExpressionInOCL bodyExpression) {
		CodeGenAnalyzer analyzer = operationNameManager.getAnalyzer();
		CGOperation cgOperation = (CGOperation)operationNameManager.getCGScope();
		if (bodyExpression != null) {
			Variable asContextVariable = bodyExpression.getOwnedContext();
			if (asContextVariable != null) {
				CGParameter cgParameter = analyzer.getSelfParameter(operationNameManager, asContextVariable);
				cgOperation.getParameters().add(cgParameter);
			}
			for (@NonNull Variable asParameterVariable : ClassUtil.nullFree(bodyExpression.getOwnedParameters())) {
				CGParameter cgParameter = createCGParameter(operationNameManager, asParameterVariable);
				cgOperation.getParameters().add(cgParameter);
			}
		}
		else {
			Operation asOperation = CGUtil.getAST(cgOperation);
			if (!asOperation.isIsStatic()) {						// XXX Static is a derived CC
				CGParameter cgParameter = operationNameManager.getSelfParameter();
				cgOperation.getParameters().add(cgParameter);
			}
			for (@NonNull Parameter asParameterVariable : ClassUtil.nullFree(asOperation.getOwnedParameters())) {
				CGParameter cgParameter = operationNameManager.getCGParameter(asParameterVariable, (String)null);
				cgOperation.getParameters().add(cgParameter);
			}
		}
	}

	protected @NonNull Parameter createBoxedValuesParameter(@NonNull JavaCodeGenerator codeGenerator, boolean isRequired) {
		NameResolution boxedValuesResolution = codeGenerator.getGlobalNameManager().getBoxedValuesNameResolution();
		String boxedValuesName = boxedValuesResolution.getResolvedName();
		LanguageSupport jLanguageSupport = codeGenerator.getLanguageSupport();
		org.eclipse.ocl.pivot.Class boxedValueType = jLanguageSupport.getNativeClass(Object[].class);
		return PivotUtil.createParameter(boxedValuesName, boxedValueType, isRequired);
	}

	protected @NonNull Parameter createExecutorParameter(@NonNull JavaCodeGenerator codeGenerator) {
		NameResolution executorResolution = codeGenerator.getGlobalNameManager().getExecutorNameResolution();
		String executorName = executorResolution.getResolvedName();
		LanguageSupport jLanguageSupport = codeGenerator.getLanguageSupport();
		org.eclipse.ocl.pivot.Class executorType = jLanguageSupport.getNativeClass(Executor.class);
		return PivotUtil.createParameter(executorName, executorType, true);
	}

	@Override
	public @NonNull CGOperation createOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation, @Nullable ExpressionInOCL asExpressionInOCL) {
		CGOperation cgOperation = createCGOperation(analyzer, asOperation);
		assert cgOperation.getCallingConvention() == null;
		cgOperation.setCallingConvention(this);
		assert cgOperation.getAst() == null;						// Lightweight createCGOperation just creates
		assert analyzer.basicGetCGElement(asOperation) == null;
		analyzer.initAst(cgOperation, asOperation, true);
		assert analyzer.basicGetCGElement(asOperation) != null;
		ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(cgOperation, asOperation);	// Needed to support downstream useOperationNameManager()
		assert cgOperation.eContainer() == null;
		CGClass cgClass = analyzer.getCGClass(PivotUtil.getOwningClass(asOperation));
		cgClass.getOperations().add(cgOperation);
		createCGParameters(operationNameManager, asExpressionInOCL);
		return cgOperation;
	}

	protected void generateArgumentList(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperationCallExp cgOperationCallExp) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		CGOperation cgOperation = CGUtil.getOperation(cgOperationCallExp);
		List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgOperation);
		List<@NonNull CGValuedElement> cgArguments = CGUtil.getArgumentsList(cgOperationCallExp);
		JavaCodeGenerator codeGenerator = cg2javaVisitor.getCodeGenerator();
		int iMax = cgArguments.size();
		assert iMax == cgParameters.size();
		for (int i = 0; i < iMax; i++) {
			if (i > 0) {
				js.append(", ");
			}
			CGValuedElement cgArgument = cgArguments.get(i);
			CGParameter cgParameter = cgParameters.get(i);
			CGTypeId cgTypeId = cgParameter.getTypeId();
			TypeDescriptor parameterTypeDescriptor = codeGenerator.getUnboxedDescriptor(ClassUtil.nonNullState(cgTypeId.getElementId()));
			CGValuedElement argument = cg2javaVisitor.getExpression(cgArgument);
			js.appendReferenceTo(parameterTypeDescriptor, argument);
		}
	}

	@Deprecated /* @deprecated use generateJavaEvaluateCall always */
	protected boolean generateDeprecatedJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperationCallExp cgOperationCallExp) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		CGOperation cgOperation = CGUtil.getOperation(cgOperationCallExp);
		Operation asOperation = CGUtil.getAST(cgOperation);
		//	Operation pOperation = cgFunctionCallExp.getReferredOperation();
		//	CGFunction cgFunction = ClassUtil.nonNullState(cgFunctionCallExp.getFunction());
		boolean useClassToCreateObject = PivotUtil.basicGetShadowExp(asOperation) != null;
		boolean useCache = !asOperation.isIsTransient();
		boolean isIdentifiedInstance = useCache;
		List<CGValuedElement> cgArguments = cgOperationCallExp.getArguments();
		List<Parameter> asParameters = asOperation.getOwnedParameters();
		List<CGParameter> cgParameters = cgOperation.getParameters();
		//
		for (@SuppressWarnings("null")@NonNull CGValuedElement cgArgument : cgArguments) {
			CGValuedElement argument = cg2javaVisitor.getExpression(cgArgument);
			if (!js.appendLocalStatements(argument)) {
				return false;
			}
		}
		//
		js.appendDeclaration(cgOperationCallExp);
		js.append(" = ");
		boolean needComma = false;
		if (isIdentifiedInstance) {
			js.append("((");
			js.appendValueName(cgOperation);
			js.append(")");
			js.append(getFunctionCtorName(cgOperation));
			js.append(".getUniqueComputation(");
			//			if (useCache && !useClassToCreateObject) {
			//				CGClass cgClass = ClassUtil.nonNullState(cgFunction.getContainingClass());
			//				//				js.appendClassReference(cgClass);
			//				//				js.append(".this");
			//				qvticg2javaVisitor.appendThis(cgClass);
			//				needComma = true;
			//			}
		}
		else {
			//	js.append(asFunction.getName());
			js.appendValueName(cgOperation);
			js.append("(");
		}
		//	int iMax = cgParameters.size();
		//	assert iMax == cgArguments.size();
		for (int i = 0; i < cgArguments.size(); i++) {
			if (needComma) {
				js.append(", ");
			}
			//	CGParameter cgParameter = cgParameters.get(i);
			CGValuedElement cgArgument = cgArguments.get(i);
			CGValuedElement argument = cg2javaVisitor.getExpression(cgArgument);
			//	TypeId asTypeId = cgParameter.getTypeId().getASTypeId();
			//	assert asTypeId != null;
			//	TypeDescriptor parameterTypeDescriptor = codeGenerator.getUnboxedDescriptor(asTypeId);
			//	js.appendReferenceTo(parameterTypeDescriptor, argument);
			js.appendValueName(argument);
			needComma = true;
		}
		js.append(")");
		if (isIdentifiedInstance) {
			js.append(")");
			//	String cachedResultName = qvticg2javaVisitor.getVariantResolvedName(cgFunction, codeGenerator.getCACHED_RESULT_NameVariant());
			GlobalNameManager globalNameManager = cg2javaVisitor.getCodeGenerator().getGlobalNameManager();
			String cachedResultName = globalNameManager.getCachedResultNameResolution().getResolvedName();
			js.append(".");
			js.append(cachedResultName);
		}
		js.append(";\n");
		return true;
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperationCallExp cgOperationCallExp) {
		// TODO Auto-generated method stub
		return false;
	}

	protected boolean generateLocals(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperationCallExp cgOperationCallExp) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		Iterable<@NonNull CGValuedElement> cgArguments = CGUtil.getArgumentsList(cgOperationCallExp);
		for (@NonNull CGValuedElement cgArgument : cgArguments) {
			CGValuedElement argument = cg2javaVisitor.getExpression(cgArgument);
			if (!js.appendLocalStatements(argument)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public @NonNull ClassCallingConvention getClassCallingConvention(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return ContextClassCallingConvention.getInstance(asClass);
	}

	private @NonNull String getFunctionCtorName(@NonNull CGOperation cgOperation) {
		return JavaStream.convertToJavaIdentifier("FTOR_" + cgOperation.getName());
	}

	protected void initCallArguments(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperationCallExp cgOperationCallExp) {
		OperationCallExp asOperationCallExp = CGUtil.getAST(cgOperationCallExp);
		Operation asOperation = PivotUtil.getReferredOperation(asOperationCallExp);
		List<@NonNull OCLExpression> asArguments = PivotUtilInternal.getOwnedArgumentsList(asOperationCallExp);
		List<@NonNull OCLExpression> asArgumentsCopy = new ArrayList<>(asArguments);		// XXX inlining can wrap a LetExp
		assert asArguments.size() == asOperation.getOwnedParameters().size();
		List<@NonNull CGValuedElement> cgArguments = CGUtil.getArgumentsList(cgOperationCallExp);
		for (@NonNull OCLExpression asArgument : asArgumentsCopy) {
			cgArguments.add(analyzer.createCGElement(CGValuedElement.class, asArgument));
		}
	}

	protected void initCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperationCallExp cgOperationCallExp, @NonNull OperationCallExp asOperationCallExp,
			@NonNull CGOperation cgOperation, boolean isRequired) {		// XXX wip eliminate isRequired
		Operation asOperation = PivotUtil.getReferredOperation(asOperationCallExp);
	//	boolean isRequired2 = asOperation.isIsRequired();
	//	Boolean ecoreIsRequired = as2cgVisitor.getCodeGenerator().isNonNull(asOperationCallExp);
	//	if (ecoreIsRequired != null) {
	//		isRequired2 = ecoreIsRequired;
	//	}
	//	assert isRequired == isRequired2;
		cgOperationCallExp.setAsOperation(asOperation);
		analyzer.initAst(cgOperationCallExp, asOperationCallExp, true);
		cgOperationCallExp.setReferredOperation(cgOperation);
		cgOperationCallExp.setInvalidating(asOperation.isIsInvalidating());
		cgOperationCallExp.setValidating(asOperation.isIsValidating());
	}

	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperation cgOperation) {
		if (cgOperation.isRequired()) {
			CGValuedElement body = cgOperation.getBody();
			if (body != null) {
				String message = "body for '" + cgOperation.getAst() + "'";
				boxingAnalyzer.rewriteAsGuarded(body, false, message);
			}
		}
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

		Operation asBaseOperation = analyzer.basicGetOriginalOperation(cgOperation);
		if (asBaseOperation != null) {
			getClass();	// XXX
		}
		List<@NonNull CGValuedElement> cgArguments = CGUtil.getArgumentsList(cgOperationCallExp);
		List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgOperation);
		int maxArgument = cgArguments.size();
		int maxParameter = cgParameters.size();
		assert (maxArgument == maxParameter) || (maxParameter > 0);	// Correct or may be varargs
		CGParameter cgParameter = null;
		for (int i = 0; i < maxArgument; i++) {			// Avoid CME from rewrite
			if (i < maxParameter) {
				cgParameter = cgParameters.get(i);
			}
			else {
				assert cgParameter != null;
				ElementId elementId = cgParameter.getTypeId().getElementId();
				boolean isArray = ((JavaTypeId)elementId).getJavaClass().getComponentType() != null;
				assert isArray;
			}
			CGValuedElement cgArgument = cgArguments.get(i);
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
				Parameter asParameter = CGUtil.basicGetParameter(cgParameter);
				if ((asParameter != null) && asParameter.isIsRequired()) {
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

		cgParameter = null;
		for (int i = 0; i < maxArgument; i++) {			// Avoid CME from rewrite
			if (i < maxParameter) {
				cgParameter = cgParameters.get(i);
			}
			assert cgParameter != null;
			CGValuedElement cgArgument = cgArguments.get(i);
			boxingAnalyzer.rewriteAsBoxed(cgArgument);
			if (i == 0) {
				if (!sourceMayBeNull && !cgArgument.isNonNull()) {
//					rewriteAsGuarded(cgSource, false, "value3 for source parameter");
					boxingAnalyzer.rewriteAsGuarded(cgArgument, false, "''" + asClass.getName() + "'' rather than ''OclVoid'' value required");
				}
			}
			else {
			//	Parameter asParameter = CGUtil.basicGetParameter(cgParameter);
			//	if ((asParameter != null) && asParameter.isIsRequired() && !cgArgument.isNonNull()) {
				if (cgParameter.isRequired() && !cgArgument.isNonNull()) {
//					rewriteAsGuarded(cgArgument, false, "value4 for " + asParameter.getName() + " parameter");
					boxingAnalyzer.rewriteAsGuarded(cgArgument, false, "''" + cgParameter.getTypeId() + "'' rather than ''OclVoid'' elementId");
				}
			}
		}
	}
}
