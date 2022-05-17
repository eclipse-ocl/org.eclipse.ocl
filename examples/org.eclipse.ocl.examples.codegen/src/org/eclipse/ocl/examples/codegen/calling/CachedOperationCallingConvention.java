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
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.JavaGlobalContext;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.internal.library.ConstrainedOperation;
import org.eclipse.ocl.pivot.internal.library.executor.AbstractDispatchOperation;
import org.eclipse.ocl.pivot.internal.library.executor.AbstractEvaluationOperation;
import org.eclipse.ocl.pivot.internal.manager.FinalAnalysis;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrinter;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

import com.google.common.collect.Iterables;

/**
 *  CachedOperationCallingConvention defines the support for the call of a Complete OCL-defined operation.
 *  If defined as part of an OCL stdlib, he operation is invoked when called. If defined as part of a
 *  Complete OCL document or OCLinEcore enrichment, the operations is invoked via a cache to avoid re-execution.
 *
 *  -- only used by QVTd
 */
public class CachedOperationCallingConvention extends ConstrainedOperationCallingConvention	// CF ConstrainedOperationCallingConvention
{
	public static final @NonNull CachedOperationCallingConvention INSTANCE = new CachedOperationCallingConvention();

	// CachedOperationCallingConvention
	public @NonNull CGOperationCallExp cachedOperationCall(@NonNull AS2CGVisitor as2cgVisitor, @NonNull OperationCallExp asOperationCallExp, @NonNull CGClass currentClass, CGValuedElement cgSource,
			@NonNull Operation asOperation, @Nullable Iterable<@NonNull Operation> asOverrideOperations) {
		//	List<@NonNull CGCachedOperation> cgOperations = generateDeclarationHierarchy(as2cgVisitor, currentClass, asOperation, asOverrideOperations);
		List<@NonNull CGCachedOperation> cgOperations = new ArrayList<>();			// XXX
		CGCachedOperationCallExp cgOperationCallExp = CGModelFactory.eINSTANCE.createCGCachedOperationCallExp();
		List<CGValuedElement> cgArguments = cgOperationCallExp.getCgArguments();
		cgArguments.add(cgSource);
		cgOperationCallExp.setThisIsSelf(false);
		for (@NonNull OCLExpression asArgument : ClassUtil.nullFree(asOperationCallExp.getOwnedArguments())) {
			CGValuedElement cgArgument = as2cgVisitor.doVisit(CGValuedElement.class, asArgument);
			cgArguments.add(cgArgument);
		}
		as2cgVisitor.initAst(cgOperationCallExp, asOperationCallExp);
		cgOperationCallExp.setReferredOperation(asOperation);
		if (asOverrideOperations != null) {
			CGOperation cgOperation = as2cgVisitor.getVirtualOperation2cgOperation(asOperation);
			if (cgOperation == null) {
				cgOperation = as2cgVisitor.createVirtualCGOperationWithoutBody(asOperation, cgOperations);
				currentClass.getOperations().add(cgOperation);
			}
		}
		return cgOperationCallExp;
	}

	@Override
	public @NonNull CGOperation createCGOperationWithoutBody(@NonNull AS2CGVisitor as2cgVisitor, @Nullable Type asSourceType, @NonNull Operation asOperation) {
		PivotMetamodelManager metamodelManager = as2cgVisitor.getMetamodelManager();
		assert metamodelManager.getImplementation(asOperation) instanceof ConstrainedOperation;
		org.eclipse.ocl.pivot.Package asPackage = PivotUtil.getOwningPackage(PivotUtil.getOwningClass(asOperation));
		assert !(asPackage instanceof Library);

		generateDeclarationHierarchy(as2cgVisitor, asSourceType, asOperation);
	//	Iterable<@NonNull Operation> asOverrideOperations = null;
	//	CGClass currentClass = as2cgVisitor.getCurrentClass();
	//	generateDeclarationHierarchy(as2cgVisitor, currentClass, asOperation, asOverrideOperations);

	//	if (currentClass != null) {
	//		Iterable<@NonNull Operation> overrides = metamodelManager.getFinalAnalysis().getOverrides(asOperation);
	//		return cachedOperationCall(element, currentClass2, cgSource, asOperation, overrides);
	//	}

		CGOperation cgOperation = as2cgVisitor.getFinalOperation2cgOperation(asOperation);
		assert cgOperation != null;
		return cgOperation;



	//	assert as2cgVisitor.getMetamodelManager().getImplementation(asOperation) instanceof ConstrainedOperation;
	//	org.eclipse.ocl.pivot.Package asPackage = PivotUtil.getOwningPackage(PivotUtil.getOwningClass(asOperation));
	//	assert !(asPackage instanceof Library);
	//	return CGModelFactory.eINSTANCE.createCGCachedOperation();
	}

