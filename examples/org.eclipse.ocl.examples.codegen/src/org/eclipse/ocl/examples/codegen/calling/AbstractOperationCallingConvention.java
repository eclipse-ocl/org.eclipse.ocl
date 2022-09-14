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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
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
import org.eclipse.ocl.examples.codegen.naming.FeatureNameManager;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.naming.OperationNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrinter;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  AbstractOperationCallingConvention defines the default support for an operation declaration or call.
 */
public abstract class AbstractOperationCallingConvention implements OperationCallingConvention
{
	protected void addExpressionInOCLParameters(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation, @NonNull ExpressionInOCL expressionInOCL) {
		FeatureNameManager operationNameManager = analyzer.getGlobalNameManager().useOperationNameManager(cgOperation);
		List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgOperation);
		Variable contextVariable = expressionInOCL.getOwnedContext();
	//	assert isStatic(cgOperation) == (contextVariable == null);
		if (contextVariable != null) {
			cgParameters.add(analyzer.getSelfParameter(operationNameManager, contextVariable));
		}
		boolean hasExternalNames = cgOperation instanceof CGEcoreOperation;		// Ecore has genmodel-defined names
		for (@NonNull Variable parameterVariable : ClassUtil.nullFree(expressionInOCL.getOwnedParameters())) {
			String name = hasExternalNames ? parameterVariable.getName() : null;
			CGParameter cgParameter = operationNameManager.getParameter(parameterVariable, name);
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

	protected void appendBody(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGValuedElement body) {
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

/*	protected void appendDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
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
	public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation) {
		Element asOperation = cgOperation.getAst();
		ExpressionInOCL asSpecification = (ExpressionInOCL) (asOperation instanceof ExpressionInOCL ? asOperation : ((Operation)asOperation).getBodyExpression());
		assert (asSpecification != null);
		OCLExpression asExpression = PivotUtil.getOwnedBody(asSpecification);
		CGValuedElement cgBody = analyzer.createCGElement(CGValuedElement.class, asExpression);
		cgOperation.setBody(cgBody);
	//	System.out.println("setBody " + NameUtil.debugSimpleName(cgOperation) + " : " + cgBody);
	}

	protected @NonNull CGParameter createCGParameter(@NonNull OperationNameManager operationNameManager, @NonNull Variable asParameterVariable) {
		return operationNameManager.getParameter(asParameterVariable, (String)null);
	}

	@Override
	public /*final*/ void createCGParameters(@NonNull OperationNameManager operationNameManager, @Nullable ExpressionInOCL bodyExpression) {
		CodeGenAnalyzer analyzer = operationNameManager.getAnalyzer();
		CGOperation cgOperation = operationNameManager.getCGOperation();
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
				CGParameter cgParameter = operationNameManager.getParameter(asParameterVariable, (String)null);
				cgOperation.getParameters().add(cgParameter);
			}
		}
	}

	protected void generateArgumentList(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
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

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		// TODO Auto-generated method stub
		return false;
	}

	protected boolean generateLocals(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
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
	public @NonNull ClassCallingConvention getClassCallingConvention() {
		return ContextClassCallingConvention.INSTANCE;
	}

	protected void initCallArguments(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperationCallExp cgOperationCallExp) {
		OperationCallExp asOperationCallExp = CGUtil.getAST(cgOperationCallExp);
		Operation asOperation = PivotUtil.getReferredOperation(asOperationCallExp);
		assert asOperationCallExp.getOwnedArguments().size() == asOperation.getOwnedParameters().size();
		List<@NonNull CGValuedElement> cgArguments = CGUtil.getArgumentsList(cgOperationCallExp);
		for (@NonNull OCLExpression asArgument : PivotUtil.getOwnedArguments(asOperationCallExp)) {
			CGValuedElement cgArgument = analyzer.createCGElement(CGValuedElement.class, asArgument);
			cgArguments.add(cgArgument);
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
		cgOperationCallExp.setAst(asOperationCallExp);
		TypeId asTypeId = asOperationCallExp.getTypeId();
		cgOperationCallExp.setTypeId(analyzer.getCGTypeId(asTypeId));
		cgOperationCallExp.setReferredOperation(cgOperation);
		cgOperationCallExp.setInvalidating(asOperation.isIsInvalidating());
		cgOperationCallExp.setValidating(asOperation.isIsValidating());
		cgOperationCallExp.setRequired(isRequired);
	}

	protected void initOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation, @NonNull Operation asOperation) {
		TypeId asTypeId = asOperation.getTypeId();
		CGTypeId cgTypeId = analyzer.getCGTypeId(asTypeId);
		cgOperation.setAst(asOperation);
		cgOperation.setTypeId(cgTypeId);
		cgOperation.setRequired(asOperation.isIsRequired());
		cgOperation.setCallingConvention(this);
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

	// Default guards and boxes al terms. Derived implementations for unboxed/ecore/simple-boxed
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

		List<@NonNull CGValuedElement> cgArguments = CGUtil.getArgumentsList(cgOperationCallExp);
		List<@NonNull CGParameter> cgParameters = CGUtil.getParametersList(cgOperation);
		int iMax = cgArguments.size();
		assert iMax == cgParameters.size();
		for (int i = 0; i < iMax; i++) {			// Avoid CME from rewrite
			CGParameter cgParameter = cgParameters.get(i);
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

		for (int i = 0; i < iMax; i++) {			// Avoid CME from rewrite
			CGParameter cgParameter = cgParameters.get(i);
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
					boxingAnalyzer.rewriteAsGuarded(cgArgument, false, "''" + cgParameter.getTypeId() + "'' rather than ''OclVoid'' value required");
				}
			}
		}
	}


	@Override
	public @NonNull String toString() {
		return getClass().getSimpleName();
	}
}
