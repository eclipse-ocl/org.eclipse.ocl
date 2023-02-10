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
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
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
import org.eclipse.ocl.pivot.internal.library.executor.AbstractEvaluationOperation;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrinter;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  CachedOperationCallingConvention defines the support for the call of a Complete OCL-defined operation.
 *  If defined as part of an OCL stdlib, he operation is invoked when called. If defined as part of a
 *  Complete OCL document or OCLinEcore enrichment, the operations is invoked via a cache to avoid re-execution.
 *
 *  -- only used by QVTd
 */
@Deprecated /* @deprecated a more appropriate calls shuld be used */
public class DefaultOperationCallingConvention extends AbstractUncachedOperationCallingConvention
{
	private static final @NonNull DefaultOperationCallingConvention INSTANCE = new DefaultOperationCallingConvention();

	public static @NonNull DefaultOperationCallingConvention getInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
		INSTANCE.logInstance(asOperation, maybeVirtual);
		return INSTANCE;
	}

	private static @NonNull String getNativeOperationDirectInstanceName(@NonNull Operation asOperation) {	// FIXME unique
//		return "INST_" + getNativeOperationName(asOperation);
		throw new UnsupportedOperationException();		// XXX
	}

	public static @NonNull String getNativeOperationInstanceName(@NonNull Operation asOperation) {	// FIXME unique
//		return "INSTANCE_" + getNativeOperationName(asOperation);
		throw new UnsupportedOperationException();		// XXX
	}

	private static @NonNull String getNativeOperationName(@NonNull Operation asOperation) {	// FIXME unique
//		return ClassUtil.nonNullState(asOperation.getOwningClass()).getName() + "_" + asOperation.getName();
		throw new UnsupportedOperationException();		// XXX
	}

	@Override
	public @NonNull CGCachedOperation createCGOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
		PivotMetamodelManager metamodelManager = analyzer.getMetamodelManager();
		assert metamodelManager.getImplementation(asOperation) instanceof ConstrainedOperation;
		org.eclipse.ocl.pivot.Package asPackage = PivotUtil.getOwningPackage(PivotUtil.getOwningClass(asOperation));
		assert !(asPackage instanceof Library);
		return CGModelFactory.eINSTANCE.createCGCachedOperation();
	}

	@Override
	public @NonNull CGValuedElement createCGOperationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		OCLExpression asSource = asOperationCallExp.getOwnedSource();
	//	assert asSource != null;
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
		Type sourceType = asSource != null ? asSource.getType() : null;
	//	generateDeclarationHierarchy(as2cgVisitor, sourceType, asOperation);
		Operation finalOperation = sourceType!= null ? analyzer.getCodeGenerator().isFinal(asOperation, (org.eclipse.ocl.pivot.Class)sourceType) : asOperation;	// FIXME cast
	//	NestedNameManager nameManager = analyzer.getNameManager();
	//	CGClass currentClass = nameManager.getCGClass();
	//	CGOperationCallExp cgCallExp;
		assert (finalOperation != null);
	//	if (finalOperation != null) {
			LanguageExpression bodyExpression = asOperation.getBodyExpression();
			assert bodyExpression != null;
		// XXX	CGValuedElement cgOperationCallExp2 = as2cgVisitor.inlineOperationCall(asOperationCallExp, bodyExpression);
		// XXX	assert cgOperationCallExp2 == null;
		//	cgCallExp = cachedOperationCall(as2cgVisitor, asOperationCallExp, currentClass, cgSource, finalOperation/*, null*/);
			CGCachedOperationCallExp cgOperationCallExp = CGModelFactory.eINSTANCE.createCGCachedOperationCallExp();
			List<CGValuedElement> cgArguments = cgOperationCallExp.getArguments();
			cgArguments.add(cgSource);
			cgOperationCallExp.setThisIsSelf(false);
			for (@NonNull OCLExpression asArgument : ClassUtil.nullFree(asOperationCallExp.getOwnedArguments())) {
				CGValuedElement cgArgument = analyzer.createCGElement(CGValuedElement.class, asArgument);
				cgArguments.add(cgArgument);
			}
			analyzer.initAst(cgOperationCallExp, asOperationCallExp, true);
	//	} else {
	//		Iterable<@NonNull Operation> overrides = as2cgVisitor.getMetamodelManager().getFinalAnalysis().getOverrides(asOperation);
	//		cgCallExp = cachedOperationCall(as2cgVisitor, asOperationCallExp, currentClass, cgSource, asOperation, overrides);
	//	}
			cgOperationCallExp.setReferredOperation(cgOperation);
		return cgOperationCallExp;
	}

