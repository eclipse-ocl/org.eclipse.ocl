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
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

public class IsUniqueIteration2Java extends AbstractAccumulation2Java
{
	public static final @NonNull IsUniqueIteration2Java INSTANCE = new IsUniqueIteration2Java();

	@Override
	public void appendAccumulatorInit(@NonNull JavaStream js, @NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		CGTypeId cgAccumulatorId = cgIterationCallExp.getSource().getTypeId();
		ElementId elementId = cgAccumulatorId.getElementId();			// MapTypeId or CollectionTypeId
		js.appendClassReference(null, ValueUtil.class);
		js.append(".createSetAccumulatorValue(");
		js.appendIdReference(elementId != null ? elementId : TypeId.OCL_VOID);
		js.append(")");
	}

	@Override
	public boolean appendFinalValue(@NonNull JavaStream js, @NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		return js.appendAssignment(cgIterationCallExp, js.getCodeGenerator().getAnalyzer().getBoolean(true));
	}

	@Override
	public boolean appendUpdate(@NonNull JavaStream js, @NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		CGValuedElement cgBody = getBody(cgIterationCallExp);
		CGIterator cgAccumulator = getAccumulator(cgIterationCallExp);
		js.append("if (");
		js.appendValueName(cgAccumulator);
		js.append(".includes(");
		js.appendValueName(cgBody);
		js.append(") == ");
		js.appendClassReference(null, ValueUtil.class);
		js.append(".TRUE_VALUE) {\n");
		{
			js.pushIndentation(null);
			js.appendAssignBooleanLiteral(true, cgIterationCallExp, false);
			js.append("break;			// Abort after second find\n");
			js.popIndentation();
		}
		js.append("}\n");
		js.append("else {\n");
		{
			js.pushIndentation(null);
			js.appendValueName(cgAccumulator);
			js.append(".add(");
			js.appendValueName(cgBody);
			js.append(");\n");
			js.popIndentation();
		}
		js.append("}\n");
		return true;
	}

	@Override
	public @Nullable CGTypeId getAccumulatorTypeId(@NonNull CodeGenAnalyzer analyzer, @NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		Class<?> accumulatorClass = getAccumulatorClass(analyzer, TypeId.SET);
		return analyzer.getTypeId(JavaConstants.getJavaTypeId(accumulatorClass));
	}
}