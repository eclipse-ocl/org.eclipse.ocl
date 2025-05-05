/*******************************************************************************
 * Copyright (c) 2025 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.java.iteration;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.types.JavaTypeId;
import org.eclipse.ocl.pivot.LoopExp;

public class SearchIteration2Java extends AbstractAccumulation2Java
{
	public static final @NonNull SearchIteration2Java INSTANCE = new SearchIteration2Java();

	@Override
	public void appendAccumulatorInit(@NonNull JavaStream js, @NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		CGIterator cgAccumulator = getAccumulator(cgIterationCallExp);
		js.appendValueName(cgAccumulator.getInit());
	}

	@Override
	public boolean appendFinalValue(@NonNull JavaStream js, @NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		CGValuedElement cgBody2 = cgIterationCallExp.getBodies().get(2);
		js.appendCommentWithOCL(null, cgBody2.getAst());
		if (js.appendLocalStatements(cgBody2)) {
			js.append("//\n");
		}
		js.appendValueName(cgIterationCallExp);
		js.append(" = ");
		js.appendValueName(cgBody2);
		js.append(";\n");
		js.append("break;\n");
		return false;
	}

	@Override
	public boolean appendUpdate(@NonNull JavaStream js, @NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		CGValuedElement cgBody0 = cgIterationCallExp.getBodies().get(0);
		if (cgBody0.isInvalid()) {
			js.appendValueName(cgBody0);
			return true;
		}
		CGIterator cgAccumulator = getAccumulator(cgIterationCallExp);
		js.appendValueName(cgAccumulator);
		js.append(" = ");
		js.appendValueName(cgBody0);
		js.append(";\n");
		CGValuedElement cgBody1 = cgIterationCallExp.getBodies().get(1);
		js.appendCommentWithOCL(null, cgBody1.getAst());
		if (js.appendLocalStatements(cgBody1)) {
			js.append("//\n");
		}
		js.append("if (");
		js.appendEqualsBoolean(cgBody1, true);
		js.append(") {			// Stop on break.\n");
		js.pushIndentation(null);
		CGValuedElement cgBody2 = cgIterationCallExp.getBodies().get(2);
		js.appendCommentWithOCL(null, cgBody2.getAst());
		if (js.appendLocalStatements(cgBody2)) {
			js.append("//\n");
		}
		js.appendValueName(cgIterationCallExp);
		js.append(" = ");
		js.appendValueName(cgBody2);
		js.append(";\n");
		js.append("break;\n");
		js.popIndentation();
		js.append("}\n");
		return true;
	}

	@Override
	public @Nullable CGTypeId getAccumulatorTypeId(@NonNull CodeGenAnalyzer analyzer, @NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		return analyzer.getTypeId(new JavaTypeId(Object.class));
	}

	@Override
	public boolean isNonNullAccumulator(@NonNull LoopExp element) {
		return element.isNonNull();
	}
}