/*	protected @NonNull CGOperationCallExp cachedOperationCall(@NonNull AS2CGVisitor as2cgVisitor, @NonNull OperationCallExp asOperationCallExp, @NonNull CGClass currentClass, CGValuedElement cgSource,
			@NonNull Operation asOperation/ *, @Nullable Iterable<@NonNull Operation> asOverrideOperations* /) {
		//	List<@NonNull CGCachedOperation> cgOperations = generateDeclarationHierarchy(as2cgVisitor, currentClass, asOperation, asOverrideOperations);
	//	List<@NonNull CGCachedOperation> cgOperations = new ArrayList<>();			// XXX
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
	//	if (asOverrideOperations != null) {
	//		CGOperation cgOperation = as2cgVisitor.getAnalyzer().basicGetVirtualCGOperation(asOperation);
	//		if (cgOperation == null) {
	//			cgOperation = as2cgVisitor.createVirtualCGOperationWithoutBody(asOperation, cgOperations);
	//			currentClass.getOperations().add(cgOperation);
	//		}
	//	}
		return cgOperationCallExp;
	} */





/*	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperationCallExp cgOperationCallExp) {
		// XXX should have been ForeignOperationCallingConvention
		if (!generateLocals(cg2javaVisitor, cgOperationCallExp)) {
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
		appendForeignOperationName(cg2javaVisitor, cgOperationCallExp);
		js.append("(");
		generateArgumentList(cg2javaVisitor, cgOperationCallExp);
		js.append(");\n");
		return true;
	} */

	protected void doCachedOperationBasicEvaluate(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		List<@NonNull CGParameter> cgParameters = ClassUtil.nullFree(cgOperation.getParameters());
		CGValuedElement body = cg2javaVisitor.getExpression(cgOperation.getBody());
		GlobalNameManager globalNameManager = cg2javaVisitor.getCodeGenerator().getGlobalNameManager();
		ExecutableNameManager executableNameManager = globalNameManager.useRootExecutableNameManager(cgOperation);
		CGVariable cgExecutorVariable = executableNameManager.lazyGetExecutorVariable();
		js.append("@Override\n");
		js.append("public ");
		//				boolean cgOperationIsInvalid = cgOperation.getInvalidValue() != null;
		//				js.appendIsCaught(!cgOperationIsInvalid, cgOperationIsInvalid);
		js.appendClassReference(false, Object.class);
		js.append(" basicEvaluate(");
		js.appendClassReference(true, Executor.class);
		js.append(" ");
		js.appendValueName(cgExecutorVariable);
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

	protected void doCachedOperationClassDirectInstance(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		Operation asOperation = (Operation) cgOperation.getAst();
		assert asOperation != null;
		String name = getNativeOperationClassName(cgOperation);
		js.append("protected final ");
		js.appendIsRequired(true);
		js.append(" ");
		js.append(name);
		js.append(" ");
		js.append(getNativeOperationDirectInstanceName(asOperation));
		js.append(" = new ");
		js.append(name);
		js.append("();\n");
	}

	protected void doCachedOperationClassInstance(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		Operation asOperation = (Operation) cgOperation.getAst();
		assert asOperation != null;
		String name = getNativeOperationClassName(cgOperation);
		js.append("protected final ");
		js.appendIsRequired(true);
		js.append(" ");
		js.append(name);
		js.append(" ");
		js.append(getNativeOperationInstanceName(asOperation));
		js.append(" = new ");
		js.append(name);
		js.append("();\n");
	}

	protected void doCachedOperationEvaluate(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		if ("_classescs2as_qvtm_qvtcas::classescs2as_qvtm_qvtcas.CACHED_OP_OclElement_c_c_unqualified_env_Class_o_e_32_c_32_lookup_c_c_LookupEnvironment_91_1_93($metamodel$::OclElement) : 'http://cs2as/tests/example2/env/1.0'::LookupEnvironment".equals(cgOperation.toString())) {
			getClass();		// XXX
		}
		GlobalNameManager globalNameManager = cg2javaVisitor.getCodeGenerator().getGlobalNameManager();
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
		js.appendName(globalNameManager.getEvaluateName());
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
		js.appendName(globalNameManager.getEvaluationCacheName());
		js.append(".");
		js.appendName(globalNameManager.getGetCachedEvaluationResultName());
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
	public @NonNull CGOperation generateDeclarationHierarchy(@NonNull AS2CGVisitor as2cgVisitor, @Nullable Type sourceType, @NonNull Operation asOperation) {		// XXX obsoleted by VirtualOperationCallingConvention
		CodeGenerator codeGenerator = as2cgVisitor.getCodeGenerator();
		CodeGenAnalyzer analyzer = codeGenerator.getAnalyzer();
	//	Type sourceType = ClassUtil.nonNullState(asSource.getType());
	//	Operation finalOperation = codeGenerator.isFinal(asOperation, (org.eclipse.ocl.pivot.Class)sourceType);	// FIXME cast
		PivotMetamodelManager metamodelManager = codeGenerator.getEnvironmentFactory().getMetamodelManager();
		FinalAnalysis finalAnalysis = metamodelManager.getFinalAnalysis();
		Operation finalOperation = (sourceType != null) ? finalAnalysis.isFinal(asOperation, metamodelManager.getCompleteClass(sourceType)) : null;
		Iterable<@NonNull Operation> asOverrideOperations = finalAnalysis.getOverrides(asOperation);
	//	List<@NonNull Operation> asNewOperations = new ArrayList<>();
	//	List<@NonNull CGOperation> cgOperations = new ArrayList<>();
	//	if (asOverrideOperations != null) {
			assert Iterables.contains(asOverrideOperations, asOperation);
			for (@NonNull Operation asOverride : asOverrideOperations) {
				CGOperation cgOperation = analyzer.basicGetCGOperation(asOverride);
				if (cgOperation == null) {
//					OperationCallingConvention callingConvention = codeGenerator.getCallingConvention(asOverride);
//					cgOperation = callingConvention.createCGOperationWithoutBody(as2cgVisitor, sourceType, asOverride);
//					if (cgOperation.getAst() == null) {
//						analyzer.installOperation(asOverride, cgOperation, callingConvention);
//					}
					OperationCallingConvention callingConvention = this; //codeGenerator.getCallingConvention(asOverride);
					cgOperation = callingConvention.createCGOperation(as2cgVisitor, sourceType, asOverride);
					assert cgOperation != null;
					assert cgOperation.getAst() == null;
//					as2cgVisitor.initAst(cgOperation, asOverride);
					LocalContext savedLocalContext = as2cgVisitor.pushLocalContext(cgOperation, asOverride);	// XXX redundant ??
//					as2cgVisitor.popLocalContext(savedLocalContext);
//					asNewOperations.add(asOverride);
				//	analyzer.installOperation(asOperation, cgOperation, this);
					as2cgVisitor.pushLocalContext(cgOperation, asOverride);
					as2cgVisitor.popLocalContext(savedLocalContext);
				//	asNewOperations.add(asOverride);
					//	cgOperations.add(cgOperation);
					analyzer.addExternalFeature(asOperation);
				}
			}
	//	}
	/ *	else {
			CGOperation cgOperation = as2cgVisitor.getFinalOperation2cgOperation(asOperation);
			if (cgOperation == null) {
				cgOperation = CGModelFactory.eINSTANCE.createCGCachedOperation();
				as2cgVisitor.installOperation(asOperation, cgOperation, this);
			//	asNewOperations.add(asOperation);
				cgOperations.add((CGCachedOperation) cgOperation);
				analyzer.addForeignFeature(asOperation);
			}
		} * /
	//	for (@NonNull Operation asNewOperation : asNewOperations) {
	//		CGOperation cgOperation = as2cgVisitor.visitOperation(asNewOperation);
	//		currentClass.getOperations().add(cgOperation);
	//	}
	//	return cgOperations;
		return as2cgVisitor.getAnalyzer().getCGOperation(asOperation);
	} */

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		CGCachedOperation cgCachedOperation = (CGCachedOperation)cgOperation;
		Operation asOperation = CGUtil.getAST(cgCachedOperation);
		assert cgCachedOperation.getFinalOperations().size() <= 0;
		String operationClassName = getNativeOperationClassName(cgCachedOperation);
		LanguageExpression expressionInOCL = asOperation.getBodyExpression();
		String title = PrettyPrinter.printName(asOperation);
		js.appendCommentWithOCL(title+"\n", expressionInOCL);
		//
		if (JavaCodeGenerator.CALLING_CONVENTION_COMMENTS.isActive()) {
			js.append("// " + cgOperation.getCallingConvention() + "\n");
		}
		js.append("public class ");
		js.append(operationClassName);
		js.append(" extends ");
		js.appendClassReference(null, AbstractEvaluationOperation.class);
		js.pushClassBody(operationClassName);
		js.appendOptionalBlankLine();					// XXX delete me
		doCachedOperationBasicEvaluate(cg2javaVisitor, cgCachedOperation);
		js.appendOptionalBlankLine();
		doCachedOperationEvaluate(cg2javaVisitor, cgCachedOperation);
		js.popClassBody(false);
		//
		if (cgCachedOperation.getVirtualOperations().size() <= 0) {
			js.appendOptionalBlankLine();
			doCachedOperationClassInstance(cg2javaVisitor, cgCachedOperation);
		}
		else {
			js.appendOptionalBlankLine();
			doCachedOperationClassDirectInstance(cg2javaVisitor, cgCachedOperation);
		}
		return true;
	}

//	@Override
	protected @NonNull String getNativeOperationClassName(@NonNull CGOperation cgOperation) {	// FIXME unique
		Operation asOperation = (Operation) cgOperation.getAst();
		assert asOperation != null;
		return "zzCACHE_" + getNativeOperationName(asOperation);
	}
}