	@Override
	public @NonNull CGValuedElement createCGOperationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		OCLExpression asSource = asOperationCallExp.getOwnedSource();
	//	assert asSource != null;
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
		Operation finalOperation = null;	// FIXME cast
		if (asSource != null) {
			Type sourceType = asSource.getType();

			Iterable<@NonNull Operation> asOverrideOperations = as2cgVisitor.getMetamodelManager().getFinalAnalysis().getOverrides(asOperation);
			List<@NonNull CGCachedOperation> ops = generateDeclarationHierarchy(as2cgVisitor, sourceType, asOperation);


			finalOperation = as2cgVisitor.getCodeGenerator().isFinal(asOperation, (org.eclipse.ocl.pivot.Class)sourceType);	// FIXME cast
		}
		CGClass currentClass = as2cgVisitor.basicGetCurrentClass();
		assert currentClass != null;
		CGOperationCallExp cgCallExp;
		if (finalOperation != null) {
			LanguageExpression bodyExpression = asOperation.getBodyExpression();
			assert bodyExpression != null;
			CGValuedElement cgOperationCallExp2 = as2cgVisitor.inlineOperationCall(asOperationCallExp, bodyExpression);
			assert cgOperationCallExp2 == null;
			cgCallExp = cachedOperationCall(as2cgVisitor, asOperationCallExp, currentClass, cgSource, finalOperation, null);
		} else {
			Iterable<@NonNull Operation> overrides = as2cgVisitor.getMetamodelManager().getFinalAnalysis().getOverrides(asOperation);
			cgCallExp = cachedOperationCall(as2cgVisitor, asOperationCallExp, currentClass, cgSource, asOperation, overrides);
		}
		cgCallExp.setCgOperation(cgOperation);
		return cgCallExp;
	}

	@Override
	public void createCGParameters(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @Nullable ExpressionInOCL bodyExpression) {
		super.createCGParameters(as2cgVisitor, cgOperation, bodyExpression);
	}

	protected void doCachedOperationDispatchInstaller(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGCachedOperation cgOperation) {
		js.append("private ");
		js.append(cg2javaVisitor.getNativeOperationClassName(cgOperation));
		js.append("() {\n");
		js.pushIndentation(null);
		for (@NonNull CGCachedOperation cgFinalOperation : ClassUtil.nullFree(cgOperation.getFinalOperations())) {
			Operation asFinalOperation = (Operation)cgFinalOperation.getAst();
			assert asFinalOperation != null;
			js.append("install(");
			js.appendClassReference(null, cgFinalOperation.getParameters().get(0));
			js.append(".class, ");
			js.append(cg2javaVisitor.getNativeOperationDirectInstanceName(asFinalOperation));
			js.append(");\n");
		}
		js.popIndentation();
		js.append("}\n");
	}

	protected void doCachedOperationBasicEvaluate(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
		List<@NonNull CGParameter> cgParameters = ClassUtil.nullFree(cgOperation.getParameters());
		CGValuedElement body = cg2javaVisitor.getExpression(cgOperation.getBody());
		js.append("@Override\n");
		js.append("public ");
		//				boolean cgOperationIsInvalid = cgOperation.getInvalidValue() != null;
		//				js.appendIsCaught(!cgOperationIsInvalid, cgOperationIsInvalid);
		js.appendClassReference(false, Object.class);
		js.append(" basicEvaluate(");
		js.appendClassReference(true, Executor.class);
		js.append(" ");
		js.append(cg2javaVisitor.getCodeGenerator().getGlobalContext().getExecutorName());
		js.append(", ");
		js.appendClassReference(true, TypedElement.class);
		js.append(" ");
		js.append("caller");
		js.append(", ");
		js.appendClassReference(false, Object.class);
		js.append(" ");
		js.appendIsRequired(true);
		js.append(" [] ");
		js.append(JavaConstants.SOURCE_AND_ARGUMENT_VALUES_NAME);
		js.append(") {\n");
		js.pushIndentation(null);
		int i = 0;
		for (@NonNull CGParameter cgParameter : cgParameters) {
			if (cgParameter.getASTypeId() instanceof CollectionTypeId) {
				js.append("@SuppressWarnings(\"unchecked\") ");
			}
			else if (cgParameter.isRequired()) {
				if (js.appendSuppressWarningsNull(false)) {
					js.append(" ");
				}
			}
			js.appendDeclaration(cgParameter);
			js.append(" = (");
			js.appendTypeDeclaration(cgParameter);
			js.append(")");
			js.append(JavaConstants.SOURCE_AND_ARGUMENT_VALUES_NAME);
			js.append("[" + i++ + "];\n");
		}
		cg2javaVisitor.appendReturn(body);
		js.popIndentation();
		js.append("}\n");
	}

	protected void doCachedOperationClassDirectInstance(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
		Operation asOperation = (Operation) cgOperation.getAst();
		assert asOperation != null;
		String name = cg2javaVisitor.getNativeOperationClassName(cgOperation);
		js.append("protected final ");
		js.appendIsRequired(true);
		js.append(" ");
		js.append(name);
		js.append(" ");
		js.append(cg2javaVisitor.getNativeOperationDirectInstanceName(asOperation));
		js.append(" = new ");
		js.append(name);
		js.append("();\n");
	}

	protected void doCachedOperationClassInstance(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
		Operation asOperation = (Operation) cgOperation.getAst();
		assert asOperation != null;
		String name = cg2javaVisitor.getNativeOperationClassName(cgOperation);
		js.append("protected final ");
		js.appendIsRequired(true);
		js.append(" ");
		js.append(name);
		js.append(" ");
		js.append(cg2javaVisitor.getNativeOperationInstanceName(asOperation));
		js.append(" = new ");
		js.append(name);
		js.append("();\n");
	}

	protected void doCachedOperationEvaluate(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
		JavaGlobalContext<@NonNull ? extends JavaCodeGenerator> globalContext = cg2javaVisitor.getCodeGenerator().getGlobalContext();
		List<@NonNull CGParameter> cgParameters = ClassUtil.nullFree(cgOperation.getParameters());
		Boolean isRequiredReturn = cgOperation.isRequired() ? true : null;
		if (cgOperation.isEcore() && (cgOperation.getASTypeId() instanceof CollectionTypeId)) {
			js.append("@SuppressWarnings(\"unchecked\")\n");
		}
		else if ((isRequiredReturn == Boolean.TRUE)) {
			js.appendSuppressWarningsNull(true);
		}
		js.append("public ");
		//				boolean cgOperationIsInvalid = cgOperation.getInvalidValue() != null;
		//				js.appendIsCaught(!cgOperationIsInvalid, cgOperationIsInvalid);
		//				js.append(" ");
		js.appendClassReference(isRequiredReturn, cgOperation);
		js.append(" ");
		js.append(globalContext.getEvaluateName());
		js.append("(");
		boolean isFirst = true;
		for (@NonNull CGParameter cgParameter : cgParameters) {
			if (!isFirst) {
				js.append(", ");
			}
			js.appendDeclaration(cgParameter);
			isFirst = false;
		}
		js.append(") {\n");
		js.pushIndentation(null);
		js.append("return (");
		js.appendClassReference(isRequiredReturn, cgOperation);
		js.append(")");
		js.append(globalContext.getEvaluationCacheName());
		js.append(".");
		js.append(globalContext.getGetCachedEvaluationResultName());
		js.append("(this, caller, new ");
		js.appendClassReference(false, Object.class);
		js.append("[]{");
		isFirst = true;
		for (@NonNull CGParameter cgParameter : cgParameters) {
			if (!isFirst) {
				js.append(", ");
			}
			js.appendValueName(cgParameter);
			isFirst = false;
		}
		js.append("});\n");
		js.popIndentation();
		js.append("}\n");
	}

