/*******************************************************************************
 * Copyright (c) 2016, 2019 Willink Transformations, University of York and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Adolfo Sanchez-Barbudo Herrera (University of York)
 *******************************************************************************/
package org.eclipse.ocl.examples.autogen.lookup;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;

public class LookupUnqualifiedCG2JavaVisitor extends LookupVisitorsCG2JavaVisitor<@NonNull LookupUnqualifiedCodeGenerator> {

	public LookupUnqualifiedCG2JavaVisitor(
			@NonNull LookupUnqualifiedCodeGenerator codeGenerator,
			@NonNull CGPackage cgPackage,
			@Nullable List<CGValuedElement> sortedGlobals) {
		super(codeGenerator, cgPackage, sortedGlobals);
	}

	@Override
	protected void doInternalVisiting(@NonNull CGClass cgClass) {
		// We call parentEnv;
		js.append("\n");
		js.append("@Override\n");
		js.append("protected ");
		js.appendClassReference(false, context.getVisitorResultClass());
		js.append(" doVisiting(");
		js.appendClassReference(true, context.getVisitableClass());
		js.append(" visitable) {\n");
		js.pushIndentation(null);
		js.append("return parentEnv((");
		js.appendClassReference(null, EObject.class);
		js.append(")");
		js.append("visitable);\n");
		js.popIndentation();
		js.append("}\n");
	}

	@Override
	protected void doMoreClassMethods(@NonNull CGClass cgClass) {
		super.doMoreClassMethods(cgClass);
		doParentEnv(cgClass);
	}

	/**
	 * protected @Nullable Environment parentEnv(@NonNull EObject element) {
	 *    EObject parent = element.eContainer();
	 *    if (parent instanceof Visitable) {
	 *        this.child = element;
	 *        return (Environment)((Visitable)parent).accept(this);
	 *    }
	 *    else {
	 *        return null;
	 *    }
	 * }
	 */
	protected void doParentEnv(@NonNull CGClass cgClass) {
		js.append("\n");
		js.append("/**\n");
		js.append(" * Continue the search for matches in the parent of " + LookupVisitorsClassContext.ELEMENT_NAME + ".\n");
		js.append(" */\n");
		js.append("protected ");
		js.appendClassReference(false, context.getEnvironmentClass());
		js.append(" " + LookupVisitorsClassContext.PARENT_ENV_NAME + "(");
		js.appendClassReference(true, EObject.class);
		js.append(" " + LookupVisitorsClassContext.ELEMENT_NAME + ") {\n");
		js.pushIndentation(null);
		js.appendClassReference(null, EObject.class);
		js.append(" " + LookupVisitorsClassContext.PARENT_NAME + " = " + LookupVisitorsClassContext.ELEMENT_NAME + ".eContainer();\n");
		js.append("if (" + LookupVisitorsClassContext.PARENT_NAME + " instanceof ");
		js.appendClassReference(null, context.getVisitableClass());
		js.append(") {\n");
		js.pushIndentation(null);
		js.append("this.");
		js.appendReferenceTo(context.getChildProperty());
		js.append(" = " + LookupVisitorsClassContext.ELEMENT_NAME + ";\n");
		js.append("return ((");
		js.appendClassReference(null, context.getVisitableClass());
		js.append(")" + LookupVisitorsClassContext.PARENT_NAME + ").accept(this);\n");
		js.popIndentation();
		js.append("}\n");
		js.append("else {\n");
		js.pushIndentation(null);
		js.append("return "+LookupVisitorsClassContext.CONTEXT_NAME +";\n");
		js.popIndentation();
		js.append("}\n");
		js.popIndentation();
		js.append("}\n");
	}
}

