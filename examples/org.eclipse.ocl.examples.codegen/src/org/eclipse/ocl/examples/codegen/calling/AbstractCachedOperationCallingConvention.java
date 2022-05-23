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
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaGlobalContext;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
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

	protected void doCachedOperationClassInstance(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
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
		js.append(getNativeOperationInstanceName(asOperation));
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

	protected abstract @NonNull String getNativeOperationClassName(@NonNull CGOperation cgOperation);

	@Override
	public boolean needsNestedClass() {
		return false;
	}
}
