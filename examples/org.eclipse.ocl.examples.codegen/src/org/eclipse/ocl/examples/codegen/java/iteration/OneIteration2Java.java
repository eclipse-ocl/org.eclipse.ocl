/*******************************************************************************
 * Copyright (c) 2013, 2020 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
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
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.messages.PivotMessages;

public class OneIteration2Java extends AbstractAccumulation2Java
{
	public static final @NonNull OneIteration2Java INSTANCE = new OneIteration2Java();

	@Override
	public void appendAccumulatorInit(@NonNull JavaStream js, @NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		js.appendFalse();
	}

	@Override
	public boolean appendUpdate(@NonNull JavaStream js, @NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		CGValuedElement cgBody = getBody(cgIterationCallExp);
		if (cgBody.getASTypeId() == TypeId.BOOLEAN) {
			CGIterator cgAccumulator = getAccumulator(cgIterationCallExp);
			js.append("if (");
			js.appendNotEqualsBoolean(cgBody, false);
			js.append(") {			// Carry on till something found\n");
			js.pushIndentation(null);
			js.append("if (");
			js.appendValueName(cgAccumulator);
			js.append(") {\n");
			js.pushIndentation(null);
			js.appendAssignBooleanLiteral(true, cgIterationCallExp, false);
			js.append("break;\n");
			js.popIndentation();
			js.append("}\n");
			js.append("else {\n");
			js.pushIndentation(null);
			js.appendAssignBooleanLiteral(true, cgAccumulator, true);
			js.popIndentation();
			js.append("}\n");
			js.popIndentation();
			js.append("}\n");
			return true;
		}
		else {
			return js.appendThrowInvalidValueException(PivotMessages.NonBooleanBody, "one");
		}
	}

	@Override
	public @Nullable CGTypeId getAccumulatorTypeId(@NonNull CodeGenAnalyzer analyzer, @NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		return analyzer.getTypeId(TypeId.BOOLEAN);
	}
}