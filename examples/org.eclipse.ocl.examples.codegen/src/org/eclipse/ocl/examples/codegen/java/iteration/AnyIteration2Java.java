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
import org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.values.InvalidValueException;

public class AnyIteration2Java extends AbstractIteration2Java
{
	public static final @NonNull AnyIteration2Java INSTANCE = new AnyIteration2Java();

	@Override
	public boolean appendUpdate(@NonNull JavaStream js, @NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		CGValuedElement cgBody = getBody(cgIterationCallExp);
		if (cgBody.getASTypeId() == TypeId.BOOLEAN) {
			CGIterator cgIterator = getIterator(cgIterationCallExp);
			js.append("if (");
			js.appendNotEqualsBoolean(cgBody, false);
			js.append(") {			// Carry on till something found\n");
			js.pushIndentation(null);
			js.appendValueName(cgIterationCallExp);
			js.append(" = ");
			js.appendValueName(cgIterator);
			js.append(";\n");
			js.append("break;\n");
			js.popIndentation();
			js.append("}\n");
			return true;
		}
		else {
			return js.appendThrowInvalidValueException(PivotMessages.NonBooleanBody, "any");
		}
	}

	@Override
	public boolean appendFinalValue(@NonNull JavaStream js, @NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		js.append("throw new ");
		js.appendClassReference(null, InvalidValueException.class);
		js.append("(\"Nothing to return for ''any''\");\n");
		return false;
	}
}