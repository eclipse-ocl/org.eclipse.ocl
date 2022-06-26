/*******************************************************************************
 * Copyright (c) 2022 Willink Transformation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.calling;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  ClassCallingConvention defines a particular style of Class declaration.
 */
public class JUnitClassCallingConvention implements ClassCallingConvention
{
	public static final @NonNull JUnitClassCallingConvention INSTANCE = new JUnitClassCallingConvention();

	/**
	 * Create the appropriate CGClass less properties and operation.
	 */
	@Override
	public @NonNull CGClass createCGClass(@NonNull AS2CGVisitor as2cgVisitor, org.eclipse.ocl.pivot.@NonNull Class asClass) {
		CGClass cgClass = CGModelFactory.eINSTANCE.createCGClass();
		cgClass.setAst(asClass);
	//	cgClass.setName(PivotUtil.getName(asClass));
		as2cgVisitor.getCodeGenerator().getGlobalNameManager().declareGlobalName(cgClass, PivotUtil.getName(asClass));		// XXX use hint
		return cgClass;

	}

	/**
	 * Generate the Java code for a Class declaration.
	 * Returns true if control flow continues, false if an exception throw has been synthesized.
	 */
	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGClass cgClass) {
		if (!isEmpty(cgClass)) {
			String className = CGUtil.getName(cgClass);
			CGPackage cgContainingPackage = cgClass.getContainingPackage();
			if (cgContainingPackage != null) {
				js.appendClassHeader(cgContainingPackage);
				org.eclipse.ocl.pivot.Class asClass = CGUtil.getAST(cgClass);
				Operation asOperation = asClass.getOwnedOperations().get(0);
				ExpressionInOCL expInOcl = (ExpressionInOCL)asOperation.getBodyExpression();
				Class<?> baseClass = cg2javaVisitor.getGenModelHelper().getAbstractOperationClass(expInOcl.getOwnedParameters().size());
				String title = cgClass.getName() + " provides the Java implementation for\n";
				js.appendCommentWithOCL(title, expInOcl);
				assert className != null;
				//	js.append("@SuppressWarnings(\"nls\")\n");
				js.append("public class " + className + " extends ");
				js.appendClassReference(null, baseClass);
				js.pushClassBody(className);
		// XXX		if (sortedGlobals != null) {
		//			generateGlobals(sortedGlobals);
		//			js.append("\n");
		//		}
				if (expInOcl.getOwnedContext() != null) {
					boolean first = true;
					for (CGOperation cgOperation : cgClass.getOperations()) {
						if (!first) {
							js.append("\n");
						}
						cgOperation.accept(cg2javaVisitor);
						first = false;
					}
				}
				else {
					js.append("/*\n");
					js.append("«IF expInOcl.messageExpression != null»«(expInOcl.messageExpression as StringLiteralExp).stringSymbol»«ENDIF»\n");
					js.append("* /\n");
				}
				for (CGClass cgNestedClass : cgClass.getClasses()) {
				//	boolean first = true;
				//	if (!first) {
						js.append("\n");
				//	}
						cgNestedClass.accept(cg2javaVisitor);
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
					cgProperty.accept(cg2javaVisitor);
					first = false;
				}
				for (CGOperation cgOperation : cgClass.getOperations()) {
					if (!first) {
						js.append("\n");
					}
					cgOperation.accept(cg2javaVisitor);
					first = false;
				}
				js.popClassBody(false);
			}
		}
		return true;
	}

	protected boolean isEmpty(@NonNull CGClass cgClass) {
		for (CGOperation cgOperation : cgClass.getOperations()) {
			if (cgOperation.getCallingConvention().needsGeneration()) {
				return false;
			}
		}
		for (CGProperty cgProperty : cgClass.getProperties()) {
			if (cgProperty.getCallingConvention().needsGeneration()) {
				return false;
			}
		}
		List<@NonNull CGClass> cgClasses = CGUtil.getClassesList(cgClass);
		if (cgClasses.size() > 0) {
			for (CGClass cgNestedClass : cgClasses) {
				if (!isEmpty(cgNestedClass)) {
					return false;
				}
			}
		}
		return true;
	}
}
