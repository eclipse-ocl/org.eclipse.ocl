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
import org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.types.JavaTypeId;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.values.InvalidValueException;

public class ForAllIteration2Java extends AbstractIteration2Java
{
	public static final @NonNull ForAllIteration2Java INSTANCE = new ForAllIteration2Java();

	@Override
	public void appendAccumulatorInit(@NonNull JavaStream js, @NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		js.appendTrue();
	}

	@Override
	public boolean appendFinalValue(@NonNull JavaStream js, @NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		CGIterator cgAccumulator = CGUtil.getAccumulator(cgIterationCallExp);
		CGValuedElement cgBody = getBody(cgIterationCallExp);
		if (!cgBody.isNonNull()) {
			js.append("if (");
			js.appendValueName(cgAccumulator);
			js.append(" == null) {\n");
			js.pushIndentation(null);
			js.appendValueName(cgIterationCallExp);
			js.append(" = null;\n");
			js.popIndentation();
			js.append("}\n");
			js.append("else ");
		}
		js.append("if (");
		js.appendEqualsBoolean(cgAccumulator, true);
		js.append(") {\n");
		js.pushIndentation(null);
		js.appendAssignBooleanLiteral(true, cgIterationCallExp, true);
		js.popIndentation();
		js.append("}\n");
		js.append("else {\n");
		js.pushIndentation(null);
		js.append("throw (");
		js.appendClassReference(null, InvalidValueException.class);
		js.append(")");
		js.appendValueName(cgAccumulator);
		js.append(";\n");
		js.popIndentation();
		js.append("}\n");
		return true;
	}

	@Override
	public boolean appendUpdate(@NonNull JavaStream js, @NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		CGInvalid cgInvalidValue;
		CGIterator cgAccumulator = CGUtil.getAccumulator(cgIterationCallExp);
		CGValuedElement cgBody = getBody(cgIterationCallExp);
		//		if ((cgBody.getASTypeId() == TypeId.BOOLEAN) || (cgBody.getASTypeId() == TypeId.OCL_VOID)) {
		if (cgBody.isTrue()) {
			js.appendAssignBooleanLiteral(true, cgIterationCallExp, true);
			js.append("break;\n");
		}
		else if (cgBody.isFalse()) {
			js.appendAssignBooleanLiteral(true, cgIterationCallExp, false);
			js.append("break;\n");
		}
		else if (cgBody.isNull()) {
			js.appendValueName(cgIterationCallExp);
			js.append(" = null;\n");
			js.append("break;\n");
		}
		else if ((cgInvalidValue = cgBody.getInvalidValue()) != null) {
			js.append("throw ");
			js.appendValueName(cgInvalidValue);
			js.append(";\n");
			return false;
		}
		else if (cgBody.isConstant()) {
			return js.appendThrowInvalidValueException(PivotMessages.NonBooleanBody, "forAll");
		}
		else {
			js.append("if (");
			js.appendEqualsBoolean(cgBody, false);
			js.append(") {					// Normal unsuccessful body evaluation result\n");
			js.pushIndentation(null);
			js.appendAssignBooleanLiteral(true, cgIterationCallExp, false);
			js.append("break;														// Stop immediately \n");
			js.popIndentation();
			js.append("}\n");
			//
			js.append("else if (");
			js.appendEqualsBoolean(cgBody, true);
			js.append(") {				// Normal successful body evaluation result\n");
			js.pushIndentation(null);
			js.append(";															// Carry on\n");
			js.popIndentation();
			js.append("}\n");
			//
			if (!cgBody.isNonNull()) {
				js.append("else if (");
				js.appendValueName(cgBody);
				js.append(" == null) {								// Abnormal null body evaluation result\n");
				js.pushIndentation(null);
				js.append("if (");
				js.appendEqualsBoolean(cgAccumulator, true);
				js.append(") {\n");
				js.pushIndentation(null);
				js.appendValueName(cgAccumulator);
				js.append(" = null;										// Cache a null failure\n");
				js.popIndentation();
				js.append("}\n");
				js.popIndentation();
				js.append("}\n");
			}
			//
			if (!cgBody.isNonInvalid()) {
				js.append("else if (");
				js.appendValueName(cgBody);
				js.append(" instanceof ");
				js.appendClassReference(null, InvalidValueException.class);
				js.append(") {		// Abnormal exception evaluation result\n");
				js.pushIndentation(null);
				js.appendValueName(cgAccumulator);
				js.append(" = ");
				js.appendValueName(cgBody);
				js.append(";									// Cache an exception failure\n");
				js.popIndentation();
				js.append("}\n");
			}
			//
			js.append("else {															// Impossible badly typed result\n");
			js.pushIndentation(null);
			js.appendValueName(cgAccumulator);
			js.append(" = new ");
			js.appendClassReference(null, InvalidValueException.class);
			js.append("(");
			js.appendClassReference(null, PivotMessages.class);
			js.append(".NonBooleanBody, \"forAll\");\n");
			js.popIndentation();
			js.append("}\n");
		}
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
