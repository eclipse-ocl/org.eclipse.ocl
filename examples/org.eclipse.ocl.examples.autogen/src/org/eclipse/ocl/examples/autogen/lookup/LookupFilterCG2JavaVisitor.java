/*******************************************************************************
 * Copyright (c) 2014, 2019 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *   Adolfo Sanchez-Barbudo Herrera (University of York) - Lookup Environment/Visitor
 *******************************************************************************/
package org.eclipse.ocl.examples.autogen.lookup;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.autogen.java.AutoCG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.internal.evaluation.EvaluationCache;
import org.eclipse.ocl.pivot.internal.evaluation.ExecutorInternal.ExecutorInternalExtension;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * LookupCG2JavaVisitor refines the regular generation of Java code from an optimized Auto CG transformation tree
 * to add contributions that are inadequately represented by the CG model.
 */
public class LookupFilterCG2JavaVisitor extends AutoCG2JavaVisitor<@NonNull LookupFilterGenerator>
{
	public LookupFilterCG2JavaVisitor(@NonNull LookupFilterGenerator codeGenerator, @NonNull CGPackage cgPackage,
			@Nullable List<CGValuedElement> sortedGlobals) {
		super(codeGenerator, cgPackage, sortedGlobals);
	}

	@Override
	protected boolean doClassFields(@NonNull CGClass cgClass, boolean needsBlankLine) {
		if (needsBlankLine) {
			js.append("\n");
		}
		js.append("protected final ");
		js.appendClassReference(true, EvaluationCache.class);
		js.append(" "+JavaConstants.EVALUATION_CACHE_NAME);
		js.append(";\n");
		return super.doClassFields(cgClass, false);
	}

	@Override
	protected void doMoreClassMethods(@NonNull CGClass cgClass) {
		for (@NonNull CGOperation cgOperation : ClassUtil.nullFree(cgClass.getOperations())) {
			if (cgOperation instanceof CGCachedOperation) {
				Operation asOperation = ClassUtil.nonNullState((Operation) cgOperation.getAst());
				Iterable<@NonNull CGParameter> cgParameters = ClassUtil.nullFree(cgOperation.getParameters());
				Boolean isRequiredReturn = cgOperation.isRequired() ? true : null;
				js.append("\n");
				js.append("@Override\n");
				js.append("protected ");
				js.appendClassReference(isRequiredReturn, cgOperation);
				js.append(" ");
				js.append(ClassUtil.nonNullState(asOperation.getName()));
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
				js.append("return ");
				js.append(getNativeOperationInstanceName(asOperation));
				js.append(".evaluate(");
				isFirst = true;
				for (@NonNull CGParameter cgParameter : cgParameters) {
					if (!isFirst) {
						js.append(", ");
					}
					js.appendReferenceTo(cgParameter);
					isFirst = false;
				}
				js.append(");\n");
				js.popIndentation();
				js.append("}\n");
			}
		}
	}

	@Override
	protected void doConstructor(@NonNull CGClass cgClass) {
		js.append("public " + cgClass.getName() + "(");
		js.appendClassReference(true, Executor.class);
		js.append(" "+JavaConstants.EXECUTOR_NAME);
		// We add the original operation parameters
		addFilterParameters(cgClass); // The first and unique OP will have the filtering operation
		js.append(") {\n");
		js.pushIndentation(null);
		js.append("super(");
		js.appendClassReference(null, getFilteredType(cgClass));
		js.append(".class");
		js.append(");\n");

		addFilterPropsInit(cgClass);
		js.append("this." + JavaConstants.EXECUTOR_NAME + " = " +JavaConstants.EXECUTOR_NAME + ";\n");
		js.append("this." + JavaConstants.ID_RESOLVER_NAME + " = " + JavaConstants.EXECUTOR_NAME + ".getIdResolver();\n");
		js.append("this." + JavaConstants.EVALUATION_CACHE_NAME + " = ((");
		js.appendClassReference(null, ExecutorInternalExtension.class);
		js.append(")" + JavaConstants.EXECUTOR_NAME + ").getEvaluationCache();\n");

		js.popIndentation();
		js.append("}\n");
	}


	private void addFilterParameters(CGClass cgClass) {
		for (CGProperty filteringVar : context.getFilteringVars(cgClass)) {
			js.append(",");
			js.appendDeclaration(filteringVar);
		}
	}

	private void addFilterPropsInit(CGClass cgClass) {
		for (CGProperty filteringVar : context.getFilteringVars(cgClass)) {
			String varName = filteringVar.getValueName();
			js.append("this.");
			js.append(varName);
			js.append(" =  ");
			js.append(varName);
			js.append(";\n");
		}
	}

	private CGValuedElement getFilteredType(CGClass cgClass) {

		//We get the first CGoperation, and the parameter will refer to the filteredType
		CGOperation cgFilteringOp = cgClass.getOperations().get(0);
		CGParameter param = cgFilteringOp.getParameters().get(0);
		return param.getTypedValue();
	}



}
