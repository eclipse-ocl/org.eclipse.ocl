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

import java.lang.reflect.Method;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaLanguageSupport;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 */
public abstract class AbstractCachedOperationCallingConvention extends ConstrainedOperationCallingConvention	// CF ConstrainedOperationCallingConvention
{
	public static @NonNull String getNativeOperationDirectInstanceName(@NonNull Operation asOperation) {	// FIXME unique
		return "INST_" + getNativeOperationName(asOperation);
	}

	public static @NonNull String getNativeOperationInstanceName(@NonNull Operation asOperation) {	// FIXME unique
		return "INSTANCE_" + getNativeOperationName(asOperation);
	}

	public static @NonNull String getNativeOperationName(@NonNull Operation asOperation) {	// FIXME unique
		return ClassUtil.nonNullState(asOperation.getOwningClass()).getName() + "_" + asOperation.getName();
	}

	@Override
	public @NonNull CGOperation createCGOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
		return CGModelFactory.eINSTANCE.createCGCachedOperation();
	}

	@Override
	public @NonNull CGValuedElement createCGOperationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		throw new UnsupportedOperationException();
	}

	protected void doCachedOperationClassInstance(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
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

	protected void doCachedOperationEvaluate(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
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
		js.append(globalNameManager.getEvaluateName());
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
		js.append(globalNameManager.getEvaluationCacheName());
		js.append(".");
		js.append(globalNameManager.getGetResultName());
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

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		JavaCodeGenerator codeGenerator = cg2javaVisitor.getCodeGenerator();
		GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
		CGCachedOperationCallExp cgCachedOperationCallExp = (CGCachedOperationCallExp)cgOperationCallExp;
		CGCachedOperation cgOperation = (CGCachedOperation)CGUtil.getOperation(cgCachedOperationCallExp);
		Operation asOperation = CGUtil.getAST(cgOperation);
	//	boolean thisIsSelf = cgCachedOperationCallExp.isThisIsSelf();			// XXX obsolete ??? ---- false
		assert cgOperationCallExp.getCgThis() == null;
	//	CGValuedElement source = getExpression(cgOperationCallExp.getSource());
		List<CGValuedElement> cgArguments = cgOperationCallExp.getArguments();
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
		js.append(getNativeOperationInstanceName(asOperation));
		js.append(".");
		js.append(globalNameManager.getEvaluateName());
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
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
		Operation asOperation = CGUtil.getAST(cgOperation);
		Method jMethod =  JavaLanguageSupport.getOverriddenMethod(asOperation);
		if (jMethod != null) {
			js.append("@Override\n");
		}
		js.append("public ");
		js.appendTypeDeclaration(cgOperation);
		js.append(" ");
		js.appendValueName(cgOperation);
		appendParameterList(js, cgOperation);
		js.append(" {\n");
		js.pushIndentation(null);
		generateJavaOperationBody(cg2javaVisitor, js, cgOperation);
		js.popIndentation();
		js.append("}\n");
		return true;
	}

	protected void generateJavaOperationBody(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
		CGValuedElement body = cg2javaVisitor.getExpression(cgOperation.getBody());
		cg2javaVisitor.appendReturn(body);
	}

	protected @NonNull String getNativeOperationClassName(@NonNull CGOperation cgOperation) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean needsNestedClass() {
		return false;
	}

	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperationCallExp cgOperationCallExp) {
		CGCachedOperationCallExp cpCachedOperationCallExp = (CGCachedOperationCallExp)cgOperationCallExp;
		List<CGValuedElement> cgArguments = cpCachedOperationCallExp.getArguments();
		int iMax = cgArguments.size();
		for (int i = 0; i < iMax; i++) {			// Avoid CME from rewrite
			CGValuedElement cgArgument = cgArguments.get(i);
			if (i == 0) {
				boxingAnalyzer.rewriteAsGuarded(cgArgument, boxingAnalyzer.isSafe(cpCachedOperationCallExp), "source for '" + cpCachedOperationCallExp.getAsOperation() + "'");
			}
			boxingAnalyzer.rewriteAsBoxed(cgArgument);
		}
	}
}