/*	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		// XXX should have been ForeignOperationCallingConvention
		if (!generateLocals(cg2javaVisitor, js, cgOperationCallExp)) {
			return false;
		}


	/ *	Operation pOperation = cgOperationCallExp.getReferredOperation();
		boolean thisIsSelf = cgOperationCallExp.isThisIsSelf();
	//	CGValuedElement source = cg2javaVisitor.getExpression(cgOperationCallExp.getSource());
		List<CGValuedElement> cgArguments = cgOperationCallExp.getCgArguments();
		List<Parameter> pParameters = pOperation.getOwnedParameters();
		//
	//	if (!js.appendLocalStatements(source)) {
	//		return false;
	//	}
		for (@SuppressWarnings("null")@NonNull CGValuedElement cgArgument : cgArguments) {
			CGValuedElement argument = cg2javaVisitor.getExpression(cgArgument);
			if (!js.appendLocalStatements(argument)) {
				return false;
			}
		} * /




		js.appendDeclaration(cgOperationCallExp);
		js.append(" = ");
		appendForeignOperationName(cg2javaVisitor, js, cgOperationCallExp);
		js.append("(");
		generateArgumentList(cg2javaVisitor, js, cgOperationCallExp);
		js.append(");\n");
		return true;
	} */

	protected @NonNull List<@NonNull CGCachedOperation> generateDeclarationHierarchy(@NonNull AS2CGVisitor as2cgVisitor,
			@Nullable Type sourceType, @NonNull Operation asOperation) {
		CodeGenerator codeGenerator = as2cgVisitor.getCodeGenerator();
		CodeGenAnalyzer analyzer = codeGenerator.getAnalyzer();
	//	Type sourceType = ClassUtil.nonNullState(asSource.getType());
	//	Operation finalOperation = codeGenerator.isFinal(asOperation, (org.eclipse.ocl.pivot.Class)sourceType);	// FIXME cast
		PivotMetamodelManager metamodelManager = codeGenerator.getEnvironmentFactory().getMetamodelManager();
		FinalAnalysis finalAnalysis = metamodelManager.getFinalAnalysis();
		Operation finalOperation = (sourceType != null) ? finalAnalysis.isFinal(asOperation, metamodelManager.getCompleteClass(sourceType)) : null;
		Iterable<@NonNull Operation> asOverrideOperations = finalAnalysis.getOverrides(asOperation);
		List<@NonNull Operation> asNewOperations = new ArrayList<>();
		List<@NonNull CGCachedOperation> cgOperations = new ArrayList<>();
	//	if (asOverrideOperations != null) {
			assert Iterables.contains(asOverrideOperations, asOperation);
			for (@NonNull Operation asOverride : asOverrideOperations) {
				CGOperation cgOperation = as2cgVisitor.getFinalOperation2cgOperation(asOverride);
				if (cgOperation == null) {
					OperationCallingConvention callingConvention = codeGenerator.getCallingConvention(asOverride);
					cgOperation = as2cgVisitor.createCGOperationWithoutBody(sourceType, asOverride, callingConvention);
					assert cgOperation != null;
					as2cgVisitor.pushLocalContext(cgOperation, asOverride);
					as2cgVisitor.popLocalContext();
					asNewOperations.add(asOverride);
				}
				cgOperations.add((CGCachedOperation) cgOperation);
				analyzer.addForeignFeature(asOperation);
			}
	//	}
	/*	else {
			CGOperation cgOperation = as2cgVisitor.getFinalOperation2cgOperation(asOperation);
			if (cgOperation == null) {
				cgOperation = CGModelFactory.eINSTANCE.createCGCachedOperation();
				as2cgVisitor.installOperation(asOperation, cgOperation, this);
			//	asNewOperations.add(asOperation);
				cgOperations.add((CGCachedOperation) cgOperation);
				analyzer.addForeignFeature(asOperation);
			}
		} */
		for (@NonNull Operation asNewOperation : asNewOperations) {
			CGOperation cgOperation = as2cgVisitor.visitOperation(asNewOperation);
	//		currentClass.getOperations().add(cgOperation);
		}
		return cgOperations;
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		JavaCodeGenerator codeGenerator = cg2javaVisitor.getCodeGenerator();
		JavaGlobalContext<@NonNull ? extends JavaCodeGenerator> globalContext = codeGenerator.getGlobalContext();
		CGCachedOperationCallExp cgCachedOperationCallExp = (CGCachedOperationCallExp)cgOperationCallExp;
		CGCachedOperation cgOperation = (CGCachedOperation)CGUtil.getOperation(cgCachedOperationCallExp);
		Operation asOperation = CGUtil.getAST(cgOperation);
	//	boolean thisIsSelf = cgCachedOperationCallExp.isThisIsSelf();			// XXX obsolete ??? ---- false
		assert cgOperationCallExp.getCgThis() == null;
	//	CGValuedElement source = getExpression(cgOperationCallExp.getSource());
		List<CGValuedElement> cgArguments = cgOperationCallExp.getCgArguments();
	//	List<Parameter> asParameters = asOperation.getOwnedParameters();
		//
	//	if (!js.appendLocalStatements(source)) {
	//		return false;
	//	}
		for (@SuppressWarnings("null")@NonNull CGValuedElement cgArgument : cgArguments) {
			CGValuedElement argument = cg2javaVisitor.getExpression(cgArgument);
			if (!js.appendLocalStatements(argument)) {
				return false;
			}
		}
		//
		js.appendDeclaration(cgOperationCallExp);
		js.append(" = ");
		//		js.appendClassCast(cgOperationCallExp);
		/*		if (thisIsSelf) {
			js.appendValueName(source);
		}
		else {
			if (localPrefix != null) {
				js.append(localPrefix);
				js.append(".");
			}
			js.append(JavaConstants.THIS_NAME);
		} */
		js.append(cg2javaVisitor.getNativeOperationInstanceName(asOperation));
		js.append(".");
		js.append(globalContext.getEvaluateName());
		//		js.append(cgOperationCallExp.getReferredOperation().getName());
		js.append("(");
	//	assert thisIsSelf;
	//	if (!thisIsSelf) {
		//	js.appendValueName(source);
	//	}
		List<CGParameter> cgParameters = cgOperation.getParameters();
		int iMax = cgArguments.size();
		assert iMax == cgParameters.size();
		for (int i = 0; i < iMax; i++) {
			if (i > 0) {// || !thisIsSelf) {
				js.append(", ");
			}
			CGValuedElement cgArgument = cgArguments.get(i);
			CGParameter cgParameter = cgParameters.get(i);
			CGTypeId cgTypeId = cgParameter.getTypeId();
			TypeDescriptor parameterTypeDescriptor = codeGenerator.getBoxedDescriptor(ClassUtil.nonNullState(cgTypeId.getElementId()));
			CGValuedElement argument = cg2javaVisitor.getExpression(cgArgument);
			js.appendReferenceTo(parameterTypeDescriptor, argument);
		}
		js.append(");\n");
		return true;
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
		CGCachedOperation cgCachedOperation = (CGCachedOperation)cgOperation;
		Operation asOperation = CGUtil.getAST(cgCachedOperation);
	//	localContext = globalContext.basicGetLocalContext(cgCachedOperation);
		boolean isVirtualDispatcher = cg2javaVisitor.isVirtualDispatcher(cgCachedOperation);
	//	try {
			String operationClassName = cg2javaVisitor.getNativeOperationClassName(cgCachedOperation);
			if (isVirtualDispatcher) {
				js.append("protected class ");
				js.append(operationClassName);
				js.append(" extends ");
				js.appendClassReference(null, AbstractDispatchOperation.class);
				js.pushClassBody(operationClassName);
				doCachedOperationDispatchInstaller(cg2javaVisitor, js, cgCachedOperation);
				js.append("\n");
				doCachedOperationEvaluate(cg2javaVisitor, js, cgCachedOperation);
				js.popClassBody(false);
				//
				js.append("\n");
				doCachedOperationClassInstance(cg2javaVisitor, js, cgCachedOperation);
			}
			else {
				LanguageExpression expressionInOCL = asOperation.getBodyExpression();
				String title = PrettyPrinter.printName(asOperation);
				js.appendCommentWithOCL(title+"\n", expressionInOCL);
				//
				js.append("public class ");
				js.append(operationClassName);
				js.append(" extends ");
				js.appendClassReference(null, AbstractEvaluationOperation.class);
				js.pushClassBody(operationClassName);
				doCachedOperationBasicEvaluate(cg2javaVisitor, js, cgCachedOperation);
				js.append("\n");
				doCachedOperationEvaluate(cg2javaVisitor, js, cgCachedOperation);
				js.popClassBody(false);
				//
				if (cgCachedOperation.getVirtualOperations().size() <= 0) {
					js.append("\n");
					doCachedOperationClassInstance(cg2javaVisitor, js, cgCachedOperation);
				}
				else {
					js.append("\n");
					doCachedOperationClassDirectInstance(cg2javaVisitor, js, cgCachedOperation);
				}
			}
	//	}
	//	finally {
	//		localContext = null;
	//	}
		return true;
	}
}
