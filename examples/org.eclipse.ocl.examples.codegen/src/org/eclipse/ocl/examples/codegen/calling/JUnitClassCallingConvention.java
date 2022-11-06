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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Operation;

/**
 *  JUnitClassCallingConvention defines the style of a JUnit root Class declaration.
 */
public class JUnitClassCallingConvention extends AbstractClassCallingConvention
{
	public static final @NonNull JUnitClassCallingConvention INSTANCE = new JUnitClassCallingConvention();

	/**
	 * Generate the Java code for a Class declaration.
	 * Returns true if control flow continues, false if an exception throw has been synthesized.
	 */
	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGClass cgClass) {
		String className = CGUtil.getName(cgClass);
		org.eclipse.ocl.pivot.Class asClass = CGUtil.getAST(cgClass);
		js.appendClassHeader(asClass);
		Operation asOperation = asClass.getOwnedOperations().get(0);
		ExpressionInOCL expInOcl = (ExpressionInOCL)asOperation.getBodyExpression();
		Class<?> baseClass = cg2javaVisitor.getGenModelHelper().getAbstractOperationClass(expInOcl.getOwnedParameters().size());
		String title = cgClass.getName() + " provides the Java implementation for";
		js.appendCommentWithOCL(title, expInOcl);
		assert className != null;
		//	js.append("@SuppressWarnings(\"nls\")\n");
		js.append("public class " + className + " extends ");
		js.appendClassReference(null, baseClass);
	//	appendSuperTypes(js, cgClass);
		js.pushClassBody(className);
		js.append("\n");					// XXX delete me
		Iterable<@NonNull CGValuedElement> sortedGlobals = cg2javaVisitor.getAnalyzer().getGlobals();
		if (sortedGlobals != null) {
			cg2javaVisitor.generateGlobals(sortedGlobals);
			js.append("\n");
		}
		if (expInOcl.getOwnedContext() != null) {
			generateOperations(cg2javaVisitor, js, cgClass);
		}
		else {
			js.append("/*\n");
			js.append("«IF expInOcl.messageExpression != null»«(expInOcl.messageExpression as StringLiteralExp).stringSymbol»«ENDIF»\n");
			js.append("* /\n");
		}
		generateClasses(cg2javaVisitor, js, cgClass);
		js.popClassBody(false);
		assert js.peekClassNameStack() == null;
		return true;
	}
}
