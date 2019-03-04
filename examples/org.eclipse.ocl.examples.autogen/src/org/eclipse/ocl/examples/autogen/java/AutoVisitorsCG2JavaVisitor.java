/*******************************************************************************
 * Copyright (c) 2013, 2019 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *   Adolfo Sanchez-Barbudo Herrera (University of York) - Lookup Environment/Visitor
 *******************************************************************************/
package org.eclipse.ocl.examples.autogen.java;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;

/**
 * AutoCG2JavaVisitor refines the regular generation of Java code from an optimized Auto CG transformation tree
 * to add contributions that are inadequately represented by the CG model.
 */
public abstract class AutoVisitorsCG2JavaVisitor<@NonNull CG extends AutoVisitorsCodeGenerator> extends AutoCG2JavaVisitor<CG>
{

	public AutoVisitorsCG2JavaVisitor(@NonNull CG codeGenerator, @NonNull CGPackage cgPackage,
			@Nullable List<CGValuedElement> sortedGlobals) {
		super(codeGenerator, cgPackage, sortedGlobals);

	}

	@Override
	protected void doMoreClassMethods(@NonNull CGClass cgClass) {
		if (context.isBaseVisitorsGeneration()) {
			doVisiting(cgClass);
		}
	}

	/**
	 * Derived classes might override
	 * @param cgClass
	 */
	protected void doVisiting(@NonNull CGClass cgClass) {
		js.append("\n");
		js.append("@Override\n");
		js.append("public ");
		js.appendClassReference(false, context.getVisitorResultClass());
		js.append(" visiting(");
		js.appendClassReference(true, context.getVisitableClass());
		js.append(" visitable) {\n");
		js.pushIndentation(null);
		js.append("throw new UnsupportedOperationException(\"");
		js.append("Visiting \"+visitable.toString()+\" is not supported by \\\"\" + getClass().getName() + \"\\\"\"");
		js.append(");\n");
		js.popIndentation();
		js.append("}\n");
	}

}
