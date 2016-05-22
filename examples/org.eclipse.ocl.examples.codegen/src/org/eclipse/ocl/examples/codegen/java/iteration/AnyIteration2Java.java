/*******************************************************************************
 * Copyright (c) 2013, 2014 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.java.iteration;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;

public class AnyIteration2Java extends AbstractIteration2Java
{
	public static final @NonNull AnyIteration2Java INSTANCE = new AnyIteration2Java();
	
	@Override
	public boolean appendUpdate(@NonNull JavaStream js, @NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		CGValuedElement cgBody = getBody(cgIterationCallExp);
		if (js.getBehavioralASTypeId(cgBody) == TypeId.BOOLEAN) { 
			CGIterator cgIterator = getIterator(cgIterationCallExp);
			js.append("if (");
			js.appendValueName(cgBody);
			js.append(" != ");
			js.appendClassReference(ValueUtil.class);
			js.append(".FALSE_VALUE) {			// Carry on till something found\n");
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
		js.appendClassReference(InvalidValueException.class);
		js.append("(\"No matching content for 'any'\");\n");
		return false;
	}
}