/*******************************************************************************
 * Copyright (c) 2013, 2021 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.java.types;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGUnboxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.JavaLocalContext;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.values.RealValue;

/**
 * A RealValueDescriptor describes the boxed unbounded polymorphic representation of an OCL Real.
 */
public class RealValueDescriptor extends BoxedValueDescriptor
{
	public RealValueDescriptor(@NonNull ElementId elementId) {
		super(elementId, RealValue.class);
	}

/*	@Override
	public @NonNull Boolean appendEcore(@NonNull JavaStream js, @NonNull JavaLocalContext<@NonNull ?> localContext, @NonNull CGEcoreExp cgEcoreExp,
			@NonNull CGValuedElement unboxedValue) {
		js.appendDeclaration(cgEcoreExp);
		js.append(" = ");
		if (!unboxedValue.isNonNull()) {
			js.appendReferenceTo(unboxedValue);
			js.append(" == null ? null : ");
		}
		js.appendClassReference(null, ValueUtil.class);
		js.append(".realValueOf(");
		js.appendReferenceTo(unboxedValue);
		js.append(")");
		js.append(";\n");
		return true;
	} */

	@Override
	public @NonNull Boolean appendUnboxStatements(@NonNull JavaStream js, @NonNull JavaLocalContext<@NonNull ?> localContext,
			@NonNull CGUnboxExp cgUnboxExp, @NonNull CGValuedElement boxedValue) {
		js.appendDeclaration(cgUnboxExp);
		js.append(" = ");
		js.appendValueName(boxedValue);
		js.append(".asNumber();\n");
		return true;
	}

	@Override
	protected @NonNull EcoreDescriptor createEcoreDescriptor() {
		return new EcoreDoubleObjectDescriptor(elementId);
	}

	@Override
	protected @NonNull UnboxedDescriptor createUnboxedDescriptor() {
		return new RealObjectDescriptor(elementId);
	}
}