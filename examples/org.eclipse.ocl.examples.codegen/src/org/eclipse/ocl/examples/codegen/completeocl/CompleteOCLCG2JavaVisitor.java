/*******************************************************************************
 * Copyright (c) 2013, 2016 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.completeocl;

import java.util.List;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;

/**
 * A CompleteOCLCG2JavaVisitor supports generation of an OCL expression as the LibraryOperation INSTANCE of a Java Class.
 */
public class CompleteOCLCG2JavaVisitor extends CG2JavaVisitor<@NonNull CompleteOCLCodeGenerator>
{
	protected final @NonNull CGPackage cgPackage;
	protected final @Nullable List<CGValuedElement> sortedGlobals;

	public CompleteOCLCG2JavaVisitor(@NonNull CompleteOCLCodeGenerator codeGenerator,
			@NonNull CGPackage cgPackage, @Nullable List<CGValuedElement> sortedGlobals) {
		super(codeGenerator);
		this.cgPackage = cgPackage;
		this.sortedGlobals = sortedGlobals;
	}

	@Override
	public @NonNull Set<String> getAllImports() {
		return globalContext.getImports();
	}

	/*	@Override
	public @NonNull Boolean visitCGClass(@NonNull CGClass cgClass) {
		js.appendClassHeader(cgClass.getContainingPackage());
		Class<?> baseClass = genModelHelper.getAbstractOperationClass(expInOcl.getOwnedParameters());
		String title = cgClass.getName() + " provides the Java implementation for\n";
		js.appendCommentWithOCL(title, expInOcl);
		String className = cgClass.getName();
		js.append("@SuppressWarnings(\"nls\")\n");
		js.append("public class " + className + " extends ");
		js.appendClassReference(baseClass);
		js.append("\n");
		js.append("{\n");
		js.pushIndentation(null);
		if (sortedGlobals != null) {
			generateGlobals(sortedGlobals);
		}
		js.append("\n");
		if (expInOcl.getOwnedContext() != null) {
			for (CGOperation cgOperation : cgClass.getOperations()) {
				cgOperation.accept(this);
			}
		}
		else {
			js.append("/*\n");
			js.append("«IF expInOcl.messageExpression != null»«(expInOcl.messageExpression as StringLiteralExp).stringSymbol»«ENDIF»\n");
			js.append("* /\n");
		}
		js.popIndentation();
		js.append("}\n");
		return true;
	} */
}
