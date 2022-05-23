/*******************************************************************************
 * Copyright (c) 2013, 2022 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.oclinjunit;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;

/**
 * A CG2JavaClassVisitor supports generation of an OCL expression as the LibraryOperation INSTANCE of a Java Class.
 */
public class JUnitCG2JavaClassVisitor extends CG2JavaVisitor<@NonNull JUnitCodeGenerator>
{
	protected final @NonNull ExpressionInOCL expInOcl;
	protected final @Nullable Iterable<@NonNull CGValuedElement> sortedGlobals;

	public JUnitCG2JavaClassVisitor(@NonNull JUnitCodeGenerator codeGenerator,
			@NonNull ExpressionInOCL expInOcl, @Nullable Iterable<@NonNull CGValuedElement> sortedGlobals) {
		super(codeGenerator);
		this.expInOcl = expInOcl;
		this.sortedGlobals = sortedGlobals;
	}

	@Override
	public @NonNull Boolean visitCGClass(@NonNull CGClass cgClass) {
		if (!isEmpty(cgClass)) {
			String className = CGUtil.getName(cgClass);
			CGPackage cgContainingPackage = cgClass.getContainingPackage();
			if (cgContainingPackage != null) {
				js.appendClassHeader(cgContainingPackage);
				Class<?> baseClass = genModelHelper.getAbstractOperationClass(expInOcl.getOwnedParameters().size());
				String title = cgClass.getName() + " provides the Java implementation for\n";
				js.appendCommentWithOCL(title, expInOcl);
				assert className != null;
				//	js.append("@SuppressWarnings(\"nls\")\n");
				js.append("public class " + className + " extends ");
				js.appendClassReference(null, baseClass);
				js.pushClassBody(className);
				if (sortedGlobals != null) {
					generateGlobals(sortedGlobals);
					js.append("\n");
				}
				if (expInOcl.getOwnedContext() != null) {
					boolean first = true;
					for (CGOperation cgOperation : cgClass.getOperations()) {
						if (!first) {
							js.append("\n");
						}
						cgOperation.accept(this);
						first = false;
					}
				}
				else {
					js.append("/*\n");
					js.append("«IF expInOcl.messageExpression != null»«(expInOcl.messageExpression as StringLiteralExp).stringSymbol»«ENDIF»\n");
					js.append("*/\n");
				}
				for (CGClass cgNestedClass : cgClass.getClasses()) {
				//	boolean first = true;
				//	if (!first) {
						js.append("\n");
				//	}
						cgNestedClass.accept(this);
				//	first = false;
				}
				js.popClassBody(false);
				assert js.peekClassNameStack() == null;
			}
			else {
				String title = cgClass.getName() + " provides the Java implementation for the additional non-Ecore features of\n";
				js.appendCommentWithOCL(title, cgClass.getAst());
				js.append("public static class " + className);
				js.pushClassBody(className);
				boolean first = true;
				for (CGProperty cgProperty : cgClass.getProperties()) {
					if (!first) {
						js.append("\n");
					}
					cgProperty.accept(this);
					first = false;
				}
				for (CGOperation cgOperation : cgClass.getOperations()) {
					if (!first) {
						js.append("\n");
					}
					cgOperation.accept(this);
					first = false;
				}
				js.popClassBody(false);
			}
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGLibraryOperation(@NonNull CGLibraryOperation cgOperation) {
		return visitCGOperation(cgOperation);
	}
}
